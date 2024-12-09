package com.pinde.sci.biz.jsres.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IJsResPhyAssBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.PhyAssExtMapper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.sci.model.jsres.ResTeachQualifiedPlanExt;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
//@Transactional(rollbackFor = Exception.class)
public class IJsResPhyAssBizImpl implements IJsResPhyAssBiz {

    @Autowired
    private ResTeachQualifiedPlanMapper planMapper;
    @Autowired
    private ResQualifiedPlanMsgMapper planMsgMapper;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private ResTeachPlanDoctorMapper planDoctorMapper;
    @Autowired
    private PhyAssExtMapper phyAssExtMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;



    @Override
    public boolean savePlannedRelease(ResTeachQualifiedPlanExt ext, List<MultipartFile> fileList,String type) throws UnsupportedEncodingException {
        String planFlow = "";
        if (type.equals("add")){
            planFlow=PkUtil.getUUID();
        }else {
            planFlow=ext.getPlan().getPlanFlow();
        }
        boolean planExtStatus = saveResTeachQualifiedPlanExt(ext,type,planFlow);
        boolean pubFileStatus = savePubFile(fileList, planFlow,"");
        if (planExtStatus==true && pubFileStatus==true){
            return true;
        }
        return false;
    }

    private boolean saveResTeachQualifiedPlanExt(ResTeachQualifiedPlanExt ext,String type,String planFlow) throws UnsupportedEncodingException {
        SysUser user = GlobalContext.getCurrentUser();
        ResTeachQualifiedPlan plan = ext.getPlan();
        if (null !=plan && StringUtil.isNotBlank(plan.getAskContent())){
            String  reason = java.net.URLDecoder.decode(plan.getAskContent(), "UTF-8");
            reason=reason.replaceAll("\n","\\\\n");
            plan.setAskContent(reason);
        }
        List<ResQualifiedPlanMsg> msgList = ext.getPlanMsgList();
        int planCount=0;
        int msgCount=0;
        if (type.equals("add")){ //新增
            plan.setPlanFlow(planFlow);
            plan.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            plan.setCreateTime(DateUtil.getCurrDateTime2());
            plan.setCreateUserFlow(user.getUserFlow());
            planCount = planMapper.insert(plan);
        }else {//修改
            ResTeachQualifiedPlan qualifiedPlan = planMapper.selectByPrimaryKey(plan.getPlanFlow());
            PojoUtils.mergeObject(plan,qualifiedPlan);
            qualifiedPlan.setModifyTime(DateUtil.getCurrDateTime2());
            qualifiedPlan.setModifyUserFlow(user.getUserFlow());
            planCount = planMapper.updateByPrimaryKey(qualifiedPlan);
            List<ResQualifiedPlanMsg> oldMsgList = searchByPlanFlow(planFlow); //删除之前的记录
            for (ResQualifiedPlanMsg msg : oldMsgList) {
                msg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                planMsgMapper.updateByPrimaryKey(msg);
            }
        }
        //保存专业的记录信息
        for (ResQualifiedPlanMsg msg : msgList) {
            msg.setMsgFlow(PkUtil.getUUID());
            msg.setPlanFlow(planFlow);
            msg.setCreateTime(DateUtil.getCurrDateTime2());
            msg.setCreateUserFlow(user.getUserFlow());
            msg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            msgCount=msgCount+planMsgMapper.insert(msg);
        }
        if (planCount>0 && msgCount==msgList.size()){
            return true;
        }
        return false;
    }


