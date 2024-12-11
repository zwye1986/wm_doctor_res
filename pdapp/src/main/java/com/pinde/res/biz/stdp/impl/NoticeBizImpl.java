package com.pinde.res.biz.stdp.impl;


import com.pinde.res.biz.stdp.INoticeBiz;
import com.pinde.res.dao.lcjn.ext.InxInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class NoticeBizImpl implements INoticeBiz {
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;


	@Override
	public List<Map<String,String>> searchInfoByOrgBeforeDate(String orgFlow, String date, String resNoticeTypeId, String resSysId, String userFlow,String sessionNumber){
		return inxInfoExtMapper.searchInfoByOrgBeforeDate(orgFlow,date,resNoticeTypeId,resSysId,userFlow,sessionNumber);
	}
	@Override
	public List<Map<String,String>> searchInfoByOrgNotRead(String orgFlow,  String resNoticeTypeId, String resSysId, String userFlow){
		return inxInfoExtMapper.searchInfoByOrgNotRead(orgFlow,resNoticeTypeId,resSysId,userFlow);
	}

}
