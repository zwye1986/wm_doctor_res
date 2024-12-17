package com.pinde.sci.biz.jsres.impl;


import com.alibaba.fastjson.JSON;
import com.pinde.core.common.sci.dao.SysRoleMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.base.ResOrgSpeMapper;
import com.pinde.sci.dao.base.ResPassScoreCfgMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.dao.jsres.ResBaseExtMapper;
import com.pinde.sci.form.jsres.*;
import com.pinde.sci.model.jsres.ResBaseExt;
import com.pinde.core.model.ResJointOrg;
import com.pinde.sci.model.mo.ResPassScoreCfg;
import com.pinde.sci.model.mo.ResPassScoreCfgExample;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.model.SysUserRoleExample;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResBaseBizImpl implements IJsResBaseBiz{

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private ResOrgSpeMapper resOrgSpeMapper;
	@Autowired
	private ResBaseExtMapper resBaseExtMapper;
	@Autowired
	private ResPassScoreCfgMapper resPassScoreCfgMapper;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private IFileBiz pubFileBiz;

	@Autowired
	private IResJointOrgBiz jointOrgBiz;

	/**
	 * 保存基地的基本信息
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	@Override
	public int saveBaseInfo(String flag, BaseInfoForm baseInfoForm, String index, String type, String[] fileFlows, HttpServletRequest request,
							String[] jointOrgFlows, String[] speIds, String[] fileUploadNum, String[] jointContractFileFlows, String[] fileRemainNum,
							BaseExtInfo baseExtInfoJson) throws Exception {
		SysOrg sysOrg = baseInfoForm.getSysOrg();
		if(sysOrg!=null && DateUtil.getYear().equals(baseInfoForm.getResBase().getSessionNumber())){
			orgBiz.update(sysOrg);
		}
		ResBase resBase=readBaseBySessionNumber(sysOrg.getOrgFlow(), baseInfoForm.getResBase().getSessionNumber());
//		ResBase resBase=readBase(sysOrg.getOrgFlow());
//		baseInfoForm.getResBase();
		String xml= null;
		String json = null;
		BaseExtInfoForm baseExtInfo = null ;
		BaseExtInfoJson baseExtInfoAllJson = null;
		boolean add = true;
		if(resBase != null){
			add = false;
			xml=resBase.getBaseInfo();
			baseExtInfo =JaxbUtil.converyToJavaBean(xml, BaseExtInfoForm.class);
			json = resBase.getBaseExtInfo();
			baseExtInfoAllJson = JSON.parseObject(json, BaseExtInfoJson.class);
		}else{
			resBase=baseInfoForm.getResBase();
			resBase.setOrgName(baseInfoForm.getSysOrg().getOrgName());
			baseExtInfo=new BaseExtInfoForm();
		}

        if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(flag) || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(flag)) {
			handleJointOrg(request, jointOrgFlows, speIds, fileUploadNum, jointContractFileFlows, fileRemainNum, sysOrg.getOrgFlow(), baseInfoForm.getResBase().getSessionNumber());
		}

		if(baseExtInfoAllJson == null) {
			baseExtInfoAllJson = new BaseExtInfoJson();
		}

        if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(flag) || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(flag)) {
			baseExtInfoAllJson.setBaseExtInfoEducationInfo(baseExtInfoJson.getBaseExtInfoEducationInfo());
		}

		if(baseInfoForm!=null){
            if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(flag)) {
				baseExtInfo.setEducationInfo(baseInfoForm.getEducationInfo());
            } else if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(flag)) {
				baseExtInfo.setOrganizationManage(baseInfoForm.getOrganizationManage());
            } else if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(flag)) {
				baseExtInfo.setSupportCondition(baseInfoForm.getSupportCondition());
            } else if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(flag)) {
				baseExtInfo.setBasicInfo(baseInfoForm.getBasicInfo());
				baseExtInfo.setSysOrg(baseInfoForm.getSysOrg());
            } else if (com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(flag)) {
				baseExtInfo.setBasicInfo(baseInfoForm.getBasicInfo());
				baseExtInfo.setSysOrg(baseInfoForm.getSysOrg());
				baseExtInfo.setEducationInfo(baseInfoForm.getEducationInfo());
			}
			// 编辑无法保存 0017045
			ResBase updateResBase = baseInfoForm.getResBase();
			if(updateResBase != null){
				String email = updateResBase.getEmail() == null ? "" : updateResBase.getEmail();
				if(StringUtil.isNotBlank(email)){
					resBase.setEmail(email);
				}
				// 全科医师基地获批文号
				String gpsApprovalNumberId = updateResBase.getGpsApprovalNumberId();
				if(StringUtil.isNotBlank(gpsApprovalNumberId)){
					resBase.setGpsApprovalNumberId(gpsApprovalNumberId);
                    String dictGpsApproval = com.pinde.core.common.enums.DictTypeEnum.GeneralBaseApproNum.getDictNameById(gpsApprovalNumberId);
					resBase.setGpsApprovalNumberName(dictGpsApproval);
				}
				// 住院医师基地获批文号 resApprovalNumberName
				String resApprovalNumberId = updateResBase.getResApprovalNumberId();
				if(StringUtil.isNotBlank(resApprovalNumberId)){
					resBase.setResApprovalNumberId(resApprovalNumberId);
                    String dictResApproval = com.pinde.core.common.enums.DictTypeEnum.ResidentBaseApproveNum.getDictNameById(resApprovalNumberId);
					resBase.setResApprovalNumberName(dictResApproval);
				}
				String baseGradeId = updateResBase.getBaseGradeId();
				if(StringUtil.isNotBlank(baseGradeId)){
					resBase.setBaseGradeId(baseGradeId);
                    resBase.setBaseGradeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseLevel, baseGradeId));
				}
				String baseTypeId = updateResBase.getBaseTypeId();
				if(StringUtil.isNotBlank(baseTypeId)){
					resBase.setBaseTypeId(baseTypeId);
                    resBase.setBaseTypeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BaseType, baseTypeId));
				}
				String basePropertyId = updateResBase.getBasePropertyId();
				if(StringUtil.isNotBlank(basePropertyId)){
					resBase.setBasePropertyId(basePropertyId);
                    resBase.setBasePropertyName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.BasProperty, basePropertyId));
				}
			}
		}
		BasicInfoForm basicInfo = baseExtInfo.getBasicInfo();
		if(basicInfo != null) {
			if (StringUtil.isNotBlank(basicInfo.getJdfzrTitleId())) {
                basicInfo.setJdfzrTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(basicInfo.getJdfzrTitleId()));
			}
			if (StringUtil.isNotBlank(basicInfo.getJdfzrPostId())) {
                basicInfo.setJdfzrPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(basicInfo.getJdfzrPostId()));
			}
			String levelRank = basicInfo.getLevelRank();
			if(StringUtil.isNotBlank(levelRank)){
                String levelRankName = com.pinde.core.common.enums.DictTypeEnum.OrgLevelRank.getDictNameById(levelRank);
				basicInfo.setLevelRankName(levelRankName);
			}
			if (CollectionUtils.isNotEmpty(basicInfo.getYwfgfzrList())) {
				for (ContactorInfoForm contactorInfoForm : basicInfo.getYwfgfzrList()) {
					if (StringUtil.isNotBlank(contactorInfoForm.getTitleId())) {
                        contactorInfoForm.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(contactorInfoForm.getTitleId()));
					}
					if (StringUtil.isNotBlank(contactorInfoForm.getPostId())) {
                        contactorInfoForm.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(contactorInfoForm.getPostId()));
					}
				}
			}
			if (CollectionUtils.isNotEmpty(basicInfo.getZpywkslxrList())) {
				for (ContactorInfoForm contactorInfoForm : basicInfo.getZpywkslxrList()) {
					if (StringUtil.isNotBlank(contactorInfoForm.getTitleId())) {
                        contactorInfoForm.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(contactorInfoForm.getTitleId()));
					}
					if (StringUtil.isNotBlank(contactorInfoForm.getPostId())) {
                        contactorInfoForm.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(contactorInfoForm.getPostId()));
					}
				}
			}
			if (CollectionUtils.isNotEmpty(basicInfo.getZpglbmfzrList())) {
				for (ContactorInfoForm contactorInfoForm : basicInfo.getZpglbmfzrList()) {
					if (StringUtil.isNotBlank(contactorInfoForm.getTitleId())) {
                        contactorInfoForm.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(contactorInfoForm.getTitleId()));
					}
					if (StringUtil.isNotBlank(contactorInfoForm.getPostId())) {
                        contactorInfoForm.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(contactorInfoForm.getPostId()));
					}
				}
			}
			if (CollectionUtils.isNotEmpty(basicInfo.getContactManList())) {
				for (ContactorInfoForm contactorInfoForm : basicInfo.getContactManList()) {
					if (StringUtil.isNotBlank(contactorInfoForm.getTitleId())) {
                        contactorInfoForm.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(contactorInfoForm.getTitleId()));
					}
					if (StringUtil.isNotBlank(contactorInfoForm.getPostId())) {
                        contactorInfoForm.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(contactorInfoForm.getPostId()));
					}
				}
			}
		}
		xml=JaxbUtil.convertToXml(baseExtInfo);
		resBase.setBaseInfo(xml);
        resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
		if(resBase.getSessionNumber() == null) {
			resBase.setSessionNumber(baseInfoForm.getResBase().getSessionNumber());
		}

		String saveExtInfo = JSON.toJSONString(baseExtInfoAllJson);
		resBase.setBaseExtInfo(saveExtInfo);

		if(StringUtil.isNotBlank(type))
		{
			List<String> fileFlowList =null;
			if(fileFlows!=null) {
				//上传文件的流水号
				fileFlowList =Arrays.asList(fileFlows);
			}
			//处理不在本次保存中的文件
			upadteFileInfo(resBase.getOrgFlow() + resBase.getSessionNumber(), fileFlowList,type);
			//处理上传文件
			addUploadFile(resBase.getOrgFlow() + resBase.getSessionNumber(), request, type);
		}
		//
		if(add) { // 下面的方法里，会根据orgFlow判断是否更新还是新增，这里给下面的saveResBase用
			resBase.setOrgFlow(null);
		}
		return saveResBase(resBase);
  	}

	/**
	 * 时间紧，简单做，后台先不校验了
	 * @param request
	 * @param jointOrgFlows
	 * @param speIds
	 * @param fileUploadNum
	 */
	private void handleJointOrg(HttpServletRequest request, String[] jointOrgFlows, String[] speIds, String[] fileUploadNum, String[] jointContractFileFlows, String[] fileRemainNum, String orgFlow, String sessionNumber) {
		ResJointOrg deleteOrg = new ResJointOrg();
		deleteOrg.setSessionNumber(sessionNumber);
		deleteOrg.setOrgFlow(orgFlow);
		List<ResJointOrg> allJointOrgList = jointOrgBiz.searchResJoint(deleteOrg);
		if(ArrayUtils.isEmpty(jointOrgFlows)) {
			if(CollectionUtils.isNotEmpty(allJointOrgList)) {
				jointOrgBiz.deleteByOrgFlow(orgFlow, sessionNumber);
			}

			for (ResJointOrg resJointOrg : allJointOrgList) {
				pubFileBiz.deleteFileByTypeFlow("jointContract", resJointOrg.getJointFlow());
			}
			return;
		}
		if(ArrayUtils.isNotEmpty(jointContractFileFlows)) {
			jointContractFileFlows = Arrays.stream(jointContractFileFlows).filter(vo -> StringUtils.isNotEmpty(vo)).toArray(len -> new String[len]);
		}
		List<String> jointOrgFlowList = Arrays.stream(jointOrgFlows).collect(Collectors.toList());

		 // 上传的协同关系协议
		 List<MultipartFile> files = new ArrayList<>();
		 int curNum = 0;
		 int remainNum = 0;
		 if(request instanceof DefaultMultipartHttpServletRequest && ((DefaultMultipartHttpServletRequest) request).isResolved()) {
			 files = ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("files");
			 if(files == null) {
				 files = new ArrayList<>();
			 }
			 files = files.stream().filter(vo -> StringUtils.isNotEmpty(vo.getOriginalFilename())).collect(Collectors.toList());
		 }

		Map<String, String> orgToSpeMap = new HashMap<>();
		 List<String> deletingFileFlowList = new ArrayList<>();
		 // 对协议，已有的flow不变，此次少的删的，此次上传的插入
		 // 协同单位此次上传的协同关系协议
		 Map<String, List<MultipartFile>> jointFlowToFileListMap = new HashMap<>();
		 for(int i = 0; i < jointOrgFlows.length; i++) {
			 String jointOrgFlow = jointOrgFlows[i];
			 orgToSpeMap.put(jointOrgFlow, speIds[i]);
			 int fileNum = Integer.parseInt(fileUploadNum[i]);
			 List<MultipartFile> curFileList = new ArrayList<>();
			 for(int j = 0; j < fileNum; j++) {
				 curFileList.add(files.get(curNum++));
			 }
			 jointFlowToFileListMap.put(jointOrgFlow, curFileList);
			 // 协同单位之前的协同关系协议的flow
			 List<String> fileRemainList = new ArrayList<>();
			 int remainFileNum = Integer.parseInt(fileRemainNum[i]);
			 for(int j = 0; j < remainFileNum; j++) {
				 fileRemainList.add(jointContractFileFlows[remainNum++]);
			 }

			 ResJointOrg jointOrgFind = allJointOrgList.stream().filter(vo -> vo.getJointOrgFlow().equals(jointOrgFlow)).findFirst().orElse(null);
			 if(jointOrgFind != null) {
				 List<PubFile> oldFileList = pubFileBiz.findFileByTypeFlow("jointContract", jointOrgFind.getJointFlow());
				 for (PubFile pubFile : oldFileList) {
					 if (!fileRemainList.contains(pubFile.getFileFlow())) {
						 deletingFileFlowList.add(pubFile.getFileFlow());
					 }
				 }
			 }
		 }
		 if(CollectionUtils.isNotEmpty(deletingFileFlowList)) {
			 pubFileBiz.deleteFile(deletingFileFlowList);
		 }

		 // 查出原先的协同单位：两边都有的更新，原先有的删除，这次有的新增
		List<String> alreadyJointOrgFlowList = allJointOrgList.stream().map(vo -> vo.getJointOrgFlow()).collect(Collectors.toList());
		// 交集:更新
		List<String> updateList = new ArrayList(CollectionUtils.intersection(jointOrgFlowList, alreadyJointOrgFlowList));
		// 差集：删除
		List<String> deleteList = new ArrayList(CollectionUtils.subtract(alreadyJointOrgFlowList, jointOrgFlowList));
		// 差集：新增
		List<String> insertList = new ArrayList(CollectionUtils.subtract(jointOrgFlowList, alreadyJointOrgFlowList));

		if(CollectionUtils.isNotEmpty(updateList)) {
			List<ResJointOrg> updateJointList = new ArrayList<>();
			List<PubFile> insertFileList = new ArrayList<>();
			//
			for (String updateOrgFlow : updateList) {

				for (ResJointOrg jointOrg : allJointOrgList) {
					if(updateOrgFlow.equals(jointOrg.getJointOrgFlow())) {
						jointOrg.setSpeId(orgToSpeMap.get(updateOrgFlow));
                        jointOrg.setSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(orgToSpeMap.get(updateOrgFlow)));
						List<MultipartFile> fileList =  jointFlowToFileListMap.get(updateOrgFlow);
						for (MultipartFile file : fileList) {
							//保存附件
							PubFile pubFile = new PubFile();
							//取得当前上传文件的文件名称
							String oldFileName = file.getOriginalFilename();
							//定义上传路径
							String dateString = DateUtil.getCurrDate2();
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "jointContract" + File.separator + dateString;
							File fileDir = new File(newDir);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//重命名上传后的文件名
							String originalFilename = "";
							originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
							File newFile = new File(fileDir, originalFilename);
							try {
								file.transferTo(newFile);
							} catch (Exception e) {
                                logger.error("", e);
								throw new RuntimeException("保存文件失败！");
							}
							String filePath = File.separator + "jointContract" + File.separator + dateString + File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setProductType("jointContract");
							//根据记录的主键存入pubFile中，查询时直接根据fileBiz.searchByProductFlow(recordFlow);返回的是List<PubFile>
							pubFile.setProductFlow(jointOrg.getJointFlow());
							insertFileList.add(pubFile);
						}

						updateJointList.add(jointOrg);
						break;
					}
				}
			}
			if(CollectionUtils.isNotEmpty(insertFileList)) {
				pubFileBiz.saveFiles(insertFileList);
			}
			jointOrgBiz.editJointOrgList(updateJointList);
		}

		if(CollectionUtils.isNotEmpty(deleteList)) {
			ResJointOrg deletingList = new ResJointOrg();
			deletingList.setJointOrgFlowList(deleteList);
			jointOrgBiz.deleteJointOrg(deletingList);
		}

		if(CollectionUtils.isNotEmpty(insertList)) {
			List<String> orgFlowList = new ArrayList(insertList);
			orgFlowList.add(orgFlow);
			List<SysOrg> orgList = orgBiz.searchOrgFlowIn(orgFlowList);
			Map<String, String> orgFlowToNameMap = orgList.stream().collect(Collectors.toMap(vo -> vo.getOrgFlow(), vo -> vo.getOrgName(), (vo1, vo2) -> vo1));
			List<PubFile> insertFileList = new ArrayList<>();
			List<ResJointOrg> insertJointList = new ArrayList<>();
			for (String insertJointFlow : insertList) {
				ResJointOrg resJointOrg = new ResJointOrg();
				resJointOrg.setJointFlow(PkUtil.getUUID());
				resJointOrg.setSpeId(orgToSpeMap.get(insertJointFlow));
                resJointOrg.setSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(orgToSpeMap.get(insertJointFlow)));
				resJointOrg.setJointOrgFlow(insertJointFlow);
				resJointOrg.setJointOrgName(orgFlowToNameMap.get(insertJointFlow));
				resJointOrg.setOrgFlow(orgFlow);
				resJointOrg.setOrgName(orgFlowToNameMap.get(orgFlow));
				resJointOrg.setSessionNumber(sessionNumber);

				GeneralMethod.setRecordInfo(resJointOrg, true);
				insertJointList.add(resJointOrg);

				List<MultipartFile> fileList =  jointFlowToFileListMap.get(insertJointFlow);
				for (MultipartFile file : fileList) {
					//保存附件
					PubFile pubFile = new PubFile();
					//取得当前上传文件的文件名称
					String oldFileName = file.getOriginalFilename();
					//定义上传路径
					String dateString = DateUtil.getCurrDate2();
					String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "jointContract" + File.separator + dateString;
					File fileDir = new File(newDir);
					if (!fileDir.exists()) {
						fileDir.mkdirs();
					}
					//重命名上传后的文件名
					String originalFilename = "";
					originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
					File newFile = new File(fileDir, originalFilename);
					try {
						file.transferTo(newFile);
					} catch (Exception e) {
                        logger.error("", e);
						throw new RuntimeException("保存文件失败！");
					}
					String filePath = File.separator + "jointContract" + File.separator + dateString + File.separator + originalFilename;
                    pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					pubFile.setFilePath(filePath);
					pubFile.setFileName(oldFileName);
					pubFile.setProductType("jointContract");
					//根据记录的主键存入pubFile中，查询时直接根据fileBiz.searchByProductFlow(recordFlow);返回的是List<PubFile>
					pubFile.setProductFlow(resJointOrg.getJointFlow());
					insertFileList.add(pubFile);
				}

				if(CollectionUtils.isNotEmpty(insertFileList)) {
					pubFileBiz.saveFiles(insertFileList);
				}
			}
			jointOrgBiz.saveAll(insertJointList);
		}
	 }


	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows, String type) {
		//查询出不在本次保存中的文件信息
		List<PubFile> files=pubFileBiz.searchByProductFlowAndTypeAndNotInFileFlows(recordFlow,fileFlows,type);
		//删除服务器中相应的文件
		if(files!=null&&files.size()>0)
		{
			String basePath = InitConfig.getSysCfg("upload_base_dir");
			for (PubFile pubFile : files) {
//                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
//                    String filePath = basePath + pubFile.getFilePath();
//                    FileUtil.deletefile(filePath);
//                }
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				pubFileBiz.editFile(pubFile);
			}
		}
	}
	//保存上传的附件
	private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
		//以下为多文件上传********************************************
		//创建一个通用的多部分解析器
		List<PubFile> pubFiles=new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				String key=iter.next();
				List<MultipartFile> files = multiRequest.getFiles(key);
				if(files != null&&files.size()>0){
					for(MultipartFile file:files) {
						//保存附件
						PubFile pubFile = new PubFile();
						//取得当前上传文件的文件名称
						String oldFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtil.isNotBlank(oldFileName)) {
							//定义上传路径
							String dateString = DateUtil.getCurrDate2();
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "resBaseInfo" + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
							File fileDir = new File(newDir);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//重命名上传后的文件名
							String originalFilename = "";
							originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
							File newFile = new File(fileDir, originalFilename);
							try {
								file.transferTo(newFile);
							} catch (Exception e) {
                                logger.error("", e);
								throw new RuntimeException("保存文件失败！");
							}
							String filePath = File.separator + "resBaseInfo" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
                            if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(noteTypeId) || com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(noteTypeId)) {
								pubFile.setProductType(noteTypeId);
								pubFile.setFileUpType(key);
							}else{
								pubFile.setProductType(noteTypeId);
							}
							pubFile.setProductFlow(recordFlow);
							pubFiles.add(pubFile);
						}
					}
				}
				//记录上传该文件后的时间
				//int finaltime = (int) System.currentTimeMillis();
			}
		}
		if(pubFiles.size()>0)
		{
			pubFileBiz.saveFiles(pubFiles);
		}
	}


	@Override
	public ResBase readBase(String orgFlow) {
		ResBase resBase = null;
		if(StringUtil.isNotBlank(orgFlow)){
			resBase = resBaseMapper.selectByPrimaryKey(orgFlow);
		}
		return  resBase ;
	}
	
	@Override
	public int  saveTrainSpe(List<ResOrgSpe> saveList, String trainCategoryType, String orgFlow, String orgName) {
		ResOrgSpe exitSpe=new ResOrgSpe();
		exitSpe.setOrgFlow(orgFlow);
		List<ResOrgSpe> exitSpeList = resOrgSpeBiz.searchResOrgSpeList(exitSpe, trainCategoryType);
		Map<String, ResOrgSpe> deleteMap = new HashMap<String, ResOrgSpe>();
		List<ResOrgSpe> oldStatusYList = new ArrayList<ResOrgSpe>();
		List<ResOrgSpe> oldStatusNList = new ArrayList<ResOrgSpe>();
		if(exitSpeList != null && !exitSpeList.isEmpty()){
				for(ResOrgSpe  s: exitSpeList){
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(s.getRecordStatus())) {
						deleteMap.put(s.getOrgSpeFlow(), s );
						oldStatusYList.add(s);
					}else{
						oldStatusNList.add(s);
					}
				}
		}
		if(saveList != null && !saveList.isEmpty()){
			for(ResOrgSpe s: saveList){
				boolean addFlag = true;
				if(oldStatusYList!= null && !oldStatusYList.isEmpty()){
					for(ResOrgSpe oldResOrgSpe: oldStatusYList){
						if(s.getSpeTypeId().equals(oldResOrgSpe.getSpeTypeId()) &&  s.getSpeId().equals(oldResOrgSpe.getSpeId()) && orgFlow.equals(oldResOrgSpe.getOrgFlow()) ){
							addFlag = false;
							if(deleteMap.size()>0){
								deleteMap.remove(oldResOrgSpe.getOrgSpeFlow());
							}
							break;
						}
					}
				}
				if(addFlag){
					ResOrgSpe addOrgSpe = new ResOrgSpe();
					addOrgSpe.setSpeId(s.getSpeId());
					addOrgSpe.setSpeName(s.getSpeName());
					addOrgSpe.setSpeTypeId(s.getSpeTypeId());
					addOrgSpe.setSpeTypeName(s.getSpeTypeName());
					addOrgSpe.setOrgFlow(orgFlow);
					addOrgSpe.setOrgName(orgName);
					if(oldStatusNList != null && !oldStatusNList.isEmpty()){
						for(ResOrgSpe N :oldStatusNList){
							if(s.getSpeTypeId().equals(N.getSpeTypeId()) &&  s.getSpeId().equals(N.getSpeId()) && orgFlow.equals(N.getOrgFlow()) ){
								addFlag = false;
                                N.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
								resOrgSpeBiz.saveResOrgSpe(N);
								break;
							}
						}
						if(addFlag){//新增
							resOrgSpeBiz.saveResOrgSpe(addOrgSpe);
						}
					}else{//新增
						resOrgSpeBiz.saveResOrgSpe(addOrgSpe);
					}
				}
			}
		}
		//删除原先的
		if(deleteMap.size()>0){
			for(Entry<String, ResOrgSpe> entry : deleteMap.entrySet()){
				ResOrgSpe delOrgSpe = entry.getValue();
                delOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				resOrgSpeBiz.saveResOrgSpe(delOrgSpe);
			}
		}
		ResBase resBase=readBase(orgFlow);
		if (resBase!=null) {
            resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
            resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
			saveResBase(resBase);
		}
        return com.pinde.core.common.GlobalConstant.ONE_LINE;
	}
	@Override
	public List<ResBaseExt> searchResBaseExtList(Map<String, Object> paramMap) {
		return resBaseExtMapper.searchResBaseExtList(paramMap);
	}
	
	@Override
	public int saveResBase(ResBase resBase) {
		if(StringUtil.isNotBlank(resBase.getOrgFlow())){
			GeneralMethod.setRecordInfo(resBase, false);
			return resBaseMapper.updateByPrimaryKeySelective(resBase);
		}else{
			SysUser sysUser =GlobalContext.getCurrentUser();
			resBase.setOrgFlow(sysUser.getOrgFlow());
			GeneralMethod.setRecordInfo(resBase, true);
			return resBaseMapper.insert(resBase);
		}
	}
	@Override
	public int delScoreConf(String cfgYear){
		int count=0;
		if(StringUtil.isNotBlank(cfgYear)){
			count = resPassScoreCfgMapper.deleteByPrimaryKey(cfgYear);
		}
		return count;
	}

	@Override
	public int savePassScoreCfg(ResPassScoreCfg resPassScoreCfg){
		ResPassScoreCfg scoreCfg = readResPassScoreCfg(resPassScoreCfg);
		if(scoreCfg!=null){
			GeneralMethod.setRecordInfo(resPassScoreCfg, false);
			return resPassScoreCfgMapper.updateByPrimaryKeySelective(resPassScoreCfg);
		}else{
			GeneralMethod.setRecordInfo(resPassScoreCfg, true);
			return resPassScoreCfgMapper.insert(resPassScoreCfg);
		}
	}

	@Override
	public List<ResPassScoreCfg> readCfgs(ResPassScoreCfg resPassScoreCfg){
		ResPassScoreCfgExample example = new ResPassScoreCfgExample();
		if(StringUtil.isNotBlank(resPassScoreCfg.getCfgYear())){
			example.createCriteria().andCfgYearEqualTo(resPassScoreCfg.getCfgYear());
		}else{
			example.createCriteria().andCfgYearLike("%");
		}
		example.setOrderByClause("CFG_YEAR desc");
		return resPassScoreCfgMapper.selectByExample(example);
	}
	@Override
	public ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg){
		ResPassScoreCfg passScoreCfg=null;
		 if(StringUtil.isNotBlank(resPassScoreCfg.getCfgYear())){
			 passScoreCfg = resPassScoreCfgMapper.selectByPrimaryKey(resPassScoreCfg.getCfgYear());
		 }
		return passScoreCfg;
	}

