package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjEduCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.xjgl.XjEduCourseExtMapper;
import com.pinde.sci.dao.xjgl.XjEduStudentCourseExtMapper;
import com.pinde.sci.enums.res.XjPartStatusEnum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class XjEduCourseBizImpl implements IXjEduCourseBiz {

    private static Logger logger = Logger.getLogger(XjEduCourseBizImpl.class);
    @Autowired
    private XjEduCourseExtMapper eduCourseExtMapper;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduCourseTeacherMapper courseTeacherMapper;
    @Autowired
    private EduUserInfoStatusMapper infoStatusMapper;
    @Autowired
    private EduScheduleLimitMapper limitMapper;


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
        example.setOrderByClause("COURSE_MAJOR_ID,NLSSORT(COURSE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
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
                   String gradationId= getDictId(eduCourse.getGradationName(), "TrainType");
                    if(StringUtil.isBlank(gradationId)){
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行课程的授课层次系统中不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                   String courseTypeId=getDictId(eduCourse.getCourseTypeName(), "XjCourseType");
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
    public List<EduCourse> searchCourseDistinct(Map<String, Object> mp) {
        return eduCourseExtMapper.selectCourseDistinct(mp);
    }

    @Override
    public int updateCourse(List<String> codeList, String inputFlag) {
        EduCourseExample example=new EduCourseExample();
        EduCourseExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(codeList!=null&&codeList.size()>0){
            criteria.andCourseCodeIn(codeList);
        }
        EduCourse ec=new EduCourse();
        if(StringUtil.isNotBlank(inputFlag)){
            ec.setResultInput(inputFlag);
        }
        return eduCourseMapper.updateByExampleSelective(ec,example);
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
}