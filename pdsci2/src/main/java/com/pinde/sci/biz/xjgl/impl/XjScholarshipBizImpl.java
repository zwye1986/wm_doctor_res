package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjScholarshipBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.NyjzScholarshipMainMapper;
import com.pinde.sci.enums.xjgl.ScholarshipTypeEnum;
import com.pinde.sci.form.xjgl.XjScholarshipApplyForm;
import com.pinde.sci.model.mo.NyjzScholarshipMain;
import com.pinde.sci.model.mo.NyjzScholarshipMainExample;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suncg
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XjScholarshipBizImpl implements IXjScholarshipBiz {

    @Autowired
    private NyjzScholarshipMainMapper scholarshipMapper;

    @Override
    public NyjzScholarshipMain readScholarship(String recordFlow) {
        return scholarshipMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<NyjzScholarshipMain> queryStuApplyList(NyjzScholarshipMain main) {
        NyjzScholarshipMainExample example = new NyjzScholarshipMainExample();
        NyjzScholarshipMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        NyjzScholarshipMainExample.Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        //学员流水号
        if(StringUtil.isNotBlank(main.getUserFlow())){
            criteria.andUserFlowEqualTo(main.getUserFlow());
        }
        //奖助类型
        if(StringUtil.isNotBlank(main.getApplyTypeId())){
            criteria.andApplyTypeIdEqualTo(main.getApplyTypeId());
            criteria2.andApplyTypeIdEqualTo(main.getApplyTypeId());
        }
        //培养单位
        if(StringUtil.isNotBlank(main.getPydwOrgFlow())){
            criteria.andPydwOrgFlowEqualTo(main.getPydwOrgFlow());
            criteria2.andPydwOrgFlowEqualTo(main.getPydwOrgFlow());
            //无需自己审核的奖助申请不展示
            criteria.andPydwAuditStatusIdIsNotNull();
            criteria2.andPydwAuditStatusIdIsNotNull();
        }
        //分委会
        if(StringUtil.isNotBlank(main.getFwhOrgFlow())){
            criteria.andFwhOrgFlowEqualTo(main.getFwhOrgFlow());
            criteria2.andFwhOrgFlowEqualTo(main.getFwhOrgFlow());
            criteria.andFwhAuditStatusIdIsNotNull();
            criteria2.andFwhAuditStatusIdIsNotNull();
        }
        //姓名
        if(StringUtil.isNotBlank(main.getUserName())){
            criteria.andUserNameLike("%"+main.getUserName()+"%");
            criteria2.andUserNameLike("%"+main.getUserName()+"%");
        }
        //学号
        if(StringUtil.isNotBlank(main.getStudentId())){
            criteria.andStudentIdLike("%"+main.getStudentId()+"%");
            criteria2.andStudentIdLike("%"+main.getStudentId()+"%");
        }
        //班级
        if(StringUtil.isNotBlank(main.getClassId())){
            criteria.andClassIdEqualTo(main.getClassId());
            criteria2.andClassIdEqualTo(main.getClassId());
        }
        //年级
        if(StringUtil.isNotBlank(main.getSessionNumber())){
            criteria.andSessionNumberEqualTo(main.getSessionNumber());
            criteria2.andSessionNumberEqualTo(main.getSessionNumber());
        }
        //申请开始时间
        if(StringUtil.isNotBlank(main.getCreateTime())){
            criteria.andApplyTimeGreaterThanOrEqualTo(main.getCreateTime());
            criteria2.andApplyTimeGreaterThanOrEqualTo(main.getCreateTime());
        }
        //申请结束时间
        if(StringUtil.isNotBlank(main.getModifyTime())){
            criteria.andApplyTimeLessThanOrEqualTo(main.getModifyTime());
            criteria2.andApplyTimeLessThanOrEqualTo(main.getModifyTime());
        }
        //导师一
        if(StringUtil.isNotBlank(main.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(main.getDoctorFlow());
            criteria.andDoctorAuditStatusIdIsNotNull();
        }
        //导师二
        if(StringUtil.isNotBlank(main.getSecondDoctorFlow())){
            criteria2.andSecondDoctorFlowEqualTo(main.getSecondDoctorFlow());
            criteria2.andSecondAuditStatusIdIsNotNull();
            //导师可作为导师一或导师二形式
            example.or(criteria2);
        }
        example.setOrderByClause("APPLY_TIME DESC,CREATE_TIME DESC");
        return scholarshipMapper.selectByExample(example);
    }

    @Override
    public int saveApplyInfo(NyjzScholarshipMain main,XjScholarshipApplyForm scholarship) {
        main.setApplyTime(DateUtil.getCurrDate());
        main.setContent(getXmlFromExtInfo(scholarship));
        //助管岗位 思政科直接审核
        if(ScholarshipTypeEnum.Zggw.getId().equals(main.getApplyTypeId())){
            main.setSzkAuditStatusId("Passing");
            main.setSzkAuditStatusName("待审核");
        }else{
            //优秀研究生，优秀研究生骨干，优秀毕业研究生 导师-培养单位-思政科审核；其他类型 导师-培养单位-分委会-思政科审核
            //导师一
            if(StringUtil.isNotBlank(main.getDoctorFlow())){
                main.setDoctorAuditStatusId("Passing");
                main.setDoctorAuditStatusName("待审核");
            }
            //导师二
            if(StringUtil.isNotBlank(main.getSecondDoctorFlow())){
                main.setSecondAuditStatusId("Passing");
                main.setSecondAuditStatusName("待审核");
            }
        }
        if(StringUtil.isBlank(main.getRecordFlow())){
            main.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(main,true);
            return scholarshipMapper.insertSelective(main);
        }else if(!ScholarshipTypeEnum.Zggw.getId().equals(main.getApplyTypeId())) {
            //重新申请
            main.setPydwAuditStatusId("");
            main.setPydwAuditStatusName("");
            main.setPydwAuditAdvice("");
            main.setPydwAuditTime("");
            main.setFwhAuditStatusId("");
            main.setFwhAuditStatusName("");
            main.setFwhAuditAdvice("");
            main.setFwhAuditTime("");
            main.setSzkAuditStatusId("");
            main.setSzkAuditStatusName("");
            main.setSzkAuditAdvice("");
            main.setSzkAuditTime("");
        }
        GeneralMethod.setRecordInfo(main, false);
        return scholarshipMapper.updateByPrimaryKeySelective(main);
    }
    //将form对象封装为xml文本
    public String getXmlFromExtInfo(XjScholarshipApplyForm scholarship){
        String xmlBody = null;
        if(null!=scholarship){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("scholarshipApplyForm");
            Map<String,String> filedMap = getClassFieldMap(scholarship);
            if(filedMap!=null && filedMap.size()>0){
                for(String key : filedMap.keySet()){
                    Element item = root.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }
            xmlBody=doc.asXML();
        }
        return xmlBody;
    }
    //获取属性名和值
    private Map<String,String> getClassFieldMap(Object obj){
        if(null!=obj){
            try{
                Map<String,String> filedMap = new HashMap<String, String>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for(Field f : fileds){
                    String typeName = f.getType().getSimpleName();
                    if(stringClassName.equals(typeName)){
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0,1).toUpperCase();
                        String methedName = "get"+firstLetter+attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String)getMethod.invoke(obj);
                        filedMap.put(attrName,StringUtil.defaultString(value));
                    }
                }
                return filedMap;
            }catch(Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    @Override
    public int delApplyInfo(String recordFlow) {
        return scholarshipMapper.deleteByPrimaryKey(recordFlow);
    }

    @Override
    public int updateAuditOption(NyjzScholarshipMain main) {
        return scholarshipMapper.updateByPrimaryKeySelective(main);
    }

    @Override
    public XjScholarshipApplyForm parseExtInfoXml(String content) {
        if(StringUtil.isNotBlank(content)){
            try{
                XjScholarshipApplyForm extInfo = new XjScholarshipApplyForm();
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                if(null!=root){
                    List<Element> extInfoAttrEles = root.elements();
                    if(extInfoAttrEles!=null && extInfoAttrEles.size()>0){
                        for(Element attr : extInfoAttrEles){
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            setValue(extInfo,attrName,attrValue);
                        }
                    }
                }
                return extInfo;
            }catch(Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    //为对象自动复制
    private void setValue(Object obj,String attrName,String attrValue) {
        try {
            Class<?> objClass = obj.getClass();
            String firstLetter = attrName.substring(0, 1).toUpperCase();
            String methedName = "set" + firstLetter + attrName.substring(1);
            Method setMethod = objClass.getMethod(methedName, new Class[]{String.class});
            setMethod.invoke(obj, new Object[]{attrValue});
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
