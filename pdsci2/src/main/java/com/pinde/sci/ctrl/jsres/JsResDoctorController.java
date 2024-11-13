package com.pinde.sci.ctrl.jsres;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.jszy.IJszyDoctorAuthBiz;
import com.pinde.sci.biz.jszy.IJszyDoctorReductionBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.jsres.TempMapper;
import com.pinde.sci.enums.jsres.*;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.form.jsres.JsResDoctorInfoForm;
import com.pinde.sci.form.jsres.JykhInfoForm;
import com.pinde.sci.form.jsres.UserInfoExtForm;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.util.jsres.ResultUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tiger
 */
@Controller
@RequestMapping("/jsres/doctor")
public class JsResDoctorController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IJszyDoctorAuthBiz doctorAuthBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResOrgSpeBiz resBaseSpeBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IJsResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IJsResGraduationApplyBiz jsresGraduationApplyBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
    @Autowired
    private IOscaBaseBiz oscaBaseBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private ISchRotationBiz schRotationtBiz;
    @Autowired
    private IResOrgSpeAssignBiz speAssignBiz;
    @Autowired
    private IJszyDoctorReductionBiz reductionBiz;

    static Logger logger = LoggerFactory.getLogger(JsResDoctorController.class);

    /**
     * 住院医师主界面
     */
    @RequestMapping(value = "/index")
    public String index(Model model, Integer currentPage, HttpServletRequest request) {
        setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, GlobalConstant.USER_LIST_PERSONAL);
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow = user.getOrgFlow();
        JsresPowerCfg read = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_guocheng");
        if (read != null) {
            model.addAttribute("cfgValue", read.getCfgValue());
        } else {
            model.addAttribute("cfgValue", "N");
        }
        List<String> titleList = new ArrayList<>();
        titleList.add("培训信息审核结果");
        titleList.add("报名信息审核结果");
        PubMsgExample example = new PubMsgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId())
                .andMsgTitleIn(titleList);
        example.setOrderByClause("MSG_TIME desc");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
        List<PubMsg> msgs = msgBiz.searchMessageWithBLOBs(example);
        if (msgList != null && msgList.size() > 0) {
            model.addAttribute("msgList", msgList);
            int newMsg = 0;
            if (msgs != null && !msgs.isEmpty()) {
                for (PubMsg msg : msgs) {
                    if (!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())) {
                        newMsg++;
                    }
                }
                model.addAttribute("newMsg", newMsg);
            }
        }
        ResDoctor doctor = resDoctorBiz.searchByUserFlow(user.getUserFlow());
        model.addAttribute("doctor", doctor);
//		PubUserResume userResume=userResumeBiz.readPubUserResume(user.getUserFlow());
//		UserResumeExtInfoForm  userResumeExt=null;
//		if(userResume != null){
//			String xmlContent =  userResume.getUserResume();
//			if(StringUtil.isNotBlank(xmlContent)){
//				//xml转换成JavaBean
//				  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				model.addAttribute("userResumeExt", userResumeExt);
//			}
//		}
//		List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
//		String benkeResult=GlobalConstant.FLAG_N;String result=GlobalConstant.FLAG_N;
//		if(sysDictList!=null&&!sysDictList.isEmpty()){
//			if(userResumeExt!=null){
//				for(SysDict dict :sysDictList){
//					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
//						if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
//							benkeResult=GlobalConstant.FLAG_Y;
//						}
//					}
////					if(StringUtil.isNotBlank(doctor.getGraduatedId())){
////						if(dict.getDictId().equals(doctor.getGraduatedId())){
////							result=GlobalConstant.FLAG_Y;
////						}
////					}
//				}
//			}
//		}
//		String school=GlobalConstant.FLAG_N;
//		List<SysDict> schoolList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
//		if(doctor!=null){
//			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())&&JsResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
//				if(schoolList!=null&&!schoolList.isEmpty()){
//					for(SysDict dic:schoolList){
//						if(dic.getDictId().equals(doctor.getWorkOrgId())){
//							school=GlobalConstant.FLAG_Y;
//						}
//					}
//				}
//			}
//		}
//		model.addAttribute("school", school);
//		model.addAttribute("benkeResult", benkeResult);
//		model.addAttribute("result", result);
        int recordCount = 0;
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(user.getUserFlow());
        recordCount = jsResDoctorRecruitBiz.searchDoctorNum(recruit);
        model.addAttribute("recordCount", recordCount);

        model.addAttribute("isPassed", jsResDoctorRecruitBiz.getRecruitStatus(user.getUserFlow()));
        return "jsres/doctor/index";
    }



    @RequestMapping(value = "/chooseType")
    public String chooseType() {
        return "jsres/doctor/chooseType";
    }

    @RequestMapping(value = "/subChooseType")
    @ResponseBody
    public String subChooseType(String trainingTypeId,String trainingTypeName) {
        SysUser user = GlobalContext.getCurrentUser();
        user.setTrainingTypeId(trainingTypeId);
        user.setTrainingTypeName(trainingTypeName);
        if ( userBiz.updateUser(user) >0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = "/looKTrainingTypeId")
    @ResponseBody
    public String looKTrainingTypeId() {
        SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
        if (StringUtil.isNotBlank(user.getTrainingTypeId())){
            return GlobalConstant.RECORD_STATUS_Y;
        }
        return GlobalConstant.RECORD_STATUS_N;
    }


    /**
     * 主页面
     */
    @RequestMapping(value = "/main")
    public String main(Model model) throws ParseException {
        String isRetrain = "N";
        //获取培训记录
        SysUser currUser = GlobalContext.getCurrentUser();
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
            for (ResDoctorRecruit rec : recruitList) {
                if (JsResDoctorAuditStatusEnum.NotSubmit.getId().equals(rec.getAuditStatusId())
                        || JsResDoctorAuditStatusEnum.Auditing.getId().equals(rec.getAuditStatusId())
                        || JsResDoctorAuditStatusEnum.NotPassed.getId().equals(rec.getAuditStatusId())) {
                    model.addAttribute("unPassed", true);
                }
                //20 在培 21结业
                int trainYear = 0;
                if (StringUtil.isNotEmpty(rec.getDoctorStatusId()) && !"21".equals(rec.getDoctorStatusId())) {
                    //培训年限
                    if (StringUtil.isNotEmpty(rec.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                        if ("OneYear".equals(rec.getTrainYear())) {
                            trainYear = 1;
                        } else if ("TwoYear".equals(rec.getTrainYear())) {
                            trainYear = 2;
                        } else if ("ThreeYear".equals(rec.getTrainYear())) {
                            trainYear = 3;
                        }
                    }
                    if (StringUtil.isNotEmpty(rec.getRecruitDate())) {
                        //培训起始时间
                        String recruitDate = rec.getRecruitDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cd = Calendar.getInstance();
                        try {
                            cd.setTime(sdf.parse(recruitDate));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                        String format = sdf.format(cd.getTime());
                        String currDate = DateUtil.getCurrDate();

                        //当前时间
                        Date fromDate1 = sdf.parse(currDate);
                        Date toDate1 = sdf.parse(format);
                        Date d1 = new Date(fromDate1.getTime());
                        Date d2 = new Date(toDate1.getTime());
                        int num = d1.compareTo(d2);
                        //条件满足 为重培
                        if (num > 0) {
                            isRetrain = GlobalConstant.RECORD_STATUS_Y;
                        } else {
                            isRetrain = GlobalConstant.RECORD_STATUS_N;
                        }
                    }
                } else {
                    isRetrain = GlobalConstant.RECORD_STATUS_N;
                }

            }
        }
        ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
        docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        List<ResDocotrDelayTeturn> backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn, null, null, null);
//        List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.ReturnTraining.getId(), currUser.getUserFlow());
        model.addAttribute("resRecList", backList);
        //如果有退赔记录 则不根据时间判断
        if (CollectionUtils.isNotEmpty(backList)) {
            for (ResDocotrDelayTeturn resDocotrDelayTeturn : backList) {
                if (StringUtil.isNotEmpty(resDocotrDelayTeturn.getAuditStatusName()) && "审核通过".equals(resDocotrDelayTeturn.getAuditStatusName())) {
                    List<ResDoctorRecruit> collect = recruitList.stream().filter(ResDoctorRecruit -> ResDoctorRecruit.getDoctorStatusId().equals("20")).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        isRetrain = GlobalConstant.RECORD_STATUS_Y;
                    }
                }
            }
        }
        model.addAttribute("isRetrain", isRetrain);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<ResDocotrDelayTeturn> delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, null, null);
//        List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), currUser.getUserFlow());
        model.addAttribute("delayList", delayList);
        return "jsres/doctor/main";
    }

    @RequestMapping(value = "/toOsca")
    public String toOsca(Model model) {
        String errorPage = "res/edu/student/errorView";
        SysUser currUser = GlobalContext.getCurrentUser();
        if (currUser == null) {
            model.addAttribute("errorMeg", "用户未登录或超时未操作");
            return errorPage;
        }
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(currUser.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
        if (role == null) {
            model.addAttribute("errorMeg", "角色未赋权!");
            return errorPage;
        }

        String roleFlow = role.getRoleFlow();
        if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
            //招录学员绑定临床技能考核学员角色flow
            oscaBaseBiz.bindDoctorRole(currUser.getUserFlow());
            //加载系统权限
            loginBiz.loadSysRoleOsce(currUser.getUserFlow());
            return "redirect:/main?time=" + new Date();
        } else {
            model.addAttribute("errorMeg", "无权限!");
            return errorPage;
        }
    }

    /**
     * 弹出上传退培附件窗口
     * 1.用于融合以前的医师退培管理，基地没有上传附件但是已经退培，现需由省厅审核故可以补传附件
     * （注：以前版本中退培学员由基地负责（无需经省厅同意），在基地退培时将医师信息置为空，
     * 经过现版本优化可能会出现与以前医师信息出现无法融合的问题）
     * 2.需求经优化后，退培附件为必传，故退培信息不会出现没有附件的记录
     *
     * @param model
     * @param recordFlow
     * @return
     */
    @RequestMapping(value = "/showUpload")
    public String showUpload(Model model, String recordFlow) {
        model.addAttribute("recordFlow", recordFlow);
        return "jsres/uploadBackFile";
    }

    /**
     * 该方法用于多附件上传
     * 给定记录的主键（页面需包含input元素含有name="recordFlow"属性）
     *
     * @param recordFlow
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveBackFile")
    @ResponseBody
    public String saveBackFile(String recordFlow, HttpServletRequest request) {
        //以下为多文件上传********************************************
        //创建一个通用的多部分解析器
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
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if (files != null && files.size() > 0) {
                    for (MultipartFile file : files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        if (file.getSize() > 10 * 1024 * 1024) {
                            return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + "10M";
                        }
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "returnImg" + File.separator + dateString;
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
                            String filePath = File.separator + "returnImg" + File.separator + dateString + File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setProductType("退培文件");
                            //根据记录的主键存入pubFile中，查询时直接根据fileBiz.searchByProductFlow(recordFlow);返回的是List<PubFile>
                            pubFile.setProductFlow(recordFlow);
                            pubFile.setFileFlow(PkUtil.getUUID());
                            int count = fileBiz.saveFile(pubFile);
                            if (count == 0) {
                                return GlobalConstant.SAVE_FAIL;
                            }
                        }
                    }
                }
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }
        }
        //以上为多文件上传********************************************
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 延期操作
     *
     * @throws DocumentException
     */
    @RequestMapping(value = "/delay")
    public String delay(Model model, ResDocotrDelayTeturn docotrDelayTeturn, Integer currentPage, String datas[],
                        HttpServletRequest request, String userFlow, String orgFlow0, String seeFlag) throws DocumentException {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            if (orgs != null && !orgs.isEmpty()) {
                for (SysOrg o : orgs) {
                    orgFlowList.add(o.getOrgFlow());
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();
        SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            docotrDelayTeturn.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
            model.addAttribute("JointOrgCount", orgList.size());
            orgList.add(0, org);
            if (StringUtil.isBlank(orgFlow0)) {
                orgFlow0 = org.getOrgFlow();
            } else if ("all".equals(orgFlow0)) {
                orgFlowList.add(docotrDelayTeturn.getOrgFlow());
                docotrDelayTeturn.setOrgFlow("");
                if (orgList != null && orgList.size() > 0) {
                    for (SysOrg so : orgList) {
                        orgFlowList.add(so.getOrgFlow());
                    }
                }
            } else {
                docotrDelayTeturn.setOrgFlow(orgFlow0);
            }
            model.addAttribute("orgFlow", orgFlow0);
            model.addAttribute("orgList", orgList);
        }
//		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//			model.addAttribute("countryOrgFlag","Y");
//			if(null != jointOrg && jointOrg.equals("checked")){
//				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
//				docotrDelayTeturn.setOrgFlow("");
//				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//				if(null != jointOrgs && jointOrgs.size() > 0){
//					for(ResJointOrg so : jointOrgs){
//						orgFlowList.add(so.getJointOrgFlow());
//					}
//				}
//			}
//		}
//        List<ResRec> resRecList = resRecBiz.searchInfo(resRec, operUserFlowList, orgFlowList);
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
        if ("Y".equals(seeFlag)) {
            orgFlowList = null;
            docotrDelayTeturn.setOrgFlow("");
        }
        if (null == currentPage)
            currentPage = 1;
        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfoNew(docotrDelayTeturn, orgFlowList, null, docTypeList);
        model.addAttribute("resRecList", resRecList);
        return "jsres/global/doctor/delayInfo";
    }

    /**
     * 延期操作
     *
     * @throws DocumentException
     */
    @RequestMapping(value = "/delayAcc")
    public String delayAcc(Model model, ResDocotrDelayTeturn docotrDelayTeturn, Integer currentPage, String datas[],
                           HttpServletRequest request, String userFlow, String orgFlow0, String seeFlag) throws DocumentException {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            if (orgs != null && !orgs.isEmpty()) {
                for (SysOrg o : orgs) {
                    orgFlowList.add(o.getOrgFlow());
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();
        SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            docotrDelayTeturn.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
            model.addAttribute("JointOrgCount", orgList.size());
            orgList.add(0, org);
            if (StringUtil.isBlank(orgFlow0)) {
                orgFlow0 = org.getOrgFlow();
            } else if ("all".equals(orgFlow0)) {
                orgFlowList.add(docotrDelayTeturn.getOrgFlow());
                docotrDelayTeturn.setOrgFlow("");
                if (orgList != null && orgList.size() > 0) {
                    for (SysOrg so : orgList) {
                        orgFlowList.add(so.getOrgFlow());
                    }
                }
            } else {
                docotrDelayTeturn.setOrgFlow(orgFlow0);
            }
            model.addAttribute("orgFlow", orgFlow0);
            model.addAttribute("orgList", orgList);
        }
//		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//			model.addAttribute("countryOrgFlag","Y");
//			if(null != jointOrg && jointOrg.equals("checked")){
//				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
//				docotrDelayTeturn.setOrgFlow("");
//				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//				if(null != jointOrgs && jointOrgs.size() > 0){
//					for(ResJointOrg so : jointOrgs){
//						orgFlowList.add(so.getJointOrgFlow());
//					}
//				}
//			}
//		}
//        List<ResRec> resRecList = resRecBiz.searchInfo(resRec, operUserFlowList, orgFlowList);
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
        if ("Y".equals(seeFlag)) {
            orgFlowList = null;
            docotrDelayTeturn.setOrgFlow("");
        }
        if (null == currentPage)
            currentPage = 1;
        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfoNew(docotrDelayTeturn, orgFlowList, null, docTypeList);
        model.addAttribute("resRecList", resRecList);
        return "jsres/global/doctor/delayInfoAcc";
    }

    @RequestMapping(value = "/delayNew")
    public String delayNew(Model model) {
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchOrgListNew(org);
        model.addAttribute("orgs", orgs);
        return "jsres/global/doctor/delayInfoMain";
    }

    @RequestMapping(value = "/delayNewAcc")
    public String delayNewAcc(Model model) {
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchOrgListNew(org);
        model.addAttribute("orgs", orgs);
        return "jsres/global/doctor/delayInfoMainAcc";
    }

    @RequestMapping(value = "/delayList")
    public String delayList(Model model, ResDocotrDelayTeturn docotrDelayTeturn, Integer currentPage, String datas[],
                            HttpServletRequest request, String userFlow, String orgFlow0, String seeFlag, String jointOrgFlag, String cityId) throws DocumentException {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();

        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
//		if("Y".equals(seeFlag)) {
//			orgFlowList=null;
//			docotrDelayTeturn.setOrgFlow("");
//		}
        if (null == currentPage)
            currentPage = 1;
        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String ss : datas) {
                docTypeList.add(ss);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        PageHelper.startPage(currentPage, getPageSize(request));
//		List<ResDocotrDelayTeturn>  resRecList = resDoctorDelayTeturnBiz.searchInfoNew(docotrDelayTeturn,orgFlowList,null,docTypeList);
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, null, docTypeList, null);
        model.addAttribute("resRecList", resRecList);
        return "jsres/global/doctor/delayInfoList";
    }

    @RequestMapping(value = "/delayListAcc")
    public String delayListAcc(Model model, ResDocotrDelayTeturn docotrDelayTeturn, Integer currentPage, String datas[],
                               HttpServletRequest request, String userFlow, String orgFlow0, String seeFlag, String jointOrgFlag, String cityId) throws DocumentException {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();

        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
//		if("Y".equals(seeFlag)) {
//			orgFlowList=null;
//			docotrDelayTeturn.setOrgFlow("");
//		}
        if (null == currentPage)
            currentPage = 1;
        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String ss : datas) {
                docTypeList.add(ss);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        PageHelper.startPage(currentPage, getPageSize(request));
//		List<ResDocotrDelayTeturn>  resRecList = resDoctorDelayTeturnBiz.searchInfoNew(docotrDelayTeturn,orgFlowList,null,docTypeList);
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, null, docTypeList, null);
        model.addAttribute("resRecList", resRecList);
        return "jsres/global/doctor/delayInfoListAcc";
    }

    @RequestMapping(value = "/exportDelayList")
    public void exportDelayList(ResDocotrDelayTeturn docotrDelayTeturn, String datas[], HttpServletResponse response, String userFlow,
                                String orgFlow0, String seeFlag, String jointOrgFlag, String cityId) throws Exception {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);

        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();

        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
//		if("Y".equals(seeFlag)) {
//			orgFlowList=null;
//			docotrDelayTeturn.setOrgFlow("");
//		}

        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String ss : datas) {
                docTypeList.add(ss);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, null, docTypeList, null);

        String[] titles = new String[]{
                "培训基地",
                "学员姓名",
                "培训类型",
                "培训专业",
                "届别",
                "结业考核年份",
                "延期原因"
        };

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("基地变更信息");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
        ExcelUtile.addMergedRegionIfNotExists(sheet,0, 0, 0, 6);

        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        if (resRecList != null && !resRecList.isEmpty()) {
            for (int i = 0; i < resRecList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                ResDocotrDelayTeturn rddt = resRecList.get(i);
                resultList = new String[]{
                        rddt.getOrgName() == null ? "" : rddt.getOrgName(),
                        rddt.getDoctorName() == null ? "" : rddt.getDoctorName(),
                        rddt.getTrainingTypeName() == null ? "" : rddt.getTrainingTypeName(),
                        rddt.getTrainingSpeName() == null ? "" : rddt.getTrainingSpeName(),
                        rddt.getSessionNumber() == null ? "" : rddt.getSessionNumber(),
                        rddt.getGraduationYear() == null ? "" : rddt.getGraduationYear(),
                        rddt.getDelayreason() == null ? "" : rddt.getDelayreason()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "基地变更信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/exportDelayListAcc")
    public void exportDelayListAcc(ResDocotrDelayTeturn docotrDelayTeturn, String datas[], HttpServletResponse response, String userFlow,
                                   String orgFlow0, String seeFlag, String jointOrgFlag, String cityId) throws Exception {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);

        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        List<SysOrg> orgList = new ArrayList<>();

        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
        }
//		if("Y".equals(seeFlag)) {
//			orgFlowList=null;
//			docotrDelayTeturn.setOrgFlow("");
//		}

        //参数flags为查询通过或不通过时用
        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String ss : datas) {
                docTypeList.add(ss);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, null, docTypeList, null);

        String[] titles = new String[]{
                "培训基地",
                "学员姓名",
                "培训类型",
                "培训专业",
                "届别",
                "结业考核年份",
                "延期原因"
        };

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("基地变更信息");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        if (resRecList != null && !resRecList.isEmpty()) {
            for (int i = 0; i < resRecList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                ResDocotrDelayTeturn rddt = resRecList.get(i);
                resultList = new String[]{
                        rddt.getOrgName() == null ? "" : rddt.getOrgName(),
                        rddt.getDoctorName() == null ? "" : rddt.getDoctorName(),
                        rddt.getTrainingTypeName() == null ? "" : rddt.getTrainingTypeName(),
                        rddt.getTrainingSpeName() == null ? "" : rddt.getTrainingSpeName(),
                        rddt.getSessionNumber() == null ? "" : rddt.getSessionNumber(),
                        rddt.getGraduationYear() == null ? "" : rddt.getGraduationYear(),
                        rddt.getDelayreason() == null ? "" : rddt.getDelayreason()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "基地变更信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }


    /**
     * 获取退培信息  住院医师
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/backTrainInfo")
    public String backTrainInfo(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow, Integer currentPage, HttpServletRequest request,
                                String sessionNumber, String seeFlag, String datas[],
                                String orgFlow0, String roleFlag, String isAudio) throws DocumentException {
        Map<Object, Object> backInfoMap = null;
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL) && StringUtil.isBlank(seeFlag)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> orgFlowList = new ArrayList<String>();
        if (StringUtil.isBlank(docotrDelayTeturn.getOrgFlow()) && getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            if (orgs != null && !orgs.isEmpty()) {
                for (SysOrg org : orgs) {
                    orgFlowList.add(org.getOrgFlow());
                }
            }
        }
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        String tpOrg = docotrDelayTeturn.getOrgFlow();
        List<SysOrg> orgList = new ArrayList<>();
        orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount", orgList.size());
        SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0, org);
//		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//			model.addAttribute("countryOrgFlag","Y");
//			if(null != jointOrg && jointOrg.equals("checked")){
//				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
//				docotrDelayTeturn.setOrgFlow("");
//				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//				if(null != jointOrgs && jointOrgs.size() > 0){
//					for(ResJointOrg so : jointOrgs){
//						orgFlowList.add(so.getJointOrgFlow());
//					}
//				}
//			}
//		}
        if (StringUtil.isBlank(orgFlow0)) {
            orgFlow0 = org.getOrgFlow();
        } else if ("all".equals(orgFlow0)) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            docotrDelayTeturn.setOrgFlow("");
            if (orgList != null && orgList.size() > 0) {
                for (SysOrg so : orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        } else {
            docotrDelayTeturn.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow", orgFlow0);
        model.addAttribute("orgList", orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         //下面的if是退培功能经优化后追加的，
         // 省厅查询审核以后的退培信息即不是待审核状态（2），信息auditOpinion只可能为通过（1）或不通过（0），
         // 条件是存放在res_rec中的大字段res_content故查询时需要解析很费时间
         if(StringUtil.isNotBlank(roleFlag)&&GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
         if(StringUtil.isNotBlank(auditOpinion)){
         currdoctor.setEmergencyName(auditOpinion);//用于查找审核过的退赔信息（通过（1）或不通过（0））
         }else {
         currdoctor.setGroupId("3");//用于查找已经审核过的退赔信息（无论通过与否都查）
         }
         }
         **/
        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add(docotrDelayTeturn.getAuditStatusId());
        } else {
            flags.add("1");
            flags.add("0");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            flags.add("2");
            flags.add(ResBaseStatusEnum.ChargeAudit.getId());
            flags.add(ResBaseStatusEnum.ChargeNotPassed.getId());
        }
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        //参数flags为查询通过或不通过时用
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, orgFlowList, flags, docTypeList);
//		List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        //退培比例
        //当勾选协同基地时退培比例为：省厅同意的退培人数（含协同基地）/培训医师总数（含协同基地）
        //当未勾选协同基地时退培比例为：省厅同意的退培人数（不含协同基地）/培训医师总数（不含协同基地）
        if (StringUtil.isNotBlank(tpOrg) && StringUtil.isNotBlank(sessionNumber)) {
            List<Map<String, String>> list = new ArrayList<>();
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
//				list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, null, null, null);
            } else {
                List<String> orgFlowList2 = new ArrayList<>();
                orgFlowList2.add(tpOrg);
//				list=resRecBiz.findTrainCharts(orgFlowList2,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList2, sessionNumber, null, null, null);
            }
            DecimalFormat df = new DecimalFormat("0.00%");
            ResDoctor doctor = new ResDoctor();
            if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
                doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
            }
            doctor.setSessionNumber(sessionNumber);
            int count = 0;
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
                for (int i = 0; i < orgFlowList.size(); i++) {
                    doctor.setOrgFlow(orgFlowList.get(i));
                    count += resDoctorBiz.findDoctorCountInOrg(doctor);
                }
            } else {
                count = resDoctorBiz.findDoctorCountInOrg(doctor);
            }
            float percent = 0;
            int resRecListSize = 0;
            if (list != null && !list.isEmpty() && count != 0) {
                for (Map<String, String> recMap : list) {
                    String temp = recMap.get("countNum");
                    resRecListSize += Integer.parseInt(temp);
                }
            }
            if (resRecList != null && !resRecList.isEmpty() && count != 0) {
                percent = (float) resRecListSize / (float) count;
                model.addAttribute("percent", df.format(percent));
                model.addAttribute("resRecListSize", resRecListSize);
            } else {
                model.addAttribute("percent", 0 + "%");
            }
        }
        //下面的if是退培功能经优化后追加的，
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            return "jsres/global/backTrainInfo4global";
        }
        return "jsres/backTrainInfo";
    }


    /**
     * 获取退培信息 助理全科
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/backTrainInfoAcc")
    public String backTrainInfoAcc(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow, Integer currentPage, HttpServletRequest request,
                                   String sessionNumber, String seeFlag, String datas[],
                                   String orgFlow0, String roleFlag, String isAudio) throws DocumentException {
        Map<Object, Object> backInfoMap = null;
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL) && StringUtil.isBlank(seeFlag)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                org.setOrgCityId(s.getOrgCityId());
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> orgFlowList = new ArrayList<String>();
        if (StringUtil.isBlank(docotrDelayTeturn.getOrgFlow()) && getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            if (orgs != null && !orgs.isEmpty()) {
                for (SysOrg org : orgs) {
                    orgFlowList.add(org.getOrgFlow());
                }
            }
        }
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        String tpOrg = docotrDelayTeturn.getOrgFlow();
        List<SysOrg> orgList = new ArrayList<>();
        orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount", orgList.size());
        SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0, org);
//		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//			model.addAttribute("countryOrgFlag","Y");
//			if(null != jointOrg && jointOrg.equals("checked")){
//				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
//				docotrDelayTeturn.setOrgFlow("");
//				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//				if(null != jointOrgs && jointOrgs.size() > 0){
//					for(ResJointOrg so : jointOrgs){
//						orgFlowList.add(so.getJointOrgFlow());
//					}
//				}
//			}
//		}
        if (StringUtil.isBlank(orgFlow0)) {
            orgFlow0 = org.getOrgFlow();
        } else if ("all".equals(orgFlow0)) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            docotrDelayTeturn.setOrgFlow("");
            if (orgList != null && orgList.size() > 0) {
                for (SysOrg so : orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        } else {
            docotrDelayTeturn.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow", orgFlow0);
        model.addAttribute("orgList", orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         //下面的if是退培功能经优化后追加的，
         // 省厅查询审核以后的退培信息即不是待审核状态（2），信息auditOpinion只可能为通过（1）或不通过（0），
         // 条件是存放在res_rec中的大字段res_content故查询时需要解析很费时间
         if(StringUtil.isNotBlank(roleFlag)&&GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
         if(StringUtil.isNotBlank(auditOpinion)){
         currdoctor.setEmergencyName(auditOpinion);//用于查找审核过的退赔信息（通过（1）或不通过（0））
         }else {
         currdoctor.setGroupId("3");//用于查找已经审核过的退赔信息（无论通过与否都查）
         }
         }
         **/
        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add(docotrDelayTeturn.getAuditStatusId());
        } else {
            flags.add("1");
            flags.add("0");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            flags.add("2");
            flags.add(ResBaseStatusEnum.ChargeAudit.getId());
            flags.add(ResBaseStatusEnum.ChargeNotPassed.getId());
        }
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        //参数flags为查询通过或不通过时用
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, orgFlowList, flags, docTypeList);
//		List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        //退培比例
        //当勾选协同基地时退培比例为：省厅同意的退培人数（含协同基地）/培训医师总数（含协同基地）
        //当未勾选协同基地时退培比例为：省厅同意的退培人数（不含协同基地）/培训医师总数（不含协同基地）
        if (StringUtil.isNotBlank(tpOrg) && StringUtil.isNotBlank(sessionNumber)) {
            List<Map<String, String>> list = new ArrayList<>();
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
//				list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, null, null, null);
            } else {
                List<String> orgFlowList2 = new ArrayList<>();
                orgFlowList2.add(tpOrg);
//				list=resRecBiz.findTrainCharts(orgFlowList2,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList2, sessionNumber, null, null, null);
            }
            DecimalFormat df = new DecimalFormat("0.00%");
            ResDoctor doctor = new ResDoctor();
            if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
                doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
            }
            doctor.setSessionNumber(sessionNumber);
            int count = 0;
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
                for (int i = 0; i < orgFlowList.size(); i++) {
                    doctor.setOrgFlow(orgFlowList.get(i));
                    count += resDoctorBiz.findDoctorCountInOrg(doctor);
                }
            } else {
                count = resDoctorBiz.findDoctorCountInOrg(doctor);
            }
            float percent = 0;
            int resRecListSize = 0;
            if (list != null && !list.isEmpty() && count != 0) {
                for (Map<String, String> recMap : list) {
                    String temp = recMap.get("countNum");
                    resRecListSize += Integer.parseInt(temp);
                }
            }
            if (resRecList != null && !resRecList.isEmpty() && count != 0) {
                percent = (float) resRecListSize / (float) count;
                model.addAttribute("percent", df.format(percent));
                model.addAttribute("resRecListSize", resRecListSize);
            } else {
                model.addAttribute("percent", 0 + "%");
            }
        }
        //下面的if是退培功能经优化后追加的，
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            return "jsres/global/backTrainInfo4globalAcc";
        }
        return "jsres/backTrainInfoAcc";
    }

    @RequestMapping(value = {"/backTrainInfoMain"})
    public String backTrainInfoMain(Model model,String roleFlag) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        SysOrg sysorg4Search = new SysOrg();
        sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            //市里所有医院
            SysOrg org = new SysOrg();
            org.setOrgProvId(currentOrg.getOrgProvId());
            org.setOrgCityId(currentOrg.getOrgCityId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
            return "jsres/city/backTrainInfo4changeMain";
        }else{
            //省里所有医院
            List<SysOrg> orgs = orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            return "jsres/global/backTrainInfo4globalMain";
        }
    }

    @RequestMapping(value = {"/backTrainInfoMainAcc"})
    public String backTrainInfoMainAcc(Model model,String roleFlag) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        SysOrg sysorg4Search = new SysOrg();
        sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            //市里所有医院
            SysOrg org = new SysOrg();
            org.setOrgProvId(currentOrg.getOrgProvId());
            org.setOrgCityId(currentOrg.getOrgCityId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
            return "jsres/city/backTrainInfo4changeMainAcc";
        }else{
            //省里所有医院
            List<SysOrg> orgs = orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            return "jsres/global/backTrainInfo4globalMainAcc";
        }
    }



    @RequestMapping(value = "/backTrainInfoList")
    public String backTrainInfoList(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow, Integer currentPage, HttpServletRequest request,
                                    String sessionNumber, String seeFlag, String datas[], String orgFlow0, String roleFlag, String jointOrgFlag, String cityId) throws DocumentException {
        Map<Object, Object> backInfoMap = null;
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL) && StringUtil.isBlank(seeFlag)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                org.setOrgCityId(cityId);
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        String tpOrg = docotrDelayTeturn.getOrgFlow();
        List<SysOrg> orgList = new ArrayList<>();
        orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount", orgList.size());
        SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0, org);
        if (StringUtil.isBlank(orgFlow0)) {
            orgFlow0 = org.getOrgFlow();
        } else if ("all".equals(orgFlow0)) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            docotrDelayTeturn.setOrgFlow("");
            if (orgList != null && orgList.size() > 0) {
                for (SysOrg so : orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        } else {
            docotrDelayTeturn.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow", orgFlow0);
        model.addAttribute("orgList", orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         //下面的if是退培功能经优化后追加的，
         // 省厅查询审核以后的退培信息即不是待审核状态（2），信息auditOpinion只可能为通过（1）或不通过（0），
         // 条件是存放在res_rec中的大字段res_content故查询时需要解析很费时间
         if(StringUtil.isNotBlank(roleFlag)&&GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
         if(StringUtil.isNotBlank(auditOpinion)){
         currdoctor.setEmergencyName(auditOpinion);//用于查找审核过的退赔信息（通过（1）或不通过（0））
         }else {
         currdoctor.setGroupId("3");//用于查找已经审核过的退赔信息（无论通过与否都查）
         }
         }
         **/
        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add(docotrDelayTeturn.getAuditStatusId());
        } else {
            flags.add("1");
            flags.add("0");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            flags.add("2");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            flags.add("1");
        }
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        //参数flags为查询通过或不通过时用
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);
//		List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        //退培比例
        //当勾选协同基地时退培比例为：省厅同意的退培人数（含协同基地）/培训医师总数（含协同基地）
        //当未勾选协同基地时退培比例为：省厅同意的退培人数（不含协同基地）/培训医师总数（不含协同基地）
        if (StringUtil.isNotBlank(tpOrg) && StringUtil.isNotBlank(sessionNumber)) {
            List<Map<String, String>> list = new ArrayList<>();
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
//				list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, null, null, null);
            } else {
                List<String> orgFlowList2 = new ArrayList<>();
                orgFlowList2.add(tpOrg);
//				list=resRecBiz.findTrainCharts(orgFlowList2,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList2, sessionNumber, null, null, null);
            }
            DecimalFormat df = new DecimalFormat("0.00%");
            ResDoctor doctor = new ResDoctor();
            if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
                doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
            }
            doctor.setSessionNumber(sessionNumber);
            int count = 0;
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
                for (int i = 0; i < orgFlowList.size(); i++) {
                    doctor.setOrgFlow(orgFlowList.get(i));
                    count += resDoctorBiz.findDoctorCountInOrg(doctor);
                }
            } else {
                count = resDoctorBiz.findDoctorCountInOrg(doctor);
            }
            float percent = 0;
            int resRecListSize = 0;
            if (list != null && !list.isEmpty() && count != 0) {
                for (Map<String, String> recMap : list) {
                    String temp = recMap.get("countNum");
                    resRecListSize += Integer.parseInt(temp);
                }
            }
            if (resRecList != null && !resRecList.isEmpty() && count != 0) {
                percent = (float) resRecListSize / (float) count;
                model.addAttribute("percent", df.format(percent));
                model.addAttribute("resRecListSize", resRecListSize);
            } else {
                model.addAttribute("percent", 0 + "%");
            }
        }
        //下面的if是退培功能经优化后追加的，
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            return "jsres/global/backTrainInfo4globalList";
        }
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            return "jsres/city/backTrainInfo4changeList";
        }
        return "jsres/backTrainInfo";
    }

    @RequestMapping(value = "/backTrainInfoListAcc")
    public String backTrainInfoListAcc(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow, Integer currentPage, HttpServletRequest request,
                                       String sessionNumber, String seeFlag, String datas[], String orgFlow0, String roleFlag, String jointOrgFlag, String cityId) throws DocumentException {
        Map<Object, Object> backInfoMap = null;
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL) && StringUtil.isBlank(seeFlag)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                org.setOrgCityId(cityId);
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        String tpOrg = docotrDelayTeturn.getOrgFlow();
        List<SysOrg> orgList = new ArrayList<>();
        orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("JointOrgCount", orgList.size());
        SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
        orgList.add(0, org);
        if (StringUtil.isBlank(orgFlow0)) {
            orgFlow0 = org.getOrgFlow();
        } else if ("all".equals(orgFlow0)) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            docotrDelayTeturn.setOrgFlow("");
            if (orgList != null && orgList.size() > 0) {
                for (SysOrg so : orgList) {
                    orgFlowList.add(so.getOrgFlow());
                }
            }
        } else {
            docotrDelayTeturn.setOrgFlow(orgFlow0);
        }
        model.addAttribute("orgFlow", orgFlow0);
        model.addAttribute("orgList", orgList);
        PageHelper.startPage(currentPage, getPageSize(request));
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         //下面的if是退培功能经优化后追加的，
         // 省厅查询审核以后的退培信息即不是待审核状态（2），信息auditOpinion只可能为通过（1）或不通过（0），
         // 条件是存放在res_rec中的大字段res_content故查询时需要解析很费时间
         if(StringUtil.isNotBlank(roleFlag)&&GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
         if(StringUtil.isNotBlank(auditOpinion)){
         currdoctor.setEmergencyName(auditOpinion);//用于查找审核过的退赔信息（通过（1）或不通过（0））
         }else {
         currdoctor.setGroupId("3");//用于查找已经审核过的退赔信息（无论通过与否都查）
         }
         }
         **/
        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add(docotrDelayTeturn.getAuditStatusId());
        } else {
            flags.add("1");
            flags.add("0");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            flags.add("2");
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            flags.add("1");
        }
        if (StringUtil.isNotBlank(userFlow)) {
            docotrDelayTeturn.setDoctorFlow(userFlow);
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        //参数flags为查询通过或不通过时用
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);
//		List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        //退培比例
        //当勾选协同基地时退培比例为：省厅同意的退培人数（含协同基地）/培训医师总数（含协同基地）
        //当未勾选协同基地时退培比例为：省厅同意的退培人数（不含协同基地）/培训医师总数（不含协同基地）
        if (StringUtil.isNotBlank(tpOrg) && StringUtil.isNotBlank(sessionNumber)) {
            List<Map<String, String>> list = new ArrayList<>();
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
//				list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, null, null, null);
            } else {
                List<String> orgFlowList2 = new ArrayList<>();
                orgFlowList2.add(tpOrg);
//				list=resRecBiz.findTrainCharts(orgFlowList2,sessionNumber,null);
                list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList2, sessionNumber, null, null, null);
            }
            DecimalFormat df = new DecimalFormat("0.00%");
            ResDoctor doctor = new ResDoctor();
            if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
                doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
            }
            doctor.setSessionNumber(sessionNumber);
            int count = 0;
            if (null != orgFlowList && orgFlowList.size() > 0) {//有协同基地
                for (int i = 0; i < orgFlowList.size(); i++) {
                    doctor.setOrgFlow(orgFlowList.get(i));
                    count += resDoctorBiz.findDoctorCountInOrg(doctor);
                }
            } else {
                count = resDoctorBiz.findDoctorCountInOrg(doctor);
            }
            float percent = 0;
            int resRecListSize = 0;
            if (list != null && !list.isEmpty() && count != 0) {
                for (Map<String, String> recMap : list) {
                    String temp = recMap.get("countNum");
                    resRecListSize += Integer.parseInt(temp);
                }
            }
            if (resRecList != null && !resRecList.isEmpty() && count != 0) {
                percent = (float) resRecListSize / (float) count;
                model.addAttribute("percent", df.format(percent));
                model.addAttribute("resRecListSize", resRecListSize);
            } else {
                model.addAttribute("percent", 0 + "%");
            }
        }
        //下面的if是退培功能经优化后追加的，
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            return "jsres/global/backTrainInfo4globalListAcc";
        }
        if (StringUtil.isNotBlank(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            return "jsres/city/backTrainInfo4changeListAcc";
        }
        return "jsres/backTrainInfo";
    }

    /**
     * 省厅的退赔医师查询
     * 页面分为两部分
     * 头部标签页（jsres/global/backTrainManage.jsp）
     * 主体功能页:1.jsres/global/backCheck.jsp(省厅审核页面)2.jsres/global/backTrainInfo4global.jsp（省厅查询页面）
     *
     * @return
     */
    @RequestMapping(value = "/globalBackTrainInfo")
    public String globalBackTrainInfo() {
        return "jsres/global/backTrainManage";
    }

    @RequestMapping(value = "/globalBackTrainInfoAcc")
    public String globalBackTrainInfoAcc() {
        return "jsres/global/backTrainManageAcc";
    }

    @RequestMapping(value = "/changeBackTrainInfo")
    public String changeBackTrainInfo() {
        return "jsres/city/backTrainManage";
    }

    @RequestMapping(value = "/changeBackTrainInfoAcc")
    public String changeBackTrainInfoAcc() {
        return "jsres/city/backTrainManageAcc";
    }
    /**
     * 标签点击退赔医师审核（仅省厅角色）
     * 展示待审核退赔医师信息
     *
     * @param docotrDelayTeturn
     * @param model
     * @param userFlow
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/showBackCheck")
    public String showBackCheck(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow,
                                Integer currentPage, HttpServletRequest request, String datas[]) {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        List<String> orgFlowList = new ArrayList<String>();
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
/**
 * 拆分res_rec表时注释掉了
 ResDoctor currdoctor = new ResDoctor();
 currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
 currdoctor.setTrainingSpeName(speName);
 currdoctor.setSessionNumber(sessionNumber);
 currdoctor.setRegNo(reasonId);
 currdoctor.setEmergencyPhone(policyId);
 if(StringUtil.isBlank(isQuery)) {
 if (StringUtil.isBlank(currdoctor.getSessionNumber())) {
 currdoctor.setSessionNumber(DateUtil.getYear());
 }
 }
 currdoctor.setEmergencyName("2");//"0"审核不通过"1"审核通过"2"待审核
 List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
 **/
        //参数flags为查询通过或不通过时用
        List<String> flags = new ArrayList<>();
        flags.add("2");
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s0 : datas) {
                docTypeList.add(s0);
            }
        }
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, orgFlowList, flags, docTypeList);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        return "jsres/global/backTrainCheck";
    }

    @RequestMapping(value = {"/showBackCheckMain"})
    public String showBackCheckMain(Model model,String roleFlag) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        SysOrg sysorg4Search = new SysOrg();
        sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            //市里所有医院
            SysOrg org = new SysOrg();
            org.setOrgProvId(currentOrg.getOrgProvId());
            org.setOrgCityId(currentOrg.getOrgCityId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
            return "/jsres/city/backTrainCheckMain";
        }else{
            //省里所有医院
            List<SysOrg> orgs = orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            return "jsres/global/backTrainCheckMain";
        }

    }

    @RequestMapping(value = {"/showBackCheckMainAcc"})
    public String showBackCheckMainAcc(Model model,String roleFlag) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        SysOrg sysorg4Search = new SysOrg();
        sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            //市里所有医院
            SysOrg org = new SysOrg();
            org.setOrgProvId(currentOrg.getOrgProvId());
            org.setOrgCityId(currentOrg.getOrgCityId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
            return "/jsres/city/backTrainCheckMainAcc";
        }else{
            //省里所有医院
            List<SysOrg> orgs = orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            return "jsres/global/backTrainCheckMainAcc";
        }

    }


    @RequestMapping(value = "/showBackCheckList")
    public String showBackCheckList(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow,
                                    Integer currentPage, HttpServletRequest request, String datas[], String cityId, String jointOrgFlag,String roleFlag) {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        if (StringUtil.isNotBlank(roleFlag) &&GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            org.setOrgCityId(currOrg.getOrgCityId());
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        //参数flags为查询通过或不通过时用
        List<String> flags = new ArrayList<>();
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            flags.add(ResBaseStatusEnum.ChargeAudit.getId());
        }else{
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s0 : datas) {
                docTypeList.add(s0);
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            return "jsres/city/backTrainCheckList";
        }else{
            return "jsres/global/backTrainCheckList";
        }

    }

    @RequestMapping(value = "/showBackCheckListAcc")
    public String showBackCheckListAcc(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow,
                                       Integer currentPage, HttpServletRequest request, String datas[], String cityId, String jointOrgFlag,String roleFlag) {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        if (StringUtil.isNotBlank(roleFlag) &&GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            org.setOrgCityId(currOrg.getOrgCityId());
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        //参数flags为查询通过或不通过时用
        List<String> flags = new ArrayList<>();
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            flags.add(ResBaseStatusEnum.ChargeAudit.getId());
        }else{
            flags.add("2");
        }
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s0 : datas) {
                docTypeList.add(s0);
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);
        Map<String, Object> fileMaps = new HashMap<String, Object>();
        if (resRecList != null && resRecList.size() > 0) {
            for (ResDocotrDelayTeturn back : resRecList) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
                if (files != null && files.size() > 0) {
                    fileMaps.put(back.getRecordFlow(), files);
                }
            }
        }
        model.addAttribute("fileMaps", fileMaps);
        model.addAttribute("resRecList", resRecList);
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            return "jsres/city/backTrainCheckListAcc";
        }else{
            return "jsres/global/backTrainCheckListAcc";
        }

    }

    /**
     * 弹出审核界面
     * 同意value=1，不同意value=0
     *
     * @param model
     * @param recordFlow 用于追踪退培记录
     * @return
     */
    @RequestMapping(value = "/showCheck")
    public String showCheck(Model model, String recordFlow, String roleFlag) {
        model.addAttribute("recordFlow", recordFlow);
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
            return "jsres/city/backCheck";
        }else{
            return "jsres/global/backCheck";
        }
    }

    /**
     * 省厅审核退培记录
     *
     * @param recordFlow
     * @param auditStatusId（审核结果同意value=1，不同意value=0）
     * @param auditOpinion（省厅审核建议）
     * @return
     */
    @RequestMapping(value = "/check")
    public @ResponseBody
    String check(String recordFlow, String auditStatusId, String auditOpinion, String cityAuditOpinion, String roleFlag) {
        int backResult = 0;
//		BackTrainForm backTrainForm = new BackTrainForm();
//		backTrainForm.setAuditStatusId(auditStatusId);
//		if("1".equals(auditStatusId)){
//			backTrainForm.setAuditStatusName(ResBaseStatusEnum.Passed.getName());
//		}
//		if("0".equals(auditStatusId)){
//			backTrainForm.setAuditStatusName(ResBaseStatusEnum.NotPassed.getName());
//		}
//		backTrainForm.setAuditOpinion(auditOpinion);
        ResDocotrDelayTeturn docotrDelayTeturn = null;
        docotrDelayTeturn = resDoctorDelayTeturnBiz.readInfo(recordFlow);
        if(StringUtil.isNotEmpty(roleFlag) && GlobalConstant.USER_LIST_CHARGE.equals(roleFlag) ){
            if ("1".equals(auditStatusId)) {
                docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Auditing.getName());
                docotrDelayTeturn.setAuditStatusId("2");
            }
            if ("0".equals(auditStatusId)) {
                docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.ChargeNotPassed.getId());
                docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.ChargeNotPassed.getName());
            }
            docotrDelayTeturn.setCityAuditOpinion(cityAuditOpinion);
        }else {
            docotrDelayTeturn.setAuditStatusId(auditStatusId);
            if ("1".equals(auditStatusId)) {
                docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Passed.getName());
            }
            if ("0".equals(auditStatusId)) {
                docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.NotPassed.getName());
            }
            docotrDelayTeturn.setAuditOpinion(auditOpinion);
        }

        docotrDelayTeturn.setAuditTime(DateUtil.getCurrentTime());
        docotrDelayTeturn.setAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        docotrDelayTeturn.setAuditUserName(GlobalContext.getCurrentUser().getUserName());
