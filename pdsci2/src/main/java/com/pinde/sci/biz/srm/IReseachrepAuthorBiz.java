package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchReseachrepAuthor;

import java.util.List;

public interface IReseachrepAuthorBiz {

    /**
     * 删除
     *
     * @param author
     * @return
     */
    int editReseachrepAuthor(SrmAchReseachrepAuthor author);

    /**
     * 查询作者
     *
     * @param author
     * @return
     */
    List<SrmAchReseachrepAuthor> searchAuthorList(SrmAchReseachrepAuthor author);

    List<String> getReseachrepFlowByAuthor(SrmAchReseachrepAuthor author);
}
