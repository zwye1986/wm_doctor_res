package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuPatent;
import com.pinde.sci.model.mo.FstuPatentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuPatentMapper {
    int countByExample(FstuPatentExample example);

    int deleteByExample(FstuPatentExample example);

    int deleteByPrimaryKey(String patentFlow);

    int insert(FstuPatent record);

    int insertSelective(FstuPatent record);

    List<FstuPatent> selectByExample(FstuPatentExample example);

    FstuPatent selectByPrimaryKey(String patentFlow);

    int updateByExampleSelective(@Param("record") FstuPatent record, @Param("example") FstuPatentExample example);

    int updateByExample(@Param("record") FstuPatent record, @Param("example") FstuPatentExample example);

    int updateByPrimaryKeySelective(FstuPatent record);

    int updateByPrimaryKey(FstuPatent record);
}