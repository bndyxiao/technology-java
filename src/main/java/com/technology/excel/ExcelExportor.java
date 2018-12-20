package com.technology.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import com.technology.util.StringUtils;
import com.technology.util.constant.SystemConstant;
import org.springframework.util.CollectionUtils;

/**
 * Excel导出通用类
 */
@Slf4j
public class ExcelExportor<T> {
    
    /** 导出excel过程中,出错的数据行*/
    private List<T> errorDatas = new ArrayList<>();
    /** 表头的配置信息*/
    private Map<String,ExcelHeader> headerMap = new HashMap<>();
    
    /* ****************************************************************************
     *          个性化定制要导出的数据样式
     * ****************************************************************************/
    
    /** 每一个sheet表格默认可以导出多少行*/
    public int maxSheetRows = 65500;
    
    /** 导出的列的头部 左边框*/
    private BorderStyle headerBorderLeft = BorderStyle.THIN;
    /** 导出的列的头部 右边框*/
    private BorderStyle headerBorderRight = BorderStyle.THIN;
    /** 导出的列的头部 底部边框*/
    private BorderStyle headerBorderBottom = BorderStyle.THIN;
    /** 导出的列的头部 顶部边框*/
    private BorderStyle headerBorderTop = BorderStyle.THIN;
    /** 导出的列的头部 文字对齐方式*/
    private HorizontalAlignment headerCellTextAlign = HorizontalAlignment.CENTER;
    /** 导出的列的头部 文字垂直对齐方式*/
    private VerticalAlignment headerCellVehicleAlign = VerticalAlignment.CENTER;
    /** 导出的列的头部 背景颜色*/
    private Short headerCellBackgroundColor = IndexedColors.WHITE.index;
    
    /** 表头字体颜色*/
    private Short headerFontColor = IndexedColors.VIOLET.index;
    /** 表头字体的高度，即大小*/
    private Short headerFontHeight = 12;
    
    /** 单元格的字体颜色*/
    private Short cellFontColor = IndexedColors.BLUE.index;
    /** 导出的内容的左边框*/
    private BorderStyle contBorderLeft = BorderStyle.THIN;
    /** 导出的内容的右边框*/
    private BorderStyle contBorderRight = BorderStyle.THIN;
    /** 导出的内容的底部边框*/
    private BorderStyle contBorderBottom = BorderStyle.THIN;
    /** 导出的内容的顶部边框*/
    private BorderStyle contBorderTop = BorderStyle.THIN;
    /** 导出的内容的文本水平对齐方式*/
    private HorizontalAlignment contCellTextAlign = HorizontalAlignment.CENTER;
    /** 导出的内容的文本垂直对齐方式*/
    private VerticalAlignment contCellVehicleAlign = VerticalAlignment.CENTER;
    /** 导出的内容的背景颜色*/
    private Short contCellBackgroundColor = IndexedColors.WHITE.index;
    
    
    public ExcelExportor(){
    }
    
    public Map<String, String> exportMultiFilesExcel(Map<String, List<? extends T>> datasetMap,String exportFilePath) {
        Map<String, String> map = new HashMap<String, String>();
        
        if (datasetMap.isEmpty()) {
            map.put("error", "没有任何要导出的数据,无法执行导出操作.");
            return map;
        }
        //数据构造
        try {
        	// 5. 将表格输出到指定的输出流
        	exportMultiFileHandle(datasetMap, map, null,exportFilePath);
        } catch (Exception e) {
            String errorMsg = "生成excel表格失败";
            log.error(errorMsg, e);
            map.put("error", errorMsg);
            return map;
        }
        return map;
    }
    