//		ResRec resRec = resRecBiz.readResRec(recordFlow);
        ResDoctor doctor = resDoctorBiz.readDoctor(docotrDelayTeturn.getDoctorFlow());
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (doctor != null) {
            recruit = doctorRecruitBiz.readResDoctorRecruit(docotrDelayTeturn.getRecruitFlow());
            //处理历史数据，可能会存在docotrDelayTeturn 中的recruitflow 是空的
            if (recruit == null) {
                ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
                doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
                doctorRecruit.setOrgFlow(doctor.getOrgFlow());
                doctorRecruit.setCatSpeId(doctor.getTrainingTypeId());
                doctorRecruit.setSpeId(doctor.getTrainingSpeId());
//				doctorRecruit.setAuditStatusId(ResDoctorAuditStatusEnum.NotSubmit.getId());
                recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
            }
        }
        try {
//			backResult = jsResRecBiz.checkBackTrain(resRec,backTrainForm,recruit);
            backResult = resDoctorDelayTeturnBiz.checkBackTrain(docotrDelayTeturn, recruit);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (GlobalConstant.ZERO_LINE == backResult) {
            return GlobalConstant.OPRE_FAIL;
        } else {
            return GlobalConstant.OPRE_SUCCESSED;
        }
    }

    @RequestMapping(value = "/exportForBack")
    public void exportForBack(HttpServletResponse response, ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow, String datas[], String sessionNumber) throws Exception {
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            List<SysOrg> orgs = new ArrayList<SysOrg>();
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         if(StringUtil.isNotBlank(auditStatusId)&&"2".equals(auditStatusId)){
         currdoctor.setEmergencyName("2");//"0"审核不通过"1"审核通过"2"待审核
         }
         if(StringUtil.isNotBlank(auditStatusId)&&"3".equals(auditStatusId)){
         currdoctor.setGroupId("3");//"3"为0或1（审核不通过或审核通过）
         }
         List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, null);
         **/
        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add("2");
        } else {
            flags.add("1");
            flags.add("0");
        }
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, flags, docTypeList);
        DecimalFormat df = new DecimalFormat("0.00%");
        ResDoctor doctor = new ResDoctor();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgName())) {
            doctor.setOrgName(docotrDelayTeturn.getOrgName());
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
        }
        doctor.setSessionNumber(sessionNumber);
        int count = resDoctorBiz.findDoctorCountInOrg(doctor);
        float percent = 0;
        String per = "";
        if (resRecList != null && !resRecList.isEmpty() && count != 0) {
            percent = (float) resRecList.size() / (float) count;
            per = df.format(percent);
        } else {
            per = 0 + "%";
        }
        String flag = GlobalConstant.FLAG_Y;
        if (StringUtil.isBlank(docotrDelayTeturn.getOrgFlow()) || StringUtil.isBlank(sessionNumber)) {
            flag = GlobalConstant.FLAG_N;
        }
        backInfoMap.put(GlobalConstant.FLAG_N, per);
        jsResDoctorBiz.exportForBack2(resRecList, backInfoMap, response, flag);
    }

    @RequestMapping(value = "/exportForBackNew")
    public void exportForBackNew(HttpServletResponse response, ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow,
                                 String datas[], String sessionNumber, String cityId, String jointOrgFlag) throws Exception {
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }

        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            List<SysOrg> orgs = new ArrayList<SysOrg>();
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                org.setOrgCityId(cityId);
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }

        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add("2");
        } else {
            flags.add("1");
            flags.add("0");
        }

        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s0 : datas) {
                docTypeList.add(s0);
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTrainingTypeId("DoctorTrainingSpe");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);

        DecimalFormat df = new DecimalFormat("0.00%");
        ResDoctor doctor = new ResDoctor();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgName())) {
            doctor.setOrgName(docotrDelayTeturn.getOrgName());
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
        }
        doctor.setSessionNumber(sessionNumber);
        int count = resDoctorBiz.findDoctorCountInOrg(doctor);
        float percent = 0;
        String per = "";
        if (resRecList != null && !resRecList.isEmpty() && count != 0) {
            percent = (float) resRecList.size() / (float) count;
            per = df.format(percent);
        } else {
            per = 0 + "%";
        }
        String flag = GlobalConstant.FLAG_Y;
        if (StringUtil.isBlank(docotrDelayTeturn.getOrgFlow()) || StringUtil.isBlank(sessionNumber)) {
            flag = GlobalConstant.FLAG_N;
        }
        backInfoMap.put(GlobalConstant.FLAG_N, per);
        jsResDoctorBiz.exportForBack2(resRecList, backInfoMap, response, flag);
    }

    @RequestMapping(value = "/exportForBackNewAcc")
    public void exportForBackNewAcc(HttpServletResponse response, ResDocotrDelayTeturn docotrDelayTeturn, Model model, String userFlow,
                                    String datas[], String sessionNumber, String cityId, String jointOrgFlag) throws Exception {
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }

        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            List<SysOrg> orgs = new ArrayList<SysOrg>();
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                org.setOrgCityId(cityId);
            }
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }

        List<String> flags = new ArrayList<>();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getAuditStatusId())) {
            flags.add("2");
        } else {
            flags.add("1");
            flags.add("0");
        }

        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg org = new SysOrg();
        SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
        org.setOrgProvId(s.getOrgProvId());
        if (StringUtil.isNotBlank(cityId)) {
            org.setOrgCityId(cityId);
        }
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        orgs = orgBiz.searchAllSysOrg(org);
        model.addAttribute("orgs", orgs);
        List<String> orgFlowList = new ArrayList<String>();
        if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())) {
            for (SysOrg tempOrg : orgs) {
                if ("Y".equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
                orgFlowList.add(tempOrg.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            orgFlowList.add(docotrDelayTeturn.getOrgFlow());
            if ("Y".equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(docotrDelayTeturn.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        orgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s0 : datas) {
                docTypeList.add(s0);
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(docotrDelayTeturn.getSessionNumber())) {
            String[] numbers = docotrDelayTeturn.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                docotrDelayTeturn.setSessionNumber("");
            }
        }
        docotrDelayTeturn.setTrainingTypeId("AssiGeneral");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo2(docotrDelayTeturn, orgFlowList, flags, docTypeList, sessionNumbers);

        DecimalFormat df = new DecimalFormat("0.00%");
        ResDoctor doctor = new ResDoctor();
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgName())) {
            doctor.setOrgName(docotrDelayTeturn.getOrgName());
        }
        if (StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())) {
            doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
        }
        doctor.setSessionNumber(sessionNumber);
        int count = resDoctorBiz.findDoctorCountInOrg(doctor);
        float percent = 0;
        String per = "";
        if (resRecList != null && !resRecList.isEmpty() && count != 0) {
            percent = (float) resRecList.size() / (float) count;
            per = df.format(percent);
        } else {
            per = 0 + "%";
        }
        String flag = GlobalConstant.FLAG_Y;
        if (StringUtil.isBlank(docotrDelayTeturn.getOrgFlow()) || StringUtil.isBlank(sessionNumber)) {
            flag = GlobalConstant.FLAG_N;
        }
        backInfoMap.put(GlobalConstant.FLAG_N, per);
        jsResDoctorBiz.exportForBack2(resRecList, backInfoMap, response, flag);
    }


    /**
     * 退培医师图表
     *
     * @return
     */
    @RequestMapping(value = "/backTrainStatistics")
    public String backTrainStatistics(HttpServletRequest request, Model model) {
        return "jsres/global/backTrainStatistics";
    }

    @RequestMapping(value = "/backTrainStatisticsAcc")
    public String backTrainStatisticsAcc(HttpServletRequest request, Model model) {
        return "jsres/global/backTrainStatisticsAcc";
    }

    /**
     * 导出excel
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportDoctor")
    public void exportDoctor(HttpServletRequest request, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String[] yearDatas, String graduationYear, String orgCityId, String baseFlag, String flag, String isPostpone,String isArmy, String workOrgId) throws Exception {
        String titleYear = "";
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            titleYear = doctor.getSessionNumber().replace(",", "_");
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        String[] headLines = new String[]{
                "住院医师规范化培训" + titleYear + "级招收对象花名册",
                "省（区、市）卫生计生行政部门（盖章）：江苏省卫生和计划生育委员会        填报人：顾丰      联系方式：025-83620704",
        };
        String[] titles = new String[]{
                ":编号",
                "sysUser.userName:姓名",
                "sysUser.sexName:性别",
                "sysUser.userBirthday:出生年月",
                "sysUser.nationName:民族",
                "sysUser.idNo:身份证号码（若为其他证件，需注明）",
                "sysUser.userPhone:联系方式（手机）",
                "sysUser.userEmail:联系方式（邮箱）",
                "userResumeExt.graduatedName:本科毕业院校及专业",
                "userResumeExt.specialized:本科毕业院校及专业",
                "userResumeExt.graduationTime:毕业时间",
                "sysUser.educationName:最高学历",
                "doctor.graduatedName:最高学历毕业院校",
                "doctor.specialized:最高学历毕业专业",
                "doctor.graduationTime:获得最高学历时间",
                "doctor.doctorTypeName:身份类型（单位人/社会人）",
                "doctor.workOrgName:派出单位（限“单位人”填写）",
                "sysUser.workOrgName:派送学校（限“在校专硕”填写）",
                "doctor.orgName:培训基地（若在协同单位，需注明）",
                "doctor.trainingSpeName:培训专业",
                "recruit.recruitDate:参训时间",
                "trainYearMonth:培训开始年月",
                "recruit.trainYear:计划参训时限",
                "trainYears:培训年限",
                "trainEndYearMonth:计划培训结束年月",
                "armyType:军队人员",
        };
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        org.setOrgCityId(orgCityId);
        SysUser exSysUser = GlobalContext.getCurrentUser();
        SysOrg currenOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
        List<String> jointOrgFlowList = new ArrayList<String>();
        List<UserInfoExtForm> userExtForms = new ArrayList<UserInfoExtForm>();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            recruit.setGraduationYear(graduationYear);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            if (OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId()) && StringUtil.isBlank(doctor.getOrgFlow())) {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
                List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
                if (resJointOrgsList != null && !resJointOrgsList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
            if (OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId()) && StringUtil.isNotBlank(doctor.getOrgFlow())) {
                jointOrgFlowList.add(doctor.getOrgFlow());
            }
            if (!OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())) {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            }
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(doctor.getOrgFlow());
            } else {
                SysOrg sysOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(exSysUser.getOrgFlow());
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    if ("Y".equals(flag)) {
                        searchOrg.setOrgLevelId("CountryOrg");
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                } else {
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    if ("Y".equals(flag)) {
//						org.setOrgLevelId("CountryOrg");
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        ResRec resRec = new ResRec();
        resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
//		if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
//			List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
//			if(sendSchools!=null && sendSchools.size()>0){
//				for(SysDict dict :sendSchools){
//					if(doctor.getWorkOrgName().equals(dict.getDictName())){
//						doctor.setWorkOrgId(dict.getDictId());
//					}
//				}
//			}
//		}

        List<String> trainYearList = new ArrayList<String>();
        if (yearDatas != null && yearDatas.length > 0) {
            for (String s : yearDatas) {
                trainYearList.add(s);
            }
        }

        String workOrgName = null;
        ServletContext servletContext2=request.getServletContext();
        if (servletContext2!=null) {
            List<SysDict> sysDictList = (List<SysDict>) servletContext2.getAttribute("dictTypeEnum" + "SendSchool" + "List");
            if(CollectionUtils.isNotEmpty(sysDictList)){
                Map<String, String> cityRelsMap = sysDictList.stream().collect(Collectors.toMap(SysDict::getDictId, SysDict::getDictName, (key1, key2)-> key1));
                if(StringUtils.isNotEmpty(workOrgId)){
                    workOrgName = cityRelsMap.get(workOrgId);
                }
            }
        }

        List<JsDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResume3(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList, trainYearList, sessionNumbers, baseFlag, isPostpone,isArmy, workOrgId, workOrgName);
//		List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
//		Map<Object, Object> orgAndJointNameMap=new HashMap<Object, Object>();
//		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
//			for(Map<String,Object> en:jointOrgs){
//				Object key=en.get("key");
//				Object value=en.get("value");
//				orgAndJointNameMap.put(key, value);
//			}
//		}
        if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
            for (JsDoctorInfoExt d : doctorInfoExts) {
                UserInfoExtForm userInfoExtForm = new UserInfoExtForm();
//				if(orgAndJointNameMap.containsKey(d.getOrgFlow())){
//					d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow())+"("+d.getOrgName()+")");
//				}
                if (StringUtil.isNotBlank(d.getJointOrgFlow())) {
                    d.getResDoctor().setOrgName(d.getOrgName() + "(" + d.getJointOrgName() + ")");
                }
                if (!JsResDocTypeEnum.Company.getId().equals(d.getResDoctor().getDoctorTypeId()) && !JsResDocTypeEnum.CompanyEntrust.getId().equals(d.getResDoctor().getDoctorTypeId())) {
                    d.getResDoctor().setWorkOrgName("");
                }
                String content = d.getUserResume().getUserResume();
                if (StringUtil.isNotBlank(content)) {
                    UserResumeExtInfoForm form = null;
                    try {
                        form = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (form == null) {
                        form = new UserResumeExtInfoForm();
                    }
                    userInfoExtForm.setUserResumeExt(form);
                }
                SysUser su = d.getSysUser();
                String cretTypeId = su.getCretTypeId();
                if (StringUtil.isNotBlank(cretTypeId)) {
                    if (!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
                        su.setIdNo(su.getIdNo() + "(" + CertificateTypeEnum.getNameById(cretTypeId) + ")");
                    }
                }
                if (!"Graduate".equals(d.getResDoctor().getDoctorTypeId())) {
                    su.setWorkOrgName("");
                }
                userInfoExtForm.setSysUser(su);
                userInfoExtForm.setDoctor(d.getResDoctor());
                ResDoctorRecruit recruit3 = d;
                userInfoExtForm.setRecruit(recruit3);
                userInfoExtForm.setArmyType(d.getArmyType());

                // 计算一下 trainYearMonth，trainYears，trainEndYearMonth
                if(StringUtils.isNotEmpty(recruit3.getRecruitDate())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat ymSdf = new SimpleDateFormat("yyyy-MM");
                    Date recruitDate = sdf.parse(recruit3.getRecruitDate());
                    userInfoExtForm.setTrainYearMonth(ymSdf.format(recruitDate));
                    if(StringUtils.isNotEmpty(recruit3.getTrainYear())) {
                        Integer years = JsResTrainYearEnum.getYearsById(recruit3.getTrainYear());
                        if(years != null) {
                            userInfoExtForm.setTrainYears(years.toString());
                            // trainEndYearMonth 是 trainYearMonth + trainYears - 一个月
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(recruitDate);
                            calendar.add(Calendar.YEAR, years);
                            calendar.add(Calendar.MONTH, -1);
                            userInfoExtForm.setTrainEndYearMonth(ymSdf.format(calendar.getTime()));
                        }
                    }
                }

                if (StringUtil.isNotBlank(d.getTrainYear())) {
                    d.setTrainYear(JsResTrainYearEnum.getNameById(d.getTrainYear()));
                }

                userExtForms.add(userInfoExtForm);
            }
        }
        String fileName = "住院医师规范化培训" + titleYear + "级招收对象花名册.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, userExtForms, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    public List<String> searchJointOrgList(String flag, String Flow) {
        List<String> jointOrgFlowList = new ArrayList<String>();
        if (GlobalConstant.FLAG_Y.equals(flag)) {
            List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(Flow);
            if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                for (ResJointOrg jointOrg : resJointOrgList) {
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     * @param sessionNumber
     * @param doctor
     * @param user
     * @param baseId
     * @param jointOrgFlag
     * @param derateFlag
     * @param orgLevel
     * @param datas
     * @param graduationYear
     * @throws Exception
     */
    @RequestMapping(value = "/exportForDetail")
    public void exportForDetail(HttpServletRequest request, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear) throws Exception {
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        SysUser exSysUser = GlobalContext.getCurrentUser();
        List<String> jointOrgFlowList = new ArrayList<String>();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            recruit.setGraduationYear(graduationYear);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
                if (resJointOrgsList != null && !resJointOrgsList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            } else {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            }
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(doctor.getOrgFlow());
            } else {
                SysOrg sysOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(exSysUser.getOrgFlow());
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                } else {
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<JsDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
        jsResDoctorBiz.exportForDetail(doctorInfoExts, response);
    }

    @RequestMapping(value = "/exportForDetail2")
    public void exportForDetail2(HttpServletRequest request, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String[] yearDatas, String graduationYear, String flag) throws Exception {
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        SysUser exSysUser = GlobalContext.getCurrentUser();
        List<String> jointOrgFlowList = new ArrayList<String>();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            recruit.setGraduationYear(graduationYear);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
                if (resJointOrgsList != null && !resJointOrgsList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            } else {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            }
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(doctor.getOrgFlow());
            } else {
                SysOrg sysOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(exSysUser.getOrgFlow());
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    if ("Y".equals(flag)) {
                        searchOrg.setOrgLevelId("CountryOrg");
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                } else {
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    if ("Y".equals(flag)) {
                        org.setOrgLevelId("CountryOrg");
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        List<String> trainYearList = new ArrayList<String>();
        if (yearDatas != null && yearDatas.length > 0) {
            for (String s : yearDatas) {
                trainYearList.add(s);
            }
        }
        String titleYear = "";
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            titleYear = doctor.getSessionNumber().replace(",", "_");
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<JsDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResume1(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList, trainYearList, sessionNumbers, "");
        jsResDoctorBiz.exportForDetail2(doctorInfoExts, titleYear, response);
    }

    /**
     * 个人基本信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/doctorInfo")
    public String doctorInfo(String userFlow, String viewFlag, Model model) throws DocumentException {
        SysUser sysUser = userBiz.readSysUser(userFlow);
        if(GlobalConstant.ROOT_USER_FLOW.equals(sysUser.getUserFlow())){
            sysUser = null;
        }
        ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);

        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && JsResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
                if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
//		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
//		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,userFlow);
//		if (resRec!=null&!resRec.isEmpty()) {
//			model.addAttribute("resRec", resRec.get(0));
//		}
        model.addAttribute("user", sysUser);
        model.addAttribute("doctor", resDoctor);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setGraduationYear(DateUtil.getYear());
        recruit.setDoctorFlow(sysUser.getUserFlow());
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> recruits = jsResDoctorRecruitBiz.readDoctorRecruits(recruit);
        String canSave = "Y";
        if (recruits != null && recruits.size() > 0) {
            String recruitFlow = recruits.get(0).getRecruitFlow();
            JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
            if (apply != null) {
                if (JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                } else if (JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                } else if (JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                }
            }
        }

        boolean isPassed = jsResDoctorRecruitBiz.getRecruitStatus(sysUser.getUserFlow());
        model.addAttribute("isPassed", isPassed);
        model.addAttribute("canSave", canSave);
        String editFlag = InitConfig.getSysCfg("assess_doctor_edit_info");
        //学员保存个人信息放开
        if (GlobalConstant.FLAG_Y.equals(viewFlag)) {
            return "jsres/doctorInfo";
        }
        //"N".equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息 并且审核期间学员是否修改个人信息为否时
        if (!GlobalConstant.FLAG_Y.equals(editFlag) && "N".equals(canSave)) {
            model.addAttribute("isDoctor", "Y");
            return "jsres/doctorInfo";
        }
        return "jsres/doctor/editDoctorInfo";
    }


    /**
     * 微信端个人基本信息
     *
     * @param userFlow
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/doctorInfo2")
    @ResponseBody
    public ResultVo doctorInfo2(String userFlow) throws DocumentException {
        DoctorInfoVo doctorInfoVo = new DoctorInfoVo();
        SysUser sysUser = userBiz.readSysUser(userFlow);
        ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && JsResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
                if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
//		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
//		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,userFlow);
//		if (resRec!=null&!resRec.isEmpty()) {
//			model.addAttribute("resRec", resRec.get(0));
//		}
        doctorInfoVo.setUser(sysUser);
        doctorInfoVo.setDoctor(resDoctor);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    doctorInfoVo.setUserResumeExt(userResumeExt);
                }
            }
        }
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setGraduationYear(DateUtil.getYear());
        recruit.setDoctorFlow(sysUser.getUserFlow());
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> recruits = jsResDoctorRecruitBiz.readDoctorRecruits(recruit);
        String canSave = "Y";
        if (recruits != null && recruits.size() > 0) {
            String recruitFlow = recruits.get(0).getRecruitFlow();
            JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
            if (apply != null) {
                if (JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                } else if (JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                } else if (JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId())) {
                    canSave = "N";
                }
            }
        }

        boolean isPassed = jsResDoctorRecruitBiz.getRecruitStatus(sysUser.getUserFlow());
        doctorInfoVo.setPassed(isPassed);
        doctorInfoVo.setCanSave(canSave);
        String editFlag = InitConfig.getSysCfg("assess_doctor_edit_info");
		/*//学员保存个人信息放开
		if(GlobalConstant.FLAG_Y.equals(viewFlag)){
			return "jsres/doctorInfo";
		}*/
        //"N".equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息 并且审核期间学员是否修改个人信息为否时
        if (!GlobalConstant.FLAG_Y.equals(editFlag) && "N".equals(canSave)) {
            doctorInfoVo.setIsDoctor("Y");
            return ResultUtil.exec(true, "不可编辑页面", doctorInfoVo);
        }
        return ResultUtil.exec(true, "可编辑页面", doctorInfoVo);
    }

    /**
     * 基地修改个人基本信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/editDoctorInfo")
    public String editDoctorInfo(String userFlow, String viewFlag, Model model) throws DocumentException {
        SysUser sysUser = userBiz.readSysUser(userFlow);
        ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && JsResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
                if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
//		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
//		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,userFlow);
//		if (resRec!=null&!resRec.isEmpty()) {
//			model.addAttribute("resRec", resRec.get(0));
//		}
        model.addAttribute("user", sysUser);
        model.addAttribute("doctor", resDoctor);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
//		ResDoctorRecruit recruit=new ResDoctorRecruit();
//		recruit.setGraduationYear(DateUtil.getYear());
//		recruit.setDoctorFlow(sysUser.getUserFlow());
//		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
//		List<ResDoctorRecruit> recruits=jsResDoctorRecruitBiz.readDoctorRecruits(recruit);
//		String canSave="Y";
//		if(recruits!=null&&recruits.size()>0)
//		{
//			String recruitFlow=recruits.get(0).getRecruitFlow();
//			JsresGraduationApply apply=jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
//			if(apply!=null){
//				if(JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId()))
//				{
//					canSave="N";
//				}else if(JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId()))
//				{
//					canSave="N";
//				}else if(JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId()))
//				{
//					canSave="N";
//				}
//			}
//		}
//
//		boolean isPassed=jsResDoctorRecruitBiz.getRecruitStatus(sysUser.getUserFlow());
//		model.addAttribute("isPassed",isPassed);
//		model.addAttribute("canSave",canSave);
//		String editFlag=InitConfig.getSysCfg("assess_doctor_edit_info");
//		//学员保存个人信息放开
//		if(GlobalConstant.FLAG_Y.equals(viewFlag)){
//			return "jsres/doctorInfo";
//		}
//		//"N".equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息 并且审核期间学员是否修改个人信息为否时
//		if(!GlobalConstant.FLAG_Y.equals(editFlag)&&"N".equals(canSave)){
//			model.addAttribute("isDoctor","Y");
//			return "jsres/doctorInfo";
//		}
        return "jsres/doctorInfo4edit";
    }

    @RequestMapping(value = "/saveForLack")
    public String saveForLack(String userFlow, String viewFlag, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        ResDoctor doctor = resDoctorBiz.readDoctor(sysUser.getUserFlow());
        PubUserResume resume = userResumeBiz.readPubUserResume(sysUser.getUserFlow());
        UserResumeExtInfoForm userResumeExt = null;
        if (resume != null) {
            String xmlContent = resume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                model.addAttribute("userResumeExt", userResumeExt);
            }
        }
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
        int benkeResult = 0;
        int result = 0;
        if (sysDictList != null && !sysDictList.isEmpty()) {
            for (SysDict dict : sysDictList) {
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                            benkeResult = 1;
                        }
                    }
                }
                if (doctor != null) {
                    if (StringUtil.isNotBlank(doctor.getGraduatedId())) {
                        if (dict.getDictId().equals(doctor.getGraduatedId())) {
                            result = 1;
                        }
                    }
                }
            }
        }
        String school = GlobalConstant.FLAG_N;
        List<SysDict> schoolList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
        if (doctor != null) {
            if (StringUtil.isNotBlank(doctor.getDoctorTypeId()) && JsResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
                if (schoolList != null && !schoolList.isEmpty()) {
                    for (SysDict dic : schoolList) {
                        if (dic.getDictId().equals(doctor.getWorkOrgId())) {
                            school = GlobalConstant.FLAG_Y;
                        }
                    }
                }
            }
        }
        model.addAttribute("school", school);
        model.addAttribute("benkeResult", benkeResult);
        model.addAttribute("result", result);
        model.addAttribute("doctor", doctor);
//		return "jsres/doctor/addInfo";
        return "jsres/doctor/addInfo2";
    }


    /**
     * 保存上传扫描件
     *
     * @param operType
     * @param uploadFile
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
    public String checkUploadFile(String operType, String fileType, String fileSuffix, MultipartFile uploadFile, Model model) {
        boolean flag = "idcardFrontImg".equals(operType) || "idcardOppositeImg".equals(operType);
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = "";
            String folderName = "jsresImages";
            if (flag) {
                folderName = "idCard";
                fileResult = jsResDoctorBiz.checkIdCardImg(uploadFile);
            } else {
                if(StringUtils.isNotEmpty(fileSuffix)) {
                    String fileName = uploadFile.getOriginalFilename();
                    String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
                    if(!fileSuffix.contains(suffix.toLowerCase())){
                        fileResult = "请上传 "+fileSuffix+" 格式的文件";
                    }else {
                        fileResult = GlobalConstant.FLAG_Y;
                    }
                }else {
                    if ("File".equals(fileType)) {
                        fileResult = jsResDoctorBiz.checkFile(uploadFile);
                    } else {
                        fileResult = jsResDoctorBiz.checkImg(uploadFile);
                    }
                }
            }
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", fileResult);
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, folderName);
                model.addAttribute("filePath", resultPath);
            }
            model.addAttribute("result", fileResult);
        }
        if (flag) {
            return "jsres/doctor/uploadIdCardFile";
        }
        return "jsres/doctor/uploadFile";
    }

    /***
     * 微信端上传文件
     * @param operType
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/checkUploadFile2", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo checkUploadFile2(String operType, MultipartFile uploadFile) {
        FileVo fileVo = new FileVo();
        boolean flag = "idcardFrontImg".equals(operType) || "idcardOppositeImg".equals(operType);
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = "";
            String folderName = "jsresImages";
            if (flag) {
                folderName = "idCard";
                fileResult = jsResDoctorBiz.checkIdCardImg(uploadFile);
            } else {
                fileResult = jsResDoctorBiz.checkImg(uploadFile);
            }
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                fileVo.setFileResult(fileResult);
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, folderName);
                fileVo.setFilePath(resultPath);
            }
            fileVo.setFileResult(fileResult);
        }
        if (flag) {
            return ResultUtil.exec(true, "上传成功!", fileVo);
        }
        return ResultUtil.exec(true, "上传成功!", fileVo);
    }

    /**
     * 保存上传头像
     *
     * @param uploadFile
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUploadHeadPortrait", method = {RequestMethod.POST})
    public String checkUploadHeadPortrait(String userFlow, MultipartFile uploadFile, Model model) {
        String resultPath = "";
        if (uploadFile != null && !uploadFile.isEmpty()) {
            resultPath = userBiz.uploadImg(userFlow, uploadFile);
            if (resultPath.length() > 8 && resultPath.indexOf("success:") != -1) {
                model.addAttribute("Msg", "Y");
            } else {
                model.addAttribute("Msg", "N");
            }
        }
        model.addAttribute("result", resultPath);
        return "jsres/doctor/uploadUserHeadPortrait";
    }

    /**
     * 检查身份证号、手机号唯一
     *
     * @param sysUser
     * @param userFlow 检查流水号
     * @return
     */
    @RequestMapping(value = "/checkUserUnique")
    @ResponseBody
    public String checkUserUnique(SysUser sysUser, String userFlow) {
        SysUser exitUser = null;
        //身份证号唯一
        String idNo = sysUser.getIdNo().toUpperCase();////身份证X大写
        if (StringUtil.isNotBlank(idNo)) {
            exitUser = userBiz.findByIdNo(idNo.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return GlobalConstant.USER_ID_NO_REPETE;
                    }
                } else {
                    return GlobalConstant.USER_ID_NO_REPETE;
                }
            }
        }
        //手机号唯一
        String userPhone = sysUser.getUserPhone();
        if (StringUtil.isNotBlank(userPhone)) {
            exitUser = userBiz.findByUserPhone(userPhone.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return GlobalConstant.USER_PHONE_REPETE;
                    }
                } else {
                    return GlobalConstant.USER_PHONE_REPETE;
                }
            }
        }
        return GlobalConstant.FLAG_Y;
    }

    /**
     * 检查黑名单中身份证号、手机号
     *
     * @param sysUser
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/checkBlackUser")
    @ResponseBody
    public String checkBlackUser(SysUser sysUser, String userFlow) {
        JsresUserBalcklist userBalcklist = null;
        String idNo = sysUser.getIdNo().toUpperCase();
        if (StringUtil.isNotBlank(idNo)) {
            userBalcklist = jsResDoctorBiz.findByIdNo4Black(idNo);
            if (userBalcklist != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!userBalcklist.getUserFlow().equals(userFlow)) {
                        return GlobalConstant.ID_NO_IN_BLACK;
                    }
                } else {
                    return GlobalConstant.ID_NO_IN_BLACK;
                }
            }
        }
        String userPhone = sysUser.getUserPhone();
        if (StringUtil.isNotBlank(userPhone)) {
            userBalcklist = jsResDoctorBiz.findByUserPhone4Black(userPhone);
            if (userBalcklist != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!userBalcklist.getUserFlow().equals(userFlow)) {
                        return GlobalConstant.USER_PHONE_IN_BLACK;
                    }
                } else {
                    return GlobalConstant.USER_PHONE_IN_BLACK;
                }
            }
        }
        return GlobalConstant.FLAG_Y;
    }


    /**
     * 保存个人基本信息
     *
     * @param doctorInfoForm
     * @return
     */
    @RequestMapping(value = "/saveDoctorInfo")
    @ResponseBody
    public String saveDoctorInfo(JsResDoctorInfoForm doctorInfoForm, String qtCountry) {
        SysUser sysUser = doctorInfoForm.getUser();
        String checkResult = checkUserUnique(sysUser, sysUser.getUserFlow());
        if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
            return checkResult;
        }
        ResDoctor doctor = doctorInfoForm.getDoctor();
        UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        String isMasterHaveDiploma = userResumeExt.getIsMasterHaveDiploma();
        //qtCountry:如果不是中国，设置地区
        int result = jsResDoctorBiz.saveDoctorInfo(sysUser, doctor, userResumeExt,qtCountry);
        if (GlobalConstant.ZERO_LINE != result) {
            ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setGraduationYear(DateUtil.getYear());
            recruit.setDoctorFlow(sysUser.getUserFlow());
            recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
            List<ResDoctorRecruit> recruits = jsResDoctorRecruitBiz.readDoctorRecruits(recruit);
            String canSave = "Y";
            if (recruits != null && recruits.size() > 0) {
                String recruitFlow = recruits.get(0).getRecruitFlow();
                JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
                if (apply != null) {
                    if (JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId())) {
                        canSave = "N";
                    } else if (JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId())) {
                        canSave = "N";
                    } else if (JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId())) {
                        canSave = "N";
                    }
                    if ("Y".equals(canSave)) {
                        tempMapper.updateRecruitAsseInfoByApplyYear(apply.getApplyFlow(), sysUser.getUserFlow());
                    }
                }
            }
            setSessionAttribute(GlobalConstant.CURRENT_USER, userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow()));
            return GlobalContext.getCurrentUser().getUserName();
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 返回个人基本信息下拉框数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ResultSelectInfo")
    @ResponseBody
    public ResultSelectVo ResultSelectInfo() {
        ResultSelectVo selectVo = new ResultSelectVo();
        //证件类型
        Map<String, String> map = CertificateTypeEnum.map;
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        List<DictVo> vos = new ArrayList<>();
        for (Map.Entry<String, String> entry : entrySet) {
            DictVo vo = new DictVo();
            vo.setDictValue(entry.getKey());
            vo.setDictLabel(entry.getValue());
            vos.add(vo);
        }
        selectVo.setCertificateColumns(vos);
        //民族
        Map<String, String> nationMap = UserNationEnum.map;
        Set<Map.Entry<String, String>> entrySet2 = nationMap.entrySet();
        List<DictVo> vos2 = new ArrayList<>();
        for (Map.Entry<String, String> entry : entrySet2) {
            DictVo vo = new DictVo();
            vo.setDictValue(entry.getKey());
            vo.setDictLabel(entry.getValue());
            vos2.add(vo);
        }
        selectVo.setNationColumns(vos2);
        //国籍
        List<DictVo> vos3 = new ArrayList<>();
        List<SysDict> dicts = dictBiz.searchDictListByDictTypeId("Nationality");
        for (SysDict d : dicts) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos3.add(vo);
        }
        selectVo.setNationalityColumns(vos3);
        //外语等级考试类型
        List<DictVo> vos4 = new ArrayList<>();
        List<SysDict> dicts2 = dictBiz.searchDictListByDictTypeId("EnglishGradeExamType");
        for (SysDict d : dicts2) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos4.add(vo);
        }
        selectVo.setEnglishGradeColumns(vos4);
        //英语能力
        List<DictVo> vos5 = new ArrayList<>();
        List<SysDict> dicts3 = dictBiz.searchDictListByDictTypeId("EnglishAbility");
        for (SysDict d : dicts3) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos5.add(vo);
        }
        selectVo.setEnglishAbilityColumns(vos5);
        //户口所在地省行政区划
        Map<String, String> provinceMap = ProvinceEnum.map;
        Set<Map.Entry<String, String>> entrySet3 = provinceMap.entrySet();
        List<DictVo> vos6 = new ArrayList<>();
        for (Map.Entry<String, String> entry : entrySet3) {
            DictVo vo = new DictVo();
            vo.setDictValue(entry.getKey());
            vo.setDictLabel(entry.getValue());
            vos6.add(vo);
        }
        selectVo.setProvinceColumns(vos6);
        //是否军队人员
        Map<String, String> armyTypeMap = ArmyTypeEnum.map;
        Set<Map.Entry<String, String>> entrySet4 = armyTypeMap.entrySet();
        List<DictVo> vos7 = new ArrayList<>();
        for (Map.Entry<String, String> entry : entrySet4) {
            DictVo vo = new DictVo();
            vo.setDictValue(entry.getKey());
            vo.setDictLabel(entry.getValue());
            vos7.add(vo);
        }
        selectVo.setArmyTypeColumns(vos7);
        //在读学历
        List<DictVo> vos8 = new ArrayList<>();
        List<SysDict> dicts4 = dictBiz.searchDictListByDictTypeId("UserEducation");
        for (SysDict d : dicts4) {
            DictVo vo = new DictVo();
            vo.setDictValue(d.getDictName());
            vo.setDictLabel(d.getDictId());
            vos8.add(vo);
        }
        selectVo.setEducationColumns(vos8);
        //学位
        List<DictVo> vos9 = new ArrayList<>();
        List<SysDict> dicts5 = dictBiz.searchDictListByDictTypeId("UserDegree");
        for (SysDict d : dicts5) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos9.add(vo);
        }
        selectVo.setDegreeColumns(vos9);
        //人员类型
        Map<String, String> dcoTypeMap = JsResDocTypeEnum.map;
        Set<Map.Entry<String, String>> entrySet5 = dcoTypeMap.entrySet();
        List<DictVo> vos10 = new ArrayList<>();
        for (Map.Entry<String, String> entry : entrySet5) {
            DictVo vo = new DictVo();
            vo.setDictValue(entry.getKey());
            vo.setDictLabel(entry.getValue());
            vos10.add(vo);
        }
        selectVo.setDcoTypeColumns(vos10);
        //医疗卫生机构
        List<DictVo> vos11 = new ArrayList<>();
        List<SysDict> dicts6 = dictBiz.searchDictListByDictTypeId("MedicalHeaithOrg");
        for (SysDict d : dicts6) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos11.add(vo);
        }
        selectVo.setMedicalColumns(vos11);
        //派送单位
        List<DictVo> vos12 = new ArrayList<>();
        List<SysDict> dicts7 = dictBiz.searchDictListByDictTypeId("WorkOrg");
        for (SysDict d : dicts7) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos12.add(vo);
        }
        selectVo.setOrgColumns(vos12);
        //派送学校
        List<DictVo> vos13 = new ArrayList<>();
        List<SysDict> dicts8 = dictBiz.searchDictListByDictTypeId("SendSchool");
        for (SysDict d : dicts8) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos13.add(vo);
        }
        selectVo.setSendSchoolColumns(vos13);
        //医院属性
        List<DictVo> vos14 = new ArrayList<>();
        List<SysDict> dicts9 = dictBiz.searchDictListByDictTypeId("HospitalAttr");
        for (SysDict d : dicts9) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos14.add(vo);
        }
        selectVo.setHospitalAttr(vos14);
        //医院类别
        List<DictVo> vos15 = new ArrayList<>();
        List<SysDict> dicts10 = dictBiz.searchDictListByDictTypeId("HospitalCategory");
        for (SysDict d : dicts10) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos15.add(vo);
        }
        selectVo.setHospitalCategory(vos15);
        //单位性质
        List<DictVo> vos16 = new ArrayList<>();
        List<SysDict> dicts11 = dictBiz.searchDictListByDictTypeId("BaseAttribute");
        for (SysDict d : dicts11) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos16.add(vo);
        }
        selectVo.setBaseAttribute(vos16);
        //基层卫生医疗机构
        List<DictVo> vos17 = new ArrayList<>();
        List<SysDict> dicts12 = dictBiz.searchDictListByDictTypeId("BasicHealthOrg");
        for (SysDict d : dicts12) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos17.add(vo);
        }
        selectVo.setBasicHealthOrg(vos17);
        //毕业学校
        List<DictVo> vos18 = new ArrayList<>();
        List<SysDict> dicts13 = dictBiz.searchDictListByDictTypeId("GraduateSchool");
        for (SysDict d : dicts13) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos18.add(vo);
        }
        selectVo.setGraduateSchool(vos18);
        //学历

        List<DictVo> vos19 = new ArrayList<>();
        List<SysDict> dicts14 = dictBiz.searchDictListByDictTypeId("UserEducation");
        for (SysDict d : dicts14) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos19.add(vo);
        }
        selectVo.setUserEducation(vos19);
        //毕业专业
        List<DictVo> vos20 = new ArrayList<>();
        List<SysDict> dicts15 = dictBiz.searchDictListByDictTypeId("GraduateMajor");
        for (SysDict d : dicts15) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            vos20.add(vo);
        }
        selectVo.setGraduateMajor(vos20);
        //毕业专业
        List<DictVo> vos21 = new ArrayList<>();
        List<DictVo> vos22 = new ArrayList<>();
        Map<String, List<SysDict>> sysListDictMap = DictTypeEnum.sysListDictMap;
        List<SysDict> dicts16 = sysListDictMap.get("PracticeType");
        for (SysDict d : dicts16) {
            DictVo vo = new DictVo();
            vo.setDictLabel(d.getDictName());
            vo.setDictValue(d.getDictId());
            Map<String, List<SysDict>> sysListDictMap2 = DictTypeEnum.sysListDictMap;
            List<SysDict> dicts17 = sysListDictMap.get("PracticeType." + d.getDictId());
            for (SysDict a : dicts17) {
                DictVo voo = new DictVo();
                voo.setDictLabel(a.getDictName());
                voo.setDictValue(a.getDictId());
                voo.setSuperiorid(d.getDictId());
                vos22.add(voo);
            }
            vos21.add(vo);
        }
        selectVo.setPracticeType(vos21);
        selectVo.setPracticeTypeid(vos22);
        return selectVo;
    }

    /**
     * 保存个人基本信息2
     *
     * @param doctorInfoForm
     * @return
     */
    @RequestMapping(value = "/saveDoctorInfo2")
    @ResponseBody
    public String saveDoctorInfo2(JsResDoctorInfoForm doctorInfoForm) {
        SysUser sysUser = doctorInfoForm.getUser();
        String checkResult = checkUserUnique(sysUser, sysUser.getUserFlow());
        if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
            return checkResult;
        }
        ResDoctor doctor = doctorInfoForm.getDoctor();
        UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        int result = jsResDoctorBiz.saveDoctorInfo2(sysUser, doctor, userResumeExt);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/searchJointOrgFlag")
    @ResponseBody
    public String searchJointOrgFlag(String orgFlow) {
        int result = 0;
        if (StringUtil.isNotBlank(orgFlow)) {
            List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if (jointOrgs != null && !jointOrgs.isEmpty()) {
                result = 1;
            }
        }
        if (GlobalConstant.ZERO_LINE == result) {
            return GlobalConstant.FLAG_N;
        } else {
            return GlobalConstant.FLAG_Y;
        }
    }

    /**
     * 跳转至上传扫描件
     *
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(String second, String fileType, String fileSuffix, Model model) {
        System.err.print("second=" + second);
        if ("File".equals(fileType)) {
            model.addAttribute("fileType", "File");
        } else {
            model.addAttribute("fileType", "");
        }
        model.addAttribute("fileSuffix", fileSuffix);
        return "jsres/doctor/uploadFile";
    }

    /**
     * 跳转至上传扫描件
     *
     * @return
     */
    @RequestMapping("/uploadIdCardFile")
    public String uploadIdCardFile(String operType, Model model) {
        if (StringUtil.isNotBlank(operType)) {
            model.addAttribute("card", operType);
        }
        return "jsres/doctor/uploadIdCardFile";
    }

    /**
     * 跳转至上传头像
     *
     * @return
     */
    @RequestMapping("/uploadUserHeadPortrait")
    public String uploadUserHeadPortrait(Model model, String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            model.addAttribute("userFlow", userFlow);
        } else {
            return "-1";
        }
        return "jsres/doctor/uploadUserHeadPortrait";
    }

    //*************************************   培训信息    **************************************************

    /**
     * 编辑培训信息
     *
     * @return
     */
    @RequestMapping("/editDoctorRecruit")
    public String editDoctorRecruit(String recruitFlow, String doctorStatus, Model model) {
        String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
        ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
        if (null!=resDoctor && StringUtil.isNotBlank(resDoctor.getTrainingTypeId())){
            model.addAttribute("typeId",resDoctor.getTrainingTypeId());
        }
        ResDoctorRecruit doctorRecruit = null;
        String addFlag = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            addFlag = "edit";
            doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
            model.addAttribute("catSpeId", doctorRecruit.getCatSpeId());
            model.addAttribute("catSpeName", doctorRecruit.getCatSpeName());
            model.addAttribute("doctorRecruit", doctorRecruit);
            model.addAttribute("addRecord", "Y");//添加新的培训记录标识
        }
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> passedRecruitList = this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<ResDoctorRecruit> prevPassedList = new ArrayList<ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            model.addAttribute("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    model.addAttribute("firstRecIsWMSecond", true);
                }
                if (TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    model.addAttribute("isWMSecondRecPassed", true);
                }
            }
            model.addAttribute("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            model.addAttribute("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                model.addAttribute("latestPrevPassed", prevPassedList.get(0));
            }
        }
        if ("report".equals(doctorStatus)) {
            return "jsres/doctor/editReportInfo";
        } else {
//			if("edit".equals(addFlag)) {
//				return "jsres/doctor/editTrainInfo";
//			}else {
            return "jsres/doctor/editDoctorType";
//			}
        }
    }

    /**
     * 查看培训信息
     *
     * @return
     */
    @RequestMapping("/getDoctorRecruit")
    public String getDoctorRecruit(String recruitFlow, String doctorFlow, String doctorStatus, Model model,String doctorSignupFlag) {
        boolean isLatest = false;//是否最新记录
        model.addAttribute("doctorSignupFlag",doctorSignupFlag);

        JsresRecruitDocInfo jsresRecruit = recruitDoctorInfoBiz.readRecruit(recruitFlow);
        model.addAttribute("jsresRecruitDocInfo", jsresRecruit);
        /**
         * 拆分res_rec表时注释掉了
         ResRec resRec2=new ResRec();
         resRec2.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
         currdoctor.setEmergencyName("2");//"0"审核不通过"1"审核通过"2"待审核
         List<String> userFlowList = new ArrayList<String>();
         if(StringUtil.isNotBlank(doctorFlow)){
         userFlowList.add(doctorFlow);
         }
         List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec2, currdoctor, userFlowList, null);
         if(resRecList!=null&&!resRecList.isEmpty())
         {
         model.addAttribute("haveReturn","Y");
         }
         **/
//		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
//		docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
//		docotrDelayTeturn.setAuditStatusId("2");
//		docotrDelayTeturn.setDoctorFlow(doctorFlow);
//		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
//		if(resRecList!=null&&!resRecList.isEmpty())
//		{
//			model.addAttribute("haveReturn","Y");
//		}

        ResDocotrDelayTeturn ret = resDoctorDelayTeturnBiz.findTeturnInfo(recruitFlow);
        model.addAttribute("ret", ret);
        if (ret != null) {
            model.addAttribute("haveReturn", "Y");
        }
        String applyYear = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
            SysUser sysUser = userBiz.readSysUser(doctorFlow);
            model.addAttribute("user", sysUser);
            if (doctorRecruit != null) {
                applyYear = doctorRecruit.getGraduationYear();
                model.addAttribute("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())) {
                    ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);
                        String degreeType = doctor.getDegreeCategoryId();
                        if (JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)) {
                            PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
                            String content = resume.getUserResume();
                            if (StringUtil.isNotBlank(content)) {
                                UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                                model.addAttribute("userResumeExt", userResumeExt);
                                if (userResumeExt != null) {
                                    doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
                                }
                            }
                        }
                    }
                }
            }

            ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
            lastRecruit.setDoctorFlow(doctorFlow);
            lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
            if (recruitList != null && !recruitList.isEmpty()) {
                lastRecruit = recruitList.get(0);
                if (lastRecruit.getRecruitFlow().equals(recruitFlow)) {
                    isLatest = true;
                }
            }
