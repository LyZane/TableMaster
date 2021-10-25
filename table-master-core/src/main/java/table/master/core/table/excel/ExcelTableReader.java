package table.master.core.table.excel;

import org.apache.poi.ss.usermodel.Workbook;
import table.master.common.ExcelReader;
import table.master.core.table.base.AbstractTableReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ExcelTableReader extends AbstractTableReader {
    private final ExcelReader reader;

    public ExcelTableReader(Workbook workbook) throws IOException {
        reader = new ExcelReader(workbook);
    }

    public ExcelTableReader(File file) throws IOException {
        reader = new ExcelReader(file);
    }

    @Override
    public List<String> doGetHeader() {
        return reader.getColumnNames(0);
    }

    @Override
    public long getTotalDataRowCount() {
        return reader.getRowCount(0) - 1;
    }

    @Override
    protected LinkedHashMap<String, Object> doReadNextRow() {
        // Read data row
        List<Object> row = reader.getRowData(0, readDataRows + 1);

        if (row == null) {
            return null;
        }

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < header.size(); i++) {

            Object value = row.size() > i ? row.get(i) : null;

            map.put(header.get(i), value);
        }
        return map;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
