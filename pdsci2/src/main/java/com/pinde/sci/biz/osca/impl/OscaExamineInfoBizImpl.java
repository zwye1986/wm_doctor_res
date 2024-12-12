package com.pinde.sci.biz.osca.impl;

import com.pinde.core.model.SysOrg;
import com.pinde.sci.biz.osca.IOscaExamineInfoBiz;
import com.pinde.sci.dao.osca.OscaExamineInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/5/12
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaExamineInfoBizImpl implements IOscaExamineInfoBiz {
    @Autowired
    private OscaExamineInfoMapper examineInfoMapper;

    @Override
    public List<Map<String, Object>> selectCheckInfoByOrgFlow(Map<String, Object> map) {
        return examineInfoMapper.queryCheckInfoByOrgFlow(map);
    }

    @Override
    public List<Map<String, Object>> selectCheckInfoBySpeId(Map<String, Object> map) {
        return examineInfoMapper.queryCheckInfoBySpeId(map);
    }

    @Override
    public List<Map<String, String>> selectCityOrg(String userOrgFlow) {
        return examineInfoMapper.queryCityOrg(userOrgFlow);
    }

    @Override
    public List<Map<String, String>> selectGlobalOrg(Map<String, String> map) {
        return examineInfoMapper.queryGlobalOrg(map);
    }

    @Override
    public List<SysOrg> selectGlobalOrg1(Map<String, String> map) {
        return examineInfoMapper.queryOrg(map);
    }

    @Override
    public List<Map<String, Object>> selectOrgCheckInfo(Map<String, Object> map) {
        return examineInfoMapper.queryOrgCheckInfo(map);
    }

    @Override
    public List<Map<String, Object>> queryOrgInfo(Map<String, Object> paramMap) {
        return examineInfoMapper.queryOrgInfo(paramMap);
    }
}
