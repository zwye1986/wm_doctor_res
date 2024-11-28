package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.jsres.IResSkillConfigBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.ResDoctorSkillMapper;
import com.pinde.sci.dao.base.ResSkillConfigMapper;
import com.pinde.sci.dao.base.ResSkillOrgMapper;
import com.pinde.sci.dao.jsres.ResSkillOrgExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResSkillConfigBizImpl implements IResSkillConfigBiz {
    @Autowired
    private ResSkillConfigMapper skillConfigMapper;
    @Autowired
    private ResSkillOrgMapper skillOrgMapper;
    @Autowired
    private ResSkillOrgExtMapper orgExtMapper;
    @Autowired
    private ResDoctorSkillMapper doctorSkillMapper;

    @Override
    public List<ResSkillConfig> searchSkillList(Map<String, String> param) {
        ResSkillConfigExample example = new ResSkillConfigExample();
        ResSkillConfigExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(param.get("testName"))){
            criteria.andTestNameLike("%"+param.get("testName")+"%");
        }
        if(StringUtil.isNotBlank(param.get("testId"))){
            criteria.andTestIdLike("%"+param.get("testId")+"%");
        }
        if(StringUtil.isNotBlank(param.get("cityId"))){
            criteria.andCityIdEqualTo(param.get("cityId"));
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return skillConfigMapper.selectByExample(example);
    }

    @Override
    public ResSkillConfig findOneSkillConfig(String skillFlow) {
        return skillConfigMapper.selectByPrimaryKey(skillFlow);
    }

    @Override
    public List<ResSkillOrg> searchSkillOrgs(String skillFlow) {
        ResSkillOrgExample example = new ResSkillOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSkillFlowEqualTo(skillFlow);
        return skillOrgMapper.selectByExample(example);
    }

    @Override
    public int saveResSkillConfig(ResSkillConfig skillConfig, String[] orgFlows) {
        //校验时间是否重叠
        List<ResSkillConfig> list = orgExtMapper.searchSkillConfig(skillConfig.getSkillStartTime(),skillConfig.getSkillEndTime(),skillConfig.getSkillFlow());
        if(null != list && list.size() > 0 ){
            if(StringUtil.isNotBlank(skillConfig.getSkillFlow())){
                for (ResSkillConfig resSkillConfig : list) {
                    if(!resSkillConfig.getSkillFlow().equals(skillConfig.getSkillFlow())){
                        return -1;
                    }
                }
            }else{
                return -1;
            }
        }
        int num = 0;
        if(StringUtil.isNotBlank(skillConfig.getSkillFlow())){
            GeneralMethod.setRecordInfo(skillConfig,false);
            num = skillConfigMapper.updateByPrimaryKeySelective(skillConfig);
        }else{
            skillConfig.setSkillFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(skillConfig,true);
            num = skillConfigMapper.insert(skillConfig);
        }
        if(num > 0){
            if(null != orgFlows && orgFlows.length>0){
                //删除关联基地
                int count = orgExtMapper.delSkillOrgBySkillFlow(skillConfig.getSkillFlow());
                Map<String,Object> map = new HashMap<>();
                map.put("skillFlow",skillConfig.getSkillFlow());
                map.put("recordStatus", com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                map.put("createTime",DateUtil.getCurrDateTime());
                map.put("createUserFlow",GlobalContext.getCurrentUser().getUserFlow());
                map.put("modifyTime",DateUtil.getCurrDateTime());
                map.put("modifyUserFlow",GlobalContext.getCurrentUser().getUserFlow());
                List<String> orgFlowList = Arrays.asList(orgFlows);
                map.put("orgFlowList",orgFlowList);
                orgExtMapper.insertAllOrg(map);
            }
        }
        return num;
    }

    @Override
    public int deleteSkillConfig(String skillFlow) {
        ResSkillConfigExample example = new ResSkillConfigExample();
        example.createCriteria().andSkillFlowEqualTo(skillFlow);
        List<ResSkillConfig> resSkillConfigs = skillConfigMapper.selectByExample(example);
        if (resSkillConfigs.size() > 0) {
            ResSkillConfig skillConfig = resSkillConfigs.get(0);
            skillConfig.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            GeneralMethod.setRecordInfo(skillConfig, false);
            ResSkillConfigExample skillConfigExample = new ResSkillConfigExample();
            skillConfigExample.createCriteria().andSkillTimeFlowEqualTo(skillConfig.getSkillFlow());
            return skillConfigMapper.updateByExampleSelective(skillConfig, example);
        }
        return 0;
    }

    @Override
    public List<ResDoctorSkill> searchDoctorSkillList(Map<String, String> param) {
        ResDoctorSkillExample example = new ResDoctorSkillExample();
        ResDoctorSkillExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("TEST_ID DESC, CREATE_TIME DESC");
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(param.get("cityId"))){
            criteria.andCityIdEqualTo(param.get("cityId"));
        }
        if(StringUtil.isNotBlank(param.get("idNo"))){
            criteria.andIdNoLike("%"+param.get("idNo")+"%");
        }
        if(StringUtil.isNotBlank(param.get("doctorName"))){
            criteria.andDoctorNameLike("%"+param.get("doctorName")+"%");
        }
        return doctorSkillMapper.selectByExample(example);
    }

    @Override
    public ResSkillConfig findOneByCurrDate(String currTime,String cityId) {
        ResSkillConfigExample example = new ResSkillConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andCityIdEqualTo(cityId)
                .andSkillStartTimeLessThan(currTime).andSkillEndTimeGreaterThan(currTime);
        List<ResSkillConfig> lists = skillConfigMapper.selectByExample(example);
        if(null != lists && lists.size()>0){
            return lists.get(0);
        }
        return null;
    }

    @Override
    public ResSkillOrg searchSkillOrg(String skillFlow, String orgFlow) {
        ResSkillOrgExample example = new ResSkillOrgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andSkillFlowEqualTo(skillFlow).andOrgFlowEqualTo(orgFlow);
        List<ResSkillOrg> orgList = skillOrgMapper.selectByExample(example);
        if(null != orgList && orgList.size()>0){
            return orgList.get(0);
        }
        return null;
    }
}
