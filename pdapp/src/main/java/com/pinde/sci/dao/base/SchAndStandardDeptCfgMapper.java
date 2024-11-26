package com.pinde.sci.dao.base;

import com.pinde.core.model.SchAndStandardDeptCfg;
import com.pinde.core.model.SchAndStandardDeptCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchAndStandardDeptCfgMapper {
    int countByExample(SchAndStandardDeptCfgExample example);

    int deleteByExample(SchAndStandardDeptCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchAndStandardDeptCfg record);

    int insertSelective(SchAndStandardDeptCfg record);

    List<SchAndStandardDeptCfg> selectByExample(SchAndStandardDeptCfgExample example);

    SchAndStandardDeptCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchAndStandardDeptCfg record, @Param("example") SchAndStandardDeptCfgExample example);

    int updateByExample(@Param("record") SchAndStandardDeptCfg record, @Param("example") SchAndStandardDeptCfgExample example);

    int updateByPrimaryKeySelective(SchAndStandardDeptCfg record);

    int updateByPrimaryKey(SchAndStandardDeptCfg record);
}