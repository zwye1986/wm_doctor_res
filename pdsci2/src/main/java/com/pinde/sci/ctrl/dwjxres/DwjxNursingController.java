package com.pinde.sci.ctrl.dwjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.dwjxres.ExportStuUserInfo;
import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.WorkResumeForm;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.StuUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pinde.core.util.StringUtil.ClobToString;

@RequestMapping("/dwjxres/nursing")
@Controller
public class DwjxNursingController extends GeneralController {
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IDocSingupBiz docSingupBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserBiz;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;

    @RequestMapping("/home")
    public String home(Integer currentPage, Model model, HttpServletRequest request) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, getPageSize(request));
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("messages", infos);
        Map<String, Object> mp = new HashMap<String, Object>();
        String batchFlow = "";//当前系统默认 进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                    batchFlow = obj.getBatchFlow();
                    break;
                }
            }
        }
        mp.put("batchFlow", batchFlow);//系统默认批次
        mp.put("statusId", StuStatusEnum.Passing.getId());
        mp.put("nursingStatusId",StuStatusEnum.Passing.getId());
        List<StuUserExt> dsh = stuUserBiz.searchStuNurseUser(mp);
        int passingCount = dsh == null ? 0 : dsh.size();//待审核数

        mp.put("statusId", StuStatusEnum.Passed.getId());
        mp.put("nursingStatusId",StuStatusEnum.Passed.getId());
        List<StuUserExt> shtg = stuUserBiz.searchStuNurseUser(mp);
        int passedCount = shtg == null ? 0 : shtg.size();//审核通过数

        mp.put("statusId", StuStatusEnum.UnPassed.getId());
        mp.put("nursingStatusId",StuStatusEnum.UnPassed.getId());
        List<StuUserExt> shbtg = stuUserBiz.searchStuNurseUser(mp);
        int uppassedCount = shbtg == null ? 0 : shbtg.size();//审核不通过数

        mp.put("statusId", StuStatusEnum.Recruitted.getId());
        mp.put("nursingStatusId",StuStatusEnum.Recruitted.getId());
        List<StuUserExt> lq2 = stuUserBiz.searchStuNurseUser(mp);
        int recruittedCount = lq2 == null ? 0 : lq2.size();//已录取数

        mp.put("statusId", StuStatusEnum.UnRecruitted.getId());
        mp.put("nursingStatusId",StuStatusEnum.UnRecruitted.getId());
        List<StuUserExt> wlq2 = stuUserBiz.searchStuNurseUser(mp);
        int unrecruittedCount = wlq2 == null ? 0 : wlq2.size();//未录取数

        mp.put("statusId", StuStatusEnum.Admited.getId());
        mp.put("nursingStatusId",StuStatusEnum.Admited.getId());
        List<StuUserExt> lq = stuUserBiz.searchStuNurseUser(mp);
        int admitedCount = lq == null ? 0 : lq.size();//报到数

        mp.put("statusId", StuStatusEnum.UnAdmitd.getId());
        mp.put("nursingStatusId", StuStatusEnum.UnAdmitd.getId());
        List<StuUserExt> wlq = stuUserBiz.searchStuNurseUser(mp);
        int unadmitedCount = wlq == null ? 0 : wlq.size();//未报到数

        mp.put("statusId", StuStatusEnum.Graduation.getId());
        mp.put("nursingStatusId", StuStatusEnum.Graduation.getId());
        List<StuUserExt> jy = stuUserBiz.searchStuNurseUser(mp);
        int graduationCount = jy == null ? 0 : jy.size();//结业数

        mp.put("statusId", StuStatusEnum.DelayGraduation.getId());
        mp.put("nursingStatusId", StuStatusEnum.DelayGraduation.getId());
        List<StuUserExt> yjy = stuUserBiz.searchStuNurseUser(mp);
        int delayGraduationCount = yjy == null ? 0 : yjy.size();//延期结业数
        Map<String,Object> parMp = new HashMap<>();
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Passing.getId());
        status.add(StuStatusEnum.Passed.getId());
        status.add(StuStatusEnum.UnPassed.getId());
        status.add(StuStatusEnum.Recruitted.getId());
        status.add(StuStatusEnum.UnRecruitted.getId());
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.UnAdmitd.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        List<String> status2 = new ArrayList<>();
        status2.add(StuStatusEnum.Passing.getId());
        status2.add(StuStatusEnum.Passed.getId());
        status2.add(StuStatusEnum.UnPassed.getId());
        status2.add(StuStatusEnum.Recruitted.getId());
        status2.add(StuStatusEnum.UnRecruitted.getId());
        status2.add(StuStatusEnum.Admited.getId());
        status2.add(StuStatusEnum.UnAdmitd.getId());
        status2.add(StuStatusEnum.Graduation.getId());
        status2.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status2", status2);//状态
        List<StuUserExt> list = stuUserBiz.searchStuNurseUser(parMp);
        int singupCount = list == null ? 0 : list.size();//报名数

        model.addAttribute("passingCount", passingCount);
        model.addAttribute("singupCount",singupCount);
