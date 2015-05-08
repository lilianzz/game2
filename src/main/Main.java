package main;

import java.io.IOException;

import phase.MainWorld;
import game.GameWorld;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//GameWorld game = new GameWorld();
        MainWorld game = new MainWorld();
		game.bigBang(600, 600, 0.2);

	}

}
