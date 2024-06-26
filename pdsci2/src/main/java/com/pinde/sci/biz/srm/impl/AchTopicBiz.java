package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAchTopicBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmAchFileMapper;
import com.pinde.sci.dao.base.SrmAchTopicMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AchTopicBiz implements IAchTopicBiz{

    @Autowired
    private ISrmAchProcessBiz srmAchProcessBiz;

    @Autowired
    private SrmAchTopicMapper topicMapper;
    @Autowired
    private SrmAchFileMapper fileMapper;

    @Override
    public SrmAchTopic readTopic(String topicFlow) {

        return topicMapper.selectByPrimaryKey(topicFlow);
    }

    @Override
    public int save(SrmAchTopic topic, SrmAchFile srmAchFile,SrmAchProcess process) {
        //判断科技编号是否为空，空则添加，不为空则修改
        if(StringUtil.isNotBlank(topic.getTopicFlow())){
            GeneralMethod.setRecordInfo(topic, false);
            topicMapper.updateByPrimaryKeySelective(topic);
        }else{
            GeneralMethod.setRecordInfo(topic, true);
            topic.setTopicFlow(PkUtil.getUUID());
            topicMapper.insert(topic);
        }

        //操作附件
        if(null != srmAchFile){
            srmAchFile.setAchFlow(topic.getTopicFlow());
            if(StringUtil.isNotBlank(srmAchFile.getFileFlow())){
                GeneralMethod.setRecordInfo(srmAchFile, false);
                fileMapper.updateByPrimaryKeySelective(srmAchFile);
            }else{
                srmAchFile.setFileFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(srmAchFile, true);
                fileMapper.insert(srmAchFile);
            }
        }

        //记录过程
        process.setProcessFlow(PkUtil.getUUID());
        process.setAchFlow(topic.getTopicFlow());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperateTime(process.getModifyTime());//更改时间
        srmAchProcessBiz.saveAchProcess(process);

        return GlobalConstant.ONE_LINE;
    }

    @Override
    public int save(SrmAchTopic topic){
        return 0;
    }

    @Override
    public int updateTopicStatus(SrmAchTopic topic, SrmAchProcess process) {
        if(StringUtil.isNotBlank(topic.getTopicFlow())){
            GeneralMethod.setRecordInfo(topic, false);
            topicMapper.updateByPrimaryKeySelective(topic);
            //保存操作过程
            srmAchProcessBiz.saveAchProcess(process);
            return GlobalConstant.ONE_LINE;
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<SrmAchTopic> search(SrmAchTopic topic) {
        SrmAchTopicExample example=new SrmAchTopicExample();
        SrmAchTopicExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != topic){
            if(StringUtil.isNotBlank(topic.getTopicName())){
                criteria.andTopicNameLike("%"+topic.getTopicName()+"%");
            }
            if(StringUtil.isNotBlank(topic.getApplyUserName())){
                criteria.andApplyUserNameLike("%"+topic.getApplyUserName()+"%");
            }
            if(StringUtil.isNotBlank(topic.getTopicStartTime())){
                criteria.andTopicStartTimeLike("%"+topic.getTopicStartTime()+"%");
            }
            if(StringUtil.isNotBlank(topic.getOperStatusId())){
                List<String> statusList= Arrays.asList(topic.getOperStatusId().split(","));
                criteria.andOperStatusIdIn(statusList);
            }
            if(StringUtil.isNotBlank(topic.getApplyUserFlow())){
                criteria.andApplyUserFlowEqualTo(topic.getApplyUserFlow());
            }
            if(StringUtil.isNotBlank(topic.getDeptFlow())){
                criteria.andDeptFlowEqualTo(topic.getDeptFlow());
            }
            if(StringUtil.isNotBlank(topic.getApplyOrgFlow())){
                criteria.andApplyOrgFlowEqualTo(topic.getApplyOrgFlow());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return topicMapper.selectByExample(example);
    }

    @Override
    public int edit(SrmAchTopic topic, SrmAchFile file) {
        if(StringUtil.isNotBlank(topic.getTopicFlow())){
            GeneralMethod.setRecordInfo(topic, false);
            topicMapper.updateByPrimaryKeySelective(topic);
        }
        //附件
        if(file != null && StringUtil.isNotBlank(file.getFileFlow())){
            GeneralMethod.setRecordInfo(file, false);
            fileMapper.updateByPrimaryKeySelective(file);
        }
        return GlobalConstant.ONE_LINE;
    }
}
