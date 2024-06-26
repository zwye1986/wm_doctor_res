package com.pinde.sci.ctrl.nyzl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlAdmissionStudentBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.nyzl.NyzlStuSignEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.NyzlAdmissionStudent;
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
@RequestMapping("/nyzl/admissionStudent")
public class NyzlAdmissionStudentController extends GeneralController {
    @Autowired
    private INyzlAdmissionStudentBiz admissionStudentBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @RequestMapping("/admissionStudentList")
    public String searchAdmissionStudentList(String stuSignFlag, String adminFlag, NyzlAdmissionStudent admissionStudent, Integer currentPage, HttpServletRequest request, Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isBlank(admissionStudent.getRecruitYear())){
            admissionStudent.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        admissionStudent.setStuSign(stuSignFlag);
        if("fwh".equals(adminFlag)){
            admissionStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if("pydw".equals(adminFlag)){
            admissionStudent.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlAdmissionStudent> recordList=admissionStudentBiz.searchAdmissionStudentList(admissionStudent);
        if("Y".equals(adminFlag)&&StringUtil.isBlank(admissionStudent.getFwhFlow())){
            recordList=null;
        }
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        List<SysOrg> orgs=orgBiz.searchTrainOrgList();
        model.addAttribute("orgs",orgs);
        model.addAttribute("deptList",deptList);
        model.addAttribute("recordList",recordList);
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            return "/nyzl/summerCamp/admissionStudentList";
        }
        return "/nyzl/doctoralStudent/admissionStudentList";
    }

    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importAdmissionStudent";
    }

    @RequestMapping(value="/leadToSummerCamp")
    public String leadToSummerCamp(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/summerCamp/importAdmissionStudent";
    }
    @RequestMapping(value="/importAdmissionStudent")
    @ResponseBody
    public String importAdmissionStudent(MultipartFile file, String stuSignFlag){
        if(file.getSize() > 0){
            try{
                int result = admissionStudentBiz.importAdmissionStudent(file,stuSignFlag);
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
    @RequestMapping("/exportAdmissionStudent")
    public void exportAdmissionStudent(String stuSignFlag, String adminFlag, NyzlAdmissionStudent admissionStudent,HttpServletResponse response)throws Exception {
        if(StringUtil.isBlank(admissionStudent.getRecruitYear())){
            admissionStudent.setRecruitYear(DateUtil.getYear());
        }
        admissionStudent.setStuSign(stuSignFlag);
        if("fwh".equals(adminFlag)){
            admissionStudent.setFwhFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        List<NyzlAdmissionStudent> recordList=admissionStudentBiz.searchAdmissionStudentList(admissionStudent);
        if(recordList!=null&&recordList.size()>0){
            for (NyzlAdmissionStudent nas:recordList) {
                if(StringUtil.isNotBlank(nas.getAdmissionFlag())){
                    if(GlobalConstant.FLAG_Y.equals(nas.getAdmissionFlag())){
                        nas.setAdmissionFlag("是");
                    }
                    if(GlobalConstant.FLAG_N.equals(nas.getAdmissionFlag())){
                        nas.setAdmissionFlag("否");
                    }
                }
                if(StringUtil.isNotBlank(nas.getSwapFlag())){
                    if(GlobalConstant.FLAG_Y.equals(nas.getSwapFlag())){
                        nas.setSwapFlag("是");
                    }
                    if(GlobalConstant.FLAG_N.equals(nas.getSwapFlag())){
                        nas.setSwapFlag("否");
                    }
                }
            }
        }
        String[] titles;//导出列表头信息
        titles = new String[]{
                "recruitYear:年份",
                "stuNo:考生编号",
                "stuName:姓名",
                "cardNo:身份证号",
                "fwhName:分委会",
                "orgName:培养单位",
                "speId:录取专业代码",
                "speName:录取专业名称",
                "stuSpeDirectionId:录取专业研究方向代码",
                "stuSpeDirectionName:录取专业研究方向",
                "testGrade:初试成绩",
                "retestGrade:复试成绩",
                "totalGrade:总成绩",
                "teaWorkUnit:导师所在单位",
                "teaName:导师姓名",
                "teaDirectionName:导师方向",
                "stuDirectionName:报考方向",
                "groupRanking:复试小组入学总成绩排名",
                "admissionFlag:是否录取",
                "degreeTypeName:学位类型",
                "swapFlag:是否调剂生",
                "swapBatchName:复试批次"
        };
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            titles = new String[]{
                    "recruitYear:年份",
                    "stuName:姓名",
                    "cardNo:身份证号",
                    "fwhName:分委会",
                    "orgName:培养单位",
                    "speId:录取专业代码",
                    "speName:录取专业名称",
                    "stuSpeDirectionId:录取专业研究方向代码",
                    "stuSpeDirectionName:录取专业研究方向",
                    "retestGrade:复试成绩",
                    "totalGrade:总成绩",
                    "teaWorkUnit:导师所在单位",
                    "teaName:导师姓名",
                    "teaDirectionName:导师方向",
                    "stuDirectionName:报考方向",
                    "groupRanking:复试小组入学总成绩排名",
                    "admissionFlag:是否录取",
                    "degreeTypeName:学位类型"
            };
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, recordList,response.getOutputStream());
        String fileName = "录取情况信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    @RequestMapping("/showAdmissionStudent")
    public String showAdmissionStudent(Model model,String recordFlow,String stuSignFlag,String educationType,String adminFlag){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isNotBlank(recordFlow)){
            NyzlAdmissionStudent admissionStudent=admissionStudentBiz.readAdmissionStudent(recordFlow);
            model.addAttribute("admissionStudent",admissionStudent);
        }
        model.addAttribute("orgs",orgBiz.searchTrainOrgList());
//        if("0".equals(educationType)){
//            return "/nyzl/doctoralStudent/editDoctorInfo0";
//        }
//        if(NyzlStuSignEnum.MasterStudent.getId().equals(stuSignFlag)){
//            return "/nyzl/masterStudent/editDoctorInfo";
//        }
        if(NyzlStuSignEnum.SummerCamp.getId().equals(stuSignFlag)){
            return "/nyzl/summerCamp/editAdmissionStudent";
        }
//        if(NyzlStuSignEnum.PushFreeStudent.getId().equals(stuSignFlag)){
//            return "/nyzl/pushFreeStudent/editDoctorInfo";
//        }
        return "/nyzl/doctoralStudent/editAdmissionStudent";
    }

    @RequestMapping("/saveAdmissionStudent")
    @ResponseBody
    public String saveAdmissionStudent(NyzlAdmissionStudent admissionStudent,String adminFlag){
        if(admissionStudent!=null){
            admissionStudent.setDegreeTypeName(DictTypeEnum.NyzlDegreeType.getDictNameById(admissionStudent.getDegreeTypeId()));
            if(StringUtil.isNotBlank(admissionStudent.getSwapBatchId())){
                admissionStudent.setSwapBatchName(DictTypeEnum.NyzlBatch.getDictNameById(admissionStudent.getSwapBatchId()));
            }else{
                admissionStudent.setSwapBatchName("");
            }
            if("1".equals(admissionStudent.getEducationTypeId())){
                admissionStudent.setEducationTypeName("全日制");
            }
            if("0".equals(admissionStudent.getEducationTypeId())){
                admissionStudent.setEducationTypeName("在职");
            }
        }
        int num=admissionStudentBiz.save(admissionStudent);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
}
