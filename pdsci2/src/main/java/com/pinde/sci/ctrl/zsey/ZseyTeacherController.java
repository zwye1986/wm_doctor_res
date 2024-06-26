package com.pinde.sci.ctrl.zsey;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.zsey.IZseyDeptBiz;
import com.pinde.sci.biz.zsey.IZseyTeacherBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.enums.zsey.ZseyAuditStatusEnum;
import com.pinde.sci.model.mo.ZseyAppointMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zsey/teacher")
public class ZseyTeacherController extends GeneralController {

    @Autowired
    private IZseyTeacherBiz teacherBiz;

    @RequestMapping("/appointList")
    public String appointList(String trainDate,Integer currentPage,Model model){
        PageHelper.startPage(currentPage,10);
        List<ZseyAppointMain> dataList = teacherBiz.queryAppointList(trainDate);
        model.addAttribute("dataList",dataList);
        return "zsey/teacher/appointList";
    }
}