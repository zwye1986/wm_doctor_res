package com.pinde.sci.biz.jsres.impl;

import com.pinde.sci.biz.jsres.IJsResGraduationBiz;
import com.pinde.sci.dao.base.GraduationExamResultsMapper;
import com.pinde.sci.dao.jsres.JsResGraduationMapper;
import com.pinde.sci.dao.jsres.SchdualTaskMapper;
import com.pinde.sci.model.jsres.DoctorExamStatisticsExt;
import com.pinde.sci.model.mo.GraduationExamResults;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResGraduationBizImpl implements IJsResGraduationBiz {
    @Autowired
    private JsResGraduationMapper graduationMapper;
    @Autowired
    private GraduationExamResultsMapper resultsMapper;
    @Autowired
    private SchdualTaskMapper schdualTaskMapper;

    @Override
    public List<Map<String, Object>> userList(Map<String, Object> param) {
        return graduationMapper.userList(param);
    }

    @Override
    public List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param) {
        return graduationMapper.getDoctorArrangements(param);
    }

//    @Override
//    public int clearScore(List<String> userFlows, String sessionNumber, String assessmentYear, String orgFlow) {
//        SchExamDoctorArrangement record = new SchExamDoctorArrangement();
//        record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
//        record.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//        record.setModifyTime(DateUtil.getCurrentTime());
//
//        SchExamDoctorArrangementExample example = new SchExamDoctorArrangementExample();
//        SchExamDoctorArrangementExample.Criteria criteria = example.createCriteria();
//        criteria.andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow).andAssessmentYearEqualTo(assessmentYear).andDoctorFlowIn(userFlows).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
//        return doctorArrangementMapper.updateByExampleSelective(record, example);
//    }
//
//    @Override
//    public SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow) {
//        return doctorArrangementMapper.selectByPrimaryKey(recordFlow);
//    }

    @Override
    public void exportInfo(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        List<String> titleList=new ArrayList<>();
        titleList.add("姓名");
        titleList.add("身份证件号");
        titleList.add("人员类型");
        titleList.add("培训类别");
        titleList.add("培训专业");
        titleList.add("年级");
        titleList.add("最高分");
        titleList.add("考核次数");
        titleList.add("合格率");
        String[] titles=new String[titleList.size()];
         titleList.toArray(titles);
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);

            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue((String)sd.get("userName"));
                cell.setCellStyle(styleCenter);
                HSSFCell cell1 = rowDepts.createCell(1);//培训基地
                cell1.setCellValue((String)sd.get("idNo"));
                cell1.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(2);//工作单位
                cell2.setCellValue((String)sd.get("doctorTypeName"));
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(3);//培训基地
                cell3.setCellValue((String)sd.get("catSpeName"));
                cell3.setCellStyle(styleCenter);
                HSSFCell cell4 = rowDepts.createCell(4);//工作单位
                cell4.setCellValue((String)sd.get("speName"));
                cell4.setCellStyle(styleCenter);
                HSSFCell cell5 = rowDepts.createCell(5);
                cell5.setCellValue((String)sd.get("sessionNumber"));
                cell5.setCellStyle(styleCenter);
                HSSFCell cell6 = rowDepts.createCell(6);
                cell6.setCellValue(sd.get("theoryScore").toString());
                cell6.setCellStyle(styleCenter);
                HSSFCell cell7 = rowDepts.createCell(7);
                cell7.setCellValue(sd.get("totalNum").toString());
                cell7.setCellStyle(styleCenter);
                HSSFCell cell8 = rowDepts.createCell(8);
                int qN = Integer.parseInt(sd.get("qualifiedNum").toString());
                int tN = Integer.parseInt(sd.get("totalNum").toString());
                DecimalFormat df=new DecimalFormat("0");
                cell8.setCellValue(df.format((float)qN/tN*100)+"%");
                cell8.setCellStyle(styleCenter);
                rownum++;
            }
        }

        String fileName = "结业理论模拟考核成绩表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public List<Map<String,Object>> findExamBydoctorFlow(String doctorFlow) {

        return graduationMapper.findExamBydoctorFlow(doctorFlow);
    }

    @Override
    public GraduationExamResults getResultByFlow(String resultId) {
        return resultsMapper.selectByPrimaryKey(resultId);
    }

    @Override
    public List<DoctorExamStatisticsExt> searchExamStatisticsList(Map<String, Object> param) {
        return schdualTaskMapper.searchExamStatisticsList(param);
    }

    @Override
    public List<DoctorExamStatisticsExt> searchExamStatisticsList2(Map<String, Object> param) {
        return schdualTaskMapper.searchExamStatisticsList2(param);
    }

    @Override
    public List<DoctorExamStatisticsExt> searchExamStatisticsListBySpe(Map<String, Object> param) {
        return schdualTaskMapper.searchExamStatisticsListBySpe(param);
    }

    @Override
    public List<DoctorExamStatisticsExt> searchExamStatisticsListByCity(Map<String, Object> param) {
        return schdualTaskMapper.searchExamStatisticsListByCity(param);
    }

    @Override
    public List<DoctorExamStatisticsExt> searchExamStatisticsListByCity2(Map<String, Object> param) {
        return schdualTaskMapper.searchExamStatisticsListByCity2(param);
    }

    @Override
    public List<Map<String, Object>> searchDoctorRecruit(Map<String, Object> params) {
        return schdualTaskMapper.searchDoctorRecruit(params);
    }

    @Override
    public List<Map<String, Object>> searchJointOrgDoctorRecruit(Map<String, Object> params) {
        return schdualTaskMapper.searchJointOrgDoctorRecruit(params);
    }
}
