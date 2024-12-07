package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResAssessCfgMapper;
import com.pinde.core.common.enums.ResAssessEvalTypeEnum;
import com.pinde.core.common.enums.ResAssessScoreTypeEnum;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.sci.form.res.ResAssessCfgForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.ResAssessCfg;
import com.pinde.sci.model.mo.ResAssessCfgExample;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResAssessCfgBizImpl implements IResAssessCfgBiz {
	@Autowired
	private ResAssessCfgMapper assessCfgMapper;

	private static Logger logger = LoggerFactory.getLogger(ResAssessCfgBizImpl.class);


	@Override
	public int editAssessCfgTitle(ResAssessCfg assesscfg, ResAssessCfgTitleForm titleForm) throws Exception {
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			Document dom = null;
			Element root = null;
			String titleId = StringUtil.defaultIfEmpty(titleForm.getId(),"");
			String titleName = StringUtil.defaultIfEmpty(titleForm.getName(),"");
			String evalTypeId = StringUtil.defaultIfEmpty(titleForm.getEvalTypeId(),"");
			String evalTypeName="";
			if(StringUtil.isNotBlank(evalTypeId))
			{
				evalTypeName= ResAssessEvalTypeEnum.getNameById(evalTypeId);
			}
			ResAssessCfg existAssessCfg = readResAssessCfg(assesscfg.getCfgFlow());
			if(existAssessCfg == null){
				
				//第一次新增XML
				dom = DocumentHelper.createDocument();
				root = dom.addElement("assessCfg");
				Element titleElement = root.addElement("title").addAttribute("id", PkUtil.getUUID());
				titleElement.addAttribute("name",titleName);
				
				existAssessCfg = new ResAssessCfg();
//				existAssessCfg.setOrgFlow(currUser.getOrgFlow());
				String cfgCodeId = assesscfg.getCfgCodeId();
				existAssessCfg.setCfgCodeId(cfgCodeId);
				existAssessCfg.setCfgCodeName(ResAssessTypeEnum.getNameById(cfgCodeId));
				existAssessCfg.setAssessTypeId(ResAssessScoreTypeEnum.Percentile.getId());
				existAssessCfg.setAssessTypeName(ResAssessScoreTypeEnum.Percentile.getName());
				
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}else{
				dom = DocumentHelper.parseText(existAssessCfg.getCfgBigValue());
				root = dom.getRootElement();
				
				if(StringUtil.isBlank(titleId)){//新增title节点
					Element titleElement = root.addElement("title");
					titleElement.addAttribute("id", PkUtil.getUUID());
					titleElement.addAttribute("name", titleName);
					titleElement.addAttribute("evalTypeId", evalTypeId);
					titleElement.addAttribute("evalTypeName", evalTypeName);
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
				if(StringUtil.isNotBlank(assesscfg.getAssessTypeId())){
					existAssessCfg.setAssessTypeId(assesscfg.getAssessTypeId());
					existAssessCfg.setAssessTypeName(ResAssessScoreTypeEnum.getNameById(assesscfg.getAssessTypeId()));
				}
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResAssessCfg readResAssessCfg(String cfgFlow) {
		if(StringUtil.isNotBlank(cfgFlow)){
			return assessCfgMapper.selectByPrimaryKey(cfgFlow);
		}
		return null;
	}
	
	@Override
	public int deleteTitle(String cfgFlow, String id) throws Exception {
		if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
			ResAssessCfg existAssessCfg = readResAssessCfg(cfgFlow);
			if(existAssessCfg != null){
				Document dom = DocumentHelper.parseText(existAssessCfg.getCfgBigValue());
				String titleXpath = "//title[@id='"+id+"']";
				Element titleElement = (Element) dom.selectSingleNode(titleXpath);
				titleElement.getParent().remove(titleElement);
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveAssessCfg(ResAssessCfg assessCfg) {
		if(StringUtil.isNotBlank(assessCfg.getCfgFlow()) ){
			GeneralMethod.setRecordInfo(assessCfg, false);
			return assessCfgMapper.updateByPrimaryKeyWithBLOBs(assessCfg);
		}else{
			assessCfg.setCfgFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(assessCfg, true);
			return assessCfgMapper.insert(assessCfg);
		}
	}

	@Override
	public List<ResAssessCfg> searchAssessCfgList(ResAssessCfg assessCfg) {
		ResAssessCfgExample example = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(assessCfg.getOrgFlow())){
//			criteria.andOrgFlowEqualTo(assessCfg.getOrgFlow());
//		}
		if (assessCfg != null) {
			if (StringUtil.isNotBlank(assessCfg.getCfgCodeId())) {
				criteria.andCfgCodeIdEqualTo(assessCfg.getCfgCodeId());
			}
			if (StringUtil.isNotBlank(assessCfg.getFormName())) {
				criteria.andFormNameLike("%"+assessCfg.getFormName()+"%");
			}
			if (StringUtil.isNotBlank(assessCfg.getFormStatusId())) {
				criteria.andFormStatusIdEqualTo(assessCfg.getFormStatusId());
			}
		}
		return assessCfgMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<ResAssessCfgTitleForm> getParsedGrade(String cfgCodeId) {
		if(!StringUtil.isNotBlank(cfgCodeId)){
			return null;
		}
		ResAssessCfgExample example = new ResAssessCfgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
			.andCfgCodeIdEqualTo(cfgCodeId);
		List<ResAssessCfg> assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(example);
		if(assessCfgList==null || assessCfgList.isEmpty()){
			return null;
		}
		ResAssessCfg assessCfg = assessCfgList.get(0);
		if(assessCfg==null || !StringUtil.isNotBlank(assessCfg.getCfgBigValue())){
			return null;
		}
		String content = assessCfg.getCfgBigValue();
		
		try {
			Document dom = DocumentHelper.parseText(content);
			
			if(dom!=null){
				String titleXpath = "//title";
				
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				
				if(titleElementList != null && !titleElementList.isEmpty()){
					
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
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
	public Map<String, List<ResAssessCfgTitleForm>> getParsedGradeMap(String cfgCodeId) {
		Map<String, List<ResAssessCfgTitleForm>> map=new HashMap<>();
		if(!StringUtil.isNotBlank(cfgCodeId)){
			return null;
		}
		ResAssessCfgExample example = new ResAssessCfgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andCfgCodeIdEqualTo(cfgCodeId);
		List<ResAssessCfg> assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(example);
		if(assessCfgList==null || assessCfgList.isEmpty()){
			return null;
		}
		for (ResAssessCfg assessCfg : assessCfgList) {
			if(assessCfg==null || !StringUtil.isNotBlank(assessCfg.getCfgBigValue())){
				return null;
			}
			String content = assessCfg.getCfgBigValue();
			try {
				Document dom = DocumentHelper.parseText(content);

				if(dom!=null){
					String titleXpath = "//title";

					List<Element> titleElementList = dom.selectNodes(titleXpath);

					if(titleElementList != null && !titleElementList.isEmpty()){

						List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();

						for(Element te :titleElementList){
							ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
							titleForm.setId(te.attributeValue("id"));
							titleForm.setName(te.attributeValue("name"));
							List<Element> itemElementList = te.elements("item");
							List<ResAssessCfgItemForm> itemFormList = null;
							if(itemElementList != null && !itemElementList.isEmpty()){
								itemFormList = new ArrayList<ResAssessCfgItemForm>();
								for(Element ie : itemElementList){
									ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
									itemForm.setId(ie.attributeValue("id"));
									itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
									itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
									itemFormList.add(itemForm);
								}
							}
							titleForm.setItemList(itemFormList);
							titleFormList.add(titleForm);
						}

						map.put(assessCfg.getCfgFlow(),titleFormList);
					}
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return map;
	}

	@Override
	public int updateByPrimaryKeySelective(ResAssessCfg resAssessCfg) {
		return assessCfgMapper.updateByPrimaryKeySelective(resAssessCfg);
	}

	@Override
	public List<ResAssessCfg> selectByExample(ResAssessCfg assessCfg) {
		ResAssessCfgExample example = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (assessCfg != null) {
			if (StringUtil.isNotBlank(assessCfg.getCfgCodeId())) {
				criteria.andCfgCodeIdEqualTo(assessCfg.getCfgCodeId());
			}
			if(StringUtil.isNotBlank(assessCfg.getFormStatusId())){
				criteria.andFormStatusIdEqualTo(assessCfg.getFormStatusId());
			}
		}
		return assessCfgMapper.selectByExample(example);
	}

	@Override
	public List<ResAssessCfg> selectByExampleWithBLOBs(ResAssessCfg assessCfg) {
		ResAssessCfgExample example = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (assessCfg != null) {
			if (StringUtil.isNotBlank(assessCfg.getCfgCodeId())) {
				criteria.andCfgCodeIdEqualTo(assessCfg.getCfgCodeId());
			}
			if(StringUtil.isNotBlank(assessCfg.getFormStatusId())){
				criteria.andFormStatusIdEqualTo(assessCfg.getFormStatusId());
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return assessCfgMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int saveAssessItemList(ResAssessCfgForm form) throws Exception {
		String cfgFlow = form.getCfgFlow();
		List<ResAssessCfgItemForm> itemFormList = form.getItemFormList();
		if(itemFormList != null && !itemFormList.isEmpty()){
			ResAssessCfg existAssessCfg = readResAssessCfg(cfgFlow);
			if(existAssessCfg != null){
				Document dom = DocumentHelper.parseText(existAssessCfg.getCfgBigValue());
				for(ResAssessCfgItemForm item : itemFormList){
					String titleId = item.getTitleId();
					String name = item.getName();
					String type = item.getType();
					String row = item.getRow();
					String score = item.getScore();
					String titleXpath = "//title[@id='"+titleId+"']";
					Element titleElement = (Element) dom.selectSingleNode(titleXpath);
					Element itemElement = titleElement.addElement("item");
					itemElement.addAttribute("id", PkUtil.getUUID());
					itemElement.addElement("type").setText(type);
					itemElement.addElement("row").setText(row);
					itemElement.addElement("name").setText(name);
					itemElement.addElement("score").setText(score);
				}
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}


	@Override
	public int modifyItem(String cfgFlow, ResAssessCfgItemForm itemForm) throws Exception {
		String id = itemForm.getId();
		if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
			ResAssessCfg existAssessCfg = readResAssessCfg(cfgFlow);
			if(existAssessCfg != null){
				Document dom = DocumentHelper.parseText(existAssessCfg.getCfgBigValue());
				String type = itemForm.getType();
				type = StringUtil.defaultString(type);
				String row = itemForm.getRow();
				row = StringUtil.defaultString(row);
				String name = itemForm.getName();
				name = StringUtil.defaultString(name);
				String score = itemForm.getScore();
				score = StringUtil.defaultString(score);
				String itemXpath = "//item[@id='"+id+"']";
				Node itemNode = dom.selectSingleNode(itemXpath);
				Node typeNode = itemNode.selectSingleNode("type");
				typeNode.setText(type);
				Node rowNode = itemNode.selectSingleNode("row");
				rowNode.setText(row);
				Node nameNode = itemNode.selectSingleNode("name");
				nameNode.setText(name);
				Node scoreNode = itemNode.selectSingleNode("score");
				scoreNode.setText(score);
				
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int deleteItem(String cfgFlow, String id) throws Exception {
		if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
			ResAssessCfg existAssessCfg = readResAssessCfg(cfgFlow);
			if(existAssessCfg != null){
				Document dom = DocumentHelper.parseText(existAssessCfg.getCfgBigValue());
				String itemXpath = "//item[@id='"+id+"']";
				Element itemElement = (Element) dom.selectSingleNode(itemXpath);
				itemElement.getParent().remove(itemElement);
				existAssessCfg.setCfgBigValue(dom.asXML());
				return saveAssessCfg(existAssessCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<ResAssessCfgTitleForm> readForm(String cfgFlow) {
		if(StringUtil.isNotBlank(cfgFlow)){
			ResAssessCfg assessCfg = assessCfgMapper.selectByPrimaryKey(cfgFlow);
			if(assessCfg==null || !StringUtil.isNotBlank(assessCfg.getCfgBigValue())){
				return null;
			}
			String content = assessCfg.getCfgBigValue();

			try {
				Document dom = DocumentHelper.parseText(content);

				if(dom!=null){
					String titleXpath = "//title";

					List<Element> titleElementList = dom.selectNodes(titleXpath);

					if(titleElementList != null && !titleElementList.isEmpty()){

						List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();

						for(Element te :titleElementList){
							ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
							titleForm.setId(te.attributeValue("id"));
							titleForm.setName(te.attributeValue("name"));
							List<Element> itemElementList = te.elements("item");
							List<ResAssessCfgItemForm> itemFormList = null;
							if(itemElementList != null && !itemElementList.isEmpty()){
								itemFormList = new ArrayList<ResAssessCfgItemForm>();
								for(Element ie : itemElementList){
									ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
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
		}
		return null;
	}
}
