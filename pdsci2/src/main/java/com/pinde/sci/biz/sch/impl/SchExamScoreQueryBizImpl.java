package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.sci.dao.JsResSchExamScoreQueryMapper;
import com.pinde.core.common.sci.dao.ResScoreMapper;
import com.pinde.core.common.sci.dao.SchExamDoctorArrangementMapper;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.ResScoreExample;
import com.pinde.core.model.SchExamDoctorArrangement;
import com.pinde.sci.biz.sch.ISchExamScoreQueryBiz;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.model.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchExamScoreQueryBizImpl implements ISchExamScoreQueryBiz {
    @Autowired
    private SchExamDoctorArrangementMapper doctorArrangementMapper;
    @Autowired
    private JsResSchExamScoreQueryMapper scoreQueryMapper;
    @Autowired
    private ResScoreMapper resScoreMapper;
    @Override
    public List<Map<String, Object>> userList(Map<String, Object> param) {
        return scoreQueryMapper.userList(param);
    }

    @Override
    public List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param) {
        return scoreQueryMapper.getDoctorArrangements(param);
    }

    @Override
    public SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow) {
        return doctorArrangementMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public void exportInfo(List<ResDoctorExt> list, List<String> years, Map<String, SchExamDoctorArrangement> doctorArrangementMap, HttpServletResponse response) throws IOException {
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
            if(i>2)
                cellTitle.setCellValue(titles[i]+"年度");
            else
                cellTitle.setCellValue(titles[i]);

            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(ResDoctorExt sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(sd.getSysUser().getUserName());
                cell.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(1);//工作单位
                cell2.setCellValue(sd.getTrainingSpeName());
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(2);
                cell3.setCellValue(sd.getSessionNumber());
                cell3.setCellStyle(styleCenter);
                int next=3;
                for(int j=0;j<years.size();j++) {
                    String year=years.get(j);
                    HSSFCell cell4 = rowDepts.createCell(next+j);
                    String v="--";
                    SchExamDoctorArrangement  seda=doctorArrangementMap.get(year+sd.getDoctorFlow()+sd.getSessionNumber());
                    if(seda!=null&&seda.getExamScore()!=null)
                    {
                        v=seda.getExamScore().toString();
                    }
                    cell4.setCellValue(v);
                    cell4.setCellStyle(styleCenter);
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

    @Override
    public List<ResScore> getDoctorSkillScore(Map<String, Object> param) {
        List<ResScore> scoreList=new ArrayList<>();
        List<String> userFlows=(List<String>)param.get("userFlows");
        ResScoreExample example=new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(userFlows!=null&&userFlows.size()>0){
            criteria.andDoctorFlowIn(userFlows);
            criteria.andScoreTypeIdEqualTo("SkillScore");
            example.setOrderByClause("SCORE_PHASE_ID DESC");
            scoreList=resScoreMapper.selectByExampleWithBLOBs(example);
        }
        return scoreList;
    }

    @Override
    public void exportSkillScoreInfo(List<ResDoctorExt> list, List<String> years, Map<String, ResScore> doctorArrangementMap, HttpServletResponse response) throws IOException {
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
            if(i>2)
                cellTitle.setCellValue(titles[i]+"年度");
            else
                cellTitle.setCellValue(titles[i]);

            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if(list!=null&&!list.isEmpty()){
            int i=1;
            for(ResDoctorExt sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                HSSFCell cell = rowDepts.createCell(0);//序号
                cell.setCellValue(sd.getSysUser().getUserName());
                cell.setCellStyle(styleCenter);
                HSSFCell cell2 = rowDepts.createCell(1);//工作单位
                cell2.setCellValue(sd.getTrainingSpeName());
                if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
                    cell2.setCellValue(sd.getDoctorCategoryName());
                }
                cell2.setCellStyle(styleCenter);
                HSSFCell cell3 = rowDepts.createCell(2);
                cell3.setCellValue(sd.getSessionNumber());
                cell3.setCellStyle(styleCenter);
                int next=3;
                for(int j=0;j<years.size();j++) {
                    String year=years.get(j);
                    HSSFCell cell4 = rowDepts.createCell(next+j);
                    String v="--";
                    ResScore seda=doctorArrangementMap.get(year+sd.getDoctorFlow());
                    if(seda!=null&&seda.getSkillScore()!=null)
                    {
                        v="1".equals(seda.getSkillScore().toString())?"合格":("0".equals(seda.getSkillScore().toString())?"不合格":"缺考");
                    }
                    cell4.setCellValue(v);
                    cell4.setCellStyle(styleCenter);
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
