package table.master.console.client;

/**
 * @author zane
 * @date 2021/10/26
 */
public class Printer {

    public static void printBlock(String title, String context) {
        int totalLength = 40;
        int maskLength = title.length() > totalLength ? 4 : totalLength - title.length();
        for (int i = 0; i < (maskLength + 6) / 2; i++) {
            System.out.print("*");
        }
        System.out.print(" ");
        System.out.print(title);
        System.out.print(" ");
        for (int i = 0; i < (maskLength + 6) / 2; i++) {
            System.out.print("*");
        }
        System.out.println();

        System.out.println(context);
        System.out.println();
    }
}
