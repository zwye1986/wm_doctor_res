package com.pinde.res.biz.jswjw.impl;


import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwAdminBiz;
import com.pinde.res.biz.stdp.IResErrorSchNoticeBiz;
import com.pinde.res.dao.jswjw.ext.*;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.core.commom.enums.NoticeStatusEnum;
import com.pinde.res.model.jswjw.mo.JsResDoctorOrgHistoryExt;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class JswjwAdminBizImpl implements IJswjwAdminBiz {

	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private ResRecMapper recMapper;
	@Resource
	private ResDoctorMapper doctorMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Resource
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	private ResLectureScanRegistMapper lectureScanRegistMapper;
	@Autowired
	private ResLectureInfoMapper lectureInfoMapper;
	@Autowired
	private ResLectureEvaDetailMapper lectureEvaDetailMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private IResErrorSchNoticeBiz noticeBiz;
	@Autowired
	private ResDocotrDelayTeturnMapper resDocotrDelayTeturnMapper;
	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;

	@Override
	public List<Map<String, String>> schDeptDoctorSchProcess(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.schDeptDoctorSchProcess(schArrangeResultMap);
	}

	@Override
	public List<SysDept> getErrorSchDepts(String orgFlow) {
		return sysDeptExtMapper.getErrorSchDepts(orgFlow);
	}

	@Override
	public void sendErrorSchNotice(List<String> doctorFlows, String content,SysUser user) {
		String currDate= DateUtil.getCurrDate();
		for(String doctorFlow:doctorFlows)
		{
			ResErrorSchNotice notice=new ResErrorSchNotice();
			notice.setStatusId(NoticeStatusEnum.NoRead.getId());
			notice.setStatusName(NoticeStatusEnum.NoRead.getName());
			notice.setNoticeContent(content);
			notice.setNoticeTime(currDate);
			SysUser doctor=sysUserMapper.selectByPrimaryKey(doctorFlow);
			if(doctor!=null)
			{
				notice.setUserFlow(doctorFlow);
				notice.setUserName(doctor.getUserName());
			}
			noticeBiz.edit(notice,user);
		}
	}

	@Override
	public List<JsResDoctorOrgHistoryExt> getSpeChangeList(Map<String,Object> param) {
		if(StringUtil.isNotBlank((String) param.get("orgFlow")))
		{
			return  resDoctorProcessExtMapper.getSpeChangeList(param);
		}
		return null;
	}

	@Override
	public List<ResDocotrDelayTeturn> getOrgDelayReturnInfo(Map<String, Object> param,List<String> flags) {

		ResDocotrDelayTeturnExample example = new ResDocotrDelayTeturnExample();
		ResDocotrDelayTeturnExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank((String) param.get("orgFlow"))){
			criteria.andOrgFlowEqualTo((String) param.get("orgFlow"));
		}else{
			return null;
		}
		if(flags!=null&&flags.size()>0){
			criteria.andAuditStatusIdIn(flags);
		}
		if(StringUtil.isNotBlank((String) param.get("typeId"))){
			criteria.andTypeIdEqualTo((String) param.get("typeId"));
		}
		if(StringUtil.isNotBlank((String) param.get("sessionNumber"))){
			criteria.andSessionNumberEqualTo((String) param.get("sessionNumber"));
		}
		example.setOrderByClause("create_time desc");
		return resDocotrDelayTeturnMapper.selectByExample(example);
	}

	@Override
	public List<ResOrgSpe> getOrgSpes(String orgFlow) {
		if(StringUtil.isNotBlank(orgFlow))
		{

			ResOrgSpeExample example=new ResOrgSpeExample();
			ResOrgSpeExample.Criteria criteria =example.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

			if (StringUtil.isNotBlank(orgFlow)) {
				criteria.andOrgFlowEqualTo(orgFlow);
			}

			List<String>speTypeIdList=new ArrayList<String>();
			speTypeIdList.add(DictTypeEnum.DoctorTrainingSpe.getId());
			speTypeIdList.add(DictTypeEnum.AssiGeneral.getId());
			criteria.andSpeTypeIdIn(speTypeIdList);
			example.setOrderByClause("SPE_TYPE_ID DESC,CREATE_TIME DESC");
			return resOrgSpeMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getOrgSpeDocNum(String orgFlow,String sessionNumber) {
		return resDoctorProcessExtMapper.getOrgSpeDocNum(orgFlow,sessionNumber);
	}

	@Override
	public int getOrgDocCount(String orgFlow, String sessionNumber, String statusId) {
		return resDoctorProcessExtMapper.getOrgDocCount(orgFlow,sessionNumber,statusId);
	}

	@Override
	public List<Map<String, String>> getOrgDocList(Map<String, Object> param) {
		return resDoctorProcessExtMapper.getOrgDocList(param);
	}
}
  