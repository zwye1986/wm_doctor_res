package com.pinde.sci.dao.sczyres;



import com.pinde.sci.model.mo.ScresGraduationApply;
import java.util.List;
import java.util.Map;
public interface SczyGraduationExtMapper {
    //结业_主基地查找申报的学员 本基地及协同基地
    List<ScresGraduationApply> searchAppliesMain(Map<String,Object> paramMap);
    //结业_中管局结业统计
    List<Map<String,Object>> graduationStatistics(Map<String,Object> paramMap);
    //结业_中管局准考证管理页面
    List<Map<String,Object>> ticketList(Map<String,Object> paramMap);
    //学员打印查询
    List<Map<String,Object>> searchDocrtor4Exl(Map<String,Object> paramMap);
    //查询学员列表（目前只有省厅角色用）
    List<Map<String,Object>> searchDoctorRecruitExtList(Map<String, Object> paramMap);
    //查询学员列表（包括大字段）
    List<Map<String,Object>> searchDoctorRecruitExtListWithClob(Map<String, Object> paramMap);

    List<ScresGraduationApply> searchApplyMap(Map<String, Object> paramMap);
}
