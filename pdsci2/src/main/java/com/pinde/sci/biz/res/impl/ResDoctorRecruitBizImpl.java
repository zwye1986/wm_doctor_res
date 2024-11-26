package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.SysDict;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresExamSignupLogMapper;
import com.pinde.sci.dao.base.JsresExamSignupMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.core.common.enums.ResBaseStatusEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResDoctorRecruitBizImpl implements IResDoctorRecruitBiz {
    @Autowired
    private JsresExamSignupMapper jsresExamSignupMapper;
    @Autowired
    private ResDoctorRecruitMapper doctorRecruitMapper;
    @Autowired
    private ResDoctorRecruitExtMapper doctorRecruitExtMapper;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private ResScoreMapper resScoreMapper;
    @Autowired
    private JsresExamSignupLogMapper jsresExamSignupLogMapper;

    @Override
    public ResDoctorRecruit getNewRecruit(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo(ResBaseStatusEnum.Passed.getId());
            example.setOrderByClause("MODIFY_TIME DESC");
            List<ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }

    @Override
    public void noticeRetest(String recruitFlow, String retestNotice) {
        String content = retestNotice;
        ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
        recruit.setRecruitFlow(recruitFlow);
        recruit.setRetestFlag(GlobalConstant.FLAG_Y);
        recruit.setRetestNotice(retestNotice);
        editDoctorRecruit(recruit);
        ResDoctorRecruit rdr = readResDoctorRecruit(recruitFlow);
        SysUser exitUser = userBiz.readSysUser(rdr.getDoctorFlow());
        String title = InitConfig.getSysCfg("res_retest_notice_email_title");
        this.msgBiz.addEmailMsg(exitUser.getUserEmail(), title, content);
    }

    @Override
    public void noticeBackOpt(String recruitFlow, String flag) {
        ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
        recruit.setRecruitFlow(recruitFlow);
        if("fs".equals(flag)){
            recruit.setRetestFlag(GlobalConstant.FLAG_N);
        }
        if("lq".equals(flag)){
            recruit.setAdmitFlag(GlobalConstant.FLAG_N);
            recruit.setRecruitFlag("");
        }
        editDoctorRecruit(recruit);
    }

    @Override
    public int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit) {
        if (recruit != null) {
            if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
                GeneralMethod.setRecordInfo(recruit, false);
                return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
            } else {
                recruit.setRecruitFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(recruit, true);
                return doctorRecruitMapper.insertSelective(recruit);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruit(String recruitFlow) {
        return this.doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
    }

    @Override
    public ResDoctorRecruitWithBLOBs readResDoctorRecruitWithBLOBs(String recruitFlow) {
        return this.doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
    }

    @Override
    public int updateDocrecruit(ResDoctorRecruit recruit) {
        int result = 0;
        if (recruit != null) {
            result = doctorRecruitMapper.updateByPrimaryKey(recruit);
        }
        return result;
    }

    @Override
    public int updateDocrecruitByFlow(ResDoctorRecruitWithBLOBs recruit) {
        return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
    }

    @Override
    public List<ResDoctorRecruit> findResDoctorRecruits(String year,
                                                        String doctorFlow) {
        ResDoctorRecruit docRecruit = new ResDoctorRecruit();
        docRecruit.setRecruitYear(year);
        docRecruit.setDoctorFlow(doctorFlow);
        docRecruit.setRecordStatus(GlobalConstant.FLAG_Y);
        ResDoctorRecruitExample example = createResDoctorRecruitExample(docRecruit);
        example.setOrderByClause("CREATE_TIME DESC");
        List<ResDoctorRecruit> recruits = this.doctorRecruitMapper.selectByExample(example);
        return recruits;

    }

    private ResDoctorRecruitExample createResDoctorRecruitExample(ResDoctorRecruit docRecruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        if (docRecruit != null) {
            com.pinde.sci.model.mo.ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
            if (StringUtil.isNotBlank(docRecruit.getDoctorFlow())) {
                criteria.andDoctorFlowEqualTo(docRecruit.getDoctorFlow());
            }
            if (StringUtil.isNotBlank(docRecruit.getRecordStatus())) {
                criteria.andRecordStatusEqualTo(docRecruit.getRecordStatus());
            }
            if (StringUtil.isNotBlank(docRecruit.getRecruitYear())) {
                criteria.andRecruitYearEqualTo(docRecruit.getRecruitYear());
            }
        }
        return example;
    }

    @Override
    public List<ResDoctorRecruit> searchDoctorRecruit(ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
        }
        if (StringUtil.isNotBlank(recruit.getSpeId())) {
            criteria.andSpeIdEqualTo(recruit.getSpeId());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
            criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
            criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
        }
        example.setOrderByClause("EXAM_RESULT desc");
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorRecruit> searchDoctorRecruitWithLine(Map<String,Object> paramMap) {
        return doctorRecruitExtMapper.searchDoctorRecruitWithLine(paramMap);
    }

    @Override
    public List<ResDoctorRecruit> searchDoctorRecruits(ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }

        if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
            criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
        }

        example.setOrderByClause("create_time desc");
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorRecruit> searchRecruitByDoctor(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow);
            return doctorRecruitMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public ResDoctorRecruit searchRecruitByResDoctor(ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null!=recruit){
            if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(recruit.getOrgFlow())){
                criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
                criteria.andJointOrgFlowEqualTo(recruit.getOrgFlow());
            }
            if(StringUtil.isNotBlank(recruit.getSpeId())){
                criteria.andSpeIdEqualTo(recruit.getSpeId());
            }
            if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
            }
            if(StringUtil.isNotBlank(recruit.getSessionNumber())){
                criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
            }
            if(StringUtil.isNotBlank(recruit.getCatSpeId())){
                criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
            }
            if(StringUtil.isNotBlank(recruit.getRecruitYear())){
                criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
            }
            example.setOrderByClause("CREATE_TIME DESC");
            List<ResDoctorRecruit> list = doctorRecruitMapper.selectByExample(example);
            if(null!=list&&list.size()>0){
                return list.get(0);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    @Override
    public List<Map<String, String>> statisticDoctorCountMap(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.doctorCounMap(paramMap);
    }

    @Override
    public List<Map<String, Object>> getCurrDocDetails(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.getCurrDocDetails(paramMap);
    }
    @Override
    public List<Map<String, Object>> getcheckDocs(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.getcheckDocs(paramMap);
    }

    @Override
    public List<ResDoctor> searchDoctorByJd(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.searchDoctorByJd(paramMap);
    }

    @Override
    public List<ResDoctor> searchDoctorBySpe(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.searchDoctorBySpe(paramMap);
    }

    @Override
    public void exportForHbGlobal(List<Map<String, Object>> jdDoctorMaps, HttpServletResponse response, List<SysDict> doctorTypeDicts, String flag) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);
        //列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
        HSSFRow rowDep = sheet.createRow(0);//第一行
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        if(flag!=null&&"hospitalSearch".equals(flag)){
            cellOne.setCellValue("基地信息概况");
        }
        if(flag!=null&&"speSearch".equals(flag)){
            cellOne.setCellValue("专业信息概况");
        }

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles=null;
        if(flag!=null&&"hospitalSearch".equals(flag)){
            titles = new String[]{
                    "编号",
                    "培训基地",
                    doctorTypeDicts.get(0).getDictName(),
                    doctorTypeDicts.get(1).getDictName(),
                    doctorTypeDicts.get(2).getDictName(),
                    doctorTypeDicts.get(3).getDictName(),
                    "总人数"
            };
        }
        if(flag!=null&&"speSearch".equals(flag)){
            titles = new String[]{
                    "编号",
                    "专业",
                    doctorTypeDicts.get(0).getDictName(),
                    doctorTypeDicts.get(1).getDictName(),
                    doctorTypeDicts.get(2).getDictName(),
                    doctorTypeDicts.get(3).getDictName(),
                    "总人数"
            };
        }
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
        int rowNum = 2;
        String[] resultList = null;
        if (jdDoctorMaps != null && !jdDoctorMaps.isEmpty()) {
            for (int i = 0; i < jdDoctorMaps.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                Object temp1 = jdDoctorMaps.get(i).get(doctorTypeDicts.get(0).getDictId());
                String stringTemp1 = "";
                if(temp1==null){
                    stringTemp1 = "0";
                }else {
                    stringTemp1 = temp1.toString();
                }
                Object temp2 = jdDoctorMaps.get(i).get(doctorTypeDicts.get(1).getDictId());
                String stringTemp2 = "";
                if(temp2==null){
                    stringTemp2 = "0";
                }else {
                    stringTemp2 = temp2.toString();
                }
                Object temp3 = jdDoctorMaps.get(i).get(doctorTypeDicts.get(2).getDictId());
                String stringTemp3 = "";
                if(temp3==null){
                    stringTemp3 = "0";
                }else {
                    stringTemp3 = temp3.toString();
                }
                Object temp4 = jdDoctorMaps.get(i).get(doctorTypeDicts.get(3).getDictId());
                String stringTemp4 = "";
                if(temp4==null){
                    stringTemp4 = "0";
                }else {
                    stringTemp4 = temp4.toString();
                }
                if(flag!=null&&"hospitalSearch".equals(flag)){
                    resultList = new String[]{
                            i+1+"",
                            jdDoctorMaps.get(i).get("orgName").toString(),
                            stringTemp1,
                            stringTemp2,
                            stringTemp3,
                            stringTemp4,
                            jdDoctorMaps.get(i).get("sumCount").toString()
                    };
                }
                if(flag!=null&&"speSearch".equals(flag)){
                    resultList = new String[]{
                            i+1+"",
                            jdDoctorMaps.get(i).get("speName").toString(),
                            stringTemp1,
                            stringTemp2,
                            stringTemp3,
                            stringTemp4,
                            jdDoctorMaps.get(i).get("sumCount").toString()
                    };
                }
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }

            }
        }
        String fileName = "";
        if(flag!=null&&"hospitalSearch".equals(flag)){
            fileName = "基地信息概况一览表.xls";
        }
        if(flag!=null&&"speSearch".equals(flag)){
            fileName = "专业信息概况一览表.xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @Override
    public boolean queryIsConfirmFlag(String doctorFlow) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecruitFlagEqualTo(GlobalConstant.FLAG_Y).andConfirmFlagEqualTo(GlobalConstant.FLAG_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        return doctorRecruitMapper.countByExample(example)>0?true:false;
    }

    @Override
    public Map<String, Object> queryPlanStatistics(String recruityYear) {
        List<Map<String,Object>> dataList = doctorRecruitExtMapper.queryPlanStatistics(recruityYear);
        if(null != dataList && dataList.size() > 0){
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryOrgStatistics(String recruityYear) {
        return doctorRecruitExtMapper.queryOrgStatistics(recruityYear);
    }

    @Override
    public Map<String, Object> queryPlanStatisticsBefore(String recruityYear) {
        List<Map<String,Object>> dataList = doctorRecruitExtMapper.queryPlanStatisticsBefore(recruityYear);
        if(null != dataList && dataList.size() > 0){
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryOrgStatisticsBefore(String recruityYear) {
        return doctorRecruitExtMapper.queryOrgStatisticsBefore(recruityYear);
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber) {

        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo(ResBaseStatusEnum.Passed.getId())
            .andSessionNumberEqualTo(sessionNumber);
            example.setOrderByClause("MODIFY_TIME DESC");
            List<ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }

    @Override
    public JsresExamSignup getSignUpByYear(String year, String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample=new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSignupYearEqualTo(year).andSignupTypeIdEqualTo(typeId);

        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        if (recruitList != null && !recruitList.isEmpty()) {
            return recruitList.get(0);
        }
        return null;
    }

    @Override
    public List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample=new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSignupYearEqualTo(year).andSignupTypeIdEqualTo(typeId);

        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);

        return recruitList;
    }
    //获取该医师某一年份的所有补考记录
    @Override
    public List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow) {
        JsresExamSignupExample examSignupExample = new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSignupYearEqualTo(year);
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    @Override
    public List<JsresExamSignup> readDoctorExanSignUps( String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample=new JsresExamSignupExample();
        JsresExamSignupExample.Criteria criteria = examSignupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
        if(StringUtil.isNotEmpty(typeId)){
            criteria.andSignupTypeIdEqualTo(typeId);
        }
        examSignupExample.setOrderByClause("Signup_year desc");
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    @Override
    public int saveSignUp(JsresExamSignup signup) {
        if(StringUtil.isBlank(signup.getSignupFlow()))
        {
            signup.setSignupFlow(PkUtil.getGUID());
            GeneralMethod.setRecordInfo(signup,true);
            return jsresExamSignupMapper.insertSelective(signup);
        }else {
            GeneralMethod.setRecordInfo(signup,false);
            return jsresExamSignupMapper.updateByPrimaryKeySelective(signup);
        }
    }

    @Override
    public List<Map<String, Object>> queryExamSignUpList(Map<String, Object> param) {
        return doctorRecruitExtMapper.queryExamSignUpList(param);
    }

    @Override
    public JsresExamSignup readExamSignByFlow(String signupFlow) {
        return jsresExamSignupMapper.selectByPrimaryKey(signupFlow);
    }

    //获取医师的所有补考记录
    @Override
    public List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow) {
        JsresExamSignupExample examSignupExample = new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        examSignupExample.setOrderByClause("CREATE_TIME desc");
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    //获取所有的成绩记录
    @Override
    public List<ResScore> selectAllScore(String userFlow, String recruitFlow) {
        ResScoreExample example = new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(userFlow)) {
            criteria.andDoctorFlowEqualTo(userFlow);
        }
        if (StringUtil.isNotBlank(recruitFlow)) {
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        example.setOrderByClause("SCORE_PHASE_ID DESC");
        return resScoreMapper.selectByExample(example);
    }

    @Override
    public JsresExamSignup readByFlow(String signupFlow) {
        return jsresExamSignupMapper.selectByPrimaryKey(signupFlow);
    }

    //保存补考审核记录
    @Override
    public int saveChargeAudit(JsresExamSignup signup, JsresExamSignupLog log) {
        saveSignUp(signup);
        saveSignUpLog(log);
        return 1;
    }

    //获取补考审核的详细记录
    @Override
    public List<JsresExamSignupLog> getAuditLogsBySignupFlow(String signupFlow) {
        JsresExamSignupLogExample example = new JsresExamSignupLogExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSignupFlowEqualTo(signupFlow);
        example.setOrderByClause("create_time desc");
        return jsresExamSignupLogMapper.selectByExample(example);
    }

    //批量保存补考审核记录
    @Override
    public int saveBatchAudit(List<JsresExamSignup> signups, List<JsresExamSignupLog> logs) {
        for (JsresExamSignup signup : signups) {
            saveSignUp(signup);
        }
        for (JsresExamSignupLog log : logs) {
            saveSignUpLog(log);
        }
        return 1;
    }

    private int saveSignUpLog(JsresExamSignupLog log) {
        if (StringUtil.isNotBlank(log.getRecordFlow())) {
            GeneralMethod.setRecordInfo(log, false);
            return jsresExamSignupLogMapper.updateByPrimaryKeySelective(log);
        } else {
            log.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(log, true);
            return jsresExamSignupLogMapper.insertSelective(log);
        }
    }

    @Override
    public ResDoctorRecruit getNewRecruitByDoctorFlow(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
            example.setOrderByClause("MODIFY_TIME DESC");
            List<ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }
}
 