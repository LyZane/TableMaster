package table.master.test;

import table.master.core.action.SplitTableAction;
import table.master.core.table.excel.ExcelTable;

import java.io.File;

/**
 * @author zane
 * @date 2021/10/26
 */
public class SplitTest {
    public static void main(String[] args) throws Exception {
        String path = "/Users/zane/data/TableMaster/split/拆分.xlsx";
        File inputFile = new File(path);

        File outputFile = new File("/Users/zane/data/TableMaster/split/split.xlsx");
        if (outputFile.exists()) {
            outputFile.delete();
        }

        ExcelTable inputTable = new ExcelTable(inputFile);

        SplitTableAction action = new SplitTableAction(inputTable, "总期数", outputFile);
        action.split();

    }
}
