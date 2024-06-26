package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IFstuBookBiz {
    /**
     * 根据bookFlow显示数据
     * @param bookFlow
     * @return
     */
    public FstuBook readBook(String bookFlow);

    /**
     * 加载著作列表
     * @param book
     * @param childOrgList 审核加载子机构
     * @return
     */
    public List<FstuBook> search(FstuBook book, List<SysOrg> childOrgList);

    /**
     * 加载列表
     * @param book
     * @param deptFlows
     * @return
     */
    List<FstuBook> searchByDeptFlow(FstuBook book, List<String> deptFlows);

    /**
     * 保存
     * @param book
     * @param authorList
     * @param file
     * @param process
     */
    public void save(FstuBook book, List<FstuBookAuthor> authorList, FstuAuditProcess process,List<PubFile> fileList);

    public int save(FstuBook book);

    /**
     * 修改状态（删除操作）
     * @param book
     * @param process
     */
    public void updateBookStatus(FstuBook book,FstuAuditProcess process);

    /**
     * 修改著作及作者
     * @param book
     * @param authorList
     * @return
     */
    int edit(FstuBook book, List<FstuBookAuthor> authorList,PubFile file);

}
