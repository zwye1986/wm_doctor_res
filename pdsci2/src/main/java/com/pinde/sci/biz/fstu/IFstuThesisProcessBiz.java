package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.FstuAuditProcess;

import java.util.List;

public interface IFstuThesisProcessBiz {
    /**
     * 保存操作过程
     *
     * @param srmAchProcess
     * @return
     */
    int saveAchProcess(FstuAuditProcess srmAchProcess);

    /**
     * 查询操作流程
     *
     * @param achFlow
     * @param statusId
     * @return
     */
    List<FstuAuditProcess> searchAchProcess(String achFlow, String statusId);

    /**
     * 成果记录查询
     *
     * @param achProcess
     * @return
     */
    List<FstuAuditProcess> searchAchProcess(FstuAuditProcess achProcess);

}
