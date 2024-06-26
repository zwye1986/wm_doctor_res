package com.pinde.sci.biz.res.impl;

import com.pinde.sci.biz.res.ResPersonnelStatisticsBiz;
import com.pinde.sci.dao.base.PersonnelCollaborativeBaseMapper;
import com.pinde.sci.dao.base.PersonnelStatisticsMapper;
import com.pinde.sci.dao.base.ResJointOrgMapper;
import com.pinde.sci.model.mo.PersonnelStatisticsByName;
import com.pinde.sci.model.mo.ResJointOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResPersonnelStatisticsImpl implements ResPersonnelStatisticsBiz {

    @Autowired
    private PersonnelStatisticsMapper personnelStatisticsMapper;

    @Autowired
    private ResJointOrgMapper resJointOrgMapper;

    @Autowired
    private PersonnelCollaborativeBaseMapper personnelCollaborativeBaseMapper;

    @Override
    public List<PersonnelStatisticsByName> selectCollaborativeBaseFlow(String orgName) {
        System.out.println(personnelCollaborativeBaseMapper.selectCollaborativeBaseFlow(orgName));
        return personnelCollaborativeBaseMapper.selectCollaborativeBaseFlow(orgName);
    }

    @Override
    public Integer inTheNumOf(Map<String, Object> sessionNum) {
        return personnelStatisticsMapper.inTheNumOf(sessionNum);
    }

    @Override
    public boolean isCollaborativelBase(String orgFlow) {
        ResJointOrg resJointOrg = resJointOrgMapper.selectByJointOrgFlow(orgFlow);
        if (resJointOrg!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<PersonnelStatisticsByName> selectSection(String lastDateMonth, String dateMonth, String strDateMonth,String orgFlow) {
        return personnelStatisticsMapper.selectSection(lastDateMonth, dateMonth, strDateMonth, orgFlow);
    }

    @Override
    public List<PersonnelStatisticsByName> selectGrade(String lastDateMonth, String dateMonth, String strDateMonth, String trainingSpeName,String orgFlow) {
        return personnelStatisticsMapper.selectGrade(lastDateMonth, dateMonth, strDateMonth, trainingSpeName, orgFlow);
    }

    @Override
    public List<PersonnelStatisticsByName> selectCollaborativeBase(String lastDateMonth, String dateMonth, String strDateMonth, String trainingSpeName, String sessionNumber, String orgFlow) {
        return personnelStatisticsMapper.selectCollaborativeBase(lastDateMonth, dateMonth, strDateMonth, trainingSpeName, sessionNumber, orgFlow);
    }
}
