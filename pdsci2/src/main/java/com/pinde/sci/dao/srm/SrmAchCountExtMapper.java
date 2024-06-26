package com.pinde.sci.dao.srm;

import com.pinde.sci.model.srm.SrmAchCountExt;

import java.util.HashMap;
import java.util.List;

public interface SrmAchCountExtMapper {

    List<SrmAchCountExt> selectSrmAch(HashMap<String, String> map);
}
