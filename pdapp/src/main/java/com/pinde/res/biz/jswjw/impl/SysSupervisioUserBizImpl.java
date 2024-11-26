package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.InitConfig;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.ISysSupervisioUserBiz;
import com.pinde.res.dao.jswjw.ext.JsresSupervisioSubjectExtMapper;
import com.pinde.sci.dao.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SysSupervisioUserBizImpl implements ISysSupervisioUserBiz {
	@Autowired
	private SysSupervisioUserMapper supervisioUserMapper;
	@Autowired
	private JsresSupervisioSubjectExtMapper subjectExtMapper;
	@Autowired
	private ResSupervisioSubjectMapper subjectMapper;
	@Autowired
	private ResSupervisioSubjectUserMapper subjectUserMapper;
	@Autowired
	private ResEvaluationScoreMapper evaluationScoreMapper;
	@Autowired
	private SysCfgMapper cfgMapper;
	@Autowired
	private JsresSupervisioFileMapper supervisioFileMapper;
	@Autowired
	private SysUserMapper sysUserMapper;


	@Override
	public SysSupervisioUser findBySuUserCode(String userCode) {
		SysSupervisioUserExample sysUserExample=new SysSupervisioUserExample();
		SysSupervisioUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysSupervisioUser> sysUserList = supervisioUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysSupervisioUser findBySuUserCodeNew(String userCode) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserLevelIdEqualTo("expertLeader");
		criteria.andUserCodeEqualTo(userCode);

		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(sysUsers.size()>0){
			SysSupervisioUser sysSupervisioUser = new SysSupervisioUser();
			SysUser sysUser = sysUsers.get(0);
			sysSupervisioUser.setUserFlow(sysUser.getUserFlow());
			sysSupervisioUser.setUserName(sysUser.getUserName());
			sysSupervisioUser.setUserLevelId(sysUser.getUserLevelId());
			sysSupervisioUser.setUserLevelName(sysUser.getUserLevelName());
			sysSupervisioUser.setUserSignUrl(sysUser.getUserSignUrl());
			sysSupervisioUser.setUserCode(sysUser.getUserCode());
			sysSupervisioUser.setUserPhone(sysUser.getUserPhone());
			return sysSupervisioUser;
		}
		return null;
	}

	@Override
	public int addSupervisioUser(SysSupervisioUser user) {
		user.setCreateTime(DateUtil.getCurrDateTime());
        user.setRecordStatus(GlobalConstant.FLAG_Y);
		return supervisioUserMapper.insert(user);
	}

	@Override
	public List<SysSupervisioUser> findByUserPhoneAndNotSelf(String userFlow, String userPhone) {
		SysSupervisioUserExample example = new SysSupervisioUserExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andUserPhoneEqualTo(userPhone).andUserFlowNotEqualTo(userFlow);
		return supervisioUserMapper.selectByExample(example);
	}

	@Override
	public int editSupervisioUser(SysSupervisioUser user) {
		user.setModifyTime(DateUtil.getCurrDateTime());
		return supervisioUserMapper.updateByPrimaryKeySelective(user);
	}


	@Override
	public SysSupervisioUser findBySuUserFlow(String userFlow) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserLevelIdEqualTo("expertLeader");
		criteria.andUserFlowEqualTo(userFlow);
		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(sysUsers.size()>0){
			SysSupervisioUser sysSupervisioUser = new SysSupervisioUser();
			SysUser sysUser = sysUsers.get(0);
			sysSupervisioUser.setUserFlow(sysUser.getUserFlow());
			sysSupervisioUser.setUserName(sysUser.getUserName());
			sysSupervisioUser.setUserLevelId(sysUser.getUserLevelId());
			sysSupervisioUser.setUserLevelName(sysUser.getUserLevelName());
			sysSupervisioUser.setUserSignUrl(sysUser.getUserSignUrl());
			sysSupervisioUser.setUserCode(sysUser.getUserCode());
			sysSupervisioUser.setUserPhone(sysUser.getUserPhone());
			sysSupervisioUser.setOrgFlow(sysUser.getOrgFlow());
			sysSupervisioUser.setOrgName(sysUser.getOrgName());
			return sysSupervisioUser;
		}
		return null;
	}

	@Override
	public SysSupervisioUser findByUserCode(String userCode) {
		SysSupervisioUserExample sysUserExample=new SysSupervisioUserExample();
		SysSupervisioUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysSupervisioUser> sysUserList = supervisioUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysSupervisioUser findByUserPhone(String userPhone) {
		SysSupervisioUserExample sysUserExample=new SysSupervisioUserExample();
		SysSupervisioUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<SysSupervisioUser> sysUserList = supervisioUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public ResSupervisioSubjectUser searchSubjectUser(String userFlow, String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
		ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResSupervisioSubjectUser> list = subjectUserMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> selectSubjectListByParam(Map<String, Object> param) {
		return subjectExtMapper.selectSubjectListByParam(param);
	}

	@Override
	public List<Map<String,Object>> selectSubjectListByParamNew(Map<String, Object> param) {
		return subjectExtMapper.selectSubjectListByParamNew(param);
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

			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
			ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
		}
		return path;
	}

	@Override
	public int updateSubjectFeedback(Map<String, Object> param) {
		return subjectExtMapper.updateSubjectFeedback(param);
	}

	@Override
	public List<ResSupervisioSubjectUser> selectSupervisioUserListByFlow(String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
				.andSubjectFlowEqualTo(subjectFlow);
		return subjectUserMapper.selectByExample(example);
	}

	@Override
	public ResSupervisioSubject selectSubjectByFlow(String subjectFlow) {
		return subjectMapper.selectByPrimaryKey(subjectFlow);
	}

	@Override
	public ResSupervisioSubjectUser selectSubjectUserByFlow(String userFlow, String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
		ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResSupervisioSubjectUser> list = subjectUserMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
		ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(evaluationScore.getSubjectFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && "Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowIsNull();
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && !"Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getItemId())){
			criteria.andItemIdEqualTo(evaluationScore.getItemId());
		}
		return evaluationScoreMapper.selectByExample(example);
	}

	@Override
	public ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
		ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(evaluationScore.getSubjectFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getItemId())){
			criteria.andItemIdEqualTo(evaluationScore.getItemId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && "Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowIsNull();
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && !"Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		List<ResEvaluationScore> list = evaluationScoreMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveScore(ResEvaluationScore evaluationScore,String userFlow) {
		if(StringUtil.isNotBlank(evaluationScore.getScoreFlow())){
			evaluationScore.setModifyUserFlow(userFlow);
			evaluationScore.setModifyTime(DateUtil.getCurrDateTime());
			return evaluationScoreMapper.updateByPrimaryKey(evaluationScore);
		}else{
			evaluationScore.setScoreFlow(PkUtil.getUUID());
			evaluationScore.setCreateUserFlow(userFlow);
			evaluationScore.setCreateTime(DateUtil.getCurrDateTime());
            evaluationScore.setRecordStatus(GlobalConstant.FLAG_Y);
			return evaluationScoreMapper.insert(evaluationScore);
		}
	}

	@Override
	public String checkImg(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))) {
			mimeList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))) {
			suffixList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
			return "请上传 " + InitConfig.getSysCfg("res_file_support_suffix") + "格式的文件";
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String orgFlow, String planYear, String itemId) {
		String path = GlobalConstant.FLAG_N;
		if (file.getSize() > 0) {
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + orgFlow + File.separator + planYear + File.separator + itemId;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//文件名
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String originalFilename = PkUtil.getGUID() + suffix;
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}

			//删除原文件
			if (com.pinde.core.util.StringUtil.isNotBlank(oldFolderName)) {
				try {
					oldFolderName = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if (imgFile.exists()) {
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/" + orgFlow + "/" + planYear + "/" + itemId + "/" + originalFilename;
		}
		return path;
	}

	@Override
	public int saveJsresSupervisioFile(JsresSupervisioFile supervisioFile, String userFlow) {
		if (com.pinde.core.util.StringUtil.isNotBlank(supervisioFile.getRecordFlow())) {
			supervisioFile.setModifyUserFlow(userFlow);
			supervisioFile.setModifyTime(DateUtil.getCurrDateTime());
			return supervisioFileMapper.updateByPrimaryKeySelective(supervisioFile);
		} else {
			supervisioFile.setRecordFlow(PkUtil.getUUID());
			supervisioFile.setCreateUserFlow(userFlow);
			supervisioFile.setCreateTime(DateUtil.getCurrDateTime());
			return supervisioFileMapper.insert(supervisioFile);
		}
	}

	@Override
	public int deleteFileByPrimaryKey(String recordFlow) {
		if (StringUtil.isNotBlank(recordFlow)) {
			return supervisioFileMapper.deleteByPrimaryKey(recordFlow);
		}
		return 0;
	}

	@Override
	public int saveSubjectUser(ResSupervisioSubjectUser subjectUser,String userFlow) {
		if(StringUtil.isNotBlank(subjectUser.getRecordFlow())) {
			subjectUser.setModifyUserFlow(userFlow);
			subjectUser.setModifyTime(DateUtil.getCurrDateTime());
			return subjectUserMapper.updateByPrimaryKeySelective(subjectUser);
		}else {
			subjectUser.setRecordFlow(PkUtil.getUUID());
			subjectUser.setCreateUserFlow(userFlow);
			subjectUser.setCreateTime(DateUtil.getCurrDateTime());
			return subjectUserMapper.insert(subjectUser);
		}
	}

	@Override
	public int saveSubject(ResSupervisioSubject subject,String userFlow) {
		if(StringUtil.isNotBlank(subject.getSubjectFlow())) {
			subject.setModifyUserFlow(userFlow);
			subject.setModifyTime(DateUtil.getCurrDateTime());
			return subjectMapper.updateByPrimaryKeySelective(subject);
		}else {
			subject.setSubjectFlow(PkUtil.getUUID());
			subject.setCreateUserFlow(userFlow);
			subject.setCreateTime(DateUtil.getCurrDateTime());
            subject.setRecordStatus(GlobalConstant.FLAG_Y);
			return subjectMapper.insert(subject);
		}
	}

	@Override
	public int saveSubjectUserTwo(ResSupervisioSubjectUser subjectUser,String userFlow) {
		if(StringUtil.isNotBlank(subjectUser.getRecordFlow())) {
			subjectUser.setModifyUserFlow(userFlow);
			subjectUser.setModifyTime(DateUtil.getCurrDateTime());
			return subjectUserMapper.updateByPrimaryKeySelective(subjectUser);
		}else {
			subjectUser.setRecordFlow(PkUtil.getUUID());
			subjectUser.setCreateUserFlow(userFlow);
			subjectUser.setCreateTime(DateUtil.getCurrDateTime());
            subjectUser.setRecordStatus(GlobalConstant.FLAG_Y);
			return subjectUserMapper.insert(subjectUser);
		}
	}



}
