package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface ISatBiz {

    /**
     * 读科技信息
     *
     * @param satFlow
     * @return
     */
    SrmAchSat readSat(String satFlow);

    /**
     * 保存和修改科技信息及作者
     *
     * @param sat
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(SrmAchSat sat, List<SrmAchSatAuthor> authorList, SrmAchFile file, SrmAchProcess process);

    int save(SrmAchSat sat);

    /**
     * 修改状态
     *
     * @param sat
     * @param process
     * @return
     */
    int updateSatStatus(SrmAchSat sat, SrmAchProcess process);

    /**
     * @param sat
     * @param childOrgList
     * @return
     */
    List<SrmAchSat> search(SrmAchSat sat, List<SysOrg> childOrgList);

    /**
     * 修改科技及作者
     *
     * @param sat
     * @param authorList
     * @return
     */
    int edit(SrmAchSat sat, List<SrmAchSatAuthor> authorList, SrmAchFile file);

    List<SrmAchSat> search(SrmAchSat sat, List<SysOrg> childOrgList,List<String> satFlows);

    SrmAchSat satIsExist(String approvalCode);
}
