package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public interface IFstuPatentBiz {
    /**
     * 读科技报奖信息
     *
     * @param patentFlow
     * @return
     */
    FstuPatent readPatent(String patentFlow);

    /**
     * 保存和修改科技信息及作者
     *
     * @param patent
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(FstuPatent patent, List<FstuPatentAuthor> authorList, List<PubFile> fileList, FstuAuditProcess process);

    int save(FstuPatent patent);

    /**
     * 修改状态
     *
     * @param patent
     * @param process
     * @return
     */
    void updatePatentStatus(FstuPatent patent,FstuAuditProcess process);

    /**
     * 根据报奖基本信息和部门查询科技报奖
     * @param patent
     * @return
     */
    List<FstuPatent> search(FstuPatent patent, List<String> deptFlows);

    /**
     * 修改科技及作者
     *
     * @param patent
     * @param authorList
     * @return
     */
    int edit(SrmAchPatent patent, List<SrmAchPatentAuthor> authorList, SrmAchFile file);


    /**
     * 下载文件
     */
    public void down(PubFile doc,final HttpServletResponse response) throws Exception;
}
