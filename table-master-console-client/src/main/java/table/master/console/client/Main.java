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

        System.out.println("???????????? " + days + " ???!");
        System.out.println();


        System.out.println();
        System.out.println("-------------------- ???(??????????)??? ???????????? --------------------");

        // System.out.println(
        //         "??????????????????????????????????????????" + System.lineSeparator()
        //                 + "1. ?????????????????????????????????" + System.lineSeparator()
        //                 + "2. ???????????????????????????????????? Sheet"
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
                System.out.println("???????????????????????????????????????...");
                return;
        }

        try {
            tool.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("???????????????...");
    }
}
