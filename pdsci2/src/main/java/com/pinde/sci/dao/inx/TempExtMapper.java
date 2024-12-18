package com.pinde.sci.dao.inx;

import com.pinde.core.model.PubProjRec;
import com.pinde.core.model.PubUserResume;
import com.pinde.core.model.ResRec;

import java.util.List;


public interface TempExtMapper {

    List<PubUserResume> selectUserResume();

    List<ResRec> getDeptGradeList();

    List<ResRec> getTeacherGradeList();

    List<PubProjRec> getPubProjRecList();
}