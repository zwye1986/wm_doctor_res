package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.InitConfig;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJsResSupervisioFileBiz;
import com.pinde.sci.dao.base.JsresSupervisioFileMapper;
import com.pinde.sci.model.mo.JsresSupervisioFile;
import com.pinde.sci.model.mo.JsresSupervisioFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class JsResSupervisioFileBizImpl implements IJsResSupervisioFileBiz {
    @Autowired
    private JsresSupervisioFileMapper supervisioFileMapper;

    @Override
    public String checkImg(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))) {
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))) {
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
            return "请上传 " + InitConfig.getSysCfg("res_file_support_suffix") + "格式的文件";
        }
        return GlobalConstant.FLAG_Y;//可执行保存
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String orgFlow, String planYear, String itemId) {
        String path = GlobalConstant.FLAG_N;
        if (file.getSize() > 0) {
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + orgFlow + File.separator + planYear + File.separator + itemId;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String originalFilename = PkUtil.getGUID() + suffix;
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
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
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/" + orgFlow + "/" + planYear + "/" + itemId + "/" + originalFilename;
        }

        return path;
    }

    @Override
    public void deleteByPrimaryKey(String recordFlow) {
        if (StringUtil.isNotBlank(recordFlow)) {
            supervisioFileMapper.deleteByPrimaryKey(recordFlow);
        }
    }

    @Override
    public List<JsresSupervisioFile> searchSubjectFile(String subjectYear, String subjectFlow, String speId,String userFlow) {
        JsresSupervisioFileExample example = new JsresSupervisioFileExample();
        JsresSupervisioFileExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(subjectFlow)) {
            criteria.andSubjectFlowEqualTo(subjectFlow);
        }
        if (StringUtil.isNotBlank(subjectYear)) {
            criteria.andPlanYearEqualTo(subjectYear);
        }
        if (StringUtil.isNotBlank(speId)) {
            criteria.andSpeIdEqualTo(speId);
        }
        if (StringUtil.isNotBlank(userFlow)) {
            criteria.andCreateUserFlowEqualTo(userFlow);
        }
        return supervisioFileMapper.selectByExample(example);
    }
}
