/**
 * 
 */
package phase;

import game.GameWorld;

import java.awt.Color;
import java.io.IOException;

import user.Config;
import user.User;
import javalib.funworld.World;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

/**
 * @author tanhao
 *
 */
public class StoreWorld extends World {
	Selections selections;
	MoneyBoard moneyBoard = new MoneyBoard();
	
	
	final static int selectionN = 4;
	final static int selectionM = 2;
	
	//final static int sum = 1; //The sum of items in the Store
	//final static String signBomb = "BOMB POWER";
	final static String signNotReady = "TBC"; //Default sign
	final static String signReturn = "RETURN";
	
	public StoreWorld() {
		/* Initiate selections */
		selections = new Selections(selectionN,selectionM, 100,0, 400,600);
		for (int i = 0; i < Config.itemNum; i++) 
			selections.setSign(i, Config.items[i].name());
		for (int i = Config.itemNum; i < selectionN*selectionM - 1; i++) {
			selections.setSign(i, signNotReady);
		}
		selections.setSign(selectionN * selectionM - 1, signReturn);
		
		/* Initiate moneyBoard */
		moneyBoard.setPos(50, 500, 50, 150);
	}
	/**
	 * Create the description of the selected item
	 * @return
	 */
	public WorldImage createDisp() {
		WorldImage pic = new RectangleImage(new Posn(300, 500), 560, 100, Color.pink);
		if (selections.getSelectedNum() < Config.itemNum) {
			Item item = Config.items[selections.getSelectedNum()];
			pic = new OverlayImages(pic, new TextImage(new Posn(300, 475), item.description, 25, Color.black));
			pic = new OverlayImages(pic, new TextImage(new Posn(100, 525), "NOW LEVEL:"+User.nowItemLevels[selections.getSelectedNum()], 25, Color.black));
			pic = new OverlayImages(pic, new TextImage(new Posn(300, 525), "MAX LEVEL:"+item.maxLevel, 25, Color.black));
			Color color;
			if (User.money >= item.cost()) color = Color.black;
			else color = Color.red;
			pic = new OverlayImages(pic, new TextImage(new Posn(500, 525), "COST:" + item.cost(), 25, color));
		}
		return pic;
	}
    public WorldImage makeImage() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        pic = new OverlayImages(pic, moneyBoard.draw());
        pic = new OverlayImages(pic, createDisp());
        pic = new OverlayImages(pic, selections.draw());
        return(pic);
    }
    
    public WorldImage makeEnd() {
        WorldImage pic = makeImage();
        TextImage s2 = new TextImage(
                new Posn(300,300),
                "THANK YOU",
                40,
                Color.red
        );        
        return(new OverlayImages(pic, s2));
    }
    
    public WorldEnd worldEnds() {
        return(new WorldEnd(false, makeEnd()));
    }
    
    //  World onTick()
    //  Block drops
    //  Check for clear & color blend
    public World onTick() {        
        return this;
        //return(new World(this.score, this.live, this.d, this.height));        
    }

    //  World onKeyEvent()
        /*  
        Move as directed if it is not stopped by the dead blocks or out of range
        */
    
    @SuppressWarnings("empty-statement")
    public World onKeyEvent(String k) {
        if (k.equals("left")) {
        	selections.left();
        } else
        if (k.equals("right")) {
        	selections.right();
        } else
        if (k.equals("up")) {            
            selections.up();
        } else
        if (k.equals("down")) {                        
            selections.down();
        } else
        if (k.equals("z")) {
        	if (selections.getSelectedNum() == selectionN * selectionM - 1) {
        		try {
					return new MainWorld();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	else {
        		Config.items[selections.getSelectedNum()].select();
        	}
        	//return choose(selections.getSelected());
        }
        else if(k.equals("x")) {
        	try {
				return new MainWorld();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
        return this;
    } 
    
    public World choose(String sign) {

    	return this;
    }

}
