package com.pinde.sci.biz.sys;

import com.pinde.core.model.PersonStaticExample;
import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysOrgExample;
import com.pinde.sci.model.sys.SysOrgExt;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 */
public interface IOrgBiz {

    SysOrg readSysOrg(String orgFlow);

    int addOrg(SysOrg org);

    int saveOrg(SysOrg org);

    List<SysOrg> searchOrg(SysOrg sysOrg);

    List<SysOrg> searchOrgIsAcc(SysOrg sysOrg);

    List<SysOrg> searchOrgList(SysOrg sysOrg);

    List<PersonStaticExample> searchOrgSession(SysOrg sysOrg);

	/**
	 * 查询全部单位
	 */
    List<SysOrg> queryAllSysOrg(SysOrg sysorg);
	
	/**
	 * 查询某个节点下所有子节点包过自身节点
	 * @param orgFlow
	 * @return
	 */
    List<SysOrg> searchChildrenOrgByOrgFlow(String orgFlow);


    List<SysOrg> searchOrgByClause(SysOrg sysorg, String orderClause);

    List<SysOrg> searchOrgWithBLOBs(SysOrg sysorg);

	List<SysOrg> searchSysOrg(SysOrg sysorg);

	List<SysOrg> searchSysOrgByName(SysOrg sysorg);

    List<SysOrg> searchOrgByExample(SysOrgExample example);

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

	SysOrg readSysOrgByName(String orgName);

	int confirmRole(String userFlow,SysOrg sysOrg);

	//过滤非培养单位数据
	List<SysOrg> searchTrainOrgList();

	int updateOrgSubmit(Map<String,Object> map);

	int saveOrgSubmit(List<String> orgFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateHospitalNotSubmit(List<String> userFlowList);


    List<SysOrg> searchSysOrgOrder(List<String> orgLevels);

	/**
	 * @Department：研发部
	 * @Description 查询高校下的培训基地信息
	 * @Author fengxf
	 * @Date 2020/10/12
	 */
	List<SysOrg> searchUniversityOrgList(String workOrgId);

	/**
	 * @Department：研发部
	 * @Description 市局查询基地和协同基地信息
	 * @Author fengxf
	 * @Date 2020/12/10
	 */
	List<SysOrg> searchOrgAndJointOrgList(SysOrg sysOrg);

    List<SysOrg> searchOrgListNew(SysOrg sysorg);


	List<SysOrg> searchOrgNotJointOrg(SysOrg sysOrg, List<String> orgLevelList);


	List<SysOrg> searchOrgByJoin(SysOrgExt sysOrgExt);

	List<ResOrgSpe> searchByCountOrg(String orgName);

	List<SysOrgExt> searchOrgListByParamNew(SysOrgExt sysOrgExt);

	List<Map<String, String>> getJointOrgCountByParamNew(SysOrgExt sysOrgExt);

	List<SysOrg> selectJointOrgAllList(SysOrg sysOrg);

    List<SysOrg> searchOrgListByCityId(String cityId);

    List<SysOrg> searchOrgNotCountryOrg(SysOrg sysorg);
}
