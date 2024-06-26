package com.pinde.sci.ctrl.zseyjxres;

import com.alibaba.druid.support.json.JSONUtils;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.PdfUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.zseyjxres.IZseyScholarSchArrangeBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxDocSingupBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.enums.zseyjxres.UnitLevelEnum;
import com.pinde.sci.enums.zseyjxres.UnitPropertyEnum;
import com.pinde.sci.enums.zseyjxres.UnitRankEnum;
import com.pinde.sci.form.zseyjxres.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.SchProcessExt;
import com.pinde.sci.model.res.StuUserExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/zseyjxres/doctor")
public class ZseyjxDoctorController extends GeneralController {

    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IZseyScholarSchArrangeBiz arrangeBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IZseyjxDocSingupBiz docSingupBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;



    @RequestMapping("/home_en")
    public String home_en(Integer currentPage, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        String batchFlow = "";//进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();//取报名中数据
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {
                    batchFlow = obj.getBatchFlow();    //取报名中默认批次的数据的批次号
                    break;
                }
            }
            //若报名中默认批次的数据的批次号为空，则取报名中数据的第一个数据的批次号
            if (StringUtil.isBlank(batchFlow))
                batchFlow = batchLst.get(0).getBatchFlow();
        }
        //如果批次号为空，则查询全表
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);
        StuUserResume stuUser = null;
        //取第一条数据，并设置当前批次号为第一条数据的批次号
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            if (StringUtil.isBlank(batchFlow))
                batchFlow = stuUser.getStuBatId();
            model.addAttribute("stuUser", stuUser);//当前系统设置批次下的医师

            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUser.getResumeInfo());
            model.addAttribute("extInfo", extInfo);
        }
        model.addAttribute("batchFlow", batchFlow);
        //查询该学员是否有已报名记录且记录状态为（Admited，Graduation，DelayGraduation）；
        List<String> paramList = new ArrayList<>();
        paramList.add(StuStatusEnum.Admited.getId());
        paramList.add(StuStatusEnum.Graduation.getId());
        paramList.add(StuStatusEnum.DelayGraduation.getId());
        List<StuUserResume> showGraduationLst = stuUserResumeBiz.getShowGraduationLst(user.getUserFlow(), paramList);
        if (null != showGraduationLst && showGraduationLst.size() > 0) {
            model.addAttribute("showGraduation", "Y");//当前系统展示结业管理菜单
        }

        //读取系统消息
        PubMsgExample example = new PubMsgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
        example.setOrderByClause("MSG_TIME desc");
        PageHelper.startPage(currentPage, 10);
        List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
        if (msgList != null && msgList.size() > 0) {
            int newMsg = 0;
            for (PubMsg msg : msgList) {
                if (!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())) {
                    newMsg++;
                }
            }
            model.addAttribute("msgList", msgList);
            model.addAttribute("newMsg", newMsg);
        }
        return "zseyjxres/doctor/english_index";
    }


    @RequestMapping("/home")
    public String home(Integer currentPage, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        String batchFlow = "";//进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();//取报名中数据
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {
                    batchFlow = obj.getBatchFlow();    //取报名中默认批次的数据的批次号
                    break;
                }
            }
            //若报名中默认批次的数据的批次号为空，则取报名中数据的第一个数据的批次号
            if (StringUtil.isBlank(batchFlow))
                batchFlow = batchLst.get(0).getBatchFlow();
        }
        //如果批次号为空，则查询全表
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);
        StuUserResume stuUser = null;
        //取第一条数据，并设置当前批次号为第一条数据的批次号
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            if (StringUtil.isBlank(batchFlow))
                batchFlow = stuUser.getStuBatId();
            model.addAttribute("stuUser", stuUser);//当前系统设置批次下的医师

            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUser.getResumeInfo());
            model.addAttribute("extInfo", extInfo);
        }
        model.addAttribute("batchFlow", batchFlow);
        //查询该学员是否有已报名记录且记录状态为（Admited，Graduation，DelayGraduation）；
        List<String> paramList = new ArrayList<>();
        paramList.add(StuStatusEnum.Admited.getId());
        paramList.add(StuStatusEnum.Graduation.getId());
        paramList.add(StuStatusEnum.DelayGraduation.getId());
        List<StuUserResume> showGraduationLst = stuUserResumeBiz.getShowGraduationLst(user.getUserFlow(), paramList);
        if (null != showGraduationLst && showGraduationLst.size() > 0) {
            model.addAttribute("showGraduation", "Y");//当前系统展示结业管理菜单
        }

        //读取系统消息
        PubMsgExample example = new PubMsgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
        example.setOrderByClause("MSG_TIME desc");
        PageHelper.startPage(currentPage, 10);
        List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
        if (msgList != null && msgList.size() > 0) {
            int newMsg = 0;
            for (PubMsg msg : msgList) {
                if (!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())) {
                    newMsg++;
                }
            }
            model.addAttribute("msgList", msgList);
            model.addAttribute("newMsg", newMsg);
        }
        return "zseyjxres/doctor/index";
    }

    @RequestMapping("/msg")
    public String msg(Integer currentPage , Model model){
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage,10);
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.JX_NOTICE_SYS_ID);
        List<InxInfo> messages = this.noticeBiz.findNotice(info);
        model.addAttribute("messages",messages);
        return "zseyjxres/doctor/msg";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        if (!userRoleList.isEmpty()) {
            userRole = userRoleList.get(0);
            return "redirect:" + this.getRoleUrl(userRole.getRoleFlow());
        } else {
            if (StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().contains("2")) {
                return "redirect:/zseyjxres/secretaries/home";
            }
        }
        String loginErrorMessage = "未赋权";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return "inx/zseyjxres/login";
    }

    @RequestMapping("/index_en")
    public String index_en(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        if (!userRoleList.isEmpty()) {
            userRole = userRoleList.get(0);
            return "redirect:" + this.getRoleUrl_en(userRole.getRoleFlow());
        } else {
            if (StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().contains("2")) {
                return "redirect:/zseyjxres/secretaries/home";
            }
        }
        String loginErrorMessage = "未赋权";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return "inx/zseyjxres/englishLogin";
    }

    /**
     * 根据职称类别加载职称
     * @param titleTypeId
     * @return
     */
    @RequestMapping("/loadTitle")
    @ResponseBody
    public List<SysDict> loadTitle(@RequestParam(value = "titleTypeId", required = true) String titleTypeId){
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(titleTypeId);

        return sysDictList;
    }

    /**
     * 加载当前机构所属的部门
     * @return
     */
    @RequestMapping("/loadDept")
    @ResponseBody
    public  List<SysDept> loadDept(){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<SysDept> depts = null;
        if(StringUtil.isNotBlank(orgFlow)) {
            depts = deptBiz.searchDeptByOrg(orgFlow);
        }
        return depts;
    }

    /**
     *
     * @param fileFlow
     * @return
     */
    @RequestMapping("/checkFile")
    @ResponseBody
    public String checkFile(String fileFlow) {
        PubFile file = fileBiz.readFile(fileFlow);
        List<String> sufs = new ArrayList<>();
        //bmp,jpg,png,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF
        sufs.add("png");
        sufs.add("jpg");
        sufs.add("bmp");
        sufs.add("gif");
        if (sufs.contains(file.getFileSuffix().toLowerCase())) {
            return file.getFilePath();
        } else {
            return "1";
        }


    }

    /**
     * 学员上传成绩结业鉴定
     *
     * @param file
     * @param productFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadFile"})
    @ResponseBody
    public String uploadApplicationFile(MultipartFile file, String productFlow) throws Exception {
        if (file != null && !file.isEmpty()) {
            stuUserResumeBiz.saveStuFile(file, productFlow);
            return GlobalConstant.UPLOAD_SUCCESSED;
        }
        return "上传附件内容不能为空！";
    }

    //下载附件
    @RequestMapping(value = {"/fileDown"}, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
        PubFile file = this.fileBiz.readFile(fileFlow);
        fileBiz.downPubFile(file, response);
    }

    public String getRoleUrl(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
            if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                return "/zseyjxres/hospital/home";
//			} else if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//系统管理员即省级管理员
//				return "/gzzyjxres/manage/home";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/zseyjxres/doctor/home";
            }else if(roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))){
                return "/zseyjxres/head/home";
            }
        }
        return "";
    }

    public String getRoleUrl_en(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
             if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/zseyjxres/doctor/home_en";
            }
        }
        return "";
    }

    /**
     * 英文版 网上报名
     * @param batchFlow
     * @param model
     * @return
     */
    @RequestMapping("/singup_en")
    public String singup_en(String batchFlow, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        user = this.userBiz.readSysUser(user.getUserFlow());
        model.addAttribute("user", user);
        String batchDate = "";
        //当前系统默认 进修批次流水号
        StuBatch sb = stuBatchBiz.getDefualBatch();
        if (sb == null) {
            List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();
            if (null != batchLst && batchLst.size() > 0) {
                sb = batchLst.get(0);
            }
        }
        if (sb != null) {
            if (StringUtil.isBlank(batchFlow)) {
                batchFlow = sb.getBatchFlow();
            }
            batchDate = sb.getBatchDate();
        }

        //获取比默认批次大的所有批次并且学员提交过审核的批次
        //只有报名中
        List<StuBatch> batchLst = stuBatchBiz.getDefualStuBatchAndStuSelect(user.getUserFlow()/*,batchDate*/);
        model.addAttribute("batchLst", batchLst);
        //选中的批次
        StuBatch selectBatch = stuBatchBiz.searchStuBatch(batchFlow);

        model.addAttribute("recordLst", stuUserResumeBiz.getStuUserLst(user.getUserFlow(), null));
        String bid = batchFlow;
        model.addAttribute("batchFlow", bid);
        model.addAttribute("batchNo", selectBatch.getBatchNo());
        model.addAttribute("batchId", bid);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), bid);
        StuUserResume stuUser = null;
        ExtInfoForm extInfo = null;
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            stuUser.setStuBatName(stuUser.getStuBatName().replace("年","-").replace("月",""));
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师
            extInfo = docSingupBiz.parseExtInfoXml(doctorLst.get(0).getResumeInfo());
            model.addAttribute("extInfo", extInfo);
            List<String> eduDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getEducationList()!=null){
                for(EducationForm eForm:extInfo.getEducationList()){
                    eduDateList.add(eForm.getEduRoundDate());
                }
            }
            Collections.sort(eduDateList);
            Collections.reverse(eduDateList);
            model.addAttribute("eduDateList", eduDateList);
            List<String> workDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                    workDateList.add(eForm.getClinicalRoundDate());
                }
            }
            Collections.sort(workDateList);
            Collections.reverse(workDateList);
            model.addAttribute("workDateList", workDateList);
            if (StuStatusEnum.Passed.getId().equals(doctorLst.get(0).getStuStatusId())
                    || StuStatusEnum.Admited.getId().equals(doctorLst.get(0).getStuStatusId())) {
                return "zseyjxres/doctor/singupinfo_en";//查看
            }
        } else {
            List<StuUserResume> lst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);//默认批次
            if (null != lst && lst.size() > 0) {
                stuUser = lst.get(0);
                model.addAttribute("stuUser", lst.get(0));//当前系统设置批次下的医师
                extInfo = docSingupBiz.parseExtInfoXml(lst.get(0).getResumeInfo());
                extInfo.setStudyAim("");
                extInfo.setVocationalLevel("");
                model.addAttribute("extInfo", extInfo);
                List<String> eduDateList=new ArrayList<>();
                if(extInfo!=null&&extInfo.getEducationList()!=null){
                    for(EducationForm eForm:extInfo.getEducationList()){
                        eduDateList.add(eForm.getEduRoundDate());
                    }
                }
                Collections.sort(eduDateList);
                Collections.reverse(eduDateList);
                model.addAttribute("eduDateList", eduDateList);
                List<String> workDateList=new ArrayList<>();
                if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                    for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                        workDateList.add(eForm.getClinicalRoundDate());
                    }
                }
                Collections.sort(workDateList);
                Collections.reverse(workDateList);
                model.addAttribute("workDateList", workDateList);
            }
        }
        Boolean b = true;//用于判断第一页是否提交extInfo为null则未提交
        if (extInfo == null && user != null) {
            extInfo = new ExtInfoForm();
            //新注册的学员StuUserResume为空,页面中的学员基本信息是从StuUserResume中的resumeInfo（对应ExtInfoForm）即extInfo取的
            //页面要默认注册时的信息下一步是将SysUser中基本信息赋到extInfo
            updateExtInfoFromUser(user, extInfo);
            model.addAttribute("extInfo", extInfo);
            b = false;
        }
        /*if(sb==null||StringUtil.isBlank(sb.getBatchDate()) *//*|| selectBatch.getBatchDate().compareTo(sb.getBatchDate())>=0*//*	){*/
        if ((stuUser == null || !RegStatusEnum.Passed.getId().equals(stuUser.getStuStatusId()))) {
            if (extInfo != null) {
                //改成身份证
                if (StringUtil.isNotBlank(extInfo.getIdNo()) && b) {
                    model.addAttribute("formHaveSubmit1", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getCertifiedNo())) {
                    model.addAttribute("formHaveSubmit2", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getVocationalLevel())) {
                    model.addAttribute("formHaveSubmit3", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getRegisterFormUri())) {
                    model.addAttribute("registerFormUri", "Y");
                }
                if (stuUser != null) {
                    if (StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                            || GlobalConstant.FLAG_Y.equals(stuUser.getIsBack())
                            || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                        model.addAttribute("stuBatId", "Y");
                        List<StuAuditProcess> processes = stuUserResumeBiz.searchProcessByResumeFlow(stuUser.getResumeFlow());
                        if (processes != null && processes.size() > 0) {
                            StuAuditProcess process = processes.get(0);
                            if (StringUtil.isNotBlank(process.getAuditContent())) {
                                model.addAttribute("auditAgree", process.getAuditContent());
                            }
                        }
                    }
                }
            }
            if (stuUser != null) {
                if (StuStatusEnum.UnSubmit.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                    return "zseyjxres/doctor/singupMain_en";//当前系统默认批次及往后批次且未提交状态 可编辑
                }
            }
            if (stuUser == null) {
                return "zseyjxres/doctor/singupMain_en";//当前系统默认批次及往后批次且未提交状态 可编辑
            }
        }
