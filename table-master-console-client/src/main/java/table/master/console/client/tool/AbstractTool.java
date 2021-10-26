package table.master.console.client.tool;

import table.master.console.client.ConsoleInput;

import java.io.IOException;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTool {

    protected abstract List<ToolParam> getParamList();

    protected abstract void setParamList(List<ToolParam> list) throws IOException;

    protected void inputParams() throws IOException {
        List<ToolParam> paramList = getParamList();
        for (ToolParam param : paramList) {
            System.out.println(param.tips);
            param.value = ConsoleInput.getLine();
            System.out.println(param.value);
        }

        setParamList(paramList);
    }

    protected abstract void run() throws Exception;

    public void start() throws Exception {
        inputParams();
        run();
    }
}
