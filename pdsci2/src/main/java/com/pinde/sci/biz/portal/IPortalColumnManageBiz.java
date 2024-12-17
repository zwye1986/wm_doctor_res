package com.pinde.sci.biz.portal;

import com.pinde.core.model.PortalColumn;

import java.util.List;
import java.util.Map;

public interface IPortalColumnManageBiz {
    /**
     * 获取所有栏目
     *
     * @param recordStatus 栏目状态
     * @return
     */
    List<PortalColumn> getAll(String recordStatus, List<String> columnIds);

    /**
     * 保存栏目
     *
     * @param column
     * @return
     */
    String save(PortalColumn column);

    /**
     * 计算栏目id
     *
     * @param parentColumnId 父栏目id
     * @return
     */
    String getNextColumnId(String parentColumnId);
    /**
     * 根据栏目id获取栏目
     *
     * @param columnId 栏目id
     * @return
     */
    PortalColumn getById(String columnId);

    /**
     * 更新栏目
     *
     * @param column
     * @return
     */
    int update(PortalColumn column);
    /**
     * 批量更新记录状态
     *
     * @param ids          id列表
     * @param recordStatus 状态
     * @return
     */
    int updateRecordStatus(List<String> ids, String recordStatus);

    List<PortalColumn> searchInxColumnList(Map<String, Object> paramMap);
}
