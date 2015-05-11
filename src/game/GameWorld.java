/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author ��lilianzz���
 */
import java.awt.Color; 
import java.io.IOException;
import java.util.ArrayList;

import main.MainWorld;
import user.User;
import javalib.funworld.World;
import javalib.worldimages.*;

public class GameWorld extends javalib.funworld.World {
    
    int coin;
    Char c;
    Blocks blocks;
    Bombs bombs;
    Ghosts ghosts;
    boolean alive;
    boolean suc;
    final static int ng = 3;
    
    public GameWorld() {
        this.c = new Char((new Posn(25,25)));
        this.blocks = new Blocks(3);
        this.ghosts = new Ghosts(User.level);
        this.bombs = new Bombs(4);
        
        blocks.remove(c.posn());
               
        this.alive = true;
       
    }    
    
    public WorldImage makeImage() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.c.draw());
        if (!(this.blocks.draw() == null)) {
            com = new OverlayImages(com,this.blocks.draw());
        }
        com = new OverlayImages(com,this.bombs.draw());
        TextImage s2 = new TextImage(
                new Posn(500,50),
                "Life:" + User.life,
                40,
                Color.red
        ); 
        if (!(this.ghosts.draw() == null)) {
            com = new OverlayImages(com,this.ghosts.draw());
        }
        com = new OverlayImages(com, s2);
        return(com);
    }
    
    public WorldImage makeEnd() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.c.draw());
        if (!(this.blocks.draw() == null)) {
            com = new OverlayImages(com,this.blocks.draw());
        }
        com = new OverlayImages(com,this.bombs.draw());
        
        if (!(this.ghosts.draw() == null)) {
            com = new OverlayImages(com,this.ghosts.draw());
        }        
        TextImage s2 = new TextImage(
                new Posn(300,300),
                "NO MORE LIVES GAME OVER!",
                40,
                Color.white
        );        
        return(new OverlayImages(com,s2));
    }
    
    public WorldEnd worldEnds() {
        boolean dead = false;
        if (User.life == 0) { dead = true;}
        return(new WorldEnd(dead,makeEnd()));
    }
    
    //  World onTick()
    //  Block drops
    //  Check for clear & color blend
    public World onTick() {        
        alive = bombs.ticks(blocks,c,ghosts);
        ghosts.randMove(blocks,bombs);
        alive = alive && (!ghosts.capture(c));
        
        if (ghosts.ghostList.isEmpty()) {
        	User.levelUp();
        	return new GameSuccessWorld(makeImage());
        }
        if (!alive) {
            User.loseLife();
        	return new GameFailWorld(makeImage());}
        return this;    
    }

    //  World onKeyEvent()
        /*  
        Move as directed if it is not stopped by the dead blocks or out of range
        */
    
    @SuppressWarnings("empty-statement")
    public World onKeyEvent(String k) {
        if (k.equals("left")) {
            if (blocks.notIn(c.left()) && bombs.notIn(c.left()) && c.left().inRange()) {
                    c = c.left();}
            //return(this);
        } else
        if (k.equals("right")) {
            if (blocks.notIn(c.right()) && bombs.notIn(c.right())&& c.right().inRange()) {
                    c = c.right();}            
            //return(this);
        } else
        if (k.equals("up")) {            
            if (blocks.notIn(c.up()) && bombs.notIn(c.up())&& c.up().inRange()) {
                    c = c.up();}        
            //return(this);
        } else
        if (k.equals("down")) {                        
            if (blocks.notIn(c.down()) && bombs.notIn(c.down())&& c.down().inRange()) {
                    c = c.down();}
            //return(this);
        } else
        if (k.equals("z")) {
            bombs.add(c.posn());
            //return this;
        }
        else if (k.equals("x")) {
        	try {
				return new MainWorld();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        if (ghosts.capture(c)) {
            User.loseLife();
        	return new GameFailWorld(makeImage());
        }
        return this;
    } 
}
