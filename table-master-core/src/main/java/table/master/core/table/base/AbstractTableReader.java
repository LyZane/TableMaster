package table.master.core.table.base;

import java.io.Closeable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTableReader implements Closeable {

    protected List<String> header;
    protected int readDataRows = 0;


    public abstract List<String> doGetHeader();

    public abstract long getTotalDataRowCount();

    protected abstract LinkedHashMap<String, Object> doReadNextRow();

    public List<String> getHeader() {
        return doGetHeader();
    }

    public LinkedHashMap<String, Object> readNextRow() {
        try {
            return doReadNextRow();
        } finally {
            readDataRows++;
        }
    }


    public void readAll(Consumer<LinkedHashMap<String, Object>> consumer) {
        while (true) {
            LinkedHashMap<String, Object> row = readNextRow();
            if (row == null) {
                return;
            }
            consumer.accept(row);
        }


    }
}
