package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.res.biz.jswjw.IJswjwChargeBiz;
import com.pinde.res.dao.jswjw.ext.ResDoctorRecruitExtMapper;
import com.pinde.sci.dao.base.SysDictMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysDictExample;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysOrgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class JswjwChargeBizImpl implements IJswjwChargeBiz {
	
	@Autowired
	private ResDoctorRecruitExtMapper recruitExtMapper;
	@Autowired
    private SysOrgMapper orgMapper;
	@Autowired
    private SysDictMapper dictMapper;

    @Override
    public List<Map<String, Object>> searchDoctorRecruitList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorRecruitList(param);
    }

    @Override
    public List<SysOrg> searchOrgList(String orgCityId) {
        SysOrgExample example = new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgCityIdEqualTo(orgCityId).andOrgTypeIdEqualTo("Hospital");
        return orgMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> searchDoctorRecruitTrainingList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorRecruitTrainingList(param);
    }

    @Override
    public List<Map<String, Object>> searchDoctorReductionList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorReductionList(param);
    }

    @Override
    public List<Map<String, Object>> searchSpeChangeList(Map<String, String> param) {
        return recruitExtMapper.searchSpeChangeList(param);
    }

    @Override
    public List<Map<String, Object>> searchDelayList(Map<String, String> param) {
        return recruitExtMapper.searchDelayList(param);
    }

    @Override
    public List<Map<String, Object>> searchReturnList(Map<String, String> param) {
        return recruitExtMapper.searchReturnList(param);
    }

    @Override
    public List<Map<String, Object>> searchBlackList(Map<String, String> param) {
        return recruitExtMapper.searchBlackList(param);
    }

    @Override
    public List<Map<String, Object>> searchInDeptList(Map<String, String> param) {
        return recruitExtMapper.searchInDeptList(param);
    }

    @Override
    public List<Map<String, Object>> searchOutDeptList(Map<String, String> param) {
        return recruitExtMapper.searchOutDeptList(param);
    }

    @Override
    public List<Map<String, Object>> searchActivityList(Map<String, String> param) {
        return recruitExtMapper.searchActivityList(param);
    }

    @Override
    public List<Map<String, Object>> searchGradutionList(Map<String, String> param) {
        return recruitExtMapper.searchGradutionList(param);
    }

    @Override
    public List<Map<String, Object>> searchSignList(Map<String, String> param) {
        return recruitExtMapper.searchSignList(param);
    }

    @Override
    public List<Map<String, Object>> searchTheoryScoreList(Map<String, String> param) {
        return recruitExtMapper.searchTheoryScoreList(param);
    }

    @Override
    public List<Map<String, Object>> searchSkillScoreList(Map<String, String> param) {
        return recruitExtMapper.searchSkillScoreList(param);
    }

    @Override
    public List<Map<String, Object>> searchDoctorRecruitAllList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorRecruitAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchDoctorRecruitTrainingAllList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorRecruitTrainingAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchDoctorReductionAllList(Map<String, String> param) {
        return recruitExtMapper.searchDoctorReductionAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchSpeChangeAllList(Map<String, String> param) {
        return recruitExtMapper.searchSpeChangeAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchDelayAllList(Map<String, String> param) {
        return recruitExtMapper.searchDelayAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchReturnAllList(Map<String, String> param) {
        return recruitExtMapper.searchReturnAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchBlackAllList(Map<String, String> param) {
        return recruitExtMapper.searchBlackAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchInDeptAllList(Map<String, String> param) {
        return recruitExtMapper.searchInDeptAllList(param);
    }

    @Override
    public List<Map<String, Object>> searchOutDeptAllList(Map<String, String> param) {
        return recruitExtMapper.searchOutDeptAllList(param);
    }

    @Override
    public List<SysDict> searchSpeList(String dictTypeId) {
        SysDictExample example = new SysDictExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andDictTypeIdEqualTo(dictTypeId);
        return dictMapper.selectByExample(example);
    }
}
