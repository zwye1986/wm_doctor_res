package com.pinde.sci.common.util;

import com.pinde.core.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用方式 在biz层直接调用  ExcelUtile.importDataExcel(T.class,StartNum, wb, keys, new IExcelUtil<T>() {})
 *    T为某个实体类或为HashMap
 *    StartNum为从Execl第几行开始读取数据， wb 为输入流对象
 *    keys要与T对应（keys个数与execl中表头字段数量一致）; 如果T为实体类，keys应该为T的属性集合  如果T为HashMap，keys为Map中的key值
 *    new IExcelUtil<T>()将重新两个方法:
 *      一个是校验方法  checkExcelData(T t,ExcelUtile eu) 在这里对某一行数据进行校验
 *                                                      返回结果 可以是这里面的一个
 *                                                      ExcelUtile.RETURN,ExcelUtile.ERROR
 *                                                      ExcelUtile.BREAK,ExcelUtile.CONTINUE,null
 *      一个保存数据方法 operExcelData(ExcelUtile eu) eu.getExcelDatas()为校验之后的数据集
 */
public class ExcelUtile extends HashMap{

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExcelUtile.class);
    public static final String CURRENT_ROW = "currentRow";//当前行
    public static final String RETURN = "RETURN";//校验时返回信息码，出错不保存直接返回
    public static final String ERROR = "ERROR";//校验时返回信息码，出现异常直接报错
    public static final String BREAK = "BREAK";//校验时返回信息码，出错只保存出错行之前成功的信息
    public static final String CONTINUE = "CONTINUE";//校验时返回信息码，出错但只保存校验成功的信息
    String error_msg = "";
    List excelDatas;
    public List getExcelDatas() {
        return excelDatas;
    }

    public void setExcelDatas(List excelDatas) {
        this.excelDatas = excelDatas;
    }
    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
