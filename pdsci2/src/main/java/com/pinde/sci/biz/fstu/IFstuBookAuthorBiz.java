package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.FstuBookAuthor;

import java.util.List;

public interface IFstuBookAuthorBiz {
    /**
     * 修改一条著作作者
      * @param author
     */
    void editBookAthor(FstuBookAuthor author);

    /**
     * 查询作者
     * @return
     */
    List<FstuBookAuthor> searchAuthorList(FstuBookAuthor author);
}
