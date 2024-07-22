package com.pinde.sci.ctrl.jszy;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyGraduationBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.biz.res.ResPaperBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/jszy/graduation")
public class JszyGraduationController extends GeneralController {

    @Autowired
    private IJszyGraduationBiz graduationBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ResPaperBiz paperBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;

    @RequestMapping(value="/queryScore")
    public String examQueryScore(Model model) {
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            return "jszy/hospital/graduationManage/main4jszy";
        }
        return "jszy/hospital/graduationManage/main";
    }

    @RequestMapping(value="/scorelist")
    public String scorelist(Model model,Integer currentPage ,HttpServletRequest request,String doctorCategoryId,
                            String trainingSpeId,String sessionNumber,String[] doctorTypeIdList,
                            String userName){
        SysUser sysuser=GlobalContext.getCurrentUser();

        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);

        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//        doctor.setOrgFlow("8133861fd9d54e4b8ef92be7b052e15f");
        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        if (doctorTypeIdList1 == null) {
            doctorTypeIdList1 = new ArrayList<>();
            for (SysDict dict : dictList) {
                doctorTypeIdList1.add(dict.getDictId());
                doctorTypeSelectMap.put(dict.getDictId(), "checked");
            }
        }
        model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
//        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        model.addAttribute("list",list);

        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
            param.put("orgFlow",sysuser.getOrgFlow());
            param.put("userFlows",userFlows);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String,Object>> examResults = graduationBiz.getExamResults(param);
            model.addAttribute("examResults",examResults);
            if(examResults!=null&&examResults.size()>0){
                Map<String,Object> examResultsMap=new HashMap<>();
                for (Map<String,Object> map:examResults) {
                    int qN = Integer.parseInt(map.get("qualifiedNum").toString());
                    int tN = Integer.parseInt(map.get("totalNum").toString());
                    DecimalFormat df=new DecimalFormat("0");
                    examResultsMap.put((String)map.get("doctorFlow"),df.format((float)qN/tN*100)+"%");
                }
                model.addAttribute("daMap",examResultsMap);
            }
        }
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            return "jszy/hospital/graduationManage/list4jszy";
        }
        return "jszy/hospital/graduationManage/list";
    }

    @RequestMapping(value="searchScore")
    public String searchScore(String doctorFlow,String userName,String speName, Model model,
                              HttpServletRequest request,Integer currentPage){
        model.addAttribute("doctorFlow",doctorFlow);
        return "jszy/hospital/graduationManage/graduationExamList";
    }
    @RequestMapping(value="searchScore2")
    public String searchScore2(String doctorFlow,String userName,String speName, Model model,
                               HttpServletRequest request,Integer currentPage){
        model.addAttribute("doctorName",userName);
        model.addAttribute("speName",speName);
        SysUser user=userBiz.readSysUser(doctorFlow);
        ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
        model.addAttribute("user",user);
        model.addAttribute("doctor",doctor);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> graduationExamList = graduationBiz.findExamBydoctorFlow(doctorFlow,user.getOrgFlow());
        model.addAttribute("graduationExamList",graduationExamList);
        return "jszy/hospital/graduationManage/examsults";
    }

    @RequestMapping(value="/exportInfo")
    public void exportInfo(Model model,HttpServletRequest request,HttpServletResponse response,
                           String doctorCategoryId,String trainingSpeId,String[] doctorTypeIdList,
                           String sessionNumber, String userName) throws IOException {

        SysUser sysuser=GlobalContext.getCurrentUser();

        ResDoctorExt doctor=new ResDoctorExt();
        doctor.setSessionNumber(sessionNumber);
        doctor.setTrainingSpeId(trainingSpeId);
        doctor.setDoctorCategoryId(doctorCategoryId);

        SysUser d=new SysUser();
        d.setUserName(userName);
        doctor.setSysUser(d);
        doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//        doctor.setOrgFlow("8133861fd9d54e4b8ef92be7b052e15f");

        //人员类型复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        List<String> doctorTypeIdList1 = Arrays.asList(doctorTypeIdList);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIdList1!=null&&doctorTypeIdList1.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIdList1.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        if (doctorTypeIdList1 == null) {
            doctorTypeIdList1 = new ArrayList<>();
            for (SysDict dict : dictList) {
                doctorTypeIdList1.add(dict.getDictId());
                doctorTypeSelectMap.put(dict.getDictId(), "checked");
            }
        }
        doctor.setDoctorTypeIdList(doctorTypeIdList1);
        List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
        Map<String,Object> rdMap = new HashMap<>();
        for (ResDoctorExt rd:list) {
            rdMap.put(rd.getDoctorFlow(),rd);
        }
        model.addAttribute("list",list);

        List<Map<String,Object>> examResults = new ArrayList<>();
        if(list!=null&&list.size()>0)
        {
            List<String> userFlows=new ArrayList<>();
            for(ResDoctorExt user:list)
            {
                userFlows.add(user.getDoctorFlow());
            }//查询条件
            Map<String,Object> param=new HashMap<>();
            param.put("userFlows",userFlows);
            examResults = graduationBiz.getExamResults(param);
            model.addAttribute("examResults",examResults);
            if(examResults!=null&&examResults.size()>0){
                Map<String,Object> examResultsMap=new HashMap<>();
                for (Map<String,Object> map:examResults) {
                    int qN = Integer.parseInt(map.get("qualifiedNum").toString());
                    int tN = Integer.parseInt(map.get("totalNum").toString());
                    DecimalFormat df=new DecimalFormat("0");
                    examResultsMap.put((String)map.get("doctorFlow"),df.format((float)qN/tN*100)+"%");
                }
                model.addAttribute("daMap",examResultsMap);
            }
        }
        graduationBiz.exportInfo(rdMap,examResults,response);
    }

    @RequestMapping(value="/toJYTest")
    public String toJYTest(Model model,HttpServletRequest request,Integer currentPage){
        String errorPage = "jszy/doctor/graduationManage/graduationExamList";
        SysUser sysUser = GlobalContext.getCurrentUser();
        ResPowerCfg powerCfg = resPowerCfgBiz.read("res_doctor_graduation_exam_"+ sysUser.getUserFlow());
        if(powerCfg == null || !powerCfg.getCfgValue().equals("Y")){
            model.addAttribute("errorMeg1","暂无权限参加考试！");
            return errorPage;
        }
        if(sysUser==null)
        {
            model.addAttribute("errorMeg","无个人信息，无法参加考试！");
            return errorPage;
        }
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(sysUser.getUserFlow());
        if(doctor==null)
        {
            model.addAttribute("errorMeg","无医师信息，无法参加考试！");
            return errorPage;
        }
        if(StringUtil.isBlank(doctor.getTrainingSpeId()))
        {
            model.addAttribute("errorMeg","医师无培训专业信息，无法参加考试！");
            return errorPage;
        }
        //试卷id
        String ExamSoluID = "0";
        if(doctor!=null){
            //专业
            String speId = doctor.getTrainingSpeId();
            ResPaper paper =  paperBiz.getPaperBySpeId(speId);

            if(paper!=null){
                ExamSoluID = paper.getPaperFlow();
            }
            if("0".equals(ExamSoluID)){
                model.addAttribute("errorMeg","该专业下暂无试卷信息！");
                return errorPage;
            }
            model.addAttribute("paperName",paper.getPaperName());
//            searchScore(sysUser.getUserFlow(),sysUser.getUserName(),doctor.getTrainingSpeName(), model,
//                    request,currentPage);
        }
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("doctorFlow",sysUser.getUserFlow());
        return errorPage;
    }

    @RequestMapping(value="/showErrorInfo")
    public String showErrorInfo(Model model,String resultId) {
        String errorPage = "jszy/doctor/graduationManage/graduationExamList";

        if(!StringUtil.isNotBlank(resultId)){
            model.addAttribute("errorMeg","当前考试试卷ID为空！");
            return errorPage;
        }

        //考试地址
        String testUrl = InitConfig.getSysCfg("res_after_wrong_exam_url_cfg");
        if(!StringUtil.isNotBlank(testUrl)){
            model.addAttribute("errorMeg","请联系管理员维护错题查看地址！");
            return errorPage;
        }

        GraduationExamResults results = graduationBiz.getResultByFlow(resultId);

        if(results==null) {
            model.addAttribute("errorMeg","当前考试记录信息获取失败！");
            return errorPage;
        }

        //当前用户
        model.addAttribute("RequestType","pc");
        model.addAttribute("SoluID",results.getSoluId());
        model.addAttribute("ResultID",resultId);
        return "redirect:"+testUrl;
    }
}
