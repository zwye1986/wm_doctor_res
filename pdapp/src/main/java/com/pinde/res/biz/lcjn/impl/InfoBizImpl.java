package com.pinde.res.biz.lcjn.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.lcjn.IInfoBiz;
import com.pinde.res.dao.jswjw.ext.SysUserExtMapper;
import com.pinde.res.dao.lcjn.ext.InxInfoExtMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.LcjnDoctorReadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class InfoBizImpl implements IInfoBiz {
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserExtMapper sysUserExtMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;
	@Autowired
	private InxInfoMapper inxInfoMapper;

	@Autowired
	private LcjnDoctorReadInfoMapper lcjnReadInfoMapper;

	@Override
	public List<InxInfo> getDocNoReadInfos(String userFlow) {
		return inxInfoExtMapper.getDocNoReadInfos(userFlow);
	}

	@Override
	public int saveDocNoReadInfos(String userFlow) {
		List<InxInfo> infos=getDocNoReadInfos(userFlow);
		if(infos!=null&&infos.size()>0)
		{	int count=0;
			for(InxInfo info:infos)
			{
				LcjnDoctorReadInfo r=new LcjnDoctorReadInfo();
				r.setDoctorFlow(userFlow);
				r.setInfoFlow(info.getInfoFlow());
				count+=saveReadInfo(r);
			}
		}
		return 0;
	}

	@Override
	public List<InxInfo> getInfos(String userFlow) {
		return inxInfoExtMapper.getInfos(userFlow);
	}

	@Override
	public InxInfo readNoticeByFlow(String infoFlow) {
		return inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

	private int saveReadInfo(LcjnDoctorReadInfo r) {

		String userFlow= r.getDoctorFlow();
		if(StringUtil.isNotBlank(r.getRecordFlow()))
		{
			r.setModifyTime(DateUtil.getCurrDateTime());
			r.setModifyUserFlow(userFlow);
			return lcjnReadInfoMapper.updateByPrimaryKeySelective(r);
		}else{
			String recordFlow= PkUtil.getUUID();
			r.setRecordFlow(recordFlow);
			r.setCreateTime(DateUtil.getCurrDateTime());
			r.setCreateUserFlow(userFlow);
			r.setModifyTime(DateUtil.getCurrDateTime());
			r.setModifyUserFlow(userFlow);
			r.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return lcjnReadInfoMapper.insertSelective(r);
		}

	}
}
  