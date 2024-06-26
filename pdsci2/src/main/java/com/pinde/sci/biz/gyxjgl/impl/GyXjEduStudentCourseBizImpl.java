package com.pinde.sci.biz.gyxjgl.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduCourseTeachingGroupMapper;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.gyxjgl.GyXjEduStudentCourseExtMapper;
import com.pinde.sci.enums.gyxjgl.XjChooseCourseStatusEnum;
import com.pinde.sci.form.gyxjgl.XjOneCourseCreditForm;
import com.pinde.sci.form.gyxjgl.XjStudentCourseNumForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import com.pinde.sci.model.gyxjgl.XjEduStudentCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjEduStudentCourseBizImpl implements IGyXjEduStudentCourseBiz {
    @Autowired
    private EduStudentCourseMapper studentCourseMapper;
    @Autowired
    private GyXjEduStudentCourseExtMapper studentCourseExtMapper;
    @Autowired
    private IGyXjEduUserBiz eduUserBiz;
    @Autowired
    private EduCourseTeachingGroupMapper teachingGroupMapper;
    @Autowired
    private GyXjEduStudentCourseExtMapper courseExtMapper;

    @Override
    public int save(EduStudentCourse eduStudentCourse) {
        //选课容纳人数实时校验
        Map<String,Object> parMp = new HashMap<>();
        parMp.put("recordFlow",eduStudentCourse.getRecordFlow());
        parMp.put("courseFlow",eduStudentCourse.getCourseFlow());
        parMp.put("studentPeriod",eduStudentCourse.getStudentPeriod());
        parMp.put("classNumber","gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))?null:eduStudentCourse.getStudentPeriod());//广医大上课人数统计包括往届，南医大则统计本届
        List<Map<String,Object>> rltList = courseExtMapper.queryChoosedCourseNum(parMp);
        if(null != rltList && rltList.size() > 0){
            String userCount = rltList.get(0).get("CHOOSED_COUNT").toString();
            String count = rltList.get(0).get("COURSE_USER_COUNT").toString();
            if(Integer.parseInt(userCount)>=Integer.parseInt(count)){//课程容纳人数已满
                return 0;
            }
        }
        if (StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())) {
            GeneralMethod.setRecordInfo(eduStudentCourse, false);
            return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
        } else {
            eduStudentCourse.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(eduStudentCourse, true);
            return studentCourseMapper.insertSelective(eduStudentCourse);

        }
    }

    @Override
    public int emptyCourse(EduStudentCourse eduStudentCourse) {
        if (StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())) {
            GeneralMethod.setRecordInfo(eduStudentCourse, false);
            return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
        } else {
            eduStudentCourse.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(eduStudentCourse, true);
            return studentCourseMapper.insertSelective(eduStudentCourse);

        }
    }

    public int update(EduStudentCourse course) {
        GeneralMethod.setRecordInfo(course, false);
        return studentCourseMapper.updateByPrimaryKeySelective(course);
    }


    @Override
    public Map<String, Object> searchCourseCreditForm(
            String courseFlow) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("courseFlow", courseFlow);
        List<XjOneCourseCreditForm> fromList = this.studentCourseExtMapper.searchCourseCreditForm(paramMap);
        Map<String, Object> userAndCourseCreditMap = new HashMap<String, Object>();
        if (fromList != null && !fromList.isEmpty()) {
            for (XjOneCourseCreditForm form : fromList) {
                userAndCourseCreditMap.put(form.getUserFlow(), form.getCourseCredit());
            }
        }
        return userAndCourseCreditMap;
    }


    @Override
    public List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse) {
        EduStudentCourseExample example = new EduStudentCourseExample();
        Criteria criteria = example.createCriteria();
        addCriteria(studentCourse, criteria);
        return studentCourseMapper.selectByExample(example);
    }
    @Override
    public EduStudentCourse searchStudentCourse(String recordFlow) {
        if(StringUtil.isBlank(recordFlow))
        {
            return null;
        }
        return studentCourseMapper.selectByPrimaryKey(recordFlow);
    }
    private void addCriteria(EduStudentCourse studentCourse, Criteria criteria) {
        if (StringUtil.isNotBlank(studentCourse.getCourseFlow())) {
            criteria.andCourseFlowEqualTo(studentCourse.getCourseFlow());
        }
        if (StringUtil.isNotBlank(studentCourse.getUserFlow())) {
            criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
        }
        if (StringUtil.isNotBlank(studentCourse.getCourseTypeId())) {
            criteria.andCourseTypeIdEqualTo(studentCourse.getCourseTypeId());
        }
        if (StringUtil.isNotBlank(studentCourse.getRecordStatus())) {
            criteria.andRecordStatusEqualTo(studentCourse.getRecordStatus());
        }
        if (StringUtil.isNotBlank(studentCourse.getAuditFlag())) {
            criteria.andAuditFlagEqualTo(studentCourse.getAuditFlag());
        }
        if (StringUtil.isNotBlank(studentCourse.getReplenishFlag())) {
            criteria.andReplenishFlagEqualTo(studentCourse.getReplenishFlag());
        }
        if (StringUtil.isNotBlank(studentCourse.getStudentPeriod())) {
            criteria.andStudentPeriodEqualTo(studentCourse.getStudentPeriod());
        }
        if (StringUtil.isNotBlank(studentCourse.getCourseCode())) {
            criteria.andCourseCodeEqualTo(studentCourse.getCourseCode());
        }
        if (StringUtil.isNotBlank(studentCourse.getImpFlow())) {
            criteria.andImpFlowEqualTo(studentCourse.getImpFlow());
        }
    }

    @Override
    public List<EduStudentCourse> searchStudentCourseList(List<String> courseFlowList) {
        EduStudentCourseExample example = new EduStudentCourseExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (courseFlowList != null && courseFlowList.size() > 0) {
            criteria.andCourseFlowIn(courseFlowList);
        }
        return this.studentCourseMapper.selectByExample(example);
    }


