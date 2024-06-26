package com.pinde.sci.biz.study.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.study.IHospitalManageBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.StudySubjectMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.study.StudySubjectExtMapper;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieshihai
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HospitalManageImpl implements IHospitalManageBiz {

    @Autowired
    private StudySubjectMapper studySubjectMapper;
    @Autowired
    private StudySubjectExtMapper studySubjectExtMapper;
    @Autowired
    private ResDoctorMapper resDoctorMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IDictBiz dictBiz;

    @Override
    public int addSubject(StudySubject studySubject) {
        if(StringUtil.isBlank(studySubject.getSubjectFlow()))
        {
            studySubject.setSubjectFlow(PkUtil.getUUID());
            studySubject.setCreateTime(DateUtil.getCurrentTime());
            studySubject.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(studySubject,true);
            return  studySubjectMapper.insert(studySubject);
        }else{
            studySubject.setModifyTime(DateUtil.getCurrTime());
            studySubject.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(studySubject,false);
            return  studySubjectMapper.updateByPrimaryKeySelective(studySubject);
        }
    }

    @Override
    public List<StudySubject> subjectList(StudySubject subject) {
        StudySubjectExample example=new StudySubjectExample();
        StudySubjectExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(subject.getSubjectName()))
        {
            criteria.andSubjectNameLike("%"+subject.getSubjectName()+"%");
        }
        if(StringUtil.isNotBlank(subject.getSubjectYear()))
        {
            criteria.andSubjectYearEqualTo(subject.getSubjectYear());
        }
        if(StringUtil.isNotBlank(subject.getSubjectType()))
        {
            criteria.andSubjectTypeEqualTo(subject.getSubjectType());
        }
        example.setOrderByClause(" subject_year desc,subject_start_time desc,subject_end_time desc");
        return studySubjectMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> searchStudents(Map<String, Object> paramMap) {
        return studySubjectExtMapper.searchStudents(paramMap);
    }

    @Override
    public void saveUser(SysUser user, ResDoctor doctor) {
        String userFlow = PkUtil.getUUID();
        user.setUserFlow(userFlow);
        user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
        user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "123456"));
        user.setStatusId(UserStatusEnum.Activated.getId());
        user.setStatusDesc(UserStatusEnum.Activated.getName());
        GeneralMethod.setRecordInfo(user, true);
        //增加学员角色

        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        String currWsId = "study";
        userRole.setWsId(currWsId);
        userRole.setRoleFlow(InitConfig.getSysCfg("study_doctor_role_flow"));
        userRole.setAuthTime(DateUtil.getCurrDate());
        userRoleBiz.saveSysUserRole(userRole);

        userMapper.insert(user);
        doctor.setDoctorFlow(userFlow);
        doctor.setOscaStudentSubmit("Y");
        GeneralMethod.setRecordInfo(doctor,true);
        resDoctorMapper.insert(doctor);
    }

    @Override
    public int importUserFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel3(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public StudySubject readSubject(String subjectFlow) {
        return  studySubjectMapper.selectByPrimaryKey(subjectFlow);
    }

    @Override
    public List<Map<String, String>> detailList(Map<String, String> param) {
        return studySubjectExtMapper.detailList(param);
    }

    @Override
    public void auditBack(List<String> detailFlows) {
         studySubjectExtMapper.auditBack(detailFlows);
    }
    @Override
    public void auditPassed(List<String> detailFlows) {
         studySubjectExtMapper.auditPassed(detailFlows);
    }
    @Override
    public void auditUnPassed(List<String> detailFlows) {
         studySubjectExtMapper.auditUnPassed(detailFlows);
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
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    private int parseExcel3(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }

            List<SysOrg> orgList = orgBiz.queryAllSysOrg(null);
            Map<String,SysOrg> orgMap=new HashMap<>();
            if(orgList!=null)
            {
                for(SysOrg org:orgList)
                {
                    orgMap.put(org.getOrgName(),org);
                }
            }
            for(int i = 1;i <= row_num; i++){
                Row r =  sheet.getRow(i);
                SysUser sysUser = new SysUser();
                String userName;
                String idNo;
                String userEmail;
                String userPhone;
                String orgFlow;
                String orgName;
                String deptFlow;
                String deptName;
                String postName;
                String userCode;
                String trainingYears="";
                int year = 0;
                ResDoctor resDoctor = new ResDoctor();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
					/* 用户编号	员工姓名	身份证  邮件电话 	机构编号	机构名称	科室编号	科室名称	职务	 登录名*/
                    if("性别".equals(colnames.get(j))) {
                        sysUser.setSexName(value);
                        for(UserSexEnum e:UserSexEnum.values())
                        {
                            if(e.getName().equals(value))
                            {
                                sysUser.setSexId(e.getId());
                            }
                        }
                        if(StringUtil.isBlank(sysUser.getSexId()))
                        {
                            throw new RuntimeException("导入失败！第"+ (count+2) +"行，性别输入错误！");
                        }
                    }else if("姓名".equals(colnames.get(j))){
                        userName = value;
                        sysUser.setUserName(userName);
                        resDoctor.setDoctorName(userName);
                    }else if("证件类型".equals(colnames.get(j))){
                        sysUser.setCretTypeName(value);
                        for(CertificateTypeEnum e:CertificateTypeEnum.values())
                        {
                            if(e.getName().equals(value))
                            {
                                sysUser.setCretTypeId(e.getId());
                            }
                        }
                        if(StringUtil.isBlank(sysUser.getCretTypeId()))
                        {
                            throw new RuntimeException("导入失败！第"+ (count+2) +"行，证件类型输入错误！");
                        }
                    }else if("证件号码".equals(colnames.get(j))){
                        idNo = value;
                        sysUser.setIdNo(idNo);
                    }else if("注册邮箱（用户名）".equals(colnames.get(j))){
                        userEmail = value;
                        sysUser.setUserEmail(userEmail);
                        sysUser.setUserCode(userEmail);
                    }else if("手机号码".equals(colnames.get(j))) {
                        userPhone = value;
                        sysUser.setUserPhone(userPhone);
                    }else if("培训基地".equals(colnames.get(j))){
                        sysUser.setOrgName(value);
                        SysOrg org=orgMap.get(value);
                        if(org!=null)
                        {
                            sysUser.setOrgFlow(org.getOrgFlow());
                        }
                        if(StringUtil.isBlank(sysUser.getOrgFlow()))
                        {
                            throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训基地输入错误！");
                        }
                    }else if("培训类型".equals(colnames.get(j))){
                        resDoctor.setTrainingTypeName(value);
                    }else if("培训专业".equals(colnames.get(j))){
                        resDoctor.setTrainingSpeName(value);
                    }else if("培养年限".equals(colnames.get(j))){
                        switch (value){
                            case "一年":{
                                trainingYears="OneYear";
                                year=1;
                                break;}
                            case "两年":{
                                trainingYears="TwoYear";
                                year=2;
                                break;}
                            case "三年":{
                                year=3;
                                trainingYears="ThreeYear";
                                break;}
                        }
                        resDoctor.setTrainingYears(trainingYears);
                    }else if("年级".equals(colnames.get(j))){
                        resDoctor.setSessionNumber(value);
                    }else if("所在单位".equals(colnames.get(j))){
                        resDoctor.setWorkOrgName(value);
                    }
                }
                if(StringUtil.isNotBlank(sysUser.getUserPhone()))
                {

                  SysUser  user = userBiz.findByUserPhone(sysUser.getUserPhone());
                    if(user!=null)
                    {
                        throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在手机号为"+sysUser.getUserPhone()+"的用户");
                    }
                }
                int sessionValue = Integer.parseInt(resDoctor.getSessionNumber());
                String graduationYear = String.valueOf(sessionValue+year);
                resDoctor.setGraduationYear(graduationYear);
                if(StringUtil.isBlank(sysUser.getUserName())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，姓名不能为空！");
                }
                if(StringUtil.isBlank(resDoctor.getTrainingTypeName())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型不能为空！");
                }
                String trainingTypeId = getDictId(resDoctor.getTrainingTypeName(), DictTypeEnum.CatSpeType.getId());
                resDoctor.setTrainingTypeId(trainingTypeId);
                if(StringUtil.isBlank(resDoctor.getTrainingTypeId())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型有误，请检查名称！");
                }
                if(StringUtil.isBlank(resDoctor.getTrainingSpeName())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业不能为空！");
                }
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeId(DictTypeEnum.CatSpeType.getId()+"."+trainingTypeId);
                sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
                boolean speFlag = false;
                if(sysDictList!=null&&sysDictList.size()>0){
                    for(SysDict dict:sysDictList){
                        if(resDoctor.getTrainingSpeName().equals(dict.getDictName())){
                            resDoctor.setTrainingSpeId(dict.getDictId());
                            speFlag = true;
                            break;
                        }
                    }
                }
                if(!speFlag){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业有误，请检查名称！");
                }
                if(StringUtil.isBlank(resDoctor.getSessionNumber())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，年级不能为空！");
                }

                resDoctor.setOrgFlow(sysUser.getOrgFlow());
                resDoctor.setOrgName(sysUser.getOrgName());
                if(StringUtil.isBlank(sysUser.getUserCode())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，注册邮箱不能为空！");
                }
                //验证惟一用户登录名
                if(StringUtil.isNotBlank(sysUser.getUserCode())){
                    SysUserExample example=new SysUserExample();
                    example.createCriteria().andUserCodeEqualTo(sysUser.getUserCode())
                            .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                    List<SysUser> sysUserList = userMapper.selectByExample(example);
                    if(sysUserList != null && !sysUserList.isEmpty()){
                        throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在邮箱（登录名）为"+sysUser.getUserCode()+"的用户");
                    }
                }
                //执行保存
                saveUser(sysUser,resDoctor);
                count ++;
            }
        }
        return count;
    }
    public String getDictId(String dictName,String dictTypeId){
        Map<String,String> dictNameMap=InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }
}
