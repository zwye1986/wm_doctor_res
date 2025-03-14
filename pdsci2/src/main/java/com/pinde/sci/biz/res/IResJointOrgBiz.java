package com.pinde.sci.biz.res;

import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.ResJointOrgExample;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.JszyResJointOrgExt;

import java.util.List;

public interface IResJointOrgBiz {
	int save(ResJointOrg resJointOrg);

	int saveAll(List<ResJointOrg> resJointOrgList);

	int editJointOrgList(List<ResJointOrg> jointOrgList);

	List<ResJointOrg> searchByOrgName(String orgName);

	List<ResJointOrg> searchResJointByOrgFlow2(String orgFlow,String jointOrgName);

	List<ResJointOrg> searchResJointByOrgFlow(String orgFlow);

	List<ResJointOrg> searchResJointByOrgFlowNotSessionYear(String orgFlow);

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

	int deleteByOrgFlow(String orgFlow);

	int deleteJointOrg(ResJointOrg jointOrg);

	/**
	 * @Department：研发部
	 * @Description 根据主键查询协同基地关联关系信息
	 * @Author fengxf
	 * @Date 2025/1/13
	 */
	ResJointOrg getResJointOrg(String jointFlow);

	/**
	 * @Department：研发部
	 * @Description 删除协同关系
	 * @Author fengxf
	 * @Date 2025/1/14
	 */
	int deleteResJointOrg(ResJointOrg resJointOrg);
}