    private void exportMultiFileHandle(Map<String, List<? extends T>> datasetMap, 
            Map<String, String> resultMap, Map<String, Class<? extends T>> classMap, String exportFilePath) {
        boolean hasClassMap = !CollectionUtils.isEmpty(classMap);
        if(classMap == null){
            classMap = new HashMap<>();
        }
  
        HSSFWorkbook workbook = null;
        FileOutputStream out = null;
        boolean hasData = false;
        int index = 0;
        for (Entry<String, List<? extends T>> entry : datasetMap.entrySet()) {
        	index++;
            String fileName = entry.getKey();
            String filePath = exportFilePath + fileName + ".xls";
            try {
				out = new FileOutputStream(filePath);
				
            } catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            workbook = new HSSFWorkbook();
            String title = StringUtils.isEmpty(entry.getKey()) ? ("Sheet" + index) : entry.getKey();
            List<? extends T> dataset = entry.getValue();

            // 每一次要开始导出操作,清空errorDatas
            errorDatas.clear();
            if (!hasClassMap && CollectionUtils.isEmpty(dataset)) {
                resultMap.put("error", title + "：没有要导出的数据.");
                continue;
            }
            hasData = true;
            // 当前要导出的数据对象类型
            Class<? extends T> clazz = classMap.get(entry.getKey());
            T beanType = null;
            try {
                if(!CollectionUtils.isEmpty(dataset)){
                    beanType = dataset.get(0);
                }else if(clazz != null){
                    beanType = clazz.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                resultMap.put("error", "实例化数据对象失败.");
                continue;
            }

            // 2. 生成一个表格
            int sheets = 1;
            int dataSize = dataset.size();
            if (dataSize > maxSheetRows) {
                sheets = (dataSize % maxSheetRows == 0) ? (dataSize / maxSheetRows) : (dataSize / maxSheetRows + 1);
            }

            int minIndex = 0, maxIndex = 0;
            for (int i = 0; i < sheets; i++) {
                HSSFSheet sheet = workbook.createSheet(title + (i == 0 ? "" : "" + i));

                // 3. 生成表头
                int maxRows = generateHeader(workbook, sheet, beanType);
                // 4. 将数据集的每一个数据项写到excel的每一行
                minIndex = i * maxSheetRows;
                if (dataSize / ((i + 1) * maxSheetRows) > 0) {
                    maxIndex = (i + 1) * maxSheetRows;
                } else {
                    maxIndex = dataSize;
                }

                List<? extends T> dataRows = dataset.subList(minIndex, maxIndex);
                exportRowDatas(dataRows, workbook, sheet, maxRows);
            }
            try {
				workbook.write(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(null != null){
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
        }
        if(!hasData){
            workbook.createSheet();
        }
    }
    
    
    /**
     * @Description: 通用导出excel方法，支持同时导出多个不同的sheet，不同的数据源
     * @param datasetMap
     * @param out
     * @return
     */
    public Map<String, String> exportExcel(Map<String, List<? extends T>> datasetMap, OutputStream out) {
        Map<String, String> map = new HashMap<String, String>();
        if (out == null) {
            map.put("error", "输出流为空,无法继续执行操作.");
            return map;
        }
        if (CollectionUtils.isEmpty(datasetMap)) {
            map.put("error", "没有任何要导出的数据,无法执行导出操作.");
            return map;
        }
        //数据构造
        HSSFWorkbook workbook = exportMultipleHandle(datasetMap, map, null);
        // 5. 将表格输出到指定的输出流
        try {
            workbook.write(out);
        } catch (IOException e) {
            String errorMsg = "生成excel表格失败";
            log.error(errorMsg, e);
            map.put("error", errorMsg);
            return map;
        }
        return map;
    }
    /**
     * @Description: 通用导出excel方法，支持同时导出多个不同的sheet，不同的数据源 TODO 所有的动态字段处理
     * @param datasetMap 导出数据集
     * @param classMap 数据集类映射 String-Class
     * @param out 输出流
     */
    public Map<String, String> exportExcel(Map<String, List<? extends T>> datasetMap, 
            Map<String, Class<? extends T>> classMap, OutputStream out) {
        Map<String, String> map = new HashMap<String, String>();
        if (out == null) {
            map.put("error", "输出流为空,无法继续执行操作.");
            return map;
        }
        if (CollectionUtils.isEmpty(datasetMap)) {
            map.put("error", "没有任何要导出的数据,无法执行导出操作.");
            return map;
        }
        //数据构造
        HSSFWorkbook workbook = exportMultipleHandle(datasetMap, map, classMap);
        // 5. 将表格输出到指定的输出流
        try {
            workbook.write(out);
        } catch (IOException e) {
            String errorMsg = "生成excel表格失败";
            log.error(errorMsg, e);
            map.put("error", errorMsg);
            return map;
        }
        return map;
    }

    /**
     * @Description: 导出多workbook数据处理
     * @param datasetMap 待导出数据集合
     * @param resultMap 结果集合 
     * @param classMap 数据类映射集合
     */
    private HSSFWorkbook exportMultipleHandle(Map<String, List<? extends T>> datasetMap, 
            Map<String, String> resultMap, Map<String, Class<? extends T>> classMap) {
        boolean hasClassMap = !CollectionUtils.isEmpty(classMap);
        if(classMap == null){
            classMap = new HashMap<>();
        }
        //1. 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        boolean hasData = false;
        int index = 0;
        for (Entry<String, List<? extends T>> entry : datasetMap.entrySet()) {
            index++;
            String title = StringUtils.isEmpty(entry.getKey()) ? ("Sheet" + index) : entry.getKey();
            List<? extends T> dataset = entry.getValue();

            // 每一次要开始导出操作,清空errorDatas
            errorDatas.clear();
            if (!hasClassMap && CollectionUtils.isEmpty(dataset)) {
                resultMap.put("error", title + "：没有要导出的数据.");
                continue;
            }
            hasData = true;
            // 当前要导出的数据对象类型
            Class<? extends T> clazz = classMap.get(entry.getKey());
            T beanType = null;
            try {
                if(!CollectionUtils.isEmpty(dataset)){
                    beanType = dataset.get(0);
                }else if(clazz != null){
                    beanType = clazz.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                resultMap.put("error", "实例化数据对象失败.");
                continue;
            }

            // 2. 生成一个表格
            int sheets = 1;
            int dataSize = dataset.size();
            if (dataSize > maxSheetRows) {
                sheets = (dataSize % maxSheetRows == 0) ? (dataSize / maxSheetRows) : (dataSize / maxSheetRows + 1);
            }

            int minIndex = 0, maxIndex = 0;
            for (int i = 0; i < sheets; i++) {
                HSSFSheet sheet = workbook.createSheet(title + (i == 0 ? "" : "" + i));

                // 3. 生成表头
                int maxRows = generateHeader(workbook, sheet, beanType);
                // 4. 将数据集的每一个数据项写到excel的每一行
                minIndex = i * maxSheetRows;
                if (dataSize / ((i + 1) * maxSheetRows) > 0) {
                    maxIndex = (i + 1) * maxSheetRows;
                } else {
                    maxIndex = dataSize;
                }

                List<? extends T> dataRows = dataset.subList(minIndex, maxIndex);
                exportRowDatas(dataRows, workbook, sheet, maxRows);
            }
        }
        if(!hasData){
            workbook.createSheet();
        }
        return workbook;
    }
    
    /**
     * 通用的excel导出方法<br>
     * 注：<br>
     * 1. 当前版本不支持 boolean类型,byte[]类型  这些类型的请自己转成String或者int类型然后用 @ExcelExport 标记为要导出的字段<br>
     * 2. 每次要导出多少行,由开发者自行设定,此处不做限定; 但是每5000条数据的时候,将创建多个sheet表格<br>
     * 3. 如果要获取导出失败的数据,通过getErrorDatas()来获取<br>
     * 
     * @param title   excel文件名称
     * @param dataset   需要显示的数据集合,集合里面的元素必须是JavaBean, 然后对于要导出的列 用 @ExcelExport annotation标记; 具体注解的使用,参照注解类的说明
     * @param out       与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     *
     * @return Map&lt;Key, Msg&gt; <br>
     *             Key--表示成功与否的类型      "success 成功;     "error 错误;<br>
     *             Key--表示成功与否的类型      "success 成功;     "error 错误;<br>
     *             Msg--表示成功或者失败的原因，为什么从该方法返回<br>
     */
    public Map<String,String> exportExcel(String title, List<T> dataset, OutputStream out) {
        Map<String,String> map = new HashMap<>();
        if(out==null){
            map.put("error", "输出流为空,无法继续执行操作.");
            return map;
        }
        if(dataset==null || dataset.size()==0){
            map.put("error", "没有任何要导出的数据,无法执行导出操作.");
            return map;
        }
        if(StringUtils.isBlank(title)){
            title = "通用excel导出工具";
        }
        
        //每一次要开始导出操作,清空errorDatas
        errorDatas.clear();
        
        //当前要导出的数据对象类型
        T beanType = (T) dataset.get(0);
        
        //1. 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        //2. 生成一个表格
        int sheets = 1;
        int dataSize = dataset.size();
        if(dataSize>maxSheetRows){
            sheets = (dataSize%maxSheetRows==0)?(dataSize/maxSheetRows):(dataSize/maxSheetRows+1);
        }
        
        int minIndex = 0,maxIndex=0;
        for(int i=0; i<sheets; i++){
            HSSFSheet sheet = workbook.createSheet(title+(i==0?"":""+i));
            
            //3. 生成表头
            int maxRows = generateHeader(workbook, sheet, beanType);
            //4. 将数据集的每一个数据项写到excel的每一行
            minIndex = i*maxSheetRows;
            if(dataSize/((i+1)*maxSheetRows) > 0){
                maxIndex = (i+1)*maxSheetRows;
            } else {
                maxIndex = dataSize;
            }
            
            List<T> dataRows = dataset.subList(minIndex, maxIndex);
            exportRowDatas(dataRows, workbook, sheet, maxRows);
        }
        
        //5. 将表格输出到指定的输出流
        try {
            workbook.write(out);
        } catch (IOException e) {
            String errorMsg = "生成excel表格失败";
            log.error(errorMsg, e);
            map.put("error", errorMsg);
            return map;
        }
       
        map.put("success", "导出成功.");
        map.put("success", "导出成功.");
        return map;
    }

    /**
     * @Description: 将数据集的每一个数据项写到excel的每一行
     * @param dataset
     * @param workbook
     * @param sheet
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void exportRowDatas(List<? extends T> dataset, HSSFWorkbook workbook, HSSFSheet sheet, int maxRows) {
        //生成数据单元格的字体
        HSSFFont contFont = workbook.createFont();
        contFont.setColor(cellFontColor);
        //生成文本单元格内容的样式
        HSSFCellStyle cellStyle = generateContStyle(workbook);
        
        HSSFRow row;
        //遍历集合数据，产生数据行
        Iterator<? extends T> it = dataset.iterator();
        int index = maxRows;
        boolean isErrorRow = false;
        while (it.hasNext()) {
            row = sheet.createRow(index++);
            T rowData = (T) it.next();
            if(null == rowData){//处理空指针
            	continue;
            }
            isErrorRow = false;
            
            /** 是否动态生成列 */
            boolean isDynamic = rowData.getClass().isAnnotationPresent(ExcelDynamicColumn.class);
            
            //根据JavaBean属性的先后顺序,动态调用getXxx()方法得到属性值
            Field[] fields = rowData.getClass().getDeclaredFields();
            for (int i = 0,colIndex=i, length=fields.length; i < length; i++) {
                Field field = fields[i];
                if(!isExcelExport(field.getAnnotations())){
                    continue ;
                }
                
                /** 如果有动态列注解的字段，则特殊处理第一条数据 **/
                Map<String, Object> dynamicFieldMap = null;
                if (isDynamic) {
                    ExcelDynamicColumn dynamicColumn = rowData.getClass().getAnnotation(ExcelDynamicColumn.class);
                    String dynamicMethod = SystemConstant.GET + StringUtils.upperFirstCharName(dynamicColumn.dynamicFieldName());

                    try {
                        Method method = rowData.getClass().getMethod(dynamicMethod, new Class[] {});
                        dynamicFieldMap = (Map<String, Object>) method.invoke(rowData, new Object[] {});
                    } catch (Exception e) {
                        log.error("处理动态导出列失败!", e.getMessage());
                    }
                }
                
                HSSFCell cell = row.createCell(colIndex++);
                cell.setCellStyle(cellStyle);
                
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Class tCls = rowData.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                    getMethod.setAccessible(true);
                    Object value = getMethod.invoke(rowData, new Object[] {});
                    if(value == null){
                        value = "";
                    }
                    //类型转换处理
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        
                        ExcelHeader excelHeader = headerMap.get(""+(colIndex-1));//因为colIndex第几列已经增加过1了，所以此处减1获取正确的列
                        SimpleDateFormat sdf = new SimpleDateFormat(excelHeader.getDatePattern());
                        textValue = sdf.format(date);
                    } else {
                        //其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                        
                        //指定部分字体的颜色?
                        
                    }
                    HSSFRichTextString richText = new HSSFRichTextString(textValue);
                    richText.applyFont(contFont);
                    cell.setCellValue(richText);
                    
                } catch (Exception e) {
                    log.error("写入excel表格时出错", e);
                    isErrorRow = true;
                    break;
                } finally {
                    //清理资源
                }

                /** 处理动态列 */
                int dynamicIndex = colIndex;
                if (isDynamic && dynamicFieldMap != null && dynamicFieldMap.size() > 0) {
                    for (Entry<String, Object> entry : dynamicFieldMap.entrySet()) {
                        String value = entry.getValue() == null ? "" : String.valueOf(entry.getValue());
                        HSSFCell dynamicCell = row.createCell(dynamicIndex++);
                        dynamicCell.setCellStyle(cellStyle);
                        HSSFRichTextString richText = new HSSFRichTextString(value);
                        richText.applyFont(contFont);
                        dynamicCell.setCellValue(richText);
                    }
                }
            }
            if(isErrorRow){
                errorDatas.add(rowData);
            }
        }
    }
    
    /**
     * @Description: 生成表头数据  并返回表头共有多少行
     * @param workbook
     * @param sheet
     * @param beanType
     */
    @SuppressWarnings("unchecked")
    private int generateHeader(HSSFWorkbook workbook, HSSFSheet sheet, T beanType) {
        //生成标题栏的样式
        HSSFCellStyle headerStyle = generateHeaderStyle(workbook);
        HSSFRow[] headerRows = null;
        int maxRow = 0;
        int headerColIndex = 0;
        headerMap = new HashMap<>(); // 重置表头map
        
        /** 是否动态生成列 */
        boolean isDynamic = beanType.getClass().isAnnotationPresent(ExcelDynamicColumn.class);
        /** 是否第一条数据 */
        boolean isFirstRow = true; 
        /** 动态列的下一个位置 */
        int dynamicNextIndex = 0;
        Map<String, Object> dynamicFieldMap = null;
        
        Field[] fields = beanType.getClass().getDeclaredFields();
        for(Field field : fields ){
            Annotation[] annotions = field.getAnnotations();
            ExcelExport excelAnnotation = null;
            for(Annotation annotation : annotions){
                if(annotation instanceof ExcelExport){
                    excelAnnotation = (ExcelExport)annotation;
                    break;
                }
            }
            if(excelAnnotation == null){
                continue ;
            }
            
            /** 如果有动态列注解的字段，则特殊处理第一条数据 **/
            if (isDynamic && isFirstRow) {
                ExcelDynamicColumn dynamicColumn = beanType.getClass().getAnnotation(ExcelDynamicColumn.class);
                String dynamicMethod = SystemConstant.GET + StringUtils.upperFirstCharName(dynamicColumn.dynamicFieldName());

                try {
                    Method method = beanType.getClass().getMethod(dynamicMethod, new Class[] {});
                    dynamicFieldMap = (Map<String, Object>) method.invoke(beanType, new Object[] {});
                    isFirstRow = false;
                } catch (Exception e) {
                    log.error("处理动态导出列失败!", e.getMessage());
                }
            }
            
            String headerStr = excelAnnotation.header();
            String rowspanStr = excelAnnotation.rowspan();
            String colspanStr = excelAnnotation.colspan();
            String columnWidthStr = excelAnnotation.colWidth();
            String datePattern = excelAnnotation.datePattern();
            String[] headerArr = headerStr.split(",");
            String[] rowspanArr = rowspanStr.split(",");
            String[] colspanArr = colspanStr.split(",");
            int columnWidth = 0;
            try {
               columnWidth = Integer.parseInt(columnWidthStr);
               
               if(headerArr.length!=rowspanArr.length || headerArr.length!=colspanArr.length){
                   throw new Exception("表头,列数,行数 配置不一致, 检查是否用英文的逗号分隔好?");
               }
               int curMaxRow = 0;
               int curRowspan = 1, curColspan = 1;
               for(int i=0,len=rowspanArr.length; i<len; i++){
                   //验证数值的合法性
                   curRowspan = Integer.parseInt(rowspanArr[i]);
                   curColspan = Integer.parseInt(colspanArr[i]);
                   if(curRowspan<=0 || curColspan<=0){
                       throw new Exception("占据的列数或者行数不能小于等于0. 请检查(colspan and rowspan should be greater than 0).");
                   }
                   if(i==0 && curColspan>1){
                       throw new Exception("最接近数据的列永远都只能是1(即你用逗号分割的那串colspan,第一个值必须是1; 如: '1,2,3'), 请确认您的表格设计是否合理");
                   }
                   curMaxRow += curRowspan;
               }
               //验证表头的行数,列数是否一致
               if(maxRow<curMaxRow){
                   maxRow = curMaxRow;
               }
            } catch(Exception e){
                throw new RuntimeException(e.getMessage() + "\n配置出错,请原谅我这么简单的提示.(检查建议: colspan,rowspan,header 检查用逗号分割后是否长度一致? col,row是否是数值?)");
            }
            headerMap.put(""+headerColIndex++, new ExcelHeader(headerArr,rowspanArr,colspanArr,columnWidth,datePattern));
            
            /** 动态列下一个位置赋值 */
            dynamicNextIndex = headerColIndex;
        }
        
        /** 处理动态列 */
        if (isDynamic && dynamicFieldMap != null && dynamicFieldMap.size() > 0) {
            for (Entry<String, Object> entry : dynamicFieldMap.entrySet()) {
                String header = entry.getKey();
                headerMap.put(String.valueOf(dynamicNextIndex++), new ExcelHeader(new String[] { header },
                        new String[] { String.valueOf(maxRow) }, new String[] { ExcelHeader.DEFAULT_COLSPAN },
                        ExcelHeader.DEFAULT_COLWIDTH, ExcelHeader.DEFAULT_PATTERN));
            }
        }
        
        headerRows = new HSSFRow[maxRow];
        for(int i=0; i<maxRow; i++){
            headerRows[i] = sheet.createRow(i);
        }
        if(maxRow==0){
            throw new RuntimeException("表头行创建失败,请检查一下annotation的配置.");
        }
        
        for(int headerIndex=0,len=headerMap.size(); headerIndex<len; headerIndex++){
            ExcelHeader header = headerMap.get(""+headerIndex);
            String[] headerArr = header.getHeaderArr();
            String[] rowspanArr = header.getRowspanArr();
            String[] colspanArr = header.getColspanArr();
            
            //为表格的每一列设置列宽
            sheet.setColumnWidth(headerIndex, header.getColumnWidth()*256);
            
            //绘制表头
            int currentRow = maxRow-1 ;
            int minRowIndex = 0;
            for(int reverseRowIndex = headerArr.length-1,rowIndex=0; reverseRowIndex>=0; reverseRowIndex--,rowIndex++){
                if(rowIndex>0){
                    currentRow -= Integer.parseInt(rowspanArr[rowIndex-1]);//扣掉上一次的行跨度
                }
                
                if(Integer.parseInt(rowspanArr[rowIndex])>1 || Integer.parseInt(colspanArr[rowIndex])>1){
                    minRowIndex = currentRow-(Integer.parseInt(rowspanArr[rowIndex])-1);

                    // TODO 将unsafe去掉会导致合并单元格出现异常
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(minRowIndex, currentRow,
                            headerIndex,headerIndex+(Integer.parseInt(colspanArr[rowIndex])-1)));
                } else {
                    minRowIndex = currentRow;
                }
                
                //直接创建一个单元格.
                HSSFCell cell = headerRows[minRowIndex].createCell(headerIndex);
                cell.setCellStyle(headerStyle);
                
                //TODO headerArr[rowIndex] 变更为存放KEY 然后根据KEY 到数据库查询相应地区的表头的名称
                
                HSSFRichTextString richText = new HSSFRichTextString(headerArr[rowIndex]);
                cell.setCellValue(richText);
            }
        }
        
        return maxRow;
    }

