package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.SchRotationDeptReq;
import com.pinde.core.model.SchRotationDeptReqExample;

import java.util.List;

public interface SchRotationDeptReqExtMapper {
    List<SchRotationDeptReq> selectByExampleTwo(SchRotationDeptReqExample example);
}