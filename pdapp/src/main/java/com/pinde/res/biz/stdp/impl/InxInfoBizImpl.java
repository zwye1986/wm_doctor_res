package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IInxInfoBiz;
import com.pinde.res.enums.stdp.InfoStatusEnum;
import com.pinde.res.model.stdp.mo.InxInfoForm;
import com.pinde.sci.dao.base.InxColumnMapper;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.base.ResReadInfoMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.InxInfoExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class InxInfoBizImpl implements IInxInfoBiz {
	@Autowired
	private InxColumnMapper inxColumnMapper;
	@Autowired
	private InxInfoMapper inxInfoMapper;
	@Autowired
	private ResReadInfoMapper resReadInfoMapper;

	
	@Override
	public InxInfo getByInfoFlow(String infoFlow) {
		return inxInfoMapper.selectByPrimaryKey(infoFlow);
	}


	@Override
	public List<InxInfo> getList(InxInfoForm form) {
		InxInfoExample example = new InxInfoExample();
		Criteria criteria = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		Criteria criteria2 = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
	    if(GlobalConstant.FLAG_Y.equals(form.getOrderByViewNum())){
			example.setOrderByClause("VIEW_NUM DESC nulls last, IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}else{
			example.setOrderByClause("IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}
		if(GlobalConstant.FLAG_Y.equals(form.getIsWithBlobs())){
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
		if(GlobalConstant.FLAG_Y.equals(form.getHasImage())){
			criteria.andTitleImgIsNotNull();
			criteria2.andTitleImgIsNotNull();
		}
	}

	@Override
	public List<InxInfo> queryClassifyList(InxInfoForm form) {
		InxInfoExample example = new InxInfoExample();
		Criteria criteria = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		Criteria criteria2 = example.createCriteria().andInfoStatusIdEqualTo(InfoStatusEnum.Passed.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(form.getColumnId())){
			criteria.andColumnIdEqualTo(form.getColumnId());
			criteria2.andColumnIdEqualTo(form.getColumnId());
		}
		addCriteria(form, criteria, criteria2);
		example.or(criteria2);
		if(GlobalConstant.FLAG_Y.equals(form.getOrderByViewNum())){
			example.setOrderByClause("VIEW_NUM DESC nulls last, IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}else{
			example.setOrderByClause("IS_TOP DESC nulls last, INFO_TIME DESC, CREATE_TIME DESC");
		}
		if(GlobalConstant.FLAG_Y.equals(form.getIsWithBlobs())){
			return this.inxInfoMapper.selectByExampleWithBLOBs(example);
		}
		return this.inxInfoMapper.selectByExample(example);
	}

	@Override
	public ResReadInfo getReadInfoByFlow(String infoFlow, String userFlow) {
		ResReadInfoExample example=new ResReadInfoExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andInfoFlowEqualTo(infoFlow).andUserFlowEqualTo(userFlow);
		List<ResReadInfo> list=resReadInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveReadInfo(String userFlow, ResReadInfo d) {
		if(StringUtil.isNotBlank(d.getRecordFlow())){
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			return resReadInfoMapper.updateByPrimaryKeySelective(d);
		}else {
			d.setRecordFlow(PkUtil.getUUID());
			d.setCreateTime(DateUtil.getCurrDateTime());
			d.setCreateUserFlow(userFlow);
			d.setModifyTime(DateUtil.getCurrDateTime());
			d.setModifyUserFlow(userFlow);
			d.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return resReadInfoMapper.insertSelective(d);
		}
	}
}
