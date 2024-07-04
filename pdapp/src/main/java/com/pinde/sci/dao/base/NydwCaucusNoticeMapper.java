package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwCaucusNotice;
import com.pinde.sci.model.mo.NydwCaucusNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwCaucusNoticeMapper {
    int countByExample(NydwCaucusNoticeExample example);

    int deleteByExample(NydwCaucusNoticeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwCaucusNotice record);

    int insertSelective(NydwCaucusNotice record);

    List<NydwCaucusNotice> selectByExampleWithBLOBs(NydwCaucusNoticeExample example);

    List<NydwCaucusNotice> selectByExample(NydwCaucusNoticeExample example);

    NydwCaucusNotice selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwCaucusNotice record, @Param("example") NydwCaucusNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") NydwCaucusNotice record, @Param("example") NydwCaucusNoticeExample example);

    int updateByExample(@Param("record") NydwCaucusNotice record, @Param("example") NydwCaucusNoticeExample example);

    int updateByPrimaryKeySelective(NydwCaucusNotice record);

    int updateByPrimaryKeyWithBLOBs(NydwCaucusNotice record);

    int updateByPrimaryKey(NydwCaucusNotice record);
}