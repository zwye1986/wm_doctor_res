package com.pinde.sci.dao.srm;

import com.pinde.sci.model.srm.RegisProjFundExt;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-12-22.
 */
public interface RegisProjFundExtMapper {

    List<RegisProjFundExt> searchProjFundExt(Map<String,String> map);
}