//		/*}*/
        return "zseyjxres/doctor/singupinfo_en";//查看
    }

    @RequestMapping("/singup")
    public String singup(String batchFlow, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        user = this.userBiz.readSysUser(user.getUserFlow());
        model.addAttribute("user", user);
        String batchDate = "";
        //当前系统默认 进修批次流水号
        StuBatch sb = stuBatchBiz.getDefualBatch();
        if (sb == null) {
            List<StuBatch> batchLst = stuBatchBiz.getSingUpStuBatchLst();
            if (null != batchLst && batchLst.size() > 0) {
                sb = batchLst.get(0);
            }
        }
        if (sb != null) {
            if (StringUtil.isBlank(batchFlow)) {
                batchFlow = sb.getBatchFlow();
            }
            batchDate = sb.getBatchDate();
        }

        //获取比默认批次大的所有批次并且学员提交过审核的批次
        //只有报名中
        List<StuBatch> batchLst = stuBatchBiz.getDefualStuBatchAndStuSelect(user.getUserFlow()/*,batchDate*/);
        model.addAttribute("batchLst", batchLst);
        //选中的批次
        StuBatch selectBatch = stuBatchBiz.searchStuBatch(batchFlow);

        model.addAttribute("recordLst", stuUserResumeBiz.getStuUserLst(user.getUserFlow(), null));
        String bid = batchFlow;
        model.addAttribute("batchFlow", bid);
        model.addAttribute("batchNo", selectBatch.getBatchNo());
        model.addAttribute("batchId", bid);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), bid);
        StuUserResume stuUser = null;
        ExtInfoForm extInfo = null;
        if (null != doctorLst && doctorLst.size() > 0) {
            stuUser = doctorLst.get(0);
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师
            extInfo = docSingupBiz.parseExtInfoXml(doctorLst.get(0).getResumeInfo());
            model.addAttribute("extInfo", extInfo);
            List<String> eduDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getEducationList()!=null){
                for(EducationForm eForm:extInfo.getEducationList()){
                    eduDateList.add(eForm.getEduRoundDate());
                }
            }
            Collections.sort(eduDateList);
            Collections.reverse(eduDateList);
            model.addAttribute("eduDateList", eduDateList);
            List<String> workDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                    workDateList.add(eForm.getClinicalRoundDate());
                }
            }
            Collections.sort(workDateList);
            Collections.reverse(workDateList);
            model.addAttribute("workDateList", workDateList);
            if (StuStatusEnum.Passed.getId().equals(doctorLst.get(0).getStuStatusId())
                    || StuStatusEnum.Admited.getId().equals(doctorLst.get(0).getStuStatusId())) {
                return "zseyjxres/doctor/singupinfo";//查看
            }
        } else {
            List<StuUserResume> lst = stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);//默认批次
            if (null != lst && lst.size() > 0) {
                stuUser = lst.get(0);
                model.addAttribute("stuUser", lst.get(0));//当前系统设置批次下的医师
                extInfo = docSingupBiz.parseExtInfoXml(lst.get(0).getResumeInfo());
                extInfo.setStudyAim("");
                extInfo.setVocationalLevel("");
                model.addAttribute("extInfo", extInfo);
                List<String> eduDateList=new ArrayList<>();
                if(extInfo!=null&&extInfo.getEducationList()!=null){
                    for(EducationForm eForm:extInfo.getEducationList()){
                        eduDateList.add(eForm.getEduRoundDate());
                    }
                }
                Collections.sort(eduDateList);
                Collections.reverse(eduDateList);
                model.addAttribute("eduDateList", eduDateList);
                List<String> workDateList=new ArrayList<>();
                if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                    for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                        workDateList.add(eForm.getClinicalRoundDate());
                    }
                }
                Collections.sort(workDateList);
                Collections.reverse(workDateList);
                model.addAttribute("workDateList", workDateList);
            }
        }
        Boolean b = true;//用于判断第一页是否提交extInfo为null则未提交
        if (extInfo == null && user != null) {
            extInfo = new ExtInfoForm();
            //新注册的学员StuUserResume为空,页面中的学员基本信息是从StuUserResume中的resumeInfo（对应ExtInfoForm）即extInfo取的
            //页面要默认注册时的信息下一步是将SysUser中基本信息赋到extInfo
            updateExtInfoFromUser(user, extInfo);
            model.addAttribute("extInfo", extInfo);
            b = false;
        }
        /*if(sb==null||StringUtil.isBlank(sb.getBatchDate()) *//*|| selectBatch.getBatchDate().compareTo(sb.getBatchDate())>=0*//*	){*/
        if ((stuUser == null || !RegStatusEnum.Passed.getId().equals(stuUser.getStuStatusId()))) {
            if (extInfo != null) {
                //改成身份证
                if (StringUtil.isNotBlank(extInfo.getIdNo()) && b) {
                    model.addAttribute("formHaveSubmit1", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getCertifiedNo())) {
                    model.addAttribute("formHaveSubmit2", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getVocationalLevel())) {
                    model.addAttribute("formHaveSubmit3", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getRegisterFormUri())) {
                    model.addAttribute("registerFormUri", "Y");
                }
                if (stuUser != null) {
                    if (StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                            || GlobalConstant.FLAG_Y.equals(stuUser.getIsBack())
                            || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                        model.addAttribute("stuBatId", "Y");
                        List<StuAuditProcess> processes = stuUserResumeBiz.searchProcessByResumeFlow(stuUser.getResumeFlow());
                        if (processes != null && processes.size() > 0) {
                            StuAuditProcess process = processes.get(0);
                            if (StringUtil.isNotBlank(process.getAuditContent())) {
                                model.addAttribute("auditAgree", process.getAuditContent());
                            }
                        }
                    }
                }
            }
            if (stuUser != null) {
                if (StuStatusEnum.UnSubmit.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.Passing.getId().equals(stuUser.getStuStatusId())
                        || StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())) {
                    return "zseyjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
                }
            }
            if (stuUser == null) {
                return "zseyjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
            }
        }
