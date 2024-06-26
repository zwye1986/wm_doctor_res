package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IProjSearchBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.pub.PubProjExtMapper;
import com.pinde.sci.enums.srm.ProjApplyStatusEnum;
import com.pinde.sci.enums.srm.ProjScheduleStatusEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.PubProjExample;
import com.pinde.sci.model.mo.PubProjExample.Criteria;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProjSearchBizImpl implements IProjSearchBiz{


	@Autowired
	private PubProjMapper pubProjMapper;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private PubProjExtMapper pubProjExtMapper;
	@Override
	public List<PubProj> searchProj(PubProj proj,List<SysOrg> orgList) {
		
		PubProjExample example  = new PubProjExample();
		example.setOrderByClause("CREATE_TIME DESC");
		Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		
		String projCateScope = (String)GlobalContext.getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE);
		criteria.andProjCategoryIdEqualTo(projCateScope);
		addCriteria(proj, criteria);
	    List<PubProj> projList=pubProjMapper.selectByExample(example);
		//查询在列表中的机构下的项目
	    List<String> orgFlowList=null;
	    List<PubProj> needRemoveList=new ArrayList<PubProj>();
		if(null!=orgList && !orgList.isEmpty()){
			orgFlowList=new ArrayList<String>();
			for(SysOrg org:orgList){
				orgFlowList.add(org.getOrgFlow());
			}
			//过滤掉申报机构流水号不在orgFlowList中的项目
	        if(null!=projList && !projList.isEmpty()){
	        	for(PubProj project:projList){
	        		if(!orgFlowList.contains(project.getApplyOrgFlow())){
	        		   needRemoveList.add(project);
	        		}
	        	}
	        }
		}
		
        projList.removeAll(needRemoveList);
        return projList;
		
	}

	@Override
	public List<PubProj> searchProj(PubProj proj) {
		PubProjExample example  = new PubProjExample();
		example.setOrderByClause("CREATE_TIME DESC");
		Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		addCriteria(proj, criteria);
	    List<PubProj> projList=pubProjMapper.selectByExample(example);
	    return projList;
	}

	@Override
	public List<PubProj> searchProj(PubProj proj, List<String> stageIdNotInList, List<String> statusIdInList,String startYear,String endYear) {
		PubProjExample example  = new PubProjExample();
		example.setOrderByClause("CREATE_TIME DESC");
		Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(stageIdNotInList != null && stageIdNotInList.size()>0){
			criteria.andProjStageIdNotIn(stageIdNotInList);
		}
		if(statusIdInList != null && statusIdInList.size()>0){
			criteria.andProjStatusIdIn(statusIdInList);
		}
		if(StringUtil.isNotBlank(startYear)){
			criteria.andProjYearGreaterThanOrEqualTo(startYear);
		}
		if(StringUtil.isNotBlank(endYear)){
			criteria.andProjYearLessThanOrEqualTo(endYear);
		}
		addCriteria(proj, criteria);
		List<PubProj> projList=pubProjMapper.selectByExample(example);
		return projList;
	}


	@Override
    public List<PubProj> searchProjWithBLOBs(PubProj proj) {
        PubProjExample example  = new PubProjExample();
        example.setOrderByClause("CREATE_TIME DESC");
        Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        addCriteria(proj, criteria);
        List<PubProj> projList=pubProjMapper.selectByExampleWithBLOBs(example);
        return projList;
    }

    @Override
	public List<PubProj> searchProjByprojListScope(PubProj proj,String projListScope) {
		Map<String,Object> map=new HashMap<String,Object>();
		//当不是本机构时，排除项目阶段为申报阶段，并且项目状态为申报填写的数据
		if(!GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope))
		{
			map.put("sql","AND (PROJ_STATUS_ID <> '"+ProjApplyStatusEnum.Apply.getId()+"' OR PROJ_STAGE_ID <> '"+ProjStageEnum.Apply.getId()+"')");
		 }
		map.put("proj",proj);
		List<PubProj> projList=pubProjExtMapper.selectProjListByMap(map);
		return projList;
	}

	@Override
	public List<PubProj> searchPassApplyProj(PubProj pubProj,String startYear,String endYear){
		PubProjExample example = new PubProjExample();
		example.setOrderByClause("CREATE_TIME DESC");
		Criteria criteria =
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjStageIdNotEqualTo(ProjStageEnum.Apply.getId());
		addCriteria(pubProj, criteria);
		if(StringUtil.isNotBlank(startYear)){
			criteria.andProjYearGreaterThanOrEqualTo(startYear);
		}
		if(StringUtil.isNotBlank(endYear)){
			criteria.andProjYearLessThanOrEqualTo(endYear);
		}
		List<PubProj> projList=pubProjMapper.selectByExample(example);
		return projList;
	}

	private void addCriteria(PubProj proj, Criteria criteria) {
		if(StringUtil.isNotBlank(proj.getProjCategoryId())){
			criteria.andProjCategoryIdEqualTo(proj.getProjCategoryId());	
		}
		if(StringUtil.isNotBlank(proj.getProjName())){
			criteria.andProjNameLike("%"+proj.getProjName()+"%");
		}
		if(StringUtil.isNotBlank(proj.getProjNo())){
			criteria.andProjNoLike("%"+proj.getProjNo()+"%");
		}
		if(StringUtil.isNotBlank(proj.getAccountNo())){
			criteria.andAccountNoLike("%"+proj.getAccountNo()+"%");
		}
		/*if(StringUtil.isNotBlank((proj.getProjNo()))){
			criteria.andProjNoEqualTo(proj.getProjNo());
		}*/
		if(StringUtil.isNotBlank((proj.getApplyDeptFlow()))){
			criteria.andApplyDeptFlowEqualTo(proj.getApplyDeptFlow());
		}
		if(StringUtil.isNotBlank((proj.getProjYear()))){
			criteria.andProjYearEqualTo(proj.getProjYear());
		}
		if(StringUtil.isNotBlank(proj.getProjTypeId())){
			criteria.andProjTypeIdEqualTo(proj.getProjTypeId());
		}
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			criteria.andApplyOrgFlowEqualTo(proj.getApplyOrgFlow());
		}
		if(StringUtil.isNotBlank(proj.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(proj.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(proj.getProjStageId())){
			criteria.andProjStageIdEqualTo(proj.getProjStageId());
		}
		if(StringUtil.isNotBlank(proj.getProjStatusId())){
			criteria.andProjStatusIdEqualTo(proj.getProjStatusId());
		}
        if(StringUtil.isNotBlank(proj.getSubjName())){
			criteria.andSubjNameLike("%"+proj.getSubjName()+"%");
		}
        if(StringUtil.isNotBlank(proj.getSubjId())){
            criteria.andSubjIdEqualTo(proj.getSubjId());
        }
        if(StringUtil.isNotBlank(proj.getApplyUserName())){
            criteria.andApplyUserNameLike("%"+proj.getApplyUserName()+"%");
        }
		if(StringUtil.isNotBlank(proj.getProjDeclarerFlow())){
			criteria.andProjDeclarerFlowEqualTo(proj.getProjDeclarerFlow());
		}
		if(StringUtil.isNotBlank(proj.getProjSecondSourceId())){
			criteria.andProjSecondSourceIdEqualTo(proj.getProjSecondSourceId());
		}
	}
}
