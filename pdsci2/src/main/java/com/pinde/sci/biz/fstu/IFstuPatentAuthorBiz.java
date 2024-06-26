package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuPatentAuthor;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public interface IFstuPatentAuthorBiz {
    /**
     * 编辑专利作者
     * @param achPatentAuthor
     */
    int editPatentAuthor(FstuPatentAuthor achPatentAuthor);

    /**
     * 查询作者
     * @param author
     * @return
     */
    List<FstuPatentAuthor> searchAuthorList(FstuPatentAuthor author);
    /**
     * 删除一利作者
     */
    void deleteOneAuthor(FstuPatentAuthor patentAuthor);
}
