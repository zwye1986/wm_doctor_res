package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyGraduationBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.GraduationExamResultsMapper;
import com.pinde.sci.dao.base.ResDoctorGraduationInfoMapper;
import com.pinde.sci.dao.res.ResDoctorGraduationInfoExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
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

/**
 * Created by pdkj on 2017/12/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JszyGraduationBizImpl implements IJszyGraduationBiz {

    @Autowired
    private ResDoctorGraduationInfoMapper graduationInfoMapper;
    @Autowired
    private ResDoctorGraduationInfoExtMapper graduationInfoExtMapper;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private GraduationExamResultsMapper resultsMapper;


    @Override
    public int editGraduationInfo(ResDoctorGraduationInfo resDoctorGraduationInfo) {
        if (StringUtil.isNotBlank(resDoctorGraduationInfo.getRecordFlow())) {
            GeneralMethod.setRecordInfo(resDoctorGraduationInfo, false);
            return graduationInfoMapper.updateByPrimaryKeySelective(resDoctorGraduationInfo);
        } else {
            GeneralMethod.setRecordInfo(resDoctorGraduationInfo, true);
            resDoctorGraduationInfo.setRecordFlow(PkUtil.getUUID());
            return graduationInfoMapper.insert(resDoctorGraduationInfo);
        }
    }

    @Override
    public ResDoctorGraduationInfo findGraduationInfo(String recordFlow) {
        return graduationInfoMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<ResDoctorGraduationInfo> searchGraduationInfoByMap(Map<String, Object> paramMap) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        if (paramMap.get("orgFlows") != null) {
            List<String> orgFlows = (List) paramMap.get("orgFlows");
            if (orgFlows.size() > 0) {
                criteria.andTrainingBaseFlowIn(orgFlows);
            }
        } if (paramMap.get("orgFlow") != null&&StringUtil.isNotBlank((String) paramMap.get("orgFlow"))) {
                criteria.andTrainingBaseFlowEqualTo((String) paramMap.get("orgFlow"));
        }
        if (paramMap.get("docTypeList") != null) {
            List<String> docTypeList = (List) paramMap.get("docTypeList");
            if (docTypeList.size() > 0) {
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if (paramMap.get("resDoctorGraduationInfo") != null) {
            ResDoctorGraduationInfo tempGraduation = (ResDoctorGraduationInfo) paramMap.get("resDoctorGraduationInfo");
            if (StringUtil.isNotBlank(tempGraduation.getDoctorName())) {
                criteria.andDoctorNameLike("%" + tempGraduation.getDoctorName() + "%");
            }
            if (StringUtil.isNotBlank(tempGraduation.getTrainingTypeId())) {
                criteria.andTrainingTypeIdEqualTo(tempGraduation.getTrainingTypeId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getTrainingSpeId())) {
                criteria.andTrainingSpeIdEqualTo(tempGraduation.getTrainingSpeId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getSessionNumber())) {
                criteria.andSessionNumberEqualTo(tempGraduation.getSessionNumber());
            }
            if (StringUtil.isNotBlank(tempGraduation.getGraduationYear())) {
                criteria.andGraduationYearEqualTo(tempGraduation.getGraduationYear());
            }
            if (StringUtil.isNotBlank(tempGraduation.getIdNo())) {
                criteria.andIdNoEqualTo(tempGraduation.getIdNo());
            }
            if (StringUtil.isNotBlank(tempGraduation.getCurrentAuditStatusId())) {
                criteria.andCurrentAuditStatusIdEqualTo(tempGraduation.getCurrentAuditStatusId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getCertificateNumber())) {
                criteria.andCertificateNumberLike("%" +tempGraduation.getCertificateNumber() +"%");
            }
            if (StringUtil.isNotBlank(tempGraduation.getDoctorFlow())) {
                criteria.andDoctorFlowEqualTo(tempGraduation.getDoctorFlow());
            }
        }
        if (paramMap.get("certificateNumber") != null) {
            criteria.andCertificateNumberIsNotNull();
        }
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (paramMap.get("order") != null) {
            example.setOrderByClause(paramMap.get("order").toString());
        }
        return graduationInfoMapper.selectByExample(example);
    }

    @Override
    public ResDoctorGraduationInfo findGraduationInfoByIdNo(String idNo) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIdNoEqualTo(idNo);
        List<ResDoctorGraduationInfo> list = graduationInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modifyBatch(List<String> recordFlows, ResDoctorGraduationInfo graduationInfo) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (recordFlows != null && recordFlows.size() > 0) {
            criteria.andRecordFlowIn(recordFlows);
        }
        return graduationInfoMapper.updateByExampleSelective(graduationInfo,example);
    }

    @Override
    public int createCertificate(ResDoctorGraduationInfo old) {
        ResDoctorGraduationInfo numInfo=graduationInfoExtMapper.getCreateCertificate(old);
        if(numInfo==null)
        {
            return 0;
        }
        if(StringUtil.isBlank(numInfo.getCertificateNumber())||StringUtil.isBlank(numInfo.getCertificateFlow()))
        {
            return 0;
        }
        int count= editGraduationInfo(numInfo);
        if(count==1)
        {
            ResDoctorRecruit recruit=recruitBiz.readResDoctorRecruit(numInfo.getRecruitFlow());
            if(recruit!=null)
            {
                recruit.setDoctorStatusId("21");
                recruit.setDoctorStatusName("结业");
                recruitBiz.updateDocrecruit(recruit);
            }
            ResDoctor doctor=doctorBiz.readDoctor(numInfo.getDoctorFlow());
            if(doctor!=null)
            {
                doctor.setDoctorStatusId("21");
                doctor.setDoctorStatusName("结业");
                doctorBiz.editDoctor(doctor);
            }
        }
        return count;
    }

    @Override
    public List<Map<String,Object>> getExamResults(Map<String, Object> param) {
        return graduationInfoExtMapper.getExamResults(param);
    }

    @Override
    public List<Map<String, Object>> findExamBydoctorFlow(String doctorFlow,String orgFlow) {
        return graduationInfoExtMapper.findExamBydoctorFlow(doctorFlow,orgFlow);
    }

    @Override
    public void exportInfo(Map<String,Object> map, List<Map<String, Object>> examResults, HttpServletResponse response) throws IOException {
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
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 17);
        HSSFFont fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        List<String> titleList = new ArrayList<>();
        titleList.add("姓名");
        titleList.add("身份证件号");
        titleList.add("人员类型");
        titleList.add("培训专业");
        titleList.add("对应专业");
        titleList.add("年级");
        titleList.add("最高分");
        titleList.add("考核次数");
        titleList.add("合格率");
        String[] titles = new String[titleList.size()];
        titleList.toArray(titles);
        //列宽自适应
        HSSFRow rowDep = sheet.createRow(0);
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowDep.createCell(i);

            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
        }
        int rownum = 1;
        if (examResults != null && !examResults.isEmpty()) {
            int i = 1;
            for (Map<String,Object> sd : examResults) {
                if(map.get(sd.get("doctorFlow")) != null){
                    ResDoctorExt rde = (ResDoctorExt) map.get(sd.get("doctorFlow"));
                    HSSFRow rowDepts = sheet.createRow(rownum);
                    HSSFCell cell = rowDepts.createCell(0);//序号
                    cell.setCellValue(rde.getSysUser().getUserName());
                    cell.setCellStyle(styleCenter);
                    HSSFCell cell1 = rowDepts.createCell(1);//培训基地
                    cell1.setCellValue(rde.getSysUser().getIdNo());
                    cell1.setCellStyle(styleCenter);
                    HSSFCell cell2 = rowDepts.createCell(2);//工作单位
//                cell2.setCellValue((String)sd.get("doctorTypeName"));
                    cell2.setCellValue(rde.getDoctorTypeName());
                    cell2.setCellStyle(styleCenter);
                    HSSFCell cell3 = rowDepts.createCell(3);//培训基地
//                cell3.setCellValue((String)sd.get("catSpeName"));
                    cell3.setCellValue(rde.getDoctorCategoryName());
                    cell3.setCellStyle(styleCenter);
                    HSSFCell cell4 = rowDepts.createCell(4);//工作单位
//                cell4.setCellValue((String)sd.get("speName"));
                    cell4.setCellValue(rde.getTrainingSpeName());
                    cell4.setCellStyle(styleCenter);
                    HSSFCell cell5 = rowDepts.createCell(5);
//                cell5.setCellValue((String)sd.get("sessionNumber"));
                    cell5.setCellValue(rde.getSessionNumber());
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
        }

        String fileName = "结业理论模拟考核成绩表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public GraduationExamResults getResultByFlow(String resultId) {
        return resultsMapper.selectByPrimaryKey(resultId);
    }


}
