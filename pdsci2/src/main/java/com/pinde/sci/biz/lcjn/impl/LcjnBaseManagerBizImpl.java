package com.pinde.sci.biz.lcjn.impl;

import com.pinde.core.common.enums.LcjnAuditStatusEnum;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnBaseManagerBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.ctrl.lcjn.LcjnBaseManagerController;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.lcjn.LcjnBaseManagerExtMapper;
import com.pinde.core.model.LcjnFixedAssets;
import com.pinde.core.model.LcjnFixedAssetsExample;
import com.pinde.core.model.LcjnSkillCfg;
import com.pinde.core.model.LcjnSkillCfgDetail;
import com.pinde.core.model.LcjnSkillCfgDetailExample;
import com.pinde.core.model.LcjnSkillCfgExample;
import com.pinde.core.model.LcjnSupplies;
import com.pinde.core.model.LcjnSuppliesExample;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnBaseManagerBizImpl implements ILcjnBaseManagerBiz {

    @Autowired
    private LcjnBaseManagerExtMapper baseManagerExtMapper;
    @Autowired
    private LcjnCourseInfoMapper lciMapper;
    @Autowired
    private LcjnCourseSkillMapper lcsMapper;
    @Autowired
    private LcjnCourseTeaMapper lcteaMapper;
    @Autowired
    private LcjnCourseTimeMapper lctimeMapper;
    @Autowired
    private LcjnDoctorCourseMapper ldcMapper;
    @Autowired
    private LcjnSkillCfgMapper lscMapper;
    @Autowired
    private LcjnSkillCfgDetailMapper lscdMapper;
    @Autowired
    private LcjnSuppliesMapper lsMapper;
    @Autowired
    private LcjnFixedAssetsMapper lfaMapper;
    @Autowired
    private SysUserMapper suMapper;
    private static final Logger logger = LoggerFactory.getLogger(LcjnBaseManagerController.class);

    @Override
    public List<Map<String, Object>> queryCourseList(Map<String, String> map) {
        return baseManagerExtMapper.queryCourseList(map);
    }

    @Override
    public List<LcjnSkillCfg> queryCourseSkillsList() {
        LcjnSkillCfgExample example = new LcjnSkillCfgExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        return lscMapper.selectByExample(example);
    }

    @Override
    public List<LcjnCourseSkill> queryCourseSkillsByFlow(String courseFlow) {
        LcjnCourseSkillExample example = new LcjnCourseSkillExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andCourseFlowEqualTo(courseFlow);
        return lcsMapper.selectByExample(example);
    }

    @Override
    public LcjnCourseInfo queryCourseByFlow(String courseFlow) {
        return lciMapper.selectByPrimaryKey(courseFlow);
    }

    @Override
    public List<LcjnCourseTime> queryCourseTimeByFlow(String courseFlow) {
        LcjnCourseTimeExample example = new LcjnCourseTimeExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andCourseFlowEqualTo(courseFlow);
        return lctimeMapper.selectByExample(example);
    }

    @Override
    public List<SysUser> queryCourseTeachersList() {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andIsExamTeaEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return suMapper.selectByExample(example);
    }

    @Override
    public List<LcjnCourseTea> queryCourseTeachersByFlow(String courseFlow) {
        LcjnCourseTeaExample example = new LcjnCourseTeaExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andCourseFlowEqualTo(courseFlow);
        return lcteaMapper.selectByExample(example);
    }

    @Override
    public int saveCourseTrain(Map<String, Object> param) {
        int count = 0;
        LcjnCourseInfo course = (LcjnCourseInfo)param.get("course");
        List<String> skillFlowList = (List<String>)param.get("skillFlowList");
        List<String> skillNameList = (List<String>)param.get("skillNameList");
        List<String> trainStartDate = (List<String>)param.get("trainStartDate");
        List<String> startTime = (List<String>)param.get("startTime");
        List<String> trainEndDate = (List<String>)param.get("trainEndDate");
        List<String> endTime = (List<String>)param.get("endTime");
        List<String> userFlowList = (List<String>)param.get("userFlowList");
        List<String> userNameList = (List<String>)param.get("userNameList");
        List<String> courseFee = (List<String>)param.get("courseFee");
        if(StringUtil.isNotBlank(course.getCourseFlow())){//修改
            //修改课程信息表
            GeneralMethod.setRecordInfo(course,false);
            count += lciMapper.updateByPrimaryKeySelective(course);
            List<String> skillRecordList = (List<String>)param.get("skillRecordList");
            List<String> trainTimeRecordList = (List<String>)param.get("trainTimeRecordList");
            List<String> userRecordList = (List<String>)param.get("userRecordList");
            if(null != skillRecordList && skillRecordList.size() > 0){//只是基于历史数据变更修改时间
                LcjnCourseSkill lcs = new LcjnCourseSkill();
                for(int i=0; i<skillRecordList.size(); i++){
                    lcs.setRecordFlow(skillRecordList.get(i));
                    lcs.setCourseName(course.getCourseName());
                    GeneralMethod.setRecordInfo(lcs,false);
                    count += lcsMapper.updateByPrimaryKeySelective(lcs);
                }
            }else{
                //删除历史数据（所需技能）
                LcjnCourseSkillExample example = new LcjnCourseSkillExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andCourseFlowEqualTo(course.getCourseFlow());
                lcsMapper.deleteByExample(example);
                //新增课程所需技能表
                LcjnCourseSkill lcs = new LcjnCourseSkill();
                lcs.setCourseFlow(course.getCourseFlow());
                lcs.setCourseName(course.getCourseName());
                for(int i=0; i<skillFlowList.size(); i++){
                    lcs.setRecordFlow(PkUtil.getUUID());
                    lcs.setSkillFlow(skillFlowList.get(i));
                    lcs.setSkillName(skillNameList.get(i));
                    GeneralMethod.setRecordInfo(lcs,true);
                    count += lcsMapper.insertSelective(lcs);
                }
            }
            if(null != trainTimeRecordList && trainTimeRecordList.size() > 0){
                //删除不在此次记录中的历史数据
                LcjnCourseTimeExample example = new LcjnCourseTimeExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andCourseFlowEqualTo(course.getCourseFlow()).andRecordFlowNotIn(trainTimeRecordList);//培训时间不会太多
                lctimeMapper.deleteByExample(example);
                //只是基于历史数据变更培训时间
                LcjnCourseTime lctime = new LcjnCourseTime();
                for(int i=0; i<trainTimeRecordList.size(); i++){
                    lctime.setRecordFlow(trainTimeRecordList.get(i));
                    lctime.setTrainStartDate(trainStartDate.get(i));
                    lctime.setStartTime(startTime.get(i));
                    lctime.setTrainEndDate(trainEndDate.get(i));
                    lctime.setEndTime(endTime.get(i));
                    lctime.setCourseName(course.getCourseName());
                    GeneralMethod.setRecordInfo(lctime,false);
                    count += lctimeMapper.updateByPrimaryKeySelective(lctime);
                }
                //另新增课程培训时间表
                if(trainStartDate.size() > trainTimeRecordList.size()){
                    LcjnCourseTime lctime2 = new LcjnCourseTime();
                    lctime2.setCourseFlow(course.getCourseFlow());
                    lctime2.setCourseName(course.getCourseName());
                    for(int i=trainTimeRecordList.size(); i<trainStartDate.size(); i++){
                        lctime2.setRecordFlow(PkUtil.getUUID());
                        lctime2.setTrainStartDate(trainStartDate.get(i));
                        lctime2.setStartTime(startTime.get(i));
                        lctime2.setTrainEndDate(trainEndDate.get(i));
                        lctime2.setEndTime(endTime.get(i));
                        GeneralMethod.setRecordInfo(lctime2,true);
                        count += lctimeMapper.insertSelective(lctime2);
                    }
                }
            }
            if(null != userRecordList && userRecordList.size() > 0) {
                //删除不在此次记录中的历史数据
                LcjnCourseTeaExample example = new LcjnCourseTeaExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andCourseFlowEqualTo(course.getCourseFlow()).andRecordFlowNotIn(userRecordList);//任课老师不会太多
                lcteaMapper.deleteByExample(example);
                //只是基于历史数据变更任课老师课程费用
                LcjnCourseTea lctea = new LcjnCourseTea();
                for(int i=0; i<userRecordList.size(); i++){
                    lctea.setRecordFlow(userRecordList.get(i));
                    lctea.setTeachingCost(courseFee.get(i));
                    lctea.setCourseName(course.getCourseName());
                    GeneralMethod.setRecordInfo(lctea,false);
                    count += lcteaMapper.updateByPrimaryKeySelective(lctea);
                }
            }else{
                //删除历史数据（任课老师）
                LcjnCourseTeaExample example = new LcjnCourseTeaExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andCourseFlowEqualTo(course.getCourseFlow());
                lcteaMapper.deleteByExample(example);
                //新增课程任课老师表
                LcjnCourseTea lctea = new LcjnCourseTea();
                lctea.setCourseFlow(course.getCourseFlow());
                lctea.setCourseName(course.getCourseName());
                for(int i=0; i<userFlowList.size(); i++){
                    lctea.setRecordFlow(PkUtil.getUUID());
                    lctea.setUserFlow(userFlowList.get(i));
                    lctea.setUserName(userNameList.get(i));
                    lctea.setTeachingCost(courseFee.get(i));
                    GeneralMethod.setRecordInfo(lctea,true);
                    count += lcteaMapper.insertSelective(lctea);
                }
            }
        }else{//新增
            //新增培训课程表
            String pk = PkUtil.getUUID();
            course.setCourseFlow(pk);
            course.setIsReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            course.setIsScoreReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            course.setCodeInfo("func://funcFlow=signin&courseFlow="+pk);//二维码规则
            course.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            course.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            GeneralMethod.setRecordInfo(course,true);
            count += lciMapper.insertSelective(course);
            //新增课程所需技能表
            LcjnCourseSkill lcs = new LcjnCourseSkill();
            lcs.setCourseFlow(pk);
            lcs.setCourseName(course.getCourseName());
            for(int i=0; i<skillFlowList.size(); i++){
                lcs.setRecordFlow(PkUtil.getUUID());
                lcs.setSkillFlow(skillFlowList.get(i));
                lcs.setSkillName(skillNameList.get(i));
                GeneralMethod.setRecordInfo(lcs,true);
                count += lcsMapper.insertSelective(lcs);
            }
            //新增课程培训时间表
            LcjnCourseTime lctime = new LcjnCourseTime();
            lctime.setCourseFlow(pk);
            lctime.setCourseName(course.getCourseName());
            for(int i=0; i<trainStartDate.size(); i++){
                lctime.setRecordFlow(PkUtil.getUUID());
                lctime.setTrainStartDate(trainStartDate.get(i));
                lctime.setStartTime(startTime.get(i));
                lctime.setTrainEndDate(trainEndDate.get(i));
                lctime.setEndTime(endTime.get(i));
                GeneralMethod.setRecordInfo(lctime,true);
                count += lctimeMapper.insertSelective(lctime);
            }
            //新增课程任课老师表
            LcjnCourseTea lctea = new LcjnCourseTea();
            lctea.setCourseFlow(pk);
            lctea.setCourseName(course.getCourseName());
            for(int i=0; i<userFlowList.size(); i++){
                lctea.setRecordFlow(PkUtil.getUUID());
                lctea.setUserFlow(userFlowList.get(i));
                lctea.setUserName(userNameList.get(i));
                lctea.setTeachingCost(courseFee.get(i));
                GeneralMethod.setRecordInfo(lctea,true);
                count += lcteaMapper.insertSelective(lctea);
            }
        }
        return count;
    }

    @Override
    public List<LcjnCourseTime> queryCourseTimeList(String time) {//time格式：yyyy-MM-
        return baseManagerExtMapper.queryCourseTimeList(time+"01",time+"31");
    }

    @Override
    public int releasedInfo(String courseFlow) {
        LcjnCourseInfo lci = new LcjnCourseInfo();
        lci.setIsReleased(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        lci.setCourseFlow(courseFlow);
        return lciMapper.updateByPrimaryKeySelective(lci);
    }

    @Override
    public int delCourseTrain(String courseFlow, String isReleased) {
        //判断是否课程已在培训中
        Boolean isTraining = false;
        LcjnCourseTimeExample lctimeExample = new LcjnCourseTimeExample();
        lctimeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andCourseFlowEqualTo(courseFlow);
        List<LcjnCourseTime> lctimeList = lctimeMapper.selectByExample(lctimeExample);
        if(null != lctimeList && lctimeList.size() > 0){
            String currentTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
            for(LcjnCourseTime time : lctimeList){
                String trainStartTime = time.getTrainStartDate()+" "+time.getStartTime();
                if(currentTime.compareTo(trainStartTime) > 0){
                    isTraining = true;
                    break;
                }
            }
        }
        if(!isTraining){
            LcjnCourseInfo lci = new LcjnCourseInfo();
            lci.setCourseFlow(courseFlow);
            lci.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);//只对培训课程主表伪删除（学员预约状态变为失效，但依旧可以查看课程关联信息,故关联信息不删）
            if (StringUtil.isNotBlank(isReleased) && isReleased.equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {//已预约学员信息审核状态变为失效
                LcjnDoctorCourse ldc = new LcjnDoctorCourse();
                ldc.setAuditStatusId(LcjnAuditStatusEnum.Invalid.getId());
                ldc.setAuditStatusName(LcjnAuditStatusEnum.Invalid.getName());
                LcjnDoctorCourseExample example = new LcjnDoctorCourseExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
                ldcMapper.updateByExampleSelective(ldc,example);
            }
            return lciMapper.updateByPrimaryKeySelective(lci);
        }else{
            return -1;
        }
    }

    @Override
    public List<SysUser> querySysUser(SysUser user) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria().andIsExamTeaEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(user.getTitleId())){
            criteria.andTitleIdEqualTo(user.getTitleId());
        }
        if(StringUtil.isNotBlank(user.getUserName())){
            criteria.andUserNameLike("%"+user.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(user.getRecordStatus())){
            criteria.andRecordStatusEqualTo(user.getRecordStatus());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return suMapper.selectByExample(example);
    }

    @Override
    public int saveTeaInfo(SysUser user) {
        int count = 0;
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        if(StringUtil.isNotBlank(user.getUserFlow())){
            SysUserExample example = new SysUserExample();
            example.createCriteria().andUserCodeEqualTo(user.getUserCode()).andUserFlowNotEqualTo(user.getUserFlow());
            if(suMapper.countByExample(example) == 0){
                count = suMapper.updateByPrimaryKeySelective(user);
            }else{
                count = -1;
            }
        }else{
            SysUserExample example = new SysUserExample();
            example.createCriteria().andUserCodeEqualTo(user.getUserCode());
            int num = suMapper.countByExample(example);
            if(num == 0){
                user.setUserFlow(PkUtil.getUUID());
                user.setIsExamTea(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);//Y 老师标识
                user.setUserPasswd("123456");//无效，为解决表结构不能为空而设
                GeneralMethod.setRecordInfo(user,true);//初始为启用状态
                count = suMapper.insertSelective(user);
            }else{
                count = -1;
            }
        }
        return count;
    }

    @Override
    public int importTeacherExcel(MultipartFile file) throws Exception{
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb);
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception(e.getMessage());
        } finally {
            is.close();
        }
    }
    private Workbook createCommonWorkbook(InputStream inS) throws Exception {
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
    private int parseExcel(Workbook wb) throws Exception{
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
                SysUser user = new SysUser();
                user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
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
                    if("用户名".equals(currTitle)){
                        user.setUserCode(value);
                    }
                    if("姓名".equals(currTitle)){
                        user.setUserName(value);
                    }
                    if("性别".equals(currTitle)){
                        if("男".equals(value)){
                            user.setSexId("Man");
                            user.setSexName(value);
                        }else if("女".equals(value)){
                            user.setSexId("Woman");
                            user.setSexName(value);
                        }
                    }
                    if("职称".equals(currTitle) && StringUtil.isNotBlank(value)){
                        user.setTitleId(InitConfig.getDictNameMap("LcjnUserTitle").get(value));
                        user.setTitleName(value);
                    }
                    if("联系方式".equals(currTitle) && StringUtil.isNotBlank(value)){
                        user.setUserPhone(value);
                    }
                    if("所在单位".equals(currTitle) && StringUtil.isNotBlank(value)){
                        user.setWorkOrgName(value);
                    }
                }
                if(StringUtil.isBlank(user.getUserCode())){
                    throw new Exception("导入失败！第"+ (count+2) +"行用户名不能为空！");
                }
                if(StringUtil.isBlank(user.getUserName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行姓名不能为空！");
                }
                SysUserExample example = new SysUserExample();
                example.createCriteria().andUserCodeEqualTo(user.getUserCode()).andIsExamTeaEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                int num = suMapper.countByExample(example);
                if(num <= 0){//新增
                    user.setUserFlow(PkUtil.getUUID());
                    user.setIsExamTea(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);//Y 老师标识
                    user.setUserPasswd("123456");//无效，为解决表结构不能为空而设
                    GeneralMethod.setRecordInfo(user,true);//初始为启用状态
                    suMapper.insertSelective(user);
                }else{//修改
                    suMapper.updateByExampleSelective(user,example);
                }
                count ++;
            }
        }
        return count;
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }
    @Override
    public int teacherOption(String userFlow, String recordStatus) {
        int count = 0;
        if(StringUtil.isNotBlank(userFlow)){
            SysUser user = new SysUser();
            user.setUserFlow(userFlow);
            user.setRecordStatus(recordStatus);
            count = suMapper.updateByPrimaryKeySelective(user);
        }
        return count;
    }

    @Override
    public List<Map<String, Object>> querySkillConfigList() {
        return baseManagerExtMapper.querySkillConfigList(GlobalContext.getCurrentUser().getOrgFlow());
    }

    @Override
    public List<LcjnSupplies> querySuppliesList() {
        LcjnSuppliesExample example = new LcjnSuppliesExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        example.setOrderByClause("CREATE_TIME");
        return lsMapper.selectByExample(example);
    }

    @Override
    public List<LcjnFixedAssets> queryFixedAssetsList() {
        LcjnFixedAssetsExample example = new LcjnFixedAssetsExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        example.setOrderByClause("CREATE_TIME");
        return lfaMapper.selectByExample(example);
    }

    @Override
    public int delSkillsConfig(String skillFlow) {
        int count = 0;
        if(StringUtil.isNotBlank(skillFlow)){
            LcjnSkillCfg lsc = new LcjnSkillCfg();
            lsc.setSkillFlow(skillFlow);
            lsc.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            count = lscMapper.updateByPrimaryKeySelective(lsc);
            LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
            lscd.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
            example.createCriteria().andSkillFlowEqualTo(skillFlow);
            count += lscdMapper.updateByExampleSelective(lscd,example);
        }
        return count;
    }

    @Override
    public List<LcjnSkillCfgDetail> querySkillDetailByFlow(String skillFlow) {
        LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSkillFlowEqualTo(skillFlow);
        example.setOrderByClause("CREATE_TIME");
        return lscdMapper.selectByExample(example);
    }

    @Override
    public int saveSkillConfig(Map<String, Object> param) {
        int count = 0;
        List<String> suppliesList = (List<String>)param.get("suppliesList");
        List<String> suppliesNumList = (List<String>)param.get("suppliesNumList");
        List<String> assetsList = (List<String>)param.get("assetsList");
        List<String> assetsNumList = (List<String>)param.get("assetsNumList");
        if(StringUtil.isNotBlank(String.valueOf(param.get("skillFlow")))){//修改
            List<String> suppliesRecordList = (List<String>)param.get("suppliesRecordList");
            List<String> assetsRecordList = (List<String>)param.get("assetsRecordList");
            if(null != suppliesRecordList && suppliesRecordList.size() > 0){//只是基于历史数据变更使用数量（耗材）
                //删除不在此次记录中的历史数据
                LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andTypeIdEqualTo("SUPPLIES")
                        .andSkillFlowEqualTo(String.valueOf(param.get("skillFlow"))).andRecordFlowNotIn(suppliesRecordList);
                lscdMapper.deleteByExample(example);
                LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
                for(int i=0; i<suppliesRecordList.size(); i++){
                    lscd.setRecordFlow(suppliesRecordList.get(i));
                    lscd.setUseNum(suppliesNumList.get(i));
                    GeneralMethod.setRecordInfo(lscd,false);
                    count += lscdMapper.updateByPrimaryKeySelective(lscd);
                }
            }else{
                //删除历史数据（耗材）
                LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSkillFlowEqualTo(String.valueOf(param.get("skillFlow"))).andTypeIdEqualTo("SUPPLIES");
                lscdMapper.deleteByExample(example);
                //新增技能配置详情表
                LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
                lscd.setSkillFlow(String.valueOf(param.get("skillFlow")));
                lscd.setSkillName(String.valueOf(param.get("skillName")));
                for(int i=0; i<suppliesList.size(); i++){
                    lscd.setRecordFlow(PkUtil.getUUID());
                    lscd.setTypeId("SUPPLIES");
                    lscd.setTypeName("耗材");
                    lscd.setMateriaFlow(suppliesList.get(i));
                    lscd.setUseNum(suppliesNumList.get(i));
                    GeneralMethod.setRecordInfo(lscd,true);
                    count += lscdMapper.insertSelective(lscd);
                }
            }
            if(null != assetsRecordList && assetsRecordList.size() > 0){//只是基于历史数据变更使用数量（固定资产）
                //删除不在此次记录中的历史数据
                LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andTypeIdEqualTo("FIXED")
                        .andSkillFlowEqualTo(String.valueOf(param.get("skillFlow"))).andRecordFlowNotIn(assetsRecordList);
                lscdMapper.deleteByExample(example);
                LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
                for(int i=0; i<assetsRecordList.size(); i++){
                    lscd.setRecordFlow(assetsRecordList.get(i));
                    lscd.setUseNum(assetsNumList.get(i));
                    GeneralMethod.setRecordInfo(lscd,false);
                    count += lscdMapper.updateByPrimaryKeySelective(lscd);
                }
            }else{
                //删除历史数据（固定资产）
                LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andSkillFlowEqualTo(String.valueOf(param.get("skillFlow"))).andTypeIdEqualTo("FIXED");
                lscdMapper.deleteByExample(example);
                //新增技能配置详情表
                LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
                lscd.setSkillFlow(String.valueOf(param.get("skillFlow")));
                lscd.setSkillName(String.valueOf(param.get("skillName")));
                for(int i=0; i<assetsList.size(); i++){
                    lscd.setRecordFlow(PkUtil.getUUID());
                    lscd.setTypeId("FIXED");
                    lscd.setTypeName("固定资产");
                    lscd.setMateriaFlow(assetsList.get(i));
                    lscd.setUseNum(assetsNumList.get(i));
                    GeneralMethod.setRecordInfo(lscd,true);
                    count += lscdMapper.insertSelective(lscd);
                }
            }
        }else{//新增
            LcjnSkillCfgExample example = new LcjnSkillCfgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andSkillIdEqualTo(String.valueOf(param.get("skillId"))).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
            if(lscMapper.countByExample(example) > 0){//是否已配置过同技能
                return -1;
            }
            //新增技能表
            LcjnSkillCfg lsc = new LcjnSkillCfg();
            String pk = PkUtil.getUUID();
            lsc.setSkillFlow(pk);
            lsc.setSkillId(String.valueOf(param.get("skillId")));
            lsc.setSkillName(String.valueOf(param.get("skillName")));
            lsc.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            lsc.setOrgName(GlobalContext.getCurrentUser().getOrgName());
            GeneralMethod.setRecordInfo(lsc,true);
            count += lscMapper.insertSelective(lsc);
            //新增技能配置详情表
            LcjnSkillCfgDetail lscd = new LcjnSkillCfgDetail();
            lscd.setSkillFlow(pk);
            lscd.setSkillName(String.valueOf(param.get("skillName")));
            for(int i=0; i<suppliesList.size(); i++){
                lscd.setRecordFlow(PkUtil.getUUID());
                lscd.setTypeId("SUPPLIES");
                lscd.setTypeName("耗材");
                lscd.setMateriaFlow(suppliesList.get(i));
                lscd.setUseNum(suppliesNumList.get(i));
                GeneralMethod.setRecordInfo(lscd,true);
                count += lscdMapper.insertSelective(lscd);
            }
            for(int i=0; i<assetsList.size(); i++){
                lscd.setRecordFlow(PkUtil.getUUID());
                lscd.setTypeId("FIXED");
                lscd.setTypeName("固定资产");
                lscd.setMateriaFlow(assetsList.get(i));
                lscd.setUseNum(assetsNumList.get(i));
                GeneralMethod.setRecordInfo(lscd,true);
                count += lscdMapper.insertSelective(lscd);
            }
        }
        return count;
    }

    @Override
    public LcjnSkillCfg querySkillByFlow(String skillFlow) {
        return lscMapper.selectByPrimaryKey(skillFlow);
    }

    @Override
    public List<Map<String, Object>> queryConfigDetailByFlow(String skillFlow) {
        return baseManagerExtMapper.queryConfigDetailByFlow(skillFlow);
    }

    @Override
    public List<SysDict> searchStartDictList(SysDict dict) {
        return baseManagerExtMapper.searchStartDictList(dict);
    }

    @Override
    public String validateTeaTimeCourse(Map<String,Object> param) {
        String resultStr = "";
        LcjnCourseInfo course = (LcjnCourseInfo)param.get("course");
        List<String> trainStartDate = (List<String>)param.get("trainStartDate");
        List<String> startTime = (List<String>)param.get("startTime");
        List<String> trainEndDate = (List<String>)param.get("trainEndDate");
        List<String> endTime = (List<String>)param.get("endTime");
        List<String> userFlowList = (List<String>)param.get("userFlowList");
        for(int i=0;i<trainStartDate.size();i++){
            String trainStartTime = trainStartDate.get(i)+" "+startTime.get(i);
            String trainEndTime = trainEndDate.get(i)+" "+endTime.get(i);
            List<Map<String,String>> mapList = null;
            if(StringUtil.isBlank(course.getCourseFlow())) {//新增
                mapList = baseManagerExtMapper.queryTeaTimeCourseList(trainStartTime,trainEndTime,null);
            }else{
                mapList = baseManagerExtMapper.queryTeaTimeCourseList(trainStartTime,trainEndTime,course.getCourseFlow());
            }

            if(null != mapList && mapList.size() > 0){
                for(int j=0;j<mapList.size();j++){
                    Map<String,String> map = mapList.get(j);
                    if(userFlowList.contains(map.get("USER_FLOW"))){
                        resultStr += map.get("USER_NAME")+"在"+trainStartTime+"到"+trainEndTime+"期间已添加过课程："+map.get("COURSE_NAME")+"<br/>";
                    }
                }
            }
        }
        return StringUtil.isBlank(resultStr) ? com.pinde.core.common.GlobalConstant.FLAG_Y : resultStr;
    }
}
