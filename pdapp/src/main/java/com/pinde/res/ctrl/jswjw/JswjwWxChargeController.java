package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GeneralController;
import com.pinde.core.commom.enums.TrainCategoryEnum;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res/jswjw/wx/charge")
public class JswjwWxChargeController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(JswjwWxChargeController.class);

    @Autowired
    private IJswjwBiz jswjwBiz;
	@Autowired
    private IJswjwChargeBiz jswjwChargeBiz;

    /**
     *查询基地
     */
    @RequestMapping(value={"/orgList"},method={RequestMethod.POST})
    @ResponseBody
    public Object orgList(String userFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            return ResultDataThrow("用户不存在");
        }
        SysOrg org = jswjwBiz.getOrg(user.getOrgFlow());
        List<SysOrg> orgList = jswjwChargeBiz.searchOrgList(org.getOrgCityId());
        resultMap.put("userFlow",userFlow);
//        resultMap.put("orgList",orgList);

        List<Map<String,Object>> orgMapList = new ArrayList<>();
        if(null != orgList && orgList.size()>0){
            for (SysOrg so:orgList) {
                Map<String,Object> orgMap = new HashMap<>();
                orgMap.put("orgFlow",so.getOrgFlow());
                orgMap.put("orgName",so.getOrgName());
                orgMapList.add(orgMap);
            }
        }
        resultMap.put("orgMapList",orgMapList);

        List<Map<String,Object>> dictMapList = new ArrayList<>();
        Map<String,Object> dictMap = new HashMap<>();
        dictMap.put("catSpeId", TrainCategoryEnum.DoctorTrainingSpe.getId());
        dictMap.put("catSpeName",TrainCategoryEnum.DoctorTrainingSpe.getName());
        List<SysDict> dictList = new ArrayList<>();
        SysDict dict = new SysDict();
        dict.setDictId("");
        dict.setDictName("全部");
        dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId()));
        dictMap.put("dictList",dictList);
        dictMapList.add(dictMap);

//        dictMap = new HashMap<>();
//        dictMap.put("catSpeId",TrainCategoryEnum.WMFirst.getId());
//        dictMap.put("catSpeName",TrainCategoryEnum.WMFirst.getName());
//        dictList = new ArrayList<>();
//        dictList.add(dict);
//        dictList.addAll(jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMFirst.getId()));
//        dictMap.put("dictList",dictList);
//        dictMapList.add(dictMap);

