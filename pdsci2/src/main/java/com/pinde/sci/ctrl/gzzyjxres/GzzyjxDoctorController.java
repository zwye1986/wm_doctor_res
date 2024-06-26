package com.pinde.sci.ctrl.gzzyjxres;

import com.alibaba.druid.support.json.JSONUtils;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.gzzyjxres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.StuUserResumeMapper;
import com.pinde.sci.enums.gzzyjxres.GenderEnum;
import com.pinde.sci.enums.gzzyjxres.TuitionCategoryEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.form.gzzyjxres.*;
import com.pinde.sci.form.gzzyjxres.ExtInfoForm;
import com.pinde.sci.form.gzzyjxres.SingUpForm;
import com.pinde.sci.form.gzzyjxres.WorkResumeForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.SchProcessExt;
import com.pinde.sci.model.res.StuUserExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/gzzyjxres/doctor")
public class GzzyjxDoctorController extends GeneralController {

    private static final String GZZY_ORG_FLOW="5cb53b872c38457a8e2a798d6c4d002f";

    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IJxTuitionManagementBiz tuitionManagementBiz;
    @Autowired
    private IJxNationalHolidaysRegisterBiz nationalHolidaysRegisterBiz;
    @Autowired
    private IJxWeekendsRegisterBiz weekendsRegisterBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IScholarSchArrangeBiz arrangeBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IGzjxDocSingupBiz docSingupBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;


    /**
     * 境外学员进入主页
     * @param currentPage
     * @param model
     * @return
     */
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
        return "gzzyjxres/doctor/english_index";
    }


    /**
     * 境内学员进行主页
     * @param currentPage
     * @param model
     * @return
     */
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
        return "gzzyjxres/doctor/index";
    }


    /**
     * 境内
     * @param model
     * @return
     */
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
                return "redirect:/gzzyjxres/secretaries/home";
            }
        }
        String loginErrorMessage = "未赋权";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return "inx/gzzyjxres/login";
    }

    /**
     * 境外
     * @param model
     * @return
     */
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
                return "redirect:/gzzyjxres/secretaries/home";
            }
        }
        String loginErrorMessage = "未赋权";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return "inx/gzzyjxres/englishLogin";
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

    /**
     * 下载附件
     */
    @RequestMapping(value = {"/fileDown"}, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
        PubFile file = this.fileBiz.readFile(fileFlow);
        fileBiz.downPubFile(file, response);
    }

    public String getRoleUrl(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
            if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                return "/gzzyjxres/hospital/home";
//			} else if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//系统管理员即省级管理员
//				return "/gzzyjxres/manage/home";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/gzzyjxres/doctor/home";
            }
        }
        return "";
    }

    public String getRoleUrl_en(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
//             if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/gzzyjxres/doctor/home_en";
//            }
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
                return "gzzyjxres/doctor/singupinfo_en";//查看
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
                    return "gzzyjxres/doctor/singupMain_en";//当前系统默认批次及往后批次且未提交状态 可编辑
                }
            }
            if (stuUser == null) {
                return "gzzyjxres/doctor/singupMain_en";//当前系统默认批次及往后批次且未提交状态 可编辑
            }
        }
//		/*}*/
        return "gzzyjxres/doctor/singupinfo_en";//查看
    }

    /**
     * 学员网上报名
     * @param batchFlow
     * @param model
     * @return
     */
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
                return "gzzyjxres/doctor/singupinfo";//查看
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
                    return "gzzyjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
                }
            }
            if (stuUser == null) {
                return "gzzyjxres/doctor/singupMain";//当前系统默认批次及往后批次且未提交状态 可编辑
            }
        }
