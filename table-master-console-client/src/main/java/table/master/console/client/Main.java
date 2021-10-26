package table.master.console.client;

import table.master.console.client.tool.MageTableTool;

/**
 * @author zane
 * @date 2021/10/25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(
                "按数字键选择需要使用的功能：" + System.lineSeparator()
                        + "1. 合并多个表格到一个文件"
        );
        String line = ConsoleInput.getLine();
        switch (line) {
            case "1":
                new MageTableTool().start();
            default:
        }
        System.out.println("程序已退出...");
    }
}
