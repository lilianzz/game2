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
import java.awt.Color;
import javalib.worldimages.Posn;

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
            Ghosts gg= new Ghosts(3);
            Char c = new Char(gg.ghostList.get(2).posn());
            if (!gg.capture(c)) {t = false;}
        }
        return t;
    }
 
    static public boolean case1() {
        Play test = new Play(480,600);
        test.add(createBlock(2,0,Color.darkGray));        
        test.add(createBlock(2,1,Color.gray));
        test.add(createBlock(2,2,Color.lightGray));
        test.add(createBlock(2,3,Color.white));
        test.add(createBlock(2,4,Color.white));
        while (!test.colorMatch()) {}        
        System.out.println("group of dead blocks is: (Should print size 1 and a black block) ");
        test.print();
        if (!(test.landscape.size() ==1)) {return(false);} else {return(true);}        
    }
    
    static public boolean stopAdd() {
        boolean r = true;
        for (int i=0; i<200; i++) {
            Play test = randomBlocks();
            int s1 = test.landscape.size();           
            Block a = aRandomBlock();
            boolean t = test.stop(a);
            if (!t) {test.add(a);}           
            if ((!t) && (s1 == test.landscape.size())) {r = false; test.print();}
        }
        return r;        
    }   
    
    static public boolean case2() {
        Play test = new Play(480,600);
        test.add(createBlock(0,0,Color.darkGray));        
        test.add(createBlock(1,0,Color.darkGray));
        test.add(createBlock(2,0,Color.darkGray));
        test.add(createBlock(3,0,Color.gray));
        test.add(createBlock(0,1,Color.white));        
        test.add(createBlock(1,1,Color.white));        
        test.add(createBlock(3,1,Color.white));   
        test.add(createBlock(3,2,Color.gray));          
        Block live = (createBlock(2,1,Color.white));
        test.add(live);
        int score = 0;
        while (!test.colorMatch()) {}
        while (!(test.checkClear() == (null))) {
            test.clear(test.checkClear());
            score = score+5;
            while (!test.colorMatch()) {}
        }
        System.out.println("Score: "+ score+"  (Should be 10)");
        System.out.println("group of dead blocks is: (Should be empty and "
                + "only show size is 0) ");
        test.print();
        if (score == 10) {return(true);} else {test.print();return(false);}        
    }
}
