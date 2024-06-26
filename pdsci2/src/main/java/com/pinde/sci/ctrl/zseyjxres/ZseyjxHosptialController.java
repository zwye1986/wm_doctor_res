package com.pinde.sci.ctrl.zseyjxres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxDocSingupBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxStuDoctorInfoBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxStuFurtherStudyRegistBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.SysDictMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.zseyjxres.EducationForm;
import com.pinde.sci.form.zseyjxres.ExportStuUserInfo;
import com.pinde.sci.form.zseyjxres.ExtInfoForm;
import com.pinde.sci.form.zseyjxres.SpeForm;
import com.pinde.sci.form.zseyjxres.WorkResumeForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.pinde.sci.biz.zseyjxres.IZseyScholarSchArrangeBiz;
import com.pinde.sci.enums.zseyjxres.StuRoleEnum;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/zseyjxres/hospital")
@Controller
public class ZseyjxHosptialController extends GeneralController {

    private static final String ZSEY_ORG_FLOW = "9f9a5bc7111a4670bda79f25c7c98efd";
    @Autowired
    private IZseyScholarSchArrangeBiz arrangeBiz;
    @Autowired
    private IDeptBiz sysDeptBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private ISchDeptRelBiz deptRelBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private ISchRotationBiz schRotationtBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private ISchRotationGroupBiz schRotationtGroupBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IZseyjxDocSingupBiz docSingupBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserBiz;
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IZseyjxStuFurtherStudyRegistBiz furtherStudyRegistBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IZseyjxStuDoctorInfoBiz stuDoctorInfoBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;

    @RequestMapping("/home")
    public String home(Integer currentPage, Model model, HttpServletRequest request) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, getPageSize(request));
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.JX_NOTICE_SYS_ID);
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
        List<StuUserExt> dsh = stuUserBiz.searchStuUser(mp);
        int passingCount = dsh == null ? 0 : dsh.size();//待审核数

        mp.put("statusId", StuStatusEnum.Passed.getId());
        List<StuUserExt> shtg = stuUserBiz.searchStuUser(mp);
        int passedCount = shtg == null ? 0 : shtg.size();//审核通过数

        mp.put("statusId", StuStatusEnum.UnPassed.getId());
        List<StuUserExt> shbtg = stuUserBiz.searchStuUser(mp);
        int uppassedCount = shbtg == null ? 0 : shbtg.size();//审核不通过数

        mp.put("statusId", StuStatusEnum.Recruitted.getId());
        List<StuUserExt> lq2 = stuUserBiz.searchStuUser(mp);
        int recruittedCount = lq2 == null ? 0 : lq2.size();//已录取数

        mp.put("statusId", StuStatusEnum.UnRecruitted.getId());
        List<StuUserExt> wlq2 = stuUserBiz.searchStuUser(mp);
        int unrecruittedCount = wlq2 == null ? 0 : wlq2.size();//未录取数


        mp.put("statusId", StuStatusEnum.Admited.getId());
        List<StuUserExt> lq = stuUserBiz.searchStuUser(mp);
        int admitedCount = lq == null ? 0 : lq.size();//报到数

        mp.put("statusId", StuStatusEnum.UnAdmitd.getId());
        List<StuUserExt> wlq = stuUserBiz.searchStuUser(mp);
        int unadmitedCount = wlq == null ? 0 : wlq.size();//未报到数

        mp.put("statusId", StuStatusEnum.Graduation.getId());
        List<StuUserExt> jy = stuUserBiz.searchStuUser(mp);
        int graduationCount = jy == null ? 0 : jy.size();//结业数

        mp.put("statusId", StuStatusEnum.DelayGraduation.getId());
        List<StuUserExt> yjy = stuUserBiz.searchStuUser(mp);
        int delayGraduationCount = yjy == null ? 0 : yjy.size();//延期结业数

        model.addAttribute("passingCount", passingCount);
        model.addAttribute("singupCount", passingCount + passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + uppassedCount + graduationCount + delayGraduationCount);//报名数
        model.addAttribute("passedCount", passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + graduationCount + delayGraduationCount);//审核通过数
        return "zseyjxres/hospital/index";
    }

    /**
     * list深拷贝
     * @param src
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * 报名审核
     */
    @RequestMapping(value = "/audit")
    public String audit(String statusId, Integer currentPage,HttpServletRequest request, String userName, String batchFlow, String speId, String speName2,String isPublish, Model model, String isQuery) {

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
        PageHelper.startPage(currentPage, getPageSize(request));

//        if(StuStatusEnum.UnHeadPassed.getId().equals(statusId)){
//            Map<String, Object> parMp = new HashMap<String, Object>();
//            parMp.put("batchFlow", batchFlow);//批次
//            parMp.put("userName", userName);//用户名
//            parMp.put("speName2", speName2);//进修专业
//
//            List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
//            model.addAttribute("statusId", statusId);
//            model.addAttribute("stuUserLst", stuUserLst);
//            Map<String, ExtInfoForm> extInfoMap = new HashMap();
//            List<StuUserExt> stuUserLst2= new ArrayList<>();
//            if (stuUserLst != null && stuUserLst.size() > 0) {
//                ExtInfoForm extInfo = null;
//                for (StuUserExt tempUserExt : stuUserLst) {
//                    List<StuHeadAuditStatus> headAuditStatuses = docSingupBiz.getHeadStatus(tempUserExt.getResumeFlow(),null,null,null,null);
//                    if(headAuditStatuses.size()>0 && !headAuditStatuses.isEmpty()){
////
//                        for(StuHeadAuditStatus status:headAuditStatuses){
//                            if(status.getStuStatusId().contains("HeadUnPassed")){
//                                stuUserLst2.add(tempUserExt);
//                            }
//                        }
//
//                    }
//                    extInfo = new ExtInfoForm();
//                    extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
//                    if(StringUtil.isNotBlank(extInfo.getPassportNo()) && StringUtil.isBlank(extInfo.getIdNo())){
//                        extInfo.setIdNo(extInfo.getPassportNo());
//                    }
//                    extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
//                }
//                model.addAttribute("stuUserLst2",stuUserLst2);
//                model.addAttribute("extInfoMap", extInfoMap);
//            }
//        }

        if (StringUtil.isBlank(statusId)) {
            statusId = StuStatusEnum.Passing.getId();
        }
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        if (StuStatusEnum.Passed.getId().equals(statusId)) {
            List<String> status = new ArrayList<>();
            status.add(StuStatusEnum.Passed.getId());
            status.add(StuStatusEnum.Recruitted.getId());
            status.add(StuStatusEnum.UnRecruitted.getId());
            status.add(StuStatusEnum.Admited.getId());
            status.add(StuStatusEnum.UnAdmitd.getId());
            status.add(StuStatusEnum.Graduation.getId());
            status.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", status);//状态
        }else{
            parMp.put("statusId", statusId);//状态
        }
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);//进修专业

