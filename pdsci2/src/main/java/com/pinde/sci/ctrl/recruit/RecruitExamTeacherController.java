package com.pinde.sci.ctrl.recruit;


import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.recruit.IRecruitExamRoomBiz;
import com.pinde.sci.biz.recruit.IRecruitExamTeacherBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.sci.model.mo.RecruitExamTeacher;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/recruit/examTeacher")
public class RecruitExamTeacherController extends GeneralController {

    @Autowired
    private IRecruitExamTeacherBiz recruitExamTeacherBiz;

    @Autowired
    private IRecruitExamRoomBiz recruitExamRoomBiz;


    @RequestMapping("/main")
    public String main(Model model, Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<RecruitExamTeacher> recruitExamTeacherList = null;
        List<String> examRoomNameList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户
        //查出监考教师
        recruitExamTeacherList= recruitExamTeacherBiz.searchAllExamRoom(orgFlow);

        //查出所有考场名称
        examRoomNameList = recruitExamRoomBiz.searchAllExamRoomName(orgFlow);
//        List<String> nameList = recruitExamRoomBiz.searchAllExamRoomName(orgFlow);
//        if (nameList != null && !nameList.isEmpty()) {
//            for (String examRoomName:nameList) {
//                examRoomNameList.add(examRoomName);
//            }
//        }
        //记录监考教师对应的考场名称
        HashMap<String, String> map = new HashMap<>();
        if (recruitExamTeacherList != null && !recruitExamTeacherList.isEmpty() &&recruitExamTeacherList.size() > 0){
            for (RecruitExamTeacher examTeacher:recruitExamTeacherList){
                String roomFlow = examTeacher.getRoomFlow();
                    RecruitExamRoom recruitExamRoom = recruitExamRoomBiz.searchExamRoomByFlow(roomFlow);
                    map.put(roomFlow,recruitExamRoom.getRoomName());
            }
        }

        model.addAttribute("examTeacherList",recruitExamTeacherList);
        model.addAttribute("examRoomNameList",examRoomNameList);
        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("map",map);
        return "recruit/exam/teacher";
    }

    @RequestMapping("/searchExamTeacherList")
    public String searchExamTeacherList(String teaName,String roomName,String teaRole,Model model, Integer currentPage, HttpServletRequest request){

        List<RecruitExamTeacher> recruitExamTeacherList = null;
        List<String> examRoomNameList = new ArrayList<String>();
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户
        String roomFlow = "";
        RecruitExamRoom examRoom = recruitExamRoomBiz.searchExamRoomByName(roomName,orgFlow);
        if (examRoom != null){
            roomFlow = examRoom.getRoomFlow();
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        recruitExamTeacherList = recruitExamTeacherBiz.searchExamTeacherList(orgFlow, teaName, roomFlow, teaRole);

        //查出所有考场名称
        examRoomNameList = recruitExamRoomBiz.searchAllExamRoomName(orgFlow);


        //记录监考教师对应的考场名称
        HashMap<String, String> map = new HashMap<>();
        if (recruitExamTeacherList != null && !recruitExamTeacherList.isEmpty() &&recruitExamTeacherList.size() > 0){
            for (RecruitExamTeacher examTeacher:recruitExamTeacherList){
                RecruitExamRoom recruitExamRoom = recruitExamRoomBiz.searchExamRoomByFlow(examTeacher.getRoomFlow());
                map.put(examTeacher.getRoomFlow(),recruitExamRoom.getRoomName());
            }
        }

        model.addAttribute("examTeacherList",recruitExamTeacherList);
        model.addAttribute("examRoomNameList",examRoomNameList);
        model.addAttribute("map",map);

        model.addAttribute("teaName",teaName);
        model.addAttribute("roomName",roomName);
        model.addAttribute("teaRole",teaRole);
        return "recruit/exam/teacher";
    }

    @RequestMapping("/addExamTeacherForm")
    public String addExamTeacherForm(Model model){
        List<String> examRoomNameList =new ArrayList<String>();
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow(); //获取当前登陆用户
        //查出所有考场名称
        List<String> nameList = recruitExamRoomBiz.searchAllExamRoomName(orgFlow);
        for (String examRoomName:nameList) {
            examRoomNameList.add(examRoomName);
        }
        model.addAttribute("examRoomNameList",examRoomNameList);
        return "recruit/exam/addExamTeacher";
    }

    @RequestMapping("/addExamTeacher")
    @ResponseBody
    public String addExamTeacher(RecruitExamTeacher examTeacher,String roomName){
        SysUser currentUser = GlobalContext.getCurrentUser();
        RecruitExamRoom examRoom = recruitExamRoomBiz.searchExamRoomByName(roomName,currentUser.getOrgFlow());
        if (examRoom == null){
            return GlobalConstant.SAVE_FAIL;
        }
        examTeacher.setRoomFlow(examRoom.getRoomFlow());
        int i = recruitExamTeacherBiz.addExamTeacher(examTeacher);
        if (i == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/editExamTeacher")
    public String editExamTeacher(String teacherFlow,Model model) {
        RecruitExamTeacher recruitExamTeacher = recruitExamTeacherBiz.searchExamTeacherByFlow(teacherFlow);
        if (recruitExamTeacher != null) {
            RecruitExamRoom recruitExamRoom = recruitExamRoomBiz.searchExamRoomByFlow(recruitExamTeacher.getRoomFlow());
            //查出所有考场名称
            List<String> examRoomNameList = new ArrayList<String>();
            List<String> nameList = recruitExamRoomBiz.searchAllExamRoomName(GlobalContext.getCurrentUser().getOrgFlow());
            if (nameList != null && nameList.size() != 0) {
                for (String examRoomName : nameList) {
                    examRoomNameList.add(examRoomName);
                }
            }
            model.addAttribute("editInfo", recruitExamTeacher);
            model.addAttribute("editExamRoomName", recruitExamRoom.getRoomName());
            model.addAttribute("examRoomNameList",examRoomNameList);
        }
        return "recruit/exam/editExamTeacher";
    }

    @RequestMapping("/updateExamTeacher")
    @ResponseBody
    public String updateExamTeacher(RecruitExamTeacher editInfo,String roomName){
        SysUser currentUser = GlobalContext.getCurrentUser();
        RecruitExamRoom examRoom = recruitExamRoomBiz.searchExamRoomByName(roomName, currentUser.getOrgFlow());
        if (examRoom == null ){
            return GlobalConstant.SAVE_FAIL;
        }
        editInfo.setRoomFlow(examRoom.getRoomFlow());
        int i = recruitExamTeacherBiz.updateExamTeacher(editInfo);
        if (i == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/deleteExamTeacher")
    @ResponseBody
    public String deleteExamTeacher(String teacherFlow){
        int i = recruitExamTeacherBiz.deleteExamTeacherByFlow(teacherFlow);
        if (i == 1){
            return GlobalConstant.DELETE_SUCCESSED;
        }else {
            return GlobalConstant.DELETE_FAIL;
        }
    }

}
