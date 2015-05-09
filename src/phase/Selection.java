package phase;

import java.awt.Color;

import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class Selection {
	protected String sign;
	protected int x, y;
	protected Posn pos;
	protected Color color = Color.green;
	public Selection() {
		// TODO Auto-generated constructor stub
	}

	public void setSign(String s) {
		this.sign = s;
	}
	
	public String getSign() {
		return this.sign;
	}
	
	public void setPos(int posX, int posY, int h, int w) {
		pos = new Posn(posY, posX);
		x = w;
		y = h;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public WorldImage draw() {
		WorldImage pic = new RectangleImage(pos, x, y, color);
		return new OverlayImages(pic, new TextImage(
                pos,
                sign,
                y / 2,
                Color.black
        ));
	}
}
