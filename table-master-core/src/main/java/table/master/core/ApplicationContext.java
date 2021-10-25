package table.master.core;

import java.nio.file.Paths;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ApplicationContext {
    /**
     * 工作目录，程序输出的所有中间文件和结果文件都在这个目录。
     */
    public static String WORK_DIR;

    /**
     * 基于 WORK_DIR 生成绝对路径
     */
    public static String getAbsolutePath(String path) {
        return Paths.get(WORK_DIR, path).toAbsolutePath().toString();
    }

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
