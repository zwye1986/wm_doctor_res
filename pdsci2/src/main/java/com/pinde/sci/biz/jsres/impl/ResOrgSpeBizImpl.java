package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResOrgSpeMapper;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.sci.enums.jsres.TrainCategoryTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResOrgSpeExample.Criteria;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tiger
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ResOrgSpeBizImpl implements IResOrgSpeBiz{

	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;

	@Autowired
	private SysCfgMapper sysCfgMapper;


	@Override
	public List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe,String trainCategoryTypeId) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		com.pinde.sci.model.mo.ResOrgSpeExample.Criteria criteria =example.createCriteria();
		List<String>speTypeIdList=new ArrayList<String>();
		if (TrainCategoryTypeEnum.BeforeCfgDate.getId().equals(trainCategoryTypeId)) {
			speTypeIdList.add(DictTypeEnum.WMFirst.getId());
		}
		if (TrainCategoryTypeEnum.AfterCfgDate.getId().equals(trainCategoryTypeId)) {
			speTypeIdList.add(DictTypeEnum.DoctorTrainingSpe.getId());
		}
		if (TrainCategoryTypeEnum.Independent.getId().equals(trainCategoryTypeId)) {
			speTypeIdList.add(DictTypeEnum.WMSecond.getId());
			speTypeIdList.add(DictTypeEnum.AssiGeneral.getId());
		}
		if (speTypeIdList!=null && !speTypeIdList.isEmpty()) {
			criteria.andSpeTypeIdIn(speTypeIdList);
		}
		andCriteria(resOrgSpe, criteria);
		return resOrgSpeMapper.selectByExample(example);

	}

	@Override
	public List<ResOrgSpe> searchResOrgSpeListNew(ResOrgSpe resOrgSpe,String trainCategoryTypeId,String speFlag) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		com.pinde.sci.model.mo.ResOrgSpeExample.Criteria criteria =example.createCriteria();
		if(GlobalConstant.RECORD_STATUS_Y.equals(speFlag)) {
            criteria.andSpeNameEqualTo("全科");
        }
		if(StringUtil.isNotBlank(trainCategoryTypeId) && !"undefined".equals(trainCategoryTypeId)){
		    criteria.andSpeTypeIdEqualTo(trainCategoryTypeId);
        }
		andCriteria(resOrgSpe, criteria);
		return resOrgSpeMapper.selectByExample(example);

	}

	private void andCriteria(ResOrgSpe resOrgSpe, Criteria criteria) {
		if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
			criteria.andOrgNameLike(resOrgSpe.getOrgName());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
			criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
			criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
		}
		if(StringUtil.isNotBlank(resOrgSpe.getRecordStatus())){
			criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
		}
		if(StringUtil.isNotBlank(resOrgSpe.getSessionYear())){
			criteria.andSessionYearEqualTo(resOrgSpe.getSessionYear());
		}
	}
	
	@Override
	public int saveResOrgSpe(ResOrgSpe resOrgSpe) {
		if(StringUtil.isNotBlank(resOrgSpe.getOrgSpeFlow())){
			GeneralMethod.setRecordInfo(resOrgSpe, false);
			return resOrgSpeMapper.updateByPrimaryKeySelective(resOrgSpe);
		}else{
			resOrgSpe.setOrgSpeFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(resOrgSpe, true);
			return resOrgSpeMapper.insert(resOrgSpe);
		}
	}

	@Override
	public int saveOrgSpeManageAll(ResOrgSpe orgSpe, List<SysOrg> orgList) throws Exception{
		String speTypeId = orgSpe.getSpeTypeId();
		String speId = orgSpe.getSpeId();
		if(StringUtil.isNotBlank(speTypeId) && StringUtil.isNotBlank(speId) && StringUtil.isNotBlank(orgSpe.getSessionYear())
			&& CollectionUtils.isNotEmpty(orgList)){
			ResOrgSpeExample example=new ResOrgSpeExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
							.andSpeTypeIdEqualTo(speTypeId).andSpeIdEqualTo(speId)
							.andOrgFlowIn(orgList.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList()))
					.andSessionYearEqualTo(orgSpe.getSessionYear());
			// 医院存在该专业基地记录的
			List<ResOrgSpe> resOrgSpeList = resOrgSpeMapper.selectByExample(example);
			Map<String, ResOrgSpe> orgFlowToOrgSpeMap = resOrgSpeList.stream().collect(Collectors.toMap(vo -> vo.getOrgFlow(), vo -> vo, (vo1, vo2) -> vo1));

			List<ResOrgSpe> insertList = new ArrayList<>();
			List<ResOrgSpe> updateList = new ArrayList<>();
			for (SysOrg org : orgList) {
				if(orgFlowToOrgSpeMap.get(org.getOrgFlow()) != null) { // 更新
					ResOrgSpe updateOrgSpe = orgFlowToOrgSpeMap.get(org.getOrgFlow());
					if(StringUtils.isNotEmpty(orgSpe.getStatus())) {
						updateOrgSpe.setStatus(orgSpe.getStatus());
					}
					if(StringUtils.isNotEmpty(orgSpe.getMinRecruitCapacity())) {
						updateOrgSpe.setMinRecruitCapacity(orgSpe.getMinRecruitCapacity());
					}

					GeneralMethod.setRecordInfo(updateList, false);
					updateList.add(updateOrgSpe);
				}else { // 新增
					ResOrgSpe insertOrgSpe = new ResOrgSpe();
					BeanUtils.copyProperties(insertOrgSpe, orgSpe);
					insertOrgSpe.setOrgFlow(org.getOrgFlow());
					insertOrgSpe.setOrgName(org.getOrgName());

					insertOrgSpe.setOrgSpeFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(insertOrgSpe, true);
					insertList.add(insertOrgSpe);
				}
			}

			int res = 0;
			if(CollectionUtils.isNotEmpty(insertList)) {
				res += resOrgSpeMapper.batchInsert(insertList);
			}
			if(CollectionUtils.isNotEmpty(updateList)) {
				res += resOrgSpeMapper.batchUpdateSelective(updateList); // 更新状态或最小容量
			}

			// 需要这个ALL的特殊的org的信息保存在sys_cfg中，因为在页面上也要展示
			saveUpdateAllCfg(orgSpe, speTypeId, speId, orgList.get(0));

			return res;
		}
		return GlobalConstant.ZERO_LINE;
	}

	private void saveUpdateAllCfg(ResOrgSpe orgSpe, String speTypeId, String speId, SysOrg sysOrg) {
		String configCode = GlobalConstant.ORG_SPE_BASE_ALL_SETTING_PREFIX + orgSpe.getSessionYear() + sysOrg.getOrgProvId();
		String desc = GlobalConstant.ORG_SPE_ALL_WS_ID + "_" + orgSpe.getSessionYear() + "_" + sysOrg.getOrgProvId();
		if(GlobalContext.getSession().getAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			desc += "_" + sysOrg.getOrgCityId();
			configCode += sysOrg.getOrgCityId();
		}
		configCode += "_" + speTypeId + speId;
		SysCfgExample example = new SysCfgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andCfgCodeEqualTo(configCode)
				.andCfgDescEqualTo(desc);
		List<SysCfg> sysCfgList = sysCfgMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(sysCfgList)) {
			String cfgValue = "";
			if(StringUtils.isNotEmpty(orgSpe.getStatus())) {
				cfgValue = orgSpe.getStatus() + ",";
			}
			if(StringUtils.isNotEmpty(orgSpe.getMinRecruitCapacity())) {
				cfgValue = "," + orgSpe.getMinRecruitCapacity();
			}
			cfgValue.replace(",,", ",");

			SysCfg sysCfg = new SysCfg();
			sysCfg.setCfgCode(configCode);
			sysCfg.setCfgValue(cfgValue);
			sysCfg.setWsId(GlobalConstant.ORG_SPE_ALL_WS_ID);
			sysCfg.setWsName("配置全部医院专业基地");
			sysCfg.setCfgDesc(desc);
			GeneralMethod.setRecordInfo(sysCfg, true);
			sysCfgMapper.insert(sysCfg);
		}else {
			SysCfg orgAllCfg = sysCfgList.get(0);
			String cfgValue = orgAllCfg.getCfgValue();
			if(StringUtils.isNotEmpty(cfgValue)) {
				String[] setting = cfgValue.split(",");
				String statusSetting = setting[0];
				String minCapacitySetting = "";
				if(setting.length == 2) {
					minCapacitySetting = setting[1];
				}

				if(StringUtils.isNotEmpty(orgSpe.getStatus())) {
					statusSetting = orgSpe.getStatus();
				}
				if(StringUtils.isNotEmpty(orgSpe.getMinRecruitCapacity())) {
					minCapacitySetting = orgSpe.getMinRecruitCapacity();
				}
				String newCfgValue = statusSetting + "," + minCapacitySetting;
				orgAllCfg.setCfgValue(newCfgValue);

			}else {
				String newCfgValue = "";
				if(StringUtils.isNotEmpty(orgSpe.getStatus())) {
					newCfgValue = orgSpe.getStatus() + ",";
				}
				if(StringUtils.isNotEmpty(orgSpe.getMinRecruitCapacity())) {
					newCfgValue = "," + orgSpe.getMinRecruitCapacity();
				}
				newCfgValue.replace(",,", ",");
				orgAllCfg.setCfgValue(newCfgValue);
			}

			GeneralMethod.setRecordInfo(orgAllCfg, false);
			sysCfgMapper.updateByPrimaryKey(orgAllCfg);
		}
	}

	@Override
	public List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		com.pinde.sci.model.mo.ResOrgSpeExample.Criteria criteria =example.createCriteria();
		andCriteria(resOrgSpe, criteria);
		example.setOrderByClause("CREATE_TIME DESC");
		return resOrgSpeMapper.selectByExample(example);
	}

	@Override
	public int saveOrgSpeManage(ResOrgSpe orgSpe) {
		String orgFlow = orgSpe.getOrgFlow();
		String speTypeId = orgSpe.getSpeTypeId();
		String speId = orgSpe.getSpeId();
		if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(speTypeId) && StringUtil.isNotBlank(speId)){
			ResOrgSpe exitOrgSpe = new ResOrgSpe();
			exitOrgSpe.setOrgFlow(orgFlow);
			exitOrgSpe.setSpeTypeId(speTypeId);
			exitOrgSpe.setSpeId(speId);
			exitOrgSpe.setSessionYear(orgSpe.getSessionYear());
			exitOrgSpe.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResOrgSpe> orgSpeList = searchResOrgSpeList(exitOrgSpe);
			if(orgSpeList != null && !orgSpeList.isEmpty()){//修改已存在的记录
				exitOrgSpe = orgSpeList.get(0);
				if(orgSpe.getStatus() != null && orgSpe.getStatus().length() > 0) {
					exitOrgSpe.setStatus(orgSpe.getStatus());
				}
				if(orgSpe.getMinRecruitCapacity() != null && orgSpe.getMinRecruitCapacity().length() > 0) {
					exitOrgSpe.setMinRecruitCapacity(orgSpe.getMinRecruitCapacity());
				}
				return saveResOrgSpe(exitOrgSpe);
			}else{//新增
				return saveResOrgSpe(orgSpe);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<ResOrgSpe> searchSpeByCondition(ResOrgSpe resOrgSpe, String flag) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		com.pinde.sci.model.mo.ResOrgSpeExample.Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(resOrgSpe.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(resOrgSpe.getOrgFlow());
		}
		if (StringUtil.isNotBlank(resOrgSpe.getOrgName())) {
			criteria.andOrgNameLike(resOrgSpe.getOrgName());
		}
		if(StringUtil.isBlank(flag)){
			if (StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())) {
				criteria.andSpeTypeIdEqualTo(resOrgSpe.getSpeTypeId());
			}
		}
		if (StringUtil.isNotBlank(resOrgSpe.getSpeId())) {
			criteria.andSpeIdEqualTo(resOrgSpe.getSpeId());
		}
		if(StringUtil.isNotBlank(resOrgSpe.getRecordStatus())){
			criteria.andRecordStatusEqualTo(resOrgSpe.getRecordStatus());
		}
		if(GlobalConstant.FLAG_Y.equals(flag)&&StringUtil.isNotBlank(resOrgSpe.getSpeTypeId())){
			criteria.andSpeTypeIdNotEqualTo(resOrgSpe.getSpeTypeId());
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return resOrgSpeMapper.selectByExample(example);
	}

	@Override
	public List<ResOrgSpe> searchResOrgSpeListByFlows(List<String> jointOrgFlowList) {
		ResOrgSpeExample example=new ResOrgSpeExample();
		com.pinde.sci.model.mo.ResOrgSpeExample.Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if (jointOrgFlowList!=null&&jointOrgFlowList.size()>0) {
			criteria.andOrgFlowIn(jointOrgFlowList);
		}
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME DESC");
		return resOrgSpeMapper.selectByExample(example);
	}
}
