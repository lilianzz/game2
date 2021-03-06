package main;

import java.io.IOException;

import user.Config;
import user.User;
import game.GameWorld;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		Config.load();
                System.out.println("Whether passed the blockInrange test?: "+Tests.blockInRange());
                System.out.println("Whether passed the ghostAfterMoveInRange test?: "+Tests.ghostAfterMoveInRange());
                System.out.println("Whether passed the charAfterMoveInRange test?: "+Tests.charAfterMoveInRange());
                System.out.println("Whether passed the moveEqual test?: "+Tests.moveEqual());
                System.out.println("Whether passed the captureEqual test?: "+Tests.captureEqual());
                System.out.println("Whether passed the selectionChange test?: "+Tests.selectionChange());  
                User.newGame();
                System.out.println("Whether passed the bombAllGhosts test?: "+Tests.bombAllGhosts());                  
                User.newGame();
                System.out.println("Whether passed the explodeBombs test?: "+Tests.explodeBombs());      
                User.newGame();
                System.out.println("Whether passed the bombBoth test?: "+Tests.bombBoth());     
                System.out.println("Whether passed the testBlocks test?: "+Tests.testBlocks());   
                User.newGame();
                System.out.println("Whether passed the bombStopMove test?: "+Tests.bombStopMove());
                Config.load();
		User.newGame();
		// TODO Auto-generated method stub
		//GameWorld game = new GameWorld();
        MainWorld game = new MainWorld();
		game.bigBang(600, 600, 0.2);

	}

}
