package table.master.core.table.csv;

import table.master.core.table.base.AbstractTableWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2022/6/13
 */
public class CsvTableWriter extends AbstractTableWriter {
    protected CsvTableWriter(List<String> header) {
        super(header);
    }

    @Override
    protected void doWriteRow(LinkedHashMap<String, Object> row) {

    }

    @Override
    public void close() throws IOException {

    }
}
