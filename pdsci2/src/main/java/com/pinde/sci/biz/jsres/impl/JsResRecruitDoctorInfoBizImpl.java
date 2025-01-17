package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.common.sci.dao.JsresRecruitDocInfoMapper;
import com.pinde.core.common.sci.dao.JsresRecruitInfoMapper;
import com.pinde.core.common.sci.dao.ResDoctorRecruitMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResRecruitDoctorInfoBiz;
import com.pinde.core.common.sci.dao.ResRecruitHistoryMapper;
import com.pinde.sci.dao.jsres.JsResDoctorRecruitExtMapper;
import com.pinde.sci.dao.jsres.JsResRecruitDoctorInfoExtMapper;
import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.core.model.JsRecruitDocInfoExt;
import com.pinde.core.model.JsResDoctorRecruitExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResRecruitDoctorInfoBizImpl implements IJsResRecruitDoctorInfoBiz {
	@Autowired
	private JsResRecruitDoctorInfoExtMapper doctorRecruitInfoExtMapper;
	@Autowired
	private JsresRecruitDocInfoMapper recruitDocInfoMapper;
	@Autowired
	private JsresRecruitInfoMapper recruitInfoMapper;
	@Autowired
	private ResDoctorRecruitMapper resDoctorRecruitMapper;
	@Autowired
	private JsResDoctorRecruitExtMapper doctorRecruitExtMapper;
	@Autowired
	private ResRecruitHistoryMapper recruitHistoryMapper;

	@Override
	public List<Map<String, Object>> searchRecruitDoctorInfos(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.searchRecruitDoctorInfos(param);
	}

	@Override
	public List<Map<String, Object>> zltjCityDoctorType(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjCityDoctorType(param);
	}
	@Override
	public List<Map<String, Object>> zltjCitySpeType(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjCitySpeType(param);
	}

	@Override
	public List<Map<String, Object>> zltjOrgDoctorType(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjOrgDoctorType(param);
	}

	@Override
	public List<Map<String, Object>> zltjOrgSpeType(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjOrgSpeType(param);
	}


	@Override
	public List<Map<String, Object>> zltjOrgLocalList(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjOrgLocalList(param);
	}

	@Override
	public JsresRecruitDocInfo readRecruit(String recruitFlow) {
		return recruitDocInfoMapper.selectByPrimaryKey(recruitFlow);
	}

	@Override
	public JsresRecruitInfo getRecruitInfo( String recruitFlow) {
		return recruitInfoMapper.selectByPrimaryKey(recruitFlow);
	}

	@Override
	public List<Map<String, Object>> cityOrgListByOrgFlow(List<String> jointOrgFlowList) {
		return doctorRecruitInfoExtMapper.cityOrgListByOrgFlow(jointOrgFlowList);
	}

	@Override
	public List<Map<String, Object>> zltjOrgCityDoctorType(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjOrgCityDoctorType(param);
	}

	@Override
	public JsresRecruitInfo getRecruitInfoBysessionNumber(String userFlow, String sessionNumber) {
		JsresRecruitInfoExample example=new JsresRecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(userFlow).andSessionNumberEqualTo(sessionNumber);
		List<JsresRecruitInfo> list=recruitInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return  list.get(0);
		}
		return null;
	}


	@Override
	public List<Map<String, Object>> orgListByOrgFlow(List<String> jointOrgFlowList) {
		if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0) {
			return doctorRecruitInfoExtMapper.orgListByOrgFlow(jointOrgFlowList);
		}
		return null;
	}

	@Override
	public List<JsRecruitDocInfoExt> searchRecruitDoctorInfosByResume(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.searchRecruitDoctorInfosByResume(param);
	}

    /**
     * @param orgFlow
     * @Author shengl
     * @Description // 查询录取学员数据
     * @Date 2020-08-28
     * @Param [orgFlow]
     */
    @Override
    public List<Map<String,Object>> getRecruitSpeInfo(String orgFlow,String sessionNumber,String flag) {
        return doctorRecruitInfoExtMapper.getRecruitSpeInfo(orgFlow,sessionNumber,flag);
    }

	@Override
	public List<Map<String,Object>> getRecruitSpeInfoNew(Map<String,Object> map) {
		return doctorRecruitInfoExtMapper.getRecruitSpeInfoNew(map);
	}

	@Override
	public List<Map<String, Object>> getRecruitSpeInfoNewAcc(Map<String, Object> map) {
		return doctorRecruitInfoExtMapper.getRecruitSpeInfoNewAcc(map);
	}

	@Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchRecruitList(String doctorFlow) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		return resDoctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume2(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.searchRecruitDoctorInfosByResume2(param);
	}

	@Override
	public List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume3(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.searchRecruitDoctorInfosByResume3(param);
	}

	@Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchRecruitListNew(String doctorFlow) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andOrgAuditEqualTo("Passed")
				.andJointOrgAuditEqualTo("Passed");
		example.setOrderByClause("CREATE_TIME DESC");
		return resDoctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<JsResDoctorRecruitExt> searchRecruitExtList(Map<String, Object> param) {
		return doctorRecruitExtMapper.searchRecruitExtList(param);
	}

	@Override
	public List<Map<String, Object>> zltjOrgLocalListNew(Map<String, Object> param) {
		return doctorRecruitInfoExtMapper.zltjOrgLocalListNew(param);
	}

	@Override
	public List<Map<String, Object>> getOrgRecruitSpeInfo(Map<String, Object> params) {
		return doctorRecruitInfoExtMapper.getOrgRecruitSpeInfo(params);
	}

	@Override
	public List<ResRecruitHistory> getRecruitHistoryInfo(String photoTime, String orgFlow, String speId) {
		ResRecruitHistoryExample recruitHistoryExample = new ResRecruitHistoryExample();
        ResRecruitHistoryExample.Criteria criteria = recruitHistoryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(photoTime)) {
			criteria.andPhotoTimeEqualTo(photoTime);
		}
		if (StringUtil.isNotBlank(orgFlow)) {
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if (StringUtil.isNotBlank(speId)) {
			criteria.andSpeIdEqualTo(speId);
		}
		return recruitHistoryMapper.selectByExample(recruitHistoryExample);
	}

	@Override
	public List<String> getHistoryPhotoTimeList() {
		return doctorRecruitInfoExtMapper.getHistoryPhotoTimeList();
	}

	@Override
	public List<ResRecruitHistory> getRecruitHistoryBySessionNumber(String sessionNumber) {
		ResRecruitHistoryExample recruitHistoryExample = new ResRecruitHistoryExample();
        ResRecruitHistoryExample.Criteria criteria = recruitHistoryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andPhotoTimeLike(sessionNumber);
		return recruitHistoryMapper.selectByExample(recruitHistoryExample);
	}

	@Override
	public void saveRecruitHistory(ResRecruitHistory history) {
		recruitHistoryMapper.insert(history);
	}

	@Override
	public void updateRecruitHistory(ResRecruitHistory history) {
		recruitHistoryMapper.updateByPrimaryKeySelective(history);
	}

}
