package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjLeaveBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.xjgl.XjNyqjApplyTypeEnum;
import com.pinde.sci.enums.xjgl.XjNyqjStatusEnum;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/xjgl/leave")
public class XjglLeaveController extends GeneralController {
    private static int SEVEN =7;
    private static int THREE =3;
    private static String DOCTOR="doctor";
    private static String PYDW="pydw";
    private static String YJSB="yjsb";
    private static String FIRST="first";
    private static String SECOND="second";
    private static String NFYKDX="南方医科大学";
    @Autowired
    private IXjLeaveBiz leaveBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private IOrgBiz orgBiz;

    @RequestMapping("/leaveInfosByStu")
    public String leaveInfosByStu(String applyTypeId, Integer currentPage, Model model, HttpServletRequest request ){
        NyqjLeaveMain nyqjLeaveMain=new NyqjLeaveMain();
        nyqjLeaveMain.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        nyqjLeaveMain.setApplyTypeId(applyTypeId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<NyqjLeaveMain> leaveList=leaveBiz.searchNyqjLeaveList(nyqjLeaveMain);
        model.addAttribute("leaveList",leaveList);
        return "/xjgl/leave/leaveManage";
    }

    @RequestMapping("/showApplyInfo")
    public String showApplyInfo(String recordFlow,String flag,Model model){
        SysUser user=GlobalContext.getCurrentUser();
        EduUser eduUser=eduUserBiz.findByFlow(user.getUserFlow());
        ResDoctor resDoctor=resDoctorBiz.findByFlow(user.getUserFlow());
        NyqjLeaveMain leaveMain=new NyqjLeaveMain();
        if(StringUtil.isNotBlank(recordFlow)){
            leaveMain=leaveBiz.searchNyqjLeaveByFlow(recordFlow);
        }
        model.addAttribute("flag",flag);
        model.addAttribute("sysUser",user);
        model.addAttribute("eduUser",eduUser);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("leaveMain",leaveMain);
        return "/xjgl/leave/applyInfo";
    }

    @RequestMapping("/saveApply")
    @ResponseBody
    public String saveApply(NyqjLeaveMain nyqjLeaveMain){
        SysUser user=GlobalContext.getCurrentUser();
        nyqjLeaveMain.setUserFlow(user.getUserFlow());
        nyqjLeaveMain.setUserName(user.getUserName());
        nyqjLeaveMain.setApplyTypeName(XjNyqjApplyTypeEnum.getNameById(nyqjLeaveMain.getApplyTypeId()));
        nyqjLeaveMain.setApplyFlag(GlobalConstant.FLAG_Y);
        nyqjLeaveMain.setApplyTime(DateUtil.getCurrDate());
        String leaveTime=nyqjLeaveMain.getLeaveTime();
        String startTime="";
        String endTime="";
        //判断是否需要研究生工作部审核
        if(StringUtil.isNotBlank(leaveTime)){
            startTime=leaveTime.split("/")[0];
            endTime=leaveTime.split("/")[1];
            nyqjLeaveMain.setLeaveTime(startTime+"至"+endTime);
            List<Date> dateList=DateUtil.getBetweenDates(DateUtil.parseDate(startTime,"yyyy-MM-dd"),DateUtil.parseDate(endTime,"yyyy-MM-dd"));
            if(dateList!=null&&dateList.size()>0){
                int daysOfLeave=dateList.size();
                if(dateList.size()==2&&dateList.get(0).equals(dateList.get(1))){
                    daysOfLeave=1;
                }
                nyqjLeaveMain.setDaysOfLeave(daysOfLeave+"");
                if(XjNyqjApplyTypeEnum.SickLeave.getId().equals(nyqjLeaveMain.getApplyTypeId())){
                    if(daysOfLeave>SEVEN){
                        nyqjLeaveMain.setYjsbmAuditFlag(GlobalConstant.FLAG_Y);
                    }
                }else if(XjNyqjApplyTypeEnum.PrivateLeave.getId().equals(nyqjLeaveMain.getApplyTypeId())){
                    if(daysOfLeave>THREE){
                        nyqjLeaveMain.setYjsbmAuditFlag(GlobalConstant.FLAG_Y);
                    }
                }
            }
        }
        //绑定导师
        EduUser eduUser=eduUserBiz.findByFlow(user.getUserFlow());
        if(eduUser!=null){
            if(StringUtil.isNotBlank(eduUser.getFirstTeacherFlow())){
                nyqjLeaveMain.setDoctorFlow(eduUser.getFirstTeacherFlow());
                nyqjLeaveMain.setDoctorAuditStatusId(XjNyqjStatusEnum.Passing.getId());
                nyqjLeaveMain.setDoctorAuditStatusName(XjNyqjStatusEnum.Passing.getName());
            }
            if(StringUtil.isNotBlank(eduUser.getSecondTeacherFlow())){
                nyqjLeaveMain.setSecondDoctorFlow(eduUser.getSecondTeacherFlow());
                nyqjLeaveMain.setSecondAuditStatusId(XjNyqjStatusEnum.Passing.getId());
                nyqjLeaveMain.setSecondAuditStatusName(XjNyqjStatusEnum.Passing.getName());
            }
        }
        int num=leaveBiz.saveNyqjLeaveMain(nyqjLeaveMain);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 取消申请
     * @param recordFlow
     * @return
     */
    @RequestMapping("/applyCancel")
    @ResponseBody
    public String applyCancel(String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)){
            int num=leaveBiz.delLeave(recordFlow);
            if(num>0){
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping("/leavesAudit")
    public String leavesAudit(String role,NyqjLeaveMain leaveMain,Integer currentPage,HttpServletRequest request, Model model){
        String leaderFlag="";
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(DOCTOR.equals(role)){
            leaveMain.setDoctorFlow(sysUser.getUserFlow());
            leaveMain.setSecondDoctorFlow(sysUser.getUserFlow());
            model.addAttribute("doctorFlow",sysUser.getUserFlow());
        }else if(PYDW.equals(role)){
            leaveMain.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(YJSB.equals(role)){
            List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
            if(userRoles!=null&&userRoles.size()>0){
                for (SysRole sr:userRoles) {
                    if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_leader_role_flow"))){
                        leaderFlag=GlobalConstant.FLAG_Y;
                    }
                }
            }
            if(GlobalConstant.FLAG_Y.equals(leaderFlag)){
                leaveMain.setLeaderOrgFlow(sysUser.getOrgFlow());
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NyqjLeaveMain> leaveList=leaveBiz.searchLeaveApplyList(leaveMain);
        model.addAttribute("leaveList",leaveList);
        model.addAttribute("role",role);
        model.addAttribute("leaderFlag",leaderFlag);
        return "/xjgl/leave/leavesAudit";
    }
    /**
     * 销假操作
     * @param recordFlow
     * @return
     */
    @RequestMapping("/sickLeaveSzk")
    @ResponseBody
    public String sickLeaveSzk(String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)){
            int num=0;
            NyqjLeaveMain leaveMain =leaveBiz.searchNyqjLeaveByFlow(recordFlow);
            if(leaveMain!=null){
                leaveMain.setSickLeaveFlag(GlobalConstant.FLAG_Y);
                num=leaveBiz.saveNyqjLeaveMain(leaveMain);
            }
            if(num>0){
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 请假申请审核界面
     */
    @RequestMapping("/auditLeave")
    public String auditLeave() {
        return "xjgl/leave/auditLeave";
    }

    /**
     * 请假审核
     */
    @RequestMapping(value="/saveAuditOption")
    @ResponseBody
    public String saveAuditOption(String role,String recordFlow,String auditFlag,String doctorFlag,String advice,String leaderFlag){
        SysOrg sysOrg=orgBiz.readSysOrgByName(NFYKDX);
        NyqjLeaveMain main = new NyqjLeaveMain();
        NyqjLeaveMain exitMain = new NyqjLeaveMain();
        main.setRecordFlow(recordFlow);
        exitMain = leaveBiz.searchNyqjLeaveByFlow(recordFlow);
        if(DOCTOR.equals(role)){
            String auditStatusId = "";
            if(FIRST.equals(doctorFlag)){
                main.setDoctorAuditStatusId(auditFlag);
                main.setDoctorAuditStatusName(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)?XjNyqjStatusEnum.Passed.getName():XjNyqjStatusEnum.UnPassed.getName());
                main.setDoctorAuditAdvice(advice);
                main.setDoctorAuditTime(DateUtil.getCurrentTime());
                //导师二审核状态（不存在则默认审核通过）
                auditStatusId = StringUtil.isBlank(exitMain.getSecondDoctorFlow())?XjNyqjStatusEnum.Passed.getId():exitMain.getSecondAuditStatusId();
            }else if(SECOND.equals(doctorFlag)){
                main.setSecondAuditStatusId(auditFlag);
                main.setSecondAuditStatusName(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)?XjNyqjStatusEnum.Passed.getName():XjNyqjStatusEnum.UnPassed.getName());
                main.setSecondAuditAdvice(advice);
                main.setSecondAuditTime(DateUtil.getCurrentTime());
                //导师一审核状态（不存在则默认审核通过）
                auditStatusId = StringUtil.isBlank(exitMain.getDoctorFlow())?XjNyqjStatusEnum.Passed.getId():exitMain.getDoctorAuditStatusId();
            }
            //需要审核的导师都已审核通过，则初始化培养单位审核状态
            if(XjNyqjStatusEnum.Passed.getId().equals(auditFlag) && XjNyqjStatusEnum.Passed.getId().equals(auditStatusId)){
                main.setPydwAuditStatusId(XjNyqjStatusEnum.Passing.getId());
                main.setPydwAuditStatusName(XjNyqjStatusEnum.Passing.getName());
                ResDoctor resDoctor=resDoctorBiz.findByFlow(exitMain.getUserFlow());
                if(resDoctor!=null){
                    main.setPydwOrgFlow(resDoctor.getOrgFlow());
                }
            }
        }else if(PYDW.equals(role)){
            main.setPydwAuditStatusId(auditFlag);
            main.setPydwAuditStatusName(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)?XjNyqjStatusEnum.Passed.getName():XjNyqjStatusEnum.UnPassed.getName());
            main.setPydwAuditAdvice(advice);
            main.setPydwAuditTime(DateUtil.getCurrentTime());
            if(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)){
                if(GlobalConstant.FLAG_Y.equals(exitMain.getYjsbmAuditFlag())){
                    if(sysOrg!=null&&StringUtil.isNotBlank(sysOrg.getOrgFlow())){
                        main.setSzkOrgFlow(sysOrg.getOrgFlow());
                    }
                    main.setSzkAuditStatusId(XjNyqjStatusEnum.Passing.getId());
                    main.setSzkAuditStatusName(XjNyqjStatusEnum.Passing.getName());
                }else{
                    main.setSickLeaveFlag(GlobalConstant.FLAG_N);
                }
            }
        }else if(YJSB.equals(role)){
            if(!GlobalConstant.FLAG_Y.equals(leaderFlag)){
                main.setSzkAuditStatusId(auditFlag);
                main.setSzkAuditStatusName(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)?XjNyqjStatusEnum.Passed.getName():XjNyqjStatusEnum.UnPassed.getName());
                main.setSzkAuditAdvice(advice);
                main.setSzkAuditTime(DateUtil.getCurrentTime());
                if(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)){
                    if(sysOrg!=null&&StringUtil.isNotBlank(sysOrg.getOrgFlow())){
                        main.setLeaderOrgFlow(sysOrg.getOrgFlow());
                    }
                    main.setLeaderAuditStatusId(XjNyqjStatusEnum.Passing.getId());
                    main.setLeaderAuditStatusName(XjNyqjStatusEnum.Passing.getName());
                }
            }else{
                main.setLeaderAuditStatusId(auditFlag);
                main.setLeaderAuditStatusName(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)?XjNyqjStatusEnum.Passed.getName():XjNyqjStatusEnum.UnPassed.getName());
                main.setLeaderAuditAdvice(advice);
                main.setLeaderAuditTime(DateUtil.getCurrentTime());
                if(XjNyqjStatusEnum.Passed.getId().equals(auditFlag)){
                    main.setSickLeaveFlag(GlobalConstant.FLAG_N);
                }
            }
        }
        int result = leaveBiz.saveNyqjLeaveMain(main);
        if(result>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 学生-请假申请导出
     * @param request
     * @param response
     * @param recordFlow
     * @throws Exception
     */
    @RequestMapping(value="/exportLeaveInfo")
    public void exportLeaveInfo(HttpServletRequest request, HttpServletResponse response, String recordFlow)throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        NyqjLeaveMain leaveMain=leaveBiz.searchNyqjLeaveByFlow(recordFlow);
        if (leaveMain != null ) {
            dataMap.put("name", leaveMain.getUserName());
            dataMap.put("college", leaveMain.getCollegeName());
            dataMap.put("major", leaveMain.getMajorName());
            dataMap.put("id", leaveMain.getStudentId());
            dataMap.put("leaveReason", leaveMain.getLeaveReason());
            dataMap.put("leaveTime", leaveMain.getLeaveTime());
            dataMap.put("destination", leaveMain.getDestination());
            dataMap.put("linkPhone", leaveMain.getLinkPhone());
            if(StringUtil.isNotBlank(leaveMain.getDoctorFlow())&&StringUtil.isBlank(leaveMain.getSecondDoctorFlow())){
                dataMap.put("doctorAuditAdvice",leaveMain.getDoctorAuditAdvice());
            }
            if(StringUtil.isBlank(leaveMain.getDoctorFlow())&&StringUtil.isNotBlank(leaveMain.getSecondDoctorFlow())){
                dataMap.put("doctorAuditAdvice",leaveMain.getSecondAuditAdvice());
            }
            if(StringUtil.isNotBlank(leaveMain.getDoctorFlow())&&StringUtil.isNotBlank(leaveMain.getSecondDoctorFlow())){
                dataMap.put("doctorAuditAdvice","导师一："+leaveMain.getDoctorAuditAdvice()+"； 导师二："+leaveMain.getSecondAuditAdvice());
            }
            if(StringUtil.isNotBlank(leaveMain.getSickLeaveFlag())&&GlobalConstant.FLAG_Y.equals(leaveMain.getSickLeaveFlag())){
                dataMap.put("modifyTime", DateUtil.transDate(leaveMain.getModifyTime()));
            }
            dataMap.put("pydwAuditAdvice", leaveMain.getPydwAuditAdvice());
            dataMap.put("szkAuditAdvice", leaveMain.getSzkAuditAdvice());
            dataMap.put("leaderAuditAdvice", leaveMain.getLeaderAuditAdvice());
        }
        ServletContext context = request.getServletContext();
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/xjgl/print/nyqjTemp.docx";
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,"",true);
        String name = "南方医科大学研究生请假申请表.docx";
        if(temeplete!=null){
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream ();
            (new SaveToZipFile (temeplete)).save (out);
            out.flush ();
        }
    }

    @RequestMapping("/exportLeaves")
    @ResponseBody
    public void exportLeaves(String role,NyqjLeaveMain leaveMain,HttpServletResponse response)throws Exception {
        String leaderFlag="";
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(DOCTOR.equals(role)){
            leaveMain.setDoctorFlow(sysUser.getUserFlow());
            leaveMain.setSecondDoctorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)){
            leaveMain.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(YJSB.equals(role)){
            List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
            if(userRoles!=null&&userRoles.size()>0){
                for (SysRole sr:userRoles) {
                    if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_leader_role_flow"))){
                        leaderFlag=GlobalConstant.FLAG_Y;
                    }
                }
            }
            if(GlobalConstant.FLAG_Y.equals(leaderFlag)){
                leaveMain.setLeaderOrgFlow(sysUser.getOrgFlow());
            }
        }
        List<NyqjLeaveMain> leaveList=leaveBiz.searchLeaveApplyList(leaveMain);
        List<NyqjLeaveMain> leaveListTemp=new ArrayList<>();
        for (NyqjLeaveMain nlm:leaveList) {
            if(GlobalConstant.FLAG_Y.equals(nlm.getYjsbmAuditFlag())){
                if(XjNyqjStatusEnum.Passed.getId().equals(nlm.getLeaderAuditStatusId())){
                    leaveListTemp.add(nlm);
                }
            }else {
                if(XjNyqjStatusEnum.Passed.getId().equals(nlm.getPydwAuditStatusId())){
                    leaveListTemp.add(nlm);
                }
            }
        }
        String []titles = new String[]{
                "userName:姓名",
                "collegeName:学院",
                "majorName:专业",
                "studentId:学号",
                "leaveReason:离校事由",
                "leaveTime:离校期限",
                "destination:去往地点",
                "linkPhone:联系方式"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, leaveListTemp, response.getOutputStream());
        String fileName = "请假申请信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
