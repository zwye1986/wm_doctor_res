package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchDeptExternalRel;
import com.pinde.sci.model.mo.SchDeptExternalRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchDeptExternalRelMapper {
    int countByExample(SchDeptExternalRelExample example);

    int deleteByExample(SchDeptExternalRelExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchDeptExternalRel record);

    int insertSelective(SchDeptExternalRel record);

    List<SchDeptExternalRel> selectByExample(SchDeptExternalRelExample example);

    SchDeptExternalRel selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchDeptExternalRel record, @Param("example") SchDeptExternalRelExample example);

    int updateByExample(@Param("record") SchDeptExternalRel record, @Param("example") SchDeptExternalRelExample example);

    int updateByPrimaryKeySelective(SchDeptExternalRel record);

    int updateByPrimaryKey(SchDeptExternalRel record);
}