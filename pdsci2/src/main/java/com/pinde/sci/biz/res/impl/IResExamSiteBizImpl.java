package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResExamSiteBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResExamSiteMapper;
import com.pinde.sci.model.mo.ResExamSite;
import com.pinde.sci.model.mo.ResExamSiteExample;
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
public class IResExamSiteBizImpl implements IResExamSiteBiz{
	@Autowired
	private ResExamSiteMapper examSiteMapper;

//	@Override
//	public int saveExamSiteList(List<ResExamSite> examSiteList) {
//		int count = 0;
//		if(examSiteList != null && !examSiteList.isEmpty()){
//			for(ResExamSite es : examSiteList){
//				int result = saveExamSite(es);
//				if(com.pinde.core.common.GlobalConstant.ZERO_LINE != result){
//					count ++;
//				}
//			}
//			return count;
//		}
//		return com.pinde.core.common.GlobalConstant.ZERO_LINE;
//	}

	@Override
	public int saveExamSite(ResExamSite examSite) {
		if(StringUtil.isBlank(examSite.getRecordFlow())){
			examSite.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(examSite, true);
			return examSiteMapper.insert(examSite);
		}else{
			GeneralMethod.setRecordInfo(examSite, false);
			return examSiteMapper.updateByPrimaryKeySelective(examSite);
		}
	}

	@Override
	public List<ResExamSite> searchExamSiteList(ResExamSite examSite) {
		ResExamSiteExample example = new ResExamSiteExample();
		com.pinde.sci.model.mo.ResExamSiteExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(examSite.getExamFlow())){
			criteria.andExamFlowEqualTo(examSite.getExamFlow());
		}
		if(StringUtil.isNotBlank(examSite.getSiteCode())){
			criteria.andSiteCodeEqualTo(examSite.getSiteCode());
		}
		if(StringUtil.isNotBlank(examSite.getRecordStatus())){
			criteria.andRecordStatusEqualTo(examSite.getRecordStatus());
		}
		example.setOrderByClause("SITE_CODE");
		return examSiteMapper.selectByExample(example);
	}

//	@Override
//	public ResExamSite getExamSite(String examFlow, String siteCode) {
//		ResExamSiteExample example = new ResExamSiteExample();
//		example.createCriteria().andExamFlowEqualTo(examFlow).andSiteCodeEqualTo(siteCode).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		List<ResExamSite> resExamSiteList= examSiteMapper.selectByExample(example);
//		if(resExamSiteList != null && !resExamSiteList.isEmpty()){
//			return resExamSiteList.get(0);
//		}
//		return null;
//	}

	@Override
	public ResExamSite readExamSite(String recordFlow) {
		return examSiteMapper.selectByPrimaryKey(recordFlow);
	}
	
} 
 