package com.pinde.sci.biz.gyxjgl.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gyxjgl.GyXjEduCourseExtMapper;
import com.pinde.sci.enums.res.XjPartStatusEnum;
import com.pinde.sci.form.gyxjgl.XjCourseOutlineForm;
import com.pinde.sci.form.gyxjgl.XjCourseSyllabusForm;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.log4j.Logger;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjEduCourseBizImpl implements IGyXjEduCourseBiz {

    private static Logger logger = Logger.getLogger(GyXjEduCourseBizImpl.class);
    @Autowired
    private GyXjEduCourseExtMapper eduCourseExtMapper;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduCourseTeacherMapper courseTeacherMapper;
    @Autowired
    private EduUserInfoStatusMapper infoStatusMapper;
    @Autowired
    private EduScheduleLimitMapper limitMapper;
    @Autowired
    private EduApprovalFormMapper approvalFormMapper;
    @Autowired
    private EduApprovalSubMapper approvalSubMapper;

    @Autowired
    private  EduCourseMaterialMapper educourseMaterialMapper;

    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public List<EduCourse> searchStuCourseList(
            EduCourse eduCourse, SysUser sysUser, String studyStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (eduCourse != null) {
            paramMap.put("eduCourse", eduCourse);
        }
        if (sysUser != null) {
            paramMap.put("sysUser", sysUser);
        }
        if (studyStatus != null) {
            paramMap.put("studyStatus", studyStatus);
        }
        List<EduCourse> stuCourseList = eduCourseExtMapper.searchStuCourseList(paramMap);
        return stuCourseList;
    }

    @Override
    public int saveCourse(EduCourse course) {
        if (StringUtil.isNotBlank(course.getCourseFlow())) {
            GeneralMethod.setRecordInfo(course, false);
            return eduCourseMapper.updateByPrimaryKeySelective(course);
        } else {
            course.setCourseFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(course, true);
            return eduCourseMapper.insert(course);
        }
    }

    @Override
    public EduCourse readCourse(String courseFlow) {
        if (StringUtil.isNotBlank(courseFlow)) {
            return eduCourseMapper.selectByPrimaryKey(courseFlow);
        }
        return null;
    }

    @Override
    public List<EduCourse> readCourse(EduCourse course) {
        EduCourseExample example = new EduCourseExample();
        com.pinde.sci.model.mo.EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(course.getCourseCode())) {
            criteria.andCourseCodeEqualTo(course.getCourseCode());
        }
        if (StringUtil.isNotBlank(course.getCourseName())) {
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        if (StringUtil.isNotBlank(course.getCourseSession())) {
            criteria.andCourseSessionEqualTo(course.getCourseSession());
        }
        return eduCourseMapper.selectByExample(example);
    }

    @Override
    public int delCourse(String courseFlow) {
        if (StringUtil.isNotBlank(courseFlow)) {
            EduCourse course = readCourse(courseFlow);
            if (course != null) { //修改课程删除
                try {
                    String courseImg = course.getCourseImg();
                    courseImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + courseImg;
                    File imgFile = new File(courseImg);
                    if (imgFile.exists()) {
                        boolean delRestlt = imgFile.delete();
                        if (delRestlt) {//删除图片成功

                        }
                    }
                    course.setCourseImg(null);
                    course.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    return eduCourseMapper.updateByPrimaryKey(course);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除图片失败！");
                }
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<EduCourse> searchAllCourseList(EduCourse eduCourse, String sort) {
        EduCourseExample example = new EduCourseExample();
        EduCourseExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(eduCourse.getCourseName())) {
            criteria.andCourseNameLike("%" + eduCourse.getCourseName() + "%");
        }
        if (StringUtil.isNotBlank(eduCourse.getCourseMajorId())) {
            criteria.andCourseMajorIdEqualTo(eduCourse.getCourseMajorId());
        }
        if (StringUtil.isNotBlank(sort)) {
            example.setOrderByClause(sort + " desc");
        }
        return eduCourseMapper.selectByExample(example);
    }

    @Override
    public List<EduCourse> searchCourseList(EduCourse course) {
        EduCourseExample example = new EduCourseExample();
        EduCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (course != null) {
            if (StringUtil.isNotBlank(course.getCourseName())) {
                criteria.andCourseNameLike("%" + course.getCourseName() + "%");
            }
            if (StringUtil.isNotBlank(course.getCourseTypeId())) {
                criteria.andCourseTypeIdEqualTo(course.getCourseTypeId());
            }
            if (StringUtil.isNotBlank(course.getGradationId())) {
                criteria.andGradationIdEqualTo(course.getGradationId());
            }
            if (StringUtil.isNotBlank(course.getCourseCategoryId())) {
                criteria.andCourseCategoryIdEqualTo(course.getCourseCategoryId());
            }
            if (StringUtil.isNotBlank(course.getCourseTypeId())) {
                criteria.andCourseTypeIdEqualTo(course.getCourseTypeId());
            }
            if (StringUtil.isNotBlank(course.getCourseCode())) {
                criteria.andCourseCodeLike("%" + course.getCourseCode() + "%");
            }
            if (StringUtil.isNotBlank(course.getAssumeOrgFlow())) {
                criteria.andAssumeOrgFlowEqualTo(course.getAssumeOrgFlow());
            }
            if (StringUtil.isNotBlank(course.getCourseSession())) {
                criteria.andCourseSessionEqualTo(course.getCourseSession());
            }
        }
        example.setOrderByClause("COURSE_SESSION DESC,COURSE_CODE,COURSE_MAJOR_ID,NLSSORT(COURSE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
        return eduCourseMapper.selectByExample(example);
    }

    @Override
    public List<EduCourse> selectCourse(String userFlow, EduCourse course, List<String> deptFlow) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userFlow != null) {
            map.put("userFlow", userFlow);
        }
        if (course != null) {
            if (StringUtil.isNotBlank(course.getCourseName()) ||
                    StringUtil.isNotBlank(course.getCourseCategoryName())) {
                map.put("educourse", course);
            }
        }
        if (deptFlow != null && !deptFlow.isEmpty()) {
            map.put("deptList", deptFlow);
        }
        List<EduCourse> list = eduCourseExtMapper.seleEduCourse(map);
        return list;
    }

    @Override
    public ExcelUtile importCourseFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "courseSession",//所属学年*
                    "courseCode",//代码*
                    "courseName",//中文名称*
                    "gradationName",//授课层次*
                    "courseTypeName",//课程类别
                    "courseUserCount",//容纳人数
                    "courseMoudleName",//所属模块
                    "assumeOrgName",//承担单位*
                    "courseNameEn",//英文名称
                    "courseCredit",//学分*
                    "coursePeriod",//总学时
                    "coursePeriodTeach",//讲授学时
                    "coursePeriodExper",//实验学时
                    "coursePeriodMachine",//上机学时
                    "coursePeriodOther"//其它学时
            };
            return ExcelUtile.importDataExcel(EduCourse.class, 1, wb, keys, new IExcelUtil<EduCourse>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<EduCourse> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        EduCourse course=datas.get(i);
                        saveCourse(course);
                        count++;
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(EduCourse eduCourse,ExcelUtile eu) {
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    if(StringUtil.isBlank(eduCourse.getCourseSession()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【所属学年】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(eduCourse.getCourseCode()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【代码】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(eduCourse.getCourseName()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【中文名称】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(eduCourse.getGradationName()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【授课层次】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(eduCourse.getAssumeOrgName()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【承担单位】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(eduCourse.getCourseCredit()))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【学分】为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    //执行保存
                    EduCourse edCourse = new EduCourse();
                    edCourse.setCourseSession(eduCourse.getCourseSession());
                    edCourse.setCourseCode(eduCourse.getCourseCode());
                    List<EduCourse> courseList = searchCourseList(edCourse);
                    if (courseList != null && courseList.size()>0) {
                        eduCourse.setCourseFlow(courseList.get(0).getCourseFlow());//存在同学年同课程代码，则覆盖
                    }
                   String gradationId= getDictId(eduCourse.getGradationName(), "GyTrainType");
                    if(StringUtil.isBlank(gradationId)){
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行课程的授课层次系统中不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                   String courseTypeId="";
                    if("选修课".equals(eduCourse.getCourseTypeName())){
                        courseTypeId = "Public";
                    }else if("必选课".equals(eduCourse.getCourseTypeName())){
                        courseTypeId = "OptionalNeed";
                    }
                    if(StringUtil.isBlank(courseTypeId)){
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行课程的课程类别系统中不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    SysOrg assumeOrg = InitConfig.getSysOrgByName(eduCourse.getAssumeOrgName());
                    if (assumeOrg == null) {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行课程所属的承担单位在系统中不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    eduCourse.setGradationId(gradationId);
                    eduCourse.setCourseTypeId(courseTypeId);
                    eduCourse.setCourseMoudleId(getDictId(eduCourse.getCourseMoudleName(), "XjCourseMoudle"));
                    eduCourse.setAssumeOrgFlow(assumeOrg.getOrgFlow());
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    private int parseExcel(Workbook wb) {
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
                colnames.add(title);
            }
            for (int i = 1; i < row_num + 1; i++) {

                Row r = sheet.getRow(i);
                EduCourse course = new EduCourse();
                String courseName;
                String gradationName;
                String courseTypeName;
                String courseCode;
                String courseNameEn;
                String courseUserCount;
                String courseMoudleName;
                String assumeOrgName;
                String courseCredit;
                String coursePeriod;
                String coursePeriodTeach;
                String coursePeriodExper;
                String coursePeriodMachine;
                String coursePeriodOther;
                String courseSession;

                EduCourse eduCourse = new EduCourse();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    if ("所属学年".equals(colnames.get(j))) {
                        courseSession = value;
                        course.setCourseSession(courseSession);
                    } else if ("代码".equals(colnames.get(j))) {
                        courseCode = value;
                        course.setCourseCode(courseCode);
                    } else if ("中文名称".equals(colnames.get(j))) {
                        courseName = value;
                        course.setCourseName(courseName);
                    } else if ("授课层次".equals(colnames.get(j))) {
                        gradationName = value;
                        course.setGradationName(gradationName);
                    } else if ("课程类别".equals(colnames.get(j))) {
                        courseTypeName = value;
                        course.setCourseTypeName(courseTypeName);
                    } else if ("英文名称".equals(colnames.get(j))) {
                        courseNameEn = value;
                        course.setCourseNameEn(courseNameEn);
                    } else if ("容纳人数".equals(colnames.get(j))) {
                        courseUserCount = value;
                        course.setCourseUserCount(courseUserCount);
                    } else if ("所属模块".equals(colnames.get(j))) {
                        courseMoudleName = value;
                        course.setCourseMoudleName(courseMoudleName);
                    } else if ("承担单位".equals(colnames.get(j))) {
                        assumeOrgName = value;
                        course.setAssumeOrgName(assumeOrgName);
                    } else if ("学分".equals(colnames.get(j))) {
                        courseCredit = value;
                        course.setCourseCredit(courseCredit);
                    } else if ("总学时".equals(colnames.get(j))) {
                        coursePeriod = value;
                        course.setCoursePeriod(coursePeriod);
                    } else if ("讲授学时".equals(colnames.get(j))) {
                        coursePeriodTeach = value;
                        course.setCoursePeriodTeach(coursePeriodTeach);
                    } else if ("实验学时".equals(colnames.get(j))) {
                        coursePeriodExper = value;
                        course.setCoursePeriodExper(coursePeriodExper);
                    } else if ("上机学时".equals(colnames.get(j))) {
                        coursePeriodMachine = value;
                        course.setCoursePeriodMachine(coursePeriodMachine);
                    } else if ("其它学时".equals(colnames.get(j))) {
                        coursePeriodOther = value;
                        course.setCoursePeriodOther(coursePeriodOther);
                    }
                }
                //执行保存
                eduCourse.setCourseCode(course.getCourseCode());
                if(StringUtil.isNotBlank(course.getCourseCode())) {
                    List<EduCourse> ecourseList = searchCourseList(eduCourse);
                    if (ecourseList != null && !ecourseList.isEmpty()) {
                        course.setCourseFlow(ecourseList.get(0).getCourseFlow());
                    }
                }
                course.setGradationId(getDictId(course.getGradationName(), "TrainType"));
                course.setCourseTypeId(getDictId(course.getCourseTypeName(), "XjCourseType"));
                course.setCourseMoudleId(getDictId(course.getCourseMoudleName(), "XjCourseMoudle"));
                SysOrg assumeOrg = InitConfig.getSysOrgByName(course.getAssumeOrgName());
                if (assumeOrg != null) {
                    course.setAssumeOrgFlow(assumeOrg.getOrgFlow());
                }
                saveCourse(course);
                count++;
            }
        }
        return count;
    }

    public String getDictId(String dictName, String dictTypeId) {
        Map<String, String> dictNameMap = InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }

    @Override
    public int courseTeacherRelation(Map<String, Object> mp) {
        String courseFlow = mp.get("courseFlow").toString();
        List<String> tearchFlowLst = (List<String>)mp.get("itemIdLst");
        List<String> tearchNameLst = (List<String>)mp.get("itemNameLst");
        EduCourseTeacherExample example = new EduCourseTeacherExample();
        example.createCriteria().andCourseFlowEqualTo(courseFlow);
        courseTeacherMapper.deleteByExample(example);//删除该课程上次关联的授课老师
        int count = 0;
        EduCourseTeacher courseTeacher = new EduCourseTeacher();
        courseTeacher.setCourseFlow(courseFlow);
        courseTeacher.setMasterFlag(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(courseTeacher,true);
        if(null != tearchFlowLst && tearchFlowLst.size() > 0){
            for (int i = 0; i < tearchFlowLst.size(); i++) {
                courseTeacher.setRecordFlow(PkUtil.getUUID());
                courseTeacher.setClassTeacherFlow(tearchFlowLst.get(i));
                courseTeacher.setClassTeacherName(tearchNameLst.get(i));
                count += courseTeacherMapper.insertSelective(courseTeacher);
            }
        }
        logger.debug("该课程成功关联授课老师"+count+"名");
        return count;
    }

    @Override
    public List<EduCourseTeacher> readCourseTeacherLst(String courseFlow) {
        EduCourseTeacherExample example = new EduCourseTeacherExample();
        example.createCriteria().andCourseFlowEqualTo(courseFlow);
        return courseTeacherMapper.selectByExample(example);
    }

    @Override
    public int savePartStatus(String userFlow, String partId, String operFlag) {
        EduUserInfoStatusExample example = new EduUserInfoStatusExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow).andPartIdEqualTo(partId);
        List<EduUserInfoStatus> userInfoStatusesLst = infoStatusMapper.selectByExample(example);
        EduUserInfoStatus status = null;
        int count = 0;
        if(null != userInfoStatusesLst && userInfoStatusesLst.size() > 0){
            status = userInfoStatusesLst.get(0);
            if(StringUtil.isNotBlank(operFlag) && operFlag.equals("back")){
                status.setPartStatus(GlobalConstant.RECORD_STATUS_N);//管理员取消状态的情况
            }else{
                status.setPartStatus(GlobalConstant.RECORD_STATUS_Y);
            }
            status.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            GeneralMethod.setRecordInfo(status,false);
            count = infoStatusMapper.updateByPrimaryKeySelective(status);
        }else{
            status = new EduUserInfoStatus();
            status.setRecordFlow(PkUtil.getUUID());
            status.setUserFlow(userFlow);
            status.setPartId(partId);
            status.setPartName(XjPartStatusEnum.getNameById(partId));
            if(StringUtil.isNotBlank(operFlag) && operFlag.equals("back")){
                status.setPartStatus(GlobalConstant.RECORD_STATUS_N);//管理员取消状态的情况
            }else{
                status.setPartStatus(GlobalConstant.RECORD_STATUS_Y);
            }
            GeneralMethod.setRecordInfo(status,true);
            count = infoStatusMapper.insertSelective(status);
        }
        return count;
    }

    @Override
    public List<EduUserInfoStatus> searchPartStatus(String userFlow) {
        EduUserInfoStatusExample example = new EduUserInfoStatusExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return infoStatusMapper.selectByExample(example);
    }

    @Override
    public int saveCourseOutline(EduCourse course) {
        return eduCourseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public List<Map<String, Object>> queryOrgOrCourse(String userFlow) {
        return eduCourseExtMapper.queryOrgOrCourse(userFlow);
    }

    @Override
    public List<EduScheduleLimit> queryScheduleLimit(String sessionNumber) {
        EduScheduleLimitExample example = new EduScheduleLimitExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSessionNumberEqualTo(sessionNumber);
        example.setOrderByClause("SESSION_NUMBER DESC,END_TIME DESC");
        return limitMapper.selectByExample(example);
    }

    @Override
    public EduScheduleLimit readScheduleLimitByFlow(String recordFlow) {
        return limitMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int updateScheduleLimit(EduScheduleLimit limit) {
        int result = 0;
        List<EduScheduleLimit> limitList = new ArrayList<>();
        EduScheduleLimitExample example = new EduScheduleLimitExample();
        example.setOrderByClause("SESSION_NUMBER DESC,END_TIME DESC");
        EduScheduleLimitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSessionNumberEqualTo(limit.getSessionNumber());
        if(StringUtil.isNotBlank(limit.getRecordFlow())){
            criteria.andRecordStatusNotEqualTo(limit.getRecordFlow());
            limitList = limitMapper.selectByExample(example);
            //时间不允许有交叉点：开始时间大于最新结束时间或者结束时间小于最初开始时间
            if(!limitList.isEmpty() && !(limit.getBeginTime().compareTo(limitList.get(0).getEndTime()) > 0 || limit.getEndTime().compareTo(limitList.get(limitList.size()-1).getBeginTime()) < 0)){
                return -1;
            }
            GeneralMethod.setRecordInfo(limit,false);
            result = limitMapper.updateByPrimaryKeySelective(limit);
        }else{
            limitList = limitMapper.selectByExample(example);
            //时间不允许有交叉点：开始时间大于最新结束时间或者结束时间小于最初开始时间
            if(!limitList.isEmpty() && !(limit.getBeginTime().compareTo(limitList.get(0).getEndTime()) > 0 || limit.getEndTime().compareTo(limitList.get(limitList.size()-1).getBeginTime()) < 0)){
                return -1;
            }
            limit.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(limit,true);
            result = limitMapper.insertSelective(limit);
        }
        return result;
    }

    @Override
    public int delScheduleLimit(String recordFlow) {
        return limitMapper.deleteByPrimaryKey(recordFlow);
    }

    @Override
    public EduApprovalForm readApprovalForm(String courseFlow) {
        return approvalFormMapper.selectByPrimaryKey(courseFlow);
    }

    @Override
    public List<EduApprovalSub> queryApprovalSub(String courseFlow) {
        EduApprovalSubExample example = new EduApprovalSubExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
        return approvalSubMapper.selectByExample(example);
    }

    @Override
    public int saveApprovalForm(EduApprovalForm form, List<EduApprovalSub> subs) {
        int result = 0;
        EduApprovalForm eaf = approvalFormMapper.selectByPrimaryKey(form.getCourseFlow());
        //approvalSubMapper
        EduApprovalSubExample example = new EduApprovalSubExample();
        example.createCriteria().andCourseFlowEqualTo(form.getCourseFlow());
        List<EduApprovalSub> eduApprovalSubs = approvalSubMapper.selectByExample(example);
        for(EduApprovalSub sub : eduApprovalSubs){
            sub.setIsApprovalTeacher("N");
            approvalSubMapper.updateByPrimaryKeySelective(sub);
        }

        if(eaf == null){
            GeneralMethod.setRecordInfo(form,true);
            result = approvalFormMapper.insertSelective(form);
        }else{
            GeneralMethod.setRecordInfo(form,false);
            result = approvalFormMapper.updateByPrimaryKeySelective(form);
        }
        if(null != subs && !subs.isEmpty()){
            for(EduApprovalSub sub : subs){
                if(StringUtil.isNotBlank(sub.getRecordFlow())){
                    sub.setIsApprovalTeacher("Y");
                    GeneralMethod.setRecordInfo(sub,false);
                    approvalSubMapper.updateByPrimaryKeySelective(sub);
                }else{
                    sub.setRecordFlow(PkUtil.getUUID());
                    sub.setCourseFlow(form.getCourseFlow());
                    sub.setIsApprovalTeacher("Y");
                    GeneralMethod.setRecordInfo(sub,true);
                    approvalSubMapper.insert(sub);
                }
            }
        }
        return result;
    }

    @Override
    public String uploadTdxlFile(MultipartFile file) {
        if(file!=null){
            String fileType = file.getContentType();//MIME类型;
            String fileName = file.getOriginalFilename();//文件名
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            if(!(".docx".equals(suffix)||".DOCX".equals(suffix)||".doc".equals(suffix)||".DOC".equals(suffix))){
                return "只能上传word文件！";
            }
            try {
				/*创建目录*/
                String dateString = DateUtil.getCurrDate2();
                String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"gyTdxlFiles"+File.separator + dateString ;
                File fileDir = new File(newDir);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
				/*文件名*/
                fileName = file.getOriginalFilename();
                fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
                File newFile = new File(fileDir, fileName);
                file.transferTo(newFile);
                String url = "gyTdxlFiles/"+dateString+"/"+fileName;
                FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
                String localFilePath=fileDir+File.separator+fileName;
                String ftpDir= "abroadApplyFiles"+File.separator +dateString ;
                String ftpFileName=fileName;
                ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
                return "success:"+url;
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalConstant.UPLOAD_FAIL;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }


    //将form对象封装为xml文本
    public String getOutlinTableXml(XjCourseOutlineForm form){
        String xmlBody = null;
        if(null != form){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("XjCourseOutlineForm");
            Element extInfoForm = root.addElement("XjCourseOutlineForm");
            Map<String,String> filedMap = getClassFieldMap(form);
            if(filedMap!=null && filedMap.size()>0){
                for(String key : filedMap.keySet()){
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
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

    @Override
    public int saveOutline(XjCourseOutlineForm form, XjCourseSyllabusForm form2, EduCourseMaterial educoursem,String json) throws Exception {

        if(StringUtil.isNotBlank(educoursem.getRecordFlow())){
            if (json != null && !json.isEmpty()){
                String syllabusContent = getSyllabusTableXml(json);
                educoursem.setContent(syllabusContent);
            }else {
                String content = getOutlinTableXml(form);
                educoursem.setContent(content);
            }
            GeneralMethod.setRecordInfo(educoursem, false);
            return educourseMaterialMapper.updateByPrimaryKeySelective(educoursem);
        }else {
            String recordFlow= PkUtil.getUUID();
            educoursem.setRecordFlow(recordFlow);
            if (json != null && !json.isEmpty()){
                String syllabusContent = getSyllabusTableXml(json);
                educoursem.setContent(syllabusContent);
            }else {
                String content = getOutlinTableXml(form);
                educoursem.setContent(content);
            }
            GeneralMethod.setRecordInfo(educoursem, true);
            return educourseMaterialMapper.insert(educoursem);
        }
    }

    @Override
    public List<EduCourseMaterial> getEduCourseMaterial(String courseFlow, String formType) {
        EduCourseMaterial eduCourseMaterial = new EduCourseMaterial();
        EduCourseMaterialExample example=new EduCourseMaterialExample();
        EduCourseMaterialExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(courseFlow)){
            criteria.andCourseFlowEqualTo(courseFlow);
        }
        if(StringUtil.isNotBlank(formType)){
            criteria.andFormTypeEqualTo(formType);
        }
        return educourseMaterialMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public XjCourseOutlineForm parseOutlineTableXml(String content) {
        XjCourseOutlineForm form = null;
        if(StringUtil.isNotBlank(content)){
            try {
                form = new XjCourseOutlineForm();
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element formEle = root.element("XjCourseOutlineForm");
                if (null != formEle) {
                    List<Element> formAttrEles = formEle.elements();
                    if (formAttrEles != null && formAttrEles.size() > 0) {
                        for (Element attr : formAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            setValue(form, attrName, attrValue);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return form;
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

    //将form对象封装为xml文本
    public String getSyllabusTableXml(String json ){
        List<XjCourseSyllabusForm> courseSyllabusFormList = JSON.parseArray(JSON.parseObject(json).getString("courserProgressList"), XjCourseSyllabusForm.class);
        String xmlBody = "";
        if(null != courseSyllabusFormList && courseSyllabusFormList.size()>0){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("xjSyllabusTableForm");

            Element extInfoForm = root.addElement("syllabusTableForm");
                Element syllabusListEle = root.addElement("syllabusList");
                for(XjCourseSyllabusForm bonusPenalty : courseSyllabusFormList){
                    Element bonusPenaltyEle = syllabusListEle.addElement("XjCourseSyllabusForm");
                    Map<String,String> bonusPenaltyFiledMap = getClassFieldMap(bonusPenalty);
                    if(bonusPenaltyFiledMap!=null && bonusPenaltyFiledMap.size()>0){
                        for(String key : bonusPenaltyFiledMap.keySet()){
                            Element item = bonusPenaltyEle.addElement(key);
                            item.setText(bonusPenaltyFiledMap.get(key));
                        }
                    }
                }

            xmlBody=doc.asXML();
        }
        return xmlBody;
    }

    @Override
    public List<XjCourseSyllabusForm>   parseSyllabusTableXml(String content) {
        List<XjCourseSyllabusForm> list=null;
        if(StringUtil.isNotBlank(content)){
            try{
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element formEle = root.element("syllabusTableForm");

                Element syllabusListEle = root.element("syllabusList");
                if(null != syllabusListEle){
                    List<Element> syllabus = syllabusListEle.elements();
                    if(null != syllabus && syllabus.size()>0){
                        List<XjCourseSyllabusForm> bonList = new ArrayList<XjCourseSyllabusForm>();
                        for(Element syllabu : syllabus){
                            XjCourseSyllabusForm bon = new XjCourseSyllabusForm();
                            List<Element> bonAttrEles = syllabu.elements();
                            if(null != bonAttrEles && bonAttrEles.size()>0){
                                for(Element attr : bonAttrEles){
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    setValue(bon,attrName,attrValue);
                                }
                            }
                            bonList.add(bon);
                        }
                        list=bonList;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<EduApprovalSub> getteachersList(String courseFlow) {

             EduApprovalSubExample example = new EduApprovalSubExample();
             example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsTestTeacherEqualTo(GlobalConstant.RECORD_STATUS_N).andCourseFlowEqualTo(courseFlow);
             return approvalSubMapper.selectByExample(example);                                                                 
    }

    @Override
    public List<EduApprovalSub> getPracticesList(String courseFlow) {
       EduApprovalSubExample example = new EduApprovalSubExample();
       example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsTestTeacherEqualTo(GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
         return approvalSubMapper.selectByExample(example); 
    }


    @Override
    public int deleteEduSTeacherub(List<EduApprovalSub> subs) {
        int num=0;
        for(EduApprovalSub sub : subs){
            num= approvalSubMapper.deleteByPrimaryKey(sub.getRecordFlow());
        }
        return num;
    }

    @Override
    public int saveEduSTeacherub(List<String> subs,String courseFlow) {
        int num=0;
        for(int i=0;i<subs.size();i++){
            if (StringUtil.isNotBlank(subs.get(i))){
                EduApprovalSub sub=new EduApprovalSub();
                String recordFlow = PkUtil.getUUID();
                sub.setRecordFlow(recordFlow);
                sub.setCourseFlow(courseFlow);
                sub.setTeacherName(subs.get(i).trim());
                sub.setIsTestTeacher(GlobalConstant.RECORD_STATUS_N);
                GeneralMethod.setRecordInfo(sub, true);
                num= approvalSubMapper.insert(sub);
            }
        }
        
        return num;
    }

    @Override
    public int savePracticeTeacherub(List<String> subs,String courseFlow) {

        int num=0;
        for(int i=0;i<subs.size();i++){
            if (StringUtil.isNotBlank(subs.get(i))){
            EduApprovalSub sub=new EduApprovalSub();
            String recordFlow = PkUtil.getUUID();
            sub.setRecordFlow(recordFlow);
            sub.setCourseFlow(courseFlow);
            sub.setTeacherName(subs.get(i));
            sub.setIsTestTeacher(GlobalConstant.RECORD_STATUS_Y);
            GeneralMethod.setRecordInfo(sub, true);
            num= approvalSubMapper.insert(sub);
            }
        }
        return num;
    }
}