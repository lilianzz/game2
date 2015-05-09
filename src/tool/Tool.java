package tool;
import java.util.*;
/**
 * supply useful tools
 * @author 栗粒盐
 *
 */
public class Tool {
	static Random random = new Random();
	public static int randInt(int range) {
		return random.nextInt(range);
	}
}
