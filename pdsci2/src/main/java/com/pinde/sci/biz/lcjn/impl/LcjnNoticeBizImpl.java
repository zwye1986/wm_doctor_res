package com.pinde.sci.biz.lcjn.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnNoticeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.lcjn.LcjnNoticeExtMapper;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.InxInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnNoticeBizImpl implements ILcjnNoticeBiz {

    @Autowired
    private InxInfoMapper inxInfoMapper;
//    @Autowired
//    private OscaNoticeExtMapper oscaNoticeExtMapper;
    @Autowired
    private LcjnNoticeExtMapper noticeExtMapper;

    @Override
    public int save(InxInfo info) {
        info.setColumnId("lcjn");
        info.setColumnName("公告");
        return editInfo(info);
    }

    @Override
    public int editInfo(InxInfo info) {
        if(StringUtil.isBlank(info.getInfoFlow())){
            info.setInfoFlow(PkUtil.getUUID());
            info.setViewNum(new Long(0));
            info.setInfoTime(DateUtil.getCurrDateTime());
            GeneralMethod.setRecordInfo(info, true);
            return this.inxInfoMapper.insert(info);
        }else{
            GeneralMethod.setRecordInfo(info, false);
            return this.inxInfoMapper.updateByPrimaryKeySelective(info);
        }
    }

    @Override
    public List<InxInfo> findNotice(InxInfo info) {
        InxInfoExample example = new InxInfoExample();
        example.setOrderByClause("INFO_TIME DESC");
        InxInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(info.getColumnId())) {
            criteria.andColumnIdEqualTo(info.getColumnId());
        }
        return inxInfoMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<InxInfo> searchNotice(InxInfo info) {
        InxInfoExample example = new InxInfoExample();
        InxInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("INFO_TIME DESC");
        if (StringUtil.isNotBlank(info.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(info.getOrgFlow());
        }
        return inxInfoMapper.selectByExample(example);
    }

//	@Override
//	public List<InxInfo> searchSevenDaysNotice(InxInfo info) {
//		String beforeSevenDay = DateUtil.addHour(DateUtil.getCurrDateTime(),-7*24).substring(0,8);
//
//		InxInfoExample example = new InxInfoExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andInfoTimeGreaterThanOrEqualTo(beforeSevenDay);
//		example.setOrderByClause("INFO_TIME DESC");
//		return inxInfoMapper.selectByExample(example);
//	}

    @Override
    public InxInfo findNoticByFlow(String flow) {
        return this.inxInfoMapper.selectByPrimaryKey(flow);
    }

    @Override
    public int delNotice(String flow) {
        InxInfo record = new InxInfo();
        record.setInfoFlow(flow);
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(record, false);
        return this.inxInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<InxInfo> findNoticeWithBLOBs(InxInfo info) {
        InxInfoExample example = new InxInfoExample();
        InxInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(info.getColumnId())) {
            criteria.andColumnIdEqualTo(info.getColumnId());
        }
        example.setOrderByClause("INFO_TIME DESC");
        return inxInfoMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<InxInfo> searchInfoByOrgBeforeDate(String orgFlow,String date){
        return noticeExtMapper.searchInfoByOrgBeforeDate(orgFlow,date);
    }
}
