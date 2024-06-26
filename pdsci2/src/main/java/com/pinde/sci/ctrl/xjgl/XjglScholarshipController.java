package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjScholarshipBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.xjgl.ScholarshipTypeEnum;
import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.form.xjgl.XjScholarshipApplyForm;
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

/**
 * @author suncg
 */
@Controller
@RequestMapping("/xjgl/scholarship")
public class XjglScholarshipController extends GeneralController {

    private static String DS = "ds";
    private static String PYDW = "pydw";
    private static String FWH = "fwh";
    private static String SZK = "szk";
    private static String FIRST = "first";
    private static String SECOND = "second";
    private static String PASSED = "Passed";
    private static String UnPassed = "UnPassed";
    @Autowired
    private IXjScholarshipBiz scholarshipBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IUserBiz userBiz;

    /**
     * 奖助申请查询（学生）
     */
    @RequestMapping("/stuApplication")
    public String tutorApplication(NyjzScholarshipMain main,Integer currentPage,HttpServletRequest request,Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage, getPageSize(request));
        main.setUserFlow(sysUser.getUserFlow());
        List<NyjzScholarshipMain> mainList = scholarshipBiz.queryStuApplyList(main);
        model.addAttribute("mainList",mainList);
        return "xjgl/scholarship/stuApplication";
    }

    /**
     * 奖助申请界面（学生）
     */
    @RequestMapping("/editApplyInfo")
    public String editApplyInfo(String recordFlow,Model model) {
        SysUser sysUser = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
        model.addAttribute("sysUser",sysUser);
        EduUser eduUser =eduUserBiz.readEduUser(sysUser.getUserFlow());
        XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(eduUser.getContent());
        model.addAttribute("eduUser",eduUser);
        //档案到齐 且 学费缴清 方可提交申请标识
        model.addAttribute("applyFlag", GlobalConstant.FLAG_Y.equals(extInfoForm.getIsArchivesComplete())&&GlobalConstant.FLAG_Y.equals(extInfoForm.getIsFeeComplete())?GlobalConstant.FLAG_Y:GlobalConstant.FLAG_N);
        ResDoctor resDoctor =resDoctorBiz.findByFlow(sysUser.getUserFlow());
        model.addAttribute("resDoctor",resDoctor);
        if(StringUtil.isNotBlank(recordFlow)){
            NyjzScholarshipMain scholarship =scholarshipBiz.readScholarship(recordFlow);
            XjScholarshipApplyForm scholarshipForm = scholarshipBiz.parseExtInfoXml(scholarship.getContent());
            model.addAttribute("scholarship",scholarship);
            model.addAttribute("scholarshipForm",scholarshipForm);
        }
        return "xjgl/scholarship/editApplyInfo";
    }
    /**
     * 奖助申请(详情)
     */
    @RequestMapping("/detailInfo")
    public String detailInfo(String recordFlow,Model model) {
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            NyjzScholarshipMain scholarship = scholarshipBiz.readScholarship(recordFlow);
            XjScholarshipApplyForm scholarshipForm = scholarshipBiz.parseExtInfoXml(scholarship.getContent());
            model.addAttribute("scholarship",scholarship);
            model.addAttribute("scholarshipForm",scholarshipForm);
            userFlow = scholarship.getUserFlow();
        }
        SysUser sysUser = userBiz.readSysUser(userFlow);
        model.addAttribute("sysUser",sysUser);
        return "xjgl/scholarship/applyDetail";
    }
    /**
     * 保存奖助申请（学生）
     */
    @RequestMapping(value="/saveApplyInfo")
    @ResponseBody
    public String saveApplyInfo(NyjzScholarshipMain main, XjScholarshipApplyForm scholarship){
        int result = scholarshipBiz.saveApplyInfo(main,scholarship);
        if(result>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 取消申请
     */
    @RequestMapping(value="/delApplyInfo")
    @ResponseBody
    public String delApplyInfo(String recordFlow) {
        int num = scholarshipBiz.delApplyInfo(recordFlow);
        if (num > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 奖助申请审核(导师，培养单位、分委会、思政科)
     */
    @RequestMapping("/stuApplyAudit")
    public String stuApplyAudit(String role,NyjzScholarshipMain main,Integer currentPage,HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(DS.equals(role)){
            //导师一
            main.setDoctorFlow(sysUser.getUserFlow());
            //导师二
            main.setSecondDoctorFlow(sysUser.getUserFlow());
            model.addAttribute("doctorFlow",sysUser.getUserFlow());
        }else if(PYDW.equals(role)){
            main.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                main.setFwhOrgFlow(sysUser.getDeptFlow());
            }
            if(SZK.equals(role)){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }

        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NyjzScholarshipMain> mainList = scholarshipBiz.queryStuApplyList(main);
        model.addAttribute("mainList",mainList);
        return "xjgl/scholarship/applyInfoAudit";
    }

    /**
     * 奖助申请审核界面
     */
    @RequestMapping("/auditOption")
    public String auditOption() {
        return "xjgl/scholarship/auditOption";
    }

    /**
     * 奖助申请审核操作
     */
    @RequestMapping(value="/saveAuditOption")
    @ResponseBody
    public String saveAuditOption(String role,String recordFlow,String auditFlag,String doctorFlag,String advice,String applyTypeId){
        NyjzScholarshipMain main = new NyjzScholarshipMain();
        main.setRecordFlow(recordFlow);
        if(DS.equals(role)){
            NyjzScholarshipMain exitMain = scholarshipBiz.readScholarship(recordFlow);
            String auditStatusId = "";
            if(FIRST.equals(doctorFlag)){
                main.setDoctorAuditStatusId(auditFlag);
                main.setDoctorAuditStatusName(PASSED.equals(auditFlag)?"审核通过":"审核不通过");
                main.setDoctorAuditAdvice(advice);
                main.setDoctorAuditTime(DateUtil.getCurrentTime());
                //导师二审核状态（不存在则默认审核通过）
                auditStatusId = StringUtil.isBlank(exitMain.getSecondDoctorFlow())?PASSED:exitMain.getSecondAuditStatusId();
            }else if(SECOND.equals(doctorFlag)){
                main.setSecondAuditStatusId(auditFlag);
                main.setSecondAuditStatusName(PASSED.equals(auditFlag)?"审核通过":"审核不通过");
                main.setSecondAuditAdvice(advice);
                main.setSecondAuditTime(DateUtil.getCurrentTime());
                //导师一审核状态（不存在则默认审核通过）
                auditStatusId = StringUtil.isBlank(exitMain.getDoctorFlow())?PASSED:exitMain.getDoctorAuditStatusId();
            }
            //需要审核的导师都已审核通过，则初始化培养单位审核状态
            if(PASSED.equals(auditFlag) && PASSED.equals(auditStatusId)){
                main.setPydwAuditStatusId("Passing");
                main.setPydwAuditStatusName("待审核");
            }
        }else if(PYDW.equals(role)){
            main.setPydwAuditStatusId(auditFlag);
            main.setPydwAuditStatusName(PASSED.equals(auditFlag)?"审核通过":"审核不通过");
            main.setPydwAuditAdvice(advice);
            main.setPydwAuditTime(DateUtil.getCurrentTime());
            if(PASSED.equals(auditFlag)){
                //国家奖学金，国家奖学金补充计划，学业奖学金 需分委会审核
                if(ScholarshipTypeEnum.Gjjxj.getId().equals(applyTypeId)||ScholarshipTypeEnum.Gjjxjbcjh.getId().equals(applyTypeId)||ScholarshipTypeEnum.Xyjxj.getId().equals(applyTypeId)){
                    main.setFwhAuditStatusId("Passing");
                    main.setFwhAuditStatusName("待审核");
                }
                //优秀研究生，优秀研究生骨干，优秀毕业研究生 思政科直接审核
                if(ScholarshipTypeEnum.Yxyjs.getId().equals(applyTypeId)||ScholarshipTypeEnum.Yxyjsgg.getId().equals(applyTypeId)||ScholarshipTypeEnum.Yxbyyjs.getId().equals(applyTypeId)){
                    main.setSzkAuditStatusId("Passing");
                    main.setSzkAuditStatusName("待审核");
                }
            }
        }else if(FWH.equals(role)){
            main.setFwhAuditStatusId(auditFlag);
            main.setFwhAuditStatusName(PASSED.equals(auditFlag)?"审核通过":"审核不通过");
            main.setFwhAuditAdvice(advice);
            main.setFwhAuditTime(DateUtil.getCurrentTime());
            if(PASSED.equals(auditFlag)){
                main.setSzkAuditStatusId("Passing");
                main.setSzkAuditStatusName("待审核");
            }
        }else if(SZK.equals(role)){
            main.setSzkOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            main.setSzkAuditStatusId(auditFlag);
            main.setSzkAuditStatusName(PASSED.equals(auditFlag)?"审核通过":"审核不通过");
            main.setSzkAuditAdvice(advice);
            main.setSzkAuditTime(DateUtil.getCurrentTime());
        }
        int result = scholarshipBiz.updateAuditOption(main);
        if(result>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 导出奖助申请模板
     */
    @RequestMapping("/exportWord")
    public void exportWord(String recordFlow,HttpServletRequest request,HttpServletResponse response) throws Exception {
        NyjzScholarshipMain main =scholarshipBiz.readScholarship(recordFlow);
        XjScholarshipApplyForm form = scholarshipBiz.parseExtInfoXml(main.getContent());
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String name = "国家助学金.docx";
        String path = "/jsp/xjgl/scholarship/gjzxjTemp.docx";
        if (ScholarshipTypeEnum.Zggw.getId().equals(main.getApplyTypeId())) {
            name = "助管岗位.docx";
            path = "/jsp/xjgl/scholarship/zggwTemp.docx";
        }else if(ScholarshipTypeEnum.Yxyjs.getId().equals(main.getApplyTypeId())){
            name = "优秀研究生.docx";
            path = "/jsp/xjgl/scholarship/yxyjsTemp.docx";
        }else if(ScholarshipTypeEnum.Yxyjsgg.getId().equals(main.getApplyTypeId())){
            name = "优秀研究生骨干.docx";
            path = "/jsp/xjgl/scholarship/yxyjsggTemp.docx";
        }else if(ScholarshipTypeEnum.Yxbyyjs.getId().equals(main.getApplyTypeId())){
            name = "优秀毕业研究生.docx";
            path = "/jsp/xjgl/scholarship/yxbyyjsTemp.docx";
        }else if(ScholarshipTypeEnum.Gjjxj.getId().equals(main.getApplyTypeId())){
            name = "国家奖学金.docx";
            path = "/jsp/xjgl/scholarship/gjjxjTemp.docx";
        }else if(ScholarshipTypeEnum.Gjjxjbcjh.getId().equals(main.getApplyTypeId())){
            name = "国家奖学金补充计划.docx";
            path = "/jsp/xjgl/scholarship/gjjxjbcjhTemp.docx";
        }else if(ScholarshipTypeEnum.Xyjxj.getId().equals(main.getApplyTypeId())){
            name = "学业奖学金.docx";
            path = "/jsp/xjgl/scholarship/xyjxjTemp.docx";
        }
        Map dataMap = new HashMap();
        dataMap.put("userName", form.getUserName());
        dataMap.put("userBirthday", form.getUserBirthday());
        dataMap.put("nativePlace", form.getNativePlace());
        dataMap.put("politicsStatusName", form.getPoliticsStatusName());
        dataMap.put("pydwOrgName", form.getPydwOrgName());
        dataMap.put("majorName", form.getMajorName());
        dataMap.put("tutorName", form.getTutorName());
        dataMap.put("sid", form.getSid());
        dataMap.put("className", form.getClassName());
        dataMap.put("graduateSchool", form.getGraduateSchool());
        dataMap.put("userPhone", form.getUserPhone());
        dataMap.put("userQq", form.getUserQq());
        dataMap.put("userEmail", form.getUserEmail());
        dataMap.put("specialty", form.getSpecialty());
        dataMap.put("jzfdyDesc", form.getJzfdyDesc());
        dataMap.put("yjsggDesc", form.getYjsggDesc());
        dataMap.put("xszlDesc", form.getXszlDesc());
        dataMap.put("resumeDesc", form.getResumeDesc());
        dataMap.put("awardDesc", form.getAwardDesc());
        dataMap.put("memo", form.getMemo());
        dataMap.put("sexName", form.getSexName());
        dataMap.put("nationName", form.getNationName());
        dataMap.put("periodYear", form.getPeriodYear());
        dataMap.put("mainDeeds", form.getMainDeeds());
        dataMap.put("awardExperience", form.getAwardExperience());
        dataMap.put("backbonePosition", form.getBackbonePosition());
        dataMap.put("baseUnit", form.getBaseUnit());
        dataMap.put("studyDegree", form.getStudyDegree());
        dataMap.put("schoolSystem", form.getSchoolSystem());
        dataMap.put("studyPeriod", form.getStudyPeriod());
        dataMap.put("cardId", form.getCardId());
        dataMap.put("applyReason", form.getApplyReason());
        dataMap.put("applyLevel", main.getApplyLevel());
        if(StringUtil.isNotBlank(main.getDoctorFlow())&&StringUtil.isNotBlank(main.getSecondDoctorFlow())){
            main.setDoctorAuditAdvice("导师一："+main.getDoctorAuditAdvice()+"  导师二："+main.getSecondAuditAdvice());
        }else if(StringUtil.isBlank(main.getDoctorFlow())){
            main.setDoctorAuditAdvice(main.getSecondAuditAdvice());
        }
        dataMap.put("doctorAuditAdvice", main.getDoctorAuditAdvice());
        dataMap.put("pydwAuditAdvice", main.getPydwAuditAdvice());
        dataMap.put("fwhAuditAdvice", main.getFwhAuditAdvice());
        dataMap.put("szkAuditAdvice", main.getSzkAuditAdvice());
        ServletContext context = request.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }

    /**
     * 导出奖助申请记录
     */
    @RequestMapping(value="/expApplyInfo")
    public void expApplyInfo(String role,NyjzScholarshipMain main,HttpServletResponse response) throws Exception {
        String[] titles = new String[]{
                "studentId:学号",
                "userName:姓名",
                "applyTime:申请时间",
                "applyTypeName:申请奖助类型",
                "doctorAuditStatusName:导师一",
                "secondAuditStatusName:导师二",
                "pydwAuditStatusName:培养单位",
                "fwhAuditStatusName:分委会",
                "szkAuditStatusName:学校"
        };
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(DS.equals(role)){
            //导师一
            main.setDoctorFlow(sysUser.getUserFlow());
            //导师二
            main.setSecondDoctorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)){
            main.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            main.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NyjzScholarshipMain> mainList = scholarshipBiz.queryStuApplyList(main);
        String fileName = "奖助申请.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, mainList, response.getOutputStream(),new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 奖助申请批量审核界面
     */
    @RequestMapping("/batchAudit")
    public String batchAudit(String role,String remarkFlag,String recordLst,String auditFlag,String docFlagLst,Model model) {
        model.addAttribute("role",role);
        model.addAttribute("remarkFlag",remarkFlag);
        model.addAttribute("recordLst",recordLst);
        model.addAttribute("auditFlag",auditFlag);
        model.addAttribute("docFlagLst",docFlagLst);
        if(StringUtil.isNotBlank(recordLst) && recordLst.split(",").length==1){
            NyjzScholarshipMain main = scholarshipBiz.readScholarship(recordLst);
            model.addAttribute("main",main);
        }
        return "xjgl/scholarship/batchAudit";
    }

    /**
     * 奖助申请批量审核保存操作
     * remarkFlag 学业奖学金并且由分委会或思政科 审核标识
     * auditFlag 空值则为批量审核
     */
    @RequestMapping("/batchAuditOpt")
    @ResponseBody
    public String batchAuditOpt(String role,String remarkFlag,String recordLst,String auditFlag,String docFlagLst,String applyLevel,String workDay,String advice){
        List<String> list = Arrays.asList(recordLst.split(","));
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            NyjzScholarshipMain main = new NyjzScholarshipMain();
            main.setRecordFlow(list.get(i));
            if(GlobalConstant.FLAG_Y.equals(remarkFlag)){
                main.setApplyLevel(applyLevel);
                main.setWorkDay(workDay);
                if(FWH.equals(role)){
                    main.setFwhAuditStatusId(StringUtil.isBlank(auditFlag)?PASSED:auditFlag);
                    main.setFwhAuditStatusName(UnPassed.equals(auditFlag)?"审核不通过":"审核通过");
                    main.setFwhAuditAdvice(advice);
                    main.setFwhAuditTime(DateUtil.getCurrentTime());
                    if(PASSED.equals(main.getFwhAuditStatusId())){
                        main.setSzkAuditStatusId("Passing");
                        main.setSzkAuditStatusName("待审核");
                    }
                }else if(SZK.equals(role)){
                    main.setSzkOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                    main.setSzkAuditStatusId(StringUtil.isBlank(auditFlag)?PASSED:auditFlag);
                    main.setSzkAuditStatusName(UnPassed.equals(auditFlag)?"审核不通过":"审核通过");
                    main.setSzkAuditAdvice(advice);
                    main.setSzkAuditTime(DateUtil.getCurrentTime());
                }
            }else{
                if(DS.equals(role) || PYDW.equals(role)){
                    NyjzScholarshipMain exitMain = scholarshipBiz.readScholarship(list.get(i));
                    if(DS.equals(role)){
                        String auditStatusId = "";
                        List<String> list2 = Arrays.asList(docFlagLst.split(","));
                        if(FIRST.equals(list2.get(i))){
                            main.setDoctorAuditStatusId(PASSED);
                            main.setDoctorAuditStatusName("审核通过");
                            main.setDoctorAuditAdvice(advice);
                            main.setDoctorAuditTime(DateUtil.getCurrentTime());
                            auditStatusId = StringUtil.isBlank(exitMain.getSecondDoctorFlow())?PASSED:exitMain.getSecondAuditStatusId();
                        }else if(SECOND.equals(list2.get(i))){
                            main.setSecondAuditStatusId(PASSED);
                            main.setSecondAuditStatusName("审核通过");
                            main.setSecondAuditAdvice(advice);
                            main.setSecondAuditTime(DateUtil.getCurrentTime());
                            auditStatusId = StringUtil.isBlank(exitMain.getDoctorFlow())?PASSED:exitMain.getDoctorAuditStatusId();
                        }
                        if(PASSED.equals(auditStatusId)){
                            main.setPydwAuditStatusId("Passing");
                            main.setPydwAuditStatusName("待审核");
                        }
                    }else{
                        main.setPydwAuditStatusId(PASSED);
                        main.setPydwAuditStatusName("审核通过");
                        main.setPydwAuditAdvice(advice);
                        main.setPydwAuditTime(DateUtil.getCurrentTime());
                        if(ScholarshipTypeEnum.Yxyjs.getId().equals(exitMain.getApplyTypeId()) || ScholarshipTypeEnum.Yxyjsgg.getId().equals(exitMain.getApplyTypeId())
                                || ScholarshipTypeEnum.Yxbyyjs.getId().equals(exitMain.getApplyTypeId())){
                            main.setSzkAuditStatusId("Passing");
                            main.setSzkAuditStatusName("待审核");
                        }else{
                            main.setFwhAuditStatusId("Passing");
                            main.setFwhAuditStatusName("待审核");
                        }
                    }
                }else if(FWH.equals(role)){
                    main.setFwhAuditStatusId(PASSED);
                    main.setFwhAuditStatusName("审核通过");
                    main.setFwhAuditAdvice(advice);
                    main.setFwhAuditTime(DateUtil.getCurrentTime());
                    main.setSzkAuditStatusId("Passing");
                    main.setSzkAuditStatusName("待审核");
                }else if(SZK.equals(role)){
                    main.setSzkOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                    main.setSzkAuditStatusId(PASSED);
                    main.setSzkAuditStatusName("审核通过");
                    main.setSzkAuditAdvice(advice);
                    main.setSzkAuditTime(DateUtil.getCurrentTime());
                }
            }
            num += scholarshipBiz.updateAuditOption(main);
        }
        if (num > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 奖助申请退回操作
     */
    @RequestMapping(value="/backOption")
    @ResponseBody
    public String backOption(String role,String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)){
            NyjzScholarshipMain main = scholarshipBiz.readScholarship(recordFlow);
            //清空思政科审核节点
            main.setSzkAuditStatusId("");
            main.setSzkAuditStatusName("");
            main.setSzkAuditAdvice("");
            main.setSzkAuditTime("");
            if(SZK.equals(role)){
                if(ScholarshipTypeEnum.Yxyjs.getId().equals(main.getApplyTypeId()) || ScholarshipTypeEnum.Yxyjsgg.getId().equals(main.getApplyTypeId())
                        ||ScholarshipTypeEnum.Yxbyyjs.getId().equals(main.getApplyTypeId())){
                    main.setPydwAuditStatusId("Passing");
                    main.setPydwAuditStatusName("待审核");
                    main.setPydwAuditAdvice("");
                    main.setPydwAuditTime("");
                }else{
                    main.setFwhAuditStatusId("Passing");
                    main.setFwhAuditStatusName("待审核");
                    main.setFwhAuditAdvice("");
                    main.setFwhAuditTime("");
                }
            }else if(FWH.equals(role)){
                //清空分委会审核节点
                main.setFwhAuditStatusId("");
                main.setFwhAuditStatusName("");
                main.setFwhAuditAdvice("");
                main.setFwhAuditTime("");
                main.setPydwAuditStatusId("Passing");
                main.setPydwAuditStatusName("待审核");
                main.setPydwAuditAdvice("");
                main.setPydwAuditTime("");
            }else if(PYDW.equals(role)){
                //清空分委会审核节点
                main.setFwhAuditStatusId("");
                main.setFwhAuditStatusName("");
                main.setFwhAuditAdvice("");
                main.setFwhAuditTime("");
                //清空培养单位审核节点
                main.setPydwAuditStatusId("");
                main.setPydwAuditStatusName("");
                main.setPydwAuditAdvice("");
                main.setPydwAuditTime("");
                if(StringUtil.isNotBlank(main.getDoctorFlow())){
                    main.setDoctorAuditStatusId("Passing");
                    main.setDoctorAuditStatusName("待审核");
                    main.setDoctorAuditAdvice("");
                    main.setDoctorAuditTime("");
                }
                if(StringUtil.isNotBlank(main.getSecondDoctorFlow())){
                    main.setSecondAuditStatusId("Passing");
                    main.setSecondAuditStatusName("待审核");
                    main.setSecondAuditAdvice("");
                    main.setSecondAuditTime("");
                }
            }
            int result = scholarshipBiz.updateAuditOption(main);
            if(result>0){
                return GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 国家/学业 奖学金 审核通过后打印页面
     */
    @RequestMapping(value="/printAward")
    public String printAward(String recordFlow,Model model) throws Exception {
        if(StringUtil.isNotBlank(recordFlow)){
            NyjzScholarshipMain scholarship = scholarshipBiz.readScholarship(recordFlow);
            XjScholarshipApplyForm scholarshipForm = scholarshipBiz.parseExtInfoXml(scholarship.getContent());
            model.addAttribute("scholarship",scholarship);
            model.addAttribute("scholarshipForm",scholarshipForm);
            if(ScholarshipTypeEnum.Gjjxj.getId().equals(scholarship.getApplyTypeId())){
                return "xjgl/scholarship/countryAward";
            }
        }
        return "xjgl/scholarship/studyAward";
    }
}
