package table.master.console.client.tool;

import java.util.List;
import java.util.function.Function;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ToolParam {
    public String tips;
    public String value;
    public Function<List<ToolParam>, Boolean> checker;

    public ToolParam(String tips) {
        this.tips = tips;
    }
}
