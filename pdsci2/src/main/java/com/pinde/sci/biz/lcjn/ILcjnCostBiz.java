package com.pinde.sci.biz.lcjn;

import com.pinde.core.model.*;

import java.util.List;
import java.util.Map;

public interface ILcjnCostBiz {
    //根据courseFlow查询所有相关技能
    List<LcjnCourseSkill> searchByCourseFlow(String courseFlow);
    //根据skillFlow查询所有所需其他耗材
    List<LcjnCourseSupplies> searchSuppliesBySkillFlowAndCourse(String skillFlow, String courseFlow);
    //根据courseFlow查询所有所需其他耗材
    List<LcjnCourseSupplies> searchSuppliesByCourseFlow(String courseFlow);
    //根据物资id查询所有所需其他耗材
    List<LcjnCourseSupplies> searchSuppliesById(String id,String skillFlow,String courseFlow);
    //根据courseFlow查询签到人数
    int countSign(String courseFlow);
    //根据courseFlow查询预约审核通过人数
    int countNum(String courseFlow);
    //根据skillFlow查询其所使用的耗材
    List<LcjnSkillCfgDetail> searchCfgBySkillFlow(String courseFlow);
    //根据主键查询一个耗材记录
    LcjnSupplies readSupply(String suppliesFlow);
    //根据耗材ID查询耗材记录
    List<LcjnSupplies> searchById(String id);
    //根据主键查询一个其他耗材
    LcjnCourseSupplies readOtherSupply(String recordFlow);
    //编辑或添加其他耗材
    int editOtherSupply(LcjnCourseSupplies courseSupplies);
    //查询所有耗材
    List<LcjnSupplies> searchSupplyList();
    //根据主键查询一个course记录
    LcjnCourseInfo readCourse(String courseFlow);
    //根据时间查询COURSE
    List<Map<String,Object>> searchCourseInfo(LcjnCourseInfo courseInfo, String startTime, String endTime);
    //根据COURSEFLOW查出所有该课程所需耗材
    List<LcjnSkillCfgDetail> searchCfgByCourseFlow(String courseFlow);
}
