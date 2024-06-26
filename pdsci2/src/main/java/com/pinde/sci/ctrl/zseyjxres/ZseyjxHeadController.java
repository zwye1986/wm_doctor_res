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
import com.pinde.sci.biz.zseyjxres.IZseyScholarSchArrangeBiz;
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
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.enums.zseyjxres.StuRoleEnum;
import com.pinde.sci.form.zseyjxres.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
import com.sdk.utils.GloatContant;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URLEncoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/zseyjxres/head")
@Controller
public  class ZseyjxHeadController extends GeneralController{
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
        SysUser user = GlobalContext.getCurrentUser();
        mp.put("deptFlow",user.getDeptFlow());
        mp.put("isBack",GlobalConstant.FLAG_N);//默认查询未退回的
        mp.put("statusId", StuStatusEnum.Passing.getId());
        List<StuUserExt> dsh = stuUserBiz.searchStuUserByHead(mp);
        int passingCount = dsh == null ? 0 : dsh.size();//待审核数
        mp.put("statusId", StuStatusEnum.Passed.getId());
        List<StuUserExt> shtg = stuUserBiz.searchStuUserByHead(mp);
        int passedCount = shtg == null ? 0 : shtg.size();//审核通过数

        mp.put("statusId", StuStatusEnum.UnPassed.getId());
        List<StuUserExt> shbtg = stuUserBiz.searchStuUserByHead(mp);
        int uppassedCount = shbtg == null ? 0 : shbtg.size();//审核不通过数

        mp.put("statusId", StuStatusEnum.Recruitted.getId());
        List<StuUserExt> lq2 = stuUserBiz.searchStuUserByHead(mp);
        int recruittedCount = lq2 == null ? 0 : lq2.size();//已录取数

        mp.put("statusId", StuStatusEnum.UnRecruitted.getId());
        List<StuUserExt> wlq2 = stuUserBiz.searchStuUserByHead(mp);
        int unrecruittedCount = wlq2 == null ? 0 : wlq2.size();//未录取数


        mp.put("statusId", StuStatusEnum.Admited.getId());
        List<StuUserExt> lq = stuUserBiz.searchStuUserByHead(mp);
        int admitedCount = lq == null ? 0 : lq.size();//报到数

        mp.put("statusId", StuStatusEnum.UnAdmitd.getId());
        List<StuUserExt> wlq = stuUserBiz.searchStuUserByHead(mp);
        int unadmitedCount = wlq == null ? 0 : wlq.size();//未报到数

        mp.put("statusId", StuStatusEnum.Graduation.getId());
        List<StuUserExt> jy = stuUserBiz.searchStuUserByHead(mp);
        int graduationCount = jy == null ? 0 : jy.size();//结业数

        mp.put("statusId", StuStatusEnum.DelayGraduation.getId());
        List<StuUserExt> yjy = stuUserBiz.searchStuUserByHead(mp);
        int delayGraduationCount = yjy == null ? 0 : yjy.size();//延期结业数

        model.addAttribute("passingCount", passingCount);
        model.addAttribute("singupCount", passingCount + passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + uppassedCount + graduationCount + delayGraduationCount);//报名数
        model.addAttribute("passedCount", passedCount + recruittedCount + unrecruittedCount + admitedCount + unadmitedCount + graduationCount + delayGraduationCount);//审核通过数

        //读取系统消息
        PubMsgExample example = new PubMsgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andReceiverEqualTo(user.getDeptFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
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

        return "zseyjxres/head/index";
    }

