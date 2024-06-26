package com.pinde.sci.ctrl.nyzl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.INyzlAdmissionSubjectBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.NyzlAdmissionSubject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nyzl/admissionSubject")
public class NyzlAdmissionSubjectController extends GeneralController{
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private INyzlAdmissionSubjectBiz admissionSubjectBiz;

    @RequestMapping("/admissionSubjectList")
    public String admissionSubjectList(String stuSignFlag, String adminFlag, NyzlAdmissionSubject admissionSubject, Integer currentPage, HttpServletRequest request, Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        PageHelper.startPage(currentPage,getPageSize(request));
        if(StringUtil.isBlank(admissionSubject.getRecruitYear())){
            admissionSubject.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        SysOrg orgTemp=new SysOrg();
        if(StringUtil.isBlank(adminFlag)){
            orgTemp.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if(StringUtil.isNotBlank(admissionSubject.getOrgFlow())){
            orgTemp.setOrgFlow(admissionSubject.getOrgFlow());
        }
        admissionSubject.setStuSign(stuSignFlag);
        List<NyzlAdmissionSubject> recordList=admissionSubjectBiz.searchAdmissionSubjectList(admissionSubject);
        if("Y".equals(adminFlag)&&StringUtil.isBlank(admissionSubject.getOrgFlow())){
            recordList=null;
        }
        List<SysOrg> orgs=orgBiz.searchTrainOrgList();
        //指标数
        Map<String ,String> countQuota=admissionSubjectBiz.countAdmissionSubject(admissionSubject.getRecruitYear(),stuSignFlag,admissionSubject.getOrgFlow());
        model.addAttribute("countQuota",countQuota);
        model.addAttribute("recordList",recordList);
        model.addAttribute("orgs",orgs);
//        if(NyzlStuSignEnum.DoctoralStudent.getId().equals(stuSignFlag)){
            return "/nyzl/doctoralStudent/admissionSubjectList";
//        }
//        return "";
    }

    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importAdmissionSubject";
    }

    @RequestMapping(value="/importAdmissionSubject")
    @ResponseBody
    public String importAdmissionSubject(MultipartFile file, String stuSignFlag){
        if(file.getSize() > 0){
            try{
                int result = admissionSubjectBiz.importAdmissionSubject(file,stuSignFlag);
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

    @RequestMapping("/exportAdmissionSubject")
    public void exportAdmissionSubject(String stuSignFlag, String adminFlag, NyzlAdmissionSubject admissionSubject,HttpServletResponse response)throws Exception {
        String orgName="";
        List<Map<String,String>> dataList=new ArrayList<>();
        if(StringUtil.isBlank(admissionSubject.getRecruitYear())){
            admissionSubject.setRecruitYear(DateUtil.getYear());
        }
        if(StringUtil.isBlank(adminFlag)){
            admissionSubject.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if(StringUtil.isNotBlank(admissionSubject.getOrgFlow())){
            SysOrg sysOrg=orgBiz.readSysOrg(admissionSubject.getOrgFlow());
            if(sysOrg!=null){
                orgName=sysOrg.getOrgName();
            }
        }
        admissionSubject.setStuSign(stuSignFlag);
        List<NyzlAdmissionSubject> recordList=admissionSubjectBiz.searchAdmissionSubjectList(admissionSubject);
        if(recordList!=null&&recordList.size()>0){
            for(NyzlAdmissionSubject nas:recordList){
                Map<String,String> tempMap=new HashMap<>();
                if(nas!=null){
                    String recruitYear=nas.getRecruitYear();
                    String speName=nas.getSpeName();
                    String directionName=nas.getDirectionName();
                    int num1=nas.getTkAcademicNum()==null?0:Integer.parseInt(nas.getTkAcademicNum());
                    int num11=nas.getAcademicAdmissionNum()==null?0:Integer.parseInt(nas.getAcademicAdmissionNum());
                    int num12=nas.getAcademicSurplusNum()==null?0:Integer.parseInt(nas.getAcademicSurplusNum());
                    int num2=nas.getTkSpecialNum()==null?0:Integer.parseInt(nas.getTkSpecialNum());
                    int num21=nas.getSpecialAdmissionNum()==null?0:Integer.parseInt(nas.getSpecialAdmissionNum());
                    int num22=nas.getSpecialSurplusNum()==null?0:Integer.parseInt(nas.getSpecialSurplusNum());
                    int num3=nas.getTmsAcademicNum()==null?0:Integer.parseInt(nas.getTmsAcademicNum());
                    int num4=nas.getTmsSpecialNum()==null?0:Integer.parseInt(nas.getTmsSpecialNum());
                    int totleNum=num1+num2+num3+num4;
                    tempMap.put("recruitYear",recruitYear);
                    tempMap.put("speName",speName);
                    tempMap.put("directionName",directionName);
                    tempMap.put("num1",num1+"");
                    tempMap.put("num11",num11+"");
                    tempMap.put("num12",num12+"");
                    tempMap.put("num2",num2+"");
                    tempMap.put("num21",num21+"");
                    tempMap.put("num22",num22+"");
                    tempMap.put("num3",num3+"");
                    tempMap.put("num4",num4+"");
                    tempMap.put("totleQuota",totleNum+"");
                    dataList.add(tempMap);
                }
            }
        }
        String[] titles;//导出列表头信息
        titles = new String[]{
                "recruitYear:年份",
                "speName:专业名称",
                "directionName:方向",
                "num1:统考学术型指标",
                "num11:实际录取人数",
                "num12:剩余指标数",
                "num2:统考专业型指标",
                "num21:实际录取人数",
                "num22:剩余指标数",
                "num3:推免生数（学术型）",
                "num4:推免生数（专业型）",
                "totleQuota:总指标数"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream());
        String fileName = orgName+"学科录取信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
