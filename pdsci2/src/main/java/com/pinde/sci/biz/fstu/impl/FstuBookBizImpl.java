package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.fstu.IFstuBookAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuBookBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.FstuBookAuthorMapper;
import com.pinde.sci.dao.base.FstuBookMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class FstuBookBizImpl implements IFstuBookBiz,IFstuBookAuthorBiz {
    @Autowired
    private FstuBookMapper bookMapper;
    @Autowired
    private FstuBookAuthorMapper bookAuthorMapper;
    @Autowired
    private IFstuAuditProcessBiz fstuAuditProcessBiz;
    @Autowired
    private PubFileMapper pubFileMapper;

    @Override
    public FstuBook readBook(String bookFlow) {
        FstuBook book = null;
        if(StringUtil.isNotBlank(bookFlow)){
            book = bookMapper.selectByPrimaryKey(bookFlow);
        }
        return book;
    }

    @Override
    public List<FstuBook> searchByDeptFlow(FstuBook book, List<String> deptFlows) {
        FstuBookExample example=new FstuBookExample();
        FstuBookExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != deptFlows && !deptFlows.isEmpty()){
            criteria.andDeptFlowIn(deptFlows);
        }
        if(book != null) {
            if (StringUtil.isNotBlank(book.getBookName())) {
                criteria.andBookNameLike("%" + book.getBookName() + "%");
            }
            if (StringUtil.isNotBlank(book.getOperStatusId())) {
                List<String> statusList = Arrays.asList(book.getOperStatusId().split(","));
                criteria.andOperStatusIdIn(statusList);
            }
            if (StringUtil.isNotBlank(book.getProjSourceId())) {
                criteria.andProjSourceIdEqualTo(book.getProjSourceId());
            }
            if (StringUtil.isNotBlank(book.getApplyOrgFlow())) {
                criteria.andApplyOrgFlowEqualTo(book.getApplyOrgFlow());
            }
            if (StringUtil.isNotBlank(book.getPublishDate())) {
                criteria.andPublishDateEqualTo(book.getPublishDate());
            }
            if (StringUtil.isNotBlank(book.getTypeId())) {
                criteria.andTypeIdEqualTo(book.getTypeId());
            }
            if (StringUtil.isNotBlank(book.getApplyUserFlow())) {
                criteria.andApplyUserFlowEqualTo(book.getApplyUserFlow());
            }
            if (StringUtil.isNotBlank(book.getApplyUserName())) {
                criteria.andApplyUserNameLike("%" + book.getApplyUserName() + "%");
            }
            if (StringUtil.isNotBlank(book.getPublishDate())) {
                criteria.andPublishDateLike("%" + book.getPublishDate() + "%");
            }
            if (StringUtil.isNotBlank(book.getDeptName())) {
                criteria.andDeptNameLike("%" + book.getDeptName() + "%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return bookMapper.selectByExample(example);
    }

    @Override
    public List<FstuBook> search(FstuBook book, List<SysOrg> childOrgList) {
        FstuBookExample example=new FstuBookExample();
        FstuBookExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<String> orgFlowList=new ArrayList<String>();
        if(null!=childOrgList && !childOrgList.isEmpty()){
            for(SysOrg org:childOrgList){
                orgFlowList.add(org.getOrgFlow());
            }
            criteria.andApplyOrgFlowIn(orgFlowList);
        }
        if(book != null){
            if(StringUtil.isNotBlank(book.getBookName())){
                criteria.andBookNameLike("%"+book.getBookName()+"%");
            }
            if(StringUtil.isNotBlank(book.getOperStatusId())){
                List<String> statusList= Arrays.asList(book.getOperStatusId().split(","));
                criteria.andOperStatusIdIn(statusList);
            }
            if(StringUtil.isNotBlank(book.getProjSourceId())){
                criteria.andProjSourceIdEqualTo(book.getProjSourceId());
            }
            if(StringUtil.isNotBlank(book.getApplyOrgFlow())){
                criteria.andApplyOrgFlowEqualTo(book.getApplyOrgFlow());
            }
            if(StringUtil.isNotBlank(book.getPublishDate())){
                criteria.andPublishDateEqualTo(book.getPublishDate());
            }
            if(StringUtil.isNotBlank(book.getTypeId())){
                criteria.andTypeIdEqualTo(book.getTypeId());
            }
            if(StringUtil.isNotBlank(book.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(book.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(book.getApplyUserName())){
                criteria.andApplyUserNameLike("%"+book.getApplyUserName()+"%");
            }
            if(StringUtil.isNotBlank(book.getPublishDate())){
                criteria.andPublishDateLike("%"+book.getPublishDate()+"%");
            }
            if(InitConfig.getSysCfg("srm_local_type").equals(GlobalConstant.FLAG_Y)){
                if(StringUtil.isNotBlank(book.getDeptFlow())){
                    criteria.andDeptFlowEqualTo(book.getDeptFlow());
                }
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return bookMapper.selectByExample(example);
    }

    @Override
    public void save(FstuBook book, List<FstuBookAuthor> authorList, FstuAuditProcess process,List<PubFile> fileList) {
//判断著作编号是否为空，空则添加，不为空则修改
        if(StringUtil.isNotBlank(book.getBookFlow())){
            GeneralMethod.setRecordInfo(book, false);
            bookMapper.updateByPrimaryKeySelective(book);
        }else{
            GeneralMethod.setRecordInfo(book, true);
            book.setBookFlow(PkUtil.getUUID());
            bookMapper.insert(book);
        }

        //著作作者
        for(int i=0;i<authorList.size();i++){
            //判断著作作者的流水号是否为空，空则增加，不为空则修改
            if(StringUtil.isNotBlank(authorList.get(i).getAuthorFlow())){
                //修改记录状态
                GeneralMethod.setRecordInfo(authorList.get(i), false);
                //著作流水号
                authorList.get(i).setBookFlow(book.getBookFlow());
                bookAuthorMapper.updateByPrimaryKeySelective(authorList.get(i));
            }else{//新增作者
                GeneralMethod.setRecordInfo(authorList.get(i), true);
                //作者流水号
                authorList.get(i).setAuthorFlow(PkUtil.getUUID());
                //著作流水号
                authorList.get(i).setBookFlow(book.getBookFlow());
                bookAuthorMapper.insert(authorList.get(i));
            }
        }

        //操作附件
        for(PubFile pubFile : fileList) {
            //操作附件
            if (null != pubFile) {
                pubFile.setProductFlow(book.getBookFlow());
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
        //操作成果过程
        process.setProcessFlow(PkUtil.getUUID());
        process.setRelFlow(book.getBookFlow());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getModifyTime());
        fstuAuditProcessBiz.saveFsaProcess(process);
    }

    @Override
    public int save(FstuBook book) {
        if(StringUtil.isNotBlank(book.getBookFlow())){
            GeneralMethod.setRecordInfo(book, false);
            bookMapper.updateByPrimaryKeySelective(book);
        }else{
            GeneralMethod.setRecordInfo(book, true);
            book.setBookFlow(PkUtil.getUUID());
            bookMapper.insert(book);
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public void updateBookStatus(FstuBook book, FstuAuditProcess process) {
        if(StringUtil.isNotBlank(book.getBookFlow())){
            GeneralMethod.setRecordInfo(book, false);
            bookMapper.updateByPrimaryKeySelective(book);
        }
        fstuAuditProcessBiz.saveFsaProcess(process);
    }

    @Override
    public int edit(FstuBook book, List<FstuBookAuthor> authorList,PubFile file) {
        if(StringUtil.isNotBlank(book.getBookFlow())){
            GeneralMethod.setRecordInfo(book, false);
            bookMapper.updateByPrimaryKeySelective(book);
        }
        //作者
        if(authorList != null && !authorList.isEmpty()){
            for(FstuBookAuthor author : authorList){
                GeneralMethod.setRecordInfo(author, false);
                bookAuthorMapper.updateByPrimaryKeySelective(author);
            }
        }
        //附件
        if(null != file){
            file.setProductFlow(book.getBookFlow());
            if(StringUtil.isNotBlank(file.getFileFlow())){
                GeneralMethod.setRecordInfo(file, false);
                pubFileMapper.updateByPrimaryKeySelective(file);
            }else{
                file.setFileFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(file, true);
                pubFileMapper.insertSelective(file);
            }
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public void editBookAthor(FstuBookAuthor author) {
        if(StringUtil.isNotBlank(author.getAuthorFlow())){
            GeneralMethod.setRecordInfo(author, false);
        }
        bookAuthorMapper.updateByPrimaryKeySelective(author);
    }

    @Override
    public List<FstuBookAuthor> searchAuthorList(FstuBookAuthor author) {
        FstuBookAuthorExample example = new FstuBookAuthorExample();
        FstuBookAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(author.getAuthorName())){
            criteria.andAuthorNameLike(author.getAuthorName());
        }
        if(StringUtil.isNotBlank(author.getBookFlow())){
            criteria.andBookFlowEqualTo(author.getBookFlow());
        }
        example.setOrderByClause("TYPE_NAME");
        return bookAuthorMapper.selectByExample(example);
    }
}
