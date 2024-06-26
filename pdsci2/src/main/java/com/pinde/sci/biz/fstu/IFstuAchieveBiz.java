package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
public interface IFstuAchieveBiz {
    /**
     * 读成果信息
     *
     * @param achieveFlow
     * @return
     */
    FstuAchieve readAchieve(String achieveFlow);

    /**
     * 保存和修改成果信息及作者
     *
     * @param achieve
     * @param authorList
     * @param fileList
     * @param process
     * @return
     */
    int save(FstuAchieve achieve, List<FstuAchieveAuthor> authorList, List<PubFile> fileList, FstuAuditProcess process);

    int save(FstuAchieve achieve);


    /**
     * 根据报奖基本信息和部门查询成果
     * @param achieve
     * @return
     */
    List<FstuAchieve> search(FstuAchieve achieve, List<String> deptFlows);


    /**
     *保存文件至指定的目录并记录
     */
    String saveFileToDirs(MultipartFile file, String folderName, String flow);
    /**
     * 下载文件
     */
    public void down(PubFile doc,final HttpServletResponse response) throws Exception;

    void updateAchieveStatus(FstuAchieve achieve,FstuAuditProcess process);
}
