package table.master.core.table.excel;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
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
        writer.write(super.header);
    }

    @Override
    protected void doWriteRow(LinkedHashMap<String, Object> row) {
        writer.write(row.values());
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
