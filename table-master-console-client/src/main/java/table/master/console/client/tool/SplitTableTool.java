package table.master.console.client.tool;

import table.master.common.StringUtil;
import table.master.core.action.SplitTableAction;
import table.master.core.table.base.SuperTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class SplitTableTool extends AbstractTool {
    private SplitTableAction action;

    @Override
    protected List<ToolParam> getParamList() {

        ToolParam param1 = new ToolParam("请指定需要切割的 excel 文件（把文件拖进来会自动填充路径）：");
        param1.checker = (list) -> {
            File file = new File(list.get(0).value);
            if (!file.exists()) {
                System.out.println("Error：未找到文件：" + file.getAbsolutePath());
                return false;
            }
            return true;
        };

        ToolParam param2 = new ToolParam("请输入按列切割的列名：");
        param2.checker = (list) -> {
            SuperTable inputTable = null;
            try {
                inputTable = new SuperTable(new File(list.get(0).value));
            } catch (IOException e) {
                System.out.println("Error：表格读取失败...");
                return false;
            }
            String columnName = list.get(1).value;
            if (!inputTable.getReader().getHeader().contains(columnName)) {
                System.out.println("Error：列名错误，表格中未找到此列。");
                return false;
            }
            return true;
        };

        return Arrays.asList(
                param1,
                param2
        );
    }

    @Override
    protected void setParamList(List<ToolParam> list) throws Exception {


        File inputFile = new File(list.get(0).value);
        File outputFile = Paths.get(inputFile.getParent(), StringUtil.substringBefore(inputFile.getName(), ".") + "-split.xlsx").toFile();


        if (outputFile.exists()) {
            System.out.println("该文件已存在，将会被覆盖：" + outputFile.getAbsolutePath());
            outputFile.delete();
        }
        SuperTable inputTable = new SuperTable(inputFile);

        action = new SplitTableAction(inputTable, list.get(1).value, outputFile);

    }


    @Override
    protected void run() throws Exception {
        System.out.println("开始合并...");
        action.split();
        System.out.println("合并结束");
    }
}
