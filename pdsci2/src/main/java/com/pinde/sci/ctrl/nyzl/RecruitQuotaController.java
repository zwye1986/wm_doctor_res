package com.pinde.sci.ctrl.nyzl;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.nyzl.IRecruitQuotaBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.NyzlRecruitQuota;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nyzl/recruitQuota")
public class RecruitQuotaController extends GeneralController{
    @Autowired
    private IRecruitQuotaBiz recruitQuotaBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @RequestMapping("/recruitQuotaList")
    public String recruitQuotaList(String stuSignFlag, String adminFlag, NyzlRecruitQuota recruitQuota, Integer currentPage, HttpServletRequest request, Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        model.addAttribute("adminFlag",adminFlag);
        PageHelper.startPage(currentPage,getPageSize(request));
        if(StringUtil.isBlank(recruitQuota.getRecruitYear())){
            recruitQuota.setRecruitYear(DateUtil.getYear());
            model.addAttribute("thisYear",DateUtil.getYear());
        }
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",recruitQuota.getRecruitYear());
        String fwhFlow=recruitQuota.getFwhFlow();
        if("fwh".equals(adminFlag)){
            fwhFlow=GlobalContext.getCurrentUser().getDeptFlow();
            map.put("publishFlag",GlobalConstant.FLAG_Y);
        }
        map.put("fwhFlow",fwhFlow);
        map.put("stuSign",stuSignFlag);
        List<Map<String,String>> dataList=recruitQuotaBiz.searchFwhList(map);
        SysOrg org=orgBiz.readSysOrgByName("南方医科大学");
        List<SysDept> deptList=new ArrayList<>();
        if(org!=null){
            deptList=deptBiz.searchDeptByOrg(org.getOrgFlow());
        }
        //指标数
        Map<String ,String> countQuota=recruitQuotaBiz.countRecruitQuota(recruitQuota.getRecruitYear(),stuSignFlag,fwhFlow);
        model.addAttribute("countQuota",countQuota);
        model.addAttribute("recordList",dataList);
        model.addAttribute("deptList",deptList);
//        if(NyzlStuSignEnum.DoctoralStudent.getId().equals(stuSignFlag)){
            return "/nyzl/doctoralStudent/recruitQuotaList";
//        }
//        return "";
    }
    //指标分配
    @RequestMapping("/updateRecruitQuota")
    @ResponseBody
    public String updateRecruitQuota(String jsonData){
        int num=0;
        Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
        Map<String,String> dataMap = (Map<String,String>)mp.get("data");
        NyzlRecruitQuota recruitQuota=new NyzlRecruitQuota();
        if(dataMap!=null){
            recruitQuota.setRecordFlow(dataMap.get("recordFlow"));
            recruitQuota.setFwhFlow(dataMap.get("fwhFlow"));
            recruitQuota.setRecruitYear(dataMap.get("recruitYear"));
            recruitQuota.setStuSign(dataMap.get("stuSign"));
            recruitQuota.setTkAcademicNum(dataMap.get("tkAcademicNum"));
            recruitQuota.setTkSpecialNum(dataMap.get("tkSpecialNum"));
            recruitQuota.setTmsAcademicNum(dataMap.get("tmsAcademicNum"));
            recruitQuota.setTmsSpecialNum(dataMap.get("tmsSpecialNum"));
        }
        if(recruitQuota!=null&&StringUtil.isNotBlank(recruitQuota.getFwhFlow())){
            SysDept sysDept=deptBiz.readSysDept(recruitQuota.getFwhFlow());
            if(sysDept!=null){
                recruitQuota.setFwhName(sysDept.getDeptName());
            }
            recruitQuota.setPublisherName(GlobalContext.getCurrentUser().getUserName());
            num=recruitQuotaBiz.save(recruitQuota);
        }
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping("/publishQuota")
    @ResponseBody
    public String publishQuota(String stuSignFlag, String recruitYear){
        int num = 0;
        if(StringUtil.isNotBlank(stuSignFlag)&&StringUtil.isNotBlank(recruitYear)){
            NyzlRecruitQuota recruitQuota=new NyzlRecruitQuota();
            recruitQuota.setStuSign(stuSignFlag);
            recruitQuota.setRecruitYear(recruitYear);
            List<NyzlRecruitQuota> list=recruitQuotaBiz.searchRecruitQuotaList(recruitQuota);
            if(list!=null&&list.size()>0){
                for(NyzlRecruitQuota nrq:list){
                    nrq.setPublishFlag(GlobalConstant.FLAG_Y);
                    int count=recruitQuotaBiz.save(nrq);
                    if(count>0){
                        num++;
                    }
                }
            }
        }
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/leadTo")
    public String leadTo(String stuSignFlag,Model model){
        model.addAttribute("stuSignFlag",stuSignFlag);
        return "/nyzl/doctoralStudent/importRecruitQuota";
    }

    @RequestMapping(value="/importRecruitQuota")
    @ResponseBody
    public String importRecruitQuota(MultipartFile file, String stuSignFlag){
        if(file.getSize() > 0){
            try{
                int result = recruitQuotaBiz.importRecruitQuota(file,stuSignFlag);
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

    @RequestMapping("/exportRecruitQuota")
    public void exportRecruitQuota(String stuSignFlag, String adminFlag, NyzlRecruitQuota recruitQuota,HttpServletResponse response)throws Exception {
        List<Map<String,String>> dataList=new ArrayList<>();
        if(StringUtil.isBlank(recruitQuota.getRecruitYear())){
            recruitQuota.setRecruitYear(DateUtil.getYear());
        }
        String year=recruitQuota.getRecruitYear();
        Map<String,String> map=new HashMap<>();
        map.put("recruitYear",year);
        String fwhFlow=recruitQuota.getFwhFlow();
        if("fwh".equals(adminFlag)){
            fwhFlow=GlobalContext.getCurrentUser().getDeptFlow();
            map.put("publishFlag",GlobalConstant.FLAG_Y);
        }
        map.put("fwhFlow",fwhFlow);
        map.put("stuSign",stuSignFlag);
        dataList=recruitQuotaBiz.searchFwhList(map);
        if(dataList!=null&&dataList.size()>0){
            for(Map<String,String> data:dataList){
                data.put("RECRUIT_YEAR",year);
                String num1=StringUtil.isBlank(data.get("TK_ACADEMIC_NUM"))?"0":data.get("TK_ACADEMIC_NUM");
                String num2=StringUtil.isBlank(data.get("TK_SPECIAL_NUM"))?"0":data.get("TK_SPECIAL_NUM");
                String num3=StringUtil.isBlank(data.get("TMS_ACADEMIC_NUM"))?"0":data.get("TMS_ACADEMIC_NUM");
                String num4=StringUtil.isBlank(data.get("TMS_SPECIAL_NUM"))?"0":data.get("TMS_SPECIAL_NUM");
                data.put("num1",num1);
                data.put("num2",num2);
                data.put("num3",num3);
                data.put("num4",num4);
                int totleQuota=Integer.parseInt(num1)+Integer.parseInt(num2)+Integer.parseInt(num3)+Integer.parseInt(num4);
                data.put("totleQuota",totleQuota+"");
            }
        }
        String[] titles;//导出列表头信息
        titles = new String[]{
                "RECRUIT_YEAR:年份",
                "DEPT_NAME:分委会",
                "num1:统考学术型指标",
                "num2:统考专业型指标",
                "num3:推免生数（学术型）",
                "num4:推免生数（专业型）",
                "totleQuota:总指标数"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream());
        String fileName = "招生指标信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
