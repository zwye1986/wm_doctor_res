package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.biz.sch.ISchExamScoreQueryBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/jsres/examCfg")
public class JsResExamCfgController extends GeneralController {

    @Autowired
    private  ISchExamCfgBiz examCfgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchExamScoreQueryBiz scoreQueryBiz;

    @RequestMapping(value="/examArrangMent")
    public String examArrangMent(Model model) {
        return "jsres/hospital/examCfg/examCfgMain";
    }

    @RequestMapping(value="/examArrangMentAcc")
    public String examArrangMentAcc(Model model) {
        return "jsres/hospital/examCfg/examCfgMainAcc";
    }

    @RequestMapping(value="/edit")
    public String edit(Model model,String arrangeFlow) {
        SchExamArrangement ment=examCfgBiz.readByFlow(arrangeFlow);
        model.addAttribute("ment",ment);
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("currentOrg",currentOrg);
        List<SchExamStandardDept> depts=examCfgBiz.readStandardDeptsByFlow(arrangeFlow);
        if(depts!=null&&depts.size()>0)
        {
            List<String> standardList=new ArrayList<>();
            for(SchExamStandardDept dept:depts)
            {
                standardList.add(dept.getStandardDeptId());
            }
            model.addAttribute("standardList",standardList);
        }
        model.addAttribute("depts",depts);
        SysDept dept=new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        dept.setOrgFlow(currentUser.getOrgFlow());
        List<Map<String, String>> list = deptBiz.searchDeptByUnion(dept, com.pinde.core.common.GlobalConstant.FLAG_Y);
        model.addAttribute("all",list);
        if(StringUtil.isBlank(arrangeFlow))
        {
            return "jsres/hospital/examCfg/editAdd";
        }
        return "jsres/hospital/examCfg/edit";
    }
    @RequestMapping(value="/list")
    public String list(Model model,Integer currentPage ,HttpServletRequest request,
                       SchExamArrangement schExamArrangement  ){
        SysUser sysuser=GlobalContext.getCurrentUser();
        schExamArrangement.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        schExamArrangement.setOrgFlow(sysuser.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<SchExamArrangement> list=examCfgBiz.searchList(schExamArrangement);
        model.addAttribute("list",list);
        return "jsres/hospital/examCfg/list";
    }
    @RequestMapping(value="/updateArrangement")
    @ResponseBody
    public String updateArrangement(SchExamArrangement schExamArrangement ,String[] standardDeptId ){
        int c=examCfgBiz.checkExists(schExamArrangement);
        if(c>0)
        {
            return "cannotInsert";
        }
        int result = examCfgBiz.updateArrangement(schExamArrangement, standardDeptId, com.pinde.core.common.GlobalConstant.FLAG_N);
        if(result==0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }
    @RequestMapping(value="/updateArrangementAdd")
    @ResponseBody
    public String updateArrangementAdd(SchExamArrangement schExamArrangement ,String[] standardDeptId,String[] itemId,String sessionNumbers){
        if(itemId.length==0)
        {
            return "请选择培训专业！";
        }
        if(sessionNumbers.split(",").length==0)
        {
            return "请选择年级！";
        }
        int c=examCfgBiz.checkExists(schExamArrangement,Arrays.asList(itemId),Arrays.asList(sessionNumbers.split(",")));
        if(c>0)
        {
            return "cannotInsert";
        }
        int result=examCfgBiz.updateArrangements(schExamArrangement,standardDeptId,Arrays.asList(itemId),Arrays.asList(sessionNumbers.split(",")));
        if(result==0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }
    @RequestMapping(value="/updateCfg")
    @ResponseBody
    public String updateCfg(Model model,  SchExamArrangement schExamArrangement  ){
        //删除时，校验是否已有学生考过试，并有成绩的，无法删除
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(schExamArrangement.getRecordStatus()))
        {
            int checkCount=examCfgBiz.checkHaveExam(schExamArrangement.getArrangeFlow());
            if(checkCount>0)
                return "已有学员参加过考试，无法删除！";
        }
        int result=examCfgBiz.updateCfg(schExamArrangement);
        if(result==0)
        {
            return "操作失败！";
        }
        return "操作成功！";
    }

    /**
     * 学员角色年度理论考试
     * @param model
     * @return
     */
    @RequestMapping(value="/theoreticalExam")
    public String theoreticalExam(Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = doctorBiz.readDoctor(currentUser.getUserFlow());
        String orgFlow="";
        if(resDoctor!=null) {
            if (StringUtil.isNotBlank(resDoctor.getSecondOrgFlow())) {
                orgFlow = resDoctor.getSecondOrgFlow();
            } else {
                orgFlow = resDoctor.getOrgFlow();
            }
        }
        SchExamArrangement schExamArrangement = new SchExamArrangement();
        schExamArrangement.setOrgFlow(orgFlow);
        schExamArrangement.setSessionNumber(resDoctor.getSessionNumber());
        schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
        schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
        List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
        //查询条件
        Map<String,Object> param = new HashMap<>();
        List<String> userFlows = new ArrayList<>();
        userFlows.add(currentUser.getUserFlow());
        param.put("orgFlow",orgFlow);
        param.put("userFlows",userFlows);
        List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
        if(doctorArrangements != null && doctorArrangements.size() > 0){
            Map<String,SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
            for(SchExamDoctorArrangement da : doctorArrangements)
            {
                // 判断是否开放成绩查看权限，未开放则将学员成绩以**展示
                SchExamArrangement examArrangement = examCfgBiz.readByFlow(da.getArrangeFlow());
                if (StringUtil.isNotBlank(examArrangement.getIsOpenResult()) && examArrangement.getIsOpenResult().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                    da.setExamScore(new BigDecimal(-20));
                }
                doctorArrangementMap.put(da.getArrangeFlow(),da);
            }
            model.addAttribute("daMap",doctorArrangementMap);
        }
        Map<String,Map<String,String>> examLogMaps = null;
        if(examArrangements != null && examArrangements.size() > 0){
            examLogMaps = new HashMap<>();
            for(SchExamArrangement tempExam : examArrangements){
                Map<String,String> paramMap = new HashMap<>();
                paramMap.put("arrangeFlow",tempExam.getArrangeFlow());
                paramMap.put("doctorFlow",resDoctor.getDoctorFlow());
                List<Map<String,String>> examArrangementMaps = examCfgBiz.searchExamLogByItems(paramMap);
                if(examArrangementMaps != null && examArrangementMaps.size() > 0){
                    for(Map<String,String> tempMap : examArrangementMaps){
                        Map<String,String> paramTempMap = new HashMap<>();
                        paramTempMap.put("countNum",tempMap.get("COUNTNUM"));
                        paramTempMap.put("maxScore",tempMap.get("MAXSCORE"));
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsWeb()) &&
                                Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))){
                            paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                        examLogMaps.put(tempMap.get("ARRANGEFLOW"),paramTempMap);
                    }
                }else {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsWeb())) {
                        Map<String,String> paramTempMap = new HashMap<>();
                        paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        examLogMaps.put(tempExam.getArrangeFlow(),paramTempMap);
                    }
                }
            }
            model.addAttribute("examLogMaps",examLogMaps);
        }
        model.addAttribute("examArrangements",examArrangements);
        return "jsres/doctor/examCfg/examList";
    }

}
