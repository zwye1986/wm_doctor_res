package com.pinde.res.biz.stdp.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.app.common.InitConfig;
import com.pinde.core.commom.enums.RecStatusEnum;
import com.pinde.core.commom.enums.ResRecTypeEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResSchProcessExpressBizImpl implements IResSchProcessExpressBiz {

    @Autowired
    private ResSchProcessExpressMapper processExpressMapper;
    @Resource
    private ResDoctorMapper doctorMapper;
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SchArrangeResultMapper resultMapper;
    @Resource
    private ResDoctorSchProcessMapper processMapper;
    @Resource
    private SysCfgMapper cfgMapper;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;
    @Autowired
    private SchRotationDeptAfterMapper afterMapper;

    //读取一条登记信息
    @Override
    public ResSchProcessExpress getExpressByRecType(String processFlow, String recTypeId){
        if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
            List<ResSchProcessExpress> recs = processExpressMapper.selectByExampleWithBLOBs(example);
            if(recs!=null && !recs.isEmpty()){
                return recs.get(0);
            }
        }
        return null;
    }
    //读取一条登记信息
    @Override
    public ResSchProcessExpress getExpressByRecTypeNoStatus(String processFlow, String recTypeId){
        if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
            ResSchProcessExpressExample example = new ResSchProcessExpressExample();
            example.createCriteria().andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
            List<ResSchProcessExpress> recs = processExpressMapper.selectByExampleWithBLOBs(example);
            if(recs!=null && !recs.isEmpty()){
                return recs.get(0);
            }
        }
        return null;
    }

    @Override
    public ResSchProcessExpress getExpressByRecFlow(String dataFlow) {
        return processExpressMapper.selectByPrimaryKey(dataFlow);
    }

    //审核一条登记数据//默认审核通过
    @Override
    public void auditDate(String userFlow,String dataFlow){
        if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(dataFlow)){
            ResSchProcessExpress rec = new ResSchProcessExpress();
            rec.setRecFlow(dataFlow);

            rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
            rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
            rec.setAuditUserFlow(userFlow);
            SysUser user = userMapper.selectByPrimaryKey(userFlow);
            if(user!=null){
                rec.setAuditUserName(user.getUserName());
            }
            rec.setAuditTime(DateUtil.getCurrDateTime());

            processExpressMapper.updateByPrimaryKeySelective(rec);
        }
    }

    @Override
    public List<ResSchProcessExpress> getDocexpressList(String processFlow, List<String> recTypeIds) {
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
       ResSchProcessExpressExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(processFlow)){
            criteria.andProcessFlowEqualTo(processFlow);
        }
        if(recTypeIds!=null&&recTypeIds.size()>0)
        {
            criteria.andRecTypeIdIn(recTypeIds);
        }
        return processExpressMapper.selectByExampleWithBLOBs(example);
    }

    //根据request获取这次表单的xml
    private String getXmlByRequest(HttpServletRequest request,Element rootEle,ResDoctorSchProcess writeBackProcess){
        if(request!=null&&rootEle!=null){
            Map<String,String[]> paramsMap = request.getParameterMap();
            if(paramsMap!=null && !paramsMap.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = rootEle;

                for(String key : paramsMap.keySet()){
                    String[] vs = paramsMap.get(key);

                    String vss = "";

                    String idCsVv = request.getParameter(key+"_name");

                    if(vs!=null && vs.length>0){
                        vss = vs[0];
                    }
//                    try {
//                        if(idCsVv!=null)
//                            idCsVv = URLDecoder.decode(idCsVv, "UTF-8") ;
//                        if(vss!=null) vss = URLDecoder.decode(vss,"UTF-8") ;
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    if(idCsVv==null){
                        currEle.setText(vss);
                    }else{
                        currEle.addAttribute("id",vss);
                        currEle.setText(idCsVv);
                    }
                    if("score".equals(key)){
                        String v = request.getParameter(key);
                        try {
                            Float sum=Float.valueOf(v);//同时回写进process
                            writeBackProcess.setSchScore(BigDecimal.valueOf(sum));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //是否存在总分
                    String totalScore = request.getParameter("theoreResult");
                    if(StringUtil.isBlank(totalScore))
                        totalScore = request.getParameter("totalScore");
                    if(StringUtil.isNotBlank(totalScore)) {
                        writeBackProcess.setSchScore(BigDecimal.valueOf(Double.valueOf(totalScore)));
                    }
                }
                return doc.asXML();
            }
        }
        return null;
    }
    //根据request获取这次表单的xml
    private String getXmlByRequest(HttpServletRequest request,String rootName,ResDoctorSchProcess writeBackProcess){
        if(request!=null){
            rootName = StringUtil.defaultIfEmpty(rootName,"root");
            Map<String,String[]> paramsMap = request.getParameterMap();
            if(paramsMap!=null && !paramsMap.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                for(String key : paramsMap.keySet()){
                    String[] vs = paramsMap.get(key);

                    String vss = "";

                    String idCsVv = request.getParameter(key+"_name");

                    if(vs!=null && vs.length>0){
                        vss = vs[0];
                    }
                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    if(idCsVv==null){
                        //东莞东华dops和mini_cex表单增加"是否评估"列
                        if("dgdhyy".equals(InitConfig.getSysCfg("res_form_category"))&&(ResRecTypeEnum.DOPS.getId().equals(request.getAttribute("recTypeId"))||ResRecTypeEnum.Mini_CEX.getId().equals(request.getAttribute("recTypeId")))){
                            if(key.equals("xgzs")||key.equals("czqdzqty")||key.equals("sqzb")||key.equals("jnsld")||key.equals("wjgn")||key.equals("czhcl")||key.equals("gtjq")||key.equals("zysyjrw")||key.equals("ssxqxz")
                                    ||key.equals("ztbx")||key.equals("bscjProj")||key.equals("tgjc")||key.equals("yhgt")||key.equals("lcpd")||key.equals("zysy")||key.equals("zzxn")||key.equals("ztbx")){
                                if("否".equals(request.getParameter(key))){
                                    currEle.setText("");
                                }else{
                                    currEle.setText(vss);
                                }
                                Element currEle2 = root.addElement(key+"Flag");
                                currEle2.setText(StringUtil.isBlank(request.getParameter(key))||"否".equals(request.getParameter(key))?"否":"是");
                            }else{
                                currEle.setText(vss);
                            }
                        }else{
                            currEle.setText(vss);
                        }
                    }else{
                        currEle.addAttribute("id",vss);
                        currEle.setText(idCsVv);
                    }
                    if("score".equals(key)){
                        String v = request.getParameter(key);
                        try {
                            Float sum=Float.valueOf(v);//同时回写进process
                            writeBackProcess.setSchScore(BigDecimal.valueOf(sum));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                return doc.asXML();
            }
        }
        return null;
    }

    //读取一个用户的医师信息
    public ResDoctor readResDoctor(String userFlow) {
        return doctorMapper.selectByPrimaryKey(userFlow);
    }
    //根据result获取peocess
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
    //编辑一条登记数据
    @Override
    public String editExpress(String recFlow,
                              String operUserFlow,
                              String resultFlow,
                              String recTypeId,
                              String formId,
                              HttpServletRequest request, String roleFlag, String oldContent, String version){

        ResSchProcessExpress rec = new ResSchProcessExpress();
        ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();
        String content = null;
            boolean isNew = !StringUtil.isNotBlank(recFlow);

            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                ResDoctorSchProcess process = getProcessByResult(resultFlow);
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    rec.setProcessFlow(processFlow);
                    writeBackProcess.setProcessFlow(processFlow);
                }
                SchArrangeResult result = searcheDocResult(null,resultFlow);
                if(result!=null){
                    rec.setDeptFlow(result.getDeptFlow());
                    rec.setDeptName(result.getDeptName());
                    rec.setSchDeptFlow(result.getSchDeptFlow());
                    rec.setSchDeptName(result.getSchDeptName());
                }
            }
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
                SysUser user=readSysUser(operUserFlow);
                String medicineTypeId="";
                if(user!=null) medicineTypeId=user.getMedicineTypeId();
                rec.setMedicineType(medicineTypeId);
                //获取表单类型名称
                if(StringUtil.isNotBlank(recTypeId)){
                    rec.setRecTypeId(recTypeId);
                    rec.setRecTypeName(ResRecTypeEnum.getNameById(recTypeId));
                }
                if(StringUtil.isNotBlank(version))
                {
                    rec.setRecVersion(version);
                }else {
                    rec.setRecVersion(GlobalConstant.RES_DEFAULT_FORM_VER);
                }
                rec.setRecForm(formId);

                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setCreateUserFlow(operUserFlow);

                rec.setRecordStatus(GlobalConstant.FLAG_Y);
            }

            rec.setRecFlow(recFlow);
            if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId))
            {
                try {
                    Document doc = null;
                    Element root = null;
                    Element roleNode = null;
                    if(StringUtil.isNotBlank(oldContent)){
                        doc = DocumentHelper.parseText(oldContent);
                        root = doc.getRootElement();
                        roleNode = root.element(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
                        if(roleNode != null){
                            roleNode.detach();
                        }
                    }else{
                        doc = DocumentHelper.createDocument();
                        root = doc.addElement(recTypeId);
                    }
                    roleNode = DocumentHelper.createElement(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
                    getXmlByRequest(request,roleNode,writeBackProcess);
                    root.add(roleNode);
                    content = root.asXML();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }else{
                content = getXmlByRequest(request,recTypeId,writeBackProcess);
            }
            rec.setRecContent(content);

            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(operUserFlow);
            String isAgree=request.getParameter("isAgree");
            boolean toUpdate=false;
            if("Y".equals(isAgree))
            {
                writeBackProcess.setSchFlag(GlobalConstant.FLAG_Y);
                writeBackProcess.setIsCurrentFlag(GlobalConstant.FLAG_N);
                toUpdate = true;
            }
            //回写分数
            if(writeBackProcess.getSchScore()!=null){
                toUpdate = true;
                if(!StringUtil.isNotBlank(writeBackProcess.getProcessFlow())){
                    ResDoctorSchProcess process = getProcessByResult(resultFlow);
                    if(process!=null){
                        String processFlow = process.getProcessFlow();
                        writeBackProcess.setProcessFlow(processFlow);
                    }
                }
            }
            if(toUpdate)
                processMapper.updateByPrimaryKeySelective(writeBackProcess);
            if(isNew){
                processExpressMapper.insertSelective(rec);
            }else{
                processExpressMapper.updateByPrimaryKeySelective(rec);
            }
            return rec.getRecFlow();

    }
    @Override
    public String editZseyExpress(String recFlow,
                          String operUserFlow,
                          String resultFlow,
                          String recTypeId,
                          String formId,
                          HttpServletRequest request,String roleFlag,String oldContent,String version){

        ResSchProcessExpress rec = new ResSchProcessExpress();
        ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();
        String content = null;
            boolean isNew = !StringUtil.isNotBlank(recFlow);

            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                ResDoctorSchProcess process = getProcessByResult(resultFlow);
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    rec.setProcessFlow(processFlow);
                    writeBackProcess.setProcessFlow(processFlow);
                }
                SchArrangeResult result = searcheDocResult(null,resultFlow);
                if(result!=null){
                    rec.setDeptFlow(result.getDeptFlow());
                    rec.setDeptName(result.getDeptName());
                    rec.setSchDeptFlow(result.getSchDeptFlow());
                    rec.setSchDeptName(result.getSchDeptName());
                }
            }
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
                SysUser user=readSysUser(operUserFlow);
                String medicineTypeId="";
                if(user!=null) medicineTypeId=user.getMedicineTypeId();
                rec.setMedicineType(medicineTypeId);
                //获取表单类型名称
                if(StringUtil.isNotBlank(recTypeId)){
                    rec.setRecTypeId(recTypeId);
                    rec.setRecTypeName(ResRecTypeEnum.getNameById(recTypeId));
                }

                rec.setRecVersion(version);
                rec.setRecForm(formId);

                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setCreateUserFlow(operUserFlow);

                rec.setRecordStatus(GlobalConstant.FLAG_Y);
            }

            rec.setRecFlow(recFlow);
            if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId))
            {
                try {
                    Document doc = null;
                    Element root = null;
                    Element roleNode = null;
                    if(StringUtil.isNotBlank(oldContent)){
                        doc = DocumentHelper.parseText(oldContent);
                        root = doc.getRootElement();
                        roleNode = root.element(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
                        if(roleNode != null){
                            roleNode.detach();
                        }
                    }else{
                        doc = DocumentHelper.createDocument();
                        root = doc.addElement(recTypeId);
                    }
                    roleNode = DocumentHelper.createElement(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
                    getXmlByRequest(request,roleNode,writeBackProcess);
                    root.add(roleNode);
                    content = root.asXML();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }else{
                content = getXmlByRequest(request,recTypeId,writeBackProcess);
            }
            rec.setRecContent(content);

            rec.setModifyTime(DateUtil.getCurrDateTime());
            rec.setModifyUserFlow(operUserFlow);
            String isAgree=request.getParameter("isAgree");
            boolean toUpdate=false;
            if("Y".equals(isAgree))
            {
                writeBackProcess.setSchFlag(GlobalConstant.FLAG_Y);
                writeBackProcess.setIsCurrentFlag(GlobalConstant.FLAG_N);
                toUpdate = true;
            }
            //回写分数
            if(writeBackProcess.getSchScore()!=null){
                toUpdate = true;
                if(!StringUtil.isNotBlank(writeBackProcess.getProcessFlow())){
                    ResDoctorSchProcess process = getProcessByResult(resultFlow);
                    if(process!=null){
                        String processFlow = process.getProcessFlow();
                        writeBackProcess.setProcessFlow(processFlow);
                    }
                }
            }
            if(toUpdate)
                processMapper.updateByPrimaryKeySelective(writeBackProcess);
            if(isNew){
                processExpressMapper.insertSelective(rec);
            }else{
                processExpressMapper.updateByPrimaryKeySelective(rec);
            }
            return rec.getRecFlow();

    }

    //编辑一条登记数据
    @Override
    public String editJszyExpress(String recFlow,
                          String operUserFlow,
                          String resultFlow,
                          String recTypeId,
                          String formId,
                          HttpServletRequest request){

        ResSchProcessExpress rec = new ResSchProcessExpress();
        ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();
        String content = getJszyXmlByRequest(request,recTypeId,",",writeBackProcess);

        ResDoctorSchProcess process = getProcessByResult(resultFlow);
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
            SysUser user=readSysUser(operUserFlow);
            String medicineTypeId="";
            if(user!=null) medicineTypeId=user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);
            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    rec.setProcessFlow(processFlow);
                    writeBackProcess.setProcessFlow(processFlow);
                }
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

        rec.setModifyTime(DateUtil.getCurrDateTime());
        rec.setModifyUserFlow(operUserFlow);
        if(StringUtil.isNotBlank(resultFlow)){
            if(process!=null) {
                String processFlow = process.getProcessFlow();
                writeBackProcess.setProcessFlow(processFlow);
                boolean testTypeFlag = false;

                String switchFlag = getCfgByCode("res_after_test_switch");
                String urlCfg = getCfgByCode("res_mobile_after_url_cfg");
                //学员开通出科考试权限
                Map<String, String> paramMap = new HashMap();
                String cfgCode = "res_doctor_ckks_" + operUserFlow;
                paramMap.put("cfgCode", cfgCode);
                String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
                if (GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
                        && StringUtil.isNotBlank(urlCfg)) {
                    testTypeFlag = true;
                }
                if (ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)&&!testTypeFlag) {
                    //计算总分方法
                    Float totalScore = sumAfterScore(request);
                    if(totalScore!=null){
                        if(GlobalConstant.RES_GZZYYY_DEFAULT_FORM.equals(formId)){
                            writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore/20*100));
                        }else{
                            writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore/25*100));
                        }
                    }
                }
            }
        }
        //回写分数
        if(writeBackProcess.getSchScore()!=null){
            if(!StringUtil.isNotBlank(writeBackProcess.getProcessFlow())){
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    writeBackProcess.setProcessFlow(processFlow);
                }
            }
            processMapper.updateByPrimaryKeySelective(writeBackProcess);
        }
        if(isNew){
            processExpressMapper.insertSelective(rec);
        }else{
            processExpressMapper.updateByPrimaryKeySelective(rec);
        }
        return rec.getRecFlow();
    }

    //根据request获取这次表单的xml
    private String getSxsXmlByRequest(HttpServletRequest request,String rootName,String separator,ResDoctorSchProcess writeBackProcess,String roleId){
        Map<String,String> idValCfg=new HashMap<>();
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status1","亲自");
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status2","一助");
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status3","二助");
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status4","参观");
        idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType1","讲课");
        idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType2","示教");
        idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType3","讨论");
        idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType4","教学查房");
        idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation1","优");
        idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation2","良");
        idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation3","中");
        idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation4","合格");
        idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation5","不合格");

        Map<String,List<String>>  sumItemMap = new HashMap<String, List<String>>();
        List<String> nodeNames = new ArrayList<String>();
        nodeNames.add("theoryTest");
        nodeNames.add("skillTest");
        sumItemMap.put(ResRecTypeEnum.AfterSummary.getId(),nodeNames);
        if(request!=null){
            separator = separator==null?",":separator;
            rootName = StringUtil.defaultIfEmpty(rootName,"root");
            Map<String,String[]> paramsMap = request.getParameterMap();
            if(paramsMap!=null && !paramsMap.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                for(String key : paramsMap.keySet()){
                    String[] vs = paramsMap.get(key);

                    String vss = "";

                    String idCsVv = null;

                    List<String> vsLs = null;
                    if(vs!=null){
                        if(vs.length>1){
                            vss = StringUtils.join(vs,separator);

                            vsLs = Arrays.asList(vs);
                        }else{
                            vss = vs[0];

                            if(StringUtil.isNotBlank(vss)){
                                if(vss.startsWith("[") && vss.endsWith("]")){
                                    vsLs = (List<String>)JSON.parse(vss);
                                    Object[] vsOs = vsLs.toArray();
                                    vss = StringUtils.join(vsOs,separator);
                                }
                            }
                        }

                        if(vsLs!=null && !vsLs.isEmpty()){
                            for(String v : vsLs){
                                String iv = idValCfg.get(rootName+key+v);
                                if(iv!=null){
                                    if(idCsVv == null){
                                        idCsVv = iv;
                                    }else{
                                        idCsVv+=(separator+iv);
                                    }
                                }
                            }
                        }

                        if(idCsVv==null){
                            idCsVv = idValCfg.get(rootName+key+vss);
                        }

                    }

                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    if(idCsVv==null){
                        currEle.setText(vss);
                    }else{
                        currEle.addAttribute("id",vss);
                        currEle.setText(idCsVv);
                    }
                }

                //计算总分方法
                if(StringUtil.isNotBlank(roleId)&&!roleId.equals("Student")) {
                    Float totalScore = countSxsSum(request, rootName,sumItemMap);
                    if (totalScore != null) {
                        //将总分放进根节点
                        Element totalScoreEle = root.addElement("totalScore");
                        totalScoreEle.setText(totalScore.toString());
                        //同时回写进process
                        writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore));
                    }
                }
                return doc.asXML();
            }
        }
        return null;
    }

    private Float countSxsSum(HttpServletRequest request, String rootName, Map<String, List<String>> sumItemMap) {
        if(request==null){
            return null;
        }
        if(!StringUtil.isNotBlank(rootName)){
            return null;
        }
        List<String> nodeNames = sumItemMap.get(rootName);
        if(nodeNames==null || nodeNames.isEmpty()){
            return null;
        }

        Float sum = 0f;
        for(String nodeName : nodeNames){
            String v = request.getParameter(nodeName);
            try {
                sum+=Float.valueOf(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    //编辑一条登记数据
    @Override
    public String editSxsExpress(String recFlow,
                          String operUserFlow,
                          String resultFlow,
                          String recTypeId,
                          String formId,
                          HttpServletRequest request,String roleId){

        ResSchProcessExpress rec = new ResSchProcessExpress();
        ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();
        String content = getSxsXmlByRequest(request,recTypeId,",",writeBackProcess, roleId);

        ResDoctorSchProcess process = getProcessByResult(resultFlow);
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
            SysUser user=readSysUser(operUserFlow);
            String medicineTypeId="";
            if(user!=null) medicineTypeId=user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);
            //读取科室数据
            if(StringUtil.isNotBlank(resultFlow)){
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    rec.setProcessFlow(processFlow);
                    writeBackProcess.setProcessFlow(processFlow);
                }
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

        rec.setModifyTime(DateUtil.getCurrDateTime());
        rec.setModifyUserFlow(operUserFlow);

        //回写分数
        if(writeBackProcess.getSchScore()!=null){
            if(!StringUtil.isNotBlank(writeBackProcess.getProcessFlow())){
                if(process!=null){
                    String processFlow = process.getProcessFlow();
                    writeBackProcess.setProcessFlow(processFlow);
                }
            }
            processMapper.updateByPrimaryKeySelective(writeBackProcess);
        }
        if(isNew){
            processExpressMapper.insertSelective(rec);
        }else{
            processExpressMapper.updateByPrimaryKeySelective(rec);
        }
        return rec.getRecFlow();
    }

    public String getCfgByCode(String code){
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
        return null;
    }

    /*************************************功能配置START*******************************************************/
    //配置id对应的value
    private static Map<String,String> idValCfg;
    //需要计算和的表单
    private static Map<String,List<String>> sumItemMap;

    static{
        //GlobalUtil.setCfg();
        idValCfg = GlobalUtil.getIdValCfg();
        sumItemMap = GlobalUtil.getSumItemMap();
    }

    /*************************************功能配置END*******************************************************/
    private String getJszyXmlByRequest(HttpServletRequest request, String rootName,String separator, ResDoctorSchProcess writeBackProcess) {
        if(request!=null){
            separator = separator==null?",":separator;
            rootName = StringUtil.defaultIfEmpty(rootName,"root");
            Map<String,String[]> paramsMap = request.getParameterMap();
            if(paramsMap!=null && !paramsMap.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                for(String key : paramsMap.keySet()){
                    String[] vs = paramsMap.get(key);

                    String vss = "";

                    String idCsVv = null;

                    List<String> vsLs = null;
                    if(vs!=null){
                        if(vs.length>1){
                            vss = StringUtils.join(vs,separator);

                            vsLs = Arrays.asList(vs);
                        }else{
                            vss = vs[0];

                            if(StringUtil.isNotBlank(vss)){
                                if(vss.startsWith("[") && vss.endsWith("]")){
                                    vsLs = (List<String>) JSON.parse(vss);
                                    Object[] vsOs = vsLs.toArray();
                                    vss = StringUtils.join(vsOs,separator);
                                }
                            }
                        }

                        if(vsLs!=null && !vsLs.isEmpty()){
                            for(String v : vsLs){
                                String iv = idValCfg.get(rootName+key+v);
                                if(iv!=null){
                                    if(idCsVv == null){
                                        idCsVv = iv;
                                    }else{
                                        idCsVv+=(separator+iv);
                                    }
                                }
                            }
                        }

                        if(idCsVv==null){
                            idCsVv = idValCfg.get(rootName+key+vss);
                        }

                    }

                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    if(idCsVv==null){
                        currEle.setText(vss);
                    }else{
                        currEle.addAttribute("id",vss);
                        currEle.setText(idCsVv);
                    }
                }
                //计算总分方法
                Float totalScore = countSum(request,rootName);
                if(totalScore!=null){
                    //将总分放进根节点
                    Element totalScoreEle = root.addElement("totalScore");
                    totalScoreEle.setText(totalScore.toString());
                    //同时回写进process
                    writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore));
                }

                return doc.asXML();
            }
        }
        return null;
    }

    private Float sumAfterScore(HttpServletRequest request) {
        if(request==null){
            return null;
        }
        List<String> nodeNames = new ArrayList<>();
//        nodeNames.add("responsibility");
//        nodeNames.add("attitude");
//        nodeNames.add("doctor");
//        nodeNames.add("unite");
//        nodeNames.add("subject");
//        nodeNames.add("disease");
//        nodeNames.add("diseases");
//        nodeNames.add("quality");
//        nodeNames.add("skill");
        nodeNames.add("theoryAssessment");
//        nodeNames.add("readingNotes");
//        nodeNames.add("activity");
//        nodeNames.add("attendance");
        Float sum = 0f;
        for(String nodeName : nodeNames){
            String v = request.getParameter(nodeName);
            try {
                sum+=Float.valueOf(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    //计算总分
    private Float countSum(HttpServletRequest request,String rootName){
        if(request==null){
            return null;
        }
        if(!StringUtil.isNotBlank(rootName)){
            return null;
        }
        List<String> nodeNames = sumItemMap.get(rootName);
        if(nodeNames==null || nodeNames.isEmpty()){
            return null;
        }

        Float sum = 0f;
        for(String nodeName : nodeNames){
            String v = request.getParameter(nodeName);
            try {
                sum+=Float.valueOf(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }


    @Override
    public ResSchProcessExpress searResRecZhuZhi(String formFileName, String recFlow,
                                   String operUserFlow, String roleFlag, String processFlow,String recordFlow,String userFlow,String cksh,
                                   HttpServletRequest req) {
        operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,userFlow);
        SysUser sysUser=readSysUser(operUserFlow);
        ResDoctorSchProcess resDoctorSchProcess=processMapper.selectByPrimaryKey(processFlow);
        String recTypeId=ResRecTypeEnum.getNameById(formFileName);
        String currDate=DateUtil.getCurrDateTime();
        ResSchProcessExpress rec=new ResSchProcessExpress();
        rec.setRecFlow(recFlow);
        rec.setOrgFlow(sysUser.getOrgFlow());
        rec.setOrgName(sysUser.getOrgName());
        rec.setDeptFlow(resDoctorSchProcess.getDeptFlow());
        rec.setDeptName(resDoctorSchProcess.getDeptName());
        rec.setRecTypeId(formFileName);
        rec.setRecTypeName(recTypeId);
        rec.setRecVersion("2.0");
        rec.setOperTime(currDate);
        rec.setOperUserFlow(operUserFlow);
        rec.setOperUserName(sysUser.getUserName());
        rec.setProcessFlow(processFlow);
        rec.setSchRotationDeptFlow(recordFlow);
        rec.setRecForm("jsst");
        if(StringUtil.isNotBlank(req.getParameter("auditStatusId")))
            rec.setAuditStatusId(req.getParameter("auditStatusId"));
        if(StringUtil.isNotBlank(req.getParameter("headAuditStatusId")))
            rec.setHeadAuditStatusId(req.getParameter("headAuditStatusId"));
        String recContent=getXmlByRequest(req,formFileName);
        rec.setRecContent(recContent);
        if(StringUtil.isBlank(recFlow)) {
            if (GlobalConstant.FLAG_Y.equals(cksh)) {
                rec.setManagerAuditUserFlow("Y");
            }
        }
        //System.out.println(recContent);
        rec.setRecContent(recContent);
        return rec;
    }

    @Override
    public int editResRec(ResSchProcessExpress rec,SysUser user) {
        if(rec!=null){
            if(StringUtil.isNotBlank(rec.getRecFlow())){//修改
                rec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return processExpressMapper.updateByPrimaryKeySelective(rec);
            }else{//新增
                rec.setRecFlow(PkUtil.getUUID());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                rec.setCreateUserFlow(user.getUserFlow());
                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return processExpressMapper.insertSelective(rec);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }
    //根据request获取这次表单的xml iaal
    private String getXmlByRequest(HttpServletRequest request,String rootName){
        if(request!=null){
            rootName = StringUtil.defaultIfEmpty(rootName,"root");
            Map<String,String[]> paramsMap = request.getParameterMap();
            if(paramsMap!=null && !paramsMap.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                for(String key : paramsMap.keySet()){
                    String[] vs = paramsMap.get(key);

                    String vss = "";

                    String idCsVv = request.getParameter(key+"_name");

                    if(vs!=null && vs.length>0){
                        vss = vs[0];
                    }
                    try {
                        if(idCsVv!=null)
                            idCsVv = URLDecoder.decode(idCsVv, "UTF-8") ;
                        if(vss!=null) vss = URLDecoder.decode(vss,"UTF-8") ;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //开始创建xml子节点
                    Element currEle = root.addElement(key);
                    if(idCsVv==null){
                        currEle.setText(vss);
                    }else{
                        currEle.addAttribute("id",vss);
                        currEle.setText(idCsVv);
                    }
                }
                return doc.asXML();
            }
        }
        return null;
    }

    @Override
    public SysUser readSysUser(String userFlow) {
        return userMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public ResSchProcessExpress queryResRec(String processFlow,
                                            String recTypeId) {
        ResSchProcessExpress rec=null;
        ResSchProcessExpressExample example = new ResSchProcessExpressExample();
        com.pinde.sci.model.mo.ResSchProcessExpressExample.Criteria create=example.createCriteria();
        create.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(processFlow)) {
            create.andProcessFlowEqualTo(processFlow);
        }
        if (StringUtil.isNotBlank(recTypeId)) {
            create.andRecTypeIdEqualTo(recTypeId);
        }
        List<ResSchProcessExpress> list = processExpressMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() >0) {
            rec = list.get(0);
        }
        return rec;
    }
    @Override
    public List<ResSchProcessExpress> searchByUserFlowAndTypeId(String operUserFlow,
                                                                String recTypeId) {
        ResSchProcessExpressExample example=new ResSchProcessExpressExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(operUserFlow);
        return processExpressMapper.selectByExampleWithBLOBs(example);
    }
    @Override
    public List<SchRotationDeptAfterWithBLOBs> getAfterByDoctorFlow(String doctorFlow, String applyYear) {
        if(StringUtil.isNotBlank(doctorFlow))
        {
            SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow).andApplyYearEqualTo(applyYear);
            return afterMapper.selectByExampleWithBLOBs(example);
        }
        return null;
    }
    @Override
    public int editAfter(SchRotationDeptAfterWithBLOBs after) {
        if(StringUtil.isNotBlank(after.getRecordFlow()))
        {
            return afterMapper.updateByPrimaryKeyWithBLOBs(after);
        }else{
            after.setRecordFlow(PkUtil.getUUID());
            return afterMapper.insertSelective(after);
        }
    }
}
