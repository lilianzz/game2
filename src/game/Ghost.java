/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

/**
 *
 * @author 栗粒盐
 */
public class Ghost implements Element{
    Posn posn;    
    int coin;
    public Posn posn() {
    	
        return this.posn;
    }
    public int Type() {
        return 3;
    }
    public Ghost(Posn p) {
        this.posn = p;
    }
    public boolean inRange() {
        return ((this.posn.x > 0) &&
                (this.posn.x < 600) &&
                (this.posn.y > 0) &&
                (this.posn.y < 600));
    }
    public Ghost up() {
        int le = this.posn.y - 50;
        return(new Ghost(new Posn(this.posn.x,le)));
    }
    public Ghost down() {
        int le = this.posn.y + 50;
        return(new Ghost(new Posn(this.posn.x,le)));
    }
    public Ghost left() {
        int le = this.posn.x - 50;
        return(new Ghost(new Posn(le,this.posn.y)));
    }    
    public Ghost right() {
        int le = this.posn.x + 50;
        return(new Ghost(new Posn(le,this.posn.y)));
    }
    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, 50, 50, Color.yellow);
    }

}