//		/*}*/
        return "zseyjxres/doctor/singupinfo";//查看
    }

    /**
     * 保存学员网上报名信息
     * @param singUpForm
     * @param flag
     * @param stuBatId
     * @param registerFormUriValue
     * @return
     */
    @RequestMapping("/saveSingupInfo")
    @ResponseBody
    public synchronized String saveSingupInfo(SingUpForm singUpForm, String flag, String stuBatId, String registerFormUriValue) {

        StuBatch bachinfo = docSingupBiz.findBatchByBatchFlow(stuBatId);

        SysUser currentUser = GlobalContext.getCurrentUser();
        currentUser = userBiz.readSysUser(currentUser.getUserFlow());
        String data = "";
        Map<String, String> dataMap = new HashMap<>();

        if ("未开始".equals(bachinfo.getBatchStatus())) {
            dataMap.put("operResult", "该批次进修报名暂未开始，无法申请！");
            data = JSONUtils.toJSONString(dataMap);
            return data;

        }
        if ("已结束".equals(bachinfo.getBatchStatus())) {
            dataMap.put("operResult", "该批次进修报名已结束，无法申请！");
            data = JSONUtils.toJSONString(dataMap);
            return data;
        }

        if ("true".equals(flag)) {//学员 提交
            List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), stuBatId);
            if (doctorLst != null && doctorLst.size() > 0) {
                StuUserResume resume = doctorLst.get(0);
                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resume.getResumeInfo());
                extInfo.setRegisterFormUri(registerFormUriValue);
                resume.setResumeInfo(docSingupBiz.getXmlFromExtInfo(extInfo));
                resume.setStuStatusId(StuStatusEnum.Passing.getId());
                resume.setStuStatusName(StuStatusEnum.Passing.getName());
