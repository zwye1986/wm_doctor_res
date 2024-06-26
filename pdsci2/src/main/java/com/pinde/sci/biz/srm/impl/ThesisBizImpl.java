package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.biz.srm.IThesisAuthorBiz;
import com.pinde.sci.biz.srm.IThesisBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SrmAchFileMapper;
import com.pinde.sci.dao.base.SrmAchThesisAuthorMapper;
import com.pinde.sci.dao.base.SrmAchThesisMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ThesisBizImpl implements IThesisBiz, IThesisAuthorBiz {
	@Autowired
	private SrmAchThesisMapper thesisMapper;
	@Autowired
	private SrmAchThesisAuthorMapper thesisAuthorMapper;
	@Autowired
	private SrmAchFileMapper fileMapper;
	
	@Autowired
	private ISrmAchProcessBiz srmAchProcessBiz;
	
	/**
	 * 保存和修改论文及其作者
	 */
	@Override
	public int save(SrmAchThesis achThesis,List<SrmAchThesisAuthor> authorList,List<SrmAchFile> fileList,SrmAchProcess srmAchProcess) {
		//操作论文
		if(StringUtil.isNotBlank(achThesis.getThesisFlow())){
			GeneralMethod.setRecordInfo(achThesis, false);
			thesisMapper.updateByPrimaryKeySelective(achThesis);
		}else{
			GeneralMethod.setRecordInfo(achThesis, true);
			achThesis.setThesisFlow(PkUtil.getUUID());
			thesisMapper.insert(achThesis);
		}
		//操作论文作者
		for(int i=0;i<authorList.size();i++){
			if(StringUtil.isNotBlank(authorList.get(i).getAuthorFlow())){
				GeneralMethod.setRecordInfo(authorList.get(i), false);
				authorList.get(i).setThesisFlow(achThesis.getThesisFlow());
				thesisAuthorMapper.updateByPrimaryKeySelective(authorList.get(i));
			}else{
				GeneralMethod.setRecordInfo(authorList.get(i), true);
				//排序（时间只精确到秒，会有重复情况）
				authorList.get(i).setCreateTime(authorList.get(i).getCreateTime()+i);
				//作者流水号
				authorList.get(i).setAuthorFlow(PkUtil.getUUID());
				authorList.get(i).setThesisFlow(achThesis.getThesisFlow());
				thesisAuthorMapper.insert(authorList.get(i));
			}
		}
	    //操作附件
		if(fileList != null && fileList.size()>0) {
			for (SrmAchFile achFile : fileList) {
				achFile.setAchFlow(achThesis.getThesisFlow());
				if (StringUtil.isNotBlank(achFile.getFileFlow())) {
					GeneralMethod.setRecordInfo(achFile, false);
					fileMapper.updateByPrimaryKeySelective(achFile);
				} else {
					achFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(achFile, true);
					fileMapper.insert(achFile);
				}
			}
		}
		//操作成果过程
	    srmAchProcess.setProcessFlow(PkUtil.getUUID());
	    srmAchProcess.setAchFlow(achThesis.getThesisFlow());
	    GeneralMethod.setRecordInfo(srmAchProcess, true);
	    srmAchProcess.setOperateTime(srmAchProcess.getModifyTime());
	    srmAchProcessBiz.saveAchProcess(srmAchProcess);
		return GlobalConstant.ONE_LINE;
	}
	
	@Override
	public int save(SrmAchThesis thesis) {
		//操作论文
		if(StringUtil.isNotBlank(thesis.getThesisFlow())){
			GeneralMethod.setRecordInfo(thesis, false);
			thesisMapper.updateByPrimaryKeySelective(thesis);
		}else{
			GeneralMethod.setRecordInfo(thesis, true);
			thesis.setThesisFlow(PkUtil.getUUID());
			thesisMapper.insert(thesis);
		}
		return GlobalConstant.ONE_LINE;
	}

	
	/**
	 * 根据条件查询一条论文的详细信息
	 */
	@Override
	public SrmAchThesis readThesis(String thesisFlow) {
		SrmAchThesis thesis = null ;
		if(StringUtil.isNotBlank(thesisFlow)){
			thesis =  thesisMapper.selectByPrimaryKey(thesisFlow);
		}
		return thesis;
	}

	@Override
	public void updateThesisStatus(SrmAchThesis thesis,SrmAchProcess process) {
		if(StringUtil.isNotBlank(thesis.getThesisFlow())){
			GeneralMethod.setRecordInfo(thesis, false);
			thesisMapper.updateByPrimaryKeySelective(thesis);
			srmAchProcessBiz.saveAchProcess(process);
	    }
	}

	@Override
	public List<SrmAchThesis> search(SrmAchThesis achThesis, List<SysOrg> childOrgList) {
		SrmAchThesisExample example = new SrmAchThesisExample();
		com.pinde.sci.model.mo.SrmAchThesisExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(null != childOrgList && !childOrgList.isEmpty()){
			List<String> orgFlowList=new ArrayList<String>();
			for(SysOrg org:childOrgList){
				orgFlowList.add(org.getOrgFlow());
			}
			criteria.andApplyOrgFlowIn(orgFlowList);
		}
		if(StringUtil.isNotBlank(achThesis.getThesisName())) {
			criteria.andThesisNameLike("%" + achThesis.getThesisName() + "%");
		}
//		if(StringUtil.isNotBlank(achThesis.getPublishDate())){
//			criteria.andPublishDateEqualTo(achThesis.getPublishDate());
//		}
		if(StringUtil.isNotBlank(achThesis.getProjSourceId())){
			criteria.andProjSourceIdEqualTo(achThesis.getProjSourceId());
		}
		if(StringUtil.isNotBlank(achThesis.getApplyOrgFlow())){
			criteria.andApplyOrgFlowEqualTo(achThesis.getApplyOrgFlow());
		}
		if (StringUtil.isNotBlank(achThesis.getJourTypeId())) {
			criteria.andJourTypeIdLike("%" + achThesis.getJourTypeId() + "%");
		}
		if(StringUtil.isNotBlank(achThesis.getOperStatusId())){
			List<String> statusList=Arrays.asList(achThesis.getOperStatusId().split(","));
			criteria.andOperStatusIdIn(statusList);
		}
		if(StringUtil.isNotBlank(achThesis.getApplyUserFlow())){
			criteria.andApplyUserFlowEqualTo(achThesis.getApplyUserFlow());
		}
		if(StringUtil.isNotBlank(achThesis.getApplyUserName())){
			criteria.andApplyUserNameLike("%"+ achThesis.getApplyUserName() +"%");
		}
        if(StringUtil.isNotBlank(achThesis.getPublishDate())){
            criteria.andPublishDateLike("%"+ achThesis.getPublishDate() +"%");
        }
		if(StringUtil.isNotBlank(achThesis.getTypeId())){
			criteria.andTypeIdEqualTo(achThesis.getTypeId());
		}
		if(StringUtil.isNotBlank(achThesis.getBranchId())){
			criteria.andBranchIdEqualTo(achThesis.getBranchId());
		}
        if(InitConfig.getSysCfg("srm_local_type").equals(GlobalConstant.FLAG_Y)){
            if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
                criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
            }
        }
		if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
			if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
				criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
			}
		}
		example.setOrderByClause("CREATE_TIME ASC");
		return thesisMapper.selectByExample(example);
	}


	@Override
	public List<SrmAchThesis> search2(SrmAchThesis achThesis, List<SysOrg> childOrgList,List<String> thesisFlows) {
		SrmAchThesisExample example = new SrmAchThesisExample();
		com.pinde.sci.model.mo.SrmAchThesisExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(null != childOrgList && !childOrgList.isEmpty()){
			List<String> orgFlowList=new ArrayList<String>();
			for(SysOrg org:childOrgList){
				orgFlowList.add(org.getOrgFlow());
			}
			criteria.andApplyOrgFlowIn(orgFlowList);
		}
		if(StringUtil.isNotBlank(achThesis.getThesisName())) {
			criteria.andThesisNameLike("%" + achThesis.getThesisName() + "%");
		}
//		if(StringUtil.isNotBlank(achThesis.getPublishDate())){
//			criteria.andPublishDateEqualTo(achThesis.getPublishDate());
//		}
		if(StringUtil.isNotBlank(achThesis.getProjSourceId())){
			criteria.andProjSourceIdEqualTo(achThesis.getProjSourceId());
		}
		if(StringUtil.isNotBlank(achThesis.getApplyOrgFlow())){
			criteria.andApplyOrgFlowEqualTo(achThesis.getApplyOrgFlow());
		}
		if (StringUtil.isNotBlank(achThesis.getJourTypeId())) {
			criteria.andJourTypeIdLike("%" + achThesis.getJourTypeId() + "%");
		}
		if(StringUtil.isNotBlank(achThesis.getOperStatusId())){
			List<String> statusList=Arrays.asList(achThesis.getOperStatusId().split(","));
			criteria.andOperStatusIdIn(statusList);
		}
		if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
			criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
		}
		if(StringUtil.isNotBlank(achThesis.getApplyUserFlow())){
			criteria.andApplyUserFlowEqualTo(achThesis.getApplyUserFlow());
		}
		if(StringUtil.isNotBlank(achThesis.getApplyUserName())){
			criteria.andApplyUserNameLike("%"+ achThesis.getApplyUserName() +"%");
		}
        if(StringUtil.isNotBlank(achThesis.getPublishDate())){
            criteria.andPublishDateLike("%"+ achThesis.getPublishDate() +"%");
        }
		if(StringUtil.isNotBlank(achThesis.getTypeId())){
			criteria.andTypeIdEqualTo(achThesis.getTypeId());
		}
		if(StringUtil.isNotBlank(achThesis.getBranchId())){
			criteria.andBranchIdEqualTo(achThesis.getBranchId());
		}
        if(InitConfig.getSysCfg("srm_local_type").equals(GlobalConstant.FLAG_Y)){
            if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
                criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
            }
        }
		if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
			if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
				criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
			}
		}
        if(thesisFlows!=null&&thesisFlows.size()>0)
        {
            criteria.andThesisFlowIn(thesisFlows);
        }
		example.setOrderByClause("CREATE_TIME ASC");
		return thesisMapper.selectByExample(example);
	}

	@Override
	public int edit(SrmAchThesis thesis, List<SrmAchThesisAuthor> authorList, SrmAchFile file) {
		if(StringUtil.isNotBlank(thesis.getThesisFlow())){
			GeneralMethod.setRecordInfo(thesis, false);
			thesisMapper.updateByPrimaryKeySelective(thesis);
		}
		//作者
		if(null != authorList && !authorList.isEmpty()){
			for(SrmAchThesisAuthor author : authorList){
				GeneralMethod.setRecordInfo(author, false);
				thesisAuthorMapper.updateByPrimaryKeySelective(author);
			}
		}
		//附件
		if(file != null && StringUtil.isNotBlank(file.getFileFlow())){
			GeneralMethod.setRecordInfo(file, false);
			fileMapper.updateByPrimaryKeySelective(file);
		}
		return GlobalConstant.ONE_LINE;
	}

	
	//-----------------------论文作者-----------------------------	

	/**
	 * 修改一条论文作者记录
	 */
	@Override
	public int editAuthor(SrmAchThesisAuthor thesisAuthor){
		if(StringUtil.isNotBlank(thesisAuthor.getAuthorFlow())){
			GeneralMethod.setRecordInfo(thesisAuthor, false);
			return thesisAuthorMapper.updateByPrimaryKeySelective(thesisAuthor);
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public List<SrmAchThesisAuthor> searchAuthorList(SrmAchThesisAuthor author) {
		SrmAchThesisAuthorExample example = new SrmAchThesisAuthorExample();
		com.pinde.sci.model.mo.SrmAchThesisAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(author.getAuthorName())){
			criteria.andAuthorNameLike("%" + author.getAuthorName() +"%");
		}
		if(StringUtil.isNotBlank(author.getThesisFlow())){
			criteria.andThesisFlowEqualTo(author.getThesisFlow());
		}
        if(StringUtil.isNotBlank(author.getWorkCode())){
            criteria.andWorkCodeLike("%"+author.getWorkCode()+"%");
        }
		example.setOrderByClause("TYPE_ID");
		return thesisAuthorMapper.selectByExample(example);
	}

	@Override
	public SrmAchThesis thesisIsExist(String thesisName) {
		SysUser currentUser = GlobalContext.getCurrentUser();
	//	SrmAchThesis thesis = null;
		SrmAchThesisExample example = new SrmAchThesisExample();
		SrmAchThesisExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andApplyOrgFlowEqualTo(currentUser.getOrgFlow());
		if(StringUtil.isNotBlank(thesisName)){
			/*根据需求要求论文题目中所有空格都去掉然后比较*/
			//criteria.andThesisNameEqualTo(thesisName);
			List<SrmAchThesis> thesisList = thesisMapper.selectByExample(example);
			if(thesisList != null && thesisList.size() >0){
				for(SrmAchThesis achThesis : thesisList){
					String thesisName2 = achThesis.getThesisName();
					thesisName2 = thesisName2.replaceAll(" ", "");//半角空格
					thesisName2 = thesisName2.replaceAll("　", "");//全角空格
					if(thesisName.equals(thesisName2)){
						return achThesis;
					}
				}
				//thesis = thesisList.get(0);
			}
		}
		return null;
	}
}
