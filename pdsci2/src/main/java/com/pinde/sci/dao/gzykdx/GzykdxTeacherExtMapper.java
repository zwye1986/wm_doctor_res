package com.pinde.sci.dao.gzykdx;

import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/8
 */
public interface GzykdxTeacherExtMapper {

    List<Map<String,Object>> queryDoctorAdmissionList(Map<String,String> map);

    int countDoctorFromTea(Map<String,String> map);

    //查询导师缺额情况
    List<Map<String,String>> countTeacherVacancies(Map<String,String> map);

    /**
     * 缺额二级机构列表
     */
    List<Map<String,String>> queryOrgVacancies(Map<String,String> map);
}
