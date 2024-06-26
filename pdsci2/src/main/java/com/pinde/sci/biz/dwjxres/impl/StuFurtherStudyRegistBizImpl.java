package com.pinde.sci.biz.dwjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IStuFurtherStudyRegistBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.StuFurtherStudyRegistMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor=Exception.class)
public class StuFurtherStudyRegistBizImpl implements IStuFurtherStudyRegistBiz {

	@Autowired
	private StuFurtherStudyRegistMapper furtherStudyRegistMapper;
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public int saveRegist(StuFurtherStudyRegist stuFurtherStudyRegist) {
		String recordFlow = stuFurtherStudyRegist.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			GeneralMethod.setRecordInfo(stuFurtherStudyRegist, false);
			int ret = furtherStudyRegistMapper.updateByPrimaryKeySelective(stuFurtherStudyRegist);
			return ret;
		}else{
			stuFurtherStudyRegist.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(stuFurtherStudyRegist, true);
			int ret = furtherStudyRegistMapper.insert(stuFurtherStudyRegist);
			return ret;
		}
	}

	@Override
	public List<StuFurtherStudyRegist> searchRegist(StuFurtherStudyRegist stuFurtherStudyRegist) {
		StuFurtherStudyRegistExample example=new StuFurtherStudyRegistExample();
		StuFurtherStudyRegistExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(stuFurtherStudyRegist.getDeptId())) {
			criteria.andDeptIdEqualTo(stuFurtherStudyRegist.getDeptId());
		}
		if (StringUtil.isNotBlank(stuFurtherStudyRegist.getDeptName())) {
			criteria.andDeptNameLike("%"+stuFurtherStudyRegist.getDeptName()+"%");
		}
		if (StringUtil.isNotBlank(stuFurtherStudyRegist.getUserName())) {
			criteria.andUserNameLike("%"+stuFurtherStudyRegist.getUserName()+"%");
		}
		example.setOrderByClause("dept_id desc");
		return furtherStudyRegistMapper.selectByExample(example);
	}

	@Override
	public StuFurtherStudyRegist readRegist(String recordFlow) {
		if (StringUtil.isNotBlank(recordFlow)) {
			return furtherStudyRegistMapper.selectByPrimaryKey(recordFlow);
		}else {
			return null;
		}
	}

	@Override
	public List<SysUser> readUserByEqDeptNameAndUserName(String userName, String deptName) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(userName)) {
			criteria.andUserNameEqualTo(userName);
		}
		if (StringUtil.isNotBlank(deptName)) {
			criteria.andDeptNameEqualTo(deptName);
		}
		return userMapper.selectByExample(example);
	}
}
