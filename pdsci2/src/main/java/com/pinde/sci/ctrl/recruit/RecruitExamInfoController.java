package com.pinde.sci.ctrl.recruit;

import com.alibaba.fastjson.JSON;
import com.pinde.sci.biz.recruit.IRecruitExamInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamMainBiz;
import com.pinde.sci.biz.recruit.IRecruitExamRoomBiz;
import com.pinde.sci.biz.recruit.IRecruitExamRoomInfoBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.form.recruit.ExamInfoForm;
import com.pinde.sci.model.mo.RecruitExamInfo;
import com.pinde.sci.model.mo.RecruitExamMain;
import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.sci.model.mo.RecruitExamRoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recruit/examInfo")
public class RecruitExamInfoController extends GeneralController {

    @Autowired
    private IRecruitExamInfoBiz recruitExamInfoBiz;

    @Autowired
    private IRecruitExamRoomInfoBiz recruitExamRoomInfoBiz;

    @Autowired
    private IRecruitExamMainBiz recruitExamMainBiz;

    @Autowired
    private IRecruitExamRoomBiz recruitExamRoomBiz;

    @RequestMapping("/examInfos")
    public String examInfos(String mainFlow, Model model){

        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);

        List<RecruitExamInfo> examInfos=recruitExamInfoBiz.searchExamInfoByMainFlow(mainFlow);
        Map<String,List<RecruitExamRoomInfo>> roomMap=new HashMap<>();
        if(examInfos!=null)
        {
            for(RecruitExamInfo examInfo:examInfos)
            {
                List<RecruitExamRoomInfo> examRoomInfos=recruitExamRoomInfoBiz.readRoomInfosByExamFlow(examInfo.getExamFlow());
                roomMap.put(examInfo.getExamFlow(),examRoomInfos);
            }
        }
        model.addAttribute("examInfos",examInfos);
        model.addAttribute("roomMap",roomMap);
        return "recruit/exam/examInfos";
    }

    @RequestMapping("/addExamInfoForm")
    public String addExamInfoForm(String examFlow,String mainFlow, Model model){

        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);
        RecruitExamInfo examInfo=recruitExamInfoBiz.readByFlow(examFlow);
        Map<String,RecruitExamRoomInfo> roomInfoMap=new HashMap<>();
        if(examInfo!=null) {
            List<RecruitExamRoomInfo> examRoomInfos = recruitExamRoomInfoBiz.readRoomInfosByExamFlow(examInfo.getExamFlow());
            if (examRoomInfos != null) {
                for (RecruitExamRoomInfo roomInfo : examRoomInfos) {
                    roomInfoMap.put(roomInfo.getRoomFlow(), roomInfo);
                }
            }
            model.addAttribute("examRoomInfos",examRoomInfos);
        }
        List<RecruitExamRoom> recruitExamRooms = recruitExamRoomBiz.searchAllExamRoom(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("recruitExamRooms",recruitExamRooms);

        model.addAttribute("mainFlow",mainFlow);
        model.addAttribute("examInfo",examInfo);
        model.addAttribute("roomInfoMap",roomInfoMap);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitExamMain.getIsPublish()))
        {
            return "recruit/exam/showExamInfo";
        }
        return "recruit/exam/addExamInfo";
    }
    @RequestMapping("/showExamInfo")
    public String showExamInfo(String examFlow,String mainFlow, Model model){

        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);
        RecruitExamInfo examInfo=recruitExamInfoBiz.readByFlow(examFlow);
        Map<String,RecruitExamRoomInfo> roomInfoMap=new HashMap<>();
        if(examInfo!=null) {
            List<RecruitExamRoomInfo> examRoomInfos = recruitExamRoomInfoBiz.readRoomInfosByExamFlow(examInfo.getExamFlow());
            if (examRoomInfos != null) {
                for (RecruitExamRoomInfo roomInfo : examRoomInfos) {
                    roomInfoMap.put(roomInfo.getRoomFlow(), roomInfo);
                }
            }
            model.addAttribute("examRoomInfos",examRoomInfos);
        }
        List<RecruitExamRoom> recruitExamRooms = recruitExamRoomBiz.searchAllExamRoom(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("recruitExamRooms",recruitExamRooms);

        model.addAttribute("mainFlow",mainFlow);
        model.addAttribute("examInfo",examInfo);
        model.addAttribute("roomInfoMap",roomInfoMap);
        return "recruit/exam/showExamInfo";
    }
    @RequestMapping("/saveExamInfo")
    @ResponseBody
    public String saveExamInfo(@RequestBody ExamInfoForm form, Model model){
        if(form==null)
        {
            return "无数据提交";
        }
        if(StringUtil.isBlank(form.getMainFlow()))
        {
            return "未选择招录年份！";
        }
        if(form.getRoomInfos()==null||form.getRoomInfos().size()<=0)
        {
            return "未选择考场信息！";
        }
        //校验考试时间是否重复
        int c=recruitExamMainBiz.checkExamTime(form.getMainFlow(),form.getExamFlow(),form.getExamStartTime(),form.getExamEndTime());
        if(c>0)
        {
            return "笔试时间与其他考试信息存在重复";
        }
        c=recruitExamMainBiz.checkInterviewTime(form.getMainFlow(),form.getExamFlow(),form.getInterviewStartTime(),form.getInterviewEndTime());
        if(c>0)
        {
            return "面试时间与其他考试信息存在重复";
        }
        System.err.println(JSON.toJSONString(form));
        String result= recruitExamMainBiz.saveExamInfo(form);
        if(StringUtil.isNotBlank(result))
        {
            return result;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("/delExamInfo")
    @ResponseBody
    public String delExamInfo(Model model,String examFlow, HttpServletRequest request){
        RecruitExamInfo examInfo=recruitExamInfoBiz.readByFlow(examFlow);
        if (examInfo == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(examInfo.getRecordStatus()))
        {
            return "考试信息不存在";
        }
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(examInfo.getMainFlow());
        if (recruitExamMain == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamMain.getRecordStatus()))
        {
            return "考试信息不存在";
        }
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(recruitExamMain.getIsPublish()))
        {
            return "考试信息已发布，无法删除，请返回前一页面";
        }
        examInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        int c=recruitExamInfoBiz.saveExamInfo(examInfo);
        if(c==0)
        {
            return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
        }else{
            //删除子表配置信息
            recruitExamInfoBiz.delExamDetail(examInfo.getExamFlow());
        }
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

}
