package table.master.core.table.base;

import java.io.Closeable;
import java.io.File;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTable implements Closeable {

    private File file;

    public AbstractTable(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public abstract AbstractTableWriter getWriter();

    public abstract AbstractTableReader getReader();
}
