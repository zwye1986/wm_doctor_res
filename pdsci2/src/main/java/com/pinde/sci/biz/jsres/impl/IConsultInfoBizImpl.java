package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IConsultInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ConsultInfoMapper;
import com.pinde.sci.dao.jsres.ConsultInfoExtMapper;
import com.pinde.sci.model.mo.ConsultInfo;
import com.pinde.sci.model.mo.ConsultInfoExample;
import com.pinde.sci.util.jsres.SensitiveFilterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class IConsultInfoBizImpl implements IConsultInfoBiz {

    @Autowired
    private ConsultInfoMapper consultInfoMapper;

    @Autowired
    private ConsultInfoExtMapper consultInfoExtMapper;

    @Override
    public List<ConsultInfo> search(ConsultInfo consultInfo,String orderBy) {
        ConsultInfoExample example = new ConsultInfoExample();
        ConsultInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(consultInfo.getConsultQuestion())){
            criteria.andConsultQuestionLike("%"+consultInfo.getConsultQuestion()+"%");
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeId())){
            criteria.andConsultTypeIdEqualTo(consultInfo.getConsultTypeId());
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeSonId())) {
            criteria.andConsultTypeSonIdEqualTo(consultInfo.getConsultTypeSonId());
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultQuestionRoleId())) {
            criteria.andConsultQuestionRoleIdEqualTo(consultInfo.getConsultQuestionRoleId());
        }
        if (StringUtil.isNotBlank(consultInfo.getIsAnswer())){
            criteria.andIsAnswerEqualTo(consultInfo.getIsAnswer());
        }
        if (StringUtil.isNotBlank(consultInfo.getIsPolicy())){
            criteria.andIsPolicyEqualTo(consultInfo.getIsPolicy());
        }
        if (StringUtil.isNotBlank(consultInfo.getOrgCityId())){
            criteria.andOrgCityIdEqualTo(consultInfo.getOrgCityId());
        }
        if (StringUtil.isNotBlank(consultInfo.getOrgCityName())){
            criteria.andOrgCityNameEqualTo(consultInfo.getOrgCityName());
        }
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if ("time".equals(orderBy)){
            example.setOrderByClause("CONSULT_QUESTION_CREATE_TIME DESC");
        }else if ("number".equals(orderBy)){
            example.setOrderByClause("CONSULT_VISIT_NUMBER DESC");
        }
        List<ConsultInfo> consultInfos = consultInfoMapper.selectByExampleWithBLOBs(example);
        return consultInfos;
    }

    @Override
    public List<ConsultInfo> search2(HashMap<String,String> map) {
        List<ConsultInfo> consultInfos = consultInfoExtMapper.selectByExampleWithBLOBs(map);
        return consultInfos;
    }

    @Override
    public List<ConsultInfo> searchMyQuestion(String userFlow) {
        ConsultInfoExample example = new ConsultInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCreateUserFlowEqualTo(userFlow);
        List<ConsultInfo> consultInfos = consultInfoMapper.selectByExample(example);
        return consultInfos;
    }

    @Override
    public void detail(String consultInfoFlow) {
        int number = consultInfoExtMapper.searchNumber(consultInfoFlow);
        ConsultInfo consultInfo = new ConsultInfo();
        consultInfo.setConsultInfoFlow(consultInfoFlow);
        consultInfo.setConsultVisitNumber((number+1)+"");
        consultInfoMapper.updateByPrimaryKeySelective(consultInfo);
    }

    @Override
    public Integer saveConsultInfo(ConsultInfo consultInfo) {
        // 获取过滤敏感词工具类
        SensitiveFilterUtil filter = SensitiveFilterUtil.getInstance();
        // 如果问题不为空则检测是否包含敏感词，包含则把敏感词部分替换为*
        if(StringUtil.isNotBlank(consultInfo.getConsultQuestion())){
            consultInfo.setConsultQuestion(filter.replaceSensitiveWord(consultInfo.getConsultQuestion(), SensitiveFilterUtil.MAX_MATCH_TYPE, "*"));
        }
        // 如果答案不为空则检测是否包含敏感词，包含则把敏感词部分替换为*
        if(StringUtil.isNotBlank(consultInfo.getConsultAnswer())){
            consultInfo.setConsultAnswer(filter.replaceSensitiveWord(consultInfo.getConsultAnswer(), SensitiveFilterUtil.MAX_MATCH_TYPE, "*"));
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultInfoFlow())){
            GeneralMethod.setRecordInfo(consultInfo,false);
            return consultInfoMapper.updateByPrimaryKeySelective(consultInfo);
        }else {
            consultInfo.setConsultInfoFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(consultInfo,true);
            return consultInfoMapper.insertSelective(consultInfo);
        }
    }

    @Override
    public List<String> searchOrgCityNameList() {
        List<String> list = consultInfoExtMapper.searchOrgCityNameList();
        return list;
    }

    @Override
    public Boolean deleteAll(List<String> list) {
        for (String consultInfoFlow:list){
            if (delete(consultInfoFlow) != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer delete(String consultInfoFlow){
        ConsultInfo consultInfo = new ConsultInfo();
        consultInfo.setRecordStatus("N");
        consultInfo.setConsultInfoFlow(consultInfoFlow);
        return consultInfoMapper.updateByPrimaryKeySelective(consultInfo);
    }

    @Override
    public ConsultInfo read(String consultInfoFlow) {
        ConsultInfoExample example = new ConsultInfoExample();
        example.createCriteria().andConsultInfoFlowEqualTo(consultInfoFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<ConsultInfo> consultInfos = consultInfoMapper.selectByExampleWithBLOBs(example);
        if (consultInfos != null && consultInfos.size() >0){
            return consultInfos.get(0);
        }else {
            return null;
        }
    }
}
