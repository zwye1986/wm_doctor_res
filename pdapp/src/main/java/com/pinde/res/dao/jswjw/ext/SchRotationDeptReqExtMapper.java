package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.SchRotationDeptReq;
import com.pinde.sci.model.mo.SchRotationDeptReqExample;

import java.util.List;

public interface SchRotationDeptReqExtMapper {
    List<SchRotationDeptReq> selectByExampleTwo(SchRotationDeptReqExample example);
}