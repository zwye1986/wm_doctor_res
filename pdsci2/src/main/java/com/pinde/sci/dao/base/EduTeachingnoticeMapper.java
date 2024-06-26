package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.EduTeachingnoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduTeachingnoticeMapper {
    int countByExample(EduTeachingnoticeExample example);

    int deleteByExample(EduTeachingnoticeExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(EduTeachingnotice record);

    int insertSelective(EduTeachingnotice record);

    List<EduTeachingnotice> selectByExampleWithBLOBs(EduTeachingnoticeExample example);

    List<EduTeachingnotice> selectByExample(EduTeachingnoticeExample example);

    EduTeachingnotice selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") EduTeachingnotice record, @Param("example") EduTeachingnoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") EduTeachingnotice record, @Param("example") EduTeachingnoticeExample example);

    int updateByExample(@Param("record") EduTeachingnotice record, @Param("example") EduTeachingnoticeExample example);

    int updateByPrimaryKeySelective(EduTeachingnotice record);

    int updateByPrimaryKeyWithBLOBs(EduTeachingnotice record);

    int updateByPrimaryKey(EduTeachingnotice record);
}