/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game2;

/**
 *
 * @author 栗粒盐
 */
import java.awt.Color; 
import java.util.ArrayList;
import javalib.worldimages.*;
import javalib.worldimages.WorldImage;

public class World extends javalib.funworld.World {
    
    int coin;
    Char c;
    Blocks b;
    Bombs bs;
    Ghosts g;
    boolean alive;
    boolean suc;
    int ng;
    
    public World() {
        this.c = new Char((new Posn(25,25)),0);
        this.b = new Blocks(3);
        b.remove(c.posn());
        this.bs = new Bombs(4);
        ng = 3;
        this.g = new Ghosts(ng);
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
        if (!(this.b.draw() == null)) {
            com = new OverlayImages(com,this.b.draw());
        }
        if (!(this.bs.number == 0)) {
            com = new OverlayImages(com,this.bs.draw());
        }
        if (!(this.g.draw() == null)) {
            com = new OverlayImages(com,this.g.draw());
        }
        return(com);
    }
    
    public WorldImage makeEnd() {
        WorldImage pic = new RectangleImage((new Posn(300,300)),600,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.c.draw());
        if (!(this.b.draw() == null)) {
            com = new OverlayImages(com,this.b.draw());
        }
        if (!(this.bs.draw() == null)) {
            com = new OverlayImages(com,this.bs.draw());
        }
        if (!(this.g.draw() == null)) {
            com = new OverlayImages(com,this.g.draw());
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
        alive = bs.Explode(b,c,g);
        g.randMove(b,bs);
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
            if (b.notIn(c.left()) && bs.notIn(c.left()) && c.left().inRange()) {
                    c = c.left();}
            return(this);
        } else
        if (k.equals("right")) {
            if (b.notIn(c.right()) && bs.notIn(c.right())&& c.right().inRange()) {
                    c = c.right();}            
            return(this);
        } else
        if (k.equals("up")) {            
            if (b.notIn(c.up()) && bs.notIn(c.up())&& c.up().inRange()) {
                    c = c.up();}        
            return(this);
        } else
        if (k.equals("down")) {                        
            if (b.notIn(c.down()) && bs.notIn(c.down())&& c.down().inRange()) {
                    c = c.down();}
            return(this);
        } else
        if (k.equals("z")) {
            bs.add(c.posn());
            return this;
        }              
        return this;
        
    } 
}