//		/*}*/
        return "gzzyjxres/doctor/singupinfo";//查看
    }

    /**
     * 计算工作日
     * @return
     */
    public ExtInfoForm countWorkDays(ExtInfoForm extInfo){
        if(extInfo!=null && extInfo.getSpeFormList()!=null){
            //计算进修天数 ，去除周末和节假日
            //管理员登记的节假日list
            List<JxNationalHolidaysRegister> holidays = nationalHolidaysRegisterBiz.listNationalHolidays();
            List<Date> allDateList = new ArrayList<>();//获取管理员登记的两个日期区间中的所有日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
            if(null!=holidays && holidays.size()>0){
                for(JxNationalHolidaysRegister holiday:holidays){
                    Date start =null;
                    Date end = null;
                    try {
                        start = simpleDateFormat.parse(holiday.getBeginDate());
                        end = simpleDateFormat.parse(holiday.getEndDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    allDateList.addAll( DateUtil.getBetweenDates(start,end));//获取管理员登记的两个日期区间中的所有日期
                }
            }

            //每次加入之前先清空
            CalendarUtil.clearList();
            if(null!=allDateList && allDateList.size()>0){
                //遍历 加入到 节假日list
                for(Date date:allDateList){
                    CalendarUtil.initHolidayList(simpleDateFormat.format(date));
                }
            }

            //管理员登记的周末为工作日的list
            List<JxWeekendsRegister> weekendsRegisterList =  weekendsRegisterBiz.listWeekends();
            if(null!=weekendsRegisterList && weekendsRegisterList.size()>0){
                for(JxWeekendsRegister weekend:weekendsRegisterList){
                    //遍历 加入到 工作日list
                    CalendarUtil.initWeekendList(weekend.getWeekendDate());
                }
            }
            //循环添加学员进修选择的两个日期区间中的所有日期
            List<Date> deptDateList=null;
                List<SpeForm> speFormList = extInfo.getSpeFormList();
                if(null!=speFormList && speFormList.size()>0){
                    for(SpeForm speForm:speFormList){
                        int workDays=0; //工作日
                        deptDateList = new ArrayList<>(); //学员进修选择的两个日期区间中的所有日期List

                        Date start =null;
                        Date end = null;
                        try {
                            start = simpleDateFormat.parse(speForm.getBeginDate());
                            end = simpleDateFormat.parse(speForm.getEndDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        deptDateList.addAll(DateUtil.getBetweenDates(start,end));//循环添加学员进修选择的两个日期区间中的所有日期

                    //判断是否是节假日
                    int num=0;
                    for(Date date:deptDateList){//遍历学员所选的日期
                        Calendar ca = Calendar.getInstance();
                        ca.setTime(date);//设置当前时间
                        try {
                            boolean k = CalendarUtil.checkHoliday(ca);
                            //如果为节假日，定义一个num为节假日的天数
                            if(k){
                                num++;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                        workDays = deptDateList.size()-num;
                        speForm.setStuTimeId(String.valueOf(workDays));
                }
            }
    }
        return extInfo;
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
    public String saveSingupInfo(SingUpForm singUpForm, String flag, String stuBatId, String registerFormUriValue) {

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

        if ("true".equals(flag)) {
            List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), stuBatId);
            if (doctorLst != null && doctorLst.size() > 0) {
                StuUserResume resume = doctorLst.get(0);
                ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(resume.getResumeInfo());
                extInfo.setRegisterFormUri(registerFormUriValue);
                resume.setResumeInfo(docSingupBiz.getXmlFromExtInfo(extInfo));
                resume.setStuStatusId(StuStatusEnum.Passing.getId());
                resume.setStuStatusName(StuStatusEnum.Passing.getName());
                int result = stuUserResumeBiz.save(resume);
                return result + "";
            }
            return GlobalConstant.OPRE_FAIL;
        }
        if ("reApply".equals(flag)) {
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
//        List<SysDict> nationalList = DictTypeEnum.GzzyjxNational.getSysDictList();
//        for(SysDict dict:nationalList){
//            if(dict.getDictId().equals(user.getNationalityId())){
//                user.setNationalityName(dict.getDictDesc());
//            }
//            break;
//        }
//        user.setCretTypeId("Shenfenzheng");//只能为身份证类型
        user.setCretTypeId(currentUser.getCretTypeId());
        if (StringUtil.isNotBlank(user.getIdNo())) {//验证证件号码是否注册过
            SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
            if (null != existUser) {
                dataMap.put("operResult", "证件号码已注册！");
                data = JSONUtils.toJSONString(dataMap);
                return data;
            }
        }


        user.setSexName(GenderEnum.getNameById(user.getSexId()));//性别名称
        if(StringUtil.isNotBlank(user.getNationId())){
            user.setNationName(UserNationEnum.getNameById(user.getNationId()));//名族名称
        }
        if(StringUtil.isNotBlank(user.getPoliticsStatusId())){
            user.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(user.getPoliticsStatusId()));//政治面貌名称
        }

        StuUserResume stuUser = singUpForm.getStuUser();
        stuUser.setStuBatId(stuBatId);
        stuUser.setUserFlow(user.getUserFlow());//用户流水号
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
                    for(SpeForm speForm:speFormList){
                        if(GlobalConstant.FLAG_N.equals(currentUser.getIsForeign())) {
                            SysDept sysDept = deptBiz.readSysDept(speForm.getSpeId());
                            speForm.setSpeName(sysDept.getDeptName());
                        }else if(GlobalConstant.FLAG_Y.equals(currentUser.getIsForeign())){
                            speForm.setSpeName(speForm.getSpeId());
                        }
                    }

                }
            }

        if(GlobalConstant.FLAG_Y.equals(currentUser.getIsForeign())){
            singUpForm.setExtInfo(countWorkDays(extInfo));  //计算每个专业的工作日
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

        //国籍(按需求 去掉字典维护)
        if(StringUtil.isNotBlank(tempUser.getNationalityId())){
            tempInfo.setNationalityId(tempUser.getNationalityId());
        }
//        if(StringUtil.isNotBlank(tempUser.getNationalityName())){
//            tempInfo.setNationalityName(tempUser.getNationalityName());
//        }

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
            tempInfo.setIdNo(tempUser.getIdNo());//证件号码
        }

        if(StringUtil.isNotBlank(tempUser.getPassportNo())){
            tempInfo.setPassportNo(tempUser.getPassportNo());//护照号
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
    public String getSingUpInfoForAudit(String isForeign,String userFlow, String batchId, String flag, Model model) {
        SysUser user = this.userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(userFlow, batchId);
        if (null != doctorLst && doctorLst.size() > 0) {
            //当前系统设置批次下的医师
            model.addAttribute("stuUser", doctorLst.get(0));
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

            List<JxTuitionManagement> tuitionList = tuitionManagementBiz.listTuitionManagements();
            if(null!=tuitionList && tuitionList.size()>0){
                //押金
                List<JxTuitionManagement> depositList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.Deposit.getName());
                model.addAttribute("deposit",depositList.get(0).getCostValue());

                //工作服
                List<JxTuitionManagement> workClothsList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.WorkCloths.getName());
                model.addAttribute("workCloths",workClothsList.get(0).getCostValue());


                //境内学员
                if(GlobalConstant.FLAG_N.equals(isForeign)){
                    //非帮扶生的学费
                    int fee =0;
                    //帮扶生的学费
                    int helpingFee=0;
                    //学费（半年以下是每月1000块/满半年不满一年每月800块/满一年及以上每月600块）
                    if(extInfo!=null && extInfo.getSpeFormList()!=null){
                        int totalMonth =0;
                        if(extInfo.getSpeFormList().size()>0){
                            for(SpeForm speForm:extInfo.getSpeFormList()){
                                totalMonth+=Integer.parseInt(speForm.getStuTimeId());
                            }
                        }
                        //帮扶生费用
                        List<JxTuitionManagement> helpList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.Helping.getName());
//                        model.addAttribute("helpingFee",Integer.parseInt(helpList.get(0).getCostValue())*totalMonth);
                        if(null!=helpList && helpList.size()>0){
                            helpingFee = Integer.parseInt(helpList.get(0).getCostValue())*totalMonth;
                        }

                        if(totalMonth<6){
                            List<JxTuitionManagement> halfList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.Half.getName());
                            if(null!=halfList && halfList.size()>0){
                                fee = Integer.parseInt(halfList.get(0).getCostValue())*totalMonth;
                            }
                        }else if(totalMonth>=6 && totalMonth<12){
                            List<JxTuitionManagement> notFullList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.NotFull.getName());
                            if(null!=notFullList && notFullList.size()>0){
                                fee = Integer.parseInt(notFullList.get(0).getCostValue())*totalMonth;
                            }
                        }else if(totalMonth>=12){
                            List<JxTuitionManagement> moreList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.More.getName());
                            if(null!=moreList && moreList.size()>0){
                                fee = Integer.parseInt(moreList.get(0).getCostValue())*totalMonth;
                            }
                        }
                        if("other".equals(extInfo.getIsOwnOrg())){
                            model.addAttribute("fee", String.valueOf(helpingFee));
                        }else {
                            model.addAttribute("fee", String.valueOf(fee));
                        }
                    }
                }else if(GlobalConstant.FLAG_Y.equals(isForeign)){

                    int tuition=0; //学费
                    int workDays=0; //工作日

                    if(extInfo!=null && extInfo.getSpeFormList()!=null){

                        //计算进修天数 ，去除周末和节假日

                        //管理员登记的节假日list
                        List<JxNationalHolidaysRegister> holidays = nationalHolidaysRegisterBiz.listNationalHolidays();
                        List<Date> allDateList = new ArrayList<>();//获取管理员登记的两个日期区间中的所有日期

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
                        if(null!=holidays && holidays.size()>0){
                            for(JxNationalHolidaysRegister holiday:holidays){
                                Date start =null;
                                Date end = null;
                                try {
                                    start = simpleDateFormat.parse(holiday.getBeginDate());
                                    end = simpleDateFormat.parse(holiday.getEndDate());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                allDateList.addAll( DateUtil.getBetweenDates(start,end));//获取管理员登记的两个日期区间中的所有日期
                            }
                        }

                        //每次加入之前先清空
                        CalendarUtil.clearList();
                        if(null!=allDateList && allDateList.size()>0){
                            //遍历 加入到 节假日list
                            for(Date date:allDateList){
                                CalendarUtil.initHolidayList(simpleDateFormat.format(date));
                            }
                        }

                        //管理员登记的周末为工作日的list
                        List<JxWeekendsRegister> weekendsRegisterList =  weekendsRegisterBiz.listWeekends();
                        if(null!=weekendsRegisterList && weekendsRegisterList.size()>0){
                            for(JxWeekendsRegister weekend:weekendsRegisterList){
                                //遍历 加入到 工作日list
                                CalendarUtil.initWeekendList(weekend.getWeekendDate());
                            }
                        }

                        List<Date> deptDateList = new ArrayList<>(); //学员进修选择的两个日期区间中的所有日期List
                        //循环添加学员进修选择的两个日期区间中的所有日期
                        if(null!=extInfo.getSpeFormList() && extInfo.getSpeFormList().size()>0){
                            List<SpeForm> speFormList = extInfo.getSpeFormList();
                            if(null!=speFormList && speFormList.size()>0){
                                for(SpeForm speForm:speFormList){
                                    Date start =null;
                                    Date end = null;
                                    try {
                                        start = simpleDateFormat.parse(speForm.getBeginDate());
                                        end = simpleDateFormat.parse(speForm.getEndDate());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    deptDateList.addAll(DateUtil.getBetweenDates(start,end));//循环添加学员进修选择的两个日期区间中的所有日期
                                }

                                //判断是否是节假日
                                int num=0;
                                for(Date date:deptDateList){//遍历学员所选的日期
                                    Calendar ca = Calendar.getInstance();
                                    ca.setTime(date);//设置当前时间
                                    try {
                                        boolean k = CalendarUtil.checkHoliday(ca);
                                        //如果为节假日，定义一个num为节假日的天数
                                        if(k){
                                            num++;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                //实际的天数 = 所选日期list的大小 - num
                                workDays = deptDateList.size()-num;
                            }
                        }

                    //学费
                    List<JxTuitionManagement> foreignList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.Foreign.getName());
                    if(null!=foreignList && foreignList.size()>0){
                        tuition = workDays*(Integer.parseInt(foreignList.get(0).getCostValue()));
                    }
                    model.addAttribute("tuition",tuition);
                    }
                }

            }
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        model.addAttribute("batchId", batchId);
        if(StringUtil.isNotBlank(isForeign) && isForeign.equals(GlobalConstant.FLAG_Y))
            return "gzzyjxres/doctor/singupinfoforaudit_en";
        return "gzzyjxres/doctor/singupinfoforaudit";
    }

    /**
     *
     * @param form
     * @param flag
     * @return
     */
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
        user.setSexName(GenderEnum.getNameById(user.getSexId()));//性别名称
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
     * 打印录取通知书
     */
    @RequestMapping(value = "/recruitNotice")
    public void recruitNotice(String resumeFlow, String titleTypeId, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        String resumeInfo = stuUser.getResumeInfo();
        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName());
            dataMap.put("sendComName", stuUser.getSendComName());
            dataMap.put("titleTypeName", DictTypeEnum.getDictName(DictTypeEnum.TitleGenre, titleTypeId));

            dataMap.put("stuBatName", stuUser.getStuBatName());//进修批次

//            String date = DateUtil.addMonth(stuUser.getStuBatName().replace("年", "-").replace("月", ""), Integer.valueOf(stuUser.getStuTimeId()));
//            dataMap.put("stuBatEndName", formate(date));//进修批次+进修时间
            dataMap.put("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            dataMap.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            ExtInfoForm infoForm = docSingupBiz.parseExtInfoXml(resumeInfo);
            StringBuilder speName= new StringBuilder();
            StringBuilder stuTimeName = new StringBuilder();

            if(null!=infoForm && null!=infoForm.getSpeFormList()){
                List<SpeForm> speFormList = infoForm.getSpeFormList();
                for(SpeForm speForm:speFormList){
                    speName.append("+").append(speForm.getSpeName());
                    stuTimeName.append("+").append(speForm.getStuTimeId()+"个月");
                }

                dataMap.put("speName", speName.substring(1).toString());//进修专业
                dataMap.put("stuTimeName", stuTimeName.substring(1).toString());//进修时间
                dataMap.put("trainFee", infoForm.getTotalFee()); //进修总费用
            }


            String workClothsFee = "";
            List<JxTuitionManagement>  coveralList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.WorkCloths.getName());
            if(null!=coveralList && coveralList.size()>0){
                workClothsFee = coveralList.get(0).getCostValue();
                dataMap.put("workClothsFee",workClothsFee);
            }


        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/gzzyjxres/print/admissionNoticeTemeplete.docx";
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "报到通知书.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }


    /**
     * 打印邀请信英文
     */
    @RequestMapping(value = "/printInvitationLetter")
    public void printInvitationLetter(String resumeFlow, String titleTypeId, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        String resumeInfo = stuUser.getResumeInfo();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);

        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName()); //姓名
            dataMap.put("nationalityId",stuUser.getSysUser().getNationalityId());//国家
            dataMap.put("sexId",stuUser.getSysUser().getSexId());//性别

            Date birthDate = sdf.parse(stuUser.getSysUser().getUserBirthday());
            dataMap.put("birthDate",dateFormat.format(birthDate).replace(",","/"));//出生日期

            ExtInfoForm infoForm = docSingupBiz.parseExtInfoXml(resumeInfo);
            StringBuilder speName= new StringBuilder();
            StringBuilder beginEndDate = new StringBuilder();


            List<String> beginEndList = new ArrayList<>();
            if(null!=infoForm && null!=infoForm.getSpeFormList()){
                List<SpeForm> speFormList = infoForm.getSpeFormList();
                for(SpeForm speForm:speFormList){
                    speName.append("+").append(speForm.getSpeId());
                    if(StringUtil.isNotBlank(speForm.getBeginDate())){
                        beginEndList.add(speForm.getBeginDate());
                    }
                    if(StringUtil.isNotBlank(speForm.getEndDate())){
                        beginEndList.add(speForm.getEndDate());
                    }
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
                        String beginDate = dates[0];
                        String finishDate = dates[dates.length-1];
                        Date bd = sdf.parse(beginDate);
                        Date ed = sdf.parse(finishDate);
                        dataMap.put("beginDate",dateFormat.format(bd).replace(",","/"));//进修开始时间
                        beginEndDate.append(dateFormat.format(bd).replace(",","/")).append(" to ").append(dateFormat.format(ed).replace(",","/"));
                }
                dataMap.put("speName", speName.substring(1).toString());//进修专业
                dataMap.put("beginEndDate",beginEndDate.toString());//进修起始时间

            }

            dataMap.put("printDate",DateUtil.getCurrDate());
            //进修费=押金+工作服+学费（月）
            String according =  StringUtil.defaultIfEmpty(infoForm.getAccording(),"0"); //押金
            String coverallNum =  StringUtil.defaultIfEmpty(infoForm.getCoverallNum(),"0");//工作服
            String tuition = StringUtil.defaultIfEmpty(infoForm.getTuition(),"0");//学费

            String workClothsFee = "";
            List<JxTuitionManagement>  coveralList = tuitionManagementBiz.getTuitionByCategory(TuitionCategoryEnum.WorkCloths.getName());
            if(null!=coveralList && coveralList.size()>0){
                workClothsFee = coveralList.get(0).getCostValue();
            }
            dataMap.put("trainFee", String.valueOf(Integer.parseInt(tuition)+
                    Integer.parseInt(according)+Integer.parseInt(coverallNum)*Integer.parseInt(workClothsFee))
            );

        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/gzzyjxres/print/invitationLetterTemplate.docx";
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "InvitationLetter.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
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

        //管理员安排的轮转时间list
        List<String> dateList = new ArrayList<>();
        //所有的轮转时长、科室Map
        Map<String,String> schMonthMap = new HashMap<>(16);
        ScholarSchArrange arrange = new ScholarSchArrange();
        arrange.setDoctorFlow(stuUserResume.getUserFlow());
        //该学员的管理员安排信息
        List<ScholarSchArrange> arrangeList =arrangeBiz.searchArrange(arrange);
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
                        //当轮转时长相同时，取最后一个
                        schMonthMap.put(schMonth,schDeptName);
                    }
                }
            }

            //若轮转时长都超过3个月，则显示第一个。若没有超过3个月的，则显示时间最长的科室
            //存放大于3个月轮转时长、轮转科室
            Map<String,String> schMonthMap2 = new HashMap<>(16);
            //存放小于3个月轮转时长、轮转科室
            Map<String,String> schMonthMap3 = new HashMap<>(16);

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

        //进修专业
        dataMap.put("speName",speName);
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
        if(dates!=null & dates.length>0){
            for(int i=0;i<dates.length-1;i++){
                for(int j=0;j<dates.length-1-i;j++){
                    if(dates[j].compareTo(dates[j+1])>0){
                        String temp=dates[j];
                        dates[j]=dates[j+1];
                        dates[j+1]=temp;
                    }
                }
            }

            regDate = dates[0];
            finishDate = dates[dates.length-1];
        }


        dataMap.put("regDate",regDate); //进修开始时间
        dataMap.put("finishDate",finishDate);//进修结束时间
//        dataMap.put("thisTime",stuUserResume.getModifyTime());
//        dataMap.put("completeNo","furtherEducation");

        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/gzzyjxres/print/zhengshuTemeplete.docx";//结业证书模板
        ServletContext context = request.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "结业证书.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
//            temeplete.save(out);
            out.flush();
        }
    }

    /**
     * 英文版证书
     * @param req
     * @param res
     * @param resumeFlow
     * @throws Exception
     */
    @RequestMapping("/englishCertificate")
    public void englishCertificate(HttpServletRequest req, HttpServletResponse res,String resumeFlow) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserResume stuUser = stuUserResumeBiz.getStuUserByKey(resumeFlow);

        stuUser.setIsGraduation(GlobalConstant.FLAG_Y);
        stuUser.setStuStatusId(StuStatusEnum.Graduation.getId());
        stuUser.setStuStatusName(StuStatusEnum.Graduation.getName());
        stuUserResumeBiz.save(stuUser);
        SysUser sysUser = userBiz.readSysUser(stuUser.getUserFlow());
        ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUser.getResumeInfo());
        //工作日
        int workDays=0;
        String speName="";
        if(null!=extInfo.getSpeFormList() && extInfo.getSpeFormList().size()>0){
            for(SpeForm speForm:extInfo.getSpeFormList()){
                speName+=speForm.getSpeName()+"+";
            }
        }
        //进修科室
        if(speName.length()-1>0){
            speName = speName.substring(0,speName.length()-1);
        }

        dataMap.put("speName",speName.toString());
        if(extInfo!=null && extInfo.getSpeFormList()!=null){

            //计算进修天数 ，去除周末和节假日

            //管理员登记的节假日list
            List<JxNationalHolidaysRegister> holidays = nationalHolidaysRegisterBiz.listNationalHolidays();
            //获取管理员登记的两个日期区间中的所有日期
            List<Date> allDateList = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
            if(null!=holidays && holidays.size()>0){
                for(JxNationalHolidaysRegister holiday:holidays){
                    Date start =null;
                    Date end = null;
                    try {
                        start = simpleDateFormat.parse(holiday.getBeginDate());
                        end = simpleDateFormat.parse(holiday.getEndDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    allDateList.addAll( DateUtil.getBetweenDates(start,end));
                }
            }

            //每次加入之前先清空
            CalendarUtil.clearList();
            if(null!=allDateList && allDateList.size()>0){
                //遍历 加入到 节假日list
                for(Date date:allDateList){
                    CalendarUtil.initHolidayList(simpleDateFormat.format(date));
                }
            }

            //管理员登记的周末为工作日的list
            List<JxWeekendsRegister> weekendsRegisterList =  weekendsRegisterBiz.listWeekends();
            if(null!=weekendsRegisterList && weekendsRegisterList.size()>0){
                for(JxWeekendsRegister weekend:weekendsRegisterList){
                    //遍历 加入到 工作日list
                    CalendarUtil.initWeekendList(weekend.getWeekendDate());
                }
            }

            List<Date> deptDateList = new ArrayList<>(); //学员进修选择的两个日期区间中的所有日期List
            //循环添加学员进修选择的两个日期区间中的所有日期
            if(null!=extInfo.getSpeFormList() && extInfo.getSpeFormList().size()>0){
                List<SpeForm> speFormList = extInfo.getSpeFormList();
                if(null!=speFormList && speFormList.size()>0){
                    for(SpeForm speForm:speFormList){

                        Date start =null;
                        Date end = null;
                        try {
                            start = simpleDateFormat.parse(speForm.getBeginDate());
                            end = simpleDateFormat.parse(speForm.getEndDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        deptDateList.addAll(DateUtil.getBetweenDates(start,end));//循环添加学员进修选择的两个日期区间中的所有日期
                    }

                    //判断是否是节假日
                    int num=0;
                    for(Date date:deptDateList){//遍历学员所选的日期
                        Calendar ca = Calendar.getInstance();
                        ca.setTime(date);//设置当前时间
                        try {
                            boolean k = CalendarUtil.checkHoliday(ca);
                            //如果为节假日，定义一个num为节假日的天数
                            if(k){
                                num++;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //实际的天数 = 所选日期list的大小 - num
                    workDays = deptDateList.size()-num;
                }
            }
        }

        dataMap.put("userName",sysUser.getUserName());//姓名
        dataMap.put("idNo",sysUser.getIdNo());//证件号
        int totalHours=0;//总学时
        dataMap.put("totalDays",String.valueOf(workDays)); //进修天数
        //进修时长（7小时/天）
        totalHours+=workDays*7;
        dataMap.put("totalHours",String.valueOf(totalHours));


//        String modifyTime = stuUserResumeBiz.getStuUserByKey(resumeFlow).getModifyTime();
//        String modifyTime2 = modifyTime.substring(0,4)+"/"+modifyTime.substring(4,6)+"/"+modifyTime.substring(6,8);
//        Date date = new Date(modifyTime2);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
//        String currDate = dateFormat.format(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String certificateDate="";
        //取 结业时间
        if(StringUtil.isNotBlank(stuUser.getCertificateDate())){
            Date d = sdf.parse(stuUser.getCertificateDate());
            certificateDate  =  dateFormat.format(d);
        }

        dataMap.put("date",certificateDate);
        String path = "/jsp/gzzyjxres/print/englishCertificate.docx";
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "英文结业证书.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = res.getOutputStream ();
            (new SaveToZipFile (temeplete)).save (out);
            out.flush ();
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

    /**
     * 批量处理 stuUserResume表中 resumeinfo大字段中信息
     * 为旧数据 进修专业的大字段中添加 speName节点
      * @return
     * @throws Exception
     */
    @RequestMapping("/updateXmlForStuUserResume")
    @ResponseBody
    public Object updateXmlForStuUserResume() throws Exception {
        String result = "结果：";
        StuUserResumeMapper resumeMapper = SpringUtil.getBean(StuUserResumeMapper.class);
        StuUserResumeExample example = new StuUserResumeExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//        .andResumeFlowEqualTo("f874d09520ef4a5aa739163a6df657e3")
        //需要被修改的数据
        List<StuUserResume> resumeList = resumeMapper.selectByExampleWithBLOBs(example);
        int count = 0;
        for (StuUserResume resume : resumeList) {
            String content = resume.getResumeInfo();
            Document dom = DocumentHelper.parseText(content);
            Element root = dom.getRootElement();
            if (root != null) {
                List<Element> items = root.elements("speFormList");
                if (items != null && items.size() > 0){
                    for (Element list : items){
                        List<Element> speForms = list.elements("speForm");
                        for(Element e:speForms){
                            Element speNameElement = e.element("speName");
                            Element speIdElement = e.element("speId");
                            if (null!=speNameElement) {
                                continue;
                            }
                        String old_speId = speIdElement.getText();
                        SysDept sysDept = deptBiz.readSysDeptByName(GZZY_ORG_FLOW,old_speId);
                            String new_speId = "";
                            if(null!=sysDept){
                                 new_speId = sysDept.getDeptFlow();
                            }
                        speNameElement = e.addElement("speName");
                        speNameElement.setText(old_speId);
                        speIdElement.setText(new_speId);
                        resume.setResumeInfo(dom.asXML());
                        //更新resume数据
                        resumeMapper.updateByPrimaryKeySelective(resume);
                        count++;
                        }
                    }
                }
            }
        }
        result += "<br/> 总共：" + resumeList.size() + "条信息";
        result += "<br/> 修改：" + count + "条信息";
        return result;
    }

    /**
     * 批量同步 deptName和deptFlow(由于导入先后顺序不对，导致查询可以看到deptName，但编辑时不同步(deptFlow为空))
     * @return
     */
    @RequestMapping("/updateDeptFlowByDeptName")
    @ResponseBody
    public String updateDeptFlowByDeptName(){
        String result="结果：";

        //带教
        String teacher_role_flow = InitConfig.getSysCfg("res_teacher_role_flow");
        //科主任
        String head_role_flow = InitConfig.getSysCfg("res_head_role_flow");
        //科秘
        String secretary_role_flow = InitConfig.getSysCfg("res_secretary_role_flow");
        List<String> roleFlows = new ArrayList<>();
        roleFlows.add(teacher_role_flow);
        roleFlows.add(head_role_flow);
        roleFlows.add(secretary_role_flow);

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("roleFlowList",roleFlows);
        paramMap.put("wsId",GlobalConstant.RES_WS_ID);
        paramMap.put("orgFlow",GZZY_ORG_FLOW);

        List<SysUser> userList = userBiz.searchUserWithRoleByJx2(paramMap);
        int count = 0;
        if(null!=userList && userList.size()>0){
            for(SysUser user:userList){
                SysDept sysDept = deptBiz.readSysDeptByName(GZZY_ORG_FLOW,user.getDeptName());
                if(null!=sysDept){
                    user.setDeptFlow(sysDept.getDeptFlow());
                    userBiz.saveUser(user);
                    count++;
                }
            }
        }

        result += "<br/> 总共：" + userList.size() + "条信息";
        result += "<br/> 修改：" + count + "条信息";
        return result;
    }

}
