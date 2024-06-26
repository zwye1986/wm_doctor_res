package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchCopyright;
import com.pinde.sci.model.mo.SrmAchCopyrightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchCopyrightMapper {
    int countByExample(SrmAchCopyrightExample example);

    int deleteByExample(SrmAchCopyrightExample example);

    int deleteByPrimaryKey(String copyrightFlow);

    int insert(SrmAchCopyright record);

    int insertSelective(SrmAchCopyright record);

    List<SrmAchCopyright> selectByExample(SrmAchCopyrightExample example);

    SrmAchCopyright selectByPrimaryKey(String copyrightFlow);

    int updateByExampleSelective(@Param("record") SrmAchCopyright record, @Param("example") SrmAchCopyrightExample example);

    int updateByExample(@Param("record") SrmAchCopyright record, @Param("example") SrmAchCopyrightExample example);

    int updateByPrimaryKeySelective(SrmAchCopyright record);

    int updateByPrimaryKey(SrmAchCopyright record);
}