//			String resRecType = ResRecTypeEnum.DoctorAuth.getId();
//			List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,doctorFlow);
//			if (resRec!=null&!resRec.isEmpty()) {
//				model.addAttribute("resRec", resRec.get(0));
//			}
            List<DoctorAuth> doctorAuths = doctorAuthBiz.searchAuthsByOperUserFlow(doctorFlow);
            if (doctorAuths != null & !doctorAuths.isEmpty()) {
                model.addAttribute("resRec", doctorAuths.get(0));
            }
            //最新记录 && 审核通过
            if (JsResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
                docOrgHistory.setDoctorFlow(doctorFlow);
                docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
                docOrgHistory.setRecruitFlow(recruitFlow);
                //List<String> changeStatusIdList = new ArrayList<String>();
                //changeStatusIdList.add(JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
                //changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
                List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (docOrgHistoryList != null && !docOrgHistoryList.isEmpty()) {
                    model.addAttribute("docOrgHistoryList", docOrgHistoryList);
                    model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
                }
                docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
                List<ResDoctorOrgHistory> changeSpeList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (changeSpeList != null && !changeSpeList.isEmpty()) {
                    model.addAttribute("changeSpeList", changeSpeList);
                    model.addAttribute("lastChangeSpe", changeSpeList.get(0));
                }
            }
        }
        //判断是否申请结业考核资格
        Boolean applyFlag = false;
        JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply == null) {
            applyFlag = true;
        }
        if (jsresGraduationApply != null) {
            if (JsResAuditStatusEnum.LocalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || JsResAuditStatusEnum.ChargeNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || JsResAuditStatusEnum.GlobalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || JsResAuditStatusEnum.NotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())) {
                applyFlag = true;
            }
        }
        model.addAttribute("isLatest", isLatest);
        model.addAttribute("applyFlag", applyFlag);
        model.addAttribute("sessionNumber", cfgBiz.read("jsres_doctorCount_sessionNumber").getCfgValue());
        SimpleDateFormat date = new SimpleDateFormat("yyyy");
        String year = date.format(new Date());
        model.addAttribute("nowYear", year);
        if ("signup".equals(doctorStatus)) {
            return "jsres/doctor/signupInfo";
        } else {
            return "jsres/doctor/trainInfo";
        }
    }


    /**
     * 查看培训信息
     *
     * @return
     */
    @RequestMapping("/getDocRecruitForReduction")
    public String getDocRecruitForReduction(String recruitFlow, Model model) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);

            if (doctorRecruit != null) {
                model.addAttribute("doctorRecruit", doctorRecruit);
                ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
                model.addAttribute("user", userBiz.findByFlow(doctor.getDoctorFlow()));
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())) {

                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);

                        String degreeType = doctor.getDegreeCategoryId();
                        if (JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)) {
                            PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
                            String content = resume.getUserResume();
                            if (StringUtil.isNotBlank(content)) {
                                UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                                model.addAttribute("userResumeExt", userResumeExt);

                                doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
                            }
                        }
                    }
                }
            }
        }
        return "jsres/doctor/trainInfo";
    }

    /**
     * 验证
     *
     * @param type
     * @return
     */
    @RequestMapping("/validateChange")
    @ResponseBody
    public String validateChange(String type, String doctorFlow, String orgFlow) {
        if (StringUtil.isNotBlank(type)) {
            ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
            orgHistory.setDoctorFlow(doctorFlow);
            orgHistory.setHistoryOrgFlow(orgFlow);
            List<String> changeStatusIdList = new ArrayList<String>();
            if (JsResChangeTypeEnum.SpeChange.getId().equals(type)) {
                orgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
                changeStatusIdList.add(JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
                changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
            }
            if (JsResChangeTypeEnum.BaseChange.getId().equals(type)) {
                orgHistory.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
                changeStatusIdList.add(JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
                changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
            }
            if (StringUtil.isNotBlank(doctorFlow)) {
                orgHistory.setDoctorFlow(doctorFlow);
            }
            List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(orgHistory, changeStatusIdList);
            if (docOrgHistoryList != null && !docOrgHistoryList.isEmpty()) {
                return GlobalConstant.FLAG_N;
            } else {
                return GlobalConstant.FLAG_Y;
            }
        } else {
            return GlobalConstant.FLAG_N;
        }
    }


    /**
     * 加载培训基地
     *
     * @param sysOrg
     * @return
     */
    @RequestMapping(value = "/searchOrgList", method = {RequestMethod.GET})
    @ResponseBody
    public List<SysOrg> searchOrgList(SysOrg sysOrg, String jointFlag, String catSpeId, HttpServletRequest request) {
        List<SysOrg> orgList = null;
        sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (StringUtil.isNotBlank(jointFlag)) {
            List<String> orgLevelList = new ArrayList<String>();
            orgLevelList.add(OrgLevelEnum.CountryOrg.getId());
            if ("DoctorTrainingSpe".equals(catSpeId)) {
                orgList = orgBiz.searchOrgNotJointOrg(sysOrg, orgLevelList);
            } else {
                orgLevelList.add(OrgLevelEnum.ProvinceOrg.getId());
                orgList = orgBiz.searchJsResOrgWithJointOrg(sysOrg, orgLevelList);
            }
        } else {
            sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            orgList = orgBiz.searchOrg(sysOrg);
        }
        return orgList;
    }

    @RequestMapping(value = "/searchOrgListNew", method = {RequestMethod.GET})
    @ResponseBody
    public List<SysOrg> searchOrgListNew(SysOrg sysOrg, String jointFlag, String catSpeId, HttpServletRequest request) {
        List<SysOrg> orgList = null;
        sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (TrainCategoryEnum.DoctorTrainingSpe.getId().equals(catSpeId)) {
            List<String> orgLevelList = new ArrayList<String>();
            orgLevelList.add(OrgLevelEnum.CountryOrg.getId());
            orgList = orgBiz.searchOrgNotJointOrg(sysOrg, orgLevelList);
        } else {
            sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            sysOrg.setIsAcc("Y");
            orgList = orgBiz.searchOrgIsAcc(sysOrg);
        }
        return orgList;
    }

    /**
     * 加载基地专业
     *
     * @return
     */
    @RequestMapping(value = "/searchResOrgSpeList", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, List> searchResOrgSpeList(String sessionNumber, ResOrgSpe resOrgSpe) {
        List<ResOrgSpe> speList = null;
        Map<String, List> resultMap = new HashMap<>();
        resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = String.valueOf(LocalDate.now().getYear());
        }
        resOrgSpe.setSessionYear(sessionNumber);
        int year = Integer.parseInt(sessionNumber);

        if (year >= 2015) {
            speList = resBaseSpeBiz.searchResOrgSpeList(resOrgSpe, TrainCategoryEnum.DoctorTrainingSpe.getTypeId());
        } else {
            String speTypeId = resOrgSpe.getSpeTypeId();
            if (StringUtil.isNotBlank(speTypeId)) {
                Map<String, String> speMap = InitConfig.getDictNameMap(speTypeId);
                speList = new ArrayList<ResOrgSpe>();
                ResOrgSpe orgSpe = null;
                for (Map.Entry<String, String> map : speMap.entrySet()) {
                    orgSpe = new ResOrgSpe();
                    orgSpe.setSpeId(map.getValue());
                    orgSpe.setSpeName(map.getKey());
                    speList.add(orgSpe);
                }
            }
        }
        resultMap.put("main", speList);
        List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(resOrgSpe.getOrgFlow());

        if (jointOrgs != null && !jointOrgs.isEmpty()) {
            resultMap.put("joint", jointOrgs);
        }
        return resultMap;
    }

    /**
     * 加载基地专业
     *
     * @return
     */
    @RequestMapping(value = "/searchResOrgSpeListNew", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, List> searchResOrgSpeListNew(String sessionNumber, String trainCategoryTypeId, ResOrgSpe resOrgSpe) {
        List<ResOrgSpe> speList = null;
        Map<String, List> resultMap = new HashMap<>();
        resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
        String speFlag = "N";
        SysUser sysUser = GlobalContext.getCurrentUser();
//		List<JsresDoctorSpe> docSpeList = speAssignBiz.searchDoctorSpe();
        List<Map<String, String>> docSpeList = resDoctorBiz.searchDoctorSpe();
        if (null != docSpeList && docSpeList.size() > 0) {
            for (Map<String, String> spe : docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if (idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)) {
                    speFlag = "Y";
                    break;
                }
            }
        }

        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = String.valueOf(LocalDate.now().getYear());
        }
        resOrgSpe.setSessionYear(sessionNumber);
        int year = Integer.parseInt(sessionNumber);
        if (year >= 2015) {
            if ("Y".equals(speFlag)) {
                speList = resBaseSpeBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
            } else {
                speList = resBaseSpeBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
            }
        } else {
            String speTypeId = resOrgSpe.getSpeTypeId();
            if (StringUtil.isNotBlank(speTypeId)) {
                Map<String, String> speMap = InitConfig.getDictNameMap(speTypeId);
                speList = new ArrayList<ResOrgSpe>();
                ResOrgSpe orgSpe = null;
                for (Map.Entry<String, String> map : speMap.entrySet()) {
                    orgSpe = new ResOrgSpe();
                    orgSpe.setSpeId(map.getValue());
                    orgSpe.setSpeName(map.getKey());
                    speList.add(orgSpe);
                }
            }
        }

        List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(resOrgSpe.getOrgFlow());
        resultMap.put("main", speList);
        if (jointOrgs != null && !jointOrgs.isEmpty()) {
            resultMap.put("joint", jointOrgs);
        }
        return resultMap;
    }

    @RequestMapping(value = "/saveDoctorCatSpe2")
    public String saveDoctorCatSpe2(String doctorFlow, String recruitFlow, Model model) {
        SysUser user = userBiz.readSysUser(doctorFlow);
        String catSpeId = user.getTrainingTypeId();
        String catSpeName=user.getTrainingTypeName();
        ResDoctorRecruit doctorRecruit = null;
        if (StringUtil.isNotBlank(recruitFlow)) {
            doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        }
        ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
        model.addAttribute("resDoctor", resDoctor);
        model.addAttribute("doctorRecruit", doctorRecruit);
        model.addAttribute("catSpeId", catSpeId);
        model.addAttribute("catSpeName", catSpeName);
        return "jsres/doctor/editTrainInfo";
    }


    @RequestMapping(value = "/saveDoctorCatSpe")
    public String saveDoctorCatSpe(String catSpeId, String catSpeName, String recruitFlow, Model model) {
        ResDoctorRecruit doctorRecruit = null;
        if (StringUtil.isNotBlank(recruitFlow)) {
            doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
        }
        if (null != doctorRecruit) {
            if (!doctorRecruit.getCatSpeId().equals(catSpeId)) {
                doctorRecruit = new ResDoctorRecruit();
                doctorRecruit.setRecruitFlow(recruitFlow);
            }
        }
        model.addAttribute("doctorRecruit", doctorRecruit);
        model.addAttribute("catSpeId", catSpeId);
        model.addAttribute("catSpeName", catSpeName);
        return "jsres/doctor/editTrainInfo";
    }


    /**
     * 保存培训信息
     *
     * @param docRecWithBLOBs
     * @param prevRecruitFlow
     * @param prevCompleteFileUrl
     * @param prevCompleteCertNo
     * @param model               注：1、二阶段的住培结业证书附件（二阶段报名需一阶段结业，二阶段的住培结业证书附件即一阶段的结业证书附件）
     *                            存放在res_doctor_recruit中complete_file_url中
     *                            2、二阶段的专培结业证书附件（该条记录的结业附件）
     *                            存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     *                            3、除二阶段以外的住培结业证书附件（该条记录的结业附件）
     *                            存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * @return
     */
    @RequestMapping(value = "/saveResDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveResDoctorRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, Model model) throws ParseException {

        SysUser sysUser = GlobalContext.getCurrentUser();
        String doctorFlow = docRecWithBLOBs.getDoctorFlow();
        docRecWithBLOBs.setSignupWay("DoctorSend");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        ResDoctorRecruitWithBLOBs prevDocRec = new ResDoctorRecruitWithBLOBs();//选择的结业阶段
        prevDocRec.setRecruitFlow(prevRecruitFlow);
        prevDocRec.setCompleteFileUrl(prevCompleteFileUrl);
        prevDocRec.setCompleteCertNo(prevCompleteCertNo);
        //住院医师需要协同基地审核后主基地审核，助理全科只需要选择的基地审核
        if ("DoctorTrainingSpe".equals(docRecWithBLOBs.getCatSpeId())) {
            if (StringUtil.isNotBlank(docRecWithBLOBs.getJointOrgFlow())) {
                docRecWithBLOBs.setJointOrgAudit("Auditing");
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
            }
            docRecWithBLOBs.setOrgAudit("Auditing");
        } else {
            //判断是否是协同基地
            List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(docRecWithBLOBs.getOrgFlow());
            if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {//是协同基地
                docRecWithBLOBs.setJointOrgAudit("Auditing");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            }
        }

        if (!TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if ("21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            } else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        } else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if (!"21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        //查询专业轮转方案
        SchRotation rotation = new SchRotation();
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId()); // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
        rotation.setSpeId(docRecWithBLOBs.getSpeId());
        String catSpeId = docRecWithBLOBs.getCatSpeId();
        if (TrainCategoryEnum.AssiGeneral.getId().equals(catSpeId) || RecDocCategoryEnum.WMFirst.getId().equals(catSpeId)) {
            rotation.setDoctorCategoryId(catSpeId);
        }
        rotation.setPublishFlag("Y");
        List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
        if (null != rotationList && rotationList.size() > 0) {
            docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
            docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
        }
        ResDoctor doctor = resDoctorBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
        docRecWithBLOBs.setIsRetrain(GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, flags, null);
        if (resRecList.size() > 0) {
            docRecWithBLOBs.setIsRetrain(GlobalConstant.RECORD_STATUS_Y);
        } else {
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<ResDoctorRecruit> recruitList = recruitDoctorInfoBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if (CollectionUtils.isNotEmpty(recruitList)) {
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if (StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())) {
                        //培训年限
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if ("OneYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 1;
                            } else if ("TwoYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 2;
                            } else if ("ThreeYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 3;
                            }
                        }
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())) {
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd = Calendar.getInstance();
                            try {
                                cd.setTime(sdf.parse(recruitDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if (num > 0) {
                                docRecWithBLOBs.setIsRetrain(GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        int result = jsResDoctorRecruitBiz.editResDoctorRecruit(docRecWithBLOBs, prevDocRec);
        if (GlobalConstant.ZERO_LINE != result) {
            return docRecWithBLOBs.getRecruitFlow();
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 保存培训信息
     *
     * @param recruitFlow
     * @param specialFileUrl
     * @param specialCertNo  注：1、二阶段的住培结业证书附件（二阶段报名需一阶段结业，二阶段的住培结业证书附件即一阶段的结业证书附件）
     *                       存放在res_doctor_recruit中complete_file_url中
     *                       2、二阶段的专培结业证书附件（该条记录的结业附件）
     *                       存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     *                       3、除二阶段以外的住培结业证书附件（该条记录的结业附件）
     *                       存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
     * @return
     */
    @RequestMapping(value = "/saveGraduationInfo", method = {RequestMethod.POST})
    @ResponseBody
    public String saveGraduationInfo(String recruitFlow, String specialFileUrl, String specialCertNo) {
        ResDoctorRecruitWithBLOBs recruitBlob = null;
        if (StringUtil.isNotBlank(recruitFlow)) {
            recruitBlob = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
            if (recruitBlob != null) {
                recruitBlob = new ResDoctorRecruitWithBLOBs();
                recruitBlob.setRecruitFlow(recruitFlow);
                recruitBlob.setSpecialCertNo(specialCertNo);
                recruitBlob.setSpecialFileUrl(specialFileUrl);
            } else {
                return GlobalConstant.SAVE_FAIL;
            }
        } else {
            return GlobalConstant.SAVE_FAIL;
        }
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(recruitBlob);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 提交培训信息
     *
     * @param recruitWithBLOBs
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitResDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public String submitResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model) {
        String auditStatusId = recruitWithBLOBs.getAuditStatusId();
        if (StringUtil.isNotBlank(auditStatusId)) {
            recruitWithBLOBs.setAuditStatusName(JsResDoctorAuditStatusEnum.getNameById(auditStatusId));
        }
        recruitWithBLOBs.setRecruitFlag(GlobalConstant.RECORD_STATUS_Y);
        recruitWithBLOBs.setConfirmFlag(GlobalConstant.RECORD_STATUS_Y);
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(recruitWithBLOBs);
        if (GlobalConstant.ZERO_LINE != result) {
            return recruitWithBLOBs.getRecruitFlow();
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 验证黑名单中信息
     *
     * @param userCode
     * @param userEmail
     * @param userFlow
     * @param userPhone
     * @param idNo
     * @return
     */
    @RequestMapping(value = "/checkUserInfoInBlack", method = {RequestMethod.POST})
    @ResponseBody
    public String checkUserInfoInBlack(String userCode, String userEmail, String userFlow, String userPhone, String idNo) {
        Map<String, Object> userInfoMap = new HashMap<String, Object>();
        userInfoMap.put("userCode", userCode);
        userInfoMap.put("userEmail", userEmail);
        userInfoMap.put("userFlow", userFlow);
        userInfoMap.put("userPhone", userPhone);
        userInfoMap.put("idNo", idNo);
        List<JsresUserBalcklist> userBalckList = jsResDoctorBiz.checkBlackList(userInfoMap);
        if (userBalckList.size() > 0) {
//			setSessionAttribute("reason",userBalckList.get(0).getReason());
//			setSessionAttribute("reasonYj",userBalckList.get(0).getReasonYj());
            String returnMsg = userBalckList.get(0).getReason() + "&pdnpc;" + userBalckList.get(0).getReasonYj() + "&pdnpc;" + GlobalConstant.USER_INFO_IN_BLACK;
            return returnMsg;
        }
        return "";
    }

    /**
     * 查找审核理论考试信息
     *
     * @param model
     * @param currentPage
     * @param roleFlag
     * @param request
     * @param doctor
     * @param user
     * @param baseId
     * @param jointOrgFlag
     * @param derateFlag
     * @param orgLevel
     * @param datas
     * @param graduationYear
     * @return
     */
    @RequestMapping(value = "/searchExamInfo", method = {RequestMethod.POST})
    public String searchExamInfo(Model model, Integer currentPage, String roleFlag, HttpServletRequest request, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear) {
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        List<String> jointOrgFlowList = new ArrayList<String>();
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysUser sysUser = new SysUser();
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(jointOrgFlag, sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }
        if (StringUtil.isNotBlank(user.getIdNo())) {
            sysUser.setIdNo(user.getIdNo());
        }
        sysUser.setUserName(user.getUserName());
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<JsDoctorInfoExt> doctorList = null;
        PageHelper.startPage(currentPage, getPageSize(request));
        doctorList = jsResDoctorRecruitBiz.searchDoctorInfoResume(resDoctorRecruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
        //解析xml
        Map<String, Object> userResumeInfoMap = new HashMap<String, Object>();
        if (doctorList != null && !doctorList.isEmpty()) {
            for (JsDoctorInfoExt d : doctorList) {
                String content = d.getUserResume().getUserResume();
                if (StringUtil.isNotBlank(content)) {
                    UserResumeExtInfoForm form = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                    String key = d.getRecruitFlow();
                    userResumeInfoMap.put(key, form);
                }
            }
        }
        model.addAttribute("userResumeInfoMap", userResumeInfoMap);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("datas", datas);

        return "jsres/examInfo";
    }


    @RequestMapping(value = "/backResDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public String backResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, String autitType, Model model) {
        String auditStatusId = recruitWithBLOBs.getAuditStatusId();
        if (StringUtil.isNotBlank(auditStatusId)) {
            recruitWithBLOBs.setAuditStatusName(JsResDoctorAuditStatusEnum.getNameById(auditStatusId));
        }
        ResDoctorRecruit recruitOld = jsResDoctorRecruitBiz.readRecruit(recruitWithBLOBs.getRecruitFlow());
        if (JsResDoctorAuditStatusEnum.Auditing.getId().equals(auditStatusId)) {
            //退回重审
            if (StringUtil.isNotBlank(recruitOld.getJointOrgFlow())) {
                recruitWithBLOBs.setJointOrgAudit(JsResDoctorAuditStatusEnum.Auditing.getId());
            } else {
                recruitWithBLOBs.setJointOrgAudit(JsResDoctorAuditStatusEnum.Passed.getId());
            }
            recruitWithBLOBs.setOrgAudit(JsResDoctorAuditStatusEnum.Auditing.getId());
        }
        if (JsResDoctorAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
            //退回修改
            recruitWithBLOBs.setJointOrgAudit(JsResDoctorAuditStatusEnum.NotPassed.getId());
            recruitWithBLOBs.setOrgAudit(JsResDoctorAuditStatusEnum.NotPassed.getId());
            if ("DoctorSignup".equals(recruitOld.getSignupWay())) {
                recruitWithBLOBs.setExamResult(null);
                recruitWithBLOBs.setExamStatusId("");
                recruitWithBLOBs.setExamStatusName("");
                recruitWithBLOBs.setAuditionResult(null);
                recruitWithBLOBs.setAuditionStatusId("");
                recruitWithBLOBs.setAuditionStatusName("");
                recruitWithBLOBs.setOperResult(null);
                recruitWithBLOBs.setOperStatusId("");
                recruitWithBLOBs.setOperStatusName("");
                recruitWithBLOBs.setTotleResult(null);
                recruitWithBLOBs.setConfirmFlag("");
                recruitWithBLOBs.setRecruitFlag("");
                recruitWithBLOBs.setRecruitDate("");
                recruitWithBLOBs.setDoctorStatusId(BaseStatusEnum.NotPassed.getId());
                recruitWithBLOBs.setDoctorStatusName(BaseStatusEnum.getNameById(BaseStatusEnum.NotPassed.getId()));
            }
        }
        int result = jsResDoctorRecruitBiz.saveDoctorRecruit(recruitWithBLOBs);
        //添加学员通知
        if (GlobalConstant.ZERO_LINE != result) {
            String msgTitle = "培训信息审核结果";
            String msgContent = null;
            if ("1".equals(autitType)) {
                if (StringUtil.isNotBlank(recruitWithBLOBs.getGlobalNotice())) {
                    msgContent = "您的培训信息省厅退回重审(" + recruitWithBLOBs.getGlobalNotice() + ")，";
                } else {
                    msgContent = "您的培训信息省厅退回重审，";
                }
            }
            if ("2".equals(autitType)) {
                if (StringUtil.isNotBlank(recruitWithBLOBs.getGlobalNotice())) {
                    msgContent = "您的培训信息省厅退回修改(" + recruitWithBLOBs.getGlobalNotice() + ")，";
                } else {
                    msgContent = "您的培训信息省厅退回修改，";
                }
            }
            String modifyTime = DateUtil.transDateTime(recruitWithBLOBs.getModifyTime());
            String operUserFlow = recruitWithBLOBs.getModifyUserFlow();
            SysUser tempUser = userBiz.readSysUser(operUserFlow);
            String orgName = tempUser.getOrgName();
            msgContent += orgName + "，" + modifyTime;
            msgBiz.addSysMsg(recruitWithBLOBs.getDoctorFlow(), msgTitle, msgContent);
        }
        if (GlobalConstant.ZERO_LINE != result) {
            String doctorFlow = recruitWithBLOBs.getDoctorFlow();
            if (StringUtil.isNotBlank(doctorFlow)) {
                ResDoctor resDoctor = this.resDoctorBiz.readDoctor(doctorFlow);
                if (resDoctor != null) {
                    ResDoctorRecruit recruit = new ResDoctorRecruit();
                    recruit.setDoctorFlow(doctorFlow);
                    recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
                    List<ResDoctorRecruit> recruitList = this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "create_time desc");
                    if (recruitList != null && !recruitList.isEmpty()) {
                        resDoctor.setOrgFlow(recruitList.get(0).getOrgFlow());
                        resDoctor.setOrgName(recruitList.get(0).getOrgName());
                        resDoctor.setTrainingTypeId(recruitList.get(0).getCatSpeId());
                        resDoctor.setTrainingTypeName(recruitList.get(0).getCatSpeName());
                        resDoctor.setTrainingSpeId(recruitList.get(0).getSpeId());
                        resDoctor.setTrainingSpeName(recruitList.get(0).getSpeName());
                        resDoctor.setDoctorStatusId(recruitList.get(0).getDoctorStatusId());
                        resDoctor.setDoctorStatusName(recruitList.get(0).getDoctorStatusName());
                        resDoctor.setTrainingYears(recruitList.get(0).getTrainYear());
                        resDoctor.setDegreeCategoryId(recruitList.get(0).getCurrDegreeCategoryId());
                        resDoctor.setDegreeCategoryName(recruitList.get(0).getCurrDegreeCategoryName());
                        resDoctor.setSessionNumber(recruitList.get(0).getSessionNumber());
                        if (StringUtil.isBlank(recruitList.get(0).getOrgFlow())) {
                            resDoctor.setOrgFlow("");
                            resDoctor.setOrgName("");
                            resDoctor.setTrainingTypeId("");
                            resDoctor.setTrainingTypeName("");
                            resDoctor.setTrainingSpeId("");
                            resDoctor.setTrainingSpeName("");
                            resDoctor.setDoctorStatusId("");
                            resDoctor.setDoctorStatusName("");
                            resDoctor.setTrainingYears("");
                            resDoctor.setDegreeCategoryId("");
                            resDoctor.setDegreeCategoryName("");
                            resDoctor.setSessionNumber("");
                            resDoctor.setRotationFlow("");
                            resDoctor.setRotationName("");
                        }
                    } else {
                        resDoctor.setOrgFlow("");
                        resDoctor.setOrgName("");
                        resDoctor.setTrainingTypeId("");
                        resDoctor.setTrainingTypeName("");
                        resDoctor.setTrainingSpeId("");
                        resDoctor.setTrainingSpeName("");
                        resDoctor.setDoctorStatusId("");
                        resDoctor.setDoctorStatusName("");
                        resDoctor.setTrainingYears("");
                        resDoctor.setDegreeCategoryId("");
                        resDoctor.setDegreeCategoryName("");
                        resDoctor.setSessionNumber("");
                        resDoctor.setRotationFlow("");
                        resDoctor.setRotationName("");
                    }
                    int docResult = resDoctorBiz.editDoctor(resDoctor, null);
                    if (docResult == GlobalConstant.ONE_LINE) {
                        resDoctor = rotationBiz.updateDocRotation(resDoctor);
                        //每一条培训记录保存一个培训方案
                        recruitWithBLOBs.setRotationFlow(resDoctor.getRotationFlow());
                        recruitWithBLOBs.setRotationName(resDoctor.getRotationName());
                        jsResDoctorRecruitBiz.saveDoctorRecruit(recruitWithBLOBs);
                        resDoctorBiz.editDoctor(resDoctor, null);
                    }
                }
                // 退回学员  APP登录权限取消
                JsresPowerCfg jsresCfg = jsResPowerCfgBiz.read("jsres_doctor_app_login_" + doctorFlow);
                if (null != jsresCfg) {
                    jsResPowerCfgBiz.deleteCfg(jsresCfg.getCfgCode());
                }
            }
            ResDoctorRecruit resDoctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
            if (resDoctorRecruit != null) {
                String sessionNumber = resDoctorRecruit.getSessionNumber();
                doctorFlow = resDoctorRecruit.getDoctorFlow();
                JsresRecruitDocInfoWithBLOBs info2 = resDoctorBiz.getRecruitDocInfoBySessionNumber(doctorFlow, sessionNumber);
                JsresRecruitInfo recruitInfo2 = resDoctorBiz.getRecruitInfoBysessionNumber(doctorFlow, sessionNumber);
                //删除备份记录
                if (info2 != null) {
                    resDoctorBiz.delJsresRecruitDocInfo(info2.getRecruitFlow());
                }
                if (recruitInfo2 != null) {
                    resDoctorBiz.delJsresRecruitInfo(recruitInfo2.getRecordFlow());
                }
            }
            return recruitWithBLOBs.getRecruitFlow();
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 上传减免培训年限证明
     *
     * @param file
     * @param courseFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadTrainYearFile"})
    @ResponseBody
    public String uploadTrainYearFile(MultipartFile file, String courseFlow) throws Exception {
        if (file != null && !file.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkImg(file);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
                return resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 添加信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/addInfo"})
    @ResponseBody
    public String addInfo(Model model, JsResDoctorInfoForm doctorInfoForm) {
        ResDoctor doctor = doctorInfoForm.getDoctor();
        UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        int result = 0;
        int resumeResult = 0;
        if (StringUtil.isNotBlank(doctor.getDoctorFlow())) {
            if (StringUtil.isNotBlank(doctor.getWorkOrgLevelId())) {
                doctor.setWorkOrgLevelName(DictTypeEnum.getDictName(DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
            }
            //毕业院校
            if (StringUtil.isNotBlank(doctor.getGraduatedName())) {
                Map<String, List<SysDict>> sysListDictMap = DictTypeEnum.sysListDictMap;
                List<SysDict> dictList = sysListDictMap.get("GraduateSchool");
                for (SysDict s : dictList) {
                    if (s.getDictName().equals(doctor.getGraduatedName())) {
                        doctor.setGraduatedId(s.getDictId());
                    }
                }
            }
            //派送单位或者学校
            if (StringUtil.isNotBlank(doctor.getWorkOrgName())) {
                if (JsResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())) {
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictName().equals(doctor.getWorkOrgName())) {
                                doctor.setWorkOrgId(dict.getDictId());
                            }
                        }
                    }
                }
            }
            result = resDoctorBiz.editDoctor(doctor);
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctor.getDoctorFlow());
            if (pubUserResume != null) {
                //JavaBean转换成xml
                UserResumeExtInfoForm infoForm = JaxbUtil.converyToJavaBean(pubUserResume.getUserResume(), UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedName())) {
                        infoForm.setGraduatedName(userResumeExt.getGraduatedName());
                        Map<String, List<SysDict>> sysListDictMap = DictTypeEnum.sysListDictMap;
                        List<SysDict> dictList = sysListDictMap.get("GraduateSchool");
                        for (SysDict s : dictList) {
                            if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
                                infoForm.setGraduatedId(s.getDictId());
                            }
                        }
                    }
                    String xmlContent = JaxbUtil.convertToXml(infoForm);
                    pubUserResume.setUserResume(xmlContent);
                }
                resumeResult = userResumeBiz.savePubUserResume(null, pubUserResume);
            }
        }
        if (result != 0 || resumeResult != 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        } else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping(value = {"/addInfo2"})
    @ResponseBody
    public String addInfo2(Model model, JsResDoctorInfoForm doctorInfoForm) {
        ResDoctor doctor = doctorInfoForm.getDoctor();
        int result = 0;
        if (StringUtil.isNotBlank(doctor.getDoctorFlow())) {
            String msg = "";
            if (StringUtil.isNotBlank(doctor.getDoctorTypeId())) {
                doctor.setDoctorTypeName(JsResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
            } else {
                msg = "请选择人员类型！";
            }
            //派送单位或者学校
            if (StringUtil.isNotBlank(doctor.getWorkOrgName())) {
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.WorkOrg.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    boolean f = false;
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictName().equals(doctor.getWorkOrgName())) {
                            f = true;
                            doctor.setWorkOrgId(dict.getDictId());
                        }
                    }
                    if (!f)
                        msg = "派送单位的值与字典不符,请重新输入";
                } else {
                    msg = "请联系系统管理员，维护派送单位信息！";
                }
            } else {
                msg = "请填写派送单位！";
            }
            if (StringUtil.isBlank(msg))
                result = resDoctorBiz.editDoctor(doctor);
            else return msg;
        }
        if (result != 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        } else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping(value = {"/uploadTrainYearFileAndSaveRecruit"})
    @ResponseBody
    public String uploadTrainYearFileAndSaveRecruit(MultipartFile file, String recruitFlow) throws Exception {
        String result = uploadTrainYearFile(file, null);
        if (StringUtil.isNotBlank(result)) {
            ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
            recruit.setRecruitFlow(recruitFlow);
            recruit.setProveFileUrl(result);
            int saveResult = jsResDoctorRecruitBiz.saveDoctorRecruit(recruit);
            if (GlobalConstant.ZERO_LINE != saveResult) {
                return result;
            }
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }


    /**
     * 读取新消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("/readMsg")
    @ResponseBody
    public String readMsg(PubMsg msg) {
        int result = msgBiz.updateMsg(msg);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }

    //*************************************    结业管理    ************************************************

    /**
     * 成绩查询
     *
     * @return
     */
    @RequestMapping(value = "/owenScore")
    public String owenScore(Model model, String hideApprove, String doctorFlow, String roleFlag) throws DocumentException {
        SysUser currUser = GlobalContext.getCurrentUser();
        String operUserFlow = currUser.getUserFlow();
        String flag = "Y";
        //List<Map<String,Object>> datas=resScoreBiz.getALLScoreByDoctoFlow(operUserFlow);
//		List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(operUserFlow);
        List<ResScore> scorelist = resScoreBiz.selectByExampleWithBLOBs2(operUserFlow, flag);
        //理论成绩
        ResScore theoryScore = new ResScore();
        //技能成绩
        ResScore skillScore = new ResScore();
        //公共成绩
        ResScore publicScore = new ResScore();
        if (null != scorelist && scorelist.size() > 0) {
            int theoryYear = 0;
            int skillYear = 0;
            for (ResScore resScore : scorelist) {
                int socreYear = Integer.valueOf(resScore.getScorePhaseId() == null ? "-1" : resScore.getScorePhaseId());

                if (ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > theoryYear) {
                            theoryYear = socreYear;
                            theoryScore = resScore;
                        }
                    }

                }
                if (ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > skillYear) {
                            skillYear = socreYear;
                            skillScore = resScore;
                        }
                    }
                }
                if (ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId())) {
                    publicScore = resScore;
                }
            }
        }
        //当前年份
        model.addAttribute("thisYear", DateUtil.getYear());

        model.addAttribute("theoryScore", theoryScore);
        model.addAttribute("skillScore", skillScore);
        model.addAttribute("publicScore", publicScore);
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(operUserFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        recruit.setAuditStatusId(ResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        ResDoctorRecruit resDoctorRecruit = null;
        if (recruitList != null && !recruitList.isEmpty()) {
            resDoctorRecruit = recruitList.get(0);
            model.addAttribute("recruitList", recruitList);
            model.addAttribute("doctorRecruit", recruitList.get(0));
        }

        //技能成绩
        List<ResScore> skillList = new ArrayList<ResScore>();
        List<ResScore> theoryList = new ArrayList<ResScore>();

        if (null != scorelist && scorelist.size() > 0) {
            for (ResScore resScore : scorelist) {
                if (ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                    skillList.add(resScore);
                } else if (ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                    theoryList.add(resScore);
                }
            }
        }
        //所有技能科目详情
        Map<String, Map<String, String>> skillExtScoreMap = new HashMap<String, Map<String, String>>();
        for (int i = 0; i < skillList.size(); i++) {
            Map<String, String> extScore = new HashMap<String, String>();
            ResScore resScore = skillList.get(i);
            String content = null == resScore ? "" : resScore.getExtScore();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            skillExtScoreMap.put(resScore.getScoreFlow(), extScore);
        }
        String validYear = String.valueOf(Integer.valueOf(resDoctorRecruit.getGraduationYear()) + 3);
        if (skillList.size() > 0) {
            //获取出3年内的所有技能成绩
            skillList = skillList.stream().filter(resScore -> StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) >= 0).collect(Collectors.toList());
            skillList.sort(Comparator.comparing(resScore -> resScore.getScorePhaseId()));
            Collections.reverse(skillList);
        }
        if (theoryList.size() > 0) {
            //获取出3年内的所有理论成绩
            theoryList = theoryList.stream().filter(resScore -> StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) >= 0).collect(Collectors.toList());
            theoryList.sort(Comparator.comparing(resScore -> resScore.getScorePhaseId()));
            Collections.reverse(theoryList);
        }
        List<JsresExamSignup> signups = doctorRecruitBiz.readDoctorExanSignUps(operUserFlow);
        Map<String, String> isExamSign = new HashMap();
        if (skillList.size() > 0) {
            for (ResScore resScore : skillList) {
                if (signups != null && signups.size() > 0) {
                    //判断当前这条技能成绩是不是补考的
                    List<JsresExamSignup> examSignups = signups.stream().filter(jsresExamSignup -> "Skill".equals(jsresExamSignup.getSignupTypeId()) &&
                            resScore.getScorePhaseId().equals(jsresExamSignup.getSignupYear()) && StringUtil.isNotBlank(resScore.getTestId()) &&
                            resScore.getTestId().equals(jsresExamSignup.getTestId())).collect(Collectors.toList());
                    isExamSign.put(resScore.getScoreFlow(), examSignups.size() > 0 ? "Y" : "N");
                }
            }
            model.addAttribute("nearestSkill", skillList.get(0));
        }
        if (theoryList.size() > 0) {
            for (ResScore resScore : theoryList) {
                if (signups != null && signups.size() > 0) {
                    //判断当前这条理论成绩是不是补考的
                    List<JsresExamSignup> examSignups = signups.stream().filter(jsresExamSignup -> "Theory".equals(jsresExamSignup.getSignupTypeId()) &&
                            resScore.getScorePhaseId().equals(jsresExamSignup.getSignupYear()) && StringUtil.isNotBlank(resScore.getTestId()) &&
                            resScore.getTestId().equals(jsresExamSignup.getTestId())).collect(Collectors.toList());
                    isExamSign.put(resScore.getScoreFlow(), examSignups.size() > 0 ? "Y" : "N");
                }
            }
            model.addAttribute("nearestTheory", theoryList.get(0));
        }
        model.addAttribute("isExamSign", isExamSign);
        model.addAttribute("skillList", skillList);
        model.addAttribute("theoryList", theoryList);
        model.addAttribute("skillExtScoreMap", skillExtScoreMap);
        //公共科目成绩详情
        String content = null == publicScore ? "" : publicScore.getExtScore();
        Map<String, String> extScoreMap = new HashMap<String, String>();
        if (StringUtil.isNotBlank(content)) {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    for (Element attr : extInfoAttrEles) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        extScoreMap.put(attrName, attrValue);
                    }
                }
            }
        }
        model.addAttribute("extScore", extScoreMap);
        //个人信息
        ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(operUserFlow);
        SysUser sysUser = userBiz.readSysUser(operUserFlow);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
        //保存医师培训时间
        if (resDoctorRecruit != null) {
            String endTime = "";
            String startTime = "";
            //开始时间
            String recruitDate = resDoctorRecruit.getRecruitDate();
            //培养年限
            String trianYear = resDoctorRecruit.getTrainYear();
            String graudationYear = resDoctorRecruit.getGraduationYear();
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {

                try {
                    int year = 0;
                    year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                    if (year != 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime = "";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                int year = 0;
                if (trianYear.equals(JsResTrainYearEnum.OneYear.getId())) {
                    year = 1;
                }
                if (trianYear.equals(JsResTrainYearEnum.TwoYear.getId())) {
                    year = 2;
                }
                if (trianYear.equals(JsResTrainYearEnum.ThreeYear.getId())) {
                    year = 3;
                }
                if (year != 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);

                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    } catch (Exception e) {

                    }
                }
            }
            if (!startTime.equals(resDoctor.getCompleteStartDate()) || !endTime.equals(resDoctor.getCompleteEndDate())) {
                resDoctor.setCompleteStartDate(startTime);
                resDoctor.setCompleteEndDate(endTime);
                resDoctorBiz.editDoctor(resDoctor);
            }
            JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), resDoctorRecruit.getGraduationYear());
            model.addAttribute("jsresGraduationApply", jsresGraduationApply);
        }
        String f = "0";
        //现在只有一阶段、住院医师和助理全科 可以提交
        if (StringUtil.isNotBlank(resDoctor.getTrainingTypeId())) {
            if (resDoctor.getTrainingTypeId().equals(TrainCategoryEnum.WMFirst.getId()) || resDoctor.getTrainingTypeId().equals(TrainCategoryEnum.DoctorTrainingSpe.getId()) || resDoctor.getTrainingTypeId().equals(TrainCategoryEnum.AssiGeneral.getId())) {
                f = "1";
            }
        }
        model.addAttribute("f", f);
        model.addAttribute("resDoctor", resDoctor);
        model.addAttribute("user", sysUser);
        return "jsres/doctor/owenScore";
    }

    /**
     * 江苏西医学员考核申请Tab页
     * @param model
     * @return
     */
