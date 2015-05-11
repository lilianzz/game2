package game;

import java.awt.Color;
import java.io.IOException;

import main.MainWorld;
import javalib.funworld.World;
import javalib.worldimages.*;
import phase.Selections;
import store.StoreWorld;
import user.User;

public class GameFailWorld extends javalib.funworld.World{
	WorldImage basic;
	public GameFailWorld(WorldImage basic) {
		this.basic = basic;
		selections = new Selections(2,1, 200,100, 400,500);
		selections.setSign(0, signRetry);
		selections.setSign(1, signReturn);
	}
	final static String signRetry = "TRY AGAIN";
	final static String signReturn = "RETURN";
	
	Selections selections;
	
	


    public WorldImage makeImage() {
        WorldImage pic = basic;
        pic = new OverlayImages(pic, new FromFileImage(new Posn(300,105), "graph/bad_end.jpg"));
        pic = new OverlayImages(pic, selections.draw());
        return(pic);
    }
    
    public WorldImage makeEnd() {
        WorldImage pic = makeImage(); 
        TextImage s2 = new TextImage(
                new Posn(300,300),
                "NO MORE LIVES GAME OVER!",
                40,
                Color.white
        );        
        return(new OverlayImages(pic, s2));
    }
    
    public WorldEnd worldEnds() {        
        boolean dead = false;
        if (User.life == 0) { dead = true;}
        return(new WorldEnd(dead,makeEnd()));
    }
    
    //  World onTick()
    //  Block drops
    //  Check for clear & color blend
    public GameFailWorld onTick() {        
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
    	if (sign.equals(signRetry)) {
    		return new GameWorld();
    	}
    	else if (sign.equals(signReturn)) {
    		try {
				return new MainWorld();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return this;
    }

}
