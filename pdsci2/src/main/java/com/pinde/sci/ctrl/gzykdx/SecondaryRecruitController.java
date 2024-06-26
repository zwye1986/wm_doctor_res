package com.pinde.sci.ctrl.gzykdx;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxRecruitBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.gzykdx.GzykdxAdmissionStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxDegreeTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxRecruitExtInfoForm;
import com.pinde.sci.form.gzykdx.GzykdxStudentExportForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LittleSheep
 *
 */
@Controller
@RequestMapping("/gzykdx/secondaryRecruit")
public class SecondaryRecruitController extends GeneralController {
    @Autowired
    IGzykdxRecruitBiz recruitBiz;
    @Autowired
    IUserBiz userBiz;
    @Autowired
    IPubUserResumeBiz pubUserResumeBiz;
    @Autowired
    IOrgBiz orgBiz;
    @Autowired
    IDictBiz dictBiz;
    @Autowired
    IGzykdxTeaAndDocBiz teaAndDocBiz;

    @RequestMapping("/firstReexamineList")
    public String firstReexamineList(SysUser user, Integer currentPage, HttpServletRequest request, Model model,
                                DoctorTeacherRecruit doctorTeacherRecruit,String org){
        String thisYear = DateUtil.getYear();
        if (StringUtil.isBlank(doctorTeacherRecruit.getRecruitYear())) {
            doctorTeacherRecruit.setRecruitYear(thisYear);
        }
        model.addAttribute("thisYear",thisYear);
        //查询所有二级机构
        SysOrg org1 = new SysOrg();
        org1.setIsSecondFlag("Y");
        List<SysOrg> orgList = orgBiz.searchOrg(org1);
        model.addAttribute("orgList",orgList);

        Map<String,String> paramMap = new HashMap<>();
        if(user!=null){
            paramMap.put("userCode",user.getUserCode());
            paramMap.put("userName",user.getUserName());
        }
        if(doctorTeacherRecruit!=null){
            paramMap.put("speId",doctorTeacherRecruit.getSpeId());
            paramMap.put("recruitBatch",doctorTeacherRecruit.getRecruitBatch());
            paramMap.put("schoolAuditStatusId",doctorTeacherRecruit.getAuditStatusId());
            paramMap.put("recruitYear",doctorTeacherRecruit.getRecruitYear());
            paramMap.put("researchAreaName",doctorTeacherRecruit.getResearchAreaName());
            paramMap.put("orgFlow",org);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> firstReexamineList = recruitBiz.searchSecondaryReexamineStudent(paramMap);
        Map<String,String> cscjMap = new HashMap<>();
        Map<String,String> fscjMap = new HashMap<>();
        Map<String,String> zcjMap = new HashMap<>();
        Map<String,String> bydwMap = new HashMap<>();
        Map<String,String> fshMap = new HashMap<>();
        if(firstReexamineList!=null&&firstReexamineList.size()>0){
            for(Map<String,Object> student:firstReexamineList){
                String userFlow = (String)student.get("USER_FLOW");
                String xml = (String)student.get("user_resume");
                if(StringUtil.isNotBlank(xml)) {
                    GzykdxRecruitExtInfoForm recruitExtInfoForm = JaxbUtil.converyToJavaBean(xml, GzykdxRecruitExtInfoForm.class);
                    if (recruitExtInfoForm != null) {
                        String cscj = recruitExtInfoForm.getCscj();
                        String fscj = recruitExtInfoForm.getFscj();
                        String zcj = recruitExtInfoForm.getZcj();
                        String bydw = recruitExtInfoForm.getBydw();
                        String fsh  = recruitExtInfoForm.getFsh();
                        cscjMap.put(userFlow, cscj);
                        fscjMap.put(userFlow, fscj);
                        zcjMap.put(userFlow, zcj);
                        bydwMap.put(userFlow, bydw);
                        fshMap.put(userFlow, fsh);
                    }
                }
            }
        }
        model.addAttribute("firstReexamineList",firstReexamineList);
        model.addAttribute("cscjMap",cscjMap);
        model.addAttribute("fscjMap",fscjMap);
        model.addAttribute("zcjMap",zcjMap);
        model.addAttribute("bydwMap",bydwMap);
        model.addAttribute("fshMap",fshMap);
        return "gzykdx/secondaryRecruitManage/firstReexamineList";
    }

    //导出复试学生大信息
    @RequestMapping(value = "/schoolExportStudents")
    public void schoolExportStudents(HttpServletResponse response,SysUser user, Integer currentPage,
                                     HttpServletRequest request, DoctorTeacherRecruit doctorTeacherRecruit,
                                     String org,String isSecondary)throws Exception{
        Map<String,String> paramMap = new HashMap<>();
        if(user!=null){
            paramMap.put("userCode",user.getUserCode());
            paramMap.put("userName",user.getUserName());
            paramMap.put("isOwnerStu",user.getIsOwnerStu());
        }
        if(doctorTeacherRecruit!=null){
            paramMap.put("speId",doctorTeacherRecruit.getSpeId());
            paramMap.put("recruitBatch",doctorTeacherRecruit.getRecruitBatch());
            paramMap.put("auditStatusId",doctorTeacherRecruit.getAuditStatusId());
            paramMap.put("researchAreaName",doctorTeacherRecruit.getResearchAreaName());
            paramMap.put("orgFlow",org);
            if("isSecondary".equals(isSecondary)){
                paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
            }
            paramMap.put("degreeTypeId",doctorTeacherRecruit.getDegreeTypeId());
        }
        String recruitYear = doctorTeacherRecruit.getRecruitYear();
        paramMap.put("recruitYear",recruitYear);
//        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> firstReexamineList = recruitBiz.searchSecondaryReexamineStudent(paramMap);
        List<GzykdxStudentExportForm> exportFormList = new ArrayList<>();//打印类
        if(firstReexamineList!=null&&firstReexamineList.size()>0){
            for(Map<String,Object> student:firstReexamineList){
                String userFlow = (String) student.get("USER_FLOW");//user类
                SysUser sysUser = userBiz.readSysUser(userFlow);
                if(sysUser.getIsOwnerStu().equals("Y")){
                    sysUser.setIsOwnerStu("否");
                }else{
                    sysUser.setIsOwnerStu("是");
                }

                DoctorTeacherRecruit searchDTR = new DoctorTeacherRecruit();//docotrTeacherRecruit类
                searchDTR.setDoctorFlow(userFlow);
                searchDTR.setRecruitYear(recruitYear);
                DoctorTeacherRecruit doctorTeacherRecruit1 = recruitBiz.searchDoctorTeacherRecruit(searchDTR).get(0);

                PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(userFlow);//大字段类
                String xml = pubUserResume.getUserResume();
                GzykdxRecruitExtInfoForm gzykdxRecruitExtInfoForm = JaxbUtil.converyToJavaBean(xml,GzykdxRecruitExtInfoForm.class);

                GzykdxStudentExportForm gzykdxStudentExportForm = new GzykdxStudentExportForm();//打印类
                gzykdxStudentExportForm.setSysUser(sysUser);
                gzykdxStudentExportForm.setDoctorTeacherRecruit(doctorTeacherRecruit1);
                gzykdxStudentExportForm.setGzykdxRecruitExtInfoForm(gzykdxRecruitExtInfoForm);
                exportFormList.add(gzykdxStudentExportForm);
            }
        }
        String[] titles = new String[]{
                "sysUser.userCode:考生编号",
                "sysUser.userName:姓名",
                "sysUser.isOwnerStu:是否校外考生",
                "doctorTeacherRecruit.speId:报考专业代码",
                "doctorTeacherRecruit.recruitTypeName:报考类型",
                "doctorTeacherRecruit.userName:报考导师",
                "doctorTeacherRecruit.orgName:报考单位",
                "doctorTeacherRecruit.userIdNo:导师身份证号",
                "doctorTeacherRecruit.speName:报考专业名称",
                "doctorTeacherRecruit.researchAreaName:研究方向名称",
                "doctorTeacherRecruit.finalSpeId:录取专业代码",
                "doctorTeacherRecruit.finalSpeName:录取专业名称",
                "doctorTeacherRecruit.finalDegreeTypeName:录取学位类型",
                "doctorTeacherRecruit.finalResearchAreaName:录取研究方向名称",
                "doctorTeacherRecruit.finalUserName:录取导师",
                "doctorTeacherRecruit.finalOrgName:录取单位",
                "gzykdxRecruitExtInfoForm.fsh:复试号",
                "gzykdxRecruitExtInfoForm.ksfsm:考试方式码",
                "gzykdxRecruitExtInfoForm.ywk1mc:业务课一名称",
                "gzykdxRecruitExtInfoForm.ywk2mc:业务课二名称",
                "gzykdxRecruitExtInfoForm.zzll:政治理论成绩",
                "gzykdxRecruitExtInfoForm.wgy:外国语成绩",
                "gzykdxRecruitExtInfoForm.ywk1:业务课一成绩",
                "gzykdxRecruitExtInfoForm.ywk2:业务课二成绩",
                "gzykdxRecruitExtInfoForm.zf:总分",
                "gzykdxRecruitExtInfoForm.cscj:初试成绩",
                "gzykdxRecruitExtInfoForm.bklb:报考类别",
                "gzykdxRecruitExtInfoForm.ywk1m:业务课一码",
                "gzykdxRecruitExtInfoForm.ywk2m:业务课二码",
                "gzykdxRecruitExtInfoForm.xmpy:姓名拼音",
                "gzykdxRecruitExtInfoForm.zjlx:证件类型",
                "gzykdxRecruitExtInfoForm.zjhm:证件号码",
                "gzykdxRecruitExtInfoForm.csny:出生年月",
                "gzykdxRecruitExtInfoForm.mzm:民族码",
                "gzykdxRecruitExtInfoForm.xbm:性别码",
                "gzykdxRecruitExtInfoForm.hfm:婚姻状况码",
                "gzykdxRecruitExtInfoForm.xyjrm:现役军人码",
                "gzykdxRecruitExtInfoForm.zzmmm:政治面貌码",
                "gzykdxRecruitExtInfoForm.hkszdm:户口所在地码",
                "gzykdxRecruitExtInfoForm.hkszdxxdz:户口所在地详细地址",
                "gzykdxRecruitExtInfoForm.daszdm:考生档案所在单位码",
                "gzykdxRecruitExtInfoForm.daszdw:考生档案所在单位",
                "gzykdxRecruitExtInfoForm.daszdwdz:考生档案所在单位地址",
                "gzykdxRecruitExtInfoForm.daszdwyzbm:考生档案所在单位邮政编码",
                "gzykdxRecruitExtInfoForm.kslym:考生来源码",
                "gzykdxRecruitExtInfoForm.bydwm:毕业学校代码",
                "gzykdxRecruitExtInfoForm.bydw:毕业学校名称",
                "gzykdxRecruitExtInfoForm.byzydm:毕业专业代码",
                "gzykdxRecruitExtInfoForm.byzymc:毕业专业名称",
                "gzykdxRecruitExtInfoForm.xxxs:取得最后学历的学习形式",
                "gzykdxRecruitExtInfoForm.xlm:最后学历码",
                "gzykdxRecruitExtInfoForm.xlzsbh:毕业证书编号",
                "gzykdxRecruitExtInfoForm.byny:获得最后学历毕业年月",
                "gzykdxRecruitExtInfoForm.zcxh:注册学号",
                "gzykdxRecruitExtInfoForm.xwm:最后学位码",
                "gzykdxRecruitExtInfoForm.xwzsbh:学位证书编号",
                "gzykdxRecruitExtInfoForm.zxjh:专项计划",
                "gzykdxRecruitExtInfoForm.dxwpdwszdm:定向就业单位所在地码",
                "gzykdxRecruitExtInfoForm.dxwpdw:定向就业单位",
                "gzykdxRecruitExtInfoForm.zzllm:政治理论码",
                "gzykdxRecruitExtInfoForm.zzllmc:政治理论名称",
                "gzykdxRecruitExtInfoForm.wgym:外国语码",
                "gzykdxRecruitExtInfoForm.wgymc:外国语名称",
                "gzykdxRecruitExtInfoForm.rwpzsbh:入伍批准书编号",
                "gzykdxRecruitExtInfoForm.tcxyzbh:退出现役证编号",
                "gzykdxRecruitExtInfoForm.lqdwdm:录取单位代码",
                "gzykdxRecruitExtInfoForm.lqdwmc:录取单位名称",
                "gzykdxRecruitExtInfoForm.lqzydm:录取专业代码",
                "gzykdxRecruitExtInfoForm.lqzymc:录取专业名称",
                "gzykdxRecruitExtInfoForm.lqlbm:录取类别码",
                "gzykdxRecruitExtInfoForm.lqxysm:录取院系所码",
                "gzykdxRecruitExtInfoForm.fscj:复试总成绩",
                "gzykdxRecruitExtInfoForm.zcjzsbf:总成绩折算办法",
                "gzykdxRecruitExtInfoForm.zcj:总成绩",
                "gzykdxRecruitExtInfoForm.js1mc:加试科目1名称",
                "gzykdxRecruitExtInfoForm.js2mc:加试科目2名称",
                "gzykdxRecruitExtInfoForm.js1cj:加试科目1成绩",
                "gzykdxRecruitExtInfoForm.js2cj:加试科目2成绩",
                "gzykdxRecruitExtInfoForm.blzgnx:保留入学资格年限",
                "gzykdxRecruitExtInfoForm.qg:是否破格",
                "gzykdxRecruitExtInfoForm.xszgzc:享受照顾政策",
                "gzykdxRecruitExtInfoForm.szssm:招生单位所在省市码",
                "gzykdxRecruitExtInfoForm.bz:备注",
                "gzykdxRecruitExtInfoForm.lhpydwm:联合培养单位代码",
                "gzykdxRecruitExtInfoForm.lhpydw:联合培养单位名称",
                "gzykdxRecruitExtInfoForm.xz:学制",
                "gzykdxRecruitExtInfoForm.bkdwdm:报考单位代码",
                "gzykdxRecruitExtInfoForm.bklbm:报考类别码",
                "gzykdxRecruitExtInfoForm.bkyxsm:报考院系所码",
                "gzykdxRecruitExtInfoForm.jgszdm:籍贯所在地码",
                "gzykdxRecruitExtInfoForm.csdm:出生地码",
                "gzykdxRecruitExtInfoForm.bmddm:报名点代码",
                "gzykdxRecruitExtInfoForm.bmh:考生报名号",
                "gzykdxRecruitExtInfoForm.xxgzdw:现在学习或工作单位",
                "gzykdxRecruitExtInfoForm.xxgzjl:学习与工作经历",
                "gzykdxRecruitExtInfoForm.jlcf:何时何地何原因受过何种奖励或处分",
                "gzykdxRecruitExtInfoForm.kszbqk:考生作弊情况",
                "gzykdxRecruitExtInfoForm.jtcy:家庭主要成员",
                "gzykdxRecruitExtInfoForm.txdz:考生通讯地址",
                "gzykdxRecruitExtInfoForm.yzbm:考生通讯地址邮政编码",
                "gzykdxRecruitExtInfoForm.lxdh:固定电话",
                "gzykdxRecruitExtInfoForm.yddh:移动电话",
                "gzykdxRecruitExtInfoForm.dzxx:电子信箱",
                "gzykdxRecruitExtInfoForm.zsdwsm:招生单位说明",
                "gzykdxRecruitExtInfoForm.bkdsm:报考点说明",
                "gzykdxRecruitExtInfoForm.byxx:备用信息",
                "gzykdxRecruitExtInfoForm.byxx1:备用信息1",
                "gzykdxRecruitExtInfoForm.byxx2:备用信息2",
                "gzykdxRecruitExtInfoForm.byxx3:备用信息3",
                "gzykdxRecruitExtInfoForm.bydwssm:毕业学校所在省市代码",
                "gzykdxRecruitExtInfoForm.kzyxx:跨专业信息",
                "gzykdxRecruitExtInfoForm.jfbz:交费标志",
                "gzykdxRecruitExtInfoForm.zxbz:照相标志",
                "gzykdxRecruitExtInfoForm.bmsj:报名时间",
                "gzykdxRecruitExtInfoForm.xgsj:修改时间",
                "gzykdxRecruitExtInfoForm.qrsj:确认时间",
                "gzykdxRecruitExtInfoForm.bkdwmc:报考单位名称",
                "gzykdxRecruitExtInfoForm.bkyxsmc:报考院系所名称"
        };
        String fileName = "学生名单.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, exportFormList, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");

    }


