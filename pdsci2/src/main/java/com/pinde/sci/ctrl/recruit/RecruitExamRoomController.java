package com.pinde.sci.ctrl.recruit;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.recruit.IRecruitExamRoomBiz;
import com.pinde.sci.biz.recruit.IRecruitExamRoomInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamTeacherBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.core.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recruit/examRoom")
public class RecruitExamRoomController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(RecruitExamRoomController.class);

    @Autowired
    private IRecruitExamRoomBiz recruitExamRoomBiz;

    @Autowired
    private IRecruitExamTeacherBiz recruitExamTeacherBiz;

    @Autowired
    private IRecruitExamRoomInfoBiz recruitExamRoomInfoBiz;

    @RequestMapping(value = "/main")
    public String main(Model model, Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<RecruitExamRoom> recruitExamRoomList = null;
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户
        recruitExamRoomList= recruitExamRoomBiz.searchAllExamRoom(orgFlow);

        Map<String,String> map=new  HashMap<String,String>();
        for (RecruitExamRoom examRoom : recruitExamRoomList) {
            String roomFlow = examRoom.getRoomFlow();
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamRoomInfoBiz.IsExamRoomUsed(roomFlow, orgFlow)) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamTeacherBiz.IsExamRoomUsed(roomFlow, orgFlow))) {
                map.put(roomFlow, com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            }else {
                map.put(roomFlow, com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            }
        }
        model.addAttribute("examRoomList",recruitExamRoomList);
        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("map",map);
        return "recruit/exam/room";
    }

    /**
     * 查询考场
     * @param roomName
     * @param roomAddress
     * @param examNum
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchExamRoomList")
    public String searchExamRoomList(String roomName, String roomAddress, String examNum, Model model, Integer currentPage, HttpServletRequest request){
        List<RecruitExamRoom> recruitExamRoomList = null;
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户
        PageHelper.startPage(currentPage,getPageSize(request));
        recruitExamRoomList= recruitExamRoomBiz.searchExamRoomList(orgFlow, roomName, roomAddress,examNum);

        Map<String,String> map=new  HashMap<String,String>();
        for (RecruitExamRoom examRoom : recruitExamRoomList) {
            String roomFlow = examRoom.getRoomFlow();
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamRoomInfoBiz.IsExamRoomUsed(roomFlow, orgFlow)) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamTeacherBiz.IsExamRoomUsed(roomFlow, orgFlow))) {
                map.put(roomFlow, com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            }else {
                map.put(roomFlow, com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            }
        }

        model.addAttribute("examRoomList",recruitExamRoomList);
//        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("roomName",roomName);
        model.addAttribute("roomAddress",roomAddress);
        model.addAttribute("examNum",examNum);
        model.addAttribute("map",map);
        return "recruit/exam/room";
    }


    /**
     * 新增考场
     * @return
     */
    @RequestMapping("/addExamRoom")
    @ResponseBody
    public String addExamRoom(RecruitExamRoom examRoom){
        if (recruitExamRoomBiz.searchExamRoomByName(examRoom.getRoomName(), GlobalContext.getCurrentUser().getOrgFlow()) != null){
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        int i = recruitExamRoomBiz.addExamRoom(examRoom);
        if (i == 1){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/editExamRoom")
    public String editExamRoom(String roomFlow,Model model){
        RecruitExamRoom recruitExamRoom = recruitExamRoomBiz.searchExamRoomByFlow(roomFlow);
        if (recruitExamRoom!=null){
            model.addAttribute("editInfo",recruitExamRoom);
        }
        return "recruit/exam/editExamRoom";
    }

    @RequestMapping("/updateExamRoom")
    @ResponseBody
    public String updateExamRoom(RecruitExamRoom editInfo,String roomFlow){
        RecruitExamRoom examRoom = recruitExamRoomBiz.searchExamRoomByName(editInfo.getRoomName(), GlobalContext.getCurrentUser().getOrgFlow());
        if (examRoom != null && !examRoom.getRoomFlow().equals(roomFlow)){
            return "编辑失败，该考场已存在";
        }else {
            int i = recruitExamRoomBiz.updateExamRoom(editInfo);
            if (i == 1){
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }else {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            }
        }

    }



    @RequestMapping("/changeExamRoomStatus")
    @ResponseBody
    public String changeExamRoomStatus(String roomFlow,String recordStatus){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamRoomInfoBiz.IsExamRoomUsed(roomFlow, orgFlow)) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruitExamTeacherBiz.IsExamRoomUsed(roomFlow, orgFlow))) {
            recordStatus = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(recordStatus) ? com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y : com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
            int i = recruitExamRoomBiz.changeExamRoomStatus(roomFlow,recordStatus);
            if (i == 1){
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
            else {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            }
        }else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping("/addExamRoomFrom")
    public String addExamRoomFrom(){
        return "recruit/exam/addExamRoom";
    }

}