    @Override
    public boolean savePubFile(List<MultipartFile> fileList, String planFlow,String fileFlow) {
        int fileCount=0;
        if(null != fileList && fileList.size()>0) {
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).getSize()<=0){
                    return true;
                }
                PubFile pubFile = new PubFile();
                List<PubFile> phyAssUser = pubFileBiz.findFileByTypeFlow("phyAssUser", planFlow);
                if (null != phyAssUser && phyAssUser.size()>0){
                     pubFile = phyAssUser.get(0);
                    if (StringUtil.isNotBlank(fileFlow) && fileFlow.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                        GeneralMethod.setRecordInfo(pubFile, false);
                        pubFileMapper.updateByPrimaryKeySelective(pubFile);
                        return true;
                    }
                }
                if (StringUtil.isNotBlank(fileFlow) && pubFile.getFileFlow().equals(fileFlow)){
                    return true;
                }
                String originalFileName = fileList.get(i).getOriginalFilename();
                String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                //默认的文件名
                pubFile.setFileName(originalFileName);
                //文件后缀名
                pubFile.setFileSuffix(suffix);
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(planFlow);
                pubFile.setProductType("phyAssUser");
                //定义上传路径
                String dateString = DateUtil.getCurrDate2();
                String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "phyAssUser" + File.separator + dateString;
                File fileDir = new File(newDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                //重命名上传后的文件名
                originalFileName = PkUtil.getUUID() + "." + suffix;
                File newFile = new File(fileDir, originalFileName);
                try {
                    fileList.get(i).transferTo(newFile);		//使用transferTo(dest)方法将上传文件写到服务器上指定的文件
                } catch (Exception e) {
                    throw new RuntimeException("文件上传异常");
                }
                String filePath = File.separator + "phyAssUser" + File.separator + dateString + File.separator + originalFileName;
                pubFile.setFilePath(filePath);
                if (com.pinde.core.util.StringUtil.isBlank(pubFile.getFileFlow())) {
                    pubFile.setFileFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(pubFile, true);
                    fileCount=fileCount+ pubFileMapper.insertSelective(pubFile);
                } else {
                    GeneralMethod.setRecordInfo(pubFile, false);
                    fileCount=fileCount+ pubFileMapper.updateByPrimaryKeySelective(pubFile);
                }
            }
        }
        if (fileCount==fileList.size()){
            return true;
        }
        return false;
    }

    @Override
    public List<ResQualifiedPlanMsg> searchByPlanFlow(String planFlow) {
        ResQualifiedPlanMsgExample example = new ResQualifiedPlanMsgExample();
        ResQualifiedPlanMsgExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(planFlow)){
            criteria.andPlanFlowEqualTo(planFlow);
        }
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return planMsgMapper.selectByExample(example);
    }



    @Override
    public boolean delPhyAss(String planFlow) {
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        plan.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        int planCount = planMapper.updateByPrimaryKey(plan);
        List<ResQualifiedPlanMsg> msgList = searchByPlanFlow(planFlow);
        int msgCount=0;
        for (ResQualifiedPlanMsg msg : msgList) {
            msg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            msgCount =msgCount+ planMsgMapper.updateByPrimaryKey(msg);
        }
        pubFileBiz.deleteFileByTypeFlow("phyAss",planFlow);
        if (planCount>0 && msgCount==msgList.size()){
            return true;
        }
        return false;
    }


    @Override
    public int checkPlanContent(String planContent) {
        ResTeachQualifiedPlanExample example = new ResTeachQualifiedPlanExample();
        ResTeachQualifiedPlanExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(planContent)){
            criteria.andPlanContentEqualTo(planContent);
        }
        return planMapper.countByExample(example);
    }

    @Override
    public String saveBaseListEntryUser(String dataMsg, String speId, String speName, String planFlow) {
        //查询已选择的人员信息
        List<ResTeachPlanDoctor> doctorList = searchPlanDoctorByPlanFlow(planFlow, speId, GlobalContext.getCurrentUser().getOrgFlow());
        JSONArray userList = JSON.parseArray(dataMsg);  //页面选择的人员
        int planCount=0;
        int allCount=userList.size();
        //库中数据和页面数据对比，看那些数据需要新增，哪些需要删除
        HashSet<String> oldData = new HashSet<>();  //存放数据库人员的流水号
        for (ResTeachPlanDoctor doctor : doctorList) {
            oldData.add(doctor.getDoctorFlow());
        }
        for (int j = 0; j < userList.size(); j++) {
            String doctorFlow = userList.get(j).toString().split("_")[0];
            if (oldData.contains(doctorFlow)) {
                oldData.remove(doctorFlow); //剩余的是要删除的人员
                userList.remove(j); //剩余的是要新增的人员
                planCount++;
                j--;
            }
        }

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("planFlow",planFlow);
        paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paramMap.put("speId",speId);
        paramMap.put("modifyTime",DateUtil.getCurrDateTime());
        paramMap.put("modifyUserFlow",GlobalContext.getCurrentUser().getUserFlow());
        //页面未选择，但是库中存在的人员需要删除
        for (String doctorFlow : oldData) {
            paramMap.put("doctorFlow",doctorFlow);
            phyAssExtMapper.delplanDoctor(paramMap);
        }

        ResTeachPlanDoctor planDoctor = new ResTeachPlanDoctor();
        planDoctor.setPlanFlow(planFlow);
        planDoctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        planDoctor.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        planDoctor.setSpeId(speId);
        planDoctor.setSpeName(speName);
        GeneralMethod.setRecordInfo(planDoctor, true);
        //页面选择但是库中没有的人员需要添加
        for (Object o : userList) {
            String[] user = o.toString().split("_");
            planDoctor.setRecordFlow(PkUtil.getUUID());
            planDoctor.setDoctorFlow(user[0]);
            planDoctor.setDoctorCode(user[1]);
            planDoctor.setDoctorName(user[2]);
            planDoctor.setDoctorRoleFlow(user[3]);
            planDoctor.setDoctorRoleName(user[4]);
            planDoctor.setGainCertificateId(com.pinde.core.common.GlobalConstant.FLAG_N);
            planDoctor.setGainCertificateName("未生成");
            planDoctor.setSendCertificateId(com.pinde.core.common.GlobalConstant.FLAG_N);
            planDoctor.setSendCertificateName("未发放");
            planCount =planCount+ planDoctorMapper.insert(planDoctor);
        }

        if (planCount==allCount){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @Override
    public List<ResTeachPlanDoctor> searchPlanDoctorByPlanFlow(String planFlow,String speId,String orgFlow) {
        ResTeachPlanDoctorExample example = new ResTeachPlanDoctorExample();
        ResTeachPlanDoctorExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(planFlow)){
            criteria.andPlanFlowEqualTo(planFlow);
        }
        if (StringUtil.isNotBlank(speId)){
            criteria.andSpeIdEqualTo(speId);
        }
        if (StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        List<ResTeachPlanDoctor> doctorList = planDoctorMapper.selectByExample(example);
        return doctorList;
    }

    @Override
    public List<ResTeachPlanDoctor> searchPlanDoctor(ResTeachPlanDoctor doctor) {
        ResTeachPlanDoctorExample example = new ResTeachPlanDoctorExample();
        ResTeachPlanDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (null !=doctor){
            if (StringUtil.isNotBlank(doctor.getRecordFlow())){
                criteria.andRecordFlowEqualTo(doctor.getRecordFlow());
            }
            if (StringUtil.isNotBlank(doctor.getPlanFlow())){
                criteria.andPlanFlowEqualTo(doctor.getPlanFlow());
            }
            if (StringUtil.isNotBlank(doctor.getSpeId())){
                criteria.andSpeIdEqualTo(doctor.getSpeId());
            }
            if (StringUtil.isNotBlank(doctor.getSpeName())){
                criteria.andSpeNameEqualTo(doctor.getSpeName());
            }
            if (StringUtil.isNotBlank(doctor.getDoctorCode())){
                criteria.andDoctorCodeEqualTo(doctor.getDoctorCode());
            }
            if (StringUtil.isNotBlank(doctor.getDoctorName())){
                criteria.andDoctorNameEqualTo(doctor.getDoctorName());
            }
            if (StringUtil.isNotBlank(doctor.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(doctor.getDoctorFlow());
            }
            if (StringUtil.isNotBlank(doctor.getOrgFlow())){
                criteria.andOrgFlowEqualTo(doctor.getOrgFlow());
            }
            if (StringUtil.isNotBlank(doctor.getAffirmFlag())){
                criteria.andAffirmFlagEqualTo(doctor.getAffirmFlag());
            }
            if (StringUtil.isNotBlank(doctor.getAppearFlag())){
                criteria.andAppearFlagEqualTo(doctor.getAppearFlag());
            }
            if (StringUtil.isNotBlank(doctor.getPlanRemove())){
                criteria.andPlanRemoveEqualTo(doctor.getPlanRemove());
            }
            if (StringUtil.isNotBlank(doctor.getGradeId())){
                criteria.andGradeIdEqualTo(doctor.getGradeId());
            }
            if (StringUtil.isNotBlank(doctor.getGradeName())){
                criteria.andGradeNameEqualTo(doctor.getGradeName());
            }

        }
        return planDoctorMapper.selectByExample(example);
    }

    @Override
    public int savePlanDoctorInfo(String planFlow, String recordFlow, String speId, String speName, String doctorCode, String doctorName) {
        ResTeachPlanDoctor planDoctor = planDoctorMapper.selectByPrimaryKey(recordFlow);    //查询人员信息
        planDoctor.setPlanFlow(planFlow);
        planDoctor.setSpeId(speId);
        planDoctor.setSpeName(speName);
        planDoctor.setDoctorCode(doctorCode);
        planDoctor.setDoctorName(doctorName);
        planDoctor.setModifyTime(com.pinde.sci.common.util.DateUtil.getCurrDateTime2());
        planDoctor.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return planDoctorMapper.updateByPrimaryKey(planDoctor);
    }

    @Override
    public boolean saveUser(String userFlow, String userCode, String userName, String sexId, String sexName, String userPhone,
                        String idNo, String userEmail,String deptFlow) {
        SysUser user = sysUserMapper.selectByPrimaryKey(userFlow);
        int userNum=0;
        if (null!=user){
            user.setUserCode(userCode);
            user.setUserName(userName);
            user.setSexId(sexId);
            user.setSexName(sexName);
            user.setUserEmail(userEmail);
            user.setIdNo(idNo);
            user.setUserPhone(userPhone);
            user.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            user.setModifyTime(DateUtil.getCurrDateTime());
            userNum= sysUserMapper.updateByPrimaryKey(user);   //修改用户表的基本信息
        }
        phyAssExtMapper.delUserDept(userFlow);  //删除之前的科室

        int deptNum=0;
        int deptSuccess=0;
        if (StringUtil.isNotBlank(deptFlow)){   //保存新的科室信息
            String[] deptFlows = deptFlow.split(",");
            deptNum=deptFlows.length;
            for (String flow : deptFlows) {
                SysDept sysDept = sysDeptMapper.selectByPrimaryKey(flow);
                if (null !=sysDept){
                    SysUserDept userDept = new SysUserDept();
                    userDept.setRecordFlow(PkUtil.getUUID());
                    userDept.setUserFlow(userFlow);
                    userDept.setDeptFlow(sysDept.getDeptFlow());
                    userDept.setDeptName(sysDept.getDeptName());
                    userDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                    userDept.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                    userDept.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    userDept.setCreateTime(DateUtil.getCurrDateTime());
                    userDept.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                    deptSuccess=deptSuccess+sysUserDeptMapper.insert(userDept);
                }
            }
        }
        if (userNum >0 && deptNum==deptSuccess){
            return true;
        }
        return false;
    }


    @Override
    public int importBaseUserExcel(MultipartFile file,String planFlow) throws Exception {
        InputStream is = file.getInputStream();
        byte[] fileData = new byte[(int) file.getSize()];
        is.read(fileData);
        Workbook wb =  createUserWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
        return parseExcelToUser(wb,planFlow);
    }

    // 读取数据
    private int parseExcelToUser(Workbook wb,String planFlow) {
        ResTeachQualifiedPlan plan = planMapper.selectByPrimaryKey(planFlow);
        String planContent = plan.getPlanContent(); //当前培训计划的名称
        int succCount = 0;   // 导入成功条数
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
            if (row_num < 1) {
                throw new RuntimeException("没有数据！");
            }
            Row titleR = sheet.getRow(0);//获取表头
            int cell_num = titleR.getLastCellNum();//获取表头单元格数
            List<String> colnames = new ArrayList<>();
            for (int i = 0; i < cell_num; i++) {
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            List<Map<String, Object>> haveUserList = new ArrayList<>(); //已存在的人员
            List<Map<String, Object>> noUserList = new ArrayList<>(); //不存在的人员
            //数据行开始遍历
            for (int i = 1; i <= row_num; i++) {
                Row r = sheet.getRow(i);
                String value = "";
                Map<String, Object> map = new HashMap<>();
                for (int j = 0; j < colnames.size(); j++) {
                    Cell cell = r.getCell(j);
                    if (null == cell || com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(cell.toString().trim())) {
                        continue;
                    }
                    if (r.getCell((short) j).getCellType().getCode() == 1) {
                        value = r.getCell((short) j).getStringCellValue();
                    }else{
                        value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
                    }
                    if ("培训计划".equals(colnames.get(j))) {
                        map.put("planContent", value);
                    } else if ("培训专业".equals(colnames.get(j))) {
                        map.put("speName", value);
                    } else if ("基地".equals(colnames.get(j))) {
                        map.put("orgName", value);
                    } else if ("登录名".equals(colnames.get(j))) {
                        map.put("userCode", value);
                    }else if (("姓名".equals(colnames.get(j)))){
                        map.put("userName",value);
                    } else if (("性别".equals(colnames.get(j)))){
                        map.put("sexName",value);
                    } else if (("角色".equals(colnames.get(j)))){
                        map.put("roleName",value);
                    } else if (("科室".equals(colnames.get(j)))){
                        map.put("deptName",value);
                    } else if (("手机号".equals(colnames.get(j)))){
                        map.put("userPhone",value);
                    } else if (("身份证号码".equals(colnames.get(j)))){
                        map.put("idNo",value);
                    } else if (("电子邮箱".equals(colnames.get(j)))){
                        map.put("userEmail",value);
                    }
                }
                if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("planContent")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，培训计划为空！");
                }
                String content = map.get("planContent").toString();
                if (!planContent.equals(content)) {
                    throw new RuntimeException("导入失败！导入的文件不是该培训方案的模板！");
                }
                if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("speName")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，培训专业为空！");
                }
                if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("orgName")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，基地为空！");
                }
                if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("doctorCode")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，登录名为空！");
                }
                if (com.pinde.sci.ctrl.sch.plan.util.StringUtil.isBlank(String.valueOf(map.get("doctorName")))) {
                    throw new RuntimeException("导入失败！第" + i + "行，姓名为空！");
                }

                // 查询人员是否存在
                SysUserExample example = new SysUserExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andUserCodeEqualTo((String)map.get("userCode"));
                int count = sysUserMapper.countByExample(example);
                if(count > 0){
                    haveUserList.add(map);  //存在
                }else {
                    noUserList.add(map);    //不存在
                }
            }
            //不存在的人员
            if(null != noUserList && 0 < noUserList.size()){
                SysUser user = new SysUser();
                user.setStatusId(UserStatusEnum.Activated.getId());
                user.setStatusDesc(UserStatusEnum.Activated.getName());

                SysUserDept userDept = new SysUserDept();
                GeneralMethod.setRecordInfo(userDept, true);
                for(Map<String, Object> map : noUserList){
                    String userFlow = PkUtil.getUUID();
                    user.setUserFlow(userFlow);
                    user.setUserName((String)map.get("userName"));
                    user.setUserCode((String)map.get("userCode"));
                    user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "JSzp123456#"));
                    String sexName=(String)map.get("sexName");
                    if (StringUtil.isNotBlank(sexName)){
                        if (sexName.equals("男")){
                            user.setSexId("Man");
                            user.setSexName(sexName);
                        }else  if(sexName.equals("女")){
                            user.setSexId("Woman");
                            user.setSexName(sexName);
                        }
                    }
                    user.setUserPhone((String)map.get("userPhone"));
                    user.setIdNo((String)map.get("idNo"));
                    user.setUserEmail((String)map.get("userEmail"));
                    String speName = (String)map.get("speName");
                    user.setResTrainingSpeName(speName);
                    user.setResTrainingSpeId(getDictId(speName, com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()));
                    SysOrg sysOrg = orgBiz.readSysOrgByName((String) map.get("orgName"));
                    if (null != sysOrg){
                        user.setOrgFlow(sysOrg.getOrgFlow());
                        user.setOrgName(sysOrg.getOrgName());
                        userDept.setOrgFlow(sysOrg.getOrgFlow());
                        userDept.setOrgName(sysOrg.getOrgName());
                    }
                    this.addUser(user,(String)map.get("roleName"),planFlow);     //添加人员和角色
                    SysDept dept = deptBiz.readSysDeptByName(user.getOrgFlow(), (String) map.get("deptName"));  //添加科室
                    if (null !=dept){
                        userDept.setRecordFlow(PkUtil.getUUID());
                        userDept.setUserFlow(userFlow);
                        userDept.setDeptFlow(dept.getDeptFlow());
                        userDept.setDeptName(dept.getDeptName());
                        sysUserDeptMapper.insert(userDept);
                    }
                    succCount ++;
                }
            }

            //存在的人员
            if (null != haveUserList && 0 < haveUserList.size()){
                ResTeachPlanDoctor planDoctor = new ResTeachPlanDoctor();
                planDoctor.setPlanFlow(planFlow);
                GeneralMethod.setRecordInfo(planDoctor, true);
                for (Map<String, Object> map : haveUserList) {
                    int num = selectPlanDoctorByUserCode((String) map.get("userCode"),(String) map.get("orgName"),planFlow); //检查人员是否在培训计划中
                    if (num ==0){
                        //登录名是唯一的，按登录名判断就行，以库里的为准
                        SysUser user = searchByUserCode((String) map.get("userCode"));
                        planDoctor.setRecordFlow(PkUtil.getUUID());
                        planDoctor.setOrgFlow(user.getOrgFlow());
                        planDoctor.setOrgName(user.getOrgName());
                        String speName = (String)map.get("speName");
                        planDoctor.setSpeId(getDictId(speName, com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()));
                        planDoctor.setSpeName(speName);
                        planDoctor.setDoctorCode(user.getUserCode());
                        planDoctor.setDoctorName(user.getUserName());
                        planDoctor.setDoctorFlow(user.getUserFlow());
                        Map<String, Object> maps = phyAssExtMapper.searchSysUserRoleName(user.getUserFlow());
                        if (null != maps){
                            planDoctor.setDoctorRoleFlow(maps.get("roleFlow").toString());
                            planDoctor.setDoctorRoleName(maps.get("roleName").toString());
                        }
                        planDoctorMapper.insert(planDoctor);
                    }else {
                        throw new RuntimeException("导入失败！"+map.get("userName")+"已在该培训计划中！");
                    }
                    succCount++;
                }
            }
        }
        return succCount;
    }

    @Transactional
    @Override
    public int addUser(SysUser user,String roleName,String planFlow) {
        // 新增用户授权角色
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setOrgFlow(user.getOrgFlow());
        userRole.setWsId("res");
        String roleFlow = "";
        if(StringUtil.isNotBlank(roleName)){
            if("带教老师".equals(roleName)){
                roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
            }else if("科主任".equals(roleName)){
                roleFlow = InitConfig.getSysCfg("res_head_role_flow");
            }else if("教秘".equals(roleName)){
                roleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
            }
        }

        ResTeachPlanDoctor planDoctor = new ResTeachPlanDoctor();
        planDoctor.setPlanFlow(planFlow);
        planDoctor.setRecordFlow(PkUtil.getUUID());
        planDoctor.setOrgFlow(user.getOrgFlow());
        planDoctor.setOrgName(user.getOrgName());
        planDoctor.setSpeId(user.getResTrainingSpeId());
        planDoctor.setSpeName(user.getResTrainingSpeName());
        planDoctor.setDoctorCode(user.getUserCode());
        planDoctor.setDoctorName(user.getUserName());
        planDoctor.setDoctorFlow(user.getUserFlow());
        planDoctor.setDoctorRoleFlow(roleFlow);
        planDoctor.setDoctorRoleName(roleName);
        GeneralMethod.setRecordInfo(planDoctor, true);
        planDoctorMapper.insert(planDoctor);
        GeneralMethod.setRecordInfo(user, true);
        userRole.setRoleFlow(roleFlow);
        userRole.setAuthTime(DateUtil.getCurrDateTime());
        userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        userRole.setRecordFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(userRole, true);
        sysUserRoleMapper.insertSelective(userRole);
        return sysUserMapper.insertSelective(user);
    }
    private SysUser searchByUserCode(String userCode){
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(userCode)){
            criteria.andUserCodeEqualTo(userCode);
        }
        List<SysUser> userList = sysUserMapper.selectByExample(example);
        if (null != userList && userList.size()>0){
            return userList.get(0);
        }else {
            return null;
        }
    }


    private static Workbook createUserWorkbook(InputStream inS) throws IOException, InvalidFormatException {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
//        // EXCEL2003使用的是微软的文件系统
//        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//            return new HSSFWorkbook(inS);
//        }
//        // EXCEL2007使用的是OOM文件格式
//        if (POIXMLDocument.hasOOXMLHeader(inS)) {
//            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
//            return new XSSFWorkbook(OPCPackage.open(inS));
//        }
        try{
            return WorkbookFactory.create(inS);
        }catch (Exception e) {
            throw new IOException("不能解析的excel版本");
        }
    }

    private static String _doubleTrans(double d){
        if((double)Math.round(d) - d == 0.0D){
            return String.valueOf((long)d);
        }else{
            return String.valueOf(d);
        }
    }

    private String getDictId(String dictName,String dictTypeId){
        Map<String,String> dictNameMap= InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }


    @Override
    public int selectPlanDoctorByUserCode(String userCode,String orgName,String planFlow) {
        // 查询人员是否存在
        ResTeachPlanDoctorExample example=new ResTeachPlanDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorCodeEqualTo(userCode).andOrgNameEqualTo(orgName)
                .andPlanFlowEqualTo(planFlow);
        return planDoctorMapper.countByExample(example);
    }

    @Override
    public int countPlanApperNum(String planFlow) {
        ResTeachPlanDoctorExample example=new ResTeachPlanDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andPlanFlowEqualTo(planFlow)
                .andAppearFlagIsNotNull();
        return planDoctorMapper.countByExample(example);
    }

    @Override
    public int operateUser(List<String> recordFlows,String type) {
        int count=0;
        ResTeachPlanDoctorExample example=new ResTeachPlanDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andRecordFlowIn(recordFlows);
        List<ResTeachPlanDoctor> doctorList = planDoctorMapper.selectByExample(example);
        if (null != doctorList && doctorList.size()>0){
            if (type.equals("del")){    //删除
                for (ResTeachPlanDoctor doctor : doctorList) {
                    doctor.setPlanRemove(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    planDoctorMapper.updateByPrimaryKey(doctor);
                    count++;
                }
            }else { //确认
                for (ResTeachPlanDoctor doctor : doctorList) {
                    doctor.setAffirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    planDoctorMapper.updateByPrimaryKey(doctor);
                    count++;
                }
            }

        }
        return count;
    }


    @Override
    public String checkImg(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix").toLowerCase()).split(","));
        }
        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
        if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
            return "请上传 "+InitConfig.getSysCfg("inx_image_support_suffix")+"格式的文件";
        }
        long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
        if(file.getSize() > limitSize*1024*1024){
            return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M！";
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
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
                e.printStackTrace();
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
                    e.printStackTrace();
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
        }
        return path;
    }
}

