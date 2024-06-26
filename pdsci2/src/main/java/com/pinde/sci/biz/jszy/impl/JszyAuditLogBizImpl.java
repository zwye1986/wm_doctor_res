package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyAuditLogBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResAuditLogMapper;
import com.pinde.sci.model.mo.ResAuditLog;
import com.pinde.sci.model.mo.ResAuditLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/12/28.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JszyAuditLogBizImpl implements IJszyAuditLogBiz {

    @Autowired
    private ResAuditLogMapper resAuditLogMapper;


    @Override
    public int saveAuditLog(ResAuditLog resAuditLog) {
        if (StringUtil.isNotBlank(resAuditLog.getRecordFlow())) {
            return 0;
        } else {
            resAuditLog.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resAuditLog, true);
            return resAuditLogMapper.insert(resAuditLog);
        }
    }

    @Override
    public List<ResAuditLog> findAuditLogsByParentFlow(String parentRecordFlow) {
        ResAuditLogExample example = new ResAuditLogExample();
        ResAuditLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andParentRecordFlowEqualTo(parentRecordFlow);
        example.setOrderByClause("create_time desc");
        return resAuditLogMapper.selectByExample(example);
    }

    @Override
    public Map<String, List<ResAuditLog>> findAuditLogsByParentFlows(List<String> parentRecordFlows) {
        ResAuditLogExample example = new ResAuditLogExample();
        ResAuditLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (parentRecordFlows != null && parentRecordFlows.size() > 0) {
            Map<String, List<ResAuditLog>> resAuditLogsMap = new HashMap<>();
            criteria.andParentRecordFlowIn(parentRecordFlows);
            example.setOrderByClause("parent_record_flow, create_time desc");
            List<ResAuditLog> resAuditLogs = resAuditLogMapper.selectByExample(example);
            if (resAuditLogs != null && resAuditLogs.size() > 0) {
                List<ResAuditLog> sameParentFlowLogs = null;
                for (String tempStr : parentRecordFlows) {
                    sameParentFlowLogs = new ArrayList<>();
                    for (ResAuditLog tempLog : resAuditLogs) {
                        if (tempStr.equals(tempLog.getParentRecordFlow())) {
                            sameParentFlowLogs.add(tempLog);
                        }
                    }
                    resAuditLogsMap.put(tempStr, sameParentFlowLogs);
                }
            }
            return resAuditLogsMap;
        } else {
            return null;
        }
    }

    @Override
    public List<ResAuditLog> findAuditLogByMap(Map<String, Object> paramMap) {
        ResAuditLogExample example = new ResAuditLogExample();
        ResAuditLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (paramMap.get("order") != null) {
            example.setOrderByClause(paramMap.get("order").toString());
        }
        return resAuditLogMapper.selectByExample(example);
    }

    @Override
    public ResAuditLog findLastAuditLogByParentFlow(String parentRecordFlow) {
        return findAuditLogsByParentFlow(parentRecordFlow) == null ? null : findAuditLogsByParentFlow(parentRecordFlow).get(0);
    }

    @Override
    public Map<String, ResAuditLog> findLastAuditLogByParentFlows(List<String> parentRecordFlows) {
        ResAuditLogExample example = new ResAuditLogExample();
        ResAuditLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (parentRecordFlows != null && parentRecordFlows.size() > 0) {
            Map<String, ResAuditLog> resAuditLogMap = new HashMap<>();
            criteria.andParentRecordFlowIn(parentRecordFlows);
            example.setOrderByClause("parent_record_flow, create_time desc");
            List<ResAuditLog> resAuditLogs = resAuditLogMapper.selectByExample(example);
            if (resAuditLogs != null && resAuditLogs.size() > 0) {
                ResAuditLog resAuditLog = null;
                for (String tempStr : parentRecordFlows) {
                    for (ResAuditLog tempLog : resAuditLogs) {
                        if (tempStr.equals(tempLog.getParentRecordFlow())) {
                            resAuditLogMap.put(tempStr, resAuditLog);
                            continue;
                        }
                    }
                }
            }
            return resAuditLogMap;
        } else {
            return null;
        }
    }
}
