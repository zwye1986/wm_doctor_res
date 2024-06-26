package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISrmAchFileBiz {

    /**
     * 查询成果附件
     *
     * @param thesisFlow
     * @return
     */
    List<SrmAchFile> searchSrmAchFile(String thesisFlow);

    /**
     * 读取成果上传文件
     *
     * @param fileFlow
     * @return
     */
    SrmAchFile readAchFile(String fileFlow);

    String saveFileToDirs(MultipartFile file, String folderName, String flow);

    int deleteFile(SrmAchFile file);
}
