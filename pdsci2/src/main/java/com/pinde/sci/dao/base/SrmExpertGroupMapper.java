package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmExpertGroup;
import com.pinde.sci.model.mo.SrmExpertGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmExpertGroupMapper {
    int countByExample(SrmExpertGroupExample example);

    int deleteByExample(SrmExpertGroupExample example);

    int deleteByPrimaryKey(String groupFlow);

    int insert(SrmExpertGroup record);

    int insertSelective(SrmExpertGroup record);

    List<SrmExpertGroup> selectByExample(SrmExpertGroupExample example);

    SrmExpertGroup selectByPrimaryKey(String groupFlow);

    int updateByExampleSelective(@Param("record") SrmExpertGroup record, @Param("example") SrmExpertGroupExample example);

    int updateByExample(@Param("record") SrmExpertGroup record, @Param("example") SrmExpertGroupExample example);

    int updateByPrimaryKeySelective(SrmExpertGroup record);

    int updateByPrimaryKey(SrmExpertGroup record);
}