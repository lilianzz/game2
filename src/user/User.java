package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class User {

	private User() {
		// TODO Auto-generated constructor stub
	}
	
	public static int level;
	public static int money;
	public static int bombLevel;
	public static String file = "game.save";
	
	public static void loadGame() throws IOException {
		DataInputStream saveFile;
		try {
			saveFile = new DataInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		level = saveFile.readInt();
		money = saveFile.readInt();
		bombLevel = saveFile.readInt();
		try {
			saveFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void newGame() throws IOException {
		level = 1;
		money = 0;
		bombLevel = 1;
		save();
	}
	
	public static void save() throws IOException {
		DataOutputStream saveFile;
		try {
			saveFile = new DataOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		saveFile.writeInt(level);
		saveFile.writeInt(money);
		saveFile.writeInt(bombLevel);
		try {
			saveFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
