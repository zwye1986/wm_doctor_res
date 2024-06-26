package com.pinde.sci.ctrl.srm;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.srm.EvaluationWayEnum;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmExpertGroup;
import com.pinde.sci.model.mo.SrmExpertGroupUser;
import com.pinde.sci.model.mo.SrmExpertProj;
import com.pinde.sci.model.srm.ExpertGroupInfo;
import com.pinde.sci.model.srm.ExpertInfo;
import com.pinde.sci.model.srm.ExpertListToExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/srm/expert/group")
public class ExpertGroupController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(ExpertGroupController.class);
    @Autowired
    private IExpertProjBiz expertProjBiz;
    @Autowired
    private IExpertGroupBiz expertGroupBiz;
    @Autowired
    private IGradeSchemeBiz gradeSchemeBiz;
    @Autowired
    private IExpertGroupsUserBiz expertGroupsUserBiz;
    @Autowired
    private IPubProjBiz pubProjBiz;

    /**
     * list页面加载显示专家组信息
     *
     * @param expert
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Integer currentPage, SrmExpertGroup expert, HttpServletRequest request, Model model) {
        expert.setEvaluationWayId(EvaluationWayEnum.NetWorkWay.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SrmExpertGroup> expertGroupList = expertGroupBiz.searchExpertGroup(expert);
        model.addAttribute("expertGroupList", expertGroupList);
        return "srm/expert/group/list";
    }


    /**
     * 根据流水号查找专家组,进行编辑专家组信息
     *
     * @param groupFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public String edit(String groupFlow, Model model) {
        SrmExpertGroup srmExpertGroup = expertGroupBiz.readSrmExpertGroup(groupFlow);
        model.addAttribute("srmExpertGroup", srmExpertGroup);
        return "srm/expert/group/edit";
    }


    /**
     * 增加专家组信息(网评)
     *
     * @param expert
     * @return
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(SrmExpertGroup expertGroup) {
        expertGroup.setEvaluationWayId(EvaluationWayEnum.NetWorkWay.getId());
        expertGroup.setEvaluationWayName(EvaluationWayEnum.NetWorkWay.getName());
        expertGroupBiz.saveExpertGroup(expertGroup);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 删除当前的专家组信息
     *
     * @param expert
     * @return
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public String delete(SrmExpertGroup expertGroup) {
        expertGroup.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        expertGroupBiz.saveExpertGroup(expertGroup);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    /**
     * 查询专家组信息
     *
     * @return
     */
    @RequestMapping("/expertGroupInfo")
    public String expertGroupInfo(Integer currentPage, SrmExpertGroup expert, HttpServletRequest request, Model model) {

        PageHelper.startPage(currentPage, getPageSize(request));
        expert.setEvaluationWayId(EvaluationWayEnum.NetWorkWay.getId());
        List<SrmExpertGroup> expertGroupList = expertGroupBiz.searchExpertGroup(expert);
        model.addAttribute("expertGroupList", expertGroupList);
        List<ExpertGroupInfo> expertGroupInfoList = new ArrayList<>();
        for (SrmExpertGroup expertGroup : expertGroupList) {
            if (StringUtil.isNotBlank(expertGroup.getGroupFlow())) {
                SrmExpertGroupUser groupUser = new SrmExpertGroupUser();
                groupUser.setGroupFlow(expertGroup.getGroupFlow());//根据流水号获取当前专家的信息
                List<ExpertInfo> expertInfoList = expertGroupsUserBiz.searchExpertGroupUserInfo(groupUser);//查询专家组中对应的专家信息
                ExpertGroupInfo expertGroupInfo = new ExpertGroupInfo();
                expertGroupInfo.setExpertGroup(expertGroup);
                expertGroupInfo.setExpertInfoList(expertInfoList);
                expertGroupInfoList.add(expertGroupInfo);
            }
        }
        model.addAttribute("expertGroupInfoList", expertGroupInfoList);
        return "srm/expert/expertGroupInfo";
    }

    @RequestMapping("/viewExpertList")
    public String viewExpertList(Integer currentPage, String groupFlow, Model model, HttpServletRequest request) {
        SrmExpertProj srmExpertProj = new SrmExpertProj();
        srmExpertProj.setGroupFlow(groupFlow);
        model.addAttribute("groupFlow", groupFlow);
        //SrmExpertGroupUser groupUser = new SrmExpertGroupUser();
        //  groupUser.setGroupFlow(srmExpertProj.getGroupFlow());
        //    List<SrmExpertGroupUser> userList = expertGroupsUserBiz.searchSrmExpertGroupUser(groupUser);
        List<SrmExpertProj> expertProjList = expertProjBiz.searchSrmExpertProjByExample(srmExpertProj);
        //评分值
        List<String> projFlowList = new ArrayList<>();
        Map<String, List<SrmExpertProj>> map = new HashMap<>();
        for (SrmExpertProj expertProj : expertProjList) {
            if (StringUtil.isNotBlank(expertProj.getProjFlow())) {
                if (map.containsKey(expertProj.getProjFlow())) {
                    map.get(expertProj.getProjFlow()).add(expertProj);
                } else {
                    projFlowList.add(expertProj.getProjFlow());
                    List<SrmExpertProj> expertList = new ArrayList<>();
                    expertList.add(expertProj);
                    map.put(expertProj.getProjFlow(), expertList);
                }
            }
        }
        model.addAttribute("expertProjMap", map);
        // PageHelper.startPage(currentPage, getPageSize(request));
        if (projFlowList.size() > 0) {
            List<PubProj> projList = pubProjBiz.searchProjByProjFlowList(projFlowList);
            model.addAttribute("projList", projList);
        }
        return "srm/expert/expertProjDetail";
    }

    /**
     * 导出专家评审结果
     *
     * @param groupFlow
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(String groupFlow, HttpServletResponse response) throws Exception {
        String[] titles;//导出列表头信息
        String[] titles2;
        SrmExpertProj srmExpertProj = new SrmExpertProj();
        srmExpertProj.setGroupFlow(groupFlow);
        List<ExpertListToExcel> searchList = new ArrayList<>();
        List<SrmExpertProj> expertProjList = expertProjBiz.searchSrmExpertProjByExample(srmExpertProj);
        List<String> projFlowList = new ArrayList<>();
        Map<String, List<SrmExpertProj>> map = new HashMap<>();
        for (SrmExpertProj expertProj : expertProjList) {
            if (StringUtil.isNotBlank(expertProj.getProjFlow())) {
                if (map.containsKey(expertProj.getProjFlow())) {
                    map.get(expertProj.getProjFlow()).add(expertProj);
                } else {
                    projFlowList.add(expertProj.getProjFlow());
                    List<SrmExpertProj> expertList = new ArrayList<>();
                    expertList.add(expertProj);
                    map.put(expertProj.getProjFlow(), expertList);
                }
            }
        }

        int num = 1;
        int a = 5;
        int b = 0;
        if (projFlowList.size() > 0) {
            b = map.get(projFlowList.get(0)).size();
        }
        titles = new String[a + b + b + b + 4];
        titles[0] = "序号";
        titles[1] = "单位";
        titles[2] = "科室";
        titles[3] = "姓名";
        titles[4] = "项目名称";
        if (b == 0) {
            titles[a + 2] = "总分";
            titles[a] = "等级";
            titles[a + 1] = "得分";
            titles[a + 3] = "推荐意见";
        } else {
            titles[a + b + b] = "总分";
            titles[a] = "等级";
            titles[a + b] = "得分";
            titles[a + b + b + 1] = "推荐意见";
        }
        titles2 = new String[a + b + b + b + 1];
        titles2[0] = "num:";
        titles2[1] = "pubProj.applyOrgName:";
        titles2[2] = "pubProj.applyDeptName:";
        titles2[3] = "pubProj.applyUserName:";
        titles2[4] = "pubProj.projName:";
        titles2[a + b + b] = "scoreCount:";
        if (projFlowList.size() > 0) {
            List<PubProj> projList = pubProjBiz.searchProjByProjFlowList(projFlowList);

            for (PubProj pubProj : projList) {
                ExpertListToExcel listToExcel = new ExpertListToExcel();
                listToExcel.setPubProj(pubProj);
                listToExcel.setExpertProjList(map.get(pubProj.getProjFlow()));
                listToExcel.setNum(num + "");
                num++;
                double scoreCount = 0;
                List<SrmExpertProj> expertProjs = map.get(pubProj.getProjFlow());
                for (int i = 0; i < expertProjs.size(); i++) {
                    double score = 0;
                    if(StringUtil.isNotBlank(expertProjs.get(i).getScoreTotal())){
                        score = Double.parseDouble(expertProjs.get(i).getScoreTotal());
                }
                    scoreCount += score;
                }
                listToExcel.setScoreCount(scoreCount + "");
                searchList.add(listToExcel);
            }
            List<SrmExpertProj> srmExpProjList = map.get(projFlowList.get(0));
            for (int i = 0; i < srmExpProjList.size(); i++) {
                if (b != 0) {
                    titles2[a] = "expertProjList.get(" + i + ").scoreResultName:" + srmExpProjList.get(i).getUserName();
                    titles2[a + b] = "expertProjList.get(" + i + ").scoreTotal:" + srmExpProjList.get(i).getUserName();
                    titles2[a + b + b + 1] = "expertProjList.get(" + i + ").expertOpinion:" + srmExpProjList.get(i).getUserName();
                    //  titles[a] = srmExpProjList.get(i).getUserName();
                    //  titles[a + b] = srmExpProjList.get(i).getUserName();
                    //   titles[a + b + b + 1] = srmExpProjList.get(i).getUserName();
                    a++;
                }
            }
        }

        ExcleUtile.exportExcleExpertProj(titles, titles2, searchList, response.getOutputStream());
        String fileName = "评审结果.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