//	@Override
//	public List<XjStudentCourseNumForm> studentCourseNumList(
//			Map<String, Object> map) {
//
//		return studentCourseExtMapper.selectStudentCourse(map);
//	}


    @Override
    public int saveStudentCourseByCourseFlowList(String studentPeriod, String userFlow, List<EduStudentCourse> studentCourseList, String replenishFlag,String saveBeforeFlag) {
        int recordCount = GlobalConstant.ZERO_LINE; //存储记录数
        if (GlobalConstant.FLAG_Y.equals(replenishFlag)) {//补选
            if (studentCourseList != null && !studentCourseList.isEmpty()) {
                //查询状态为N的(含非补选)  修改为补选
                EduStudentCourse statusN = new EduStudentCourse();
                statusN.setStudentPeriod(studentPeriod);
                statusN.setUserFlow(userFlow);
                statusN.setRecordStatus(GlobalConstant.FLAG_N);
                List<EduStudentCourse> oldStatusNList = searchStudentCourseList(statusN);
                for (EduStudentCourse esc : studentCourseList) {
                    esc.setStudentPeriod(studentPeriod);
                    esc.setUserFlow(userFlow);
                    esc.setReplenishFlag(GlobalConstant.FLAG_Y);
                    esc.setChooseTime(DateUtil.getCurrDateTime());
                    if (oldStatusNList != null && !oldStatusNList.isEmpty()) {
                        boolean addFlag = true;
                        for (EduStudentCourse oldN : oldStatusNList) {
                            if (oldN.getCourseFlow().equals(esc.getCourseFlow())) {
                                addFlag = false;
                                oldN.setRecordStatus(GlobalConstant.FLAG_Y);
                                oldN.setReplenishFlag(GlobalConstant.FLAG_Y);
                                oldN.setChooseTime(DateUtil.getCurrDateTime());
                                save(oldN);
                                break;
                            }
                        }
                        if (addFlag) {//新增
                            save(esc);
                        }
                    } else {//全部新增
                        save(esc);
                    }
                    recordCount++;
                }
            }
            return recordCount;
        } else {//选课
            EduStudentCourse exitStudentCourse = new EduStudentCourse();
            exitStudentCourse.setStudentPeriod(studentPeriod);
            exitStudentCourse.setUserFlow(userFlow);
            List<EduStudentCourse> exitStudentCourseList = searchStudentCourseList(exitStudentCourse);
            Map<String, EduStudentCourse> deleMap = new HashMap<String, EduStudentCourse>();//记录需要删除的
            List<EduStudentCourse> oldStatusYList = new ArrayList<EduStudentCourse>();//状态为Y
            List<EduStudentCourse> oldStatusNList = new ArrayList<EduStudentCourse>();//状态为N
            if (exitStudentCourseList != null && !exitStudentCourseList.isEmpty()) {
                for (EduStudentCourse sc : exitStudentCourseList) {
                    if (GlobalConstant.RECORD_STATUS_Y.equals(sc.getRecordStatus())) {
                        deleMap.put(sc.getRecordFlow(), sc);
                        oldStatusYList.add(sc);
                    } else {
                        oldStatusNList.add(sc);
                    }
                }
            }
            if (studentCourseList != null && !studentCourseList.isEmpty()) {
                for (EduStudentCourse esc : studentCourseList) {
                    String courseFlow = esc.getCourseFlow();
                    boolean addFlag = true;
                    if (oldStatusYList != null && !oldStatusYList.isEmpty()) {
                        for (EduStudentCourse oldY : oldStatusYList) {
                            if (courseFlow.equals(oldY.getCourseFlow())) {
                                addFlag = false;
                                if (deleMap.size() > 0) {
                                    deleMap.remove(oldY.getRecordFlow());//不删除
                                    oldY.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                                    oldY.setChooseTime(DateUtil.getCurrDateTime());
                                    oldY.setDegreeCourseFlag(esc.getDegreeCourseFlag());
                                    oldY.setCourseTypeId(esc.getCourseTypeId());
                                    oldY.setCourseTypeName(esc.getCourseTypeName());
                                    save(oldY);//更新历史数据
                                }
                                break;
                            }
                        }
                    }
                    if (addFlag) {
                        esc.setStudentPeriod(studentPeriod);
                        esc.setUserFlow(userFlow);
                        esc.setChooseTime(DateUtil.getCurrDateTime());
                        if (oldStatusNList != null && !oldStatusNList.isEmpty()) {
                            for (EduStudentCourse oldN : oldStatusNList) {
                                if (courseFlow.equals(oldN.getCourseFlow())) {
                                    addFlag = false;
                                    oldN.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                                    oldN.setChooseTime(DateUtil.getCurrDateTime());
                                    oldN.setDegreeCourseFlag(esc.getDegreeCourseFlag());
                                    oldN.setCourseTypeId(esc.getCourseTypeId());
                                    oldN.setCourseTypeName(esc.getCourseTypeName());
                                    save(oldN);
                                    break;
                                }
                            }
                            if (addFlag) {//新增
                                save(esc);
                            }
                        } else {//新增
                            save(esc);
                        }
                    }
                }
            }
            //删除
            if (deleMap.size() > 0) {
                for (Entry<String, EduStudentCourse> entry : deleMap.entrySet()) {
                    EduStudentCourse delEduStudentCourse = entry.getValue();
                    delEduStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    delEduStudentCourse.setAuditFlag(GlobalConstant.RECORD_STATUS_N);
                    save(delEduStudentCourse);
                }
            }
            //修改EduUser为已选课状态
            EduUser eduUser = new EduUser();
            eduUser.setUserFlow(userFlow);
            eduUser.setChooseCourseStatusId(XjChooseCourseStatusEnum.Choose.getId());
            eduUser.setChooseCourseStatusName(XjChooseCourseStatusEnum.Choose.getName());
            if(StringUtil.isNotBlank(saveBeforeFlag)&&GlobalConstant.RECORD_STATUS_Y.equals(saveBeforeFlag)){
//                eduUser.setChooseCourseStatusId(XjChooseCourseStatusEnum.Save.getId());
//                eduUser.setChooseCourseStatusName(XjChooseCourseStatusEnum.Save.getName());
            }
            return eduUserBiz.saveEduUser(eduUser);
        }
    }

    @Override
    public List<XjStudentCourseNumForm> searchStudentCourseChooseCount(Map<String, Object> paramMap) {
        return studentCourseExtMapper.searchStudentCourseChooseCount(paramMap);
    }

    @Override
    public int saveCourseMaintain(EduStudentCourse studentCourse) {
        String studentPeriod = studentCourse.getStudentPeriod();
        String userFlow = studentCourse.getUserFlow();
        String courseFlow = studentCourse.getCourseFlow();
        if (StringUtil.isNotBlank(studentPeriod) && StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(courseFlow)) {
            EduStudentCourse search = new EduStudentCourse();
            search.setStudentPeriod(studentPeriod);
            search.setUserFlow(userFlow);
            search.setCourseFlow(courseFlow);
            List<EduStudentCourse> studentCourseList = searchStudentCourseList(search);
            if (studentCourseList != null && !studentCourseList.isEmpty()) {//修改
                search = studentCourseList.get(0);
                String recordStatus = studentCourse.getRecordStatus();
                search.setRecordStatus(recordStatus);
                search.setReplenishFlag(studentCourse.getReplenishFlag());
                if (GlobalConstant.RECORD_STATUS_Y.equals(recordStatus)) {//修改选课时间
                    search.setChooseTime(DateUtil.getCurrDateTime());
                }
                //search.setReplenishFlag(GlobalConstant.FLAG_N);//取消补选？
                return save(search);
            } else {//新增
                studentCourse.setChooseTime(DateUtil.getCurrDateTime());
                return save(studentCourse);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }


    @Override
    public Map<String, Object> extractStudentCourseMapByCourseType(String studentPeriod, String userFlow) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        EduStudentCourse searchStudentCourse = new EduStudentCourse();
        searchStudentCourse.setStudentPeriod(studentPeriod);
        searchStudentCourse.setUserFlow(userFlow);
        searchStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<EduStudentCourse> studentCourseList = searchStudentCourseList(searchStudentCourse);
        if (studentCourseList != null && !studentCourseList.isEmpty()) {
            Map<String, Map<String, EduStudentCourse>> chooseCourseMap = new HashMap<String, Map<String, EduStudentCourse>>();
            for (EduStudentCourse sc : studentCourseList) {
                Map<String, EduStudentCourse> courseMap = chooseCourseMap.get(sc.getCourseTypeId());
                if (courseMap == null) {
                    courseMap = new HashMap<String, EduStudentCourse>();
                }
                courseMap.put(sc.getCourseFlow(), sc);
                chooseCourseMap.put(sc.getCourseTypeId(), courseMap);
            }
            resultMap.put("chooseCourseMap", chooseCourseMap);
        }
        return resultMap;
    }


    @Override
    public List<EduStudentCourse> searchStudentCourseListByUserFlowList(List<String> userFlowList) {
        EduStudentCourseExample example = new EduStudentCourseExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (userFlowList != null && !userFlowList.isEmpty()) {
            criteria.andUserFlowIn(userFlowList);
        }
        return studentCourseMapper.selectByExample(example);
    }


    @Override
    public List<XjEduStudentCourseExt> searchStudentCourseExtWithUserList(String planYear, String courseFlow) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("studentPeriod", planYear);
        paramMap.put("courseFlow", courseFlow);
        paramMap.put("classNumber","gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))?null:planYear);//广医大上课人数统计包括往届，南医大则统计本届
        return studentCourseExtMapper.searchStudentCourseExtWithUserList(paramMap);
    }

    @Override
    public String createQrCodeForGrade(String userFlow) throws Exception {
        String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "grade";
        // String filePath=System.getProperty("user.home")+File.separator+"grade";
        String fileName = userFlow + ".png";
        File file = new File(filePath + File.separator + fileName);
        file.mkdirs();
        String content = InitConfig.getSysCfg("qr_grade_search_url") + "/gyxjgl/student/resultSun?userFlow=" + userFlow;// 内容
        int width = 100; // 图像宽度
        int height = 100; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
        return fileName;
    }

    @Override
    public String decodeQrCodeForGrade(String userFlow) throws Exception {
        String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "grade" + File.separator;
        //String filePath=System.getProperty("user.home")+File.separator+"grade";
        String fileName = userFlow + ".png";
        filePath += fileName;
        BufferedImage image;
        File imgFile = new File(filePath);
        if (imgFile.isFile()) {
            image = ImageIO.read(imgFile);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
            return result.getText().trim();
        }
        return null;
    }

    @Override
    public int deleteCourse(String recordFlow) {
        int result = 0;
        if (StringUtil.isNotBlank(recordFlow)) {
            result = studentCourseMapper.deleteByPrimaryKey(recordFlow);
        }
        return result;
    }

    @Override
    public List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser,EduUser eduUser,ResDoctor doctor,EduStudentCourse studentCourse,String scoreSum,String minDegreeGrade,String maxDegreeGrade,String courseTypeScore) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", sysUser.getUserName());
        paramMap.put("sid", eduUser.getSid());
        paramMap.put("trainOrgId", eduUser.getTrainOrgId());
        paramMap.put("impFlow", studentCourse.getImpFlow());
        paramMap.put("studentPeriod", studentCourse.getStudentPeriod());
        paramMap.put("gradeTermId", studentCourse.getGradeTermId());
        paramMap.put("courseName", studentCourse.getCourseName());
        paramMap.put("resDoctor", doctor);
        paramMap.put("scoreSum", scoreSum);//总学分
        paramMap.put("minDegreeGrade", minDegreeGrade);//学位课程成绩最小值
        paramMap.put("maxDegreeGrade", maxDegreeGrade);//学位课程成绩最大值
        paramMap.put("courseTypeId", studentCourse.getCourseTypeId());//课程类型
        paramMap.put("courseTypeScore", courseTypeScore);//各课程类型下的学分
        paramMap.put("gyFlag", GlobalConstant.RECORD_STATUS_Y);//广医标识
        paramMap.put("courseGrade", studentCourse.getCourseGrade());//成绩
        //广医现要求：未审批通过仍然显示
