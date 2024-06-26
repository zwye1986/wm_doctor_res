package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IStuDoctorInfoBiz;
import com.pinde.sci.biz.gzzyjxres.IGzjxDocSingupBiz;
import com.pinde.sci.biz.gzzyjxres.IGzjxStuDoctorInfoBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxDocSingupBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.StuUserExtMapper;
import com.pinde.sci.dao.res.StuUserResumeExtMapper;
import com.pinde.sci.enums.dwjxres.StuRoleEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;

import com.pinde.sci.form.gzzyjxres.SpeForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
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
public class StuUserResumeBizImpl implements IStuUserResumeBiz {


    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IGzjxStuDoctorInfoBiz gzjxstuDoctorInfoBiz;
    @Autowired
    private StuUserResumeMapper stuUserResumeMapper;
    @Autowired
    private StuUserResumeExtMapper stuUserResumeExtMapper;
    @Autowired
    private StuUserExtMapper stuUserExtMapper;
    @Autowired
    private StuHeadAuditStatusMapper headAuditStatusMapper;
    @Autowired
    private IGzjxDocSingupBiz gzjxDocSingupBiz;
    @Autowired
    private IZseyjxDocSingupBiz docSingupBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private StuAuditProcessMapper processMapper;
    @Autowired
    private IStuDoctorInfoBiz stuDoctorInfoBiz;
    @Autowired
    private StuDeptOfStaffMapper stuDeptOfStaffMapper;
    @Autowired
    private SysDictMapper sysDictMapper;
    private static final String EXT_INFO_ROOT = "extInfo";
    private static final String EXT_INFO_ELE = "extInfoForm";
    private static final String WORK_RESUME_LIST_ELE = "workResumeList";
    private static final String WORK_RESUME_ELE = "workResumeForm";
    private static final String SPE_FORM_LIST_ELE = "speFormList";
    private static final String SPE_FORM_ELE = "speForm";
    private static final String REG_LIST_ELE = "regList";
    private static final String REG_ELE = "regForm";

    @Override
    public List<StuUserResume> getStuUserLst(String userFlow, String batchFlow) {
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andUserFlowEqualTo(userFlow);
        if (StringUtil.isNotBlank(batchFlow)) {
            example.clear();
            example.createCriteria().andUserFlowEqualTo(userFlow).andStuBatIdEqualTo(batchFlow);
        }
        //如果批次号为空，则查询全表
        example.setOrderByClause("CREATE_TIME DESC");
        return stuUserResumeMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<StuHeadAuditStatus> getStuHeadAudit(Map<String, Object> mp) {
        return stuUserExtMapper.getStuHeadAudit(mp);
    }

    @Override
    public List<StuHeadAuditStatus> getStuHeadAudits(Map<String, String> mp) {
        StuHeadAuditStatusExample example = new StuHeadAuditStatusExample();
        StuHeadAuditStatusExample.Criteria  criteria= example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(mp.get("batchFlow"))){
            criteria.andStuBatIdEqualTo(mp.get("batchFlow"));
        }
        if(StringUtil.isNotBlank(mp.get("deptFlow"))){
            criteria.andDeptFlowEqualTo(mp.get("deptFlow"));
        }
        if(StringUtil.isNotBlank(mp.get("statusId"))){
            criteria.andStuStatusIdEqualTo(mp.get("statusId"));
        }
        if(StringUtil.isNotBlank(mp.get("isBack"))){
            criteria.andIsBackEqualTo(mp.get("isBack"));
        }
        return headAuditStatusMapper.selectByExample(example);
    }

    @Override
    public List<StuUserExt> searchStuUser(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUser(mp);
    }

    @Override
    public List<StuUserExt> searchStuUserByrosteringHand(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUserByrosteringHand(mp);
    }



    @Override
    public List<StuUserExt> searchStuUserByForeign(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUserByForeign(mp);
    }

    @Override
    public List<StuUserExt> searchStuUserAll(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUserAll(mp);
    }

    @Override
    public List<StuUserExt> searchStuUserByHead(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUserByHead(mp);
    }

    @Override
    public List<StuUserExt> searchStuUserAndDoc(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUserAndDoc(mp);
    }

    @Override
    public List<StuUserExt> teaQueryStuList(Map<String, Object> mp) {
        return stuUserExtMapper.teaQueryStuList(mp);
    }

    @Override
    public List<StuUserExt> queryStuList(Map<String, Object> mp) {
        return stuUserExtMapper.queryStuList(mp);
    }

    @Override
    public List<Map<String, Object>> queryStuListForExport(Map<String, Object> mp) {
        return stuUserExtMapper.queryStuListForExport(mp);
    }

