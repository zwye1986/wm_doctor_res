package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.PersonnelChangeReport;
import com.pinde.sci.biz.res.ResPersonnelChangeReportBiz;
import com.pinde.core.common.sci.dao.PersonnelChangeReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResPersonnelChangeReportImpl implements ResPersonnelChangeReportBiz {
    @Autowired
    private PersonnelChangeReportMapper personnelChangeReportMapper;

    @Override
    public List<PersonnelChangeReport> selectSectionByChangeTime(String monthDate, String orgFlow) {
        return personnelChangeReportMapper.selectSectionByChangeTime(monthDate,orgFlow);
    }

    @Override
    public List<PersonnelChangeReport> selectSessionByChangeTime(String monthDate, String orgFlow, String trainingSpeName) {
        return personnelChangeReportMapper.selectSessionByChangeTime(monthDate, orgFlow, trainingSpeName);
    }

    @Override
    public List<PersonnelChangeReport> selectOrgNameByChangeTime(String monthDate, String orgFlow, String trainingSpeName, String sessionNumber) {
        return personnelChangeReportMapper.selectOrgNameByChangeTime(monthDate, orgFlow, trainingSpeName, sessionNumber);
    }

    @Override
    public List<PersonnelChangeReport> selectSectionBySpeChange(String monthDate, String orgFlow) {
        return personnelChangeReportMapper.selectSectionBySpeChange(monthDate, orgFlow);
    }

    @Override
    public List<PersonnelChangeReport> selectSessionBySpeChange(String monthDate, String orgFlow, String trainingSpeName) {
        return personnelChangeReportMapper.selectSessionBySpeChange(monthDate, orgFlow, trainingSpeName);
    }

    @Override
    public List<PersonnelChangeReport> selectOrgNameBySpeChange(String monthDate, String orgFlow, String trainingSpeName, String sessionNumber) {
        return personnelChangeReportMapper.selectOrgNameBySpeChange(monthDate, orgFlow, trainingSpeName, sessionNumber);
    }
}