    /**
     * Description: 生成表格的 头部的单元格样式
     * @param workbook
     * @return
     */
    private HSSFCellStyle generateHeaderStyle(HSSFWorkbook workbook) {
        //生成表格头部标题栏样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        // 设置这些样式
        headerStyle.setFillForegroundColor(headerCellBackgroundColor);
        
        headerStyle.setBorderBottom(headerBorderBottom);
        headerStyle.setBorderLeft(headerBorderLeft);
        headerStyle.setBorderRight(headerBorderRight);
        headerStyle.setBorderTop(headerBorderTop);
        
        headerStyle.setAlignment(headerCellTextAlign);
        headerStyle.setVerticalAlignment(headerCellVehicleAlign);
        
        // 生成字体
        HSSFFont font = workbook.createFont();
        font.setColor(headerFontColor);
        font.setFontHeightInPoints(headerFontHeight);
        font.setBold(true);
        
        // 把字体应用到当前的样式
        headerStyle.setFont(font);
        
        return headerStyle;
    }

    /**
     * @Description: 生成excel表格 单元格内容的样式
     * @param workbook
     * @return
     */
    private HSSFCellStyle generateContStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(contCellBackgroundColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        cellStyle.setBorderBottom(contBorderBottom);
        cellStyle.setBorderLeft(contBorderLeft);
        cellStyle.setBorderRight(contBorderRight);
        cellStyle.setBorderTop(contBorderTop);
        cellStyle.setAlignment(contCellTextAlign);
        
        cellStyle.setVerticalAlignment(contCellVehicleAlign);
        // 生成字体
        HSSFFont font = workbook.createFont();
        font.setBold(false);
        // 把字体应用到当前的样式
        cellStyle.setFont(font);
        
        return cellStyle;
    }

    
    /**
     * @Description: 判断是否包含 ExcelExport的annotation注解
     * @param annotations
     * @return  返回true表示要导出的列, false表示
     *
     */
    private boolean isExcelExport(Annotation[] annotations){
        for(Annotation annotation : annotations){
            if(annotation instanceof ExcelExport){
                return true;
            }
        }
        return false;
    }
    
    
/* ****************************************************************************
 *          getters and setters...
 * ****************************************************************************/
   