    @Override
    public StuUserExt searchAdmitedInfo(String resumeFlow) {
        return stuUserExtMapper.searchAdmitedInfo(resumeFlow);
    }

    @Override
    public StuUserResume getStuUserByKey(String resumeFlow) {
        return stuUserResumeMapper.selectByPrimaryKey(resumeFlow);
    }

    @Override
    public void saveRegisterTeacher(String resumeFlow, String teacherFlow) {
        StuUserResume stuUser = new StuUserResume();
        stuUser.setResumeFlow(resumeFlow);
        stuUser.setTeacherFlow(teacherFlow);
        SysUser tea = userMapper.selectByPrimaryKey(teacherFlow);
        if (tea != null) {
            stuUser.setTeacherName(tea.getUserName());
        }
        stuUserResumeMapper.updateByPrimaryKeySelective(stuUser);
    }

    @Override
    public void saveGraduation(Map<String, Object> map, MultipartFile file, String isSctcm) {
        StuUserResume stuUser = new StuUserResume();
        if ("Y".equals((String) map.get("agree"))) {
            if("Y".equals(isSctcm)) {
                stuUser.setIsGraduation(GlobalConstant.FLAG_Y);
                stuUser.setStuStatusId(StuStatusEnum.Graduation.getId());
                stuUser.setStuStatusName(StuStatusEnum.Graduation.getName());
                stuUser.setNursingStatusId(StuStatusEnum.Graduation.getId());
                stuUser.setNursingStatusName(StuStatusEnum.Graduation.getName());
            }
        } else {
            stuUser.setIsGraduation(GlobalConstant.FLAG_N);
            stuUser.setStuStatusId(StuStatusEnum.DelayGraduation.getId());
            stuUser.setStuStatusName(StuStatusEnum.DelayGraduation.getName());
            stuUser.setNursingStatusId(StuStatusEnum.DelayGraduation.getId());
            stuUser.setNursingStatusName(StuStatusEnum.DelayGraduation.getName());
        }
        if(com.pinde.sci.form.dwjxres.ExtInfoForm.class.equals(map.get("extInfo").getClass())){
            com.pinde.sci.form.dwjxres.ExtInfoForm extInfo = (com.pinde.sci.form.dwjxres.ExtInfoForm) map.get("extInfo");//查询医师注册相关信息
            extInfo.setGraduationMark((String) map.get("comment"));//增加结业评语
            stuUser.setResumeInfo(getXmlFromExtInfo(extInfo));
        }else if(com.pinde.sci.form.gzzyjxres.ExtInfoForm.class.equals(map.get("extInfo").getClass())){
            com.pinde.sci.form.gzzyjxres.ExtInfoForm extInfo = (com.pinde.sci.form.gzzyjxres.ExtInfoForm) map.get("extInfo");//查询医师注册相关信息
            extInfo.setGraduationMark((String) map.get("comment"));//增加结业评语
            stuUser.setResumeInfo(getXmlFromExtInfoForGuangZhou(extInfo));
        }else{
            com.pinde.sci.form.czyyjxres.ExtInfoForm extInfo = (com.pinde.sci.form.czyyjxres.ExtInfoForm) map.get("extInfo");//查询医师注册相关信息
            extInfo.setGraduationMark((String) map.get("comment"));//增加结业评语
            stuUser.setResumeInfo(getXmlFromExtInfoForCZ(extInfo));
        }
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andResumeFlowEqualTo(map.get("resumeFlow").toString());
        if (file != null && !file.isEmpty()) {
            List<PubFile> files = fileBiz.findFileByTypeFlow("AdminFlie", map.get("resumeFlow").toString());

            PubFile pubFile = null;
            if (files != null && files.size() > 0) {
                pubFile = files.get(0);
            } else {
                pubFile = new PubFile();
            }
            String oldPath = "";
            if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                oldPath = pubFile.getFilePath();
            }
            String resultPath = saveFileToDirs(oldPath, file, "graduationFile" + File.separator + "AdminFlie");
            if (StringUtil.isNotBlank(resultPath)) {
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(map.get("resumeFlow").toString());
                pubFile.setFilePath(resultPath);
                //默认的文件名
                pubFile.setFileName(file.getOriginalFilename());
                pubFile.setProductType("AdminFlie");
                String originalFilename = file.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                //文件后缀名
                pubFile.setFileSuffix(suffix);
                fileBiz.editFile(pubFile);
            }
        } else {

            List<PubFile> files = fileBiz.findFileByTypeFlow("AdminFlie", map.get("resumeFlow").toString());

            PubFile pubFile = null;
            if (files != null && files.size() > 0) {
                pubFile = files.get(0);
            }
            if (pubFile != null) {
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                fileBiz.editFile(pubFile);
            }
        }
        stuUserResumeMapper.updateByExampleSelective(stuUser, example);
    }

    @Override
    public void saveStuFile(MultipartFile file, String productFlow) {
        List<PubFile> files = fileBiz.findFileByTypeFlow("StuFlie", productFlow);
        PubFile pubFile = null;
        if (files != null && files.size() > 0) {
            pubFile = files.get(0);
        } else {
            pubFile = new PubFile();
        }
        String oldPath = "";
        if (StringUtil.isNotBlank(pubFile.getFilePath())) {
            oldPath = pubFile.getFilePath();
        }
        String dateString = DateUtil.getCurrDate2();
        String resultPath = saveFileToDirs(oldPath, file, "graduationFile" + File.separator + "StuFlie"+File.separator);
        if (StringUtil.isNotBlank(resultPath)) {
            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            pubFile.setProductFlow(productFlow);
            pubFile.setFilePath(resultPath);
            //默认的文件名
            pubFile.setFileName(file.getOriginalFilename());
            pubFile.setProductType("StuFlie");
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //文件后缀名
            pubFile.setFileSuffix(suffix);
        }
        fileBiz.editFile(pubFile);
    }

    public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName) {
        String path = "";
        if (file.getSize() > 0) {
            //创建目录

            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))  + File.separator + folderName;
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //文件名
            String originalFilename = file.getOriginalFilename();
            originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(fileDir, originalFilename);
            try {
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("保存文件失败！");
            }

            //删除原文件
            if (StringUtil.isNotBlank(oldFolderName)) {
                try {
                    oldFolderName = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
                    File imgFile = new File(oldFolderName);
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("删除文件失败！");
                }
            }
            path = "/" + folderName + "/" + originalFilename;
        }

        return path;
    }

    /**
     * 获取额外信息xml
     *
     * @param extInfo
     * @return
     */
    private String getXmlFromExtInfo(com.pinde.sci.form.dwjxres.ExtInfoForm extInfo) {
        String xmlBody = null;
        if (extInfo != null) {
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement(EXT_INFO_ROOT);
            Element extInfoForm = root.addElement(EXT_INFO_ELE);
            Map<String, String> filedMap = getClassFieldMap(extInfo);
            if (filedMap != null && filedMap.size() > 0) {
                for (String key : filedMap.keySet()) {
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }

            List<com.pinde.sci.form.dwjxres.WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
            if (workResumeList != null && workResumeList.size() > 0) {
                Element workResumeListEle = root.addElement(WORK_RESUME_LIST_ELE);
                for (com.pinde.sci.form.dwjxres.WorkResumeForm resume : workResumeList) {
                    Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
                    Map<String, String> resumeFiledMap = getClassFieldMap(resume);
                    if (resumeFiledMap != null && resumeFiledMap.size() > 0) {
                        for (String key : resumeFiledMap.keySet()) {
                            Element item = resumeEle.addElement(key);
                            item.setText(resumeFiledMap.get(key));
                        }
                    }
                }
            }
            List<com.pinde.sci.form.dwjxres.RegForm> regList = extInfo.getRegList();
            if (regList != null && regList.size() > 0) {
                Element regListEle = root.addElement(REG_LIST_ELE);
                for (com.pinde.sci.form.dwjxres.RegForm reg : regList) {
                    Element regEle = regListEle.addElement(REG_ELE);
                    Map<String, String> rgeFiledMap = getClassFieldMap(reg);
                    if (rgeFiledMap != null && rgeFiledMap.size() > 0) {
                        for (String key : rgeFiledMap.keySet()) {
                            Element item = regEle.addElement(key);
                            item.setText(rgeFiledMap.get(key));
                        }
                    }
                }
            }

            xmlBody = doc.asXML();
        }
        return xmlBody;
    }

    /**
     * 获取额外信息xml（广医招录）
     *
     * @param extInfo
     * @return
     */
    private String getXmlFromExtInfoForGuangZhou(com.pinde.sci.form.gzzyjxres.ExtInfoForm extInfo) {
        String xmlBody = null;
        if (extInfo != null) {
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement(EXT_INFO_ROOT);
            Element extInfoForm = root.addElement(EXT_INFO_ELE);
            Map<String, String> filedMap = getClassFieldMap(extInfo);
            if (filedMap != null && filedMap.size() > 0) {
                for (String key : filedMap.keySet()) {
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }

            List<com.pinde.sci.form.gzzyjxres.SpeForm> speFormList = extInfo.getSpeFormList();
            if(speFormList!=null && speFormList.size()>0){
                Element speListEle = root.addElement(SPE_FORM_LIST_ELE);
                for(SpeForm resume : speFormList){
                    Element resumeEle = speListEle.addElement(SPE_FORM_ELE);
                    Map<String,String> resumeFiledMap = getClassFieldMap(resume);
                    if(resumeFiledMap!=null && resumeFiledMap.size()>0){
                        for(String key : resumeFiledMap.keySet()){
                            Element item = resumeEle.addElement(key);
                            item.setText(resumeFiledMap.get(key));
                        }
                    }
                }
            }



            List<com.pinde.sci.form.gzzyjxres.WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
            if (workResumeList != null && workResumeList.size() > 0) {
                Element workResumeListEle = root.addElement(WORK_RESUME_LIST_ELE);
                for (com.pinde.sci.form.gzzyjxres.WorkResumeForm resume : workResumeList) {
                    Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
                    Map<String, String> resumeFiledMap = getClassFieldMap(resume);
                    if (resumeFiledMap != null && resumeFiledMap.size() > 0) {
                        for (String key : resumeFiledMap.keySet()) {
                            Element item = resumeEle.addElement(key);
                            item.setText(resumeFiledMap.get(key));
                        }
                    }
                }
            }
            List<com.pinde.sci.form.gzzyjxres.RegForm> regList = extInfo.getRegList();
            if (regList != null && regList.size() > 0) {
                Element regListEle = root.addElement(REG_LIST_ELE);
                for (com.pinde.sci.form.gzzyjxres.RegForm reg : regList) {
                    Element regEle = regListEle.addElement(REG_ELE);
                    Map<String, String> rgeFiledMap = getClassFieldMap(reg);
                    if (rgeFiledMap != null && rgeFiledMap.size() > 0) {
                        for (String key : rgeFiledMap.keySet()) {
                            Element item = regEle.addElement(key);
                            item.setText(rgeFiledMap.get(key));
                        }
                    }
                }
            }

            xmlBody = doc.asXML();
        }
        return xmlBody;
    }
    /**
     * 获取额外信息xml（潮州进修）
     *
     * @param extInfo
     * @return
     */
    private String getXmlFromExtInfoForCZ(com.pinde.sci.form.czyyjxres.ExtInfoForm extInfo) {
        String xmlBody = null;
        if (extInfo != null) {
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement(EXT_INFO_ROOT);
            Element extInfoForm = root.addElement(EXT_INFO_ELE);
            Map<String, String> filedMap = getClassFieldMap(extInfo);
            if (filedMap != null && filedMap.size() > 0) {
                for (String key : filedMap.keySet()) {
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }

            List<com.pinde.sci.form.czyyjxres.SpeForm> speFormList = extInfo.getSpeFormList();
            if(speFormList!=null && speFormList.size()>0){
                Element speListEle = root.addElement(SPE_FORM_LIST_ELE);
                for(com.pinde.sci.form.czyyjxres.SpeForm resume : speFormList){
                    Element resumeEle = speListEle.addElement(SPE_FORM_ELE);
                    Map<String,String> resumeFiledMap = getClassFieldMap(resume);
                    if(resumeFiledMap!=null && resumeFiledMap.size()>0){
                        for(String key : resumeFiledMap.keySet()){
                            Element item = resumeEle.addElement(key);
                            item.setText(resumeFiledMap.get(key));
                        }
                    }
                }
            }



            List<com.pinde.sci.form.czyyjxres.WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
            if (workResumeList != null && workResumeList.size() > 0) {
                Element workResumeListEle = root.addElement(WORK_RESUME_LIST_ELE);
                for (com.pinde.sci.form.czyyjxres.WorkResumeForm resume : workResumeList) {
                    Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
                    Map<String, String> resumeFiledMap = getClassFieldMap(resume);
                    if (resumeFiledMap != null && resumeFiledMap.size() > 0) {
                        for (String key : resumeFiledMap.keySet()) {
                            Element item = resumeEle.addElement(key);
                            item.setText(resumeFiledMap.get(key));
                        }
                    }
                }
            }
            List<com.pinde.sci.form.czyyjxres.RegForm> regList = extInfo.getRegList();
            if (regList != null && regList.size() > 0) {
                Element regListEle = root.addElement(REG_LIST_ELE);
                for (com.pinde.sci.form.czyyjxres.RegForm reg : regList) {
                    Element regEle = regListEle.addElement(REG_ELE);
                    Map<String, String> rgeFiledMap = getClassFieldMap(reg);
                    if (rgeFiledMap != null && rgeFiledMap.size() > 0) {
                        for (String key : rgeFiledMap.keySet()) {
                            Element item = regEle.addElement(key);
                            item.setText(rgeFiledMap.get(key));
                        }
                    }
                }
            }

            xmlBody = doc.asXML();
        }
        return xmlBody;
    }
    /**
     * 获取属性名和值
     *
     * @return
     */
    private Map<String, String> getClassFieldMap(Object obj) {
        Map<String, String> filedMap = null;
        if (obj != null) {
            try {
                filedMap = new HashMap<String, String>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for (Field f : fileds) {
                    String typeName = f.getType().getSimpleName();
                    if (stringClassName.equals(typeName)) {
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0, 1).toUpperCase();
                        String methedName = "get" + firstLetter + attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String) getMethod.invoke(obj);
                        filedMap.put(attrName, StringUtil.defaultString(value));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filedMap;
    }

    @Override
    public List<StuUserResume> getPassingBatchLst(String userFlow, String statusId) {
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(userFlow).andStuStatusIdNotEqualTo(statusId);
        example.setOrderByClause("create_time desc");
        return stuUserResumeMapper.selectByExample(example);
    }


    @Override
    public int saveResumeAndHead(StuUserResume stuUser, Map<String, String> mp, List<com.pinde.sci.form.zseyjxres.SpeForm> speFormList, List<StuHeadAuditStatus> statuses) {
        int count=0;
        int r1 = save(stuUser);
        int r2 = docSingupBiz.saveHeadstatus(mp,speFormList,statuses);
        if(r1>0 && r2>0){
            return GlobalConstant.ONE_LINE;
        }
        return count;
    }

    @Override
    public int save(StuUserResume stuUser) {
        if (StringUtil.isBlank(stuUser.getResumeFlow())) {
            stuUser.setResumeFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(stuUser, true);
            return stuUserResumeMapper.insertSelective(stuUser);
        } else {
            GeneralMethod.setRecordInfo(stuUser, false);
            return stuUserResumeMapper.updateByPrimaryKeySelective(stuUser);
        }
    }

    @Override
    public List<SysUser> getTeas(Map<String, String> paramMap) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andIsOwnerStuEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(paramMap.get("deptFlow")) && StringUtil.isNotBlank(paramMap.get("roleId"))) {
            String deptFlow = paramMap.get("deptFlow");
            StuDeptOfStaffExample deptExample = new StuDeptOfStaffExample();
            StuDeptOfStaffExample.Criteria deptCriteria = deptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andDeptFlowEqualTo(deptFlow).andUserRoleEqualTo(paramMap.get("roleId"));
            List<StuDeptOfStaff> tempDepts = stuDeptOfStaffMapper.selectByExample(deptExample);
            //存放带教老师Flow
            List<String> userFlows = new ArrayList<>();
            if (tempDepts != null && tempDepts.size() > 0) {
                for (StuDeptOfStaff tempDept : tempDepts) {
                    userFlows.add(tempDept.getUserFlow());
                }
            }
            if (userFlows != null && userFlows.size() > 0) {
                criteria.andUserFlowIn(userFlows);
            }else {
                return null;
            }
        }
        if (StringUtil.isNotBlank(paramMap.get("roleId"))) {
            criteria.andIsOrgAdminEqualTo(paramMap.get("roleId"));
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public ExcelUtile importStaffFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "deptId",
                    "userName",
                    "userCode",
                    "userPhone",
                    "isAdmin"
            };
            final List<String> userCodes = new ArrayList<>();
            final List<String> userPhones = new ArrayList<>();
            //用于校验同一科室是否导入不同教秘
            final Map tempDeptFlowMap = new HashMap();
            return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String, Object>> datas = eu.getExcelDatas();
                    int count = 0;
                    String code = "0";
                    for (int i = 0; i < datas.size(); i++) {
                        Map<String, Object> data = datas.get(i);
                        String[] deptFlowArr = (String[]) data.get("deptFlowArr");
                        SysUser sysUser = (SysUser) data.get("user");
                        sysUser.setStatusId(UserStatusEnum.Activated.getId());
                        sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
                        sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
                        count += stuDoctorInfoBiz.saveUserAndDeptInfo(sysUser, deptFlowArr, GlobalContext.getCurrentUser());
                    }
                    eu.put("code", code);
                    eu.put("count", count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    String sheetName = (String) eu.get("SheetName");
                    if (sheetName == null || !"Staffs".equals(sheetName)) {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的模板！！");
                        return ExcelUtile.RETURN;
                    }
                    SysUser sysUser = new SysUser();
                    //科室可以分配多条
                    String deptFlowStr = "";
                    //deptFlow的数组
                    String[] deptFlowArr = null;
                    String userName = "";
                    String userCode = "";
                    String userPhone = "";
                    String isOrgAdmin = "";
                    int rowNum = (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    for (Object key1 : data.keySet()) {
                        String key = (String) key1;
                        String value = (String) data.get(key);
                        /* 科室	姓名 工号 联系电话 是否教秘 	*/
                        if ("deptId".equals(key)) {
                            deptFlowStr = value.trim();
                            if (StringUtil.isNotBlank(deptFlowStr)) {
                                deptFlowArr = deptFlowStr.split("\\*");
                                if (deptFlowArr != null && deptFlowArr.length > 0) {
                                    for (int i = 0; i < deptFlowArr.length; i++) {
                                        String deptFlow = deptFlowArr[i];
                                        SysDictExample example = new SysDictExample();
                                        example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                                                .andDictIdEqualTo(deptFlow)
                                                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                                        example.setOrderByClause("ORDINAL DESC");
                                        List<SysDict> speLst = sysDictMapper.selectByExample(example);
                                        if (speLst == null || speLst.size() < 1) {
                                            eu.put("count", 0);
                                            eu.put("code", "1");
                                            eu.put("msg", "导入文件第" + (rowNum + 1) + "行科室编码有误，请确认后提交！！");
                                            return ExcelUtile.RETURN;
                                        }
                                    }
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行科室编码不能为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        } else if ("userCode".equals(key)) {
                            userCode = value.trim();
                            if (StringUtil.isNotBlank(userCode)) {
                                SysUserExample example = new SysUserExample();
                                SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                                criteria.andUserCodeEqualTo(userCode);
                                List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
                                if (sysUsers != null && sysUsers.size() > 0) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行人员工号重复，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                } else if (userCodes.contains(userCode)) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件中第" + (rowNum + 1) + "行人员工号存在重复，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                } else {
                                    userCodes.add(userCode);
                                    sysUser.setUserCode(userCode);
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行工号不能为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        } else if ("userName".equals(key)) {
                            userName = value.trim();
                            if (StringUtil.isNotBlank(userName)) {
                                sysUser.setUserName(userName);
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行姓名不能为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        } else if ("userPhone".equals(key)) {
                            userPhone = value.trim();

                            if (StringUtil.isNotBlank(userPhone)) {
                                Boolean b = userPhone.matches("^([\\+][0-9]{1,3}[ \\.\\-])?([\\(]{1}[0-9]{2,6}[\\)])?([0-9 \\.\\-\\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$");
                                if (!b) {
                                    b = userPhone.matches("^([0-9]{3,4})[\\-]([0-9]{7,8})([\\-][0-9]{1,8})?$");
                                }
                                if (b) {
                                    sysUser.setUserPhone(userPhone);
                                } else {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行联系电话格式有误，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                                SysUserExample example = new SysUserExample();
                                SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                                criteria.andUserPhoneEqualTo(userPhone);
                                List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
                                if (sysUsers != null && sysUsers.size() > 0) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行人员联系电话重复，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                                if (userPhones.contains(userPhone)) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件中第" + (rowNum + 1) + "行人员联系电话存在重复，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                } else {
                                    userPhones.add(userPhone);
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行联系电话,不得为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        } else if ("isAdmin".equals(key)) {
                            isOrgAdmin = value.trim();
                            if (StringUtil.isNotBlank(isOrgAdmin)) {

                                if (("带教老师".equals(isOrgAdmin) || "教学秘书".equals(isOrgAdmin))) {
                                    if ("教学秘书".equals(isOrgAdmin)) {
                                        //查询当前科室是否已经分配教秘
                                        sysUser.setIsOrgAdmin(StuRoleEnum.Secretary.getId());
                                    } else {
                                        sysUser.setIsOrgAdmin(StuRoleEnum.Teacher.getId());
                                    }
                                } else {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行角色格式有误,未按格式填写，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            } else {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行角色不得为空,请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }
                    }
                    data.put("user", sysUser);
                    data.put("deptFlowArr", deptFlowArr);
                    //校验是否所分配的科室是否已经分配过教秘
                    if (StuRoleEnum.Secretary.getId().equals(sysUser.getIsOrgAdmin())
                            && deptFlowArr != null && deptFlowArr.length > 0) {
                        for (String tempDeptFlow : deptFlowArr) {
                            if ("Y".equals(tempDeptFlowMap.get(tempDeptFlow))) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行有误,该科室已经分配过教学秘书，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            } else {
                                StuDeptOfStaff stuDeptOfStaff = new StuDeptOfStaff();
                                stuDeptOfStaff.setUserRole(StuRoleEnum.Secretary.getId());
                                stuDeptOfStaff.setDeptFlow(tempDeptFlow);
                                List<StuDeptOfStaff> stuDeptOfStaffs = stuDoctorInfoBiz.searchIfDeptHasSecretary(stuDeptOfStaff);
                                if (stuDeptOfStaffs != null && stuDeptOfStaffs.size() > 0) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行有误,该科室已经分配过教学秘书，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                } else {
                                    tempDeptFlowMap.put(tempDeptFlow, "Y");
                                }
                            }
                        }
                    }
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

    @Override
    public List<StuAuditProcess> searchProcessByResumeFlow(String resumeFlow) {
        StuAuditProcessExample example = new StuAuditProcessExample();
        StuAuditProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(resumeFlow)) {
            criteria.andResumeFlowEqualTo(resumeFlow);
        }
        example.setOrderByClause("modify_time");
        return processMapper.selectByExample(example);
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
            String sheetName = sheet.getSheetName();
            if (!"Staffs".equals(sheetName)) {
                return -1;
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
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                if (r == null) {
                    throw new RuntimeException("导入失败！表中不能有空行！");
                }
                SysUser sysUser = new SysUser();
                String deptFlow = "";
                String userName = "";
                String userCode = "";
                String userPhone = "";
                String isOrgAdmin = "";
                for (int j = 0; j < colnames.size(); j++) {

                    int page = count + 2;
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (cell != null && StringUtil.isNotBlank(cell.toString().trim())) {
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    /* 科室	姓名 工号 联系电话 是否教秘 	*/
                    if ("工号".equals(colnames.get(j))) {
                        userCode = value;
                        if (StringUtil.isNotBlank(userCode)) {
                            SysUserExample example = new SysUserExample();
                            SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

                            criteria.andUserCodeEqualTo(userCode);
                            List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
                            if (sysUsers != null && sysUsers.size() > 0) {
                                throw new RuntimeException("导入失败！第" + page + "行人员工号重复！");
                            } else {
                                sysUser.setUserCode(userCode);
                            }
                        } else {
                            throw new RuntimeException("导入失败！第" + page + "行工号不能为空！");
                        }
                    } else if ("科室编码".equals(colnames.get(j))) {
                        deptFlow = value;
                        if (StringUtil.isNotBlank(deptFlow)) {
                            SysDictExample example = new SysDictExample();
                            example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                                    .andDictIdEqualTo(deptFlow)
                                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                            example.setOrderByClause("ORDINAL DESC");
                            List<SysDict> speLst = sysDictMapper.selectByExample(example);
                            if (speLst != null && speLst.size() > 0) {
                                SysDict temp = speLst.get(0);
                                sysUser.setDeptFlow(temp.getDictId());
                                sysUser.setDeptName(temp.getDictName());
                            } else {

                                throw new RuntimeException("导入失败！第" + page + "行科室有误，请参考说明！");
                            }
                        } else {
                            throw new RuntimeException("导入失败！第" + page + "行科室编码不能为空！");
                        }
                    } else if ("姓名".equals(colnames.get(j))) {
                        userName = value;
                        if (StringUtil.isNotBlank(userName)) {
                            sysUser.setUserName(userName);
                        } else {
                            throw new RuntimeException("导入失败！第" + page + "行姓名不能为空！");
                        }
                    } else if ("联系电话".equals(colnames.get(j))) {
                        userPhone = value;

                        if (StringUtil.isNotBlank(userPhone)) {
                            Boolean b = userPhone.matches("^([\\+][0-9]{1,3}[ \\.\\-])?([\\(]{1}[0-9]{2,6}[\\)])?([0-9 \\.\\-\\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$");
                            if (!b) {
                                b = userPhone.matches("^([0-9]{3,4})[\\-]([0-9]{7,8})([\\-][0-9]{1,8})?$");
                            }
                            if (b) {
                                sysUser.setUserPhone(userPhone);
                            } else {
                                throw new RuntimeException("导入失败！第" + page + "行联系电话格式有误！");
                            }
                            SysUserExample example = new SysUserExample();
                            SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                            criteria.andUserPhoneEqualTo(userPhone);
                            List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
                            if (sysUsers != null && sysUsers.size() > 0) {

                                throw new RuntimeException("导入失败！第" + page + "行人员联系电话重复！");
                            }
                        } else {
                            throw new RuntimeException("导入失败！第" + page + "行联系电话,不得为空！");
                        }
                    } else if ("是否教秘".equals(colnames.get(j))) {
                        isOrgAdmin = value;
                        if (StringUtil.isNotBlank(isOrgAdmin))

                            if (("是".equals(isOrgAdmin) || "否".equals(isOrgAdmin))) {
                                if ("是".equals(isOrgAdmin)) {
                                    sysUser.setIsOrgAdmin(GlobalConstant.FLAG_Y);
                                } else {
                                    sysUser.setIsOrgAdmin(GlobalConstant.FLAG_N);
                                }
                            } else {
                                throw new RuntimeException("导入失败！第" + page + "行是否教秘格式有误！");
                            }
                    } else {
                        throw new RuntimeException("导入失败！第" + page + "行是否教秘不得为空！");
                    }

                }
                //执行保存
                sysUser.setUserFlow(PkUtil.getUUID());
                sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), InitPasswordUtil.getInitPass()));
                GeneralMethod.setRecordInfo(sysUser, true);
                sysUser.setStatusId(UserStatusEnum.Activated.getId());
                sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
                sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
                sysUserMapper.insert(sysUser);
                count++;
            }
        }
        return count;
    }

    @Override
    public int updateSpeNameById(String dictId, String dictName) {
        return stuUserResumeExtMapper.updateSpeNameById(dictId, dictName);
    }

    @Override
    public int updateUserDeptNameById(String dictId, String dictName) {
        return stuUserResumeExtMapper.updateUserDeptNameById(dictId, dictName);
    }

    @Override
    public int updateRegistDeptNameById(String dictId, String dictName) {
        return stuUserResumeExtMapper.updateRegistDeptNameById(dictId, dictName);
    }

    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public List<StuUserResume> getShowGraduationLst(String userFlow, List<String> paramList) {
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow)
                .andStuStatusIdIn(paramList);
        return stuUserResumeMapper.selectByExample(example);
    }

    @Override
    public List<StuUserResume> getPassStuUserLst(String userFlow, List<String> statues) {
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andUserFlowEqualTo(userFlow);
        if (statues != null) {
            example.clear();
            example.createCriteria().andUserFlowEqualTo(userFlow).andStuStatusIdIn(statues);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return stuUserResumeMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public StuUserResume searchStuUserResume(String resumeFlow) {
        return stuUserResumeMapper.selectByPrimaryKey(resumeFlow);
    }

    @Override
    public int saveDateAndStatus(String statusId, StuUserResume userResume) {
        statusId = "Y".equals(statusId) ? "Admited" : "UnAdmitd";
        gzjxDocSingupBiz.changeRecruit(userResume.getResumeFlow(), statusId,userResume.getReportDate());
        //确认报到添加工号
        if("Admited".equals(statusId)){
            int count=gzjxstuDoctorInfoBiz.countAdmitedStuUsers(DateUtil.getYear());
            String workNo=count+"";
            if(workNo.length() == 1){
                workNo = DateUtil.getYear()+"J"+"00"+workNo;
            }else if(workNo.length() == 2){
                workNo = DateUtil.getYear()+"J"+"0"+workNo;
            }else {
                workNo=DateUtil.getYear()+"J"+count;
            }
            StuUserResume sur=getStuUserByKey(userResume.getResumeFlow());
            if(sur!=null&&sur.getUserFlow()!=null){
                SysUser user=new SysUser();
                user.setUserCode(workNo);
                user.setUserFlow(sur.getUserFlow());
                userBiz.edit(user);
                String resumeInfo=sur.getResumeInfo();
                if(StringUtil.isNotBlank(resumeInfo)){
                    com.pinde.sci.form.gzzyjxres.ExtInfoForm form=gzjxDocSingupBiz.parseExtInfoXml(resumeInfo);
                    if(form!=null){
                        form.setUserCode(workNo);
                        resumeInfo=gzjxDocSingupBiz.getXmlFromExtInfo(form);
                        sur.setResumeInfo(resumeInfo);
                    }
                    gzjxDocSingupBiz.updateStuUserResume(sur);
                }

            }
//            StuUserResume stuUserResume=getStuUserByKey(userResume.getResumeFlow());
//            if(stuUserResume!=null){
//
//            }
        }
        return GlobalConstant.ONE_LINE;
    }

    @Override
    public List<StuUserExt> searchStuNurseUser(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuNurseUser(mp);
    }

    @Override
    public List<StuUserExt> searchStuUser2(Map<String, Object> mp) {
        return stuUserExtMapper.searchStuUser2(mp);
    }

    @Override
    public List<StuUserExt> searchUser(Map<String, Object> mp) {
        return stuUserExtMapper.searchUser(mp);
    }

    @Override
    public List<StuUserExt> queryNurseList(Map<String, Object> parMp) {
        return stuUserExtMapper.queryNurseList(parMp);
    }

    @Override
    public List<Map<String, Object>> queryNurseListForExport(Map<String, Object> parMp) {
        return stuUserExtMapper.queryNurseListForExport(parMp);
    }
}
 