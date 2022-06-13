package table.master.core.table.csv;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import table.master.core.table.base.AbstractTableReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2022/6/13
 */
public class CsvTableReader extends AbstractTableReader {
    CsvReader reader = CsvUtil.getReader();
    CsvData data;

    public CsvTableReader(File file) {
        reader.setContainsHeader(true);
        data = reader.read(file);

    }

    @Override
    public List<String> doGetHeader() {
        return data.getHeader();
    }

    @Override
    public long getTotalDataRowCount() {
        return data.getRowCount();
    }

    @Override
    protected LinkedHashMap<String, Object> doReadNextRow() {
        if (readDataRows >= data.getRowCount()) {
            return null;
        }

        CsvRow row = data.getRow(readDataRows);
        return new LinkedHashMap<>(row.getFieldMap());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public static void main(String[] args) {
        CsvTableReader reader = new CsvTableReader(new File("/Users/zane/data/wefe/wefe_horz_train_provider_5560_standard--01.csv"));
        System.out.println(reader.getHeader());
        System.out.println(reader.getTotalDataRowCount());
        reader.readAll(row -> {
            System.out.println(reader.readDataRows);
        });
    }
}