//                int result = stuUserResumeBiz.save(resume);


                //先查询科主任审核表中是否有数据,有数据删除
                List<StuHeadAuditStatus> statuses = docSingupBiz.getHeadStatus(stuBatId,resume.getResumeFlow(),null,null,null,currentUser.getUserFlow());
                Map<String, String> mp = new HashMap<String, String>();
                mp.put("resumeFlow", resume.getResumeFlow());
                mp.put("userFlow", currentUser.getUserFlow());
                mp.put("stuBatId",stuBatId);//当前批次
                mp.put("stuBatName",bachinfo.getBatchNo());
                mp.put("isBack",GlobalConstant.FLAG_N);//是否退回标识 默认为N
                List<SpeForm> speFormList = null;
                if(null!= extInfo){
                     speFormList = extInfo.getSpeFormList();
//                    docSingupBiz.saveHeadstatus(mp,speFormList,statuses);
                }
                int result = stuUserResumeBiz.saveResumeAndHead(resume,mp,speFormList,statuses);
                return result + "";
            }
            return GlobalConstant.OPRE_FAIL;
        }
        if ("reApply".equals(flag)) {// 重新申请
            List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), stuBatId);
            if (doctorLst != null && doctorLst.size() > 0) {
                StuUserResume resume = doctorLst.get(0);
                resume.setIsBack(GlobalConstant.FLAG_N);
                resume.setStuStatusId(StuStatusEnum.UnSubmit.getId());
                resume.setStuStatusName(StuStatusEnum.UnSubmit.getName());
                int result = stuUserResumeBiz.save(resume);
                return result + "";
            }
            return GlobalConstant.OPRE_FAIL;
        }
        SysUser user = singUpForm.getUser();
        if (user == null) {
            user = currentUser;
            singUpForm.setUser(user);
        }
        user.setUserFlow(currentUser.getUserFlow());//用户流水号
        user.setNationalityName(DictTypeEnum.getNameById(user.getNationalityId()));

        user.setCretTypeId("Shenfenzheng");//只能为身份证类型
        if (StringUtil.isNotBlank(user.getIdNo())) {//验证身份证是否注册过
            SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
            if (null != existUser) {
                dataMap.put("operResult", "证件号码已注册！");
                data = JSONUtils.toJSONString(dataMap);
                return data;
            }
        }
        user.setSexName(UserSexEnum.getNameById(user.getSexId()));//性别名称
        if(StringUtil.isNotBlank(user.getNationId())){
            user.setNationName(UserNationEnum.getNameById(user.getNationId()));//名族名称
        }
        if(StringUtil.isNotBlank(user.getPoliticsStatusId())){
            user.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(user.getPoliticsStatusId()));//政治面貌名称
        }

        StuUserResume stuUser = singUpForm.getStuUser();
        stuUser.setStuBatId(stuBatId);
        stuUser.setUserFlow(user.getUserFlow());//用户流水号
        //涉及到 管理员做 退回 操作后(更改isBack为Y)。
        stuUser.setIsBack(GlobalConstant.FLAG_N);//报名的时候，默认为N

