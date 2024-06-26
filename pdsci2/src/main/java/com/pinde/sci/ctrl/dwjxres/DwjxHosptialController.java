package com.pinde.sci.ctrl.dwjxres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.freemaker.HtmlGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.*;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
import com.pinde.sci.biz.dwjxres.IStuDoctorInfoBiz;
import com.pinde.sci.biz.dwjxres.IStuFurtherStudyRegistBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.SysDictMapper;
import com.pinde.sci.enums.dwjxres.StuRoleEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.dwjxres.ExportStuUserInfo;
import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.WorkResumeForm;
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

@RequestMapping("/dwjxres/hospital")
@Controller
public class DwjxHosptialController extends GeneralController {

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
    private SysDictMapper sysDictMapper;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IStuFurtherStudyRegistBiz furtherStudyRegistBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IStuDoctorInfoBiz stuDoctorInfoBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IMsgBiz msgBiz;


    @RequestMapping("/home")
    public String home(Integer currentPage, Model model, HttpServletRequest request) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, getPageSize(request));
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("messages", infos);
        Map<String, Object> mp = new HashMap<String, Object>();
        Map<String, Object> mp2 = new HashMap<String, Object>();
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
        List<StuUserExt> dsh = stuUserBiz.searchUser(mp);
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
        return "dwjxres/hospital/index";
    }

    /**
     * 报名审核
     */
    @RequestMapping(value = "/audit")
    public String audit(String statusId, Integer currentPage, String userName, String batchFlow, String speId, String isPublish, Model model, String isQuery) {

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
        if(statusId.equals(StuStatusEnum.Passing.getId())){
            Map<String, Object> mp = new HashMap<String, Object>();
            mp.put("batchFlow", batchFlow);//系统默认批次
            mp.put("statusId", StuStatusEnum.Passing.getId());
            mp.put("userName", userName);//用户名
            mp.put("speId", speId);//进修专业
            mp.put("isPublish", isPublish);//发布状态
            PageHelper.startPage(currentPage, 10);
            List<StuUserExt> dsh = stuUserBiz.searchUser(mp);
            model.addAttribute("stuUserLst",dsh);
            model.addAttribute("statusId", statusId);
            Map<String, ExtInfoForm> extInfoMap = new HashMap();
            if (dsh != null && dsh.size() > 0) {
                ExtInfoForm extInfo = null;
                for (StuUserExt tempUserExt : dsh) {
                    extInfo = new ExtInfoForm();
                    extInfo = docSingupBiz.parseExtInfoXml(tempUserExt.getResumeInfo());
                    extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
                }
                model.addAttribute("extInfoMap", extInfoMap);
            }
            return "dwjxres/hospital/audit";
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
        } else if(!"Back".equals(statusId)){
            parMp.put("statusId", statusId);//状态
        }
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业
        parMp.put("isPublish", isPublish);//发布状态
        if (StuStatusEnum.UnPassed.getId().equals(statusId)) {
            parMp.put("isBack", GlobalConstant.FLAG_N);//未被退回的
        }else if("Back".equals(statusId)){//退回标识
            parMp.put("isBack", GlobalConstant.FLAG_Y);
        }
        PageHelper.startPage(currentPage, 10);
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("statusId", statusId);
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
        return "dwjxres/hospital/audit";
    }

    /**
     * 学员录取
     */
    @RequestMapping(value = "/_recruitDoctor")
    public String _recruitDoctor(String statusId, Integer currentPage, String userName, String batchFlow, String speId, String isPublish, Model model, String isQuery,HttpServletRequest request) {

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
        parMp.put("speId", speId);//进修专业
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
                extInfoMap.put(tempUserExt.getResumeFlow(), extInfo);
            }
            model.addAttribute("extInfoMap", extInfoMap);
        }
        return "dwjxres/hospital/_recruitDoctor";
    }

    /**
     * 用户信息审核
     */
    @RequestMapping(value = "/auditOption")
    @ResponseBody
    public String auditOption(String resumeFlow, String userFlow, String reason, String statusId,String titleTypeName, Model model) {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("resumeFlow", resumeFlow);
        mp.put("userFlow", userFlow);
        mp.put("reason", reason);
        mp.put("statusId", statusId);
        mp.put("titleTypeName",titleTypeName);
        this.docSingupBiz.auditOption(mp);
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
        return "dwjxres/hospital/showPublish";
    }


    /**
     * 在线预览录取通知书
     */
    @RequestMapping(value = "/recruitNoticeShow")
    public String recruitNoticeShow(String resumeFlow, String templeteName, HttpServletRequest req, HttpServletResponse res,Model model) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        StuUserExt stuUser = this.stuUserResumeBiz.searchAdmitedInfo(resumeFlow);
        if (null != stuUser) {
            dataMap.put("userName", stuUser.getSysUser().getUserName());
            dataMap.put("speName", stuUser.getSpeName());//进修专业
            dataMap.put("stuBatName", stuUser.getStuBatName());//进修批次
            dataMap.put("stuTimeName", stuUser.getStuTimeName());//进修时间
            String stuTimeId = "";
            if(stuUser.getStuTimeId().equals("短期培训")){
                stuTimeId = "3";
            }else{
                stuTimeId = stuUser.getStuTimeId();
            }
            String date = DateUtil.addMonth(stuUser.getStuBatName().replace("年", "-").replace("月", ""), Integer.valueOf(stuTimeId));
            dataMap.put("stuBatEndName", formate(date));//进修批次+进修时间
            dataMap.put("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            dataMap.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            dataMap.put("trainFee", (stuUser.getStuBatch().getMonthFee()).intValue() * Integer.valueOf(stuTimeId) + "");//进修培养费
        }

        String template = "";
        if ("cd".equals(templeteName)) {
            template = "dwjxres/templete.html";//进修申请表模板
        } else if ("xz".equals(templeteName)) {
            template = "dwjxres/templeteXZ.html";//进修申请表模板
        }

        HtmlGenerator htmlGenerator = new HtmlGenerator();
        String htmlData = htmlGenerator.generate(template, dataMap);
        model.addAttribute("htmlData",htmlData);

        return "dwjxres/hospital/recruitNoticeShow";
 /*       //html转pdf
        final String fileName =  "进修申请表";
        String outputFileClass = ResourceLoader.getPath("");
        String outputFile = new File(outputFileClass)
                + "/load/" + fileName + ".pdf" ;
        File file = new File(outputFile);
        //root 储存的数据
        final Map<String,Object> root=new HashMap<String,Object>();
        if (null != stuUser) {
            root.put("userName", stuUser.getSysUser().getUserName());
            root.put("speName", stuUser.getSpeName());//进修专业
            root.put("stuBatName", stuUser.getStuBatName());//进修批次
            root.put("stuTimeName", stuUser.getStuTimeName());//进修时间
            String date = DateUtil.addMonth(stuUser.getStuBatName().replace("年", "-").replace("月", ""), Integer.valueOf(stuUser.getStuTimeId()));
            root.put("stuBatEndName", formate(date));//进修批次+进修时间
            root.put("batchRegDate", formate(stuUser.getStuBatch().getBatchRegDate()));//报到时间
            root.put("recruitTime", formate(stuUser.getModifyTime()));//录取时间
            root.put("trainFee", (stuUser.getStuBatch().getMonthFee()).intValue() * Integer.valueOf(stuUser.getStuTimeId()) + "");//进修培养费
        }
        // 模板数据
        DocumentVo vo = new DocumentVo() {
            @Override
            public String findPrimaryKey() {
                return fileName;
            }

            @Override
            public Map<String, Object> fillDataMap() {
                return root;
            }
        };
        String template = "dwjxres/templete.html";
        PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
        // 生成pdf
        pdfGenerator.generate(template, vo, outputFile);
        pubFileBiz.downFile(file,res);*/


//
//        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
//        String path = "";
//        if ("cd".equals(templeteName)) {
//            path = "/jsp/dwjxres/print/admissionNoticeTemeplete.docx";//进修申请表模板
//        } else if ("xz".equals(templeteName)) {
//            path = "/jsp/dwjxres/print/admissionNoticeXZTemeplete.docx";//进修申请表模板
//        }
//        ServletContext context = req.getServletContext();
//        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
//        if (temeplete != null) {
//            String name = "录取通知书.docx";
//            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
//            res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//            ServletOutputStream out = res.getOutputStream();

//            File file = new File("d://docx/录取通知书.docx");
//            OutputStream out = new FileOutputStream(file);
//            temeplete.save(out);
//            out.flush();
//            String docxFilePath = "d://docx/录取通知书.docx";
//            String pdfPath="d://docx/录取通知书.pdf";


//            String htmlPath = "d://docx/录取通知书.html";
//            Docx4jUtil.convertDocxToPDF(docxFilePath,pdfPath);

//        }

    }



    /**
     * 发布录取通知
     */
    @RequestMapping(value = "/publish")
    @ResponseBody
    public String publish(String resumeFlow) {

        StuUserResume stuUser = stuUserResumeBiz.getStuUserByKey(resumeFlow);
        stuUser.setIsPublish(GlobalConstant.FLAG_Y);
        int count = stuUserResumeBiz.save(stuUser);
        if (count == 0) {
            return "发布失败";
        }
        SysUser user = userBiz.readSysUser(stuUser.getUserFlow());
        this.msgBiz.sendSMS(user.getUserPhone(),"dwjxres10004",user.getUserName(),stuUser.getResumeFlow(),"STU_USER_RESUME");
        return "发布成功";
    }

    /**
     * 退回操作
     */
    @RequestMapping(value = "/returnInfo")
    @ResponseBody
    public String returnInfo(String resumeFlow, String reason, String userFlow,String titleTypeName) {
        int result = docSingupBiz.returnInfo(resumeFlow, reason, userFlow,titleTypeName);
        if (result == 1) {
            return GlobalConstant.OPERATE_SUCCESSED;
        } else {
            return GlobalConstant.OPERATE_FAIL;
        }
    }

    /**
     * 导出录取人员名单
     *
     * @param userName
     * @param batchFlow
     * @param speId
     * @param isPublish
     * @param res
     * @throws Exception
     */
    @RequestMapping("/exportAuditRecruitted")
    public void exportRecruittedDoctor(String userName, String batchFlow, String speId, String isPublish, HttpServletResponse res) throws Exception {
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
        parMp.put("speId", speId);//用户名
        parMp.put("isPublish", isPublish);//用户名
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        String fileName = "录取学员名单.xls";
        createExcle(res, fileName, stuUserLst, "audit");
    }

    /**
     * 导出审核通过数据
     */
    @RequestMapping("/exportAuditPassed")
    public void exportPassedDoctor(String userName, String batchFlow, String speId, String isPublish, HttpServletResponse res) throws Exception {
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
        parMp.put("speId", speId);//用户名
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
            eui.setStuTimeName(ext.getStuTimeName());
            eui.setSpeName(ext.getSpeName());
            eui.setBatchRegDate(ext.getStuBatch().getBatchRegDate());
            eui.setTeacherName(ext.getTeacherName());
            eui.setIsGraduation(GlobalConstant.FLAG_Y.equals(ext.getIsGraduation()) ? "是" : "否");
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
                    "batchRegDate:报到时间"
            };
        } else {
            titles = new String[]{
                    "userName:姓名",
                    "schoolSpeName:毕业专业",
                    "stuTimeName:进修时间",
                    "speName:进修专业",
                    "batchRegDate:报到时间",
                    "teacherName:带教老师",
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
    public String recruitDoctor(String status, Integer currentPage, String userName, String batchFlow, String speId, Model model, String isQuery) {
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
        PageHelper.startPage(currentPage, 10);
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
        parMp.put("speId", speId);//进修专业
        parMp.put("isPublish", GlobalConstant.FLAG_Y);
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
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
        return "dwjxres/hospital/doctorrecruit";
    }

    /**
     * 学员结业
     */
    @RequestMapping("/graduationDoctor")
    public String graduationDoctor(String status, Integer currentPage, String userName, String batchFlow, String speId, Model model, String isQuery) {
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
        PageHelper.startPage(currentPage, 10);
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
        parMp.put("speId", speId);//进修专业
        parMp.put("haveFile", "Y");//上传了结业鉴定表
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
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
        return "dwjxres/hospital/docGraduation";
    }

    /**
     * 不录取的操作
     */
    @RequestMapping("/notrecruit")
    @ResponseBody
    public String notrecruit(String resumeFlow, String statusId,String titleTypeName) {
        statusId = "Y".equals(statusId) ? "Admited" : "UnAdmitd";
        this.docSingupBiz.changeRecruit(resumeFlow, statusId,titleTypeName);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 导出已录取的学员
     */
    @RequestMapping("/exportAdmitPassed")
    public void exportRecruitDoctor(String batchFlow, String userName, String speId, HttpServletResponse response) throws Exception {
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
//		parMp.put("statusId", StuStatusEnum.Admited.getId());//状态
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("status", status);//状态
        parMp.put("userName", userName);//用户名
        parMp.put("speId", speId);//进修专业id
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
            dataMap.put("nationName", extInfo.getNationName());
            dataMap.put("titleName", stuUser.getTitleName());
            dataMap.put("deptName", stuUser.getDeptName());
            dataMap.put("jobYear", stuUser.getJobYear());
            dataMap.put("postName", stuUser.getPostName());
            dataMap.put("userPhone", extInfo.getUserPhone());
//			dataMap.put("userEmail",extInfo.getUserEmail());
            dataMap.put("certifiedNo", extInfo.getCertifiedNo());
            dataMap.put("certifiedTypeName", stuUser.getCertifiedTypeName());
            dataMap.put("maxEduName", stuUser.getMaxEduName());
            dataMap.put("maxEduDate", stuUser.getMaxEduBdate() + "至" + stuUser.getMaxEduEdate());
            dataMap.put("schoolName", stuUser.getSchoolName());
            dataMap.put("schoolSpeName", stuUser.getSchoolSpeName());
            dataMap.put("isComputer", "Y".equals(stuUser.getIsComputer()) ? "是" : "否");
            dataMap.put("sendComInfo", stuUser.getSendComName() + " " + stuUser.getHospitalLevelName() + " " + stuUser.getSendComAddress());
            dataMap.put("speName", stuUser.getSpeName());
            dataMap.put("identificationNumber", stuUser.getIdentificationNumber());
            dataMap.put("stuTimeId",stuUser.getStuTimeId());
            dataMap.put("stuTimeName", stuUser.getStuTimeName());
            dataMap.put("stuBatName", stuUser.getStuBatName());
            dataMap.put("clotherSizeName", stuUser.getClotherSizeName());
            dataMap.put("isPutup", stuUser.getIsPutup());
            dataMap.put("titleTypeName",DictTypeEnum.TitleGenre.getDictNameById( extInfo.getTitleTypeId()));
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
            dataMap.put("templeteName", java.net.URLDecoder.decode(templeteName, "UTF-8"));
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
        String path = null;
        if ("doctor".equals(printFlag)) {
            if(dataMap.get("titleTypeName").equals("护士") || !dataMap.get("stuTimeId").equals("短期培训")) {
                path = "/jsp/dwjxres/print/docStudyApplTemeplete.docx";//暂存或提交后 学员打印模板
            }else {
                path = "/jsp/dwjxres/print/docStudyApplTemeplete2.docx";
            }
        } else {
            if(!dataMap.get("titleTypeName").equals("护士") && dataMap.get("stuTimeId").equals("短期培训")) {
                path = "/jsp/dwjxres/print/studyApplicationTemeplete2.docx";//审核通过后 医院打印模板
            }else {
                path = "/jsp/dwjxres/print/studyApplicationTemeplete.docx";
            }
        }
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "进修申请表.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
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
            String stuTimeId = "";
            if(stuUser.getStuTimeId().equals("短期培训")){
                stuTimeId = "3";
            }else {
                stuTimeId = stuUser.getStuTimeId();
            }
            //开始时间加1个自然月
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(stuUser.getStuBatch().getBatchRegDate());
            c.setTime(date);
            c.add(Calendar.MONTH, Integer.valueOf(stuTimeId));
            String NextEndDate = sdf.format(c.getTime());
            dataMap.put("endDate", formate(NextEndDate));//报到时间+进修时间
            dataMap.put("sendComName", stuUser.getSendComName());//选送单位
        }
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String path = "/jsp/dwjxres/print/inSpeNoticeTemeplete.docx";//进修申请表模板
        ServletContext context = req.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            String name = "入科通知.docx";
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
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
        paramMap.put("roleId",StuRoleEnum.Teacher.getId());
        List<SysUser> teas = stuUserBiz.getTeas(paramMap);
        model.addAttribute("resumeFlow", resumeFlow);
        model.addAttribute("teas", teas);
        return "dwjxres/hospital/registerTeacher";
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
        return "dwjxres/hospital/graduation";
    }

    @RequestMapping(value = "/saveGraduation", method = {RequestMethod.POST})
    @ResponseBody
    public String saveGraduation(String resumeFlow, String comment, String agree, MultipartFile file) {

        Map<String, Object> map = new HashMap<String, Object>();
        ExtInfoForm extInfo = docSingupBiz.parseExtInfoXml(stuUserBiz.getStuUserByKey(resumeFlow).getResumeInfo());
        map.put("extInfo", extInfo);
        map.put("resumeFlow", resumeFlow);
        map.put("comment", comment);
        map.put("agree", agree);
        this.stuUserBiz.saveGraduation(map, file,"Y");
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
        return "dwjxres/hospital/furtherStudyRegist";
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
        return "dwjxres/hospital/editFurtherStudy";
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
        return "dwjxres/hospital/furtherStudyList";
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
        return "dwjxres/hospital/orgRecruitInfo";
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
        return "dwjxres/hospital/queryStu/queryStuMain";
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
        String path1 = "/jsp/dwjxres/print/batchStudent/title.docx";//头模板
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
                    path1 = "/jsp/dwjxres/print/batchStudent/info.docx";//模板
                    context = req.getServletContext();
                    temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                    addTemplates.add(temeplete1);
                    objMap = new HashMap<>();
                }
            }
            if (objMap != null && objMap.size() > 0 && objMap.size() <= 4) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/dwjxres/print/batchStudent/info2.docx";//模板
                context = req.getServletContext();
                temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), objMap, watermark, true);
                addTemplates.add(temeplete1);
            } else if (objMap != null && objMap.size() > 4) {
                temeplete1 = new WordprocessingMLPackage();
                path1 = "/jsp/dwjxres/print/batchStudent/info.docx";//模板
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
            res.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
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
                               Integer currentPage, String userName, String batchFlow, Model model, String isQuery) {
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
        return "dwjxres/hospital/queryStu/queryStuList";
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
     *学员查询导出
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
//				"userEmail:电子邮箱",
                "sendComName:选送单位",
                "hospitalLevelName:医院等级",
                "sendComAddress:选送单位详细地址",
//				"graduatedNo:毕业证编号",
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


    /**
     * 公告维护
     */
    @RequestMapping("/noticemanage")
    public String noticeManage(Integer currentPage, Model model) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, 5);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos", infos);
        return "dwjxres/hospital/notice";
    }

    /**
     * 科室维护
     */
    @RequestMapping(value = "speMaintenance")
    public String speMaintenance(Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, 10);
        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(DictTypeEnum.DwjxSpe.getId())
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL DESC");
        List<SysDict> speLst = sysDictMapper.selectByExample(example);
        model.addAttribute("dwjxSpeLst", speLst);
        return "dwjxres/hospital/spe";
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
        return "dwjxres/hospital/editSpe";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/saveSpe", method = {RequestMethod.POST})
    @ResponseBody
    public String saveSpe(String dictFlow, String dictId, String dictName, HttpServletRequest request) {
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
    public String staffManage(Integer currentPage, Model model, SysUser user4Search) {
        String isOrgAdmin = user4Search.getIsOrgAdmin();
        user4Search.setIsOwnerStu(GlobalConstant.FLAG_Y);
        if (StringUtil.isBlank(isOrgAdmin)) {
            isOrgAdmin = StuRoleEnum.Teacher.getId();
            user4Search.setIsOrgAdmin(isOrgAdmin);
        }
        PageHelper.startPage(currentPage, 10);
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
        return "dwjxres/hospital/staffList";
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
        return "dwjxres/hospital/editStaff";
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
        return "dwjxres/hospital/importStaff";
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
    public String registManage(Integer currentPage, Model model, String idNo, String userName) {
        PageHelper.startPage(currentPage, 10);
        SysUser sysUser = new SysUser();
        if (StringUtil.isNotBlank(idNo)) {
            sysUser.setIdNo(idNo);
        }
        if (StringUtil.isNotBlank(userName)) {
            sysUser.setUserName(userName);
        }
        sysUser.setIsOwnerStu("P");
        List<SysUser> userList = userBiz.searchSysUserByLikeCode(sysUser);
        model.addAttribute("userList", userList);
        return "dwjxres/hospital/registList";
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
        return "dwjxres/hospital/batchSetting";
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
        return "dwjxres/hospital/edit";
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
        int count = stuBatchBiz.saveBatchSetting(batchFlow, registerTime, fee, batchDate, batchStatus);
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
    //管理员发放结业证书
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