//        dictMap = new HashMap<>();
//        dictMap.put("catSpeId",TrainCategoryEnum.WMSecond.getId());
//        dictMap.put("catSpeName",TrainCategoryEnum.WMSecond.getName());
//        dictList = new ArrayList<>();
//        dictList.add(dict);
//        dictList.addAll(jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMSecond.getId()));
//        dictMap.put("dictList",dictList);
//        dictMapList.add(dictMap);

        dictMap = new HashMap<>();
        dictMap.put("catSpeId",TrainCategoryEnum.AssiGeneral.getId());
        dictMap.put("catSpeName",TrainCategoryEnum.AssiGeneral.getName());
        dictList = new ArrayList<>();
        dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(TrainCategoryEnum.AssiGeneral.getId()));
        dictMap.put("dictList",dictList);
        dictMapList.add(dictMap);
        resultMap.put("dictMapList", dictMapList);

        return resultMap;
    }

    /**
     *查询专业
     */
    @RequestMapping(value={"/speList"},method={RequestMethod.POST})
    @ResponseBody
    public Object speList(String catSpeId){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        List<SysDict> doctorTrainingSpeList = jswjwChargeBiz.searchSpeList("DoctorTrainingSpe");
        List<SysDict> assiGeneralList = jswjwChargeBiz.searchSpeList("AssiGeneral");
        resultMap.put("doctorTrainingSpeList",doctorTrainingSpeList);
        resultMap.put("assiGeneralList",assiGeneralList);
        return resultMap;
    }

    /**
     * 招录查询
     */
    @RequestMapping(value = {"/doctorRecruitList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorRecruitList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             Integer pageIndex, Integer pageSize)  {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorRecruitList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 学员统计(在培，待结业)
     */
    @RequestMapping(value = {"/trainingList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object trainingList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                    String doctorStatusId,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if (StringUtil.isBlank(doctorStatusId)) {
            return ResultDataThrow("学员状态标识符为空");
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorRecruitTrainingList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 学员减免
     */
    @RequestMapping(value = {"/reductionList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object reductionList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                               Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(sessionNumber)) {
            sessionNumber = DateUtil.getYear();
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDoctorReductionList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 专业变更
     */
    @RequestMapping(value = {"/speChangeList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object speChangeList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchSpeChangeList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 延期
     */
    @RequestMapping(value = {"/delayList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object delayList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                                Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchDelayList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 退培
     */
    @RequestMapping(value = {"/returnList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object returnList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                            Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchReturnList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 黑名单
     */
    @RequestMapping(value = {"/blackList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object blackList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchBlackList(param);
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 入科
     */
    @RequestMapping(value = {"/inDeptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object inDeptList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                            String startDate,String endDate,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(startDate)) {
            return ResultDataThrow("开始时间为空");
        }
        if (StringUtil.isBlank(endDate)) {
            return ResultDataThrow("结束时间为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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

        resultMap.put("dataCount",PageHelper.total);
        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchInDeptList(param);
                map.put("sessionNumber","全部");
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 出科
     */
    @RequestMapping(value = {"/outDeptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object outDeptList(String userFlow,String roleId,String orgFlow,String catSpeId,String speId,String sessionNumber,
                             String startDate,String endDate,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(startDate)) {
            return ResultDataThrow("开始时间为空");
        }
        if (StringUtil.isBlank(endDate)) {
            return ResultDataThrow("结束时间为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("dataCount",PageHelper.total);

        if(null != results && results.size()>0){
            for (Map<String,Object> map:results) {
                param.put("orgFlow",(String)map.get("ORG_FLOW"));
                List<Map<String,Object>> speResults = jswjwChargeBiz.searchOutDeptList(param);
                map.put("sessionNumber","全部");
                map.put("speName","全部");
                map.put("speList",speResults);
            }
        }
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);

        return resultMap;
    }

    /**
     * 教学活动
     */
    @RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object activityList(String userFlow,String roleId,String orgFlow, String startDate,String endDate,
                               String activityTypeId,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(startDate)) {
            return ResultDataThrow("开始时间为空");
        }
        if (StringUtil.isBlank(endDate)) {
            return ResultDataThrow("结束时间为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);
        resultMap.put("dataCount",PageHelper.total);

        List<Map<String,Object>> activityTypeList = new ArrayList<>();
        Map<String,Object> typeMap = new HashMap<>();
        typeMap.put("activityTypeId","1");
        typeMap.put("activityTypeName","教学查房");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","2");
        typeMap.put("activityTypeName","疑难病例讨论");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","3");
        typeMap.put("activityTypeName","危重病例讨论");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","4");
        typeMap.put("activityTypeName","小讲课");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","5");
        typeMap.put("activityTypeName","死亡病例讨论");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","6");
        typeMap.put("activityTypeName","入科教育");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","7");
        typeMap.put("activityTypeName","出科考核");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","8");
        typeMap.put("activityTypeName","技能培训");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","9");
        typeMap.put("activityTypeName","阅片会");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","10");
        typeMap.put("activityTypeName","教学会诊");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","11");
        typeMap.put("activityTypeName","教学病例讨论");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","12");
        typeMap.put("activityTypeName","临床操作技能指导");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","13");
        typeMap.put("activityTypeName","临床病历书写指导");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","14");
        typeMap.put("activityTypeName","手术操作指导");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","15");
        typeMap.put("activityTypeName","影像诊断报告书写指导");
        activityTypeList.add(typeMap);
        typeMap = new HashMap<>();
        typeMap.put("activityTypeId","16");
        typeMap.put("activityTypeName","临床文献研读");
        activityTypeList.add(typeMap);
        resultMap.put("activityTypeList",activityTypeList);

        return resultMap;
    }

    /**
     * 结业资格查看
     */
    @RequestMapping(value = {"/gradutionList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object gradutionList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                                String auditStatusId,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);
        resultMap.put("dataCount",PageHelper.total);
        return resultMap;
    }

    /**
     * 补考审核查看
     */
    @RequestMapping(value = {"/signList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object signList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                           String auditStatusId,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);
        resultMap.put("dataCount",PageHelper.total);
        return resultMap;
    }

    /**
     * 成绩管理
     */
    @RequestMapping(value = {"/scoreList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object scoreList(String userFlow,String roleId,String orgFlow, String catSpeId,String speId,String sessionNumber,
                            String scoreType,String isPass,Integer pageIndex, Integer pageSize) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(scoreType)) {
            return ResultDataThrow("成绩类型为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色ID为空");
        }
        if(!roleId.equals("Charge")){
            return ResultDataThrow("用户角色ID与角色不符");
        }
        if(pageIndex==null){
            return ResultDataThrow("起始页为空");
        }
        if(pageSize==null){
            return ResultDataThrow("页面大小为空");
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
        resultMap.put("results",results);
        resultMap.put("userFlow",userFlow);
        resultMap.put("dataCount",PageHelper.total);
        return resultMap;
    }
}