//        if (StringUtil.isNotBlank(stuUser.getSpeId())) {
//            stuUser.setSpeName(DictTypeEnum.DwjxSpe.getDictNameById(stuUser.getSpeId()));//进修专业名称
//        }
        StuBatch bach = docSingupBiz.findBatchByBatchFlow(stuUser.getStuBatId());
        stuUser.setStuBatName(bach.getBatchNo());//进修批次名称


        if (StringUtil.isNotBlank(stuUser.getClotherSizeId())) {
            stuUser.setClotherSizeName(stuUser.getClotherSizeId() + "号");//工作尺寸名称
        }
        if(StringUtil.isNotBlank(stuUser.getMaxEduId())){
            stuUser.setMaxEduName(DictTypeEnum.UserEducation.getDictNameById(stuUser.getMaxEduId()));//最高学历
        }

        if(StringUtil.isNotBlank(stuUser.getUnitpropertyId())){
            stuUser.setUnitpropertyName(UnitPropertyEnum.getNameById(stuUser.getUnitpropertyId()));//单位性质
        }
        if(StringUtil.isNotBlank(stuUser.getUnitrankId())){
            stuUser.setUnitrankName(UnitRankEnum.getNameById(stuUser.getUnitrankId()));//单位等级
        }
        if(StringUtil.isNotBlank(stuUser.getUnitlevelId())){
            stuUser.setUnitlevelName(UnitLevelEnum.getNameById(stuUser.getUnitlevelId()));//单位级别
        }

