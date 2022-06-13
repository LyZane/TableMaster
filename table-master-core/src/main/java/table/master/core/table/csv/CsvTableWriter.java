package table.master.core.table.csv;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import table.master.core.table.base.AbstractTableWriter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zane
 * @date 2022/6/13
 */
public class CsvTableWriter extends AbstractTableWriter {
    CsvWriter writer;

    public CsvTableWriter(List<String> header, File file) {
        super(header, file);
        writer = CsvUtil.getWriter(file, CharsetUtil.CHARSET_UTF_8);
        init();
    }

    private void init() {
        writer.write(super.header.toArray(new String[header.size()]));
    }

    @Override
    protected void doWriteRow(LinkedHashMap<String, Object> row) {
        String[] line = row.values().stream().map(x -> x.toString()).collect(Collectors.toList()).toArray(new String[header.size()]);
        writer.write(line);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
