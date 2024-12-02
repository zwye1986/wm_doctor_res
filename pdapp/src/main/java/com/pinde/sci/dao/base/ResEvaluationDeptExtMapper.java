package com.pinde.sci.dao.base;

import com.pinde.core.model.ResEvaluationCfg;
import com.pinde.core.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResEvaluationDeptExtMapper {

    /**
     * 获取可用科室列表
     * @return
     */
    List<SysDept> getDeptExt(@Param("cfgFlow") String cfgFlow, @Param("orgFlow")String orgFlow);

    List<ResEvaluationCfg> readResEvaluationCfgByDept(@Param("deptFlow") String deptFlow);
}