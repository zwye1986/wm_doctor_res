package com.pinde.sci.ctrl.nyzl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlTeacherQuotaBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.NyzlTeacherQuota;
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
import java.util.List;

@Controller
@RequestMapping("/nyzl/teacherQuota")
public class NyzlTeacherQuotaController extends GeneralController{
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private INyzlTeacherQuotaBiz teacherQuotaBiz;

    @RequestMapping("/teacherQuotaList")
    public String searchTeacherQuotaList(String stuSignFlag, String adminFlag, NyzlTeacherQuota teacherQuota, Integer currentPage, HttpServletRequest request, Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isBlank(teacherQuota.getRecruitYear())){
            teacherQuota.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        teacherQuota.setStuSign(stuSignFlag);
        if(StringUtil.isBlank(adminFlag)){
            teacherQuota.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlTeacherQuota> recordList=teacherQuotaBiz.searchTeacherQuotaList(teacherQuota);
        if("Y".equals(adminFlag)&&StringUtil.isBlank(teacherQuota.getOrgFlow())){
            recordList=null;
        }
        List<SysOrg> orgs=orgBiz.searchTrainOrgList();
        model.addAttribute("orgs",orgs);
        model.addAttribute("recordList",recordList);
//        if(NyzlStuSignEnum.DoctoralStudent.getId().equals(stuSignFlag)){
            return "/nyzl/doctoralStudent/teacherQuotaList";
//        }
//        return "";
    }

    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importTeacherQuota";
    }

    @RequestMapping(value="/importTeacherQuota")
    @ResponseBody
    public String importTeacherQuota(MultipartFile file, String stuSignFlag){
        if(file.getSize() > 0){
            try{
                int result = teacherQuotaBiz.importTeacherQuota(file,stuSignFlag);
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
    @RequestMapping("/exportTeacherQuota")
    public void exportTeacherQuota(String stuSignFlag, String adminFlag, NyzlTeacherQuota teacherQuota,HttpServletResponse response)throws Exception {
        if(StringUtil.isBlank(teacherQuota.getRecruitYear())){
            teacherQuota.setRecruitYear(DateUtil.getYear());
        }
        teacherQuota.setStuSign(stuSignFlag);
        if(StringUtil.isBlank(adminFlag)){
            teacherQuota.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlTeacherQuota> recordList=teacherQuotaBiz.searchTeacherQuotaList(teacherQuota);
        String[] titles;//导出列表头信息
        titles = new String[]{
                "recruitYear:年份",
                "speName:专业名称",
                "directionName:方向",
                "teacherName:导师姓名",
                "quotaNum:导师指标",
                "finishNum:实际完成"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, recordList,response.getOutputStream());
        String fileName = "导师录取信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
