package table.master.console.client.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author zane
 * @date 2021/10/25
 */
public abstract class AbstractTool {


    public abstract String name();

    public abstract List<ToolParam> getParamList();

    public abstract void setParamList(List<ToolParam> list);

    public void inputParams() throws IOException {
        List<ToolParam> paramList = getParamList();
        for (ToolParam param : paramList) {
            System.out.println(param.tips);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            param.value = br.readLine();
            System.out.println(param.value);
        }

        setParamList(paramList);
    }

    public abstract void run();
}
