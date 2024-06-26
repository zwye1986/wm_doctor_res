package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IPatentAuthorBiz;
import com.pinde.sci.biz.srm.IPatentBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SrmAchFileMapper;
import com.pinde.sci.dao.base.SrmAchPatentAuthorMapper;
import com.pinde.sci.dao.base.SrmAchPatentMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SrmAchPatentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PatentBizImpl implements IPatentBiz, IPatentAuthorBiz{
	@Autowired
	private SrmAchPatentMapper patentMapper;
	@Autowired
	private SrmAchPatentAuthorMapper authorMapper;
	@Autowired
	private SrmAchFileMapper fileMapper;

	@Autowired
	private ISrmAchProcessBiz srmAchProcessBiz;

	@Override
	public void save(SrmAchPatent srmAchPatent, List<SrmAchPatentAuthor> authorList, SrmAchFile srmAchFile, SrmAchProcess srmAchProcess) {
		//如果专利流水号不为空则修改，如果为空则新增
		if(StringUtil.isNotBlank(srmAchPatent.getPatentFlow())){
			GeneralMethod.setRecordInfo(srmAchPatent, false);
			patentMapper.updateByPrimaryKeySelective(srmAchPatent);
		}else{
			GeneralMethod.setRecordInfo(srmAchPatent, true);
			srmAchPatent.setPatentFlow(PkUtil.getUUID());
			patentMapper.insert(srmAchPatent);
		}
		//--------------------------华丽的分割线，以下是对专利作者的处理----------------------------
		 if(authorList != null && !authorList.isEmpty()){
			 int i=0;
			 for(SrmAchPatentAuthor author : authorList){
				 if(StringUtil.isNotBlank(author.getAuthorFlow())){//修改作者
					 GeneralMethod.setRecordInfo(author, false);
				     authorMapper.updateByPrimaryKeySelective(author);
				 }else{//新增作者
					 GeneralMethod.setRecordInfo(author, true);
					 //添加排序
					 author.setCreateTime(author.getCreateTime()+i);
					 i++;
				     author.setAuthorFlow(PkUtil.getUUID());
				     author.setPatentFlow(srmAchPatent.getPatentFlow());//专利流水号！
				     authorMapper.insert(author);
				 }
			 }
		 }

		 //操作附件
	    if(srmAchFile !=null){
			srmAchFile.setAchFlow(srmAchPatent.getPatentFlow());
			if(StringUtil.isNotBlank(srmAchFile.getFileFlow())){
				GeneralMethod.setRecordInfo(srmAchFile, false);
				fileMapper.updateByPrimaryKeySelective(srmAchFile);
			}
			else{
				srmAchFile.setFileFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(srmAchFile, true);
				fileMapper.insert(srmAchFile);
			}
		}
		//操作成果过程
	    srmAchProcess.setProcessFlow(PkUtil.getUUID());
	    srmAchProcess.setAchFlow(srmAchPatent.getPatentFlow());
	    GeneralMethod.setRecordInfo(srmAchProcess, true);
	    srmAchProcess.setOperateTime(srmAchProcess.getModifyTime());
	    srmAchProcessBiz.saveAchProcess(srmAchProcess);
	}

	public int save(SrmAchPatent srmAchPatent){
		if(StringUtil.isNotBlank(srmAchPatent.getPatentFlow())){
			GeneralMethod.setRecordInfo(srmAchPatent, false);
			patentMapper.updateByPrimaryKeySelective(srmAchPatent);
		}else{
			GeneralMethod.setRecordInfo(srmAchPatent, true);
			srmAchPatent.setPatentFlow(PkUtil.getUUID());
			patentMapper.insert(srmAchPatent);
		}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public SrmAchPatent readPatent(String patentFlow) {
		SrmAchPatent patent = null;
		if(StringUtil.isNotBlank(patentFlow)){
			patent = patentMapper.selectByPrimaryKey(patentFlow);
		}
		return patent;
	}

	@Override
	public int editAuthor(SrmAchPatentAuthor author){
		if(StringUtil.isNotBlank(author.getAuthorFlow())){
			GeneralMethod.setRecordInfo(author, false);
			return authorMapper.updateByPrimaryKeySelective(author);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public void updatePatentStatus(SrmAchPatent patent, SrmAchProcess process) {
		if(StringUtil.isNotBlank(patent.getPatentFlow())){
			GeneralMethod.setRecordInfo(patent, false);
			patentMapper.updateByPrimaryKeySelective(patent);
			srmAchProcessBiz.saveAchProcess(process);
	    }
	}

	@Override
	public List<SrmAchPatent> search(SrmAchPatent srmAchPatent, List<SysOrg> childOrgList) {
		SrmAchPatentExample example = new SrmAchPatentExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<String> orgFlowList=new ArrayList<String>();
		if(null != childOrgList && !childOrgList.isEmpty()){
			for(SysOrg org:childOrgList){
				orgFlowList.add(org.getOrgFlow());
			}
			criteria.andApplyOrgFlowIn(orgFlowList);
		}
		if(null != srmAchPatent){
			if (StringUtil.isNotBlank(srmAchPatent.getPatentName())) {
				criteria.andPatentNameLike("%" + srmAchPatent.getPatentName() + "%");
			}
			if(StringUtil.isNotBlank(srmAchPatent.getApplyDate())){
				criteria.andApplyDateEqualTo(srmAchPatent.getApplyDate());
			}
			if(StringUtil.isNotBlank(srmAchPatent.getTypeId())){
				criteria.andTypeIdEqualTo(srmAchPatent.getTypeId());
			}
			if(StringUtil.isNotBlank(srmAchPatent.getApplyOrgFlow())){
				criteria.andApplyOrgFlowEqualTo(srmAchPatent.getApplyOrgFlow());
			}
			if(StringUtil.isNotBlank(srmAchPatent.getOperStatusId())){
				List<String> statusList=Arrays.asList(srmAchPatent.getOperStatusId().split(","));
				criteria.andOperStatusIdIn(statusList);
			}
			if(StringUtil.isNotBlank(srmAchPatent.getApplyUserFlow())){
				criteria.andApplyUserFlowEqualTo(srmAchPatent.getApplyUserFlow());
			}
            if(StringUtil.isNotBlank(srmAchPatent.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(srmAchPatent.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getAuthorizeDate())){
                criteria.andAuthorizeDateLike("%"+srmAchPatent.getAuthorizeDate()+"%");
            }
            if(StringUtil.isNotBlank(srmAchPatent.getDeptFlow())){
                criteria.andDeptFlowEqualTo(srmAchPatent.getDeptFlow());
            }
			if(StringUtil.isNotBlank(srmAchPatent.getBranchId())){
				criteria.andBranchIdEqualTo(srmAchPatent.getBranchId());
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return patentMapper.selectByExample(example);
	}

	@Override
	public int edit(SrmAchPatent patent, List<SrmAchPatentAuthor> authorList, SrmAchFile file) {
		if(patent != null && StringUtil.isNotBlank(patent.getPatentFlow())){
			GeneralMethod.setRecordInfo(patent, false);
			patentMapper.updateByPrimaryKeySelective(patent);
		}
		//作者
		if(authorList != null && !authorList.isEmpty()){
			for(SrmAchPatentAuthor author : authorList){
				GeneralMethod.setRecordInfo(author, false);
				authorMapper.updateByPrimaryKeySelective(author);
			}
		}
		if(file != null && StringUtil.isNotBlank(file.getFileFlow())){
			GeneralMethod.setRecordInfo(file, false);
			fileMapper.updateByPrimaryKeySelective(file);
		}
		return GlobalConstant.ONE_LINE;
	}


	@Override
	public List<SrmAchPatentAuthor> searchAuthorList(SrmAchPatentAuthor author) {
		SrmAchPatentAuthorExample example = new SrmAchPatentAuthorExample();
		com.pinde.sci.model.mo.SrmAchPatentAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(author.getAuthorName())){
			criteria.andAuthorNameLike("%" + author.getAuthorName() + "%");
		}
		if(StringUtil.isNotBlank(author.getPatentFlow())){
			criteria.andPatentFlowEqualTo(author.getPatentFlow());
		}
        if(StringUtil.isNotBlank(author.getWorkCode())){
            criteria.andWorkCodeLike("%"+author.getWorkCode()+"%");
        }
		example.setOrderByClause("CREATE_TIME ASC");
		return authorMapper.selectByExample(example);
	}

    @Override
    public List<SrmAchPatent> search(SrmAchPatent srmAchPatent, List<SysOrg> childOrgList, List<String> patentFlows) {
        SrmAchPatentExample example = new SrmAchPatentExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<String> orgFlowList=new ArrayList<String>();
        if(null != childOrgList && !childOrgList.isEmpty()){
            for(SysOrg org:childOrgList){
                orgFlowList.add(org.getOrgFlow());
            }
            criteria.andApplyOrgFlowIn(orgFlowList);
        }
        if(null != srmAchPatent){
            if (StringUtil.isNotBlank(srmAchPatent.getPatentName())) {
                criteria.andPatentNameLike("%" + srmAchPatent.getPatentName() + "%");
            }
            if(StringUtil.isNotBlank(srmAchPatent.getApplyDate())){
                criteria.andApplyDateEqualTo(srmAchPatent.getApplyDate());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getTypeId())){
                criteria.andTypeIdEqualTo(srmAchPatent.getTypeId());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getApplyOrgFlow())){
                criteria.andApplyOrgFlowEqualTo(srmAchPatent.getApplyOrgFlow());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getOperStatusId())){
                List<String> statusList=Arrays.asList(srmAchPatent.getOperStatusId().split(","));
                criteria.andOperStatusIdIn(statusList);
            }
            if(StringUtil.isNotBlank(srmAchPatent.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(srmAchPatent.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(srmAchPatent.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getAuthorizeDate())){
                criteria.andAuthorizeDateLike("%"+srmAchPatent.getAuthorizeDate()+"%");
            }
            if(StringUtil.isNotBlank(srmAchPatent.getDeptFlow())){
                    criteria.andDeptFlowEqualTo(srmAchPatent.getDeptFlow());
            }
            if(StringUtil.isNotBlank(srmAchPatent.getBranchId())){
                criteria.andBranchIdEqualTo(srmAchPatent.getBranchId());
            }
            if(patentFlows!=null&&patentFlows.size()>0) {
                criteria.andPatentFlowIn(patentFlows);
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return patentMapper.selectByExample(example);
    }

	@Override
	public SrmAchPatent patentIsExist(String patentCode) {
		SrmAchPatent patent = null;
		SrmAchPatentExample example = new SrmAchPatentExample();
		SrmAchPatentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(patentCode)){
			criteria.andAuthorizeCodeEqualTo(patentCode);
			List<SrmAchPatent> patentList = patentMapper.selectByExample(example);
			if(patentList != null && patentList.size() >0){
				patent = patentList.get(0);
			}
		}
		return patent;
	}
}
