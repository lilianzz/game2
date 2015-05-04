/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game2;

import javalib.worldimages.Posn;

/**
 *
 * @author 栗粒盐
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static boolean testBlocks() {
        
        Blocks bs = new Blocks(5);    
        bs.remove(new Posn(125,125));       
        Block r = new Block(new Posn(125,125));
        return (bs.notIn(r));
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        World game = new World();
        game.bigBang(600, 600, 0.2);
        for (int i=0; i<300; i++) {
        System.out.println(testBlocks());
        }
    }
    
}
