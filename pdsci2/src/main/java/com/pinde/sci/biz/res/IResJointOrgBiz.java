package com.pinde.sci.biz.res;

import com.pinde.sci.model.jszy.JszyResJointOrgExt;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.ResJointOrgExample;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;

public interface IResJointOrgBiz {
	int save(ResJointOrg resJointOrg);

	int saveAll(List<ResJointOrg> resJointOrgList);

	int editJointOrgList(List<ResJointOrg> jointOrgList);

	List<ResJointOrg> searchByOrgName(String orgName);

	List<ResJointOrg> searchResJointByOrgFlow2(String orgFlow,String jointOrgName);

	List<ResJointOrg> searchResJointByOrgFlow(String orgFlow);

	List<ResJointOrg> readResJointOrgByExample(ResJointOrgExample example);

	//查询主基地在本地市的外地协同基地
	List<SysOrg> findJointOrgByCityId(String cityId);

	List<ResJointOrg> searchResJoint(ResJointOrg joint);

	List<ResJointOrg> searchJointOrgAll();

	//	List<ResJointOrg> searResJointOrgInOrgList(List<String> orgList);
	List<ResJointOrg> searchResJointByJointOrgFlow(String orgFlow);

	List<SysOrg> searchCouAndProList(SysOrg org);

	int checkJointOrgNotSelf(String orgFlow, String jointOrgFlow);
    //查询协同基地
    List<JszyResJointOrgExt> findJointOrgExtByOrgFlow(String orgFlow);

	//根据协同基地唯一标识获取协同关系表的所有记录
	List<ResJointOrg> selectByJointOrgFlow(String jointOrgFlow);

	int deleteByOrgFlow(String orgFlow, String sessionNumber);

	int deleteJointOrg(ResJointOrg jointOrg);
}
