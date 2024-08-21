package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.core.commom.enums.ResAssessTypeEnum;
import com.pinde.core.commom.enums.ResRecTypeEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.dao.jswjw.ext.DeptTeacherGradeInfoExtMapper;
import com.pinde.core.commom.enums.RecStatusEnum;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResGradeBizImpl implements IResGradeBiz {

    @Autowired
    private SysCfgMapper cfgMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private DeptTeacherGradeInfoMapper gradeInfoMapper;
    @Autowired
    private DeptTeacherGradeInfoExtMapper gradeInfoExtMapper;
    @Resource
    private ResDoctorMapper doctorMapper;
    @Resource
    private SchArrangeResultMapper resultMapper;
    @Resource
    private ResAssessCfgMapper assessCfgMapper;
    @Resource
    private ResDoctorSchProcessMapper processMapper;
    //读取一条登记信息
    @Override
    public DeptTeacherGradeInfo getRecByRecType(String processFlow, String recTypeId){
        if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
            DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
            List<DeptTeacherGradeInfo> recs = gradeInfoMapper.selectByExampleWithBLOBs(example);
            if(recs!=null && !recs.isEmpty()){
                return recs.get(0);
            }
        }
        return null;
    }
    @Override
    public List<DeptTeacherGradeInfo> getRecListByRecType(String processFlow, String recTypeId){
        if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
            DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);

            return gradeInfoMapper.selectByExampleWithBLOBs(example);
        }
        return null;
    }

    @Override
    public String editGradeInfo(String recFlow, String operUserFlow, String resultFlow, String recTypeId, HttpServletRequest request,String formId) {
        DeptTeacherGradeInfo rec = new DeptTeacherGradeInfo();
        ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();

        //大字段内容
        String content = "";
        //评分类型
        String assessType = "";
        if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
            //-------------app提交后无法修改 begin--------------//
            rec.setStatusId(RecStatusEnum.Submit.getId());
            rec.setStatusName(RecStatusEnum.Submit.getName());
            //-------------app提交后无法修改 end--------------//
            //为评分类型赋值
            if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)){
                assessType = ResAssessTypeEnum.TeacherAssess.getId();
            }else if(ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
                assessType = ResAssessTypeEnum.DeptAssess.getId();
            }

            if(StringUtil.isNotBlank(assessType)){
                ResAssessCfg assessCfg = getGradeTemplate(assessType);

                if(assessCfg!=null){
                    String gradeContent = assessCfg.getCfgBigValue();
                    List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
                    if(assessMaps!=null && !assessMaps.isEmpty()){
                        String checkResult=checkGradeScore(assessMaps,request);
                        if(StringUtil.isNotBlank(checkResult))
                        {
                            return "error:"+checkResult;
                        }
                        content = getGradeXml(assessMaps,request);
                    }
                }
            }

        }

        boolean isNew = !StringUtil.isNotBlank(recFlow);

        if(isNew){
            //生成rec流水号
            recFlow = PkUtil.getUUID();
            //读取医师数据
            if(StringUtil.isNotBlank(operUserFlow)){
                ResDoctor doc = readResDoctor(operUserFlow);
                if(doc!=null){
                    rec.setOrgFlow(doc.getOrgFlow());
                    rec.setOrgName(doc.getOrgName());
                    rec.setOperTime(DateUtil.getCurrDateTime());
                    rec.setOperUserFlow(operUserFlow);
                    rec.setOperUserName(doc.getDoctorName());
                }
            }
            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                SchArrangeResult result = searcheDocResult(null,resultFlow);
                if(result!=null){
                    rec.setDeptFlow(result.getDeptFlow());
                    rec.setDeptName(result.getDeptName());
                    rec.setSchDeptFlow(result.getSchDeptFlow());
                    rec.setSchDeptName(result.getSchDeptName());
                }

                    ResDoctorSchProcess process = getProcessByResult(resultFlow);
                    if(process!=null){
                        String processFlow = process.getProcessFlow();
                        rec.setProcessFlow(processFlow);
                        writeBackProcess.setProcessFlow(processFlow);
                    }
            }
            //获取表单类型名称
            if(StringUtil.isNotBlank(recTypeId)){
                rec.setRecTypeId(recTypeId);
                rec.setRecTypeName(ResRecTypeEnum.getNameById(recTypeId));
            }

            rec.setRecVersion(GlobalConstant.RES_DEFAULT_FORM_VER);
            rec.setRecForm(formId);

            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(operUserFlow);

            rec.setRecordStatus(GlobalConstant.FLAG_Y);
        }

        rec.setRecFlow(recFlow);

        rec.setRecContent(content);
        if(StringUtil.isNotBlank(content))
        {
            Document dom = null;
            try {
                dom = DocumentHelper.parseText(content);

                if(dom!=null) {
                    Element root = dom.getRootElement();
                    Element totalScore=root.element("totalScore");
                    if(totalScore!=null)
                    {
                        rec.setAllScore(totalScore.getTextTrim());
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        rec.setModifyTime(DateUtil.getCurrDateTime());
        rec.setModifyUserFlow(operUserFlow);

        if(isNew){
            gradeInfoMapper.insertSelective(rec);
        }else{
            gradeInfoMapper.updateByPrimaryKeySelective(rec);
        }
        return rec.getRecFlow();
    }
    @Override
    public String editKmGradeInfo(String recFlow, String operUserFlow,String resultFlow,String processFlow,  String recTypeId, HttpServletRequest request,String formId) {
        DeptTeacherGradeInfo rec = new DeptTeacherGradeInfo();
        //大字段内容
        String content = "";
        if (StringUtil.isNotBlank(recTypeId)) {
            ResAssessCfg assessCfg = getGradeTemplate(recTypeId);
            if (assessCfg != null) {
                String gradeContent = assessCfg.getCfgBigValue();
                List<Map<String, Object>> assessMaps = parseAssessCfg(gradeContent);
                if (assessMaps != null && !assessMaps.isEmpty()) {
                    content = getKmGradeXml(assessMaps, request);
                }
            }
        }
        boolean isNew = !StringUtil.isNotBlank(recFlow);
        if(isNew){
            //生成rec流水号
            recFlow = PkUtil.getUUID();
            //读取医师数据
            if(StringUtil.isNotBlank(operUserFlow)){
                SysUser doc = userMapper.selectByPrimaryKey(operUserFlow);
                if(doc!=null){
                    rec.setOrgFlow(doc.getOrgFlow());
                    rec.setOrgName(doc.getOrgName());
                    rec.setOperTime(DateUtil.getCurrDateTime());
                    rec.setOperUserFlow(operUserFlow);
                    rec.setOperUserName(doc.getUserName());
                }
            }
            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                SchArrangeResult result = searcheDocResult(null,resultFlow);
                if(result!=null){
                    rec.setDeptFlow(result.getDeptFlow());
                    rec.setDeptName(result.getDeptName());
                    rec.setSchDeptFlow(result.getSchDeptFlow());
                    rec.setSchDeptName(result.getSchDeptName());
                }
            }
            //获取表单类型名称
            if(StringUtil.isNotBlank(recTypeId)){
                rec.setRecTypeId(recTypeId);
                rec.setRecTypeName(ResAssessTypeEnum.getNameById(recTypeId));
            }

            rec.setRecVersion(GlobalConstant.RES_DEFAULT_FORM_VER);
            rec.setRecForm(formId);

            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(operUserFlow);

            rec.setRecordStatus(GlobalConstant.FLAG_Y);
        }

        rec.setRecFlow(recFlow);
        rec.setProcessFlow(processFlow);

        rec.setRecContent(content);

        rec.setModifyTime(DateUtil.getCurrDateTime());
        rec.setModifyUserFlow(operUserFlow);

        if(isNew){
            gradeInfoMapper.insertSelective(rec);
        }else{
            gradeInfoMapper.updateByPrimaryKeySelective(rec);
        }
        return rec.getRecFlow();
    }

    //根据result获取peocess
    @Override
    public ResDoctorSchProcess getProcessByResult(String resultFlow){
        if(StringUtil.isNotBlank(resultFlow)){
            ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
            com.pinde.sci.model.mo.ResDoctorSchProcessExample.Criteria criteria = example.createCriteria()
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andSchResultFlowEqualTo(resultFlow);
            List<ResDoctorSchProcess> processes = processMapper.selectByExample(example);
            if(processes!=null && !processes.isEmpty()){
                return processes.get(0);
            }
        }
        return null;
    }
    //获取医师轮转计划
    @Override
    public <T> T searcheDocResult(String doctorFlow,String resultFlow){
        if(StringUtil.isNotBlank(resultFlow)){
            return (T)resultMapper.selectByPrimaryKey(resultFlow);
        }
        if(StringUtil.isNotBlank(doctorFlow)){
            SchArrangeResultExample example = new SchArrangeResultExample();
            SchArrangeResultExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow);
            return (T)resultMapper.selectByExample(example);
        }
        return null;
    }


    @Override
    public DeptTeacherGradeInfo getGradeRec(String userFlow, String deptFlow,
                              String processFlow, String recTypeId) {
        DeptTeacherGradeInfoExample example=new DeptTeacherGradeInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(userFlow).andProcessFlowEqualTo(processFlow).andSchRotationDeptFlowEqualTo(deptFlow);
        List<DeptTeacherGradeInfo> recList = gradeInfoMapper.selectByExampleWithBLOBs(example);
        if(recList.size()>0){
            return recList.get(0);
        }
        return null;
    }

    @Override
    public DeptTeacherGradeInfo getRec(String recFlow) {
        DeptTeacherGradeInfo deptTeacherGradeInfo = null;
        if(StringUtil.isNotBlank(recFlow)){
            deptTeacherGradeInfo = this.gradeInfoMapper.selectByPrimaryKey(recFlow);
        }
        return deptTeacherGradeInfo;
    }

    @Override
    public List<ResAssessCfg> getAssCfg(String cfgCodeId) {
        ResAssessCfgExample example = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andFormStatusIdEqualTo("Y");
        if (StringUtil.isNotBlank(cfgCodeId)) {
          criteria.andCfgCodeIdEqualTo(cfgCodeId);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return assessCfgMapper.selectByExampleWithBLOBs(example);
    }



    @Override
    public ResDoctorSchProcess getProcessByResultFlow(String resultFlow) {
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
        List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
        if(processList.size()>0){
            return processList.get(0);
        }
        return null;
    }

    @Override
    public String saveGrade(String userFlow, String deptFlow,String subDeptFlow, String assessType,HttpServletRequest request) {
        String recTypeId = "";
        String recTypeName = "";
        if("TeacherAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherGrade.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherGrade.getName();
        }else if("DeptAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.DeptGrade.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.DeptGrade.getName();
        }

        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        ResDoctorSchProcess process= getProcessByResultFlow(result.getResultFlow());
        DeptTeacherGradeInfo rec = getGradeRec(userFlow,deptFlow,process.getProcessFlow(),recTypeId);

        ResAssessCfg assessCfg = getGradeTemplate(assessType);
        String content = "";
        if(assessCfg!=null){
            String gradeContent = assessCfg.getCfgBigValue();
            List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
            if(assessMaps!=null && !assessMaps.isEmpty()){
                String checkResult=checkGradeScore(assessMaps,request);
                if(StringUtil.isNotBlank(checkResult))
                {
                    return "error:"+checkResult;
                }
                content = getGradeXml(assessMaps,request);
            }
        }

        String totalScore1="";
        if(StringUtil.isNotBlank(content))
        {
            Document dom = null;
            try {
                dom = DocumentHelper.parseText(content);

                if(dom!=null) {
                    Element root = dom.getRootElement();
                    Element totalScore=root.element("totalScore");
                    if(totalScore!=null)
                    {
                        totalScore1=totalScore.getTextTrim();
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        if(rec==null){
            rec = new DeptTeacherGradeInfo();
            rec.setRecFlow(PkUtil.getUUID());
            rec.setDeptFlow(result.getDeptFlow());
            rec.setDeptName(result.getDeptName());
            rec.setSchDeptFlow(result.getDeptFlow());
            rec.setSchDeptName(result.getDeptName());
            rec.setRecTypeId(recTypeId);
            rec.setRecTypeName(recTypeName);
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            ResDoctor doc = readResDoctor(userFlow);
            if(doc!=null){
                rec.setOrgFlow(doc.getOrgFlow());
                rec.setOrgName(doc.getOrgName());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setOperUserFlow(userFlow);
                rec.setOperUserName(doc.getDoctorName());
            }
            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(userFlow);
            rec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            rec.setProcessFlow(process.getProcessFlow());
            rec.setSchRotationDeptFlow(deptFlow);
            gradeInfoMapper.insertSelective(rec);
        }else {
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            gradeInfoMapper.updateByPrimaryKeyWithBLOBs(rec);
        }
        return "";

    }

    @Override
    public String saveGrade1(ResAssessCfg assessCfg,String cfgFlow,String userFlow, String deptFlow,String subDeptFlow, String assessType,HttpServletRequest request) {
        String recTypeId = "";
        String recTypeName = "";
        if("TeacherAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherGrade.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherGrade.getName();
        }else if("DeptAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.DeptGrade.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.DeptGrade.getName();
        }

        SchArrangeResult result = resultMapper.selectByPrimaryKey(subDeptFlow);
        ResDoctorSchProcess process= getProcessByResultFlow(result.getResultFlow());
        DeptTeacherGradeInfo rec = getGradeRec(userFlow,deptFlow,process.getProcessFlow(),recTypeId);

//        ResAssessCfg assessCfg = getGradeTemplate(assessType);
        String content = "";
        if(assessCfg!=null){
            String gradeContent = assessCfg.getCfgBigValue();
            List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
            if(assessMaps!=null && !assessMaps.isEmpty()){
                String checkResult=checkGradeScore(assessMaps,request);
                if(StringUtil.isNotBlank(checkResult))
                {
                    return "error:"+checkResult;
                }
                content = getGradeXml(assessMaps,request);
            }
        }

        String totalScore1="";
        if(StringUtil.isNotBlank(content))
        {
            Document dom = null;
            try {
                dom = DocumentHelper.parseText(content);

                if(dom!=null) {
                    Element root = dom.getRootElement();
                    Element totalScore=root.element("totalScore");
                    if(totalScore!=null)
                    {
                        totalScore1=totalScore.getTextTrim();
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        if(rec==null){
            rec = new DeptTeacherGradeInfo();
            rec.setRecFlow(PkUtil.getUUID());
            rec.setDeptFlow(result.getDeptFlow());
            rec.setDeptName(result.getDeptName());
            rec.setSchDeptFlow(result.getDeptFlow());
            rec.setSchDeptName(result.getDeptName());
            rec.setRecTypeId(recTypeId);
            rec.setRecTypeName(recTypeName);
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            rec.setCfgFlow(cfgFlow);
            ResDoctor doc = readResDoctor(userFlow);
            if(doc!=null){
                rec.setOrgFlow(doc.getOrgFlow());
                rec.setOrgName(doc.getOrgName());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setOperUserFlow(userFlow);
                rec.setOperUserName(doc.getDoctorName());
            }
            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(userFlow);
            rec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            rec.setProcessFlow(process.getProcessFlow());
            rec.setSchRotationDeptFlow(deptFlow);
            gradeInfoMapper.insertSelective(rec);
        }else {
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            gradeInfoMapper.updateByPrimaryKeyWithBLOBs(rec);
        }
        return "";

    }

    public String saveGradeTwo(ResAssessCfg assessCfg,String cfgFlow,String userFlow, String deptFlow,String resultFlow, String assessType,HttpServletRequest request) {
        String recTypeId = "";
        String recTypeName = "";
        if("TeacherDoctorAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherDoctorAssess.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherDoctorAssess.getName();
        }else if("TeacherDoctorAssessTwo".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherDoctorAssessTwo.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherDoctorAssessTwo.getName();
        }else if("NurseDoctorAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.NurseDoctorAssess.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.NurseDoctorAssess.getName();
        }else if("TeacherAssess".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherAssess.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherAssess.getName();
        }else if("TeacherAssessTwo".equals(assessType)){
            recTypeId = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherAssessTwo.getId();
            recTypeName = com.pinde.core.commom.enums.ResRecTypeEnum.TeacherAssessTwo.getName();
        }

        SchArrangeResult result = resultMapper.selectByPrimaryKey(resultFlow);
        ResDoctorSchProcess process= getProcessByResultFlow(result.getResultFlow());
        DeptTeacherGradeInfo rec = getGradeRec(userFlow,deptFlow,process.getProcessFlow(),recTypeId);

        String content = "";
        if(assessCfg!=null){
            String gradeContent = assessCfg.getCfgBigValue();
            List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
            if(assessMaps!=null && !assessMaps.isEmpty()){
                String checkResult=checkGradeScore(assessMaps,request);
                if(StringUtil.isNotBlank(checkResult))
                {
                    return "error:"+checkResult;
                }
                content = getGradeXmlTwo(assessMaps,request);
            }
        }

        String totalScore1="";
        if(StringUtil.isNotBlank(content))
        {
            Document dom = null;
            try {
                dom = DocumentHelper.parseText(content);

                if(dom!=null) {
                    Element root = dom.getRootElement();
                    Element totalScore=root.element("totalScore");
                    if(totalScore!=null)
                    {
                        totalScore1=totalScore.getTextTrim();
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        if(rec==null){
            rec = new DeptTeacherGradeInfo();
            rec.setRecFlow(PkUtil.getUUID());
            rec.setDeptFlow(result.getDeptFlow());
            rec.setDeptName(result.getDeptName());
            rec.setSchDeptFlow(result.getDeptFlow());
            rec.setSchDeptName(result.getDeptName());
            rec.setRecTypeId(recTypeId);
            rec.setRecTypeName(recTypeName);
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            rec.setCfgFlow(cfgFlow);
            rec.setStatusId(RecStatusEnum.Submit.getId());
            rec.setStatusName(RecStatusEnum.Submit.getName());
            ResDoctor doc = readResDoctor(userFlow);
            SysUser user = userMapper.selectByPrimaryKey(userFlow);
            rec.setOperUserFlow(userFlow);
            rec.setOperUserName(user.getUserName());
            if(doc!=null){
                rec.setOrgFlow(doc.getOrgFlow());
                rec.setOrgName(doc.getOrgName());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setOperUserFlow(userFlow);
                rec.setOperUserName(doc.getDoctorName());
            }
            rec.setCreateTime(DateUtil.getCurrDateTime());
            rec.setCreateUserFlow(userFlow);
            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(userFlow);
            rec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            rec.setProcessFlow(process.getProcessFlow());
            rec.setSchRotationDeptFlow(deptFlow);
            gradeInfoMapper.insertSelective(rec);
        }else {
            rec.setRecContent(content);
            rec.setAllScore(totalScore1);
            gradeInfoMapper.updateByPrimaryKeyWithBLOBs(rec);
        }
        return "";

    }

    //解析登记信息的xml
    @Override
    public Map<String,String> parseRecContent(String recContent){
        if(StringUtil.isNotBlank(recContent)){
            try {
                Document doc = DocumentHelper.parseText(recContent);
                if(doc!=null){
                    Element root = doc.getRootElement();
                    if(root!=null){
                        List<Element> elements = root.elements();
                        if(elements!=null && !elements.isEmpty()){
                            Map<String,String> formDataMap = new HashMap<String, String>();

                            for(Element element : elements){
                                String eleName = element.getName();

                                List<Node> valueNodes = element.selectNodes("value");

                                if(valueNodes != null && !valueNodes.isEmpty()){
                                    String value = "";
                                    for(Node node : valueNodes){
                                        if(StringUtil.isNotBlank(value)){
                                            value+=",";
                                        }
                                        value += node.getText();
                                    }
                                    formDataMap.put(eleName,value);
                                }else {

                                    String isSelect = element.attributeValue("id");

                                    if(StringUtil.isNotBlank(isSelect)){
                                        formDataMap.put(eleName+"_id",isSelect);
                                        formDataMap.put(eleName,element.getText());
                                    }else{
                                        formDataMap.put(eleName,element.getText());
                                    }
                                }
                            }
                            return formDataMap;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public List<DeptTeacherGradeInfo> searchProssFlowRec(String teacherUserFlow) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("teacherUserFlow",teacherUserFlow);
        String isOpen = getCfgByCode("res_permit_open_doc");
        map.put("isOpen", isOpen);
        return gradeInfoExtMapper.searchHbresProssFlowRecRec(map);
    }
    @Override
    public String getCfgByCode(String code) {

        if(StringUtil.isNotBlank(code)){
            String v= GlobalUtil.getLocalCfgMap().get(code);
            if(StringUtil.isNotBlank(v))
                return v;
            SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
            if(cfg!=null){
                String val = cfg.getCfgValue();
                if(!StringUtil.isNotBlank(val)){
                    val = cfg.getCfgBigValue();
                }
                return val;
            }
        }
        return  null;
    }

    @Override
    public List<DeptTeacherGradeInfo> searchDeptFlowRec(String deptFlow, String isOpen) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("deptFlow",deptFlow);
        map.put("isOpen", isOpen);
        return gradeInfoExtMapper.searchHbresDeptFlowRec(map);
    }

    @Override
    public DeptTeacherGradeInfo readByFlow(String recFlow) {
        return gradeInfoMapper.selectByPrimaryKey(recFlow);
    }

    //读取一个用户的医师信息
    @Override
    public ResDoctor readResDoctor(String userFlow) {
        return doctorMapper.selectByPrimaryKey(userFlow);
    }
    //根据request获取评分表单的xml
    private String getGradeXml(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        if(request!=null){
            String rootName = "gradeInfo";
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                //计算总分
                Float totalScore = 0f;
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                if(StringUtil.isNotBlank(itemId)){
                                    Element grade = root.addElement("grade");
                                    grade.addAttribute("assessId",itemId);

                                    //获取分数并统计
                                    String score = request.getParameter(itemId+"_score");
                                    Element scoreEle = grade.addElement("score");
                                    if(StringUtil.isNotBlank(score)){
                                        scoreEle.setText(score);
                                        Float scf = Float.valueOf(score);
                                        totalScore+=scf;
                                    }

                                    String lostReason = request.getParameter(itemId+"_lostReason");
                                    Element lostReasonEle = grade.addElement("lostReason");
                                    if(StringUtil.isNotBlank(lostReason)){
                                        lostReasonEle.setText(lostReason);
                                    }
                                }
                            }
                        }
                    }
                }

                Element totalScoreEle = root.addElement("totalScore");
                totalScoreEle.setText(totalScore.toString());

                return doc.asXML();
            }
        }
        return null;
    }

    //根据request获取评分表单的xml
    private String getGradeXmlTwo(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        if(request!=null){
            String rootName = "gradeInfo";
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                //计算总分
                Float totalScore = 0f;
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                if(StringUtil.isNotBlank(itemId)){
                                    Element grade = root.addElement("grade");
                                    grade.addAttribute("assessId",itemId);

                                    //获取分数并统计
                                    String score = request.getParameter(itemId+"_score");
                                    Element scoreEle = grade.addElement("score");
                                    if(StringUtil.isNotBlank(score)){
                                        scoreEle.setText(score);
                                        Float scf = Float.valueOf(score);
                                        totalScore+=scf;
                                    }

//                                    String lostReason = request.getParameter(itemId+"_lostReason");
//                                    Element lostReasonEle = grade.addElement("lostReason");
//                                    if(StringUtil.isNotBlank(lostReason)){
//                                        lostReasonEle.setText(lostReason);
//                                    }
                                }
                            }
                        }
                    }
                }

                Element totalScoreEle = root.addElement("totalScore");
                totalScoreEle.setText(totalScore.toString());
                Element lostReasonEle = root.addElement("lostReason");
                if(null != request.getParameter("lostReason")){
                    lostReasonEle.setText(request.getParameter("lostReason"));
                }

                return doc.asXML();
            }
        }
        return null;
    }
    //根据request获取评分表单的评分校验
    private String checkGradeScore(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        String res = "";
        if(request!=null){
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                String name = (String)item.get("name");
                                Float score = Float.valueOf((String) item.get("score"));
                                if(StringUtil.isNotBlank(itemId)){
                                    //获取分数并统计
                                    String evalScore = request.getParameter(itemId+"_score");
                                    if(StringUtil.isBlank(evalScore))
                                    {
                                        return "请为【"+name+"】评分项，进行打分";
                                    }
                                    Float scf = Float.valueOf(evalScore);
                                    if(scf>score)
                                    {
                                        return "【"+name+"】评分项最大分为"+score+"，不得超过此分数";
                                    }
                                }
                            }
                        }
                    }
                }
                return res;
            }
        }
        return res;
    }
    //根据request获取教秘评分表单的xml
    private String getKmGradeXml(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        if(request!=null){
            String rootName = "gradeInfo";
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                //计算总分
                Float totalScore = 0f;
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        String titleId= (String) map.get("id");
                        if(StringUtil.isNotBlank(titleId)){
                            Element grade = root.addElement("grade");
                            grade.addAttribute("assessId",titleId);

                            //获取分数并统计
                            String score = request.getParameter(titleId);
                            Element scoreEle = grade.addElement("score");
                            if(StringUtil.isNotBlank(score)){
                                scoreEle.setText(score);
                                Float scf = Float.valueOf(score);
                                totalScore+=scf;
                            }
                        }
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                if(StringUtil.isNotBlank(itemId)){
                                    Element grade = root.addElement("grade");
                                    grade.addAttribute("assessId",itemId);

                                    //获取分数并统计
                                    String score = request.getParameter(itemId);
                                    Element scoreEle = grade.addElement("score");
                                    if(StringUtil.isNotBlank(score)){
                                        scoreEle.setText(score);
                                        Float scf = Float.valueOf(score);
                                        totalScore+=scf;
                                    }
                                }
                            }
                        }
                    }
                }

                Element totalScoreEle = root.addElement("totalScore");
                totalScoreEle.setText(totalScore.toString());
                String evalName = request.getParameter("evalName");
                Element evalNameEle = root.addElement("evalName");
                if(StringUtil.isNotBlank(evalName)){
                    evalNameEle.setText(evalName);
                }
                return doc.asXML();
            }
        }
        return null;
    }

    //获取评分模板
    @Override
    public ResAssessCfg getGradeTemplate(String cfgCode){
        if(StringUtil.isNotBlank(cfgCode)){
            ResAssessCfgExample example = new ResAssessCfgExample();
            ResAssessCfgExample.Criteria criteria = example.createCriteria()
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andCfgCodeIdEqualTo(cfgCode);

            List<ResAssessCfg> assessList = assessCfgMapper.selectByExampleWithBLOBs(example);

            if(assessList!=null && !assessList.isEmpty()){
                return assessList.get(0);
            }
        }
        return null;
    }

    //解析评分模板
    @Override
    public List<Map<String,Object>> parseAssessCfg(String content){
        if(StringUtil.isNotBlank(content)){
            try {
                Document dom = DocumentHelper.parseText(content);
                if(dom!=null){
                    Element root = dom.getRootElement();
                    if(root!=null){
                        //获取根节点下的所有title节点
                        List<Element> titles = root.elements();
                        if(titles!=null && !titles.isEmpty()){
                            List<Map<String,Object>> dataMaps = new ArrayList<Map<String,Object>>();
                            for(Element i : titles){
                                Map<String,Object> dataMap = new HashMap<String, Object>();
                                //获取title对象的所有属性,属性名为key,属性值为value
                                putAttrToMap(i,dataMap);
                                List<Element> items = i.elements();
                                if(items!=null && !items.isEmpty()){
                                    List<Map<String,Object>> itemMaps = new ArrayList<Map<String,Object>>();
                                    for(Element si : items){
                                        Map<String,Object> itemMap = new HashMap<String, Object>();
                                        //获取节点的所有属性包装进Map
                                        putAttrToMap(si,itemMap);
                                        //以节点名称为key将节点文本包装进map
                                        putTextToMap(si,itemMap);
                                        itemMaps.add(itemMap);
                                    }
                                    dataMap.put("items",itemMaps);
                                }
                                dataMaps.add(dataMap);
                            }
                            return dataMaps;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //获取节点的所有属性包装进Map
    private <T> void putAttrToMap(Element e,Map<String,T> map){
        if(e!=null && map!=null){
            List<?> attrs = e.attributes();
            if(attrs!=null && !attrs.isEmpty()){
                int attrSize = attrs.size();
                for(int index = 0 ; index < attrSize ; index++){
                    Attribute attr = (Attribute)attrs.get(index);
                    if(attr!=null){
                        String name = attr.getName();
                        String val = attr.getValue();
                        map.put(name,(T)val);
                    }
                }
            }
        }
    }
    //以节点名称为key将节点文本包装进map
    private <T> void putTextToMap(Element e,Map<String,T> map){
        if(e!=null && map!=null){
            List<Element> es = e.elements();
            if(es!=null && !es.isEmpty()){
                for(Element se : es){
                    String eleName = se.getName();
                    String eleText = se.getText();
                    map.put(eleName,(T)eleText);
                }
            }
        }
    }

    //解析评分数据
    @Override
    public List<Map<String,String>> parseDocGradeXml(String content){
        if(StringUtil.isNotBlank(content)){
            List<Map<String,String>> gradeMaps = parseGradeXml(content);
            if(gradeMaps!=null && !gradeMaps.isEmpty()){
                List<Map<String,String>> gms = new ArrayList<Map<String,String>>();
                //重新包装解析的评分数据
                for(Map<String,String> grameMap : gradeMaps){
                    String id = grameMap.get("assessId");
                    String score = grameMap.get("score");
                    String lostReason = grameMap.get("lostReason");

                    Map<String,String> gm1 = new HashMap<String, String>();
                    gm1.put("inputId",id+"_score");
                    gm1.put("value",score);

                    Map<String,String> gm2 = new HashMap<String, String>();
                    gm2.put("inputId",id+"_lostReason");
                    gm2.put("value",lostReason);

                    gms.add(gm1);
                    gms.add(gm2);
                }
                return gms;
            }
        }
        return null;
    }

    //讲评分数据解析成基础的对象集合格式
    private List<Map<String,String>> parseGradeXml(String content){
        if(StringUtil.isNotBlank(content)){
            try {
                Document dom = DocumentHelper.parseText(content);
                if(dom!=null){
                    Element root = dom.getRootElement();
                    if(root!=null){
                        List<Element> grades = root.elements();
                        if(grades!=null && !grades.isEmpty()){
                            List<Map<String,String>> gradeMaps = new ArrayList<Map<String,String>>();
                            for(Element grade : grades){
                                Map<String,String> gradeMap = new HashMap<String, String>();
                                putAttrToMap(grade,gradeMap);
                                putTextToMap(grade,gradeMap);
                                gradeMaps.add(gradeMap);
                            }
                            return gradeMaps;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Map<String,Object> parseGradeInfoXml(String recContent){
        Map<String,Object> gradeMap = null;
        if(StringUtil.isNotBlank(recContent)){
            try {
                Document doc = DocumentHelper.parseText(recContent);
                Element root = doc.getRootElement();
                if(root!=null){
                    List<Element> items = root.elements("grade");
                    if(items!=null && items.size()>0){
                        gradeMap = new HashMap<String, Object>();
                        for(Element e : items){
                            String assessId = e.attributeValue("assessId");
                            Map<String,String> dataMap = new HashMap<String, String>();
                            gradeMap.put(assessId,dataMap);

                            Element score = e.element("score");
                            if(score!=null){
                                String scoreStr = score.getText();
                                dataMap.put("score",scoreStr);
                            }
                            Element lostReason = e.element("lostReason");
                            if(lostReason!=null){
                                dataMap.put("lostReason",lostReason.getText());
                            }
                        }

                        Element totalScore = root.element("totalScore");
                        if(totalScore!=null){
                            gradeMap.put("totalScore",totalScore.getText());
                        }
                        Element evalName = root.element("evalName");
                        if(evalName!=null){
                            gradeMap.put("evalName",evalName.getText());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gradeMap;
    }
    @Override
    public List<DeptTeacherGradeInfo> searchResGradeByItems(Map<String, Object> itemsMap) {
        DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
        DeptTeacherGradeInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        if(null!= itemsMap.get("processFlow")){
            criteria.andProcessFlowEqualTo((String) itemsMap.get("processFlow"));
        }
        if(null!= itemsMap.get("processFlows") && ((ArrayList<String>) itemsMap.get("processFlows")).size()>0){
            criteria.andProcessFlowIn((ArrayList<String>)itemsMap.get("processFlows"));
        }
        if(itemsMap.get("currentUserFlow") != null){
            criteria.andOperUserFlowEqualTo(itemsMap.get("currentUserFlow").toString());
        }
        if(null!=itemsMap.get("recTypeIds") && ((ArrayList<String>) itemsMap.get("recTypeIds")).size()>0){
            criteria.andRecTypeIdIn((ArrayList<String>)itemsMap.get("recTypeIds"));
        }
        if(null!= itemsMap.get("recFormId")){
            criteria.andRecTypeIdLike((String) itemsMap.get("recFormId")+"%");
        }
        if(null!= itemsMap.get("createUserFlow")){
            criteria.andCreateUserFlowEqualTo((String) itemsMap.get("createUserFlow"));
        }
        List<DeptTeacherGradeInfo> gradeInfos = gradeInfoMapper.selectByExampleWithBLOBs(example);
        example.setOrderByClause("OPER_TIME");
        return gradeInfos;
    }
}
