package com.pinde.sci.biz.sys;

import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.PersonStaticExample;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysOrgExample;
import com.pinde.sci.model.sys.SysOrgExt;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 */
public interface IOrgBiz {
	
	public SysOrg readSysOrg(String orgFlow);
	
	public int addOrg(SysOrg org);
	
	public int saveOrg(SysOrg org);
	
	public List<SysOrg> searchOrg(SysOrg sysOrg);

	public List<SysOrg> searchOrgIsAcc(SysOrg sysOrg);

	public List<SysOrg> searchOrgList(SysOrg sysOrg);

	public List<PersonStaticExample> searchOrgSession(SysOrg sysOrg);

	/**
	 * 查询全部单位
	 */
	public List<SysOrg> queryAllSysOrg(SysOrg sysorg);
	
	/**
	 * 查询某个节点下所有子节点包过自身节点
	 * @param orgFlow
	 * @return
	 */
	public List<SysOrg> searchChildrenOrgByOrgFlow(String orgFlow);
	
	/**
	 * 查询某个节点下所有子节点但不包括自身节点
	 * @param orgFlow
	 * @return
	 */
	public List<SysOrg> searchChildrenOrgByOrgFlowNotIncludeSelf(String orgFlow);
	
	/**
	 * 查询某个角色没有被注册的单位
	 * @param roleFlow
	 * @return
	 */
	public List<SysOrg> searchOrgNoRegByRoleFlow(String roleFlow);
	
	/**
	 * 根据orgFlow和chargeOrgFlow查询主管部门和申报单位列表
	 * @param org
	 * @return
	 */
	public Map<String, List<SysOrg>> searchChargeAndApply(SysOrg org,String projListScope);
	
	/**
	 * 查询主管部门为chargeOrgFlow的单位
	 * @param chargeOrgFlow
	 * @return
	 */
	public List<SysOrg> searchOrgListByChargeOrgFlow(String chargeOrgFlow);
	
	/**
	 * 查询所有的主管部门 当有些医院是直属市医院时，该主管部门就是卫生局
	 * @return
	 */
	public List<SysOrg> searchChargeOrg();


    List<SysOrg> searchOrgByClause(SysOrg sysorg, String orderClause);

    List<SysOrg> searchOrgWithBLOBs(SysOrg sysorg);

	int saveDeclarerOrg(SysOrg org, SysUser user);

	List<SysOrg> searchSysOrg(SysOrg sysorg);

	List<SysOrg> searchSysOrgByName(SysOrg sysorg);

	public List<SysOrg> searchOrgByExample(SysOrgExample example);

	List<SysOrg> searchOrderBy(SysOrg sysorg);

	List<SysOrg> searchSysOrg();

	/**
	 * 查询基地
	 * @return
	 */
	List<SysOrg> searchHbresOrgList();
 
	List<SysOrg> searchOrgNotSelf(String orgFlow);

	List<SysOrg> searchAllJointOrg4sczy(String orgFlow);

	List<SysOrg> searchOrgFlowIn(List<String> orgFlows);

	int update(SysOrg org);

	List<SysOrg> searchByOrgNotSelf(String orgFlow, SysOrg sysorg);

	
	/**
	 * 查询基地及协同基地
	 * @param sysOrg
	 * @param orgLevelList
	 * @return
	 */
	List<SysOrg> searchJsResOrgWithJointOrg(SysOrg sysOrg, List<String> orgLevelList);

	List<SysOrg> searchAllSysOrg(SysOrg sysorg);

	List<SysOrg> searOrgTeacherRoleCheckUser(SysOrg org);
	
	List<SysOrg> searchOrgs(SysOrg sysOrg,String Flag);

	/**
	 * 查询高校下所有学生参加培训的培训基地
	 * @param sendSchoolId
	 * @param sendSchoolName
     * @return
     */
	List<SysOrg> getUniOrgs(String sendSchoolId, String sendSchoolName);
	/**
	 * 查询高校下所有学生参加培训的培训基地包含退培过的
	 * @param sendSchoolId
	 * @param sendSchoolName
     * @return
     */
	List<SysOrg> getUniBackTrainOrgs(String sendSchoolId, String sendSchoolName);


	List<SysOrg> searchJointOrgsByOrg(String orgFlow);

	List<PersonStaticExample> searchJointOrgsSession(String orgFlow);

	SysOrg readSysOrgByName(String orgName);
	SysOrg readSysOrgByCode(String orgCode);


	int checkOrgCodeNotSelf(String orgCode, String orgFlow);

	int checkOrgNameNotSelf(String orgName, String orgFlow);

	List<SysOrg> searchOrgNotSelfAndNotCountryAndNotProvince(String flow);

	List<String> getOrgFlowsBySysOrgs(List<SysOrg> orgs);

	int confirmRole(String userFlow,SysOrg sysOrg);

	//过滤非培养单位数据
	List<SysOrg> searchTrainOrgList();

	int updateOrgSubmit(Map<String,Object> map);

	int saveOrgSubmit(List<String> orgFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateHospitalNotSubmit(List<String> userFlowList);

    List<SysOrg> searchOrgNotCountryOrg(SysOrg sysorg);

    List<SysOrg> searchSysOrgOrder(List<String> orgLevels);

    /**
     * @Department：研发部
     * @Description 查询基地列表信息
     * @Author fengxf
     * @Date 2020/9/2
     */
    List<SysOrgExt> searchOrgListByParam(SysOrgExt sysOrgExt);

	/**
	 * @Department：研发部
	 * @Description 查询基地下协同基地的数量
	 * @Author fengxf
	 * @Date 2020/9/2
	 */
	List<Map<String, String>> getJointOrgCountByParam(SysOrgExt sysOrgExt);

	/**
	 * @Department：研发部
	 * @Description 查询高校下的培训基地信息
	 * @Author fengxf
	 * @Date 2020/10/12
	 */
	List<SysOrg> searchUniversityOrgList(String workOrgId);

	/**
	 * @Department：研发部
	 * @Description 除当前医院的剩下医院
	 * @Author Zjie
	 * @Date 0027, 2020年11月27日
	 */
	List<SysOrg> searchOrgNew(SysOrg sysOrg);

	/**
	 * @Department：研发部
	 * @Description 市局查询基地和协同基地信息
	 * @Author fengxf
	 * @Date 2020/12/10
	 */
	List<SysOrg> searchOrgAndJointOrgList(SysOrg sysOrg);

    List<SysOrg> searchOrgListNew(SysOrg sysorg);

	/**
	 * 查询派送单位
	 * @return
	 */
	List<SysOrg> searchOrgWork();

	List<SysOrg> searchOrgNotJointOrg(SysOrg sysOrg, List<String> orgLevelList);

	List<SysOrg> orgGxList();

	List<SysOrg> searchOrgByJoin(SysOrgExt sysOrgExt);

	List<ResOrgSpe> searchByCountOrg(String orgName);

	List<SysOrgExt> searchOrgListByParamNew(SysOrgExt sysOrgExt);

	List<Map<String, String>> getJointOrgCountByParamNew(SysOrgExt sysOrgExt);

	List<SysOrg> selectJointOrgAllList(SysOrg sysOrg);

    List<SysOrg> searchOrgListByCityId(String cityId);
}
