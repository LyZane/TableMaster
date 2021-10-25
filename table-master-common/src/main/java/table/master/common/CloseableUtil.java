package table.master.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * @author zane
 * @date 2021/10/25
 */
public class CloseableUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CloseableUtil.class);

    public static void close(Closeable obj) {
        if (obj == null) {
            return;
        }
        try {
            obj.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            obj = null;
        }
    }
}
