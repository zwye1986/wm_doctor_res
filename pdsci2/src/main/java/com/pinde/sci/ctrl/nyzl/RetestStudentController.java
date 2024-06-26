package com.pinde.sci.ctrl.nyzl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.IRetestStudentBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.nyzl.NyzlRegisterStatusEnum;
import com.pinde.sci.enums.nyzl.NyzlStuSignEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.NyzlRetestStudent;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nyzl/retestStudent")
public class RetestStudentController extends GeneralController{
    @Autowired
    private IRetestStudentBiz retestStudentBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;

    //复试人员信息列表
    @RequestMapping("/retestStudentList")
    public String retestStudentList(String stuSignFlag,String adminFlag,NyzlRetestStudent retestStudent,Integer currentPage,HttpServletRequest request,Model model ){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isBlank(retestStudent.getRecruitYear())){
            retestStudent.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        retestStudent.setStuSign(stuSignFlag);
        if("pydw".equals(adminFlag)){
            retestStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("fwh".equals(adminFlag)){
            retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        List<NyzlRetestStudent> recordList=retestStudentBiz.searchRetestStudentList(retestStudent);
        if("Y".equals(adminFlag)&&StringUtil.isBlank(retestStudent.getFwhFlow())){
            recordList=null;
        }
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        model.addAttribute("deptList",deptList);
        model.addAttribute("recordList",recordList);
        if(NyzlStuSignEnum.DoctoralStudent.getId().equals(stuSignFlag)){
            return "/nyzl/doctoralStudent/doctorInfoList";
        }
        if(NyzlStuSignEnum.MasterStudent.getId().equals(stuSignFlag)){
            return "/nyzl/masterStudent/masterInfoList";
        }
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            return "/nyzl/summerCamp/summerCampInfoList";
        }
        if(NyzlStuSignEnum.PushFreeStudent.getId().equals(stuSignFlag)){
            return "/nyzl/pushFreeStudent/pushFreeInfoList";
        }
        return "";
    }

    @RequestMapping("/showEditStudent")
    public String showEditStudent(Model model,String recordFlow,String stuSignFlag,String educationType,String adminFlag){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isNotBlank(recordFlow)){
            NyzlRetestStudent retestStudent=retestStudentBiz.searchStudentByRecordFlow(recordFlow);
            model.addAttribute("retestStudent",retestStudent);
        }
        if("0".equals(educationType)){
            return "/nyzl/doctoralStudent/editDoctorInfo0";
        }
        if(NyzlStuSignEnum.MasterStudent.getId().equals(stuSignFlag)){
            return "/nyzl/masterStudent/editDoctorInfo";
        }
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            return "/nyzl/summerCamp/editDoctorInfo";
        }
        if(NyzlStuSignEnum.PushFreeStudent.getId().equals(stuSignFlag)){
            return "/nyzl/pushFreeStudent/editDoctorInfo";
        }
        return "/nyzl/doctoralStudent/editDoctorInfo1";
    }

    @RequestMapping("/saveRetestStudent")
    @ResponseBody
    public String saveRetestStudent(NyzlRetestStudent retestStudent,String adminFlag){
        if(retestStudent!=null){
            if("1".equals(retestStudent.getEducationTypeId())){
                retestStudent.setEducationTypeName("全日制");
            }
            if("0".equals(retestStudent.getEducationTypeId())){
                retestStudent.setEducationTypeName("在职");
            }
            if("Man".equals(retestStudent.getSexId())){
                retestStudent.setSexName("男");
            }
            if("Woman".equals(retestStudent.getSexId())){
                retestStudent.setSexName("女");
            }
            retestStudent.setApplicationCategoryName(DictTypeEnum.NyzlApplicationType.getDictNameById(retestStudent.getApplicationCategoryId()));
            retestStudent.setDegreeTypeName(DictTypeEnum.NyzlDegreeType.getDictNameById(retestStudent.getDegreeTypeId()));
//            retestStudent.setSpeName(DictTypeEnum.NyzlSpe.getDictNameById(retestStudent.getSpeId()));
//            retestStudent.setDirectionName(DictTypeEnum.NyzlDirection.getDictNameById(retestStudent.getDirectionId()));
//            retestStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//            retestStudent.setOrgName(GlobalContext.getCurrentUser().getOrgName());
//            retestStudent.setRegisterStatusName(NyzlRegisterStatusEnum.getNameById(retestStudent.getRegisterStatusId()));
            if("fwh".equals(adminFlag)){
                retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
                retestStudent.setFwhName(GlobalContext.getCurrentUser().getDeptName());
            }
            if(StringUtil.isNotBlank(retestStudent.getSwapBatchId())){
                retestStudent.setSwapBatchName(DictTypeEnum.NyzlBatch.getDictNameById(retestStudent.getSwapBatchId()));
            }else{
                retestStudent.setSwapBatchName("");
            }
        }
        int num=retestStudentBiz.save(retestStudent);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    //修改报到状态
    @RequestMapping("/updateRetestStudent")
    @ResponseBody
    public String updateRetestStudent(String recordFlow,String registerStatus){
        if(StringUtil.isNotBlank(recordFlow)){
            NyzlRetestStudent retestStudent=retestStudentBiz.searchStudentByRecordFlow(recordFlow);
            if(retestStudent!=null){
                retestStudent.setRegisterStatusId(registerStatus);
                retestStudent.setRegisterStatusName(NyzlRegisterStatusEnum.getNameById(registerStatus));
                int num=retestStudentBiz.save(retestStudent);
                if(num>0){
                    return GlobalConstant.SAVE_SUCCESSED;
                }
                return GlobalConstant.SAVE_FAIL;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importDoctoralInfo";
    }

    @RequestMapping(value="/importRetestStudent")
    @ResponseBody
    public String importRetestStudent(MultipartFile file,String educationTypeId,String swapBatchId,String stuSignFlag,String adminFlag){
        if(file.getSize() > 0){
            try{
                int result = retestStudentBiz.importRetestStudent(file,educationTypeId,swapBatchId,stuSignFlag,adminFlag);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping("/exportStudentInfo")
    public void exportStudentInfo(String stuSignFlag,String adminFlag,NyzlRetestStudent retestStudent,HttpServletResponse response)throws Exception {
        retestStudent.setStuSign(stuSignFlag);
        if(StringUtil.isBlank(retestStudent.getRecruitYear())){
            retestStudent.setRecruitYear(DateUtil.getYear());
        }
        if("fwh".equals(adminFlag)){
            retestStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if("pydw".equals(adminFlag)){
            retestStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlRetestStudent> recordList=retestStudentBiz.searchRetestStudentList(retestStudent);
        String[] titles;//导出列表头信息
        titles = new String[]{
                "recruitYear:年份",
                "stuNo:考生编号",
                "stuName:姓名",
                "educationTypeName:博士类型",
                "certificateNo:合格证编号",
                "intentionTeacherName:意向报考导师",
                "sexName:性别",
                "graduationUnit:硕士毕业单位",
                "contactPhone:联系方式",
                "fwhName:分委会",
                "speName:报考专业名称",
                "directionName:方向",
                "degreeTypeName:学位类型",
                "applicationCategoryName:报考类型",
                "stuGrade:总分",
                "schoolRanking:全校排名",
                "collegeRankging:全院排名",
                "registerStatusName:状态",
                "swapBatchName:复试批次"
        };
        if(NyzlStuSignEnum.MasterStudent.getId().equals(stuSignFlag)){
            titles = new String[]{
                    "recruitYear:年份",
                    "stuNo:考生编号",
                    "stuName:姓名",
                    "sexName:性别",
                    "graduationUnit:毕业单位",
                    "contactPhone:联系方式",
                    "fwhName:分委会",
                    "speName:报考专业名称",
                    "directionName:方向",
                    "degreeTypeName:学位类型",
                    "applicationCategoryName:报考类型",
                    "stuGrade:总分",
                    "schoolRanking:全校排名",
                    "collegeRankging:全院排名",
                    "registerStatusName:状态",
                    "swapBatchName:复试批次"
            };
        }
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            titles = new String[]{
                    "recruitYear:年份",
                    "stuNo:考生编号",
                    "stuName:姓名",
                    "sexName:性别",
                    "graduationUnit:毕业单位",
                    "contactPhone:联系方式",
                    "fwhName:分委会",
                    "speName:报考专业名称",
                    "directionName:方向",
                    "degreeTypeName:学位类型",
                    "applicationCategoryName:报考类型",
                    "gradeRankingPercentage:年级排名百分比",
                    "sixGrade:六级成绩",
                    "institutionTypeName:院校类型",
                    "registerStatusName:状态"
            };
        }
        if(NyzlStuSignEnum.PushFreeStudent.getId().equals(stuSignFlag)){
            titles = new String[]{
                    "recruitYear:年份",
                    "stuNo:考生编号",
                    "stuName:姓名",
                    "sexName:性别",
                    "graduationUnit:毕业单位",
                    "contactPhone:联系方式",
                    "fwhName:分委会",
                    "speName:报考专业名称",
                    "directionName:方向",
                    "degreeTypeName:学位类型",
                    "applicationCategoryName:报考类型",
                    "stuGrade:总分",
                    "schoolRanking:全校排名",
                    "collegeRankging:全院排名",
                    "registerStatusName:状态"
            };
        }
        ExcleUtile.exportSimpleExcle(titles, recordList, NyzlRetestStudent.class,response.getOutputStream());
        String fileName = "复试人员信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    //硕士生复试人员导入
    @RequestMapping(value="/leadToMaster")
    public String leadToMaster(String stuSignFlag,String adminFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        return "/nyzl/masterStudent/importMasterInfo";
    }
    @RequestMapping(value="/importMasterInfo")
    @ResponseBody
    public String importMasterInfo(MultipartFile file,String stuSignFlag,String adminFlag){
        if(file.getSize() > 0){
            try{
                int result = retestStudentBiz.importStuInfo(file,stuSignFlag,adminFlag);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    //夏令营复试人员导入
    @RequestMapping(value="/leadToSummerCamp")
    public String leadToSummerCamp(String stuSignFlag,String adminFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        return "/nyzl/summerCamp/importSummerCampInfo";
    }
    //推免生复试人员导入
    @RequestMapping(value="/leadToPushFreeStudent")
    public String leadToPushFreeStudent(String stuSignFlag,String adminFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        return "/nyzl/pushFreeStudent/importPushFreeStudent";
    }
}
