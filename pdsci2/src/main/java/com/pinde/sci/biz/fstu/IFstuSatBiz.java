package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IFstuSatBiz {

    /**
     * 读科技报奖信息
     *
     * @param satFlow
     * @return
     */
    FstuSat readSat(String satFlow);

    /**
     * 保存和修改科技信息及作者
     *
     * @param sat
     * @param authorList
     * @param file
     * @param process
     * @return
     */
    int save(FstuSat sat, List<FstuSatAuthor> authorList, List<PubFile> fileList, FstuAuditProcess process);

    int save(FstuSat sat);

    /**
     * 修改状态
     *
     * @param sat
     * @param process
     * @return
     */
    int updateSatStatus(SrmAchSat sat, SrmAchProcess process);

    /**
     * 根据报奖基本信息和部门查询科技报奖
     * @param sat
     * @return
     */
    List<FstuSat> search(FstuSat sat, List<String> deptFlows);

    /**
     * 修改科技及作者
     *
     * @param sat
     * @param authorList
     * @return
     */
    int edit(SrmAchSat sat, List<SrmAchSatAuthor> authorList, SrmAchFile file);

    /**
     *保存文件至指定的目录并记录
     */
   // String saveFileToDirs(MultipartFile file, String folderName,String flow);
    /**
     * 下载文件
     */
    public void down(PubFile doc,final HttpServletResponse response) throws Exception;
}
