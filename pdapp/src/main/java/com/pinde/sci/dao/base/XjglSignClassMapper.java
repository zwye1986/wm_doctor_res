package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.XjglSignClass;
import com.pinde.sci.model.mo.XjglSignClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XjglSignClassMapper {
    int countByExample(XjglSignClassExample example);

    int deleteByExample(XjglSignClassExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(XjglSignClass record);

    int insertSelective(XjglSignClass record);

    List<XjglSignClass> selectByExample(XjglSignClassExample example);

    XjglSignClass selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") XjglSignClass record, @Param("example") XjglSignClassExample example);

    int updateByExample(@Param("record") XjglSignClass record, @Param("example") XjglSignClassExample example);

    int updateByPrimaryKeySelective(XjglSignClass record);

    int updateByPrimaryKey(XjglSignClass record);
}