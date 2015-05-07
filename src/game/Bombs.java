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
public class Bombs {
    ArrayList<Bomb> b;
    int number;
    int max;
    ArrayList<Fire> f;
    
    public Bombs(int i) {
        this.b = new ArrayList<>(300);
        this.number = 0;
        this.max = i;
    }
    public Bombs(ArrayList<Bomb> b, int number, int max) {
        this.b = b;
        this.number = number;
        this.max = max;
    }
    
    public boolean add(Posn p) {
        //if (number < max) {
        
        	System.out.println("I add a bomb");
            Bomb temp = new Bomb(15,p);
            number = number +1;
            this.b.add(temp);
            System.out.println(number);
            for (int i=0; i<b.size(); i++) {
                System.out.println(b.get(i).posn.x);
            }
            return true;
        //} else 
        //    return this;
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
        for (Bomb b1 : this.b) {
            t = new OverlayImages(t,b1.draw());
        }
        if (!(this.f==null)) {
        for (Fire f1: this.f) {
            t = new OverlayImages(t,f1.draw());
        }}
        return t;  
                    
    }
    
    public boolean Explode(Blocks bk,Char c,Ghosts ghosts) {
        boolean temp = true;
        this.f = new ArrayList<>(300);
        int r;
        for (int i=0; i<b.size();i++) {
            b.get(i).time--;
        }
        for (int i = 0; i<b.size();i++) {
            if (b.get(i).time <= 0) {                                
                Posn p = b.get(i).posn();                 
                Posn t = c.posn();
                if (this.checkExplode(p,t,3,bk)) {
                temp = false;                
                }
                r = 3;                
                for (int j = 0; j < ghosts.ghostList.size();j++){
                    Posn a = ghosts.ghostList.get(j).posn();
                    if (this.checkExplode(p, a, 3, bk)){
                        ghosts.ghostList.remove(j);
                    }        
                }
                for (int j = 0; j < bk.b.size();j++){
                    Posn a = bk.b.get(j).posn();
                    if (this.checkExplode(p, a, 3, bk)){
                        bk.b.remove(j);
                    }        
                }
                b.remove(i);
                for (int j = p.x-100;j<p.x+150; j = j+50) {
                    f.add(new Fire(new Posn(j,p.y)));
                }
                for (int j = p.y-100;j<p.y+150; j = j+50) {
                    f.add(new Fire(new Posn(p.x,j)));
                }
            }
        }
        return temp;
    }
    
    public boolean checkExplode(Posn p, Posn t, int r, Blocks bk) {
        boolean temp = false;
        if ((((t.x - p.x) < (r*50)) && ((t.x - p.x) > (r*-50)) && (t.y==p.y)) ||                
                (((t.y - p.y) < (r*50)) && ((t.y - p.y) > (r*-50)) && (t.x==p.x))) {
            temp = true;
            for (int i = 0; i <bk.b.size();i++) {            
                if (this.inBetween(p,t,bk.b.get(i).posn())) {
                    temp = false;
                }
            }
        }
        return temp;
    }
    
    public boolean inBetween(Posn a, Posn b, Posn c) {
        if (((a.x < c.x) && (c.x < b.x) && (a.y == c.y) && (b.y == c.y)) ||
                ((b.x < c.x) && (c.x < a.x) && (a.y == c.y) && (b.y == c.y)) ||
                ((a.y < c.y) && (c.y < b.y) && (a.x == c.x) && (b.x == c.x)) ||
                ((a.y < c.y) && (c.y < b.y) && (a.x == c.x) && (b.x == c.x))) {
            return true;
        } else return false;
    }
    

}
