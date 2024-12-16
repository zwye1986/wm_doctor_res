package com.pinde.sci.ctrl.res;


import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResHBArchiveBiz;
import com.pinde.sci.biz.res.IResRotationOrgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDoctorDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDoctorDept;
import com.pinde.sci.model.mo.SchRotation;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 湖北西医招录数据存档
 */
@Controller
@RequestMapping("/hbres/archive")
public class ResDoctorArchiveController extends GeneralController {
    @Autowired
    private IResHBArchiveBiz archiveBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private ISchRotationBiz schRotationtBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private IResRotationOrgBiz iResRotationOrgBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private IDeptBiz deptBiz;


    @RequestMapping("/saveArchiveInfo")
    @ResponseBody
    public synchronized String archiveInfo(String archiveTime,String sessionNumber) throws DocumentException {
        boolean success = false;
        if (StringUtil.isNotBlank(archiveTime)) {
            archiveTime = DateUtil.getDateTime(archiveTime);
            int count = archiveBiz.checkArchive(archiveTime,sessionNumber);
            if (count > 0) {
                return "当前时间当前年级已存在存档";
            }
            success = archiveBiz.saveArchiveInfo4Hb(archiveTime,sessionNumber);
        }
        if(success){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }
        return "存档失败";
    }
    @RequestMapping("/addArchive")
    public String addArchive(){
        return "res/manager/archiveNew";
    }

    @RequestMapping("/archiveInfoList")
    public String archiveInfoList(ResDoctorExt doctor,String archiveFlow,Integer currentPage, String sysidNo,
                                  HttpServletRequest request, Model model){
        //复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
        //获取所有存档记录
        List<ResArchiveSequence> sequenceList = archiveBiz.allResArchiveSequence();
        model.addAttribute("sequenceList",sequenceList);
        if(StringUtil.isNotBlank(archiveFlow)){
            ResArchiveSequence resArchiveSequence = archiveBiz.readResArchiveSequence(archiveFlow);
            String archiveTime = resArchiveSequence.getArchiveTime();
            String sessionNumber = resArchiveSequence.getSessionNumber();
            SysUser user=GlobalContext.getCurrentUser();
            String medicineTypeId=user.getMedicineTypeId();
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("doctor",doctor);
            paramMap.put("sysidNo",sysidNo);
            paramMap.put("archiveTime",archiveTime);
            paramMap.put("sessionNumber",sessionNumber);
            paramMap.put("medicineTypeId",medicineTypeId);
            PageHelper.startPage(currentPage,getPageSize(request));
            List<ResDoctorExt> doctorList = archiveBiz.searchArchiveList(paramMap);
            model.addAttribute("doctorList",doctorList);
            model.addAttribute("archiveFlow",archiveFlow);
        }
        return "/res/college/archiveUserInfoList";
    }

    //用户存档详情
    @RequestMapping(value = {"/archiveDetails"}, method = {RequestMethod.GET})
    public String archiveDetails(String doctorFlow, Model model,String archiveFlow) throws DocumentException {
        ResArchiveSequence resArchiveSequence = archiveBiz.readResArchiveSequence(archiveFlow);
        String archiveTime = resArchiveSequence.getArchiveTime();
        String sessionNumber = resArchiveSequence.getSessionNumber();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("archiveTime",archiveTime);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("userFlow",doctorFlow);

        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctor doctor = archiveBiz.readDoctorArchive(paramMap);
            model.addAttribute("doctor", doctor);
            SysUser user = archiveBiz.readUserArchive(paramMap);
            model.addAttribute("user", user);
            //大字段信息
            PubUserResume pubUserResume = archiveBiz.readUserResumeArchive(paramMap);
            if(pubUserResume != null){
                String xmlContent =  pubUserResume.getUserResume();
                if(StringUtil.isNotBlank(xmlContent)){
                    //xml转换成JavaBean
                    BaseUserResumeExtInfoForm extInfo=null;
                    extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
                    model.addAttribute("extInfo", extInfo);
                }
            }

            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchSchDoctorDept(doctorFlow);
            if (doctorDeptList != null && doctorDeptList.size() > 0) {
                model.addAttribute("rotationInUse", true);
            } else {
                List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
                if (resultList != null && resultList.size() > 0) {
                    model.addAttribute("rotationInUse", true);
                }
            }
        }
        SysUser user = GlobalContext.getCurrentUser();
        List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
        rotationList = schRotationtBiz.schRotations(rotationList, user.getOrgFlow());
        model.addAttribute("rotationList", rotationList);


        SysDept dept = new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<SysDept> deptList = deptBiz.searchDept(dept);
        model.addAttribute("deptList", deptList);

        //所有导师
        String tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
        if (StringUtil.isNotBlank(tutorRoleFlow)) {
            List<SysUser> tutorList = userBiz.findUserByOrgFlowAndRoleFlow(user.getOrgFlow(),tutorRoleFlow);
            model.addAttribute("tutorList", tutorList);
        }
        //所有师承老师
        String discipleFlow = InitConfig.getSysCfg("res_disciple_role_flow");
        if(StringUtil.isNotBlank(discipleFlow)&&StringUtil.isNotBlank(user.getOrgFlow())){
            List<SysUser> discipleList = userBiz.findUserByOrgFlowAndRoleFlow(user.getOrgFlow(),discipleFlow);
            model.addAttribute("discipleList", discipleList);
        }
        return "res/college/archiveDetails";
    }

    //导出
    @RequestMapping(value = "/exportDoc")
    public void exportDoc(ResDoctorExt doctor,String archiveFlow,Integer currentPage, String sysidNo,
                          HttpServletRequest request, HttpServletResponse response,Model model)throws Exception{
        ResArchiveSequence resArchiveSequence = archiveBiz.readResArchiveSequence(archiveFlow);
        String archiveTime = resArchiveSequence.getArchiveTime();
        String sessionNumber = resArchiveSequence.getSessionNumber();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("doctor",doctor);
        paramMap.put("sysidNo",sysidNo);
        paramMap.put("archiveTime",archiveTime);
        paramMap.put("sessionNumber",sessionNumber);
        SysUser user=GlobalContext.getCurrentUser();
        String medicineTypeId=user.getMedicineTypeId();
        paramMap.put("medicineTypeId",medicineTypeId);
//        PageHelper.startPage(currentPage,getPageSize(request));
        List<ResDoctorExt> doctorList = archiveBiz.searchArchiveList(paramMap);
        doctorBiz.exportForDetail4HB(doctorList,paramMap,response);
    }
}