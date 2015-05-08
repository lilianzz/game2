/**
 * 
 */
package phase;

import game.GameWorld;

import java.awt.Color;
import java.io.IOException;

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
	
	final static int selectionN = 4;
	final static int selectionM = 2;
	
	final static int sum = 1; //The sum of items in the Store
	final static String signBomb = "BOMB";
	final static String signNotReady = "TBC"; //Default sign
	
	public StoreWorld() {
		selections = new Selections(selectionN,selectionM, 100,100, 500,500);
		selections.setSign(0, signBomb);
		for (int i = sum; i < selectionN*selectionM; i++) {
			selections.setSign(i, signNotReady);
		}
		
	}

    public WorldImage makeImage() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
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
            return choose(selections.getSelected());
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
