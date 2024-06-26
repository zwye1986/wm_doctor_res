package com.pinde.sci.biz.gzzyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzzyjxres.IGzjxResDoctorEvaluationBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResDoctorProcessEvalConfigMapper;
import com.pinde.sci.dao.base.ResDoctorSchProcessEvalMapper;
import com.pinde.sci.enums.gzzyjxres.StuRoleEnum;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgExt;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgItemExt;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgTitleExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GzjxResDoctorEvaluationBizImpl implements IGzjxResDoctorEvaluationBiz {
    @Autowired
    private ResDoctorProcessEvalConfigMapper evalConfigMapper;
    @Autowired
    private ResDoctorSchProcessEvalMapper evalMapper;
    @Autowired
    private IStuUserResumeBiz stuUserBiz;
    @Autowired
    private IUserBiz sysUserBiz;

    @Override
    public ResDoctorProcessEvalConfig readEvalConfig(String roleId) {
        if (StringUtil.isNotBlank(roleId)) {
            ResDoctorProcessEvalConfigExample example = new ResDoctorProcessEvalConfigExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andRoleIdEqualTo(roleId);
            List<ResDoctorProcessEvalConfig> evalConfigList = evalConfigMapper.selectByExampleWithBLOBs(example);
            if (null != evalConfigList && evalConfigList.size() > 0) {
                return evalConfigList.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveEvalConfig(String configFlow, StuEvalCfgTitleExt title, String roleId) throws DocumentException {
        if (StringUtil.isBlank(configFlow)) {//新增第一个评分项目
            configFlow = PkUtil.getUUID();
            ResDoctorProcessEvalConfig record = new ResDoctorProcessEvalConfig();
            record.setConfigFlow(configFlow);
            record.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            record.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            record.setRoleId(roleId);
            Document dom = DocumentHelper.createDocument();
            Element root = dom.addElement("evalCfg");
            Element titleElement = root.addElement("title").addAttribute("id", PkUtil.getUUID());
            titleElement.addAttribute("name", title.getName());
            titleElement.addAttribute("orderNum", title.getOrderNum());
            record.setFormCfg(dom.asXML());
            GeneralMethod.setRecordInfo(record, true);
            return evalConfigMapper.insertSelective(record);
        } else {
            ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
            Document dom = DocumentHelper.parseText(existForm.getFormCfg());
            Element root = dom.getRootElement();
            if (StringUtil.isBlank(title.getId())) {//新增评分项目，title节点
                Element titleElement = root.addElement("title");
                titleElement.addAttribute("id", PkUtil.getUUID());
                titleElement.addAttribute("name", title.getName());
                titleElement.addAttribute("orderNum", title.getOrderNum());
            } else {//修改评分项目，title节点
                String titleXpath = "//title[@id='" + title.getId() + "']";
                Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                titleElement.addAttribute("name", title.getName());
                titleElement.addAttribute("orderNum", title.getOrderNum());
            }
            existForm.setFormCfg(dom.asXML());
            return editForm(existForm);
        }
    }

    public int editForm(ResDoctorProcessEvalConfig form) {
        if (form != null) {
            String configFlow = form.getConfigFlow();
            if (StringUtil.isNotBlank(configFlow)) {
                GeneralMethod.setRecordInfo(form, false);
                return evalConfigMapper.updateByPrimaryKeySelective(form);
            } else {
                form.setConfigFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(form, true);
                return evalConfigMapper.insertSelective(form);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int deleteTitle(String configFlow, String id) throws DocumentException {
        if (StringUtil.isNotBlank(configFlow) && StringUtil.isNotBlank(id)) {
            ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
            if (null != existForm) {
                Document dom = DocumentHelper.parseText(existForm.getFormCfg());
                String titleXpath = "//title[@id='" + id + "']";
                Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                titleElement.getParent().remove(titleElement);
                existForm.setFormCfg(dom.asXML());
                return editForm(existForm);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int deleteItem(String configFlow, String id) throws DocumentException {
        if (StringUtil.isNotBlank(configFlow) && StringUtil.isNotBlank(id)) {
            ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
            if (null != existForm) {
                Document dom = DocumentHelper.parseText(existForm.getFormCfg());
                String itemXpath = "//item[@id='" + id + "']";
                Element itemElement = (Element) dom.selectSingleNode(itemXpath);
                itemElement.getParent().remove(itemElement);
                existForm.setFormCfg(dom.asXML());
                return editForm(existForm);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int modifyItem(String configFlow, StuEvalCfgItemExt item) throws DocumentException {
        if (StringUtil.isNotBlank(configFlow)) {
            ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(configFlow);
            Document dom = DocumentHelper.parseText(existForm.getFormCfg());
            Element root = dom.getRootElement();
            //修改评分指标，item节点
            String itemXpath = "//item[@id='" + item.getId() + "']";
            Node itemNode = dom.selectSingleNode(itemXpath);
            Node nameNode = itemNode.selectSingleNode("name");
            nameNode.setText(item.getName());
            Node scoreNode = itemNode.selectSingleNode("score");
            scoreNode.setText(item.getScore());
            Node orderNode = itemNode.selectSingleNode("order");
            orderNode.setText(item.getOrder());
            existForm.setFormCfg(dom.asXML());
            return editForm(existForm);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int saveFormItemList(StuEvalCfgExt form) throws DocumentException {
        List<StuEvalCfgItemExt> itemFormList = form.getItemFormList();
        if (null != itemFormList && !itemFormList.isEmpty()) {
            ResDoctorProcessEvalConfig existForm = evalConfigMapper.selectByPrimaryKey(form.getConfigFlow());
            if (null != existForm) {
                Document dom = DocumentHelper.parseText(existForm.getFormCfg());
                for (StuEvalCfgItemExt item : itemFormList) {
                    String titleId = item.getTitleId();
                    String name = item.getName();
                    String score = item.getScore();
                    String order = item.getOrder();
                    String titleXpath = "//title[@id='" + titleId + "']";
                    Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                    Element itemElement = titleElement.addElement("item");
                    itemElement.addAttribute("id", PkUtil.getUUID());
                    itemElement.addElement("name").setText(name);
                    itemElement.addElement("score").setText(score);
                    itemElement.addElement("order").setText(order);
                }
                existForm.setFormCfg(dom.asXML());
                return editForm(existForm);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int saveForm(Map<String, Object> param) {
        ResDoctorSchProcessEvalWithBLOBs eval = (ResDoctorSchProcessEvalWithBLOBs) param.get("eval");
        StuUserResume stuUserResume = stuUserBiz.getStuUserByKey(eval.getProcessFlow());
        SysUser sysUser = sysUserBiz.readSysUser(stuUserResume.getUserFlow());
        //被考核人角色
        String roleId = (String) param.get("roleId");
        //考核人角色
        String kaoherenRoleId = (String) param.get("kaoherenRoleId");
        //考核人
        String kaoherenFlow = "";
        String kaoherenName = "";
        //被考核人
        String beikaoherenFlow = "";
        String beikaoherenName = "";
        if (StuRoleEnum.Doctor.getId().equals(roleId)) {
            if (StuRoleEnum.Secretary.getId().equals(kaoherenRoleId)) {
                kaoherenFlow = stuUserResume.getSpeId();
                kaoherenName = stuUserResume.getSpeName();
            } else if (StuRoleEnum.Teacher.getId().equals(kaoherenRoleId)) {
                kaoherenFlow = stuUserResume.getTeacherFlow();
                kaoherenName = stuUserResume.getTeacherName();
            }
            beikaoherenFlow = stuUserResume.getUserFlow();
            beikaoherenName = sysUser.getUserName();
        } else if (StuRoleEnum.Secretary.getId().equals(roleId)) {
            kaoherenFlow = stuUserResume.getUserFlow();
            kaoherenName = sysUser.getUserName();
            beikaoherenFlow = stuUserResume.getSpeId();
            beikaoherenName = stuUserResume.getSpeName();
        } else if (StuRoleEnum.Teacher.getId().equals(roleId)) {
            kaoherenFlow = stuUserResume.getUserFlow();
            kaoherenName = sysUser.getUserName();
            beikaoherenFlow = stuUserResume.getTeacherFlow();
            beikaoherenName = stuUserResume.getTeacherName();
        }

        if (StringUtil.isNotBlank(eval.getProcessFlow())) {
            ResDoctorProcessEvalConfig config = new ResDoctorProcessEvalConfig();
            if ("Y".equals(eval.getIsForm())) {
                config = evalConfigMapper.selectByPrimaryKey((String) param.get("configFlow"));
            }
            evalConfigMapper.selectByPrimaryKey(eval.getRecordFlow());
            eval.setRecordFlow(PkUtil.getUUID());
            eval.setDoctorFlow(beikaoherenFlow);
            eval.setDoctorName(beikaoherenName);
            eval.setEvalUserFlow(kaoherenFlow);
            eval.setEvalUserName(kaoherenName);
            String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            eval.setEvalTime(curTime);
            if (GlobalConstant.FLAG_Y.equals(eval.getIsForm())) {
                eval.setFormCfg(config.getFormCfg());
                List<String> scoreIdList = (List<String>) param.get("scoreIdList");
                List<String> scoreList = (List<String>) param.get("scoreList");
                Document dom = DocumentHelper.createDocument();
                Element root = dom.addElement("evalCfg");
                for (int i = 0; i < scoreIdList.size(); i++) {
                    Element titleElement = root.addElement("score").addAttribute("id", scoreIdList.get(i));
                    titleElement.setText(scoreList.get(i));
                }
                eval.setEvalResult(dom.asXML());
            } else {
                eval.setIsForm(GlobalConstant.FLAG_N);
            }
            GeneralMethod.setRecordInfo(eval, true);
            return evalMapper.insertSelective(eval);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResDoctorSchProcessEvalWithBLOBs readProcessEvalByFlow(String recordFlow) {
        return evalMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<ResDoctorSchProcessEval> queryEvalListByFlow(String resumeFlow, List<String> kaoherenFlowList, List<String> beikaoherenFlowList) {
        ResDoctorSchProcessEvalExample example = new ResDoctorSchProcessEvalExample();
        ResDoctorSchProcessEvalExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(resumeFlow)) {
            criteria.andProcessFlowEqualTo(resumeFlow);
        }
        if (kaoherenFlowList != null && kaoherenFlowList.size() > 0) {
            criteria.andEvalUserFlowIn(kaoherenFlowList);
        }
        if (beikaoherenFlowList != null && beikaoherenFlowList.size() > 0) {
            criteria.andDoctorFlowIn(beikaoherenFlowList);
        }
        example.setOrderByClause("PROCESS_FLOW,START_TIME");
        return evalMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorSchProcessEval> searchByItems(ResDoctorSchProcessEval schProcessEval) {
        ResDoctorSchProcessEvalExample example = new ResDoctorSchProcessEvalExample();
        ResDoctorSchProcessEvalExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(schProcessEval.getEvalUserFlow())) {
            criteria.andEvalUserFlowEqualTo(schProcessEval.getEvalUserFlow());
        }
        example.setOrderByClause("PROCESS_FLOW,START_TIME");
        return evalMapper.selectByExample(example);
    }
}
