package phase;

import java.awt.Color;

import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

/**
 * The selections with size (n,  m)
 * @author 栗粒盐
 *
 */
public class Selections {
	int n, m;
	int selectedN, selectedM; //The selection which is selected by user
	Selection [][] window;
	/**
	 * an n * m matrix
	 * @param n 
	 * @param m
	 */
	public Selections(int n, int m) {
		this.n = n;
		this.m = m;
		window = new Selection[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) 
				window[i][j] = new Selection();
		selectedN = selectedM = 0;
	}
	/**
	 * Create a selections with n * m which is restricted in a rectangle(x1, y1, x2, y2)
	 * Use self-adjust method to put the selection's
	 * @param n
	 * @param m
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Selections(int n, int m, int x1, int y1, int x2, int y2) {
		this(n, m);
		int stepX = (x2 - x1) / n;
		int stepY = (y2 - y1) / m;
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++)
				window[i][j].setPos(x1 + stepX*i + stepX/2, y1 + stepY*j + stepY/2, stepX*2/3, stepY*2/3);
	}
	
	/**
	 * Set the words presented on one selection
	 * @param x	The x coordinate
	 * @param y The y coordinate
	 * @param sign The words on the selection
	 */
	public void setSign(int x, int y, String sign) {
		window[x][y].setSign(sign);
	}
	/**
	 * Set the words presented on one selection
	 * Here's a 3*2 example:
	 * ---------
	 * |0  | 1 |
	 * ---------
	 * |2  | 3 |
	 * ---------
	 * |4  | 5 |
	 * ---------
	 * @param num	The global number of blocks, start from left-upper corner with number 0
	 * @param sign	The word
	 */
	public void setSign(int num, String sign) {
		int x = num / m;
		int y = num % m;
		window[x][y].setSign(sign);
	}
	
	public int selectedN() {
		return this.selectedN;
	}
	
	public int selectedM() {
		return this.selectedM;
	}
	/**
	 * Get the sign of selected selection
	 * @return sign
	 */
	public String getSelected() {
		return window[selectedN][selectedM].getSign();
	}
	
	/*four methods that update this class */
	public void up() {
		selectedN = (selectedN - 1 + n) % n;
	}
	
	public void down() {
		selectedN = (this.selectedN + 1) % n;
	}
	
	public void left() {
		this.selectedM = (this.selectedM - 1 + m) % m;
	}
	
	public void right() {
		this.selectedM = (this.selectedM + 1) % m;
	}
	
	public WorldImage draw() {
		WorldImage draw = new RectangleImage(new Posn(0,0),1,1,Color.blue); //A useless particle
		window[selectedN][selectedM].setColor(Color.red);					//Change the selected block's color to red
		for (int i = 0; i < n; i ++)
			for (int j = 0; j < m; j++)
				draw = new OverlayImages(draw, window[i][j].draw());		//Draw the selections
		window[selectedN][selectedM].setColor(Color.green);					//recover the selected block's color
		return draw;
	}
	
}
