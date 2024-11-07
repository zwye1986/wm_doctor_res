package com.pinde.sci.biz.sch.impl;


import com.pinde.sci.biz.sch.ISchMonthlyReportBiz;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchMonthlyReportBizImpl implements ISchMonthlyReportBiz {
    @Autowired
    private ResDoctorSchProcessExtMapper ResDoctorSchProcessExtMapper;

    @Override
    public List<SchProcessExt> searchPlanAccessDoc(Map<String, Object> paramMap) {
        List<SchProcessExt> planAccessDocList = ResDoctorSchProcessExtMapper.searchPlanAccessDoc(paramMap);
        return planAccessDocList;
    }

    @Override
    public List<SchProcessExt> searchAlreadyInDoc(Map<String, Object> paramMap) {
        return ResDoctorSchProcessExtMapper.searchAlreadyInDoc(paramMap);
    }

    @Override
    public List<SchProcessExt> searchInAndOutDoc(Map<String, Object> paramMap) {
        return ResDoctorSchProcessExtMapper.searchInAndOutDoc(paramMap);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void export4MonthlyReport(String fileName,String sheet1Title,
                                     String sheet2Title,
                                     List<Map<String,String>> dataList,
                                     List<Map<String,String>> dataList2,
                                     HttpServletResponse response) throws Exception {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet(sheet1Title);
        //列宽自适应
        sheet.setDefaultColumnWidth((short)50);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
        HSSFRow rowDep = sheet.createRow(0);//第一行
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue(sheet1Title);
        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));//合并单元格
        HSSFCell cellFour = rowTwo.createCell(0);
        cellFour.setCellStyle(styleCenter);
        cellFour.setCellValue("科室名称");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(1);
        cellFive.setCellStyle(styleCenter);
        cellFive.setCellValue("计划入科学员信息");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));//合并单元格
        HSSFCell cellEight = rowTwo.createCell(3);
        cellEight.setCellStyle(styleCenter);
        cellEight.setCellValue("计划出科学员信息");
        HSSFRow rowThree = sheet.createRow(2);//第三行
        String[] titles = new String[]{
                "",
                "姓名",
                "入科时间",
                "姓名",
                "带教老师",
                "出科时间",
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        int rowNum = 3;
        int inDocTotal=0;
        int outDocTotal=0;
        String[] resultList = null;
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                resultList = new String[]{
                        dataList.get(i).get("deptName"),
                        dataList.get(i).get("inDocName"),
                        dataList.get(i).get("inDate"),
                        dataList.get(i).get("outDocName"),
                        dataList.get(i).get("outTeaName"),
                        dataList.get(i).get("outDate")
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
                if(dataList.get(i).get("inDocName")!=""){
                    inDocTotal+=1;
                }
                if(dataList.get(i).get("outDocName")!=""){
                    outDocTotal+=1;
                }
            }
        }
        HSSFRow rowFoot = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 0));//合并单元格
        HSSFCell cellTotal1 = rowFoot.createCell(0);
        cellTotal1.setCellStyle(styleCenter);
        cellTotal1.setCellValue("总计");
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 2));//合并单元格
        HSSFCell cellTotalIn = rowFoot.createCell(1);
        cellTotalIn.setCellStyle(styleCenter);
        cellTotalIn.setCellValue(inDocTotal);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 5));//合并单元格
        HSSFCell cellTotalOut = rowFoot.createCell(3);
        cellTotalOut.setCellStyle(styleCenter);
        cellTotalOut.setCellValue(outDocTotal);
        //小结报告
        // 为工作簿添加sheet
        HSSFSheet sheet2 = wb.createSheet(sheet2Title);
        //列宽自适应
        sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
        HSSFRow rowDep1 = sheet2.createRow(0);//第一行
        rowDep1.setHeightInPoints(20);
        HSSFCell headerCell = rowDep1.createCell(0);
        headerCell.setCellStyle(styleCenter);
        headerCell.setCellValue(sheet2Title);
        HSSFRow rowTwo1 = sheet2.createRow(1);//第二行
        sheet2.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));//合并单元格
        HSSFCell cellFour1 = rowTwo1.createCell(0);
        cellFour1.setCellStyle(styleCenter);
        cellFour1.setCellValue("科室名称");
        sheet2.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));//合并单元格
        HSSFCell cellFive1 = rowTwo1.createCell(1);
        cellFive1.setCellStyle(styleCenter);
        cellFive1.setCellValue("已入科学员信息");
        sheet2.addMergedRegion(new CellRangeAddress(1, 1, 4, 7));//合并单元格
        HSSFCell cellEight1 = rowTwo1.createCell(4);
        cellEight1.setCellStyle(styleCenter);
        cellEight1.setCellValue("已出科学员信息");
        sheet2.addMergedRegion(new CellRangeAddress(1, 1, 8, 12));//合并单元格
        HSSFCell cellEight2 = rowTwo1.createCell(8);
        cellEight2.setCellStyle(styleCenter);
        cellEight2.setCellValue("未出科学员信息");
        HSSFRow rowThree1 = sheet2.createRow(2);//第三行
        String[] titles1 = new String[]{
                "",
                "姓名",
                "带教老师",
                "入科时间",
                "姓名",
                "带教老师",
                "完成比例",
                "出科时间",
                "姓名",
                "带教老师",
                "完成比例",
                "计划出科时间",
                "原因"
        };
        HSSFCell cellTitle1 = null;
        for (int i = 0; i < titles1.length; i++) {
            cellTitle1 = rowThree1.createCell(i);
            cellTitle1.setCellValue(titles1[i]);
            cellTitle1.setCellStyle(styleCenter);
            sheet2.setColumnWidth(i, titles1.length * 2 * 156);
        }
        int rowNum1 = 3;
        int alreadyInDocTotal=0;
        int alreadyOutDocTotal=0;
        int notOutDocTotal=0;
        String[] resultList1 = null;
        if (dataList2 != null && !dataList2.isEmpty()) {
            for (int i = 0; i < dataList2.size(); i++, rowNum1++) {
                HSSFRow rowFour1= sheet2.createRow(rowNum1);//第二行
                resultList1 = new String[]{
                    dataList2.get(i).get("deptName"),
                    dataList2.get(i).get("inDocName"),
                    dataList2.get(i).get("inTeacherName"),
                    dataList2.get(i).get("inDate"),
                    dataList2.get(i).get("outDocName"),
                    dataList2.get(i).get("outTeaName"),
                    dataList2.get(i).get("outSchPer"),
                    dataList2.get(i).get("outDate"),
                    dataList2.get(i).get("notOutDocName"),
                    dataList2.get(i).get("notOutTeaName"),
                    dataList2.get(i).get("notOutSchPer"),
                    dataList2.get(i).get("notOutDate"),
                    dataList2.get(i).get("reason")
                };
                for (int j = 0; j < titles1.length; j++) {
                    HSSFCell cellFirst1 = rowFour1.createCell(j);
                    cellFirst1.setCellStyle(styleCenter);
                    cellFirst1.setCellValue(resultList1[j]);
                }
                if(dataList2.get(i).get("inDocName")!=""){
                    alreadyInDocTotal+=1;
                }
                if(dataList2.get(i).get("outDocName")!=""){
                    alreadyOutDocTotal+=1;
                }
                if(dataList2.get(i).get("notOutDocName")!=""){
                    notOutDocTotal+=1;
                }
            }
        }
        HSSFRow rowFoot1 = sheet2.createRow(rowNum1);
        sheet2.addMergedRegion(new CellRangeAddress(rowNum1, rowNum1, 0, 0));//合并单元格
        HSSFCell cellTotal2 = rowFoot1.createCell(0);
        cellTotal2.setCellStyle(styleCenter);
        cellTotal2.setCellValue("总计");
        sheet2.addMergedRegion(new CellRangeAddress(rowNum1, rowNum1, 1, 3));//合并单元格
        HSSFCell inDocTotal2 = rowFoot1.createCell(1);
        inDocTotal2.setCellStyle(styleCenter);
        inDocTotal2.setCellValue(alreadyInDocTotal);
        sheet2.addMergedRegion(new CellRangeAddress(rowNum1, rowNum1, 4, 7));//合并单元格
        HSSFCell outDocTotal2 = rowFoot1.createCell(4);
        outDocTotal2.setCellStyle(styleCenter);
        outDocTotal2.setCellValue(alreadyOutDocTotal);
        sheet2.addMergedRegion(new CellRangeAddress(rowNum1, rowNum1, 8, 12));//合并单元格
        HSSFCell notOutDocTotal2 = rowFoot1.createCell(8);
        notOutDocTotal2.setCellStyle(styleCenter);
        notOutDocTotal2.setCellValue(notOutDocTotal);

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());
        }
    }

