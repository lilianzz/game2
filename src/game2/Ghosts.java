/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game2;
import java.awt.Color; 
import java.util.ArrayList;
import javalib.worldimages.*; 
import javalib.worldimages.WorldImage;

/**
 *
 * @author 栗粒盐
 */
public class Ghosts {
    ArrayList<Ghost> g = new ArrayList<>(20);
    int number;
    public Ghosts(int n) {
        int a;
        int b;
        number = n;
        for (int i=0; i<n;i++) {
            a = (int) (Math.random()*12);
            a = a*50+25;
            b = (int) (Math.random()*12);
            b = b*50+25;            
            g.add(new Ghost(new Posn(a,b)));
        }
    }
    
    public boolean randMove(Blocks b, Bombs bs) {
        int t;
        int a = (int) Math.floor(Math.random() * 14);
        for (int i = 0; i<g.size();i++) {
            t = (int) Math.floor(Math.random() * 5);
            switch(t) {
                case 0: if (b.notIn(g.get(i).up()) && bs.notIn(g.get(i).up()) && g.get(i).up().inRange()) {
                        g.set(i,g.get(i).up());}
                    break;
                case 1: if (b.notIn(g.get(i).down()) && bs.notIn(g.get(i).down()) && g.get(i).down().inRange()) {
                    g.set(i,g.get(i).down());}                
                    break;
                case 2: if (b.notIn(g.get(i).left()) && bs.notIn(g.get(i).left()) && g.get(i).left().inRange()) {
                    g.set(i,g.get(i).left());}
                    break;
                case 3: if (b.notIn(g.get(i).right()) && bs.notIn(g.get(i).right()) && g.get(i).right().inRange()) {
                    g.set(i,g.get(i).right());}
                    break;
                case 4: if (a==0) bs.add(g.get(i).posn());
                    break;                    
            }
        }
        return true;
    }
    
    public WorldImage draw() {        
        WorldImage t = new RectangleImage(new Posn(0,0),1,1,Color.blue);             
        for (Ghost b1 : this.g) {
            t = new OverlayImages(t,b1.draw());
        }
        return t;  
                    
    }
    
}
