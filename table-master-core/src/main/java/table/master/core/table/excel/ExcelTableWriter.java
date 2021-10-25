package table.master.core.table.excel;

import org.apache.poi.ss.usermodel.*;
import table.master.core.table.base.AbstractTableWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ExcelTableWriter extends AbstractTableWriter {

    private Workbook workbook;

    public ExcelTableWriter(List<String> header, Workbook workbook) {
        super(header);
        this.workbook = workbook;
    }

    private void initExcel() {
        // 初始化 sheet
        if (workbook.getNumberOfSheets() <= 0) {
            workbook.createSheet("TableMaster" + workbook.getNumberOfSheets());
        }

        // 初始化列头行
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row row = sheet.createRow(0);
            for (int i = 0; i < super.header.size(); i++) {
                Cell cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(super.header.get(i));
            }
        }
    }

    @Override
    protected void doWriteRow(LinkedHashMap<String, Object> row) {
        Sheet sheet = workbook.getSheetAt(0);
        Row excelRow = sheet.createRow(sheet.getPhysicalNumberOfRows());

        for (int i = 0; i < super.header.size(); i++) {
            Cell cell = excelRow.createCell(i, CellType.STRING);
            String columnName = super.header.get(i);
            Object value = row.get(columnName);
            cell.setCellValue(value == null ? "" : String.valueOf(value));
        }
    }

    @Override
    public void close() throws IOException {
        workbook.close();
    }
}
