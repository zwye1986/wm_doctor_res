package com.pinde.sci.biz.fstu;


import com.pinde.sci.model.mo.PubFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface IFstuFileBiz {
    /**
     *保存文件至指定的目录并记录
     */
    String saveFileToDirs(MultipartFile file, String folderName, String flow);
    /**
     * 下载文件
     */
    public void down(PubFile file, final HttpServletResponse response) throws Exception;
}
