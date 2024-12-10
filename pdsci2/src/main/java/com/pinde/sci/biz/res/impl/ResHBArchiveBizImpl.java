package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResHBArchiveBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResHBArchiveExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResHBArchiveBizImpl implements IResHBArchiveBiz {
    @Autowired
    private ResArchiveSequenceMapper archiveSequenceMapper;
    @Autowired
    private ResHBArchiveExtMapper archiveExtMapper;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private ResUserResumeLogMapper resumeLogMapper;
    @Autowired
    private ResDoctorRecruitLogMapper recruitLogMapper;
    @Autowired
    private ResDoctorLogMapper doctorLogMapper;
    @Autowired
    private SysUserLogMapper userLogMapper;
    @Override
    public int checkArchive(String archiveTime,String sessionNumber) {
        int count = 0;
        if (StringUtil.isNotBlank(archiveTime)) {
            ResArchiveSequenceExample example = new ResArchiveSequenceExample();
            ResArchiveSequenceExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andArchiveTimeEqualTo(archiveTime).andSessionNumberEqualTo(sessionNumber);
            count = archiveSequenceMapper.countByExample(example);

        }
        return count;
    }

    @Override
    public boolean saveArchiveInfo4Hb(String archiveTime,String sessionNumber) throws DocumentException {
        if(StringUtil.isNotBlank(archiveTime)) {
            //保存存档时间信息
            ResArchiveSequence archiveSequence = new ResArchiveSequence();
            archiveSequence.setArchiveFlow(PkUtil.getUUID());
            archiveSequence.setArchiveTime(archiveTime);
            archiveSequence.setSessionNumber(sessionNumber);
            GeneralMethod.setRecordInfo(archiveSequence, true);
            archiveSequenceMapper.insertSelective(archiveSequence);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("archiveTime",archiveTime);
            paramMap.put("sessionNumber",sessionNumber);
            paramMap.put("archiveFlow",archiveSequence.getArchiveFlow());
            int a = archiveExtMapper.createResDoctorLog(paramMap);
            int a2 = archiveExtMapper.saveResDoctorLog(paramMap);
            int b = archiveExtMapper.createSysUserLog(paramMap);
            int b2 = archiveExtMapper.saveSysUserLog(paramMap);
            int c = archiveExtMapper.createResDoctorRecruitLog(paramMap);
            int c2 = archiveExtMapper.saveResDoctorRecruitLog(paramMap);
            int d = archiveExtMapper.createResumeLog(paramMap);
            int d2 = archiveExtMapper.saveResumeLog(paramMap);
            int e = archiveExtMapper.createResRecruitRegisterLog(paramMap);
            int e2 = archiveExtMapper.saveResRecruitRegisterLog(paramMap);
            if (a2 >= 0 && b2 >= 0 && c2 >= 0 && d2 >= 0 && e2 >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ResArchiveSequence> allResArchiveSequence() {
        ResArchiveSequenceExample example = new ResArchiveSequenceExample();
        ResArchiveSequenceExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        example.setOrderByClause("CREATE_TIME DESC");
        return archiveSequenceMapper.selectByExample(example);
    }

    @Override
    public ResArchiveSequence readResArchiveSequence(String archiveFlow) {
        if(StringUtil.isNotBlank(archiveFlow)){
            return archiveSequenceMapper.selectByPrimaryKey(archiveFlow);
        }
        return null;
    }

    @Override
    public List<ResDoctorExt> searchArchiveList(Map<String, Object> paramMap) {
        return archiveExtMapper.searchArchiveList(paramMap);
    }

    @Override
    public SysUser readUserArchive(Map<String, Object> paramMap) {
        return archiveExtMapper.readUserArchive(paramMap);
    }

    @Override
    public ResDoctor readDoctorArchive(Map<String, Object> paramMap) {
        return archiveExtMapper.readDoctorArchive(paramMap);
    }

    @Override
    public PubUserResume readUserResumeArchive(Map<String, Object> paramMap) {
        return archiveExtMapper.readUserResume(paramMap);
    }
}
