package com.pinde.sci.biz.sys.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.sys.SysOrgExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class OrgBizImpl implements IOrgBiz {
	
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SysOrgExtMapper sysOrgExtMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	
	@Override
	public SysOrg readSysOrg(String sysOrgFlow) {
		return sysOrgMapper.selectByPrimaryKey(sysOrgFlow);
	}

	@Override
	public int addOrg(SysOrg org) {
		GeneralMethod.setRecordInfo(org, true);
		return sysOrgMapper.insertSelective(org);
	}
	
	@Override
	public int saveOrg(SysOrg org) {
		if(StringUtil.isNotBlank(org.getOrgFlow())){
			GeneralMethod.setRecordInfo(org, false);
			if (StringUtil.isBlank(org.getSendSchoolId())) {
				org.setSendSchoolName(null);
			}
			sysOrgMapper.updateByOrgFlow(org.getOrgFlow(),org.getOrgName());
			int result = sysOrgMapper.updateByPrimaryKeySelective(org);
			//同时更新机构名到用户表中
			SysUser user = new SysUser();
			user.setOrgFlow(org.getOrgFlow());
			user.setOrgName(org.getOrgName());
			GeneralMethod.setRecordInfo(user, false);
			this.userBiz.modifyUserByExample(user);
			return result;
		}else{
			org.setOrgFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(org, true);
			return sysOrgMapper.insertSelective(org);
		}
	}
	
	@Override
	public int saveDeclarerOrg(SysOrg org,SysUser user) {
		if(org != null && user != null){
			boolean orgRecord = StringUtil.isNotBlank(org.getOrgFlow());
			boolean userRecord = StringUtil.isNotBlank(user.getUserFlow());
			if(orgRecord){
				update(org);
			}else{
				org.setOrgFlow(PkUtil.getUUID());
				save(org);
			}
			user.setOrgFlow(org.getOrgFlow());
			user.setOrgName(org.getOrgName());
			if(userRecord){
				userBiz.saveUser(user);
			}else{
				user.setUserFlow(PkUtil.getUUID());
				userBiz.addUser(user);
			}
			if(!userRecord){
				SysUserRole userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
				userRole.setOrgFlow(org.getOrgFlow());
                userRole.setWsId((String) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
                userRole.setRoleFlow(InitConfig.getSysCfg(com.pinde.core.common.GlobalConstant.DECLARER_ROLE_FLOW));
				userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				userRole.setAuthTime(DateUtil.getCurrDateTime());
				userRoleBiz.saveSysUserRole(userRole);
			}
            return com.pinde.core.common.GlobalConstant.ONE_LINE;
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
	private int save(SysOrg org){
		GeneralMethod.setRecordInfo(org, true);
		return sysOrgMapper.insertSelective(org);
	}
	
	@Override
	public int update(SysOrg org){
		GeneralMethod.setRecordInfo(org, false);
		return sysOrgMapper.updateByPrimaryKeySelective(org);
	}
	
	@Override
	public List<SysOrg> searchOrg(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sysorg.getOrgFlow())){
            criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
        }
		if(StringUtil.isNotBlank(sysorg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgName())){
			criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(sysorg.getIsSecondFlag())){
			criteria.andIsSecondFlagEqualTo(sysorg.getIsSecondFlag());
		}
		if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
			criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
		}
		example.setOrderByClause(" ORDINAL,ORG_CODE,RECORD_STATUS DESC,ORG_FLOW desc");
		return sysOrgMapper.selectByExample(example);
	}


	@Override
	public List<SysOrg> searchOrgIsAcc(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysorg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgName())){
			criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(sysorg.getIsSecondFlag())){
			criteria.andIsSecondFlagEqualTo(sysorg.getIsSecondFlag());
		}
		if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
			criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
		}
		if (StringUtil.isNotBlank(sysorg.getIsAcc())){
			criteria.andIsAccEqualTo(sysorg.getIsAcc());
		}
		example.setOrderByClause(" ORDINAL,ORG_CODE,RECORD_STATUS DESC,ORG_FLOW desc");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchOrgList(SysOrg sysOrg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysOrg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysOrg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysOrg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgName())){
			criteria.andOrgNameLike("%"+sysOrg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysOrg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysOrg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysOrg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysOrg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysOrg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysOrg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysOrg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysOrg.getOrgTypeId());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(sysOrg.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(sysOrg.getIsSecondFlag())){
			criteria.andIsSecondFlagEqualTo(sysOrg.getIsSecondFlag());
		}
		if(StringUtil.isNotBlank(sysOrg.getIsExamOrg())){
			criteria.andIsExamOrgEqualTo(sysOrg.getIsExamOrg());
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("ORG_CODE");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<PersonStaticExample> searchOrgSession(SysOrg sysOrg) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OrgProvId", sysOrg.getOrgProvId());
		paramMap.put("OrgCityId", sysOrg.getOrgCityId());
		paramMap.put("OrgTypeId", sysOrg.getOrgTypeId());
		paramMap.put("OrgLevelId", sysOrg.getOrgLevelId());
		paramMap.put("RecordStatus", sysOrg.getRecordStatus());
		return sysOrgMapper.selectByExampleSession(paramMap);
	}

	@Override
    public List<SysOrg> searchOrgByClause(SysOrg sysorg, String orderClause) {
        SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sysorg.getOrgFlow())){
            criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgCode())){
            criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgName())){
            criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
        }
        if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
            criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
        }
        if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
            criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
        }
        if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
            criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
            criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
            criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
            criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
        }
        if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
            criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
        }
        if(StringUtil.isNotBlank(sysorg.getIsSecondFlag())){
            criteria.andIsSecondFlagEqualTo(sysorg.getIsSecondFlag());
        }
        if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
            criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
        }
        if(StringUtil.isNotBlank(orderClause)){
            example.setOrderByClause(" "+orderClause);
        }else{
            example.setOrderByClause(" ORDINAL,ORG_CODE,RECORD_STATUS DESC,ORG_FLOW desc");
        }
        return sysOrgMapper.selectByExample(example);
    }
	
	@Override
	public List<SysOrg> searchOrgWithBLOBs(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysorg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgName())){
			criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		example.setOrderByClause(" RECORD_STATUS DESC,CREATE_TIME");
		return sysOrgMapper.selectByExampleWithBLOBs(example);
	}	
	
	@Override
	public List<SysOrg> queryAllSysOrg(SysOrg sysorg) {
		SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysorg!=null){
			if(StringUtil.isNotBlank(sysorg.getOrgName())){
				criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
			}
			if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
				criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
				criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
				criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
			}
		}
		example.setOrderByClause("NLSSORT(ORG_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
	    return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchChildrenOrgByOrgFlow(String orgFlow) {
		return this. sysOrgExtMapper.selectChildrenOrgListByOrgFlow(orgFlow);
	}
	@Override
	public List<SysOrg> searchAllSysOrg(SysOrg sysorg) {
		SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysorg!=null){

					if(StringUtil.isNotBlank(sysorg.getOrgName())){
				criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
			}
			if(StringUtil.isNotBlank(sysorg.getOrgCode())){
				criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
			}
			if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
				criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
			}
			if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
				criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
				criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
				criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
				criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
				criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
				criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
			}
		}
		example.setOrderByClause("ORG_CODE");
	    return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchOrgNoRegByRoleFlow(String roleFlow) {
		return this.sysOrgExtMapper.selectOrgNoRegByRoleFlow(roleFlow);
	}

	@Override
	public List<SysOrg> searchChildrenOrgByOrgFlowNotIncludeSelf(String orgFlow) {
		 return sysOrgExtMapper.selectChildrenOrgListByOrgFlowNotIncludeSelf(orgFlow);
	}

	@Override
	public Map<String, List<SysOrg>> searchChargeAndApply(SysOrg org,String projListScope) {
		List<SysOrg> firstGradeOrgList=null;
		List<SysOrg> secondGradeOrgList=null;
        org.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		Map<String,List<SysOrg>> resultMap=new HashMap<String, List<SysOrg>>();
		SysUser currUser=GlobalContext.getCurrentUser();
		//如果chargeOrgFlow不为空，查询该机构下所有下属机构
		if(StringUtil.isNotBlank(org.getChargeOrgFlow())){
			secondGradeOrgList=searchOrg(org);
			resultMap.put("secondGradeOrgList", secondGradeOrgList);
		}
		//查询当前机构下一级所有机构
		SysOrg currOrg=new SysOrg();
		currOrg.setChargeOrgFlow(currUser.getOrgFlow());
		firstGradeOrgList=searchOrg(currOrg);
        if (com.pinde.core.common.GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)) {
			SysOrg globalOrg=readSysOrg(currUser.getOrgFlow());
			firstGradeOrgList.add(globalOrg);
		}
		resultMap.put("firstGradeOrgList", firstGradeOrgList);
		return resultMap;
	}

	@Override
	public List<SysOrg> searchOrgListByChargeOrgFlow(String chargeOrgFlow) {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setChargeOrgFlow(chargeOrgFlow);
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysOrg> orgList = this.searchOrg(sysOrg);
		return orgList;
	}

	@Override
	public List<SysOrg> searchChargeOrg() {
		return this.sysOrgExtMapper.selectChargeOrgList();
	}
	
	@Override
	public List<SysOrg> searchSysOrg(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		setCriteria(criteria,sysorg);
		example.setOrderByClause("CREATE_TIME");
		return sysOrgMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<SysOrg> searchSysOrg() {
		SysOrgExample example=new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}
	
	@Override
	public List<SysOrg> searchOrderBy(SysOrg sysorg){
		SysOrgExample example=new SysOrgExample();
		//非培养单位
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsTrainOrgNotEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		setCriteria(criteria,sysorg);
		//默认为培养单位
        SysOrgExample.Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsTrainOrgIsNull();
		setCriteria(criteria2,sysorg);
		example.or(criteria2);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExampleWithBLOBs(example);
	}
	
	private void setCriteria(SysOrgExample.Criteria criteria,SysOrg sysorg){
		if(StringUtil.isNotBlank(sysorg.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgName())){
			criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
		}
	}
	@Override
	public List<SysOrg> searchByOrgNotSelf(String orgFlow,SysOrg sysorg){
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow);
		setCriteria(criteria,sysorg);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}

    @Override
    public List<SysOrg> searchSysOrgByName(SysOrg sysorg) {
        SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgNameEqualTo(sysorg.getOrgName());
        criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());

        return sysOrgMapper.selectByExample(example);
    }

    @Override
	public List<SysOrg> searchOrgByExample(SysOrgExample example) {
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchHbresOrgList() {
		SysOrgExample example = new SysOrgExample();
        example.createCriteria().andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchOrgNotSelf(String orgFlow) {
		SysOrgExample example = new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow);
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchAllJointOrg4sczy(String orgFlow) {
		SysOrgExample example = new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowNotEqualTo(orgFlow).andOrgLevelIdEqualTo(com.pinde.core.common.enums.ResOrgLevelEnum.Joint.getId());
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchOrgNotSelfAndNotCountryAndNotProvince(String orgFlow) {
		return sysOrgExtMapper.searchOrgNotSelfAndNotCountryAndNotProvince(orgFlow);
	}
	@Override
	public List<SysOrg> searchOrgFlowIn(List<String> orgFlows){
		SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(orgFlows!=null&&orgFlows.size()>0){
			criteria.andOrgFlowIn(orgFlows);
		}
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchJsResOrgWithJointOrg(SysOrg sysOrg, List<String> orgLevelList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("orgLevelList", orgLevelList);
		return sysOrgExtMapper.searchJsResOrgWithJointOrg(paramMap);
	}

	@Override
	public List<SysOrg> searOrgTeacherRoleCheckUser(SysOrg org) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("org", org);
		List<SysOrg> sysOrgList=sysOrgExtMapper.searOrgTeacherRoleCheckUser(map);
		return sysOrgList;
	}

	@Override
	public List<SysOrg> searchOrgs(SysOrg sysOrg,String flag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("flag", flag);
		return sysOrgExtMapper.searchOrgs(paramMap);
	}

	@Override
	public List<SysOrg> getUniOrgs(String sendSchoolId, String sendSchoolName) {
		if(StringUtil.isBlank(sendSchoolId)&&StringUtil.isBlank(sendSchoolName)){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sendSchoolId", sendSchoolId);
		paramMap.put("sendSchoolName", sendSchoolName);
		return sysOrgExtMapper.getUniOrgs(paramMap);
	}
	@Override
	public List<SysOrg> getUniBackTrainOrgs(String sendSchoolId, String sendSchoolName) {
		if(StringUtil.isBlank(sendSchoolId)&&StringUtil.isBlank(sendSchoolName)){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sendSchoolId", sendSchoolId);
		paramMap.put("sendSchoolName", sendSchoolName);
		return sysOrgExtMapper.getUniBackTrainOrgs(paramMap);
	}

	@Override
	public List<SysOrg> searchJointOrgsByOrg(String orgFlow) {

		return sysOrgExtMapper.searchJointOrgsByOrg(orgFlow);
	}

	@Override
	public List<PersonStaticExample> searchJointOrgsSession(String orgFlow) {
		return sysOrgExtMapper.searchJointOrgsSession(orgFlow);
	}

	@Override
	public SysOrg readSysOrgByName(String orgName) {
		if(StringUtil.isNotBlank(orgName)) {
			SysOrgExample example = new SysOrgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgNameEqualTo(orgName);
			example.setOrderByClause("ORDINAL");
			List<SysOrg> list= sysOrgMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public SysOrg readSysOrgByCode(String orgCode) {
		if(StringUtil.isNotBlank(orgCode)) {
			SysOrgExample example = new SysOrgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgCodeEqualTo(orgCode);
			example.setOrderByClause("ORDINAL");
			List<SysOrg> list= sysOrgMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public int checkOrgCodeNotSelf(String orgCode,String orgFlow) {
		if(StringUtil.isNotBlank(orgCode)) {
			SysOrgExample example = new SysOrgExample();
			SysOrgExample.Criteria criteria= example.createCriteria()
					.andOrgCodeEqualTo(orgCode);
			if(StringUtil.isNotBlank(orgFlow)) {
				criteria.andOrgFlowNotEqualTo(orgFlow);
			}
			List<SysOrg> list= sysOrgMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.size();
			}
		}
		return 0;
	}
	@Override
	public int checkOrgNameNotSelf(String orgName,String orgFlow) {
		if(StringUtil.isNotBlank(orgName)) {
			SysOrgExample example = new SysOrgExample();
			SysOrgExample.Criteria criteria= example.createCriteria()
					.andOrgNameEqualTo(orgName);
			if(StringUtil.isNotBlank(orgFlow)) {
				criteria.andOrgFlowNotEqualTo(orgFlow);
			}
			List<SysOrg> list= sysOrgMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.size();
			}
		}
		return 0;
	}

	public List<String> getOrgFlowsBySysOrgs(List<SysOrg> orgs){
		List<String> orgFlows = null;
		if(orgs != null && orgs.size() > 0){
			orgFlows = new ArrayList<>();
			for(SysOrg tempOrg : orgs){
				orgFlows.add(tempOrg.getOrgFlow());
			}
		}
		return orgFlows;
	}

	@Override
	public int confirmRole(String userFlow, SysOrg sysOrg) {
		if(StringUtil.isNotBlank(sysOrg.getHospitalPassword())){
			List<String> roleList = new ArrayList<>();
			roleList.add(InitConfig.getSysCfg("res_head_role_flow"));//科主任
			roleList.add(InitConfig.getSysCfg("res_teacher_role_flow"));//带教
			roleList.add(InitConfig.getSysCfg("res_doctor_role_flow"));//学员
			Map<String,Object> params = new HashMap<>();
			params.put("userFlow",userFlow);
			params.put("orgFlow",sysOrg.getOrgFlow());
			params.put("hospitalPassword",sysOrg.getHospitalPassword());
			params.put("roleList",roleList);
			return sysOrgExtMapper.confirmRole(params).size();
		}
		return 0;
	}

	@Override
	public List<SysOrg> searchTrainOrgList() {
		SysOrgExample example = new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId()).andIsTrainOrgNotEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        SysOrgExample.Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId()).andIsTrainOrgIsNull();
		example.or(criteria2);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public int updateOrgSubmit(Map<String,Object> map) {
		return sysOrgExtMapper.updateOrgSubmit(map);
	}

	@Override
	public int saveOrgSubmit(List<String> orgFlowList) {
		return sysOrgExtMapper.saveOrgSubmit(orgFlowList);
	}

	@Override
	public int updateCheckAll(Map<String, Object> param) {
		return sysOrgExtMapper.updateCheckAll(param);
	}

	@Override
	public int updateHospitalNotSubmit(List<String> userFlowList) {
		return sysOrgExtMapper.updateHospitalNotSubmit(userFlowList);
	}

	@Override
	public List<SysOrg> searchOrgNotCountryOrg(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgLevelIdNotEqualTo(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		example.setOrderByClause(" ORDINAL,ORG_CODE DESC,ORG_FLOW desc");
		return sysOrgMapper.selectByExample(example);
	}

    @Override
    public List<SysOrg> searchSysOrgOrder(List<String> orgLevels){
        SysOrgExample example=new SysOrgExample();
        //非培养单位
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (orgLevels != null && orgLevels.size() > 0) {
            criteria.andOrgLevelIdIn(orgLevels);
        }
        criteria.andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        criteria.andOrgProvIdEqualTo("320000");
        example.setOrderByClause("ORDINAL");
        return sysOrgMapper.selectByExampleWithBLOBs(example);
    }

	/**
	 * @param sysOrgExt
	 * @Department：研发部
	 * @Description 查询基地列表信息
	 * @Author fengxf
	 * @Date 2020/9/2
	 */
	@Override
	public List<SysOrgExt> searchOrgListByParam(SysOrgExt sysOrgExt) {
		return sysOrgExtMapper.searchOrgListByParam(sysOrgExt);
	}

	/**
	 * @param sysOrgExt
	 * @Department：研发部
	 * @Description 查询基地下协同基地的数量
	 * @Author fengxf
	 * @Date 2020/9/2
	 */
	@Override
	public List<Map<String, String>> getJointOrgCountByParam(SysOrgExt sysOrgExt) {
		return sysOrgExtMapper.getJointOrgCountByParam(sysOrgExt);
	}

	/**
	 * @param workOrgId
	 * @Department：研发部
	 * @Description 查询高校下的培训基地信息
	 * @Author fengxf
	 * @Date 2020/10/12
	 */
	@Override
	public List<SysOrg> searchUniversityOrgList(String workOrgId) {
		return sysOrgExtMapper.searchUniversityOrgList(workOrgId);
	}

	/**
	 * @param sysorg
	 * @Department：研发部
	 * @Description 除当前医院的剩下医院
	 * @Author Zjie
	 * @Date 0027, 2020年11月27日
	 */
	@Override
	public List<SysOrg> searchOrgNew(SysOrg sysorg) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysorg.getOrgFlow())){
			criteria.andOrgFlowNotEqualTo(sysorg.getOrgFlow());
//			criteria.andOrgFlowEqualTo(sysorg.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCode())){
			criteria.andOrgCodeEqualTo(sysorg.getOrgCode());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgName())){
			criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(sysorg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysorg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysorg.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysorg.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysorg.getChargeOrgFlow())){
			criteria.andChargeOrgFlowEqualTo(sysorg.getChargeOrgFlow());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgAreaId())){
			criteria.andOrgAreaIdEqualTo(sysorg.getOrgAreaId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
		}
		if(StringUtil.isNotBlank(sysorg.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(sysorg.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(sysorg.getIsSecondFlag())){
			criteria.andIsSecondFlagEqualTo(sysorg.getIsSecondFlag());
		}
		if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
			criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
		}
		example.setOrderByClause(" ORDINAL,ORG_CODE,RECORD_STATUS DESC,ORG_FLOW desc");
		return sysOrgMapper.selectByExample(example);
	}

	/**
	 * @param sysOrg
	 * @Department：研发部
	 * @Description 市局查询基地和协同基地信息
	 * @Author fengxf
	 * @Date 2020/12/10
	 */
	@Override
	public List<SysOrg> searchOrgAndJointOrgList(SysOrg sysOrg) {
		return sysOrgExtMapper.searchOrgAndJointOrgList(sysOrg);
	}

	@Override
	public List<SysOrg> searchOrgListNew(SysOrg sysorg) {
		Map<String,Object> param = new HashMap<>();
		param.put("sysorg",sysorg);
		return sysOrgExtMapper.searchOrgListNew(param);
	}

	@Override
	public List<SysOrg> searchOrgWork() {
		SysOrgExample example=new SysOrgExample();
		example.createCriteria().andOrgTypeIdEqualTo("Hospital");
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> searchOrgNotJointOrg(SysOrg sysOrg, List<String> orgLevelList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("orgLevelList", orgLevelList);
		return sysOrgExtMapper.searchOrgNotJointOrg(paramMap);
	}

	@Override
	public List<SysOrg> orgGxList() {
		return sysOrgExtMapper.orgGxList();
	}

	@Override
	public List<SysOrg> searchOrgByJoin(SysOrgExt sysOrgExt) {
		return sysOrgExtMapper.searchOrgByJoin(sysOrgExt);
	}

	@Override
	public List<ResOrgSpe> searchByCountOrg(String orgName) {
		return sysOrgExtMapper.searchByCountOrg(orgName);
	}

	@Override
	public List<SysOrgExt> searchOrgListByParamNew(SysOrgExt sysOrgExt) {
		return sysOrgExtMapper.searchOrgListByParamNew(sysOrgExt);
	}

	@Override
	public List<Map<String, String>> getJointOrgCountByParamNew(SysOrgExt sysOrgExt) {
		return sysOrgExtMapper.getJointOrgCountByParamNew(sysOrgExt);
	}

	@Override
	public List<SysOrg> selectJointOrgAllList(SysOrg sysOrg) {
		return sysOrgMapper.selectJointOrgAllList(sysOrg);
	}

	@Override
	public List<SysOrg> searchOrgListByCityId(String cityId) {
		SysOrgExample example = new SysOrgExample();
		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		//非培养单位
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (orgLevels.size() > 0) {
			criteria.andOrgLevelIdIn(orgLevels);
		}
        criteria.andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		criteria.andOrgProvIdEqualTo("320000");
		criteria.andOrgCityIdEqualTo(cityId);
		example.setOrderByClause("org_code asc");
		return sysOrgMapper.selectByExampleWithBLOBs(example);
	}
}
