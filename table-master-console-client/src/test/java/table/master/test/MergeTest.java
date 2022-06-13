package table.master.test;

import table.master.common.StringUtil;
import table.master.core.action.MergeTableAction;
import table.master.core.table.base.SuperTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zane
 * @date 2021/10/26
 */
public class MergeTest {
    public static void main(String[] args) throws Exception {
        String baseDir = "/Users/zane/Code/TableMaster/table-master-console-client/src/test/resources/merge/case2";


        File outputFile = Paths.get(baseDir, "merge.xlsx").toFile();
        if (outputFile.exists()) {
            outputFile.delete();
        }

        List<SuperTable> tableList = Files
                .list(Paths.get(baseDir))
                .map(x -> {
                    String suffix = StringUtil.substringAfterLast(x.toString(), ".").toLowerCase();
                    switch (suffix) {
                        case "xls":
                        case "xlsx":
                        case "csv":

                            // 跳过结果文件
                            if (x.getFileName().equals("merge.")) {
                                return null;
                            }

                            try {
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

        MergeTableAction action = new MergeTableAction(tableList, outputFile);
        action.merge();

    }
}
