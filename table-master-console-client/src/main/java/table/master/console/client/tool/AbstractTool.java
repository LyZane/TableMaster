package table.master.console.client.tool;

import table.master.console.client.ConsoleInput;

import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTool {

    protected abstract List<ToolParam> getParamList();

    protected abstract void setParamList(List<ToolParam> list) throws Exception;

    protected void inputParams() throws Exception {
        List<ToolParam> paramList = getParamList();
        for (ToolParam param : paramList) {

            do {
                System.out.println(param.tips);
                param.value = ConsoleInput.getLine();
            }
            while (param.checker != null && !param.checker.apply(paramList));
        }

        setParamList(paramList);
    }

    protected abstract void run() throws Exception;

    public void start() throws Exception {
        inputParams();
        run();
    }
}
