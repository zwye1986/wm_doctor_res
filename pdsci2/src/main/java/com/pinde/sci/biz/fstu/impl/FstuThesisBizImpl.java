package com.pinde.sci.biz.fstu.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuThesisAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuThesisBiz;
import com.pinde.sci.biz.fstu.IFstuThesisProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.FstuThesisAuthorMapper;
import com.pinde.sci.dao.base.FstuThesisMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FstuThesisBizImpl implements IFstuThesisBiz,IFstuThesisAuthorBiz{
    @Autowired
    private FstuThesisMapper thesisMapper;
    @Autowired
    private FstuThesisAuthorMapper thesisAuthorMapper;
    @Autowired
    private IFstuThesisProcessBiz thesisProcessBiz;
    @Autowired
    private PubFileMapper pubFileMapper;

    @Override
    public int editAuthor(FstuThesisAuthor author) {
        if(StringUtil.isNotBlank(author.getAuthorFlow())){
            GeneralMethod.setRecordInfo(author, false);
            return thesisAuthorMapper.updateByPrimaryKeySelective(author);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<FstuThesisAuthor> searchAuthorList(FstuThesisAuthor author) {
        FstuThesisAuthorExample example = new FstuThesisAuthorExample();
        com.pinde.sci.model.mo.FstuThesisAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(author.getAuthorName())){
            criteria.andAuthorNameLike("%" + author.getAuthorName() +"%");
        }
        if(StringUtil.isNotBlank(author.getThesisFlow())){
            criteria.andThesisFlowEqualTo(author.getThesisFlow());
        }
        example.setOrderByClause("TYPE_ID");
        return thesisAuthorMapper.selectByExample(example);
    }

    @Override
    public int save(FstuThesis thesis, List<FstuThesisAuthor> authorList, FstuAuditProcess process,List<PubFile> fileList) {
        //操作论文
        if(StringUtil.isNotBlank(thesis.getThesisFlow())){
            GeneralMethod.setRecordInfo(thesis, false);
            thesisMapper.updateByPrimaryKeySelective(thesis);
        }else{
            GeneralMethod.setRecordInfo(thesis, true);
            thesis.setThesisFlow(PkUtil.getUUID());
            thesisMapper.insert(thesis);
        }
        //操作论文作者
        for(int i=0;i<authorList.size();i++){
            if(StringUtil.isNotBlank(authorList.get(i).getAuthorFlow())){
                GeneralMethod.setRecordInfo(authorList.get(i), false);
                authorList.get(i).setThesisFlow(thesis.getThesisFlow());
                thesisAuthorMapper.updateByPrimaryKeySelective(authorList.get(i));
            }else{
                GeneralMethod.setRecordInfo(authorList.get(i), true);
                authorList.get(i).setAuthorFlow(PkUtil.getUUID());
                authorList.get(i).setThesisFlow(thesis.getThesisFlow());
                thesisAuthorMapper.insert(authorList.get(i));
            }
        }
        //操作附件
        for(PubFile pubFile : fileList) {
            //操作附件
            if (null != pubFile) {
                pubFile.setProductFlow(thesis.getThesisFlow());
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
        process.setRelFlow(thesis.getThesisFlow());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getModifyTime());
        thesisProcessBiz.saveAchProcess(process);
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public int save(FstuThesis thesis) {
        //操作论文
        if(StringUtil.isNotBlank(thesis.getThesisFlow())){
            GeneralMethod.setRecordInfo(thesis, false);
            thesisMapper.updateByPrimaryKeySelective(thesis);
        }else{
            GeneralMethod.setRecordInfo(thesis, true);
            thesis.setThesisFlow(PkUtil.getUUID());
            thesisMapper.insert(thesis);
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public List<FstuThesis> search(FstuThesis achThesis, List<SysOrg> childOrgList) {
        FstuThesisExample example = new FstuThesisExample();
        com.pinde.sci.model.mo.FstuThesisExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != childOrgList && !childOrgList.isEmpty()){
            List<String> orgFlowList=new ArrayList<String>();
            for(SysOrg org:childOrgList){
                orgFlowList.add(org.getOrgFlow());
            }
            criteria.andApplyOrgFlowIn(orgFlowList);
        }
        if(StringUtil.isNotBlank(achThesis.getThesisName())) {
            criteria.andThesisNameLike("%" + achThesis.getThesisName() + "%");
        }
        if(StringUtil.isNotBlank(achThesis.getPublishDate())){
            criteria.andPublishDateEqualTo(achThesis.getPublishDate());
        }
        if(StringUtil.isNotBlank(achThesis.getProjSourceId())){
            criteria.andProjSourceIdEqualTo(achThesis.getProjSourceId());
        }
        if(StringUtil.isNotBlank(achThesis.getApplyOrgFlow())){
            criteria.andApplyOrgFlowEqualTo(achThesis.getApplyOrgFlow());
        }
        if (StringUtil.isNotBlank(achThesis.getJourTypeId())) {
            criteria.andJourTypeIdLike("%" + achThesis.getJourTypeId() + "%");
        }
        if(StringUtil.isNotBlank(achThesis.getOperStatusId())){
            List<String> statusList= Arrays.asList(achThesis.getOperStatusId().split(","));
            criteria.andOperStatusIdIn(statusList);
        }
        if(StringUtil.isNotBlank(achThesis.getApplyUserFlow())){
            criteria.andApplyUserFlowEqualTo(achThesis.getApplyUserFlow());
        }
        if(StringUtil.isNotBlank(achThesis.getApplyUserName())){
            criteria.andApplyUserNameLike("%"+ achThesis.getApplyUserName() +"%");
        }
        if(StringUtil.isNotBlank(achThesis.getPublishDate())){
            criteria.andPublishDateLike("%"+ achThesis.getPublishDate() +"%");
        }
        if(StringUtil.isNotBlank(achThesis.getDeptName())){
            criteria.andDeptNameLike("%"+ achThesis.getDeptName() +"%");
        }
        if(InitConfig.getSysCfg("srm_local_type").equals(GlobalConstant.FLAG_Y)){
            if(StringUtil.isNotBlank(achThesis.getDeptFlow())){
                criteria.andDeptFlowEqualTo(achThesis.getDeptFlow());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return thesisMapper.selectByExample(example);
    }

    @Override
    public FstuThesis readThesis(String thesisFlow) {
        FstuThesis thesis = null ;
        if(StringUtil.isNotBlank(thesisFlow)){
            thesis =  thesisMapper.selectByPrimaryKey(thesisFlow);
        }
        return thesis;
    }

    @Override
    public void updateThesisStatus(FstuThesis thesis, FstuAuditProcess process) {
        if(StringUtil.isNotBlank(thesis.getThesisFlow())){
            GeneralMethod.setRecordInfo(thesis, false);
            thesisMapper.updateByPrimaryKeySelective(thesis);
            thesisProcessBiz.saveAchProcess(process);
        }
    }

    @Override
    public int edit(FstuThesis thesis, List<FstuThesisAuthor> authorList,PubFile file ) {
        if(StringUtil.isNotBlank(thesis.getThesisFlow())){
            GeneralMethod.setRecordInfo(thesis, false);
            thesisMapper.updateByPrimaryKeySelective(thesis);
        }
        //作者
        if(null != authorList && !authorList.isEmpty()){
            for(FstuThesisAuthor author : authorList){
                GeneralMethod.setRecordInfo(author, false);
                thesisAuthorMapper.updateByPrimaryKeySelective(author);
            }
        }
        //附件
        if(null != file){
            file.setProductFlow(thesis.getThesisFlow());
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
    public List<FstuThesis> searchByDeptFlow(FstuThesis thesis, List<String> deptFlows) {
        FstuThesisExample example=new FstuThesisExample();
        FstuThesisExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != deptFlows && !deptFlows.isEmpty()){
            criteria.andDeptFlowIn(deptFlows);
        }
        if(null != thesis) {
            if (StringUtil.isNotBlank(thesis.getThesisName())) {
                criteria.andThesisNameLike("%" + thesis.getThesisName() + "%");
            }
            if (StringUtil.isNotBlank(thesis.getProjSourceId())) {
                criteria.andProjSourceIdEqualTo(thesis.getProjSourceId());
            }
            if (StringUtil.isNotBlank(thesis.getPublishDate())) {
                criteria.andPublishDateEqualTo(thesis.getPublishDate());
            }
            if (StringUtil.isNotBlank(thesis.getProjSourceId())) {
                criteria.andProjSourceIdEqualTo(thesis.getProjSourceId());
            }
            if (StringUtil.isNotBlank(thesis.getApplyOrgFlow())) {
                criteria.andApplyOrgFlowEqualTo(thesis.getApplyOrgFlow());
            }
            if (StringUtil.isNotBlank(thesis.getJourTypeId())) {
                criteria.andJourTypeIdLike("%" + thesis.getJourTypeId() + "%");
            }
            if (StringUtil.isNotBlank(thesis.getOperStatusId())) {
                List<String> statusList = Arrays.asList(thesis.getOperStatusId().split(","));
                criteria.andOperStatusIdIn(statusList);
            }
            if (StringUtil.isNotBlank(thesis.getApplyUserFlow())) {
                criteria.andApplyUserFlowEqualTo(thesis.getApplyUserFlow());
            }
            if (StringUtil.isNotBlank(thesis.getApplyUserName())) {
                criteria.andApplyUserNameLike("%" + thesis.getApplyUserName() + "%");
            }
            if (StringUtil.isNotBlank(thesis.getPublishDate())) {
                criteria.andPublishDateLike("%" + thesis.getPublishDate() + "%");
            }
            if (StringUtil.isNotBlank(thesis.getDeptName())) {
                criteria.andDeptNameLike("%" + thesis.getDeptName() + "%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return thesisMapper.selectByExample(example);
    }
}
