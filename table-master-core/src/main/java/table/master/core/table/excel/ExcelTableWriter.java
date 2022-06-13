package table.master.core.table.excel;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.RowUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import table.master.core.table.base.AbstractTableWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ExcelTableWriter extends AbstractTableWriter {

    private BigExcelWriter writer;

    public ExcelTableWriter(List<String> header, File file) {
        super(header, file);
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


    // public void writeRowToSheet(LinkedHashMap<String, Object> row) {
    //
    //     if (writer.getWorkbook().getSheet(sheetName) == null) {
    //         addSheet(sheetName, row.keySet());
    //     }
    //     Sheet sheet = writer.getWorkbook().getSheet(sheetName);
    //     Row sheetRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
    //     RowUtil.writeRow(sheetRow, row.values(), writer.getStyleSet(), false);
    // }

    public void addSheet(String sheetName, Collection<String> header) {
        // 创建 sheet
        if (sheetName == null) {
            sheetName = "TableMaster" + writer.getWorkbook().getNumberOfSheets();
        }
        writer.getWorkbook().createSheet(sheetName);

        // 初始化列头行
        Sheet sheet = writer.getWorkbook().getSheet(sheetName);
        Row sheetRow = sheet.createRow(0);
        RowUtil.writeRow(sheetRow, header, writer.getStyleSet(), true);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
