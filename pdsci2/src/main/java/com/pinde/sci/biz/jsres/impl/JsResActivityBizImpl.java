package com.pinde.sci.biz.jsres.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.ActivityTypeEnum;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.jsres.ISysSupervisioUserBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.ctrl.cfg.JsresPowerCfgController;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.core.model.SysOrgExt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResActivityBizImpl implements IJsResActivityBiz {

	@Autowired
	private TeachingActivityInfoExtMapper activityInfoExtMapper;
	@Autowired
	private TeachingActivityEvalMapper activityEvalMapper;
	@Autowired
	private TeachingActivityInfoMapper activityInfoMapper;
	@Autowired
	private TeachingActivityResultMapper resultMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private IJsResActivityTargetBiz targetBiz;
	@Autowired
	private TeachingActivityFormValueMapper activityFormValueMapper;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private TeachActivityCfgMapper activityCfgMapper;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private SysOrgExtMapper sysOrgExtMapper;
	@Autowired
	private ResHospSupervSubjectMapper hospSupervSubjectMapper;
	@Autowired
	private ISysSupervisioUserBiz supervisioUserBiz;
	@Autowired
	private TeachingActivitySpeakerMapper activitySpeakerMapper;
	@Autowired
	private IUserBiz userBiz;

    private static final Logger logger = LoggerFactory.getLogger(JsResActivityBizImpl.class);

	@Override
	public List<Map<String, Object>> findActivityList(Map<String, String> param) {
		List<Map<String, Object>> list=activityInfoExtMapper.findActivityList(param);
		return list;
	}

	@Override
	public List<Map<String, Object>> activityStatisticsList(Map<String, String> param) {
		return activityInfoExtMapper.activityStatisticsList(param);
	}

	@Override
	public List<Map<String, Object>> activityStatisticsExportList(Map<String, String> param) {
		return activityInfoExtMapper.activityStatisticsExportList(param);
	}

	@Override
	public List<Map<String, Object>> queryJoin(String activityFlow,String orgName) {
		return activityInfoExtMapper.queryJoin(activityFlow,orgName);
	}

	@Override
	public List<Map<String, Object>> supervisioFindActivityList(Map<String, Object> param) {
		List<Map<String, Object>> list=activityInfoExtMapper.supervisioFindActivityList(param);
		return list;
	}

	@Override
	public Map<String, Object> readActivity(String activityFlow) {
		return activityInfoExtMapper.readActivityForWeb(activityFlow);
	}
	@Override
	public Map<String, Object> getDeptActivityStatisticsMap(String deptFlow,String startTime,String endTime) {

		Map<String, Object> map=new HashMap<>();
		List<Map<String,String>> datas=activityInfoExtMapper.getDeptActivityStatisticsMap(deptFlow,startTime,endTime);
		if(datas!=null)
		{
			for(Map<String,String> d:datas)
			{
				map.put(d.get("typeId"),d.get("qty"));
			}
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getDeptCountActivityStatisticsList(String orgFlow, String deptFlow, String startTime, String endTime,String notNull) {
		return activityInfoExtMapper.getDeptCountActivityStatisticsList(orgFlow,deptFlow,startTime,endTime,notNull);
	}

	@Override
	public Map<String, Object> getTeacherActivityStatisticsMap(String deptFlow,String teacherFlow,String startTime,String endTime) {

		Map<String, Object> map=new HashMap<>();
		List<Map<String,String>> datas=activityInfoExtMapper.getTeacherActivityStatisticsMap(deptFlow,teacherFlow,startTime,endTime);
		if(datas!=null)
		{
			for(Map<String,String> d:datas)
			{
				map.put(d.get("typeId"),d.get("qty"));
			}
		}
		return map;
	}

	@Override
	public List<Map<String, String>> getTeacherActivityStatisticsReport(Map<String, Object> param) {
		return activityInfoExtMapper.getTeacherActivityStatisticsReport(param);
	}


	@Override
	public String getRealitSpeaker2(String userFlow, String deptFlow, String orgFlow, String startTime, String endTime) {
		String realitSpeaker = activityInfoExtMapper.getRealitSpeaker2(userFlow, deptFlow, orgFlow,startTime,endTime);
		if (StringUtil.isEmpty(realitSpeaker)){
			return "";
		}
		return realitSpeaker;
	}

	@Override
	public Map<String, Object> getDoctorActivityStatisticsMap(String doctorFlow, String startTime, String endTime, String isDept, String userFlow) {

		Map<String, Object> map=new HashMap<>();
		List<Map<String,String>> datas=activityInfoExtMapper.getDoctorActivityStatisticsMap(doctorFlow,startTime,endTime, isDept, userFlow);
		if(datas!=null)
		{
			for(Map<String,String> d:datas)
			{
				map.put(d.get("typeId"),d.get("qty"));
			}
		}
		return map;
	}

	@Override
	public TeachingActivityResult readRegistInfo(String activityFlow, String userFlow) {
		TeachingActivityResultExample example=new TeachingActivityResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andActivityFlowEqualTo(activityFlow)
				.andUserFlowEqualTo(userFlow);
		List<TeachingActivityResult> list=resultMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getTeacherActivityStatistics(Map<String,Object> param) {
		return activityInfoExtMapper.getTeacherActivityStatistics(param);

	}

	@Override
	public List<Map<String, Object>> getTeacherActivityStatistics2(Map<String, Object> param) {
		return activityInfoExtMapper.getTeacherActivityStatistics2(param);
	}

	@Override
	public List<Map<String, Object>> readActivityResults(String activityFlow) {
		return activityInfoExtMapper.readActivityResults(activityFlow);
	}
	@Override
	public List<Map<String, Object>> readActivityRegists(String activityFlow) {
		return activityInfoExtMapper.readActivityRegists(activityFlow);
	}

	@Override
	public List<TeachingActivityEval> readActivityResultEvals(String resultFlow) {
		TeachingActivityEvalExample example=new TeachingActivityEvalExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andResultFlowEqualTo(resultFlow);
		example.setOrderByClause("ORDINAL");
		return activityEvalMapper.selectByExample(example);
	}

	@Override
	public TeachingActivityInfo readActivityInfo(String activityFlow) {
		return activityInfoMapper.selectByPrimaryKey(activityFlow);
	}

	@Override
	public int saveActivity(TeachingActivityInfo info) {
		if (StringUtil.isBlank(info.getActivityFlow())) {
			info.setActivityFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(info, true);
			return activityInfoMapper.insertSelective(info);
		} else {
			ResHospSupervSubject subject = supervisioUserBiz.selectHospByActivityFlow(info.getActivityFlow());
			if (null != subject){
				if (!info.getRecordStatus().equals(subject.getRecordStatus())){
					subject.setRecordStatus(info.getRecordStatus());
					supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject);
				}
			}
			GeneralMethod.setRecordInfo(info, false);
			return activityInfoMapper.updateByPrimaryKeySelective(info);
		}
	}
	@Override
	public int saveRegist(TeachingActivityResult info) {
		if (StringUtil.isBlank(info.getResultFlow())) {
			info.setResultFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(info, true);
			return resultMapper.insertSelective(info);
		} else {
			GeneralMethod.setRecordInfo(info, false);
			return resultMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public TeachingActivityResult readResult(String resultFlow) {
		return resultMapper.selectByPrimaryKey(resultFlow);
	}

	@Override
	public int countByActivity(String activityFlow) {
		TeachingActivityResultExample example = new TeachingActivityResultExample();
		TeachingActivityResultExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(activityFlow)){
			criteria.andActivityFlowEqualTo(activityFlow);
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return resultMapper.countByExample(example);
	}

	@Override
	public int checkJoin(String activityFlow, String userFlow) {
		return activityInfoExtMapper.checkJoin(activityFlow,userFlow);
	}

	@Override
	public List<TeachingActivityInfo> checkJoinList(String activityFlow, String userFlow) {
		return activityInfoExtMapper.checkJoinList(activityFlow,userFlow);
	}

	@Override
	public int saveEvalInfo(List<TeachingActivityEval> evals, String resultFlow) {
		if(evals!=null&&evals.size()>0)
		{
			TeachingActivityResult result=readResult(resultFlow);
			if(result==null)
				return 0;
			double sum=0;
			int remarks=0;
			for(TeachingActivityEval eval:evals)
			{
				eval.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				eval.setResultFlow(resultFlow);
                if (eval.getIsText().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
					sum+=eval.getEvalScore();
				}else {
					remarks=remarks+1;
				}
				saveResultEval(eval);
			}
			int count= evals.size()- remarks;
            BigDecimal evalScore = new BigDecimal(sum / count).setScale(0, RoundingMode.HALF_UP);
			result.setEvalScore(evalScore);
			return saveResult(result);

		}
		return 0;
	}

	@Override
	public  int saveResult(TeachingActivityResult info) {
		if (StringUtil.isBlank(info.getResultFlow())) {
			info.setResultFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(info, true);
			return resultMapper.insertSelective(info);
		} else {
			GeneralMethod.setRecordInfo(info, false);
			return resultMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public  int saveResultEval(TeachingActivityEval eval) {
		if (StringUtil.isBlank(eval.getEvalFlow())) {
			eval.setEvalFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eval, true);
			return activityEvalMapper.insertSelective(eval);
		} else {
			GeneralMethod.setRecordInfo(eval, false);
			return activityEvalMapper.updateByPrimaryKeySelective(eval);
		}
	}

	@Override
	public String editActivity(TeachingActivityInfo activity, MultipartFile file, String isRe, String isRes,String data,String role) {
//		List<TeachingActivityTarget> targets=targetBiz.readByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		List<TeachingActivityTarget> targets=targetBiz.readByOrgNew(activity.getActivityTypeId(),GlobalContext.getCurrentUser().getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			return "暂未配置活动指标，请联系基地管理员！";
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0)
		{
			return "该主讲人在时间段内已开展其他活动！";
		}
		if(file!=null&&!file.isEmpty()&&file.getSize()>0)
		{
			String fileFlow=saveActivityFile(activity.getFileFlow(),file);
			activity.setFileFlow(fileFlow);
        } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRe)) {
			activity.setFileFlow("");
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRes)) {
                activity.setActivityTypeName(com.pinde.core.common.enums.DictTypeEnum.ActivityType.getDictNameById(activity.getActivityTypeId()));
			}else{
				activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
			}
		}
		activity.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		Map<String,String> param = new HashMap<>();
		param.put("orgFlow",activity.getOrgFlow());
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> userRoleList = new ArrayList();
		// 由于科主任和教秘 前台传入role都是head 无法区分 改为获取当前登录角色
		String roleFlow = "";
        String currentRole = (String) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(currentRole)) {
			userRoleList.add("res_head_role_flow");
			roleFlow = InitConfig.getSysCfg("res_head_role_flow");
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(currentRole)) {
			userRoleList.add("res_teacher_role_flow");
			roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(currentRole)) {
			userRoleList.add("res_secretary_role_flow");
			roleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		}
		criteria.andCfgCodeIn(userRoleList);
		SysCfg sysCfg= sysCfgMapper.selectByExample(example).get(0);     //获取该用户当前角色信息
		List<SysUserRole> subRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//		List<ActivityCfgExt> list = roleBiz.searchActvity(param);         //获取该基地下的所有教学活动配置
//		String subName="";
		List<TeachActivityCfg> cfgList = this.searchActivityCfgs(roleFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if(null != cfgList && cfgList.size()>0){
			TeachActivityCfg cfg = cfgList.get(0);
			if(cfg.getSubmitRole().equals(roleFlow)){
				activity.setSubmitRole(cfg.getSubmitRole());
				activity.setAuditRole(cfg.getAuditRole());
				activity.setActivityStatus("audit");
				//判断由谁审核
				if(cfg.getAuditRole().equals(roleFlow)){
					//当前角色审核
					activity.setActivityStatus("pass");
				}else{
					//当前登录人拥有多个角色
					for(SysUserRole sur:subRoleList){
						if(sur.getRoleFlow().equals(cfg.getAuditRole())){
							activity.setActivityStatus("pass");
							break;
						}
					}
				}
			}else {
				return "该角色无法发起教学活动，请联系基地管理员！";
			}

		}else{
			return "该角色无法发起教学活动，请联系基地管理员！";
		}
//		for (ActivityCfgExt actCfg: list) {                                //嵌套循环基地下的教学活动和用户角色
//			if(actCfg.getSubmitRole().equals(sysCfg.getCfgValue())){   //若教学活动发起人角色和当前用户角色相同时将当前基地下的提交人和审核人角色信息存在该教学活动下
//				activity.setSubmitRole(actCfg.getSubmitRole());
//				activity.setAuditRole(actCfg.getAuditRole());
//				for (SysUserRole userRole:subRoleList) {
//					if("audit".equals(activity.getActivityStatus()) || StringUtil.isBlank(activity.getActivityStatus())) {
//						if (actCfg.getAuditRole().contains(userRole.getRoleFlow())) {  //判断该角色发起的活动审批角色是否包含该用户所拥有的角色，有的话就直接审核通过
//							activity.setActivityStatus("pass");
//						}else{
//							activity.setActivityStatus("audit");
//						}
//					}
//				}
//			}else if(StringUtil.isBlank(activity.getActivityStatus())){
//				activity.setActivityStatus("audit");
//			}
//			if("带教老师角色".equals(actCfg.getSubRoleName())){
//				subName=subName+"teach";
//			}else if("科主任角色".equals(actCfg.getSubRoleName())){
//				subName=subName+"head";
//			}else if("科秘角色".equals(actCfg.getSubRoleName())){
//				subName=subName+"secretary";
//			}
//		}
//		if(("undefined".equals(activity.getSubmitRole())||StringUtil.isBlank(activity.getSubmitRole()))&&(!subName.contains(role))){
//			return "该角色无法发起教学活动，请联系基地管理员！";
//		}
		int c ;
		String newFlow = PkUtil.getUUID();
		if (StringUtil.isBlank(activity.getActivityFlow())) {
			activity.setActivityFlow(newFlow);
			GeneralMethod.setRecordInfo(activity, true);
			c =  activityInfoMapper.insertSelective(activity);

		} else {
			GeneralMethod.setRecordInfo(activity, false);
			c =  activityInfoMapper.updateByPrimaryKeySelective(activity);
		}

		if(StringUtil.isNotBlank(data)){
			List<Map<String,Object>> mapList = JSON.parseObject(data,ArrayList.class);
			if(mapList!=null&&mapList.size()>0){
				for(Map<String,Object> map:mapList){
					String recordFlow = (String)map.get("recordFlow");
					String formFlow = (String)map.get("formFlow");
					String detailValue = (String)map.get("detailValue");
					String activityFlow = (String)map.get("activityFlow");
					if(StringUtil.isBlank(activityFlow)){
						activityFlow = newFlow;
					}

					TeachingActivityFormValue save = new TeachingActivityFormValue();
					save.setRecordFlow(recordFlow);
					save.setFormFlow(formFlow);
					save.setDetailValue(detailValue);
					save.setActivityFlow(activityFlow);
					editActivityFormValue(save);
				}
			}
		}
		if(c==0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		else {
			saveActivityTarget(activity,targets);
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
	}
	private void saveActivityTarget(TeachingActivityInfo activity, List<TeachingActivityTarget> targets) {
		int i=1;
		for(TeachingActivityTarget target:targets)
		{
			TeachingActivityInfoTarget infoTarget=targetBiz.readActivityTarget(activity.getActivityFlow(),target.getTargetFlow());
			if(infoTarget==null)
				infoTarget=new TeachingActivityInfoTarget();
			infoTarget.setActivityFlow(activity.getActivityFlow());
			infoTarget.setTargetFlow(target.getTargetFlow());
			infoTarget.setTargetName(target.getTargetName());
			infoTarget.setOrdinal(i++);
            infoTarget.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			infoTarget.setIsText(target.getIsText());
			targetBiz.saveInfoTarget(infoTarget);
		}
	}

	private int checkTime(TeachingActivityInfo activity) {
		return activityInfoExtMapper.checkTime(activity);
	}
	@Override
	public String saveActivityFile(String fileFlow, MultipartFile file) {
		if(!(file!=null&&!file.isEmpty()&&file.getSize()>0)){
			return "";
		}
		PubFile pubFile=null;
		if(StringUtil.isNotBlank(fileFlow))
		{
			pubFile=pubFileMapper.selectByPrimaryKey(fileFlow);
		}
		if(pubFile==null){
			 pubFile = new PubFile();
		}
		String originalFileName = file.getOriginalFilename();
		String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		//默认的文件名
		pubFile.setFileName(originalFileName);
		//文件后缀名
		pubFile.setFileSuffix(suffix);
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "activityFile" + File.separator + dateString;
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		//重命名上传后的文件名
		originalFileName = PkUtil.getUUID() +"."+ suffix;
		File newFile = new File(fileDir, originalFileName);
		try {
			file.transferTo(newFile);
		} catch (Exception e) {
			throw new RuntimeException("文件上传异常");
		}
		String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + originalFileName;
		pubFile.setFilePath(filePath);
		if(StringUtil.isBlank(pubFile.getFileFlow())) {
			pubFile.setFileFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(pubFile, true);
			pubFileMapper.insertSelective(pubFile);
		}else{
			GeneralMethod.setRecordInfo(pubFile, false);
			pubFileMapper.updateByPrimaryKeySelective(pubFile);
		}
		return pubFile.getFileFlow();
	}

	@Override
	public List<Map<String, Object>> getDoctorActivityStatistics(Map<String, Object> parMp) {
		return activityInfoExtMapper.getDoctorActivityStatistics(parMp);
	}
	@Override
	public List<Map<String, Object>> getResDoctorActivityStatistics(Map<String, Object> parMp) {
		return activityInfoExtMapper.getResDoctorActivityStatistics(parMp);
	}

	@Override
	public List<Map<String, String>> parseImageXml(String content) throws DocumentException {
		Document document= DocumentHelper.parseText(content);
		Element elem=document.getRootElement();
		List<Element> ec = elem.elements();
		List<Map<String, String>> imageList=new ArrayList<Map<String,String>>();
		for (Element element : ec) {
			Map<String,String> imageMap=new HashMap<String, String>();
			String imageFlow=element.attributeValue("imageFlow");
			imageMap.put("imageFlow", imageFlow);
			List<Element> items=element.elements();
			for (Element item : items) {
				String itemName=item.getName();
				String itemText=item.getTextTrim();
				imageMap.put(itemName, itemText);
			}
			imageList.add(imageMap);
		}
		return imageList;
	}
	@Override
	public Map<String, String> activityImg(String activityFlow,MultipartFile file,String fileAddress) {
		Map<String, String> map=new HashMap<String, String>();
        map.put("status", com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG);
		if(file!=null){
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}

			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
				return  map;

			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
                map.put("error", com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M");
				return  map;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = InitConfig.getSysCfg("upload_base_url")+"/"+fileAddress+"/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(activityFlow)){
					TeachingActivityInfo resRec = readActivityInfo(activityFlow);
					String content =resRec.getImageUrl();
					Document document=DocumentHelper.parseText(content);
					if (document!=null) {
						Element element=document.getRootElement();
						Element elem=element.addElement("image");
						String imageFlow=PkUtil.getUUID();
						elem.addAttribute("imageFlow",imageFlow);
						Element imageUrl=elem.addElement("imageUrl");
						Element thumbUrl=elem.addElement("thumbUrl");
						imageUrl.setText(url);
						thumbUrl.setText(url);
						resRec.setImageUrl(document.asXML());
						saveActivity(resRec);
						map.put("url",url);
						map.put("flow",imageFlow);
					}
				}
                map.put("status", com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG);
			} catch (Exception e) {
                logger.error("", e);
			}
		}
		return map;
	}

	@Override
	public int editActivityFormValue(TeachingActivityFormValue formValue) {
		String recordFlow = formValue.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			GeneralMethod.setRecordInfo(formValue,false);
			return activityFormValueMapper.updateByPrimaryKeySelective(formValue);
		}else {
			formValue.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(formValue,true);
			return activityFormValueMapper.insertSelective(formValue);
		}
	}

	@Override
	public List<Map<String, Object>> findActivityList2(Map<String, String> param) {
		List<Map<String, Object>> list=activityInfoExtMapper.findActivityList2(param);
		return list;
	}
	@Override
	public List<Map<String, Object>> findActivityList3(Map<String, String> param) {
		List<Map<String, Object>> list=activityInfoExtMapper.findActivityList3(param);
		return list;
	}

	@Override
	public List<Map<String, Object>> getActivitys(Map<String, Object> param) {
		return activityInfoExtMapper.getActivitys(param);
	}

	@Override
	public List<Map<String,Object>> getDeptActivityCountMap(Map<String,Object> param) {
		return activityInfoExtMapper.getDeptActivityCountMap(param);
	}

	@Override
	public List<Map<String, Object>> readActivityResults2(Map<String, Object> param2) {
		return activityInfoExtMapper.readActivityResults2(param2);
	}

	@Override
	public Map<String,Object> getDeptActivityMap(String trainingSpeId,String startTime,String endTime) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String,String>> datas = activityInfoExtMapper.getDeptActivityMap(trainingSpeId,startTime,endTime);
		if(datas!=null)
		{
			for(Map<String,String> d:datas)
			{
				map.put(d.get("typeId"),d.get("qty"));
			}
		}
		return map;
	}

	@Override
	public List<Map<String,String>> searchSpeDept(String orgFlow,String trainingSpeId) {
		return activityInfoExtMapper.searchSpeDept(orgFlow,trainingSpeId);
	}

	@Override
	public List<Map<String, Object>> getActivityCountMap(Map<String, Object> param) {
		return activityInfoExtMapper.getActivityCountMap(param);
	}

	@Override
	public List<Map<String, Object>> readResults(Map<String, Object> param) {
		return activityInfoExtMapper.readResults(param);
	}

	@Override
	public List<TeachActivityCfg> searchActivityCfgs(String roleFlow, String orgFlow) {
		TeachActivityCfgExample example = new TeachActivityCfgExample();
        TeachActivityCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(roleFlow)){
			criteria.andSubmitRoleEqualTo(roleFlow);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		return activityCfgMapper.selectByExample(example);
	}
	@Override
	public String editActivityNew2(TeachingActivityInfo activity, String isRes, Map<String, List<MultipartFile>> fileMap, String data, String[] fileFlow) throws ParseException {
		List<TeachingActivityTarget> targets=targetBiz.readByOrgNew(activity.getActivityTypeId(),GlobalContext.getCurrentUser().getOrgFlow());
		if(targets==null||targets.size()<=0) {
			return "暂未配置活动指标，请联系基地管理员！";
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0) {
			return "该主讲人在时间段内已开展其他活动！";
		}

		//校验当前时间与活动开始时间是否符合配置要求
		String startDateTime = activity.getStartTime();
		String currDate = DateUtil.getCurrDateTime("yyyy-MM-dd");
		String currTime = DateUtil.getCurrDateTime("HH:mm:ss");
		String keyOfDay = "jsres_" + GlobalContext.getCurrentUser().getOrgFlow() + "_org_activity_add_day";
		String keyOfTime = "jsres_" + GlobalContext.getCurrentUser().getOrgFlow() + "_org_activity_add_time";
		String day = JsresPowerCfgController.jsresPowerCfgMap(keyOfDay);
		String time = JsresPowerCfgController.jsresPowerCfgMap(keyOfTime);
		if (StringUtil.isNotBlank(day) && StringUtil.isNotBlank(time)) {
			String startDate = startDateTime.substring(0, 10).trim();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date startDate1 = sdf.parse(currDate);
			Date endDate = sdf.parse(startDate);
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(startDate1);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			long days = 0;
			if (startCal.before(endCal)) {
				while (startCal.before(endCal)) {
					startCal.add(Calendar.DAY_OF_MONTH, 1);
					days++;
				}
			}
			if (endCal.before(startCal)) {
				while (endCal.before(startCal)) {
					endCal.add(Calendar.DAY_OF_MONTH, 1);
					days--;
				}
			}
			if ((days == new Long(day) && currTime.compareTo(time) > 0) || days < new Long(day)) {
				return "请在活动开始前" + day + "天" + time + "点前设置活动!";
			}
		}

//		if(file!=null&&!file.isEmpty()&&file.getSize()>0) {
//			String fileFlow=saveActivityFile(activity.getFileFlow(),file);
//			activity.setFileFlow(fileFlow);
//		}else if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRe)){
//			activity.setFileFlow("");
//		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId())) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRes)) {
                activity.setActivityTypeName(com.pinde.core.common.enums.DictTypeEnum.ActivityType.getDictNameById(activity.getActivityTypeId()));
			}else{
				activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
			}
		}
		activity.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		Map<String,String> param = new HashMap<>();
		param.put("orgFlow",activity.getOrgFlow());
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> userRoleList = new ArrayList();
		// 由于科主任和教秘 前台传入role都是head 无法区分 改为获取当前登录角色
		String roleFlow = "";
        String currentRole = (String) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(currentRole)) {
			userRoleList.add("res_head_role_flow");
			roleFlow = InitConfig.getSysCfg("res_head_role_flow");
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(currentRole)) {
			userRoleList.add("res_teacher_role_flow");
			roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(currentRole)) {
			userRoleList.add("res_secretary_role_flow");
			roleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		}
		criteria.andCfgCodeIn(userRoleList);
		SysCfg sysCfg= sysCfgMapper.selectByExample(example).get(0);     //获取该用户当前角色信息
		if (StringUtil.isBlank(activity.getActivityStatus())) {
			List<SysUserRole> subRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			List<TeachActivityCfg> cfgList = this.searchActivityCfgs(roleFlow,GlobalContext.getCurrentUser().getOrgFlow());
			if(null != cfgList && cfgList.size()>0){
				TeachActivityCfg cfg = cfgList.get(0);
				if(cfg.getSubmitRole().equals(roleFlow)){
					activity.setSubmitRole(cfg.getSubmitRole());
					activity.setAuditRole(cfg.getAuditRole());
					activity.setActivityStatus("audit");
					//判断由谁审核
					if(cfg.getAuditRole().contains(roleFlow)){
						//当前角色审核
						activity.setActivityStatus("pass");
					}else{
						//当前登录人拥有多个角色
						for(SysUserRole sur:subRoleList){
							if(sur.getRoleFlow().equals(cfg.getAuditRole())){
								activity.setActivityStatus("pass");
								break;
							}
						}
					}
				}else {
					return "该角色无法发起教学活动，请联系基地管理员！";
				}
			}else{
				return "该角色无法发起教学活动，请联系基地管理员！";
			}
		}

		int c ;
		String newFlow ;
		if (StringUtil.isBlank(activity.getActivityFlow())) {
			newFlow = PkUtil.getUUID();
            activity.setFileFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
			activity.setActivityFlow(newFlow);
			GeneralMethod.setRecordInfo(activity, true);
			c =  activityInfoMapper.insertSelective(activity);
			logger.info("准备创建项目");
			//创建院级督导的项目
			ResHospSupervSubject subject = new ResHospSupervSubject();
			subject.setSubjectFlow(PkUtil.getUUID());
			subject.setSubjectName(activity.getActivityName());
			subject.setActivityFlow(newFlow);
			subject.setActivityName(activity.getActivityName());
			SysOrgExt orgExt = sysOrgExtMapper.searchOrgFlow(activity.getOrgFlow());
			subject.setOrgFlow(orgExt.getOrgFlow());
			subject.setOrgName(orgExt.getOrgName());
			subject.setBaseCode(orgExt.getBaseCode());
			subject.setOrderAction("free");
			subject.setCreateTime(DateUtil.getCurrDateTime2());
			subject.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			subject.setSubjectYear(DateUtil.getYear());
			SysDept dept = deptBiz.readSysDept(activity.getDeptFlow());
			subject.setDeptFlow(activity.getDeptFlow());
			subject.setDeptName(dept.getDeptName());
			subject.setInspectionType(activity.getActivityTypeId());
			subject.setTeachFlow(GlobalContext.getCurrentUser().getUserFlow());
			subject.setTeachName(GlobalContext.getCurrentUser().getUserName());
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			subject.setActualSpeaker(activity.getRealitySpeaker());
			subject.setActivityStartTime(activity.getStartTime());
			subject.setActivityEndTime(activity.getEndTime());
			int insert = hospSupervSubjectMapper.insert(subject);
			if (insert>0){
				logger.info("院级督导项目创建完成");
			}else {
				logger.info("院级督导项目创建失败");
			}
		} else {
			newFlow = activity.getActivityFlow();
			TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(newFlow);
			if (!activity.getSpeakerFlow().equals(activityInfo.getSpeakerFlow())){
				TeachingActivitySpeaker activitySpeaker = new TeachingActivitySpeaker();
				activitySpeaker.setRecordFlow(PkUtil.getUUID());
				activitySpeaker.setActivityFlow(newFlow);
				activitySpeaker.setSpeakerFlow(activityInfo.getSpeakerFlow());
				activitySpeaker.setSpeakerName(userBiz.readSysUser(activityInfo.getSpeakerFlow()).getUserName());
				GeneralMethod.setRecordInfo(activitySpeaker, true);
				activitySpeakerMapper.insert(activitySpeaker);
			}
            activity.setFileFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
			GeneralMethod.setRecordInfo(activity, false);
			c =  activityInfoMapper.updateByPrimaryKeySelective(activity);

			//修改院级督导项目
			ResHospSupervSubject subject = supervisioUserBiz.selectHospByActivityFlow(activity.getActivityFlow());
			if (null != subject){
				subject.setActualSpeaker(activity.getRealitySpeaker());
				supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject);
			}
		}

		String[] fileType=new String[]{"files","kj","zp"};
		for (String s : fileType) {
			List<MultipartFile> fileList = fileMap.get(s);
			if (s.equals("files")){
				saveActivityFileNew(newFlow,fileList,fileFlow);
			}else{
				saveActivityFileNew2(newFlow,fileList,fileFlow,s);
			}
		}


		if(StringUtil.isNotBlank(data)){
			List<Map<String,Object>> mapList = JSON.parseObject(data,ArrayList.class);
			if(mapList!=null&&mapList.size()>0){
				for(Map<String,Object> map:mapList){
					String recordFlow = (String)map.get("recordFlow");
					String formFlow = (String)map.get("formFlow");
					String detailValue = (String)map.get("detailValue");
					String activityFlow = (String)map.get("activityFlow");
					if(StringUtil.isBlank(activityFlow)){
						activityFlow = newFlow;
					}

					TeachingActivityFormValue save = new TeachingActivityFormValue();
					save.setRecordFlow(recordFlow);
					save.setFormFlow(formFlow);
					save.setDetailValue(detailValue);
					save.setActivityFlow(activityFlow);
					editActivityFormValue(save);
				}
			}
		}
		if(c==0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		else {
			saveActivityTarget(activity,targets);
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
	}

	@Override
	public String editActivityFiles(String activityFlow, Map<String, List<MultipartFile>> fileMap, String[] fileFlow) {
		String[] fileType=new String[]{"files","kj","zp"};
		for (String s : fileType) {
			List<MultipartFile> fileList = fileMap.get(s);
			if (s.equals("files")){
				saveActivityFileNew(activityFlow,fileList,fileFlow);
			}else{
				saveActivityFileNew2(activityFlow,fileList,fileFlow,s);
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	@Override
	public String editActivityForAdmin(TeachingActivityInfo activityInfo) {
		GeneralMethod.setRecordInfo(activityInfo,false);
		int i = activityInfoMapper.updateByPrimaryKey(activityInfo);
		if (i < 1) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	private void saveActivityFileNew(String productFlow, List<MultipartFile> fileList, String[] fileFlow) {
	/*	//删除旧记录
		List<String> fileFlows = null;
		if(null != fileFlow && fileFlow.length > 0){
			fileFlows = Arrays.asList(fileFlow);
		}
		activityInfoExtMapper.delFileByProductFlow2("activity",productFlow,fileFlows,"");*/
		if(null != fileList && fileList.size()>0) {
			for (int i = 0; i < fileList.size(); i++) {
				PubFile pubFile = new PubFile();
				String originalFileName = fileList.get(i).getOriginalFilename();
				String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				//默认的文件名
				pubFile.setFileName(originalFileName);
				//文件后缀名
				pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				pubFile.setProductFlow(productFlow);
				pubFile.setProductType("activity");
				//定义上传路径
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "activityFile" + File.separator + dateString;
				File fileDir = new File(newDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				//重命名上传后的文件名
				originalFileName = PkUtil.getUUID() + "." + suffix;
				File newFile = new File(fileDir, originalFileName);
				try {
					fileList.get(i).transferTo(newFile);
				} catch (Exception e) {
					throw new RuntimeException("文件上传异常", e);
				}
				String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + originalFileName;
				pubFile.setFilePath(filePath);
				if (StringUtil.isBlank(pubFile.getFileFlow())) {
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile, true);
					pubFileMapper.insertSelective(pubFile);
				} else {
					GeneralMethod.setRecordInfo(pubFile, false);
					pubFileMapper.updateByPrimaryKeySelective(pubFile);
				}
			}
		}
	}

	private void saveActivityFileNew2(String productFlow, List<MultipartFile> fileList,String[] fileFlow,String fileUpType) {
	/*	//删除旧记录
		List<String> fileFlows = null;
		if(null != fileFlow && fileFlow.length > 0){
			fileFlows = Arrays.asList(fileFlow);
		}
		activityInfoExtMapper.delFileByProductFlow2("activity",productFlow,fileFlows,fileUpType);*/
		if(null != fileList && fileList.size()>0) {
			for (int i = 0; i < fileList.size(); i++) {
				PubFile pubFile = new PubFile();
				String originalFileName = fileList.get(i).getOriginalFilename();
				String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				//默认的文件名
				pubFile.setFileName(originalFileName);
				//文件后缀名
				pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				pubFile.setProductFlow(productFlow);
				pubFile.setProductType("activity");
				pubFile.setFileUpType(fileUpType);
				//定义上传路径
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "activityFile" + File.separator + dateString;
				File fileDir = new File(newDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				//重命名上传后的文件名
				originalFileName = PkUtil.getUUID() + "." + suffix;
				File newFile = new File(fileDir, originalFileName);
				try {
					fileList.get(i).transferTo(newFile);
				} catch (Exception e) {
					throw new RuntimeException("文件上传异常");
				}
				String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + originalFileName;
				pubFile.setFilePath(filePath);
				if (StringUtil.isBlank(pubFile.getFileFlow())) {
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile, true);
					pubFileMapper.insertSelective(pubFile);
				} else {
					GeneralMethod.setRecordInfo(pubFile, false);
					pubFileMapper.updateByPrimaryKeySelective(pubFile);
				}
			}
		}
	}

	/**
	 * 定时任务，每分钟执行一次将过期未审核的教学活动状态置为“已过期”
	 */
//	@Scheduled(cron = "0 */1 * * * ?") // todo moved
	public void activityBat(){
		activityInfoExtMapper.activityBat();
	}
}
