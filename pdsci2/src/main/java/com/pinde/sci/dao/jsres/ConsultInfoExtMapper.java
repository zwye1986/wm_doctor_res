package com.pinde.sci.dao.jsres;

import com.pinde.core.model.ConsultInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ConsultInfoExtMapper {
    List<String> searchOrgCityNameList();

    List<ConsultInfo> selectByExampleWithBLOBs(HashMap map);

    Integer searchNumber(@Param("consultInfoFlow")String consultInfoFlow);
}
