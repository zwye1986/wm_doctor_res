package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmExpertGroupUser;
import com.pinde.sci.model.mo.SrmExpertGroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmExpertGroupUserMapper {
    int countByExample(SrmExpertGroupUserExample example);

    int deleteByExample(SrmExpertGroupUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SrmExpertGroupUser record);

    int insertSelective(SrmExpertGroupUser record);

    List<SrmExpertGroupUser> selectByExample(SrmExpertGroupUserExample example);

    SrmExpertGroupUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SrmExpertGroupUser record, @Param("example") SrmExpertGroupUserExample example);

    int updateByExample(@Param("record") SrmExpertGroupUser record, @Param("example") SrmExpertGroupUserExample example);

    int updateByPrimaryKeySelective(SrmExpertGroupUser record);

    int updateByPrimaryKey(SrmExpertGroupUser record);
}