package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchThesisAuthor;

import java.util.List;

public interface IThesisAuthorBiz {

    /**
     * 修改、删除作者
     *
     * @param author
     * @return
     */
    int editAuthor(SrmAchThesisAuthor author);


    /**
     * 查询作者
     *
     * @param author
     * @return
     */
    List<SrmAchThesisAuthor> searchAuthorList(SrmAchThesisAuthor author);


}
