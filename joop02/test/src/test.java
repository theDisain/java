import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.StringWriter;
import java.util.Scanner;

/**
 * Created by Ain-Joonas on 14.03.2015.
 */
public class test {
    public static void main(String[] args) {
        isikukood();
    }
    public static String isikukood(){

        Scanner sc = new Scanner(System.in);
        String thing = sc.next();
        if(thing.length() >= 7) {
            String isikukood = thing.substring(5, 6) + thing.substring(6, 7) + "." + thing.substring(3, 4) + thing.substring(4, 5);
            System.out.println(isikukood);
        }
        if(thing.matches("exit")) {
            System.out.println("exitcmd");
            System.exit(0);
        }
        return isikukood();
    }
}
