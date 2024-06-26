package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.fstu.IFstuPatentAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuPatentBiz;
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
public class FstuPatentBizImpl implements IFstuPatentBiz,IFstuPatentAuthorBiz {
    @Autowired
    private IFstuAuditProcessBiz fstuAuditProcessBiz;
    @Autowired
    private SrmAchPatentMapper patentMapper;
    @Autowired
    private SrmAchPatentAuthorMapper patentAuthorMapper;
    @Autowired
    private SrmAchFileMapper fileMapper;
    @Autowired
    private FstuPatentMapper fstuPatentMapper;
    @Autowired
    private FstuPatentAuthorMapper fstuPatentAuthorMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private IFileBiz fileBiz;

    @Override
    public FstuPatent readPatent(String patentFlow) {
        return fstuPatentMapper.selectByPrimaryKey(patentFlow);
    }


    @Override
    public int save(FstuPatent patent, List<FstuPatentAuthor> authorList, List<PubFile> fileList, FstuAuditProcess auditProcess) {
        //判断科技编号是否为空，空则添加，不为空则修改
        if(StringUtil.isNotBlank(patent.getPatentFlow())){
            GeneralMethod.setRecordInfo(patent, false);
            fstuPatentMapper.updateByPrimaryKeySelective(patent);
        }else{
            GeneralMethod.setRecordInfo(patent, true);
            patent.setPatentFlow(PkUtil.getUUID());
            fstuPatentMapper.insertSelective(patent);
        }

        //科技作者的操作
        if(null != authorList && !authorList.isEmpty()){
            for(FstuPatentAuthor author : authorList){
                if(StringUtil.isNotBlank(author.getAuthorFlow())){//修改作者
                    GeneralMethod.setRecordInfo(author, false);
                    author.setPatentFlow(patent.getPatentFlow());
                    fstuPatentAuthorMapper.updateByPrimaryKeySelective(author);
                }else{//新增作者
                    GeneralMethod.setRecordInfo(author, true);
                    //作者流水号
                    author.setAuthorFlow(PkUtil.getUUID());
                    //科技流水号！！！
                    author.setPatentFlow(patent.getPatentFlow());
                    fstuPatentAuthorMapper.insert(author);
                }
            }
        }

        //操作附件
        for(PubFile pubFile : fileList) {
            //操作附件
            if (null != pubFile) {
                pubFile.setProductFlow(patent.getPatentFlow());
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
            auditProcess.setProcessFlow(PkUtil.getUUID());
            auditProcess.setRelFlow(patent.getPatentFlow());
            GeneralMethod.setRecordInfo(auditProcess, true);
            auditProcess.setOperTime(auditProcess.getModifyTime());
            fstuAuditProcessBiz.saveFsaProcess(auditProcess);
        }
        return GlobalConstant.ONE_LINE;
    }

    public int save(FstuPatent patent){
        if(StringUtil.isNotBlank(patent.getPatentFlow())){
            GeneralMethod.setRecordInfo(patent, false);
            fstuPatentMapper.updateByPrimaryKeySelective(patent);
        }else{
            GeneralMethod.setRecordInfo(patent, true);
            patent.setPatentFlow(PkUtil.getUUID());
            fstuPatentMapper.insertSelective(patent);
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public void updatePatentStatus(FstuPatent patent,FstuAuditProcess process){
        if(StringUtil.isNotBlank(patent.getPatentFlow())){
            GeneralMethod.setRecordInfo(patent, false);
            fstuPatentMapper.updateByPrimaryKeySelective(patent);
        }
        fstuAuditProcessBiz.saveFsaProcess(process);
    }

    @Override
    public List<FstuPatent> search(FstuPatent patent, List<String> deptFlows) {
        FstuPatentExample example=new FstuPatentExample();
        FstuPatentExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != deptFlows && !deptFlows.isEmpty()){
            criteria.andDeptFlowIn(deptFlows);
        }
        if(null != patent){
            if(StringUtil.isNotBlank(patent.getPatentName())){
                criteria.andPatentNameLike("%"+patent.getPatentName()+"%");
            }
            if(StringUtil.isNotBlank(patent.getProjSourceId())){
                criteria.andProjSourceIdEqualTo(patent.getProjSourceId());
            }
            if(StringUtil.isNotBlank(patent.getOperStatusId())){
                if("all".equals(patent.getOperStatusId())){
                    List<String> ids = new ArrayList<>();
                    ids.add(ProStatusEnum.Apply.getId());
                    ids.add(ProStatusEnum.Passed.getId());
                    criteria.andOperStatusIdIn(ids);
                }else{
                    criteria.andOperStatusIdEqualTo(patent.getOperStatusId());
                }
            }
            if(StringUtil.isNotBlank(patent.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(patent.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(patent.getPrizedDate())){
                criteria.andPrizedDateLike("%"+patent.getPrizedDate()+"%");
            }
            if(StringUtil.isNotBlank(patent.getApplyUserName())){
                criteria.andApplyUserNameLike("%"+patent.getApplyUserName()+"%");
            }
            if(StringUtil.isNotBlank(patent.getDeptName())){
                criteria.andDeptNameLike("%"+patent.getDeptName()+"%");
            }
            if(StringUtil.isNotBlank(patent.getPatentChangeMan())){
                criteria.andPatentChangeManLike("%"+patent.getPatentChangeMan()+"%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fstuPatentMapper.selectByExample(example);
    }


    @Override
    public int edit(SrmAchPatent patent, List<SrmAchPatentAuthor> authorList, SrmAchFile file){
        if(StringUtil.isNotBlank(patent.getPatentFlow())){
            GeneralMethod.setRecordInfo(patent, false);
            patentMapper.updateByPrimaryKeySelective(patent);
        }
        //作者
        if(null != authorList && !authorList.isEmpty()){
            for(SrmAchPatentAuthor author : authorList){
                GeneralMethod.setRecordInfo(author, false);
                patentAuthorMapper.updateByPrimaryKeySelective(author);
            }
        }
        //附件
        if(file != null && StringUtil.isNotBlank(file.getFileFlow())){
            GeneralMethod.setRecordInfo(file, false);
            fileMapper.updateByPrimaryKeySelective(file);
        }
        return GlobalConstant.ONE_LINE;
    }



    @Override
    public int editPatentAuthor(FstuPatentAuthor achPatentAuthor) {
        return 0;
    }

    @Override
    public List<FstuPatentAuthor> searchAuthorList(FstuPatentAuthor author) {
        FstuPatentAuthorExample example = new FstuPatentAuthorExample();
        FstuPatentAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(author.getAuthorName())){
            criteria.andAuthorNameLike("%"+ author.getAuthorName() +"%");
        }
        if(StringUtil.isNotBlank(author.getPatentFlow())){
            criteria.andPatentFlowEqualTo(author.getPatentFlow());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fstuPatentAuthorMapper.selectByExample(example);
    }

    @Override
    public void deleteOneAuthor(FstuPatentAuthor patentAuthor) {
        if(patentAuthor != null){
            patentAuthor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(patentAuthor, false);
            fstuPatentAuthorMapper.updateByPrimaryKeySelective(patentAuthor);
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
}
