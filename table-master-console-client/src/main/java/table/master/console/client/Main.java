package table.master.console.client;

import table.master.common.TimeSpan;
import table.master.console.client.tool.AbstractTool;
import table.master.console.client.tool.MergeTableTool;
import table.master.console.client.tool.SplitTableTool;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author zane
 * @date 2021/10/25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("..................,]]/O@@@@OO]]`.................\n" +
                "............../@@@@@@@@/\\@/@@@@@@@@\\`............\n" +
                "..........,/@@@/`@/./@./@.//./@`,@[@@@@`.........\n" +
                "........=@@@@@[`..[\\@@@\\/@\\]@@@/[[[\\@@@@@\\.......\n" +
                "......,@@@@[.  .      .......         ,@@@@\\.....\n" +
                ".....@@@@`.  ,]O@@@@@]].   .]]@@@@@@]` ..[@@@]]].\n" +
                "/@@@@@@@@@@@@@@@@@@@@@@@@]@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@@O\\//.. ...\\@@@@@@@@@@@/.\\@@@@@@@@@@@/.. ..,@@@,\n" +
                "@`.//........\\@@@@@@@@@/...=@@@@@@@@@/.......,@`.\n" +
                "@^,@.,^,^=^/`...................... .../.O.O,^=O.\n" +
                "@@O^.[.`.`,`...... ...................,`,.[.`..@@\n" +
                "=@@^..........=@@@]].... .....,]/@@@^..........\\@\n" +
                "=@@...........\\@@@@@@@@@@@@@@@@@@@@@^......... =@\n" +
                "=@@^..........=@@@@@@@@@@@@@@@@@@@@@.......... =@\n" +
                ".@@^..........=@@@@@@@@@@@@@@@@@@@@@...........O@\n" +
                ".=@@...........@O[`**,\\@@@@O`****[@^..........,@@\n" +
                ".,@@\\..........,@*******=/*******,O.........../@@\n" +
                "..=@@^..........,@**.*..*..*****,@.........../@@.\n" +
                "...,@@\\..........,@`*.*....****//..........,@@@..\n" +
                "....*\\@@\\..........,@]*******/@`.........,@@@`...\n" +
                "......,O@@@`..........[\\@@@[`........../@@@/.....\n" +
                ".........[@@@@]`..................,]@@@@/`.......\n" +
                "............,@@@\\@@@\\]]]]]]]]@@@O[`,@@^..........\n" +
                "............,@@^....................=@@..........\n" +
                "............/@@...@^.....O\\.....,@...@@^.........\n");


        LocalDateTime publishTime = LocalDateTime.of(2021, 10, 25, 0, 0, 0);

        long days = TimeSpan.fromMs(
                System.currentTimeMillis() - publishTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()
        ).toDays();

        System.out.println("白嫖的第 " + days + " 天!");
        System.out.println();


        System.out.println();
        System.out.println("-------------------- ଘ(੭ˊᵕˋ)੭ 欢迎使用 --------------------");

        // System.out.println(
        //         "按数字键选择需要使用的功能：" + System.lineSeparator()
        //                 + "1. 合并多个表格到一个文件" + System.lineSeparator()
        //                 + "2. 将一个表格按列切割为多个 Sheet"
        // );
        // String line = ConsoleInput.getLine();
        String line = "1";
        AbstractTool tool = null;
        switch (line) {
            case "1":
                tool = new MergeTableTool();
                break;
            case "2":
                tool = new SplitTableTool();
                break;
            default:
                System.out.println("意料之外的选择，程序已退出...");
                return;
        }

        try {
            tool.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("程序已退出...");
    }
}
