import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huangzhb
 * @Date: 2019年02月11日 09:22:11
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

// 表头定义 可以将表头配置在数据库中，然后在代码里动态生成表头
        // 这里只是展示如何用代码生成表头
        List<ExcelExportEntity> columnList = new ArrayList<ExcelExportEntity>();
        ExcelExportEntity colEntity1 = new ExcelExportEntity("序号", "id");
        colEntity1.setNeedMerge(true);
        columnList.add(colEntity1);

        ExcelExportEntity colEntity2 = new ExcelExportEntity("班级", "class");
        colEntity2.setNeedMerge(true);
        columnList.add(colEntity2);

        ExcelExportEntity yhxxGroup = new ExcelExportEntity("用户信息", "yhxx");
        List<ExcelExportEntity> yyxxList = new ArrayList<ExcelExportEntity>();
        yyxxList.add(new ExcelExportEntity("姓名", "name"));
        yyxxList.add(new ExcelExportEntity("年龄", "age"));
        yyxxList.add(new ExcelExportEntity("性别", "sex"));
        yhxxGroup.setList(yyxxList);
        columnList.add(yhxxGroup);

        // 数据拉取 一般需要从数据库中拉取
        // 这里是手动模拟数据
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> values = new HashMap<>();
            values.put("id", i);
            values.put("class", "班级" + i);

            List<Map<String, Object>> yhxxList = new ArrayList<Map<String, Object>>();
            Map<String, Object> yhxxMap = new HashMap<String, Object>();
            yhxxMap.put("name", "姓名" + i);
            yhxxMap.put("age", "年龄" + i);
            yhxxMap.put("sex", "性别" + i);
            yhxxList.add(yhxxMap);

            values.put("yhxx", yhxxList);
            dataList.add(values);
        }

        // 定义标题和sheet名称
        ExportParams exportParams = new ExportParams("", "人员数据");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, columnList, dataList);
        // 导入到本地目录，如果需要从浏览器导出，参看上一篇文章
        FileOutputStream fos  = null;
        try {
            fos = new FileOutputStream(new File("d:/test.xls"));
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processMap(Map map) {
        map = new HashMap();
        map.put(1,20);
    }



    public static boolean compareVersion(String version1, String version2) throws RuntimeException{
        if (version1 == null || version2 == null || version1.equals("") || version2.equals("")) {
            throw new RuntimeException("illegal params");
        }

        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int minLength = Math.min(versionArray1.length, versionArray2.length);

        int idx = 0;
        int diff = 0;

        while(idx < minLength
                && ((diff = versionArray1[idx].length() - versionArray2[idx].length())) == 0
                && ((diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0)) {
            idx++;
        }

        if (diff > 0) {
            return true;
        } else {
            return versionArray1.length - versionArray2.length > 0 ? true : false;
        }
    }





    public static boolean compareVersion1(String newVersion, String oldVersion) throws IllegalArgumentException{

        if (newVersion == null || newVersion.equals("") || !newVersion.contains("*")
                || oldVersion == null || oldVersion.equals("") || !oldVersion.contains("*")) {
            throw new IllegalArgumentException("参数不合法");
        }

        String[] newVersionArray = newVersion.substring(newVersion.indexOf("*")+1).split("\\.");
        String[] oldVersionArray = oldVersion.substring(oldVersion.indexOf("*")+1).split("\\.");

        int minLength = Math.min(newVersionArray.length, oldVersionArray.length);
        int diff = 0;
        int idx = 0;
        while (idx < minLength
                && ((diff = (newVersionArray[idx].length() - oldVersionArray[idx].length())) == 0)
                && ((diff = (newVersionArray[idx].compareTo(oldVersionArray[idx]))) == 0)) {
            idx++;
        }

        if (diff > 0) {
            return true;
        } else {
            return newVersionArray.length - oldVersionArray.length > 0 ? true : false;
        }
    }
}
