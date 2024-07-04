package com.pinde.res.ctrl.jswjw;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwChargeBiz;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res/jswjw/charge")
public class JswjwChargeController {
	private static Logger logger = LoggerFactory.getLogger(JswjwChargeController.class);

    @Autowired
    private IJswjwBiz jswjwBiz;
	@Autowired
    private IJswjwChargeBiz jswjwChargeBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/jswjw/500";
    }

    /**
     *查询基地
     */
    @RequestMapping(value={"/orgList"},method={RequestMethod.POST})
    public String orgList(String userFlow,HttpServletRequest request,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/orgList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        List<SysOrg> orgList = jswjwChargeBiz.searchOrgList(org.getOrgCityId());
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("orgList",orgList);
        return "res/jswjw/charge/orgList";
    }

    /**
     *查询专业
     */
    @RequestMapping(value={"/speList"},method={RequestMethod.POST})
    public String speList(String catSpeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
//        if(StringUtil.isBlank(catSpeId)){
//            model.addAttribute("resultId", "3030201");
//            model.addAttribute("resultType", "培训类型不能为空");
//            return "res/jswjw/charge/speList";
//        }
        List<SysDict> doctorTrainingSpeList = jswjwChargeBiz.searchSpeList("DoctorTrainingSpe");
        List<SysDict> assiGeneralList = jswjwChargeBiz.searchSpeList("AssiGeneral");
        model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
        model.addAttribute("assiGeneralList",assiGeneralList);
        return "res/jswjw/charge/speList";
    }

    /**
     * 招录查询
     */
    @RequestMapping(value = {"/doctorRecruitList"}, method = {RequestMethod.POST})
    public String doctorRecruitList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             Integer pageIndex, Integer pageSize,Model model)  {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/doctorRecruitList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/doctorRecruitList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/doctorRecruitList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/doctorRecruitList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/doctorRecruitList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchDoctorRecruitAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorRecruitList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/doctorRecruitList";
    }

    /**
     * 学员统计(在培，待结业)
     */
    @RequestMapping(value = {"/trainingList"}, method = {RequestMethod.POST})
    public String trainingList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                    String doctorStatusId,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/trainingList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/trainingList";
        }
        if (StringUtil.isBlank(doctorStatusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学员状态标识符为空");
            return "res/jswjw/charge/trainingList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/trainingList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/trainingList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/trainingList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("doctorStatusId",doctorStatusId);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchDoctorRecruitTrainingAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorRecruitTrainingList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/trainingList";
    }

    /**
     * 学员减免
     */
    @RequestMapping(value = {"/reductionList"}, method = {RequestMethod.POST})
    public String reductionList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                               Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/reductionList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/reductionList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/reductionList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/reductionList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/reductionList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchDoctorReductionAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorReductionList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/reductionList";
    }

    /**
     * 专业变更
     */
    @RequestMapping(value = {"/speChangeList"}, method = {RequestMethod.POST})
    public String speChangeList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/speChangeList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/speChangeList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/speChangeList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/speChangeList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/speChangeList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchSpeChangeAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchSpeChangeList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/speChangeList";
    }

    /**
     * 延期
     */
    @RequestMapping(value = {"/delayList"}, method = {RequestMethod.POST})
    public String delayList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/delayList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/delayList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/delayList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/delayList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/delayList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchDelayAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDelayList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/delayList";
    }

    /**
     * 退培
     */
    @RequestMapping(value = {"/returnList"}, method = {RequestMethod.POST})
    public String returnList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                            Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/returnList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/returnList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/returnList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/returnList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/returnList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchReturnAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchReturnList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/returnList";
    }

    /**
     * 黑名单
     */
    @RequestMapping(value = {"/blackList"}, method = {RequestMethod.POST})
    public String blackList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/blackList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/blackList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/blackList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/blackList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/blackList";
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchBlackAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchBlackList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/blackList";
    }

    /**
     * 入科
     */
    @RequestMapping(value = {"/inDeptList"}, method = {RequestMethod.POST})
    public String inDeptList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                            String startDate,String endDate,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/inDeptList";
        }
        if (StringUtil.isBlank(startDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "开始时间为空");
            return "res/jswjw/charge/inDeptList";
        }
        if (StringUtil.isBlank(endDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "结束时间为空");
            return "res/jswjw/charge/inDeptList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/inDeptList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/inDeptList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/inDeptList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/inDeptList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchInDeptAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchInDeptList(param);
                map.put("SESSION_NUMBER","全部");
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/inDeptList";
    }

    /**
     * 出科
     */
    @RequestMapping(value = {"/outDeptList"}, method = {RequestMethod.POST})
    public String outDeptList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             String startDate,String endDate,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/outDeptList";
        }
        if (StringUtil.isBlank(startDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "开始时间为空");
            return "res/jswjw/charge/outDeptList";
        }
        if (StringUtil.isBlank(endDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "结束时间为空");
            return "res/jswjw/charge/outDeptList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/outDeptList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/outDeptList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/outDeptList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/outDeptList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchOutDeptAllList(param);
        Map<String,Object> speMap = new HashMap<>();
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchOutDeptList(param);
                speMap.put((String)map.get("ORG_FLOW"),speResults);
            }
        }
        model.addAttribute("results",results);
        model.addAttribute("speMap",speMap);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/outDeptList";
    }

    /**
     * 教学活动
     */
    @RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
    public String activityList(String userFlow,String roleId,String orgFlow, String startDate,String endDate,
                               String activityTypeId,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/activityList";
        }
        if (StringUtil.isBlank(startDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "开始时间为空");
            return "res/jswjw/charge/activityList";
        }
        if (StringUtil.isBlank(endDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "结束时间为空");
            return "res/jswjw/charge/activityList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/activityList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/activityList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/activityList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/activityList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        param.put("activityTypeId",activityTypeId);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchActivityList(param);
        model.addAttribute("results",results);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/activityList";
    }

    /**
     * 结业资格查看
     */
    @RequestMapping(value = {"/gradutionList"}, method = {RequestMethod.POST})
    public String gradutionList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                                String auditStatusId,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/gradutionList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/gradutionList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/gradutionList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/gradutionList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/gradutionList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("auditStatusId",auditStatusId);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchGradutionList(param);
        model.addAttribute("results",results);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/gradutionList";
    }

    /**
     * 补考审核查看
     */
    @RequestMapping(value = {"/signList"}, method = {RequestMethod.POST})
    public String signList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                           String auditStatusId,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/signList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/signList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/signList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/signList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/signList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
        param.put("auditStatusId",auditStatusId);
        param.put("cityId",org.getOrgCityId());
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results = jswjwChargeBiz.searchSignList(param);
        model.addAttribute("results",results);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/signList";
    }

    /**
     * 成绩管理
     */
    @RequestMapping(value = {"/scoreList"}, method = {RequestMethod.POST})
    public String scoreList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                            String scoreType,String isPass,Integer pageIndex, Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/charge/scoreList";
        }
        if (StringUtil.isBlank(scoreType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "成绩类型为空");
            return "res/jswjw/charge/scoreList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/jswjw/charge/scoreList";
        }
        if(!roleId.equals("Charge")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/jswjw/charge/scoreList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/jswjw/charge/scoreList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/jswjw/charge/scoreList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        Map<String,String> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("catSpeId",catSpeId);
        param.put("speId",speId);
        param.put("sessionNumber",sessionNumber);
//        param.put("scoreType",scoreType);
        param.put("isPass",isPass);
        param.put("cityId",org.getOrgCityId());
        List<Map<String,Object>> results = null;
        PageHelper.startPage(pageIndex,pageSize);
        if("TheoryScore".equals(scoreType)){
            results = jswjwChargeBiz.searchTheoryScoreList(param);
        }else{
            results = jswjwChargeBiz.searchSkillScoreList(param);
        }
        model.addAttribute("results",results);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/jswjw/charge/scoreList";
    }
}