//        // EXCEL2003使用的是微软的文件系统
//        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//            return new HSSFWorkbook(inS);
//        }
//        // EXCEL2007使用的是OOM文件格式
//        if (POIXMLDocument.hasOOXMLHeader(inS)) {
//            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
//            return new XSSFWorkbook(OPCPackage.open(inS));
//        }
        try{
            return WorkbookFactory.create(inS);
        }catch (Exception e) {
            throw new IOException("不能解析的excel版本");
        }
    }
	 /**
	  * @param <T>
	 * @param clazz 返回的对象
	  * @param rows 需要忽略的表头行数
	  * @param wb wb对象
	  * @param topRows 表头转换的对象属性名
	  * @return
	  */
	 public static <T> ExcelUtile importDataExcel(
				 Class<T> clazz,
				 Integer rows,
				 Workbook wb,
				 String[] topRows,
				 IExcelUtil<T> operInterface
			 ){
         ExcelUtile eu = new ExcelUtile();
         eu.setExcelDatas(new ArrayList<T>());
		 int sheetNum = wb.getNumberOfSheets();
		 if(sheetNum>0){
            Sheet sheet;
            try{
                sheet = (HSSFSheet)wb.getSheetAt(0);
            }catch(Exception e){
                sheet = (XSSFSheet)wb.getSheetAt(0);
            }

            int row_num = sheet.getLastRowNum();
            if(row_num<1){
                throw new RuntimeException("该表格没有数据");
            }
             List<String> colnames = new ArrayList<String>();
             //获取表头
             Row titleR =  sheet.getRow(0);
             //获取表头单元格数
             int cell_num = titleR.getLastCellNum();
             String title = "";
             for(int i = 0 ; i <cell_num; i++){
                 title = titleR.getCell(i).getStringCellValue();
                 colnames.add(title);
             }
            eu.put("colnames",colnames);
            eu.put("SheetName",sheet.getSheetName());//保存文件中sheet的名字，用来判断是不是正确的导入文件
            for(int i = rows;i <=row_num; i++){//从忽略的行号开始读所有行
                Row r =  sheet.getRow(i);//获得一行的对象
                if(topRows==null || topRows.length==0){
                    throw new RuntimeException("映射配置为空");
                }
                int rowLength = topRows.length;
                T t;
                try{
                    t = clazz.newInstance();
                }catch(Exception e){
                    throw new RuntimeException("初始化实例对象失败");
                }
                eu.put(CURRENT_ROW,i);
                for(int j=0;j<rowLength;j++){//读一行
                    String value = "";
                    Cell cell = r.getCell(j);
//                    if (cell != null) {
//                        // 判断当前Cell的Type
//                        switch (cell.getCellType()) {
//                            // 如果当前Cell的Type为NUMERIC
//                            case HSSFCell.CELL_TYPE_NUMERIC: {
//                                // 如果是纯数字
//                                // 取得当前Cell的数值
//                                value = NumberToTextConverter.toText(cell.getNumericCellValue());
//                                break;
//                            }
//                            // 如果当前Cell的Type为STRIN
//                            case CellType.STRING:
//                                // 取得当前的Cell字符串
//                                value = cell.getStringCellValue().trim();
//                                break;
//                            case  HSSFCell.CELL_TYPE_BLANK:
//                                value = "";
//                                break;
//                            // 默认的Cell值
//                            default:{
//                                value = "";
//                            }
//                        }
//                    } else {
//                        value = "";
//                    }
                    if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
                            if (cell.getCellType().getCode() == 1) {
                                value = cell.getStringCellValue().trim();
                            } else {
                                value = _doubleTrans(cell.getNumericCellValue()).trim();
                            }
                        }
					 if(t instanceof Map){
                        ((Map)t).put(topRows[j],value);
                    }else{
                        setValue(t,topRows[j],value);
                    }
                }
                //校验数据信息
                String flay=operInterface.checkExcelData(t,eu);
                //校验失败直接返回
                if(RETURN.equals(flay))
                {
                    return eu;
                }else if(CONTINUE.equals(flay))
                {
                    continue;
                }else if(BREAK.equals(flay))
                {
                    break;
                }else if(ERROR.equals(flay))
                {
                    throw new RuntimeException(eu.getError_msg());
                }
                eu.getExcelDatas().add(t);
            }
             //保存数据
             operInterface.operExcelData(eu);
		 }
		return eu;
	}
	 public static <T> ExcelUtile importDataExcel2(
				 Class<T> clazz,
				 Integer rows,
				 Workbook wb,
				 String[] topRows,
				 IExcelUtil<T> operInterface
			 ){
         ExcelUtile eu = new ExcelUtile();
         eu.setExcelDatas(new ArrayList<T>());
		 int sheetNum = wb.getNumberOfSheets();
		 if(sheetNum>0){
            Sheet sheet;
            try{
                sheet = (HSSFSheet)wb.getSheetAt(0);
            }catch(Exception e){
                sheet = (XSSFSheet)wb.getSheetAt(0);
            }

             if(topRows==null || topRows.length==0){
                 throw new RuntimeException("映射配置为空");
             }
             Map<Integer,String> titleMap=new HashMap<>();
             Map<String,String> topMap=new HashMap<>();
             for(String top:topRows)
             {
                 String []kv=top.split(":");
                 if(kv.length<=1)
                 {
                     throw new RuntimeException("映射配置错误");
                 }
                 topMap.put(kv[0],kv[1]);
             }
            int row_num = sheet.getLastRowNum();
            if(row_num<1){
                throw new RuntimeException("该表格没有数据");
            }
             List<String> colnames = new ArrayList<String>();
             //获取表头
             Row titleR =  sheet.getRow(0);
             //获取表头单元格数
             int cell_num = titleR.getLastCellNum();
             String title = "";
             for(int i = 0 ; i <cell_num; i++){
                 title = titleR.getCell(i).getStringCellValue();
                 colnames.add(title);
                 titleMap.put(i,title);
             }

            eu.put("colnames",colnames);
            eu.put("SheetName",sheet.getSheetName());//保存文件中sheet的名字，用来判断是不是正确的导入文件
            for(int i = rows;i <=row_num; i++){//从忽略的行号开始读所有行
                Row r =  sheet.getRow(i);//获得一行的对象
                int rowLength = topRows.length;
                T t;
                try{
                    t = clazz.newInstance();
                }catch(Exception e){
                    throw new RuntimeException("初始化实例对象失败");
                }
                eu.put(CURRENT_ROW,i);
                for(int j=0;j<rowLength;j++){//读一行
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
                            if (cell.getCellType().getCode() == 1) {
                                value = cell.getStringCellValue().trim();
                            } else {
                                value = _doubleTrans(cell.getNumericCellValue()).trim();
                            }
                        }
                    String attr=topMap.get(titleMap.get(j));
                    if(StringUtil.isNotBlank(attr)) {
                        if (t instanceof Map) {
                            ((Map) t).put(attr, value);
                        } else {
                            setValue(t, attr, value);
                        }
                    }
                }
                //校验数据信息
                String flay=operInterface.checkExcelData(t,eu);
                //校验失败直接返回
                if(RETURN.equals(flay))
                {
                    return eu;
                }else if(CONTINUE.equals(flay))
                {
                    continue;
                }else if(BREAK.equals(flay))
                {
                    break;
                }else if(ERROR.equals(flay))
                {
                    throw new RuntimeException(eu.getError_msg());
                }
                eu.getExcelDatas().add(t);
            }
             //保存数据
             operInterface.operExcelData(eu);
		 }
		return eu;
	}
	public static void setValue(Object obj,String attrName,String attrValue){
		try{
			Class<?> objClass = obj.getClass();
			String firstLetter = attrName.substring(0,1).toUpperCase();
			String methedName = "set"+firstLetter+attrName.substring(1);
			Method setMethod = objClass.getMethod(methedName,new Class[] {String.class});
			setMethod.invoke(obj,new Object[] {attrValue});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    public static String errorMsg(ExcelUtile eu, String msg) {
        eu.put("count", 0);
        eu.put("code", "1");
        eu.put("msg", msg);
        return ExcelUtile.RETURN;
    }

    public static void addMergedRegionIfNotExists(HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        // 检查是否存在相同的合并区域
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress region = sheet.getMergedRegion(i);
            if (region.getFirstRow() == firstRow && region.getLastRow() == lastRow &&
                    region.getFirstColumn() == firstCol && region.getLastColumn() == lastCol) {
                // 合并区域已存在，直接返回
                return;
            }
        }

        // 合并区域不存在，添加新的合并区域
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }
}
