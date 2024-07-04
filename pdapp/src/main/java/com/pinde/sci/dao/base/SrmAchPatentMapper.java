package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchPatent;
import com.pinde.sci.model.mo.SrmAchPatentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchPatentMapper {
    int countByExample(SrmAchPatentExample example);

    int deleteByExample(SrmAchPatentExample example);

    int deleteByPrimaryKey(String patentFlow);

    int insert(SrmAchPatent record);

    int insertSelective(SrmAchPatent record);

    List<SrmAchPatent> selectByExample(SrmAchPatentExample example);

    SrmAchPatent selectByPrimaryKey(String patentFlow);

    int updateByExampleSelective(@Param("record") SrmAchPatent record, @Param("example") SrmAchPatentExample example);

    int updateByExample(@Param("record") SrmAchPatent record, @Param("example") SrmAchPatentExample example);

    int updateByPrimaryKeySelective(SrmAchPatent record);

    int updateByPrimaryKey(SrmAchPatent record);
}