package table.master.core.action;

import table.master.common.StringUtil;
import table.master.core.ActionException;
import table.master.core.table.base.SuperTable;

import java.io.File;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class MergeTableAction {
    private List<SuperTable> tableList;
    private SuperTable outputTable;

    public MergeTableAction(List<SuperTable> tableList, File outputFile) throws Exception {
        if (outputFile.exists()) {
            throw new Exception("合成后的文件已存在，请删除后重试：" + outputFile.getAbsolutePath());
        }

        this.tableList = tableList;
        this.outputTable = new SuperTable(tableList.get(0).getReader().getHeader(), outputFile);
    }

    private void beforeMage() throws ActionException {
        // 检查列头是否相同
        long distinctCount = tableList
                .stream()
                .map(x -> StringUtil.join(x.getReader().getHeader()))
                .distinct()
                .count();

        if (distinctCount > 1) {
            String message = "tables header 不一致" + System.lineSeparator();
            for (SuperTable table : tableList) {
                message += "【" + table.getFileName() + "】";
                message += "(" + table.getReader().getHeader().size() + "列)";
                message += StringUtil.join(table.getReader().getHeader(), ",");
                message += System.lineSeparator();
            }
            ActionException.of("MageTableAction Error", message);
        }
    }

    public SuperTable merge() throws Exception {
        beforeMage();

        try {
            for (SuperTable subTable : tableList) {
                subTable.getReader().readAll(x -> {
                    outputTable.getWriter().writeRow(x);
                });
                System.out.println("已合并：" + subTable.getFileName());
            }
        } finally {
            outputTable.close();
        }

        System.out.println();
        System.out.println("-------------------- ଘ(੭ˊᵕˋ)੭ 合并成功 --------------------");
        System.out.println("输出文件：" + outputTable.getFileName());
        System.out.println("共计：" + outputTable.getWriter().getWrittenRowCount() + "行");
        return outputTable;
    }

}
