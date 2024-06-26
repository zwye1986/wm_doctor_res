package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IThesisBiz {
    /**
     * 保存
     *
     * @param thesis
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(SrmAchThesis thesis, List<SrmAchThesisAuthor> authorList, List<SrmAchFile> fileList, SrmAchProcess process);

    int save(SrmAchThesis thesis);

    /**
     * 加载列表
     *
     * @param achThesis
     * @param childOrgList 审核列表页面加载子机构
     * @return
     */
    List<SrmAchThesis> search(SrmAchThesis achThesis, List<SysOrg> childOrgList);

    /**
     * 查询一条论文
     *
     * @param thesisFlow
     * @return
     */
    SrmAchThesis readThesis(String thesisFlow);

    /**
     * 修改论文状态
     *
     * @return
     */
    void updateThesisStatus(SrmAchThesis thesis, SrmAchProcess process);

    /**
     * 论文及作者
     *
     * @param thesis
     * @param authorList
     * @param file
     * @return
     */
    int edit(SrmAchThesis thesis, List<SrmAchThesisAuthor> authorList, SrmAchFile file);

    List<SrmAchThesis> search2(SrmAchThesis thesis, List<SysOrg> currOrgChildList, List<String> thesisFlows);

    SrmAchThesis thesisIsExist(String thesisName);
}
