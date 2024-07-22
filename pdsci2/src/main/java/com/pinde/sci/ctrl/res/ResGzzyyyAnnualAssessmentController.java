package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.res.IGzzyyyAssessmentBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.GzzyyyAnnualAssessment;
import com.pinde.sci.model.mo.GzzyyyQuarterlyAssessment;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/res/gzzyyyAssessment")
public class ResGzzyyyAnnualAssessmentController extends GeneralController{
    @Autowired
    private IGzzyyyAssessmentBiz assessmentBiz;

    @RequestMapping("/annualAssessmentList")
    public String annualAssessment(GzzyyyAnnualAssessment annualAssessment, Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        annualAssessment.setOrgFlow(currentUser.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List< GzzyyyAnnualAssessment> annualAssessmentList = assessmentBiz.searchAnnualAssessment(annualAssessment);
        model.addAttribute("annualAssessmentList",annualAssessmentList);
        return "res/gzzyyyAssessment/annualAssessmentList";
    }

    @RequestMapping("/delAnnualAssessment")
    @ResponseBody
    public int delAnnualAssessment(GzzyyyAnnualAssessment annualAssessment){
        annualAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return assessmentBiz.editAnnualAssessment(annualAssessment);
    }

    /**
     * 导入分数页面
     */
    @RequestMapping("/importAnnualAssessmentPage")
    public String importAnnualAssessmentPage(){
        return "res/gzzyyyAssessment/importAnnualAssessmentPage";
    }

    @RequestMapping(value="/importAnnualAssessment")
    @ResponseBody
    public String importAnnualAssessment(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = assessmentBiz.importAnnualAssessment(file);
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

    @RequestMapping("/quarterlyAssessmentList")
    public String quarterlyAssessmentList(GzzyyyQuarterlyAssessment quarterlyAssessment,Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        quarterlyAssessment.setOrgFlow(currentUser.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<GzzyyyQuarterlyAssessment> quarterlyAssessmentList = assessmentBiz.searchQuarterlyAssessment(quarterlyAssessment);
        model.addAttribute("quarterlyAssessmentList",quarterlyAssessmentList);
        return "res/gzzyyyAssessment/quarterlyAssessmentList";
    }

    @RequestMapping("/delQuarterlyAssessment")
    @ResponseBody
    public int delQuarterlyAssessment(GzzyyyQuarterlyAssessment quarterlyAssessment){
        quarterlyAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return assessmentBiz.editQuarterlyAssessment(quarterlyAssessment);
    }

    /**
     * 导入分数页面
     */
    @RequestMapping("/importQuarterlyAssessmentPage")
    public String importQuarterlyAssessmentPage(){
        return "res/gzzyyyAssessment/importQuarterlyAssessmentPage";
    }

    @RequestMapping(value="/importQuarterlyAssessment")
    @ResponseBody
    public String importQuarterlyAssessment(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = assessmentBiz.importQuarterlyAssessment(file);
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

    //学员查看---------------------------------------------
    @RequestMapping("/annualAssessmentList4doctor")
    public String annualAssessmentList4doctor(GzzyyyAnnualAssessment annualAssessment, Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        annualAssessment.setDoctorFlow(currentUser.getUserFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List< GzzyyyAnnualAssessment> annualAssessmentList = assessmentBiz.searchAnnualAssessment(annualAssessment);
        model.addAttribute("annualAssessmentList",annualAssessmentList);
        return "res/gzzyyyAssessment/annualAssessmentList4doctor";
    }

    @RequestMapping("/quarterlyAssessmentList4doctor")
    public String quarterlyAssessmentList4doctor(GzzyyyQuarterlyAssessment quarterlyAssessment,Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        quarterlyAssessment.setDoctorFlow(currentUser.getUserFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<GzzyyyQuarterlyAssessment> quarterlyAssessmentList = assessmentBiz.searchQuarterlyAssessment(quarterlyAssessment);
        model.addAttribute("quarterlyAssessmentList",quarterlyAssessmentList);
        return "res/gzzyyyAssessment/quarterlyAssessmentList4doctor";
    }
}
