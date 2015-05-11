/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color; 
import java.util.ArrayList;
import javalib.worldimages.*; 
import javalib.worldimages.WorldImage;
/**
 *
 * @author 栗粒盐
 */
public class Char implements Element{
    Posn posn;    
    
    public Posn posn() {
        return this.posn;
    }
    public boolean inRange() {
        return ((this.posn.x > 0) &&
                (this.posn.x < 600) &&
                (this.posn.y > 0) &&
                (this.posn.y < 600));
    }
    public Char(Posn p) {
        this.posn = p;
    }
    public Char up() {
        int le = this.posn.y - 50;
        return(new Char(new Posn(this.posn.x,le)));
    }
    public Char down() {
        int le = this.posn.y + 50;
        return(new Char(new Posn(this.posn.x,le)));
    }
    public Char left() {
        int le = this.posn.x - 50;
        return(new Char(new Posn(le,this.posn.y)));
    }    
    public Char right() {
        int le = this.posn.x + 50;
        return(new Char(new Posn(le,this.posn.y)));
    }
    
    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, 50, 50, Color.white);
    }

}
