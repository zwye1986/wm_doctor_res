package com.pinde.sci.dao.srm;

import com.pinde.sci.model.mo.SrmExpert;
import com.pinde.sci.model.srm.SysUserExt;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * expert扩展mapper
 *
 * @author shij
 */
public interface ExpertExtMapper {

    /**
     * 根据不在某专家组的专家
     *
     * @param groupFlow
     * @return
     */
    List<SrmExpert> searchSysUserNotInByGroupFlow(String groupFlow);

    List<SrmExpert> searchExpertInfo(HashMap<String, Object> map);

    /**
     * 查询专家列表不在某个评审设置里的所有专家 当评审设置流水号为null 或 ""时 将查询到所有专家
     *
     * @param evalSetFlow
     * @return
     */
    List<SysUserExt> selectExpertNotInEvalSettingByEvalSetFlow(@Param(value = "evalSetFlow") String evalSetFlow);
}