//        model.addAttribute("singupCount", passingCount + passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + uppassedCount + graduationCount + delayGraduationCount);//报名数
        model.addAttribute("passedCount", passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + graduationCount + delayGraduationCount);//审核通过数
        return "dwjxres/hospital/nursing/nurseIndex";
    }

    /**
     * 报名审核
     */
    @RequestMapping(value = "/audit")
    public String audit(String nursingStatusId, Integer currentPage, String userName, String batchFlow, String speId, String isPublish, Model model, String isQuery) {

        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        if (StringUtil.isBlank(isQuery) && StringUtil.isBlank(batchFlow)) {
            if (null != batchLst && batchLst.size() > 0) {
                for (StuBatch obj : batchLst) {
                    if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                        batchFlow = obj.getBatchFlow();//当前系统默认 进修批次流水号
                        break;
                    }
                }
            }
        }
        model.addAttribute("batchFlow", batchFlow);
        PageHelper.startPage(currentPage, 10);
        Map<String, Object> parMp = new HashMap<String, Object>();
        if (StringUtil.isBlank(nursingStatusId)) {
            nursingStatusId = StuStatusEnum.Passing.getId();
        }
        if (StuStatusEnum.Passing.getId().equals(nursingStatusId)) {
            parMp.put("statusId", StuStatusEnum.Passing.getId());
        }
        if (StuStatusEnum.Passed.getId().equals(nursingStatusId)) {
            List<String> status = new ArrayList<>();
            status.add(StuStatusEnum.Passed.getId());
            status.add(StuStatusEnum.Recruitted.getId());
            status.add(StuStatusEnum.UnRecruitted.getId());
            status.add(StuStatusEnum.Admited.getId());
            status.add(StuStatusEnum.UnAdmitd.getId());
            status.add(StuStatusEnum.Graduation.getId());
            status.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", status);//状态
            List<String> status2 = new ArrayList<>();
            status2.add(StuStatusEnum.Passed.getId());
            status2.add(StuStatusEnum.Recruitted.getId());
            status2.add(StuStatusEnum.UnRecruitted.getId());
            status2.add(StuStatusEnum.Admited.getId());
            status2.add(StuStatusEnum.UnAdmitd.getId());
            status2.add(StuStatusEnum.Graduation.getId());
            status2.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status2", status2);//状态
        }else if(!"Back".equals(nursingStatusId)){
            parMp.put("nursingStatusId", nursingStatusId);//状态
        }
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        parMp.put("isPublish", isPublish);//发布状态
        if (StuStatusEnum.UnPassed.getId().equals(nursingStatusId)) {
            parMp.put("isBack", GlobalConstant.FLAG_N);//未被退回的
            parMp.put("statusId", StuStatusEnum.UnPassed.getId());
        }else if("Back".equals(nursingStatusId)){//退回标识
            parMp.put("isBack", GlobalConstant.FLAG_Y);
            parMp.put("statusId", StuStatusEnum.UnPassed.getId());
            parMp.put("nursingStatusId", StuStatusEnum.UnPassed.getId());
        }
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuNurseUser(parMp);
        model.addAttribute("nursingStatusId", nursingStatusId);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        return "dwjxres/hospital/nursing/nurseAudit";
    }

    /**
     * 学员信息
     */
    @RequestMapping("/getsingupinfoaudit")
    public String getSingUpInfoForAudit(String userFlow, String batchId, String flag, Model model) {
        SysUser user = this.userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(userFlow, batchId);
        if (null != doctorLst && doctorLst.size() > 0) {
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师
            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(doctorLst.get(0).getResumeInfo());
            model.addAttribute("extInfo", extInfo);
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        return "dwjxres/hospital/nursing/singupinfoforaudit";
    }

    /**
     * 护士信息审核
     */
    @RequestMapping(value = "/auditNurseOption")
    @ResponseBody
    public String auditNurseOption(String resumeFlow, String userFlow, String reason, String nursingStatusId, Model model) {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("resumeFlow", resumeFlow);
        mp.put("userFlow", userFlow);
        mp.put("reason", reason);
        mp.put("nursingStatusId", nursingStatusId);
        if(StuStatusEnum.Passed.getId().equals(nursingStatusId)) {
            mp.put("statusId", StuStatusEnum.Passing.getId());
        }else{
            mp.put("statusId",nursingStatusId);
        }
        this.docSingupBiz.auditNurseOption(mp);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 退回操作
     */
    @RequestMapping(value = "/returnInfo")
    @ResponseBody
    public String returnInfo(String resumeFlow, String reason, String userFlow) {
        int result = docSingupBiz.returnNurseInfo(resumeFlow, reason, userFlow);
        if (result == 1) {
            return GlobalConstant.OPERATE_SUCCESSED;
        } else {
            return GlobalConstant.OPERATE_FAIL;
        }
    }

    /**
     * 导出审核通过数据
     */
    @RequestMapping("/exportAuditPassed")
    public void exportAuditPassed(String userName, String batchFlow, String speId, String isPublish, HttpServletResponse res) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Passed.getId());
        status.add(StuStatusEnum.Recruitted.getId());
        status.add(StuStatusEnum.UnRecruitted.getId());
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.UnAdmitd.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        List<String> status2 = new ArrayList<>();
        status2.add(StuStatusEnum.Passed.getId());
        status2.add(StuStatusEnum.Recruitted.getId());
        status2.add(StuStatusEnum.UnRecruitted.getId());
        status2.add(StuStatusEnum.Admited.getId());
        status2.add(StuStatusEnum.UnAdmitd.getId());
        status2.add(StuStatusEnum.Graduation.getId());
        status2.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status2", status2);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//用户名
        parMp.put("isPublish", isPublish);//用户名
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuNurseUser(parMp);
        String fileName = "审核通过学员名单.xls";
        createExcle(res, fileName, stuUserLst, "audit");
    }
    private void createExcle(HttpServletResponse response, String fileName, List<StuUserExt> stuUserLst, String opertype) throws Exception {
        List<ExportStuUserInfo> dataList = new ArrayList<ExportStuUserInfo>();
        ExportStuUserInfo eui = null;
        for (StuUserExt ext : stuUserLst) {
            eui = new ExportStuUserInfo();
            eui.setUserName(ext.getSysUser().getUserName());
            eui.setIdNo(ext.getSysUser().getIdNo());
            eui.setSchoolSpeName(ext.getSchoolSpeName());
            eui.setStuTimeName(ext.getStuTimeName());
            eui.setSpeName(ext.getSpeName());
//            eui.setBatchRegDate(ext.getStuBatch().getBatchRegDate());
//            eui.setTeacherName(ext.getTeacherName());
//            eui.setIsGraduation(GlobalConstant.FLAG_Y.equals(ext.getIsGraduation()) ? "是" : "否");
            dataList.add(eui);
        }
        String[] titles = null;
        if ("audit".equals(opertype)) {
            titles = new String[]{
                    "userName:姓名",
                    "idNo:身份证号",
                    "schoolSpeName:毕业专业",
                    "stuTimeName:进修时间",
                    "speName:进修专业",
//                    "batchRegDate:报到时间"
            };
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcle(titles, dataList, ExportStuUserInfo.class, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     *护士学员查询
     */
    @RequestMapping("/queryNurseMain")
    public String queryNurseMain(Model model) {
        String batchFlow = "";//当前系统默认 进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                    batchFlow = obj.getBatchFlow();
                    break;
                }
            }
        }
        model.addAttribute("batchFlow", batchFlow);
        return "dwjxres/hospital/nursing/queryStuMain";
    }

    @RequestMapping("/queryNurseList")
    public String queryNurseList(String sexId, String startAge, String endAge, String titleId, String certifiedTypeId, String speId,
                               String stuTimeId, String isAudit, String maxEduId, String hospitalLevelId, Integer currentPage,
                               String userName, String batchFlow, Model model, String isQuery) {
        if (StringUtil.isBlank(batchFlow) && StringUtil.isBlank(isQuery)) {
            List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
            if (null != batchLst && batchLst.size() > 0) {
                for (StuBatch obj : batchLst) {
                    if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                        batchFlow = obj.getBatchFlow();//当前系统默认 进修批次流水号
                        break;
                    }
                }
            }
        }

        model.addAttribute("batchFlow", batchFlow);
        PageHelper.startPage(currentPage, 10);
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("sexId", sexId);//批次
        parMp.put("startAge", startAge);//年龄
        parMp.put("endAge", endAge);//年龄
        parMp.put("titleTypeName","护士");
        parMp.put("titleId", titleId);//职称
        parMp.put("certifiedTypeId", certifiedTypeId);//执业类别
        parMp.put("stuTimeId", stuTimeId);//进修时间
        parMp.put("isAudit", isAudit);//审核情况
        if(StringUtil.isBlank(isAudit)){
            List<String> status = new ArrayList<>();
            status.add(StuStatusEnum.Passed.getId());
            status.add(StuStatusEnum.UnPassed.getId());
            status.add(StuStatusEnum.Recruitted.getId());
            status.add(StuStatusEnum.UnRecruitted.getId());
            status.add(StuStatusEnum.Admited.getId());
            status.add(StuStatusEnum.UnAdmitd.getId());
            status.add(StuStatusEnum.Graduation.getId());
            status.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", status);//状态
            List<String> status2 = new ArrayList<>();
            status2.add(StuStatusEnum.Passed.getId());
            status2.add(StuStatusEnum.UnPassed.getId());
            status2.add(StuStatusEnum.Recruitted.getId());
            status2.add(StuStatusEnum.UnRecruitted.getId());
            status2.add(StuStatusEnum.Admited.getId());
            status2.add(StuStatusEnum.UnAdmitd.getId());
            status2.add(StuStatusEnum.Graduation.getId());
            status2.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status2", status2);//状态
        }
        parMp.put("maxEduId", maxEduId);//最高学历
        parMp.put("hospitalLevelId", hospitalLevelId);//医院等级
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        List<StuUserExt> stuUserLst = stuUserBiz.queryNurseList(parMp);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        return "dwjxres/hospital/nursing/queryStuList";
    }

    @RequestMapping("/exportQueryNurse")
    public void exportQueryNurse(String sexId, String startAge, String endAge, String titleId, String certifiedTypeId, String speId,
                               String stuTimeId, String isAudit, String maxEduId, String hospitalLevelId, Integer currentPage,
                               String userName, String batchFlow, HttpServletResponse response, String isQuery) throws Exception {
        if (StringUtil.isBlank(isQuery) && StringUtil.isBlank(batchFlow)) {
            List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
            if (null != batchLst && batchLst.size() > 0) {
                for (StuBatch obj : batchLst) {
                    if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                        batchFlow = obj.getBatchFlow();//当前系统默认 进修批次流水号
                        break;
                    }
                }
            }
        }
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("sexId", sexId);//性别
        parMp.put("startAge", startAge);//年龄起始值
        parMp.put("endAge", endAge);//年龄结束值
        parMp.put("titleId", titleId);//职称
        parMp.put("certifiedTypeId", certifiedTypeId);//执业类别
        parMp.put("stuTimeId", stuTimeId);//进修时间
        parMp.put("isAudit", isAudit);//是否审核通过
        if(StringUtil.isBlank(isAudit)){
            List<String> status = new ArrayList<>();
            status.add(StuStatusEnum.Passed.getId());
            status.add(StuStatusEnum.UnPassed.getId());
            status.add(StuStatusEnum.Recruitted.getId());
            status.add(StuStatusEnum.UnRecruitted.getId());
            status.add(StuStatusEnum.Admited.getId());
            status.add(StuStatusEnum.UnAdmitd.getId());
            status.add(StuStatusEnum.Graduation.getId());
            status.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", status);//状态
            List<String> status2 = new ArrayList<>();
            status2.add(StuStatusEnum.Passed.getId());
            status2.add(StuStatusEnum.UnPassed.getId());
            status2.add(StuStatusEnum.Recruitted.getId());
            status2.add(StuStatusEnum.UnRecruitted.getId());
            status2.add(StuStatusEnum.Admited.getId());
            status2.add(StuStatusEnum.UnAdmitd.getId());
            status2.add(StuStatusEnum.Graduation.getId());
            status2.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status2", status2);//状态
        }

        parMp.put("maxEduId", maxEduId);//最高学历
        parMp.put("hospitalLevelId", hospitalLevelId);//医院等级
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业

        List<Map<String, Object>> stuUserLst = stuUserBiz.queryNurseListForExport(parMp);
        for (Map map : stuUserLst) {
            if (null != map.get("RESUME_INFO")) {
                String resumeInfo = ClobToString((Clob) map.get("RESUME_INFO"));
                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resumeInfo);
                if (extInfo.getWorkResumeList() != null) {
                    String msg = "";
                    int num = 1;
                    for (WorkResumeForm work : extInfo.getWorkResumeList()) {

                        msg += num + "、" + work.getClinicalRoundDate() + "" + work.getHospitalName() + "" + work.getPostName()
                                + "" + work.getWorkDescription() + "\n";
                        num++;
                    }

                    map.put("workResumeList", msg);
                    map.put("titleTypeName", DictTypeEnum.TitleGenre.getDictNameById(extInfo.getTitleTypeId()));
                    map.put("graduatedNo", extInfo.getGraduatedNo());
                    map.put("qualifiedNo", extInfo.getQualifiedNo());
                    map.put("certifiedNo", extInfo.getCertifiedNo());
                    map.put("studyAim",extInfo.getStudyAim());
                }

            }
        }

        String fileName = "学员名单.xls";
        String titles[] = {
                "userName:姓名",
                "userBirthday:出生日期",
                "sexName:性别",
                "userAge:年龄",
                "idNo:身份证号",
                "nationName:民族",
                "titleTypeName:职称类别",
                "titleName:职称",
                "deptName:所在科室",
                "jobYear:工作年限",
                "postName:职务",
                "certifiedTypeName:执业资格",
                "speName:进修专业",
                "stuTimeName:进修时间",
                "stuBatName:进修批次",
                "clotherSizeName:工作服尺寸",
                "isPutup:是否住宿",
                "maxEduName:最高学历",
                "schoolName:毕业学校",
                "schoolSpeName:学习专业",
                "maxEduBdate:最高学历开始时间",
                "maxEduEdate:最高学历结束时间",
                "isComputer:是否熟练电脑",
                "userPhone:手机号码",
                "sendComName:选送单位",
                "hospitalLevelName:医院等级",
                "sendComAddress:选送单位详细地址",
                "qualifiedNo:医师资格证书编号",
                "certifiedNo:医师执业证书编号",
                "workResumeList:工作经历",
                "studyAim:进修目的",
                "identificationNumber:选送单位纳税人识别号"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, stuUserLst, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");

    }
}
