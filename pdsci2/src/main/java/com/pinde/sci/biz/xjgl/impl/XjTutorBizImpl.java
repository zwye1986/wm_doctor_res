package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.xjgl.IXjTutorBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjTutorBizImpl implements IXjTutorBiz {
	@Autowired
	private NydsOfficialDoctorMapper doctorMapper;
	@Autowired
	private NydsDoctorPaperMapper paperMapper;
	@Autowired
	private NydsDoctorTopicMapper topicMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private IOrgBiz orgBiz;

	@Override
	public NydsOfficialDoctor queryTutorByFlow(String tutorFlow) {
		return doctorMapper.selectByPrimaryKey(tutorFlow);
	}

	@Override
	public String uploadImg(String tutorFlow, String identifyFlag, MultipartFile file) {
		if(null!=file){
			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			long limitSize = 1;//导师照片限制1M
			List<String> mimeList = new ArrayList<String>();
			List<String> suffixList = new ArrayList<String>();
			if("headUrl".equals(identifyFlag)){//自定义上传图片限制
				//导师照片只允许jpg格式
				mimeList.add("image/jpg");
				mimeList.add("image/jpeg");
				suffixList.add(".jpg");
				suffixList.add(".jpeg");
			}else{//后台上传图片要求限制
				limitSize = Long.parseLong(InitConfig.getSysCfg("inx_image_limit_size"));//图片大小限制
				if(StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_mime"))){
					mimeList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_mime").split(","));
				}
				if(StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_suffix"))){
					suffixList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_suffix").split(","));
				}
			}
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
				String mimeStr = "";
				for(String mime : mimeList){
					mimeStr=mimeStr+mime;
				}
				return "文件格式只支持"+mimeStr;
			}
			if(file.getSize()>limitSize*1024*1024){
				return "文件大小不能超过"+limitSize +"M" ;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = InitConfig.getSysCfg("upload_base_dir")+ File.separator+"tutorImages"+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = "/tutorImages/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(tutorFlow) && "headUrl".equals(identifyFlag)){
					NydsOfficialDoctor tutor = new NydsOfficialDoctor();
					tutor.setDoctorFlow(tutorFlow);
					tutor.setHeadUrl(url);
					GeneralMethod.setRecordInfo(tutor,false);
					doctorMapper.updateByPrimaryKeySelective(tutor);
				}
				return "success:"+url;
			} catch (Exception e) {
				e.printStackTrace();
				return GlobalConstant.UPLOAD_FAIL;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	@Override
	public String uploadFile(String tutorFlow, MultipartFile file) {
		if(null!=file){
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			List<String> suffixList = new ArrayList<String>();
			suffixList.add(".pdf");
			if(!suffixList.contains(suffix)){
				return "文件格式只支持pdf";
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = InitConfig.getSysCfg("upload_base_dir")+ File.separator+"tutorFile"+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = "/tutorFile/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(tutorFlow)){
					NydsOfficialDoctor tutor = new NydsOfficialDoctor();
					tutor.setDoctorFlow(tutorFlow);
					tutor.setCertificateUrl(url);
					GeneralMethod.setRecordInfo(tutor,false);
					doctorMapper.updateByPrimaryKeySelective(tutor);
				}
				return "success:"+url;
			} catch (Exception e) {
				e.printStackTrace();
				return GlobalConstant.UPLOAD_FAIL;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	@Override
	public int saveTutor(String tabFlag, NydsOfficialDoctor tutor, NydsDoctorPaper paper, NydsDoctorTopic topic) {
		if("introduction".equals(tabFlag) || "recruit".equals(tabFlag)){
			GeneralMethod.setRecordInfo(tutor,false);
			return doctorMapper.updateByPrimaryKeySelective(tutor);
		}else if("paper".equals(tabFlag)){
			if(StringUtil.isNotBlank(paper.getRecordFlow())){//编辑
				GeneralMethod.setRecordInfo(paper,false);
				return paperMapper.updateByPrimaryKeySelective(paper);
			}else{
				NydsDoctorPaperExample example = new NydsDoctorPaperExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andDoctorFlowEqualTo(paper.getDoctorFlow()).andPaperTitleEqualTo(paper.getPaperTitle());
				if(paperMapper.countByExample(example)>0){
					return -1;//该论文已维护
				}
				paper.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(paper,true);
				return paperMapper.insertSelective(paper);
			}
		}else if("topic".equals(tabFlag)){
			if(StringUtil.isNotBlank(topic.getRecordFlow())){//编辑
				GeneralMethod.setRecordInfo(topic,false);
				return topicMapper.updateByPrimaryKeySelective(topic);
			}else{
				NydsDoctorTopicExample example = new NydsDoctorTopicExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andDoctorFlowEqualTo(topic.getDoctorFlow()).andTopicTitleEqualTo(topic.getTopicTitle());
				if(topicMapper.countByExample(example)>0){
					return -2;//该课题已维护
				}
				topic.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(topic,true);
				return topicMapper.insertSelective(topic);
			}
		}
		return 0;
	}

	@Override
	public List<NydsDoctorPaper> queryPaperByFlow(String tutorFlow) {
		NydsDoctorPaperExample example = new NydsDoctorPaperExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(tutorFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		return paperMapper.selectByExample(example);
	}

	@Override
	public List<NydsDoctorTopic> queryTopicByFlow(String tutorFlow) {
		NydsDoctorTopicExample example = new NydsDoctorTopicExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(tutorFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		return topicMapper.selectByExample(example);
	}

	@Override
	public int delPaperTopicByFlow(String tabFlag, String recordFlow) {
		if("paper".equals(tabFlag)){
			return paperMapper.deleteByPrimaryKey(recordFlow);
		}else{
			return topicMapper.deleteByPrimaryKey(recordFlow);
		}
	}

	@Override
	public int applyOption(String tutorFlow) {
		NydsOfficialDoctor record = new NydsOfficialDoctor();
		record.setDoctorFlow(tutorFlow);
		record.setApplyTime(DateUtil.getCurrDate());
		record.setApplyFlag(GlobalConstant.FLAG_Y);
		record.setPydwAuditStatusId("Passing");
		record.setPydwAuditStatusName("待审核");
		return doctorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor) {
		NydsOfficialDoctorExample example=new NydsOfficialDoctorExample();
		NydsOfficialDoctorExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		NydsOfficialDoctorExample.Criteria criteria1=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		//分委会
		if(StringUtil.isNotBlank(officialDoctor.getFwhOrgFlow())){
			criteria.andFwhOrgFlowEqualTo(officialDoctor.getFwhOrgFlow());
			criteria1.andFwhOrgFlowEqualTo(officialDoctor.getFwhOrgFlow());
		}
		//培养单位
		if(StringUtil.isNotBlank(officialDoctor.getPydwOrgFlow())){
			criteria.andPydwOrgFlowEqualTo(officialDoctor.getPydwOrgFlow());
			criteria1.andPydwOrgFlowEqualTo(officialDoctor.getPydwOrgFlow());
		}
		//一级学科
		if(StringUtil.isNotBlank(officialDoctor.getOneLevelSubjectId())){
			criteria.andOneLevelSubjectIdEqualTo(officialDoctor.getOneLevelSubjectId());
			criteria1.andOneLevelSubjectIdEqualTo(officialDoctor.getOneLevelSubjectId());
		}
		//二级学科
		if(StringUtil.isNotBlank(officialDoctor.getTwoLevelSubjectId())){
			criteria.andTwoLevelSubjectIdEqualTo(officialDoctor.getTwoLevelSubjectId());
			criteria1.andTwoLevelSubjectIdEqualTo(officialDoctor.getTwoLevelSubjectId());
		}
		//导师类别
		if(StringUtil.isNotBlank(officialDoctor.getDoctorTypeId())){
			criteria.andDoctorTypeIdEqualTo(officialDoctor.getDoctorTypeId());
			criteria1.andDoctorTypeIdEqualTo(officialDoctor.getDoctorTypeId());
		}
		//专业
		if(StringUtil.isNotBlank(officialDoctor.getRecruitSpeId())){
			criteria.andRecruitSpeIdEqualTo(officialDoctor.getRecruitSpeId());
			criteria1.andRecruitSpeIdEqualTo(officialDoctor.getRecruitSpeId());
		}
		//姓名
		if(StringUtil.isNotBlank(officialDoctor.getDoctorName())){
			criteria.andDoctorNameLike("%"+officialDoctor.getDoctorName()+"%");
			criteria1.andDoctorNameLike("%"+officialDoctor.getDoctorName()+"%");
		}
		//学位科审核状态
		if(StringUtil.isNotBlank(officialDoctor.getXwkAuditStatusId())){
			criteria.andXwkAuditStatusIdEqualTo(officialDoctor.getXwkAuditStatusId());
			criteria1.andXwkAuditStatusIdEqualTo(officialDoctor.getXwkAuditStatusId());
		}
		//是否展示网页
		if(StringUtil.isNotBlank(officialDoctor.getShowFlag())){
			criteria.andShowFlagEqualTo(officialDoctor.getShowFlag());
			criteria1.andShowFlagIsNull();
			example.or(criteria1);
		}
		example.setOrderByClause("CREATE_TIME DESC,DOCTOR_NAME");
		return doctorMapper.selectByExample(example);
	}

	@Override
	public NydsDoctorTopic queryTopicByRecordFlow(String recordFlow) {
		return topicMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public NydsDoctorPaper queryPaperByRecordFlow(String recordFlow) {
		return paperMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int auditOption(String role, String statusId, NydsOfficialDoctor tutor) {
		SysUser sysUser = GlobalContext.getCurrentUser();
		tutor.setLastAuditYear(DateUtil.getYear());
		if("pydw".equals(role)){
			tutor.setPydwUserFlow(sysUser.getUserFlow());
			tutor.setPydwAuditStatusId(statusId);
			tutor.setPydwAuditStatusName(statusId.equals("Passed")?"通过":"不通过");
			tutor.setPydwAuditTime(DateUtil.getCurrDate());
			//通过后初始化下一节点审核状态
			tutor.setFwhAuditStatusId(statusId.equals("Passed")?"Passing":"");
			tutor.setFwhAuditStatusName(statusId.equals("Passed")?"待审核":"");
		}else if("fwh".equals(role)){
			tutor.setFwhUserFlow(sysUser.getUserFlow());
			tutor.setFwhAuditStatusId(statusId);
			tutor.setFwhAuditStatusName(statusId.equals("Passed")?"通过":"不通过");
			tutor.setFwhAuditTime(DateUtil.getCurrDate());
			//通过后初始化下一节点审核状态
			tutor.setXwkAuditStatusId(statusId.equals("Passed")?"Passing":"");
			tutor.setXwkAuditStatusName(statusId.equals("Passed")?"待审核":"");
		}else{
			tutor.setXwkUserFlow(sysUser.getUserFlow());
			tutor.setXwkAuditStatusId(statusId);
			tutor.setXwkAuditStatusName(statusId.equals("Passed")?"通过":"不通过");
			tutor.setXwkAuditTime(DateUtil.getCurrDate());
			if("Passed".equals(statusId)){
				tutor.setInvalidTime(DateUtil.addDate(DateUtil.addYear(tutor.getXwkAuditTime(),4),1));
				if(StringUtil.isBlank(tutor.getFirstPassFlag())){
					//导师资格审核通过 首次标识
					tutor.setFirstPassFlag(GlobalConstant.FLAG_Y);
				}else{
					//导师资格审核通过 非首次标识
					tutor.setFirstPassFlag(GlobalConstant.FLAG_N);
				}
			}else{
				tutor.setXwkAuditTime("");
				tutor.setInvalidTime("");
			}
		}
		GeneralMethod.setRecordInfo(tutor,false);
		return doctorMapper.updateByPrimaryKeySelective(tutor);
	}

	@Override
	public int backAuditOption(String tutorFlow) {
		NydsOfficialDoctor record = new NydsOfficialDoctor();
		record.setDoctorFlow(tutorFlow);
		record.setApplyFlag("");
		record.setApplyTime("");
		//清空培养单位审核信息
		record.setPydwUserFlow("");
		record.setPydwAuditStatusId("");
		record.setPydwAuditStatusName("");
		record.setPydwAuditAdvice("");
		record.setPydwAuditTime("");
		//清空分委会审核信息
		record.setFwhUserFlow("");
		record.setFwhAuditStatusId("");
		record.setFwhAuditStatusName("");
		record.setFwhAuditAdvice("");
		record.setFwhAuditTime("");
		//清空学位科审核信息
		record.setXwkUserFlow("");
		record.setXwkAuditStatusId("");
		record.setXwkAuditStatusName("");
		record.setXwkAuditAdvice("");
		record.setXwkAuditTime("");
		GeneralMethod.setRecordInfo(record,false);
		return doctorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor, Map<String,String> params) {
		NydsOfficialDoctorExample example=new NydsOfficialDoctorExample();
		NydsOfficialDoctorExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		NydsOfficialDoctorExample.Criteria criteria2=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(officialDoctor.getDoctorName())){//姓名
			criteria.andDoctorNameLike("%"+officialDoctor.getDoctorName()+"%");
			criteria2.andDoctorNameLike("%"+officialDoctor.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(officialDoctor.getApplyFlag())){//导师申请标识
			criteria.andApplyFlagEqualTo(officialDoctor.getApplyFlag());
			criteria2.andApplyFlagEqualTo(officialDoctor.getApplyFlag());
		}
		if(StringUtil.isNotBlank(officialDoctor.getFwhOrgFlow())){//分委会
			criteria.andFwhOrgFlowEqualTo(officialDoctor.getFwhOrgFlow());
			criteria2.andFwhOrgFlowEqualTo(officialDoctor.getFwhOrgFlow());
		}
		if(StringUtil.isNotBlank(officialDoctor.getPydwOrgFlow())){//培养单位
			criteria.andPydwOrgFlowEqualTo(officialDoctor.getPydwOrgFlow());
			criteria2.andPydwOrgFlowEqualTo(officialDoctor.getPydwOrgFlow());
		}
		if(StringUtil.isNotBlank(officialDoctor.getDoctorTypeId())){//导师类型
			criteria.andDoctorTypeIdEqualTo(officialDoctor.getDoctorTypeId());
			criteria2.andDoctorTypeIdEqualTo(officialDoctor.getDoctorTypeId());
		}
		if(StringUtil.isNotBlank(officialDoctor.getPydwAuditStatusId())){//培养单位审核
			criteria.andPydwAuditStatusIdEqualTo(officialDoctor.getPydwAuditStatusId());
			criteria2.andPydwAuditStatusIdEqualTo(officialDoctor.getPydwAuditStatusId());
		}
		if(StringUtil.isNotBlank(officialDoctor.getFwhAuditStatusId())){//分委会审核
			criteria.andFwhAuditStatusIdEqualTo(officialDoctor.getFwhAuditStatusId());
			criteria2.andFwhAuditStatusIdEqualTo(officialDoctor.getFwhAuditStatusId());
		}
		if(StringUtil.isNotBlank(officialDoctor.getXwkAuditStatusId())){//学位科审核
			criteria.andXwkAuditStatusIdEqualTo(officialDoctor.getXwkAuditStatusId());
			criteria2.andXwkAuditStatusIdEqualTo(officialDoctor.getXwkAuditStatusId());
		}
		//导师新增开始时间(新增时间为创建时间)
		if(StringUtil.isNotBlank(officialDoctor.getCreateTime())){
			criteria.andCreateTimeGreaterThanOrEqualTo(officialDoctor.getCreateTime().replace("-","")+"000000");
			criteria2.andCreateTimeGreaterThanOrEqualTo(officialDoctor.getCreateTime().replace("-","")+"000000");
		}
		//导师新增结束时间
		if(StringUtil.isNotBlank(params.get("createTime2"))){
			criteria.andCreateTimeLessThanOrEqualTo(params.get("createTime2").replace("-","")+"999999");
			criteria2.andCreateTimeLessThanOrEqualTo(params.get("createTime2").replace("-","")+"999999");
		}
		//导师申请开始时间
		if(StringUtil.isNotBlank(officialDoctor.getApplyTime())){
			criteria.andApplyTimeGreaterThanOrEqualTo(officialDoctor.getApplyTime());
			criteria2.andApplyTimeGreaterThanOrEqualTo(officialDoctor.getApplyTime());
		}
		//导师申请结束时间
		if(StringUtil.isNotBlank(params.get("applyTime2"))){
			criteria.andApplyTimeLessThanOrEqualTo(params.get("applyTime2"));
			criteria2.andApplyTimeLessThanOrEqualTo(params.get("applyTime2"));
		}
		//导师认定请开始时间
		if(StringUtil.isNotBlank(officialDoctor.getXwkAuditTime())){
			criteria.andXwkAuditTimeGreaterThanOrEqualTo(officialDoctor.getXwkAuditTime());
			criteria2.andXwkAuditTimeGreaterThanOrEqualTo(officialDoctor.getXwkAuditTime());
		}
		//导师认定结束时间
		if(StringUtil.isNotBlank(params.get("xwkAuditTime2"))){
			criteria.andXwkAuditTimeLessThanOrEqualTo(params.get("xwkAuditTime2"));
			criteria2.andXwkAuditTimeLessThanOrEqualTo(params.get("xwkAuditTime2"));
		}
		//导师失效开始时间
		if(StringUtil.isNotBlank(officialDoctor.getInvalidTime())){
			criteria.andInvalidTimeGreaterThanOrEqualTo(officialDoctor.getInvalidTime());
			criteria2.andInvalidTimeGreaterThanOrEqualTo(officialDoctor.getInvalidTime());
		}
		//导师失效结束时间
		if(StringUtil.isNotBlank(params.get("invalidTime2"))){
			criteria.andInvalidTimeLessThanOrEqualTo(params.get("invalidTime2"));
			criteria2.andInvalidTimeLessThanOrEqualTo(params.get("invalidTime2"));
		}
		//导师资格从审核通过后默认4年失效
		if(StringUtil.isNotBlank(params.get("invalidYear"))){
			criteria.andXwkAuditTimeLike(Integer.valueOf(params.get("invalidYear"))-4+"%");
			criteria2.andXwkAuditTimeLike(Integer.valueOf(params.get("invalidYear"))-4+"%");
		}
		//是否暂停招生
		if(StringUtil.isNotBlank(officialDoctor.getStopRecruit())){
			criteria.andStopRecruitEqualTo(officialDoctor.getStopRecruit());
			criteria2.andStopRecruitEqualTo(officialDoctor.getStopRecruit());
		}
		//导师退休开始时间
		if(StringUtil.isNotBlank(officialDoctor.getRetireTime())){
			criteria.andRetireTimeGreaterThanOrEqualTo(officialDoctor.getRetireTime());
			criteria2.andRetireTimeGreaterThanOrEqualTo(officialDoctor.getRetireTime());
		}
		//导师退休结束时间
		if(StringUtil.isNotBlank(params.get("retireTime2"))){
			criteria.andRetireTimeLessThanOrEqualTo(params.get("retireTime2"));
			criteria2.andRetireTimeLessThanOrEqualTo(params.get("retireTime2"));
		}
		//取消开始时间
		if(StringUtil.isNotBlank(officialDoctor.getBlockTime())){
			criteria.andBlockTimeGreaterThanOrEqualTo(officialDoctor.getBlockTime());
			criteria2.andBlockTimeGreaterThanOrEqualTo(officialDoctor.getBlockTime());
		}
		//取消结束时间
		if(StringUtil.isNotBlank(params.get("blockTime2"))){
			criteria.andBlockTimeLessThanOrEqualTo(params.get("blockTime2"));
			criteria2.andBlockTimeLessThanOrEqualTo(params.get("blockTime2"));
		}
		//导师启用状态
		if(StringUtil.isNotBlank(officialDoctor.getBlockFlag())){
			if(GlobalConstant.FLAG_Y.equals(officialDoctor.getBlockFlag())){
				criteria.andBlockFlagEqualTo(officialDoctor.getBlockFlag());
				criteria2.andBlockFlagEqualTo(officialDoctor.getBlockFlag());
			}else{
				criteria.andBlockFlagEqualTo(officialDoctor.getBlockFlag());
				criteria2.andBlockFlagIsNull();
			}
		}
		example.or(criteria2);
		example.setOrderByClause("APPLY_FLAG DESC,PYDW_AUDIT_STATUS_ID DESC,FWH_AUDIT_STATUS_ID DESC,XWK_AUDIT_STATUS_ID DESC,CREATE_TIME DESC");
		return doctorMapper.selectByExample(example);
	}

	@Override
	public int importTutorExcel(MultipartFile file) throws Exception {
		InputStream is = null;
		try {
			byte[] fileData = new byte[(int) file.getSize()];
			is = file.getInputStream();
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			return parseTutorExcel(wb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			is.close();
		}
	}
	private int parseTutorExcel(Workbook wb) throws Exception{
		//导入记录数
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum > 0){
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum()+1;
			if(row_num <= 1){
				throw new Exception("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			List<String> colnames = new ArrayList<String>();
			for(int i = 0 ; i <cell_num; i++){
				if(null == titleR.getCell(i)){
					throw new Exception("导入文件首行列名异常！");
				}
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			for(int i = 1;i < row_num; i++) {
				SysUser user = new SysUser();
				NydsOfficialDoctor tutor = new NydsOfficialDoctor();
				//cmis导师角色配置
				String roleFlow = InitConfig.getSysCfg("xjgl_tutor_role_flow");
				Row r = sheet.getRow(i);
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					Cell cell = r.getCell(j);
					if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					String currTitle = colnames.get(j);
					if("用户名".equals(currTitle)){
						user.setUserCode(value);
					}
					if("导师姓名".equals(currTitle)){
						user.setUserName(value);
						tutor.setDoctorName(value);
					}
					if("性别".equals(currTitle)){
						user.setSexName(value);
						tutor.setSexName(value);
						if(StringUtil.isNotBlank(value)){
							if("男".equals(value)){
								user.setSexId(UserSexEnum.Man.getId());
								tutor.setSexId(UserSexEnum.Man.getId());
							}
							if("女".equals(value)){
								user.setSexId(UserSexEnum.Woman.getId());
								tutor.setSexId(UserSexEnum.Woman.getId());
							}
						}
					}
					if("出生年月".equals(currTitle)){
						user.setUserBirthday(value);
						tutor.setBirthDay(value);
					}
					if("技术职称".equals(currTitle)){
						tutor.setTechnicalTitleName(value);
//						List<SysDict> dictList= DictTypeEnum.XjTechnicalTitle.getSysDictList();
//						if(dictList!=null&&dictList.size()>0){
//							for (SysDict sd:dictList) {
//								if(StringUtil.isNotBlank(value)&&value.equals(sd.getDictName())){
//									tutor.setTechnicalTitleId(sd.getDictId());
//								}
//							}
//							if(StringUtil.isBlank(tutor.getTechnicalTitleId())){
//								throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的技术职称！");
//							}
//						}
					}
					if("学位".equals(currTitle)){
						tutor.setDegreeName(value);
						if(StringUtil.isNotBlank(value)){
							List<SysDict> dictList= DictTypeEnum.UserDegree.getSysDictList();
							if(dictList!=null&&dictList.size()>0){
								for (SysDict sd:dictList) {
									if(value.equals(sd.getDictName())){
										tutor.setDegreeId(sd.getDictId());
									}
								}
								if(StringUtil.isBlank(tutor.getDegreeId())){
									throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的学位！");
								}
							}
						}
					}
					if("手机".equals(currTitle)){
						tutor.setMobilePhone(value);
					}
					if("办公电话".equals(currTitle)){
						tutor.setWorkPhone(value);
					}
					if("电子邮箱".equals(currTitle)){
						tutor.setEmailNo(value);
					}
					if("工作单位".equals(currTitle)){
						tutor.setWorkUnit(value);
					}
					if("是否暂停招生".equals(currTitle)){
						tutor.setStopRecruit(value);
						if(StringUtil.isNotBlank(value)){
							if("是".equals(value)){
								tutor.setStopRecruit("Y");
							}else if("否".equals(value)){
								tutor.setStopRecruit("N");
							}else{
								throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的是否暂停招生！");
							}
						}
					}
					if("导师类型".equals(currTitle)){
						tutor.setDoctorTypeName(value);
						if(StringUtil.isNotBlank(value)){
							if("学术型博导".equals(value)){
								tutor.setDoctorTypeId("xsxbd");
							}else if("学术型硕导".equals(value)){
								tutor.setDoctorTypeId("xsxsd");
							}else if("专业型博导".equals(value)){
								tutor.setDoctorTypeId("zyxbd");
							}else if("专业型硕导".equals(value)){
								tutor.setDoctorTypeId("zyxsd");
							}else{
								throw new Exception("导入失败！第"+ (count+2) +"行请填写正确的导师类型！");
							}
						}
					}
					if("培养单位".equals(currTitle)){
						tutor.setPydwOrgName(value);
					}
					if("分委员会".equals(currTitle)){
						tutor.setFwhOrgName(value);
					}
				}
				if(StringUtil.isBlank(user.getUserCode())){
					throw new Exception("导入失败！第"+ (count+2) +"行用户名不能为空！");
				}
				if(StringUtil.isBlank(user.getUserName())){
					throw new Exception("导入失败！第"+ (count+2) +"行导师姓名不能为空！");
				}
				if(StringUtil.isBlank(tutor.getDoctorTypeName())){
					throw new Exception("导入失败！第"+ (count+2) +"行导师类型不能为空！");
				}
				if(StringUtil.isBlank(tutor.getPydwOrgName())){
					throw new Exception("导入失败！第"+ (count+2) +"行培养单位不能为空！");
				}
				if(StringUtil.isBlank(tutor.getFwhOrgName())){
					throw new Exception("导入失败！第"+ (count+2) +"行分委员会不能为空！");
				}
				user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				//验证分委会是否存在
				SysDeptExample deptExpl = new SysDeptExample();
				deptExpl.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andOrgFlowEqualTo(user.getOrgFlow());
				List<SysDept> sysDept = deptMapper.selectByExample(deptExpl);
				if(null != sysDept && !sysDept.isEmpty()){
					for(SysDept sd : sysDept){
						if(tutor.getFwhOrgName().equals(sd.getDeptName())){
							tutor.setFwhOrgFlow(sd.getDeptFlow());
						}
					}
				}
				if(StringUtil.isBlank(tutor.getFwhOrgFlow())){
					throw new Exception("导入失败！第"+ (count+2) +"行分委员会有误！");
				}
				//验证分委会是否存在
				List<SysOrg> orgList = orgBiz.searchHbresOrgList();
				if(null != orgList && !orgList.isEmpty()){
					for(SysOrg so : orgList){
						if(tutor.getPydwOrgName().equals(so.getOrgName())){
							tutor.setPydwOrgFlow(so.getOrgFlow());
						}
					}
				}
				if(StringUtil.isBlank(tutor.getPydwOrgFlow())){
					throw new Exception("导入失败！第"+ (count+2) +"行培养单位有误！");
				}
				SysUserExample userExpl = new SysUserExample();
				userExpl.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andUserCodeEqualTo(user.getUserCode());
				List<SysUser> existUser = userMapper.selectByExample(userExpl);
				if(null != existUser && !existUser.isEmpty()){
					//存在此账号
					for(SysUser su : existUser){
						if(user.getUserName().equals(su.getUserName())){
							user.setUserFlow(su.getUserFlow());
							tutor.setDoctorFlow(su.getUserFlow());
						}
					}
					//账号和姓名不匹配
					if(StringUtil.isBlank(user.getUserFlow())){
						throw new Exception("导入失败！第"+ (count+2) +"行用户名已存在，但与导师姓名有误！");
					}else{
						//修改
						GeneralMethod.setRecordInfo(user,false);
						userMapper.updateByPrimaryKeySelective(user);
						SysUserRoleExample userRoleExpl = new SysUserRoleExample();
						userRoleExpl.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
								.andUserFlowEqualTo(user.getUserFlow()).andRoleFlowEqualTo(roleFlow);
						if(userRoleMapper.countByExample(userRoleExpl) == 0){
							//新增导师角色
							SysUserRole record = new SysUserRole();
							record.setRecordFlow(PkUtil.getUUID());
							record.setUserFlow(user.getUserFlow());
							record.setWsId("cmis");
							record.setRoleFlow(roleFlow);
							GeneralMethod.setRecordInfo(record,true);
							userRoleMapper.insertSelective(record);
						}
						NydsOfficialDoctorExample nodExpl = new NydsOfficialDoctorExample();
						nodExpl.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
								.andDoctorFlowEqualTo(user.getUserFlow());
						if(doctorMapper.countByExample(nodExpl) <= 0){
							GeneralMethod.setRecordInfo(tutor,true);
							doctorMapper.insertSelective(tutor);
						}else{
							GeneralMethod.setRecordInfo(tutor,false);
							doctorMapper.updateByPrimaryKeySelective(tutor);
						}

					}
				}else{
					//新账号
					user.setUserFlow(PkUtil.getUUID());
					user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
					user.setStatusId(UserStatusEnum.Activated.getId());
					user.setStatusDesc(UserStatusEnum.Activated.getName());
					GeneralMethod.setRecordInfo(user, true);
					userMapper.insert(user);
					SysUserRole record = new SysUserRole();
					record.setRecordFlow(PkUtil.getUUID());
					record.setUserFlow(user.getUserFlow());
					record.setWsId("cmis");
					record.setRoleFlow(roleFlow);
					GeneralMethod.setRecordInfo(record,true);
					userRoleMapper.insertSelective(record);
					tutor.setDoctorFlow(user.getUserFlow());
					GeneralMethod.setRecordInfo(tutor,true);
					doctorMapper.insertSelective(tutor);
				}
				count ++;
			}
		}
		return count;
	}
	private Workbook createCommonWorkbook(InputStream inS) throws Exception {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
		// EXCEL2003使用的是微软的文件系统
		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
			return new HSSFWorkbook(inS);
		}
		// EXCEL2007使用的是OOM文件格式
		if (POIXMLDocument.hasOOXMLHeader(inS)) {
			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
			return new XSSFWorkbook(OPCPackage.open(inS));
		}
		throw new Exception("不能解析的excel版本");
	}
	private static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D){
			return String.valueOf((long) d);
		}
		return String.valueOf(d);
	}

	@Override
	public int delTutorByFlow(String tutorFlow) {
		int count = 0;
		if(StringUtil.isNotBlank(tutorFlow)){
			doctorMapper.deleteByPrimaryKey(tutorFlow);
			SysUserRoleExample example = new SysUserRoleExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andUserFlowEqualTo(tutorFlow).andRoleFlowEqualTo(InitConfig.getSysCfg("xjgl_tutor_role_flow"));
			userRoleMapper.deleteByExample(example);
			count += userMapper.deleteByPrimaryKey(tutorFlow);
		}
		return count;
	}

	@Override
	public int blockTutorByFlow(NydsOfficialDoctor doctor) {
		int count = 0;
		if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
			GeneralMethod.setRecordInfo(doctor,false);
			count=doctorMapper.updateByPrimaryKey(doctor);
		}
		return count;
	}
}
