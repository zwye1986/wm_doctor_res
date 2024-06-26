package com.pinde.sci.biz.srm.impl;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.IProjProcessBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PubProjAuthorMapper;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.pub.PubProjExtMapper;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.ReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor=Exception.class)
public class PubProjBizImpl implements IPubProjBiz{

	@Autowired
	private PubProjExtMapper pubProjExtMapper;
	@Autowired
	private PubProjMapper projMapper;
	@Autowired
	private IFileBiz fileBiz;
    @Autowired
    private IProjProcessBiz processBiz;
	@Autowired
	private PubProjAuthorMapper projAuthorMapper;
	
	@Override
	public void modProject(PubProj proj) {
		GeneralMethod.setRecordInfo(proj, false);
		this.projMapper.updateByPrimaryKeySelective(proj);

	}

	@Override
	public void modProjAuthor(PubProjAuthor projAuthor) {
		if(StringUtil.isBlank(projAuthor.getAuthorFlow())) {
			GeneralMethod.setRecordInfo(projAuthor, true);
			projAuthor.setAuthorFlow(PkUtil.getUUID());
			projAuthorMapper.insertSelective(projAuthor);
		}else{
			GeneralMethod.setRecordInfo(projAuthor, false);
			projAuthorMapper.updateByPrimaryKeySelective(projAuthor);
		}
	}

	@Override
	public PubProjAuthor readProjAuthor(PubProjAuthor projAuthor) {
		PubProjAuthorExample example = new PubProjAuthorExample();
		PubProjAuthorExample.Criteria criteria = example.createCriteria();
		criteriaProjAuthor(criteria,projAuthor);
		List<PubProjAuthor> authorList = projAuthorMapper.selectByExample(example);
		if(authorList != null && authorList.size()>0){
			return authorList.get(0);
		}
		return null;
	}
	private void criteriaProjAuthor(PubProjAuthorExample.Criteria criteria ,PubProjAuthor projAuthor){
		if(StringUtil.isNotBlank(projAuthor.getAuthorFlow())){
			criteria.andAuthorFlowEqualTo(projAuthor.getAuthorFlow());
		}
		if(StringUtil.isNotBlank(projAuthor.getUserFlow())){
			criteria.andUserFlowEqualTo(projAuthor.getUserFlow());
		}
		if(StringUtil.isNotBlank(projAuthor.getProjFlow())){
			criteria.andProjFlowEqualTo(projAuthor.getProjFlow());
		}
		if(StringUtil.isNotBlank(projAuthor.getScoreFlow())){
			criteria.andScoreFlowEqualTo(projAuthor.getScoreFlow());
		}
	}
	@Override
	public PubProj readProject(String projFlow) {
		return projMapper.selectByPrimaryKey(projFlow);
	}
	
	@Override
	public PubProj readProjectNoBlogs(String projFlow) {
		PubProj proj = null;
		PubProjExample example = new PubProjExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowEqualTo(projFlow);
		List<PubProj> list = projMapper.selectByExample(example);
		if (list != null) {
			proj = list.get(0);
		}
		return proj;
	}

//	@Override
//	public List<PubProj> searchPubProjList(PubProj proj) {
//		return this.pubProjExtMapper.selectCommonProjList(proj);
//	}
	
	public List<PubProj> searchPubProjListForFundPlan(Map<Object , Object> paramMap){
		return this.pubProjExtMapper.selectProjListForFundPlan(paramMap);
	}

	@Override
	public List<PubProj> queryProjList(PubProj proj) {
		PubProjExample example = new PubProjExample();
		PubProjExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(proj.getProjName())){
			criteria.andProjNameLike("%"+ proj.getProjName() +"%");
		}
		if(StringUtil.isNotBlank(proj.getProjShortName())){
			criteria.andProjShortNameLike("%"+ proj.getProjShortName() +"%");
		}
		if(StringUtil.isNotBlank(proj.getProjNo())){
			criteria.andProjNoLike("%"+ proj.getProjNo() +"%");
		}
		if(StringUtil.isNotBlank(proj.getApplyUserFlow())){
			criteria.andApplyUserFlowEqualTo(proj.getApplyUserFlow());
		}
		if(StringUtil.isNotBlank(proj.getApplyUserName())){
			criteria.andApplyUserNameLike("%" + proj.getApplyUserName() + "%");
		}
		if(StringUtil.isNotBlank(proj.getApplyDeptName())){
			criteria.andApplyDeptNameLike("%" + proj.getApplyDeptName()+ "%");
		}
		
