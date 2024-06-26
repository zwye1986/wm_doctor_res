package com.pinde.sci.ctrl.nyzl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlVacancySwapBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.NyzlVacancySwap;
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
@RequestMapping("/nyzl/vacancySwap")
public class NyzlVacancySwapController extends GeneralController{
    @Autowired
    private INyzlVacancySwapBiz vacancySwapBiz;
    @Autowired
    private IOrgBiz orgBiz;

    @RequestMapping("/vacancySwapList")
    public String searchVacancySwapList(String stuSignFlag, String adminFlag, NyzlVacancySwap vacancySwap, Integer currentPage, HttpServletRequest request, Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        if(StringUtil.isBlank(vacancySwap.getRecruitYear())){
            vacancySwap.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        vacancySwap.setStuSign(stuSignFlag);
        if(StringUtil.isBlank(adminFlag)){
            vacancySwap.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlVacancySwap> recordList=vacancySwapBiz.searchVacancySwapList(vacancySwap);
        if("Y".equals(adminFlag)&&StringUtil.isBlank(vacancySwap.getOrgFlow())){
            recordList=null;
        }
        List<SysOrg> orgs=orgBiz.searchTrainOrgList();
        model.addAttribute("orgs",orgs);
        model.addAttribute("recordList",recordList);
//        if(NyzlStuSignEnum.DoctoralStudent.getId().equals(stuSignFlag)){
            return "/nyzl/doctoralStudent/vacancySwapList";
//        }
//        return "";
    }
    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importVacancySwap";
    }

    @RequestMapping(value="/importVacancySwap")
    @ResponseBody
    public String importVacancySwap(MultipartFile file, String stuSignFlag){
        if(file.getSize() > 0){
            try{
                int result = vacancySwapBiz.importVacancySwap(file,stuSignFlag);
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

    @RequestMapping("/exportVacancySwap")
    public void exportRecruitQuota(String stuSignFlag, String adminFlag, NyzlVacancySwap vacancySwap,HttpServletResponse response)throws Exception {
        if(StringUtil.isBlank(vacancySwap.getRecruitYear())){
            vacancySwap.setRecruitYear(DateUtil.getYear());
        }
        vacancySwap.setStuSign(stuSignFlag);
        if(StringUtil.isBlank(adminFlag)){
            vacancySwap.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<NyzlVacancySwap> recordList=vacancySwapBiz.searchVacancySwapList(vacancySwap);
        String[] titles;//导出列表头信息
        titles = new String[]{
                "recruitYear:年份",
                "speId:专业代码",
                "degreeTypeName:学位类型",
                "speName:专业名称",
                "directionName:方向",
                "fullNum:上线人数",
                "vacancyNum:缺额人数",
                "basciConditions:接收调剂考生条件（初试成绩）",
                "otherConditions:接收调剂考生条件（其他要求）"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, recordList,response.getOutputStream());
        String fileName = "调剂缺额信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
