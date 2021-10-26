package table.master.core.table.excel;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import table.master.core.table.base.AbstractTableWriter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ExcelTableWriter extends AbstractTableWriter {

    private BigExcelWriter writer;

    public ExcelTableWriter(List<String> header, File file) {
        super(header);
        writer = ExcelUtil.getBigWriter(file);
        initExcel();
    }

    private void initExcel() {
        // 初始化 sheet
        if (writer.getWorkbook().getNumberOfSheets() <= 0) {
            writer.getWorkbook().createSheet("TableMaster" + writer.getWorkbook().getNumberOfSheets());
        }

        // 初始化列头行
        Sheet sheet = writer.getWorkbook().getSheetAt(0);
        if (sheet.getPhysicalNumberOfRows() == 0) {
            writer.writeRow(super.header);
        }

    }

    @Override
    protected void doWriteRow(LinkedHashMap<String, Object> row) {
        writer.writeRow(row.values());
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
