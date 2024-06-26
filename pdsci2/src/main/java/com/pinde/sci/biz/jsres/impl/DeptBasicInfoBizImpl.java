package com.pinde.sci.biz.jsres.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.jsres.IDeptBasicInfoBiz;
import com.pinde.sci.biz.jsres.IJsResDeptManagementBiz;
import com.pinde.sci.biz.jsres.IResBaseSpeDeptBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.ResBaseSpeDeptDataMapper;
import com.pinde.sci.dao.base.ResBaseSpeDeptInfoMapper;
import com.pinde.sci.dao.base.ResBaseSpeDeptMapper;
import com.pinde.sci.dao.base.ResTeacherTrainingMapper;
import com.pinde.sci.dao.sch.ResBaseSpeDeptDataExtMapper;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptExtForm;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.vo.ResDeptRelStdDeptVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptBasicInfoBizImpl implements IDeptBasicInfoBiz {

    @Autowired
    private ResBaseSpeDeptMapper baseSpeDeptMapper;
    @Autowired
    private IResBaseSpeDeptBiz baseSpeDeptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private ResTeacherTrainingMapper teacherTrainingMapper;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private ResBaseSpeDeptInfoMapper baseSpeDeptInfoMapper;
    @Autowired
    private ResBaseSpeDeptDataMapper baseSpeDeptDataMapper;
    @Autowired
    private ResBaseSpeDeptDataExtMapper resBaseSpeDeptDataExtMapper;

    @Autowired
    private IJsResDeptManagementBiz deptManagementBiz;

    @Override
    public int saveBaseInfo(String flag, BaseSpeDeptForm baseSpeDeptForm, String index, String type, String[] fileFlows,
                            HttpServletRequest request, String infoType) throws Exception {
        String xml = "";
        BaseSpeDeptExtForm extForm = null;
        ResBaseSpeDept fromBase = baseSpeDeptForm.getResBaseSpeDept();  //界面获取的数据
        ResBaseSpeDept resBaseSpeDept = null;
        if (infoType.equals("dept")) {
            resBaseSpeDept = readByOrgAndDept(fromBase.getOrgFlow(), fromBase.getDeptFlow(),fromBase.getSessionNumber());    //查询数据库中的数据
        } else if (infoType.equals("spe")) {
            resBaseSpeDept = readByOrgAndSpe(fromBase.getOrgFlow(), fromBase.getSpeFlow(),fromBase.getSessionNumber());    //查询数据库中的数据
        }

        if (null != resBaseSpeDept) {
            xml = resBaseSpeDept.getBaseInfo();
            extForm = JaxbUtil.converyToJavaBean(xml, BaseSpeDeptExtForm.class);
        } else {
            extForm = new BaseSpeDeptExtForm();
            resBaseSpeDept = new ResBaseSpeDept();
            resBaseSpeDept.setOrgFlow(fromBase.getOrgFlow());
            resBaseSpeDept.setOrgName(orgBiz.readSysOrg(fromBase.getOrgFlow()).getOrgName());
            resBaseSpeDept.setSessionNumber(fromBase.getSessionNumber());
            if (infoType.equals("dept")) {
                resBaseSpeDept.setDeptFlow(fromBase.getDeptFlow());
                SysDept sysDept = deptBiz.readSysDept(fromBase.getDeptFlow());
                if(sysDept != null) {
                    resBaseSpeDept.setDeptName(sysDept.getDeptName());
                }
            } else if (infoType.equals("spe")) {
                resBaseSpeDept.setSpeFlow(fromBase.getSpeFlow());
                SysDict dict = dictBiz.readAllSecondLevelDict("DoctorTrainingSpe", fromBase.getSpeFlow());
                if (null != dict) {
                    resBaseSpeDept.setSpeName(dict.getDictName());
                }
            }
            resBaseSpeDept.setType(infoType);
        }
        if (null != extForm) { //将数据库中的数据替换成页面上的数据
            if (GlobalConstant.DEPT_BASIC_INFO.equals(flag)) {
                extForm.setDeptBasicInfoForm(baseSpeDeptForm.getDeptBasicInfoForm());
            } else if (GlobalConstant.TRAINING.equals(flag)) {
                extForm.setTrainingForm(baseSpeDeptForm.getTrainingForm());
            } else if (GlobalConstant.DEPARTMENT_HEAD.equals(flag)) {
                extForm.setDepartmentHeadForm(baseSpeDeptForm.getDepartmentHeadForm());
            }
        }
        xml = JaxbUtil.convertToXml(extForm);
        if (StringUtil.isNotBlank(type)) {
            List<String> fileFlowList = null;
            if (null != fileFlows) {
                fileFlowList = Arrays.asList(fileFlows);
            }
            if (infoType.equals("dept")) {
                //处理不在本次保存中的文件
                upadteFileInfo(resBaseSpeDept.getOrgFlow() + resBaseSpeDept.getDeptFlow(), fileFlowList, type);
                //处理上传文件
                addUploadFile(resBaseSpeDept.getOrgFlow() + resBaseSpeDept.getDeptFlow(), request, type);
            } else if (infoType.equals("spe")) {
                //处理不在本次保存中的文件
                upadteFileInfo(resBaseSpeDept.getOrgFlow() + resBaseSpeDept.getSpeFlow(), fileFlowList, type);
                //处理上传文件
                addUploadFile(resBaseSpeDept.getOrgFlow() + resBaseSpeDept.getSpeFlow(), request, type);
            }

        }
        resBaseSpeDept.setBaseInfo(xml);
        return baseSpeDeptBiz.saveInfo(resBaseSpeDept);
    }

    //处理文件
    private void upadteFileInfo(String recordFlow, List<String> fileFlows, String type) {
        //查询出不在本次保存中的文件信息
        List<PubFile> files = pubFileBiz.searchByProductFlowAndTypeAndNotInFileFlows(recordFlow, fileFlows, type);
        //删除服务器中相应的文件
        if (files != null && files.size() > 0) {
            String basePath = InitConfig.getSysCfg("upload_base_dir");
            for (PubFile pubFile : files) {
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                pubFileBiz.editFile(pubFile);
            }
        }
    }

    //保存上传的附件
    private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
        //以下为多文件上传********************************************
        //创建一个通用的多部分解析器
        List<PubFile> pubFiles = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                String key = iter.next();
                List<MultipartFile> files = multiRequest.getFiles(key);
                if (files != null && files.size() > 0) {
                    for (MultipartFile file : files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (com.pinde.core.util.StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))
                                    + File.separator + "resBaseInfo" + File.separator + noteTypeId + File.separator
                                    + dateString + File.separator + recordFlow;
                            File fileDir = new File(newDir);
                            if (!fileDir.exists()) {
                                fileDir.mkdirs();
                            }
                            //重命名上传后的文件名
                            String originalFilename = "";
                            originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                            File newFile = new File(fileDir, originalFilename);
                            try {
                                file.transferTo(newFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException("保存文件失败！");
                            }
                            String filePath = File.separator + "resBaseInfo" + File.separator + noteTypeId + File.separator + dateString + File.separator + recordFlow + File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
                            if (GlobalConstant.TRAINING.equals(noteTypeId)) {
                                pubFile.setProductType(noteTypeId);
                                pubFile.setFileUpType(key);
                            } else {
                                pubFile.setProductType(noteTypeId);
                            }
                            pubFile.setProductFlow(recordFlow);
                            pubFiles.add(pubFile);
                        }
                    }
                }
            }
        }
        if (pubFiles.size() > 0) {
            pubFileBiz.saveFiles(pubFiles);
        }
    }


    @Override
    public ResBaseSpeDept readByRecordFlow(String recordFlow) {
        ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
        ResBaseSpeDeptExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo("Y").andRecordFlowEqualTo(recordFlow);
        List<ResBaseSpeDept> list = baseSpeDeptMapper.selectByExampleWithBLOBs(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ResBaseSpeDept readByOrgAndDept(String orgFlow, String deptFlow,String sessionNumber) {
        ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
        ResBaseSpeDeptExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            criteria.andDeptFlowEqualTo(deptFlow);
        }
        if (StringUtil.isNotBlank(sessionNumber)){
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        criteria.andRecordStatusEqualTo("Y").andTypeEqualTo("dept");
        List<ResBaseSpeDept> list = baseSpeDeptMapper.selectByExampleWithBLOBs(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ResBaseSpeDept readByOrgAndSpe(String orgFlow, String speFlow,String sessionNumber) {
        ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
        ResBaseSpeDeptExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(speFlow)) {
            criteria.andSpeFlowEqualTo(speFlow);
        }
        if (StringUtil.isNotBlank(sessionNumber)){
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        criteria.andRecordStatusEqualTo("Y").andTypeEqualTo("spe");
        List<ResBaseSpeDept> list = baseSpeDeptMapper.selectByExampleWithBLOBs(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResBaseSpeDept> readByOrgFlowAndYear(String orgFlow, String sessionNumber) {
        ResBaseSpeDeptExample example = new ResBaseSpeDeptExample();
        ResBaseSpeDeptExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo("Y").andTypeEqualTo("spe");
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(sessionNumber)){
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        return baseSpeDeptMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ResTeacherTraining> searchByOrgAndDept(String orgFlow, String deptFlow) {
        ResTeacherTrainingExample example = new ResTeacherTrainingExample();
        ResTeacherTrainingExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow).andOrgFlowIsNotNull();
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            criteria.andDeptFlowEqualTo(deptFlow);
        }
        criteria.andRecordStatusEqualTo("Y");
        return teacherTrainingMapper.selectByExample(example);
    }

    @Override
    public List<ResTeacherTraining> searchByOrgAndSpe(String orgFlow, String speFlow) {
        ResTeacherTrainingExample example = new ResTeacherTrainingExample();
        ResTeacherTrainingExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow).andOrgFlowIsNotNull();
        }
        if (StringUtil.isNotBlank(speFlow)) {
            criteria.andSpeIdEqualTo(speFlow);
        }
        criteria.andRecordStatusEqualTo("Y");
        return teacherTrainingMapper.selectByExample(example);
    }

    @Override
    public List<ResBaseSpeDeptInfo> searchInfo(ResBaseSpeDeptInfo baseSpeDeptInfo) {
        ResBaseSpeDeptInfoExample example = new ResBaseSpeDeptInfoExample();
        ResBaseSpeDeptInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo("Y");
        if (null != baseSpeDeptInfo) {
            if (StringUtil.isNotBlank(baseSpeDeptInfo.getType())) {
                criteria.andTypeEqualTo(baseSpeDeptInfo.getType());
            }
            if (StringUtil.isNotBlank(baseSpeDeptInfo.getStandardDeptId())) {
                criteria.andStandardDeptIdEqualTo(baseSpeDeptInfo.getStandardDeptId());
            }
            if (StringUtil.isNotBlank(baseSpeDeptInfo.getStandardDeptName())) {
                criteria.andStandardDeptNameEqualTo(baseSpeDeptInfo.getStandardDeptName());
            }
            if (StringUtil.isNotBlank(baseSpeDeptInfo.getSpeFlow())) {
                criteria.andSpeFlowEqualTo(baseSpeDeptInfo.getSpeFlow());
            }
        }
        example.setOrderByClause("ORDINAL");
        return baseSpeDeptInfoMapper.selectByExample(example);
    }

    @Override
    public ResBaseSpeDeptData searchResBaseSpeDeptDataOne(String infoFlow, String orgFlow, String speFlow, String deptFlow, String type,String sessionNumber,String infoType) {
        ResBaseSpeDeptDataExample example = new ResBaseSpeDeptDataExample();
        ResBaseSpeDeptDataExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo("Y");
        if (StringUtil.isNotBlank(infoFlow)) {
            criteria.andInfoFlowEqualTo(infoFlow);
        }
        if (StringUtil.isNotBlank(orgFlow)) {
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if (StringUtil.isNotBlank(type)) {
            criteria.andTypeEqualTo(type);
        }
        if (StringUtil.isNotBlank(speFlow)) {
            criteria.andSpeFlowEqualTo(speFlow);
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            criteria.andDeptFlowEqualTo(deptFlow);
        }
        if (StringUtil.isNotBlank(sessionNumber)){
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        if (StringUtil.isNotBlank(infoType)){
            criteria.andInfoTypeEqualTo(infoType);
        }
        List<ResBaseSpeDeptData> list = baseSpeDeptDataMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int saveResBaseSpeDeptInfoData(ResBaseSpeDeptData speDeptData) {
        if (null != speDeptData) {

            ResBaseSpeDeptData deptData = searchResBaseSpeDeptDataOne(speDeptData.getInfoFlow(), speDeptData.getOrgFlow()
                    , speDeptData.getSpeFlow(), speDeptData.getDeptFlow(), speDeptData.getType(),speDeptData.getSessionNumber(),speDeptData.getInfoType());
            if (null == deptData) {
                speDeptData.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(speDeptData, true);
                return baseSpeDeptDataMapper.insert(speDeptData);
            } else {
                if (StringUtil.isNotBlank(speDeptData.getInfo())) {
                    deptData.setInfo(speDeptData.getInfo());
                }
                if (StringUtil.isNotBlank(speDeptData.getInfoTwo())) {
                    deptData.setInfoTwo(speDeptData.getInfoTwo());
                }
                GeneralMethod.setRecordInfo(deptData, false);
                return baseSpeDeptDataMapper.updateByPrimaryKey(deptData);
            }
        }
        return 0;
    }

    @Override
    public List<Map<String, String>> searchResBaseSpeDeptInfoData(Map<String, String> paramMap) {
        return resBaseSpeDeptDataExtMapper.searchResBaseSpeDeptInfoData(paramMap);
    }

    @Override
    public List<Map<String, String>> countByOrgListAndSpe(List<String> orgFlowList) {
        return resBaseSpeDeptDataExtMapper.countByOrgListAndSpe(orgFlowList);
    }

    @Override
    public int countResBaseSpeDeptInfoData(Map<String, String> paramMap) {
        ResBaseSpeDeptDataExample example = new ResBaseSpeDeptDataExample();
        ResBaseSpeDeptDataExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo("Y");
        if (null!=paramMap){
            if (StringUtil.isNotBlank(paramMap.get("sessionNumber"))){
                criteria.andSessionNumberEqualTo(paramMap.get("sessionNumber"));
            }
            if (StringUtil.isNotBlank(paramMap.get("orgFlow"))){
                criteria.andOrgFlowEqualTo(paramMap.get("orgFlow"));
            }
            if (StringUtil.isNotBlank(paramMap.get("speFlow"))){
                criteria.andSpeFlowEqualTo(paramMap.get("speFlow"));
            }
            if (StringUtil.isNotBlank(paramMap.get("deptFlow"))){
                criteria.andDeptFlowEqualTo(paramMap.get("deptFlow"));
            }
            if (StringUtil.isNotBlank(paramMap.get("dtype"))){
                criteria.andTypeEqualTo(paramMap.get("dtype"));
            }
            if (StringUtil.isNotBlank(paramMap.get("infoType"))){
                criteria.andInfoTypeEqualTo(paramMap.get("infoType"));
            }
        }
        return baseSpeDeptDataMapper.countByExample(example);
    }

    @Override
    public List<Map<String, Object>> countDoctorNum(Map<String, String> paramMap) {
        return resBaseSpeDeptDataExtMapper.countDoctorNum(paramMap);
    }
}