//        parMp.put("speId", speId);//进修专业ID
//        parMp.put("speName", speName);//进修专业


        parMp.put("isPublish", isPublish);//发布状态
        if (StuStatusEnum.UnPassed.getId().equals(statusId)) {
            parMp.put("isBack", GlobalConstant.FLAG_N);//未被退回的
        }
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("statusId", statusId);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            Map<String,List<StuHeadAuditStatus>> statusMap = new HashMap<>();//要记存 每个审核不通过的科室意见
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                List<StuHeadAuditStatus> statuses = docSingupBiz.getHeadStatus(batchFlow,
                        tempUserExt.getResumeFlow(),null,StuStatusEnum.UnPassed.getId(),null,tempUserExt.getUserFlow());
                if(statuses.size()>0 && !statuses.isEmpty()){
//                    for(StuHeadAuditStatus audit:statuses){
//
//                    }
                    statusMap.put(tempUserExt.getResumeFlow(),statuses);
                }
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                if(StringUtil.isNotBlank(extInfo.getPassportNo()) && StringUtil.isBlank(extInfo.getIdNo())){
                    extInfo.setIdNo(extInfo.getPassportNo());
                }
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("statusMap",statusMap);
            model.addAttribute("stuUserLst",stuUserLst);
            model.addAttribute("extInfoMap", extInfoMap);
        }

        return "zseyjxres/hospital/audit";
    }

    /**
     * 学员录取
     */
    @RequestMapping(value = "/_recruitDoctor")
    public String _recruitDoctor(String statusId, Integer currentPage,HttpServletRequest request, String userName, String batchFlow, String speId, String speName2,String isPublish, Model model, String isQuery) {

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
        PageHelper.startPage(currentPage, getPageSize(request));
        if (StringUtil.isBlank(statusId)) {
            statusId = StuStatusEnum.Passed.getId();
        }
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        if (StuStatusEnum.Recruitted.getId().equals(statusId)) {
            List<String> status = new ArrayList<>();
            status.add(StuStatusEnum.Recruitted.getId());
            status.add(StuStatusEnum.Admited.getId());
            status.add(StuStatusEnum.UnAdmitd.getId());
            status.add(StuStatusEnum.Graduation.getId());
            status.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", status);//状态
        } else {
            parMp.put("statusId", statusId);//状态
        }
        parMp.put("userName", userName);//用户名
//        parMp.put("speId", speId);//进修专业
        parMp.put("speName2", speName2);//进修专业
        parMp.put("isPublish", isPublish);//发布状态
        /*if(StuStatusEnum.UnPassed.getId().equals(statusId)){
			parMp.put("isBack",GlobalConstant.FLAG_N);//未被退回的
		}*/
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("statusId", statusId);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                if(StringUtil.isNotBlank(extInfo.getPassportNo()) && StringUtil.isBlank(extInfo.getIdNo())){
                    extInfo.setIdNo(extInfo.getPassportNo());
                }
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        return "zseyjxres/hospital/_recruitDoctor";
    }

    /**
     * 用户信息审核
     */
    @RequestMapping(value = "/auditOption")
    @ResponseBody
    public String auditOption(String reportDate,String hotelExpense,String resumeFlow, String userFlow, String reason, String statusId, String tuition,String according,String coverallNum,Model model) {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("resumeFlow", resumeFlow);
        mp.put("userFlow", userFlow);
        mp.put("reason", reason);
        mp.put("statusId", statusId);
        this.docSingupBiz.auditOption(mp);
        StuUserResume sur=stuUserResumeBiz.searchStuUserResume(resumeFlow);
        if(sur!=null&&StringUtil.isNotBlank(sur.getResumeInfo())){
            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(sur.getResumeInfo());
            extInfo.setTuition(tuition);//进修费
            extInfo.setAccording(according);//材料费
//            extInfo.setCoverallNum(coverallNum);
            extInfo.setHotelExpense(hotelExpense);//住宿费
            extInfo.setReportDate(reportDate);//报到时间
            sur.setResumeInfo(docSingupBiz.getXmlFromExtInfo(extInfo));
            if(StuStatusEnum.Recruitted.getId().equals(statusId)){
                sur.setIsPublish(GlobalConstant.FLAG_N);
            }
            stuUserResumeBiz.save(sur);
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 展示发布录取通知
     */
    @RequestMapping(value = "/showPublish")
    public String showPublish(String resumeFlow, String userFlow, String batchId, Model model) {
        StuUserExt stuUser = stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        if (null != stuUser) {
            model.addAttribute("userName", stuUser.getSysUser().getUserName());
            model.addAttribute("speName", stuUser.getSpeName());//进修专业
            model.addAttribute("stuBatName", stuUser.getStuBatName());//进修批次
            model.addAttribute("stuTimeName", stuUser.getStuTimeName());//进修时间
            String date = DateUtil.addMonth(stuUser.getStuBatName().replace("年", "-").replace("月", ""), Integer.valueOf(stuUser.getStuTimeId()));
            model.addAttribute("stuBatEndName", formate(date));//进修批次+进修时间
            model.addAttribute("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            model.addAttribute("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            model.addAttribute("trainFee", (stuUser.getStuBatch().getMonthFee()).intValue() * Integer.valueOf(stuUser.getStuTimeId()) + "");//进修培养费
        }
        return "zseyjxres/hospital/showPublish";
    }

    /**
     * 发布录取通知
     */
    @RequestMapping(value = "/publish")
    @ResponseBody
    public String publish(String resumeFlow,Model model) {

        StuUserResume stuUser = stuUserResumeBiz.getStuUserByKey(resumeFlow);
        if(!"不住宿".equals(stuUser.getIsPutup())){
            stuUser.setIsPublishAccommodation(GlobalConstant.FLAG_Y);//只给住宿学员发送 住宿介绍信
        }
        stuUser.setIsPublish(GlobalConstant.FLAG_Y);
        int count = stuUserResumeBiz.save(stuUser);
        if (count == 0) {
            return "发布失败";
        }
        //广医大进修发布通知功能已取消，直接在录取操作时即发布
//        SysUser user = userBiz.readSysUser(stuUser.getUserFlow());
//        this.msgBiz.sendSMS(user.getUserPhone(),"gzzyjxres10004",user.getUserName(),stuUser.getResumeFlow(),"STU_USER_RESUME");
        return "发布成功";
    }

    /**
     * 退回窗口
     * @param userFlow
     * @param resumeFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/sendBack")
    public String sendBack(String userFlow,String resumeFlow,Model model){

        model.addAttribute("userFlow",userFlow);
        model.addAttribute("resumeFlow",resumeFlow);
        return "zseyjxres/hospital/sendBack";
    }
    /**
     * 退回操作
     */
    @RequestMapping(value = "/returnInfo")
    @ResponseBody
    public String returnInfo(String resumeFlow, String reason, String userFlow) {
        if(StringUtil.isNotBlank(resumeFlow) && StringUtil.isNotBlank(userFlow)){
            int result = docSingupBiz.returnInfo(resumeFlow, reason, userFlow);
            if (result>GlobalConstant.ZERO_LINE) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }

        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 导出录取人员名单
     *
     * @param userName
     * @param batchFlow
     * @param speName2
     * @param isPublish
     * @param res
     * @throws Exception
     */
    @RequestMapping("/exportAuditRecruitted")
    public void exportRecruittedDoctor(String userName, String batchFlow, String speName2, String isPublish, HttpServletResponse res) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
//		parMp.put("statusId", StuStatusEnum.Passed.getId());//状态

        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Recruitted.getId());
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.UnAdmitd.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);//进修专业
        parMp.put("isPublish", isPublish);//是否已发布通知书
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        String fileName = "录取学员名单.xls";
        createExcle(res, fileName, stuUserLst, "audit");
    }

    /**
     * 导出审核通过数据
     */
    @RequestMapping("/exportAuditPassed")
    public void exportPassedDoctor(String userName, String batchFlow, String speName2, String isPublish, HttpServletResponse res) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
//		parMp.put("statusId", StuStatusEnum.Passed.getId());//状态

        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Passed.getId());
        status.add(StuStatusEnum.Recruitted.getId());
        status.add(StuStatusEnum.UnRecruitted.getId());
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.UnAdmitd.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);
        parMp.put("isPublish", isPublish);//用户名
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
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