//        stuUser.setTitleName(DictTypeEnum.UserTitle.getDictNameById(stuUser.getTitleId()));//职称
        if(StringUtil.isNotBlank(stuUser.getCertifiedTypeId())){
            stuUser.setCertifiedTypeName(DictTypeEnum.PracticeGenre.getDictNameById(stuUser.getCertifiedTypeId()));//执业类别
        }
        if(StringUtil.isNotBlank(stuUser.getHospitalLevelId())){
            stuUser.setHospitalLevelName(DictTypeEnum.HospitalRank.getDictNameById(stuUser.getHospitalLevelId()));//医院等级
        }
        stuUser.setStuStatusId(RegStatusEnum.UnSubmit.getId());
        stuUser.setStuStatusName("未提交");
        singUpForm.setStuUser(stuUser);
        ExtInfoForm extInfo = singUpForm.getExtInfo();


        if(extInfo!=null){
            List<SpeForm> speFormList = extInfo.getSpeFormList();
            if(speFormList!=null && speFormList.size()>0){
                int totalTime = 0;
                for(SpeForm speForm:speFormList){
                    SysDept sysDept = deptBiz.readSysDept(speForm.getSpeId());
                    speForm.setSpeName(sysDept.getDeptName());
                    if (StringUtil.isNotBlank(speForm.getStuTimeId())){
                        totalTime+=Float.parseFloat(speForm.getStuTimeId());
                    }
                }
                stuUser.setStuTimeId(String.valueOf(totalTime));
                stuUser.setStuTimeName(totalTime + "个月");//进修时间名称

            }
        }




        try {
            SysUser tempUser = singUpForm.getUser();
            ExtInfoForm tempInfo = singUpForm.getExtInfo();
            if (tempUser != null) {
                //因为刚注册的学员基本信息要先存入SYS_USER表，页面将基本信息（userName/sexId/sexName/userBirthday/
                // idNo/nationId/nationName/userPhone/userEmail/userHeadImg）映射到SysUser类中
                //然而报考的每一批次的记录要可编辑故基本信息要存入ExtInfoForm中即StuUserResume的resumeInfo（大字段）中
                //以下做的就是把SysUser中的基本信息更新到ExtInfoForm中
                if (tempInfo == null) {
                    tempInfo = new ExtInfoForm();
                }
//                tempInfo.setHotelExpense(hotelExpense);
                updateExtInfoFromUser(tempUser, tempInfo);
                singUpForm.setExtInfo(tempInfo);
            }
            docSingupBiz.saveSingupByPage(singUpForm);

//            List<StuUserResume> resumeList = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(),stuBatId);
//            //把学员所选科室插入张表中，每个科室初始审核状态为待审核
//            List<StuHeadAuditStatus> statuses = docSingupBiz.getHeadStatus(resumeList.get(0).getResumeFlow(),currentUser.getDeptName(),null,null,null);
//
//            if(extInfo!=null && extInfo.getSpeFormList()!=null){
//                List<SpeForm> speFormList = extInfo.getSpeFormList();
//                Map<String, String> mp = new HashMap<String, String>();
//                mp.put("resumeFlow", resumeList.get(0).getResumeFlow());
//                mp.put("userFlow", currentUser.getUserFlow());
//                mp.put("stuBatId",stuBatId);//当前批次
//                mp.put("stuBatName",bachinfo.getBatchNo());
//                mp.put("isBack",GlobalConstant.FLAG_N);//是否退回标识 默认为N
//                docSingupBiz.saveHeadstatus(mp,speFormList,statuses);
//            }


            dataMap.put("operResult", GlobalConstant.OPRE_SUCCESSED);
            dataMap.put("formHaveSubmit1", "Y");
            if (extInfo != null) {
                if (StringUtil.isNotBlank(extInfo.getCertifiedUri())) {
                    dataMap.put("formHaveSubmit2", "Y");
                }
                if (StringUtil.isNotBlank(extInfo.getVocationalLevel())) {
                    dataMap.put("formHaveSubmit3", "Y");
                    dataMap.put("resumeFlow", stuUser.getResumeFlow());
                }
            }
            data = JSONUtils.toJSONString(dataMap);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("operResult", GlobalConstant.OPRE_FAIL);
            data = JSONUtils.toJSONString(dataMap);
            return data;
        }
    }

    private void updateExtInfoFromUser(SysUser tempUser, ExtInfoForm tempInfo) {

        //国籍
        if(StringUtil.isNotBlank(tempUser.getNationalityId())){
            tempInfo.setNationalityId(tempUser.getNationalityId());
        }
        if(StringUtil.isNotBlank(tempUser.getNationalityName())){
            tempInfo.setNationalityName(tempUser.getNationalityName());
        }
        if(StringUtil.isNotBlank(tempUser.getUserMajor())){
            tempInfo.setUserMajor(tempUser.getUserMajor());//个人专业
        }
        if (StringUtil.isNotBlank(tempUser.getUserName())) {
            tempInfo.setUserName(tempUser.getUserName());//姓名
        }
        if (StringUtil.isNotBlank(tempUser.getSexId())) {
            tempInfo.setSexId(tempUser.getSexId());//性别Id
        }
        if (StringUtil.isNotBlank(tempUser.getSexName())) {
            tempInfo.setSexName(tempUser.getSexName());//性别Name
        }
        if (StringUtil.isNotBlank(tempUser.getUserBirthday())) {
            tempInfo.setUserBirthday(tempUser.getUserBirthday());//生日
        }
        if (StringUtil.isNotBlank(tempUser.getIdNo())) {
            tempInfo.setIdNo(tempUser.getIdNo());//身份证号
        }
        if (StringUtil.isNotBlank(tempUser.getNationId())) {
            tempInfo.setNationId(tempUser.getNationId());//民族Id
        }
        if (StringUtil.isNotBlank(tempUser.getNationName())) {
            tempInfo.setNationName(tempUser.getNationName());//民族Name
        }
        if (StringUtil.isNotBlank(tempUser.getUserPhone())) {
            tempInfo.setUserPhone(tempUser.getUserPhone());//联系电话
        }
        if (StringUtil.isNotBlank(tempUser.getUserEmail())) {
            tempInfo.setUserEmail(tempUser.getUserEmail());//邮箱
        }
        if (StringUtil.isNotBlank(tempUser.getUserHeadImg())) {
            tempInfo.setUserHeadImg(tempUser.getUserHeadImg());//证件照地址
        }
        if (StringUtil.isNotBlank(tempUser.getPoliticsStatusId())) {
            tempInfo.setPoliticsStatusId(tempUser.getPoliticsStatusId());//政治面貌ID
        }
        if (StringUtil.isNotBlank(tempUser.getPoliticsStatusName())) {
            tempInfo.setPoliticsStatusName(tempUser.getPoliticsStatusName());//政治面貌NAME
        }
        if (StringUtil.isNotBlank(tempUser.getNativePlaceProvId())) {
            tempInfo.setNativePlaceProvId(tempUser.getNativePlaceProvId());//籍贯省id
        }
        if (StringUtil.isNotBlank(tempUser.getNativePlaceProvName())) {
            tempInfo.setNativePlaceProvName(tempUser.getNativePlaceProvName());//籍贯省name
        }
        if (StringUtil.isNotBlank(tempUser.getNativePlaceCityId())) {
            tempInfo.setNativePlaceCityId(tempUser.getNativePlaceCityId());//籍贯市id
        }
        if (StringUtil.isNotBlank(tempUser.getNativePlaceCityName())) {
            tempInfo.setNativePlaceCityName(tempUser.getNativePlaceCityName());//籍贯市name
        }
        if (StringUtil.isNotBlank(tempUser.getUserCode())) {
            tempInfo.setUserCode(tempUser.getUserCode());//工号
        }
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
            List<String> eduDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getEducationList()!=null){
                for(EducationForm eForm:extInfo.getEducationList()){
                    eduDateList.add(eForm.getEduRoundDate());
                }
            }
            Collections.sort(eduDateList);
            Collections.reverse(eduDateList);
            model.addAttribute("eduDateList", eduDateList);
            List<String> workDateList=new ArrayList<>();
            if(extInfo!=null&&extInfo.getWorkResumeList()!=null){
                for(WorkResumeForm eForm:extInfo.getWorkResumeList()){
                    workDateList.add(eForm.getClinicalRoundDate());
                }
            }
            Collections.sort(workDateList);
            Collections.reverse(workDateList);
            model.addAttribute("workDateList", workDateList);
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        model.addAttribute("batchId", batchId);
        return "zseyjxres/doctor/singupinfoforaudit";
    }

    @RequestMapping("/submitSingup")
    @ResponseBody
    public String submitSingup(SingUpForm form, String flag) {
        SysUser user = form.getUser();
        user.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());//用户流水号
        user.setCretTypeId("Shenfenzheng");//只能为身份证类型
        if (StringUtil.isNotBlank(user.getIdNo())) {//验证身份证是否注册过
            SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
            if (null != existUser) {
                return "证件号码已注册！";
            }
        }
        user.setSexName(UserSexEnum.getNameById(user.getSexId()));//性别名称
        user.setNationName(UserNationEnum.getNameById(user.getNationId()));//名族名称

        StuUserResume stuUser = form.getStuUser();
        stuUser.setUserFlow(user.getUserFlow());//用户流水号
        stuUser.setSpeName(DictTypeEnum.DwjxSpe.getDictNameById(stuUser.getSpeId()));//进修专业名称
        stuUser.setStuTimeName(stuUser.getStuTimeId() + "个月");//进修时间名称
        if (StringUtil.isNotBlank(stuUser.getStuBatId())) {
            StuBatch selectBatch = stuBatchBiz.searchStuBatch(stuUser.getStuBatId());
            if (selectBatch != null)
                stuUser.setStuBatName(selectBatch.getBatchNo());//进修批次名称
        }
        stuUser.setClotherSizeName(stuUser.getClotherSizeId() + "号");//工作尺寸名称
        stuUser.setMaxEduName(DictTypeEnum.UserEducation.getDictNameById(stuUser.getMaxEduId()));//最高学历
        stuUser.setTitleName(DictTypeEnum.UserTitle.getDictNameById(stuUser.getTitleId()));//职称
        stuUser.setCertifiedTypeName(DictTypeEnum.PracticeGenre.getDictNameById(stuUser.getCertifiedTypeId()));//执业类别
        stuUser.setHospitalLevelName(DictTypeEnum.HospitalRank.getDictNameById(stuUser.getHospitalLevelId()));//医院等级
        if ("false".equals(flag)) {
            stuUser.setStuStatusId(RegStatusEnum.UnSubmit.getId());
            stuUser.setStuStatusName("未提交");
        } else {
            stuUser.setStuStatusId(RegStatusEnum.Passing.getId());
            stuUser.setStuStatusName("待审核");
        }
        this.docSingupBiz.submitSingup(form);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 验证当前批次的网上报名是否暂存或者提交
     *
     * @return
     */
    @RequestMapping("/valideExist")
    @ResponseBody
    public String valideExist(String batchFlow) {
        SysUser user = GlobalContext.getCurrentUser();
        List<StuUserResume> stuUserLst = this.stuUserResumeBiz.getStuUserLst(user.getUserFlow(), batchFlow);
        if (null != stuUserLst && stuUserLst.size() > 0) {
            return stuUserLst.get(0).getResumeFlow();
        }
        return null;
    }

    /**
     * 下载录取通知书
     */
    @RequestMapping(value = "/recruitNotice")
    public String recruitNotice(String resumeFlow, String titleTypeId, HttpServletRequest req, HttpServletResponse res,Model model) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        String resumeInfo = stuUser.getResumeInfo();
        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName());
            dataMap.put("sendComName",stuUser.getSendComName());   //选送单位
            dataMap.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            ExtInfoForm infoForm = docSingupBiz.parseExtInfoXml(resumeInfo);

            dataMap.put("tuition",infoForm.getTuition());//进修费
            String hotelExpense ="";
            if("不住宿".equals(stuUser.getIsPutup())){
                hotelExpense="0";
            }else{
                hotelExpense = infoForm.getHotelExpense();
            }
            dataMap.put("hotelExpense",hotelExpense);//住宿费
            dataMap.put("according",infoForm.getAccording());//材料费
            String speName="";
            String stuTimeName="";
            List<SpeForm> speFormList = infoForm.getSpeFormList();
            for(SpeForm speForm:speFormList){
                speName+="+"+speForm.getSpeName();
                stuTimeName+="+"+speForm.getStuTimeId();
            }
            dataMap.put("speName", speName.substring(1));//进修专业
            dataMap.put("stuTimeName", stuTimeName.substring(1));//进修时间
            dataMap.put("reportDate",infoForm.getReportDate());//报到时间

