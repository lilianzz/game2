/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import game.Char;
import game.Blocks;
import game.Ghost;
import game.Bombs;
import game.Ghosts;
import game.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javalib.worldimages.Posn;
import phase.*;
import user.*;
import static user.User.save;


/**
 *
 * @author 栗粒盐
 */
public class Tests {
    
    //whether blocks are in range
    static public boolean blockInRange() {
        Blocks b;
        Char c;
        Boolean t = true;
        for (int j = 0; j<300; j++) {
            b = new Blocks(j%4+3);
        for (int i = 0; i<b.size(); i++) {
            c = new Char(b.b.get(i).posn());
            if (!c.inRange()) {
                t = false;
            }
        }
        }        
        return t;
    }
    
    static public Posn randPosn() {
        int x = (int) (Math.random()*12);
        x = x*50+25;
        int y = (int) (Math.random()*12);
        y = y*50+25;            
        return (new Posn(x,y));
    }
    //alive is false if and only if randomly generated char are in fire
    //since there is no ghost
    static public boolean inFireFail() {
        Bombs b;
        Char c;
        boolean t = true;
        for (int i = 0; i< 300; i++) {
        b = new Bombs(4);            
        b.add(randPosn());
        c = new Char(randPosn());
        
        }
        return true;
    }
    
    
    static public boolean ghostAfterMoveInRange(){
        boolean r=true;
        for (int i=0; i<300;i++) {            
            Ghosts gg = new Ghosts(3);
            for (int j = 0; j<3; j++) {
                Ghost test = new Ghost(gg.ghostList.get(j).posn());
                int x = test.posn().x;
                int y = test.posn().y;        
                test.up();
                if (test.inRange() == (y == 25)) { r = false; 
                System.out.println(test.posn().x+"up"+test.posn().y);}
                test.down();
                test.down();
                if (test.inRange() == (y == 575)) {r = false;
                System.out.println(test.posn().x+"up"+test.posn().y);}
                test.up();
                test.left();
                if (test.inRange() == (x == 25)) {r = false;
                System.out.println(test.posn().x+"up"+test.posn().y);}
                test.right();
                test.right();
                if (test.inRange() == (x == 575)) {r = false;
                System.out.println(test.posn().x+"up"+test.posn().y);}                        
            }
        }
        return(r);
    }    
    
    static public boolean charAfterMoveInRange(){
        boolean r=true;
        for (int i=0; i<10;i++) {            
            Char test = new Char(randPosn());
            int x = test.posn().x;
            int y = test.posn().y;       
            if (test.up().inRange() == (y == 25)) { r = false; 
            System.out.println(test.posn().x+"up"+test.posn().y+""+y);}
            if (test.down().inRange() == (y == 575)) {r = false;
            System.out.println(test.posn().x+"down"+test.posn().y);}
            if (test.left().inRange() == (x == 25)) {r = false;
            System.out.println(test.posn().x+"left"+test.posn().y);}
            if (test.right().inRange() == (x == 575)) {r = false;
            System.out.println(test.posn().x+"right"+test.posn().y);}
        }       
        return(r);
    }    
    
    static public boolean moveEqual() {
        boolean t = true;
        for (int i = 0; i<300; i++) {
            Char c = new Char(randPosn());
            Posn p = c.posn();
            Char d = c.left().up().right().down();
            if (!((c.posn().x == d.posn().x)&&(c.posn().y == d.posn().y))) {t = false;}
        }
        return t;
    }
    
    static public boolean captureEqual() {
        boolean t = true;
        for (int i = 0; i<300;i++) {
            Ghosts gg= new Ghosts(10);
            Random random = new Random();
            int j = random.nextInt(10);
            Char c = new Char(gg.ghostList.get(j).posn());
            if (!gg.capture(c)) {t = false;}
        }
        return t;
    }
    
    static public boolean selectionChange() {
        boolean f = true;
        for (int i=0; i<300;i++) {
        Selections selections = new Selections(4,4, 100,100, 500,500);
        Random random = new Random();
        int t = random.nextInt(40);
        for (int j=0; j<t; j++) {selections.up();}
         t = random.nextInt(40);
        for (int j=0; j<t; j++) {selections.left();}
         t = random.nextInt(40);
        for (int j=0; j<t; j++) {selections.down();}
         t = random.nextInt(40);
        for (int j=0; j<t; j++) {selections.right();}
        if ((selections.selectedM() > 3) || (selections.selectedM() < 0) ||
                (selections.selectedN() > 3) || (selections.selectedN() < 0)) {
            f = false;
        }
        }
        return f;              
    }
    
    static public boolean bombAllGhosts() {
        boolean t = true;        
        Blocks b = new Blocks();
        Char c = new Char(new Posn(1000,1000));
        for (int i=0; i<300; i++) {
            Bombs bombs = new Bombs(5);
            Ghosts g = new Ghosts(20);            
            Posn r = randPosn();
            for (int j=0; j<10 ;j++) {
                r = g.ghostList.get(0).posn();
                bombs.add(r);}
            for (int j = 0; j<15; j++) {                
                bombs.ticks(b,c,g);
            }
            if (g.ghostList.size() >= 20) {t = false;}
        }
        return t;
    }
    
    static public boolean explodeBombs() {
        boolean t = true;        
        Blocks b = new Blocks();
        Ghosts g = new Ghosts(0);
        Char c = new Char(new Posn(1000,1000));
        for (int i=0; i<3; i++) {
            Bombs bombs = new Bombs(5);       
            Posn r = randPosn();
            Posn r1 = randPosn();
            bombs.add(r);
            bombs.add(r1);            
            bombs.bombList.get(1).time = 0;
            bombs.ticks(b, c, g);  
            if (!(bombs.bombList.size() <2 )) 
            {t =  false;}
        }
        return t;
    }
    
}
