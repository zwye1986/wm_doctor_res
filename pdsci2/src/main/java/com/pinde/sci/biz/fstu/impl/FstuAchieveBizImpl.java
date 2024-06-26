package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAchieveAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuAchieveBiz;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-03-08.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FstuAchieveBizImpl implements IFstuAchieveBiz,IFstuAchieveAuthorBiz{
    @Autowired
    private IFstuAuditProcessBiz fstuAuditProcessBiz;
    @Autowired
    private SrmAchFileMapper fileMapper;
    @Autowired
    private FstuAchieveMapper fstuAchieveMapper;
    @Autowired
    private FstuAchieveAuthorMapper fstuAchieveAuthorMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private IFileBiz fileBiz;

    @Override
    public FstuAchieve readAchieve(String achieveFlow) {
        return fstuAchieveMapper.selectByPrimaryKey(achieveFlow);
    }


    @Override
    public int save(FstuAchieve achieve, List<FstuAchieveAuthor> authorList, List<PubFile> fileList, FstuAuditProcess auditProcess) {
        //判断科技编号是否为空，空则添加，不为空则修改
        if(StringUtil.isNotBlank(achieve.getAchieveFlow())){
            GeneralMethod.setRecordInfo(achieve, false);
            fstuAchieveMapper.updateByPrimaryKeySelective(achieve);
        }else{
            GeneralMethod.setRecordInfo(achieve, true);
            achieve.setAchieveFlow(PkUtil.getUUID());
            fstuAchieveMapper.insertSelective(achieve);
        }

        //科技作者的操作
        if(null != authorList && !authorList.isEmpty()){
            for(FstuAchieveAuthor author : authorList){
                if(StringUtil.isNotBlank(author.getAuthorFlow())){//修改作者
                    GeneralMethod.setRecordInfo(author, false);
                    author.setAchieveFlow(achieve.getAchieveFlow());
                    fstuAchieveAuthorMapper.updateByPrimaryKeySelective(author);
                }else{//新增作者
                    GeneralMethod.setRecordInfo(author, true);
                    //作者流水号
                    author.setAuthorFlow(PkUtil.getUUID());
                    //科技流水号！！！
                    author.setAchieveFlow(achieve.getAchieveFlow());
                    fstuAchieveAuthorMapper.insert(author);
                }
            }
        }
        for(PubFile pubFile : fileList) {
            //操作附件
            if (null != pubFile) {
                pubFile.setProductFlow(achieve.getAchieveFlow());
                if (StringUtil.isNotBlank(pubFile.getFileFlow())) {
                    GeneralMethod.setRecordInfo(pubFile, false);
                    pubFileMapper.updateByPrimaryKeySelective(pubFile);
                } else {
                    pubFile.setFileFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(pubFile, true);
                    pubFileMapper.insertSelective(pubFile);
                }
            }
        }
        if(null != auditProcess) {
            //操作成果过程
            auditProcess.setProcessFlow(PkUtil.getUUID());
            auditProcess.setRelFlow(achieve.getAchieveFlow());
            GeneralMethod.setRecordInfo(auditProcess, true);
            auditProcess.setOperTime(auditProcess.getModifyTime());
            fstuAuditProcessBiz.saveFsaProcess(auditProcess);
        }
        return GlobalConstant.ONE_LINE;
    }

    public int save(FstuAchieve achieve){
        if(StringUtil.isNotBlank(achieve.getAchieveFlow())){
            GeneralMethod.setRecordInfo(achieve, false);
            fstuAchieveMapper.updateByPrimaryKeySelective(achieve);
        }else{
            GeneralMethod.setRecordInfo(achieve, true);
            achieve.setAchieveFlow(PkUtil.getUUID());
            fstuAchieveMapper.insertSelective(achieve);
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public List<FstuAchieve> search(FstuAchieve achieve, List<String> deptFlows) {
        FstuAchieveExample example=new FstuAchieveExample();
        FstuAchieveExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != deptFlows && !deptFlows.isEmpty()){
            criteria.andDeptFlowIn(deptFlows);
        }
        if(null != achieve){
            if(StringUtil.isNotBlank(achieve.getAchieveName())){
                criteria.andAchieveNameLike("%"+achieve.getAchieveName()+"%");
            }
            if(StringUtil.isNotBlank(achieve.getProjSourceId())){
                criteria.andProjSourceIdEqualTo(achieve.getProjSourceId());
            }
            if(StringUtil.isNotBlank(achieve.getOperStatusId())){
                if("all".equals(achieve.getOperStatusId())){
                    List<String> ids = new ArrayList<>();
                    ids.add(ProStatusEnum.Apply.getId());
                    ids.add(ProStatusEnum.Passed.getId());
                    criteria.andOperStatusIdIn(ids);
                }else{
                    criteria.andOperStatusIdEqualTo(achieve.getOperStatusId());
                }
            }
            if(StringUtil.isNotBlank(achieve.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(achieve.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(achieve.getPrizedDate())){
                criteria.andPrizedDateLike("%"+achieve.getPrizedDate()+"%");
            }
            if(StringUtil.isNotBlank(achieve.getApplyUserName())){
                criteria.andApplyUserNameLike("%"+achieve.getApplyUserName()+"%");
            }
            if(StringUtil.isNotBlank(achieve.getDeptName())){
                criteria.andDeptNameLike("%"+achieve.getDeptName()+"%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fstuAchieveMapper.selectByExample(example);
    }



    @Override
    public String saveFileToDirs(MultipartFile file, String folderName,String flow){
        String path = GlobalConstant.FLAG_N;
        if(file.getSize() > 0){
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + folderName + File.separator+ dateString ;
            File fileDir = new File(newDir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }


            //删除原文件
            if(StringUtil.isNotBlank(flow)){
                PubFile oldFile = fileBiz.readFile(flow);
                String oldPath = oldFile.getFilePath();
                try {
                    File imgFile = new File(oldPath);
                    if(imgFile.exists()){
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + File.separator+dateString+File.separator+originalFilename;
        }

        return path;
    }

    @Override
    public int editAchieveAuthor(FstuAchieveAuthor author) {
        return 0;
    }

    @Override
    public List<FstuAchieveAuthor> searchAuthorList(FstuAchieveAuthor author) {
        FstuAchieveAuthorExample example = new FstuAchieveAuthorExample();
        FstuAchieveAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(author.getAuthorName())){
            criteria.andAuthorNameLike("%"+ author.getAuthorName() +"%");
        }
        if(StringUtil.isNotBlank(author.getAchieveFlow())){
            criteria.andAchieveFlowEqualTo(author.getAchieveFlow());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fstuAchieveAuthorMapper.selectByExample(example);
    }

    @Override
    public void deleteOneAuthor(FstuAchieveAuthor achieveAuthor) {
        if(achieveAuthor != null){
            achieveAuthor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(achieveAuthor, false);
            fstuAchieveAuthorMapper.updateByPrimaryKeySelective(achieveAuthor);
        }
    }

    @Override
    public void down(PubFile file, final HttpServletResponse response) throws Exception{
        /*文件是否存在*/
        Boolean fileExists = false;
        if(file !=null){
            byte[] data=null;
            long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
            if("1".equals(file.getFileUpType()) && StringUtil.isNotBlank(file.getFilePath())){
                /*获取文件物理路径*/
                String filePath =file.getFilePath();
                File downLoadFile = new File(file.getFilePath());
                /*文件是否存在*/
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = file.getFileName();
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                    if (StringUtil.isNotBlank(file.getFileSuffix())) {
                        fileName += "." + file.getFileSuffix();
                    }
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    fileExists = false;
                }
            }
        }else {
            fileExists = false;
        }
        if(!fileExists){
            /*设置页面编码为UTF-8*/
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public void updateAchieveStatus(FstuAchieve achieve, FstuAuditProcess process) {
        if(StringUtil.isNotBlank(achieve.getAchieveFlow())){
            GeneralMethod.setRecordInfo(achieve, false);
            fstuAchieveMapper.updateByPrimaryKeySelective(achieve);
        }
        fstuAuditProcessBiz.saveFsaProcess(process);
    }
}
