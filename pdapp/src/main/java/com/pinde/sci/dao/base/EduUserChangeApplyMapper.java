package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduUserChangeApply;
import com.pinde.sci.model.mo.EduUserChangeApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduUserChangeApplyMapper {
    int countByExample(EduUserChangeApplyExample example);

    int deleteByExample(EduUserChangeApplyExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduUserChangeApply record);

    int insertSelective(EduUserChangeApply record);

    List<EduUserChangeApply> selectByExampleWithBLOBs(EduUserChangeApplyExample example);

    List<EduUserChangeApply> selectByExample(EduUserChangeApplyExample example);

    EduUserChangeApply selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduUserChangeApply record, @Param("example") EduUserChangeApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") EduUserChangeApply record, @Param("example") EduUserChangeApplyExample example);

    int updateByExample(@Param("record") EduUserChangeApply record, @Param("example") EduUserChangeApplyExample example);

    int updateByPrimaryKeySelective(EduUserChangeApply record);

    int updateByPrimaryKeyWithBLOBs(EduUserChangeApply record);

    int updateByPrimaryKey(EduUserChangeApply record);
}