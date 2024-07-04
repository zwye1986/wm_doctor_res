package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmExpertProj;
import com.pinde.sci.model.mo.SrmExpertProjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmExpertProjMapper {
    int countByExample(SrmExpertProjExample example);

    int deleteByExample(SrmExpertProjExample example);

    int deleteByPrimaryKey(String expertProjFlow);

    int insert(SrmExpertProj record);

    int insertSelective(SrmExpertProj record);

    List<SrmExpertProj> selectByExampleWithBLOBs(SrmExpertProjExample example);

    List<SrmExpertProj> selectByExample(SrmExpertProjExample example);

    SrmExpertProj selectByPrimaryKey(String expertProjFlow);

    int updateByExampleSelective(@Param("record") SrmExpertProj record, @Param("example") SrmExpertProjExample example);

    int updateByExampleWithBLOBs(@Param("record") SrmExpertProj record, @Param("example") SrmExpertProjExample example);

    int updateByExample(@Param("record") SrmExpertProj record, @Param("example") SrmExpertProjExample example);

    int updateByPrimaryKeySelective(SrmExpertProj record);

    int updateByPrimaryKeyWithBLOBs(SrmExpertProj record);

    int updateByPrimaryKey(SrmExpertProj record);
}