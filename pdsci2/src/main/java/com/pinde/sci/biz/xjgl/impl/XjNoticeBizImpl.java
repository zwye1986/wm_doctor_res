package com.pinde.sci.biz.xjgl.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.xjgl.IXjNoticeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduTeachingnoticeMapper;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.edu.EduCourseNoticeExtMapper;
import com.pinde.sci.dao.inx.InxInfoExtMapper;
import com.pinde.sci.dao.xjgl.XjEduNoticeExtMapper;
import com.pinde.sci.enums.inx.InfoStatusEnum;
import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.EduTeachingnoticeExample;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.InxInfoExample;
import com.pinde.sci.model.mo.InxInfoExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjNoticeBizImpl implements IXjNoticeBiz {

	@Autowired
	private EduTeachingnoticeMapper inxInfoMapper;
	@Autowired
	private XjEduNoticeExtMapper eduNoticeExtMapper;
	
	@Override
	public int editNotice(EduTeachingnotice teachingnotice) {
		if(StringUtil.isBlank(teachingnotice.getInfoFlow())){
			teachingnotice.setInfoFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(teachingnotice, true);
			return this.inxInfoMapper.insert(teachingnotice);
		}else{
			GeneralMethod.setRecordInfo(teachingnotice, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(teachingnotice);
		}
	}

	@Override
	public List<EduTeachingnotice> findNotice(EduTeachingnotice teachingnotice) {
		EduTeachingnoticeExample example = new EduTeachingnoticeExample();
		example.setOrderByClause("CREATE_TIME DESC");
		EduTeachingnoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public EduTeachingnotice findNoticByFlow(String flow) {
		return this.inxInfoMapper.selectByPrimaryKey(flow);
	}

	@Override
	public int delNotice(String flow) {
		EduTeachingnotice record = new EduTeachingnotice();
		record.setInfoFlow(flow);
		record.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(record, false);
		return this.inxInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<EduTeachingnotice> searchAllNotice(){
		EduTeachingnoticeExample example = new EduTeachingnoticeExample();
		EduTeachingnoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME DESC");
		return inxInfoMapper.selectByExample(example);
	}

	@Override
	public List<EduTeachingnotice> searchByRoles(String userFlow) {
		return eduNoticeExtMapper.searchByRoles(userFlow);
	}
}
