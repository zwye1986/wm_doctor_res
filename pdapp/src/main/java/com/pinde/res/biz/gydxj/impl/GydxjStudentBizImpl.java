package com.pinde.res.biz.gydxj.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjStudentBiz;
import com.pinde.res.dao.gydxj.ext.GyXjEduNoticeExtMapper;
import com.pinde.res.enums.gydxj.UserChangeApplyStatusEnum;
import com.pinde.res.enums.gydxj.UserChangeApplyTypeEnum;
import com.pinde.res.model.gydxj.mo.SubmitApplyForm;
import com.pinde.res.model.gydxj.mo.XjEduUserExtInfoForm;
import com.pinde.res.model.gydxj.mo.XjEduUserForm;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GydxjStudentBizImpl implements IGydxjStudentBiz {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ResDoctorMapper doctorMapper;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Resource
    private GyXjEduNoticeExtMapper eduExtMapper;
    @Resource
    private EduUserChangeApplyMapper applyMapper;
    @Resource
    private EduStudentCourseMapper stuCourseMapper;
    @Resource
    private XjglSignClassMapper classMapper;
    @Resource
    private EduScheduleClassMapper scheduleMapper;
    @Resource
    private EduScheduleTeacherMapper schTeaMapper;
    @Resource
    private EduUserInfoStatusMapper infoStatusMapper;

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
    public int updateUser(SysUser user) {
        if(StringUtil.isNotBlank(user.getUserFlow())){
            return sysUserMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }

    @Override
    public int editDoctor(ResDoctor doctor) {
        if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
            return doctorMapper.updateByPrimaryKeySelective(doctor);
        }
        return 0;
    }

    @Override
    public int saveEduUser(EduUser eduUser) {
        if (StringUtil.isNotBlank(eduUser.getUserFlow())) {
            return eduUserMapper.updateByPrimaryKeySelective(eduUser);
        }
        return 0;
    }

    @Override
    public int save(XjEduUserForm form) throws Exception {
        if (form != null) {
            updateUser(form.getSysUser());
            editDoctor(form.getResDoctor());
            if (form.getEduUser() != null) {
                XjEduUserExtInfoForm eduUserExtInfoForm = form.getEduUserExtInfoForm();
                String content = getXmlFromExtInfo(eduUserExtInfoForm);
                form.getEduUser().setContent(content);
                return saveEduUser(form.getEduUser());
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

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
            xmlBody=doc.asXML();
        }
        return xmlBody;
    }
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
    public List<Map<String, Object>> searchApplyList(Map<String, Object> map) {
        return eduExtMapper.searchApplyList(map);
    }

    @Override
    public List<Map<String, Object>> classScheduleList(String classTime,String userFlow,String termFlow) {
        return eduExtMapper.classScheduleList(classTime,userFlow,termFlow);
    }

    @Override
    public List<Map<String,Object>> searchEduCourse(Map<String, Object> map) {
        return eduExtMapper.searchEduCourse(map);
    }

    @Override
    public List<Map<String, Object>> searchChooseCourse(Map<String, Object> map) {
        return eduExtMapper.searchChooseCourse(map);
    }

    @Override
    public Map<String, Object> searchNeedData() {
//        return eduExtMapper.searchNeedData();
        return null;
    }

    @Override
    public List<Map<String,Object>> searchStuCourse(Map<String, Object> map) {
        return eduExtMapper.searchStuCourse(map);
    }

    @Override
    public EduUserChangeApply readEduUserChangeApply(String recordFlow) {
        return applyMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<EduCourse> searchStuCourseList(String userFlow) {
        return eduExtMapper.searchStuCourseList(userFlow);
    }

    @Override
    public int saveApply(SubmitApplyForm form, EduUserChangeApply apply) {
        if(StringUtil.isNotBlank(apply.getRecordFlow())){
            String xml= JaxbUtil.convertToXml(form);
            apply.setContent(xml);
            applyMapper.updateByPrimaryKeySelective(apply);
        }else{
            apply.setRecordFlow(PkUtil.getUUID());
            apply.setApplyTime(DateUtil.getCurrDate());
            apply.setApplyTypeName(UserChangeApplyTypeEnum.getNameById(apply.getApplyTypeId()));
            String xml=JaxbUtil.convertToXml(form);
            apply.setContent(xml);
            apply.setStatusId(UserChangeApplyStatusEnum.Save.getId());
            apply.setStatusName(UserChangeApplyStatusEnum.Save.getName());
            apply.setRecordStatus(GlobalConstant.FLAG_Y);
            applyMapper.insertSelective(apply);
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public List<String> queryExitCourseFlow(Map<String, Object> map) {
        return eduExtMapper.queryExitCourseFlow(map);
    }

    @Override
    public int saveStuCourse(EduCourse course, String userFlow, String studentPeriod, String roleId) {
        EduStudentCourse record = new EduStudentCourse();
        record.setRecordFlow(PkUtil.getUUID());
        record.setUserFlow(userFlow);
        record.setCourseFlow(course.getCourseFlow());
        record.setCourseCode(course.getCourseCode());
        record.setCourseName(course.getCourseName());
        record.setCourseNameEn(course.getCourseNameEn());
        record.setChooseTime(DateUtil.getCurrDateTime());
        if("Teacher".equals(roleId)){
            record.setReplenishFlag(GlobalConstant.FLAG_N);
        }
        record.setRecordStatus(GlobalConstant.FLAG_Y);
        record.setCreateTime(DateUtil.getCurrDateTime());
        record.setCreateUserFlow(userFlow);
        record.setCourseTypeId(course.getCourseTypeId());
        record.setCourseTypeName(course.getCourseTypeName());
        record.setCourseCredit(course.getCourseCredit());
        record.setCoursePeriod(course.getCoursePeriod());
        record.setStudentPeriod(studentPeriod);
        return stuCourseMapper.insertSelective(record);
    }

    @Override
    public int delStuCourse(Map<String, Object> map) {
        return eduExtMapper.delStuCourse(map);
    }

    @Override
    public List<EduScheduleClass> searchClassList(Map<String, Object> map) {
        String courseFlow = (String)map.get("courseFlow");
        String curDate = (String)map.get("curDate");
        String curTime = (String)map.get("curTime");
        EduScheduleClassExample example = new EduScheduleClassExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCourseFlowEqualTo(courseFlow).andClassTimeEqualTo(curDate).andClassOrderEqualTo(curTime);
        return scheduleMapper.selectByExample(example);
    }

    @Override
    public int signClass(Map<String, Object> map) {
        String userFlow = (String)map.get("userFlow");
        String classFlow = (String)map.get("classFlow");
        XjglSignClassExample example = new XjglSignClassExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow).andClassFlowEqualTo(classFlow);
        List<XjglSignClass> list = classMapper.selectByExample(example);
        if(null != list && list.size()>0){
            return 0;
        }
        XjglSignClass record = new XjglSignClass();
        record.setRecordFlow(PkUtil.getUUID());
        record.setClassFlow(classFlow);
        record.setUserFlow(userFlow);
        record.setSignTime((String)map.get("signTime"));
        record.setRecordStatus(GlobalConstant.FLAG_Y);
        record.setCreateTime(DateUtil.getCurrentTime());
        record.setCreateUserFlow((String)map.get("userFlow"));
        return classMapper.insertSelective(record);
    }

    @Override
    public int submitApply(String recordFlow) {
        EduUserChangeApply record = new EduUserChangeApply();
        record.setRecordFlow(recordFlow);
        record.setStatusId(UserChangeApplyStatusEnum.Submit.getId());
        record.setStatusName(UserChangeApplyStatusEnum.Submit.getName());
        return applyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<EduScheduleTeacher> searchTeacherByClass(String recordFlow) {
        EduScheduleTeacherExample example = new EduScheduleTeacherExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andScheduleClassFlowEqualTo(recordFlow);
        return schTeaMapper.selectByExample(example);
    }

    @Override
    public int confirmStatus(String userFlow, String partId) {
        EduUserInfoStatus record = new EduUserInfoStatus();
        record.setPartStatus(GlobalConstant.FLAG_Y);
        EduUserInfoStatusExample example = new EduUserInfoStatusExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow).andPartIdEqualTo(partId);
        return infoStatusMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int searchEduStuCourse(String userFlow, String courseFlow) {
        EduStudentCourseExample example = new EduStudentCourseExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow).andCourseFlowEqualTo(courseFlow);
        return stuCourseMapper.countByExample(example);
    }
}
