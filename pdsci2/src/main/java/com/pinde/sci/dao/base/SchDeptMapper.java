package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchDeptMapper {
    int countByExample(SchDeptExample example);

    int deleteByExample(SchDeptExample example);

    int deleteByPrimaryKey(String schDeptFlow);

    int insert(SchDept record);

    int insertSelective(SchDept record);

    List<SchDept> selectByExample(SchDeptExample example);

    SchDept selectByPrimaryKey(String schDeptFlow);

    int updateByExampleSelective(@Param("record") SchDept record, @Param("example") SchDeptExample example);

    int updateByExample(@Param("record") SchDept record, @Param("example") SchDeptExample example);

    int updateByPrimaryKeySelective(SchDept record);

    int updateByPrimaryKey(SchDept record);
}