//	@RequestMapping(value="/asseApplicationMain")
//	public String asseApplication(Model model){
//		//获取培训记录
//		SysUser currUser = GlobalContext.getCurrentUser();
//		String doctorFlow = currUser.getUserFlow();
//		ResDoctorRecruit recruit =new ResDoctorRecruit();
//		recruit.setDoctorFlow(doctorFlow);
//		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//		// 在培
//		recruit.setDoctorStatusId("20");
//		List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
//		if(recruitList != null && !recruitList.isEmpty()){
//			model.addAttribute("recruitList", recruitList);
//		}
//		return "jsres/doctor/asseApplicationMain";
//	}

    /**
     * 江苏西医学员考核申请Tab页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/mainNew")
    public String mainNew(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        model.addAttribute("user", user);
        ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor", doctor);
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        model.addAttribute("inTime", inTime);
        List<JsresExamSignup> signups = doctorRecruitBiz.readDoctorExanSignUps(user.getUserFlow());
        model.addAttribute("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = "Y";
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = "N";
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null || (apply != null && !"GlobalPassed".equals(apply.getAuditStatusId()))) {
                        // 2019/2018/2017级助理全科全走补考报名
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = "Y";
                        }else{
                            isAllowApply = "N";
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = "N";
            String isTheoryQualifed = "N";
            String isSkill = "Y";
            String isTheory = "Y";
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
            List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = "Y";
                            break;
                        }
                    }
                } else {
                    isSkill = "N";
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if(GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)){
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = "Y";
                                break;
                            }
                        }
                    }
                } else {
                    isTheory = "N";
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && "N".equals(isSkill) && "N".equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                        isAllowApply = "Y";
                }else{
                    //从未参加过考核
                    isAllowApply = "N";
                }
            }
            if ("Y".equals(isSkillQualifed) && "Y".equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = "N";
            }
        }
        model.addAttribute("isAllowApply", isAllowApply);
        return "jsres/doctor/mainNew";
    }

    JsresGraduationApply jsresGraduationApplyAll = null;

    @RequestMapping(value = "/asseApplicationMain")
    public String asseApplicationNew(Model model) throws Exception {
        //获取培训记录
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("currUser", currUser);
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        // 在培
//		recruit.setDoctorStatusId("20");
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");

        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
            recruit = recruitList.get(0);
            String recruitFlow = recruit.getRecruitFlow();
            String applyYear = com.pinde.sci.common.util.DateUtil.getYear();

            model.addAttribute("auditStatusId", recruit.getAuditStatusId());
            model.addAttribute("doctorRecruit", recruit);

            //查询减免信息
            ResDoctorReduction reduction = reductionBiz.findReductionByRecruitFlowandStatusId(recruitFlow,"GlobalPassed");
            model.addAttribute("reduction", reduction);
            String operUserFlow = currUser.getUserFlow();
            List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
            //公共成绩
            ResScore publicScore = null;
            if (null != scorelist && scorelist.size() > 0) {
                publicScore = scorelist.get(0);
                //公共科目成绩详情
                String content = null == publicScore ? "" : publicScore.getExtScore();
                Map<String, String> extScoreMap = new HashMap<String, String>();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    Element extScoreInfo = root.element("extScoreInfo");
                    if (extScoreInfo != null) {
                        List<Element> extInfoAttrEles = extScoreInfo.elements();
                        if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                            for (Element attr : extInfoAttrEles) {
                                String attrName = attr.getName();
                                String attrValue = attr.getText();
                                extScoreMap.put(attrName, attrValue);
                            }
                        }
                    }
                }
                model.addAttribute("extScore", extScoreMap);
            }
            model.addAttribute("publicScore", publicScore);
            //个人信息
            ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(operUserFlow);
            SysUser sysUser = userBiz.readSysUser(operUserFlow);
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
            Map<String, String> practicingMap = new HashMap<>();
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }
                        model.addAttribute("userResumeExt", userResumeExt);
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if ("Y".equals(isPassQualifyingExamination)) {
                            if ("Y".equals(isHaveQualificationCertificate)) {

                                if ("Y".equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }
            model.addAttribute("practicingMap", practicingMap);
            //结业考核年份不空且小于当前年份的
            if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
                applyYear = recruit.getGraduationYear();
            }
            JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, "");
            //保存医师培训时间
            if (recruit != null) {
                String endTime = "";
                String startTime = "";
                //开始时间
                String recruitDate = recruit.getRecruitDate();
                //培养年限
                String trianYear = recruit.getTrainYear();
                String graudationYear = recruit.getGraduationYear();
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                    try {
                        int year = 0;
                        year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                        if (year != 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());
                        }
                    } catch (Exception e) {
                        endTime = "";
                    }
                }
                //如果没有结业考核年份，按照届别与培养年限计算
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                    int year = 0;
                    if (JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                        year = 1;
                    }
                    if (JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                        year = 2;
                    }
                    if (JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
                        year = 3;
                    }
                    if (year != 0) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());

                        } catch (Exception e) {

                        }
                    }
                }
                model.addAttribute("startDate", startTime);
                model.addAttribute("endDate", endTime);
            }
            String apply = "N";
            //判断为当前年，当前提交时间段内，学员非二阶段，且未提交的可以提交
            String currYear = DateUtil.getYear();
            Integer yearNew = Integer.valueOf(currYear) - 6;
            boolean applyFlag = false;
            if (yearNew <= Integer.valueOf(recruit.getGraduationYear()) && Integer.valueOf(currYear) >= Integer.valueOf(recruit.getGraduationYear())) {
                applyFlag = true;
            }
            String currTime = DateUtil.getCurrDateTime();
            String currDateTime = DateUtil.getCurrDateTime2();
            String orgFlow = sysUser.getOrgFlow();
            if (StringUtil.isBlank(orgFlow)) {
                orgFlow = resDoctor.getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
            //当前城市的学员有没有正在进行的考试
            Boolean inTime = resTestConfigList.size() > 0 ? true : false;
            String inApplyTime = resTestConfigList.size() > 0 ? "Y" : "N";
            // 判断学员有没有重新提交
            if (null != jsresGraduationApply) {
                // 查询结业申请创建时间内的考试时间没有结束的考试信息
                resTestConfigList = resTestConfigBiz.findEffectiveByParam(currDateTime, DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
                inApplyTime = resTestConfigList.size() > 0 ? "Y" : "N";
            }
            Boolean isWaitAudit = false;//是否待审核
            if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())
                    && (!"JointAuditing".equals(jsresGraduationApply.getAuditStatusId()) || !"Auditing".equals(jsresGraduationApply.getAuditStatusId())
                    || !"WaitChargePass".equals(jsresGraduationApply.getAuditStatusId()) || !"WaitGlobalPass".equals(jsresGraduationApply.getAuditStatusId()))
            ) {
                isWaitAudit = true;
            }
            if (recruit != null && applyFlag && inTime
                    && !TrainCategoryEnum.WMSecond.getId().equals(recruit.getCatSpeId())
                    && !isWaitAudit) {
                apply = "Y";
            }
            //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
            String isAssiGeneral = "";
            if (recruit != null && ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                    || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId()))) {
                isAssiGeneral = "Y";
            } else {
                isAssiGeneral = "N";
            }
            List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
            PubFile file = null;
            if (files != null && files.size() > 0) {
                file = files.get(0);
                model.addAttribute("file", file);
            }
            model.addAttribute("inApplyTime", inApplyTime);
            model.addAttribute("apply", apply);
            model.addAttribute("resDoctor", resDoctor);
            model.addAttribute("user", sysUser);
            model.addAttribute("isAssiGeneral", isAssiGeneral);
            if ((jsresGraduationApply != null) && (jsresGraduationApply.getRemark() != null) && (jsresGraduationApply.getRemark().equals("undefined"))) {
                jsresGraduationApply.setRemark("无");
            }

            model.addAttribute("jsresGraduationApply", jsresGraduationApply);
            jsresGraduationApplyAll = jsresGraduationApply;

            model.addAttribute("recruitFlow", recruitFlow);

            showMaterials(model, recruit, applyYear, jsresGraduationApply);
            List<Map<String, String>> spes = new ArrayList<>();
            if (recruit != null) {
                Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
                spes = speMap.get(recruit.getSpeId());
            }
            if (spes != null && spes.size() > 0) {
                model.addAttribute("needChange", "Y");
                model.addAttribute("spes", spes);
            } else {
                model.addAttribute("needChange", "N");
            }
            // 2019级以前的助理全科学员只能走补考报名
            if("2019".compareTo(resDoctor.getSessionNumber()) >= 0 && "50".equals(resDoctor.getTrainingSpeId())){
                model.addAttribute("submitBtnShow", "N");
            }else{
                model.addAttribute("submitBtnShow", "Y");
            }
        }
        return "jsres/doctor/asseApplicationMain";
    }

    @RequestMapping(value = "/showReductionImg")
    public String showReductionImg(Model model,String recordFlow){
        List<PubFile> reductionFiles = fileBiz.findFileByTypeFlow("Reduction",recordFlow);
        model.addAttribute("reductionFiles", reductionFiles);
        return "jsres/doctor/showReductionImg";
    }


    /**
     * 江苏西医学员证书申请Tab页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/certificateManageMain")
    public String certificateManageMain(Model model) {
        return "jsres/doctor/certificateManageMain";
    }

    @RequestMapping(value = "/certificateApply")
    public String certificateApply(Model model) throws Exception {
        //获取培训记录
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("currUser", currUser);
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        // 在培
//		recruit.setDoctorStatusId("20");
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
            recruit = recruitList.get(0);
            String recruitFlow = recruit.getRecruitFlow();
            String applyYear = com.pinde.sci.common.util.DateUtil.getYear();

            model.addAttribute("auditStatusId", recruit.getAuditStatusId());
            model.addAttribute("doctorRecruit", recruit);

            String operUserFlow = currUser.getUserFlow();
            List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
            //公共成绩
            ResScore publicScore = null;
            if (null != scorelist && scorelist.size() > 0) {
                publicScore = scorelist.get(0);
                //公共科目成绩详情
                String content = null == publicScore ? "" : publicScore.getExtScore();
                Map<String, String> extScoreMap = new HashMap<String, String>();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    Element extScoreInfo = root.element("extScoreInfo");
                    if (extScoreInfo != null) {
                        List<Element> extInfoAttrEles = extScoreInfo.elements();
                        if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                            for (Element attr : extInfoAttrEles) {
                                String attrName = attr.getName();
                                String attrValue = attr.getText();
                                extScoreMap.put(attrName, attrValue);
                            }
                        }
                    }
                }
                model.addAttribute("extScore", extScoreMap);
            }
            model.addAttribute("publicScore", publicScore);
            //个人信息
            ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(operUserFlow);
            SysUser sysUser = userBiz.readSysUser(operUserFlow);
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
            Map<String, String> practicingMap = new HashMap<>();

            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }
                        model.addAttribute("userResumeExt", userResumeExt);
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if ("Y".equals(isPassQualifyingExamination)) {
                            if ("Y".equals(isHaveQualificationCertificate)) {

                                if ("Y".equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }
            model.addAttribute("practicingMap", practicingMap);
            //结业考核年份不空且小于当前年份的
            if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
                applyYear = recruit.getGraduationYear();
            }
            JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
            //保存医师培训时间
            if (recruit != null) {
                String endTime = "";
                String startTime = "";
                //开始时间
                String recruitDate = recruit.getRecruitDate();
                //培养年限
                String trianYear = recruit.getTrainYear();
                String graudationYear = recruit.getGraduationYear();
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                    try {
                        int year = 0;
                        year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                        if (year != 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());
                        }
                    } catch (Exception e) {
                        endTime = "";
                    }
                }
                //如果没有结业考核年份，按照届别与培养年限计算
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                    int year = 0;
                    if (JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                        year = 1;
                    }
                    if (JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                        year = 2;
                    }
                    if (JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
                        year = 3;
                    }
                    if (year != 0) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());

                        } catch (Exception e) {

                        }
                    }
                }
                model.addAttribute("startDate", startTime);
                model.addAttribute("endDate", endTime);
            }

            //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
            String isAssiGeneral = "";
            if (recruit != null && ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                    || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId()))) {
                isAssiGeneral = "Y";
            } else {
                isAssiGeneral = "N";
            }
            List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
            PubFile file = null;
            if (files != null && files.size() > 0) {
                file = files.get(0);
                model.addAttribute("file", file);
            }
            model.addAttribute("resDoctor", resDoctor);
            model.addAttribute("user", sysUser);
            model.addAttribute("isAssiGeneral", isAssiGeneral);
            model.addAttribute("jsresGraduationApply", jsresGraduationApply);
            model.addAttribute("recruitFlow", recruitFlow);

            //证书申请确认前学员数据比例重新计算
            if (null != jsresGraduationApply && "Y".equals(jsresGraduationApply.getDoctorIsApply())) {
                jsresGraduationApplyBiz.updatePer(jsresGraduationApply.getApplyFlow(), recruitFlow, "", recruit.getDoctorFlow(), applyYear);
                //更新数据填写度一天内及一小时内比例
//				jsresGraduationApplyBiz.deleteResRecPer(recruit.getDoctorFlow(),applyYear);
//				jsresGraduationApplyBiz.updateResRecPer(recruit.getDoctorFlow(),applyYear);
            }
            showMaterials(model, recruit, applyYear, jsresGraduationApply);

            List<Map<String, String>> spes = new ArrayList<>();
            if (recruit != null) {
                Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
                spes = speMap.get(recruit.getSpeId());
            }
            if (spes != null && spes.size() > 0) {
                model.addAttribute("needChange", "Y");
                model.addAttribute("spes", spes);
            } else {
                model.addAttribute("needChange", "N");
            }
            //结业成绩
            scorelist = resScoreBiz.selectByExampleWithBLOBs2(operUserFlow, "Y");
            List<ResScore> skillList = new ArrayList<ResScore>();
            List<ResScore> theoryList = new ArrayList<ResScore>();
            if (null != scorelist && scorelist.size() > 0) {
                for (ResScore resScore : scorelist) {
                    if (ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                        skillList.add(resScore);
                    } else if (ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                        theoryList.add(resScore);
                    }
                }
            }
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
            if (skillList.size() > 0) {
                //获取出3年内的所有技能成绩
                skillList = skillList.stream().filter(resScore -> StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                skillList.sort(Comparator.comparing(resScore -> resScore.getScorePhaseId()));
                Collections.reverse(skillList);
            }
            if (theoryList.size() > 0) {
                //获取出3年内的所有理论成绩
                theoryList = theoryList.stream().filter(resScore -> StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                theoryList.sort(Comparator.comparing(resScore -> resScore.getScorePhaseId()));
                Collections.reverse(theoryList);
            }
            List<JsresExamSignup> signups = doctorRecruitBiz.readDoctorExanSignUps(operUserFlow);
            Map<String, String> isExamSign = new HashMap();
            if (skillList.size() > 0) {
                for (ResScore resScore : skillList) {
                    if (signups != null && signups.size() > 0) {
                        //判断当前这条技能成绩是不是补考的
                        List<JsresExamSignup> examSignups = signups.stream().filter(jsresExamSignup -> "Skill".equals(jsresExamSignup.getSignupTypeId()) &&
                                resScore.getScorePhaseId().equals(jsresExamSignup.getSignupYear()) && StringUtil.isNotBlank(resScore.getTestId()) &&
                                resScore.getTestId().equals(jsresExamSignup.getTestId())).collect(Collectors.toList());
                        isExamSign.put(resScore.getScoreFlow(), examSignups.size() > 0 ? "Y" : "N");
                    }
                }
            }
            if (theoryList.size() > 0) {
                for (ResScore resScore : theoryList) {
                    if (signups != null && signups.size() > 0) {
                        //判断当前这条理论成绩是不是补考的
                        List<JsresExamSignup> examSignups = signups.stream().filter(jsresExamSignup -> "Theory".equals(jsresExamSignup.getSignupTypeId()) &&
                                resScore.getScorePhaseId().equals(jsresExamSignup.getSignupYear()) && StringUtil.isNotBlank(resScore.getTestId()) &&
                                resScore.getTestId().equals(jsresExamSignup.getTestId())).collect(Collectors.toList());
                        isExamSign.put(resScore.getScoreFlow(), examSignups.size() > 0 ? "Y" : "N");
                    }
                }
            }
            model.addAttribute("isExamSign", isExamSign);
            model.addAttribute("skillList", skillList);
            model.addAttribute("theoryList", theoryList);
        }
        return "jsres/doctor/certificateApply";
    }

    @RequestMapping(value = "/saveCertificateApply")
    @ResponseBody
    public String saveCertificateApply(String applyFlow) {
        JsresGraduationApply apply = jsresGraduationApplyBiz.readByFlow(applyFlow);
        apply.setDoctorIsApply("Y");
        jsresGraduationApplyBiz.editGraduationApply(apply);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value = "/certificateShow")
    public String certificateShow(Model model) throws Exception {
        //获取培训记录
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("currUser", currUser);
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        // 在培
//		recruit.setDoctorStatusId("20");
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        if (recruitList != null && !recruitList.isEmpty()) {
            recruit = recruitList.get(0);
            model.addAttribute("doctorRecruit", recruit);
        }
        return "jsres/doctor/certificateShow";
    }

    /**
     * 江苏西医学员考核申请展示页
     *
     * @param model
     * @param recruitFlow
     * @param doctorFlow
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/getAsseApplication")
    public String getAsseApplication(Model model, String recruitFlow, String doctorFlow, String applyYear) throws DocumentException {
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("currUser", currUser);
        //培训记录
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        if (recruit != null) {
            model.addAttribute("auditStatusId", recruit.getAuditStatusId());
            model.addAttribute("doctorRecruit", recruit);
        }
        String operUserFlow = currUser.getUserFlow();
        List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
        //公共成绩
        ResScore publicScore = null;
        if (null != scorelist && scorelist.size() > 0) {
            publicScore = scorelist.get(0);
            //公共科目成绩详情
            String content = null == publicScore ? "" : publicScore.getExtScore();
            Map<String, String> extScoreMap = new HashMap<String, String>();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScoreMap.put(attrName, attrValue);
                        }
                    }
                }
            }
            model.addAttribute("extScore", extScoreMap);
        }
        model.addAttribute("publicScore", publicScore);
        //个人信息
        ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(operUserFlow);
        SysUser sysUser = userBiz.readSysUser(operUserFlow);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        Map<String, String> practicingMap = new HashMap<>();

        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                    String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                    String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                    String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                    if ("Y".equals(isPassQualifyingExamination)) {
                        if ("Y".equals(isHaveQualificationCertificate)) {

                            if ("Y".equals(isHavePracticingCategory)) {

                                practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            } else {

                                practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {

                            practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                            practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                        }

                    } else {
                        practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                        practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                        practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                        practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                        practicingMap.put("graduationCategoryId", "暂无");//执业类型
                        practicingMap.put("graduationCategoryName", "暂无");//执业类型

                        practicingMap.put("graduationScopeId", "暂无");//执业范围
                        practicingMap.put("graduationScopeName", "暂无");//执业范围

                        practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                    }
                }
            }
        }
        model.addAttribute("practicingMap", practicingMap);
        //结业考核年份不空且小于当前年份的
        if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
            applyYear = recruit.getGraduationYear();
        }
        JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
        //保存医师培训时间
        if (recruit != null) {
            String endTime = "";
            String startTime = "";
            //开始时间
            String recruitDate = recruit.getRecruitDate();
            //培养年限
            String trianYear = recruit.getTrainYear();
            String graudationYear = recruit.getGraduationYear();
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                try {
                    int year = 0;
                    year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                    if (year != 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime = "";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                int year = 0;
                if (JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                    year = 1;
                }
                if (JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                    year = 2;
                }
                if (JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
                    year = 3;
                }
                if (year != 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());

                    } catch (Exception e) {

                    }
                }
            }
            model.addAttribute("startDate", startTime);
            model.addAttribute("endDate", endTime);
        }
        String apply = "N";
        //判断为当前年，当前提交时间段内，学员非二阶段，且未提交的可以提交
        String currYear = DateUtil.getYear();
        String currTime = DateUtil.getCurrDateTime();
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = sysUser.getOrgFlow();
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = resDoctor.getOrgFlow();
        }
        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        String inApplyTime = resTestConfigList.size() > 0 ? "Y" : "N";
        // 判断学员有没有重新提交
        if (null != jsresGraduationApply) {
            // 查询结业申请创建时间内的考试时间没有结束的考试信息
            resTestConfigList = resTestConfigBiz.findEffectiveByParam(currDateTime, DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
            inApplyTime = resTestConfigList.size() > 0 ? "Y" : "N";
        }
        Boolean isWaitAudit = false;//是否待审核
        if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
            isWaitAudit = true;
        }
        if (recruit != null && currYear.equals(recruit.getGraduationYear()) && inTime
                && !TrainCategoryEnum.WMSecond.getId().equals(recruit.getCatSpeId())
                && !isWaitAudit) {
            apply = "Y";
        }
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        String isAssiGeneral = "";
        if (recruit != null && ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId()))) {
            isAssiGeneral = "Y";
        } else {
            isAssiGeneral = "N";
        }
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
        PubFile file = null;
        if (files != null && files.size() > 0) {
            file = files.get(0);
            model.addAttribute("file", file);
        }
        model.addAttribute("inApplyTime", inApplyTime);
        model.addAttribute("apply", apply);
        model.addAttribute("resDoctor", resDoctor);
        model.addAttribute("user", sysUser);
        model.addAttribute("isAssiGeneral", isAssiGeneral);
        model.addAttribute("jsresGraduationApply", jsresGraduationApply);
        model.addAttribute("recruitFlow", recruitFlow);
        showMaterials(model, recruit, applyYear, jsresGraduationApply);
        List<Map<String, String>> spes = new ArrayList<>();
        if (recruit != null) {
            Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
            spes = speMap.get(recruit.getSpeId());
        }
        if (spes != null && spes.size() > 0) {
            model.addAttribute("needChange", "Y");
            model.addAttribute("spes", spes);
        } else {
            model.addAttribute("needChange", "N");
        }
        return "jsres/doctor/asseApplication";
    }

    private Map<String, List<Map<String, String>>> setSpeMap(Model model) {
        Map<String, List<Map<String, String>>> speMap = new HashMap<>();
        List<Map<String, String>> spes = new ArrayList<>();
        Map<String, String> spe = new HashMap<>();
        spe.put("speId", "0400");
        spe.put("speName", "皮肤科");
        spes.add(spe);
        speMap.put("10", spes);//		皮肤性病科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0700");
        spe.put("speName", "全科");
        spes.add(spe);
        speMap.put("52", spes);//		全科方向（西医）
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0900");
        spe.put("speName", "外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1000");
        spe.put("speName", "外科（神经外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1100");
        spe.put("speName", "外科（胸心外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1200");
        spe.put("speName", "外科（泌尿外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1300");
        spe.put("speName", "外科（整形外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1400");
        spe.put("speName", "骨科");
        spes.add(spe);
        speMap.put("2", spes);//		外科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2000");
        spe.put("speName", "临床病理科");
        spes.add(spe);
        speMap.put("7", spes);//		病理科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2100");
        spe.put("speName", "检验医学科");
        spes.add(spe);
        speMap.put("4", spes);//		医学检验科
        spes = new ArrayList<>();

        spe = new HashMap<>();
        spe.put("speId", "2200");
        spe.put("speName", "放射科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2500");
        spe.put("speName", "放射肿瘤科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2400");
        spe.put("speName", "核医学科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2300");
        spe.put("speName", "超声医学科");
        spes.add(spe);
        speMap.put("6", spes);//		医学影像科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2800");
        spe.put("speName", "口腔全科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2900");
        spe.put("speName", "口腔内科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3000");
        spe.put("speName", "口腔颌面外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3100");
        spe.put("speName", "口腔修复科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3200");
        spe.put("speName", "口腔正畸科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3300");
        spe.put("speName", "口腔病理科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3400");
        spe.put("speName", "口腔颌面影像科");
        spes.add(spe);
        speMap.put("17", spes);//		口腔科
        return speMap;
    }

    Map<String, Object> avgBiMapAll = new HashMap<>();

    private void showMaterials(Model model, ResDoctorRecruit recruit, String applyYear, JsresGraduationApply jsresGraduationApply) throws DocumentException {
        //培训方案
        SchRotation rotation = null;
        if (recruit != null && StringUtil.isNotBlank(recruit.getRotationFlow())) {
            rotation = rotationBiz.readSchRotation(recruit.getRotationFlow());
        } else {
            rotation = rotationBiz.getRotationByRecruit(recruit);
        }
        if (rotation != null && recruit != null && StringUtil.isNotBlank(rotation.getRotationFlow())) {
            model.addAttribute("rotation", rotation);
            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();
            ResDoctor resDoctor = resDoctorBiz.findByFlow(doctorFlow);
            resDoctor.setRotationFlow(rotationFlow);
            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctorFlow, rotationFlow);
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            Map<String, Integer> groupRowSpan = new HashMap<>();
            Map<String, Integer> deptRowSpan = new HashMap<>();


            //方案中的组
            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, Object> afterImgMap = new HashMap<String, Object>();
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String, Object> biMap = new HashMap<>();

            float allMonth = 0;

            //计算轮转时间内登记进度
            List<ResRec> recList = new ArrayList<ResRec>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("doctorFlow", resDoctor.getDoctorFlow());
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                Integer count = groupRowSpan.get(dept.getGroupFlow());
                if (count == null)
                    count = 0;
                count++;
                groupRowSpan.put(dept.getGroupFlow(), count);
                temp.add(dept);
                // 优化：此处查询之后并未使用recs，暂时注释
                String groupFlow = dept.getGroupFlow();
                String standardDeptId = dept.getStandardDeptId();
                paramMap.put("standardGroupFlow", groupFlow);
                paramMap.put("standardDeptId", standardDeptId);
                paramMap.put("processFlow", dept.getRecordFlow());
                List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
                if (recs != null && !recs.isEmpty()) {
                    recList.addAll(recs);
                }
                //轮转科室
                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
                if (resultList != null && resultList.size() > 0) {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    resultMap.put(key, resultList);
                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), resultList.size() + t);
                    deptRowSpan.put(key, resultList.size());
                    for (SchArrangeResult result : resultList) {
                        Float month = realMonthMap.get(key);
                        if (month == null) {
                            month = 0f;
                        }
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            month += realMonthF;
                            allMonth += realMonthF;
                        }
                        realMonthMap.put(key, month);
                    }
                } else {

                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), 1 + t);
                }

                //完成比例与审核比例
//				JsresDoctorDeptDetail doctorDeptDetail=resultBiz.deptDoctorWorkDetail(dept.getRecordFlow(),dept.getRotationFlow(),doctorFlow);
//				biMap.put(dept.getRecordFlow(),doctorDeptDetail);

            }
            Map<String, Object> avgBiMap = new HashMap<>();
            if (jsresGraduationApply == null) {
                //出科考核表
                List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                        String content = null == rec ? "" : rec.getRecContent();
                        if (StringUtil.isNotBlank(content)) {
                            Document doc = DocumentHelper.parseText(content);
                            Element root = doc.getRootElement();
                            List<Element> imageEles = root.elements();
                            if (imageEles != null && imageEles.size() > 0) {
                                for (Element image : imageEles) {
                                    Map<String, Object> recContent = new HashMap<String, Object>();
                                    String imageFlow = image.attributeValue("imageFlow");
                                    List<Element> elements = image.elements();
                                    for (Element attr : elements) {
                                        String attrName = attr.getName();
                                        String attrValue = attr.getText();
                                        recContent.put(attrName, attrValue);
                                    }
                                    imagelist.add(recContent);
                                }
                            }
                        }
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetailByNow(recruit.getRecruitFlow(), doctorFlow, applyYear);
                if (details != null && details.size() > 0) {
                    int isShortY = 0;
                    int isShortN = 0;
                    int shortYCount = 0;
                    int shortNCount = 0;
                    double shortYCBSum = 0;//完成比例
                    double shortNCBSum = 0;
                    double shortYOCBSum = 0;//补填比例
                    double shortNOCBSum = 0;
                    double shortYABSum = 0;//审核 比例
                    double shortNABSum = 0;
                    double avgComBi = 0;//平均完成比例
                    double avgOutComBi = 0;//平均补填比例
                    double avgAuditComBi = 0;//平均审核比例
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                        if ("Y".equals(d.getIsAdd())) {
                            if ("Y".equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortYCount++;
                                    shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortY == 0) {
                                    isShortY = 1;
                                }
                            }
                            if ("N".equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortNCount++;
                                    shortNCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortNOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortNABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortN == 0) {
                                    isShortN = 1;
                                }
                            }
                        }
                    }
                    //平均完成比例与平均审核比例
                    if ((isShortY + isShortN) > 1) {
                        if ((shortYCount) != 0) {
                            avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    } else {
                        if ((shortYCount + shortNCount) != 0) {
                            avgComBi = new BigDecimal((shortYCBSum + shortNCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal((shortYOCBSum + shortNOCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal((shortYABSum + shortNABSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    }

                    avgBiMap.put("avgComplete", avgComBi);
                    avgBiMap.put("avgOutComplete", avgOutComBi);
                    avgBiMap.put("avgAudit", avgAuditComBi);
                }
            }
            model.addAttribute("allMonth", allMonth);
            if (jsresGraduationApply != null) {
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetail(rotation.getRotationFlow(), doctorFlow, jsresGraduationApply.getApplyYear());
                if (details != null && details.size() > 0) {
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                    }
                }
                //平均完成比例与平均审核比例
                avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), jsresGraduationApply.getApplyYear());

                //出科考核表
                List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                        String content = null == rec ? "" : rec.getRecContent();
                        if (StringUtil.isNotBlank(content)) {
                            Document doc = DocumentHelper.parseText(content);
                            Element root = doc.getRootElement();
                            List<Element> imageEles = root.elements();
                            if (imageEles != null && imageEles.size() > 0) {
                                for (Element image : imageEles) {
                                    Map<String, Object> recContent = new HashMap<String, Object>();
                                    String imageFlow = image.attributeValue("imageFlow");
                                    List<Element> elements = image.elements();
                                    for (Element attr : elements) {
                                        String attrName = attr.getName();
                                        String attrValue = attr.getText();
                                        recContent.put(attrName, attrValue);
                                    }
                                    imagelist.add(recContent);
                                }
                            }
                        }
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
            }
//            if (jsresGraduationApply != null) {
//                //平均完成比例与平均审核比例
//                avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), jsresGraduationApply.getApplyYear());
//            }
            model.addAttribute("avgBiMap", avgBiMap);
            avgBiMapAll = avgBiMap;

            model.addAttribute("biMap", biMap);//各科室比例
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("afterImgMap", afterImgMap);

            model.addAttribute("resultMap", resultMap);
            model.addAttribute("groupRowSpan", groupRowSpan);
            model.addAttribute("deptRowSpan", deptRowSpan);
            model.addAttribute("realMonthMap", realMonthMap);

        }
    }

    @RequestMapping(value = "/saveRegisteManua")
    @ResponseBody
    public String saveRegisteManua(String registeManua) throws DocumentException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(currentUser.getUserFlow());
        if (pubUserResume != null) {
            //旧的数据中xmlContent没有registeManua节点
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean拓展类
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    userResumeExt.setRegisteManua(registeManua);
                    String newXmlContent = JaxbUtil.convertToXml(userResumeExt);
                    pubUserResume.setUserResume(newXmlContent);
                    userResumeBiz.savePubUserResume(currentUser, pubUserResume);
                }
            }
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return "请到培训信息填写个人基本信息！";
        }
    }

    /**
     * 江苏西医学员考核申请保存分数
     *
     * @param score
     * @param scoreType 公共科目成绩类型
     * @return
     */
    @RequestMapping(value = "/saveAsseScore")
    @ResponseBody
    public String saveAsseScore(String score, String scoreType, String recruitFlow) throws DocumentException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResScore resScore = new ResScore();
        if (StringUtil.isNotBlank(scoreType)) {
            resScore.setDoctorFlow(currentUser.getUserFlow());//医师流水号
            resScore.setScoreTypeId(ResScoreTypeEnum.PublicScore.getId());
            resScore.setScoreTypeName(ResScoreTypeEnum.PublicScore.getName());
            resScore.setRecruitFlow(recruitFlow);
            if (scoreType.equals("theoryScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setTheoryScore(bd);
                } else {
                    return "请输入正确数字！";
                }
            } else if (scoreType.equals("skillScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setSkillScore(bd);
                } else {
                    return "请选择是否合格！";
                }
            } else {
                List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
                //公共成绩
                Map<String, String> extScoreMap = new HashMap<String, String>();
                extScoreMap.put("lawScore", "");
                extScoreMap.put("medicineScore", "");
                extScoreMap.put("clinicalScore", "");
                extScoreMap.put("ckScore", "");
                ResScore publicScore = null;
                if (null != scorelist && scorelist.size() > 0) {
                    publicScore = scorelist.get(0);
                    //公共科目成绩详情
                    String content = null == publicScore ? "" : publicScore.getExtScore();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScoreMap.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                }
                if (StringUtil.isNotBlank(scoreType)) {
                    extScoreMap.put(scoreType, score);
                }
                String extScore = null;
                try {
                    extScore = resDoctorBiz.convertMapToXml(extScoreMap, resScore);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (StringUtil.isNotBlank(extScore)) {
                    resScore.setExtScore(extScore);

                }
            }
        }
        int i = 0;
        i = resDoctorBiz.savePublic(resScore);
        if (i == 1) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 江苏西医学员考核申请上传成绩信息附件
     *
     * @param file
     * @param productFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadApplicationFile"})
    @ResponseBody
    public String uploadApplicationFile(MultipartFile file, String productFlow) throws Exception {
        if (file != null && !file.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkImg(file);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages" + File.separator + "asseApplication");
                List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", productFlow);
                PubFile pubFile = null;
                if (files != null && files.size() > 0) {
                    pubFile = files.get(0);
                } else {
                    pubFile = new PubFile();
                }
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(productFlow);
                pubFile.setFilePath(resultPath);
                pubFile.setFileName(file.getOriginalFilename());
                pubFile.setProductType("asseApplication");
                fileBiz.editFile(pubFile);
                return resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 结业考核申请下删除上传分数附件
     *
     * @param recruitFlow
     * @return
     */
    @RequestMapping(value = "/delScoreFile")
    @ResponseBody
    public String delScoreFile(String recruitFlow) {
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
        PubFile file = null;
        int i = 0;
        if (files != null && files.size() > 0) {
            file = files.get(0);
            file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            i = fileBiz.editFile(file);
        }
        if (i == 1) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 考核申请
     *
     * @param recruitFlow
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = {"/asseApply"})
    @ResponseBody
    public synchronized String asseApply(String recruitFlow, String doctorFlow, String applyYear, String changeSpeId, String remark) throws DocumentException {

        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            return "结业考核年份不是当前年，无法申请！";
        }
        JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, "");
        if (jsresGraduationApply == null) {

            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(recruit.getDoctorFlow());
            Map<String, String> practicingMap = new HashMap<>();

            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if ("Y".equals(isPassQualifyingExamination)) {
                            if ("Y".equals(isHaveQualificationCertificate)) {

                                if ("Y".equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }

            //培训记录
            //培训方案
            SchRotation rotation = rotationBiz.getRotationByRecruit(recruit);
            jsresGraduationApply = new JsresGraduationApply();
            jsresGraduationApply.setRemark(remark);
            jsresGraduationApply.setRecruitFlow(recruitFlow);
            if (rotation != null) {
                jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
                jsresGraduationApply.setRotationName(rotation.getRotationName());
            }
            jsresGraduationApply.setApplyYear(applyYear);
            String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
            //如果user表中没有org_flow去医师表中的
            if (StringUtil.isBlank(orgFlow)) {
                orgFlow = resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow()).getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
            //如果当前学员是协同基地取他主基地所在的城市在当前时间有没有正在进行的考试
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
            if (resTestConfigList.size() > 0) {
                ResTestConfig resTestConfig = resTestConfigList.get(0);
                jsresGraduationApply.setTestId(resTestConfig.getTestId());
                //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
                if (StringUtil.isNotBlank(recruit.getJointOrgFlow()) && "Y".equals(resTestConfig.getJointLocalAudit())) {
                    if ("DoctorTrainingSpe".equals(recruit.getCatSpeId())) {
                        jsresGraduationApply.setAuditStatusId("JointAuditing");
                        jsresGraduationApply.setAuditStatusName("待协同基地审核");
                    } else {
                        jsresGraduationApply.setAuditStatusId(JsResAuditStatusEnum.Auditing.getId());
                        jsresGraduationApply.setAuditStatusName(JsResAuditStatusEnum.Auditing.getName());
                    }
                } else if ("Y".equals(resTestConfig.getLocalAudit())) {
                    jsresGraduationApply.setAuditStatusId(JsResAuditStatusEnum.Auditing.getId());
                    jsresGraduationApply.setAuditStatusName(JsResAuditStatusEnum.Auditing.getName());
                } else if ("Y".equals(resTestConfig.getChargeAudit())) {
                    jsresGraduationApply.setAuditStatusId(JsResAuditStatusEnum.WaitChargePass.getId());
                    jsresGraduationApply.setAuditStatusName(JsResAuditStatusEnum.WaitChargePass.getName());
                } else {
                    jsresGraduationApply.setAuditStatusId(JsResAuditStatusEnum.WaitGlobalPass.getId());
                    jsresGraduationApply.setAuditStatusName(JsResAuditStatusEnum.WaitGlobalPass.getName());
                }
            } else {
                return "当前时间没有正在进行的考试！";
            }

            int i = jsresGraduationApplyBiz.editGraduationApply2(jsresGraduationApply, recruitFlow, changeSpeId, recruit.getDoctorFlow(), applyYear, practicingMap);

            return i + "";
        } else {
            return "-1";
        }
    }

    @RequestMapping(value = "/printDoc")
    public String printDoc(Model model, String recruitFlow, String doctorFlow, String applyYear) throws DocumentException {
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("currUser", currUser);
        //培训记录
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("auditStatusId", recruit.getAuditStatusId());
        model.addAttribute("doctorRecruit", recruit);
        //个人信息
        ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(currUser.getUserFlow());
        SchRotation rotation = rotationBiz.readSchRotation(resDoctor.getRotationFlow());
//		if (resDoctor.getSessionNumber().compareTo("2019") < 0) {
//			rotation = rotationBiz.getRotationByRecruitNew(recruit);
//		} else {
//			//默认赋值学员方案
//			rotation = rotationBiz.getRotationByRecruit(recruit);
//		}
        String operUserFlow = currUser.getUserFlow();
        List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
        //公共成绩
        ResScore publicScore = null;
        if (null != scorelist && scorelist.size() > 0) {
            publicScore = scorelist.get(0);
            //公共科目成绩详情
            String content = null == publicScore ? "" : publicScore.getExtScore();
            Map<String, String> extScoreMap = new HashMap<String, String>();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScoreMap.put(attrName, attrValue);
                        }
                    }
                }
            }
            model.addAttribute("extScore", extScoreMap);
        }
        model.addAttribute("publicScore", publicScore);
        SysUser sysUser = userBiz.readSysUser(operUserFlow);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            Map<String, String> practicingMap = new HashMap<>();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                    //					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                    String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                    String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                    String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                    if ("Y".equals(isPassQualifyingExamination)) {
                        if ("Y".equals(isHaveQualificationCertificate)) {

                            if ("Y".equals(isHavePracticingCategory)) {

                                practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                            } else {

                                practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {

                            practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                            practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                        }

                    } else {
                        practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                        practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                        practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                        practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                        practicingMap.put("graduationCategoryId", "暂无");//执业类型
                        practicingMap.put("graduationCategoryName", "暂无");//执业类型

                        practicingMap.put("graduationScopeId", "暂无");//执业范围
                        practicingMap.put("graduationScopeName", "暂无");//执业范围

                        practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                    }
                    model.addAttribute("practicingMap", practicingMap);
                }
            }
        }

        //结业考核年份不空且小于当前年份的
        if (StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
            applyYear = recruit.getGraduationYear();
        }
        JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, "");
        //保存医师培训时间
        if (recruit != null) {
            String endTime = "";
            String startTime = "";
            //开始时间
            String recruitDate = recruit.getRecruitDate();
            //培养年限
            String trianYear = recruit.getTrainYear();
            String graudationYear = recruit.getGraduationYear();
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                try {
                    int year = 0;
                    year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));

                    if (year != 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime = "";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                int year = 0;
                if (trianYear.equals(JsResTrainYearEnum.OneYear.getId())) {
                    year = 1;
                }
                if (trianYear.equals(JsResTrainYearEnum.TwoYear.getId())) {
                    year = 2;
                }
                if (trianYear.equals(JsResTrainYearEnum.ThreeYear.getId())) {
                    year = 3;
                }
                if (year != 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());

                    } catch (Exception e) {

                    }
                }
            }
            model.addAttribute("startDate", startTime);
            model.addAttribute("endDate", endTime);
        }

        /*Map<String, Object> avgBiMap = new HashMap<>();
        if (jsresGraduationApply == null) {
            //完成比例与审核比例
            List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetailByNow(recruit.getRecruitFlow(), doctorFlow, jsresGraduationApply.getApplyYear());
            if (details != null && details.size() > 0) {
                int isShortY = 0;
                int isShortN = 0;
                int shortYCount = 0;
                int shortNCount = 0;
                double shortYCBSum = 0;//完成比例
                double shortNCBSum = 0;
                double shortYOCBSum = 0;//补填比例
                double shortNOCBSum = 0;
                double shortYABSum = 0;//审核 比例
                double shortNABSum = 0;
                double avgComBi = 0;//平均完成比例
                double avgOutComBi = 0;//平均补填比例
                double avgAuditComBi = 0;//平均审核比例
                for (JsresDoctorDeptDetail d : details) {
                    if ("Y".equals(d.getIsShort())) {
                        shortYCount++;
                        shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                        shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                        shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                        if (isShortY == 0) {
                            isShortY = 1;
                        }
                    }
                    if ("N".equals(d.getIsShort())) {
                        shortNCount++;
                        shortNCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                        shortNOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                        shortNABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                        if (isShortN == 0) {
                            isShortN = 1;
                        }
                    }
                }
                //平均完成比例与平均审核比例
                if ((isShortY + isShortN) > 1) {
                    if ((shortYCount) != 0) {
                        avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                } else {
                    if ((shortYCount + shortNCount) != 0) {
                        avgComBi = new BigDecimal((shortYCBSum + shortNCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgOutComBi = new BigDecimal((shortYOCBSum + shortNOCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgAuditComBi = new BigDecimal((shortYABSum + shortNABSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
                avgBiMap.put("avgComplete", avgComBi);
                avgBiMap.put("avgOutComplete", avgOutComBi);
                avgBiMap.put("avgAudit", avgAuditComBi);
            }
        }
        if (jsresGraduationApply != null) {
            //平均完成比例与平均审核比例
            avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), jsresGraduationApply.getApplyYear());
        }
        model.addAttribute("avgBiMap", avgBiMap);

        if (rotation != null) {
            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus
                    (doctorFlow, rotation.getRotationFlow());
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            float allMonth = 0;
            //方案中的科室
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());
            for (SchRotationDept dept : deptList) {
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                //轮转科室
                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
                if (resultList != null && resultList.size() > 0) {
                    for (SchArrangeResult result : resultList) {
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            allMonth += realMonthF;
                        }
                    }
                }

            }
            model.addAttribute("allMonth", allMonth);
        }*/

        showMaterials(model, recruit, applyYear, jsresGraduationApply);
        Map<String, Object> avgBiMap = new HashMap<>();
        avgBiMap = avgBiMapAll;
        model.addAttribute("avgBiMap", avgBiMap);
        String apply = "N";
        //判断为当前年，当前提交时间段内，学员非二阶段，且未提交的可以提交
        String currYear = DateUtil.getYear();
        String currTime = DateUtil.getCurrDateTime();
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = currUser.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = resDoctorBiz.readDoctor(currUser.getUserFlow()).getOrgFlow();
        }
        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        //如果当前学员是协同基地取他主基地所在的城市
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        Boolean isWaitAudit = false;//是否待审核
        if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
            isWaitAudit = true;
        }
        if (currYear.equals(recruit.getGraduationYear()) && inTime
                && !TrainCategoryEnum.WMSecond.getId().equals(recruit.getCatSpeId())
                && !isWaitAudit) {
            apply = "Y";
        }
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        String isAssiGeneral = "";
        if ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId())) {
            isAssiGeneral = "Y";
        } else {
            isAssiGeneral = "N";
        }
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
        PubFile file = null;
        if (files != null && files.size() > 0) {
            file = files.get(0);
            model.addAttribute("file", file);
        }
        model.addAttribute("apply", apply);
        model.addAttribute("resDoctor", resDoctor);
        model.addAttribute("user", sysUser);
        model.addAttribute("isAssiGeneral", isAssiGeneral);
        if ((jsresGraduationApply != null) && (jsresGraduationApply.getRemark() != null) && (jsresGraduationApply.getRemark().equals("undefined"))) {
            jsresGraduationApply.setRemark("无");
        }
        jsresGraduationApply = jsresGraduationApplyAll;
        model.addAttribute("jsresGraduationApply", jsresGraduationApply);
        model.addAttribute("recruitFlow", recruitFlow);

        Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
        List<Map<String, String>> spes = speMap.get(recruit.getSpeId());
        if (spes != null && spes.size() > 0) {
            model.addAttribute("needChange", "Y");
            model.addAttribute("spes", spes);
        } else {
            model.addAttribute("needChange", "N");
        }
        return "jsres/asse/hospital/printApplication";
    }

    @RequestMapping(value = "/applyDownload")
    public String applyDownload(HttpServletRequest request, HttpServletResponse response, String recruitFlow, String doctorFlow, String applyYear) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();

        //获取当前用户
        SysUser currentUser = userBiz.readSysUser(doctorFlow);

        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();

        //context获取路径
        ServletContext context = request.getServletContext();

        //水印
        String watermark = "";

        //文件名称
        String name = "";

        //培训记录
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        //个人信息
        ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(currentUser.getUserFlow());
        SchRotation rotation = rotationBiz.readSchRotation(resDoctor.getRotationFlow());
