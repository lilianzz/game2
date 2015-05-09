package phase;

import user.User;

/**
 * The item sold in store
 * @author 栗粒盐
 *
 */
public class Item {
	String name;
	public String description;
	public int costMoney, initLevel, maxLevel;
	
	int index;
	
	public Item(String name, String description, int costMoney, int initLevel, int maxLevel, int index) {
		this.name = name;
		this.description = description;
		this.costMoney = costMoney;
		this.initLevel = initLevel;
		this.maxLevel = maxLevel;
	}
	
	public String name() {
		return name;
	}
	public int cost() {
		return costMoney * User.nowItemLevels[index];
	}
	
	public boolean select() {
		boolean tmp = User.consume(cost());
		if (tmp) {
			User.levelUp(index);
		}
		return tmp;
	}
}
