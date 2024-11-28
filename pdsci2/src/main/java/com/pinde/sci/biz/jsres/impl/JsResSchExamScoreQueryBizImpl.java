package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.jsres.IJsResSchExamScoreQueryBiz;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchExamDoctorArrangementMapper;
import com.pinde.sci.dao.jsres.JsResSchExamScoreQueryMapper;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import com.pinde.sci.model.mo.SchExamDoctorArrangementExample;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResSchExamScoreQueryBizImpl implements IJsResSchExamScoreQueryBiz {
    @Autowired
    private SchExamDoctorArrangementMapper doctorArrangementMapper;
    @Autowired
    private JsResSchExamScoreQueryMapper scoreQueryMapper;
    @Override
    public List<Map<String, Object>> userList(Map<String, Object> param) {
        return scoreQueryMapper.userList(param);
    }

    @Override
    public List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param) {
        return scoreQueryMapper.getDoctorArrangements(param);
    }

    @Override
    public int clearScore(List<String> userFlows, String sessionNumber, String assessmentYear, String orgFlow) {
        SchExamDoctorArrangement record = new SchExamDoctorArrangement();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        record.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        record.setModifyTime(DateUtil.getCurrentTime());

        SchExamDoctorArrangementExample example = new SchExamDoctorArrangementExample();
        SchExamDoctorArrangementExample.Criteria criteria = example.createCriteria();
        criteria.andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow).andAssessmentYearEqualTo(assessmentYear).andDoctorFlowIn(userFlows).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return doctorArrangementMapper.updateByExampleSelective(record, example);
    }

    @Override
    public SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow) {
        return doctorArrangementMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public void exportInfo(List<Map<String, Object>> list, List<String> years, Map<String, SchExamDoctorArrangement> doctorArrangementMap, HttpServletResponse response) throws IOException {
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
        titleList.add("证件号");
        titleList.add("人员类型");
        titleList.add("培训类别");
        titleList.add("培训专业");
        titleList.add("年级");
        titleList.addAll(years);
        String[] titles=new String[titleList.size()];
         titleList.toArray(titles);
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for(int i = 0 ; i<titles.length ; i++){
            cellTitle = rowDep.createCell(i);
            if(i>5)
                cellTitle.setCellValue(titles[i]+"年度");
            else
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
                int next=6;
                for(int j=0;j<years.size();j++) {
                    String year=years.get(j);
                    HSSFCell cell6 = rowDepts.createCell(next+j);
                    String v="--";
                    SchExamDoctorArrangement  seda=doctorArrangementMap.get(year+sd.get("doctorFlow")+sd.get("sessionNumber"));
                    if(seda!=null&&seda.getExamScore()!=null)
                    {
                        v=seda.getExamScore().toString();
                    }
                    cell6.setCellValue(v);
                    cell6.setCellStyle(styleCenter);
                }

                rownum++;
            }
        }

        String fileName = "年度考核成绩表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}
