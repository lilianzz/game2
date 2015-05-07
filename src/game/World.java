/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author ���绮����
 */
import java.awt.Color; 
import java.util.ArrayList;
import javalib.worldimages.*;
import javalib.worldimages.WorldImage;

public class World extends javalib.funworld.World {
    
    int coin;
    Char c;
    Blocks blocks;
    Bombs bombs;
    Ghosts ghost;
    boolean alive;
    boolean suc;
    int ng;
    
    public World() {
        this.c = new Char((new Posn(25,25)),0);
        this.blocks = new Blocks(3);
        ng = 3;
        this.ghost = new Ghosts(ng);
        this.bombs = new Bombs(4);
        
        blocks.remove(c.posn());
               
        this.alive = true;
       
    }
    /*
    public boolean randMove() {
        int t;
        for (int i = 0; i<g.size();i++) {
            t = (int) (Math.random() * 4);
            switch(t) {
                case 0: if (b.notIn(g.get(i).up()) && bs.notIn(g.get(i).up())) {
                        g.set(i,g.get(i).up());}
                    break;
                case 1: if (b.notIn(g.get(i).down()) && bs.notIn(g.get(i).down())) {
                    g.set(i,g.get(i).down());}                
                    break;
                case 2: if (b.notIn(g.get(i).left()) && bs.notIn(g.get(i).left())) {
                    g.set(i,g.get(i).left());}
                    break;
                case 3: if (b.notIn(g.get(i).right()) && bs.notIn(g.get(i).right())) {
                    g.set(i,g.get(i).right());}
                    break;
                    
            }
        }
        return true;
    }
    */
    
    
    
    
    public WorldImage makeImage() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.c.draw());
        if (!(this.blocks.draw() == null)) {
            com = new OverlayImages(com,this.blocks.draw());
        }
        if (!(this.bombs.number == 0)) {
            com = new OverlayImages(com,this.bombs.draw());
        }
        if (!(this.ghost.draw() == null)) {
            com = new OverlayImages(com,this.ghost.draw());
        }
        return(com);
    }
    
    public WorldImage makeEnd() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.c.draw());
        if (!(this.blocks.draw() == null)) {
            com = new OverlayImages(com,this.blocks.draw());
        }
        if (!(this.bombs.draw() == null)) {
            com = new OverlayImages(com,this.bombs.draw());
        }
        if (!(this.ghost.draw() == null)) {
            com = new OverlayImages(com,this.ghost.draw());
        }        
        TextImage s2 = new TextImage(
                new Posn(300,300),
                "GAME OVER:",
                40,
                Color.red
        );        
        return(new OverlayImages(com,s2));
    }
    
    public WorldEnd worldEnds() {
        boolean dead = !alive;
        return(new WorldEnd(dead,makeEnd()));
    }
    
    //  World onTick()
    //  Block drops
    //  Check for clear & color blend
    public World onTick() {        
        alive = bombs.Explode(blocks,c,ghost);
        ghost.randMove(blocks,bombs);
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
            if (blocks.notIn(c.left()) && bombs.notIn(c.left()) && c.left().inRange()) {
                    c = c.left();}
            return(this);
        } else
        if (k.equals("right")) {
            if (blocks.notIn(c.right()) && bombs.notIn(c.right())&& c.right().inRange()) {
                    c = c.right();}            
            return(this);
        } else
        if (k.equals("up")) {            
            if (blocks.notIn(c.up()) && bombs.notIn(c.up())&& c.up().inRange()) {
                    c = c.up();}        
            return(this);
        } else
        if (k.equals("down")) {                        
            if (blocks.notIn(c.down()) && bombs.notIn(c.down())&& c.down().inRange()) {
                    c = c.down();}
            return(this);
        } else
        if (k.equals("z")) {
            bombs.add(c.posn());
            return this;
        }
        return this;
    } 
}
