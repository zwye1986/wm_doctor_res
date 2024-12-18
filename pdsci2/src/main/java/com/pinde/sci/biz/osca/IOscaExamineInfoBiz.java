package com.pinde.sci.biz.osca;

import com.pinde.core.model.SysOrg;

import java.util.List;
import java.util.Map;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/5/12
 */
public interface IOscaExamineInfoBiz {

    //专业考核情况查询
    List<Map<String,Object>> selectCheckInfoBySpeId(Map<String, Object> map);
    //基地考核情况查询
    List<Map<String,Object>> selectCheckInfoByOrgFlow(Map<String, Object> map);
    //查询市局下的基地
    List<Map<String,String>> selectCityOrg(String userOrgFlow);
    //查询省厅下的基地
    List<Map<String,String>> selectGlobalOrg(Map<String, String> map);
    List<SysOrg> selectGlobalOrg1(Map<String, String> map);
    //考核情况查询(基地)
    List<Map<String,Object>> selectOrgCheckInfo(Map<String, Object> map);
    //专业统计信息查询-基地考核统计
    List<Map<String,Object>> queryOrgInfo(Map<String, Object> paramMap);
}
