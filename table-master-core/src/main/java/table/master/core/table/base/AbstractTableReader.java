package table.master.core.table.base;

import java.io.Closeable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
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
        if (header != null) {
            return header;
        }
        header = doGetHeader();
        return header;
    }

    public LinkedHashMap<String, Object> readNextRow() {
        if (header == null) {
            getHeader();
        }

        try {
            LinkedHashMap<String, Object> row = doReadNextRow();
            // 跳过空行
            if (row != null && row.values().stream().noneMatch(Objects::nonNull)) {
                return null;
            }

            return row;
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
