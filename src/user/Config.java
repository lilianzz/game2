package user;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import phase.Item;

/**
 * The configuration of the game. Load information of item from file "config"
 * @author 栗粒盐
 *
 */
public class Config {
	public final static String fileName = "config";
	
	public final static int x = 600;
	public final static int y = 600;
	/* money */
	public final static int initMoney = 100;
	public final static int ghostEarnMoney = 10;
	public final static int blockEarnMoney = 1;
	/* level */
	public final static int initLevel = 1;
	public final static int maxLevel = 10;
	/* fire */
	public final static int fireExistTime = 1; //The ticks of fire exists
	/* items */
	public static int itemNum;
	public static Item[] items;
	
	
	public static void load() throws IOException {
		DataInputStream configFile;
		try {
			configFile = new DataInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		itemNum = Integer.valueOf(configFile.readLine());
		items = new Item[itemNum];
		User.nowItemLevels = new int[itemNum];
		
		for (int i = 0; i<itemNum; i++) {
			String line = configFile.readLine();
			String[] a = line.split("[\t ]*\\|[\t ]*");
			items[i] = new Item(a[0], a[1], Integer.valueOf(a[2]), Integer.valueOf(a[3]), Integer.valueOf(a[4]), i);
		}
	}
}