		if(StringUtil.isNotBlank(proj.getProjStageId())){//阶段
			List<String> projStageIdList = new ArrayList<String>();
			String[] projStageIds  = proj.getProjStageId().split(",");
			projStageIdList = Arrays.asList(projStageIds);
			criteria.andProjStageIdIn(projStageIdList);
		}
		if(StringUtil.isNotBlank(proj.getProjCategoryId())){//类别 
			List<String> projCategoryIdList = new ArrayList<String>();
			String[] projCategoryIds  = proj.getProjCategoryId().split(",");
			projCategoryIdList = Arrays.asList(projCategoryIds);
			criteria.andProjCategoryIdIn(projCategoryIdList);
		}
		if(StringUtil.isNotBlank(proj.getProjSubTypeId())){//期类别
			criteria.andProjSubTypeIdEqualTo(proj.getProjSubTypeId());
		}
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){//机构
			criteria.andApplyOrgFlowEqualTo(proj.getApplyOrgFlow());
		}
		if(StringUtil.isNotBlank(proj.getApplyDeptFlow())){//承担科室
			criteria.andApplyDeptFlowEqualTo(proj.getApplyDeptFlow());
		}
		if(StringUtil.isNotBlank(proj.getProjStatusId())){//状态
			criteria.andProjStatusIdEqualTo(proj.getProjStatusId());
		}
		if(StringUtil.isNotBlank(proj.getProjTypeId())){
			criteria.andProjTypeIdEqualTo(proj.getProjTypeId());
		}
        if(StringUtil.isNotBlank(proj.getProjYear())){
            criteria.andProjYearLike("%" + proj.getProjYear() + "%");
        }
        if(StringUtil.isNotBlank(proj.getBranchId())){
            criteria.andBranchIdEqualTo(proj.getBranchId());
        }
        if(StringUtil.isNotBlank(proj.getProjDeclarerFlow())){
            criteria.andProjDeclarerFlowEqualTo(proj.getProjDeclarerFlow());
        }
		example.setOrderByClause("CREATE_TIME DESC, PROJ_START_TIME DESC, PROJ_END_TIME");
		return projMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<PubProj> searchProjByProjFlows(List<String> projFlows) {
		return this.pubProjExtMapper.searchProjByProjFlows(projFlows);
	}

    @Override
    public List<PubProj> searchProjByProjFlowList(List<String> projFlows) {
        PubProjExample example = new PubProjExample();
        PubProjExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andProjFlowIn(projFlows);
        return projMapper.selectByExample(example);
    }

    @Override
	public List<String> getFileFlows(Map<String, Object> resultMap) {
		List<String> fileFlows = new ArrayList<String>();
		List<String> orderFileFlows = new ArrayList<String>();
		Object isFileObj = resultMap.get(GlobalConstant.IS_FILE);
		if(isFileObj instanceof String){
			if(StringUtil.isNotBlank(isFileObj.toString())){
			fileFlows.add(isFileObj.toString());
			}
		}else if(isFileObj instanceof List){
			for(String str:(List<String>)isFileObj){
				if(StringUtil.isNotBlank(str)){
					fileFlows.add(str);
				}
			}
		}
		
		for(Entry<String, Object> entry:resultMap.entrySet()){
			Object obj = entry.getValue();
			if(obj instanceof List){
				List<?> list = (List<?>)obj; 
				if(list==null ||list.isEmpty()){
					continue;
				}
				for(Object itemObj:list){
					if(itemObj instanceof ItemGroupData){
						ItemGroupData itemGroupData = (ItemGroupData)itemObj;
						Map<String , Object> objMap = itemGroupData.getObjMap();
						if(objMap!=null){
							Object fileObj = objMap.get(GlobalConstant.IS_FILE);
							if(fileObj instanceof String){
							  if(StringUtil.isNotBlank(fileObj.toString())){
								fileFlows.add(fileObj.toString());
							  }
							}else if(fileObj instanceof List){
								for(String str:(List<String>)fileObj){
									if(StringUtil.isNotBlank(str)){
										fileFlows.add(str);
									}
									}
								
							}
						}
						
					}
				}
				
			}
		}
		/*按时间降序排列文件流水号*/
		if(fileFlows!=null &&  !fileFlows.isEmpty()) {
			List<PubFile> fileList = this.fileBiz.searchFile(fileFlows);
			for (PubFile file : fileList) {
				orderFileFlows.add(file.getFileFlow());
			}
		}
		return orderFileFlows;
	}
	
	public Map<String , PubFile> getFile(Map<String , Object> resultMap){
		Map<String , PubFile> pageFileMap = null;
		 List<String> fileFlows = this.getFileFlows(resultMap);
		if(fileFlows!=null &&  !fileFlows.isEmpty()){
			pageFileMap = new LinkedHashMap <String, PubFile>();
			List<PubFile> fileList = this.fileBiz.searchFile(fileFlows);
			for(PubFile file:fileList){
				pageFileMap.put(file.getFileFlow(), file);
			}
	  }	
		return pageFileMap;
	}

