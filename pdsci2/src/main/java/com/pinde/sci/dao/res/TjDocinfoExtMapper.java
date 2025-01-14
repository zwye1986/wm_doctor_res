package com.pinde.sci.dao.res;

import com.pinde.core.model.TjDocinfo;
import com.pinde.core.model.TjDocinfoExt;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface TjDocinfoExtMapper {

    List<TjDocinfoExt> searchTjDocinfo(TjDocinfo tjDocinfo);
}