    public Short getHeaderCellBackgroundColor() {
        return headerCellBackgroundColor;
    }

    public void setHeaderCellBackgroundColor(Short headerCellBackgroundColor) {
        this.headerCellBackgroundColor = headerCellBackgroundColor;
    }



    public Short getContCellBackgroundColor() {
        return contCellBackgroundColor;
    }

    public void setContCellBackgroundColor(Short contCellBackgroundColor) {
        this.contCellBackgroundColor = contCellBackgroundColor;
    }

    public List<T> getErrorDatas() {
        return errorDatas;
    }
    
    public void setErrorDatas(List<T> errorDatas) {
        this.errorDatas = errorDatas;
    }

    public int getMaxSheetRows() {
        return maxSheetRows;
    }
    public Short getHeaderFontColor() {
        return headerFontColor;
    }
    public void setHeaderFontColor(Short headerFontColor) {
        this.headerFontColor = headerFontColor;
    }
    public Short getHeaderFontHeight() {
        return headerFontHeight;
    }
    public void setHeaderFontHeight(Short headerFontHeight) {
        this.headerFontHeight = headerFontHeight;
    }

    public Short getCellFontColor() {
        return cellFontColor;
    }
    public void setCellFontColor(Short cellFontColor) {
        this.cellFontColor = cellFontColor;
    }
    