//        if(eduUser!=null&&GlobalContext.getCurrentUser().getUserFlow().equals(eduUser.getUserFlow())){//学生查看 过滤未审核通过的成绩
//            paramMap.put("studentFlag",GlobalConstant.RECORD_STATUS_Y);
//        }
        return studentCourseExtMapper.searchStudentCourse(paramMap);
    }

    @Override
    public int reOpenChooseCourse(String userFlow) {
        EduUser user = eduUserBiz.readEduUser(userFlow);
        user.setChooseCourseStatusId(XjChooseCourseStatusEnum.UnChoose.getId());
        user.setChooseCourseStatusName(XjChooseCourseStatusEnum.UnChoose.getName());
        return eduUserBiz.saveEduUser(user);
    }

    @Override
    public List<Map<String, Object>> getScoreSum(Map<String, Object> paramMap) {
        return studentCourseExtMapper.getScoreSum(paramMap);
    }
    @Override
    public List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser,EduUser eduUser,EduStudentCourse studentCourse) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userFlow",  GlobalContext.getCurrentUser().getUserFlow());
        paramMap.put("userName", sysUser.getUserName());
        paramMap.put("sid", eduUser.getSid());
        paramMap.put("impFlow", studentCourse.getImpFlow());
        paramMap.put("studentPeriod", studentCourse.getStudentPeriod());
        paramMap.put("gradeTermId", studentCourse.getGradeTermId());
        paramMap.put("courseGrade", studentCourse.getCourseGrade());// 1有成绩 2没有成绩
        paramMap.put("submitFlag", studentCourse.getSubmitFlag());
        paramMap.put("auditStatusId", studentCourse.getAuditStatusId());
        paramMap.put("studyWayId", studentCourse.getStudyWayId());
        paramMap.put("trainTypeId", eduUser.getTrainTypeId());
        paramMap.put("trainCategoryId", eduUser.getTrainCategoryId());
        paramMap.put("studyFormId", eduUser.getStudyFormId());
        return studentCourseExtMapper.searchStudentCourseByTeacherGroup(paramMap);
    }
    @Override
    public List<Map<String, Object>> getChoosedCourseStus(Map<String, Object> paramMap) {
        return studentCourseExtMapper.getChoosedCourseStus(paramMap);
    }

    @Override
    public List<Map<String, Object>> getCourseGroupList(Map<String, String> parMp) {
        return studentCourseExtMapper.getCourseGroupList(parMp);
    }

    @Override
    public int saveCourseGroup(EduCourseTeachingGroup group) {
        int count = 0;
        EduCourseTeachingGroupExample example = new EduCourseTeachingGroupExample();
        if(StringUtil.isNotBlank(group.getRecordFlow())){
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andCourseFlowEqualTo(group.getCourseFlow()).andRecordFlowNotEqualTo(group.getRecordFlow());
            if(teachingGroupMapper.selectByExample(example).size() > 0){
                return -1;//该课程授课组已存在
            };
            GeneralMethod.setRecordInfo(group,false);
            count = teachingGroupMapper.updateByPrimaryKeySelective(group);
        }else{
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(group.getCourseFlow());
            EduCourseTeachingGroupExample.Criteria criteria = example.createCriteria().andCourseFlowEqualTo(group.getCourseFlow());
            example.or(criteria);
            if(teachingGroupMapper.selectByExample(example).size() > 0){
                return -2;//该课程或者该授课组已维护
            };
            group.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(group,true);
            count = teachingGroupMapper.insertSelective(group);
        }
        return count;
    }

    @Override
    public List<Map<String, Object>> getGradeAuditGroupList(Map<String, String> parMp) {
        return studentCourseExtMapper.getGradeAuditGroupList(parMp);
    }

    @Override
    public List<Map<String, Object>> getGradeAuditStus(Map<String, String> parMp) {
        return studentCourseExtMapper.getGradeAuditStus(parMp);
    }

    @Override
    public int auditSigleGroupGrade(String courseCode) {
        return studentCourseExtMapper.auditSigleGroupGrade(courseCode);
    }

    @Override
    public int auditSelectGrade(String recordFlow,String auditStatusId) {
        EduStudentCourse record = new EduStudentCourse();
        record.setRecordFlow(recordFlow);
        if("UnPassed".equals(auditStatusId)){
            record.setAuditStatusId("UnPassed");
            record.setAuditStatusName("不通过");
        }else{
            record.setAuditStatusId("Passed");
            record.setAuditStatusName("通过");
        }
        GeneralMethod.setRecordInfo(record,false);
        return studentCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public EduCourseTeachingGroup searchCourseGroupByFlow(String userFlow) {
        EduCourseTeachingGroupExample example = new EduCourseTeachingGroupExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow);
        List<EduCourseTeachingGroup> groups = teachingGroupMapper.selectByExample(example);
        return (null!=groups && groups.size()>0)?groups.get(0):null;
    }

    @Override
    public int saveDegreeCourse(EduStudentCourse eduStudentCourse) {
        if (StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())) {
            GeneralMethod.setRecordInfo(eduStudentCourse, false);
            return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
        } else {
            eduStudentCourse.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(eduStudentCourse, true);
            return studentCourseMapper.insertSelective(eduStudentCourse);

        }
    }

    @Override
    public Map<String, Object> queryBaseInfo(String userFlow) {
        return studentCourseExtMapper.queryBaseInfo(userFlow);
    }

    @Override
    public List<Map<String, Object>> queryGradeList(String userFlow) {
        return studentCourseExtMapper.queryGradeList(userFlow);
    }

    @Override
    public List<Map<String, Object>> queryScheduleClass(Map map) {
        return studentCourseExtMapper.queryScheduleClass(map);
    }
}
