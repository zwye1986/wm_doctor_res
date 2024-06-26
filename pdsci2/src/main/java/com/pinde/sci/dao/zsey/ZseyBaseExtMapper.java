package com.pinde.sci.dao.zsey;

import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.ZseyAppointMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZseyBaseExtMapper {

    //培训预约审核信息
    List<Map<String, Object>> queryAppointList(Map<String, String> map);
    //验证时间段内教室是否占用
    List<ZseyAppointMain> checkRoomInUsing(Map<String, String> map);
    //用户账号查询
    List<Map<String, Object>> queryAccountList(Map<String, String> map);
    //培训预约加载任课老师
    List<SysUser> searchTeacher();
    //查询设备和耗材信息
    List<Map<String, Object>> queryAppointMaterialList(@Param("materialName") String materialName);
    //教室使用统计
    List<Map<String, Object>> roomUseNumList(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //教室每月使用统计
    List<Map<String, Object>> monthStatistics(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //教室使用情况
    List<Map<String,Object>> deptAppointList(@Param("trainDate") String trainDate);
    //部门使用耗材统计
    List<Map<String, Object>> deptStatisticsList(Map<String, String> map);
}
