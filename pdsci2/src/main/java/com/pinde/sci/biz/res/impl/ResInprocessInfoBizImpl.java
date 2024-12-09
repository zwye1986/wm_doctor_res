package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResInprocessInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResDiscipleNoteInfoMapper;
import com.pinde.sci.dao.base.ResInprocessInfoMapper;
import com.pinde.sci.dao.base.ResInprocessInfoMemberMapper;
import com.pinde.sci.dao.base.ResStudentDiscipleTeacherMapper;
import com.pinde.sci.dao.res.DiscipleDoctorExtMapper;
import com.pinde.sci.dao.res.ResInprocessInfoMemberExtMapper;
import com.pinde.sci.model.mo.ResInprocessInfo;
import com.pinde.sci.model.mo.ResInprocessInfoExample;
import com.pinde.sci.model.mo.ResInprocessInfoMember;
import com.pinde.sci.model.mo.ResInprocessInfoMemberExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-12.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResInprocessInfoBizImpl implements IResInprocessInfoBiz {
    @Autowired
    private ResStudentDiscipleTeacherMapper teacherMapper;
    @Autowired
    private ResDiscipleNoteInfoMapper noteInfoMapper;
    @Autowired
    private DiscipleDoctorExtMapper doctorExtMapper;
    @Autowired
    private ResInprocessInfoMapper inprocessInfoMapper;
    @Autowired
    private ResInprocessInfoMemberMapper memberMapper;
    @Autowired
    private ResInprocessInfoMemberExtMapper memberExtMapper;
    @Override
    public ResInprocessInfo readByDeptFlowAndOrgFlow(String deptFlow, String orgFlow) {
        ResInprocessInfoExample example=new ResInprocessInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowEqualTo(deptFlow);
        List<ResInprocessInfo> list=inprocessInfoMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResInprocessInfoMember> readMembersByRecordFlow(String recordFlow) {
        ResInprocessInfoMemberExample example=new ResInprocessInfoMemberExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andInfoRecordFlowEqualTo(recordFlow);

        return memberMapper.selectByExample(example);
    }

    @Override
    public int deleteMemberNotInFlow(String recordFlow, List<String> memberFlows) {

        return memberExtMapper.deleteMemberNotInFlow(recordFlow, memberFlows);
    }

    @Override
    public int saveMember(ResInprocessInfoMember m) {
        if(StringUtil.isNotBlank(m.getRecordFlow()))
        {
            GeneralMethod.setRecordInfo(m,false);
            return memberMapper.updateByPrimaryKeySelective(m);
        }else{
            m.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(m,true);
            return memberMapper.insertSelective(m);
        }
    }

    @Override
    public int saveInfo(ResInprocessInfo info) {
        ResInprocessInfo old=inprocessInfoMapper.selectByPrimaryKey(info.getRecordFlow());
        if(old==null){
            if(StringUtil.isBlank(info.getRecordFlow()))
            {
                info.setRecordFlow(PkUtil.getUUID());
            }
            GeneralMethod.setRecordInfo(info,true);
            return inprocessInfoMapper.insertSelective(info);
        }else {
            GeneralMethod.setRecordInfo(info,false);
            return inprocessInfoMapper.updateByPrimaryKeySelective(info);
        }
    }

    @Override
    public List<ResInprocessInfo> readInfoList(String orgFlow) {
        return memberExtMapper.readInfoList(orgFlow);
    }
    @Override
    public List<ResInprocessInfo> readInfoList4Global(Map<String,Object> map) {
        return memberExtMapper.readInfoList4Global(map);
    }
}
