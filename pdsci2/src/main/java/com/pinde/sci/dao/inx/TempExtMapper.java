package com.pinde.sci.dao.inx;

import com.pinde.sci.model.mo.PubProjRec;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.ResRec;

import java.util.List;


public interface TempExtMapper {

    List<PubUserResume> selectUserResume();

    List<ResRec> getDeptGradeList();

    List<ResRec> getTeacherGradeList();

    List<PubProjRec> getPubProjRecList();
}