package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * A class maintain static configuration of the user now
 * @author 栗粒盐
 *
 */
public final class User {

	private User() {
		// TODO Auto-generated constructor stub
	}
	
	public static int level;
	public static int money;
	public static int[] nowItemLevels;
	public static String fileName = "game.save";
	
	
	public static void loadGame() throws IOException {
		DataInputStream saveFile;
		try {
			saveFile = new DataInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		level = saveFile.readInt();
		money = saveFile.readInt();
		for (int i = 0; i < Config.itemNum; i++) {
			User.nowItemLevels[i] = saveFile.readInt();
		}
		try {
			saveFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void newGame() throws IOException {
		level = Config.initLevel;
		money = Config.initMoney;
		for (int i = 0; i < Config.itemNum; i++) {
			nowItemLevels[i] = Config.items[i].initLevel;
		}
		save();
	}
	
	public static void levelUp() {
		if (level < Config.maxLevel) level ++;
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void levelUp(int index) {
		if (User.nowItemLevels[index] < Config.items[index].maxLevel) User.nowItemLevels[index] ++;
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void earn(int delta) {
		money += delta;
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean consume(int delta) {
		if (money < delta) {
			return false;
		}
		else {
			money -= delta;
			try {
				save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}
	
	public static void save() throws IOException {
		DataOutputStream saveFile;
		try {
			saveFile = new DataOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		saveFile.writeInt(level);
		saveFile.writeInt(money);
		for (int i = 0; i < Config.itemNum; i++) {
			saveFile.writeInt(User.nowItemLevels[i]);
		}
		try {
			saveFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int parameter(String str) {
		for (int i = 0; i < Config.itemNum; i++) {
			//System.out.println(Config.items[i].name());
			if (str.equals(Config.items[i].name())) {
				return User.nowItemLevels[i];
			}
		}
		System.out.println("Item not found:" + str);
		return 0;
	}
}
