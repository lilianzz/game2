/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javalib.worldimages.*; 
/**
 *
 * @author 栗粒盐
 */
public class Test {
    static public boolean testSpace(Blocks b, Char c) {
        int s = 0;
        for (int i = 0; i< b.b.size(); i++) {
        Posn j = c.posn();
        Posn k = b.b.get(i).posn();
        if (((k.x == j.x) && (k.y == (j.y - 50))) ||
                ((k.x == j.x) && (k.y == (j.y + 50))) ||
                ((k.y == j.y) && (k.x == (j.x - 50))) ||
                ((k.y == j.y) && (k.x == (j.x + 50)))) {
            s++;
        }
        }
        return (!(s>=4));
    }
}