//	@Override
//	public List<PubProj> searchProjListWithBlob(PubProj proj) {
//		PubProjExample example = new PubProjExample();
//		com.pinde.sci.model.mo.PubProjExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(proj.getApplyUserFlow())){
//			criteria.andApplyUserFlowEqualTo(proj.getApplyUserFlow());
//		}
//		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
//			criteria.andApplyOrgFlowEqualTo(proj.getApplyOrgFlow());
//		}
//		if(StringUtil.isNotBlank(proj.getProjStatusId())){
//			criteria.andProjStatusIdEqualTo(proj.getProjStatusId());
//		}
//		if(StringUtil.isNotBlank(proj.getProjStageId())){
//			criteria.andProjStageIdEqualTo(proj.getProjStageId());
//		}
//		if(StringUtil.isNotBlank(proj.getProjNo())){
//			criteria.andProjNoLike("%"+proj.getProjNo()+"%");
//		}
//		if(StringUtil.isNotBlank(proj.getProjName())){
//			criteria.andProjNameLike("%"+proj.getProjName()+"%");
//		}
//		if(StringUtil.isNotBlank(proj.getProjCategoryId())){
//			criteria.andProjCategoryIdEqualTo(proj.getProjCategoryId());
//		}
//		example.setOrderByClause("CREATE_TIME DESC, PROJ_START_TIME DESC, PROJ_END_TIME");
//		return projMapper.selectByExampleWithBLOBs(example);
//	}

//	@Override
//	public List<PubProj> queryProjList(PubProjExt projExt) {
//		if(projExt!=null&&StringUtil.isNotBlank(projExt.getKeyword())){
//			projExt.setKeyword("%"+projExt.getKeyword()+"%");
//		}
//		return  pubProjExtMapper.selectList(projExt);
//	}

	@Override
	public List<ReportForm> findReportForm(PubProj proj) {
      String[] factors = new String[]{"applyCount" , "evalCount" , "approveCount" , "notApproveCount"};
      List<ReportForm> resultReportForm = new ArrayList<ReportForm>();
      Map<String , ReportForm> tempMap = new HashMap<String, ReportForm>();
      for(String factor:factors){
    	  List<ReportForm> reportForms = findReportForm(proj ,factor);
    	  for(ReportForm form:reportForms){
    		  String orgFlow = form.getOrgFlow();
    		  if(tempMap.containsKey(orgFlow)){
                  ReportForm existsForm = tempMap.get(orgFlow);
                  if(factor.equals(factors[0])){
                	  existsForm.setApplyCount(form.getProjCount());
                  }else if(factor.equals(factors[1])){
                	  existsForm.setEvalCount(form.getProjCount());
                  }else if(factor.equals(factors[2])){
                	  existsForm.setApproveCount(form.getProjCount());
                  }else if(factor.equals(factors[3])){
                	  existsForm.setNotApproveCount(form.getProjCount());
                  }
    		  }else{
    			  ReportForm newForm = new ReportForm();
    			  newForm.setOrgFlow(form.getOrgFlow());
    			  newForm.setOrgName(form.getOrgName());
    			  if(factor.equals(factors[0])){
    				  newForm.setApplyCount(form.getProjCount());
                  }else if(factor.equals(factors[1])){
                	  newForm.setEvalCount(form.getProjCount());
                  }else if(factor.equals(factors[2])){
                	  newForm.setApproveCount(form.getProjCount());
                  }else if(factor.equals(factors[3])){
                	  newForm.setNotApproveCount(form.getProjCount());
                  }
    			  tempMap.put(orgFlow, newForm);
    		  }
    	  }
      }
        resultReportForm.addAll(tempMap.values());
        //计算立项比例
//        for(ReportForm report:resultReportForm){
//        	if(report.getEvalCount()!=null && report.getEvalCount()!=0){
//        		BigDecimal tmpApproveCount = new BigDecimal(report.getApproveCount());
//        		BigDecimal tmpEvalCount = new BigDecimal(report.getEvalCount());
//        		BigDecimal tempScale = tmpApproveCount.divide(tmpEvalCount, 2,BigDecimal.ROUND_HALF_UP);
//        		report.setApproveScale(tempScale.floatValue());
//        	}
//        	
//        }
		return resultReportForm;
	}
	
	private List<ReportForm> findReportForm(PubProj proj , String factor){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("factor", factor);
		paramMap.put("proj", proj);
		return this.pubProjExtMapper.selectReportFormData(paramMap);
	}

    @Override
    public void savePubProj(PubProj proj) {
        if(StringUtil.isNotBlank(proj.getProjFlow())) {
			GeneralMethod.setRecordInfo(proj, true);
			this.projMapper.insertSelective(proj);
			/*PubProjProcess process = new PubProjProcess();
			process.setProjStageId(ProjStageEnum.Apply.getId());
			process.setProjStageName(ProjStageEnum.Apply.getName());
			process.setProjFlow(proj.getProjFlow());
			process.setProjStatusId(proj.getProjStatusId());
			process.setProjStatusName(proj.getProjStatusName());
			processBiz.addProcess(process);*/
		}
    }
}
