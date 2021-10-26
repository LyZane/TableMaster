package table.master.core.action;

import table.master.common.StringUtil;
import table.master.core.ActionException;
import table.master.core.enums.TableType;
import table.master.core.table.base.AbstractTable;
import table.master.core.table.excel.ExcelTable;

import java.io.File;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class MageTableAction {
    private TableType tableType;
    private List<AbstractTable> tableList;
    private File outputFile;

    public MageTableAction(TableType tableType, List<AbstractTable> tableList, File outputFile) {
        this.tableType = tableType;
        this.tableList = tableList;
        this.outputFile = outputFile;
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
            for (AbstractTable table : tableList) {
                message += "【" + table.getFileName() + "】";
                message += "(" + table.getReader().getHeader().size() + "列)";
                message += StringUtil.join(table.getReader().getHeader(), ",");
                message += System.lineSeparator();
            }
            ActionException.of("MageTableAction Error", message);
        }
    }

    public AbstractTable mage() throws Exception {
        beforeMage();
        AbstractTable table = createTable();

        for (AbstractTable subTable : tableList) {
            subTable.getReader().readAll(x -> {
                table.getWriter().writeRow(x);
            });
        }

        return table;
    }

    private AbstractTable createTable() throws Exception {
        List<String> header = tableList.get(0).getReader().getHeader();
        switch (tableType) {
            case Excel:
                return new ExcelTable(header, outputFile);
            default:
                throw new Exception("尚不支持 excel 以外的格式");
        }
    }
}
