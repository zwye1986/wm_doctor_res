package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuCredit;
import com.pinde.sci.model.mo.FstuCreditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuCreditMapper {
    int countByExample(FstuCreditExample example);

    int deleteByExample(FstuCreditExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(FstuCredit record);

    int insertSelective(FstuCredit record);

    List<FstuCredit> selectByExample(FstuCreditExample example);

    FstuCredit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") FstuCredit record, @Param("example") FstuCreditExample example);

    int updateByExample(@Param("record") FstuCredit record, @Param("example") FstuCreditExample example);

    int updateByPrimaryKeySelective(FstuCredit record);

    int updateByPrimaryKey(FstuCredit record);
}