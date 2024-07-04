package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCollection;
import com.pinde.sci.model.mo.EduCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCollectionMapper {
    int countByExample(EduCollectionExample example);

    int deleteByExample(EduCollectionExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCollection record);

    int insertSelective(EduCollection record);

    List<EduCollection> selectByExample(EduCollectionExample example);

    EduCollection selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCollection record, @Param("example") EduCollectionExample example);

    int updateByExample(@Param("record") EduCollection record, @Param("example") EduCollectionExample example);

    int updateByPrimaryKeySelective(EduCollection record);

    int updateByPrimaryKey(EduCollection record);
}