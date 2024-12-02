package com.pinde.res.biz.stdp.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IResErrorSchNoticeBiz;
import com.pinde.sci.dao.base.ResErrorSchNoticeMapper;
import com.pinde.core.model.ResErrorSchNotice;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResErrorSchNoticeBizImpl implements IResErrorSchNoticeBiz {

    @Autowired
    private ResErrorSchNoticeMapper noticeMapper;

    @Override
    public int edit(ResErrorSchNotice notice, SysUser user) {

        if(notice!=null){
            if(StringUtil.isNotBlank(notice.getRecordFlow())){//修改
                notice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                notice.setModifyUserFlow(user.getUserFlow());
                notice.setModifyTime(DateUtil.getCurrDateTime());
                return this.noticeMapper.updateByPrimaryKeySelective(notice);
            }else{//新增
                notice.setRecordFlow(PkUtil.getUUID());
                notice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                notice.setCreateUserFlow(user.getUserFlow());
                notice.setCreateTime(DateUtil.getCurrDateTime());
                notice.setModifyUserFlow(user.getUserFlow());
                notice.setModifyTime(DateUtil.getCurrDateTime());
                return this.noticeMapper.insertSelective(notice);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }
}
