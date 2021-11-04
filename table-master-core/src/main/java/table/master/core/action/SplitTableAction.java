package table.master.core.action;

import table.master.core.table.excel.ExcelTable;

import java.io.File;

/**
 * @author zane
 * @date 2021/11/03
 */
public class SplitTableAction {
    private ExcelTable inputTable;
    private String groupByColumn;
    private File outputFile;

    public SplitTableAction(ExcelTable inputTable, String groupByColumn, File outputFile) throws Exception {
        this.inputTable = inputTable;
        this.outputFile = outputFile;
        this.groupByColumn = groupByColumn;
        if (outputFile.exists()) {
            throw new Exception("切割后的文件已存在，请删除后重试：" + outputFile.getAbsolutePath());
        }
    }

    public ExcelTable split() throws Exception {

        ExcelTable outputTable = new ExcelTable(inputTable.getReader().getHeader(), outputFile);

        try {
            inputTable.getReader().readAll(x -> {
                outputTable.getWriter().writeRow(x);

                String key = String.valueOf(x.get(groupByColumn));
                key = key.replace(":", "-");
                outputTable.getWriter().writeRowToSheet(x, groupByColumn + "：" + key);
            });

            System.out.println("切割完毕：" + outputTable.getFileName());
        } finally {
            outputTable.close();
        }

        return outputTable;
    }
}
