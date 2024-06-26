package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuAuditProcess;
import com.pinde.sci.model.mo.FstuSat;
import com.pinde.sci.model.mo.FstuStudy;

import java.util.List;

public interface IFstuAuditProcessBiz {
    /**
     * 查询某成果所有操作记录
     *
     */
    List<FstuAuditProcess> search(String relFlow);

    /**
     * 增加一条操作记录
     */
    int add(FstuAuditProcess auditProcess,FstuSat sat);

    int saveFsaProcess(FstuAuditProcess auditProcess);

    /**
     * 根据流水号和项目状态查询记录
     * @param relFlow
     * @param statusId
     * @return
     */
    List<FstuAuditProcess> searchFsaProcess(String relFlow, String statusId);
}
