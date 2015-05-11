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
    public Ghost(Posn p) {
        this.posn = p;
    }
    public boolean inRange() {
        return ((this.posn.x > 0) &&
                (this.posn.x < 600) &&
                (this.posn.y > 0) &&
                (this.posn.y < 600));
    }
	public void up() {
		posn.y -= 50;
	}
	public void down() {
		posn.y += 50;
	}
	public void left() {
		posn.x -= 50;
	}
	public void right() {
		posn.x += 50;
	}
    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, 50, 50, Color.yellow);
    }

}
