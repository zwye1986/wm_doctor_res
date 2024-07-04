package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTarinNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResTarinNoticeMapper {
    int countByExample(ResTarinNoticeExample example);

    int deleteByExample(ResTarinNoticeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTarinNotice record);

    int insertSelective(ResTarinNotice record);

    List<ResTarinNotice> selectByExampleWithBLOBs(ResTarinNoticeExample example);

    List<ResTarinNotice> selectByExample(ResTarinNoticeExample example);

    ResTarinNotice selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTarinNotice record, @Param("example") ResTarinNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") ResTarinNotice record, @Param("example") ResTarinNoticeExample example);

    int updateByExample(@Param("record") ResTarinNotice record, @Param("example") ResTarinNoticeExample example);

    int updateByPrimaryKeySelective(ResTarinNotice record);

    int updateByPrimaryKeyWithBLOBs(ResTarinNotice record);

    int updateByPrimaryKey(ResTarinNotice record);
}