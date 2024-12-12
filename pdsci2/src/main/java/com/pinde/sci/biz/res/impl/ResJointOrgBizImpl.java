package com.pinde.sci.biz.res.impl;


import com.pinde.core.common.sci.dao.SysOrgMapper;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysOrgExample;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResJointOrgMapper;
import com.pinde.sci.dao.jszy.JszyResJointOrgExtMapper;
import com.pinde.sci.model.jszy.JszyResJointOrgExt;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.ResJointOrgExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResJointOrgBizImpl implements IResJointOrgBiz{
	@Autowired
	private ResJointOrgMapper resJointOrgMapper;
    @Autowired
    private SysOrgMapper sysOrgMapper;
    @Autowired
    private JszyResJointOrgExtMapper jszyResJointOrgExtMapper;
    @Override
	public int save(ResJointOrg resJointOrg) {
		if(StringUtil.isEmpty(resJointOrg.getSessionNumber())) {
			resJointOrg.setSessionNumber(DateUtil.getYear());
		}
		if(StringUtil.isNotBlank(resJointOrg.getJointFlow())){
			GeneralMethod.setRecordInfo(resJointOrg, false);
			return resJointOrgMapper.updateByPrimaryKeySelective(resJointOrg);
		}else{
			resJointOrg.setJointFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(resJointOrg, true);
			return resJointOrgMapper.insertSelective(resJointOrg);
		}
	}

	@Override
	public int saveAll(List<ResJointOrg> resJointOrgList) {
		return resJointOrgMapper.insertAll(resJointOrgList);
	}

	@Override
	public int editJointOrgList(List<ResJointOrg> jointOrgList){
		if(jointOrgList!=null&&jointOrgList.size()>0){
			for(ResJointOrg jointOrg : jointOrgList){
				save(jointOrg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}

	@Override
	public List<ResJointOrg> searchByOrgName(String orgName) {
		ResJointOrgExample example = new ResJointOrgExample();
		ResJointOrgExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(orgName)){
			criteria.andOrgNameLike("%"+orgName+"%");
		}
		criteria.andSessionNumberEqualTo(DateUtil.getYear());
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResJointOrg> searchResJointByOrgFlow2(String orgFlow,String jointOrgName){
		ResJointOrgExample example = new ResJointOrgExample();
		if (StringUtil.isNotBlank(jointOrgName)) {
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgNameLike("%" + jointOrgName + "%")
					.andSessionNumberEqualTo(DateUtil.getYear());
		}
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow)
				.andSessionNumberEqualTo(DateUtil.getYear());
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResJointOrg> searchResJointByOrgFlow(String orgFlow){
		ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow)
				.andSessionNumberEqualTo(DateUtil.getYear());
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResJointOrg> searchResJointByOrgFlowNotSessionYear(String orgFlow){
		ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResJointOrg> readResJointOrgByExample(ResJointOrgExample example) {
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysOrg> findJointOrgByCityId(String cityId) {
		return jszyResJointOrgExtMapper.findJointOrgByCityId(cityId);
	}

	@Override
	public List<ResJointOrg> searchResJointByJointOrgFlow(String orgFlow){
		ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowEqualTo(orgFlow)
				.andSessionNumberEqualTo(DateUtil.getYear());
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public int checkJointOrgNotSelf(String orgFlow,String jointOrgFlow) {

		ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowEqualTo(jointOrgFlow)
		.andOrgFlowNotEqualTo(orgFlow).andSessionNumberEqualTo(DateUtil.getYear());
		List<ResJointOrg> list= resJointOrgMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.size();
		}
		return 0;
	}
	@Override
	public List<ResJointOrg> searchResJoint(ResJointOrg joint){
		ResJointOrgExample example = new ResJointOrgExample();
		ResJointOrgExample.Criteria  criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andSessionNumberEqualTo(joint.getSessionNumber());
		if(StringUtils.isNotEmpty(joint.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(joint.getOrgFlow());
		}

		return resJointOrgMapper.selectByExample(example);
	}
	
	@Override
	public List<ResJointOrg> searchJointOrgAll(){
		ResJointOrg joint = new ResJointOrg();
		joint.setSessionNumber(DateUtil.getYear());
		return searchResJoint(joint);
	}

	//	@Override
//	public List<ResJointOrg> searResJointOrgInOrgList(List<String> orgList) {
//		ResJointOrgExample example = new ResJointOrgExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowIn(orgList);
//		return resJointOrgMapper.selectByExample(example);
//	}
	@Override
	public List<SysOrg> searchCouAndProList(SysOrg sysOrg) {
		SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        SysOrgExample.Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgLevelIdEqualTo(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId()).andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        criteria2.andOrgLevelIdEqualTo(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId()).andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(sysOrg.getOrgProvId())){
			criteria.andOrgProvIdEqualTo(sysOrg.getOrgProvId());
			criteria2.andOrgProvIdEqualTo(sysOrg.getOrgProvId());
		}
		if(StringUtil.isNotBlank(sysOrg.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(sysOrg.getOrgCityId());
			criteria2.andOrgCityIdEqualTo(sysOrg.getOrgCityId());
		}
		example.or(criteria2);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}

    @Override
    public List<JszyResJointOrgExt> findJointOrgExtByOrgFlow(String orgFlow) {
        return jszyResJointOrgExtMapper.findJointOrgExtByOrgFlow(orgFlow);
    }

	//根据协同基地唯一标识获取协同关系表的所有记录
	@Override
	public List<ResJointOrg> selectByJointOrgFlow(String jointOrgFlow) {
		ResJointOrgExample example = new ResJointOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andJointOrgFlowEqualTo(jointOrgFlow)
				.andSessionNumberEqualTo(DateUtil.getYear());
		return resJointOrgMapper.selectByExample(example);
	}

	@Override
	public int deleteByOrgFlow(String orgFlow, String sessionNumber) {
		return resJointOrgMapper.deleteByOrgFlow(orgFlow, sessionNumber);
	}

	@Override
	public int deleteJointOrg(ResJointOrg jointOrg) {
		return resJointOrgMapper.deleteJointOrg(jointOrg);
	}
}
