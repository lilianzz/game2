/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;
import java.awt.Color; 
import java.util.ArrayList;

import tool.Tool;
import javalib.worldimages.*; 

/**
 *
 * @author 栗粒盐
 */
public class Ghosts {
    ArrayList<Ghost> ghostList = new ArrayList<>(20);
    int number;
    public Ghosts(int n) {
        int x;
        int y;
        number = n;
        for (int i=0; i<n;i++) {
            x = (int) (Math.random()*12);
            x = x*50+25;
            y = (int) (Math.random()*12);
            y = y*50+25;            
            ghostList.add(new Ghost(new Posn(x,y)));
        }
    }
    
    public boolean randMove(Blocks b, Bombs bs) {
        /*
        int a = (int) Math.floor(Math.random() * 14);
        for (int i = 0; i<ghostList.size();i++) {
            t = (int) Math.floor(Math.random() * 5);
            switch(t) {
                case 0: if (b.notIn(ghostList.get(i).up()) && bs.notIn(ghostList.get(i).up()) && ghostList.get(i).up().inRange()) {
                        ghostList.set(i,ghostList.get(i).up());}
                    break;
                case 1: if (b.notIn(ghostList.get(i).down()) && bs.notIn(ghostList.get(i).down()) && ghostList.get(i).down().inRange()) {
                    ghostList.set(i,ghostList.get(i).down());}                
                    break;
                case 2: if (b.notIn(ghostList.get(i).left()) && bs.notIn(ghostList.get(i).left()) && ghostList.get(i).left().inRange()) {
                    ghostList.set(i,ghostList.get(i).left());}
                    break;
                case 3: if (b.notIn(ghostList.get(i).right()) && bs.notIn(ghostList.get(i).right()) && ghostList.get(i).right().inRange()) {
                    ghostList.set(i,ghostList.get(i).right());}
                    break;
                case 4: if (a==0) bs.add(ghostList.get(i).posn());
                    break;                    
            }
        }*/
        for (Ghost g:ghostList) {
        	int t = Tool.randInt(4);
        	switch (t) {
        	case 0: g.up(); break;
        	case 1: g.down(); break;
        	case 2: g.left(); break;
        	case 3: g.right(); break;
        	}
        	if (!(b.notIn(g) && bs.notIn(g) && g.inRange()))
	        	switch (t) {
	        	case 0: g.down(); break;
	        	case 1: g.up(); break;
	        	case 2: g.right(); break;
	        	case 3: g.left(); break;
	        	}
        }
        return true;
    }
    /**
     * Check whether the ghosts have met the character
     * @param c The character
     * @return
     */
    public boolean capture(Char c) {
    	for (Ghost g : ghostList) {
    		if (g.posn().x == c.posn().x && g.posn().y == c.posn().y) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public WorldImage draw() {        
        WorldImage t = new RectangleImage(new Posn(0,0),1,1,Color.blue);             
        for (Ghost ghost : this.ghostList) {
            t = new OverlayImages(t, ghost.draw());
        }
        return t;  
                    
    }
    
}
