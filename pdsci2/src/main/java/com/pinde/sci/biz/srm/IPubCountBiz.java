package com.pinde.sci.biz.srm;


import com.pinde.sci.form.srm.PubProjCount;
import com.pinde.sci.model.mo.AidTalents;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.pub.PubProjCountExt;
import com.pinde.sci.model.srm.SrmAchCountExt;

import java.util.List;
import java.util.Map;

public interface IPubCountBiz {

    /**
     * 卫生局待办事项统计
     *
     * @param org
     * @return
     */
    List<Map<String, Integer>> selectCountProjGlobal(SysOrg org);

    /**
     * 主管部门待办事项统计
     *
     * @param org
     * @return
     */
    List<Map<String, Integer>> selectCountProjCharge(SysOrg org);

    /**
     * 医院待办事项统计
     *
     * @param org
     * @return
     */
    List<Map<String, Integer>> selectCountProjLocal(SysOrg org);

    /**
     * 医院待审核成果统计
     *
     * @param org
     * @return
     */
    Map<String, Integer> selectCountSrmAchLocal(SysOrg org);

    /**
     * 根据该单位的流水号查询该单位下参加立项评审的项目的总数
     *
     * @return
     */
    int selectProjInExpertAll();

    /**
     * 根据该单位的流水号查询该单位下立项通过的项目的总数
     *
     * @return
     */
    int selectProjApproveAll();

    /**
     * 根据该单位的流水号查询该单位下立项不通过的项目的总数
     *
     * @return
     */
    int selectProjNotApproveAll();

    /**
     * 根据该单位的流水号查询该单位下通过申报审核的项目的总数
     *
     * @return
     */
    int selectProjApply();

    /**
     * 根据该单位的流水号查询该单位下通过验收报告审核的项目总数
     *
     * @return
     */
    int selectProjComplete();

    /**
     * 根据该单位的流水号查询该单位下一级所有单位分别承担的项目数
     *
     * @return
     */
    List<PubProjCountExt> selectProjGroupByOrg();

    /**
     * 根据该单位的流水号查询该单位下一级所有单位分别承担的成果种类及数量
     *
     * @return
     */
    List<SrmAchCountExt> selectSrmAchByOrg();

    /**
     * 根据该单位的流水号查询该单位下一集所有单位承担某一种成果的数量
     *
     * @param table
     * @param tableName
     * @return
     */
    List<SrmAchCountExt> selectSrmAchByTypeId(String table, String tableName, Boolean sumFlag);

    /**
     * 查询当前登录者所属机构下属所有重点人才的记录
     *
     * @param aidTalents
     * @return
     */
    List<AidTalents> selectAidTalentsByOrg(AidTalents aidTalents);

    /**
     * 查询当前登录者所属机构下待审核的人员
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectRegedUser(SysUser sysUser);

    /**
     * 院版查询待预算审核的项目数量
     *
     * @return
     */
    Integer findDealBudgetAuditProjCount();

    /**
     * 院版查询待报销审核的数量
     *
     * @return
     */
    Integer findDealPaymentAuditCount(String Scope,String status);

    List<PubProjCount> statisticsProjByTypeId(Map<String,Object> map);
}
