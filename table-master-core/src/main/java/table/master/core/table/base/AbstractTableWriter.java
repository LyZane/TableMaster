package table.master.core.table.base;

import java.io.Closeable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTableWriter implements Closeable {
    protected List<String> header;

    protected AbstractTableWriter(List<String> header) {
        this.header = header;
    }

    protected abstract void doWriteRow(LinkedHashMap<String, Object> row);

    public void writeRow(LinkedHashMap<String, Object> row) {
        doWriteRow(row);
    }
}