//            String tuition = StringUtil.defaultIfEmpty(infoForm.getTuition(),"0");
//            String hotelExpense =  StringUtil.defaultIfEmpty(infoForm.getHotelExpense(),"0");
//            String according =  StringUtil.defaultIfEmpty(infoForm.getAccording(),"0");
//            String coverallNum =  StringUtil.defaultIfEmpty(infoForm.getCoverallNum(),"0");
//            dataMap.put("trainFee", String.valueOf(Integer.parseInt(tuition)+Integer.parseInt(hotelExpense)+
//                    Integer.parseInt(according)+Integer.parseInt(coverallNum)*65)
//            );//进修培养费

        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/zseyjxres/print/admissionNoticeTemeplete.docx";//报到通知书        }
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
//        if (temeplete != null) {
//            String name = "报到通知书.pdf";
//            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
////            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//            res.setContentType("multipart/form-data");
//            ServletOutputStream out = res.getOutputStream();
//            Docx4jUtil.toPdf(temeplete,out);
//            out.flush();
//        }
        if (temeplete != null) {
            String name = "报到通知书.pdf";
            //获取当前天（每天删除前一天的转换的文件释放空间）
            String dayTime = DateUtil.getCurrDate2();

            String docxRealPath = InitConfig.getSysCfg("upload_base_dir") + File.separator+"word2pdf" + File.separator + "recword" + File.separator+ dayTime +File.separator +resumeFlow+".docx";
//						String pdfRealPath = InitConfig.getSysCfg("upload_base_dir") + File.separator + "recpdf" + File.separator + dayTime +File.separator +resumeFlow+".pdf";
            String pdfRealPath = InitConfig.getSysCfg("upload_base_dir") + File.separator + "word2pdf"+ File.separator +"recpdf" + File.separator + dayTime +File.separator +name;
            String pdfPath =File.separator + "word2pdf"+File.separator + "recpdf" + File.separator + dayTime +File.separator +name;
            File docxFile = new File(docxRealPath);
            File docxDir = docxFile.getParentFile();
            if (!docxDir.exists()) {
                docxDir.mkdirs();
            }
            docxFile.createNewFile();
            File pdfFile = new File(pdfRealPath);
            File pdfDir = pdfFile.getParentFile();
            if (!pdfDir.exists()) {
                pdfDir.mkdirs();
            }
            pdfFile.createNewFile();
            OutputStream os =null;
            os = new FileOutputStream(docxFile);
//						Docx4jUtil.toPdf(temeplete, out);
            (new SaveToZipFile(temeplete)).save(os);
            os.flush();
            PdfUtil.convert2PDF(docxRealPath,pdfRealPath);
//            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
//            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//            res.setContentType("multipart/form-data");
//            ServletOutputStream out = res.getOutputStream();

            model.addAttribute("pdfPath",pdfPath);
        }

        return "/zseyjxres/doctor/projRecPdf";
    }


    /**
     * 下载住宿介绍信
     */
    @RequestMapping(value = "/accommodation")
    public void accommodation(String resumeFlow, String titleTypeId, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        String resumeInfo = stuUser.getResumeInfo();
        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName());//学员姓名
            dataMap.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间

            ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resumeInfo);
            String beginDate ="";//进修开始时间
            String finishDate = "";//进修结束时间
            List<String> beginEndList = new ArrayList<>();//学员进行起始时间List
            if(null!=extInfo.getSpeFormList() && extInfo.getSpeFormList().size()>0){
                for(SpeForm speForm:extInfo.getSpeFormList()){
                    beginEndList.add(speForm.getBeginDate());
                    beginEndList.add(speForm.getEndDate());
                }
                if(beginEndList.size()>0 && !beginEndList.isEmpty()){
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
            dataMap.put("finishDate", finishDate);//结束时间
            dataMap.put("reportDate", extInfo.getReportDate());//管理员填写的报到时间


        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/zseyjxres/print/accommodationTemplate.docx";//住宿介绍信        }
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "住宿介绍信.pdf";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
//            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            res.setContentType("multipart/form-data");
            ServletOutputStream out = res.getOutputStream();
            Docx4jUtil.toPdf(temeplete,out);
            out.flush();
        }
    }





    /**
     * 查看结业证书
     * @param resumeFlow
     * @param flag
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/showCertificate")
    public void showCertificate(String resumeFlow, String flag,Model model,HttpServletRequest request,HttpServletResponse res) throws Exception{
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserResume stuUserResume = stuUserResumeBiz.getStuUserByKey(resumeFlow);

        stuUserResume.setIsGraduation(GlobalConstant.FLAG_Y);
        stuUserResume.setStuStatusId(StuStatusEnum.Graduation.getId());
        stuUserResume.setStuStatusName(StuStatusEnum.Graduation.getName());
        stuUserResumeBiz.save(stuUserResume);

        model.addAttribute("stuUserResume",stuUserResume);
        //培训专业显示亚专科：即科秘安排的轮转科室。
        String speName="";

        List<String> dateList = new ArrayList<>();//管理员安排的轮转时间list
        Map<String,String> schMonthMap = new HashMap<>();//所有的轮转时长、科室Map
        ScholarSchArrange arrange = new ScholarSchArrange();
        arrange.setDoctorFlow(stuUserResume.getUserFlow());
        List<ScholarSchArrange> arrangeList =arrangeBiz.searchArrange(arrange);//该学员的管理员安排信息
        if(arrangeList!=null && arrangeList.size()>0){
            for(ScholarSchArrange schArrange:arrangeList){
                dateList.add(schArrange.getSchStartDate());
                dateList.add(schArrange.getSchEndDate());

                //得到科秘安排的信息
                List<SchProcessExt> processList = processBiz.searchProcessAndResultByDoctorFlow(stuUserResume.getUserFlow(),schArrange.getDeptFlow());
                if(processList!=null && processList.size()>0){
                    for(SchProcessExt month:processList){
                        String schMonth = month.getSchArrangeResult().getSchMonth();
                        String schDeptName = month.getSchDeptName();
                        schMonthMap.put(schMonth,schDeptName);
                    }
                }
            }

            //若轮转时长都超过3个月，则显示第一个。若没有超过3个月的，则显示时间最长的科室
            Map<String,String> schMonthMap2 = new HashMap<>(); //存放大于3个月轮转时长、轮转科室
            Map<String,String> schMonthMap3 = new HashMap<>(); //存放小于3个月轮转时长、轮转科室

            for (Map.Entry<String,String> entry : schMonthMap.entrySet()) {
                if(entry.getKey().compareTo("3")>0){
                    schMonthMap2.put(entry.getKey(),entry.getValue());
                }else{
                    schMonthMap3.put(entry.getKey(),entry.getValue());
                }
            }
            if(schMonthMap2!=null && schMonthMap2.size()>0){
                List<String> speNameList = new ArrayList<>();
                Iterator it = schMonthMap2.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().toString();
                    speNameList.add(schMonthMap2.get(key));
                }
                speName = speNameList.get(0);
            }else {
                Float maxValue = Float.MIN_VALUE;
                Set<String> keys = schMonthMap3.keySet();
                for(String key : keys){
                    if(maxValue < Float.parseFloat(key)){
                        maxValue = Float.parseFloat(key);
                    }
                }

                for (Map.Entry<String, String> entry : schMonthMap3.entrySet()) {
                    if(entry.getKey().equals(maxValue.toString())){
                        speName = entry.getValue();
                    }
                }
            }
        }

        dataMap.put("speName",speName);//进修专业
//        model.addAttribute("speName",speName);
        String userFlow= stuUserResume.getUserFlow();
        SysUser sysUser = userBiz.readSysUser(userFlow);
        model.addAttribute("sysUser",sysUser);
        dataMap.put("userName",sysUser.getUserName()); //姓名
        dataMap.put("sexName",sysUser.getSexName()); //性别
        dataMap.put("userBirthday",sysUser.getUserBirthday()); //出生日期
        dataMap.put("idNo",sysUser.getIdNo()); //身份证号
        SysUser currentUser = GlobalContext.getCurrentUser();
        ServletContext application = request.getServletContext();
        Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        String orgName = sysCfgMap.get("jx_certificate_name");
        model.addAttribute("orgName",orgName);
        String batchFlow = stuUserResume.getStuBatId();
        ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUserResume.getResumeInfo());
        List<SpeForm> speFormList = extInfo.getSpeFormList();
//        String stutyTime=""; //进修时间（月）

        StuBatch stuBatch = stuBatchBiz.searchStuBatch(batchFlow);

//        String regDate = stuBatch.getBatchRegDate();
//        if(speFormList!=null && speFormList.size()>0){
//            for(SpeForm speForm:speFormList){
//                Float maxValue = Float.MIN_VALUE;
//                if(maxValue<Float.parseFloat(speForm.getStuTimeId())){
//                    maxValue = Float.parseFloat(speForm.getStuTimeId());
//                    stutyTime = String.valueOf(maxValue);
//                }
//            }
//
//
//        }


        //进修开始时间:取管理员安排的最早时间
        String regDate ="";
        //进修结束时间：取管理员安排的最晚时间
        String finishDate="";
        String dates[] = new String[dateList.size()];
        dateList.toArray(dates);
        for(int i=0;i<dates.length-1;i++){
            for(int j=0;j<dates.length-1-i;j++){
                if(dates[j].compareTo(dates[j+1])>0){
                    String temp=dates[j];
                    dates[j]=dates[j+1];
                    dates[j+1]=temp;
                }
            }
        }
        for(int i=0;i<dates.length;i++){
            regDate = dates[0];
            finishDate = dates[dates.length-1];
        }

        dataMap.put("regDate",regDate); //进修开始时间
        dataMap.put("finishDate",finishDate);//进修结束时间
//        dataMap.put("thisTime",stuUserResume.getModifyTime());
//        dataMap.put("completeNo","furtherEducation");

        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/zseyjxres/print/zhengshuTemeplete.docx";//结业证书模板
        ServletContext context = request.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "结业证书.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("utf-8"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    @RequestMapping("/englishCertificate")
    public void englishCertificate(HttpServletRequest req, HttpServletResponse res,String resumeFlow) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String modifyTime = stuUserResumeBiz.getStuUserByKey(resumeFlow).getModifyTime();
        String modifyTime2 = modifyTime.substring(0,4)+"/"+modifyTime.substring(4,6)+"/"+modifyTime.substring(6,8);
        Date date = new Date(modifyTime2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
        String currDate = dateFormat.format(date);
        dataMap.put("date",currDate);
        String path = "/jsp/zseyjxres/print/englishCertificate.docx";
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "英文结业证书模板.docx";
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
                return arry[0] + "年" + arry[1] + "月";
            } else if (arry.length == 3) {
                return arry[0] + "年" + arry[1] + "月" + arry[2] + "日";
            }
        }
        return "";
    }
}
