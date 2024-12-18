package com.pinde.sci.biz.lcjn;

import com.pinde.core.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ILcjnBaseManagerBiz {
    //培训信息管理查询
    List<Map<String, Object>> queryCourseList(Map<String, String> map);
    //技能查询
    List<LcjnSkillCfg> queryCourseSkillsList();
    //培训课程查询
    LcjnCourseInfo queryCourseByFlow(String courseFlow);
    //培训课程所需技能查询
    List<LcjnCourseSkill> queryCourseSkillsByFlow(String courseFlow);
    //课程培训时间查询
    List<LcjnCourseTime> queryCourseTimeByFlow(String courseFlow);
    //任课老师查询
    List<SysUser> queryCourseTeachersList();
    //培训课程所需任课老师查询
    List<LcjnCourseTea> queryCourseTeachersByFlow(String courseFlow);
    //新增培训课程保存操作
    int saveCourseTrain(Map<String, Object> param);
    //获取某年某月所有培训课程信息
    List<LcjnCourseTime> queryCourseTimeList(String time);
    //课程信息发布操作
    int releasedInfo(String courseFlow);
    //课程信息删除操作
    int delCourseTrain(String courseFlow, String isReleased);
    //师资管理信息查询
    List<SysUser> querySysUser(SysUser user);
    //师资保存操作
    int saveTeaInfo(SysUser user);
    //师资信息导入操作
    int importTeacherExcel(MultipartFile file) throws Exception;
    //启用/停用老师操作
    int teacherOption(String userFlow,String recordStatus);
    //技能配置管理查询
    List<Map<String, Object>> querySkillConfigList();
    //根据技能流水号查询技能
    LcjnSkillCfg querySkillByFlow(String skillFlow);
    //技能所需的已维护好的耗材查询
    List<LcjnSupplies> querySuppliesList();
    //技能所需的已维护好的固定资产查询
    List<LcjnFixedAssets> queryFixedAssetsList();
    //根据课程技能查询配置的耗材及固定资产详情
    List<LcjnSkillCfgDetail> querySkillDetailByFlow(String skillFlow);
    //根据课程技能查询配置的耗材及固定资产详情(关联获取耗材及固定资产名称)
    List<Map<String, Object>> queryConfigDetailByFlow(String skillFlow);
    //新增技能配置保存操作
    int saveSkillConfig(Map<String, Object> param);
    //技能配置删除操作
    int delSkillsConfig(String skillFlow);
    //查询已维护好且非停用耗材和固定资产
    List<SysDict> searchStartDictList(SysDict dict);
    //验证同一老师同一时间内是否有多个课程
    String validateTeaTimeCourse(Map<String,Object> param);
}
