package phase;

import game.GameWorld;

import javalib.funworld.World;

import java.awt.Color;
import java.io.IOException;

import user.User;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import javalib.worldimages.FromFileImage;

public class MainWorld extends javalib.funworld.World{
	final static String signNewGame = "NEW GAME";
	final static String signLoadGame = "LOAD GAME";
	final static String signStore = "STORE";
	final static String signQuit = "QUIT";
	
	Selections selections;
	
	
	public MainWorld() throws IOException {
		User.loadGame();
		selections = new Selections(4,1, 100,100, 500,500);
		selections.setSign(0, 0, signNewGame);
		selections.setSign(1, 0, signLoadGame);
		selections.setSign(2, 0, signStore);
		selections.setSign(3, 0, signQuit);
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
    public MainWorld onTick() {        
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
        return this;
    } 
    
    public World choose(String sign) {
    	if (sign.equals(signNewGame)) {
    		try {
				User.newGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return new GameWorld();
    	}
    	else if (sign.equals(signLoadGame)) {
    		//User.loadGame();
    		return new GameWorld();
    	}
    	else if (sign.equals(signStore)) {
    		return new StoreWorld();
    	}
    	else if (sign.equals(signQuit)) {
    		
    	}
    	return this;
    }

}
