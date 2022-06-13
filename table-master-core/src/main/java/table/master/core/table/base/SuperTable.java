package table.master.core.table.base;

import table.master.common.CloseableUtil;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public class SuperTable implements Closeable {
    protected AbstractTableReader reader;
    protected AbstractTableWriter writer;

    private boolean readOnly = false;
    private boolean writeOnly = false;

    private File file;


    /**
     * read only
     */
    public SuperTable(File file) throws IOException {
        this.file = file;
        if (!file.exists()) {
            throw new RuntimeException("文件尚未创建时，不能使用此方法构造。");
        }
        readOnly = true;
    }

    /**
     * write only
     */
    public SuperTable(List<String> header, File file) throws Exception {
        this.file = file;
        if (file.exists()) {
            throw new Exception("文件已创建时，不能使用此方法构造。");
        }
        writeOnly = true;
    }


    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public AbstractTableWriter getWriter() {
        return writer;
    }


    public AbstractTableReader getReader() {
        return reader;
    }


    @Override
    public void close() throws IOException {
        CloseableUtil.close(reader);
        CloseableUtil.close(writer);
    }
}
