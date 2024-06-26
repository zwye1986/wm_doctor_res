package com.pinde.sci.biz.srm.impl;

import com.pinde.sci.biz.srm.IAchExportBiz;
import com.pinde.sci.dao.base.SrmAchBookMapper;
import com.pinde.sci.dao.srm.AchExportExtMapper;
import com.pinde.sci.dao.srm.SrmAchCountExtMapper;
import com.pinde.sci.model.srm.AchBookExportExt;
import com.pinde.sci.model.srm.AchPatentExportExt;
import com.pinde.sci.model.srm.AchSatExportExt;
import com.pinde.sci.model.srm.AchThesisExportExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-12-14.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AchExportBizImpl implements IAchExportBiz{
    @Autowired
    private AchExportExtMapper exportExtMapper;

    @Override
    public List<AchThesisExportExt> searchAchThesisExportByDept(Map<String, String> map) {
        return exportExtMapper.searchAchThesisExportByDept(map);
    }

    @Override
    public List<AchThesisExportExt> searchAchThesis(Map<String, String> map) {
        return exportExtMapper.searchAchThesisExt(map);
    }

    @Override
    public List<AchSatExportExt> searchAchSat(Map<String, String> map) {
        return exportExtMapper.searchAchSatExt(map);
    }

    @Override
    public List<AchBookExportExt> searchAchBook(Map<String, String> map) {
        return exportExtMapper.searchAchBookExt(map);
    }

    @Override
    public List<AchPatentExportExt> searchAchPatent(Map<String, String> map) {
        return exportExtMapper.searchAchPatentExt(map);
    }
}
