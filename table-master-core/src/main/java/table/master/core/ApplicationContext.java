package table.master.core;

import java.nio.file.Path;
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

    public static Path getWorkDirPath() {
        return Paths.get(WORK_DIR);
    }

    /**
     * 基于 WORK_DIR 生成绝对路径
     */
    public static String getAbsolutePath(String path) {
        return Paths.get(WORK_DIR, path).toAbsolutePath().toString();
    }

}
