package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEvaluationCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResEvaluationCfgMapper;
import com.pinde.sci.dao.base.ResEvaluationDeptExtMapper;
import com.pinde.sci.dao.base.ResEvaluationDeptMapper;
import com.pinde.sci.form.res.ResEvaluationCfgForm;
import com.pinde.sci.form.res.ResEvaluationCfgItemForm;
import com.pinde.sci.form.res.ResEvaluationCfgTitleForm;
import com.pinde.sci.form.res.ResEvaluationDeptExt;
import com.pinde.sci.model.mo.ResEvaluationCfg;
import com.pinde.sci.model.mo.ResEvaluationCfgExample;
import com.pinde.sci.model.mo.ResEvaluationDept;
import com.pinde.sci.model.mo.ResEvaluationDeptExample;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResEvaluationCfgBizImpl implements IResEvaluationCfgBiz {
    @Autowired
    private ResEvaluationCfgMapper evaluationCfgMapper;
    @Autowired
    private ResEvaluationDeptMapper evaluationDeptMapper;
    @Autowired
    private ResEvaluationDeptExtMapper evaluationDeptExtMapper;

    private static Logger logger = LoggerFactory.getLogger(ResEvaluationCfgBizImpl.class);


    @Override
    public int editEvaluationCfgTitle(ResEvaluationCfg evaluationcfg, ResEvaluationCfgTitleForm titleForm) throws Exception {
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            Document dom = null;
            Element root = null;
            String titleId = StringUtil.defaultIfEmpty(titleForm.getId(),"");
            String titleName = StringUtil.defaultIfEmpty(titleForm.getName(),"");
            String evalTypeId = StringUtil.defaultIfEmpty(titleForm.getEvalTypeId(),"");
            String evalTypeName="";

            ResEvaluationCfg existEvaluationCfg = readResEvaluationCfg(evaluationcfg.getCfgFlow());
            if(existEvaluationCfg == null){
                //第一次新增XML
                dom = DocumentHelper.createDocument();
                root = dom.addElement("evaluationCfg");
                if(StringUtil.isNotBlank(titleName) ) {
                    Element titleElement = root.addElement("title").addAttribute("id", PkUtil.getUUID());
                    titleElement.addAttribute("name", titleName);
                }
                existEvaluationCfg = new ResEvaluationCfg();
				existEvaluationCfg.setOrgFlow(currUser.getOrgFlow());
                String cfgCodeId = evaluationcfg.getCfgCodeId();
                existEvaluationCfg.setAssessTypeId(evaluationcfg.getAssessTypeId());
                existEvaluationCfg.setAssessTypeName(evaluationcfg.getAssessTypeName());
                existEvaluationCfg.setCfgCodeId(cfgCodeId);
                existEvaluationCfg.setCfgCodeName(evaluationcfg.getCfgCodeName());
                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }else{
                dom = DocumentHelper.parseText(existEvaluationCfg.getCfgBigValue());
                root = dom.getRootElement();

                if(StringUtil.isBlank(titleId)){//新增title节点
                    if(StringUtil.isNotBlank(titleName) ){
                        Element titleElement = root.addElement("title");
                        titleElement.addAttribute("id", PkUtil.getUUID());
                        titleElement.addAttribute("name", titleName);
                        titleElement.addAttribute("evalTypeId", evalTypeId);
                        titleElement.addAttribute("evalTypeName", evalTypeName);
                    }
                }else{
                    String titleXpath = "//title[@id='"+titleId+"']";
                    Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                    //titleElement.setAttributeValue("name", titleName);
                    titleElement.attribute("name").setValue(titleName);
                    Attribute attribute= titleElement.attribute("evalTypeId");
                    if(attribute!=null)
                        titleElement.attribute("evalTypeId").setValue( evalTypeId);
                    else{
                        titleElement.addAttribute("evalTypeId", evalTypeId);
                    }
                    Attribute attribute2= titleElement.attribute("evalTypeName");
                    if(attribute2!=null)
                        titleElement.attribute("evalTypeName").setValue( evalTypeName);
                    else{
                        titleElement.addAttribute("evalTypeName", evalTypeName);
                    }
                }

                existEvaluationCfg.setCfgCodeName(evaluationcfg.getCfgCodeName());
                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResEvaluationCfg readResEvaluationCfg(String cfgFlow) {
        if(StringUtil.isNotBlank(cfgFlow)){
            return  evaluationCfgMapper.selectByPrimaryKey(cfgFlow);
        }
        return null;
    }

    @Override
    public ResEvaluationCfg readResEvaluationCfgByDept(String deptFlow) {
        if(StringUtil.isEmpty(deptFlow)){
            return null;
        }
        List<ResEvaluationCfg> evaluationCfgs = evaluationDeptExtMapper.readResEvaluationCfgByDept(deptFlow);
        if(evaluationCfgs !=null && evaluationCfgs.size()>0){
           return  evaluationCfgs.get(0);
        }
        return null;
    }

    @Override
    public int deleteTitle(String cfgFlow, String id) throws Exception {
        if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
            ResEvaluationCfg existEvaluationCfg = readResEvaluationCfg(cfgFlow);
            if(existEvaluationCfg != null){
                Document dom = DocumentHelper.parseText(existEvaluationCfg.getCfgBigValue());
                String titleXpath = "//title[@id='"+id+"']";
                Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                titleElement.getParent().remove(titleElement);
                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int saveEvaluationCfg(ResEvaluationCfg evaluationCfg) {
        if(StringUtil.isNotBlank(evaluationCfg.getCfgFlow()) ){
            GeneralMethod.setRecordInfo(evaluationCfg, false);
//            return evaluationCfgMapper.updateByPrimaryKeyWithBLOBs(evaluationCfg);
            ResEvaluationCfgExample example = new ResEvaluationCfgExample();
            ResEvaluationCfgExample.Criteria criteria = example.createCriteria();
            criteria.andCfgFlowEqualTo(evaluationCfg.getCfgFlow());
            return evaluationCfgMapper.updateByExampleSelective(evaluationCfg,example);
        }else{
            evaluationCfg.setCfgFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(evaluationCfg, true);
            return evaluationCfgMapper.insert(evaluationCfg);
        }
    }

    @Override
    public List<ResEvaluationCfg> searchEvaluationCfgList(ResEvaluationCfg evaluationCfg) {
        ResEvaluationCfgExample example = new ResEvaluationCfgExample();
        com.pinde.sci.model.mo.ResEvaluationCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(evaluationCfg.getAssessTypeId())){
			criteria.andAssessTypeIdEqualTo(evaluationCfg.getAssessTypeId());
		}
        if(StringUtil.isNotBlank(evaluationCfg.getCfgCodeName())){
			criteria.andCfgCodeNameLike("%"+evaluationCfg.getCfgCodeName()+"%");
		}
        if(StringUtil.isNotBlank(evaluationCfg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationCfg.getOrgFlow());
		}
        if(StringUtil.isNotBlank(evaluationCfg.getCfgCodeId())){
            criteria.andCfgCodeIdEqualTo(evaluationCfg.getCfgCodeId());
        }
        return evaluationCfgMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ResEvaluationCfgTitleForm> getParsedGrade(String cfgCodeId) {
        if(!StringUtil.isNotBlank(cfgCodeId)){
            return null;
        }
        ResEvaluationCfgExample example = new ResEvaluationCfgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andCfgCodeIdEqualTo(cfgCodeId);
        List<ResEvaluationCfg> evaluationCfgList = evaluationCfgMapper.selectByExampleWithBLOBs(example);
        if(evaluationCfgList==null || evaluationCfgList.isEmpty()){
            return null;
        }
        ResEvaluationCfg evaluationCfg = evaluationCfgList.get(0);
        if(evaluationCfg==null || !StringUtil.isNotBlank(evaluationCfg.getCfgBigValue())){
            return null;
        }
        String content = evaluationCfg.getCfgBigValue();

        try {
            Document dom = DocumentHelper.parseText(content);

            if(dom!=null){
                String titleXpath = "//title";

                List<Element> titleElementList = dom.selectNodes(titleXpath);

                if(titleElementList != null && !titleElementList.isEmpty()){

                    List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();

                    for(Element te :titleElementList){
                        ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                        titleForm.setId(te.attributeValue("id"));
                        titleForm.setName(te.attributeValue("name"));
                        List<Element> itemElementList = te.elements("item");
                        List<ResEvaluationCfgItemForm> itemFormList = null;
                        if(itemElementList != null && !itemElementList.isEmpty()){
                            itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                            for(Element ie : itemElementList){
                                ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                                itemForm.setId(ie.attributeValue("id"));
                                itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                itemFormList.add(itemForm);
                            }
                        }
                        titleForm.setItemList(itemFormList);
                        titleFormList.add(titleForm);
                    }

                    return titleFormList;
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        return null;
    }

    @Override
    public String getNextCodeId(String orgFlow, String id) {
        ResEvaluationCfgExample example = new ResEvaluationCfgExample();
        ResEvaluationCfgExample.Criteria criteria = example.createCriteria().andOrgFlowEqualTo(orgFlow)
                .andAssessTypeIdEqualTo(id);
        example.setOrderByClause("cfg_code_id desc");
        List<ResEvaluationCfg> list = evaluationCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0){//已有数据
            String bigestColumnId = list.get(0).getCfgCodeId();
            int length = bigestColumnId.length();
            String leftPart = bigestColumnId.substring(0, length-2);
            String rightPart = bigestColumnId.substring(length-2);
            int idValue = new Integer(rightPart).intValue();
            idValue++;
            if(idValue<10){
                rightPart = "0"+idValue;
            }else{
                rightPart = String.valueOf(idValue);
            }
            return leftPart + rightPart;
        }
        return "EV001";
    }

    @Override
    public List<ResEvaluationDeptExt> readEvaluationDeptList(String cfgFlow) {
        ResEvaluationDept record = new ResEvaluationDept();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        record.setCfgFlow(cfgFlow);
        return evaluationDeptExtMapper.selectByRecord(record);
    }

    @Override
    public List<SysDept> getDepts(String cfgFlow) {
        SysUser currUser = GlobalContext.getCurrentUser();
        String orgFlow = currUser.getOrgFlow();
        if(StringUtil.isEmpty(cfgFlow) || StringUtil.isEmpty(orgFlow)){
            return null;
        }
        return evaluationDeptExtMapper.getDeptExt(cfgFlow,orgFlow);
    }

    @Override
    public int delEvaluation(String cfgFlow) {
        if(StringUtil.isNotEmpty(cfgFlow)){
            ResEvaluationCfg cfg = new ResEvaluationCfg();
            cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            GeneralMethod.setRecordInfo(cfg, false);
            ResEvaluationCfgExample example = new ResEvaluationCfgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCfgFlowEqualTo(cfgFlow);
            int result = evaluationCfgMapper.updateByExampleSelective(cfg, example);
            ResEvaluationDept dept = new ResEvaluationDept();
            dept.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            ResEvaluationDeptExample example2 = new ResEvaluationDeptExample();
            example2.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCfgFlowEqualTo(cfgFlow);
            GeneralMethod.setRecordInfo(dept, false);
            evaluationDeptMapper.updateByExampleSelective(dept, example2);
            return result;
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int saveEvaluationItemList(ResEvaluationCfgForm form) throws Exception {
        String cfgFlow = form.getCfgFlow();
        List<ResEvaluationCfgItemForm> itemFormList = form.getItemFormList();
        if(itemFormList != null && !itemFormList.isEmpty()){
            ResEvaluationCfg existEvaluationCfg = readResEvaluationCfg(cfgFlow);
            if(existEvaluationCfg != null){
                Document dom = DocumentHelper.parseText(existEvaluationCfg.getCfgBigValue());
                for(ResEvaluationCfgItemForm item : itemFormList){
                    String titleId = item.getTitleId();
                    String name = item.getName();
                    String score = item.getScore();
                    String titleXpath = "//title[@id='"+titleId+"']";
                    Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                    Element itemElement = titleElement.addElement("item");
                    itemElement.addAttribute("id", PkUtil.getUUID());
                    itemElement.addElement("name").setText(name);
                    if(StringUtil.isNotBlank(score))
                     itemElement.addElement("score").setText(score);
                }
                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }



    @Override
    public int modifyItem(String cfgFlow, ResEvaluationCfgItemForm itemForm) throws Exception {
        String id = itemForm.getId();
        if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
            ResEvaluationCfg existEvaluationCfg = readResEvaluationCfg(cfgFlow);
            if(existEvaluationCfg != null){
                Document dom = DocumentHelper.parseText(existEvaluationCfg.getCfgBigValue());
                String name = itemForm.getName();
                name = StringUtil.defaultString(name);
                String score = itemForm.getScore();
                score = StringUtil.defaultString(score);
                String itemXpath = "//item[@id='"+id+"']";
                Node itemNode = dom.selectSingleNode(itemXpath);
                Node nameNode = itemNode.selectSingleNode("name");
                nameNode.setText(name);
                Node scoreNode = itemNode.selectSingleNode("score");
                scoreNode.setText(score);

                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public int deleteItem(String cfgFlow, String id) throws Exception {
        if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
            ResEvaluationCfg existEvaluationCfg = readResEvaluationCfg(cfgFlow);
            if(existEvaluationCfg != null){
                Document dom = DocumentHelper.parseText(existEvaluationCfg.getCfgBigValue());
                String itemXpath = "//item[@id='"+id+"']";
                Element itemElement = (Element) dom.selectSingleNode(itemXpath);
                itemElement.getParent().remove(itemElement);
                existEvaluationCfg.setCfgBigValue(dom.asXML());
                return saveEvaluationCfg(existEvaluationCfg);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }
    /**
     * 保存评分科室关联表
     * @param cfgFlow
     * @param deptFlows
     * @return
     */
    public int saveEvaluationDept(String cfgFlow ,List<String> deptFlows) {
        if(StringUtil.isEmpty(cfgFlow) ) return 0;
        if(deptFlows == null || deptFlows.size() == 0 ) return 0 ;
        //1.清空双向评分表的所有关联科室
        updateEvaluationDeptN(cfgFlow);
        //2.双向评分表关联科室
        return updateEvaluationDeptY(cfgFlow,deptFlows);
    }

    private int updateEvaluationDeptY(String cfgFlow,List<String> deptFlows) {
        SysUser currUser = GlobalContext.getCurrentUser();
        List<ResEvaluationDeptExt> resEvaluationDeptExts = readEvaluationDeptList(cfgFlow);
        Set set = new HashSet<String>();
        List<String> oldDeptList = new ArrayList();
        List<String>  newDeptList= new ArrayList();
        int count = 0;
        if(resEvaluationDeptExts != null ){
            for(ResEvaluationDeptExt dept:resEvaluationDeptExts){
                set.add(dept.getDeptFlow());
            }
        }
        for(String d :deptFlows){
            if(set.contains(d)){
                oldDeptList.add(d);
            }else{
                newDeptList.add(d);
            }
        }
        //1.将已存在的置为有效
        if(oldDeptList!=null && oldDeptList.size() > 0){
            ResEvaluationDeptExample example = new ResEvaluationDeptExample();
            ResEvaluationDeptExample.Criteria criteria = example.createCriteria();
            criteria.andCfgFlowIn(oldDeptList);
            ResEvaluationDept record =new ResEvaluationDept();
            record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            GeneralMethod.setRecordInfo(record, false);
            count +=evaluationDeptMapper.updateByExampleSelective(record,example);
        }
        //2.插入不存在的
        if(newDeptList!=null && newDeptList.size() > 0){
            ResEvaluationDept record= null;
            for(String s : newDeptList){
                record =new ResEvaluationDept();
                record.setRecordFlow( PkUtil.getUUID());
                record.setCfgFlow(cfgFlow);
                record.setDeptFlow(s);
                record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                GeneralMethod.setRecordInfo(record, true);
                count += evaluationDeptMapper.insert(record);
            }
        }
        return count;
    }

    /**
     * 将双向评分表关联的科室置为无效
     * @param cfgFlow
     * @return
     */
    private int updateEvaluationDeptN(String cfgFlow) {
        ResEvaluationDeptExample example = new ResEvaluationDeptExample();
        ResEvaluationDeptExample.Criteria criteria = example.createCriteria();
        criteria.andCfgFlowEqualTo(cfgFlow);
        ResEvaluationDept record =new ResEvaluationDept();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        GeneralMethod.setRecordInfo(record, false);
        return evaluationDeptMapper.updateByExampleSelective(record,example);
    }

    @Override
    public ResEvaluationCfg read(String cfgFlow) {
        ResEvaluationCfgExample example = new ResEvaluationCfgExample();
        ResEvaluationCfgExample.Criteria criteria = example.createCriteria();
        criteria.andCfgFlowEqualTo(cfgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<ResEvaluationCfg> evaluationCfgs = evaluationCfgMapper.selectByExample(example);
        if(evaluationCfgs !=null && evaluationCfgs.size()> 0 ){
            return evaluationCfgs.get(0);
        }
        return null;
    }
}
