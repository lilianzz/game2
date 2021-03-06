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
public class Block implements Element {
    Posn posn;
    public Block(Posn p) {
        this.posn = p;
    }
    public Posn posn() {
        return this.posn;
    }
    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, 50, 50, Color.black);
    }

}
