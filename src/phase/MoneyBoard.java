package phase;

import java.awt.Color;

import user.User;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class MoneyBoard extends Selection{

	public MoneyBoard() {
		this.setColor(Color.lightGray);
	}

	public WorldImage draw() {
		WorldImage pic = super.draw();
		return new OverlayImages(pic, new TextImage(pos, "Money: " + User.money, y / 2, Color.black));
	}
}
