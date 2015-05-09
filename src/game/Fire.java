/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;

import user.Config;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

/**
 *
 * @author 栗粒盐
 */
public class Fire {
    Posn posn;
    int time;
    public Fire(Posn p) {
        this.posn = p;
        time = Config.fireExistTime;
    }
    public int Type() {
        return 6;
    }
    public int ticks() {
    	return --time;
    }
    public Posn posn() {
        return this.posn;
    }
    
    public boolean contains(Posn posn) {
    	if (this.posn.x-25 < posn.x && posn.x < this.posn.x + 25 && this.posn.y - 25 < posn.y && posn.y < this.posn.y + 25) {
    		return true;
    	}
    	else return false;
    }
    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, 50, 50, Color.gray);
    }
}
