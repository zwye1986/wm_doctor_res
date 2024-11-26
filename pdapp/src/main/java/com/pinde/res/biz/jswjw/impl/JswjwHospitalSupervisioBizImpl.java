package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.InitConfig;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwHospitalSupervisioBiz;
import com.pinde.res.dao.jswjw.ext.HospitalSupervisioExtMapper;
import com.pinde.sci.dao.base.ResHospSupervSubjectMapper;
import com.pinde.sci.dao.base.ResScheduleScoreMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JswjwHospitalSupervisioBizImpl implements IJswjwHospitalSupervisioBiz {
    @Autowired
    private ResHospSupervSubjectMapper hospSupervSubjectMapper;
    @Autowired
    private HospitalSupervisioExtMapper hospitalSupervisioExtMapper;
    @Autowired
    ResScheduleScoreMapper resScheduleScoreMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    private static Logger logger = LoggerFactory.getLogger(JswjwHospitalSupervisioBizImpl.class);

    @Override
    public List<ResHospSupervSubject> queryMyreviewItems(String userFlow) {
        return hospitalSupervisioExtMapper.queryMyreviewItems(userFlow);
    }

    @Override
    public List<ResHospSupervSubject> queryAllSubject(Map<String, Object> params) {
        return hospitalSupervisioExtMapper.queryAllSubject(params);
    }

    @Override
    public ResHospSupervSubject selectHospSupervisioBySubjectFlow(String subjectFlow) {
        ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
        ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(subjectFlow)){
            criteria.andSubjectFlowEqualTo(subjectFlow);
        }
        List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
        if (null !=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ResHospSupervSubject selectHospSupervisioByActivityFlow(String activityFlow) {

        ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
        ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(activityFlow)){
            criteria.andActivityFlowEqualTo(activityFlow);
        }
        List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
        if (null !=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateHospSupervisioBySubjectFlow(ResHospSupervSubject resHospSupervSubject) {
        ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
        ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
        if (null != resHospSupervSubject.getSubjectFlow()){
            criteria.andSubjectFlowEqualTo(resHospSupervSubject.getSubjectFlow());
            return hospSupervSubjectMapper.updateByExample(resHospSupervSubject,example);
        }
        return 0;
    }

    @Override
    public int insertHospSupervisio(ResHospSupervSubject subject) {
        return hospSupervSubjectMapper.insert(subject);
    }

    @Override
    public List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
            criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
        }

        if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
            criteria.andItemIdEqualTo(scheduleScore.getItemId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
            criteria.andItemNameEqualTo(scheduleScore.getItemName());
        }
        if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
            criteria.andGradeEqualTo(scheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getScoreType())){
            criteria.andScoreTypeEqualTo(scheduleScore.getScoreType());
        }
        return resScheduleScoreMapper.selectByExample(example);
    }

    @Override
    public int saveSchedule(ResScheduleScore scheduleScore,String userFlow) {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        List<ResScheduleScore> scoreList = queryScheduleList(scheduleScore);
        if (scoreList.size() > 0) {
            ResScheduleScore resScheduleScore = scoreList.get(0);
            resScheduleScore.setScore(scheduleScore.getScore());
            resScheduleScore.setModifyUserFlow(user.getUserFlow());
            resScheduleScore.setModifyTime(DateUtil.getCurrDate());
            return resScheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
        } else {
            scheduleScore.setCreateTime(DateUtil.getCurrDate());
            scheduleScore.setScheduleFlow(PkUtil.getUUID());
            scheduleScore.setCreateUserFlow(user.getUserFlow());
            return resScheduleScoreMapper.insert(scheduleScore);
        }
    }

    @Override
    public int saveScheduleDetailed(ResScheduleScore scheduleScore,String userFlow) {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        List<ResScheduleScore> scoreList = queryScheduleList(scheduleScore);
        if (scoreList.size() > 0) {
            ResScheduleScore resScheduleScore = scoreList.get(0);
            resScheduleScore.setItemDetailed(scheduleScore.getItemDetailed());
            resScheduleScore.setModifyUserFlow(user.getUserFlow());
            resScheduleScore.setModifyTime(DateUtil.getCurrDate());
            return resScheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
        } else {
            scheduleScore.setCreateTime(DateUtil.getCurrDate());
            scheduleScore.setEvaluationYear(DateUtil.getYear());
            scheduleScore.setScheduleFlow(PkUtil.getUUID());
            scheduleScore.setCreateUserFlow(user.getUserFlow());
            return resScheduleScoreMapper.insert(scheduleScore);
        }
    }


    @Override
    public SysUser selectUser(String userCode, String leader) {
        SysUserExample example=new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(userCode)){
            criteria.andUserCodeEqualTo(userCode);
        }
        if (StringUtil.isNotBlank(leader)){
            criteria.andUserLevelIdEqualTo(leader);
        }
        List<SysUser> userList = sysUserMapper.selectByExample(example);
        if (null !=userList && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName){
        String path = GlobalConstant.FLAG_N;
        if(file.getSize() > 0){
            //创建目录
            String dateString = DateUtil.getCurrDate2();
            String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator+ dateString ;
            File fileDir = new File(newDir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if(com.pinde.core.util.StringUtil.isNotBlank(oldFolderName)){
                try {
                    oldFolderName = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if(imgFile.exists()){
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = folderName + "/"+dateString+"/"+originalFilename;
            FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
            String localFilePath=fileDir+File.separator+originalFilename;
            String ftpDir= folderName+File.separator +dateString ;
            String ftpFileName=originalFilename;

            logger.info("newDir:::::::::::::"+newDir);
            logger.info("path:::::::::::::"+path);
            System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
            ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
            System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
        }
        return path;
    }


    @Override
    public int editSupervisioUser(SysUser user) {
        user.setRecordStatus(GlobalConstant.FLAG_Y);
        user.setModifyTime(DateUtil.getCurrDateTime());
        user.setModifyUserFlow(user.getUserFlow());
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public List<SysDept> selectDeptByOrgFlow(String orgFlow) {
        return hospitalSupervisioExtMapper.selectDeptByOrgFlow(orgFlow);
    }


    @Override
    public List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
            criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
            criteria.andItemIdLike(scheduleScore.getItemId()+"%");
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
            criteria.andItemNameNotEqualTo(scheduleScore.getItemName());
        }
        if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
            criteria.andGradeEqualTo(scheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
        }
        return resScheduleScoreMapper.selectByExample(example);
    }
}
