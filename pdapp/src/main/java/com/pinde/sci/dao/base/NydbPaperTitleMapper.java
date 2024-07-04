package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydbPaperTitle;
import com.pinde.sci.model.mo.NydbPaperTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydbPaperTitleMapper {
    int countByExample(NydbPaperTitleExample example);

    int deleteByExample(NydbPaperTitleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydbPaperTitle record);

    int insertSelective(NydbPaperTitle record);

    List<NydbPaperTitle> selectByExampleWithBLOBs(NydbPaperTitleExample example);

    List<NydbPaperTitle> selectByExample(NydbPaperTitleExample example);

    NydbPaperTitle selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydbPaperTitle record, @Param("example") NydbPaperTitleExample example);

    int updateByExampleWithBLOBs(@Param("record") NydbPaperTitle record, @Param("example") NydbPaperTitleExample example);

    int updateByExample(@Param("record") NydbPaperTitle record, @Param("example") NydbPaperTitleExample example);

    int updateByPrimaryKeySelective(NydbPaperTitle record);

    int updateByPrimaryKeyWithBLOBs(NydbPaperTitle record);

    int updateByPrimaryKey(NydbPaperTitle record);
}