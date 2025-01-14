package com.pinde.sci.dao.sys;

import com.pinde.core.model.PersonStaticExample;
import com.pinde.core.model.ResOrgSpe;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysOrgExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 机构扩展dao接口
 *
 * @author Administrator
 */
public interface SysOrgExtMapper {

    /**
     * 查询某个节点下的所有子节点包过自身
     *
     * @param orgFlow
     * @return
     */
    List<SysOrg> selectChildrenOrgListByOrgFlow(String orgFlow);

    /**
     * 查询某个节点下的所有子节点但不包括自身
     *
     * @param orgFlow
     * @return
     */
    List<SysOrg> selectChildrenOrgListByOrgFlowNotIncludeSelf(String orgFlow);

    /**
     * 查询没有被注册过角色的单位
     *
     * @return
     */
    List<SysOrg> selectOrgNoRegByRoleFlow(String roleFlow);

    /**
     * 查询主管部门
     *
     * @return
     */
    List<SysOrg> selectChargeOrgList();

    /**
     * 查询基地及协同基地
     *
     * @param paramMap
     * @return
     */
    List<SysOrg> searchJsResOrgWithJointOrg(Map<String, Object> paramMap);

    /**
     * 查询省厅下基地及协同基地
     *
     * @param map
     * @return
     */
    List<SysOrg> searOrgTeacherRoleCheckUser(Map<String, Object> map);

    /**
     * 查询org_type_id='hospital'和不是'hospital'的org
     */
    List<SysOrg> searchOrgs(Map<String, Object> map);

    List<SysOrg> getUniOrgs(Map<String, Object> paramMap);
    List<SysOrg> getUniBackTrainOrgs(Map<String, Object> paramMap);

    List<SysOrg> searchJointOrgsByOrg(String orgFlow);

    List<PersonStaticExample> searchJointOrgsSession(String orgFlow);

    List<SysOrg> searchOrgNotSelfAndNotCountryAndNotProvince(@Param("orgFlow") String orgFlow);

    List<SysOrg> confirmRole(Map<String, Object> map);

    //湖北 根据管理员orgFlow查询高校的基地列表
    List<SysOrg> searchOrgs4hbUniversity(@Param("workOrgId") String workOrgId);

    int updateOrgSubmit(Map<String,Object> map);

    int saveOrgSubmit(List<String> orgFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateHospitalNotSubmit(List<String> userFlowList);

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
     * @Description 查询高校下培训基地信息
     * @Author fengxf
     * @Date 2020/10/12
     */
    List<SysOrg> searchUniversityOrgList(@Param("workOrgId") String workOrgId);

    /**
     * @Department：研发部
     * @Description 市局查询基地和协同基地信息
     * @Author fengxf
     * @Date 2020/12/10
     */
    List<SysOrg> searchOrgAndJointOrgList(SysOrg sysOrg);

    List<SysOrg> searchOrgListNew(Map<String,Object> param);

    List<SysOrg> searchOrgNotJointOrg(Map<String, Object> paramMap);

    List<SysOrg> orgGxList();

    List<SysOrgExt> searchBase(@Param("baseCode") String baseCode);

    SysOrgExt searchOrgFlow(@Param("orgFlow") String orgFlow);

    List<SysOrgExt> searchOrgList();

    List<SysOrg> searchOrgByJoin(SysOrgExt sysOrgExt);

    List<ResOrgSpe> searchByCountOrg(@Param("orgName") String orgName);

    List<SysOrgExt> searchOrgListByParamNew(SysOrgExt sysOrgExt);

    List<Map<String, String>> getJointOrgCountByParamNew(SysOrgExt sysOrgExt);
}