    public void setMaxSheetRows(int maxSheetRows) {
        if(maxSheetRows<=0){
            throw new RuntimeException("最大的表格行数不能小于0");
        }
        
        this.maxSheetRows = maxSheetRows;
    }

    public Map<String, ExcelHeader> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, ExcelHeader> headerMap) {
        this.headerMap = headerMap;
    }

    public BorderStyle getContBorderLeft() {
        return contBorderLeft;
    }

    public void setContBorderLeft(BorderStyle contBorderLeft) {
        this.contBorderLeft = contBorderLeft;
    }

    public BorderStyle getContBorderRight() {
        return contBorderRight;
    }

    public void setContBorderRight(BorderStyle contBorderRight) {
        this.contBorderRight = contBorderRight;
    }

    public BorderStyle getContBorderBottom() {
        return contBorderBottom;
    }

    public void setContBorderBottom(BorderStyle contBorderBottom) {
        this.contBorderBottom = contBorderBottom;
    }

    public BorderStyle getContBorderTop() {
        return contBorderTop;
    }

    public void setContBorderTop(BorderStyle contBorderTop) {
        this.contBorderTop = contBorderTop;
    }

    public HorizontalAlignment getContCellTextAlign() {
        return contCellTextAlign;
    }

    public void setContCellTextAlign(HorizontalAlignment contCellTextAlign) {
        this.contCellTextAlign = contCellTextAlign;
    }

    public VerticalAlignment getContCellVehicleAlign() {
        return contCellVehicleAlign;
    }

    public void setContCellVehicleAlign(VerticalAlignment contCellVehicleAlign) {
        this.contCellVehicleAlign = contCellVehicleAlign;
    }

    public BorderStyle getHeaderBorderLeft() {
        return headerBorderLeft;
    }

    public void setHeaderBorderLeft(BorderStyle headerBorderLeft) {
        this.headerBorderLeft = headerBorderLeft;
    }

    public BorderStyle getHeaderBorderRight() {
        return headerBorderRight;
    }

    public void setHeaderBorderRight(BorderStyle headerBorderRight) {
        this.headerBorderRight = headerBorderRight;
    }

    public BorderStyle getHeaderBorderBottom() {
        return headerBorderBottom;
    }

    public void setHeaderBorderBottom(BorderStyle headerBorderBottom) {
        this.headerBorderBottom = headerBorderBottom;
    }

    public BorderStyle getHeaderBorderTop() {
        return headerBorderTop;
    }

    public void setHeaderBorderTop(BorderStyle headerBorderTop) {
        this.headerBorderTop = headerBorderTop;
    }

    public HorizontalAlignment getHeaderCellTextAlign() {
        return headerCellTextAlign;
    }

    public void setHeaderCellTextAlign(HorizontalAlignment headerCellTextAlign) {
        this.headerCellTextAlign = headerCellTextAlign;
    }

    public VerticalAlignment getHeaderCellVehicleAlign() {
        return headerCellVehicleAlign;
    }

    public void setHeaderCellVehicleAlign(VerticalAlignment headerCellVehicleAlign) {
        this.headerCellVehicleAlign = headerCellVehicleAlign;
    }
}
