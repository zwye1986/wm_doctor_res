package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchProcess;
import com.pinde.sci.model.mo.SrmAchProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchProcessMapper {
    int countByExample(SrmAchProcessExample example);

    int deleteByExample(SrmAchProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(SrmAchProcess record);

    int insertSelective(SrmAchProcess record);

    List<SrmAchProcess> selectByExample(SrmAchProcessExample example);

    SrmAchProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") SrmAchProcess record, @Param("example") SrmAchProcessExample example);

    int updateByExample(@Param("record") SrmAchProcess record, @Param("example") SrmAchProcessExample example);

    int updateByPrimaryKeySelective(SrmAchProcess record);

    int updateByPrimaryKey(SrmAchProcess record);
}