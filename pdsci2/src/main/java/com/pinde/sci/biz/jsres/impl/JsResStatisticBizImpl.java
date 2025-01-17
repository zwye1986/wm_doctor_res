package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.jsres.JsResTeacherLevelEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IJsResStatisticBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.jsres.ChartExtMapper;
import com.pinde.sci.dao.jsres.JsResDoctorRecruitExtMapper;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import com.pinde.sci.dao.sys.SysUserExtMapper;
import com.pinde.sci.model.jsres.TeacherReportCountDto;
import com.pinde.sci.model.jsres.TeacherTrainingInfoVo;
import com.pinde.core.model.JsDoctorInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResStatisticBizImpl implements IJsResStatisticBiz{

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private SysDictMapper dictMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private JsResDoctorRecruitExtMapper recruitExtMapper;
	@Autowired
	private ChartExtMapper chartExtMapper;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Autowired
	private ResTeacherTrainingMapper teacherTrainingMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserExtMapper userExtMapper;

    private static final Logger logger = LoggerFactory.getLogger(JsResStatisticBizImpl.class);

	@Override
	public int statisticCountyOrgCount(SysOrg org) {
		SysOrgExample example=new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(org.getOrgProvId())){
	    	criteria.andOrgProvIdEqualTo(org.getOrgProvId());
	    }
		if(StringUtil.isNotBlank(org.getOrgLevelId())){
			criteria.andOrgLevelIdEqualTo(org.getOrgLevelId());
		}
		if(StringUtil.isNotBlank(org.getOrgCityId())){
			criteria.andOrgCityIdEqualTo(org.getOrgCityId());
		}
		if(StringUtil.isNotBlank(org.getOrgTypeId())){
			criteria.andOrgTypeIdEqualTo(org.getOrgTypeId());
		}
		return sysOrgMapper.countByExample(example);
	}
	@Override
    public int statisticDoctorCount(com.pinde.core.model.ResDoctorRecruit recruit, List<String> cityIdList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("cityIdList", cityIdList);
		return recruitExtMapper.doctorCount(paramMap);
	}
	@Override
	public Map statisticDoctorCountMap(Map paramMap) {
		return recruitExtMapper.doctorCounMap(paramMap);
	}
	@Override
	public Map statisticDoctorCountMapForUni(Map paramMap) {
		return recruitExtMapper.doctorCountMapForUni(paramMap);
	}

	@Override
	public List<Map<String, Object>> getCurrDocDetails(Map<String, Object> paramMap) {
		return recruitExtMapper.getCurrDocDetails(paramMap);
	}
	@Override
	public List<Map<String, Object>> getCurrDocDetailsForUni(Map<String, Object> paramMap) {
		return recruitExtMapper.getCurrDocDetailsForUni(paramMap);
	}

	@Override
	public Map<String, Object> sumCountAudit(List<String> orgFlows) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orgFlows",orgFlows);
		return recruitExtMapper.sumCountAudit(paramMap);
	}

	@Override
	public Map<String, Object> sumCountAuditRes(List<String> orgFlows) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orgFlows",orgFlows);
		return recruitExtMapper.sumCountAuditRes(paramMap);
	}

	@Override
    public List<Map<String, Object>> statisticJointCountByOrg(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList, String trainTypeId, List<String> docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticJointCountByOrg(paramMap);
	}

	@Override
	public List<Map<String,String>> findCountryDocCount(String sessionNumber) {
		return chartExtMapper.findCountryDocCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findProDocCount(String sessionNumber) {
		return chartExtMapper.findProDocCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findCountrySpeDocCount(String sessionNumber) {
		return chartExtMapper.findCountrySpeDocCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findProSpeDocCount(String sessionNumber) {
		return chartExtMapper.findProSpeDocCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findDocTypeCount(String sessionNumber) {
		return chartExtMapper.findDocTypeCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findOrgTypeCount(String sessionNumber) {
		return chartExtMapper.findOrgTypeCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findCityDocCount(String sessionNumber) {
		return chartExtMapper.findCityDocCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findOrgAssiCount(String sessionNumber) {
		return chartExtMapper.findOrgAssiCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findSpeGraduateCount(String sessionNumber) {
		return chartExtMapper.findSpeGraduateCount(sessionNumber);
	}
	@Override
	public List<Map<String,String>> findOrgGraduateCount(String sessionNumber) {
		return chartExtMapper.findOrgGraduateCount(sessionNumber);
	}
	@Override
    public List<Map<String, String>> doctorNumForUni1(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlows, SysOrg org) {
		return chartExtMapper.doctorNumForUni1(recruit,orgFlows,org);
	}
	@Override
    public List<Map<String, String>> doctorNumForUni1DaoChu(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlows, SysOrg org) {
		return chartExtMapper.doctorNumForUni1DaoChu(recruit,orgFlows,org);
	}
	@Override
    public List<Map<String, String>> doctorNumForUni2(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlows, SysOrg org) {
		return chartExtMapper.doctorNumForUni2(recruit,orgFlows,org);
	}

	@Override
    public List<Map<String, String>> doctorNumForUni2DaoChu(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlows, SysOrg org) {
		return chartExtMapper.doctorNumForUni2DaoChu(recruit,orgFlows,org);
	}

	@Override
	public List<SysOrg> getRotationData(Map<String, Object> paramMap) {
		return chartExtMapper.getRotationData(paramMap);
	}

	@Override
	public List<PersonStaticExample> getPersonStaticData(Map<String, Object> paramMap) {
		return chartExtMapper.getPersonStaticData(paramMap);
	}
	@Override
	public List<PersonStaticExample> getPersonStaticDataNewyuh(Map<String, Object> paramMap) {
		return chartExtMapper.getPersonStaticDataNEWyuh(paramMap);
	}
	@Override
	public Integer residentRecruitCount(Map<String,Object> paramMap){
		return chartExtMapper.residentRecruitCount(paramMap);
	}
	@Override
	public Integer inSchoolRecruitCount(Map<String,Object> paramMap){
		return chartExtMapper.inSchoolRecruitCount(paramMap);
	}

	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName){
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator+ dateString ;
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
                logger.error("", e);
				throw new RuntimeException("保存文件失败！");
			}

			//删除原文件
			if(StringUtil.isNotBlank(oldFolderName)){
				try {
					oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if(imgFile.exists()){
						imgFile.delete();
					}
				} catch (Exception e) {
                    logger.error("", e);
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

			//删除临时目录
//			FileUtil.deletefile(newDir+File.separator +originalFilename);
		}
		return path;
	}

	@Override
	public int deleteCerfiticateImg(String recordFlow) {
		if(StringUtil.isNotBlank(recordFlow)){
			ResTeacherTraining training = teacherTrainingMapper.selectByPrimaryKey(recordFlow);
			if(training != null){ //修改课程删除
				String img = training.getCertificateUrl();
				if(StringUtil.isNotBlank(img)){
					try {
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))  + File.separator + img;
						File file = new File(img);
						if(file.exists()){
							boolean delRestlt = file.delete();
							if(delRestlt){//删除成功
								training.setCertificateUrl(null);
								return teacherTrainingMapper.updateByPrimaryKeySelective(training);
							}
						}
					} catch (Exception e) {
                        logger.error("", e);
						throw new RuntimeException("删除图片失败！");
					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int deleteAchievementImg(String recordFlow) {
		if(StringUtil.isNotBlank(recordFlow)){
			ResTeacherTraining training = teacherTrainingMapper.selectByPrimaryKey(recordFlow);
			if(training != null){ //修改课程删除
				String img = training.getAchievementUrl();
				if(StringUtil.isNotBlank(img)){
					try {
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))  + File.separator + img;
						File file = new File(img);
						if(file.exists()){
							boolean delRestlt = file.delete();
							if(delRestlt){//删除成功
								training.setAchievementUrl(null);
								return teacherTrainingMapper.updateByPrimaryKeySelective(training);
							}
						}
					} catch (Exception e) {
                        logger.error("", e);
						throw new RuntimeException("删除图片失败！");
					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public int importTeacherExcelNew(MultipartFile file,String roleFlag) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			return parseOperExcel(wb,roleFlag);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void downImg(ResTeacherTraining training, HttpServletResponse response) throws Exception{
		/*文件是否存在*/
		Boolean fileExists = false;
		if(training !=null){
			byte[] data=null;
			long dataLength = 0;
			String sux=".jpg";
			/*如果文件相对路径不为空*/
			if( StringUtil.isNotBlank(training.getCertificateUrl())){
				/*获取文件物理路径*/
				String filePath = InitConfig.getSysCfg("upload_base_dir") + File.separator + training.getCertificateUrl();
				sux = training.getCertificateUrl().substring(training.getCertificateUrl().lastIndexOf("."));
				File downLoadFile = new File(filePath);
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {
					String fileName = training.getDoctorName() + "（证书编号：" + training.getCertificateNo() +"）";
					fileName = URLEncoder.encode(fileName, "UTF-8")+sux;
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
			/*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现证书附件,点击返回上一页</a>".getBytes(StandardCharsets.UTF_8));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	@Override
	public ResTeacherTraining searchTeacherInfoByCertificateNoNotSelf(String certificateNo, String doctorName) {
		ResTeacherTrainingExample example = new ResTeacherTrainingExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andCertificateNoEqualTo(certificateNo)
				.andDoctorNameNotEqualTo(doctorName);
		List<ResTeacherTraining> list = teacherTrainingMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private int parseOperExcel(Workbook wb,String roleFlag) throws Exception{
		int count = 0;//导入记录数
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum > 0){
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new Exception("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			List<String> colnames = new ArrayList<String>();
			for(int i = 0 ; i <cell_num; i++){
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			for(int i = 1;i < row_num; i++) {
				ResTeacherTraining training = new ResTeacherTraining();//建立bean
				Row r = sheet.getRow(i);
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					Cell cell = r.getCell(j);
					if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType().getCode() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					String currTitle = colnames.get(j);
					if("师资培训会议名称".equals(currTitle)){
						training.setMeetingName(value);
					}
					if("证书编号".equals(currTitle)){
						training.setCertificateNo(value);
					}
					if("基地名称".equals(currTitle)){
						training.setOrgName(value);
					}
					if("姓名".equals(currTitle)){
						training.setDoctorName(value);
					}
					if("性别".equals(currTitle)){
						training.setSexName(value);
					}
					if("科室".equals(currTitle)){
						training.setDeptName(value);
					}
					if("专业".equals(currTitle)){
						training.setSpeName(value);
					}
					if("培训年份".equals(currTitle)){
						training.setTrainingYear(value);
					}
					if("技术职称".equals(currTitle)){
						training.setTechnicalTitle(value);
					}
					if("师资类型".equals(currTitle)){
						training.setTeacherLevelName(value);
						if (JsResTeacherLevelEnum.GeneralFaculty.getName().equals(value)) {
							training.setTeacherLevelId(JsResTeacherLevelEnum.GeneralFaculty.getId());
						}
						if (JsResTeacherLevelEnum.KeyFaculty.getName().equals(value)) {
							training.setTeacherLevelId(JsResTeacherLevelEnum.KeyFaculty.getId());
						}
					}
					if("手机号码".equals(currTitle)){
						training.setUserPhone(value);
					}

				}

				if(StringUtil.isBlank(training.getMeetingName())){
					throw new Exception("导入失败！第"+ (count+2) +"行师资培训会议名称不能为空！");
				}
				if(StringUtil.isBlank(training.getCertificateNo())){
					throw new Exception("导入失败！第"+ (count+2) +"行证书编号不能为空！");
				}
                if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
					if (StringUtil.isBlank(training.getOrgName())) {
						throw new Exception("导入失败！第" + (count + 2) + "行基地名称不能为空！");
					} else {
						SysOrg org = orgBiz.readSysOrgByName(training.getOrgName());
						if (org == null)
							throw new RuntimeException("导入失败！第" + count + 2 + "行，基地名称输入不正确！");
						training.setOrgFlow(org.getOrgFlow());
					}
				}else{
					training.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
					training.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				}
				if(StringUtil.isBlank(training.getDoctorName())){
					throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
				}
				if(StringUtil.isNotBlank(training.getCertificateNo()) && StringUtil.isNotBlank(training.getDoctorName())){
					ResTeacherTraining rtt = this.searchTeacherInfoByCertificateNoNotSelf(training.getCertificateNo(),training.getDoctorName());
					if(null != rtt){
						throw new Exception("导入失败！第"+ (count+2) +"行证书编号重复，请核对！");
					}
				}
				if(StringUtil.isBlank(training.getSexName())){
					throw new Exception("导入失败！第"+ (count+2) +"行性别不能为空！");
				}else{
					if(!"男".equals(training.getSexName()) && !"女".equals(training.getSexName())){
						throw new Exception("导入失败！第"+ (count+2) +"行性别填写错误！");
					}
				}
				if(StringUtil.isBlank(training.getDeptName())){
					throw new Exception("导入失败！第"+ (count+2) +"行科室不能为空！");
				}else {
					//判断基地是否存在科室
					SysDeptExample deptExample = new SysDeptExample();
                    deptExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
							.andOrgFlowEqualTo(training.getOrgFlow()).andDeptNameEqualTo(training.getDeptName());
					List<SysDept> deptList = deptMapper.selectByExample(deptExample);
					if(null == deptList || deptList.size() == 0)
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，基地无该科室，请核对！");
					training.setDeptFlow(deptList.get(0).getDeptFlow());
				}
				if(StringUtil.isBlank(training.getSpeName())){
					throw new Exception("导入失败！第"+ (count+2) +"行专业不能为空！");
				}else{
					SysDictExample dictExample = new SysDictExample();
                    dictExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                            .andDictTypeIdEqualTo(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId())
							.andDictNameEqualTo(training.getSpeName());
					List<SysDict> dictList = dictMapper.selectByExample(dictExample);
					if(null == dictList || dictList.size() == 0)
						throw new RuntimeException("导入失败！第"+ count+2 +"行，专业输入错误！");
					training.setSpeId(dictList.get(0).getDictId());
				}
				if(StringUtil.isBlank(training.getTrainingYear())){
					throw new Exception("导入失败！第"+ (count+2) +"行培训年份不能为空！");
				}
				if(StringUtil.isBlank(training.getTechnicalTitle())){
					throw new Exception("导入失败！第"+ (count+2) +"行技术职称不能为空！");
				}
				if(StringUtil.isBlank(training.getTeacherLevelName())){
					training.setTeacherLevelName(JsResTeacherLevelEnum.GeneralFaculty.getName());
					training.setTeacherLevelId(JsResTeacherLevelEnum.GeneralFaculty.getId());
				} else {
					if (!training.getTeacherLevelName().equals(JsResTeacherLevelEnum.GeneralFaculty.getName()) && !training.getTeacherLevelName().equals(JsResTeacherLevelEnum.KeyFaculty.getName())) {
						throw new Exception("导入失败！第"+ (count+2) +"行,师资类型输入错误！");
					}
				}
				if(StringUtil.isBlank(training.getUserPhone())){
					throw new Exception("导入失败！第"+ (count+2) +"行,手机号码不能为空！");
				} else {
					SysUserExample example=new SysUserExample();
                    example.createCriteria().andUserPhoneEqualTo(training.getUserPhone()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
					if(sysUserList != null && !sysUserList.isEmpty()){
						throw new Exception("导入失败！第"+ (count+2) +"行,手机号码重复！");
					}
				}
				training.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(training,true);
				teacherTrainingMapper.insertSelective(training);

				SysUser user = new SysUser();
				user.setUserFlow(training.getRecordFlow());
				user.setUserCode(training.getUserPhone());
				SysUser sysUser = userBiz.readSysUser(training.getRecordFlow());
				if (sysUser == null) {
					user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getJxInitPassWord()));
					user.setStatusId(UserStatusEnum.Activated.getId());
					user.setStatusDesc(UserStatusEnum.Activated.getName());
					user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
					user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
					GeneralMethod.setRecordInfo(user, true);
					sysUserMapper.insert(user);
				}
				user = userBiz.readSysUser(training.getRecordFlow());
				user.setUserName(training.getDoctorName());
				user.setUserPhone(training.getUserPhone());
				user.setSexName(training.getSexName());
				SysDept dept =deptBiz.readSysDept(training.getDeptFlow());
				if(dept!=null)
				{
					user.setDeptName(dept.getDeptName());
					user.setDeptFlow(training.getDeptFlow());
				}
				user.setOrgFlow(training.getOrgFlow());
				user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
				userBiz.saveUser(user);
				List<String> allDeptFlows = new ArrayList<String>();
				if(StringUtil.isNotBlank(user.getDeptFlow())&&!allDeptFlows.contains(user.getDeptFlow())){
					allDeptFlows.add(user.getDeptFlow());
				}
				if(allDeptFlows.size()>0){
					userBiz.addUserDept(user,allDeptFlows);
				}else {
					userBiz.disUserDept(user);
				}
				//打开app权限
				String cfgCode = "jsres_teacher_app_login_"+user.getUserFlow();
                String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
				String cfgDesc = "是否开放带教app权限";
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setCfgDesc(cfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				jsResPowerCfgBiz.save(cfg);

				SysRole sysRole = userRoleBiz.getByRoleName("带教老师");
				SysUserRole sysUserRole = userRoleBiz.readUserRole(user.getUserFlow(), sysRole.getRoleFlow());
				if (sysUserRole == null) {
					List<String> allRoleFlows = new ArrayList<String>();
					allRoleFlows.add(sysRole.getRoleFlow());
					for (String roleFlow : allRoleFlows) {
                        userRoleBiz.saveSysUserRole(user.getUserFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
					}
				}

				count ++;
			}
		}
		return count;
	}

	@Override
	public int  statisticJointOrgCount(SysOrg org) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		if(org!=null){
			paramMap.put("org", org);
		}
		return recruitExtMapper.joingorgCount(paramMap);
	}
	@Override
	public int statisticYearCondocCount(ResDoctor doctor, List<SysOrg> sysOrgs) {
		List<String> orgFlows=new ArrayList<String>();
		if(sysOrgs!=null&&!sysOrgs.isEmpty()){
			for(SysOrg s:sysOrgs){
				orgFlows.add(s.getOrgFlow());
			}
		}
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("doctor", doctor);
		paramMap.put("orgFlowList", orgFlows);
	return doctorExtMapper.statisticYearCondocCount(paramMap);
	}
	@Override
    public List<Map<String, Object>> statisticDocCouByType(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList, ResDoctor doctor, List<String> docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticDocCouByType(paramMap);
	}
	@Override
    public List<Map<String, Object>> statisticJointCount(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList, ResDoctor doctor, List<String> docTypeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("doctor", doctor);
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("docTypeList", docTypeList);
		return recruitExtMapper.statisticJointCount(paramMap);
	}
	@Override
	public void export(List<SysOrg> sysOrgList,List<SysDict> typeSpeList,String trainTypeId,Map<Object, Object> totalCountMap,Map<Object, Object> zongjiMap,Map<Object, Object> orgSpeFlagMap,Map<Object, Object> joingCountMap,HttpServletResponse response) throws IOException {
				//创建工作簿
				HSSFWorkbook wb = new HSSFWorkbook();  
				// 为工作簿添加sheet  
			    HSSFSheet sheet = wb.createSheet("sheet1"); 
			    //定义将用到的样式 
			    HSSFCellStyle styleCenter = wb.createCellStyle(); //居中 
			    styleCenter.setAlignment(HorizontalAlignment.CENTER);
			    
			    HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
			    styleLeft.setAlignment(HorizontalAlignment.LEFT);
			    styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
			    
			    HSSFCellStyle stylevwc = wb.createCellStyle(); //居中 
			    stylevwc.setAlignment(HorizontalAlignment.CENTER);
			    stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
			    
			    //列宽自适应
			    Map<Integer,Integer> colWidthAuto = new HashMap<Integer, Integer>();
			    HSSFRow rowDep = sheet.createRow(0);
			    
			    HSSFCell cel = rowDep.createCell(0);
			    cel.setCellValue("名称");
			    cel.setCellStyle(styleCenter);
			    HSSFCell celTwo = rowDep.createCell(1);
			    celTwo.setCellValue("小计");
			    celTwo.setCellStyle(styleCenter);
			    int colnum = 2;//列
			    int jielie=0;
			    if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			    	for(SysOrg so:sysOrgList){
			    		Integer width = colWidthAuto.get(colnum);
			    		if(width==null){
			    			width = 0;
			    		}
			    		jielie=colnum;
			    		HSSFCell orgCell = rowDep.createCell(colnum);
			    		orgCell.setCellValue(so.getOrgName());
			    		orgCell.setCellStyle(styleCenter);

                        Integer dateNewWidth = so.getOrgName().getBytes().length * 256;
			    		width = width<dateNewWidth?dateNewWidth:width;
			    		sheet.setColumnWidth(colnum,width);
			    		colWidthAuto.put(colnum,width);
			    		colnum++;
			    		//算总计
			    		int count=0;
			    		if(typeSpeList!=null&&!typeSpeList.isEmpty()){
		    		    	for(SysDict sd : typeSpeList){
				    		String key = so.getOrgFlow()+trainTypeId+sd.getDictId();//确定唯一key
                                if (totalCountMap.get(key) != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(key))) {
				    			count+=Integer.parseInt(totalCountMap.get(key)+"");
				    			}
		    		    	}
			    		}
			    		zongjiMap.put(so.getOrgFlow(), count);
			    	}
			    }
			    int rownum = 1;
			    int total=0;
			    if(typeSpeList!=null&&!typeSpeList.isEmpty()){
			    	for(SysDict sd : typeSpeList){
			    		HSSFRow rowDepts= sheet.createRow(rownum);
			    		HSSFCell cell = rowDepts.createCell(0);
			    		cell.setCellValue(sd.getDictName());
			    		cell.setCellStyle(styleCenter);
			    		int lie = 2;
			    		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			    			int xiaojiCount=0;
		    		    	for(SysOrg so:sysOrgList){
		    		    		sheet.setColumnWidth(0, 4500);
		    		    		String key = so.getOrgFlow()+trainTypeId+sd.getDictId();//确定唯一key
		    		    		HSSFCell orgCell = rowDepts.createCell(lie);
                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(key))) {
		    		    			if(totalCountMap.get(key)!= null){
		    		    				xiaojiCount+=Integer.parseInt(totalCountMap.get(key)+"");//算小计
		    		    				if(joingCountMap.get(key)!= null){
		    		    					orgCell.setCellValue(totalCountMap.get(key)+"("+joingCountMap.get(key)+")");
		    		    				}else{
		    		    					orgCell.setCellValue(totalCountMap.get(key)+"");
		    		    				}
		    		    			}else{
		    		    				orgCell.setCellValue(0);
		    		    			}
		    		    		}else{
		    		    			orgCell.setCellValue("--");
		    		    		}
		    		    		orgCell.setCellStyle(styleCenter);
		    		    		lie++;
		    		    		}
		    		    	//放小计
				    		HSSFCell orgCell = rowDepts.createCell(1);
				    		orgCell.setCellValue(xiaojiCount);
				    		orgCell.setCellStyle(styleCenter);
				    		total+=xiaojiCount;
				    	}
			    		rownum++;
			    	}
			    	HSSFRow rowDepts= sheet.createRow(rownum);
			    	HSSFCell cell = rowDepts.createCell(0);
			    	cell.setCellValue("总计");
		    		cell.setCellStyle(styleCenter);
		    		HSSFCell orgCell = rowDepts.createCell(1);
		    		orgCell.setCellValue(total);
		    		orgCell.setCellStyle(styleCenter);
		    		int row=2;
		    		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
		    			for(SysOrg so:sysOrgList){
		    				HSSFCell Cell = rowDepts.createCell(row);
		    				Cell.setCellValue(zongjiMap.get(so.getOrgFlow())+"");
		    				Cell.setCellStyle(styleCenter);
		    				row++;
		    			}
		    			
		    		}
			    }
			    String fileName = "医师信息统计表.xls";
			    fileName = URLEncoder.encode(fileName, "UTF-8");
			    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
			    response.setContentType("application/octet-stream;charset=UTF-8");
			    wb.write(response.getOutputStream());
	}
	@Override
    public List<Map<String, Object>> statisticDocCountByOrg(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList, String graduate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		List<String> typeId=new ArrayList<>();
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Company.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Social.getId());
		if(StringUtil.isNotBlank(graduate))
		{
            typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		}
		paramMap.put("docTypes", typeId);
		return recruitExtMapper.statisticDocCountByOrg(paramMap);
	}
	@Override
    public List<Map<String, Object>> statisticAppCountByOrg(com.pinde.core.model.ResDoctorRecruit recruit, ResRec resRec, String endTime, String startTime, List<String> delTypeList, String graduate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("resRec", resRec);
		paramMap.put("endTime", endTime);
		paramMap.put("startTime", startTime);
		paramMap.put("delTypeList", delTypeList);
		List<String> typeId=new ArrayList<>();
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Company.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Social.getId());
		if(StringUtil.isNotBlank(graduate))
		{
            typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		}
		paramMap.put("docTypes", typeId);
		return recruitExtMapper.statisticAppCountByOrg(paramMap);
	}
	@Override
    public List<Map<String, Object>> statisticRealAppCount(List<String> delTypeList, com.pinde.core.model.ResDoctorRecruit recruit, String endTime, String startTime, List<String> orgFlowList, String jointFlag, String month) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("endTime", endTime);
		paramMap.put("delTypeList", delTypeList);
		paramMap.put("startTime", startTime);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("jointFlag", jointFlag);
		paramMap.put("recruit", recruit);
		paramMap.put("month", month);
		return recruitExtMapper.statisticRealAppCount(paramMap);
	}
	@Override
    public List<Map<String, Object>> statisticDocCountByOrgForTime(String jointFlag, List<String> orgFlowList, String startTime, String endTime, com.pinde.core.model.ResDoctorRecruit recruit) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("jointFlag", jointFlag);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("endTime", endTime);
		paramMap.put("startTime", startTime);
		paramMap.put("recruit", recruit);
		return recruitExtMapper.statisticDocCountByOrgForTime(paramMap);
	}
	@Override
    public List<JsDoctorInfoExt> statisticNoAppUser(com.pinde.core.model.ResDoctorRecruit recruit, ResRec resRec, List<String> delTypeList, String startDate, String endDate, String graduate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("resRec", resRec);
		paramMap.put("delTypeList", delTypeList);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<String> typeId=new ArrayList<>();
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Company.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId());
        typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Social.getId());
		if(StringUtil.isNotBlank(graduate))
		{
            typeId.add(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		}
		paramMap.put("docTypes", typeId);
		return recruitExtMapper.statisticNoAppUser(paramMap);
	}
	@Override
	public ExcelUtile importTeacherExcel(MultipartFile file) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int)file.getSize()];  
			is.read(fileData); 
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			final	String[] row = {
					"certificateNo",
					"orgName",
					"doctorName",
					"sexName",
					"technicalTitle",
					};
			return ExcelUtile.importDataExcel(ResTeacherTraining.class, 1, wb, row, new IExcelUtil<ResTeacherTraining>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<ResTeacherTraining> datas=eu.getExcelDatas();
					int succCount=0;
					int failCount=0;
					if(null!=datas&&datas.size()>0){
						for(ResTeacherTraining teacherTraining:datas)
						{
							if(StringUtil.isBlank(teacherTraining.getTechnicalTitle())&&
											StringUtil.isBlank(teacherTraining.getDoctorName())&&
											StringUtil.isBlank(teacherTraining.getCertificateNo())&&
											StringUtil.isBlank(teacherTraining.getOrgName())&&
											StringUtil.isBlank(teacherTraining.getSexName())
									){
								failCount++;
							}else{
								int result = save(teacherTraining);
                                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
									succCount++;
								}else{
									failCount++;
								}
							}
						}
					}
					eu.put("succCount",succCount);
					eu.put("loseCount",failCount);
				}
				@Override
				public String checkExcelData(ResTeacherTraining data, ExcelUtile eu) {
					return  null;
				}
			});
		} catch (Exception e) {
            logger.error("", e);
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException { 
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持 
		if (!inS.markSupported()) { 
			// 还原流信息 
			inS = new PushbackInputStream(inS); 
		} 
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}
	public static String _doubleTrans(double d){
        if((double)Math.round(d) - d == 0.0D)
            return String.valueOf((long)d);
        else
            return String.valueOf(d);
    }
	@Override
	public int save(ResTeacherTraining teacherTraining) {
		if(StringUtil.isNotBlank(teacherTraining.getRecordFlow())){
			GeneralMethod.setRecordInfo(teacherTraining, false);
			return teacherTrainingMapper.updateByPrimaryKeySelective(teacherTraining);
		}else{
			teacherTraining.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(teacherTraining, true);
			return teacherTrainingMapper.insertSelective(teacherTraining);
		}
	}
	@Override
	public List<ResTeacherTraining> searchTeacherInfo(ResTeacherTraining resTeacherTraining) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resTeacherTraining.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSpeId())){
			criteria.andSpeIdEqualTo(resTeacherTraining.getSpeId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getDeptFlow())){
			criteria.andDeptFlowEqualTo(resTeacherTraining.getDeptFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTrainingYear())){
			criteria.andTrainingYearEqualTo(resTeacherTraining.getTrainingYear());
		}
		return teacherTrainingMapper.selectByExample(example);
	}
	@Override
	public ResTeacherTraining searchTeacherInfoByPK(String recordflow) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
        ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andRecordFlowEqualTo(recordflow);
		List<ResTeacherTraining> list= teacherTrainingMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return  list.get(0);
		}
		return null;
	}

	@Override
	public int editTeacherInfo(ResTeacherTraining teacherTraining) {
		return teacherTrainingMapper.updateByPrimaryKeySelective(teacherTraining);
	}

	@Override
	public List<ResTeacherTraining> searchTeacherInfo2(ResTeacherTraining resTeacherTraining,String dataFlag) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resTeacherTraining.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSpeId())){
			criteria.andSpeIdEqualTo(resTeacherTraining.getSpeId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getDeptFlow())){
			criteria.andDeptFlowEqualTo(resTeacherTraining.getDeptFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTrainingYear())){
			criteria.andTrainingYearEqualTo(resTeacherTraining.getTrainingYear());
		}
		if("oldData".equals(dataFlag)){
			criteria.andOrgFlowIsNull();
		}else{
			criteria.andOrgFlowIsNotNull();
		}
		example.setOrderByClause("DOCTOR_NAME");
		return teacherTrainingMapper.selectByExample(example);
	}

	@Override
	public List<ResTeacherTraining> searchTeacherInfo3(ResTeacherTraining resTeacherTraining,String dataFlag) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resTeacherTraining.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSpeId())){
			criteria.andSpeIdEqualTo(resTeacherTraining.getSpeId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getDeptFlow())){
			criteria.andDeptFlowEqualTo(resTeacherTraining.getDeptFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTrainingYear())){
			criteria.andTrainingYearEqualTo(resTeacherTraining.getTrainingYear());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getUserPhone())){
			criteria.andUserPhoneEqualTo(resTeacherTraining.getUserPhone());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTeacherLevelId())){
			criteria.andTeacherLevelIdEqualTo(resTeacherTraining.getTeacherLevelId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getApplicationAuditStatus())){
			criteria.andApplicationAuditStatusEqualTo(resTeacherTraining.getApplicationAuditStatus());
		}
		if("oldData".equals(dataFlag)){
			criteria.andOrgFlowIsNull();
		}else{
			criteria.andOrgFlowIsNotNull();
		}
		criteria.andApplicationAuditStatusIsNotNull();
		return teacherTrainingMapper.selectByExample(example);
	}

	@Override
	public List<ResTeacherTraining> searchTeacherInfoByCharge(ResTeacherTraining resTeacherTraining, String dataFlag, List<String> orgFlows) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resTeacherTraining.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSpeId())){
			criteria.andSpeIdEqualTo(resTeacherTraining.getSpeId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getDeptFlow())){
			criteria.andDeptFlowEqualTo(resTeacherTraining.getDeptFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTrainingYear())){
			criteria.andTrainingYearEqualTo(resTeacherTraining.getTrainingYear());
		}
		if("oldData".equals(dataFlag)){
			criteria.andOrgFlowIsNull();
		}else{
			criteria.andOrgFlowIsNotNull();
		}
		if(null != orgFlows && orgFlows.size()>0){
			criteria.andOrgFlowIn(orgFlows);
		}
		return teacherTrainingMapper.selectByExample(example);
	}

	@Override
	public List<ResTeacherTraining> searchTeacherInfoByCharge2(ResTeacherTraining resTeacherTraining, String dataFlag, List<String> orgNames) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		ResTeacherTrainingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resTeacherTraining.getDoctorName())){
			criteria.andDoctorNameLike("%"+resTeacherTraining.getDoctorName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getCertificateNo())){
			criteria.andCertificateNoLike("%"+resTeacherTraining.getCertificateNo()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resTeacherTraining.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getOrgName())){
			criteria.andOrgNameLike("%"+resTeacherTraining.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSexName())){
			criteria.andSexNameEqualTo(resTeacherTraining.getSexName());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTechnicalTitle())){
			criteria.andTechnicalTitleLike("%"+resTeacherTraining.getTechnicalTitle()+"%");
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getSpeId())){
			criteria.andSpeIdEqualTo(resTeacherTraining.getSpeId());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getDeptFlow())){
			criteria.andDeptFlowEqualTo(resTeacherTraining.getDeptFlow());
		}
		if(StringUtil.isNotBlank(resTeacherTraining.getTrainingYear())){
			criteria.andTrainingYearEqualTo(resTeacherTraining.getTrainingYear());
		}
		if("oldData".equals(dataFlag)){
			criteria.andOrgFlowIsNull();
		}else{
			criteria.andOrgFlowIsNotNull();
		}
		if(null != orgNames && orgNames.size()>0){
			criteria.andOrgNameIn(orgNames);
		}
		return teacherTrainingMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> searchDoctorData(Map<String, Object> param) {
		return recruitExtMapper.searchDoctorData(param);
	}

	@Override
	public List<Map<String, Object>> searchDoctorDataNew(Map<String, Object> param) {
		return recruitExtMapper.searchDoctorDataNew(param);
	}

	@Override
	public List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param) {
		return recruitExtMapper.searchDoctorDataNew2(param);
	}


	@Override
	public List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param) {
		return recruitExtMapper.searchTeacherUserList(param);
	}

	@Override
	public List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param) {
		return recruitExtMapper.searchTeacherAuditList(param);
	}

	@Override
	public List<ResTeacherTraining> searchTeacherInfoByUserFlow(String recordFlow) {
		ResTeacherTrainingExample example=new ResTeacherTrainingExample();
		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorNameEqualTo(recordFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		return teacherTrainingMapper.selectByExample(example);
	}

	@Override
	public List<TeacherTrainingInfoVo> searchTeacherInfoByCondition(String doctorName, String deptFlow, String technicalPositionId, String teacherLevelId, String applicationAuditStatus, String orgFlow) {
		return userExtMapper.searchTeacherInfoByCondition(doctorName, deptFlow, technicalPositionId, teacherLevelId, applicationAuditStatus, orgFlow);
	}

	@Override
	public List<TeacherReportCountDto> countTeacherLevelByOrgFlow(String orgFlow) {
		return userExtMapper.countTeacherLevelByOrgFlow(orgFlow);
	}
}
