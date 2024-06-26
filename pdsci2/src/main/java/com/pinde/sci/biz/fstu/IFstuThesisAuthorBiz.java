package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.FstuThesisAuthor;

import java.util.List;

public interface IFstuThesisAuthorBiz {
    /**
     * 修改、删除作者
     *
     * @param author
     * @return
     */
    int editAuthor(FstuThesisAuthor author);


    /**
     * 查询作者
     *
     * @param author
     * @return
     */
    List<FstuThesisAuthor> searchAuthorList(FstuThesisAuthor author);

}
