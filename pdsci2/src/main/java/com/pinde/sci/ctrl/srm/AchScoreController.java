package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAchScoreBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.AchScoreEnum;
import com.pinde.sci.model.mo.SrmAchScore;
import com.pinde.sci.model.mo.SrmAchScoreType;
import com.pinde.sci.model.mo.SysSubjCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/srm/ach/score")
public class AchScoreController extends GeneralController {
    @Autowired
    private IAchScoreBiz achScoreBiz;

    @RequestMapping(value={"/saveScore"})
    @ResponseBody
    public String saveScore(SrmAchScore score){
//        SrmAchScore score= JSON.parseObject(jsondata, SrmAchScore.class);
        score.setScoreStatusId(AchScoreEnum.Enable.getId());
        score.setScoreStatusName(AchScoreEnum.Enable.getName());
        achScoreBiz.saveScoreSet(score);
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/saveScoreType"})
    @ResponseBody
    public String saveScoreType(SrmAchScoreType scoreType){
        if(StringUtil.isNotBlank(scoreType.getScoreTypeName())){
            int a = achScoreBiz.saveScoreType(scoreType);
            if(a>0){
                return GlobalConstant.SAVE_SUCCESSED;
            }
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/scoreList",method={RequestMethod.POST,RequestMethod.GET})
    public String searchScoreList(Model model,String expandNode){
//        PageHelper.startPage(currentPage, getPageSize(request));
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        List<SrmAchScore> scoreList=achScoreBiz.searchScoreSetList(new SrmAchScore());
        List<SrmAchScoreType> scoreTypeList = achScoreBiz.allScoreType();
        if(scoreList.isEmpty() && scoreTypeList.isEmpty()){
            sb.append("{\"id\":1, \"pId\":0, \"name\":\"积分信息未设置\",\"td\":[\"无\",\"无\",\"无\",\"无\"]},");
        }
        for(SrmAchScoreType scoreType: scoreTypeList){
            sb.append("{");
            sb.append("\"id\":").append("\""+scoreType.getTypeFlow()+"\"").append(",");
            sb.append("\"pId\":").append(StringUtil.isNotBlank(scoreType.getParentTypeFlow())?"\""+scoreType.getParentTypeFlow()+"\"":0).append(",");
            sb.append("\"name\":").append("\"").append(scoreType.getScoreTypeName()).append("\",");
            sb.append("\"td\":[")
                    .append("\"\",").append("\"\",").append("\"\",")
                    .append("\""+"<a href='javascript:void(0);' onclick=\\\"addScoreType('"+scoreType.getTypeFlow()+"');\\\">添加分类</a>&#12288;" +
                            "<a href='javascript:void(0);' onclick=\\\"addScore('"+scoreType.getTypeFlow()+"');\\\">添加积分项</a>&#12288;" +
                            "<a href='javascript:void(0);' onclick=\\\"editScoreType('"+scoreType.getTypeFlow()+"');\\\">修改</a>&#12288;"+
                            "<a href='javascript:void(0);' onclick=\\\"delScoreType('"+scoreType.getTypeFlow()+"');\\\">删除</a>"+"\"")
                    .append("]");
            sb.append("},");
        }
        for(SrmAchScore achScore: scoreList){
            String scoreStatusName;
            String scoreStatusId;
            if(achScore.getScoreStatusId().equals(AchScoreEnum.Enable.getId())){
                scoreStatusId = AchScoreEnum.Disable.getId();
                scoreStatusName = AchScoreEnum.Disable.getName();
            }else{
                scoreStatusId = AchScoreEnum.Enable.getId();
                scoreStatusName = AchScoreEnum.Enable.getName();
            }
            sb.append("{");
            sb.append("\"id\":").append("\""+achScore.getScoreFlow()+"\"").append(",");
            sb.append("\"pId\":").append(StringUtil.isNotBlank(achScore.getParentScoreFlow())?"\""+achScore.getParentScoreFlow()+"\"":0).append(",");
            sb.append("\"name\":").append("\"").append(achScore.getScoreName()).append("\",");
            sb.append("\"td\":[")
                    .append("\""+achScore.getScoreDeptValue()+"\",")
                    .append("\""+achScore.getScorePersonalValue()+"\",")
                    .append(StringUtil.isNotBlank(achScore.getScoreRemark())?"\""+achScore.getScoreRemark()+"\"":"\"\"").append(",")
                    .append("\""+
                            "<a href='javascript:void(0);' onclick=\\\"setScoreStatus('"+scoreStatusName+"','"+achScore.getScoreFlow()+"','"+scoreStatusId+"');\\\">"+scoreStatusName+"</a>&#12288;"+
                            "<a href='javascript:void(0);' onclick=\\\"updateScore('"+achScore.getScoreFlow()+"');\\\">修改</a>&#12288;" +
                            "<a href='javascript:void(0);' onclick=\\\"delScore('"+achScore.getScoreFlow()+"');\\\">删除</a>"+"\"")
                    .append("]");
            sb.append("},");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        model.addAttribute("jsonData",sb.toString());
        if(StringUtil.isNotBlank(expandNode)) {
            List<String> parentFlowList = new ArrayList<>();
          //  parentFlowList.add(expandNode);
            getAllParentFlow(parentFlowList,scoreTypeList,expandNode);
            Collections.reverse(parentFlowList);//倒序
            model.addAttribute("parentFlowList", parentFlowList);
        }
        return "srm/score/scoreSet";
    }

    /**
     * 获取修改元素的所有父类Flow
     * @param parentFlowList
     * @param scoreTypeList
     * @param parentFlow
     */
    private void getAllParentFlow(List<String> parentFlowList,List<SrmAchScoreType> scoreTypeList,String parentFlow){
        parentFlowList.add(parentFlow);
        for(SrmAchScoreType scoreType: scoreTypeList){
            if(scoreType.getParentTypeFlow() != null && !"0".equals(scoreType.getParentTypeFlow()) && scoreType.getTypeFlow().equals(parentFlow)){
              //  parentFlowList.add(scoreType.getParentTypeFlow());
                getAllParentFlow(parentFlowList,scoreTypeList,scoreType.getParentTypeFlow());
            }
        }
    }

    @RequestMapping(value = "/getScoreDataJson")
    public
    @ResponseBody
    String getScoreDataJson() {
        SrmAchScore srmAchScore = new SrmAchScore();
        srmAchScore.setScoreStatusId(AchScoreEnum.Enable.getId());
        List<SrmAchScore> scoreList=achScoreBiz.searchScoreSetList(srmAchScore);
        List<SrmAchScoreType> scoreTypeList = achScoreBiz.allScoreType();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
      //  sb.append("{\"id\":0, \"pId\":-1, \"name\":\"学科代码\",\"open\":true,\"t\":\"根节点\"},");

        for(SrmAchScoreType scoreType: scoreTypeList){
            sb.append("{");
            sb.append("\"id\":").append("\"type"+scoreType.getTypeFlow()+"\"").append(",");//id前添加 type 为了区分'积分类别' 与 '积分项'
            sb.append("\"pId\":").append(StringUtil.isNotBlank(scoreType.getParentTypeFlow())?"\"type"+scoreType.getParentTypeFlow()+"\"":0).append(",");
            sb.append("\"name\":").append("\"").append(scoreType.getScoreTypeName()).append("\",");
            sb.append("\"t\":").append("\""+"积分项类别"+"\"");
            sb.append("},");
        }
        for(SrmAchScore achScore: scoreList){
            sb.append("{");
            sb.append("\"id\":").append("\""+achScore.getScoreFlow()+"\"").append(",");
            sb.append("\"pId\":").append(StringUtil.isNotBlank(achScore.getParentScoreFlow())?"\"type"+achScore.getParentScoreFlow()+"\"":0).append(",");
            sb.append("\"name\":").append("\"").append(achScore.getScoreName()).append("\",");
            sb.append("\"t\":").append("\""+"积分项"+"\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    @RequestMapping("/delScore")
    @ResponseBody
    public String delScore(String scoreFlow){
        if(StringUtil.isNotBlank(scoreFlow)) {
            List<String> scoreFlowList = new ArrayList<>();
            scoreFlowList.add(scoreFlow);
            int a = achScoreBiz.achScoreIsUse(scoreFlowList);
            if (a > 0) {
                return "该项积分已经被使用，无法删除";
            }
            int result = achScoreBiz.delScoreSet(scoreFlow);
            if (result > 0) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping("/updateScore")
    @ResponseBody
    public String updateScore(String scoreFlow){
        if(StringUtil.isNotBlank(scoreFlow)) {
            List<String> scoreFlowList = new ArrayList<>();
            scoreFlowList.add(scoreFlow);
            int a = achScoreBiz.achScoreIsUse(scoreFlowList);
            if (a > 0) {
                return "该项积分已经被使用，无法删除";
            }
            int result = achScoreBiz.delScoreSet(scoreFlow);
            if (result > 0) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping("/delScoreType")
    @ResponseBody
    public String delScoreType(String typeFlow){
        if(StringUtil.isNotBlank(typeFlow)) {
            List<String> typeFlowList = new ArrayList<>();
            List<String> scoreFlowList = new ArrayList<>();
            typeFlowList.add(typeFlow);
            List<SrmAchScoreType> achScoreTypeList = achScoreBiz.allScoreType();
            getTypeFlowList(achScoreTypeList, typeFlow, typeFlowList);
            List<SrmAchScore> achScoreList = achScoreBiz.searchScoreListByprarentFlow(typeFlowList);
            for(SrmAchScore score : achScoreList){
                scoreFlowList.add(score.getScoreFlow());
            }
            if(scoreFlowList.size()>0){
                int a = achScoreBiz.achScoreIsUse(scoreFlowList);
                if (a > 0) {
                    return "该项积分已经被使用，无法删除";
                }
                achScoreBiz.delScoreSets(scoreFlowList);
            }
            achScoreBiz.delScoreTypes(typeFlowList);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    private  void getTypeFlowList(List<SrmAchScoreType> achScoreTypeList,String typeFlow,List<String> typeFlowList){
        for(SrmAchScoreType scoreType : achScoreTypeList){
            if(typeFlow.equals(scoreType.getParentTypeFlow())){
                typeFlowList.add(scoreType.getTypeFlow());
                getTypeFlowList(achScoreTypeList,scoreType.getTypeFlow(),typeFlowList);
            }
        }
    }
    @RequestMapping(value={"/modifyScoreStatus"})
    @ResponseBody
    public String modifyScoreStatus(String scoreFlow,String scoreStatus){
        SrmAchScore score=new SrmAchScore();
        score.setScoreFlow(scoreFlow);
        score.setScoreStatusId(scoreStatus);
        achScoreBiz.saveScoreSet(score);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 修改积分项
     * @param scoreFlow
     * @return
     */
    @RequestMapping(value = "/editScore", method = RequestMethod.GET)
    public String editScore(String scoreFlow,Model model) {
        if(StringUtil.isNotBlank(scoreFlow)) {
            SrmAchScore achScore = achScoreBiz.readScoreSet(scoreFlow);
            model.addAttribute("achScore",achScore);
            model.addAttribute("parentScoreFlow",achScore.getParentScoreFlow());
        }
        return "srm/score/editScore";
    }

    /**
     * 新增积分项
     * @param parentScoreFlow
     * @return
     */
    @RequestMapping(value = "/addScore", method = RequestMethod.GET)
    public String addScore(String parentScoreFlow,Model model) {
        model.addAttribute("parentScoreFlow",parentScoreFlow);
        return "srm/score/editScore";
    }

    /**
     * 修改积分类别
     * @param typeFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editScoreType", method = RequestMethod.GET)
    public String editScoreType(String typeFlow,Model model) {
        if(StringUtil.isNotBlank(typeFlow)) {
            SrmAchScoreType scoreType = achScoreBiz.readScoreType(typeFlow);
            if(null != scoreType) {
                model.addAttribute("typeFlow", typeFlow);
                model.addAttribute("parentTypeFlow",scoreType.getParentTypeFlow());
                model.addAttribute("scoreTypeName",scoreType.getScoreTypeName());
            }
        }
        return "srm/score/editType";
    }

    /**
     * 新增积分类型
     * @param typeFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/addScoreType", method = RequestMethod.GET)
    public String addScoreType(String typeFlow,Model model) {
//        model.addAttribute("typeFlow",typeFlow);
        if(StringUtil.isNotBlank(typeFlow)) {
            model.addAttribute("parentTypeFlow", typeFlow);//如果typeFlow不为空  即  增加子类
        }
        return "srm/score/editType";
    }

    @RequestMapping("/searchScores")
    @ResponseBody
    public List<SrmAchScore> searchScores() {
        SrmAchScore score=new SrmAchScore();
        List<SrmAchScore> scoreList = new ArrayList<>();
        scoreList = achScoreBiz.searchScoreSetList(score);
        return scoreList;
    }

    @RequestMapping(value="/scoreSumList",method={RequestMethod.POST,RequestMethod.GET})
    public String scoreSumList(String typeId,String startYear,String endYear,String userName,String isPublish,Integer currentPage,HttpServletRequest request, Model model){
        model.addAttribute("isPublish",isPublish);
        Map<String ,String > map=new HashMap<>();
//        startTime = DateUtil.transDateTime(startTime,"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss");
//        endTime = DateUtil.transDateTime(endTime,"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss");
//        map.put("startTime",startTime);
//        map.put("endTime",endTime);
        if("fixedFlag".equals(isPublish)){//积分汇总页面 归档状态
            map.put("isFixedFlag",GlobalConstant.FLAG_Y);
        }else if("publish".equals(isPublish)){//积分汇总页面 公示状态
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }else if("isPublish".equals(isPublish)){//公示页面
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }
        map.put("userName",userName);
        if(StringUtil.isNotBlank(startYear)){
            map.put("startTime",startYear+"0000000000");
        }
        if(StringUtil.isNotBlank(endYear)){
            map.put("endTime",endYear+"1231235959");
        }
        map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        List<Map<String ,String >> scoreSumList=new ArrayList<>();
        if("forDept".equals(typeId)){
            PageHelper.startPage(currentPage, getPageSize(request));
            scoreSumList=achScoreBiz.searchDeptScoreList(map);
            model.addAttribute("titleName","科室");
        }else {
            PageHelper.startPage(currentPage, getPageSize(request));
            scoreSumList=achScoreBiz.searchAuthorScoreList(map);
            model.addAttribute("titleName","个人");
        }
        model.addAttribute("scoreSumList",scoreSumList);
        model.addAttribute("currentPage",currentPage);
        return "srm/score/scoreSum";
    }
    @RequestMapping("/fixedScoreByYear")
    public String fixedScoreByYear(String isPublish,Model model){
        model.addAttribute("isPublish",isPublish);
        return "srm/score/fixedScore";
    }

    @RequestMapping("/fixedScore")
    @ResponseBody
    public String fixedScore(String year,String isPublish){
        if(StringUtil.isNotBlank(year)) {
            achScoreBiz.updadeScoreUsedAuthor(null, year,isPublish);
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }


    @RequestMapping("/scoreItemList")
    public String scoreItemList(String typeId,String startYear,String endYear,String flow,String isPublish,Model model){
        model.addAttribute("isPublish",isPublish);
        List<Map<String ,String >> scoreItemList=new ArrayList<>();
        Map<String ,String > map=new HashMap<>();
        if("fixedFlag".equals(isPublish)){//积分汇总页面 归档状态
            map.put("isFixedFlag",GlobalConstant.FLAG_Y);
        }else if("publish".equals(isPublish)){//积分汇总页面 公示状态
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }else if("isPublish".equals(isPublish)){//公示页面
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }
        if(StringUtil.isNotBlank(startYear)){
            map.put("startTime",startYear+"0000000000");
            model.addAttribute("startYear",startYear);
        }
        if(StringUtil.isNotBlank(endYear)){
            map.put("endTime",endYear+"1231235959");
            model.addAttribute("endYear",endYear);
        }
        if("forDept".equals(typeId)){
            map.put("deptFlow",flow);
            scoreItemList=achScoreBiz.searchScoreListByDept(map);
            model.addAttribute("scoreItemList",scoreItemList);
        }else {
            map.put("userFlow",flow);
            scoreItemList=achScoreBiz.searchScoreListByAuthor(map);
            model.addAttribute("scoreItemList",scoreItemList);
        }
        model.addAttribute("typeId",typeId);
        model.addAttribute("flow",flow);
        return "srm/score/scoreItemList";
    }

    @RequestMapping("/achScoreList")
    public String achScoreList(String typeId,String flow,String scoreFlow,String isPublish,String startYear,String endYear,Model model){
        List<Map<String ,String >> scoreItemList=new ArrayList<>();
        Map<String ,String > map=new HashMap<>();
        if("fixedFlag".equals(isPublish)){//积分汇总页面 归档状态
            map.put("isFixedFlag",GlobalConstant.FLAG_Y);
        }else if("publish".equals(isPublish)){//积分汇总页面 公示状态
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }else if("isPublish".equals(isPublish)){//公示页面
            map.put("isPublish",GlobalConstant.FLAG_Y);
        }
        if(StringUtil.isNotBlank(startYear)){
            map.put("startTime",startYear+"0000000000");
        }
        if(StringUtil.isNotBlank(endYear)){
            map.put("endTime",endYear+"1231235959");
        }
        if("forDept".equals(typeId)){
            map.put("deptFlow",flow);
        }else {
            map.put("userFlow",flow);
        }
        map.put("scoreFlow",scoreFlow);
        scoreItemList=achScoreBiz.searchAchScoreList(map);
        model.addAttribute("scoreItemList",scoreItemList);
        return "srm/score/achScoreList";
    }
}
