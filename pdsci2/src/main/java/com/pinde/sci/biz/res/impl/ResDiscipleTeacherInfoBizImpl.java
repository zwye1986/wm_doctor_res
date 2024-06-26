package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDiscipleTeacherInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResDiscipleTeacherInfoMapper;
import com.pinde.sci.model.mo.ResDiscipleTeacherInfo;
import com.pinde.sci.model.mo.ResDiscipleTeacherInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ResDiscipleTeacherInfoBizImpl implements IResDiscipleTeacherInfoBiz {
    @Autowired
    private ResDiscipleTeacherInfoMapper teacherInfoMapper;
    @Override
    public ResDiscipleTeacherInfo readResDiscipleTeacherInfo(String userFlow) {
        ResDiscipleTeacherInfoExample example=new ResDiscipleTeacherInfoExample();
        example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<ResDiscipleTeacherInfo> list= teacherInfoMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int savaResDiscipleTeacherInfo(ResDiscipleTeacherInfo bean) {
        if(StringUtil.isBlank(bean.getRecordFlow())) {
            GeneralMethod.setRecordInfo(bean, true);
            bean.setRecordFlow(PkUtil.getUUID());
            return teacherInfoMapper.insert(bean);
        }else{
            GeneralMethod.setRecordInfo(bean, false);
            return teacherInfoMapper.updateByPrimaryKeySelective(bean);
        }
    }

    @Override
    public ResDiscipleTeacherInfo readResDiscipleTeacherInfoByFlow(String infoFlow) {
        return teacherInfoMapper.selectByPrimaryKey(infoFlow);
    }
}
