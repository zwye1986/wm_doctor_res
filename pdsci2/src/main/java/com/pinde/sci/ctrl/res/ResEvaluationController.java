package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.EvaluationTypeEnum;
import com.pinde.core.model.DeptTeacherGradeInfo;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResEvaluationCfgBiz;
import com.pinde.sci.biz.res.IResGradeBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.res.ResEvaluationCfgForm;
import com.pinde.sci.form.res.ResEvaluationCfgItemForm;
import com.pinde.sci.form.res.ResEvaluationCfgTitleForm;
import com.pinde.sci.form.res.ResEvaluationDeptExt;
import com.pinde.sci.model.mo.ResEvaluationCfg;
import com.pinde.sci.model.mo.ResEvaluationDept;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SysCfg;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/evaluation")
public class ResEvaluationController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResCfgController.class);

    @Autowired
    private IResEvaluationCfgBiz evaluationCfgBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;

    /**
     * 跳转至考核指标配置
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/evaluationCfg")
    public String evaluationCfg(String cfgCodeId,Model model) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null ){
            if(StringUtil.isBlank(cfgCodeId)){
                cfgCodeId = evaluationCfgBiz.getNextCodeId(currUser.getOrgFlow(), EvaluationTypeEnum.DoctorEval.getId());
                model.addAttribute("cfgCodeId", cfgCodeId);
                //隐藏按钮标识
                String flag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                model.addAttribute("flag",flag);
            }else{
                ResEvaluationCfg search = new ResEvaluationCfg();
                search.setCfgCodeId(cfgCodeId);
                search.setAssessTypeId(EvaluationTypeEnum.DoctorEval.getId());
                search.setOrgFlow(currUser.getOrgFlow());
                List<ResEvaluationCfg> evaluationCfgList = evaluationCfgBiz.searchEvaluationCfgList(search);
                if(evaluationCfgList != null && !evaluationCfgList.isEmpty()){
                    ResEvaluationCfg evaluationCfg = evaluationCfgList.get(0);
                    //根据cfg_flow查询学员是否已评分显示或隐藏按钮
                    String flag = com.pinde.core.common.GlobalConstant.FLAG_N;
                    DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGradeByCfgFlow(evaluationCfg.getCfgFlow());
                    if(teacherGradeInfo == null) {//没有评分
                        flag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                    model.addAttribute("flag",flag);
                    Document dom = DocumentHelper.parseText(evaluationCfg.getCfgBigValue());
                    String titleXpath = "//title";
                    List<Element> titleElementList = dom.selectNodes(titleXpath);
                    if(titleElementList != null && !titleElementList.isEmpty()){
                        List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
                        for(Element te :titleElementList){
                            ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                            titleForm.setId(te.attributeValue("id"));
                            titleForm.setName(te.attributeValue("name"));
                            titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
                            titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
                            List<Element> itemElementList = te.elements("item");
                            List<ResEvaluationCfgItemForm> itemFormList = null;
                            if(itemElementList != null && !itemElementList.isEmpty()){
                                itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                                int sum=0;
                                for(Element ie : itemElementList){
                                    ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                                    itemForm.setId(ie.attributeValue("id"));
                                    itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                    String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
                                    itemForm.setScore(score);
                                    itemFormList.add(itemForm);
                                    if(StringUtil.isNotBlank(score))
                                    {
                                        sum+=Integer.parseInt(score);
                                    }
                                    titleForm.setScore(String.valueOf(sum));
                                }
                            }
                            titleForm.setItemList(itemFormList);
                            titleFormList.add(titleForm);
                        }
                        model.addAttribute("titleFormList", titleFormList);
                    }
                    model.addAttribute("cfgCodeId", cfgCodeId);
                    model.addAttribute("cfgCodeName", evaluationCfg.getCfgCodeName());
                    model.addAttribute("evaluationCfg", evaluationCfg);
                }
            }
        }
        return "res/evaluation/evaluationCfg";

    }

    @RequestMapping(value="/deptEvalCfg")
    public String deptEvalCfg(String cfgCodeId,Model model) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null ){
                ResEvaluationCfg search = new ResEvaluationCfg();
                search.setCfgCodeId(cfgCodeId);
                search.setAssessTypeId(EvaluationTypeEnum.DeptEval.getId());
    			search.setOrgFlow(currUser.getOrgFlow());
                List<ResEvaluationCfg> evaluationCfgList = evaluationCfgBiz.searchEvaluationCfgList(search);
                if(evaluationCfgList != null && !evaluationCfgList.isEmpty()){
                    ResEvaluationCfg evaluationCfg = evaluationCfgList.get(0);
                    Document dom = DocumentHelper.parseText(evaluationCfg.getCfgBigValue());
                    String titleXpath = "//title";
                    List<Element> titleElementList = dom.selectNodes(titleXpath);
                    if(titleElementList != null && !titleElementList.isEmpty()){
                        List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
                        for(Element te :titleElementList){
                            ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
                            titleForm.setId(te.attributeValue("id"));
                            titleForm.setName(te.attributeValue("name"));
                            titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
                            titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
                            List<Element> itemElementList = te.elements("item");
                            List<ResEvaluationCfgItemForm> itemFormList = null;
                            if(itemElementList != null && !itemElementList.isEmpty()){
                                itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
                                int sum=0;
                                for(Element ie : itemElementList){
                                    ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
                                    itemForm.setId(ie.attributeValue("id"));
                                    itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                    String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
                                    itemForm.setScore(score);
                                    itemFormList.add(itemForm);
                                    if(StringUtil.isNotBlank(score))
                                    {
                                        sum+=Integer.parseInt(score);
                                    }
                                    titleForm.setScore(String.valueOf(sum));
                                }
                            }
                            titleForm.setItemList(itemFormList);
                            titleFormList.add(titleForm);
                        }
                        model.addAttribute("titleFormList", titleFormList);
                    }
                    model.addAttribute("cfgCodeId", cfgCodeId);
                    model.addAttribute("cfgCodeName", evaluationCfg.getCfgCodeName());
                    model.addAttribute("evaluationCfg", evaluationCfg);
                }
        }
        return "res/evaluation/deptEvalCfg";
    }

    /**
     * 保存考核指标标题
     * @param evaluationCfg
     * @param titleForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveEvaluationTitle")
    @ResponseBody
    public String saveEvaluationTitle(ResEvaluationCfg evaluationCfg, ResEvaluationCfgTitleForm titleForm) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.editEvaluationCfgTitle(evaluationCfg, titleForm);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除考核指标标题
     * @param cfgFlow
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deleteTitle")
    @ResponseBody
    public String deleteTitle(String cfgFlow, String id) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.deleteTitle(cfgFlow, id);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    //*********************************************************************

    /**
     * 保存考核指标列表
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveEvaluationItemList")
    @ResponseBody
    public String saveEvaluationItemList(@RequestBody ResEvaluationCfgForm form) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.saveEvaluationItemList(form);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    /**
     * 修改考试指标
     * @param cfgFlow
     * @param itemForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/modifyItem")
    @ResponseBody
    public String modifyItem(String cfgFlow, ResEvaluationCfgItemForm itemForm) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.modifyItem(cfgFlow, itemForm);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除考试指标
     * @param cfgFlow
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deleteItem")
    @ResponseBody
    public String deleteItem(String cfgFlow, String id) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.deleteItem(cfgFlow, id);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }


    @RequestMapping(value="/cfgSwitchUseCount")
    @ResponseBody
    public Map<String,Integer> cfgSwitchUseCount(String[] cfgCodePrefix,String orgFlow){
        Map<String,Integer> results = null;
        if(cfgCodePrefix!=null && cfgCodePrefix.length>0){
            results = new HashMap<String, Integer>();
            Map<String,Object> paramMap = new HashMap<String,Object>();
            for(String code : cfgCodePrefix){
                paramMap.put("cfgCodePre",code+orgFlow);
//				paramMap.put("cfgCodeSuf",code);
                int useCount = cfgBiz.getOpenSwitchCount(paramMap);
                results.put(code,useCount);
            }
        }
        return results;
    }

    @RequestMapping(value="/deptFormCfgPage")
    public String deptFormCfgPage(String orgFlow,String rotationFlow,Model model){
        List<SysOrg> orgList = orgBiz.searchSysOrg();
        model.addAttribute("orgList",orgList);

        if(StringUtil.isNotBlank(orgFlow)){
            List<SchDept> deptList = schDeptBiz.searchSchDeptList(orgFlow);
            model.addAttribute("deptList",deptList);

            if(StringUtil.isNotBlank(rotationFlow)){
                Map<String,Object> paramMap = new HashMap<String,Object>();
                String cfgCodePre = "form_dept_cfg_"+rotationFlow+orgFlow;
                paramMap.put("cfgCodePre",cfgCodePre);
                List<SysCfg> cfgs = cfgBiz.searchByPreAndSuf(paramMap);
                if(cfgs!=null && !cfgs.isEmpty()){
                    Map<String,String> cfgMap = new HashMap<String, String>();
                    for(SysCfg cfg : cfgs){
                        cfgMap.put(cfg.getCfgCode(),cfg.getCfgValue());
                    }
                    model.addAttribute("cfgMap",cfgMap);
                }
            }
        }
        return "sch/template/deptFormEdit";
    }

    /**
     * 双向评价表查询
     */
    @RequestMapping("/evaluationView")
    public String evaluationView(String cfgName,String assessTypeId, Model model, Integer currentPage,HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        ResEvaluationCfg evaluationCfg = new ResEvaluationCfg();
        evaluationCfg.setCfgCodeName(cfgName);
        evaluationCfg.setAssessTypeId(EvaluationTypeEnum.DoctorEval.getId());
        evaluationCfg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<ResEvaluationCfg> evaluationCfgs = evaluationCfgBiz.searchEvaluationCfgList(evaluationCfg);
        StringBuilder depts = null;
        Map<String,String> flagMap = new HashMap<String,String>();
        Map<String,String> msgMap = new HashMap<String,String>();
        Map<String,String> deptMap = new HashMap<String,String>();
        if(evaluationCfgs !=null&& evaluationCfgs.size()>0 ){
            for(int i =0 ;i<evaluationCfgs.size();i++){
                depts = new StringBuilder();
                List<ResEvaluationDeptExt> resEvaluationDepts = evaluationCfgBiz.readEvaluationDeptList(evaluationCfgs.get(i).getCfgFlow());
                if(resEvaluationDepts != null && resEvaluationDepts.size()>0 ){
                    for(int j =0 ;j<resEvaluationDepts.size();j++){
                        depts.append(resEvaluationDepts.get(j).getDeptName()).append("|");
                    }
                    depts.deleteCharAt(depts.length()-1);
                    deptMap.put(evaluationCfgs.get(i).getCfgFlow(),depts.toString());
                }
                //根据cfg_flow查询学员是否已评分显示或隐藏编辑按钮
               // String msg = "学员已评分，无法进行更改！";
                DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGradeByCfgFlow(evaluationCfgs.get(i).getCfgFlow());
                if(teacherGradeInfo == null) {
                   // msg = "";
                    flagMap.put(evaluationCfgs.get(i).getCfgCodeId(), com.pinde.core.common.GlobalConstant.FLAG_N);
                }
               // msgMap.put(evaluationCfgs.get(i).getCfgCodeId(),msg);
            }

        }
        model.addAttribute("evaluationCfgs",evaluationCfgs);
        model.addAttribute("deptMap",deptMap);
        model.addAttribute("flagMap",flagMap);
       // model.addAttribute("msgMap",msgMap);
        return "res/evaluation/evaluationView";
    }
    /**
     * 双向评价表删除
     */
    @RequestMapping("/delEvaluation")
    @ResponseBody
    public String delEvaluation(String cfgFlow) {
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            int result = evaluationCfgBiz.delEvaluation(cfgFlow);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/evaluationDept")
    public String evaluationDept(String cfgFlow,Model model) {
        SysUser current = GlobalContext.getCurrentUser();
       // String msg = "";
        //根据cfg_flow查询学员是否已评分
      //  DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGradeByCfgFlow(cfgFlow);
       // if(teacherGradeInfo == null) {

            ResEvaluationCfg evaluation = evaluationCfgBiz.readResEvaluationCfg(cfgFlow);
            List<ResEvaluationDeptExt> resEvaluationDepts = evaluationCfgBiz.readEvaluationDeptList(cfgFlow);
            PageHelper.startPage(1, 100);
            List<SysDept> sysDepts = evaluationCfgBiz.getDepts(cfgFlow);
            Set<String> deptSet = new HashSet<String>();
            if (resEvaluationDepts != null && resEvaluationDepts.size() > 0) {
                for (ResEvaluationDept dept : resEvaluationDepts) {
                    deptSet.add(dept.getDeptFlow());
                }
            }
            List<Map> depts = new ArrayList<Map>();
            Map map = null;
            if (sysDepts != null) {
                for (SysDept dept : sysDepts) {
                    map = new HashMap();
                    map.put("deptFlow", dept.getDeptFlow());
                    map.put("deptName", dept.getDeptName());
                    if (deptSet.contains(dept.getDeptFlow())) {
                        map.put("checked", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    depts.add(map);
                }
            }
            model.addAttribute("evaluation", evaluation);
            model.addAttribute("depts", depts);

            return "res/evaluation/evaluationDept";
       /* }else{
            msg = "学员已评分，无法进行更改！";
            model.addAttribute("msg", msg);
            return "res/evaluation/evaluationDept";
        }*/
    }

    @RequestMapping("/saveEvaluationDept")
    @ResponseBody
    public String saveEvalDept(@RequestBody ResEvaluationCfgForm form) {
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser != null){
            String cfgFlow = form.getCfgFlow();
            List<String> depts = form.getDepts();
            int result =  evaluationCfgBiz.saveEvaluationDept(cfgFlow, depts);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping("/checkHaveEval")
    @ResponseBody
    public String checkHaveEval(String deptFlow,String cfgFlow) {
       int count= resGradeBiz.checkHaveEval(deptFlow,cfgFlow);
        if(count>0)
        {
            return com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;
    }
}
