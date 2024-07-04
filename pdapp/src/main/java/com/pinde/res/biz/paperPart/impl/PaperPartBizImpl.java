package com.pinde.res.biz.paperPart.impl;


import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.paperPart.IPaperPartBiz;
import com.pinde.res.model.jswjw.mo.OscaSkillRoomExt;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class PaperPartBizImpl implements IPaperPartBiz {

	@Autowired
	private  JsresDoctorPaperMapper paperMapper;
	@Autowired
	private  JsresDoctorParticipationMapper partMapper;

	@Override
	public int editJsresDoctorPaper(JsresDoctorPaper paper, SysUser user) {
		if(paper!=null){
			if(StringUtil.isNotBlank(paper.getRecordFlow())){//修改
				paper.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				paper.setModifyUserFlow(user.getUserFlow());
				paper.setModifyTime(DateUtil.getCurrDateTime());
				return this.paperMapper.updateByPrimaryKeySelective(paper);
			}else{//新增
				paper.setRecordFlow(PkUtil.getUUID());
				paper.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				paper.setCreateUserFlow(user.getUserFlow());
				paper.setCreateTime(DateUtil.getCurrDateTime());
				paper.setModifyUserFlow(user.getUserFlow());
				paper.setModifyTime(DateUtil.getCurrDateTime());
				return this.paperMapper.insertSelective(paper);
			}
		}
		return 0;
	}

	@Override
	public int editJsresDoctorPart(JsresDoctorParticipation part, SysUser user) {
		if(part!=null){
			if(StringUtil.isNotBlank(part.getRecordFlow())){//修改
				part.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				part.setModifyUserFlow(user.getUserFlow());
				part.setModifyTime(DateUtil.getCurrDateTime());
				return this.partMapper.updateByPrimaryKeySelective(part);
			}else{//新增
				part.setRecordFlow(PkUtil.getUUID());
				part.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				part.setCreateUserFlow(user.getUserFlow());
				part.setCreateTime(DateUtil.getCurrDateTime());
				part.setModifyUserFlow(user.getUserFlow());
				part.setModifyTime(DateUtil.getCurrDateTime());
				return this.partMapper.insertSelective(part);
			}
		}
		return 0;
	}

	@Override
	public JsresDoctorPaper readJsresDoctorPaperByFlow(String recordFlow) {
		return paperMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public JsresDoctorParticipation readJsresDoctorJsresDoctorParticipationByFlow(String recordFlow) {
		return partMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<JsresDoctorPaper> readJsresDoctorPaperByDoctorFlow(String userFlow) {
		JsresDoctorPaperExample example=new JsresDoctorPaperExample();
		JsresDoctorPaperExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("create_time desc");
		return paperMapper.selectByExample(example);
	}

	@Override
	public List<JsresDoctorParticipation> readJsresDoctorJsresDoctorParticipationByDoctorFlow(String userFlow) {
		JsresDoctorParticipationExample example=new JsresDoctorParticipationExample();
		JsresDoctorParticipationExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("create_time desc");
		return partMapper.selectByExample(example);
	}

	@Override
	public void deleteJsresDoctorPaperByFlow(String recordFlow) {
		paperMapper.deleteByPrimaryKey(recordFlow);
	}

	@Override
	public void deleteJsresDoctorParticipationByFlow(String recordFlow) {
		partMapper.deleteByPrimaryKey(recordFlow);
	}

}
  