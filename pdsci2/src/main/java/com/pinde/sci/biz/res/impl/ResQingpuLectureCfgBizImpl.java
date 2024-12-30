package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.QingpuLectureEvalCfg;
import com.pinde.core.model.QingpuLectureEvalCfgExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResQingpuLectureCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.core.common.sci.dao.QingpuLectureEvalCfgMapper;
import com.pinde.sci.form.res.QingpuLectureCfgItemExt;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResQingpuLectureCfgBizImpl implements IResQingpuLectureCfgBiz {
	@Autowired
	private QingpuLectureEvalCfgMapper lectureEvalCfgMapper;


	@Override
	public QingpuLectureEvalCfg readLectureEvalCfg(String recordFlow) {
		if(StringUtil.isNotBlank(recordFlow)){
			return lectureEvalCfgMapper.selectByPrimaryKey(recordFlow);
		}
		return null;
	}

	@Override
	public int deleteTitle(String recordFlow, String id) throws Exception {
		if(StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(id)){
			QingpuLectureEvalCfg existLectureEvalCfg = readLectureEvalCfg(recordFlow);
			if(existLectureEvalCfg != null){
				Document dom = DocumentHelper.parseText(existLectureEvalCfg.getCfgBigValue());
				String titleXpath = "//title[@id='"+id+"']";
				Element titleElement = (Element) dom.selectSingleNode(titleXpath);
				titleElement.getParent().remove(titleElement);
				existLectureEvalCfg.setCfgBigValue(dom.asXML());
				return saveQingpuLectureCfgCfg(existLectureEvalCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int saveQingpuLectureCfgCfg(QingpuLectureEvalCfg lectureEvalCfg) {
		if(StringUtil.isNotBlank(lectureEvalCfg.getRecordFlow()) ){
			GeneralMethod.setRecordInfo(lectureEvalCfg, false);
			return lectureEvalCfgMapper.updateByPrimaryKeyWithBLOBs(lectureEvalCfg);
		}else{
			lectureEvalCfg.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(lectureEvalCfg, true);
			return lectureEvalCfgMapper.insert(lectureEvalCfg);
		}
	}

	@Override
	public List<QingpuLectureEvalCfg> searchLectureEvalCfgList(QingpuLectureEvalCfg lectureEvalCfg) {
		QingpuLectureEvalCfgExample example = new QingpuLectureEvalCfgExample();
        QingpuLectureEvalCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(lectureEvalCfg.getTypeId())){
			criteria.andTypeIdEqualTo(lectureEvalCfg.getTypeId());
		}
		if(StringUtil.isNotBlank(lectureEvalCfg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(lectureEvalCfg.getOrgFlow());
		}
		example.setOrderByClause("create_time desc");
		return lectureEvalCfgMapper.selectByExampleWithBLOBs(example);
	}



	@Override
	public int modifyItem(String recordFlow, QingpuLectureCfgItemExt itemForm) throws Exception {
		String id = itemForm.getId();
		if(StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(id)){
			QingpuLectureEvalCfg existLectureEvalCfg = readLectureEvalCfg(recordFlow);
			if(existLectureEvalCfg != null){
				Document dom = DocumentHelper.parseText(existLectureEvalCfg.getCfgBigValue());
				String name = itemForm.getName();
				name = StringUtil.defaultString(name);
				String score = itemForm.getScore();
				score = StringUtil.defaultString(score);
				String order = itemForm.getOrder();
				order = StringUtil.defaultString(order);
				String itemXpath = "//item[@id='"+id+"']";
				Node itemNode = dom.selectSingleNode(itemXpath);
				Node nameNode = itemNode.selectSingleNode("name");
				nameNode.setText(name);
				Node scoreNode = itemNode.selectSingleNode("score");
				scoreNode.setText(score);
				Node orderNode = itemNode.selectSingleNode("order");
				if(orderNode!=null){
					orderNode.setText(order);
				}
				existLectureEvalCfg.setCfgBigValue(dom.asXML());
				return saveQingpuLectureCfgCfg(existLectureEvalCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int deleteItem(String recordFlow, String id) throws Exception {
		if(StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(id)){
			QingpuLectureEvalCfg existLectureEvalCfg = readLectureEvalCfg(recordFlow);
			if(existLectureEvalCfg != null){
				Document dom = DocumentHelper.parseText(existLectureEvalCfg.getCfgBigValue());
				String itemXpath = "//item[@id='"+id+"']";
				Element itemElement = (Element) dom.selectSingleNode(itemXpath);
				itemElement.getParent().remove(itemElement);
				existLectureEvalCfg.setCfgBigValue(dom.asXML());
				return saveQingpuLectureCfgCfg(existLectureEvalCfg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
}
