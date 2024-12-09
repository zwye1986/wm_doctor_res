package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.StuUserResumeMapper;
import com.pinde.sci.dao.res.StuUserExtMapper;
import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.res.StuUserExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class StuUserResumeBizImpl implements IStuUserResumeBiz {
    @Autowired
    private StuUserResumeMapper stuUserResumeMapper;
    @Autowired
    private StuUserExtMapper stuUserExtMapper;


    @Override
    public StuUserResume getStuUserByKey(String resumeFlow) {
        return stuUserResumeMapper.selectByPrimaryKey(resumeFlow);
    }

    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
        String path = "";
        if (file.getSize() > 0) {
            //创建目录

            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))  + File.separator + folderName;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    logger.error("", e);
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = "/" + folderName + "/" + originalFilename;
        }

        return path;
    }
    @Override
    public List<StuUserExt> searchUser(Map<String, Object> mp) {
        return stuUserExtMapper.searchUser(mp);
    }

    private static Logger logger = LoggerFactory.getLogger(StuUserResumeBizImpl.class);

}
 