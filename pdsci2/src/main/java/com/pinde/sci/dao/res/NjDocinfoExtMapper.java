package com.pinde.sci.dao.res;

import com.pinde.core.model.TjDocinfo;
import com.pinde.core.model.TjDocinfoExample;
import com.pinde.sci.model.res.NjDocinfoExt;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface NjDocinfoExtMapper {

    List<NjDocinfoExt> searchNjDocinfo(TjDocinfo tjDocinfo);

    List<NjDocinfoExt> selectByParam(TjDocinfoExample example);

    String selectMaxTickNumber();
}
