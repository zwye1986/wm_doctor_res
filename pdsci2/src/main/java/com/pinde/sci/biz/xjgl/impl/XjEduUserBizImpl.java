package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.xjgl.XjEduUserExtMapper;
import com.pinde.sci.dao.xjgl.XjStuFeeExtMapper;
import com.pinde.sci.enums.pub.*;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MarriageTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.enums.xjgl.XjDocCategoryEnum;
import com.pinde.sci.enums.xjgl.XjglCourseTypeEnum;
import com.pinde.sci.form.xjgl.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduUserExample.Criteria;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.xjgl.XjEduGraduateInfoExt;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import com.pinde.sci.model.xjgl.XjEduUserInfoExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjEduUserBizImpl implements IXjEduUserBiz {
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private XjEduUserExtMapper eduUserExtMapper;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IXjEduCourseBiz eduCourseBiz;
    @Autowired
    private IXjEduStudentCourseBiz eduStudentCourseBiz;
    @Autowired
    private IXjImportRecordBiz importRecordBiz;
    @Autowired
    private ResDoctorMapper doctorMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private EduLogMapper logMapper;
    @Autowired
    private IXjEduStudentCourseBiz studentCourseBiz;
    @Autowired
    private EduCourseTeachingGroupMapper teachingGroupMapper;
    @Autowired
    private PubUserGenericContentMapper genericContentMapper;
    @Autowired
    private SysCfgMapper sysCfgMapper;
    @Autowired
    private DegreeInfoMainMapper degreeInfoMapper;
    @Autowired
    private IXjDefenceBiz defenceBiz;
    @Autowired
    private NydbDefenceApplyMapper defenceApplyMapper;
    @Autowired
    private GydxjSupplementInfoMapper suppleInfoMapper;

    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }


    @Override
    public EduUser readEduUser(String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            return eduUserMapper.selectByPrimaryKey(userFlow);
        }
        return null;
    }

    @Override
    public int saveUserAndEduUser(SysUser sysUser, EduUser eduUser) {
        String userCode = sysUser.getUserCode();
        if (StringUtil.isNotBlank(userCode)) {
            sysUser.setUserCode(userCode.trim());
        }
        String userPhone = sysUser.getUserPhone();
        if (StringUtil.isNotBlank(userPhone)) {
            sysUser.setUserPhone(userPhone.trim());
        }
        String idNo = sysUser.getIdNo();
        if (StringUtil.isNotBlank(idNo)) {
            sysUser.setIdNo(idNo.trim());
        }
        String userEmail = sysUser.getUserEmail();
        if (StringUtil.isNotBlank(userEmail)) {
            sysUser.setUserEmail(userEmail.trim());
        }
        String orgFlow = sysUser.getOrgFlow();
        SysOrg sysOrg = null;
        if (StringUtil.isNotBlank(orgFlow)) {
            sysOrg = orgBiz.readSysOrg(orgFlow);
            sysUser.setOrgName(sysOrg.getOrgName());
        }
        String majorId = eduUser.getMajorId();
        if (StringUtil.isNotBlank(majorId)) {
            eduUser.setMajorName(DictTypeEnum.CourseMajor.getDictNameById(majorId));
        }
        userBiz.saveUser(sysUser);
        eduUser.setUserFlow(sysUser.getUserFlow());
        return addEduUser(eduUser);
    }

    @Override
    public String uploadImg(MultipartFile file) {
        if (file != null) {
            List<String> mimeList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))) {
                mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))) {
                suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
            }

            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
            }
            try {
                /*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "eduImages" + File.separator + dateString;
                File fileDir = new File(newDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                /*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                return "success:eduImages/" + dateString + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }


    @Override
    public List<XjEduUserExt> searchEduUserForManage(Map<String, Object> paramMap) {
        return this.eduUserExtMapper.searchEduUserForManage(paramMap);
    }

    @Override
    public List<XjEduUserExt> searchEduUserForCourseDetail(
            Map<String, Object> paramMap) {
        return this.eduUserExtMapper.searchEduUserForCourseDetail(paramMap);
    }


    @Override
    public int addEduUser(EduUser eduUser) {
        String userFlow = eduUser.getUserFlow();
        if (StringUtil.isNotBlank(userFlow)) {
            EduUser search = readEduUser(userFlow);
            if (search == null) {
                GeneralMethod.setRecordInfo(eduUser, true);
                return eduUserMapper.insert(eduUser);
            } else {
                GeneralMethod.setRecordInfo(eduUser, false);
                return eduUserMapper.updateByPrimaryKeySelective(eduUser);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public XjEduUserExt readEduUserInfo(String userFlow) {
        return eduUserExtMapper.readEduUserInfo(userFlow);
    }

//	@Override
//	public List<EduUserExt> searchEduUserByFlows(List<String> teacherFlowList) {
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//		paramMap.put("teacherFlowList", teacherFlowList);
//		return eduUserExtMapper.searchEduUserList(paramMap);
//	}


    @Override
    public int saveEduUser(EduUser eduUser) {
        if (StringUtil.isNotBlank(eduUser.getUserFlow())) {
            GeneralMethod.setRecordInfo(eduUser, false);
            return eduUserMapper.updateByPrimaryKeySelective(eduUser);
        } else {
            eduUser.setUserFlow(PkUtil.getUUID());
            return onlySaveEduUser(eduUser);
        }
    }

    private int onlySaveEduUser(EduUser eduUser) {
        GeneralMethod.setRecordInfo(eduUser, true);
        return eduUserMapper.insertSelective(eduUser);
    }

    @Override
    public List<EduUser> search(EduUser eduUser) {
        EduUserExample example = new EduUserExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(eduUser.getSid())) {
            criteria.andSidEqualTo(eduUser.getSid());
            return eduUserMapper.selectByExample(example);
        } else {
            return null;
        }
    }


    @Override
    public EduUser findByFlow(String userFlow) {
        return eduUserMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public List<EduUser> searchEduUserBySysUser(Map<String, Object> paramMap) {
        return eduUserExtMapper.searchEduUserBySysUser(paramMap);
    }

    @Override
    public List<XjEduUserInfoExt> searchEduUserInfoExtBySysUser(
            Map<String, Object> paramMap) {
        return eduUserExtMapper.searchEduUserInfoExtBySysUser(paramMap);
    }
    @Override
    public List<XjEduUserInfoExt> searchEduUserInfoExtBySysUser1(
            Map<String, Object> paramMap) {
        return eduUserExtMapper.searchEduUserInfoExtBySysUser1(paramMap);
    }
    //	@Override
//	public List<EduUser> searchEduByOrg(String orgFlow){
//		EduUserExample example = new EduUserExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(orgFlow);
//		return eduUserMapper.selectByExample(example);
//	}
    @Override
    public int importCourseFromExcel(MultipartFile file) throws Exception {
//        Map<String, String> resultMap = RegionUtil.getRefMap();
        Map<String, String> resultMap = new HashMap<>();
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb, resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private boolean isNumeric(String str) {
        boolean result = false;
        Pattern pattern = Pattern.compile("[0-9]*.?[0-9]*");
        if (pattern.matcher(str).matches()) {
            result = true;
        }
        return result;
    }

    private Map<String, Object> parseExcelToGrade(Workbook wb,String roleFlag) {
        //授课组录入权限-录入年度
        SysCfg inputSession=sysCfgMapper.selectByPrimaryKey("input_session_authority");
        SysCfg inputCourseSession=sysCfgMapper.selectByPrimaryKey("input_course_session_authority");
        String inputSessionStr="";
        String inputCourseSessionStr="";
        if(inputSession!=null){
            inputSessionStr=inputSession.getCfgValue();
        }
        if(inputCourseSession!=null){
            inputCourseSessionStr=inputCourseSession.getCfgValue();
        }
        int succCount = 0, loseCount = 0;
        String impFlow = PkUtil.getUUID();//导入批次流水号
        Map<String, Object> returnDataMap = new HashMap<String, Object>();
        List<Integer> loseList = new ArrayList<Integer>();
        Map<Integer,String> problemsMap = new HashMap<>();
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try {
                sheet = wb.getSheetAt(0);
            } catch (Exception e) {
                sheet = wb.getSheetAt(0);
            }
            //解决空行问题
            int row_num = sheet.getLastRowNum();
            if (row_num < 1) {
                throw new RuntimeException("没有数据");
            }
            String groupCourseCode = "";//确定授课组导入的课程
            String groupCourseName = "";
            if("teachingGroup".equals(roleFlag)){
                EduCourseTeachingGroupExample example = new EduCourseTeachingGroupExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andUserFlowEqualTo(GlobalContext.getCurrentUser().getUserFlow());
                List<EduCourseTeachingGroup> dataList = teachingGroupMapper.selectByExample(example);
                if(null != dataList && dataList.size() > 0){
                    groupCourseCode = dataList.get(0).getCourseCode();
                    groupCourseName = dataList.get(0).getCourseName();
                }
            }

            //获取表头
            Row titleR = sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for (int i = 0; i < cell_num; i++) {
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for (int i = 1; i <= row_num; i++) {//读所有行
                Row r = sheet.getRow(i);
                EduUser eduUser = new EduUser();
                EduCourse course = new EduCourse();
                String checkUserName="";
                String flag = GlobalConstant.UPLOAD_SUCCESSED;
                String p1 = "课程代码或名称不存在！";
                String p2 = "学号不存在！";
                String p3 = "学生未选择该课程！";
                String p4 = "获得学年，课程代码及名称不能为空！";
                String p5 = "学号和姓名不匹配！";
                String p6 = "获得学年与可录入年度不符！";
                String courseFlag = GlobalConstant.FLAG_Y;//默认录入的课程已维护
                EduStudentCourse updateAndInsertCourse = new EduStudentCourse();
                EduStudentCourse oldEduStudentCourse=new EduStudentCourse();
                EduLog eduLog = new EduLog();//修改日志对象
                for (int j = 0; j < colnames.size(); j++) {//读一行
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (cell.getCellType() == 1) {
                        value = cell.getStringCellValue().trim();
                    } else {
                        value = _doubleTrans(cell.getNumericCellValue()).trim();
                    }
                    if ("学号".equals(colnames.get(j))) {
                        eduUser.setSid(value);
                    }else if ("姓名".equals(colnames.get(j))) {
                        checkUserName=value;
                    }else if ("课程代码".equals(colnames.get(j))) {
                        course.setCourseCode(value);
                        updateAndInsertCourse.setCourseCode(value);
                    } else if ("课程名称".equals(colnames.get(j))) {
                        course.setCourseName(value);
                        updateAndInsertCourse.setCourseName(value);
                    } else if ("学时".equals(colnames.get(j))) {
                        updateAndInsertCourse.setCoursePeriod(value);
                    } else if ("学分".equals(colnames.get(j))) {
                        updateAndInsertCourse.setCourseCredit(value);
                    } else if ("成绩".equals(colnames.get(j))) {
                        if (isNumeric(value)) {
                            DecimalFormat df=new DecimalFormat("0.0");
                            updateAndInsertCourse.setCourseGrade(df.format(Double.parseDouble(value)));
                        } else {
                            List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("XjIsPassed");
                            for (SysDict dict : dictList) {
                                if (dict.getDictName().equals(value)) {
                                    if(dict.getDictName().equals("通过")){
                                        updateAndInsertCourse.setCourseGrade(GlobalConstant.FLAG_Y);
                                    }else if(dict.getDictName().equals("不通过")){
                                        updateAndInsertCourse.setCourseGrade(GlobalConstant.FLAG_N);
                                    }else if(dict.getDictName().equals("缺考")){
                                        updateAndInsertCourse.setCourseGrade("T");
                                    }
                                }
                            }
                        }
                    } else if ("考勤成绩".equals(colnames.get(j))) {
                        updateAndInsertCourse.setPacificGrade(value);
                    } else if ("考核成绩".equals(colnames.get(j))) {
                        updateAndInsertCourse.setTeamEndGrade(value);
                    } else if ("修读性质".equals(colnames.get(j))) {
                        List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("XjStudyWay");
                        for (SysDict dict : dictList) {
                            if (dict.getDictName().equals(value)) {
                                updateAndInsertCourse.setStudyWayId(dict.getDictId());
                                updateAndInsertCourse.setStudyWayName(dict.getDictName());
                            }
                        }
                    } else if ("考核方式".equals(colnames.get(j))) {
                        List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("XjEvaluationMode");
                        for (SysDict dict : dictList) {
                            if (dict.getDictName().equals(value)) {
                                updateAndInsertCourse.setAssessTypeId(dict.getDictId());
                                updateAndInsertCourse.setAssessTypeName(dict.getDictName());
                            }
                        }
                    }else if ("成绩获得方式".equals(colnames.get(j))) {
                        if ("补考".equals(value)) {
                            updateAndInsertCourse.setScoreMode("R");
                        }
                    } else if ("获得学期".equals(colnames.get(j))) {
                        List<SysDict> dictList = DictTypeEnum.sysListDictMap.get("RecruitSeason");
                        for (SysDict dict : dictList) {
                            if (dict.getDictName().equals(value)) {
                                updateAndInsertCourse.setGradeTermId(dict.getDictId());
                                updateAndInsertCourse.setGradeTermName(dict.getDictName());
                            }
                        }
                    } else if ("选课学年".equals(colnames.get(j))) {
                        if("teachingGroup".equals(roleFlag)){
                            if(GlobalConstant.FLAG_N.equals(inputSessionStr)){
                                if(StringUtil.isNotBlank(inputCourseSessionStr)&&
                                        !inputCourseSessionStr.equals(value)){
                                    flag = GlobalConstant.UPLOAD_FAIL;
                                    problemsMap.put(i+1,p6);
                                }
                            }
                        }
                        course.setCourseSession(value);
                        updateAndInsertCourse.setStudentPeriod(value);
                    } else if ("获得学年".equals(colnames.get(j))) {
                        updateAndInsertCourse.setGradeYear(value);
                    } else if("课程类型".equals(colnames.get(j))){
                        updateAndInsertCourse.setCourseTypeName(value);
                        if(XjglCourseTypeEnum.Degree.getName().equals(value)){
                            value = XjglCourseTypeEnum.Degree.getId();
                        }else if(XjglCourseTypeEnum.Optional.getName().equals(value)){
                            value = XjglCourseTypeEnum.Optional.getId();
                        }else if(XjglCourseTypeEnum.OptionalNeed.getName().equals(value)){
                            value = XjglCourseTypeEnum.OptionalNeed.getId();
                        }else if(XjglCourseTypeEnum.Public.getName().equals(value)){
                            value = XjglCourseTypeEnum.Public.getId();
                        }else if(XjglCourseTypeEnum.PublicNeed.getName().equals(value)){
                            value = XjglCourseTypeEnum.PublicNeed.getId();;
                        }
                        updateAndInsertCourse.setCourseTypeId(value);
                    } else if("课程是否已维护Y/N".equals(colnames.get(j))){
                        courseFlag = GlobalConstant.FLAG_Y.equals(value)?GlobalConstant.FLAG_Y:GlobalConstant.FLAG_N;
                    }
                }
                if("teachingGroup".equals(roleFlag)){
                    course.setCourseCode(groupCourseCode);
                    course.setCourseName(groupCourseName);
                    List<EduCourse> euList=eduCourseBiz.searchCourseList(course);
                    if(euList!=null&&euList.size()>0){
                        course=euList.get(0);
                        updateAndInsertCourse.setCourseName(course.getCourseName());
                        updateAndInsertCourse.setCourseCode(course.getCourseCode());
                        updateAndInsertCourse.setCourseTypeId(course.getCourseTypeId());
                        updateAndInsertCourse.setCourseTypeName(course.getCourseTypeName());
                    }
                }
                if (StringUtil.isBlank(course.getCourseCode()) || StringUtil.isBlank(course.getCourseName()) || StringUtil.isBlank(course.getCourseSession())){
                    flag = GlobalConstant.UPLOAD_FAIL;
                    problemsMap.put(i+1,p4);
                }
                //判断课程是否在course表这种存在（problem1）
                EduCourse exitCourse = null;
                List<EduCourse> eduCoursesList = eduCourseBiz.readCourse(course);
                if(GlobalConstant.FLAG_Y.equals(courseFlag)){
                    if (eduCoursesList != null && !eduCoursesList.isEmpty()) {
                        exitCourse = eduCoursesList.get(0);
                    }else{
                        flag = GlobalConstant.UPLOAD_FAIL;
                        problemsMap.put(i+1,p1);
                    }
                }
                //判断此人是否在eduUser中存在(p2)
                List<EduUser> eduUsersList = search(eduUser);
                EduUser user = null;
                if (eduUsersList != null && !eduUsersList.isEmpty()) {
                    user = eduUsersList.get(0);
                } else {
                    flag = GlobalConstant.UPLOAD_FAIL;
                    problemsMap.put(i+1,p2);
                }
                if (user != null) {
                    //判断此人学号与姓名是否匹配
                    SysUser tempUser=userBiz.readSysUser(user.getUserFlow());
                    if(tempUser!=null){
                        if(!tempUser.getUserName().equals(checkUserName)){
                            flag = GlobalConstant.UPLOAD_FAIL;
                            problemsMap.put(i+1,p5);
                        }
                    }
                    //判断课程之间的联系是否存在
                    EduStudentCourse testCourse = new EduStudentCourse();
                    testCourse.setUserFlow(user.getUserFlow());
                    testCourse.setCourseCode(updateAndInsertCourse.getCourseCode());
                    testCourse.setStudentPeriod(updateAndInsertCourse.getStudentPeriod());
                    List<EduStudentCourse> result = eduStudentCourseBiz.searchStudentCourseList(testCourse);
                    if (result != null && !result.isEmpty()) {
                        updateAndInsertCourse.setRecordFlow(result.get(0).getRecordFlow());//已维护过该学生该课程
                    }
                    if (GlobalConstant.UPLOAD_SUCCESSED.equals(flag)) {
                        updateAndInsertCourse.setUserFlow(user.getUserFlow());
                        if (exitCourse != null) {
                            updateAndInsertCourse.setCourseCredit(exitCourse.getCourseCredit());
                            updateAndInsertCourse.setCoursePeriod(exitCourse.getCoursePeriod());
                            updateAndInsertCourse.setCourseName(exitCourse.getCourseName());
                            updateAndInsertCourse.setCourseNameEn(exitCourse.getCourseNameEn());
                            updateAndInsertCourse.setCourseCode(exitCourse.getCourseCode());
                            updateAndInsertCourse.setCourseFlow(exitCourse.getCourseFlow());
                        } else {//不存在该课程，支持导入成绩
                            if (StringUtil.isNotBlank(updateAndInsertCourse.getCourseCode())) {
                                updateAndInsertCourse.setCourseFlow(updateAndInsertCourse.getCourseCode());
                            }
                        }
                        //操作日志
                        if(StringUtil.isNotBlank(updateAndInsertCourse.getRecordFlow())){
                            oldEduStudentCourse=studentCourseBiz.searchStudentCourse(updateAndInsertCourse.getRecordFlow());
                        }

                        SysUser sysUser= userBiz.readSysUser(updateAndInsertCourse.getUserFlow());
                        eduLog.setUserFlow(sysUser.getUserFlow());//用户流水号
                        eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
                        eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
                        eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
                        eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
                        eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
                        eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                        eduLog.setCreateTime(DateUtil.getCurrentTime());
                        eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                        eduLog.setChangeItem("成绩管理");
                        eduLog.setLogDesc(updateAndInsertCourse.getCourseName());
                        eduLog.setUserName(sysUser.getUserName());
                        eduLog.setStudyId(user.getSid());
                        eduLog.setGradeNumber(user.getPeriod());

                        updateAndInsertCourse.setImpFlow(impFlow);
                        updateAndInsertCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                        if("admin".equals(roleFlag)){
                            if(StringUtil.isNotBlank(updateAndInsertCourse.getCourseGrade())){//管理员录入成绩后，授课组就无权录入成绩
                                updateAndInsertCourse.setRoleFlag("admin");//区分授课组角色
                                updateAndInsertCourse.setSubmitFlag(GlobalConstant.FLAG_Y);//已提交
                                updateAndInsertCourse.setAuditStatusId("Passed");
                                updateAndInsertCourse.setAuditStatusName("通过");
                            }
                            eduStudentCourseBiz.save(updateAndInsertCourse,"GradeEdit");
                            succCount++;//成功的数
                        }else if(StringUtil.isNotBlank(updateAndInsertCourse.getRecordFlow()) && !"admin".equals(result.get(0).getRoleFlag())//授课组只能对学生已选课成绩打分且不能覆盖管理员已录入的成绩(recordFlow存在，result.get(0)对象必存在)
                                 && updateAndInsertCourse.getCourseCode().equals(groupCourseCode)){//且为当前授课组课程
                            eduStudentCourseBiz.save(updateAndInsertCourse,"GradeEdit");
                            succCount++;//成功的数
                        }else{
                            problemsMap.put(i+1,p3);
                            flag = GlobalConstant.UPLOAD_FAIL;
                        }
                        if(flag.equals(GlobalConstant.UPLOAD_FAIL)){//日志记录需在成绩导入成功前提下
                            //成绩是否修改
                            if(StringUtil.isNotBlank(updateAndInsertCourse.getRecordFlow())){
                                if(StringUtil.isBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isNotBlank(updateAndInsertCourse.getCourseGrade())){
                                    eduLog.setLogFlow(PkUtil.getUUID());
                                    eduLog.setNewData(updateAndInsertCourse.getCourseGrade());
                                    logMapper.insertSelective(eduLog);
                                }else if(StringUtil.isNotBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isBlank(updateAndInsertCourse.getCourseGrade())){
                                    eduLog.setLogFlow(PkUtil.getUUID());
                                    eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
                                    logMapper.insertSelective(eduLog);
                                }else if(StringUtil.isNotBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isNotBlank(updateAndInsertCourse.getCourseGrade())&&!oldEduStudentCourse.getCourseGrade().equals(updateAndInsertCourse.getCourseGrade())){
                                    eduLog.setLogFlow(PkUtil.getUUID());
                                    eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
                                    eduLog.setNewData(updateAndInsertCourse.getCourseGrade());
                                    logMapper.insertSelective(eduLog);
                                }
                            }else{
                                eduLog.setLogFlow(PkUtil.getUUID());
                                eduLog.setNewData(updateAndInsertCourse.getCourseGrade());
                                logMapper.insertSelective(eduLog);
                            }
                        }
                    }
                }
                if (GlobalConstant.UPLOAD_FAIL.equals(flag)) {
                    loseCount++;//失败的数
                    int hanghao = i + 1;
                    loseList.add(hanghao);
                    continue;
                }
            }
            if (succCount > 0) {
                PubImportRecord importRecord = new PubImportRecord();
                importRecord.setImpFlow(impFlow);
                if("admin".equals(roleFlag)){
                    importRecord.setRoleFlag("admin");
                }else{
                    importRecord.setRoleFlag("teachingGroup");
                }
                importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
                importRecord.setImpTypeName(XjImpTypeEnum.EduStudentCourse.getName());
                importRecord.setImpTime(DateUtil.getCurrDateTime());
                importRecord.setImpNum(String.valueOf(succCount));
                importRecordBiz.addRecord(importRecord);
            }
            returnDataMap.put("succCount", succCount);
            returnDataMap.put("loseCount", loseCount);
            returnDataMap.put("loseList", loseList);
            returnDataMap.put("problemsMap", problemsMap);
        }
        return returnDataMap;
    }

    private int parseExcel(Workbook wb, Map<String, String> resultMap) throws Exception {
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try {
                sheet = wb.getSheetAt(0);
            } catch (Exception e) {
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR = sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for (int i = 0; i < cell_num; i++) {
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title.trim());
            }
            if (row_num < 1) {
                throw new RuntimeException("没有数据");
            }
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                if(null == r){
                    continue;
                }
                SysUser sysUser = new SysUser();
                EduUser eduUser = new EduUser();
                ResDoctor resDoctor = new ResDoctor();
                XjEduUserExtInfoForm euInfo = new XjEduUserExtInfoForm();
                XjEduUserForm eduUserForm = new XjEduUserForm();
                eduUserForm.setEduUser(eduUser);
                eduUserForm.setSysUser(sysUser);
                eduUserForm.setResDoctor(resDoctor);
                String sid;//学号
                String userName;//姓名
                String nameSpell;//姓名拼音
                String cretTypeId;//证件类型码
                String idNo;//证件号码
                String userBirthday;//出生日期
                String sexId;//性别码
                String nationId;//民族码
                String period;//入学年级
                String className;//班级
                String majorId;//专业代码
                String majorName;//专业名称
                String is5plus3;//是否5+3培养模式
                String trainOrgName;//学位分委员会
                String orgName;//培养单位名称
                String researchDirName;//研究方向
                String trainTypeName;//培养层次
                String trainCategoryName;//培养类型
                String firstTeacher;//导师一
                String secondTeacher;//导师二
                String schoolRollStatusName;//学籍状态
                String xjRegDate;//学籍注册时间
                String isReported;//是否报到
                String reportedDate;//报到时间
                String atSchoolStatusName;//在校状态
                String studyFormName;//学习形式
                String isRecommendName;//是否推免生
                String isExceptionalName;//是否破格
                String isOutOfSchool;//是否休学
                String outOfSchoolDate;//休学日期
                String isBackToSchool;//是否复学
                String backToSchoolDate;//复学日期
                String dropOutSchoolDate;//退学时间
                String studentCode;//考生编号
                String studentSourceName;//考生来源
                String recruitSeasonName;//招生季节
                String recruitType;//招录方式
                String admitTypeName;//录取类别
                String foreignLanguageName;//外国语名称
                String foreignLanguageScore;//外国语成绩
                String politicalTheoryName;//政治理论
                String politicalTheoryScore;//政治理论成绩
                String firstSubjectCode;//业务课一码
                String firstSubjectName;//业务课一
                String firstSubjectScore;//业务课一成绩
                String secondSubjectCode;//业务课二码
                String secondSubjectName;//业务课二
                String secondSubjectScore;//业务课二成绩
                String firstAddExamName;//加试科一
                String firstAddExamScore;//加试科一成绩
                String secondAddExamName;//加试科二
                String secondAddExamScore;//加试科二成绩
                String reexamineScore;//复试成绩
                String totalPointsScore;//总分
                String nativePlaceAreaId;//籍贯
                String maritalId;//婚姻状况id
                String bearName;//生育状况
                String politicsStatusId;//政治面貌码
                String joinOrgTime;//入党（团）时间
                String isRelationInto;//党团关系是否转入
                String joinTime;//转入年月日
                String domicilePlaceId;//入学前户口所在地码
                String domicilePlaceAddress;//入学前户口所在地详细地址
                String hkInSchool;//户口是否需要迁入我校
                String hkChangeNo;//户口迁移证编号;
                String oldOrgName;//原学习或工作单位
                String oldFileLocationOrg;//原档案所在单位
                String oldFileLocationOrgCode;//原档案所在单位邮编
                String recordLocationId;//档案所在地码
                String nowResideAddress ;//现家庭地址
                String linkMan;//紧急联系人姓名
                String telephone;//紧急联系人电话
                String isDoctorQuaCert;//是否有医师资格证
                String codeDoctorQuaCert;//资格证编号
                String isMedicalPractitioner;//是否有职业医师执照
                String codeMedicalPractitioner;//执照编号
                String isStay;//是否住宿
                String roomNumStay;//宿舍号
                String userPhone;//本人手机号
                String userEmail;//电子邮箱
                String weiXinId;//微信号
                String userQq;//QQ号
                String height;//身高
                String bloodType;//血型
                String ybCardNo;//医保卡号
                String foreignLanguageLevel ;//外语水平
                String mandarinLevel ;//普通话水平
                String computerLevel;//计算机水平
                String studentSourceArea;//生源省市
                String schoolSystem;//学制
                String dormitoryAdd;//宿舍地址
                String homePhone;// 家庭电话
                String mateName; //配偶姓名
                String mateIdNo;//配偶身份证
                String spouseUnit;//配偶工作单位
                String speciality;//特长
                String mainResume;//本人主要简历
                String reAndPuStatusContent;//入学前奖惩情况
                String scientificTogether;//曾担任过合作教学工作和进行何种科研工作
                String remark;//备注
                String accountNum;//缴费账号
                String tuitionFee;//学费总额
                String paytuitionFee;//已缴纳学费
                String arrearageFee;//欠缴学费
                String dormitoryFee;//住宿费
                String payDormitoryFee;//已缴纳住宿费
                String arrearageDormitoryFee;//欠缴纳住宿费
                String firstEducationContentName;//第一学历
                String preGraduation;//前置学历
                String directionalOrgName;//定向培养单位
                String bkgain;//是否获得本科学历
                String underStudyForm;//获得本科学历的学习形式
                String underGraduateTime;//本科毕业年月
                String underDiplomaCode; //本科毕业证书编号
                String underGraduateOrgName;//本科毕业单位名称
                String underGraduateMajor;//本科毕业专业名称
                String xsgain;//是否获得学士学位
                String underAwardDegreeTime;//获得学士学位年月
                String underDegreeCertCode;//学士学位证书编号
                String underAwardDegreeOrg;//获学士学位单位名称
                String underMajor;//获学士学位专业名称
                String gotBachelorCertSubject;//获得学士学位学科门类
                String gotMasterCertSpe;//获得硕士学位专业
                String code;//最后学位证编号
                String ssyjsgain;//是否获得硕士研究生学历
                String masterGraduateTime;//硕士研究生毕业年月
                String masterDiplomaCode;//硕士研究生毕业证编号
                String masterUnitName;//硕士研究生毕业单位名称
                String masterGraduateMajor;//硕士研究生毕业专业名称
                String ssxwgain;//是否获得硕士学位
                String masterStudyForm;//获得硕士学位的学习形式
                String masterAwardDegreeTime;//获得硕士学位年月
                String masterDegreeCertCode;//硕士学位证书编号
                String masterAwardDegreeOrg;//获得硕士学位单位名称
                String masterSubject;//获得硕士学位学科门类
                String planGraduateTime;//预毕业时间
                String graduateFlag;//是否毕业
                String graduateTime;//毕业时间
                String diplomaCode;//毕业证书编号
                String awardDegreeFlag;//是否授予学位
                String awardSubjectCategory;//授予学科门类
                String awardDegreeCategoryName;//授予学位类别
                String awardDegreeTime;//授予学位时间
                String awardDegreeCertCode;//授予学位证书编号
                String paperTitle;//论文题目
                String paperSource;//论文选题来源
                String paperKey;//论文关键词
                String paperType;//论文类型
                String degreeDirection;//获学位后去向
                String unitNature;//就业单位性质
                String unitAddress;//就业单位所在省（直辖市）
                String workNature;//工作性质
                String unitName;//就业单位名称
                String unitLinkMan;//就业单位联系人
                String unitLinkManPhone;//就业单位联系人电话
                String unitLinkManEmail;//就业单位联系人邮箱

                Map<String, SysOrg> orgNameMap = getOrgByNameMap();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (cell == null || StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (cell.getCellType() == 1) {
                        value = cell.getStringCellValue().trim();
                    } else {
                        value = _doubleTrans(cell.getNumericCellValue()).trim();
                    }
                    if ("学号".equals(colnames.get(j))) {
                        sid = value;
                        eduUser.setSid(sid);
                        sysUser.setUserCode(sid);
                        resDoctor.setDoctorCode(sid);
                    } else if ("姓名".equals(colnames.get(j))) {
                        userName = value;
                        sysUser.setUserName(userName);
                        resDoctor.setDoctorName(userName);
                    } else if ("姓名拼音".equals(colnames.get(j))) {
                        nameSpell = value;
                        eduUser.setNameSpell(nameSpell);
                    } else if ("证件类型".equals(colnames.get(j))) {
                        if(value.equals("身份证")){
                            value = "01";
                        }else if(value.equals("军官证")){
                            value = "02";
                        }else if(value.equals("港澳台身份证")){
                            value = "03";
                        }else if(value.equals("华侨身份证")){
                            value = "04";
                        }else if(value.equals("护照")){
                            value = "05";
                        }
                        cretTypeId = value;
                        sysUser.setCretTypeId(cretTypeId);
                        sysUser.setCretTypeName(CertificateTypeEnum.getNameById(cretTypeId));
                    } else if ("证件号码".equals(colnames.get(j))) {
                        idNo = value;
                        sysUser.setIdNo(idNo);
                    } else if ("出生日期".equals(colnames.get(j))) {
                        userBirthday = value;
                        sysUser.setUserBirthday(_dateTypeTrans(userBirthday));
                    } else if ("性别".equals(colnames.get(j))) {
                        if(value.equals("通用")){
                            value = "Unknown";
                        }else if(value.equals("男")){
                            value = "Man";
                        }else if(value.equals("女")){
                            value = "Woman";
                        }
                        sexId = value;
                        sysUser.setSexId(sexId);
                        sysUser.setSexName(UserSexEnum.getNameById(sysUser.getSexId()));
                    } else if ("民族".equals(colnames.get(j))) {//仅支持民族码导入
                        nationId = value;
                        sysUser.setNationId(nationId);
                        sysUser.setNationName(UserNationEnum.getNameById(nationId));
                    }else if ("入学年级".equals(colnames.get(j))) {
                        period = value;
                        eduUser.setPeriod(period);
                        resDoctor.setSessionNumber(period);
                    } else if ("班级".equals(colnames.get(j))) {
                        className = value;
                        eduUser.setClassName(className);
                        eduUser.setClassId(getDictId(className, "XjClass"));
                    } else if ("专业代码".equals(colnames.get(j))) {
                        majorId = value;
                        eduUser.setMajorId(majorId);
                    } else if ("专业名称".equals(colnames.get(j))) {
                        majorName = value;
                        eduUser.setMajorName(majorName);
                    } else if ("是否5+3培养模式".equals(colnames.get(j))) {
                        is5plus3 = value;
                        eduUser.setIs5plus3("是".equals(is5plus3) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("学位分委员会".equals(colnames.get(j))) {
                        trainOrgName = value;
                        eduUser.setTrainOrgName(trainOrgName);
                        sysUser.setDeptName(trainOrgName);
                        String deptFlow = getDeptFlow(trainOrgName, GlobalContext.getCurrentUser().getOrgFlow());
                        eduUser.setTrainOrgId(deptFlow);
                        sysUser.setDeptFlow(deptFlow);
                    } else if ("培养单位".equals(colnames.get(j))) {
                        orgName = value;
                        if (StringUtil.isBlank(resDoctor.getOrgName())) {
                            resDoctor.setOrgName(orgName);
                            SysOrg org = orgNameMap.get(orgName);
                            if (null != org) {
                                resDoctor.setOrgFlow(org.getOrgFlow());
                            }
                        }
                    } else if ("研究方向".equals(colnames.get(j))) {
                        researchDirName = value;
                        eduUser.setResearchDirName(researchDirName);
                        resDoctor.setResearchDirection(researchDirName);
                    } else if ("培养层次".equals(colnames.get(j))) {
                        trainTypeName = value;
                        eduUser.setTrainTypeName(trainTypeName);
                        eduUser.setTrainTypeId(getDictId(trainTypeName, "TrainType"));
                    } else if ("培养类型".equals(colnames.get(j))) {
                        trainCategoryName = value;
                        eduUser.setTrainCategoryName(trainCategoryName);
                        eduUser.setTrainCategoryId(getDictId(trainCategoryName, "TrainCategory"));
                    } else if ("导师一".equals(colnames.get(j))) {
                        firstTeacher = value;
                        eduUser.setFirstTeacher(firstTeacher);
                    } else if ("导师二".equals(colnames.get(j))) {
                        secondTeacher = value;
                        eduUser.setSecondTeacher(secondTeacher);
                    } else if ("学籍状态".equals(colnames.get(j))) {
                        schoolRollStatusName = value;
                        eduUser.setSchoolRollStatusName(schoolRollStatusName);
                        eduUser.setSchoolRollStatusId(getDictId(schoolRollStatusName, "SchoolRollStatus"));
                    } else if ("学籍注册时间".equals(colnames.get(j))) {
                        xjRegDate = value;
                        euInfo.setXjRegDate(_dateTypeTrans(xjRegDate));
                    } else if ("是否报到".equals(colnames.get(j))) {
                        isReported = value;
                        eduUser.setIsReported("是".equals(isReported) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("报到时间".equals(colnames.get(j))) {
                        reportedDate = value;
                        euInfo.setReportedDate(_dateTypeTrans(reportedDate));
                    } else if ("在校状态".equals(colnames.get(j))) {
                        atSchoolStatusName = value;
                        eduUser.setAtSchoolStatusName(atSchoolStatusName);
                        eduUser.setAtSchoolStatusId(getDictId(atSchoolStatusName, "AtSchoolStatus"));
                    } else if ("学习形式".equals(colnames.get(j))) {
                        studyFormName = value;
                        eduUser.setStudyFormName(studyFormName);
                        eduUser.setStudyFormId(getDictId(studyFormName, "StudyForm"));
                    } else if ("是否推免生".equals(colnames.get(j))) {
                        isRecommendName = value;
                        eduUser.setIsRecommendName(isRecommendName);
                        eduUser.setIsRecommendId(getDictId(isRecommendName, "IsRecommend"));
                    } else if ("课程班入班时间".equals(colnames.get(j))) {
                        euInfo.setInClassTime(_dateTypeTrans(value));
                    } else if ("课程班结业时间".equals(colnames.get(j))) {
                        euInfo.setClassEndTime(_dateTypeTrans(value));
                    } else if ("结业证书编号".equals(colnames.get(j))) {
                        euInfo.setClassCertNo(value);
                    } else if ("导师接收时间".equals(colnames.get(j))) {
                        euInfo.setReceiveTime(_dateTypeTrans(value));
                    } else if ("是否破格".equals(colnames.get(j))) {
                        isExceptionalName = value;
                        eduUser.setIsExceptionalName(isExceptionalName);
                        eduUser.setIsExceptionalId(getDictId(isExceptionalName, "IsExceptional"));
                    } else if ("是否休学".equals(colnames.get(j))) {
                        isOutOfSchool = value;
                        eduUser.setIsOutOfSchool("是".equals(isOutOfSchool) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("休学日期".equals(colnames.get(j))) {
                        outOfSchoolDate = value;
                        euInfo.setOutOfSchoolDate(_dateTypeTrans(outOfSchoolDate));
                    } else if ("是否复学".equals(colnames.get(j))) {
                        isBackToSchool = value;
                        eduUser.setIsBackToSchool("是".equals(isBackToSchool) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("复学日期".equals(colnames.get(j))) {
                        backToSchoolDate = value;
                        euInfo.setBackToSchoolDate(_dateTypeTrans(backToSchoolDate));
                    } else if ("退学时间".equals(colnames.get(j))) {
                        dropOutSchoolDate = value;
                        euInfo.setDropOutSchoolDate(_dateTypeTrans(dropOutSchoolDate));
                    } else if ("考生编号".equals(colnames.get(j))) {
                        studentCode = value;
                        eduUser.setStudentCode(studentCode);
                    } else if ("考生来源".equals(colnames.get(j))) {
                        studentSourceName = value;
                        eduUser.setStudentSourceName(studentSourceName);
                        eduUser.setStudentSourceId(getDictId(studentSourceName, "StudentSource"));
                    } else if ("招生季节".equals(colnames.get(j))) {
                        recruitSeasonName = value;
                        eduUser.setRecruitSeasonName(recruitSeasonName);
                        eduUser.setRecruitSeasonId(getDictId(recruitSeasonName, "RecruitSeason"));
                    } else if ("招录方式".equals(colnames.get(j))) {
                        recruitType = value;
                        euInfo.setRecruitType(getDictId(recruitType, "RecruitType"));
                    } else if ("录取类别".equals(colnames.get(j))) {
                        admitTypeName = value;
                        eduUser.setAdmitTypeName(admitTypeName);
                        eduUser.setAdmitTypeId(getDictId(admitTypeName, "AdmitType"));
                    } else if ("外国语名称".equals(colnames.get(j))) {
                        foreignLanguageName = value;
                        euInfo.setForeignLanguageName(foreignLanguageName);
                    } else if ("外国语成绩".equals(colnames.get(j))) {
                        foreignLanguageScore = value;
                        euInfo.setForeignLanguageScore(foreignLanguageScore);
                    } else if ("政治理论".equals(colnames.get(j))) {
                        politicalTheoryName = value;
                        euInfo.setPoliticalTheoryName(politicalTheoryName);
                    } else if ("政治理论成绩".equals(colnames.get(j))) {
                        politicalTheoryScore = value;
                        euInfo.setPoliticalTheoryScore(politicalTheoryScore);
                    } else if ("业务课一".equals(colnames.get(j))) {
                        firstSubjectName = value;
                        euInfo.setFirstSubjectName(firstSubjectName);
                    } else if ("业务课一成绩".equals(colnames.get(j))) {
                        firstSubjectScore = value;
                        euInfo.setFirstSubjectScore(firstSubjectScore);
                    } else if ("业务课二".equals(colnames.get(j))) {
                        secondSubjectName = value;
                        euInfo.setSecondSubjectName(secondSubjectName);
                    } else if ("业务课二成绩".equals(colnames.get(j))) {
                        secondSubjectScore = value;
                        euInfo.setSecondSubjectScore(secondSubjectScore);
                    } else if ("加试科一".equals(colnames.get(j))) {
                        firstAddExamName = value;
                        euInfo.setFirstAddExamName(firstAddExamName);
                    } else if ("加试科一成绩".equals(colnames.get(j))) {
                        firstAddExamScore = value;
                        euInfo.setFirstAddExamScore(firstAddExamScore);
                    } else if ("加试科二".equals(colnames.get(j))) {
                        secondAddExamName = value;
                        euInfo.setSecondAddExamName(secondAddExamName);
                    } else if ("加试科二成绩".equals(colnames.get(j))) {
                        secondAddExamScore = value;
                        euInfo.setSecondAddExamScore(secondAddExamScore);
                    } else if ("复试成绩".equals(colnames.get(j))) {
                        reexamineScore = value;
                        euInfo.setReexamineScore(reexamineScore);
                    } else if ("总分".equals(colnames.get(j))) {
                        totalPointsScore = value;
                        euInfo.setTotalPointsScore(totalPointsScore);
                    } else if ("籍贯".equals(colnames.get(j))) {
                        nativePlaceAreaId = value;
                        sysUser.setNativePlaceAreaId(nativePlaceAreaId);
                        if (resultMap.containsKey(nativePlaceAreaId)) {
                            String[] addString = resultMap.get(nativePlaceAreaId).split("_");
                            if (addString != null && addString.length == 5) {
                                sysUser.setNativePlaceAreaName(addString[0]);
                                sysUser.setNativePlaceCityId(addString[1]);
                                sysUser.setNativePlaceCityName(addString[2]);
                                sysUser.setNativePlaceProvId(addString[3]);
                                sysUser.setNativePlaceProvName(addString[4]);
                            }
                        }
                    } else if ("婚姻状况".equals(colnames.get(j))) {
                        if(value.equals("未婚")){
                            value = "1";
                        }else if(value.equals("已婚")){
                            value = "2";
                        }else if(value.equals("丧偶")){
                            value = "3";
                        }else if(value.equals("离婚")){
                            value = "4";
                        }else if(value.equals("其他")){
                            value = "9";
                        }
                        maritalId = value;
                        sysUser.setMaritalId(maritalId);
                        sysUser.setMaritalName(MarriageTypeEnum.getNameById(maritalId));
                    }else if ("生育状况".equals(colnames.get(j))) {
                        bearName = value;
                        sysUser.setBearName(bearName);
                    }
                    else if ("政治面貌".equals(colnames.get(j))) {//仅支持政治面貌码导入
                        politicsStatusId = value;
                        sysUser.setPoliticsStatusId(politicsStatusId);
                        sysUser.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(politicsStatusId));
                    } else if ("入党（团）时间".equals(colnames.get(j))) {
                        joinOrgTime = value;
                        euInfo.setJoinOrgTime(_dateTypeTrans(joinOrgTime));
                    }else if ("党团关系是否转入".equals(colnames.get(j))) {
                        isRelationInto = value;
                        euInfo.setIsRelationInto("是".equals(isRelationInto) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("转入年月日".equals(colnames.get(j))) {
                        joinTime = value;
                        euInfo.setJoinTime(_dateTypeTrans(joinTime));
                    } else if ("所属党支部".equals(colnames.get(j))) {
                        sysUser.setPartyBranchName(value);
                        sysUser.setPartyBranchId(getDictId(value, "XjPartyBranch"));
                    } else if ("入学前户口所在地".equals(colnames.get(j))) {
                        domicilePlaceId = value;
                        if (resultMap.containsKey(domicilePlaceId)) {
                            String[] addString = resultMap.get(domicilePlaceId).split("_");
                            if (addString != null && addString.length == 5) {
                                sysUser.setDomicilePlaceId(addString[3] + "," + addString[1] + "," + domicilePlaceId);
                                sysUser.setDomicilePlaceName(addString[4] + "," + addString[2] + "," + addString[0]);
                            }
                        }else {
                            sysUser.setDomicilePlaceName(domicilePlaceId);
                        }
                    } else if ("入学前户口所在地详细地址".equals(colnames.get(j))) {
                        domicilePlaceAddress = value;
                        sysUser.setDomicilePlaceAddress(domicilePlaceAddress);
                    }else if ("户口是否需要迁入我校".equals(colnames.get(j))) {
                        hkInSchool = value;
                        euInfo.setHkInSchool("是".equals(hkInSchool) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    } else if ("户口迁移证编号".equals(colnames.get(j))) {
                        hkChangeNo = value;
                        euInfo.setHkChangeNo(hkChangeNo);
                    } else if ("原学习或工作单位".equals(colnames.get(j))) {
                        oldOrgName = value;
                        euInfo.setOldOrgName(oldOrgName);
                    } else if ("原档案所在单位".equals(colnames.get(j))) {
                        oldFileLocationOrg = value;
                        euInfo.setOldFileLocationOrg(oldFileLocationOrg);
                    }else if ("原档案所在单位邮编".equals(colnames.get(j))) {
                        oldFileLocationOrgCode = value;
                        euInfo.setOldFileLocationOrgCode(oldFileLocationOrgCode);
                    } else if ("档案所在地码".equals(colnames.get(j))) {
                        recordLocationId = value;
                        if (resultMap.containsKey(recordLocationId)) {
                            String[] addString = resultMap.get(recordLocationId).split("_");
                            if (addString != null && addString.length == 5) {
                                eduUser.setRecordLocationId(addString[3] + "," + addString[1] + "," + recordLocationId);
                                eduUser.setRecordLocationName(addString[4] + "," + addString[2] + "," + addString[0]);
                            }
                        }
                    }else if ("现家庭地址".equals(colnames.get(j))) {
                        nowResideAddress = value;
                        euInfo.setNowResideAddress(nowResideAddress);
                    }else if ("紧急联系人姓名".equals(colnames.get(j))) {
                        linkMan = value;
                        euInfo.setLinkMan(linkMan);
                    }else if ("紧急联系人电话".equals(colnames.get(j))) {
                        telephone = value;
                        euInfo.setTelephone(telephone);
                    }else if ("是否有医师资格证".equals(colnames.get(j))) {
                        isDoctorQuaCert = value;
                        euInfo.setIsDoctorQuaCert("是".equals(isDoctorQuaCert) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    }else if ("资格证编号".equals(colnames.get(j))) {
                        codeDoctorQuaCert = value;
                        euInfo.setCodeDoctorQuaCert(codeDoctorQuaCert);
                    }else if ("是否有职业医师执照".equals(colnames.get(j))) {
                        isMedicalPractitioner = value;
                        euInfo.setIsMedicalPractitioner("是".equals(isMedicalPractitioner) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    }else if ("执照编号".equals(colnames.get(j))) {
                        codeMedicalPractitioner = value;
                        euInfo.setCodeMedicalPractitioner(codeMedicalPractitioner);
                    } else if ("是否住宿".equals(colnames.get(j))) {
                        isStay = value;
                        euInfo.setIsStay("是".equals(isStay) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    }else if ("宿舍号".equals(colnames.get(j))) {
                        roomNumStay = value;
                        euInfo.setRoomNumStay(roomNumStay);
                    } else if ("本人手机号码".equals(colnames.get(j))) {
                        userPhone = value;
                        sysUser.setUserPhone(userPhone);
                    } else if ("电子邮箱".equals(colnames.get(j))) {
                        userEmail = value;
                        sysUser.setUserEmail(userEmail);
                    } else if ("微信号".equals(colnames.get(j))) {
                        weiXinId = value;
                        sysUser.setWeiXinName(weiXinId);
                    } else if ("QQ号".equals(colnames.get(j))) {
                        userQq = value;
                        sysUser.setUserQq(userQq);
                    } else if ("身高".equals(colnames.get(j))) {
                        height = value;
                        euInfo.setHeight(height);
                    } else if ("血型".equals(colnames.get(j))) {
                        bloodType = value;
                        euInfo.setBloodType(bloodType);
                    } else if ("医保卡号".equals(colnames.get(j))) {
                        ybCardNo = value;
                        euInfo.setYbCardNo(ybCardNo);
                    }else if ("外语水平".equals(colnames.get(j))) {
                        foreignLanguageLevel = value;
                        euInfo.setForeignLanguageLevel(foreignLanguageLevel);
                    }else if ("普通话水平".equals(colnames.get(j))) {
                        mandarinLevel = value;
                        euInfo.setMandarinLevel(mandarinLevel);
                    }else if ("计算机水平".equals(colnames.get(j))) {
                        computerLevel = value;
                        euInfo.setComputerLevel(computerLevel);
                    }else if ("生源省市".equals(colnames.get(j))) {
                        studentSourceArea = value;
                        euInfo.setStudentSourceArea(studentSourceArea);
                    }else if ("学制".equals(colnames.get(j))) {
                        schoolSystem = value;
                        euInfo.setSchoolSystem(schoolSystem);
                    }else if ("宿舍地址".equals(colnames.get(j))) {
                        dormitoryAdd = value;
                        euInfo.setDormitoryAdd(dormitoryAdd);
                    }else if ("家庭电话".equals(colnames.get(j))) {
                        homePhone = value;
                        euInfo.setHomePhone(homePhone);
                    }else if ("配偶姓名".equals(colnames.get(j))) {
                        mateName = value;
                        euInfo.setMateName(mateName);
                    }else if ("配偶身份证".equals(colnames.get(j))) {
                        mateIdNo = value;
                        euInfo.setMateIdNo(mateIdNo);
                    }else if ("配偶工作单位".equals(colnames.get(j))) {
                        spouseUnit = value;
                        euInfo.setSpouseUnit(spouseUnit);
                    }else if ("特长".equals(colnames.get(j))) {
                        speciality = value;
                        euInfo.setSpeciality(speciality);
                    }else if ("本人主要简历".equals(colnames.get(j))) {
                        mainResume = value;
                        euInfo.setMainResume(mainResume);
                    } else if ("入学前奖惩情况".equals(colnames.get(j))) {
                        reAndPuStatusContent = value;
                        euInfo.setReAndPuStatusContent(reAndPuStatusContent);
                    } else if ("曾担任过合作教学工作和进行何种科研工作".equals(colnames.get(j))) {
                        scientificTogether = value;
                        euInfo.setScientificTogether(scientificTogether);
                    } else if ("备注".equals(colnames.get(j))) {
                        remark = value;
                        euInfo.setRemark(remark);
                    } else if ("缴费账号".equals(colnames.get(j))) {
                        accountNum = value;
                        euInfo.setAccountNum(accountNum);
                    } else if ("学费总额".equals(colnames.get(j))) {
                        tuitionFee = value;
                        euInfo.setTuitionFee(tuitionFee);
                    } else if ("已缴纳学费".equals(colnames.get(j))) {
                        paytuitionFee = value;
                        euInfo.setPaytuitionFee(paytuitionFee);
                    } else if ("欠缴纳学费".equals(colnames.get(j))) {
                        arrearageFee = value;
                        euInfo.setArrearageFee(arrearageFee);
                    } else if ("住宿费".equals(colnames.get(j))) {
                        dormitoryFee = value;
                        euInfo.setDormitoryFee(dormitoryFee);
                    } else if ("已缴纳住宿费".equals(colnames.get(j))) {
                        payDormitoryFee = value;
                        euInfo.setPayDormitoryFee(payDormitoryFee);
                    } else if ("欠缴纳住宿费".equals(colnames.get(j))) {
                        arrearageDormitoryFee = value;
                        euInfo.setArrearageDormitoryFee(arrearageDormitoryFee);
                    } else if ("档案是否已到齐".equals(colnames.get(j))) {
                        euInfo.setIsArchivesComplete("是".equals(value) ? GlobalConstant.FLAG_Y : ("否".equals(value) ? GlobalConstant.FLAG_N:""));
                    } else if ("学费是否已缴清".equals(colnames.get(j))) {
                        euInfo.setArrearageDormitoryFee("是".equals(value) ? GlobalConstant.FLAG_Y : ("否".equals(value) ? GlobalConstant.FLAG_N:""));
                    } else if ("第一学历".equals(colnames.get(j))) {
                        firstEducationContentName = value;
                        euInfo.setFirstEducationContentName(firstEducationContentName);
                        euInfo.setFirstEducationContentId(getDictId(firstEducationContentName, "FirstEducation"));
                    } else if ("前置学历".equals(colnames.get(j))) {
                        preGraduation = value;
                        euInfo.setPreGraduation(preGraduation);
                    } else if ("定向培养单位".equals(colnames.get(j))) {
                        directionalOrgName = value;
                        euInfo.setDirectionalOrgName(directionalOrgName);
                    } else if ("是否获得本科学历".equals(colnames.get(j))) {
                        bkgain = value;
                        euInfo.setBkgain("是".equals(bkgain) ? "1" : "2");
                    } else if ("获得本科学历的学习形式".equals(colnames.get(j))) {
                        underStudyForm = value;
                        euInfo.setUnderStudyForm(underStudyForm);
                    } else if ("本科毕业年月".equals(colnames.get(j))) {
                        underGraduateTime = value;
                        euInfo.setUnderGraduateTime(_dateTypeTrans(underGraduateTime));
                    } else if ("本科毕业证书编号".equals(colnames.get(j))) {
                        underDiplomaCode = value;
                        euInfo.setUnderDiplomaCode(underDiplomaCode);
                    } else if ("本科毕业单位名称".equals(colnames.get(j))) {
                        underGraduateOrgName = value;
                        euInfo.setUnderGraduateOrgName(underGraduateOrgName);
                    } else if ("本科毕业专业名称".equals(colnames.get(j))) {
                        underGraduateMajor = value;
                        euInfo.setUnderGraduateMajor(underGraduateMajor);
                    } else if ("是否获得学士学位".equals(colnames.get(j))) {
                        xsgain = value;
                        euInfo.setXsgain("是".equals(xsgain) ? "1" : "2");
                    } else if ("获得学士学位年月".equals(colnames.get(j))) {
                        underAwardDegreeTime = value;
                        euInfo.setUnderAwardDegreeTime(_dateTypeTrans(underAwardDegreeTime));
                    } else if ("学士学位证书编号".equals(colnames.get(j))) {
                        underDegreeCertCode = value;
                        euInfo.setUnderDegreeCertCode(underDegreeCertCode);
                    } else if ("获学士学位单位名称".equals(colnames.get(j))) {
                        underAwardDegreeOrg = value;
                        euInfo.setUnderAwardDegreeOrg(underAwardDegreeOrg);
                    } else if ("获学士学位专业名称".equals(colnames.get(j))) {
                        underMajor = value;
                        euInfo.setUnderMajor(underMajor);
                    } else if ("获得学士学位学科门类".equals(colnames.get(j))) {
                        gotBachelorCertSubject = value;
                        euInfo.setGotBachelorCertSubject(gotBachelorCertSubject);
                    } else if ("获得硕士学位专业".equals(colnames.get(j))) {
                        gotMasterCertSpe = value;
                        euInfo.setGotMasterCertSpe(gotMasterCertSpe);
                    } else if ("最后学位证编号".equals(colnames.get(j))) {
                        code = value;
                        euInfo.setCode(code);
                    } else if ("是否获得硕士研究生学历".equals(colnames.get(j))) {
                        ssyjsgain = value;
                        euInfo.setSsyjsgain("是".equals(ssyjsgain) ? "1" : "2");
                    } else if ("硕士研究生毕业年月".equals(colnames.get(j))) {
                        masterGraduateTime = value;
                        euInfo.setMasterGraduateTime(_dateTypeTrans(masterGraduateTime));
                    } else if ("硕士研究生毕业证编号".equals(colnames.get(j))) {
                        masterDiplomaCode = value;
                        euInfo.setMasterDiplomaCode(masterDiplomaCode);
                    } else if ("硕士研究生毕业单位名称".equals(colnames.get(j))) {
                        masterUnitName = value;
                        euInfo.setMasterUnitName(masterUnitName);
                    } else if ("硕士研究生毕业专业名称".equals(colnames.get(j))) {
                        masterGraduateMajor = value;
                        euInfo.setMasterGraduateMajor(masterGraduateMajor);
                    } else if ("是否获得硕士学位".equals(colnames.get(j))) {
                        ssxwgain = value;
                        euInfo.setSsxwgain("是".equals(ssxwgain) ? "1" : "2");
                    } else if ("获得硕士学位的学习形式".equals(colnames.get(j))) {
                        masterStudyForm = value;
                        euInfo.setMasterStudyForm(masterStudyForm);
                    } else if ("获得硕士学位年月".equals(colnames.get(j))) {
                        masterAwardDegreeTime = value;
                        euInfo.setMasterAwardDegreeTime(_dateTypeTrans(masterAwardDegreeTime));
                    } else if ("硕士学位证书编号".equals(colnames.get(j))) {
                        masterDegreeCertCode = value;
                        euInfo.setMasterDegreeCertCode(masterDegreeCertCode);
                    } else if ("获得硕士学位单位名称".equals(colnames.get(j))) {
                        masterAwardDegreeOrg = value;
                        euInfo.setMasterAwardDegreeOrg(masterAwardDegreeOrg);
                    } else if ("获得硕士学位学科门类".equals(colnames.get(j))) {
                        masterSubject = value;
                        euInfo.setMasterSubject(masterSubject);
                    } else if ("预毕业时间".equals(colnames.get(j))) {
                        planGraduateTime = value;
                        eduUser.setPreGraduateDate(_dateTypeTrans(planGraduateTime));
                    } else if ("是否毕业".equals(colnames.get(j))) {
                        graduateFlag = value;
                        euInfo.setGraduateFlag("是".equals(graduateFlag) ? "1" : "2");
                    } else if ("毕业时间".equals(colnames.get(j))) {
                        graduateTime = value;
                        eduUser.setGraduateTime(_dateTypeTrans(graduateTime));
                    } else if ("毕业证书编号".equals(colnames.get(j))) {
                        diplomaCode = value;
                        eduUser.setDiplomaCode(diplomaCode);
                    } else if ("是否授予学位".equals(colnames.get(j))) {
                        awardDegreeFlag = value;
                        eduUser.setAwardDegreeFlag("是".equals(awardDegreeFlag) ? GlobalConstant.FLAG_Y : GlobalConstant.FLAG_N);
                    }else if("授予学科门类".equals(colnames.get(j))) {
                        awardSubjectCategory = value;
                        euInfo.setAwardSubjectCategory(awardSubjectCategory);
                    } else if ("授予学位类别".equals(colnames.get(j))) {
                        awardDegreeCategoryName = value;
                        eduUser.setAwardDegreeCategoryName(awardDegreeCategoryName);
                        eduUser.setAwardDegreeCategoryId(getDictId(awardDegreeCategoryName, "DegreeCategory"));
                    }else if("授予学位时间".equals(colnames.get(j))) {
                        awardDegreeTime = value;
                        eduUser.setAwardDegreeTime(_dateTypeTrans(awardDegreeTime));
                    }else if ("授予学位证书编号".equals(colnames.get(j))) {
                        awardDegreeCertCode = value;
                        eduUser.setAwardDegreeCertCode(awardDegreeCertCode);
                    }else if ("论文题目".equals(colnames.get(j))) {
                        paperTitle = value;
                        euInfo.setPaperTitle(paperTitle);
                    }else if ("论文选题来源".equals(colnames.get(j))) {
                        paperSource = value;
                        euInfo.setPaperSource(paperSource);
                    }else if ("论文关键词".equals(colnames.get(j))) {
                        paperKey = value;
                        euInfo.setPaperKey(paperKey);
                    }else if ("论文类型".equals(colnames.get(j))) {
                        paperType = value;
                        euInfo.setPaperType(paperType);
                    }else if ("获学位后去向".equals(colnames.get(j))) {
                        degreeDirection = value;
                        euInfo.setDegreeDirection(getDictId(degreeDirection, "GotDegreeDirection"));
                    }else if ("就业单位性质".equals(colnames.get(j))) {
                        unitNature = value;
                        euInfo.setUnitNature(getDictId(unitNature, "UnitNature"));
                    }else if ("就业单位所在省（直辖市）".equals(colnames.get(j))) {
                        unitAddress = value;
                        euInfo.setUnitAddress(unitAddress);
                    }else if ("工作性质".equals(colnames.get(j))) {
                        workNature = value;
                        euInfo.setWorkNature(getDictId(workNature, "WorkNature"));
                    }else if ("就业单位名称".equals(colnames.get(j))) {
                        unitName = value;
                        euInfo.setUnitName(unitName);
                    }else if ("就业单位联系人".equals(colnames.get(j))) {
                        unitLinkMan = value;
                        euInfo.setUnitLinkMan(unitLinkMan);
                    }else if ("就业单位联系人电话".equals(colnames.get(j))) {
                        unitLinkManPhone = value;
                        euInfo.setUnitLinkManPhone(unitLinkManPhone);
                    }else if ("就业单位联系人邮箱".equals(colnames.get(j))) {
                        unitLinkManEmail = value;
                        euInfo.setUnitLinkManEmail(unitLinkManEmail);
                    }
                }
                if (StringUtil.isBlank(sysUser.getUserCode()) || StringUtil.isBlank(sysUser.getUserName())) {
                    continue;
                }
                SysUser currUser = GlobalContext.getCurrentUser();
                sysUser.setOrgFlow(currUser.getOrgFlow());
                sysUser.setOrgName(currUser.getOrgName());
                //验证用户登录名(学号)及姓名
                SysUser exitUser = userBiz.findByUserCode(sysUser.getUserCode());
                if (null != exitUser) {
                    if(sysUser.getUserName().equals(exitUser.getUserName())){
                        sysUser.setUserFlow(exitUser.getUserFlow());
                        eduUser.setUserFlow(exitUser.getUserFlow());
                        resDoctor.setDoctorFlow(exitUser.getUserFlow());
                        //针对子版块导入信息不能影响其他版块内容
                        EduUser obj = eduUserMapper.selectByPrimaryKey(exitUser.getUserFlow());
                        if(null != obj && StringUtil.isNotBlank(obj.getContent())){
                            XjEduUserExtInfoForm extInfoForm = parseExtInfoXml(obj.getContent());
                            Copy(euInfo,extInfoForm);
                            eduUserForm.setEduUserExtInfoForm(extInfoForm);
                        }else{
                            eduUserForm.setEduUserExtInfoForm(euInfo);
                        }
                    }else{//学号存在，但姓名错误
//                        continue;
                        throw new RuntimeException("第"+(count+1)+"行姓名有误！");
                    }
                }else{//批量新增
                    eduUserForm.setEduUserExtInfoForm(euInfo);
                }
                save(eduUserForm,null);
                count++;
            }
        }
        return count;
    }
    //同类互相赋值
    public static void Copy(Object source, Object dest) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
        try {
            for (int i = 0; i < sourceProperty.length; i++) {
                if(null != sourceProperty[i].getReadMethod().invoke(source)){
                    for (int j = 0; j < destProperty.length; j++) {
                        if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
                            // 调用source的getter方法和dest的setter方法
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }
    @Override
    public int save(XjEduUserForm form, String flag) throws Exception {
        if (form != null) {
            String docRole = InitConfig.getSysCfg("res_doctor_role_flow");
            String stuRole = InitConfig.getSysCfg("xjgl_student_role_flow");
            SysUser user = form.getSysUser();
            EduUser eduUser = form.getEduUser();
            ResDoctor resDoctor = form.getResDoctor();
            resDoctor.setDoctorCategoryId(XjDocCategoryEnum.Graduate.getId());
            resDoctor.setDoctorCategoryName(XjDocCategoryEnum.Graduate.getName());
            EduLog eduLog = new EduLog();//修改日志对象
            eduLog.setUserFlow(user.getUserFlow());//用户流水号
            eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
            eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
            eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
            eduLog.setLogDesc("学籍修改日志");
            eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
            eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
            eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            eduLog.setCreateTime(DateUtil.getCurrentTime());
            eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            if (user != null) {
                SysUser oldUser = userBiz.readSysUser(user.getUserFlow());
                EduUser oldEduUser = eduUserMapper.selectByPrimaryKey(eduUser.getUserFlow());
                if(null != oldEduUser){//对于导入新增的学员无须记录日志
                    eduLog.setGradeNumber(oldEduUser.getPeriod());//入学年级
                    eduLog.setStudyId(oldEduUser.getSid());//学号
                    eduLog.setUserName(oldUser.getUserName());//姓名
                }
                if(null != oldUser){//新增修改项日志记录
                    if(StringUtil.isNotBlank(user.getUserName()) && !user.getUserName().equals(oldUser.getUserName())){//姓名
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("姓名");
                        eduLog.setOldData(oldUser.getUserName());
                        eduLog.setNewData(user.getUserName());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getIdNo()) && !user.getIdNo().equals(oldUser.getIdNo())){//证件号码
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("证件号码");
                        eduLog.setOldData(oldUser.getIdNo());
                        eduLog.setNewData(user.getIdNo());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getSexName()) && !user.getSexName().equals(oldUser.getSexName())){//性别
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("性别");
                        eduLog.setOldData(oldUser.getSexName());
                        eduLog.setNewData(user.getSexName());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getNationName()) && !user.getNationName().equals(oldUser.getNationName())){//民族
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("民族");
                        eduLog.setOldData(oldUser.getNationName());
                        eduLog.setNewData(user.getNationName());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getUserBirthday()) && !user.getUserBirthday().equals(oldUser.getUserBirthday())){//出生日期
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("出生日期");
                        eduLog.setOldData(oldUser.getUserBirthday());
                        eduLog.setNewData(user.getUserBirthday());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getDeptName()) && !user.getDeptName().equals(oldUser.getDeptName())){//学位分委员会
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("学位分委员会");
                        eduLog.setOldData(oldUser.getDeptName());
                        eduLog.setNewData(user.getDeptName());
                        logMapper.insertSelective(eduLog);
                    }
                    if(StringUtil.isNotBlank(user.getPoliticsStatusName()) && !user.getPoliticsStatusName().equals(oldUser.getPoliticsStatusName())){//政治面貌
                        eduLog.setLogFlow(PkUtil.getUUID());
                        eduLog.setChangeItem("政治面貌");
                        eduLog.setOldData(oldUser.getPoliticsStatusName());
                        eduLog.setNewData(user.getPoliticsStatusName());
                        logMapper.insertSelective(eduLog);
                    }
                }
                userBiz.saveUser(user);//修改操作
                if("1".equals(eduUser.getTrainCategoryId())){//加入学籍工作站（及res工作站）学生角色（培养类型-南医： 1 专业学位； 2 学术学位）
                    userRoleBiz.saveSysUserRoleNoDel(user.getUserFlow(), stuRole, docRole);
                }else{//加入学籍工作站学生角色
                    userRoleBiz.saveSysUserRoleNoDel(user.getUserFlow(), stuRole, null);
                }
                if (resDoctor != null) {
                    ResDoctor oldDoctor = doctorMapper.selectByPrimaryKey(resDoctor.getDoctorFlow());
                    if (StringUtil.isNotBlank(resDoctor.getDoctorFlow())) {
                        editDoctor(resDoctor);
                    } else {
                        String userFlow = user.getUserFlow();
                        resDoctor.setDoctorFlow(userFlow);
                        onlySaveResDoctor(resDoctor);
                    }
                    if(null != oldDoctor){//新增修改项日志记录
                        if(StringUtil.isNotBlank(resDoctor.getOrgName()) && !resDoctor.getOrgName().equals(oldDoctor.getOrgName())){//培养单位
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("培养单位");
                            eduLog.setOldData(oldDoctor.getOrgName());
                            eduLog.setNewData(resDoctor.getOrgName());
                            logMapper.insertSelective(eduLog);
                        }
                    }
                }
                if (eduUser != null) {
                    XjEduUserExtInfoForm eduUserExtInfoForm = form.getEduUserExtInfoForm();
                    String content = getXmlFromExtInfo(eduUserExtInfoForm);
                    eduUser.setContent(content);
                    Integer result = 0;
                    if (StringUtil.isNotBlank(eduUser.getUserFlow())) {
                        result = saveEduUser(eduUser);
                    } else {
                        String userFlow = user.getUserFlow();
                        eduUser.setUserFlow(userFlow);
                        result = onlySaveEduUser(eduUser);
                    }
                    if(result > 0 && null != oldEduUser){//新增修改项日志记录
                        if(StringUtil.isNotBlank(eduUser.getSid()) && !eduUser.getSid().equals(oldEduUser.getSid())){//学号
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("学号");
                            eduLog.setOldData(oldEduUser.getSid());
                            eduLog.setNewData(eduUser.getSid());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getMajorId()) && !eduUser.getMajorId().equals(oldEduUser.getMajorId())){//专业名称
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("专业名称");
                            eduLog.setOldData(DictTypeEnum.Major.getDictNameById(oldEduUser.getMajorId()));
                            eduLog.setNewData(DictTypeEnum.Major.getDictNameById(eduUser.getMajorId()));
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getTrainCategoryName()) && !eduUser.getTrainCategoryName().equals(oldEduUser.getTrainCategoryName())){//培养类型
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("培养类型");
                            eduLog.setOldData(oldEduUser.getTrainCategoryName());
                            eduLog.setNewData(eduUser.getTrainCategoryName());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getIs5plus3()) && !eduUser.getIs5plus3().equals(oldEduUser.getIs5plus3())){//是否5+3培养模式
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("是否5+3培养模式");
                            eduLog.setOldData(oldEduUser.getIs5plus3());
                            eduLog.setNewData(eduUser.getIs5plus3());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getFirstTeacher()) && !eduUser.getFirstTeacher().equals(oldEduUser.getFirstTeacher())){//导师一
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("导师一");
                            eduLog.setOldData(oldEduUser.getFirstTeacher());
                            eduLog.setNewData(eduUser.getFirstTeacher());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getSecondTeacher()) && !eduUser.getSecondTeacher().equals(oldEduUser.getSecondTeacher())){//导师二
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("导师二");
                            eduLog.setOldData(oldEduUser.getSecondTeacher());
                            eduLog.setNewData(eduUser.getSecondTeacher());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getStudentCode()) && !eduUser.getStudentCode().equals(oldEduUser.getStudentCode())){//考生编号
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("考生编号");
                            eduLog.setOldData(oldEduUser.getStudentCode());
                            eduLog.setNewData(eduUser.getStudentCode());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUser.getStudentSourceName()) && !eduUser.getStudentSourceName().equals(oldEduUser.getStudentSourceName())){//考生来源
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("考生来源");
                            eduLog.setOldData(oldEduUser.getStudentSourceName());
                            eduLog.setNewData(eduUser.getStudentSourceName());
                            logMapper.insertSelective(eduLog);
                        }
                        XjEduUserExtInfoForm oldEduExtInfoForm = parseExtInfoXml(oldEduUser.getContent());
                        if(StringUtil.isNotBlank(eduUserExtInfoForm.getJoinOrgTime()) && !eduUserExtInfoForm.getJoinOrgTime().equals(oldEduExtInfoForm.getJoinOrgTime())){//入党（团）时间
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("入党（团）时间");
                            eduLog.setOldData(oldEduExtInfoForm.getJoinOrgTime());
                            eduLog.setNewData(eduUserExtInfoForm.getJoinOrgTime());
                            logMapper.insertSelective(eduLog);
                        }
                        if(StringUtil.isNotBlank(eduUserExtInfoForm.getDropOutSchoolDate()) && !eduUserExtInfoForm.getDropOutSchoolDate().equals(oldEduExtInfoForm.getDropOutSchoolDate())){//入党（团）时间
                            eduLog.setLogFlow(PkUtil.getUUID());
                            eduLog.setChangeItem("退学时间");
                            eduLog.setOldData(oldEduExtInfoForm.getDropOutSchoolDate());
                            eduLog.setNewData(eduUserExtInfoForm.getDropOutSchoolDate());
                            logMapper.insertSelective(eduLog);
                        }
                    }
                    //变更学位采集信息
                    if(StringUtil.isBlank(flag)){
                        DegreeInfoMain main = new DegreeInfoMain();
                        Copy(user,main);
                        Copy(resDoctor,main);
                        Copy(eduUser,main);
                        Copy(eduUserExtInfoForm,main);
                        saveDegreeInfo(main,"remove");
                        //变更学位管理中申请信息
                        NydbDefenceApply apply=new NydbDefenceApply();
                        apply.setStuNo(eduUser.getSid());
                        apply.setUserName(user.getUserName());
                        apply.setMajorId(eduUser.getMajorId());
                        apply.setMajorName(eduUser.getMajorName());
                        apply.setTrainGradationId(eduUser.getTrainTypeId());
                        apply.setTrainGradationName(eduUser.getTrainTypeName());
                        apply.setTrainCategoryId(eduUser.getTrainCategoryId());
                        apply.setTrainCategoryName(eduUser.getTrainCategoryName());
                        apply.setTutorFlow(eduUser.getFirstTeacherFlow());
                        apply.setTutorName(eduUser.getFirstTeacher());
                        apply.setTwoTutorFlow(eduUser.getSecondTeacherFlow());
                        apply.setTwoTutorName(eduUser.getSecondTeacher());
                        apply.setPydwOrgFlow(resDoctor.getOrgFlow());
                        apply.setPydwOrgName(resDoctor.getOrgName());
                        apply.setFwhOrgFlow(user.getDeptFlow());
                        apply.setFwhOrgName(user.getDeptName());
                        apply.setStudyFormId(eduUser.getStudyFormId());
                        apply.setStudyFormName(eduUser.getStudyFormName());
                        List<NydbDefenceApply> applyList=new ArrayList<>();
                        NydbDefenceApplyExample example=new NydbDefenceApplyExample();
                        NydbDefenceApplyExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                        if(StringUtil.isNotBlank(user.getUserFlow())){
                            criteria.andUserFlowEqualTo(user.getUserFlow());
                            applyList=defenceApplyMapper.selectByExample(example);
                        }
                        if(applyList!=null&&applyList.size()>0){
                            for (NydbDefenceApply applyTemp:applyList) {
                                if(StringUtil.isNotBlank(applyTemp.getRecordFlow())){
                                    apply.setRecordFlow(applyTemp.getRecordFlow());
                                    defenceBiz.saveDefence(apply);
                                }
                            }
                        }
                    }
                    return result;
                }
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    //将form对象封装为xml文本
    public String getXmlFromExtInfo(XjEduUserExtInfoForm extInfo){
        String xmlBody = null;
        if(extInfo!=null){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("eduUserExtInfoForm");
            Element extInfoForm = root.addElement("xjEduUserExtInfoForm");
            Map<String,String> filedMap = getClassFieldMap(extInfo);
            if(filedMap!=null && filedMap.size()>0){
                for(String key : filedMap.keySet()){
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }

            List<XjRegisterDateForm> regList = extInfo.getRegisterDateList();
            if(regList!=null && regList.size()>0){
                Element regListEle = root.addElement("registerDateList");
                for(XjRegisterDateForm reg : regList){
                    Element regEle = regListEle.addElement("xjRegisterDateForm");
                    Map<String,String> rgeFiledMap = getClassFieldMap(reg);
                    if(rgeFiledMap!=null && rgeFiledMap.size()>0){
                        for(String key : rgeFiledMap.keySet()){
                            Element item = regEle.addElement(key);
                            item.setText(rgeFiledMap.get(key));
                        }
                    }
                }
            }

            xmlBody=doc.asXML();
        }
        return xmlBody;
    }

    //获取属性名和值
    private Map<String,String> getClassFieldMap(Object obj){
        Map<String,String> filedMap = null;
        if(obj!=null){
            try{
                filedMap = new HashMap<String, String>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for(Field f : fileds){
                    String typeName = f.getType().getSimpleName();
                    if(stringClassName.equals(typeName)){
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0,1).toUpperCase();
                        String methedName = "get"+firstLetter+attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String)getMethod.invoke(obj);
                        filedMap.put(attrName,StringUtil.defaultString(value));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return filedMap;
    }

    public int editDoctor(ResDoctor doctor) {
        if (doctor != null) {
//			checkSelDeptFlag(doctor);
            if (StringUtil.isNotBlank(doctor.getDoctorFlow())) {
                GeneralMethod.setRecordInfo(doctor, false);
                return doctorMapper.updateByPrimaryKeySelective(doctor);
            } else {
                doctor.setDoctorFlow(PkUtil.getUUID());
                return onlySaveResDoctor(doctor);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    public int onlySaveResDoctor(ResDoctor resDoctor) {
        GeneralMethod.setRecordInfo(resDoctor, true);
        resDoctor.setSelDeptFlag(GlobalConstant.FLAG_N);
        resDoctor.setSchFlag(GlobalConstant.FLAG_N);
        return doctorMapper.insertSelective(resDoctor);
    }

    private Map<String, SysOrg> getOrgByNameMap() {
        Map<String, SysOrg> orgMap = new HashMap<String, SysOrg>();
        List<SysOrg> orgList = this.orgBiz.queryAllSysOrg(null);
        if (orgList != null && !orgList.isEmpty()) {
            for (SysOrg org : orgList) {
                orgMap.put(org.getOrgName(), org);
            }
        }
        return orgMap;
    }

    private Map<String, SysOrg> getOrgByCodeMap() {
        Map<String, SysOrg> orgMap = new HashMap<String, SysOrg>();
        List<SysOrg> orgList = this.orgBiz.queryAllSysOrg(null);
        if (orgList != null && !orgList.isEmpty()) {
            for (SysOrg org : orgList) {
                orgMap.put(org.getOrgCode(), org);
            }
        }
        return orgMap;
    }

    /**
     * 根据部门名称获取部门flow
     *
     * @param deptName
     * @return
     */
    public String getDeptFlow(String deptName, String orgFlow) {
        SysDept sysDept = new SysDept();
        sysDept.setDeptName(deptName);
        sysDept.setOrgFlow(orgFlow);
        List<SysDept> deptList = this.deptBiz.searchDept(sysDept);
        if (deptList != null && deptList.size() == 1) {
            return deptList.get(0).getDeptFlow();
        }
        return "";
    }

    /**
     * 根据字典名称获取Id
     *
     * @param dictName
     * @param dictTypeId
     * @return
     */
    public String getDictId(String dictName, String dictTypeId) {
        Map<String, String> dictNameMap = InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }

    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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
        throw new IOException("不能解析的excel版本");
    }

    @Override
    public EduUser findBySid(String sid) {
        EduUserExample example = new EduUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andSidEqualTo(sid);
        List<EduUser> eduUserList = eduUserMapper.selectByExample(example);
        if (eduUserList.size() > 0) {
            return eduUserList.get(0);
        }
        return null;
    }

    @Override
    public EduUser findBySidNotSelf(String userFlow, String sid) {
        EduUserExample example = new EduUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andSidEqualTo(sid);
        criteria.andUserFlowNotEqualTo(userFlow);
        List<EduUser> eduUserList = eduUserMapper.selectByExample(example);
        if (eduUserList.size() > 0) {
            return eduUserList.get(0);
        }
        return null;
    }

    @Override
    public List<XjEduUserExt> searchEduUserCourseExtMajorList(List<String> userFlowList, EduStudentCourse sc) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userFlowList", userFlowList);
        if (sc != null) {
            map.put("studentCourse", sc);
        }
        return eduUserExtMapper.searchEduUserCourseExtMajorList(map);
    }

    @Override
    public List<XjEduUserExt> searchEduUserExtList(SysUser sysUser, EduUser eduUser, ResDoctor doctor) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String roleFlow = InitConfig.getSysCfg("xjgl_student_role_flow");//获取学员角色流水号
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("sysUser", sysUser);
        paramMap.put("eduUser", eduUser);
        paramMap.put("resDoctor", doctor);

        return eduUserExtMapper.searchEduUserExtList(paramMap);
    }

    //将xml文本封装form
    @Override
    public XjEduUserExtInfoForm parseExtInfoXml(String extInfoXml) {
        XjEduUserExtInfoForm extInfo = null;
        if(StringUtil.isNotBlank(extInfoXml)){
            try{
                extInfo = new XjEduUserExtInfoForm();
                Document doc = DocumentHelper.parseText(extInfoXml);
                Element root = doc.getRootElement();
                Element extInfoFormEle = root.element("xjEduUserExtInfoForm");
                if(extInfoFormEle!=null){
                    List<Element> extInfoAttrEles = extInfoFormEle.elements();
                    if(extInfoAttrEles!=null && extInfoAttrEles.size()>0){
                        for(Element attr : extInfoAttrEles){
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            setValue(extInfo,attrName,attrValue);
                        }
                    }
                }
                Element regListEle = root.element("registerDateList");
                if(regListEle!=null){
                    List<Element> regEles = regListEle.elements();
                    if(regEles!=null && regEles.size()>0){
                        List<XjRegisterDateForm> regList = new ArrayList<XjRegisterDateForm>();
                        for(Element regEle : regEles){
                            XjRegisterDateForm reg = new XjRegisterDateForm();
                            List<Element> regAttrEles = regEle.elements();
                            if(regAttrEles!=null && regAttrEles.size()>0){
                                for(Element attr : regAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(reg,attrName,attrValue);
                                }
                            }
                            regList.add(reg);
                        }
                        extInfo.setRegisterDateList(regList);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return extInfo;
    }
    //为对象自动复制
    private void setValue(Object obj,String attrName,String attrValue) {
        try {
            Class<?> objClass = obj.getClass();
            String firstLetter = attrName.substring(0, 1).toUpperCase();
            String methedName = "set" + firstLetter + attrName.substring(1);
            Method setMethod = objClass.getMethod(methedName, new Class[]{String.class});
            setMethod.invoke(obj, new Object[]{attrValue});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<XjRegisterDateForm> searchRegisterDateList(String userFlow) throws Exception {
        List<XjRegisterDateForm> registerDateList = null;
        EduUser eduUser = readEduUser(userFlow);
        String content = eduUser.getContent();
        if (StringUtil.isNotBlank(content)) {
            Document dom = DocumentHelper.parseText(content);
            //Element root = dom.getRootElement();
            String xjRegisterDateXpath = "//registerDate";
            List<Element> registerDateElementList = dom.selectNodes(xjRegisterDateXpath);
            if (registerDateElementList != null && !registerDateElementList.isEmpty()) {
                registerDateList = new ArrayList<XjRegisterDateForm>();
                for (Element rd : registerDateElementList) {
                    XjRegisterDateForm registerDate = new XjRegisterDateForm();
                    registerDate.setId(rd.attributeValue("id"));
                    registerDate.setRegisterDate(rd.getText());
                    registerDateList.add(registerDate);
                }
            }
        }
        return registerDateList;
    }

    @Override
    public List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows) {
        ResDoctorExample example = new ResDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
        return doctorMapper.selectByExample(example);
    }

    @Override
    public ResDoctor findDoctorByFlow(String doctorFlow) {
        return doctorMapper.selectByPrimaryKey(doctorFlow);
    }

    @Override
    public int saveRegisterDate(List<XjRegisterDateForm> registerDateList, String userFlow) throws Exception {
        if(StringUtil.isNotBlank(userFlow)){
            Document dom = null;
            Element root = null;
            Element xjRegisterDateElement = null;
            EduUser eduUser = readEduUser(userFlow);
            if(eduUser == null){
                eduUser = new EduUser();
                dom = DocumentHelper.createDocument();
                root = dom.addElement("eduUserExtInfoForm");
            }else{
                String content = eduUser.getContent();
                if(StringUtil.isNotBlank(content)){
                    dom = DocumentHelper.parseText(content);
                    root = dom.getRootElement();
                    String xjRegisterDateXpath = "//xjRegisterDate";
                    xjRegisterDateElement = (Element) dom.selectSingleNode(xjRegisterDateXpath);
                }
            }
            if(xjRegisterDateElement == null){
                xjRegisterDateElement = root.addElement("xjRegisterDate");
            }
            xjRegisterDateElement.clearContent();
            if(registerDateList != null && !registerDateList.isEmpty()){
                for(XjRegisterDateForm regDate :registerDateList){
                    Element registerDateElement = xjRegisterDateElement.addElement("registerDate");
                    registerDateElement.addAttribute("id", regDate.getId());
                    registerDateElement.setText(StringUtil.defaultString(regDate.getRegisterDate()));
                }
            }
            String xml = dom.asXML();
            eduUser.setContent(xml);
            return saveEduUser(eduUser);
        }
        return GlobalConstant.ZERO_LINE;
    }
    @Override
    public Map<String, Object> importGradeFromExcel(MultipartFile file,String roleFlag) {
        Map<String, Object> nMap =new HashMap<String,Object>();
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int)file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcelToGrade(wb,roleFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return nMap;
    }
    @Override
    public List<XjEduCourseMajorExt> searchEduUserCourseList(String majorId, String period) {
        Map<String, Object> map=new HashMap<String, Object>();
        if (StringUtil.isNotBlank(majorId)) {
            map.put("majorId", majorId);
        }
        if (StringUtil.isNotBlank(period)) {
            map.put("period", period);
        }
        List<XjEduCourseMajorExt> list=eduUserExtMapper.searchEduUserCourseList(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> searchCerInfoLstByUserFlow(String userFlow) {
        return eduUserExtMapper.searchCerInfoLstByUserFlow(userFlow);
    }

    @Override
    public String uploadImg(String userFlow, String certType, MultipartFile file) {
        if (file != null) {
            List<String> mimeList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))) {
                mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))) {
                suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
            }

            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
            }
            try {
                /*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "eduImages" + File.separator + dateString;
                File fileDir = new File(newDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                /*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "eduImages/" + dateString + "/" + fileName;
                if(StringUtil.isNotBlank(userFlow)){//文件名存储在pub_file  file_remark(学位证明) file_path(毕业证明)
                    PubFile pf = new PubFile();
                    pf.setFileFlow(userFlow);
                    if("1".equals(certType))
                        pf.setFileRemark(url);
                    if("2".equals(certType))
                        pf.setFilePath(url);
                    pf.setFileName("证明照上传");//file_name不能为空
                    pf.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    saveFileName(pf);
                }
                return "success:" + url;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @Override
    public List<EduLog> searchUserOperLog(String gradeNumber, String startStudyId, String endStudyId, String userName, String changeItem, String startChangeTime, String endChangeTime) {
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("gradeNumber",gradeNumber);
        paramMap.put("startStudyId",startStudyId);
        paramMap.put("endStudyId",endStudyId);
        paramMap.put("userName",userName);
        paramMap.put("changeItem",changeItem);
        if(StringUtil.isNotBlank(startChangeTime)) {
            startChangeTime = startChangeTime.replace("-", "");
        }
        if(StringUtil.isNotBlank(endChangeTime)) {
            endChangeTime = endChangeTime.replace("-", "");
        }
        paramMap.put("startChangeTime",startChangeTime);
        paramMap.put("endChangeTime",endChangeTime);
        return eduUserExtMapper.searchEduLogList(paramMap);
    }

    //学位证明管理模块中上传证明照片存储服务器实际名称于PUB_FILE
    public void saveFileName(PubFile pubFile){
        PubFile pf = pubFileMapper.selectByPrimaryKey(pubFile.getFileFlow());
        if(null != pf){
            GeneralMethod.setRecordInfo(pubFile,false);
            pubFileMapper.updateByPrimaryKeySelective(pubFile);
        }else{
            pubFile.setCreateTime(DateUtil.getCurrentTime());
            pubFile.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            pubFileMapper.insert(pubFile);
        }
    };

    @Override
    public Map<String, Object> queryStuInfo(String sid) {
        return eduUserExtMapper.queryStuInfo(sid);
    }

    @Override
    public String uploadMaterialCert(String userFlow, String columnName, MultipartFile file) {
        if (file != null) {
            List<String> mimeList = new ArrayList<String>();
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_mime"))) {
                mimeList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_mime").split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_suffix"))) {
                suffixList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_suffix").split(","));
            }
            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
            }
            try {
                /*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "eduImages" + File.separator + dateString;
                File fileDir = new File(newDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                /*文件名*/
                fileName = PkUtil.getUUID() + suffix;
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "eduImages/" + dateString + "/" + fileName;
                if(StringUtil.isNotBlank(userFlow)){//文件名存储在eduUser表Clob中
                    EduUser eduUser = readEduUser(userFlow);
                    if(null != eduUser){
                        XjEduUserExtInfoForm extInfoForm = parseExtInfoXml(eduUser.getContent());
                        if("employAgreementUrl".equals(columnName)){
                            extInfoForm.setEmployAgreementUrl(url);
                        }else if("practiceCertUrl".equals(columnName)){
                            extInfoForm.setPracticeCertUrl(url);
                        }else if("emsUrl".equals(columnName)){
                            extInfoForm.setEmsUrl(url);
                        }
                        eduUser.setContent(getXmlFromExtInfo(extInfoForm));
                        eduUserMapper.updateByPrimaryKeySelective(eduUser);
                    }
                }
                return "success:" + url;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @Override
    public XjPollingTableForm parsePollingTableXml(String content) {
        XjPollingTableForm form = null;
        if(StringUtil.isNotBlank(content)){
            try{
                form = new XjPollingTableForm();
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element formEle = root.element("pollingTableForm");
                if(null != formEle){
                    List<Element> formAttrEles = formEle.elements();
                    if(formAttrEles!=null && formAttrEles.size()>0){
                        for(Element attr : formAttrEles){
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            setValue(form,attrName,attrValue);
                        }
                    }
                }
                Element eduListEle = root.element("educationList");
                if(null != eduListEle){
                    List<Element> eduEles = eduListEle.elements();
                    if(null != eduEles && eduEles.size()>0){
                        List<XjPollingTableEducationForm> eduList = new ArrayList<XjPollingTableEducationForm>();
                        for(Element eduEle : eduEles){
                            XjPollingTableEducationForm edu = new XjPollingTableEducationForm();
                            List<Element> eduAttrEles = eduEle.elements();
                            if(null != eduAttrEles && eduAttrEles.size()>0){
                                for(Element attr : eduAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(edu,attrName,attrValue);
                                }
                            }
                            eduList.add(edu);
                        }
                        form.setEducationList(eduList);
                    }
                }
                Element famListEle = root.element("familyList");
                if(null != famListEle){
                    List<Element> famEles = famListEle.elements();
                    if(null != famEles && famEles.size()>0){
                        List<XjPollingTableFamilyForm> famList = new ArrayList<XjPollingTableFamilyForm>();
                        for(Element famEle : famEles){
                            XjPollingTableFamilyForm fam = new XjPollingTableFamilyForm();
                            List<Element> famAttrEles = famEle.elements();
                            if(null != famAttrEles && famAttrEles.size()>0){
                                for(Element attr : famAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(fam,attrName,attrValue);
                                }
                            }
                            famList.add(fam);
                        }
                        form.setFamilyList(famList);
                    }
                }
                Element changeListEle = root.element("changeList");
                if(null != changeListEle){
                    List<Element> changeEles = changeListEle.elements();
                    if(null != changeEles && changeEles.size()>0){
                        List<XjPollingTableChangeForm> changeList = new ArrayList<XjPollingTableChangeForm>();
                        for(Element changeEle : changeEles){
                            XjPollingTableChangeForm change = new XjPollingTableChangeForm();
                            List<Element> changeAttrEles = changeEle.elements();
                            if(null != changeAttrEles && changeAttrEles.size()>0){
                                for(Element attr : changeAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(change,attrName,attrValue);
                                }
                            }
                            changeList.add(change);
                        }
                        form.setChangeList(changeList);
                    }
                }
                Element bonListEle = root.element("bonusPenaltyList");
                if(null != bonListEle){
                    List<Element> bonEles = bonListEle.elements();
                    if(null != bonEles && bonEles.size()>0){
                        List<XjPollingTableBonusPenaltyForm> bonList = new ArrayList<XjPollingTableBonusPenaltyForm>();
                        for(Element bonEle : bonEles){
                            XjPollingTableBonusPenaltyForm bon = new XjPollingTableBonusPenaltyForm();
                            List<Element> bonAttrEles = bonEle.elements();
                            if(null != bonAttrEles && bonAttrEles.size()>0){
                                for(Element attr : bonAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(bon,attrName,attrValue);
                                }
                            }
                            bonList.add(bon);
                        }
                        form.setBonusPenaltyList(bonList);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return form;
    }
    @Override
    public int save(String userFlow, XjPollingTableForm form) throws Exception {
        if (null != form) {//学籍登记表
            String content = getPollingTableXml(form);
            if(StringUtil.isBlank(userFlow)){
                userFlow = GlobalContext.getCurrentUser().getUserFlow();
            }
            //更新学籍信息中下列信息
            if(StringUtil.isNotBlank(userFlow)){
                XjEduUserForm eduUserForm=new XjEduUserForm();
                EduUser eduUser=findByFlow(userFlow);
                if(eduUser!=null){
                    XjEduUserExtInfoForm extInfoForm = parseExtInfoXml(eduUser.getContent());
                    extInfoForm.setHomePhone(form.getHomePhone());
                    extInfoForm.setSpeciality(form.getSpecialty());
                    extInfoForm.setStudentSourceArea(form.getBirthPlaceProvince()+"省"+form.getBirthPlacePrefectureLevelCity()+"市"+form.getBirthPlaceCountyLevelCity()+"县/区");//生源地
                    extInfoForm.setNowResideAddress(form.getHomeAddress());//家庭通讯地址
                    extInfoForm.setPostCode(form.getPostCode());//邮政编码
                    eduUser.setSid(form.getStudentId());
                    eduUser.setNameSpell(form.getNameSpell());
                    eduUser.setContent(getXmlFromExtInfo(extInfoForm));
                    eduUserForm.setEduUserExtInfoForm(extInfoForm);
                }
                SysUser sysUser=userBiz.findByFlow(userFlow);
                if(sysUser!=null){
                    sysUser.setUserName(form.getUserName());
                    sysUser.setSexId(form.getSexName());
                    sysUser.setSexName(UserSexEnum.getNameById(form.getSexName()));
                    sysUser.setUserBirthday(form.getBirthDate());
                    sysUser.setIdNo(form.getIdNumber());
                    sysUser.setPoliticsStatusName(form.getPoliticalOutlook());
                    sysUser.setPoliticsStatusId(PoliticsAppearanceEnum.getIdByName(form.getPoliticalOutlook()));
                    sysUser.setNationName(form.getNation());
                    sysUser.setNationId(UserNationEnum.getIdByName(form.getNation()));
                    sysUser.setNativePlaceProvId(form.getNativePlaceProvinceId());
                    sysUser.setNativePlaceProvName(form.getNativePlaceProvince());
                    sysUser.setNativePlaceCityId(form.getNativePlaceCityId());
                    sysUser.setNativePlaceCityName(form.getNativePlaceCity());
                    sysUser.setNativePlaceAreaId(form.getNativePlaceAreaId());
                    sysUser.setNativePlaceAreaName(form.getNativePlaceArea());
                    sysUser.setUserPhone(form.getMobilePhone());//本人手机号码
                }
                ResDoctor resDoctor=findDoctorByFlow(userFlow);
                if(resDoctor!=null){
                    resDoctor.setDoctorCode(form.getStudentId());
                }
                eduUserForm.setEduUser(eduUser);
                eduUserForm.setSysUser(sysUser);
                eduUserForm.setResDoctor(resDoctor);
                save(eduUserForm,null);
            }
            PubUserGenericContent generic = readPubUserGenericContent(userFlow,"RegistrationForm");
            if(null != generic){
                generic.setContent(content);
                GeneralMethod.setRecordInfo(generic,false);
                return genericContentMapper.updateByPrimaryKeySelective(generic);
            }else{
                generic = new PubUserGenericContent();
                generic.setUserFlow(userFlow);
                generic.setContentType("RegistrationForm");
                generic.setContent(content);
                GeneralMethod.setRecordInfo(generic,true);
                return genericContentMapper.insertSelective(generic);
            }
//            form.setNation(eduUserForm.getSysUser().getNationName());
//            form.setNativePlaceProvince(eduUserForm.getSysUser().getNativePlaceProvName());
//            form.setNativePlaceCity(eduUserForm.getSysUser().getNativePlaceCityName());
//            form.setNativePlaceArea(eduUserForm.getSysUser().getNativePlaceAreaName());
//            form.setPoliticalOutlook(eduUserForm.getSysUser().getPoliticsStatusName());
        }
        return GlobalConstant.ZERO_LINE;
    }
    //将form对象封装为xml文本
    public String getPollingTableXml(XjPollingTableForm form){
        String xmlBody = null;
        if(null != form){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("xjPollingTableForm");
            Element extInfoForm = root.addElement("pollingTableForm");
            Map<String,String> filedMap = getClassFieldMap(form);
            if(filedMap!=null && filedMap.size()>0){
                for(String key : filedMap.keySet()){
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }
            List<XjPollingTableEducationForm> eduList = form.getEducationList();
            if(null != eduList && eduList.size()>0){
                Element eduListEle = root.addElement("educationList");
                for(XjPollingTableEducationForm edu : eduList){
                    Element eduEle = eduListEle.addElement("educationListForm");
                    Map<String,String> eduFiledMap = getClassFieldMap(edu);
                    if(eduFiledMap!=null && eduFiledMap.size()>0){
                        for(String key : eduFiledMap.keySet()){
                            Element item = eduEle.addElement(key);
                            item.setText(eduFiledMap.get(key));
                        }
                    }
                }
            }
            List<XjPollingTableFamilyForm> famList = form.getFamilyList();
            if(null != famList && famList.size()>0){
                Element famListEle = root.addElement("familyList");
                for(XjPollingTableFamilyForm fam : famList){
                    Element famEle = famListEle.addElement("familyListForm");
                    Map<String,String> famFiledMap = getClassFieldMap(fam);
                    if(famFiledMap!=null && famFiledMap.size()>0){
                        for(String key : famFiledMap.keySet()){
                            Element item = famEle.addElement(key);
                            item.setText(famFiledMap.get(key));
                        }
                    }
                }
            }
            List<XjPollingTableChangeForm> changeList = form.getChangeList();
            if(null != changeList && changeList.size()>0){
                Element changeListEle = root.addElement("changeList");
                for(XjPollingTableChangeForm change : changeList){
                    Element changeEle = changeListEle.addElement("changeListForm");
                    Map<String,String> changeFiledMap = getClassFieldMap(change);
                    if(changeFiledMap!=null && changeFiledMap.size()>0){
                        for(String key : changeFiledMap.keySet()){
                            Element item = changeEle.addElement(key);
                            item.setText(changeFiledMap.get(key));
                        }
                    }
                }
            }
            List<XjPollingTableBonusPenaltyForm> bonusPenaltyList = form.getBonusPenaltyList();
            if(null != bonusPenaltyList && bonusPenaltyList.size()>0){
                Element bonusPenaltyListEle = root.addElement("bonusPenaltyList");
                for(XjPollingTableBonusPenaltyForm bonusPenalty : bonusPenaltyList){
                    Element bonusPenaltyEle = bonusPenaltyListEle.addElement("bonusPenaltyListForm");
                    Map<String,String> bonusPenaltyFiledMap = getClassFieldMap(bonusPenalty);
                    if(bonusPenaltyFiledMap!=null && bonusPenaltyFiledMap.size()>0){
                        for(String key : bonusPenaltyFiledMap.keySet()){
                            Element item = bonusPenaltyEle.addElement(key);
                            item.setText(bonusPenaltyFiledMap.get(key));
                        }
                    }
                }
            }
            xmlBody=doc.asXML();
        }
        return xmlBody;
    }

    @Override
    public PubUserGenericContent readPubUserGenericContent(String userFlow, String contentType) {
        return genericContentMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public String uploadImage(String userFlow, MultipartFile file) {
        if (file != null) {
            List<String> mimeList = new ArrayList<String>();
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_mime"))) {
                mimeList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_mime").split(","));
            }
            List<String> suffixList = new ArrayList<String>();
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("inx_image_support_suffix"))) {
                suffixList = Arrays.asList(InitConfig.getSysCfg("inx_image_support_suffix").split(","));
            }
            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
            }
            long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
            if (file.getSize() > limitSize * 1024 * 1024) {
                return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
            }
            try {
                /*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "eduImages" + File.separator + dateString;
                File fileDir = new File(newDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                /*文件名*/
                fileName = PkUtil.getUUID() + suffix;
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "eduImages/" + dateString + "/" + fileName;
                if(StringUtil.isNotBlank(userFlow)){
                    PubUserGenericContent genericContent = readPubUserGenericContent(userFlow,"RegistrationForm");
                    if(null != genericContent){
                        XjPollingTableForm form = parsePollingTableXml(genericContent.getContent());
                        form.setHeadImgUrl(url);
                        genericContent.setContent(getPollingTableXml(form));
                        genericContentMapper.updateByPrimaryKeySelective(genericContent);
                    }
                }
                return "success:" + url;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    //学生毕业信息查询
    @Override
    public List<XjEduGraduateInfoExt> searchEduGraduateInfo(Map<String, Object> paramMap) {
        return eduUserExtMapper.searchEduGraduateInfo(paramMap);
    }
    @Override
    public List<DegreeInfoMain> queryDegreeInfoList(DegreeInfoMain main,List<String> periodList) {
        DegreeInfoMainExample example = new DegreeInfoMainExample();
        DegreeInfoMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(main.getUserFlow())){
            criteria.andUserFlowEqualTo(main.getUserFlow());
        }
        if(StringUtil.isNotBlank(main.getSid())){
            criteria.andSidLike("%"+main.getSid()+"%");
        }
        if(StringUtil.isNotBlank(main.getUserName())){
            criteria.andUserNameLike("%"+main.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(main.getSexId())){
            criteria.andSexIdEqualTo(main.getSexId());
        }
        if(StringUtil.isNotBlank(main.getTrainCategoryId())){
            criteria.andTrainCategoryIdEqualTo(main.getTrainCategoryId());
        }
        if(StringUtil.isNotBlank(main.getTrainTypeId())){
            criteria.andTrainTypeIdEqualTo(main.getTrainTypeId());
        }
        if(StringUtil.isNotBlank(main.getMajorId())){
            criteria.andMajorIdEqualTo(main.getMajorId());
        }
        if(StringUtil.isNotBlank(main.getPeriod())){
            criteria.andPeriodEqualTo(main.getPeriod());
        }
        if(StringUtil.isNotBlank(main.getNationId())){
            criteria.andNationIdEqualTo(main.getNationId());
        }
        if(StringUtil.isNotBlank(main.getPoliticsStatusId())){
            criteria.andPoliticsStatusIdEqualTo(main.getPoliticsStatusId());
        }
        if(StringUtil.isNotBlank(main.getOrgFlow())){
            criteria.andOrgFlowEqualTo(main.getOrgFlow());
        }
        if(StringUtil.isNotBlank(main.getDeptFlow())){
            criteria.andDeptFlowEqualTo(main.getDeptFlow());
        }
        if(periodList!=null&&periodList.size()>0){
            criteria.andPeriodIn(periodList);
        }
        if(StringUtil.isNotBlank(main.getCreateTime())){//起止时间-开始时间
            String startTime=DateUtil.transDateTime(main.getCreateTime(),"yyyy-MM-dd","yyyyMMddHHmmss");
            criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        if(StringUtil.isNotBlank(main.getCreateUserFlow())){//起止时间-结束时间
            String endTime=DateUtil.transDateTime(main.getCreateUserFlow(),"yyyy-MM-dd","yyyyMMddHHmmss");
            criteria.andCreateTimeLessThanOrEqualTo(endTime);
        }
        example.setOrderByClause("SID,CREATE_TIME");
        return degreeInfoMapper.selectByExample(example);
    }

    @Override
    public DegreeInfoMain readDegreeInfo(String userFlow) {
        return degreeInfoMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public int saveDegreeInfo(DegreeInfoMain main, String flag) throws Exception{
        if(StringUtil.isNotBlank(main.getPreDegreeId())){
            main.setPreDegreeName(UserDegreeEnum.getNameById(main.getPreDegreeId()));
        }else{
            main.setPreDegreeName("");
        }
        if(StringUtil.isNotBlank(main.getFirstLevelSubjectId())){
            main.setFirstLevelSubjectName(UserDegreeSubjectEnum.getNameById(main.getFirstLevelSubjectId()));
        }else{
            main.setFirstLevelSubjectName("");
        }
        int result = 0;
        if(StringUtil.isNotBlank(main.getUserFlow())){
            GeneralMethod.setRecordInfo(main,false);
            result = degreeInfoMapper.updateByPrimaryKeySelective(main);
        }else{
            main.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(main,true);
            result = degreeInfoMapper.insertSelective(main);
        }
        //对应学籍信息修改
        if(StringUtil.isBlank(flag)){
            XjEduUserForm eduUserForm = new XjEduUserForm();
            SysUser user = new SysUser();
            EduUser eduUser = new EduUser();
            ResDoctor doctor = new ResDoctor();
            String content = readEduUser(main.getUserFlow()).getContent();
            XjEduUserExtInfoForm extForm = parseExtInfoXml(content);
            //对应修改学籍信息时置空org，防止Copy更新SYSUSER中org
            main.setOrgFlow(null);
            main.setOrgName(null);
            Copy(main,user);
            Copy(main,eduUser);
            Copy(main,extForm);
            doctor.setDoctorFlow(main.getUserFlow());
            eduUserForm.setSysUser(user);
            eduUserForm.setEduUser(eduUser);
            eduUserForm.setResDoctor(doctor);
            eduUserForm.setEduUserExtInfoForm(extForm);
            save(eduUserForm,"remove");
        }
        return result;
    }
    public static String _dateTypeTrans(String value) {
        if(StringUtil.isNotBlank(value)){
            if (value.length()==8) {
                value=DateUtil.transDateTime(value, "yyyyMMdd", "yyyy-MM-dd");
            }
            if (value.length()==6) {
                value=DateUtil.transDateTime(value, "yyyyMM", "yyyy-MM");
            }
        }
        return value;
    }

    @Override
    public List<Map<String, Object>> readStuFeeInfo(String sid) {
        ApplicationContext contextnfykdx = new ClassPathXmlApplicationContext("spring-context-nfykdx.xml");
        XjStuFeeExtMapper stuFeeExtMapper = contextnfykdx.getBean(XjStuFeeExtMapper.class);
        Map<String,Object> map=new HashMap<>();
        map.put("sid",sid);
        return stuFeeExtMapper.queryStuFeeInfo(map);
    }

    @Override
    public Map<String, Object> readStuAllFeeInfo(String sid) {
        ApplicationContext contextnfykdx = new ClassPathXmlApplicationContext("spring-context-nfykdx.xml");
        XjStuFeeExtMapper stuFeeExtMapper = contextnfykdx.getBean(XjStuFeeExtMapper.class);
        Map<String,Object> map=new HashMap<>();
        map.put("sid",sid);
        return stuFeeExtMapper.queryStuAllFeeInfo(map);
    }

    @Override
    public List<DegreeInfoMain> queryDegreeInfoList1(DegreeInfoMain main, List<String> periodList) {
        if(StringUtil.isNotBlank(main.getCreateTime())){
            String startTime=DateUtil.transDateTime(main.getCreateTime(),"yyyy-MM-dd","yyyyMMddHHmmss");
            main.setCreateTime(startTime);
        }
        if(StringUtil.isNotBlank(main.getCreateUserFlow())){
            String endTime=DateUtil.transDateTime(main.getCreateUserFlow(),"yyyy-MM-dd","yyyyMMddHHmmss");
            main.setCreateUserFlow(endTime);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("main",main);
        map.put("periodList",periodList);
        return eduUserExtMapper.queryDegreeInfoList(map);
    }

    @Override
    public GydxjSupplementInfo readSupplement(String userFlow) {
        return suppleInfoMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public int saveSuppleInfo(GydxjSupplementInfo supple) {
        if(null != supple && StringUtil.isNotBlank(supple.getUserFlow())){
            if(null != suppleInfoMapper.selectByPrimaryKey(supple.getUserFlow())){
                GeneralMethod.setRecordInfo(supple,false);
                return suppleInfoMapper.updateByPrimaryKeySelective(supple);
            }else{
                GeneralMethod.setRecordInfo(supple,true);
                return suppleInfoMapper.insertSelective(supple);
            }
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> supplementList(Map<String, Object> map) {
        return eduUserExtMapper.supplementList(map);
    }
}

