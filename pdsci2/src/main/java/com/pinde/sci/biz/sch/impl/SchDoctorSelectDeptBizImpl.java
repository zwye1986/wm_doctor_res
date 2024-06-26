package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.*;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.ctrl.sch.plan.domain.Plan;
import com.pinde.sci.ctrl.sch.plan.domain.SchDeptPlanAssignment;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sch.SchOrgArrangeResultExtMapper;
import com.pinde.sci.dao.sch.SchRotationCfgExtMapper;
import com.pinde.sci.enums.sch.SchSelYearEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.form.sch.SchArrangeForm;
import com.pinde.sci.form.sch.SchDoctorForm;
import com.pinde.sci.form.sch.SchSelectDeptForm;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchDoctorSelectDeptBizImpl implements ISchDoctorSelectDeptBiz {

    @Autowired
    private SchRotationOrgDeptMapper orgDeptMapper;
    @Autowired
    private SchRotationOrgGroupMapper orgGroupMapper;
    @Autowired
    private SchDoctorSelectDeptMapper doctorDeptMapper;
    @Autowired
    private SchOrgArrangeResultMapper schResultMapper;
    @Autowired
    private SchOrgArrangeResultExtMapper schResultExtMapper;
    @Autowired
    private ResDoctorMapper doctorMapper;
    @Autowired
    private SchRotationCfgExtMapper rotationCfgExtMapper;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IDeptBiz sysDeptBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private ISchRotationBiz schRotationtBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private ISchRotationGroupBiz schRotationtGroupBiz;

    @Override
    public List<SchDoctorSelectDept> findSelectDepts(String doctorFlow) {
        if(StringUtil.isNotBlank(doctorFlow))
        {
            SchDoctorSelectDeptExample example = new SchDoctorSelectDeptExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
            example.setOrderByClause("ORDINAL");
            return doctorDeptMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<SchOrgArrangeResult> findSchResults(String doctorFlow) {
        if(StringUtil.isNotBlank(doctorFlow))
        {
            SchOrgArrangeResultExample example = new SchOrgArrangeResultExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
            example.setOrderByClause("SCH_START_DATE");
            return schResultMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<SchDoctorSelectDept> readSelectDepts(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow, String cycleType) {
        SchDoctorSelectDeptExample example = new SchDoctorSelectDeptExample();
        SchDoctorSelectDeptExample.Criteria criteria=  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
                .andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow).andDoctorFlowEqualTo(doctorFlow);
        if(StringUtil.isNotBlank(cycleType))
        {
            criteria.andSelectYearEqualTo(cycleType);
        }
        example.setOrderByClause("create_time");
        return doctorDeptMapper.selectByExample(example);
    }

    @Override
    public void saveSelDept(List<SchSelectDeptForm> selDepts, String orgFlow, String doctorFlow, String sessionNumber, String rotationFlow) throws Exception {
        ResDoctor doctor=doctorMapper.selectByPrimaryKey(doctorFlow);
        List<SchDoctorSelectDept> news= new ArrayList<>();
        List<SchDoctorSelectDept> olds= readSelectDepts(doctorFlow,sessionNumber, rotationFlow,orgFlow, "");
        Map<String,SchDoctorSelectDept> selectDeptMap=new HashMap<>();
        for(SchDoctorSelectDept dept:olds)
        {
            String key=dept.getGroupFlow()+dept.getStandardDeptId()+dept.getSchDeptFlow();
            if(StringUtil.isNotBlank(dept.getSelectYear()))
            {
                key+=dept.getSelectYear();
            }
            selectDeptMap.put(key,dept);
        }
        //删除以前选择的科室信息
        rotationCfgExtMapper.delDocSelInfo(doctorFlow,rotationFlow,sessionNumber,orgFlow);
        for(SchSelectDeptForm selectDeptForm:selDepts)
        {
            SchRotationOrgDept dept=orgDeptMapper.selectByPrimaryKey(selectDeptForm.getRecordFlow());
            if(dept!=null)
            {
                String key=dept.getGroupFlow()+dept.getStandardDeptId()+dept.getSchDeptFlow();
                if(StringUtil.isNotBlank(selectDeptForm.getSelectYear()))
                {
                    key+=selectDeptForm.getSelectYear();
                }
                SchDoctorSelectDept selectDept=selectDeptMap.get(key);
                if(selectDept==null)
                    selectDept=new SchDoctorSelectDept();
                BeanUtil.mergeNotSameClassObject(dept,selectDept);
                selectDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                selectDept.setSelectYear(selectDeptForm.getSelectYear());
                selectDept.setSchMonth(selectDeptForm.getSchMonth());
                selectDept.setDoctorFlow(doctorFlow);
                selectDept.setDoctorName(doctor.getDoctorName());
                news.add(selectDept);
            }
        }

        for(SchDoctorSelectDept selectDept:news) {
            selectDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            saveSelectDept(selectDept);
        }
        doctor =new ResDoctor();
        doctor.setDoctorFlow(doctorFlow);
        doctor.setSelAllFlag("Y");
        doctor.setSelOneFlag("Y");
        doctor.setSelTwoFlag("Y");
        doctor.setSelThreeFlag("Y");
        doctorMapper.updateByPrimaryKeySelective(doctor);
    }

    @Override
    public List<SchOrgArrangeResult> readArrangeResults(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow) {
        SchOrgArrangeResultExample example = new SchOrgArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRotationFlowEqualTo(rotationFlow)
                .andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow).andDoctorFlowEqualTo(doctorFlow);
        example.setOrderByClause("SCH_START_DATE");
        return schResultMapper.selectByExample(example);
    }

    @Override
    public int findSesssionNumberResults(String sessionNumber, String orgFlow) {
        SchOrgArrangeResultExample example = new SchOrgArrangeResultExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow);
        return schResultMapper.countByExample(example);
    }

    @Override
    public void delArrangeResult(SchArrangeForm form) {
        for(SchDoctorForm doctorForm:form.getDoctors())
        {
            ResDoctor doctor=doctorMapper.selectByPrimaryKey(doctorForm.getUserFlow());
            schResultExtMapper.delSchInfoByCycle(doctor.getDoctorFlow(),doctor.getOrgFlow(),doctor.getSessionNumber(),doctor.getRotationFlow(),form.getCycleYear());
            schResultExtMapper.updateDoctorSchFlag(doctor.getDoctorFlow(),form.getCycleYear());
            doctor=doctorMapper.selectByPrimaryKey(doctorForm.getUserFlow());
            if("N".equals(doctor.getSchOneFlag())&&
                    "N".equals(doctor.getSchTwoFlag())&&
                    "N".equals(doctor.getSchThreeFlag()))
            {
                doctor.setSchAllFlag("N");
            }
            doctorMapper.updateByPrimaryKeySelective(doctor);
        }
    }

    @Override
    public int findDeptMonthUseNum(String deptFlow, String startDate, String endDate) {
        return  schResultExtMapper.findDeptMonthUseNum(deptFlow, startDate, endDate);
    }

    @Override
    public Double getRotationCycleMonthNum(String rotationFlow, String selectYear, String cycleYear, String orgFlow, String sessionNumber) {

        return  schResultExtMapper.getRotationCycleMonthNum(rotationFlow, selectYear, cycleYear, orgFlow, sessionNumber);
    }

    @Override
    public void saveArrangeResult(Plan solvedCloudBalance, SchArrangeForm form) throws Exception {
        if(solvedCloudBalance!=null)
        {
            List<String> doctorflows=new ArrayList<>();
            List<String> flows=new ArrayList<>();
            Map<String,SchRotationOrgGroup> groupMap=new HashMap<>();
            Map<String,String> startDateMap=new HashMap<>();
            Map<String,String> endDateMap=new HashMap<>();
            for(SchDeptPlanAssignment assignment:solvedCloudBalance.getAssignmentList())
            {
                if(!doctorflows.contains(assignment.getDoctorFlow()))
                    doctorflows.add(assignment.getDoctorFlow());
                if(!flows.contains(assignment.getRecordFlow()))
                    flows.add(assignment.getRecordFlow());
                String startDate=startDateMap.get(assignment.getRecordFlow());
                String endDate=endDateMap.get(assignment.getRecordFlow());
                if(StringUtil.isBlank(startDate)||startDate.compareTo(assignment.getMonth().getStartDate())>=0)
                {
                    startDateMap.put(assignment.getRecordFlow(),assignment.getMonth().getStartDate());
                }
                if(StringUtil.isBlank(endDate)||endDate.compareTo(assignment.getMonth().getEndDate())<=0)
                {
                    endDateMap.put(assignment.getRecordFlow(),assignment.getMonth().getEndDate());
                }
            }
            for(String flow:flows)
            {
                SchDoctorSelectDept dept=readByFlow(flow);
                SchRotationOrgGroup group=groupMap.get(dept.getGroupFlow());
                if(group==null)
                 group=orgGroupMapper.selectByPrimaryKey(dept.getGroupFlow());
                groupMap.put(dept.getGroupFlow(),group);
                SchOrgArrangeResult result=new SchOrgArrangeResult();
                BeanUtil.mergeNotSameClassObject(dept,result);
                result.setSchStartDate(startDateMap.get(flow));
                result.setSchEndDate(endDateMap.get(flow));
                result.setStandardGroupFlow(group.getStandardGroupFlow());
                saveResult(result);
            }
            for(String doctorFlow:doctorflows) {
                ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
                if (SchSelYearEnum.One.getId().equals(form.getCycleYear())) {
                    doctor.setSchOneFlag("Y");
                }
                if (SchSelYearEnum.Two.getId().equals(form.getCycleYear())) {
                    doctor.setSchTwoFlag("Y");
                }
                if (SchSelYearEnum.Three.getId().equals(form.getCycleYear())) {
                    doctor.setSchThreeFlag("Y");
                }
                if("All".equals(form.getCycleYear()))
                {
                    doctor.setSchAllFlag("Y");
                    doctor.setSchOneFlag("Y");
                    doctor.setSchTwoFlag("Y");
                    doctor.setSchThreeFlag("Y");
                }
                if("Y".equals(doctor.getSchOneFlag())||
                        "Y".equals(doctor.getSchTwoFlag())||
                        "Y".equals(doctor.getSchThreeFlag()))
                {
                    doctor.setSchAllFlag("Y");
                }
                doctorMapper.updateByPrimaryKeySelective(doctor);
            }
        }
    }

    @Override
    public List<SchOrgArrangeResult> readOrgArrangeResult(Map<String, String> param) {

        return  schResultExtMapper.readOrgArrangeResult(param);
    }

    @Override
    public List<SchOrgArrangeResult> readArrangeResultsByMap(Map<String, String> param) {
        return  schResultExtMapper.readArrangeResultsByMap(param);
    }

    @Override
    public int findCycleResultCount(String doctorFlow, String cycleYear) {
        return  schResultExtMapper.findCycleResultCount(doctorFlow,cycleYear);
    }
    @Override
    public int findSchResultCount(String doctorFlow, String cycleYear) {
        return  schResultExtMapper.findSchResultCount(doctorFlow,cycleYear);
    }

    @Override
    public List<SchArrangeResult> getCycleResults(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow) {

        return  schResultExtMapper.getCycleResults(doctorFlow,sessionNumber,rotationFlow,orgFlow);
    }

    @Override
    public int saveRostering(String doctorFlow, String groupFlow, String standardDeptId, String standardDeptName, String schDeptFlow, String resultFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
            SchRotationGroup group = groupBiz.readSchRotationGroup(groupFlow);
            if (doctor != null) {
                if (StringUtil.isNotBlank(schDeptFlow)) {
                    SchDept schDept=schDeptBiz.readSchDept(schDeptFlow);
                    if(schDept!=null)
                    {
                        SchArrangeResult result = new SchArrangeResult();
                        result.setDoctorFlow(doctor.getDoctorFlow());
                        result.setDoctorName(doctor.getDoctorName());
                        result.setSessionNumber(doctor.getSessionNumber());
                        result.setSchYear(doctor.getSessionNumber());
                        result.setResultFlow(resultFlow);
                        result.setArrangeFlow(resultFlow);
                        result.setSchDeptFlow(schDept.getSchDeptFlow());
                        result.setSchDeptName(schDept.getSchDeptName());
                        result.setDeptFlow(schDept.getDeptFlow());
                        result.setDeptName(schDept.getDeptName());
                        result.setRotationFlow(group.getRotationFlow());
                        result.setOrgFlow(doctor.getOrgFlow());
                        result.setOrgName(doctor.getOrgName());
                        if(group.getRotationFlow().equals(doctor.getRotationFlow())){
                            result.setRotationName(doctor.getRotationName());
                        }else if(group.getRotationFlow().equals(doctor.getSecondRotationFlow())){
                            result.setRotationName(doctor.getSecondRotationName());
                        }
                        result.setStandardDeptId(standardDeptId);
                        result.setStandardDeptName(standardDeptName);
                        result.setStandardGroupFlow(group.getGroupFlow());

                        //不是方案配置下的轮转记录
                        result.setIsRotation(GlobalConstant.FLAG_N);
                        doctor.setIsSchFlag("Y");
                        doctorBiz.editDoctor(doctor);
                       return schArrangeResultBiz.saveSchArrangeResult(result);
                    }
                }
            }
        }

        return GlobalConstant.ONE_LINE;
    }

    @Override
    public ExcelUtile importArrange(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "姓名:userName",
                    "证件号:idNo",
                    "年级:sessionNumber",
                    "培训专业:trainingSpeName",
                    "培养年限:trainingYears",
                    "轮转组:groupName",
                    "轮转组代码:groupFlow",
                    "标准科室:standardDeptName",
                    "标准科室代码:standardDeptId",
                    "轮转科室:schDeptName",
                    "轮转科室代码:schDeptFlow",
                    "开始时间:schStartDate",
                    "结束时间:schEndDate",
                    "带教老师:teaName",
                    "科主任:headName"
            };

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            List<SchRotation> rotationList =schRotationtBiz.searchSchRotationByIsPublish();
            Map<String,SchRotation> rotationMap=new HashMap<>();
            Map<String,SchRotationGroup> groupMap=new HashMap<>();
            Map<String,SchRotationDept> deptMap=new HashMap<>();
            Map<String,SchDept> schDeptMap=new HashMap<>();
            Map<String,SysUser> userMap=new HashMap<>();
            Map<String,SysUser> teaMap=new HashMap<>();
            Map<String,List<SchDept>> teaDeptMap=new HashMap<>();
            Map<String,List<SysUserRole>> userRoleMap=new HashMap<>();
            Map<String,ResDoctor> doctorMap=new HashMap<>();
            Map<String,List<Map<String,String>>> doctorTimeMap=new HashMap<>();
            List<String> doctorFlows=new ArrayList<>();
            for(SchRotation rotation:rotationList)
            {
                rotationMap.put(rotation.getRotationFlow(),rotation);
                List<SchRotationGroup> groups=schRotationtGroupBiz.searchSchRotationGroup(rotation.getRotationFlow());
                if(groups!=null)
                {
                    for(SchRotationGroup group:groups)
                    {
                        groupMap.put(rotation.getRotationFlow()+group.getGroupFlow(),group);
                        List<SchRotationDept> depts=schRotationtGroupBiz.readSchRotationDept(group.getGroupFlow());
                        if(depts!=null)
                        {
                            for(SchRotationDept dept:depts)
                            {
                                deptMap.put(group.getGroupFlow()+dept.getStandardDeptId(),dept);
                            }
                        }
                    }

                }
            }
            String orgFlow = "";
            orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
            List<SysDept> sysDeptList =sysDeptBiz.searchDeptByOrg(orgFlow);
            if(sysDeptList!=null && sysDeptList.size()>0){
                List<String> deptFlows = new ArrayList<String>();
                for(SysDept dept : sysDeptList){
                    deptFlows.add(dept.getDeptFlow());
                }
                List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
                if(deptList!=null)
                {
                    for(SchDept dept:deptList)
                    {
                        schDeptMap.put(dept.getSchDeptFlow(),dept);
                    }
                }
            }
            String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
            String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");

            return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(String doctorFlow:doctorFlows)
                    {
                        ResDoctor resDoctor=doctorMap.get(doctorFlow);
                        schResultExtMapper.delCycleResultByYear(doctorFlow,resDoctor.getOrgFlow(),resDoctor.getSessionNumber(),resDoctor.getRotationFlow(),"");
                        schResultExtMapper.delCycleProcessByResult(doctorFlow,resDoctor.getOrgFlow(),resDoctor.getSessionNumber(),resDoctor.getRotationFlow(),"");
                        resDoctor.setIsSchFlag("Y");
                        doctorMapper.updateByPrimaryKeySelective(resDoctor);
                    }
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String,Object> data=datas.get(i);
                        String groupFlow= (String) data.get("groupFlow");
                        String standardDeptId= (String) data.get("standardDeptId");
                        String schDeptFlow= (String) data.get("schDeptFlow");
                        String schStartDate= (String) data.get("schStartDate");
                        String schEndDate= (String) data.get("schEndDate");
                        String idNo= (String) data.get("idNo");
                        String teaName= (String) data.get("teaName");
                        String headName= (String) data.get("headName");
                        SchDept schDept=schDeptMap.get(schDeptFlow);

                        SysUser sysUser1=userMap.get(idNo);
                        ResDoctor resDoctor=doctorMap.get(sysUser1.getUserFlow());
                        SchRotation rotation=rotationMap.get(resDoctor.getRotationFlow());
                        SchRotationGroup group=groupMap.get(rotation.getRotationFlow()+groupFlow);
                        SchRotationDept dept=deptMap.get(group.getGroupFlow()+standardDeptId);
                        SysUser tea=teaMap.get(teaName);
                        SysUser head=teaMap.get(headName);

                        SchArrangeResult result=new SchArrangeResult();

                        String resultFlow = PkUtil.getUUID();

                        result.setResultFlow(resultFlow);
                        result.setArrangeFlow(resultFlow);

                        result.setDoctorFlow(sysUser1.getUserFlow());
                        result.setDoctorName(sysUser1.getUserName());
                        result.setSessionNumber(resDoctor.getSessionNumber());
                        result.setOrgFlow(resDoctor.getOrgFlow());
                        result.setOrgName(resDoctor.getOrgName());

                        result.setDeptFlow(schDept.getDeptFlow());
                        result.setDeptName(schDept.getDeptName());
                        result.setSchDeptFlow(schDept.getSchDeptFlow());
                        result.setSchDeptName(schDept.getSchDeptName());

                        result.setRotationFlow(rotation.getRotationFlow());
                        result.setRotationName(rotation.getRotationName());
                        result.setStandardGroupFlow(groupFlow);
                        result.setStandardDeptId(dept.getStandardDeptId());
                        result.setStandardDeptName(dept.getStandardDeptName());

                        result.setSchStartDate(schStartDate);
                        result.setSchEndDate(schEndDate);//轮转计划单位
                        String unit = InitConfig.getSysCfg("res_rotation_unit");
                        //默认按月计算
                        int step = 30;
                        if(SchUnitEnum.Week.getId().equals(unit)){
                            //如果是周按7天算/没配置或者选择月按30天
                            step = 7;
                            BigDecimal realMonth = BigDecimal.valueOf(0);
                            long realDays = DateUtil.signDaysBetweenTowDate(result.getSchEndDate(),result.getSchStartDate())+1;
                            if(realDays!=0){
                                //计算实际轮转的月/周数
                                double realMonthF = (realDays/(step*1.0));
                                realMonth = BigDecimal.valueOf(realMonthF);
                                realMonth = realMonth.setScale(1,BigDecimal.ROUND_HALF_UP);
                            }
                            String schMonth= String.valueOf(realMonth.doubleValue());
                            result.setSchMonth(schMonth);
                        }else{
                            Map<String,String> map= new HashMap<>();
                            map.put("startDate",result.getSchStartDate());
                            map.put("endDate",result.getSchEndDate());

                            Double month = null;
                            try {
                                month = TimeUtil.getMonthsBetween(map);
                            } catch (ParseException e) {
                                month=0.0;
                            }
                            String schMonth = String.valueOf(Double.parseDouble(month + ""));
                            result.setSchMonth(schMonth);
                        }
                        if(tea!=null&&head!=null)
                        {
                            ResDoctorSchProcess process=new ResDoctorSchProcess();

                            process.setSchStartDate(result.getSchStartDate());
                            process.setSchEndDate(result.getSchEndDate());//轮转计划单位
                            process.setDeptFlow(result.getDeptFlow());
                            process.setDeptName(result.getDeptName());
                            process.setSchDeptFlow(result.getSchDeptFlow());
                            process.setSchDeptName(result.getSchDeptName());
                            process.setOrgFlow(result.getOrgFlow());
                            process.setOrgName(result.getOrgName());

                            process.setTeacherUserFlow(tea.getUserFlow());
                            process.setTeacherUserName(tea.getUserName());
                            process.setHeadUserFlow(head.getUserFlow());
                            process.setHeadUserName(head.getUserName());

                            process.setSchFlag(GlobalConstant.FLAG_N);
                            process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
                            process.setSchResultFlow(resultFlow);
                            process.setUserFlow(result.getDoctorFlow());
                            processBiz.edit(process);
                        }
                        count+=schArrangeResultBiz.save(result);
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(HashMap data,ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if(sheetName==null||!"ArrangeResult".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的排班导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(teacherRoleFlow))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "系统中未维护带教角色，请联系系统管理员！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(headRoleFlow))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "系统中未维护主任角色，请联系系统管理员！！");
                        return ExcelUtile.RETURN;
                    }
                    SysUser sysUser=new SysUser();
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        Map<String,Object> map=new HashMap<String, Object>();
                        String value=(String) data.get(key);
                        if("userName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行姓名为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            sysUser.setUserName(value);
                        }else  if("idNo".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]证件号为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            sysUser.setIdNo(value);
                        }else  if("sessionNumber".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]年级为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("trainingSpeName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]专业为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("trainingYears".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]培养年限为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("groupName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]轮转组为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("groupFlow".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]轮转组代码为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("standardDeptName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]标准科室为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("standardDeptId".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]标准科室代码为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("schDeptName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]轮转科室为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("schDeptFlow".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]轮转科室代码为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("schStartDate".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]开始时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            Date date = null;
                            try {
                                date = format.parse(value);
                                data.put("schStartDate",format.format(date));
                            } catch (ParseException e) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]开始时间格式不正确，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("schEndDate".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]结束时间为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            Date date = null;
                            try {
                                date = format.parse(value);
                                data.put("schEndDate",format.format(date));
                            } catch (ParseException e) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]结束时间格式不正确，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }
                    }
                   SysUser sysUser1=userMap.get(sysUser.getIdNo());
                    if(sysUser1==null)
                        sysUser1=userBiz.findByIdNo(sysUser.getIdNo());
                    if(null==sysUser1)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,身份证号所属学生不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    userMap.put(sysUser.getIdNo(),sysUser1);

                    ResDoctor resDoctor=doctorMap.get(sysUser1.getUserFlow());
                    if (resDoctor==null)
                        resDoctor=  doctorBiz.readDoctor(sysUser1.getUserFlow());
                    if(null==resDoctor)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生医师信息不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    doctorMap.put(sysUser1.getUserFlow(),resDoctor);

                    if(StringUtil.isBlank(resDoctor.getOrgFlow())){
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生暂未参加培训，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(!resDoctor.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生所在培训基地与当前登录人的基地不一致，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isBlank(resDoctor.getRotationFlow())){
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生暂未分配培训方案，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }

                    int count=findCycleResultCount(resDoctor.getDoctorFlow(),"");
                    if(count>0)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生已开始轮转，无法导入，请从模板中删除此学员！！");
                        return ExcelUtile.RETURN;
                    }
                    String groupFlow= (String) data.get("groupFlow");
                    String standardDeptId= (String) data.get("standardDeptId");
                    String schDeptFlow= (String) data.get("schDeptFlow");
                    String teaName= (String) data.get("teaName");
                    String headName= (String) data.get("headName");
                    String schStartDate= (String) data.get("schStartDate");
                    String schEndDate= (String) data.get("schEndDate");
                    if(schStartDate.compareTo(schEndDate)>0)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]开始时间不得大于结束时间！！");
                        return ExcelUtile.RETURN;
                    }

                    List<Map<String,String>> times= doctorTimeMap.get(sysUser1.getUserFlow());
                    if(times==null)
                        times=new ArrayList<Map<String, String>>();
                    doctorTimeMap.put(sysUser1.getUserFlow(),times);

                    Map<String,String> time=new HashMap<String, String>();
                    time.put("s",schStartDate);
                    time.put("e",schEndDate);
                    time.put("n",rowNum+1+"");
                    Map<String,String> sameTime=haveSameTime(times,time);
                    if(sameTime!=null)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]轮转时间与第"+sameTime.get("n")+"行轮转时间存在重复，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    times.add(time);
                    SchDept schDept=schDeptMap.get(schDeptFlow);
                    if(schDept==null)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]轮转科室系统中不存在，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(!schDept.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]轮转科室所在基地与当前登录人的基地不一致，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    SchRotation rotation=rotationMap.get(resDoctor.getRotationFlow());
                    String name=rotation.getRotationName()+"-"+rotation.getDoctorCategoryName()
                            +"-"+rotation.getSpeName()+rotation.getRotationFlow();
                    SchRotationGroup group=groupMap.get(rotation.getRotationFlow()+groupFlow);
                    if(group==null)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]轮转组与学员的培训方案不存在对应关系，请确认后提交！！学员培训方案为"+name);
                        return ExcelUtile.RETURN;
                    }
                    SchRotationDept dept=deptMap.get(group.getGroupFlow()+standardDeptId);
                    if(dept==null)
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]标准科室与学员的培训方案下的轮转组【"+group.getGroupName()+"】不存在对应关系，请确认后提交！！学员培训方案为"+name);
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isNotBlank(teaName)&&StringUtil.isBlank(headName)
                            ||StringUtil.isNotBlank(headName)&&StringUtil.isBlank(teaName))
                    {
                        eu.put("count",0);
                        eu.put("code","1");
                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教与主任必须为同时填写或不填写，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    if(StringUtil.isNotBlank(teaName))
                    {
                        SysUser tea=teaMap.get(teaName);
                        if(tea==null)
                            tea=userBiz.findByUserCode(teaName);
                        if(tea==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教帐号在系统中不存在，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        teaMap.put(teaName,tea);
                        List<SchDept> depts=teaDeptMap.get(tea.getUserFlow());
                        if(depts==null)
                            depts= schDeptBiz.userSchDept(tea.getUserFlow());
                        if(depts==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教帐号在系统中无科室信息，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        boolean isHaveDept=false;
                        for(SchDept dept1:depts)
                        {
                            if(dept1.getSchDeptFlow().equals(schDept.getSchDeptFlow()))
                            {
                                isHaveDept=true;
                                break;
                            }
                        }
                        if(!isHaveDept)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教帐号与轮转科室【"+schDept.getSchDeptName()+"】无对应关系，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        teaDeptMap.put(tea.getUserFlow(),depts);

                        List<SysUserRole> userRoles=userRoleMap.get(tea.getUserFlow());
                        if(userRoles==null)
                            userRoles= userRoleBiz.getByUserFlow(tea.getUserFlow());
                        if(userRoles==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教帐号在系统中未分配角色，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        boolean f=false;
                        for(SysUserRole userRole:userRoles)
                        {
                            if(teacherRoleFlow.equals(userRole.getRoleFlow()))
                            {
                                f=true;
                                break;
                            }
                        }
                        if(!f)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]带教帐号在系统中不存在带教角色，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        userRoleMap.put(tea.getUserFlow(),userRoles);
                    }
                    if(StringUtil.isNotBlank(headName))
                    {
                        SysUser tea=teaMap.get(headName);
                        if(tea==null)
                            tea=userBiz.findByUserCode(headName);
                        if(tea==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]主任帐号在系统中不存在，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        teaMap.put(headName,tea);

                        List<SchDept> depts=teaDeptMap.get(tea.getUserFlow());
                        if(depts==null)
                            depts= schDeptBiz.userSchDept(tea.getUserFlow());
                        if(depts==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]主任帐号在系统中无科室信息，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        boolean isHaveDept=false;
                        for(SchDept dept1:depts)
                        {
                            if(dept1.getSchDeptFlow().equals(schDept.getSchDeptFlow()))
                            {
                                isHaveDept=true;
                                break;
                            }
                        }
                        if(!isHaveDept)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]主任帐号与轮转科室【"+schDept.getSchDeptName()+"】无对应关系，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        teaDeptMap.put(tea.getUserFlow(),depts);

                        List<SysUserRole> userRoles=userRoleMap.get(tea.getUserFlow());
                        if(userRoles==null)
                            userRoles= userRoleBiz.getByUserFlow(tea.getUserFlow());
                        if(userRoles==null)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]主任帐号在系统中未分配角色，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        boolean f=false;
                        for(SysUserRole userRole:userRoles)
                        {
                            if(headRoleFlow.equals(userRole.getRoleFlow()))
                            {
                                f=true;
                                break;
                            }
                        }
                        if(!f)
                        {
                            eu.put("count",0);
                            eu.put("code","1");
                            eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]主任帐号在系统中不存在科主任角色，请确认后提交！！");
                            return ExcelUtile.RETURN;
                        }
                        userRoleMap.put(tea.getUserFlow(),userRoles);
                    }
                    if(!doctorFlows.contains(sysUser1.getUserFlow()))
                    {
                        doctorFlows.add(sysUser1.getUserFlow());
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
    public ExcelUtile importArrange2(MultipartFile file, String startDate, String endDate) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));


            List<String> times= TimeUtil.getMonthsByTwoMonth(startDate,endDate);
            if(times==null)
            {
                ExcelUtile eu=new ExcelUtile();
                eu.put("count", 0);
                eu.put("code", "1");
                return eu;
            }
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys=new String[times.size()+5];
            keys[0]="姓名:userName";
            keys[1]="证件号:idNo";
            keys[2]="年级:sessionNumber";
            keys[3]="培训专业:trainingSpeName";
            keys[4]="培养年限:trainingYears";
            int j=5;
            for(String time:times)
            {
                keys[j++]=time+":month"+(j-1);
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            Map<String,SchDept> schDeptMap=new HashMap<>();
            Map<String,SysUser> userMap=new HashMap<>();
            Map<String,ResDoctor> doctorMap=new HashMap<>();
            Map<String,List<Map<String,String>>> doctorTimeMap=new HashMap<>();
            List<String> doctorFlows=new ArrayList<>();

            String orgFlow = "";
            orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
            List<SysDept> sysDeptList =sysDeptBiz.searchDeptByOrg(orgFlow);
            if(sysDeptList!=null && sysDeptList.size()>0){
                List<String> deptFlows = new ArrayList<String>();
                for(SysDept dept : sysDeptList){
                    deptFlows.add(dept.getDeptFlow());
                }
                List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
                if(deptList!=null)
                {
                    for(SchDept dept:deptList)
                    {
                        schDeptMap.put(dept.getSchDeptName(),dept);
                    }
                }
            }

            return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String,Object> data=datas.get(i);
                        String idNo= (String) data.get("idNo");
                        SysUser sysUser1=userMap.get(idNo);
                        ResDoctor resDoctor=doctorMap.get(sysUser1.getUserFlow());


                        List<Map<String,String>> schDeptDates= (List<Map<String, String>>) data.get("schDeptDates");
                        if(schDeptDates!=null)
                        {
                            resDoctor.setIsSchFlag("Y");
                            doctorMapper.updateByPrimaryKeySelective(resDoctor);
                            for(Map<String,String> schDeptDate:schDeptDates)
                            {
                                String deptName=schDeptDate.get("deptName");
                                String resultFlow=schDeptDate.get("resultFlow");
                                SchDept schDept=schDeptMap.get(deptName);
                                SchRotationDept schRotationDept=null;
                                //获取关联的标准科室
                                List<SchRotationDept> depts=readRotationDeptBySchDept(schDept.getSchDeptFlow(),
                                        resDoctor.getRotationFlow(),resDoctor.getSecondRotationFlow());
                                if(depts!=null&&!depts.isEmpty()&&depts.size()==1)
                                {
                                    schRotationDept=depts.get(0);
                                }
                                SchArrangeResult result=new SchArrangeResult();
                                if(StringUtil.isNotBlank(resultFlow)) {
                                    result.setResultFlow(resultFlow);
                                    result.setArrangeFlow(resultFlow);
                                }
                                result.setDoctorFlow(sysUser1.getUserFlow());
                                result.setDoctorName(sysUser1.getUserName());
                                result.setSessionNumber(resDoctor.getSessionNumber());
                                result.setSchYear(resDoctor.getSessionNumber());
                                result.setOrgFlow(resDoctor.getOrgFlow());
                                result.setOrgName(resDoctor.getOrgName());

                                result.setDeptFlow(schDept.getDeptFlow());
                                result.setDeptName(schDept.getDeptName());
                                result.setSchDeptFlow(schDept.getSchDeptFlow());
                                result.setSchDeptName(schDept.getSchDeptName());
                                result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);

                                if(schRotationDept!=null)
                                {
                                    result.setRotationFlow(schRotationDept.getRotationFlow());
                                    if(schRotationDept.getRotationFlow().equals(resDoctor.getRotationFlow()))
                                    {
                                        result.setRotationName(resDoctor.getRotationName());
                                    }
                                    if(schRotationDept.getRotationFlow().equals(resDoctor.getSecondRotationFlow()))
                                    {
                                        result.setRotationName(resDoctor.getSecondRotationName());
                                    }
                                    result.setStandardGroupFlow(schRotationDept.getGroupFlow());
                                    result.setStandardDeptId(schRotationDept.getStandardDeptId());
                                    result.setStandardDeptName(schRotationDept.getStandardDeptName());
                                }else{
                                    result.setRotationFlow("");
                                    result.setRotationName("");
                                    result.setStandardGroupFlow("");
                                    result.setStandardDeptId("");
                                    result.setStandardDeptName("");
                                }

                                result.setSchStartDate(schDeptDate.get("startDate"));
                                result.setSchEndDate(schDeptDate.get("endDate"));

                                Map<String,String> map= new HashMap<>();
                                map.put("startDate",result.getSchStartDate());
                                map.put("endDate",result.getSchEndDate());
                                Double month = null;
                                try {
                                    month = TimeUtil.getMonthsBetween(map);
                                } catch (ParseException e) {
                                    month=0.0;
                                }
                                String schMonth = String.valueOf(Double.parseDouble(month + ""));
                                result.setSchMonth(schMonth);
                                if(StringUtil.isNotBlank(result.getResultFlow()))
                                {
                                    processBiz.delProcessByResultFlow(result.getResultFlow());
                                    count += schArrangeResultBiz.editSchArrangeResult(result);
                                }else {

                                    resultFlow = PkUtil.getUUID();
                                    result.setResultFlow(resultFlow);
                                    result.setArrangeFlow(resultFlow);
                                    count += schArrangeResultBiz.save(result);
                                }
                            }
                        }

                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }


                @Override
                public String checkExcelData(HashMap data,ExcelUtile eu) {
                    String sheetName=(String)eu.get("SheetName");
                    if(sheetName==null||!"ArrangeResult".equals(sheetName))
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的排班导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    List<String> colnames = (List<String>) eu.get("colnames");
                    if(colnames==null||colnames.isEmpty())
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "请使用系统提供的排班导入模板！！");
                        return ExcelUtile.RETURN;
                    }
                    for(int i=0;i<times.size();i++)
                    {
                        if(!colnames.get(i+5).equals(times.get(i)))
                        {
                            eu.put("count", 0);
                            eu.put("code", "1");
                            eu.put("msg", "请使用系统提供的排班导入模板！！");
                            return ExcelUtile.RETURN;
                        }
                    }
                    SysUser sysUser=new SysUser();
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    Map<String,List<String>> deptTimesMap=new HashMap<String, List<String>>();
                    ResDoctor resDoctor=null;
                    SysUser sysUser1=null;
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        String value=(String) data.get(key);
                        if("userName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行姓名为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            sysUser.setUserName(value);
                        }else  if("idNo".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]证件号为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            sysUser.setIdNo(value);
                            sysUser1 =userBiz.findByIdNo(sysUser.getIdNo());
                            if(null==sysUser1)
                            {
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生不存在，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            userMap.put(sysUser.getIdNo(),sysUser1);

                            resDoctor =  doctorBiz.readDoctor(sysUser1.getUserFlow());
                            if(null==resDoctor)
                            {
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生医师信息不存在，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            doctorMap.put(sysUser1.getUserFlow(),resDoctor);

                            if(StringUtil.isBlank(resDoctor.getOrgFlow())){
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生暂未参加培训，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            if(!resDoctor.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
                            {
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生所在培训基地与当前登录人的基地不一致，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            if(StringUtil.isBlank(resDoctor.getRotationFlow())){
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]身份证号所属学生暂未分配培训方案，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                            if(!doctorFlows.contains(sysUser1.getUserFlow()))
                            {
                                doctorFlows.add(sysUser1.getUserFlow());
                            }else{
                                eu.put("count",0);
                                eu.put("code","1");
                                eu.put("msg","导入文件第"+(rowNum+1)+"行,学员["+sysUser.getUserName()+"]学员排班信息在导入模板中存在重复，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("sessionNumber".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]年级为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else  if("trainingSpeName".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]专业为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }else if("trainingYears".equals(key))
                        {
                            if(StringUtil.isBlank(value)) {
                                eu.put("count", 0);
                                eu.put("code", "1");
                                eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]培养年限为空，请确认后提交！！");
                                return ExcelUtile.RETURN;
                            }
                        }
                        int j=5;
                        for(String t:times)
                        {
                            if(("month"+j++).equals(key))
                            {
                                if(StringUtil.isBlank(value)) {
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行"+t+"为空，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }else{
                                    //判断科室存在不
                                    SchDept schDept=schDeptMap.get(value);
                                    if(schDept==null)
                                    {
                                        eu.put("count", 0);
                                        eu.put("code", "1");
                                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行【"+value+"】为科室在系统中不存在，请确认后提交！！");
                                        return ExcelUtile.RETURN;
                                    }
                                    if(!schDept.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
                                    {
                                        eu.put("count",0);
                                        eu.put("code","1");
                                        eu.put("msg","导入文件第"+(rowNum+1)+"行学员["+sysUser.getUserName()+"]轮转科室所在基地与当前登录人的基地不一致，请确认后提交！！");
                                        return ExcelUtile.RETURN;
                                    }
                                    List<String> doctorTimes=deptTimesMap.get(value);
                                    if(doctorTimes==null) doctorTimes=new ArrayList<String>();
                                    doctorTimes.add(t);
                                    deptTimesMap.put(value,doctorTimes);
                                }
                            }
                        }
                    }
                    //map 为 科室 开始时间，结束时间
                    List<Map<String,String>> schDeptDates=findSchDeptDates(deptTimesMap);
                    if(schDeptDates==null)
                    {
                        eu.put("count", 0);
                        eu.put("code", "1");
                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]学员排班信息为空，请确认后提交！！");
                        return ExcelUtile.RETURN;
                    }
                    for(Map<String,String> d:schDeptDates)
                    {
                        d.put("doctorFlow",resDoctor.getDoctorFlow());
                        //与时间段有交集的轮转记录，如果导入时间段不轮转记录的时间不一样不允许导入
                        List<SchArrangeResult> results=readArrangeResultsByTimeMap(d);
                        if(results!=null)
                        {
                            for(SchArrangeResult result:results)
                            {
                                if(result.getSchStartDate().equals(d.get("startDate"))&&
                                        result.getSchEndDate().equals(d.get("endDate")) )
                                {
                                    int c=findRecCount(result.getResultFlow());
                                    if(c>0)
                                    {
                                        eu.put("count", 0);
                                        eu.put("code", "1");
                                        eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]在轮转时间["+d.get("startDate")+"~"+d.get("endDate")+
                                                "]在系统中已填写轮转数据，无法导入，请确认后提交！！");
                                        return ExcelUtile.RETURN;
                                    }
                                    d.put("resultFlow",result.getResultFlow());

                                }else{
                                    eu.put("count", 0);
                                    eu.put("code", "1");
                                    eu.put("msg", "导入文件第" + (rowNum + 1) + "行学员["+sysUser.getUserName()+"]在轮转时间["+d.get("startDate")+"~"+d.get("endDate")+"]在系统中已有轮转记录。" +
                                            "轮转记录为：["+result.getSchStartDate()+"~"+result.getSchEndDate()+"]"+result.getSchDeptName()+"，请确认后提交！！");
                                    return ExcelUtile.RETURN;
                                }
                            }
                        }
                    }
                    data.put("schDeptDates",schDeptDates);
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
    public void saveArrangeTime(List<SchOrgArrangeResult> results) {
        if(results!=null)
        {
            for(SchOrgArrangeResult result:results)
            {
                if(StringUtil.isNotBlank(result.getArrangeFlow()))
                {
                    GeneralMethod.setRecordInfo(result, false);
                    schResultMapper.updateByPrimaryKeySelective(result);
                }
            }
        }
    }

    private List<SchRotationDept> readRotationDeptBySchDept(String schDeptFlow, String rotationFlow, String secondRotationFlow) {

        return schResultExtMapper.readRotationDeptBySchDept(schDeptFlow,rotationFlow,secondRotationFlow);
    }

    private int findRecCount(String resultFlow) {
        return schResultExtMapper.findRecCount(resultFlow);
    }

    private List<SchArrangeResult> readArrangeResultsByTimeMap(Map<String, String> d) {
        return schResultExtMapper.readArrangeResultsByTimeMap(d);
    }

    private List<Map<String, String>> findSchDeptDates(Map<String, List<String>> deptTimesMap) {
        List<Map<String, String>> maps=null;
        if(deptTimesMap!=null&&!deptTimesMap.isEmpty())
        {
            maps=new ArrayList<>();
            for(Map.Entry<String, List<String>> entry : deptTimesMap.entrySet())
            {
                List<String> times=entry.getValue();
                String deptName=entry.getKey();
                List<Map<String,String>> allTimes=new ArrayList<>();
                for(String time:times)
                {
                    Map<String,String> t=new HashMap<>();
                    t.put("startDate",DateUtil.setFirstDayOfMonth(time));
                    t.put("endDate",DateUtil.setLastDateOfMonth(time));
                    allTimes.add(t);
                }
                //连续的时间段组合成一个时间段
                List<Map<String,String>> newTimes= TimeUtil.getNewTimes(allTimes);
                if(newTimes!=null)
                {
                    for(Map<String,String> t:newTimes)
                    {
                        t.put("deptName",deptName);
                        maps.add(t);
                    }
                }
            }
        }

        return maps;
    }

    @Override
    public List<Map<String, String>> readOrgArrangeStartDate(String doctorFlow, String sessionNumber, String rotationFlow, String orgFlow) {
        return schResultExtMapper.readOrgArrangeStartDate(doctorFlow,sessionNumber,rotationFlow,orgFlow);
    }

    private Map<String, String> haveSameTime(List<Map<String, String>> times, Map<String, String> time) {
        if(times!=null&&times.size()>0)
        {
            for(Map<String, String> baseTime:times)
            {
                if(isOverlap(baseTime.get("s"),baseTime.get("e"),time.get("s"),time.get("e")))
                {
                    return baseTime;
                }
            }
        }
        return null;
    }
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static boolean isOverlap(String startdate1, String enddate1,
                                     String startdate2, String enddate2) {
        Date leftStartDate = null;
        Date leftEndDate = null;
        Date rightStartDate = null;
        Date rightEndDate = null;
        try {
            leftStartDate = format.parse(startdate1);
            leftEndDate = format.parse(enddate1);
            rightStartDate = format.parse(startdate2);
            rightEndDate = format.parse(enddate2);
        } catch (ParseException e) {
            return false;
        }
        boolean flag = false;// 默认无交集
        long l1_1 = leftStartDate.getTime();
        long l1_2 = leftEndDate.getTime();
        long l2_1 = rightStartDate.getTime();
        long l2_2 = rightEndDate.getTime();

        if (((l1_1 <= l2_1) && (l2_1 <= l1_2)) || ((l1_1 <= l2_2) && (l2_2 <= l1_2))
                || ((l2_1 <= l1_1) && (l1_1 <= l2_2)) || ((l2_1 <= l1_2) && (l1_2 <= l2_2))) {
            flag = true;
        }
        return flag;

    }
    /**
     * 判断两个时间区间是否有交集的方法
     *
     * @param date1_1
     *            区间1的时间始
     * @param date1_2
     *            区间1的时间止
     * @param date2_1
     *            区间2的时间始
     * @param date2_2
     *            区间2的时间止
     * @return 区间1和区间2如果存在交集,则返回true,否则返回falses
     */
    public static boolean isDateCross(Date date1_1, Date date1_2, Date date2_1, Date date2_2) {
        boolean flag = false;// 默认无交集
        long l1_1 = date1_1.getTime();
        long l1_2 = date1_2.getTime();
        long l2_1 = date2_1.getTime();
        long l2_2 = date2_2.getTime();

        if (((l1_1 <= l2_1) && (l2_1 <= l1_2)) || ((l1_1 <= l2_2) && (l2_2 <= l1_2))
                || ((l2_1 <= l1_1) && (l1_1 <= l2_2)) || ((l2_1 <= l1_2) && (l1_2 <= l2_2))) {
            flag = true;
        }
        return flag;
    }


    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr1_1 = "2017-10-01";
        String dateStr1_2 = "2017-10-03";
        String dateStr2_1 = "2017-10-03";
        String dateStr2_2 = "2017-10-04";
        Date date1_1 = sdf.parse(dateStr1_1);
        Date date1_2 = sdf.parse(dateStr1_2);
        Date date2_1 = sdf.parse(dateStr2_1);
        Date date2_2 = sdf.parse(dateStr2_2);
        boolean b = isDateCross(date1_1, date1_2, date2_1, date2_2);
        System.out.println(b == true ? "有交集" : "无交集");
    }

    @Override
    public void syncArrange(SchArrangeForm form) {
        for(SchDoctorForm d:form.getDoctors())
        {
            schResultExtMapper.delCycleResultByYear(d.getUserFlow(),form.getOrgFlow(),form.getSessionNumber(),form.getRotationFlow(),form.getCycleYear());
            schResultExtMapper.delCycleProcessByResult(d.getUserFlow(),form.getOrgFlow(),form.getSessionNumber(),form.getRotationFlow(),form.getCycleYear());
            schResultExtMapper.AddCycleResultByYear(d.getUserFlow(),form.getOrgFlow(),form.getSessionNumber(),form.getRotationFlow(),form.getCycleYear());
            schResultExtMapper.updateDoctorIsSch(d.getUserFlow());
        }
    }

    private int saveResult(SchOrgArrangeResult d) {

        if(StringUtil.isBlank(d.getArrangeFlow()))
        {
            d.setArrangeFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(d, true);
            return schResultMapper.insertSelective(d);
        }else{
            GeneralMethod.setRecordInfo(d, false);
            return schResultMapper.updateByPrimaryKeySelective(d);
        }
    }

    private SchDoctorSelectDept readByFlow(String flow) {
        return doctorDeptMapper.selectByPrimaryKey(flow);
    }

    private int saveSelectDept(SchDoctorSelectDept d) {
        if(StringUtil.isBlank(d.getSelectFlow()))
        {
            d.setSelectFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(d, true);
            return doctorDeptMapper.insertSelective(d);
        }else{
            GeneralMethod.setRecordInfo(d, false);
            return doctorDeptMapper.updateByPrimaryKeySelective(d);
        }
    }
}
