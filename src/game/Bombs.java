/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;
import java.awt.Color; 
import java.util.ArrayList;
import java.util.Iterator;

import user.Config;
import user.User;
import javalib.worldimages.*; 
/**
 *
 * @author 栗粒盐
 */
public class Bombs {
    ArrayList<Bomb> bombList;
    int max;
    ArrayList<Fire> fireList = new ArrayList<>();
    ArrayList<Fire> drawFireList = new ArrayList<>();
    
    public Bombs(int i) {
        this.bombList = new ArrayList<>(300);
        this.max = i;
    }
    public Bombs(ArrayList<Bomb> b, int number, int max) {
        this.bombList = b;
        this.max = max;
    }
    
    public boolean add(Posn p) {
        if (this.bombList.size() < User.parameter("BOMB NUMBER")+2) {
        	//System.out.println("I add a bomb");
            Bomb temp = new Bomb(15,p);
            this.bombList.add(temp);
            //System.out.println(number);
            //for (int i=0; i<bombList.size(); i++) {
            //    System.out.println(bombList.get(i).posn.x);
            //}
            return true;
        }
        return false;
        //    return this;
    }
    
    /**
     * whether the element overlap a bomb
     * @param e
     * @return
     */
    public boolean notIn(Element e) {
        boolean t = true;
        for (int i = 0; i < bombList.size();i++){
            Posn a = bombList.get(i).posn();
            if ((e.posn().x == a.x) && (a.y == e.posn().y)) {
                t = false;
            }
        }
        return t;
    }
    
    
    public WorldImage draw() {        
        WorldImage t = new RectangleImage(new Posn(0,0),1,1,Color.blue);             
        for (Bomb b1 : this.bombList) {
            t = new OverlayImages(t,b1.draw());
        }
        for (Fire fire: this.drawFireList) {
        	//System.out.println("draw fire");
            t = new OverlayImages(t,fire.draw());
        }
        return t;  
                    
    }
    /** 
     * Add fire to fireList, ignite other bombs and destroy blocks
     * @param fire		The fire object
     * @param blocks	The blocks
     * @return Whether the fire destory a block
     */
    public boolean addFire(Fire fire, Blocks blocks) {
    	fireList.add(fire);
    	/* Ignite bombs */
    	for (Bomb bomb : bombList) {
    		if (fire.contains(bomb.posn())) {
    			bomb.time = 0;
    		}
    	}
    	/* check block */
    	for (Iterator<Block> iter = blocks.b.iterator(); iter.hasNext();) {
    		Block block = iter.next();
    		if (fire.contains(block.posn())) {
    			User.earn(Config.blockEarnMoney);
    			iter.remove();
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Add fires to fireList
     * @param bomb		
     * @param blocks	
     */
    public void explode(Bomb bomb, Blocks blocks) {
        Posn posBomb = bomb.posn();
        /* The radius of explosion */
        int radiusBlocks = User.parameter("BOMB POWER") + 1;
        int radius = radiusBlocks * 50;
        /* explode in each directions */
        for (int x = posBomb.x; x < posBomb.x + radius; x = x + 50) {
        	if (addFire(new Fire(new Posn(x, posBomb.y)), blocks)) break;//break when the fire meets a block
        }
        for (int x = posBomb.x; x > posBomb.x - radius; x = x - 50) {
        	if (addFire(new Fire(new Posn(x, posBomb.y)), blocks)) break;
        }
        for (int y = posBomb.y;	y < posBomb.y + radius; y = y + 50) {
        	if (addFire(new Fire(new Posn(posBomb.x, y)), blocks)) break;
        }
        for (int y = posBomb.y; y > posBomb.y - radius; y = y - 50) {
        	if (addFire(new Fire(new Posn(posBomb.x, y)), blocks)) break;
        }
    }
    /**
     * Simulate one tick of bombs
     * @param blocks
     * @param c
     * @param ghosts
     * @return
     */
    public boolean ticks(Blocks blocks,Char c,Ghosts ghosts) {
        boolean tmpAlive = true;
        //this.fireList = new ArrayList<>(300);
        /* ticks */
        for (int i=0; i<bombList.size();i++) {
            bombList.get(i).time--;
        }
        for (Iterator<Fire> iter = drawFireList.iterator(); iter.hasNext();){
        	Fire fire = iter.next();
        	if (fire.ticks() <= 0) iter.remove();
        }
        /* Iteratively ignite bombs */
        boolean finish = false;
        while (!finish) {
	        finish = true;
        	for (Iterator<Bomb> iter = bombList.iterator(); iter.hasNext();) {
	        	Bomb b = iter.next();
	        	if (b.time <= 0) {
	        		iter.remove();
	        		explode(b, blocks);
	        		finish = false;
	        	}
	        }
        }
        
        for (Fire fire : fireList) {
        	//Check alive
        	if (fire.contains(c.posn())) {
        		tmpAlive = false;
        	}
        	//Kill ghosts
        	for (Iterator<Ghost> iter = ghosts.ghostList.iterator(); iter.hasNext();) {
            	Ghost ghost = iter.next();
            	if (fire.contains(ghost.posn())) {
            		iter.remove();
            		User.earn(Config.ghostEarnMoney);
            	}
            }
        } 
        
        for (Fire fire : fireList) drawFireList.add(fire);
        fireList.clear();
        
        return tmpAlive;
    }

}
