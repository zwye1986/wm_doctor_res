//package com.pinde;
//
//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.date.DateUtil;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.util.MapUtils;
//import com.pinde.excelUtils.enums.NumberEngEnum;
//
//import java.util.*;
//
///**
// * ~~~~~~~~~溺水的鱼~~~~~~~~
// *
// * @Author: 吴强
// * @Date: 2024/10/25/11:52
// * @Description:
// */
//public class Main {
//    public static void main(String[] args) {
//        String modelPath = "C:\\Users\\16088\\Desktop\\PbImportModel.xls";
//        String filePath = "D:\\linshi\\test.xls";
//        //填充模板示例
//        Map<String, Object> map = MapUtils.newHashMap();
//        map.put("userName","例:张三");
//        map.put("idNo","例:4312211......");
//        map.put("speName","例:内科");
//        map.put("sessionNumber","例:2022");
//        map.put("trainYear","例:三年");
//        //获取当前月份
//        for (int i = 0; i < 36; i++) {
//            int num = i + 1;
//            String key = "M"+ NumberEngEnum.getResult(num);
//            DateTime dateTime = DateUtil.offsetMonth(new Date(), i);
//            String M = DateUtil.format(dateTime, "yyyy-MM");
//            map.put(key,M);
//        }
//        EasyExcel.write(filePath).withTemplate(modelPath).sheet("sheet1").doFill(map);
//    }
//}