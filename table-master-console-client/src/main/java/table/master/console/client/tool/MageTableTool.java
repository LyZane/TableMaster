package table.master.console.client.tool;

import table.master.common.StringUtil;
import table.master.core.ActionException;
import table.master.core.ApplicationContext;
import table.master.core.action.MageTableAction;
import table.master.core.table.base.SuperTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zane
 * @date 2021/10/25
 */
public class MageTableTool extends AbstractTool {
    private MageTableAction action;

    @Override
    protected List<ToolParam> getParamList() {
        return Arrays.asList(
                new ToolParam("请指定文件所在的目录（把文件夹拖进来会自动填充路径）："),
                new ToolParam(
                        "请指定合并后的文件类型：" + System.lineSeparator()
                                + "1. Excel" + System.lineSeparator()
                                + "2. Csv"
                )
        );
    }

    @Override
    protected void setParamList(List<ToolParam> list) throws Exception {
        ApplicationContext.WORK_DIR = list.get(0).value;
        String outputTableSuffix = "1".equals(list.get(1).value) ? "xlsx" : "csv";

        List<SuperTable> tableList = Files
                .list(ApplicationContext.getWorkDirPath())
                .map(x -> {
                    String suffix = StringUtil.substringAfterLast(x.toString(), ".").toLowerCase();
                    switch (suffix) {
                        case "xls":
                        case "xlsx":
                        case "cav":
                            try {
                                System.out.println("发现表格：" + x);
                                return new SuperTable(x.toFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        default:
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (tableList.isEmpty()) {
            ActionException.of(
                    "MageTableAction Error",
                    "在此目录未找到表格文件（excel/csv）：" + ApplicationContext.WORK_DIR
            );
        }

        File outputFile = new File(ApplicationContext.getAbsolutePath("mage." + outputTableSuffix));
        if (outputFile.exists()) {
            System.out.println("该文件已存在，将会被覆盖：" + outputFile.getAbsolutePath());
            outputFile.delete();
        }
        action = new MageTableAction(tableList, outputFile);
    }


    @Override
    protected void run() throws Exception {
        System.out.println("开始合并...");
        action.mage();
        System.out.println("合并结束");
    }
}
