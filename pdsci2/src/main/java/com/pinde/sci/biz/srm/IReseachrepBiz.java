package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IReseachrepBiz {
    /**
     * 保存研究报告
     *
     * @param reseachrep
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(SrmAchReseachrep reseachrep, List<SrmAchReseachrepAuthor> authorList, SrmAchFile file, SrmAchProcess process);

    /**
     * 获取研究报告列表
     *
     * @param reseachrep
     * @param childOrgList
     * @return
     */
    List<SrmAchReseachrep> search(SrmAchReseachrep reseachrep, List<SysOrg> childOrgList,List<String> reseachrepFlowList );

    /**
     * 获取研究报告
     *
     * @param reseachrepFlow
     * @return
     */
    SrmAchReseachrep readReseachrep(String reseachrepFlow);

    /**
     * 修改研究报告及作者
     *
     * @param reseachrep
     * @return
     */
    int edit(SrmAchReseachrep reseachrep, List<SrmAchReseachrepAuthor> authorList, SrmAchFile file);

    /**
     * 更新状态
     *
     * @param reseachrep
     * @param process
     * @return
     */
    int updateReseachrepStatus(SrmAchReseachrep reseachrep, SrmAchProcess process);

}
