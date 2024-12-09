package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.sci.dao.base.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResSchProcessExpressBizImpl implements IResSchProcessExpressBiz {

    private static Logger logger = LoggerFactory.getLogger(ResSchProcessExpressBizImpl.class);

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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
        ResSchProcessExpressExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(processFlow)){
            criteria.andProcessFlowEqualTo(processFlow);
        }
        if(recTypeIds!=null&&recTypeIds.size()>0)
        {
            criteria.andRecTypeIdIn(recTypeIds);
        }
        return processExpressMapper.selectByExampleWithBLOBs(example);
    }

    //读取一个用户的医师信息
    public ResDoctor readResDoctor(String userFlow) {
        return doctorMapper.selectByPrimaryKey(userFlow);
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


    @Override
    public ResSchProcessExpress searResRecZhuZhi(String formFileName, String recFlow,
                                   String operUserFlow, String roleFlag, String processFlow,String recordFlow,String userFlow,String cksh,
                                   HttpServletRequest req) {
        operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,userFlow);
        SysUser sysUser=readSysUser(operUserFlow);
        ResDoctorSchProcess resDoctorSchProcess=processMapper.selectByPrimaryKey(processFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.getNameById(formFileName);
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
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cksh)) {
                rec.setManagerAuditUserFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
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
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return processExpressMapper.updateByPrimaryKeySelective(rec);
            }else{//新增
                rec.setRecFlow(PkUtil.getUUID());
                rec.setOperTime(DateUtil.getCurrDateTime());
                rec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                rec.setCreateUserFlow(user.getUserFlow());
                rec.setCreateTime(DateUtil.getCurrDateTime());
                rec.setModifyUserFlow(user.getUserFlow());
                rec.setModifyTime(DateUtil.getCurrDateTime());
                return processExpressMapper.insertSelective(rec);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
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
                        logger.error("", e);
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
        ResSchProcessExpressExample.Criteria create=example.createCriteria();
        create.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
                .andOperUserFlowEqualTo(operUserFlow);
        return processExpressMapper.selectByExampleWithBLOBs(example);
    }
    @Override
    public List<SchRotationDeptAfterWithBLOBs> getAfterByDoctorFlow(String doctorFlow, String applyYear) {
        if(StringUtil.isNotBlank(doctorFlow))
        {
            SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow).andApplyYearEqualTo(applyYear);
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