    /**
     * 报名审核
     */
    @RequestMapping(value = "/audit")
    public String audit(String statusId, Integer currentPage,HttpServletRequest request, String userName, String batchFlow, String speId, String speName2,String isPublish, Model model, String isQuery) {
        SysUser user = GlobalContext.getCurrentUser();
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

        if (StringUtil.isBlank(statusId)) {
            statusId = StuStatusEnum.Passing.getId();
        }
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        parMp.put("userName", userName);//用户名
        parMp.put("deptFlow",user.getDeptFlow()); //当前科室

        parMp.put("isPublish", isPublish);//发布状态

        parMp.put("statusId", statusId);//状态

//        if (StuStatusEnum.UnPassed.getId().equals(statusId)) {
            parMp.put("isBack", GlobalConstant.FLAG_N);//未被退回的
//        }

        if (StuStatusEnum.Passing.getId().equals(statusId)) {
            //科主任查询待审核，状态应为 待科室审核
            parMp.put("status2",StuStatusEnum.HeadPassing.getId());
        }

        PageHelper.startPage(currentPage, getPageSize(request));
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUserByHead(parMp);

        model.addAttribute("statusId", statusId);

        Map<String, ExtInfoForm> extInfoMap = new HashMap();
        if (stuUserLst != null && stuUserLst.size() > 0) {
            Map<String,StuHeadAuditStatus> statusMap = new HashMap<>();
            ExtInfoForm extInfo = null;
            for (StuUserExt tempUserExt : stuUserLst) {
                List<StuHeadAuditStatus> statuses = docSingupBiz.getHeadStatus(batchFlow,
                   tempUserExt.getResumeFlow(),null,StuStatusEnum.UnPassed.getId(),user.getDeptFlow(),tempUserExt.getUserFlow());
                if(statuses.size()>0 && !statuses.isEmpty()){
                    statusMap.put(tempUserExt.getResumeFlow(),statuses.get(0));
                    model.addAttribute("statusMap",statusMap);
                }
                extInfo = new ExtInfoForm();
                extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                if(StringUtil.isNotBlank(extInfo.getPassportNo()) && StringUtil.isBlank(extInfo.getIdNo())){
                    extInfo.setIdNo(extInfo.getPassportNo());
                }

                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }

            model.addAttribute("stuUserLst", stuUserLst);

            model.addAttribute("extInfoMap", extInfoMap);

            model.addAttribute("isShow",GlobalConstant.FLAG_Y);//显示 审核功能的按钮
        }
        return "zseyjxres/head/audit";
    }


    /**
     * 学员信息
     */
    @RequestMapping("/getsingupinfoaudit")
    public String getSingUpInfoForAudit(String userFlow, String batchId, String flag, String isShow,Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();

        SysUser user = this.userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(userFlow, batchId);
        if (null != doctorLst && doctorLst.size() > 0) {
            model.addAttribute("stuUser", doctorLst.get(0));//当前系统设置批次下的医师

            //查询 科室审核表的主键
            List<StuHeadAuditStatus> statuses = docSingupBiz.getHeadStatus(batchId,doctorLst.get(0).getResumeFlow(),null,null,currentUser.getDeptFlow(),userFlow);
            if(statuses.size()>0 && !statuses.isEmpty()){
                model.addAttribute("processFlow",statuses.get(0).getProcessFlow());
            }
            if(statuses.get(0).getStuStatusId().equals(StuStatusEnum.Passing.getId())){
                model.addAttribute("statusId",StuStatusEnum.Passing.getId());
            }
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

        model.addAttribute("isShow",isShow);
        model.addAttribute("batchId", batchId);
        return "zseyjxres/head/singupinfoforaudit";
    }


    /**
     * 用户信息审核
     */
    @RequestMapping(value = "/auditOption")
    @ResponseBody
    public synchronized String auditOption(String batchId,String resumeFlow,String processFlow, String userFlow, String reason, String statusId,Model model) {
       int result = docSingupBiz.updateHeadAndResume(batchId,resumeFlow,processFlow,userFlow,reason,statusId);

        if(result>GlobalConstant.ZERO_LINE){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 撤销审核操作
     */
    @RequestMapping(value = "/reAudit")
    @ResponseBody
    public synchronized String reAudit(String resumeFlow, String userFlow,String batchId) {
        SysUser user = GlobalContext.getCurrentUser();

        String statusId = StuStatusEnum.Passing.getId();

        int result = docSingupBiz.updateheadAudit(batchId,resumeFlow,userFlow,user.getDeptFlow(),statusId);
        if (result == GlobalConstant.ONE_LINE) {
            return GlobalConstant.OPERATE_SUCCESSED;
        } else {
            return GlobalConstant.OPERATE_FAIL;
        }
    }

}