//	@Override
//	public List<ResBase> searchResBaseList(ResBase resBase) {
//		ResBaseExample example=new ResBaseExample();
//		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
//		if (StringUtil.isNotBlank(resBase.getOrgFlow())) {
//			criteria.andOrgFlowEqualTo(resBase.getOrgFlow());
//		}
//		if (StringUtil.isNotBlank(resBase.getBaseStatusId())) {
//			criteria.andBaseStatusIdEqualTo(resBase.getBaseStatusId());
//		}
//		if (StringUtil.isNotBlank(resBase.getBaseStatusName())) {
//			criteria.andBaseStatusNameEqualTo(resBase.getBaseStatusName());
//		}
//		return resBaseMapper.selectByExample(example);
//	}


	@Override
	public void bindDoctorRole(String userFlow) {

			SysRoleExample example = new SysRoleExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andWsIdEqualTo("osca").andRoleNameEqualTo("学员");
			List<SysRole> roleList = sysRoleMapper.selectByExample(example);//查询临床学员角色
			if(null != roleList && roleList.size() > 0 && StringUtil.isNotBlank(roleList.get(0).getRoleFlow())){
				SysUserRoleExample userRoleExample = new SysUserRoleExample();
                userRoleExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andUserFlowEqualTo(userFlow).andWsIdEqualTo("osca").andRoleFlowEqualTo(roleList.get(0).getRoleFlow());
				int count = userRoleMapper.countByExample(userRoleExample);//查询此学员在临床是否是学员角色
				if(count > 0){
					SysUserRoleExample userRoleExampleRes = new SysUserRoleExample();
                    userRoleExampleRes.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
							.andUserFlowEqualTo(userFlow).andWsIdEqualTo("res");
					int countRes = userRoleMapper.countByExample(userRoleExampleRes);//查询此学员在住培是否已有角色
					if(countRes == 0) {
						if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_doctor_role_flow"))) {
							SysUserRole record = new SysUserRole();
							record.setRecordFlow(PkUtil.getUUID());
							record.setUserFlow(userFlow);
                            record.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
							record.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
							GeneralMethod.setRecordInfo(record, true);
							count = userRoleMapper.insertSelective(record);
						}
					}
				}
			}

	}

	@Override
	public List<ResBase> readBaseList(List<String> orgFlows) {
		if(orgFlows!=null&&!orgFlows.isEmpty())
		{
			ResBaseExample example=new ResBaseExample();
			ResBaseExample.Criteria criteria=example.createCriteria()
					.andOrgFlowIn(orgFlows);
			return resBaseMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public List<ResBase> readBaseListByYear(List<String> orgFlows, String sessionNumber) {
		if(orgFlows!=null&&!orgFlows.isEmpty())
		{
			ResBaseExample example=new ResBaseExample();
			ResBaseExample.Criteria criteria=example.createCriteria()
					.andOrgFlowIn(orgFlows).andSessionNumberEqualTo(sessionNumber);
			return resBaseMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public Map<String, ResBase> searchAll() {
		ResBaseExample example=new ResBaseExample();
		ResBaseExample.Criteria criteria=example.createCriteria()
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<ResBase> list = resBaseMapper.selectByExampleWithBLOBs(example);
		HashMap<String, ResBase> baseMap = new HashMap<>();
		for (ResBase resBase : list) {
			baseMap.put(resBase.getOrgFlow(),resBase);
		}
		return baseMap;
	}

	@Override
	public ResBase readBaseBySessionNumber(String orgFlow, String sessionNumber) {
		ResBase resBase = null;
		if(StringUtil.isNotBlank(orgFlow)){
			resBase = resBaseMapper.selectByPrimaryKeyAndSessionNumber(orgFlow, sessionNumber);
		}
		return  resBase ;
	}

    private static final Logger logger = LoggerFactory.getLogger(JsResBaseBizImpl.class);

}
