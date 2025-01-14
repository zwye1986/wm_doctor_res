package com.pinde.sci.biz.inx.impl;

import com.pinde.core.common.enums.InfoStatusEnum;
import com.pinde.core.common.sci.dao.InxInfoMapper;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.InxInfoExample;
import com.pinde.core.model.InxInfoExample.Criteria;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.core.common.form.InxInfoForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
//@Transactional(rollbackFor=Exception.class)
public class InxInfoBizImpl implements IInxInfoBiz {
	@Resource
	private InxInfoMapper inxInfoMapper;


//	@Override
//	public List<InxInfo> queryByForm(InxInfoForm form) {
//		List<InxInfo> infoList = inxInfoExtMapper.selectByForm(form);
//		return infoList;
//	}

//	@Override
//	public List<InxInfo> queryByFormWithBlobs(InxInfoForm form) {
//		List<InxInfo> infoList = inxInfoExtMapper.selectByFormWithBlobs(form);
//		return infoList;
//	}
	
	@Override
	public InxInfo getByInfoFlow(String infoFlow) {
		return inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

//	@Override
//	public List<InxInfo> queryImgNews(InxInfoForm form) {
//		List<InxInfo> imgNewsList = inxInfoExtMapper.selectByFormWithBlobs(form);
//		return imgNewsList;
//	}

	@Override
	public List<InxInfo> getList(InxInfoForm form) {
		InxInfoExample example = new InxInfoExample();
        Criteria criteria = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        Criteria criteria2 = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(form.getColumnId())){
			criteria.andColumnIdLike(form.getColumnId()+"%");
			criteria2.andColumnIdLike(form.getColumnId()+"%");
		}
		if(null!=form.getColumnIds()&&form.getColumnIds().size()>0){
			criteria.andColumnIdIn(form.getColumnIds());
			criteria2.andColumnIdIn(form.getColumnIds());
		}
		if(null!=form.getRoleFlows()&&form.getRoleFlows().size()>0){
			criteria.andRoleFlowIn(form.getRoleFlows());
			criteria2.andRoleFlowIn(form.getRoleFlows());
		}
		if(StringUtil.isNotBlank(form.getRoleFlow())){
			criteria.andRoleFlowEqualTo(form.getRoleFlow());
			criteria2.andRoleFlowEqualTo(form.getRoleFlow());
		}
		addCriteria(form, criteria, criteria2);
		example.or(criteria2);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(form.getOrderByViewNum())) {
			example.setOrderByClause("VIEW_NUM DESC nulls last, IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}else{
			example.setOrderByClause("IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(form.getIsWithBlobs())) {
			return this.inxInfoMapper.selectByExampleWithBLOBs(example);
		}
		return this.inxInfoMapper.selectByExample(example);
	}

	private void addCriteria(InxInfoForm form, Criteria criteria, Criteria criteria2) {
		if(StringUtil.isNotBlank(form.getRecordStatus())){
			criteria.andRecordStatusEqualTo(form.getRecordStatus());
			criteria2.andRecordStatusEqualTo(form.getRecordStatus());
		}
		if(StringUtil.isNotBlank(form.getInfoTitle())){
			criteria.andInfoTitleLike("%"+form.getInfoTitle()+"%");
			criteria2.andInfoTitleLike("%"+form.getInfoTitle()+"%");
		}
		if(StringUtil.isNotBlank(form.getInfoKeyword())){
			criteria.andInfoTitleLike("%"+form.getInfoKeyword()+"%");
			criteria2.andInfoKeywordLike("%"+form.getInfoKeyword()+"%");
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(form.getHasImage())) {
			criteria.andTitleImgIsNotNull();
			criteria2.andTitleImgIsNotNull();
		}
	}

	@Override
	public List<InxInfo> queryClassifyList(InxInfoForm form) {
		InxInfoExample example = new InxInfoExample();
        Criteria criteria = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        Criteria criteria2 = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(form.getColumnId())){
			criteria.andColumnIdEqualTo(form.getColumnId());
			criteria2.andColumnIdEqualTo(form.getColumnId());
		}
		addCriteria(form, criteria, criteria2);
		example.or(criteria2);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(form.getOrderByViewNum())) {
			example.setOrderByClause("VIEW_NUM DESC nulls last, IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}else{
			example.setOrderByClause("IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(form.getIsWithBlobs())) {
			return this.inxInfoMapper.selectByExampleWithBLOBs(example);
		}
		return this.inxInfoMapper.selectByExample(example);
	}

	@Override
	public int modifyInxInfo(InxInfo info) {
		if(StringUtil.isNotBlank(info.getInfoFlow())){
			GeneralMethod.setRecordInfo(info, false);
			return inxInfoMapper.updateByPrimaryKeySelective(info);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
}
