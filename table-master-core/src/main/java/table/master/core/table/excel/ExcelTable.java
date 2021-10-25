package table.master.core.table.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import table.master.common.CloseableUtil;
import table.master.core.table.base.AbstractTable;
import table.master.core.table.base.AbstractTableReader;
import table.master.core.table.base.AbstractTableWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ExcelTable extends AbstractTable {
    private ExcelTableReader reader;
    private ExcelTableWriter writer;

    public ExcelTable(File file) throws Exception {
        super(file);
        if (!file.exists()) {
            throw new Exception("文件尚未创建时，不能使用此方法构造。");
        }
    }

    public ExcelTable(List<String> header, File file) throws Exception {
        super(file);
        if (file.exists()) {
            throw new Exception("文件已创建时，不能使用此方法构造。");
        }

        // 创建文件
        Workbook workbook = WorkbookFactory.create(file);
        writer = new ExcelTableWriter(header, workbook);
        reader = new ExcelTableReader(workbook);
    }

    @Override
    public List<String> getHeader() {
        return null;
    }

    @Override
    public AbstractTableWriter getWriter() {
        return writer;
    }

    @Override
    public AbstractTableReader getReader() {
        return reader;
    }

    @Override
    public void close() throws IOException {
        CloseableUtil.close(reader);
        CloseableUtil.close(writer);
    }
}
