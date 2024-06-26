package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuAchieveAuthor;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public interface IFstuAchieveAuthorBiz {
    /**
     * 删除一成果作者
     * @param author
     */
    int editAchieveAuthor(FstuAchieveAuthor author);

    /**
     * 查询作者
     * @param author
     * @return
     */
    List<FstuAchieveAuthor> searchAuthorList(FstuAchieveAuthor author);
    /**
     * 删除一成果作者
     */
    void deleteOneAuthor(FstuAchieveAuthor achieveAuthor);
}
