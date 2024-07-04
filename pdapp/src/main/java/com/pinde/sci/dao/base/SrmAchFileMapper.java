package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchFile;
import com.pinde.sci.model.mo.SrmAchFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchFileMapper {
    int countByExample(SrmAchFileExample example);

    int deleteByExample(SrmAchFileExample example);

    int deleteByPrimaryKey(String fileFlow);

    int insert(SrmAchFile record);

    int insertSelective(SrmAchFile record);

    List<SrmAchFile> selectByExampleWithBLOBs(SrmAchFileExample example);

    List<SrmAchFile> selectByExample(SrmAchFileExample example);

    SrmAchFile selectByPrimaryKey(String fileFlow);

    int updateByExampleSelective(@Param("record") SrmAchFile record, @Param("example") SrmAchFileExample example);

    int updateByExampleWithBLOBs(@Param("record") SrmAchFile record, @Param("example") SrmAchFileExample example);

    int updateByExample(@Param("record") SrmAchFile record, @Param("example") SrmAchFileExample example);

    int updateByPrimaryKeySelective(SrmAchFile record);

    int updateByPrimaryKeyWithBLOBs(SrmAchFile record);

    int updateByPrimaryKey(SrmAchFile record);
}