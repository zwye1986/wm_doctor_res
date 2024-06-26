package com.pinde.sci.biz.res.impl;

import com.pinde.sci.biz.res.ITjResExamBiz;
import com.pinde.sci.dao.res.TjDocinfoExtMapper;
import com.pinde.sci.model.mo.TjDocinfo;
import com.pinde.sci.model.res.TjDocinfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TjResExamBizImpl implements ITjResExamBiz {
    @Autowired
    private TjDocinfoExtMapper docinfoExtMapper;

    @Override
    public List<TjDocinfoExt> searchTjDocinfoExt(TjDocinfo docinfo) {
        return docinfoExtMapper.searchTjDocinfo(docinfo);
    }
}
