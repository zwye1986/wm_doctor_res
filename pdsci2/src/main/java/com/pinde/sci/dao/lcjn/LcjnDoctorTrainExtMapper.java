package com.pinde.sci.dao.lcjn;

import java.util.List;
import java.util.Map;

public interface LcjnDoctorTrainExtMapper {
    /**
     * 查询学员签到列表
     * @param map
     * @return
     */
    List<Map<String,Object>> queryDoctorSignList(Map<String, String> map);

    /**
     * 查询学员申请列表
     * @param map
     * @return
     */
    List<Map<String,Object>> queryDoctorOrderInfoList(Map<String, String> map);

    /**
     * 查询本院学员信息
     * @param map
     * @return
     */
    List<Map<String,Object>> queryLocalDoctors(Map<String, String> map);

    /**
     * 校验学员预约时间是否重复
     * @param map
     * @return
     */
    int countOrderTime(Map<String, String> map);

    /**
     * 时间重复且是待审核状态
     * @param map
     * @return
     */
    List<String> queryOrderTimeAndPassing(Map<String, String> map);

    /**
     * 成绩管理列表
     * @param map
     * @return
     */
    List<Map<String,Object>> queryDoctorScoreList(Map<String, String> map);
}
