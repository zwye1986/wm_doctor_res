package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchProcess;

import java.util.List;

public interface ISrmAchProcessBiz {
    /**
     * 保存操作过程
     *
     * @param srmAchProcess
     * @return
     */
    int saveAchProcess(SrmAchProcess srmAchProcess);

    /**
     * 查询操作流程
     *
     * @param achFlow
     * @param statusId
     * @return
     */
    List<SrmAchProcess> searchAchProcess(String achFlow, String statusId);

    /**
     * 成果记录查询
     *
     * @param achProcess
     * @return
     */
    List<SrmAchProcess> searchAchProcess(SrmAchProcess achProcess);
}
