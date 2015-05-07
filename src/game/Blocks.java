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
public class Blocks {
    ArrayList<Block> b;
    
    public Blocks(int t) {
        b = new ArrayList<>(300);
        int a;
        for (int i = 0; i<12;i++) {
            for (int j = 0; j<12; j++) {
                a = ((int) (Math.random()*t));
                if ((a==0) && ((i>1) ||(j>1))) {
                    b.add(new Block(new Posn(((50*i)+25),((50*j)+25))));
                }
            }
        }
    }
    
    public boolean remove(Posn p) {
        for (int i = 0; i < b.size();i++){
            Posn t = b.get(i).posn();
            if ((t.x == p.x) && (t.y == p.y)) {
                this.b.remove(i);
            }
        }
        return true;
    }
    public boolean notIn(Element e) {
        boolean t = true;
        for (int i = 0; i < b.size();i++){
            Posn a = b.get(i).posn();
            if ((e.posn().x == a.x) && (a.y == e.posn().y)) {
                t = false;
            }
        }
        return t;
    }
    
    
    public WorldImage draw() {        
        WorldImage t = new RectangleImage(new Posn(0,0),1,1,Color.blue);             
        for (Block b1 : this.b) {
            t = new OverlayImages(t,b1.draw());
        }
        return t;  
                    
    }

}