//            eui.setSpeName(ext.getSpeName());
            eui.setBatchRegDate(ext.getStuBatch().getBatchRegDate());
            eui.setTeacherName(ext.getTeacherName());
            eui.setIsGraduation(GlobalConstant.FLAG_Y.equals(ext.getIsGraduation()) ? "是" : "否");
            eui.setEducation(ext.getMaxEduName());
            eui.setTitleName(ext.getTitleName());
            eui.setSendComName(ext.getSendComName());

            StuUserResume sur=stuUserResumeBiz.searchStuUserResume(ext.getResumeFlow());
            ExtInfoForm extInfoForm=new ExtInfoForm();
            if(sur!=null){
                extInfoForm= docSingupBiz.parseExtInfoXml(sur.getResumeInfo());
            }
            String speName ="";
            String setStuTimeName = "";
            List<String> beginEndList = new ArrayList<>();//学员进行起始时间List
            if(extInfoForm.getSpeFormList()!=null && extInfoForm.getSpeFormList().size()>0){
                for(SpeForm speForm:extInfoForm.getSpeFormList()){
                    speName+=speForm.getSpeName()+",";
                    setStuTimeName+=speForm.getStuTimeId()+"个月,";
                    if(StringUtil.isNotBlank(speForm.getBeginDate())){
                        beginEndList.add(speForm.getBeginDate());
                    }
                    if(StringUtil.isNotBlank(speForm.getEndDate())){
                        beginEndList.add(speForm.getEndDate());
                    }
                }


            eui.setSpeName(speName.substring(0,speName.length()-1));
            eui.setStuTimeName(setStuTimeName.substring(0,setStuTimeName.length()-1));
            String startDate="";
            String endDate="";
            String stuStartAndEndTime="";


            if(beginEndList!=null && beginEndList.size()>0){
                String dates[] = new String[beginEndList.size()];
                beginEndList.toArray(dates);
                //冒泡排序，取得最小值和最大值
                for(int i=0;i<dates.length-1;i++){
                    for(int j=0;j<dates.length-1-i;j++){
                        if(dates[j].compareTo(dates[j+1])>0){
                            String temp=dates[j];
                            dates[j]=dates[j+1];
                            dates[j+1]=temp;
                        }
                    }
                }
                startDate = dates[0];
                endDate = dates[dates.length-1];
            }

            stuStartAndEndTime=startDate+"至"+endDate;
            eui.setStuStartAndEndTime(stuStartAndEndTime);
        }
            eui.setUserPhone(extInfoForm.getUserPhone());
            eui.setSexName(extInfoForm.getSexName());
            eui.setUserBirthday(extInfoForm.getUserBirthday());
            eui.setCertifiedNo(extInfoForm.getCertifiedNo());
            eui.setTuition(extInfoForm.getTuition());
            eui.setHotelExpense(extInfoForm.getHotelExpense());
            dataList.add(eui);
        }
        String[] titles = null;
        if ("audit".equals(opertype)) {
            titles = new String[]{
//                    "userCode:编号",
                    "userName:姓名",
                    "sexName:性别",
                    "userBirthday:出生年月",
                    "education:学历",
                    "titleName:职称",
                    "certifiedNo:执业资格证号",
                    "sendComName:工作单位",
                    "speName:进修科目",
                    "stuStartAndEndTime:进修起止时间",
                    "stuTimeName:进修时长",
                    "userPhone:联系电话",
//                    "tuition:学费",
//                    "hotelExpense:住宿费",
                    "beizhu:备注"
            };
        } else {
            titles = new String[]{
                    "userName:姓名",
                    "idNo:身份证号",
                    "schoolSpeName:毕业专业",
                    "stuTimeName:进修时间",
                    "speName:进修专业",
                    "batchRegDate:报到时间",
//                    "teacherName:带教老师",
                    "isGraduation:是否结业"
            };
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcle(titles, dataList, ExportStuUserInfo.class, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 学员报到
     */
    @RequestMapping("/recruitDoctor")
    public String recruitDoctor(String status, Integer currentPage,HttpServletRequest request, String userName, String batchFlow, String speName2, Model model, String isQuery) {
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        if (StringUtil.isBlank(batchFlow) && StringUtil.isBlank(isQuery)) {
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
        PageHelper.startPage(currentPage, getPageSize(request));
        if (StringUtil.isBlank(status)) {
            status = StuStatusEnum.Recruitted.getId();
        } else if ("Y".equals(status)) {
            status = StuStatusEnum.Admited.getId();
        } else {
            status = StuStatusEnum.UnAdmitd.getId();
        }

        Map<String, Object> parMp = new HashMap<String, Object>();
        if (StuStatusEnum.Admited.getId().equals(status)) {
            List<String> statuss = new ArrayList<>();
            statuss.add(StuStatusEnum.Admited.getId());
            statuss.add(StuStatusEnum.Graduation.getId());
            statuss.add(StuStatusEnum.DelayGraduation.getId());
            parMp.put("status", statuss);//状态
        } else {
            parMp.put("statusId", status);//状态
        }
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);//进修专业

        parMp.put("isPublish", GlobalConstant.FLAG_Y);
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                if(StringUtil.isBlank(extInfo.getIdNo()) && StringUtil.isNotBlank(extInfo.getPassportNo())){
                    extInfo.setIdNo(extInfo.getPassportNo());
                }
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        return "zseyjxres/hospital/doctorrecruit";
    }

    /**
     * 学员结业
     */
    @RequestMapping("/graduationDoctor")
    public String graduationDoctor(String status, Integer currentPage,HttpServletRequest request, String userName, String batchFlow, String speName2, Model model, String isQuery) {
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        if (StringUtil.isBlank(batchFlow) && StringUtil.isBlank(isQuery)) {
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
        PageHelper.startPage(currentPage, getPageSize(request));
        if (StringUtil.isBlank(status)) {
            status = StuStatusEnum.Admited.getId();
        } else if ("Y".equals(status)) {
            status = StuStatusEnum.Graduation.getId();
        } else {
            status = StuStatusEnum.DelayGraduation.getId();
        }
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("statusId", status);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);//进修专业
        parMp.put("haveFile", "Y");//上传了结业鉴定表
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("stuUserLst", stuUserLst);
        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        //进修时长不满三个月的人员，不显示发放结业证书

        String flag1 = GlobalConstant.FLAG_N;
        //学习时间不够三个月的，系统不显示结业证书打印
        if (stuUserLst != null && stuUserLst.size() > 0){
            for(StuUserExt userExt:stuUserLst){
                List<SchArrangeResult> arrangeResults = schArrangeResultBiz.searchSchArrangeResultByDoctor(userExt.getUserFlow());
                if(arrangeResults!=null && arrangeResults.size()>0){
                    Double totalMonth=0.0;
                    for(SchArrangeResult result:arrangeResults){
                        totalMonth+=Double.parseDouble(result.getSchMonth());//总轮转时间
                    }
                    if(totalMonth.compareTo(Double.valueOf(3.0))>=0){
                        flag1=GlobalConstant.FLAG_Y;
                    }else{

                            if (stuUserLst != null && stuUserLst.size() > 0) {
                                for (StuUserExt tempUserExt : stuUserLst) {
                                    StuUserResume stuUserResume = stuUserResumeBiz.getStuUserByKey(tempUserExt.getResumeFlow());
                                    stuUserResume.setIsGraduation(GlobalConstant.FLAG_Y);
                                    stuUserResume.setStuStatusId(StuStatusEnum.Graduation.getId());
                                    stuUserResume.setStuStatusName(StuStatusEnum.Graduation.getName());
                                    stuUserResumeBiz.save(stuUserResume);
                                }
                            }
                    }
                }
            }
        }
        model.addAttribute("flag1",flag1);


        if (stuUserLst != null && stuUserLst.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        //学生上传结业鉴定
        Map<String, Object> stuFileMap = new HashMap<>();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            for (StuUserResume sur : stuUserLst) {
                List<PubFile> files2 = fileBiz.findFileByTypeFlow("StuFlie", sur.getResumeFlow());
                if (files2 != null && files2.size() > 0) {
                    stuFileMap.put(sur.getResumeFlow(), files2.get(0));
                }
            }
        }
        model.addAttribute("stuFileMap", stuFileMap);
        return "zseyjxres/hospital/docGraduation";
    }

    /**
     * 不录取的操作
     */
    @RequestMapping("/notrecruit")
    @ResponseBody
    public String notrecruit(String resumeFlow, String statusId) {
        statusId = "Y".equals(statusId) ? "Admited" : "UnAdmitd";
        this.docSingupBiz.changeRecruit(resumeFlow, statusId);
        //确认报到添加工号
        if("Admited".equals(statusId)){
            int count=stuDoctorInfoBiz.countAdmitedStuUsers(DateUtil.getYear());
            String workNo=count+"";
            if(workNo.length() == 1){
                workNo = DateUtil.getYear()+"J"+"00"+workNo;
            }else if(workNo.length() == 2){
                workNo = DateUtil.getYear()+"J"+"0"+workNo;
            }else {
                workNo=DateUtil.getYear()+"J"+count;
            }
            StuUserResume sur=stuUserBiz.getStuUserByKey(resumeFlow);
            if(sur!=null&&sur.getUserFlow()!=null){
                SysUser user=new SysUser();
                user.setUserCode(workNo);
                user.setUserFlow(sur.getUserFlow());
                userBiz.edit(user);
            }
            StuUserResume stuUserResume=stuUserResumeBiz.getStuUserByKey(resumeFlow);
            if(stuUserResume!=null){
                String resumeInfo=stuUserResume.getResumeInfo();
                if(StringUtil.isNotBlank(resumeInfo)){
                    ExtInfoForm form=docSingupBiz.parseExtInfoXml(resumeInfo);
                    if(form!=null){
                        form.setUserCode(workNo);
                        resumeInfo=docSingupBiz.getXmlFromExtInfo(form);
                        stuUserResume.setResumeInfo(resumeInfo);
                    }
                    docSingupBiz.updateStuUserResume(stuUserResume);
                }
            }
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 导出已录取的学员
     */
    @RequestMapping("/exportAdmitPassed")
    public void exportRecruitDoctor(String batchFlow, String userName, String speName2, HttpServletResponse response) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
//		parMp.put("statusId", StuStatusEnum.Admited.getId());//状态
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speName2", speName2);//进修专业id
        List<StuUserExt> StuUserExtLst = stuUserBiz.searchStuUser(parMp);
        String fileName = "录取学员名单.xls";
        createExcle(response, fileName, StuUserExtLst, null);
    }

    /**
     * 导出已录取的学员
     */
    @RequestMapping("/exportGraduation")
    public void exportGraduation(String batchFlow, String userName, String speId, HttpServletResponse response) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("statusId", StuStatusEnum.Graduation.getId());//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        List<StuUserExt> StuUserExtLst = stuUserBiz.searchStuUser(parMp);
        String fileName = "结业学员名单.xls";
        createExcle(response, fileName, StuUserExtLst, null);
    }

    /**
     * 打印进修申请表
     */
    @RequestMapping(value = "/printApplForm")
    public void printApplForm(String resumeFlow, String templeteName, String printFlag, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserResume stuUser = this.stuUserBiz.getStuUserByKey(resumeFlow);
        if (null != stuUser) {
            ExtInfoForm extInfo = this.docSingupBiz.parseExtInfoXml(stuUser.getResumeInfo());
            SysUser user = this.userBiz.readSysUser(stuUser.getUserFlow());
            dataMap.put("userName", extInfo.getUserName());
            dataMap.put("sexName", extInfo.getSexName());
            dataMap.put("userAge", stuUser.getUserAge());
            dataMap.put("idNo", extInfo.getIdNo());
            dataMap.put("major",extInfo.getUserMajor());//个人专业
            dataMap.put("isPutup",stuUser.getIsPutup());//是否要住宿
            dataMap.put("schoolName",stuUser.getSchoolName());//毕业学校
            dataMap.put("certifiedNo",stuUser.getCertifiedNo());
            dataMap.put("nationName", extInfo.getNationName());
            dataMap.put("titleName", stuUser.getTitleName());
            dataMap.put("deptName", stuUser.getDeptName());
            dataMap.put("jobYear", stuUser.getJobYear());
            dataMap.put("postName", stuUser.getPostName());
            dataMap.put("userPhone", extInfo.getUserPhone());
            dataMap.put("areaCode",extInfo.getAreaCode());//长途区号
            dataMap.put("oph",extInfo.getOph());//办公电话
            dataMap.put("faxTelephone",extInfo.getFaxTelephone());//传真电话
			dataMap.put("userEmail",extInfo.getUserEmail());//邮箱
            dataMap.put("graduationDate",extInfo.getGraduationDate());//毕业时间
            dataMap.put("cerLastRegisterDate",extInfo.getCerLastRegisterDate());//执业证末次注册时间
            dataMap.put("practiceContent",extInfo.getPracticeContent());//医师执业地点、类别、范围

            String speName = "";
            String setStuTimeName = "";
            String beginDate ="";//进修开始时间
            String finishDate = "";//进修结束时间
            double totalMonth=0.0;//进修月数
            List<String> beginEndList = new ArrayList<>();//学员进行起始时间List
            if(extInfo.getSpeFormList()!=null && extInfo.getSpeFormList().size()>0){
                for(SpeForm speForm:extInfo.getSpeFormList()){
                    speName+=speForm.getSpeName()+"+";
//                    setStuTimeName+=DateUtil.signDaysBetweenTowDate(speForm.getEndDate(),speForm.getBeginDate())+1+"天"+"+";
                    setStuTimeName+=speForm.getStuTimeId()+"+";
                    totalMonth+=Double.parseDouble(speForm.getStuTimeId());
                    if(StringUtil.isNotBlank(speForm.getBeginDate())){
                        beginEndList.add(speForm.getBeginDate());
                    }
                    if(StringUtil.isNotBlank(speForm.getEndDate())){
                        beginEndList.add(speForm.getEndDate());
                    }
                }
                if(null!=beginEndList && beginEndList.size()>0){
                    String dates[] = new String[beginEndList.size()];
                    beginEndList.toArray(dates);
                    //冒泡排序，取得最小值和最大值
                    for(int i=0;i<dates.length-1;i++){
                        for(int j=0;j<dates.length-1-i;j++){
                            if(dates[j].compareTo(dates[j+1])>0){
                                String temp=dates[j];
                                dates[j]=dates[j+1];
                                dates[j+1]=temp;
                            }
                        }
                    }
                    beginDate = dates[0];
                    finishDate = dates[dates.length-1];
                }

            }
            dataMap.put("speName", speName.substring(0,speName.length()-1));
            dataMap.put("beginDate",beginDate); //进修开始时间
            dataMap.put("finishDate",finishDate); //进修结束时间
            dataMap.put("setStuTimeName",setStuTimeName.substring(0,setStuTimeName.length()-1)); //所选科室进修时间
            dataMap.put("totalMonth",String.valueOf(totalMonth)); //进修总月数
            dataMap.put("unitpropertyName",stuUser.getUnitpropertyName());//单位性质
            dataMap.put("unitrankName",stuUser.getUnitrankName());//单位等级
            dataMap.put("unitlevelName",stuUser.getUnitlevelName());//单位级别

            String year  = stuUser.getModifyTime().substring(0,4);
            String month = stuUser.getModifyTime().substring(4,6);
            String day = stuUser.getModifyTime().substring(6,8);
            dataMap.put("applyTime",year+"年"+month+"月"+day+"日");//填表时间

            dataMap.put("certifiedNo", extInfo.getCertifiedNo());
            dataMap.put("certifiedTypeName", stuUser.getCertifiedTypeName());
            dataMap.put("maxEduName", stuUser.getMaxEduName());
            dataMap.put("maxEduDate", stuUser.getMaxEduBdate() + "至" + stuUser.getMaxEduEdate());
            dataMap.put("schoolName", stuUser.getSchoolName());
            dataMap.put("schoolSpeName", stuUser.getSchoolSpeName());
            dataMap.put("isComputer", "Y".equals(stuUser.getIsComputer()) ? "是" : "否");
            dataMap.put("sendComName",stuUser.getSendComName());
            dataMap.put("sendComAddress",stuUser.getSendComAddress());
            dataMap.put("sendComInfo", stuUser.getSendComName() + " " + stuUser.getHospitalLevelName() + " " + stuUser.getSendComAddress());


            dataMap.put("stuBatName", stuUser.getStuBatName());
            dataMap.put("clotherSizeName", stuUser.getClotherSizeName());
            dataMap.put("isPutup", stuUser.getIsPutup());
            dataMap.put("userBirthday", user.getUserBirthday().substring(0,7));
            dataMap.put("postCodes", extInfo.getPostCodes());
            dataMap.put("politicsStatusName", extInfo.getPoliticsStatusName());
            dataMap.put("nativePlaceProvName", extInfo.getNativePlaceProvName());
            dataMap.put("nativePlaceCityName", extInfo.getNativePlaceCityName());
            //学历及社会经历
            String eduData="一、学历信息：";
            List<String> eduDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getEducationList()!=null){
                for(EducationForm eForm:extInfo.getEducationList()){
                    eduDateList.add(eForm.getEduRoundDate());
                }
                Collections.sort(eduDateList);
                Collections.reverse(eduDateList);
                for(String edu:eduDateList){
                    for(EducationForm efrom:extInfo.getEducationList()){
                        if(edu!=null&&edu.equals(efrom.getEduRoundDate())){
                            eduData=eduData+" "+efrom.getEduRoundDate()+" "+efrom.getSchoolName()+" "+efrom.getSpeName()+" "+efrom.getLength()+" "+efrom.getEducation()+" "+efrom.getDegree();
                        }
                    }
                }
            }

            String workData="二、工作经历：";
            List<String> workDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                    workDateList.add(eForm.getClinicalRoundDate());
                }
            }
            Collections.sort(workDateList);
            Collections.reverse(workDateList);
            for(String wd:workDateList){
                for(WorkResumeForm efrom:extInfo.getWorkResumeList()){
                    if(wd!=null&&wd.equals(efrom.getClinicalRoundDate())){
                        workData=workData+" "+efrom.getClinicalRoundDate()+" "+efrom.getHospitalName()+" "+efrom.getWorkDescription()+" "+efrom.getPostName();
                    }
                }
            }
            dataMap.put("socialExperience", eduData+"\r"+workData);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            String currDate = dateFormat.format(date);
            dataMap.put("date",currDate);
            //进修系统打印申请表去除工作经历一栏
//			List<Map<String,Object>> mapLst = new ArrayList<Map<String, Object>>();
//			List<WorkResumeForm> formLst = extInfo.getWorkResumeList();
//			if(null != formLst && formLst.size() > 0){
//				for(WorkResumeForm work : formLst){
//					Map<String,Object> mp = new HashMap<String, Object>();
//					mp.put("clinicalRoundDate",work.getClinicalRoundDate());
//					mp.put("hospitalName",work.getHospitalName());
//					mp.put("workDescription",work.getWorkDescription());
//					mp.put("postName",work.getPostName());
//					mapLst.add(mp);
//				}
//			}
//			dataMap.put("workResumeList",mapLst);//工作经历
            dataMap.put("studyAim", extInfo.getStudyAim());
            dataMap.put("vocationalLevel", extInfo.getVocationalLevel());
//            dataMap.put("templeteName", java.net.URLDecoder.decode(templeteName, "UTF-8"));
            if (StringUtil.isNotBlank(extInfo.getUserHeadImg())) {
                String headImg = InitConfig.getSysCfg("upload_base_url") + "/" + extInfo.getUserHeadImg();
                headImg = "<img  src='" + headImg + "' width='60' height='100'  alt='证件照'/>";
                dataMap.put("headImg", headImg);//学员照片
            } else {
                String strBackUrl = "http://" + req.getServerName() //服务器地址
                        + ":" + req.getServerPort();
                String getPath = strBackUrl + "/pdsci/css/skin/up-pic.jpg";
                dataMap.put("headImg", "<img src='" + getPath + "' width='60' height='100'  alt='证件照'/>");//学员照片
            }
        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/zseyjxres/print/ApplyTemeplete.docx";

        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "进修申请表.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * 打印入科通知条
     */
    @RequestMapping(value = "/printSpeNotice")
    public void printSpeNotice(String resumeFlow, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserBiz.searchAdmitedInfo(resumeFlow);
        if (null != stuUser) {
            dataMap.put("speName", stuUser.getSpeName());//进修专业
            dataMap.put("userName", stuUser.getSysUser().getUserName());//学员姓名
            dataMap.put("stuTimeName", stuUser.getStuTimeName());//进修时间
            dataMap.put("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            //开始时间加1个自然月
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(stuUser.getStuBatch().getBatchRegDate());
            c.setTime(date);
            c.add(Calendar.MONTH, Integer.valueOf(stuUser.getStuTimeId()));
            String NextEndDate = sdf.format(c.getTime());
            dataMap.put("endDate", formate(NextEndDate));//报到时间+进修时间
            dataMap.put("sendComName", stuUser.getSendComName());//选送单位
        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/zseyjxres/print/inSpeNoticeTemeplete.docx";//进修申请表模板
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "入科通知.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    public String formate(String date) {
        String[] arry = date.split("-");
        if (null != arry) {
            if (arry.length == 2) {
                return arry[0] + "." + arry[1];
            } else if (arry.length == 3) {
                return arry[0] + "." + arry[1] + "." + arry[2];
            }
        }
        return "";
    }

    /**
     * 登记带教老师
     */
    @RequestMapping("/registerTeacher")
    public String registerTeacher(String resumeFlow, String deptFlow, Model model) {
        StuUserResume stuUser = this.stuUserBiz.getStuUserByKey(resumeFlow);
        if (null != stuUser) {
            model.addAttribute("teacherName", stuUser.getTeacherName());
            model.addAttribute("teacherFlow", stuUser.getTeacherFlow());
            model.addAttribute("speName", stuUser.getSpeName());
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("deptFlow",stuUser.getSpeId());
        paramMap.put("roleId", StuRoleEnum.Teacher.getId());
        List<SysUser> teas = stuUserBiz.getTeas(paramMap);
        model.addAttribute("resumeFlow", resumeFlow);
        model.addAttribute("teas", teas);
        return "zseyjxres/hospital/registerTeacher";
    }

    @RequestMapping(value = "/saveTeacher", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String saveTeacher(String resumeFlow, String teacherFlow) {
        this.stuUserBiz.saveRegisterTeacher(resumeFlow, teacherFlow);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 结业
     */
    @RequestMapping("/graduation")
    public String graduation(String resumeFlow, Model model) {
        StuUserResume stuUser = this.stuUserBiz.getStuUserByKey(resumeFlow);
        if (null != stuUser) {
            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUser.getResumeInfo());
            model.addAttribute("comment", extInfo.getGraduationMark());
            model.addAttribute("agree", stuUser.getIsGraduation());
        }
        model.addAttribute("resumeFlow", resumeFlow);
        model.addAttribute("stuUser", stuUser);
        return "zseyjxres/hospital/graduation";
    }

    /**
     * 结业审核保存
     * @param resumeFlow
     * @param comment
     * @param agree
     * @param file
     * @return
     */
    @RequestMapping(value = "/saveGraduation", method = {RequestMethod.POST})
    @ResponseBody
    public String saveGraduation(String resumeFlow, String comment, String agree, MultipartFile file) {

        Map<String, Object> map = new HashMap<String, Object>();
        ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUserBiz.getStuUserByKey(resumeFlow).getResumeInfo());
        map.put("extInfo", extInfo);
        map.put("resumeFlow", resumeFlow);
        map.put("comment", comment);
        map.put("agree", agree);
        this.stuUserBiz.saveGraduation(map, file, "N");
        return GlobalConstant.SAVE_SUCCESSED;

    }

    /**
     * 外出进修登记
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "furtherStudyRegist")
    public String furtherStudyRegist(Model model) {
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> speLst = dictBiz.searchDictList(sysDict);
        SysUser sysUser = new SysUser();
        sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
        List<SysUser> userList = userBiz.searchUser(sysUser);
        model.addAttribute("userList", userList);
        model.addAttribute("dwjxSpeLst", speLst);
        return "zseyjxres/hospital/furtherStudyRegist";
    }

    /**
     * 保存/编辑外出进修登记
     */
    @RequestMapping(value = "/editStudyRegist", method = {RequestMethod.POST})
    @ResponseBody
    public String editStudyRegist(StuFurtherStudyRegist stuFurtherStudyRegist) {
//        List<SysUser> users = furtherStudyRegistBiz.readUserByEqDeptNameAndUserName(stuFurtherStudyRegist.getUserName(), stuFurtherStudyRegist.getDeptName());
//        if (users == null || users.size() != 1) {
//            return "该科室下未找到此人，请检查科室名和姓名！";
//        }
        int count = 0;
        count = furtherStudyRegistBiz.saveRegist(stuFurtherStudyRegist);
        if (count > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

//	/**
//	 * 保存/编辑外出进修登记查询相应科室的人员
//	 */
//	@RequestMapping(value="/changeDept",method={RequestMethod.POST})
//	@ResponseBody
//	public String changeDept(String deptId){
//		SysUser sysUser = new SysUser();
//		sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
//		sysUser.setDeptFlow(deptId);
//		List<SysUser> userList = userBiz.searchUser(sysUser);
//		return JSON.toJSONString(userList);
//	}

    /**
     * 进修登记模糊查询
     */
    @RequestMapping(value = "/searchSuggest", method = {RequestMethod.POST})
    @ResponseBody
    public String searchSuggest(SysUser user) {
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        String dataStr = "";
        if (user != null) {
            if (user.getUserName() == null) {
                sysDict.setDictName(user.getDeptName().trim());
                sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysDict> speLst = dictBiz.searchDictList(sysDict);
                for (int i = 0; i < speLst.size(); i++) {
                    SysDict tempDict = speLst.get(i);
                    SysUser sysUser = new SysUser();
                    sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
                    sysUser.setDeptName(tempDict.getDictName());
                    List<StuDeptOfStaff> userdeptList = stuDoctorInfoBiz.searchUserBydept(sysUser);
                    if (userdeptList == null || userdeptList.size() < 1) {
                        speLst.remove(i);
                        i--;
                    }
                }
                dataStr = JSON.toJSONString(speLst);
            } else {
                //先过滤掉姓名
                SysUser sysUser = new SysUser();
                sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
                sysUser.setUserName(user.getUserName().trim());
                List<SysUser> userList = userBiz.searchUser(sysUser);
                //再过滤掉科室名称
                Map<String, Object> paramMap = new HashMap<>();
                sysUser.setDeptName(user.getDeptName().trim());
                paramMap.put("userList", userList);
                paramMap.put("sysUser", sysUser);
                stuDoctorInfoBiz.operUserByMap(paramMap);
                dataStr = JSON.toJSONString(userList);
            }
        }
        return dataStr;
    }

    /**
     * 外出进修登记刪除
     */
    @RequestMapping(value = "/delStudyRegist", method = {RequestMethod.POST})
    @ResponseBody
    public String delStudyRegist(String recordFlow) {
        StuFurtherStudyRegist stuFurtherStudyRegist = new StuFurtherStudyRegist();
        stuFurtherStudyRegist.setRecordFlow(recordFlow);
        stuFurtherStudyRegist.setRecordStatus(GlobalConstant.FLAG_N);
        int count = furtherStudyRegistBiz.saveRegist(stuFurtherStudyRegist);
        if (count > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 新增/修改外出进修登记弹框
     */
    @RequestMapping(value = "showEdit")
    public String showEdit(String recordFlow, Model model) {
        if (StringUtil.isNotBlank(recordFlow)) {
            StuFurtherStudyRegist stuFurtherStudyRegist = furtherStudyRegistBiz.readRegist(recordFlow);
            model.addAttribute("studyRegist", stuFurtherStudyRegist);
        }
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> speLst = dictBiz.searchDictList(sysDict);
        SysUser sysUser = new SysUser();
        sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
        List<SysUser> userList = userBiz.searchUser(sysUser);
        model.addAttribute("userList", userList);
        model.addAttribute("dwjxSpeLst", speLst);
        return "zseyjxres/hospital/editFurtherStudy";
    }

    /**
     * 外出进修查询
     *
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "furtherStudyQuery")
    public String furtherStudyQuery(Integer currentPage, Model model, StuFurtherStudyRegist stuFurtherStudyRegist, HttpServletRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> speLst = dictBiz.searchDictList(sysDict);
        model.addAttribute("dwjxSpeLst", speLst);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<StuFurtherStudyRegist> registList = furtherStudyRegistBiz.searchRegist(stuFurtherStudyRegist);
        model.addAttribute("registList", registList);
        return "zseyjxres/hospital/furtherStudyList";
    }

    /**
     * 招录概况
     */
    @RequestMapping("/orgRecruitInfo")
    public String orgRecruitInfo(Model model, String batchFlow, String isQuery) {
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        if (StringUtil.isBlank(batchFlow) && StringUtil.isBlank(isQuery)) {
            if (null != batchLst && batchLst.size() > 0) {
                for (StuBatch obj : batchLst) {
                    if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                        batchFlow = obj.getBatchFlow();
                        break;
                    }
                }
            }
        }
        model.addAttribute("batchFlow", batchFlow);

        List<SysDict> dictList = DictTypeEnum.sysListDictMap.get(DictTypeEnum.DwjxSpe.getId());//进修专业
        Map<String, Object> mp = new HashMap<String, Object>();
        Map<String, List<Integer>> recruitCountMap = new HashMap<String, List<Integer>>();//专业id — 录取人数，待录取人数，结业人数
        if (null != dictList && dictList.size() > 0) {
            for (SysDict rdr : dictList) {
                List<Integer> lst = new ArrayList<Integer>();
                mp.clear();
                mp.put("batchFlow", batchFlow);//批次
                mp.put("speId", rdr.getDictId());//专业
                mp.put("statusId", StuStatusEnum.Recruitted.getId());//待报到
                mp.put("isPublish", GlobalConstant.FLAG_Y);
                List<StuUserExt> dlq = stuUserBiz.searchStuUser(mp);
                int passedCount = dlq == null ? 0 : dlq.size();//待报到人数

                List<String> statuss = new ArrayList<>();
                statuss.add(StuStatusEnum.Admited.getId());
                statuss.add(StuStatusEnum.Graduation.getId());
                statuss.add(StuStatusEnum.DelayGraduation.getId());
                mp.put("status", statuss);//录取
                mp.put("statusId", "");
                List<StuUserExt> lq = stuUserBiz.searchStuUser(mp);
                int admitedCount = lq == null ? 0 : lq.size();//录取人数


                mp.put("status", null);//录取
                mp.put("statusId", StuStatusEnum.Graduation.getId());//结业
                mp.put("isGraduation", GlobalConstant.FLAG_Y);//录取状态且结业标识
                List<StuUserExt> jy = stuUserBiz.searchStuUser(mp);
                int graduationCount = jy == null ? 0 : jy.size();//结业人数

                statuss = new ArrayList<>();
                statuss.add(StuStatusEnum.DelayGraduation.getId());
                mp.put("status", null);//录取
                mp.put("statusId", StuStatusEnum.DelayGraduation.getId());//结业
                mp.put("isGraduation", "");//结业
                List<StuUserExt> yqjy = stuUserBiz.searchStuUser(mp);
                int delayGraduationCount = yqjy == null ? 0 : yqjy.size();//结业人数
                lst.add(admitedCount);//录取人数(加上结业人数)
                lst.add(passedCount);
                lst.add(graduationCount);
                lst.add(delayGraduationCount);
                recruitCountMap.put(rdr.getDictId(), lst);
            }
        }
        model.addAttribute("recruitCountMap", recruitCountMap);
        return "zseyjxres/hospital/orgRecruitInfo";
    }

    /**
     *
     */
    @RequestMapping("/queryStuMain")
    public String queryStuMain(Model model) {
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
        return "zseyjxres/hospital/queryStu/queryStuMain";
    }


    /**
     * 胸牌导出
     */
    @RequestMapping(value = "/chestCardExport")
    public void chestCardExport(String sexId, String startAge, String endAge, String titleTypeId, String titleId, String certifiedTypeId, String speId,
                                String stuTimeId, String isAudit, String maxEduId, String hospitalLevelId, String isAdmited, String isRecruit,
                                String userName, String batchFlow, HttpServletRequest req, HttpServletResponse res) throws Exception {

        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("sexId", sexId);//批次
        parMp.put("startAge", startAge);//年龄
        parMp.put("endAge", endAge);//年龄
        parMp.put("titleTypeId", titleTypeId);//职称类别
        parMp.put("titleId", titleId);//职称
        parMp.put("certifiedTypeId", certifiedTypeId);//执业类别
        parMp.put("stuTimeId", stuTimeId);//进修时间
        parMp.put("isAudit", isAudit);//审核情况
        parMp.put("maxEduId", maxEduId);//最高学历
        parMp.put("hospitalLevelId", hospitalLevelId);//医院等级
        parMp.put("isAdmited", isAdmited);//是否报道
        parMp.put("isRecruit", isRecruit);//是否录取
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        List<StuUserExt> stuUserLst = stuUserBiz.queryStuList(parMp);


        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        Map<String, Object> dataMap1 = new HashMap<String, Object>();
        StuBatch stuBatch = stuBatchBiz.searchStuBatch(batchFlow);
        if (stuBatch != null)
            dataMap1.put("stuBatName", stuBatch.getBatchNo());    //批次名称
        WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
        String path1 = "/jsp/zseyjxres/print/batchStudent/title.docx";//头模板
        ServletContext context = req.getServletContext();
        String watermark = "";
        temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap1, watermark, true);
        addTemplates.add(temeplete1);

        if (stuUserLst != null && stuUserLst.size() > 0) {
            Map<String, Object> objMap = new HashMap<String, Object>();
            for (int i = 0; i < stuUserLst.size(); i++) {
                StuUserExt tempUserExt = stuUserLst.get(i);
                if (i % 2 == 0)
                    objMap = new HashMap<String, Object>();

                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                String userName2 = extInfo.getUserName();
                String speName = tempUserExt.getSpeName();
                String stuTimeName = tempUserExt.getStuTimeName();
                String headImg = InitConfig.getSysCfg("upload_base_url") + "/" + extInfo.getUserHeadImg();
                headImg = "<img  src='" + headImg + "' width='80' height='90'  alt='证件照'/>";
                if (i % 2 == 0) {
                    objMap.put("userName", userName2);    //姓名
                    objMap.put("speName", speName);//进修专业
                    objMap.put("stuTimeName", stuTimeName);    //进修时间
                    objMap.put("userHeadImg", headImg);    //证件照
                } else {
                    objMap.put("userName2", userName2);    //姓名
                    objMap.put("speName2", speName);//进修专业
                    objMap.put("stuTimeName2", stuTimeName);    //进修时间
                    objMap.put("userHeadImg2", headImg);    //证件照
                }
                if (i % 2 == 1) {
                    temeplete1 = new WordprocessingMLPackage();
                    path1 = "/jsp/zseyjxres/print/batchStudent/info.docx";//模板
                    context = req.getServletContext();
                    temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                    addTemplates.add(temeplete1);
                    objMap = new HashMap<>();
                }
            }
            if (objMap != null && objMap.size() > 0 && objMap.size() <= 4) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/zseyjxres/print/batchStudent/info2.docx";//模板
                context = req.getServletContext();
                temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                addTemplates.add(temeplete1);
            } else if (objMap != null && objMap.size() > 4) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/zseyjxres/print/batchStudent/info.docx";//模板
                context = req.getServletContext();
                temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                addTemplates.add(temeplete1);
            }
        }
        WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
        if (temeplete != null) {
            String stuBatName = "";
            if (stuBatch != null)
                stuBatName = stuBatch.getBatchNo();
            String name = stuBatName + "进修学员信息.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * 学员报到胸牌导出
     */
    @RequestMapping(value = "/stuChestCardExport")
    public void stuChestCardExport(String speId,String userName, String batchFlow, HttpServletRequest req, HttpServletResponse res) throws Exception {

        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        parMp.put("isAdmited", "Y");//是否报道
        List<StuUserExt> stuUserLst = stuUserBiz.queryStuList(parMp);


        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        Map<String, Object> dataMap1 = new HashMap<String, Object>();
        StuBatch stuBatch = stuBatchBiz.searchStuBatch(batchFlow);
        if (stuBatch != null)
            dataMap1.put("stuBatName", stuBatch.getBatchNo());    //批次名称
        WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
        String path1 = "/jsp/zseyjxres/print/batchStudent/title.docx";//头模板
        ServletContext context = req.getServletContext();
        String watermark = "";
        temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap1, watermark, true);
        addTemplates.add(temeplete1);

        if (stuUserLst != null && stuUserLst.size() > 0) {
            Map<String, Object> objMap = new HashMap<String, Object>();
            for (int i = 0; i < stuUserLst.size(); i++) {
                StuUserExt tempUserExt = stuUserLst.get(i);
                if (i % 2 == 0)
                    objMap = new HashMap<String, Object>();

                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                String userCode2 = tempUserExt.getSysUser().getUserCode();
                String userName2 = extInfo.getUserName();

//                String speName = tempUserExt.getSpeName();
//
//                String stuTimeName = tempUserExt.getStuTimeName();
//                String startDate=tempUserExt.getStuBatch().getBatchRegDate();
//                String endDate="";
//                if(StringUtil.isNotBlank(stuTimeName)){
//                    String num=stuTimeName.substring(0,1);
//                    endDate=DateUtil.addMonthForArrange(startDate,num,false);
//                }
//                stuTimeName=startDate+"至"+endDate;
                String speName ="";
                String setStuTimeName = "";
                List<String> beginEndList = new ArrayList<>();//学员进行起始时间List
                if(extInfo.getSpeFormList()!=null && extInfo.getSpeFormList().size()>0){
                    for(SpeForm speForm:extInfo.getSpeFormList()){
                        speName+=speForm.getSpeId()+",";
                        setStuTimeName+=speForm.getStuTimeId()+"个月,";
                        if(StringUtil.isNotBlank(speForm.getBeginDate())){
                            beginEndList.add(speForm.getBeginDate());
                        }
                        if(StringUtil.isNotBlank(speForm.getEndDate())){
                            beginEndList.add(speForm.getEndDate());
                        }
                    }
                }


                String startDate="";
                String endDate="";
                String stuStartAndEndTime="";

                if(null!=beginEndList && beginEndList.size()>0){
                    String dates[] = new String[beginEndList.size()];
                    beginEndList.toArray(dates);
                    //冒泡排序，取得最小值和最大值
                    for(int k=0;k<dates.length-1;k++){
                        for(int j=0;j<dates.length-1-i;j++){
                            if(dates[j].compareTo(dates[j+1])>0){
                                String temp=dates[j];
                                dates[j]=dates[j+1];
                                dates[j+1]=temp;
                            }
                        }
                    }
                    startDate = dates[0];
                    endDate = dates[dates.length-1];
                }
                stuStartAndEndTime=startDate+"至"+endDate;

//                eui.setSpeName(speName.substring(0,speName.length()-1));
//                eui.setStuTimeName(setStuTimeName.substring(0,setStuTimeName.length()-1));
//                eui.setStuStartAndEndTime(stuStartAndEndTime);


                String headImg = InitConfig.getSysCfg("upload_base_url") + "/" + extInfo.getUserHeadImg();
                headImg = "<img  src='" + headImg + "' width='80' height='90'  alt='证件照'/>";
                if (i % 2 == 0) {
                    objMap.put("userCode", userCode2);    //编号
                    objMap.put("userName", userName2);    //姓名
                    objMap.put("speName", speName.length()==0?"":speName.substring(0,speName.length()-1));//进修专业
                    objMap.put("stuTimeName", stuStartAndEndTime);    //进修时间
                    objMap.put("userHeadImg", headImg);    //证件照
                } else {
                    objMap.put("userCode2", userCode2);    //编号
                    objMap.put("userName2", userName2);    //姓名
                    objMap.put("speName2", speName.length()==0?"":speName.substring(0,speName.length()-1));//进修专业
                    objMap.put("stuTimeName2", stuStartAndEndTime);    //进修时间
                    objMap.put("userHeadImg2", headImg);    //证件照
                }
                if (i % 2 == 1) {
                    temeplete1 = new WordprocessingMLPackage();
                    path1 = "/jsp/zseyjxres/print/batchStudent/stuInfo.docx";//模板
                    context = req.getServletContext();
                    temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                    addTemplates.add(temeplete1);
                    objMap = new HashMap<>();
                }
            }
            if (objMap != null && objMap.size() > 0 && objMap.size() <= 5) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/zseyjxres/print/batchStudent/stuInfo2.docx";//模板
                context = req.getServletContext();
                temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                addTemplates.add(temeplete1);
            } else if (objMap != null && objMap.size() > 5) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/zseyjxres/print/batchStudent/stuInfo.docx";//模板
                context = req.getServletContext();
                temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                addTemplates.add(temeplete1);
            }
        }
        WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
        if (temeplete != null) {
            String stuBatName = "";
            if (stuBatch != null)
                stuBatName = stuBatch.getBatchNo();
            String name = stuBatName + "进修学员信息.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("UTF-8"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * 学员结业
     */
    @RequestMapping("/queryStuList")
    public String queryStuList(String sexId, String startAge, String endAge, String titleTypeId, String titleId, String certifiedTypeId, String speId,
                               String stuTimeId, String isAudit, String maxEduId, String hospitalLevelId, String isAdmited, String isRecruit,
                               Integer currentPage,HttpServletRequest request, String userName, String batchFlow, Model model, String isQuery) {
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
        PageHelper.startPage(currentPage, getPageSize(request));
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("sexId", sexId);//批次
        parMp.put("startAge", startAge);//年龄
        parMp.put("endAge", endAge);//年龄
        parMp.put("titleTypeId", titleTypeId);//职称类别
        parMp.put("titleId", titleId);//职称
        parMp.put("certifiedTypeId", certifiedTypeId);//执业类别
        parMp.put("stuTimeId", stuTimeId);//进修时间
        parMp.put("isAudit", isAudit);//审核情况
        parMp.put("maxEduId", maxEduId);//最高学历
        parMp.put("hospitalLevelId", hospitalLevelId);//医院等级
        parMp.put("isAdmited", isAdmited);//是否报道
        parMp.put("isRecruit", isRecruit);//是否录取
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        List<StuUserExt> stuUserLst = stuUserBiz.queryStuList(parMp);
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
        return "zseyjxres/hospital/queryStu/queryStuList";
    }


    /**
     * Clob类型转成String
     */
    private String ClobToString(Clob clob) throws SQLException, IOException {

        String reString = "";
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }

    /**
     *
     */
    @RequestMapping("/exportQueryStu")
    public void exportQueryStu(String sexId, String startAge, String endAge, String titleTypeId, String titleId, String certifiedTypeId, String speId,
                               String stuTimeId, String isAudit, String maxEduId, String hospitalLevelId, String isAdmited, String isRecruit,
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
        parMp.put("titleTypeId", titleTypeId);//职称类别
        parMp.put("titleId", titleId);//职称
        parMp.put("certifiedTypeId", certifiedTypeId);//职业类别
        parMp.put("stuTimeId", stuTimeId);//进修时间
        parMp.put("isAudit", isAudit);//是否审核通过
        parMp.put("maxEduId", maxEduId);//最高学历
        parMp.put("isRecruit", isRecruit);//是否录取
        parMp.put("hospitalLevelId", hospitalLevelId);//医院等级
        parMp.put("isAdmited", isAdmited);//是否报到
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业

        List<Map<String, Object>> stuUserLst = stuUserBiz.queryStuListForExport(parMp);
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
//				"userEmail:电子邮箱",
                "sendComName:选送单位",
                "hospitalLevelName:医院等级",
                "sendComAddress:选送单位详细地址",
//				"graduatedNo:毕业证编号",
                "qualifiedNo:医师资格证书编号",
                "certifiedNo:医师执业证书编号",
                "workResumeList:工作经历"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, stuUserLst, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    /**
     * 公告维护
     */
    @RequestMapping("/noticemanage")
    public String noticeManage(Integer currentPage, Model model) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, 5);
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.JX_NOTICE_SYS_ID);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos", infos);
        return "zseyjxres/hospital/notice";
    }

    /**
     * 科室信息查询
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "headInfo")
    public String headInfo(String deptFlow,String userName,String userCode,Integer currentPage,HttpServletRequest request ,Model model) {
        List<SysUser> sysUserList=null;
        String roleFlow = InitConfig.getSysCfg("res_head_role_jx_flow");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow",roleFlow);
        paramMap.put("wsId",GlobalConstant.RES_WS_ID);
        paramMap.put("orgFlow",ZSEY_ORG_FLOW);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("userName",userName);
        paramMap.put("userCode",userCode);

        PageHelper.startPage(currentPage, getPageSize(request));
        sysUserList = userBiz.searchUserWithRoleByJx(paramMap);
        model.addAttribute("sysUserList",sysUserList);

        return "zseyjxres/hospital/headInfo";
    }

    /**
     * 科室维护
     */
    @RequestMapping(value = "speMaintenance")
    public String speMaintenance(Integer currentPage,HttpServletRequest request ,Model model) {

        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL DESC");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysDict> speLst = sysDictMapper.selectByExample(example);
        model.addAttribute("dwjxSpeLst", speLst);
        return "zseyjxres/hospital/spe";
    }

    /**
     * 科室账号维护
     */
    @RequestMapping(value = "/headMaintenance")
    public String headMaintenance(SysUser user,Integer currentPage,HttpServletRequest request ,Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        String roleFlow = InitConfig.getSysCfg("res_head_role_flow");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser> userList = userBiz.headUserByOrgFlowAndRoleFlow(currentUser.getOrgFlow(),roleFlow,user);
        if(userList!=null && userList.size()>0){
            model.addAttribute("userList",userList);
        }
        return "zseyjxres/hospital/headAccount";
    }

    /**
     * 国籍维护
     */
    @RequestMapping(value = "/national")
    public String national(Integer currentPage,HttpServletRequest request ,Model model) {

        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.GzzyjxNational.getId())
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL DESC");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysDict> nationalList = sysDictMapper.selectByExample(example);
        model.addAttribute("nationalList", nationalList);
        return "zseyjxres/hospital/national";
    }
    /**
     * 新增/修改 国籍 弹框
     */
    @RequestMapping(value = "editNational")
    public String editNational(String dictFlow, Model model) {
        if (StringUtil.isNotBlank(dictFlow)) {
            SysDict dictNational = sysDictMapper.selectByPrimaryKey(dictFlow);
            model.addAttribute("dictNational", dictNational);
        }
        return "zseyjxres/hospital/editNational";
    }

    /**
     * 保存国籍信息
     * @param dictFlow
     * @param dictId
     * @param dictName
     * @param dictDesc
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveNational", method = {RequestMethod.POST})
    @ResponseBody
    public String saveNational(String dictFlow, String dictId, String dictName, String dictDesc,HttpServletRequest request) {
        int count = 0;
        SysDict dictNational = new SysDict();
        SysDictExample example = new SysDictExample();
        if (StringUtil.isBlank(dictFlow)) {
            dictNational.setDictId(dictId);
            example.createCriteria().andDictIdEqualTo(dictId).andDictTypeIdEqualTo(DictTypeEnum.GzzyjxNational.getId())
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            int n = sysDictMapper.countByExample(example);
            if (n > 0) {
                return "国籍编码存在重复，无法保存！";
            }
        }
        if (StringUtil.isNotBlank(dictFlow)) {
            dictNational.setDictFlow(dictFlow);
            dictNational.setDictId(dictId);
            dictNational.setDictName(dictName);
            dictNational.setDictDesc(dictDesc);
            count = sysDictMapper.updateByPrimaryKeySelective(dictNational);
        } else {
            example.clear();
            example.createCriteria().andDictIdEqualTo(dictId).andDictNameEqualTo(dictName)
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if (sysDictMapper.countByExample(example) == 0) {
                dictNational = new SysDict();
                dictNational.setDictFlow(PkUtil.getUUID());
                dictNational.setDictTypeId(DictTypeEnum.GzzyjxNational.getId());
                dictNational.setDictTypeName(DictTypeEnum.GzzyjxNational.getName());
                dictNational.setDictId(dictId);
                dictNational.setDictName(dictName);
                dictNational.setDictDesc(dictDesc);
                example.clear();
                example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.GzzyjxNational.getId())
                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                int serial = sysDictMapper.countByExample(example) + 1;
                dictNational.setOrdinal(serial);
                dictNational.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                dictNational.setCreateTime(DateUtil.getCurrDateTime());
                dictNational.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                dictNational.setModifyTime(DateUtil.getCurrDateTime());
                dictNational.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                count = sysDictMapper.insertSelective(dictNational);
            }
        }
//        if (StringUtil.isNotBlank(dictId) && StringUtil.isNotBlank(dictName)) {
//            stuUserResumeBiz.updateSpeNameById(dictId, dictName);
//            stuUserResumeBiz.updateUserDeptNameById(dictId, dictName);
//            stuUserResumeBiz.updateRegistDeptNameById(dictId, dictName);
//        }
        if (count > 0) {
            InitConfig.refreshDict(request.getServletContext());
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 新增/修改弹框
     */
    @RequestMapping(value = "editSpe")
    public String editSpe(String dictFlow, Model model) {
        if (StringUtil.isNotBlank(dictFlow)) {
            SysDict dictSpe = sysDictMapper.selectByPrimaryKey(dictFlow);
            model.addAttribute("dictSpe", dictSpe);
        }
        return "zseyjxres/hospital/editSpe";
    }

    /**
     * 新增/修改弹框
     */
    @RequestMapping(value = "/editHead")
    public String editHead(String userFlow,Model model) {
        if(StringUtil.isNotBlank(userFlow)){
            SysUser user = userBiz.readSysUser(userFlow);
            model.addAttribute("user",user);
        }
        return "zseyjxres/hospital/editHead";
    }

    /**
     * 保存科室人员
     * @return
     */
    @RequestMapping(value = "/saveHeadAccount",method = {RequestMethod.POST})
    @ResponseBody
    public String saveHeadAccount(SysUser user){
        SysUser currentUser = GlobalContext.getCurrentUser();
        user.setOrgFlow(currentUser.getOrgFlow());
        user.setOrgName(currentUser.getOrgName());
        setSessionAttribute(GlobalConstant.CURRENT_WS_ID, GlobalConstant.RES_WS_ID);
        user.setDeptName(DictTypeEnum.DwjxSpe.getDictNameById(user.getDeptFlow()));
        String roleFlow = InitConfig.getSysCfg("res_head_role_flow"); //科主任
        int result = userBiz.saveUser(user,roleFlow);
        if(result>GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 保存
     */
    @RequestMapping(value = "/saveSpe", method = {RequestMethod.POST})
    @ResponseBody
    public String saveSpe(String dictFlow, String dictId, String dictName, String dictDesc,HttpServletRequest request) {
        int count = 0;
        SysDict dictSpe = new SysDict();
        SysDictExample example = new SysDictExample();
        if (StringUtil.isBlank(dictFlow)) {
            dictSpe.setDictId(dictId);
            example.createCriteria().andDictIdEqualTo(dictId).andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            int n = sysDictMapper.countByExample(example);
            if (n > 0) {
                return "科室编码存在重复，无法保存！";
            }
        }
        if (StringUtil.isNotBlank(dictFlow)) {
            dictSpe.setDictFlow(dictFlow);
            dictSpe.setDictId(dictId);
            dictSpe.setDictName(dictName);
            dictSpe.setDictDesc(dictDesc);
            count = sysDictMapper.updateByPrimaryKeySelective(dictSpe);
        } else {
            example.clear();
            example.createCriteria().andDictIdEqualTo(dictId).andDictNameEqualTo(dictName)
                    .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if (sysDictMapper.countByExample(example) == 0) {
                dictSpe = new SysDict();
                dictSpe.setDictFlow(PkUtil.getUUID());
                dictSpe.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
                dictSpe.setDictTypeName(DictTypeEnum.DwjxSpe.getName());
                dictSpe.setDictId(dictId);
                dictSpe.setDictName(dictName);
                dictSpe.setDictDesc(dictDesc);
                example.clear();
                example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                int serial = sysDictMapper.countByExample(example) + 1;
                dictSpe.setOrdinal(serial);
                dictSpe.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                dictSpe.setCreateTime(DateUtil.getCurrDateTime());
                dictSpe.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                dictSpe.setModifyTime(DateUtil.getCurrDateTime());
                dictSpe.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                count = sysDictMapper.insertSelective(dictSpe);
            }
        }
        if (StringUtil.isNotBlank(dictId) && StringUtil.isNotBlank(dictName)) {
            stuUserResumeBiz.updateSpeNameById(dictId, dictName);
            stuUserResumeBiz.updateUserDeptNameById(dictId, dictName);
            stuUserResumeBiz.updateRegistDeptNameById(dictId, dictName);
        }
        if (count > 0) {
            InitConfig.refreshDict(request.getServletContext());
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 刪除
     */
    @RequestMapping(value = "/delSpe", method = {RequestMethod.POST})
    @ResponseBody
    public String delSpe(String dictFlow, HttpServletRequest request) {
        int count = sysDictMapper.deleteByPrimaryKey(dictFlow);
        if (count > 0) {
            InitConfig.refreshDict(request.getServletContext());
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 删除科主任账号
     * @param userFlow
     *
     * @return
     */
    @RequestMapping(value = "/delHead", method = {RequestMethod.POST})
    @ResponseBody
    public String delHead(String userFlow) {
        int count= userBiz.deleteUser(userFlow);
        if (count > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 刷新
     */
    @RequestMapping(value = "refresh")
    @ResponseBody
    public String refresh(HttpServletRequest request) {
        InitConfig.refreshDict(request.getServletContext());
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }


    /**
     * 医院人员维护
     *
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "staffManage")
    public String staffManage(Integer currentPage,HttpServletRequest request, Model model, SysUser user4Search) {
        String isOrgAdmin = user4Search.getIsOrgAdmin();
        user4Search.setIsOwnerStu(GlobalConstant.FLAG_Y);
        if (StringUtil.isBlank(isOrgAdmin)) {
            isOrgAdmin = StuRoleEnum.Teacher.getId();
            user4Search.setIsOrgAdmin(isOrgAdmin);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        //查询本院人员
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("userName",user4Search.getUserName());
        paramMap.put("userCode",user4Search.getUserCode());
        paramMap.put("deptFlow",user4Search.getDeptFlow());
        paramMap.put("isOwnerStu",user4Search.getIsOwnerStu());
        paramMap.put("isOrgAdmin",user4Search.getIsOrgAdmin());
        List<Map<String,String>> userList = stuDoctorInfoBiz.searchUser(paramMap);
        //查询为本院人员维护的科室
        Map<String, List<SysDict>> deptForUser = new HashMap<>();
        for (Map<String,String> tempUserMap : userList) {
            SysUser tempUser = new SysUser();
            tempUser.setUserFlow(tempUserMap.get("userFlow"));
            List<SysDict> sysDicts = stuDoctorInfoBiz.searchDeptByUser(tempUser);
            deptForUser.put(tempUser.getUserFlow(), sysDicts);
        }
        model.addAttribute("isOrgAdmin", isOrgAdmin);
        model.addAttribute("userList", userList);
        model.addAttribute("deptForUser", deptForUser);
        return "zseyjxres/hospital/staffList";
    }

    /**
     * 新增/编辑医院人员弹框
     */
    @RequestMapping(value = "editStaff")
    public String editStaff(String userFlow, String roleId, Model model) {
        SysUser sysUser = null;
        if (StringUtil.isNotBlank(userFlow)) {
            sysUser = userBiz.readSysUser(userFlow);
            model.addAttribute("user", sysUser);
        }
        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL DESC");
        List<SysDict> speLst = sysDictMapper.selectByExample(example);
        if (StuRoleEnum.Secretary.getId().equals(roleId)) {
//          获取科室配置用于保证单个科室只能有一个教秘
            SysUser currentUser = GlobalContext.getCurrentUser();
            String orgFlow = currentUser.getOrgFlow();
            //tempUser需set机构流水号查当前机构，userFlow用于区分是否编辑的人员已经分配了科室
            SysUser tempUser = new SysUser();
            tempUser.setOrgFlow(currentUser.getOrgFlow());
            tempUser.setUserFlow(userFlow);
            stuDoctorInfoBiz.searchDeptOfUser(speLst, tempUser);
        }
        model.addAttribute("dwjxSpeLst", speLst);
        model.addAttribute("roleId", roleId);
//      查询该人员分配的科室
        if (sysUser != null) {
            List<SysDict> sysDicts = stuDoctorInfoBiz.searchDeptByUser(sysUser);
            Map<String,String> deptForUser = new HashMap<>();
            if(sysDicts != null && sysDicts.size() > 0){
                for(SysDict tempDict : sysDicts){
                    deptForUser.put(tempDict.getDictId(), GlobalConstant.FLAG_Y);
                }
            }
            model.addAttribute("deptForUser", deptForUser);
        }
        return "zseyjxres/hospital/editStaff";
    }

    /**
     * 保存医院人员
     */
    @RequestMapping(value = "/saveStaff", method = {RequestMethod.POST})
    @ResponseBody
    public String saveStaff(String userFlow, String[] deptFlow, String deptName, String userName,
                            String userCode, String userPhone, String isOrgAdmin) {
        if (StringUtil.isNotBlank(userCode)) {
            SysUser user = userBiz.findByUserCode(userCode);
            if (user != null && !user.getUserFlow().equals(userFlow)) {
                return "工号重复,添加失败！";
            }
        }
        int count = 0;
        SysUser sysUser = new SysUser();
        SysUser currentUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            sysUser.setUserFlow(userFlow);
            sysUser.setUserName(userName);
            sysUser.setUserCode(userCode);
            sysUser.setUserPhone(userPhone);
            sysUser.setIsOrgAdmin(isOrgAdmin);
            count = stuDoctorInfoBiz.saveUserAndDeptInfo(sysUser, deptFlow, currentUser);
        } else {
            sysUser.setUserFlow(userFlow);
            sysUser.setUserName(userName);
            sysUser.setUserCode(userCode);
            sysUser.setUserPhone(userPhone);
            sysUser.setIsOrgAdmin(isOrgAdmin);
            sysUser.setIsOwnerStu(GlobalConstant.FLAG_Y);
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            count = stuDoctorInfoBiz.saveUserAndDeptInfo(sysUser, deptFlow, currentUser);
        }
        if (count > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 医院人员维护刪除
     */
    @RequestMapping(value = "/delStaff", method = {RequestMethod.POST})
    @ResponseBody
    public String delStaff(String userFlow) {
        if(StringUtil.isBlank(userFlow)){
            return GlobalConstant.DELETE_FAIL;
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserFlow(userFlow);
        //需要同时将科室配置表置N
        int count = stuDoctorInfoBiz.removeUser(sysUser);
        if (count > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 医院人员维护导入
     */
    @RequestMapping(value = "/showImport")
    public String showImport() {
        return "zseyjxres/hospital/importStaff";
    }

    @RequestMapping(value = "importStaff")
    public
    @ResponseBody
    String importStaff(MultipartFile file) {
        if (file.getSize() > 0) {
            try {
                ExcelUtile result = (ExcelUtile) stuUserBiz.importStaffFromExcel(file);
                if (null != result) {
                    String code = (String) result.get("code");
                    int count = (Integer) result.get("count");
                    String msg = (String) result.get("msg");
                    if ("1".equals(code)) {
                        return GlobalConstant.UPLOAD_FAIL + msg;
                    } else {
                        if (GlobalConstant.ZERO_LINE != count) {
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        } else {
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                } else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException re) {
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 注册人员维护
     *
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "registManage")
    public String registManage(Integer currentPage, HttpServletRequest request,Model model, String idNo, String userName) {

        SysUser sysUser = new SysUser();
        if (StringUtil.isNotBlank(idNo)) {
            sysUser.setIdNo(idNo);
        }
        if (StringUtil.isNotBlank(userName)) {
            sysUser.setUserName(userName);
        }
        sysUser.setIsOwnerStu("P");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser> userList = userBiz.searchSysUserByLikeCode(sysUser);
        model.addAttribute("userList", userList);
        return "zseyjxres/hospital/registList";
    }

    /**
     * 报名批次设置
     */
    @RequestMapping(value = "searchSetting")
    public String searchSetting(String batchDate, Model model) {
        List<StuBatch> stuBatchLst = new ArrayList<StuBatch>();
        if (StringUtil.isNotBlank(batchDate)) {
            StuBatch stuBatch = stuBatchBiz.searchStuBatchByDate(batchDate);
            if (null != stuBatch) {
                stuBatchLst.add(stuBatch);
            }
        } else {
            stuBatchLst = stuBatchBiz.getStuBatchLst();
        }
        model.addAttribute("batchLst", stuBatchLst);
        return "zseyjxres/hospital/batchSetting";
    }


    /**
     * 系统默认进修批次设置
     */
    @RequestMapping("/defaultSet")
    @ResponseBody
    public String defaultSet(String batchFlow) {
        stuBatchBiz.changeDefaultSetting(batchFlow);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }


    /**
     * 新增/修改弹框
     */
    @RequestMapping(value = "edit")
    public String edit(String batchFlow, Model model) {
        if (StringUtil.isNotBlank(batchFlow)) {
            StuBatch stuBatch = stuBatchBiz.searchStuBatch(batchFlow);
            model.addAttribute("stuBatch", stuBatch);
        }
        return "zseyjxres/hospital/edit";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/saveSetting", method = {RequestMethod.POST})
    @ResponseBody
    public String saveSetting(String batchFlow, String registerTime, String fee, String batchDate, String batchStatus) {
        if (StringUtil.isBlank(batchDate)) {
            return "请选择进修批次！";
        }
        if (!batchDate.equals(DateUtil.transDateTime(registerTime, "yyyy-MM-dd", "yyyy-MM"))) {
            return "进修批次与报到时间不在同一月份，无法保存！";
        }
        if (StringUtil.isBlank(batchFlow) && StringUtil.isNotBlank(batchDate)) {
            StuBatch stuBatch = stuBatchBiz.searchStuBatchByDate(batchDate);
            if (stuBatch != null) {
                return "进修批次已存在，无法保存！";
            }
        }
        int count = stuBatchBiz.saveBatchSetting(batchFlow, registerTime, "0", batchDate, batchStatus);
        if (count > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 刪除
     */
    @RequestMapping(value = "/delSetting", method = {RequestMethod.POST})
    @ResponseBody
    public String delBatchSetting(String batchFlow) {
        int havaStu = stuBatchBiz.getStuNumByFlow(batchFlow);
        if (havaStu > 0) {
            return "该批次已有学员提交进修信息，无法删除！";
        }
        int count = stuBatchBiz.delBatchSetting(batchFlow);
        if (count > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
    /**
     * 管理员发放结业证书
     */
    @RequestMapping(value = "/issueCertificate", method = {RequestMethod.POST})
    @ResponseBody
    public String issueCertificate(String resumeFlow) {
        StuUserResume stuUserResume = stuUserResumeBiz.getStuUserByKey(resumeFlow);
        stuUserResume.setIssueCertificate(GlobalConstant.FLAG_Y);
        int result = stuUserResumeBiz.save(stuUserResume);
        if(result > GlobalConstant.ZERO_LINE){
            return GlobalConstant.OPERATE_SUCCESSED;
        }else {
            return GlobalConstant.OPERATE_FAIL;
        }
    }

    /**
     * 排班安排
     * */
    @RequestMapping(value = {"/rosteringHand" }, method = {RequestMethod.GET,RequestMethod.POST})
    public String rosteringHandList (String batchFlow,String userName,Model model){

        //进修批次号
        List<StuBatch> batchList = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchList", batchList);

        Map<String, Object> paramMp = new HashMap<String, Object>();
        paramMp.put("batchFlow",batchFlow); //批次号
        paramMp.put("userName",userName); //姓名
        paramMp.put("statusId",StuStatusEnum.Admited.getId()); //已报到状态

        List<StuUserExt> stuUserList = stuUserBiz.searchStuUser(paramMp);
        model.addAttribute("stuUserList", stuUserList);

        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserList != null && stuUserList.size() > 0) {
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserList) {
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }

        return "zseyjxres/hospital/rosteringHand";
    }


    /**
     * 加载排班数据
     * @param doctorFlow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/rosteringHandDept"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String rosteringHandDept(String doctorFlow,Model model){
        if(StringUtil.isNotBlank(doctorFlow)){
            ResDoctor doctor = doctorBiz.searchByUserFlow(doctorFlow);

            model.addAttribute("doctor",doctor);

            ScholarSchArrange arrange = new ScholarSchArrange();
            arrange.setDoctorFlow(doctorFlow);
            List<ScholarSchArrange> arrangeList = arrangeBiz.searchArrange(arrange);
            if(arrangeList.size()>0){
                model.addAttribute("arrangeList",arrangeList);
            }
        }
        return "zseyjxres/hospital/rosteringHandDept";
    }

    /**
     * 医院管理员新增/编辑
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(String doctorFlow,String doctorName,String arrangeFlow,Model model)throws IOException{
        if(StringUtil.isNotBlank(arrangeFlow)){
            ScholarSchArrange arrange = arrangeBiz.selectByArrangeFlow(arrangeFlow);
            model.addAttribute("arrange",arrange);
        }
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow = user.getOrgFlow();
        List<SysDept> deptList = sysDeptBiz.searchDeptByOrg(orgFlow);
        model.addAttribute("deptList",deptList); //医院科室
        model.addAttribute("doctorFlow",doctorFlow);
        model.addAttribute("doctorName",java.net.URLDecoder.decode(doctorName,"UTF-8"));

        return "zseyjxres/hospital/add";
    }

    /**
     * 根据不同的科室 加载科秘
     * @return
     */
    @RequestMapping("/loadKm")
    @ResponseBody
    public Object loadKm(String deptFlow){
        SysUser user =null;
        String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
        if(StringUtil.isNotBlank(secretaryRoleFlow)){
            List<SysUser> secretaryList = userBiz.searchUserByDeptAndRole(deptFlow,secretaryRoleFlow);
            if(secretaryList.size()>0){
                user = secretaryList.get(0);
            }
        }
        return user;
    }

    /**
     * 保存进修生轮转安排数据
     * @param arrange
     * @return
     */
    @RequestMapping("/save")



    @ResponseBody
    public String save(ScholarSchArrange arrange){
        if(StringUtil.isNotBlank(arrange.getDoctorFlow())){
            SysUser user = userBiz.readSysUser(arrange.getDoctorFlow());
            arrange.setDoctorName(user.getUserName());
            int count = arrangeBiz.save(arrange);
            if(count>GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除
     * @param arrangeFlows
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public String del(String arrangeFlows){
        String  arrangeFlow[] = arrangeFlows.split(",");
        List<String> arrageFlowList = Arrays.asList(arrangeFlow);
        int count = arrangeBiz.delbyFlows(arrageFlowList);
        if(count>GlobalConstant.ZERO_LINE){
            return   GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
//    @RequestMapping(value = "/operRole", method = {RequestMethod.POST})
//    @ResponseBody
//    public String operRole(String role, String userFlow) {
//        SysUser sysUser = userBiz.readSysUser(userFlow);
//        sysUser.setIsOrgAdmin(role);
//        int result = stuDoctorInfoBiz.updateUserByExp(sysUser);
//        if(result ==  GlobalConstant.ZERO_LINE){
//            return GlobalConstant.OPERATE_FAIL;
//        }else {
//            return GlobalConstant.OPRE_SUCCESSED;
//        }
//    }
}


