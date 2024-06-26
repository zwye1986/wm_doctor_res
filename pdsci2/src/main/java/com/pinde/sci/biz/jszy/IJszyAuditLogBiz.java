package com.pinde.sci.biz.jszy;

import com.pinde.sci.model.mo.ResAuditLog;

import java.util.List;
import java.util.Map;

/**
 * Created by czz on 2017/12/28.
 * 用于记录业务审核流程的日志
 * 该表只提供插入、查询操作，不提供更改，删除操作
 */
public interface IJszyAuditLogBiz {
    /**
     * 保存审核流程表
     * @param resAuditLog
     * @return
     */
    int saveAuditLog(ResAuditLog resAuditLog);

    /**
     * 根据父表流水号查询审核日志记录
     * 按记录创建时间顺序（最新的在最前面）
     * @param parentRecordFlow
     * @return
     */
    List<ResAuditLog> findAuditLogsByParentFlow(String parentRecordFlow);

    /**
     * 根据父表流水号的集合查询审核日志记录
     * 按记录创建时间顺序（最新的在最前面）
     * @param parentRecordFlows
     * @return 返回值是Map,Map的key是集合的parentRecordFlow，value是审核日志的集合
     */
    Map<String,List<ResAuditLog>> findAuditLogsByParentFlows(List<String> parentRecordFlows);

    /**
     * 根据Example查询AuditLog
     * @param paramMap
     * @return
     */
    List<ResAuditLog> findAuditLogByMap(Map<String, Object> paramMap);

    /**
     * 根据父表流水号查询审核日志记录最新的一条审核状态
     * @param parentRecordFlow
     * @return
     */
    ResAuditLog findLastAuditLogByParentFlow(String parentRecordFlow);

    /**
     * 根据父表流水号的集合查询审核日志记录最新的一条审核状态
     * @param parentRecordFlows
     * @return 返回值是Map,Map的key是集合的parentRecordFlow，value是最新的审核日志信息
     */
    Map<String,ResAuditLog> findLastAuditLogByParentFlows(List<String> parentRecordFlows);
}
