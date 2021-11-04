package table.master.console.client;

import table.master.console.client.tool.MageTableTool;
import table.master.console.client.tool.SplitTableTool;

/**
 * @author zane
 * @date 2021/10/25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(
                "按数字键选择需要使用的功能：" + System.lineSeparator()
                        + "1. 合并多个表格到一个文件" + System.lineSeparator()
                        + "2. 将一个表格按列切割为多个 Sheet"
        );
        String line = ConsoleInput.getLine();
        switch (line) {
            case "1":
                new MageTableTool().start();
            case "2":
                new SplitTableTool().start();
            default:
        }
        System.out.println("程序已退出...");
    }
}
