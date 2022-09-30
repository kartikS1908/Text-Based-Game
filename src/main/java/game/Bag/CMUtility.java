package game.Bag;
import java.util.*;
public class CMUtility {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' &&
                    c != '3' && c != '4' && c != '5') {
                System.out.print("Choose wrong!");
            } else break;
        }
        return c;

    }
    String str = readKeyBoard(1, false);
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("")? defaultValue : str;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Choose wrong!");
            }
        }
        return n;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Choose wrong!");
            }
        }
        return c;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    public static int readInt(int defaultValue) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, true);
            if (str.equals("")) {
                return defaultValue;
            }

            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Choose wrong!");
            }
        }
        return n;
    }
    /**
     * TODO: what this function do.
     * @author Dehao Liu
     * TODO: Lisence
     * TODO: This is a example, param and return goes here.
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("The input length is not greater than" + limit + "Choose wrong!");
                continue;
            }
            break;
        }

        return line;
    }
}
