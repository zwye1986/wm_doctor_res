package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.common.enums.recruit.RecruitOperEnum;
import com.pinde.core.model.RecruitInfoLog;
import com.pinde.core.model.RecruitInfoLogExample;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitInfoLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitInfoLogBizImpl implements IRecruitInfoLogBiz {

    @Autowired
    private RecruitInfoLogMapper recruitInfoLogMapper;

    @Override
    public Integer updateRecruitInfoLog(RecruitInfoLog recruitInfoLog,String auditContent) {
        recruitInfoLog.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        recruitInfoLog.setOperUserName(GlobalContext.getCurrentUser().getUserName());
        recruitInfoLog.setAuditContent(auditContent);
        if (recruitInfoLog.getLogFlow() != null && recruitInfoLog.getLogFlow() != ""){
            GeneralMethod.setRecordInfo(recruitInfoLog,false);
            return recruitInfoLogMapper.updateByPrimaryKeySelective(recruitInfoLog);
        }else {
            recruitInfoLog.setLogFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitInfoLog,true);
            return recruitInfoLogMapper.insertSelective(recruitInfoLog);
        }
    }

    /**
     * 根据recruitFlow检查是否存在保存的记录
     * @param recruitFlow
     * @return
     */
    @Override
    public RecruitInfoLog checkSavedRecruitInfo(String recruitFlow) {
        RecruitInfoLogExample example = new RecruitInfoLogExample();
        example.createCriteria().andRecruitFlowEqualTo(recruitFlow)
        .andOperTypeIdEqualTo(RecruitOperEnum.Save.getId())
        .andOperTypeNameEqualTo(RecruitOperEnum.Save.getName())
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<RecruitInfoLog> recruitInfoLogs = recruitInfoLogMapper.selectByExample(example);
        if (recruitInfoLogs != null && recruitInfoLogs.size() > 0){
            return recruitInfoLogs.get(0);
        }else {
            return new RecruitInfoLog();
        }
    }

    @Override
    public int saveRecruitLog(RecruitInfoLog log) {
        log.setLogFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(log,true);
        log.setOperTime(DateUtil.getCurrDateTime2());
        log.setAuditTime(DateUtil.getCurrDateTime2());
        return recruitInfoLogMapper.insertSelective(log);
    }
}