//		if (resDoctor.getSessionNumber().compareTo("2019") < 0) {
//			rotation = rotationBiz.getRotationByRecruitNew(recruit);
//		} else {
//			//默认赋值学员方案
//			rotation = rotationBiz.getRotationByRecruit(recruit);
//		}
        String operUserFlow = currentUser.getUserFlow();
        List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, ResScoreTypeEnum.PublicScore.getId());
        //公共成绩
        ResScore publicScore = null;
        if (null != scorelist && scorelist.size() > 0) {
            publicScore = scorelist.get(0);
            //公共科目成绩详情
            String content = null == publicScore ? "" : publicScore.getExtScore();
            Map<String, String> extScoreMap = new HashMap<String, String>();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScoreMap.put(attrName, attrValue);
                        }
                    }
                }
            }
        }
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        UserResumeExtInfoForm userResumeExt = null;
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply != null) {
            if (JsResAuditStatusEnum.Auditing.getId().equals(jsresGraduationApply.getAuditStatusId())) {

            }
        }
        String startTime = "";
        String endTime = "";
        //保存医师培训时间
        if (recruit != null) {
            //开始时间
            String recruitDate = recruit.getRecruitDate();
            //培养年限
            String trianYear = recruit.getTrainYear();
            String graudationYear = recruit.getGraduationYear();
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {

                try {
                    int year = 0;
                    year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                    if (year != 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime = "";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                int year = 0;
                if (trianYear.equals(JsResTrainYearEnum.OneYear.getId())) {
                    year = 1;
                }
                if (trianYear.equals(JsResTrainYearEnum.TwoYear.getId())) {
                    year = 2;
                }
                if (trianYear.equals(JsResTrainYearEnum.ThreeYear.getId())) {
                    year = 3;
                }
                if (year != 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    } catch (Exception e) {

                    }
                }
            }
        }

        //学员的减免方案
        List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus
                (doctorFlow, rotation.getRotationFlow());
        Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
        if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
            for (SchDoctorDept sdd : doctorDeptList) {
                String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                doctorDeptMap.put(key, sdd);
            }
        }
        float allMonth = 0;
        //方案中的科室
        List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());
        for (SchRotationDept dept : deptList) {
            String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
            SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
            if (reductionDept != null) {
                if (!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                    continue;
                }
                String reductionSchMonth = reductionDept.getSchMonth();
                dept.setSchMonth(reductionSchMonth);
            }
            //轮转科室
            List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
            if (resultList != null && resultList.size() > 0) {
                for (SchArrangeResult result : resultList) {
                    String realMonth = result.getSchMonth();
                    if (StringUtil.isNotBlank(realMonth)) {
                        Float realMonthF = Float.parseFloat(realMonth);
                        allMonth += realMonthF;
                    }
                }
            }
            dataMap.put("allMonth", allMonth + "");//实际轮转时间
        }
        dataMap.put("userName", currentUser.getUserName());//姓名
        String sexName = UserSexEnum.getNameById(currentUser.getSexId());
        dataMap.put("sexName", sexName);//性别
        dataMap.put("doctorTypeName", resDoctor.getDoctorTypeName());//人员类型
        dataMap.put("trainingTypeName", resDoctor.getTrainingTypeName());//培训类别
        String cretTypeName = CertificateTypeEnum.getNameById(currentUser.getCretTypeId());
        dataMap.put("cretTypeName", cretTypeName);//证件类型
        dataMap.put("idNum", currentUser.getIdNo());//证件号
        dataMap.put("orgName", recruit.getOrgName());//培训基地
        String speName = recruit.getSpeName();
        if (StringUtil.isNotBlank(recruit.getChangeSpeName())) {
            speName += "(" + recruit.getChangeSpeName() + ")";
        }
        dataMap.put("speName", speName);//培训专业
        dataMap.put("sessionNumber", recruit.getSessionNumber());//入培年级
        dataMap.put("graduationYear", recruit.getGraduationYear());//结业考核年份
        String degreeName = "";
        if (StringUtil.isNotBlank(userResumeExt.getDoctorDegreeName())) {
            degreeName = userResumeExt.getDoctorDegreeName();
        } else if (StringUtil.isNotBlank(userResumeExt.getMasterDegreeName())) {
            degreeName = userResumeExt.getMasterDegreeName();
        } else {
            degreeName = userResumeExt.getDegreeName();
        }
        dataMap.put("degreeName", degreeName);//学位
        dataMap.put("userPhone", currentUser.getUserPhone());//联系方式
        dataMap.put("educationName", currentUser.getEducationName());//学历
        dataMap.put("collegeCertificateNo", resDoctor.getCertificateNo());//毕业证书编号
        String trainYear = "";
        if ("OneYear".equals(recruit.getTrainYear())) {
            trainYear = "一年";
        }
        if ("TwoYear".equals(recruit.getTrainYear())) {
            trainYear = "两年";
        }
        if ("ThreeYear".equals(recruit.getTrainYear())) {
            trainYear = "三年";
        }
        dataMap.put("trainYear", trainYear);//培训年限
        dataMap.put("startDate", startTime);//培训起止日期
        dataMap.put("endDate", endTime);
        dataMap.put("qualifiName", jsresGraduationApply.getGraduationMaterialName());//执业资格材料
        String aqualifiNo = "";
        aqualifiNo = jsresGraduationApply.getGraduationMaterialCode();
        dataMap.put("qualifiNo", aqualifiNo);//执业资格材料编号
        dataMap.put("graduationMaterialTime", jsresGraduationApply.getGraduationMaterialTime());//执业时间
        dataMap.put("remark", jsresGraduationApply.getRemark());//受疫情影响未完成的培训
        String practName = "";
        practName = jsresGraduationApply.getGraduationCategoryName();
        if (practName.contains("\t")) {
            practName = practName.substring(practName.lastIndexOf("\t") + 1);
        }
        dataMap.put("practName", practName);//执业类型
        String practScope = "";
        practScope = jsresGraduationApply.getGraduationScopeName();
        if (practScope.contains("\t")) {
            practScope = practScope.substring(practScope.lastIndexOf("\t") + 1);
        }
        dataMap.put("practScope", practScope);//执业范围
        String skillScore = "合格";
        /*if (publicScore != null && publicScore.getSkillScore() != null) {
            if ((GlobalConstant.PASS + "").equals(publicScore.getSkillScore().toString())) {
                skillScore = "合格";
            }
            if ((GlobalConstant.UNPASS + "").equals(publicScore.getSkillScore().toString())) {
                skillScore = "不合格";
            }
        }*/
        dataMap.put("skillScore", skillScore);//考试成绩打印时统一合格
        String registeManua = "";
        if ((GlobalConstant.PASS + "").equals(userResumeExt.getRegisteManua())) {
            registeManua = "已完成";
        }
        if ((GlobalConstant.UNPASS + "").equals(userResumeExt.getRegisteManua())) {
            registeManua = "未完成";
        }
        if ((GlobalConstant.NORMAL + "").equals(userResumeExt.getRegisteManua())) {
            registeManua = "正常";
        }
        dataMap.put("registeManua", registeManua);//培训登记手册完成情况

        Map<String, Object> avgBiMap = new HashMap<>();
        if (jsresGraduationApply == null) {
            //完成比例与审核比例
            List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetailByNow(recruit.getRecruitFlow(), doctorFlow, applyYear);
            if (details != null && details.size() > 0) {
                int isShortY = 0;
                int isShortN = 0;
                int shortYCount = 0;
                int shortNCount = 0;
                double shortYCBSum = 0;//完成比例
                double shortNCBSum = 0;
                double shortYOCBSum = 0;//补填比例
                double shortNOCBSum = 0;
                double shortYABSum = 0;//审核 比例
                double shortNABSum = 0;
                double avgComBi = 0;//平均完成比例
                double avgOutComBi = 0;//平均补填比例
                double avgAuditComBi = 0;//平均审核比例
                for (JsresDoctorDeptDetail d : details) {
                    if ("Y".equals(d.getIsShort())) {
                        shortYCount++;
                        shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                        shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                        shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                        if (isShortY == 0) {
                            isShortY = 1;
                        }
                    }
                    if ("N".equals(d.getIsShort())) {
                        shortNCount++;
                        shortNCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                        shortNOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                        shortNABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                        if (isShortN == 0) {
                            isShortN = 1;
                        }
                    }
                }
                //平均完成比例与平均审核比例
                if ((isShortY + isShortN) > 1) {
                    if ((shortYCount) != 0) {
                        avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                } else {
                    if ((shortYCount + shortNCount) != 0) {
                        avgComBi = new BigDecimal((shortYCBSum + shortNCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgOutComBi = new BigDecimal((shortYOCBSum + shortNOCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        avgAuditComBi = new BigDecimal((shortYABSum + shortNABSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
                avgBiMap.put("avgComplete", avgComBi);
                avgBiMap.put("avgOutComplete", avgOutComBi);
                avgBiMap.put("avgAudit", avgAuditComBi);
            }
        }
        if (jsresGraduationApply != null) {
            //平均完成比例与平均审核比例
            avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), applyYear);
        }
        dataMap.put("avgComplete", StringUtil.isBlank((String) avgBiMap.get("avgComplete")) ? 0 + "%" : avgBiMap.get("avgComplete") + "%");//数据完后比例
        dataMap.put("avgAudit", StringUtil.isBlank((String) avgBiMap.get("avgAudit")) ? 0 + "%" : avgBiMap.get("avgAudit") + "%");//数据审核比例
        if (StringUtil.isNotBlank(currentUser.getUserHeadImg())) {
            String headImg = InitConfig.getSysCfg("upload_base_url") + "/" + currentUser.getUserHeadImg();
            headImg = "<img  src='" + headImg + "' width='60' height='100'  alt='证件照'/>";
            dataMap.put("headImg", headImg);//学员照片
        } else {
            String strBackUrl = "http://" + request.getServerName() //服务器地址
                    + ":" + request.getServerPort();
            String getPath = strBackUrl + "/pdsci/css/skin/up-pic.jpg";
            dataMap.put("headImg", "<img src='" + getPath + "' width='60' height='100'  alt='证件照'/>");//学员照片
        }
        String path = "";//模板
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        if ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId())) {
            path = "/jsp/jsres/asse/isGeneral.docx";
            name = "江苏省住院医师规范化培训（西医）理论省统考资格审核情况表（全科专业）.docx";
        } else {
            path = "/jsp/jsres/asse/notGeneral.docx";
            name = "江苏省住院医师规范化培训（西医）理论省统考资格审核情况表（非全科专业）.docx";
        }
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }

    //*************************************    医师账户管理    ************************************************

    @RequestMapping(value = "/accountManage")
    public String accountManage() {
        return "jsres/hospital/accountManage";
    }


    @RequestMapping(value = "/accountManageAcc")
    public String accountManageAcc() {
        return "jsres/hospital/accountManageAcc";
    }

    /**
     * 加载医师账户列表
     *
     * @param currentPage
     * @param sysUser
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/accountList")
    public String accountList(Integer currentPage, SysUser sysUser, Model model, HttpServletRequest request, String baseFlag, String lockStatus, String trainingSpeId, String trainingTypeId) {
        SysUser currUser = GlobalContext.getCurrentUser();
        String orgFlow = currUser.getOrgFlow();
        SysOrg sysOrg = new SysOrg();
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            sysOrg.setOrgFlow(currUser.getOrgFlow());
        }
        SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            sysOrg.setOrgProvId(org.getOrgProvId());
            sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            sysOrg.setOrgProvId(org.getOrgProvId());
            sysOrg.setOrgCityId(org.getOrgCityId());
            sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> doctorExtList = resDoctorBiz.searchDoctorAccountList(sysUser, sysOrg, baseFlag, orgFlow, lockStatus, trainingSpeId, trainingTypeId);
        model.addAttribute("doctorExtList", doctorExtList);

        Map<String, ResUserBindMacid> macidMap = new HashMap<>();
        if (doctorExtList != null && doctorExtList.size() > 0) {
            for (ResDoctorExt doctorExt : doctorExtList) {
                String doctorFlow = doctorExt.getDoctorFlow();
                macidMap.put(doctorFlow, resDoctorBiz.readBindMacidByFlow(doctorFlow));
            }
        }
        model.addAttribute("macidMap", macidMap);
        return "/jsres/hospital/accountList";
    }

    /**
     * 锁定用户
     *
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/lock")
    @ResponseBody
    public String lock(String userFlow) {
        SysUser sysUser = new SysUser();
        sysUser.setUserFlow(userFlow);
        sysUser.setStatusId(UserStatusEnum.Locked.getId());
        sysUser.setStatusDesc(UserStatusEnum.Locked.getName());
        sysUser.setLockTime(DateUtil.getCurrDateTime());
        userBiz.saveUser(sysUser);
        return GlobalConstant.LOCK_SUCCESSED;
    }


    /**
     * 解锁用户
     *
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/unLock")
    @ResponseBody
    public String unLock(String userFlow) {
        SysUser sysUser = new SysUser();
        sysUser.setUserFlow(userFlow);
        sysUser.setStatusId(UserStatusEnum.Activated.getId());
        sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
        userBiz.saveUser(sysUser);
        return GlobalConstant.UnLOCK_SUCCESSED;
    }

    /**
     * 还原密码
     *
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/resetPasswd", method = RequestMethod.GET)
    @ResponseBody
    public String resetPasswd(String userFlow) {
        SysUser sysUser = new SysUser();
        sysUser.setUserFlow(userFlow);
        sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), InitPasswordUtil.getInitPass()));
        userBiz.saveUser(sysUser);
        return GlobalConstant.RESET_SUCCESSED;
    }

    //*******************************************   培训登记  *************************************************

    /**
     * 培训登记
     *
     * @return
     */
    @RequestMapping(value = "/trainRegister")
    public String trainRegister(String hideApprove, String doctorFlow, String roleFlag, String search, String currentPage,
                                String yearStr, String baseFlag, String rotationFlow) throws UnsupportedEncodingException {
        SysUser currUser = GlobalContext.getCurrentUser();
        String operUserFlow = currUser.getUserFlow();
        String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
        //TODO
//        List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(operUserFlow, recTypeId);
        List<DoctorAuth> resRecList = doctorAuthBiz.searchAuthsByOperUserFlow(operUserFlow);
        if (StringUtil.isNotBlank(search)) {
            search = URLEncoder.encode(search, "iso-8859-1");
        }
        if (StringUtil.isNotBlank(doctorFlow)) {
            return "redirect:/jsres/doctor/process?hideApprove=" + hideApprove + "&doctorFlow=" + doctorFlow + "&roleFlag=" + roleFlag + "&search=" + search + "&currentPage=" + currentPage + "&yearStr=" + yearStr + "&baseFlag=" + baseFlag + "&rotationFlow=" + rotationFlow;
        }
        if (resRecList != null && !resRecList.isEmpty()) {
            return "redirect:/jsres/doctor/process?hideApprove=" + hideApprove + "&roleFlag=" + roleFlag + "&search=" + search + "&yearStr=" + yearStr + "&baseFlag=" + baseFlag + "&rotationFlow=" + rotationFlow;
        } else {
            return "jsres/doctor/recordHandbook";
        }
    }

    @RequestMapping(value = {"/checkDoctorAuth"})
    @ResponseBody
    public String checkDoctorAuth(String doctorFlow, String roleFlag) {
        //TODO
        String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
//        List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(doctorFlow, recTypeId);
        List<DoctorAuth> resRecList = doctorAuthBiz.searchAuthsByOperUserFlow(doctorFlow);
        if (resRecList != null && !resRecList.isEmpty()) {
            return GlobalConstant.FLAG_Y;
        }
        return GlobalConstant.FLAG_N;
    }


    /**
     * 上传培训考核记录手册电子版诚信声明
     *
     * @param file
     * @param courseFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadResRecFile"})
    @ResponseBody
    public String uploadResRecFile(MultipartFile file, String courseFlow) throws Exception {
        if (file != null && !file.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkImg(file);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
                return resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }


    /**
     * 保存ResRec文件路径
     *
     * @return
     */
    @RequestMapping(value = "/saveRecFileUri")
    @ResponseBody
    public String saveRecFileUri(DoctorAuth resRec) {
        //TODO
        SysUser currUser = GlobalContext.getCurrentUser();
        resRec.setRecTypeId(ResRecTypeEnum.DoctorAuth.getId());
        resRec.setRecTypeName(ResRecTypeEnum.DoctorAuth.getName());
        resRec.setOperUserFlow(currUser.getUserFlow());
        resRec.setOperUserName(currUser.getUserName());
        resRec.setOperTime(DateUtil.getCurrDateTime());
        int result = doctorAuthBiz.edit(resRec);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 培训登记
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/process", method = {RequestMethod.GET})
    public String process(Model model, String hideApprove, String doctorFlow, String roleFlag, String search, String rotationFlow) throws DocumentException, UnsupportedEncodingException {
        search = java.net.URLDecoder.decode(search, "UTF-8");
        model.addAttribute("hideApprove", hideApprove);
        model.addAttribute("search", search);
        model.addAttribute("roleFlag", roleFlag);
        ResDoctor doctor = new ResDoctor();
        SysUser sysUser = new SysUser();
        if (StringUtil.isNotBlank(doctorFlow)) {
            sysUser = userBiz.readSysUser(doctorFlow);
            doctor = resDoctorBiz.searchByUserFlow(doctorFlow);
        } else {
            sysUser = GlobalContext.getCurrentUser();
            doctor = resDoctorBiz.searchByUserFlow(sysUser.getUserFlow());
        }
        //TODO
        String resRecType = ResRecTypeEnum.DoctorAuth.getId();
//		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,sysUser.getUserFlow());
        List<DoctorAuth> resRec = doctorAuthBiz.searchAuthsByOperUserFlow(sysUser.getUserFlow());
        //System.out.println("==temp======resRec====="+resRec);
        if (resRec != null && !resRec.isEmpty()) {
            model.addAttribute("resRec", resRec.get(0));
        }
        //培训手册
//		String cfgCode = "stu_manual_" + doctor.getDoctorFlow();
        String cfgCode = "jsres_doctor_manual_" + doctor.getDoctorFlow();
        JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
        //System.out.println("==temp======cfg====="+cfg);
        if (cfg != null) {
            model.addAttribute("showManual", cfg.getCfgValue());
        }
        model.addAttribute("doctor", doctor);
        //查询学员培训记录(审核通过)
//		List<ResDoctorRecruit> recruitList = recruitDoctorInfoBiz.searchRecruitList(doctor.getDoctorFlow());
        List<ResDoctorRecruit> recruitList = recruitDoctorInfoBiz.searchRecruitListNew(doctor.getDoctorFlow());
        model.addAttribute("recruitList", recruitList);

        if (null != recruitList && recruitList.size() > 0) {
            if (StringUtil.isBlank(rotationFlow) || "null".equals(rotationFlow)) {
                rotationFlow = recruitList.get(0).getRotationFlow();
                model.addAttribute("doctorRecruit", recruitList.get(0));
            } else {
                for (ResDoctorRecruit rdr : recruitList) {
                    if (rotationFlow.equals(rdr.getRotationFlow())) {
                        model.addAttribute("doctorRecruit", rdr);
                    }
                }
            }
        } else {
            return "jsres/doctor/process";
        }

        model.addAttribute("rotationFlow", rotationFlow);
//		return "jsres/doctor/processMain";
        if (doctor != null && StringUtil.isNotBlank(rotationFlow)) {
            String trainingType = doctor.getTrainingTypeId();
            String sessionNumber = doctor.getSessionNumber();
            String trainingYears = doctor.getTrainingYears();
            boolean isReduction = TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
            isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
            isReduction = isReduction && (JsResTrainYearEnum.OneYear.getId().equals(trainingYears) || JsResTrainYearEnum.TwoYear.getId().equals(trainingYears));
            //当学员需要维护减免方案的时候，才去查询学员的减免结果。
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (isReduction) {
                List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctor.getDoctorFlow(), rotationFlow);
                if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                    for (SchDoctorDept sdd : doctorDeptList) {
                        String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                        doctorDeptMap.put(key, sdd);
                    }
                }
            }

            SchRotation rotation = rotationBiz.readSchRotation(rotationFlow);
            model.addAttribute("rotation", rotation);

            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);

            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, ResStandardDeptPer> perMap = new HashMap<String, ResStandardDeptPer>();

            //计算轮转时间内登记进度
            List<ResRec> recList = new ArrayList<ResRec>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("doctorFlow", sysUser.getUserFlow());


            Map<String, String> afterMap = new HashMap<>();

            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);

                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                temp.add(dept);
//				ResStandardDeptPer per=resRecBiz.getStandardDeptPer(doctor.getDoctorFlow(),dept.getRecordFlow(),dept.getRotationFlow());
//				perMap.put(dept.getRecordFlow(),per);
                String groupFlow = dept.getGroupFlow();
                String standardDeptId = dept.getStandardDeptId();
                paramMap.put("standardGroupFlow", groupFlow);
                paramMap.put("standardDeptId", standardDeptId);
                paramMap.put("processFlow", dept.getRecordFlow());
                List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
                if (recs != null && !recs.isEmpty()) {
                    recList.addAll(recs);
                }
                //仅当学员上传出科考核才显示，未上传不显示 基地，市局，高校，省厅
                if (StringUtil.isNotBlank(hideApprove)) {
                    afterMap.put(dept.getRecordFlow(), "N");
                    List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
//						ResRec rec =resRecBiz.queryResRec(dept.getRecordFlow(),doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                    ResSchProcessExpress rec = expressBiz.queryResRec(dept.getRecordFlow(), doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                    String content = null == rec ? "" : rec.getRecContent();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        List<Element> imageEles = root.elements();
                        if (imageEles != null && imageEles.size() > 0) {
                            for (Element image : imageEles) {
                                Map<String, Object> recContent = new HashMap<String, Object>();
                                String imageFlow = image.attributeValue("imageFlow");
                                List<Element> elements = image.elements();
                                for (Element attr : elements) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    recContent.put(attrName, attrValue);
                                }
                                imagelist.add(recContent);
                            }
                        }
                    }
                    if (imagelist.size() > 0) {
                        afterMap.put(dept.getRecordFlow(), "Y");
                    }
                }
            }
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("perMap", perMap);

            List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            for (SchArrangeResult result : resultList) {
                String key = result.getStandardGroupFlow() + result.getStandardDeptId();

                List<SchArrangeResult> temp = resultMap.get(key);
                if (temp == null) {
                    temp = new ArrayList<SchArrangeResult>();
                    resultMap.put(key, temp);
                }
                temp.add(result);

                Float month = realMonthMap.get(key);
                if (month == null) {
                    month = 0f;
                }
                String realMonth = result.getSchMonth();
                if (StringUtil.isNotBlank(realMonth)) {
                    Float realMonthF = Float.parseFloat(realMonth);
                    month += realMonthF;
                }

                realMonthMap.put(key, month);
            }
            model.addAttribute("afterMap", afterMap);
            model.addAttribute("resultMap", resultMap);
            model.addAttribute("realMonthMap", realMonthMap);

            boolean f = false;
            f = resDoctorBiz.getCkkPower(doctor.getDoctorFlow());
            if (f && resultList != null && resultList.size() > 0) {
                Map<String, Object> outScoreMap = new HashMap<>();
                for (SchArrangeResult result : resultList) {
                    ResDoctorSchProcess process = processBiz.searchByResultFlow(result.getResultFlow());
                    if (process != null) {
                        ResScore outScore = resScoreBiz.getScoreByProcess(process.getProcessFlow());
                        outScoreMap.put(result.getResultFlow() + "Process", process);
                        outScoreMap.put(result.getResultFlow() + "OutScore", outScore);
                    }
                }
                model.addAttribute("outScoreMap", outScoreMap);
            }

            model.addAttribute("ckk", f);

            if ("doctor".equals(roleFlag)) {
                if (resultList != null && resultList.size() > 0) {
                    Map<String, Object> resultProcessMap = new HashMap<>();
                    List<String> resultFlows = new ArrayList<>();
                    for (SchArrangeResult result : resultList) {
                        resultFlows.add(result.getResultFlow());
                    }
                    List<ResDoctorSchProcess> processes = processBiz.searchByResultFlows(resultFlows);
                    if (processes != null && processes.size() > 0) {
                        for (ResDoctorSchProcess temp : processes) {
                            resultProcessMap.put(temp.getSchResultFlow(), temp);
                        }
                    }
                    model.addAttribute("resultProcessMap", resultProcessMap);
                }
            }

            //计算登记进度
            Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
            model.addAttribute("processPerMap", processPerMap);

            //计算轮转时间内登记进度
            Map<String, String> processingPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), rotationFlow, null, 0, recList);
            model.addAttribute("processingPerMap", processingPerMap);
            model.addAttribute("sysUser", sysUser);

            // 是否开通过程
            cfgCode = "jsres_doctor_app_menu_" + doctor.getDoctorFlow();
            cfg = jsResPowerCfgBiz.read(cfgCode);
            String appMenu = "N";
            if (cfg != null) {
                appMenu = cfg.getCfgValue();
            }
            model.addAttribute("appMenu", appMenu);
            String orgFlow = "";

            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            String daoRu = "N";

            String key1 = "jsres_" + orgFlow + "_daoru";
            JsresPowerCfg dr = jsResPowerCfgBiz.read(key1);
            if (dr != null) {
                if (GlobalConstant.FLAG_Y.equals(dr.getCfgValue())) {
                    cfgCode = "jsres_" + orgFlow + "_org_daoru";
                    cfg = jsResPowerCfgBiz.read(cfgCode);
                    if (cfg != null) {
                        daoRu = cfg.getCfgValue();
                    }
                }
            }
            model.addAttribute("daoRu", daoRu);
        }
        return "jsres/doctor/process";
    }

    /**
     * 培训登记
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getProcess", method = {RequestMethod.GET})
    public String getProcess(Model model, String hideApprove, String doctorFlow, String roleFlag, String search) throws DocumentException, UnsupportedEncodingException {
        search = java.net.URLDecoder.decode(search, "UTF-8");
        model.addAttribute("hideApprove", hideApprove);
        model.addAttribute("search", search);
        model.addAttribute("roleFlag", roleFlag);
        ResDoctor doctor = new ResDoctor();
        SysUser sysUser = new SysUser();
        if (StringUtil.isNotBlank(doctorFlow)) {
            sysUser = userBiz.readSysUser(doctorFlow);
            doctor = resDoctorBiz.searchByUserFlow(doctorFlow);
        } else {
            sysUser = GlobalContext.getCurrentUser();
            doctor = resDoctorBiz.searchByUserFlow(sysUser.getUserFlow());
        }
        //TODO
        String resRecType = ResRecTypeEnum.DoctorAuth.getId();
        List<DoctorAuth> resRec = doctorAuthBiz.searchAuthsByOperUserFlow(sysUser.getUserFlow());
        if (resRec != null && !resRec.isEmpty()) {
            model.addAttribute("resRec", resRec.get(0));
        }
        //培训手册
        String cfgCode = "jsres_doctor_manual_" + doctor.getDoctorFlow();
        JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
        //System.out.println("==temp======cfg====="+cfg);
        if (cfg != null) {
            model.addAttribute("showManual", cfg.getCfgValue());
        }
        model.addAttribute("doctor", doctor);


        if (doctor != null && StringUtil.isNotBlank(doctor.getRotationFlow())) {

            String trainingType = doctor.getTrainingTypeId();
            String sessionNumber = doctor.getSessionNumber();
            String trainingYears = doctor.getTrainingYears();
            boolean isReduction = TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
            isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
            isReduction = isReduction && (JsResTrainYearEnum.OneYear.getId().equals(trainingYears) || JsResTrainYearEnum.TwoYear.getId().equals(trainingYears));
            //当学员需要维护减免方案的时候，才去查询学员的减免结果。
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (isReduction) {
                List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctor.getDoctorFlow(), doctor.getRotationFlow());
                if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                    for (SchDoctorDept sdd : doctorDeptList) {
                        String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                        doctorDeptMap.put(key, sdd);
                    }
                }
            }

            SchRotation rotation = rotationBiz.readSchRotation(doctor.getRotationFlow());
            model.addAttribute("rotation", rotation);

            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(doctor.getRotationFlow());
            model.addAttribute("groupList", groupList);

            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, ResStandardDeptPer> perMap = new HashMap<String, ResStandardDeptPer>();

            //计算轮转时间内登记进度
            List<ResRec> recList = new ArrayList<ResRec>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("doctorFlow", sysUser.getUserFlow());


            Map<String, String> afterMap = new HashMap<>();

            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);

                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                temp.add(dept);
                String groupFlow = dept.getGroupFlow();
                String standardDeptId = dept.getStandardDeptId();
                paramMap.put("standardGroupFlow", groupFlow);
                paramMap.put("standardDeptId", standardDeptId);
                paramMap.put("processFlow", dept.getRecordFlow());
                List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
                if (recs != null && !recs.isEmpty()) {
                    recList.addAll(recs);
                }
                //仅当学员上传出科考核才显示，未上传不显示 基地，市局，高校，省厅
                if (StringUtil.isNotBlank(hideApprove)) {
                    afterMap.put(dept.getRecordFlow(), "N");
                    List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                    ResSchProcessExpress rec = expressBiz.queryResRec(dept.getRecordFlow(), doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                    String content = null == rec ? "" : rec.getRecContent();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        List<Element> imageEles = root.elements();
                        if (imageEles != null && imageEles.size() > 0) {
                            for (Element image : imageEles) {
                                Map<String, Object> recContent = new HashMap<String, Object>();
                                String imageFlow = image.attributeValue("imageFlow");
                                List<Element> elements = image.elements();
                                for (Element attr : elements) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    recContent.put(attrName, attrValue);
                                }
                                imagelist.add(recContent);
                            }
                        }
                    }
                    if (imagelist.size() > 0) {
                        afterMap.put(dept.getRecordFlow(), "Y");
                    }
                }
            }
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("perMap", perMap);

            List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            for (SchArrangeResult result : resultList) {
                String key = result.getStandardGroupFlow() + result.getStandardDeptId();

                List<SchArrangeResult> temp = resultMap.get(key);
                if (temp == null) {
                    temp = new ArrayList<SchArrangeResult>();
                    resultMap.put(key, temp);
                }
                temp.add(result);

                Float month = realMonthMap.get(key);
                if (month == null) {
                    month = 0f;
                }
                String realMonth = result.getSchMonth();
                if (StringUtil.isNotBlank(realMonth)) {
                    Float realMonthF = Float.parseFloat(realMonth);
                    month += realMonthF;
                }

                realMonthMap.put(key, month);
            }
            model.addAttribute("afterMap", afterMap);
            model.addAttribute("resultMap", resultMap);
            model.addAttribute("realMonthMap", realMonthMap);

            boolean f = false;
            f = resDoctorBiz.getCkkPower(doctor.getDoctorFlow());
            if (f && resultList != null && resultList.size() > 0) {
                Map<String, Object> outScoreMap = new HashMap<>();
                for (SchArrangeResult result : resultList) {
                    ResDoctorSchProcess process = processBiz.searchByResultFlow(result.getResultFlow());
                    if (process != null) {
                        ResScore outScore = resScoreBiz.getScoreByProcess(process.getProcessFlow());
                        outScoreMap.put(result.getResultFlow() + "Process", process);
                        outScoreMap.put(result.getResultFlow() + "OutScore", outScore);
                    }
                }
                model.addAttribute("outScoreMap", outScoreMap);
            }

            model.addAttribute("ckk", f);

            if ("doctor".equals(roleFlag)) {
                if (resultList != null && resultList.size() > 0) {
                    Map<String, Object> resultProcessMap = new HashMap<>();
                    List<String> resultFlows = new ArrayList<>();
                    for (SchArrangeResult result : resultList) {
                        resultFlows.add(result.getResultFlow());
                    }
                    List<ResDoctorSchProcess> processes = processBiz.searchByResultFlows(resultFlows);
                    if (processes != null && processes.size() > 0) {
                        for (ResDoctorSchProcess temp : processes) {
                            resultProcessMap.put(temp.getSchResultFlow(), temp);
                        }
                    }
                    model.addAttribute("resultProcessMap", resultProcessMap);
                }
            }

            //计算登记进度
            Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
            model.addAttribute("processPerMap", processPerMap);

            //计算轮转时间内登记进度
            Map<String, String> processingPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(), null, 0, recList);
            model.addAttribute("processingPerMap", processingPerMap);
            model.addAttribute("sysUser", sysUser);

            // 是否开通过程
            cfgCode = "jsres_doctor_app_menu_" + doctor.getDoctorFlow();
            cfg = jsResPowerCfgBiz.read(cfgCode);
            String appMenu = "N";
            if (cfg != null) {
                appMenu = cfg.getCfgValue();
            }
            model.addAttribute("appMenu", appMenu);
            String orgFlow = "";

            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            String daoRu = "N";

            String key1 = "jsres_" + orgFlow + "_daoru";
            JsresPowerCfg dr = jsResPowerCfgBiz.read(key1);
            if (dr != null) {
                if (GlobalConstant.FLAG_Y.equals(dr.getCfgValue())) {
                    cfgCode = "jsres_" + orgFlow + "_org_daoru";
                    cfg = jsResPowerCfgBiz.read(cfgCode);
                    if (cfg != null) {
                        daoRu = cfg.getCfgValue();
                    }
                }
            }
            model.addAttribute("daoRu", daoRu);
        }
        return "jsres/doctor/process";
    }

    @RequestMapping(value = "/openImport")
    public String openImport(Model model, String recordFlow, String processFlow, String doctorFlow) throws DocumentException, UnsupportedEncodingException {

        return "jsres/doctor/openImport";
    }

    @RequestMapping(value = "/downImportFile")
    public void downImportFile(Model model, String recordFlow, String processFlow, String doctorFlow, String recTypeId,
                               HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        String fileName = "未选择数据类型.xls";
        HSSFWorkbook wb = new HSSFWorkbook();
        if (StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(recTypeId)) {
            fileName = ResRecTypeEnum.getNameById(recTypeId) + "模板.xls";
            List<SchRotationDeptReq> reqs = schRotationDeptBiz.searchRotationDeptReq(recordFlow, recTypeId);
            if (reqs == null || reqs.size() == 0) {
                fileName = "请联系系统管理员维护【操作名称】.xls";
            } else {
                if (ResRecTypeEnum.SkillRegistry.getId().equals(recTypeId)) {
                    wb = createSkill(reqs);
                }
                if (ResRecTypeEnum.DiseaseRegistry.getId().equals(recTypeId)) {
                    wb = createDisease(reqs);
                }
            }
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.setCookie(response);
        wb.write(response.getOutputStream());
    }

    private HSSFWorkbook createSkill(List<SchRotationDeptReq> reqs) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("SkillRegistry");
        HSSFRow rowOne = sheet.createRow(0);//第一行

        String[] titles = new String[]{
                "操作名称",
                "名称",
                "操作日期",
                "病人姓名",
                "病历号/病理号/检查号",
                "成功",
                "失败原因"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            cellTitle.setCellType(CellType.STRING);
            sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
        }
        // 为工作簿添加sheet
        HSSFSheet sheet2 = wb.createSheet("填写说明");
        HSSFRow rowOne2 = sheet2.createRow(0);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【名称】这一列，只有当【操作名称】的值为[其他]时，才需要填写。");
        rowOne2 = sheet2.createRow(1);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【失败原因】这一列为非必填项，其他项均为必填项");
        rowOne2 = sheet2.createRow(2);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【操作日期】格式为YYYY-MM-dd");
        rowOne2 = sheet2.createRow(3);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【成功】的值只为【是】或【否】");
        rowOne2 = sheet2.createRow(4);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【操作名称】的值请从【操作名称选择】sheet中选择填写");
        rowOne2 = sheet2.createRow(5);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("请先将模板的单元格格式设置成【文字文本】");

        // 为工作簿添加sheet
        HSSFSheet sheet3 = wb.createSheet("操作名称选择");

        HSSFRow rowOne3 = sheet3.createRow(0);//第一行
        cellTitle = rowOne3.createCell(0);
        cellTitle.setCellValue("操作名称");
        cellTitle.setCellStyle(styleCenter);
        int i = 1;
        for (SchRotationDeptReq req : reqs) {
            rowOne3 = sheet3.createRow(i++);//第一行
            cellTitle = rowOne3.createCell(0);
            cellTitle.setCellValue(req.getItemName());
        }

        return wb;
    }

    private HSSFWorkbook createDisease(List<SchRotationDeptReq> reqs) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("DiseaseRegistry");
        HSSFRow rowOne = sheet.createRow(0);//第一行

        String[] titles = new String[]{
                "病种名称",
                "名称",
                "日期",
                "病人姓名",
                "病历号/病理号",
                "诊断类型",
                "是否主管",
                "是否抢救",
                "转归情况"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
        }
        // 为工作簿添加sheet
        HSSFSheet sheet2 = wb.createSheet("填写说明");
        HSSFRow rowOne2 = sheet2.createRow(0);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中所有字段信息为必填项");
        rowOne2 = sheet2.createRow(1);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【名称】这一列，只有当【病种名称】的值为[其他]时，才需要填写。");
        rowOne2 = sheet2.createRow(2);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【日期】格式为YYYY-MM-dd");
        rowOne2 = sheet2.createRow(3);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【诊断类型】的值只为【主要诊断】或【次要诊断】或【并行诊断】");
        rowOne2 = sheet2.createRow(4);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【是否主管】与【是否抢救】的值只为【是】或【否】");
        rowOne2 = sheet2.createRow(5);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("模板中【病种名称】的值请从【病种名称选择】sheet中选择填写");
        rowOne2 = sheet2.createRow(6);//第一行
        cellTitle = rowOne2.createCell(0);
        cellTitle.setCellValue("请先将模板的单元格格式设置成【文字文本】");

        // 为工作簿添加sheet
        HSSFSheet sheet3 = wb.createSheet("病种名称选择");

        HSSFRow rowOne3 = sheet3.createRow(0);//第一行
        cellTitle = rowOne3.createCell(0);
        cellTitle.setCellValue("病种名称");
        cellTitle.setCellStyle(styleCenter);
        int i = 1;
        for (SchRotationDeptReq req : reqs) {
            rowOne3 = sheet3.createRow(i++);//第一行
            cellTitle = rowOne3.createCell(0);
            cellTitle.setCellValue(req.getItemName());
        }


        return wb;
    }

    @RequestMapping(value = "/importData")
    @ResponseBody
    public String importData(MultipartFile file, String recordFlow, String processFlow, String recTypeId, String doctorFlow) {
        if (file.getSize() > 0) {
            if (StringUtil.isBlank(recordFlow)) {
                return "请选择标准科室";
            }
            if (StringUtil.isBlank(processFlow)) {
                return "请选择轮转科室";
            }
            if (StringUtil.isBlank(recTypeId)) {
                return "请选择需要导入的数据类型";
            }
            if (!GlobalContext.getCurrentUser().getUserFlow().equals(doctorFlow)) {
                return "当前用户已变更，请关闭页面";
            }
            ResDoctorSchProcess p = processBiz.read(processFlow);
            if (p == null) {
                return "轮转科室记录不存在，请重新选择！";
            }
            ResDoctor docote = resDoctorBiz.readDoctor(doctorFlow);
            if (docote == null) {
                return "医师信息不存在，请重新选择！";
            }
            SysUser user = userBiz.readSysUser(doctorFlow);
            if (user == null) {
                return "个人信息不存在，请重新选择！";
            }
            if (!doctorFlow.equals(p.getUserFlow())) {
                return "轮转科室记录所属学员非当前登录人，请关闭页面！";
            }
            try {
                ExcelUtile result = (ExcelUtile) resRecBiz.importJsresData(file, recordFlow, processFlow, doctorFlow, recTypeId);
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
        return "文件内容不能为空";
    }

    /**
     * 学员下载出科考核表前，判断是否进行“双向评价”
     *
     * @param processFlow
     * @param deptRecordFlow
     * @return
     */
    @RequestMapping(value = "/checkedHadEvaluate")
    @ResponseBody
    public String checkedHadEvaluate(String processFlow, String deptRecordFlow) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        String cfgCode = "jsres_doctor_app_menu_" + currentUser.getUserFlow();
        String evaluateCfgCode =  "jsres_" + currentUser.getOrgFlow()+"_360evaluate";
        JsresPowerCfgMapper jsresPowerCfgMapper = SpringUtil.getBean(JsresPowerCfgMapper.class);
        JsresPowerCfg jsresPowerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
        JsresPowerCfg powerCfg = jsresPowerCfgMapper.selectByPrimaryKey(evaluateCfgCode);
        String value = "";
        String evaluateValue="";
        if (jsresPowerCfg != null) {
            value = jsresPowerCfg.getCfgValue();
            if (null!=powerCfg){
                evaluateValue=powerCfg.getCfgValue();
            }
            if (!GlobalConstant.FLAG_Y.equals(value)) {  //双向评价没有开
                if (!GlobalConstant.FLAG_Y.equals(evaluateValue)){ //360评价没有开
                    return GlobalConstant.FLAG_Y;
                }else {
                    if (evaluateValue.equals(GlobalConstant.FLAG_Y)){  //开启360评价必填
                        List<String> processFlows = new ArrayList<>();
                        if (StringUtil.isNotBlank(deptRecordFlow)) {
                            SchRotationDept schRotationDept = rotationDeptBiz.readSchRotationDept(deptRecordFlow);
                            Map<String, String> paramsMap = new HashMap<>();
                            paramsMap.put("standardDeptId", schRotationDept.getStandardDeptId());
                            paramsMap.put("standardGroupFlow", schRotationDept.getGroupFlow());
                            paramsMap.put("doctorFlow", currentUser.getUserFlow());
                            List<ResDoctorSchProcess> processes = processBiz.searchByStandadDeptIdAndGroupFLow(paramsMap);
                            if (processes != null && processes.size() > 0) {
                                for (ResDoctorSchProcess temp : processes) {
                                    processFlows.add(temp.getProcessFlow());
                                }
                            }
                        }
                        if (StringUtil.isNotBlank(processFlow)) {
                            processFlows.add(processFlow);
                        }
                        if (processFlows.size() > 0) {
                            Map<String, Object> paramsMap = new HashMap<>();
                            paramsMap.put("processFlows", processFlows);
                            paramsMap.put("doctorFlow", currentUser.getUserFlow());
                            List<Map<String, String>> hadEvaluateByProcesses = resGradeBiz.getHadEvaluateByProcesses(paramsMap);
                            if (hadEvaluateByProcesses != null && hadEvaluateByProcesses.size() > 0) {
                                for (String temp : processFlows) {
                                    boolean evaluateFlag = false;
                                    for (Map<String, String> tempEva : hadEvaluateByProcesses) {
                                        if (tempEva.get("processFlow") != null && tempEva.get("recTypeId") != null
                                                && temp.equals(tempEva.get("processFlow"))) {
                                            if (ResRecTypeEnum.TeacherAssess.getId().equals(tempEva.get("recTypeId"))
                                                    || ResAssessTypeEnum.DoctorTeacherAssessTwo360.getId().equals(tempEva.get("recTypeId"))) {
                                                evaluateFlag=true;
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                    if (evaluateFlag){
                                        return GlobalConstant.FLAG_Y;
                                    }
                                }
                            }
                        }
                        return  "E";
                    }
                }
            }
        }




        List<String> processFlows = new ArrayList<>();
        if (StringUtil.isNotBlank(deptRecordFlow)) {
            SchRotationDept schRotationDept = rotationDeptBiz.readSchRotationDept(deptRecordFlow);
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("standardDeptId", schRotationDept.getStandardDeptId());
            paramsMap.put("standardGroupFlow", schRotationDept.getGroupFlow());
            paramsMap.put("doctorFlow", currentUser.getUserFlow());
            List<ResDoctorSchProcess> processes = processBiz.searchByStandadDeptIdAndGroupFLow(paramsMap);
            if (processes != null && processes.size() > 0) {
                for (ResDoctorSchProcess temp : processes) {
                    processFlows.add(temp.getProcessFlow());
                }
            }
        }
        if (StringUtil.isNotBlank(processFlow)) {
            processFlows.add(processFlow);
        }
        if (processFlows.size() > 0) {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("processFlows", processFlows);
            paramsMap.put("doctorFlow", currentUser.getUserFlow());
            List<Map<String, String>> hadEvaluateByProcesses = resGradeBiz.getHadEvaluateByProcesses(paramsMap);
            if (hadEvaluateByProcesses != null && hadEvaluateByProcesses.size() > 0) {
                for (String temp : processFlows) {
                    boolean hadEvaTeacher = false;
                    boolean hadEvaDept = false;
                    boolean evaluateFlag = false;
                    for (Map<String, String> tempEva : hadEvaluateByProcesses) {
                        if (tempEva.get("processFlow") != null && tempEva.get("recTypeId") != null
                                && temp.equals(tempEva.get("processFlow"))) {
                            if (ResRecTypeEnum.TeacherGrade.getId().equals(tempEva.get("recTypeId"))) {
                                hadEvaTeacher = true;
                            }
                            if (ResRecTypeEnum.DeptGrade.getId().equals(tempEva.get("recTypeId"))) {
                                hadEvaDept = true;
                            }
                            if (ResRecTypeEnum.TeacherAssess.getId().equals(tempEva.get("recTypeId"))
                                    || ResAssessTypeEnum.DoctorTeacherAssessTwo360.getId().equals(tempEva.get("recTypeId"))) {
                                evaluateFlag=true;
                            }
                        } else {
                            continue;
                        }
                    }
                    if (!hadEvaTeacher || !hadEvaDept) {
                        return GlobalConstant.FLAG_N;
                    }
                    if (!evaluateFlag && evaluateValue.equals(GlobalConstant.FLAG_Y)){
                        return "E";
                    }
                }
                return GlobalConstant.FLAG_Y;
            } else {
                return GlobalConstant.FLAG_N;
            }
        } else {
            return GlobalConstant.FLAG_N;
        }
    }

    public SchRotationDept readStandardRotationDept(String resultFlow) {
        SchRotationDept rotationDept = null;
        if (StringUtil.isNotBlank(resultFlow)) {
            SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
            if (result != null) {
                String rotationFlow = result.getRotationFlow();
                String standardDeptId = result.getStandardDeptId();
                SchRotationGroup group = groupBiz.readSchRotationGroup(result.getStandardGroupFlow());
                if (group != null) {
                    if (StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)) {
                        SchRotationDeptExample example = new SchRotationDeptExample();
                        SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
                                .andRotationFlowEqualTo(rotationFlow)
                                .andStandardDeptIdEqualTo(standardDeptId)
                                .andOrgFlowIsNull()
                                .andGroupFlowEqualTo(result.getStandardGroupFlow());
                        List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
                        if (rotationDeptList != null && rotationDeptList.size() > 0) {
                            rotationDept = rotationDeptList.get(0);
                        }
                    }
                }
            }
        }
        return rotationDept;
    }

    /**
     * 导出手册
     *
     * @param doctorFlow
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/daochu")
    public void daochu(String doctorFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
        if (doctor != null) {
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }

                        dataMap.put("graduationTime", userResumeExt.getGraduationTime());
                        // 最高学位
                        String degreeCategoryName = "";
                        if (GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsDoctor())) {
                            degreeCategoryName = userResumeExt.getDoctorDegreeName();
                        }
                        if (StringUtil.isBlank(degreeCategoryName) && GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsMaster())) {
                            degreeCategoryName = userResumeExt.getMasterDegreeName();
                        }
                        if (StringUtil.isBlank(degreeCategoryName) && GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsUndergraduate())) {
                            degreeCategoryName = userResumeExt.getDegreeName();
                        }
                        dataMap.put("degreeCategoryName", degreeCategoryName);
                    }
                }
            }
            dataMap.put("trainingSpeName", doctor.getTrainingSpeName());
            dataMap.put("orgName", doctor.getOrgName());
            dataMap.put("doctorName", doctor.getDoctorName());
            dataMap.put("workOrgName", doctor.getWorkOrgName());
        }
        String rotationFlow = doctor.getRotationFlow();
        List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
        List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
        Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
        for (SchRotationDept dept : deptList) {
            List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
            if (temp == null) {
                temp = new ArrayList<SchRotationDept>();
            }
            temp.add(dept);
            rotationDeptMap.put(dept.getGroupFlow(), temp);

        }

        List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
        int seq = 1;
        List<String> schRotationDeptFlows = new ArrayList<>();
        for (SchRotationGroup srg : groupList) {
            List<SchRotationDept> schRotationDeptList = rotationDeptMap.get(srg.getGroupFlow());
            if (schRotationDeptList != null && !schRotationDeptList.isEmpty()) {
                for (SchRotationDept srd : schRotationDeptList) {
                    schRotationDeptFlows.add(srd.getRecordFlow());
                    Map<String, Object> objMap = new HashMap<String, Object>();
                    objMap.put("seq", String.valueOf(seq++));
                    objMap.put("standardDeptName", srd.getStandardDeptName());
                    String groupFlow = srd.getGroupFlow();
                    String standardDeptId = srd.getStandardDeptId();
                    List<SchArrangeResult> schArrangeResultList = resultBiz.searchResultByGroupFlow(groupFlow, standardDeptId, doctor.getDoctorFlow());
                    if (schArrangeResultList != null && !schArrangeResultList.isEmpty()) {
                        String startDate = null;
                        String endDate = null;
                        Float schMonth = 0f;
                        for (SchArrangeResult sar : schArrangeResultList) {
                            String month = sar.getSchMonth();
                            if (startDate == null) {
                                startDate = sar.getSchStartDate();
                            }
                            endDate = sar.getSchEndDate();
                            if (StringUtil.isNotEmpty(month)) {
                                schMonth += (Float.parseFloat(month));
                            }
                        }
                        objMap.put("startDate", startDate);
                        objMap.put("endDate", endDate);
                        objMap.put("schMonth", schMonth.toString());
                    }
                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    yearPlanTargetList.add(igd);
                }
            }
        }
        dataMap.put("yearPlanTargetList", yearPlanTargetList);

        //学员所有填写的数据
        List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBsByRotationDeptFlows(doctorFlow, schRotationDeptFlows);

//		List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBsBySchRotationFlows(ResRecTypeEnum.CaseRegistry.getId(), doctorFlow,schRotationDeptFlows);
        if (resRecList != null && !resRecList.isEmpty()) {
            List<ItemGroupData> casePlanTargetList = new ArrayList<ItemGroupData>();
            for (ResRec rec : resRecList) {
                if (ResRecTypeEnum.CaseRegistry.getId().equals(rec.getRecTypeId())) {
                    Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                    Map<String, Object> objMap = new HashMap<String, Object>();
                    objMap.put("CaseRegistry_schDeptName", rec.getSchDeptName());
                    objMap.put("CaseRegistry_mr_pName", formDataMap.get("mr_pName"));
                    objMap.put("CaseRegistry_mr_no", formDataMap.get("mr_no"));
                    objMap.put("CaseRegistry_disease_pName", formDataMap.get("disease_pName"));
                    String zyzd = "1";
                    String cyzd = "2";
                    String bxzd = "3";
                    String mr_diagType = (String) formDataMap.get("mr_diagType_id");
                    if (StringUtil.isNotBlank(mr_diagType)) {
                        if (mr_diagType.contains(zyzd)) {
                            objMap.put("CaseRegistry_mr_diagType_zyzd", "√");
                        }
                        if (mr_diagType.contains(cyzd)) {
                            objMap.put("CaseRegistry_mr_diagType_cyzd", "√");
                        }
                        if (mr_diagType.contains(bxzd)) {
                            objMap.put("CaseRegistry_mr_diagType_bxzd", "√");
                        }
                    }
                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    casePlanTargetList.add(igd);
                }
            }
            dataMap.put("casePlanTargetList", casePlanTargetList);
        }

        WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
        String path1 = "/jsp/jsres/daochu/daochuTemeplete1.docx";//模板
        ServletContext context = request.getServletContext();
        String watermark = "";
        temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);

        addTemplates.add(temeplete1);

        Map<String, String> processMap = resRecBiz.getProcessPer(doctorFlow, rotationFlow, null, 0, resRecList);
        if (processMap == null) {
            processMap = new HashMap<String, String>();
        }
        for (SchRotationGroup srg : groupList) {
            List<SchRotationDept> schRotationDeptList = rotationDeptMap.get(srg.getGroupFlow());

            for (SchRotationDept dept : schRotationDeptList) {
                Map<String, Object> dataMap1 = new HashMap<String, Object>();

                String relRecordFlow = dept.getRecordFlow();


                dataMap1.put("standardDeptName", dept.getStandardDeptName());
//			}	Map<String,List<SchRotationDeptReq>> schRotationDeptReqMap = new HashMap<String,List<SchRotationDeptReq>>();
//				for(SchRotationDeptReq schRotationDeptReq : schRotationDeptReqList){
//					String recTypeId = schRotationDeptReq.getRecTypeId();
//					if(schRotationDeptReqMap.get(recTypeId)==null){
//						List<SchRotationDeptReq>  schRotationDeptReqs = new ArrayList<SchRotationDeptReq>();
//						schRotationDeptReqs.add(schRotationDeptReq);
//						schRotationDeptReqMap.put(recTypeId, schRotationDeptReqs);
//					}else{
//						schRotationDeptReqMap.get(recTypeId).add(schRotationDeptReq);
//
//					}
//
//

                //病种
                String desRecTypeId = ResRecTypeEnum.DiseaseRegistry.getId();
                List<SchRotationDeptReq> diseRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
                List<ItemGroupData> dasePlanTargetList = new ArrayList<ItemGroupData>();
                for (SchRotationDeptReq req : diseRotationDeptReqList) {

                    Map<String, Object> objMap = new HashMap<String, Object>();
                    String itemId = req.getItemId();
                    objMap.put("diseaseRegistry_itemName", req.getItemName());
                    String reqKey = relRecordFlow + desRecTypeId + itemId + "ReqNum";
                    String reqFin = relRecordFlow + desRecTypeId + itemId + "Finished";
                    String reqResult = processMap.get(reqKey);
                    if (!StringUtil.isNotBlank(reqResult)) {
                        reqResult = "0";
                    }
                    objMap.put("diseaseRegistry_reqKey", reqResult);
                    String reqFinish = processMap.get(reqFin);
                    if (!StringUtil.isNotBlank(reqFinish)) {
                        reqFinish = "0";
                    }
                    objMap.put("diseaseRegistry_reqFin", reqFinish);
                    objMap.put("diseaseRegistry_seq", String.valueOf(seq++));

                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    dasePlanTargetList.add(igd);
                }
                dataMap1.put("dasePlanTargetList", dasePlanTargetList);

                //操作技能
                String skillRecTypeId = ResRecTypeEnum.SkillRegistry.getId();
                List<SchRotationDeptReq> skillRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, skillRecTypeId);
                List<ItemGroupData> skillPlanTargetList = new ArrayList<ItemGroupData>();
                for (SchRotationDeptReq req : skillRotationDeptReqList) {
                    Map<String, Object> objMap = new HashMap<String, Object>();
                    String itemId = req.getItemId();
                    String reqKey = relRecordFlow + skillRecTypeId + itemId + "ReqNum";
                    String reqFin = relRecordFlow + skillRecTypeId + itemId + "Finished";
                    objMap.put("skillRegistry_itemName", req.getItemName());
                    String reqResult = processMap.get(reqKey);
                    if (!StringUtil.isNotBlank(reqResult)) {
                        reqResult = "0";
                    }
                    objMap.put("skillRegistry_reqKey", reqResult);
                    String reqFinish = processMap.get(reqFin);
                    if (!StringUtil.isNotBlank(reqFinish)) {
                        reqFinish = "0";
                    }
                    objMap.put("skillRegistry_reqFin", reqFinish);
                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    skillPlanTargetList.add(igd);
                }
                dataMap1.put("skillPlanTargetList", skillPlanTargetList);

                //手术
                String operRecTypeId = ResRecTypeEnum.OperationRegistry.getId();
                List<SchRotationDeptReq> operRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, operRecTypeId);
                List<ItemGroupData> operPlanTargetList = new ArrayList<ItemGroupData>();
                for (SchRotationDeptReq req : operRotationDeptReqList) {
                    Map<String, Object> objMap = new HashMap<String, Object>();
                    String itemId = req.getItemId();
                    String reqKey = relRecordFlow + operRecTypeId + itemId + "ReqNum";
                    String reqFin = relRecordFlow + operRecTypeId + itemId + "Finished";

                    objMap.put("operationRegistry_itemName", req.getItemName());

                    String reqResult = processMap.get(reqKey);
                    if (!StringUtil.isNotBlank(reqResult)) {
                        reqResult = "0";
                    }
                    objMap.put("operationRegistry_reqKey", reqResult);
                    String reqFinish = processMap.get(reqFin);
                    if (!StringUtil.isNotBlank(reqFinish)) {
                        reqFinish = "0";
                    }
                    objMap.put("operationRegistry_reqFin", reqFinish);
                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    operPlanTargetList.add(igd);
                }
                dataMap1.put("operPlanTargetList", operPlanTargetList);


                WordprocessingMLPackage temeplete2 = new WordprocessingMLPackage();
                String path2 = "/jsp/jsres/daochu/daochuTemeplete2.docx";//模板
                temeplete2 = Docx4jUtil.convert(new File(context.getRealPath(path2)), dataMap1, watermark, true);
                addTemplates.add(temeplete2);

                //病种
                Map<String, Object> dataMap2 = new HashMap<String, Object>();
                dataMap2.put("standardDeptName", dept.getStandardDeptName());
                List<SchRotationDeptReq> dise2RotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
                int count = 1;
                for (SchRotationDeptReq deptReq : dise2RotationDeptReqList) {
                    List<ItemGroupData> dase2PlanTargetList = new ArrayList<ItemGroupData>();
                    String itemId = deptReq.getItemId();
                    if (StringUtil.isBlank(itemId)) {
                        itemId = "";
                    }
//                    List<ResRec> diseRecList = resRecBiz.searchByResRecWithBLOBs(desRecTypeId, relRecordFlow, doctorFlow, itemId);
                    dataMap2.put("diseaseRegistry_itemSeq", String.valueOf(count));
                    count++;
                    dataMap2.put("diseaseRegistry_itemName", deptReq.getItemName());
                    String reqKey = relRecordFlow + desRecTypeId + itemId + "ReqNum";
                    String reqFin = relRecordFlow + desRecTypeId + itemId + "Finished";
                    String reqResult = processMap.get(reqKey);
                    if (!StringUtil.isNotBlank(reqResult)) {
                        reqResult = "0";
                    }
                    dataMap2.put("disease_reqKey", reqResult);
                    String reqFinish = processMap.get(reqFin);
                    if (!StringUtil.isNotBlank(reqFinish)) {
                        reqFinish = "0";
                    }
                    dataMap2.put("disease_reqFin", reqFinish);
                    seq = 1;
                    for (ResRec rec : resRecList) {
                        if (desRecTypeId.equals(rec.getRecTypeId())
                                && relRecordFlow.equals(rec.getSchRotationDeptFlow())
                                && itemId.equals(rec.getItemId())) {
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            objMap.put("diseaseRegistry_seq", String.valueOf(seq++));
                            objMap.put("disease_pName", formDataMap.get("disease_pName"));
                            objMap.put("disease_pDate", formDataMap.get("disease_pDate"));
                            objMap.put("disease_mrNo", formDataMap.get("disease_mrNo"));
                            objMap.put("disease_diagType", formDataMap.get("disease_diagType"));
                            objMap.put("disease_isCharge", formDataMap.get("disease_isCharge"));
                            objMap.put("disease_treatStep", formDataMap.get("disease_treatStep"));
                            String disease_isCharge = (String) formDataMap.get("disease_isCharge");
                            if (StringUtil.isNotBlank(disease_isCharge)) {
                                if ("是".equals(disease_isCharge)) {
                                    objMap.put("disease_isCharge", "√");
                                }
                            }
                            objMap.put("disease_isRescue", formDataMap.get("disease_isRescue"));
                            String disease_isRescue = (String) formDataMap.get("disease_isRescue");
                            if (StringUtil.isNotBlank(disease_isRescue)) {
                                if ("是".equals(disease_isRescue)) {
                                    objMap.put("disease_isRescue", "√");
                                }
                            }
                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            dase2PlanTargetList.add(igd);
                        }
                    }
                    dataMap2.put("dase2PlanTargetList", dase2PlanTargetList);
                    WordprocessingMLPackage temeplete3 = new WordprocessingMLPackage();
                    String path3 = "/jsp/jsres/daochu/daochuTemeplete3.docx";//模板
                    temeplete3 = Docx4jUtil.convert(new File(context.getRealPath(path3)), dataMap2, watermark, true);
                    addTemplates.add(temeplete3);
                }


                //操作技能
                Map<String, Object> dataMap3 = new HashMap<String, Object>();
                dataMap3.put("standardDeptName", dept.getStandardDeptName());
//				List<ResRec> skillRecList = resRecBiz.searchByResRecWithBLOBs(skillRecTypeId,relRecordFlow, doctorFlow, null);
                if (resRecList != null && !resRecList.isEmpty()) {
                    List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
                    seq = 1;
                    for (ResRec rec : resRecList) {
                        if (skillRecTypeId.equals(rec.getRecTypeId())
                                && relRecordFlow.equals(rec.getSchRotationDeptFlow())) {
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            objMap.put("skill_seq", String.valueOf(seq++));
                            objMap.put("skill_operDate", formDataMap.get("skill_operDate"));
                            objMap.put("skill_pName", formDataMap.get("skill_pName"));
                            objMap.put("skill_mrNo", formDataMap.get("skill_mrNo"));
                            objMap.put("skill_operName", formDataMap.get("skill_operName"));
                            objMap.put("skill_result", formDataMap.get("skill_result"));
                            objMap.put("skill_fail_reason", formDataMap.get("fail_reason"));

                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            skillsPlanTargetList.add(igd);
                        }
                    }
                    dataMap3.put("skillsPlanTargetList", skillsPlanTargetList);
                }

                //手术
//				List<ResRec> operaRecList = resRecBiz.searchByResRecWithBLOBs(operRecTypeId,relRecordFlow, doctorFlow,null);
                if (resRecList != null && !resRecList.isEmpty()) {
                    List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
                    seq = 1;
                    for (ResRec rec : resRecList) {
                        if (operRecTypeId.equals(rec.getRecTypeId())
                                && relRecordFlow.equals(rec.getSchRotationDeptFlow())) {
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            objMap.put("opera_seq", String.valueOf(seq++));
                            objMap.put("operation_operDate", formDataMap.get("operation_operDate"));
                            objMap.put("operation_pName", formDataMap.get("operation_pName"));
                            objMap.put("operation_mrNo", formDataMap.get("operation_mrNo"));
                            objMap.put("operation_operName", formDataMap.get("operation_operName"));
                            objMap.put("operation_operRole", formDataMap.get("operation_operRole"));
                            objMap.put("operation_memo", formDataMap.get("operation_memo"));
                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            operaPlanTargetList.add(igd);
                        }
                    }
                    dataMap3.put("operaPlanTargetList", operaPlanTargetList);
                }

                ResSchProcessExpress rec = expressBiz.queryResRec(relRecordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
                if (rec != null) {
                    String content = rec.getRecContent();
                    Document document = DocumentHelper.parseText(content);
                    Element elem = document.getRootElement();
                    List<Element> ec = elem.elements();
                    for (Element element : ec) {
                        Element imgEle = element.element("imageUrl");
                        if (imgEle != null) {
                            String imageUrl = imgEle.getTextTrim();
                            imageUrl = "<img alt='出科考核表' title='出科考核表' src='" + imageUrl + "'/>";
                            dataMap3.put("imageUrl", imageUrl);
                        }
                    }
                }
                WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
                String path4 = "/jsp/jsres/daochu/daochuTemeplete4.docx";//模板
                temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(path4)), dataMap3, watermark, true);
                addTemplates.add(temeplete4);
            }

        }


        WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);

        if (temeplete != null) {
            String name = "APP项目填写项及手册.docx";
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            //更新完后，设定cookie，用于页面判断更新完成后的标志
            Cookie status = new Cookie("updateStatus", "success");
            status.setMaxAge(600);
            status.setPath("/");
            response.addCookie(status);//添加cookie操作必须在写出文件前，如果写在后面，随着数据量增大时cookie无法写入。
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * 管理层导出手册
     *
     * @param recruitFlow
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/admindaochu")
    public void admindaochu(String recruitFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        String doctorFlow = recruit.getDoctorFlow();
        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
        if (doctor != null) {
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }

                        dataMap.put("graduationTime", userResumeExt.getGraduationTime());
                        // 最高学位
                        String degreeCategoryName = "";
                        if (GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsDoctor())) {
                            degreeCategoryName = userResumeExt.getDoctorDegreeName();
                        }
                        if (StringUtil.isBlank(degreeCategoryName) && GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsMaster())) {
                            degreeCategoryName = userResumeExt.getMasterDegreeName();
                        }
                        if (StringUtil.isBlank(degreeCategoryName) && GlobalConstant.RECORD_STATUS_Y.equals(userResumeExt.getIsUndergraduate())) {
                            degreeCategoryName = userResumeExt.getDegreeName();
                        }
                        dataMap.put("degreeCategoryName", degreeCategoryName);
                    }
                }
            }
            dataMap.put("trainingSpeName", recruit.getSpeName());
            dataMap.put("orgName", recruit.getOrgName());
            dataMap.put("doctorName", doctor.getDoctorName());
            dataMap.put("workOrgName", doctor.getWorkOrgName());
        }
        SchRotation rotation = rotationBiz.getRotationByRecruit(recruit);
        if (rotation != null) {
            String rotationFlow = rotation.getRotationFlow();

            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                temp.add(dept);
                rotationDeptMap.put(dept.getGroupFlow(), temp);

            }

            List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
            int seq = 1;
            List<String> schRotationDeptFlows = new ArrayList<>();
            for (SchRotationGroup srg : groupList) {
                List<SchRotationDept> schRotationDeptList = rotationDeptMap.get(srg.getGroupFlow());
                if (schRotationDeptList != null && !schRotationDeptList.isEmpty()) {
                    for (SchRotationDept srd : schRotationDeptList) {
                        schRotationDeptFlows.add(srd.getRecordFlow());
                        Map<String, Object> objMap = new HashMap<String, Object>();
                        objMap.put("seq", String.valueOf(seq++));
                        objMap.put("standardDeptName", srd.getStandardDeptName());
                        String groupFlow = srd.getGroupFlow();
                        String standardDeptId = srd.getStandardDeptId();
                        List<SchArrangeResult> schArrangeResultList = resultBiz.searchResultByGroupFlow(groupFlow, standardDeptId, doctor.getDoctorFlow());
                        if (schArrangeResultList != null && !schArrangeResultList.isEmpty()) {
                            String startDate = null;
                            String endDate = null;
                            Float schMonth = 0f;
                            for (SchArrangeResult sar : schArrangeResultList) {
                                String month = sar.getSchMonth();
                                if (startDate == null) {
                                    startDate = sar.getSchStartDate();
                                }
                                endDate = sar.getSchEndDate();
                                if (StringUtil.isNotEmpty(month)) {
                                    schMonth += (Float.parseFloat(month));
                                }
                            }
                            objMap.put("startDate", startDate);
                            objMap.put("endDate", endDate);
                            objMap.put("schMonth", schMonth.toString());
                        }
                        ItemGroupData igd = new ItemGroupData();
                        igd.setObjMap(objMap);
                        yearPlanTargetList.add(igd);
                    }
                }
            }
            dataMap.put("yearPlanTargetList", yearPlanTargetList);

            List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBsBySchRotationFlows(ResRecTypeEnum.CaseRegistry.getId(), doctorFlow, schRotationDeptFlows);
            if (resRecList != null && !resRecList.isEmpty()) {
                List<ItemGroupData> casePlanTargetList = new ArrayList<ItemGroupData>();
                for (ResRec rec : resRecList) {
                    Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                    Map<String, Object> objMap = new HashMap<String, Object>();
                    objMap.put("CaseRegistry_schDeptName", rec.getSchDeptName());
                    objMap.put("CaseRegistry_mr_pName", formDataMap.get("mr_pName"));
                    objMap.put("CaseRegistry_mr_no", formDataMap.get("mr_no"));
                    objMap.put("CaseRegistry_disease_pName", formDataMap.get("disease_pName"));
                    String zyzd = "1";
                    String cyzd = "2";
                    String bxzd = "3";
                    String mr_diagType = (String) formDataMap.get("mr_diagType_id");
                    if (StringUtil.isNotBlank(mr_diagType)) {
                        if (mr_diagType.contains(zyzd)) {
                            objMap.put("CaseRegistry_mr_diagType_zyzd", "√");
                        }
                        if (mr_diagType.contains(cyzd)) {
                            objMap.put("CaseRegistry_mr_diagType_cyzd", "√");
                        }
                        if (mr_diagType.contains(bxzd)) {
                            objMap.put("CaseRegistry_mr_diagType_bxzd", "√");
                        }
                    }
                    ItemGroupData igd = new ItemGroupData();
                    igd.setObjMap(objMap);
                    casePlanTargetList.add(igd);
                }
                dataMap.put("casePlanTargetList", casePlanTargetList);
            }

            WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
            String path1 = "/jsp/jsres/daochu/daochuTemeplete1.docx";//模板
            ServletContext context = request.getServletContext();
            String watermark = "";
            temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);

            addTemplates.add(temeplete1);

            for (SchRotationGroup srg : groupList) {
                List<SchRotationDept> schRotationDeptList = rotationDeptMap.get(srg.getGroupFlow());

                for (SchRotationDept dept : schRotationDeptList) {
                    Map<String, Object> dataMap1 = new HashMap<String, Object>();

                    String relRecordFlow = dept.getRecordFlow();
                    Map<String, String> processMap = resRecBiz.getProcessPer(doctorFlow, rotationFlow, relRecordFlow);
                    if (processMap == null) {
                        processMap = new HashMap<String, String>();
                    }

                    dataMap1.put("standardDeptName", dept.getStandardDeptName());
//			}	Map<String,List<SchRotationDeptReq>> schRotationDeptReqMap = new HashMap<String,List<SchRotationDeptReq>>();
//				for(SchRotationDeptReq schRotationDeptReq : schRotationDeptReqList){
//					String recTypeId = schRotationDeptReq.getRecTypeId();
//					if(schRotationDeptReqMap.get(recTypeId)==null){
//						List<SchRotationDeptReq>  schRotationDeptReqs = new ArrayList<SchRotationDeptReq>();
//						schRotationDeptReqs.add(schRotationDeptReq);
//						schRotationDeptReqMap.put(recTypeId, schRotationDeptReqs);
//					}else{
//						schRotationDeptReqMap.get(recTypeId).add(schRotationDeptReq);
//
//					}
//
//

                    //病种
                    String desRecTypeId = ResRecTypeEnum.DiseaseRegistry.getId();
                    List<SchRotationDeptReq> diseRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
                    List<ItemGroupData> dasePlanTargetList = new ArrayList<ItemGroupData>();
                    for (SchRotationDeptReq req : diseRotationDeptReqList) {

                        Map<String, Object> objMap = new HashMap<String, Object>();
                        String itemId = req.getItemId();
                        objMap.put("diseaseRegistry_itemName", req.getItemName());
                        String reqKey = relRecordFlow + desRecTypeId + itemId + "ReqNum";
                        String reqFin = relRecordFlow + desRecTypeId + itemId + "Finished";
                        String reqResult = processMap.get(reqKey);
                        if (!StringUtil.isNotBlank(reqResult)) {
                            reqResult = "0";
                        }
                        objMap.put("diseaseRegistry_reqKey", reqResult);
                        String reqFinish = processMap.get(reqFin);
                        if (!StringUtil.isNotBlank(reqFinish)) {
                            reqFinish = "0";
                        }
                        objMap.put("diseaseRegistry_reqFin", reqFinish);
                        objMap.put("diseaseRegistry_seq", String.valueOf(seq++));

                        ItemGroupData igd = new ItemGroupData();
                        igd.setObjMap(objMap);
                        dasePlanTargetList.add(igd);
                    }
                    dataMap1.put("dasePlanTargetList", dasePlanTargetList);

                    //操作技能
                    String skillRecTypeId = ResRecTypeEnum.SkillRegistry.getId();
                    List<SchRotationDeptReq> skillRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, skillRecTypeId);
                    List<ItemGroupData> skillPlanTargetList = new ArrayList<ItemGroupData>();
                    for (SchRotationDeptReq req : skillRotationDeptReqList) {
                        Map<String, Object> objMap = new HashMap<String, Object>();
                        String itemId = req.getItemId();
                        String reqKey = relRecordFlow + skillRecTypeId + itemId + "ReqNum";
                        String reqFin = relRecordFlow + skillRecTypeId + itemId + "Finished";
                        objMap.put("skillRegistry_itemName", req.getItemName());
                        String reqResult = processMap.get(reqKey);
                        if (!StringUtil.isNotBlank(reqResult)) {
                            reqResult = "0";
                        }
                        objMap.put("skillRegistry_reqKey", reqResult);
                        String reqFinish = processMap.get(reqFin);
                        if (!StringUtil.isNotBlank(reqFinish)) {
                            reqFinish = "0";
                        }
                        objMap.put("skillRegistry_reqFin", reqFinish);
                        ItemGroupData igd = new ItemGroupData();
                        igd.setObjMap(objMap);
                        skillPlanTargetList.add(igd);
                    }
                    dataMap1.put("skillPlanTargetList", skillPlanTargetList);

                    //手术
                    String operRecTypeId = ResRecTypeEnum.OperationRegistry.getId();
                    List<SchRotationDeptReq> operRotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, operRecTypeId);
                    List<ItemGroupData> operPlanTargetList = new ArrayList<ItemGroupData>();
                    for (SchRotationDeptReq req : operRotationDeptReqList) {
                        Map<String, Object> objMap = new HashMap<String, Object>();
                        String itemId = req.getItemId();
                        String reqKey = relRecordFlow + operRecTypeId + itemId + "ReqNum";
                        String reqFin = relRecordFlow + operRecTypeId + itemId + "Finished";

                        objMap.put("operationRegistry_itemName", req.getItemName());

                        String reqResult = processMap.get(reqKey);
                        if (!StringUtil.isNotBlank(reqResult)) {
                            reqResult = "0";
                        }
                        objMap.put("operationRegistry_reqKey", reqResult);
                        String reqFinish = processMap.get(reqFin);
                        if (!StringUtil.isNotBlank(reqFinish)) {
                            reqFinish = "0";
                        }
                        objMap.put("operationRegistry_reqFin", reqFinish);
                        ItemGroupData igd = new ItemGroupData();
                        igd.setObjMap(objMap);
                        operPlanTargetList.add(igd);
                    }
                    dataMap1.put("operPlanTargetList", operPlanTargetList);


                    WordprocessingMLPackage temeplete2 = new WordprocessingMLPackage();
                    String path2 = "/jsp/jsres/daochu/daochuTemeplete2.docx";//模板
                    temeplete2 = Docx4jUtil.convert(new File(context.getRealPath(path2)), dataMap1, watermark, true);
                    addTemplates.add(temeplete2);


                    //病种
                    Map<String, Object> dataMap2 = new HashMap<String, Object>();
                    dataMap2.put("standardDeptName", dept.getStandardDeptName());
                    int count = 1;
                    List<SchRotationDeptReq> dise2RotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
                    for (SchRotationDeptReq deptReq : dise2RotationDeptReqList) {
                        List<ItemGroupData> dase2PlanTargetList = new ArrayList<ItemGroupData>();
                        String itemId = deptReq.getItemId();
                        if (StringUtil.isBlank(itemId)) {
                            itemId = "";
                        }
                        List<ResRec> diseRecList = resRecBiz.searchByResRecWithBLOBs(desRecTypeId, relRecordFlow, doctorFlow, itemId);
                        dataMap2.put("diseaseRegistry_itemSeq", String.valueOf(count));
                        count++;
                        dataMap2.put("diseaseRegistry_itemName", deptReq.getItemName());
                        String reqKey = relRecordFlow + desRecTypeId + itemId + "ReqNum";
                        String reqFin = relRecordFlow + desRecTypeId + itemId + "Finished";
                        String reqResult = processMap.get(reqKey);
                        if (!StringUtil.isNotBlank(reqResult)) {
                            reqResult = "0";
                        }
                        dataMap2.put("disease_reqKey", reqResult);
                        String reqFinish = processMap.get(reqFin);
                        if (!StringUtil.isNotBlank(reqFinish)) {
                            reqFinish = "0";
                        }
                        dataMap2.put("disease_reqFin", reqFinish);
                        seq = 1;
                        for (ResRec rec : diseRecList) {
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            objMap.put("diseaseRegistry_seq", String.valueOf(seq++));
                            objMap.put("disease_pName", formDataMap.get("disease_pName"));
                            objMap.put("disease_pDate", formDataMap.get("disease_pDate"));
                            objMap.put("disease_mrNo", formDataMap.get("disease_mrNo"));
                            objMap.put("disease_diagType", formDataMap.get("disease_diagType"));
                            objMap.put("disease_isCharge", formDataMap.get("disease_isCharge"));
                            objMap.put("disease_treatStep", formDataMap.get("disease_treatStep"));
                            String disease_isCharge = (String) formDataMap.get("disease_isCharge");
                            if (StringUtil.isNotBlank(disease_isCharge)) {
                                if ("是".equals(disease_isCharge)) {
                                    objMap.put("disease_isCharge", "√");
                                }
                            }
                            objMap.put("disease_isRescue", formDataMap.get("disease_isRescue"));
                            String disease_isRescue = (String) formDataMap.get("disease_isRescue");
                            if (StringUtil.isNotBlank(disease_isRescue)) {
                                if ("是".equals(disease_isRescue)) {
                                    objMap.put("disease_isRescue", "√");
                                }
                            }
                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            dase2PlanTargetList.add(igd);

                        }
                        dataMap2.put("dase2PlanTargetList", dase2PlanTargetList);
                        WordprocessingMLPackage temeplete3 = new WordprocessingMLPackage();
                        String path3 = "/jsp/jsres/daochu/daochuTemeplete3.docx";//模板
                        temeplete3 = Docx4jUtil.convert(new File(context.getRealPath(path3)), dataMap2, watermark, true);
                        addTemplates.add(temeplete3);
                    }


                    //操作技能
                    Map<String, Object> dataMap3 = new HashMap<String, Object>();
                    dataMap3.put("standardDeptName", dept.getStandardDeptName());
                    List<ResRec> skillRecList = resRecBiz.searchByResRecWithBLOBs(skillRecTypeId, relRecordFlow, doctorFlow, null);
                    if (skillRecList != null && !skillRecList.isEmpty()) {
                        List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
                        seq = 1;
                        for (ResRec rec : skillRecList) {
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            objMap.put("skill_seq", String.valueOf(seq++));
                            objMap.put("skill_operDate", formDataMap.get("skill_operDate"));
                            objMap.put("skill_pName", formDataMap.get("skill_pName"));
                            objMap.put("skill_mrNo", formDataMap.get("skill_mrNo"));
                            objMap.put("skill_operName", formDataMap.get("skill_operName"));
                            objMap.put("skill_result", formDataMap.get("skill_result"));
                            objMap.put("skill_fail_reason", formDataMap.get("fail_reason"));

                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            skillsPlanTargetList.add(igd);
                        }
                        dataMap3.put("skillsPlanTargetList", skillsPlanTargetList);
                    }

                    //手术
                    List<ResRec> operaRecList = resRecBiz.searchByResRecWithBLOBs(operRecTypeId, relRecordFlow, doctorFlow, null);
                    if (operaRecList != null && !operaRecList.isEmpty()) {
                        List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
                        seq = 1;
                        for (ResRec rec : operaRecList) {
                            Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
                            Map<String, Object> objMap = new HashMap<String, Object>();
                            objMap.put("opera_seq", String.valueOf(seq++));
                            objMap.put("operation_operDate", formDataMap.get("operation_operDate"));
                            objMap.put("operation_pName", formDataMap.get("operation_pName"));
                            objMap.put("operation_mrNo", formDataMap.get("operation_mrNo"));
                            objMap.put("operation_operName", formDataMap.get("operation_operName"));
                            objMap.put("operation_operRole", formDataMap.get("operation_operRole"));
                            objMap.put("operation_memo", formDataMap.get("operation_memo"));
                            ItemGroupData igd = new ItemGroupData();
                            igd.setObjMap(objMap);
                            operaPlanTargetList.add(igd);
                        }
                        dataMap3.put("operaPlanTargetList", operaPlanTargetList);
                    }

                    ResSchProcessExpress rec = expressBiz.queryResRec(relRecordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
                    if (rec != null) {
                        String content = rec.getRecContent();
                        Document document = DocumentHelper.parseText(content);
                        Element elem = document.getRootElement();
                        List<Element> ec = elem.elements();
                        for (Element element : ec) {
                            Element imgEle = element.element("imageUrl");
                            if (imgEle != null) {
                                String imageUrl = imgEle.getTextTrim();
                                imageUrl = "<img src='" + imageUrl + "' />";
                                dataMap3.put("imageUrl", imageUrl);
                            }
                        }
                    }
                    WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
                    String path4 = "/jsp/jsres/daochu/daochuTemeplete4.docx";//模板
                    temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(path4)), dataMap3, watermark, true);
                    addTemplates.add(temeplete4);
                }

            }
        }
        WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
        if (temeplete != null) {
            String name = "APP项目填写项及手册.docx";
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/inDept", method = {RequestMethod.GET})
    public String inDept(String recordFlow, Model model) {
        SchRotationDept dept = rotationDeptBiz.readSchRotationDept(recordFlow);
        model.addAttribute("schRotationDept", dept);
        return "jsres/doctor/inDept";
    }


    @RequestMapping(value = "/saveInDept", method = {RequestMethod.POST})
    @ResponseBody
    public String saveInDept(String recordFlow, String startDate, String endDate, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        ResDoctor doctor = resDoctorBiz.searchByUserFlow(sysUser.getUserFlow());
        SchRotation rotation = rotationBiz.readSchRotation(doctor.getRotationFlow());
        SchRotationDept dept = rotationDeptBiz.readSchRotationDept(recordFlow);
        //add SCH_ARRANGE_RESULT
        SchArrangeResult result = new SchArrangeResult();
        result.setResultFlow(PkUtil.getUUID());
        result.setArrangeFlow(result.getResultFlow());
        result.setDoctorFlow(doctor.getDoctorFlow());
        result.setDoctorName(doctor.getDoctorName());
        result.setSessionNumber(doctor.getSessionNumber());
        result.setRotationFlow(doctor.getRotationFlow());
        result.setRotationName(doctor.getRotationName());
        result.setSchYear(doctor.getSessionNumber());
        result.setSchDeptOrder(new BigDecimal(dept.getOrdinal()));
        result.setSchStartDate(startDate);
        result.setSchEndDate(endDate);
//		result.setDeptFlow(deptFlow);
//		result.setDeptName(deptName);
        result.setOrgFlow(doctor.getOrgFlow());
        result.setOrgName(doctor.getOrgName());
        result.setSchMonth(dept.getSchMonth());
        result.setIsRequired(dept.getIsRequired());
        result.setStandardDeptId(dept.getStandardDeptId());
        result.setStandardDeptName(dept.getStandardDeptName());
//		result.setGroupFlow(groupFlow);
        result.setStandardGroupFlow(dept.getGroupFlow());
        resultBiz.save(result);

        //add RES_DOCTOR_SCH_PROCESS
        ResDoctorSchProcess process = new ResDoctorSchProcess();
        process.setUserFlow(sysUser.getUserFlow());
        process.setOrgFlow(doctor.getOrgFlow());
        process.setOrgName(doctor.getOrgName());
        process.setDeptFlow(dept.getStandardDeptId());
        process.setDeptName(dept.getStandardDeptName());
//		process.setSchDeptFlow(dept.getGroupFlow()); //标记组别
//		process.setSchDeptName("");
        process.setSchResultFlow(result.getResultFlow());
        process.setSchStartDate(startDate);
        process.setSchEndDate(endDate);
        process.setStartDate(startDate);
        process.setEndDate(endDate);
        process.setSchFlag(GlobalConstant.FLAG_Y);
        processBiz.edit(process);

        return GlobalConstant.SAVE_SUCCESSED;
    }


    @RequestMapping(value = "/uploadAfterEvaluation", method = {RequestMethod.GET})
    public String uploadAfterEvaluation(String processFlow, Model model) {
        ResDoctorSchProcess process = processBiz.read(processFlow);
        List<ResRec> recList = resRecBiz.searchRecByProcessWithBLOBs(processFlow, ResRecTypeEnum.AfterEvaluation.getId());
        if (recList != null && recList.size() > 0) {
            model.addAttribute("afterEvaluation", recList.get(0));
        }
        return "jsres/doctor/uploadAfterEvaluation";
    }

    //**************************************   变更培训申请    ******************************************
    @RequestMapping(value = "/applyChangeBase", method = {RequestMethod.GET})
    public String applyChangeBase(String recruitFlow, Model model) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruitWithBLOBs recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
            model.addAttribute("recruit", recruit);
        }
        return "jsres/doctor/applyChangeBase";
    }

    //**************************************  专业变更申请   ******************************************
    @RequestMapping(value = "/applyChangeSpe", method = {RequestMethod.GET})
    public String applyChangeSpe(String doctorFlow, String recruitFlow, String orgFlow, Model model) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            List<ResOrgSpe> speList = new ArrayList<ResOrgSpe>();
            ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
            model.addAttribute("recruit", recruit);
            if (recruit != null) {
                if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
                    if (Integer.parseInt(recruit.getSessionNumber()) > 2014) {
                        ResOrgSpe spe = new ResOrgSpe();
                        spe.setOrgFlow(recruit.getOrgFlow());
                        spe.setSpeTypeId(recruit.getCatSpeId());
                        spe.setRecordStatus(GlobalConstant.FLAG_Y);
                        speList = resBaseSpeBiz.searchResOrgSpeList(spe);
                    } else {
                        List<SysDict> dictList = DictTypeEnum.sysListDictMap.get(recruit.getCatSpeId());
                        if (dictList != null && !dictList.isEmpty()) {
                            ResOrgSpe spe = null;
                            for (SysDict dict : dictList) {
                                spe = new ResOrgSpe();
                                spe.setSpeId(dict.getDictId());
                                spe.setSpeName(dict.getDictName());
                                speList.add(spe);
                            }
                        }
                    }
                    model.addAttribute("speList", speList);
                }
            }
        }
        return "jsres/doctor/applyChangeSpe";
    }

    /**
     * 上传变更专业申请图片
     */
    @RequestMapping(value = {"/uploadChangeSpeFile"})
    @ResponseBody
    public String uploadChangeSpeFile(MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkImg(file);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
                return resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping(value = {"/uploadDelayFile"})
    @ResponseBody
    public String uploadDelayFile(MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkImg(file);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages_delay");
                return resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 上传退赔附件
     *
     * @param backFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadBackFile"})
    @ResponseBody
    public String uploadBackFile(MultipartFile backFile) throws Exception {
        if (backFile != null && !backFile.isEmpty()) {
            String checkResult = jsResDoctorBiz.checkFile(backFile);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
                return checkResult;
            } else {
                resultPath = jsResDoctorBiz.saveFileToDirs("", backFile, "jsresFiles_back");
                return backFile.getOriginalFilename() + "/back" + resultPath;
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 提交变更基地申请
     *
     * @param recruitFlow
     * @param docOrgHistory
     * @return
     */
    @RequestMapping(value = "/submitDoctorOrgHistory", method = {RequestMethod.POST})
    @ResponseBody
    public String submitDoctorOrgHistory(String recruitFlow, ResDoctorOrgHistory docOrgHistory) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            String doctorFlow = docOrgHistory.getDoctorFlow();
            ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
            if (doctor != null) {
                docOrgHistory.setDoctorName(doctor.getDoctorName());
            }
            ResDoctorRecruit docRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
            if (docRecruit != null) {
                String changeStatusId = docOrgHistory.getChangeStatusId();
                docOrgHistory.setHistoryOrgFlow(docRecruit.getOrgFlow());
                docOrgHistory.setHistoryOrgName(docRecruit.getOrgName());
                if (StringUtil.isNotBlank(changeStatusId)) {
                    docOrgHistory.setChangeStatusName(JsResChangeApplyStatusEnum.getNameById(changeStatusId));
                }
                docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
                docOrgHistory.setChangeTypeName(JsResChangeTypeEnum.BaseChange.getName());
                docOrgHistory.setTrainingSpeId(docRecruit.getSpeId());
                docOrgHistory.setTrainingSpeName(docRecruit.getSpeName());
                docOrgHistory.setHistoryTrainingSpeId(docRecruit.getSpeId());
                docOrgHistory.setHistoryTrainingSpeName(docRecruit.getSpeName());
                docOrgHistory.setHistorySessionNumber(docRecruit.getSessionNumber());
                docOrgHistory.setRecruitFlow(recruitFlow);//将变更信息绑定到相应的规培记录中
                int result = resDoctorOrgHistoryBiz.saveDocOrgHistory(docOrgHistory);
                if (GlobalConstant.ZERO_LINE != result) {
                    return GlobalConstant.SAVE_SUCCESSED;
                }
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 提交变更专业申请
     *
     * @param recruit
     * @param speChangeApplyFile
     * @return
     */
    @RequestMapping(value = "/submitChangeSpe", method = {RequestMethod.POST})
    @ResponseBody
    public String submitChangeSpe(ResDoctorRecruit recruit, String speChangeApplyFile) {
        ResDoctorOrgHistory history = new ResDoctorOrgHistory();
        if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
            ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
            if (doctor != null) {
                history.setDoctorFlow(doctor.getDoctorFlow());
                history.setDoctorName(doctor.getDoctorName());
            }
            ResDoctorRecruit docRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruit.getRecruitFlow());
            if (docRecruit != null) {
                history.setOrgFlow(docRecruit.getOrgFlow());
                history.setOrgName(docRecruit.getOrgName());
                history.setHistoryOrgFlow(docRecruit.getOrgFlow());
                history.setHistoryOrgName(docRecruit.getOrgName());
                history.setChangeStatusId(JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
                history.setChangeStatusName(JsResChangeApplySpeEnum.BaseWaitingAudit.getName());
                history.setTrainingSpeId(recruit.getSpeId());
                history.setTrainingSpeName(recruit.getSpeName());
                history.setHistoryTrainingSpeId(docRecruit.getSpeId());
                history.setHistoryTrainingSpeName(docRecruit.getSpeName());
                history.setSpeChangeApplyFile(speChangeApplyFile);
                history.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
                history.setChangeTypeName(JsResChangeTypeEnum.SpeChange.getName());
                history.setRecruitFlow(recruit.getRecruitFlow());//将变更信息绑定到相应的规培记录中
                if (StringUtil.isNotBlank(recruit.getCatSpeId())) {
                    history.setTrainingTypeId(recruit.getCatSpeId());
                    history.setTrainingTypeName(TrainCategoryEnum.getNameById(recruit.getCatSpeId()));
                    history.setHistoryTrainingTypeId(docRecruit.getCatSpeId());
                    history.setHistoryTrainingTypeName(docRecruit.getCatSpeName());
                }
                int result = resDoctorOrgHistoryBiz.saveDocOrgHistory(history);
                if (GlobalConstant.ZERO_LINE != result) {
                    return GlobalConstant.SAVE_SUCCESSED;
                }
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 查询专业
     *
     * @return
     */
    @RequestMapping(value = "/changeType", method = {RequestMethod.GET})
    @ResponseBody
    public List<ResOrgSpe> changeType(ResDoctorRecruit recruit) {
        List<ResOrgSpe> speList = new ArrayList<ResOrgSpe>();
        if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
            if (Integer.parseInt(recruit.getSessionNumber()) > 2014) {
                ResOrgSpe spe = new ResOrgSpe();
                spe.setOrgFlow(recruit.getOrgFlow());
                spe.setSpeTypeId(recruit.getCatSpeId());
                spe.setRecordStatus(GlobalConstant.FLAG_Y);
                speList = resBaseSpeBiz.searchResOrgSpeList(spe);
            } else {
                List<SysDict> dictList = DictTypeEnum.sysListDictMap.get(recruit.getCatSpeId());
                if (dictList != null && !dictList.isEmpty()) {
                    ResOrgSpe spe = null;
                    for (SysDict dict : dictList) {
                        spe = new ResOrgSpe();
                        spe.setSpeId(dict.getDictId());
                        spe.setSpeName(dict.getDictName());
                        speList.add(spe);
                    }
                }
            }
        }
        return speList;
    }

    /**
     * @param resOrgSpe
     * @return
     */
    @RequestMapping(value = "/checkResOrgSpe", method = {RequestMethod.GET})
    @ResponseBody
    public String checkResOrgSpe(ResOrgSpe resOrgSpe) {
        resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
        List<ResOrgSpe> orgSpeList = resBaseSpeBiz.searchResOrgSpeList(resOrgSpe, null);
        if (orgSpeList != null && !orgSpeList.isEmpty()) {
            return GlobalConstant.FLAG_Y;
        } else {
            return GlobalConstant.FLAG_N;
        }
    }
    /****************************高******校******管******理******员******角******色************************************************/
    /**
     * 延期医师查询
     *
     * @param model
     * @param docotrDelayTeturn
     * @param currentPage
     * @param request
     * @param orgFlow
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/delayForUni")
    public String delayForUni(Model model, ResDocotrDelayTeturn docotrDelayTeturn, Integer currentPage, String orgFlow, HttpServletRequest request) throws DocumentException {
        List<String> orgFlowList = new ArrayList<String>();
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysUser curUser = GlobalContext.getCurrentUser();
        SysOrg currOrg = new SysOrg();
        currOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
        ResDoctor doctor = new ResDoctor();
        doctor.setDoctorTypeId(JsResDocTypeEnum.Graduate.getId());
        doctor.setDoctorTypeName(JsResDocTypeEnum.Graduate.getName());
        doctor.setWorkOrgId(currOrg.getSendSchoolId());
        doctor.setWorkOrgName(currOrg.getSendSchoolName());
        orgs = orgBiz.getUniOrgs(currOrg.getSendSchoolId(), currOrg.getSendSchoolName());
        model.addAttribute("orgs", orgs);
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
        docotrDelayTeturn.setOrgFlow(orgFlow);
        orgFlowList.add(currOrg.getOrgFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
//		List<ResRec> resRecList = resRecBiz.searchDelayInfoForUni(resRec,doctor);
        ResDoctor currdoctor = new ResDoctor();
        currdoctor.setWorkOrgId(currOrg.getSendSchoolId());
        currdoctor.setWorkOrgName(currOrg.getSendSchoolName());
        if (StringUtil.isNotEmpty(curUser.getSchool())) {
            currdoctor.setSchool(curUser.getSchool());
        }
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfoForUni(docotrDelayTeturn, currdoctor);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (resRecList != null && !resRecList.isEmpty()) {
            for (Iterator<ResDocotrDelayTeturn> it = resRecList.iterator(); it.hasNext(); ) {
                ResDocotrDelayTeturn beResRec = it.next();
                String operUserFlow = beResRec.getDoctorFlow();
                ResDoctor beDoctor = resDoctorBiz.readDoctor(operUserFlow);
                if (!JsResDocTypeEnum.Graduate.getId().equals(beDoctor.getDoctorTypeId())) {
                    it.remove();
                }
            }
        }
        model.addAttribute("resRecList", resRecList);
        return "jsres/university/doctor/delayInfo";
    }

    /**
     * 退培医师查询
     *
     * @param docotrDelayTeturn
     * @param model
     * @param userFlow
     * @param currentPage
     * @param request
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/backTrainInfoForUni")
    public String backTrainInfoForUni(ResDocotrDelayTeturn docotrDelayTeturn, Model model, String trainingTypeId, String userFlow,
                                      Integer currentPage, HttpServletRequest request) throws DocumentException {
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        List<SysOrg> orgs = new ArrayList<SysOrg>();
        SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
        orgs = orgBiz.getUniBackTrainOrgs(currOrg.getSendSchoolId(), currOrg.getSendSchoolName());
        model.addAttribute("orgs", orgs);
        docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        /**
         * 拆分res_rec表时注释掉了
         ResDoctor currdoctor = new ResDoctor();
         currdoctor.setDoctorName(docotrDelayTeturn.getDoctorName());
         currdoctor.setDoctorTypeId(JsResDocTypeEnum.Graduate.getId());
         currdoctor.setWorkOrgId(currOrg.getSendSchoolId());
         currdoctor.setWorkOrgName(currOrg.getSendSchoolName());
         currdoctor.setTrainingSpeName(speName);
         currdoctor.setTrainingTypeId(trainingTypeId);
         currdoctor.setSessionNumber(sessionNumber);
         currdoctor.setRegNo(reasonId);
         currdoctor.setEmergencyPhone(policyId);
         List<Map<String,String>> resRecList = resRecBiz.searchInfoForUni(resRec, currdoctor);
         **/

        ResDoctor currdoctor = new ResDoctor();
        currdoctor.setWorkOrgId(currOrg.getSendSchoolId());
        currdoctor.setWorkOrgName(currOrg.getSendSchoolName());
        if (StringUtil.isNotEmpty(user.getSchool())) {
            currdoctor.setSchool(user.getSchool());
        }
        //参数flags为查询通过或不通过时用
        List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfoForUni(docotrDelayTeturn, currdoctor);
        model.addAttribute("resRecList", resRecList);
        return "jsres/university/backTrainInfo";
    }

    @RequestMapping(value = "/exportDoctorForUni")
    public void exportDoctorForUni(HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String graduationYear) throws Exception {
        String[] headLines = null;
        headLines = new String[]{
                "住院医师规范化培训" + sessionNumber + "级招收对象花名册",
                "省（区、市）卫生计生行政部门（盖章）：江苏省卫生和计划生育委员会        填报人：顾丰      联系方式：025-83620704",
        };
        String[] titles = new String[]{
                ":编号",
                "sysUser.userName:姓名",
                "sysUser.sexName:性别",
                "sysUser.userBirthday:出生年月",
                "sysUser.nationName:民族",
                "sysUser.idNo:身份证号码（若为其他证件，需注明）",
                "sysUser.userPhone:联系方式（手机）",
                "sysUser.userEmail:联系方式（邮箱）",
                "userResumeExt.graduatedName:本科毕业院校及专业",
                "userResumeExt.specialized:本科毕业院校及专业",
                "userResumeExt.graduationTime:毕业时间",
                "sysUser.educationName:最高学历",
                "doctor.graduatedName:最高学历毕业院校",
                "doctor.specialized:最高学历毕业专业",
                "doctor.graduationTime:获得最高学历时间",
                "doctor.doctorTypeName:身份类型（单位人/社会人）",
                "doctor.school:院校",
                "doctor.workOrgName:派出单位（限“单位人”填写）",
                "doctor.orgName:培训基地（若在协同单位，需注明）",
                "doctor.trainingSpeName:培训专业",
                "recruit.recruitDate:参训时间",
                "recruit.trainYear:计划参训时限",
        };
        SysOrg org = new SysOrg();
        SysUser exSysUser = GlobalContext.getCurrentUser();
        SysUser sysUser = new SysUser();
        List<String> jointOrgFlowList = new ArrayList<String>();
        List<UserInfoExtForm> userExtForms = new ArrayList<UserInfoExtForm>();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            recruit.setGraduationYear(graduationYear);
        }
        List<String> docTypeList = new ArrayList<String>();
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            {
                org.setOrgProvId("320000");
                if (StringUtil.isNotBlank(orgLevel)) {
                    org.setOrgLevelId(orgLevel);
                    org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                }
            }
        }
        if (StringUtil.isNotBlank(user.getIdNo())) {
            sysUser.setIdNo(user.getIdNo());
        }
        sysUser.setUserName(user.getUserName());
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        docTypeList.add(JsResDocTypeEnum.Graduate.getId());
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        ResRec resRec = new ResRec();
        resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
        if (StringUtil.isNotEmpty(exSysUser.getSchool())) {
            doctor.setSchool(exSysUser.getSchool());
        }
        List<JsDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResumeForUni(recruit, doctor, sysUser, org, derateFlag, docTypeList, exSysUser);
        List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
        Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
        if (jointOrgs != null && !jointOrgs.isEmpty()) {
            for (Map<String, Object> en : jointOrgs) {
                Object key = en.get("key");
                Object value = en.get("value");
                orgAndJointNameMap.put(key, value);
            }
        }
        if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
            for (JsDoctorInfoExt d : doctorInfoExts) {
                UserInfoExtForm userInfoExtForm = new UserInfoExtForm();
                if (orgAndJointNameMap.containsKey(d.getOrgFlow())) {
                    d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow()) + "(" + d.getOrgName() + ")");
                }
                String content = d.getUserResume().getUserResume();
                if (StringUtil.isNotBlank(content)) {
                    UserResumeExtInfoForm form = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                    userInfoExtForm.setUserResumeExt(form);
                }
                SysUser su = d.getSysUser();
                String cretTypeId = su.getCretTypeId();
                if (StringUtil.isNotBlank(cretTypeId)) {
                    if (!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
                        su.setIdNo(su.getIdNo() + "(" + CertificateTypeEnum.getNameById(cretTypeId) + ")");
                    }
                }
                userInfoExtForm.setSysUser(su);
                userInfoExtForm.setDoctor(d.getResDoctor());
                ResDoctorRecruit recruit3 = d;
                userInfoExtForm.setRecruit(recruit3);
                if (StringUtil.isNotBlank(d.getTrainYear())) {
                    d.setTrainYear(JsResTrainYearEnum.getNameById(d.getTrainYear()));
                }
                userExtForms.add(userInfoExtForm);
            }
        }
        String fileName = "住院医师规范化培训" + sessionNumber + "级招收对象花名册.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, userExtForms, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping(value = "/exportForBackForUni")
    public void exportForBackForUni(HttpServletRequest request, HttpServletResponse response, ResRec resRec, Model model, String userFlow, String sessionNumber, String speName, String reasonId, String policyId) throws Exception {
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        List<String> userFlowList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(userFlow)) {
            userFlowList.add(userFlow);
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            resRec.setOrgFlow(user.getOrgFlow());
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            List<SysOrg> orgs = new ArrayList<SysOrg>();
            SysOrg org = new SysOrg();
            SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
            org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgs = orgBiz.searchAllSysOrg(org);
            model.addAttribute("orgs", orgs);
        }
        resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
        List<ResRec> resRecList = resRecBiz.searchRecInfo(resRec, userFlowList);
        if (resRecList != null && !resRecList.isEmpty()) {
            backInfoMap = jsResRecBiz.paraseBackTrain(resRecList, sessionNumber, speName, reasonId, policyId);
        }
        DecimalFormat df = new DecimalFormat("0.00%");
        ResDoctor doctor = new ResDoctor();
        if (StringUtil.isNotBlank(resRec.getOrgName())) {
            doctor.setOrgName(resRec.getOrgName());
        }
        if (StringUtil.isNotBlank(resRec.getOrgFlow())) {
            doctor.setOrgFlow(resRec.getOrgFlow());
        }
        doctor.setSessionNumber(sessionNumber);
        int count = resDoctorBiz.findDoctorCountInOrg(doctor);
        Map<Object, Object> countMap = (Map<Object, Object>) backInfoMap.get(GlobalConstant.FLAG_F);
        float percent = 0;
        String per = "";
        if (resRecList != null && !resRecList.isEmpty() && count != 0) {
            percent = (float) countMap.size() / (float) count;
            per = df.format(percent);
        } else {
            per = 0 + "%";
        }
        String flag = GlobalConstant.FLAG_Y;
        if (StringUtil.isBlank(resRec.getOrgFlow()) || StringUtil.isBlank(sessionNumber)) {
            flag = GlobalConstant.FLAG_N;
        }
        backInfoMap.put(GlobalConstant.FLAG_N, per);
        jsResDoctorBiz.exportForBack(backInfoMap, response, flag);
    }

    @RequestMapping(value = "/manualSearchForUni")
    public String manualSearchForUni(Model model, Integer currentPage, HttpServletRequest request, ResDoctor doctor) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        List<SysOrg> orgs = orgBiz.getUniOrgs(currentOrg.getSendSchoolId(), currentOrg.getSendSchoolName());
        model.addAttribute("orgs", orgs);
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        List<String> docTypeList = new ArrayList<String>();
        docTypeList.add(JsResDocTypeEnum.Graduate.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<JsResDoctorRecruitExt> doctorList = null;
        SysUser sysUser = null;
        if (StringUtil.isNotBlank(doctor.getDoctorName())) {
            sysUser = new SysUser();
            sysUser.setUserName(doctor.getDoctorName());
        }
        if (StringUtil.isNotEmpty(currentUser.getSchool())) {
            doctor.setSchool(currentUser.getSchool());
        }
        doctorList = jsResDoctorRecruitBiz.searchDoctorManualForUni(resDoctorRecruit, doctor, docTypeList, currentUser);
//		Map<String,String> manualMap = new HashMap();
//		if(doctorList!=null && doctorList.size()>0){
//			for (JsResDoctorRecruitExt Temp:doctorList) {
//				SysCfg cfg =cfgBiz.read("stu_manual_"+Temp.getDoctorFlow());
//				if(cfg != null && GlobalConstant.FLAG_Y.equals(cfg.getCfgValue())){
//					manualMap.put(Temp.getDoctorFlow(),GlobalConstant.FLAG_Y);
//				}
//			}
//		}
        model.addAttribute("doctorList", doctorList);
//		model.addAttribute("manualMap",manualMap);
        return "jsres/university/manualSearch";
    }

    @RequestMapping(value = "/expGraduationDoc")
    public void expGraduationDoc(String year, HttpServletResponse response) throws Exception {
        String[] titles = new String[]{
                "province:省",
                "userName:姓名",
                "sexName:性别",
                "idCardType:身份证类型",
                "idCard:身份证号码",
                "nationName:民族",
                "birthDate:出生日期",
                "phoneNo:手机号码",
                "emailNo:电子邮箱地址",
                ":QQ",
                ":照片",
                ":外语等级考试类型",
                ":英语等级",
                ":外语等级（非英语）",
                ":外语等级考试证书编号",
                ":外语等级考试证书取得时间",
                "isYszgTest:是否通过医师资格考试",
                ":通过时间",
                "isYszgCert:是否获得医师资格证书",
                "yszgLevel:医师资格级别",
                "yszgType:医师资格类别",
                "yszgCertNo:医师资格证书编码",
                ":取得医师资格证书时间",
                "isGzdw:是否有工作单位",
                "gzdwName:工作单位名称",
                ":工作单位统一社会信用代码/组织机构代码",
                ":工作单位医院级别",
                ":工作单位医院等次",
                ":工作单位医疗卫生机构类别",
                ":工作单位医疗卫生机构隶属关系",
                "ryAttributes:人员属性",
                "ryType:人员类型",
                ":人员类型其他",
                "isQkdddxxy:是否全科订单定向学员",
                "thisPastYear:应届/往届",
                "sessionNumber:招收年度",
                "zsztName:报名人招收状态",
                "province:培训所在省份",
                "trainOrgName:培训基地",
                ":培训基地统一社会信用代码/组织机构代码",
                "trainSpeName:培训专业",
                "trainYear:核定培训年限",
                "trainBeginTime:实际培训开始时间",
                "trainEndTime:实际培训结束时间",
                ":是否为援疆援藏住院医师",
                ":援疆援藏住院医师送出省份",
                ":援疆援藏住院医师接受省份",
                ":援疆援藏住院医师送出单位统一社会信用代码/组织机构代码",
                "isReading:是否在读",
                "educationName:学历",
                "schoolName:毕业/在读院校",
                ":毕业/在读院校（其他）",
                ":毕业/在读院校统一社会信用代码/组织机构代码",
                "schoolSpeName:毕业（在读）专业/二级学科",
                ":三级学科（研究生填写）",
                "isQrzjy:是否全日制教育",
                "isGainCert:是否获得毕业（学历）证书",
                "xlCertNo:学历证书编号",
                "degreeName:学位",
                ":取得学位时间",
                "degreeCertNo:学位证书编号",
                "degreeType:学位类型",
                "isInXtOrg:是否在协同单位培训",
                ":协同单位培训时长（月）",
                ":协同单位医院级别",
                ":协同单位医院等次",
                ":协同单位统一社会信用代码/组织机构代码",
                "province:考核所在省",
                "trainSpeName:报考专业",
                "theoryTestYear:理论考核年度",
                ":理论报考次数（累计）",
                "skillTestYear:技能考核年度",
                ":技能报考次数（累计）",
                ":理论考核时间",
                "theoryTestScore:理论考核成绩",
                "theoryTestResult:理论考核结果",
                "oneScore:第一站成绩",
                "twoScore:第二站成绩",
                "threeScore:第三站成绩",
                "fourScore:第四站成绩",
                "fiveScore:第五站成绩",
                "sixScore:第六站成绩",
                "sevenScore:第七站成绩",
                "eightScore:第八站成绩",
                "nineScore:第九站成绩",
                "skillTestScore:技能考试总成绩",
                "skillTestResult:技能考试综合结果",
                "graduateTestResult:结业考核结果",
                "isPassAllTest:学员是否通过住院医师规范化培训全部过程考核"
        };
        String fileName = year + "年结业考核信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        Map<String, Object> param = new HashMap<>();
        SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        param.put("orgProvId", sysOrg.getOrgProvId());
        List<JykhInfoForm> userExtForms = jsResDoctorRecruitBiz.graduateExtList(param);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, userExtForms, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
	/*@RequestMapping(value="/exportMessage")
	public void exportMessage(HttpServletResponse response)throws Exception{
		String[] titles = new String[]{
				"userName:姓名",
				"sexName:性别",
				"nationName:民族",
				"birthDate:出生日期",
				":婚姻状况",
				":国家及地区",
				"idCardType:身份证件类型",
				"idCard:身份证号码",
				":户口所在地省行政区划",
				"phoneNo:有效的手机号码",
				":有效的QQ号码",
				"emailNo:有效的电子邮箱地址",
				":是否通过医师资格考试",
				":通过医师资格考试时间",
				":是否获得医师资格证书",
				":取得医师资格证书时间",
				":医师资格级别",
				":医师资格类别",
				":医师资格证书编码",
				":外语等级考试类型",
				":英语能力",
				":外语能力",
				":外语等级考试证书编号",
				":外语等级考试证书取得时间",
				":是否中专",
				":是否是全日制",
				":是否在读",
				":在读学历",
				":预计毕业时间",
				":在读专业",
				":在读院校",
				":其他在读院校",
				":学历",
				":毕业时间",
				":毕业专业",
				":毕业院校",
				":其他毕业院校",
				":是否获得毕业证书",
				":学历证书编号",
				":学历证书取得时间",
				":是否获得学位证书",
				":学位",
				":学位类型",
				":学位证书编号",
				":学位证书取得时间",
				":是否大专",
				":是否全日制",
				":是否在读",
				":在读学历",
				":预计毕业时间",
				":在读专业",
				":在读院校",
				":其他在读院校",
				":学历",
				":毕业时间",
				":毕业专业",
				":毕业院校",
				":其他毕业院校",
				":是否获得毕业证书",
				":学历证书编号",
				":学历证书取得时间",
				":是否获得学位证书",
				":学位",
				":学位类型",
				":学位证书编号",
				":学位证书取得时间",
				":是否本科",
				":是否全日制",
				":是否在读",
				":在读学历",
				":预计毕业时间",
				":在读专业",
				":在读院校",
				":其他在读院校",
				":学历",
				":毕业时间",
				":毕业专业",
				":毕业院校",
				":其他毕业院校",
				":是否获得毕业证书",
				":学历证书编号",
				":学历证书取得时间",
				":是否获得学位证书",
				":学位",
				":学位类型",
				":学位证书编号",
				":学位证书取得时间",
				":是否硕士研究生",
				":是否全日制",
				":是否在读",
				":在读学历",
				":预计毕业时间",
				":在读专业",
				":在读院校",
				":其他在读院校",
				":学历",
				":毕业时间",
				":毕业专业",
				":毕业院校",
				":其他毕业院校",
				":是否获得毕业证书",
				":学历证书编号",
				":学历证书取得时间",
				":是否获得学位证书",
				":学位",
				":学位类型",
				":学位证书编号",
				":学位证书取得时间",
				":是否博士研究生",
				":是否全日制",
				":是否在读",
				":在读学历",
				":预计毕业时间",
				":在读专业",
				":在读院校",
				":其他在读院校",
				":学历",
				":毕业时间",
				":毕业专业",
				":毕业院校",
				":其他毕业院校",
				":是否获得毕业证书",
				":学历证书编号",
				":学历证书取得时间",
				":是否获得学位证书",
				":学位",
				":学位类型",
				":学位证书编号",
				":学位证书取得时间",
				":培养类型",
				":人员类型",
				":其他人员",
				":工作单位名称",
				":工作单位统一信用代码",
				":往届生/应届生",
				":是否为农村订单定向免费医学毕业生",
				":招收年度",
				":培训所在省行政区划",
				":培训所在培训基地（医院）统一社会信用代码",
				":培训所在培训基地",
				":培训专业",
				":核定的培训年限（年）",
				":培训起止时间",
				":是否为西部支援行动住院医师",
				":西部支援行动住院医师送出省份",
				":西部支援行动住院医师送出单位统一社会信用代码",
				":西部支援行动住院医师送出单位",
				":西部支援熊东住院医师接收省份",
				":西部支援行动住院医师接收省份培训结缔（医院）统一社会信用代码",
				":西部支援行动住院医师接收省份培训基地（医院）",

		};
		String fileName = "2018国家信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		Map<String,Object> param = new HashMap<>();
		SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		param.put("orgProvId",sysOrg.getOrgProvId());
		List<JykhInfoForm> userExtForms = jsResDoctorRecruitBiz.graduateExtList(param);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleByObjs(titles, userExtForms, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}*/

    @RequestMapping(value = "/exportMessage")
    public void exportMessage(HttpServletRequest request, HttpServletResponse response, String sessionNumber, ResDoctor doctor, SysUser user, String baseId, String jointOrgFlag, String derateFlag, String orgLevel, String[] datas, String[] yearDatas, String graduationYear) throws Exception {
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        SysUser exSysUser = GlobalContext.getCurrentUser();
        SysOrg currenOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
        List<String> jointOrgFlowList = new ArrayList<String>();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            recruit.setGraduationYear(graduationYear);
        }
		/*if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(doctor.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(exSysUser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}*/
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            if (OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId()) && StringUtil.isBlank(doctor.getOrgFlow())) {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
                List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
                if (resJointOrgsList != null && !resJointOrgsList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
            if (OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId()) && StringUtil.isNotBlank(doctor.getOrgFlow())) {
                jointOrgFlowList.add(doctor.getOrgFlow());
            }
            if (!OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())) {
                jointOrgFlowList.add(exSysUser.getOrgFlow());
            }
        }
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE) || getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    List<ResJointOrg> resJointOrgsList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    for (ResJointOrg jointOrg : resJointOrgsList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
                jointOrgFlowList.add(doctor.getOrgFlow());
            } else {
                SysOrg sysOrg = orgBiz.readSysOrg(exSysUser.getOrgFlow());
                if (GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(exSysUser.getOrgFlow());
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                } else {
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }
        if (StringUtil.isNotBlank(derateFlag)) {
            if (GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        List<String> trainYearList = new ArrayList<String>();
        if (yearDatas != null && yearDatas.length > 0) {
            for (String s : yearDatas) {
                trainYearList.add(s);
            }
        }
        String titleYear = "";
        List<String> sessionNumbers = new ArrayList<String>();//年级多选

        doctor.setSessionNumber("2018");
        recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<JsDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResume1(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList, trainYearList, sessionNumbers, "");
        jsResDoctorBiz.exportMessage(doctorInfoExts, titleYear, response);
    }

    @RequestMapping(value = "/doctorFileMain")
    public String doctorFileMain(Model model) {
        return "jsres/doctor/doctorFileMain";
    }

    @RequestMapping(value = "/doctorFileList", method = {RequestMethod.POST})
    public String doctorFileList(Model model, Integer currentPage, String productFlow, HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<PubFile> fileList = fileBiz.findFileByType("doctorFile", productFlow, "");
        model.addAttribute("fileList", fileList);
        return "jsres/doctor/doctorFileList";
    }

    @RequestMapping(value = "/uploadDoctorFile")
    public String uploadDoctorFile(Model model, HttpServletRequest request) {
        List<ResTestConfig> testList = resTestConfigBiz.findAll();
        model.addAttribute("testList", testList);
        return "jsres/doctor/uploadDoctorFile";
    }

    @RequestMapping(value = "/checkUploadDoctorFile", method = {RequestMethod.POST})
    public String checkUploadDoctorFile(String sessionNumber, String testId, MultipartFile uploadFile, Model model) {
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = "";
            String folderName = "doctorGraduation";
            fileResult = jsResDoctorBiz.checkFile(uploadFile);
            String resultPath = "";
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", "请上传.xlsx,.xls格式的文件");
            } else {
                PubFile pubFile = null;
                List<PubFile> fileList = fileBiz.findFileByType("doctorFile", sessionNumber, testId);
                if (null != fileList && fileList.size() > 0) {
                    pubFile = fileList.get(0);
                } else {
                    pubFile = new PubFile();
                }
                resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, folderName);
                model.addAttribute("filePath", resultPath);
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(sessionNumber);
                pubFile.setFileUpType(testId);
                pubFile.setFilePath(resultPath);
                pubFile.setFileName(uploadFile.getOriginalFilename());
                pubFile.setProductType("doctorFile");
                fileBiz.editFile(pubFile);
            }
            model.addAttribute("result", fileResult);
        }
        return "jsres/doctor/uploadDoctorFile";
    }

    @RequestMapping(value = "/downDoctorFile")
    public void downDoctorFile(String fileFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PubFile pubFile = fileBiz.readFile(fileFlow);
        fileBiz.downPubFile2(pubFile, response);
    }
}
