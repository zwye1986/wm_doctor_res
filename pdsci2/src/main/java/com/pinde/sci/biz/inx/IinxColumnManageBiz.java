package com.pinde.sci.biz.inx;

import com.pinde.sci.model.mo.InxColumn;

import java.util.List;
import java.util.Map;

public interface IinxColumnManageBiz {
    /**
     * 获取所有栏目
     *
     * @param recordStatus 栏目状态
     * @return
     */
    List<InxColumn> getAll(String recordStatus,List<String> columnIds);

    /**
     * 保存栏目
     *
     * @param column
     * @return
     */
    String save(InxColumn column);

    /**
     * 计算栏目id
     *
     * @param parentColumnId 父栏目id
     * @return
     */
    String getNextColumnId(String parentColumnId);
    /**
     * 获取栏目所属的所有父栏目
     * @param columnId 栏目id
     * @return
     */
//	public String getAllparentColumn(String columnId);

    /**
     * 根据栏目id获取栏目
     *
     * @param columnId 栏目id
     * @return
     */
    InxColumn getById(String columnId);

    /**
     * 更新栏目
     *
     * @param column
     * @return
     */
    int update(InxColumn column);
    /**
     * 获取栏目
     * @param flow 主键流水号
     * @return
     */
//	public InxColumn getByFlow(String flow);
    /**
     * 获取栏目扩展
     * @param flow 主键流水号
     * @return
     */
//	public InxColumnExt getExtByFlow(String flow);
    /**
     * 获取子栏目
     * @param columnId
     * @return
     */
//	public List<InxColumn> queryChildColumn(String columnId);

    /**
     * 批量更新记录状态
     *
     * @param ids          id列表
     * @param recordStatus 状态
     * @return
     */
    int updateRecordStatus(List<String> ids, String recordStatus);

    List<InxColumn> searchInxColumnList(Map<String, Object> paramMap);
}
