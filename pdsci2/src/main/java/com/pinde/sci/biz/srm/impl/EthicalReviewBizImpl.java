package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.IEthicalReviewBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.base.SrmIrbApplyMapper;
import com.pinde.sci.enums.srm.SrmIrbTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class EthicalReviewBizImpl implements IEthicalReviewBiz {

    @Autowired
    private PubProjMapper pubProjMapper;
    @Autowired
    private SrmIrbApplyMapper srmIrbApplyMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private IFileBiz fileBiz;

    @Override
    public List<PubProj> getPubProjInfo(PubProj proj) {

        PubProjExample example  = new PubProjExample();
        PubProjExample.Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsEthicalFlagEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(proj.getProjName())){
            criteria.andProjNameLike("%"+proj.getProjName()+"%");
        }
        if(StringUtil.isNotBlank(proj.getProjDeclarerFlow())){
            criteria.andProjDeclarerFlowEqualTo(proj.getProjDeclarerFlow());
        }
        if(StringUtil.isNotBlank(proj.getProjSecondSourceId())){
            criteria.andProjSecondSourceIdEqualTo(proj.getProjSecondSourceId());
        }

        example.setOrderByClause("CREATE_TIME DESC");

        return pubProjMapper.selectByExample(example);
    }

    @Override
    public List<SrmIrbApply> searchSrmIrbApply(String projFlow){
        SrmIrbApplyExample example = new SrmIrbApplyExample();
        if(StringUtil.isNotBlank(projFlow)){
            example.createCriteria().andProjFlowEqualTo(projFlow);
        }
        return srmIrbApplyMapper.selectByExample(example);
    }

    @Override
    public int editFile(PubFile file) {
        if(file!=null){
            if(StringUtil.isNotBlank(file.getFileFlow())){//修改
                GeneralMethod.setRecordInfo(file, false);
                PubFileExample example = new PubFileExample();
                example.createCriteria().andProductFlowEqualTo(file.getProductFlow());
                GeneralMethod.setRecordInfo(file, false);
                return pubFileMapper.updateByExample(file,example);
            }else{//新增
                file.setFileFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(file, true);
                return pubFileMapper.insertSelective(file);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }
    @Override
    public int addFile(SrmIrbApply srmIrbApply,MultipartFile file){

        if(StringUtil.isNotBlank(srmIrbApply.getIrbFlow())){//更新
            PubFile pubFile = new PubFile();
            if(!file.isEmpty() && file.getSize()>0){
                String originalFilename = file.getOriginalFilename();//文件名
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);//后缀名

                pubFile.setFileName(originalFilename);
                pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                String oldPath = "";
                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                    oldPath = pubFile.getFilePath();
                }
                String resultPath = saveFileToDirs(oldPath, file, "SrmIrbFile");

                pubFile.setProductFlow(srmIrbApply.getIrbFlow());
                pubFile.setProductType("伦理审查附件");
                pubFile.setFilePath(resultPath);//设置文件路径
                fileBiz.editFile(pubFile);//保存文件
            }
            srmIrbApply.setIrbTypeName(SrmIrbTypeEnum.getNameById(srmIrbApply.getIrbTypeId()));
            GeneralMethod.setRecordInfo(srmIrbApply, false);
            return srmIrbApplyMapper.updateByPrimaryKeySelective(srmIrbApply);
        }else {//新增
            PubFile pubFile = new PubFile();
            String key = PkUtil.getUUID();
            if(!file.isEmpty() && file.getSize()>0){
                String originalFilename = file.getOriginalFilename();//文件名
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);//后缀名

                pubFile.setFileName(originalFilename);
                pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                String oldPath = "";
                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                    oldPath = pubFile.getFilePath();
                }
                String resultPath = saveFileToDirs(oldPath, file, "SrmIrbFile");

                pubFile.setProductType("伦理审查附件");
                pubFile.setFilePath(resultPath);//设置文件路径
                srmIrbApply.setIrbFlow(key);//主键
                pubFile.setProductFlow(srmIrbApply.getIrbFlow());
                fileBiz.editFile(pubFile);//保存文件
            }
            srmIrbApply.setIrbFlow(key);
            srmIrbApply.setIrbTypeName(SrmIrbTypeEnum.getNameById(srmIrbApply.getIrbTypeId()));
            GeneralMethod.setRecordInfo(srmIrbApply, true);
            return srmIrbApplyMapper.insert(srmIrbApply);
        }
    }

    @Override
    public int updateFile(SrmIrbApply srmIrbApply,MultipartFile file,String fileFlow) {

        if(StringUtil.isNotBlank(srmIrbApply.getIrbFlow())){//更新
            PubFile pubFile = new PubFile();
            if(!file.isEmpty() && file.getSize()>0){
                String originalFilename = file.getOriginalFilename();//文件名
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);//后缀名

                pubFile.setFileName(originalFilename);
                pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                String oldPath = "";
                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                    oldPath = pubFile.getFilePath();
                }
                String resultPath = saveFileToDirs(oldPath, file, "SrmIrbFile");

                pubFile.setFileFlow(fileFlow);
                pubFile.setProductFlow(srmIrbApply.getIrbFlow());
                pubFile.setProductType("伦理审查附件");
                pubFile.setFilePath(resultPath);//设置文件路径
                fileBiz.editFile(pubFile);//保存文件
            }
            srmIrbApply.setIrbTypeName(SrmIrbTypeEnum.getNameById(srmIrbApply.getIrbTypeId()));
            GeneralMethod.setRecordInfo(srmIrbApply, false);
            return srmIrbApplyMapper.updateByPrimaryKeySelective(srmIrbApply);
        }else {//新增
                 PubFile pubFile = new PubFile();
            String key = PkUtil.getUUID();
            if(!file.isEmpty() && file.getSize()>0){
                String originalFilename = file.getOriginalFilename();//文件名
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);//后缀名

                pubFile.setFileName(originalFilename);
                pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                String oldPath = "";
                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                    oldPath = pubFile.getFilePath();
                }
                String resultPath = saveFileToDirs(oldPath, file, "SrmIrbFile");

                pubFile.setProductType("伦理审查附件");
                pubFile.setFilePath(resultPath);//设置文件路径
                srmIrbApply.setIrbFlow(key);//主键
                pubFile.setProductFlow(srmIrbApply.getIrbFlow());
                fileBiz.editFile(pubFile);//保存文件
            }
            srmIrbApply.setIrbFlow(key);
            srmIrbApply.setIrbTypeName(SrmIrbTypeEnum.getNameById(srmIrbApply.getIrbTypeId()));
            GeneralMethod.setRecordInfo(srmIrbApply, true);
            return srmIrbApplyMapper.insert(srmIrbApply);
        }

    }
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
        String path = "";
        if (file.getSize() > 0) {
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName+ File.separator + dateString;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);//文件上传到服务器
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName+ File.separator + dateString;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = "/" + folderName + "/"+ File.separator + dateString+"/"+ originalFilename;
        }

        return path;
    }

}
