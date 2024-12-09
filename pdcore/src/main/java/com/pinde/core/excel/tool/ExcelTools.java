package com.pinde.core.excel.tool;

import com.alibaba.excel.EasyExcel;

import java.util.Map;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/10/25/13:35
 * @Description:
 */
public class ExcelTools {

    public static void fillWithTemplate(String modelPath,
                                        String filePath,
                                        String sheetName,
                                        Map<String,Object> map){
        EasyExcel.write(filePath).withTemplate(modelPath).sheet(sheetName).doFill(map);
    }
}