    @RequestMapping("/secondaryReexamineList")
    public String reexamineList(SysUser user, Integer currentPage, HttpServletRequest request, Model model,
                                DoctorTeacherRecruit doctorTeacherRecruit){
        String thisYear = DateUtil.getYear();
        if (StringUtil.isBlank(doctorTeacherRecruit.getRecruitYear())) {
            doctorTeacherRecruit.setRecruitYear(thisYear);
        }
        model.addAttribute("thisYear",thisYear);
        Map<String,String> paramMap = new HashMap<>();
        if(user!=null){
            paramMap.put("userCode",user.getUserCode());
            paramMap.put("userName",user.getUserName());
            paramMap.put("isOwnerStu",user.getIsOwnerStu());
        }
        if(doctorTeacherRecruit!=null){
            paramMap.put("speId",doctorTeacherRecruit.getSpeId());
            paramMap.put("schoolAuditStatusId",doctorTeacherRecruit.getSchoolAuditStatusId());
            paramMap.put("recruitYear",doctorTeacherRecruit.getRecruitYear());
            paramMap.put("degreeTypeId",doctorTeacherRecruit.getDegreeTypeId());
            paramMap.put("researchAreaName",doctorTeacherRecruit.getResearchAreaName());
            paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> secondaryReexamineList = recruitBiz.searchSecondaryReexamineStudent(paramMap);
        Map<String,String> cscjMap = new HashMap<>();
        Map<String,String> fscjMap = new HashMap<>();
        Map<String,String> zcjMap = new HashMap<>();
        Map<String,String> bydwMap = new HashMap<>();
        Map<String,String> fshMap = new HashMap<>();
        Map<String,String> halfFscjMap = new HashMap<>();
        if(secondaryReexamineList!=null&&secondaryReexamineList.size()>0){
            for(Map<String,Object> student:secondaryReexamineList){
                String userFlow = (String)student.get("USER_FLOW");
                String xml = (String)student.get("user_resume");
                if(StringUtil.isNotBlank(xml)) {
                    GzykdxRecruitExtInfoForm recruitExtInfoForm = JaxbUtil.converyToJavaBean(xml, GzykdxRecruitExtInfoForm.class);
                    if (recruitExtInfoForm != null) {
                        String cscj = recruitExtInfoForm.getCscj();
                        String fscj = recruitExtInfoForm.getFscj();
                        String zcj = recruitExtInfoForm.getZcj();
                        String bydw = recruitExtInfoForm.getBydw();
                        String fsh = recruitExtInfoForm.getFsh();
                        String halfFscj = recruitExtInfoForm.getHalfFscj();
                        cscjMap.put(userFlow, cscj);
                        fscjMap.put(userFlow, fscj);
                        zcjMap.put(userFlow, zcj);
                        bydwMap.put(userFlow, bydw);
                        fshMap.put(userFlow, fsh);
                        halfFscjMap.put(userFlow, halfFscj);
                    }
                }
            }
        }
        model.addAttribute("secondaryReexamineList",secondaryReexamineList);
        model.addAttribute("cscjMap",cscjMap);
        model.addAttribute("fscjMap",fscjMap);
        model.addAttribute("zcjMap",zcjMap);
        model.addAttribute("bydwMap",bydwMap);
        model.addAttribute("fshMap",fshMap);
        model.addAttribute("halfFscjMap",halfFscjMap);
        return "gzykdx/secondaryRecruitManage/secondaryReexamineList";
    }

    //学校审核
    @RequestMapping("/editDocTeaRec")
    @ResponseBody
    public int editDocTeaRec(DoctorTeacherRecruit doctorTeacherRecruit){
        String recordFlow = doctorTeacherRecruit.getRecordFlow();
        DoctorTeacherRecruit doctorTeacherRecruit1 = recruitBiz.readDocTeaRec(recordFlow);
        String recruitYear = doctorTeacherRecruit1.getRecruitYear();
        String userFlow = doctorTeacherRecruit1.getFinalUserFlow();
        String degreeTypeId = doctorTeacherRecruit1.getFinalDegreeTypeId();
        if(GzykdxAdmissionStatusEnum.Passed.getId().equals(doctorTeacherRecruit.getSchoolAuditStatusId())) {
            //如果是通过审核判断老师名额够不够
            TeacherTargetApply teacherTargetApply1 = new TeacherTargetApply();
            teacherTargetApply1.setRecruitYear(recruitYear);
            teacherTargetApply1.setUserFlow(userFlow);
            teacherTargetApply1.setRecordStatus("Y");
            TeacherTargetApply targetApply2 = recruitBiz.searchTeacherTargetApply(teacherTargetApply1).get(0);
            int result = 0;
            if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
                String academicNum = targetApply2.getAcademicNum();
                String academicRecruitNum = targetApply2.getAcademicRecruitNum();
                result = Integer.parseInt(academicNum)-Integer.parseInt(academicRecruitNum);
            }
            if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
                String specializedNum = targetApply2.getSpecializedNum();
                String specializedRecruitNum = targetApply2.getSpecializedRecruitNum();
                result = Integer.parseInt(specializedNum)-Integer.parseInt(specializedRecruitNum);
            }
            if (result <= 0) {
                return -1;
            }
        }
        //TeacherTargetApply更新
            int a =1;//新增+1
            if(GzykdxAdmissionStatusEnum.GiveUpPassed.getId().equals(doctorTeacherRecruit.getSchoolAuditStatusId())){
                a =-1;//放弃-1
            }
            if(GzykdxAdmissionStatusEnum.UnPassed.getId().equals(doctorTeacherRecruit.getSchoolAuditStatusId())){
                a=0;
            }
            TeacherTargetApply teacherTargetApply = new TeacherTargetApply();
            teacherTargetApply.setRecruitYear(recruitYear);
            teacherTargetApply.setUserFlow(userFlow);
            List<TeacherTargetApply> teacherTargetApplyList = recruitBiz.searchTeacherTargetApply(teacherTargetApply);

