package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.TjDocinfo;
import com.pinde.sci.model.res.TjDocinfoExt;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface ITjResExamBiz {

    List<TjDocinfoExt> searchTjDocinfoExt(TjDocinfo docinfo);
}
