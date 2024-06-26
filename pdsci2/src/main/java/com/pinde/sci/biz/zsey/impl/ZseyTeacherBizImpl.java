package com.pinde.sci.biz.zsey.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.zsey.IZseyTeacherBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ZseyAppointMainMapper;
import com.pinde.sci.enums.zsey.ZseyAuditStatusEnum;
import com.pinde.sci.model.mo.ZseyAppointMain;
import com.pinde.sci.model.mo.ZseyAppointMainExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZseyTeacherBizImpl implements IZseyTeacherBiz {

    @Autowired
    private ZseyAppointMainMapper mainMapper;

    @Override
    public List<ZseyAppointMain> queryAppointList(String trainDate) {
        ZseyAppointMainExample example = new ZseyAppointMainExample();
        ZseyAppointMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andAuditStatusIdEqualTo(ZseyAuditStatusEnum.Passed.getId()).andTeacherFlowEqualTo(GlobalContext.getCurrentUser().getUserFlow());
        if(StringUtil.isNotBlank(trainDate)){
            criteria.andTrainDateEqualTo(trainDate);
        }
        return mainMapper.selectByExample(example);
    }
}
