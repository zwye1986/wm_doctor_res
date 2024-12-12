package com.pinde.sci.dao.osca;

import com.pinde.core.model.SysOrg;

import java.util.List;
import java.util.Map;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/5/12
 */
public interface OscaExamineInfoMapper {

    //专业考核情况查询(市局)
    List<Map<String,Object>> queryCheckInfoBySpeId(Map<String, Object> map);
    //基地考核情况查询(市局)
    List<Map<String,Object>> queryCheckInfoByOrgFlow(Map<String, Object> map);
    //查询市局下的基地
    List<Map<String,String>> queryCityOrg(String userOrgFlow);
    //查询省厅下的基地
    List<Map<String,String>> queryGlobalOrg(Map<String, String> map);
    List<SysOrg> queryOrg(Map<String, String> map);
    //考核情况查询(基地)
    List<Map<String,Object>> queryOrgCheckInfo(Map<String, Object> map);
    //专业统计信息查询-基地考核统计
    List<Map<String,Object>> queryOrgInfo(Map<String, Object> paramMap);
}
