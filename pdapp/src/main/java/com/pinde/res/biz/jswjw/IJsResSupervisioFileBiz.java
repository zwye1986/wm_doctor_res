package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.JsresSupervisioFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IJsResSupervisioFileBiz {
    /**
     * 检查上传图片的类型及大小
     *
     * @param uploadFile
     * @return
     */
    String checkImg(MultipartFile uploadFile);

    /**
     * 保存文件至指定的目录
     *
     * @param oldFolderName
     * @param file
     * @param folderName
     * @return
     */
    String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String orgFlow, String planYear, String itemId);

    List<JsresSupervisioFile> searchSubjectFile(String subjectYear, String subjectFlow, String speId, String userFlow);

    /**
     * 删除督导基地上传的文件信息
     *
     * @param recordFlow
     */
    void deleteByPrimaryKey(String recordFlow);
}
