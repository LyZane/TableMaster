package table.master.console.client.tool;

import table.master.core.action.MageTableAction;
import table.master.core.enums.TableType;
import table.master.core.table.base.AbstractTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zane
 * @date 2021/10/25
 */
public class MageTableTool extends AbstractTool {

    @Override
    public void inputParams() throws IOException {
        new ToolParam("请指定文件所在的目录（把文件夹拖进来会自动填充路径）：");
        System.out.println();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //java.io.InputStreamReader继承了Reader类
        String line = br.readLine();
        System.out.println(line);
        TableType tableType, List<AbstractTable > tableList, File outputFile
    }

    @Override
    public void run() {
        new MageTableAction()
    }
}
