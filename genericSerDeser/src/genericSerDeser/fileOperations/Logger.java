
package genericSerDeser.fileOperations;
/**
 * Class having methods for logging data
 * @author    Shraddha Apharande
 */
public class Logger {

	public static enum DebugLevel {
		RELEASE
	};

	private static DebugLevel debugLevel;

	/**
	 * @param levelIn
	 * @return void
	 */
	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		// to display result
		case 0:
			debugLevel = DebugLevel.RELEASE;
			break;
		}
	}

	/**
	 * @param levelIn
	 * @return void
	 */
	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	/**
	 * @param message
	 * @param levelIn
	 * @return void
	 */
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}
