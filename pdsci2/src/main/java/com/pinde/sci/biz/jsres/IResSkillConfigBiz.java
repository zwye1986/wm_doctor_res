package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResDoctorSkill;
import com.pinde.core.model.ResSkillConfig;
import com.pinde.core.model.ResSkillOrg;

import java.util.List;
import java.util.Map;

public interface IResSkillConfigBiz {

    List<ResSkillConfig> searchSkillList(Map<String, String> param);

    ResSkillConfig findOneSkillConfig(String skillFlow);

    List<ResSkillOrg> searchSkillOrgs(String skillFlow);

    int saveResSkillConfig(ResSkillConfig skillConfig, String[] orgFlows);

    int deleteSkillConfig(String skillFlow);

    List<ResDoctorSkill> searchDoctorSkillList(Map<String, String> param);

    ResSkillConfig findOneByCurrDate(String currTime,String cityId);

    ResSkillOrg searchSkillOrg(String skillFlow, String orgFlow);
}
