package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IFstuThesisBiz {
    /**
     * 保存
     *
     * @param thesis
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(FstuThesis thesis, List<FstuThesisAuthor> authorList, FstuAuditProcess process,List<PubFile> fileList);

    int save(FstuThesis thesis);

    /**
     * 加载列表
     *
     * @param achThesis
     * @param childOrgList 审核列表页面加载子机构
     * @return
     */
    List<FstuThesis> search(FstuThesis achThesis, List<SysOrg> childOrgList);

    /**
     * 加载列表
     * @param thesis
     * @param deptFlows
     * @return
     */
    List<FstuThesis> searchByDeptFlow(FstuThesis thesis, List<String> deptFlows);

    /**
     * 查询一条论文
     *
     * @param thesisFlow
     * @return
     */
    FstuThesis readThesis(String thesisFlow);

    /**
     * 修改论文状态
     *
     * @return
     */
    void updateThesisStatus(FstuThesis thesis, FstuAuditProcess process);

    /**
     * 论文及作者
     *
     * @param thesis
     * @param authorList
     * @param file
     * @return
     */
    int edit(FstuThesis thesis, List<FstuThesisAuthor> authorList, PubFile file);

}