                if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
                    TeacherTargetApply targetApply = teacherTargetApplyList.get(0);
                    if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)) {
                        String academicRecruitNum = targetApply.getAcademicRecruitNum();
                        academicRecruitNum = Integer.toString(Integer.parseInt(academicRecruitNum) + a);
                        targetApply.setAcademicRecruitNum(academicRecruitNum);
                    }else{
                        String specializedRecruitNum  = targetApply.getSpecializedRecruitNum();
                        specializedRecruitNum = Integer.toString(Integer.parseInt(specializedRecruitNum) + a);
                        targetApply.setSpecializedRecruitNum(specializedRecruitNum);
                    }
                    recruitBiz.editTTA(targetApply);
                }

        return recruitBiz.editDocTeaRec(doctorTeacherRecruit);
    }

    //二级机构审核
    @RequestMapping("/secondaryEditDocTeaRec")
    @ResponseBody
    public int secondaryCEditDocTeaRec(DoctorTeacherRecruit doctorTeacherRecruit){
        if(StringUtil.isNotBlank(doctorTeacherRecruit.getUserFlow())){//更换导师
            SysUser user = userBiz.findByFlow(doctorTeacherRecruit.getUserFlow());
            doctorTeacherRecruit.setUserIdNo(StringUtil.defaultIfEmpty(user.getIdNo(),"缺失"));//更新身份证
            //判断老师名额够不够
            String recordFlow = doctorTeacherRecruit.getRecordFlow();
            DoctorTeacherRecruit doctorTeacherRecruit1 = recruitBiz.readDocTeaRec(recordFlow);
            String recruitYear = doctorTeacherRecruit1.getRecruitYear();
            String userFlow = doctorTeacherRecruit1.getFinalUserFlow();
            String degreeTypeId = doctorTeacherRecruit1.getFinalDegreeTypeId();
            TeacherTargetApply teacherTargetApply1 = new TeacherTargetApply();
            teacherTargetApply1.setRecruitYear(recruitYear);
            teacherTargetApply1.setUserFlow(userFlow);
            teacherTargetApply1.setRecordStatus("Y");
            TeacherTargetApply targetApply2 = recruitBiz.searchTeacherTargetApply(teacherTargetApply1).get(0);
            int result = 0;
            if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
                String academicNum = targetApply2.getAcademicNum();
                String academicRecruitNum = targetApply2.getAcademicRecruitNum();
                result = Integer.parseInt(academicNum)-Integer.parseInt(academicRecruitNum);
            }
            if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
                String specializedNum = targetApply2.getSpecializedNum();
                String specializedRecruitNum = targetApply2.getSpecializedRecruitNum();
                result = Integer.parseInt(specializedNum)-Integer.parseInt(specializedRecruitNum);
            }
            if (result <= 0) {
                return -1;
            }
        }
        return recruitBiz.editDocTeaRec(doctorTeacherRecruit);
    }

    //打开变更导师列表
    @RequestMapping("/changeTeacher")
    public String changeTeacher(String recordFlow,String userName,Model model){
        DoctorTeacherRecruit doctorTeacherRecruit = recruitBiz.readDocTeaRec(recordFlow);
        model.addAttribute("recordFlow",recordFlow);
        String currentDegreeTypeId = doctorTeacherRecruit.getDegreeTypeId();
        String degreeTypeId = null;
        if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(currentDegreeTypeId)){
            degreeTypeId = GzykdxDegreeTypeEnum.AcademicType.getId();
        }
        if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(currentDegreeTypeId)){
            if(doctorTeacherRecruit.getSpeId().compareTo("10501")>=0&&doctorTeacherRecruit.getSpeId().compareTo("105127")<=0){
                degreeTypeId = "";
            }else{
                degreeTypeId = GzykdxDegreeTypeEnum.ProfessionalType.getId();
            }
        }
        String orgFlow = doctorTeacherRecruit.getOrgFlow();
        String recruitYear  = doctorTeacherRecruit.getRecruitYear();
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("recruitYear",recruitYear);
        paramMap.put("userName",userName);
        paramMap.put("degreeTypeId",degreeTypeId);
        List<TeacherTargetApply> teacherTargetApplyList =  recruitBiz.changeTeacherList(paramMap);
        model.addAttribute("teacherTargetApplyList",teacherTargetApplyList);
        return "gzykdx/secondaryRecruitManage/teacherList";
    }

    //打开录取界面
    @RequestMapping("/changeAuditStatus")
    public String changeAuditStatus(String recordFlow){
        return "gzykdx/secondaryRecruitManage/changeAuditStatus";
    }

    //学校打开录取界面
    @RequestMapping("/schoolChangeAuditStatus")
    public String schoolChangeAuditStatus(String recordFlow){
        return "gzykdx/secondaryRecruitManage/schoolChangeAuditStatus";
    }

    //一键审核
    @RequestMapping("/fastPass")
    @ResponseBody
    public String stopTeaOption(String jsonData){
        Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
        List<String> recordLst = (List<String>)mp.get("recordLst");
        String auditStatusId = (String)mp.get("auditStatusId");
        String auditStatusName = GzykdxAdmissionStatusEnum.getNameById(auditStatusId);
        if(null != recordLst && recordLst.size() > 0){
            for (int i = 0; i < recordLst.size(); i++) {
                DoctorTeacherRecruit dtr = new DoctorTeacherRecruit();
                dtr.setRecordFlow(recordLst.get(i));
                dtr.setSchoolAuditStatusId(auditStatusId);
                dtr.setSchoolAuditStatusName(auditStatusName);
                if(editDocTeaRec(dtr)==-1){
                    DoctorTeacherRecruit doctorTeacherRecruit = recruitBiz.readDocTeaRec(recordLst.get(i));
                    return doctorTeacherRecruit.getDoctorName()+"导师录取名额为0";
                }
            }
        }
        return GlobalConstant.OPRE_SUCCESSED;

    }

    //审核批次轨迹查询
    @RequestMapping("/trajectory")
    public String trajectory(String recordFlow,Model model){
        DoctorTeacherRecruitBatch doctorTeacherRecruitBatch = new DoctorTeacherRecruitBatch();
        doctorTeacherRecruitBatch.setRefRecordFlow(recordFlow);
        List<DoctorTeacherRecruitBatch> doctorTeacherRecruitBatches = recruitBiz.searchBatches(doctorTeacherRecruitBatch);
        model.addAttribute("doctorTeacherRecruitBatches",doctorTeacherRecruitBatches);
        DoctorTeacherRecruit doctorTeacherRecruit = recruitBiz.readDocTeaRec(recordFlow);
        model.addAttribute("doctorTeacherRecruit",doctorTeacherRecruit);
        return "gzykdx/secondaryRecruitManage/trajectory";
    }

    //修改考生复试成绩
    @RequestMapping("/changeFscj")
    @ResponseBody
    public void changeFscj(String userFlow,String fscj){
        DecimalFormat df = new DecimalFormat("0.0");
        Double value = Double.parseDouble(fscj);
        fscj = df.format(value);
        String halfFscj = df.format(value/2);
        PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(userFlow);
        String xml = pubUserResume.getUserResume();
        GzykdxRecruitExtInfoForm gzykdxRecruitExtInfoForm = JaxbUtil.converyToJavaBean(xml,GzykdxRecruitExtInfoForm.class);
        gzykdxRecruitExtInfoForm.setFscj(fscj);
        gzykdxRecruitExtInfoForm.setHalfFscj(halfFscj);
        String cscj = gzykdxRecruitExtInfoForm.getCscj();
        String zcj = String.valueOf(Double.parseDouble(cscj)+Double.parseDouble(halfFscj));
        gzykdxRecruitExtInfoForm.setZcj(zcj);
        xml = JaxbUtil.convertToXml(gzykdxRecruitExtInfoForm);
        pubUserResume.setUserResume(xml);
        pubUserResumeBiz.savePubUserResume(null,pubUserResume);
    }

    //修改考生第几录取
    @RequestMapping("/changeRecruitNum")
    @ResponseBody
    public void changeRecruitNum(DoctorTeacherRecruit doctorTeacherRecruit){
        recruitBiz.editDocTeaRec(doctorTeacherRecruit);

    }
}
