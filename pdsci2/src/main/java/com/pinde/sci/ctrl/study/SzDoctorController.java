package com.pinde.sci.ctrl.study;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.study.IStudySubjectBiz;
import com.pinde.sci.biz.study.IStudySubjectDetailBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.StudySubjectDetail;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieshihai
 */
@Controller
@RequestMapping("/study/doctor")
public class SzDoctorController extends GeneralController {
    @Autowired
    private IStudySubjectBiz studySubjectBiz;
    @Autowired
    private IStudySubjectDetailBiz subjectDetailBiz;

    /**
     * @author limin
     * 查询发布的课程
     */
    @RequestMapping(value="/subjectList")
    public String subjectList(String flag,Integer currentPage,Model model,HttpServletRequest request) throws Exception{
        if (currentPage == null) {
            currentPage = 1;
        }
        SysUser user = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage, getPageSize(request));
        ArrayList<StudySubject> subjectList = new ArrayList<>();
        ArrayList<StudySubject> orderList = new ArrayList<>();
        Map<String,StudySubjectDetail> map = new HashMap<>();
        Map<String,Integer> numMap = new HashMap<>();
        if(user!=null){
            String postStatus = "Y";//发布
            if(flag.equals("false")){//查询所有
                List<StudySubject> allList = studySubjectBiz.findAllSubject(postStatus);
                for(StudySubject s:allList)
                {
                    int num=studySubjectBiz.findAppiontNum(s.getSubjectFlow());
                    numMap.put(s.getSubjectFlow(),num);
                }
                model.addAttribute("dataList",allList);
            }else{//预约人员未满
                List<StudySubject> allList = studySubjectBiz.findSubject(postStatus);
                for(StudySubject s:allList)
                {
                    int num=studySubjectBiz.findAppiontNum(s.getSubjectFlow());
                    numMap.put(s.getSubjectFlow(),num);
                }
                model.addAttribute("flag","true");
                model.addAttribute("dataList",allList);
            }
            //查询是否预约
            List<StudySubjectDetail> studySubjectDetailList = subjectDetailBiz.findByDoctorFlow(user.getUserFlow());
            if(studySubjectDetailList!=null)
            for(StudySubjectDetail d:studySubjectDetailList)
            {
                map.put(d.getSubjectFlow(),d);
            }
        }

        model.addAttribute("map",map);
        model.addAttribute("numMap",numMap);
        model.addAttribute("nowDate",DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
        return "study/doctor/subjectList";
    }

    /**
     * @author limin
     * 预约课程查看
     */
    @RequestMapping(value="/searchSubject")
    public String searchSubject(String subjectFlow,Model model) throws Exception{
        StudySubject studySubject = studySubjectBiz.findBySubjectFlow(subjectFlow);
        model.addAttribute("studySubject",studySubject);
        return "study/doctor/searchSubject";
    }

    /**
     * 保存
     */
    @RequestMapping(value="/saveSubject",method={RequestMethod.POST})
    @ResponseBody
    public synchronized String saveSubject(StudySubject studySubject,Model model) throws Exception{
        if(studySubject != null) {
            StudySubject subject = studySubjectBiz.findBySubjectFlow(studySubject.getSubjectFlow());

            StudySubjectDetail d=studySubjectBiz.getSubjectDetail(studySubject.getSubjectFlow(),GlobalContext.getCurrentUser().getUserFlow());
            if(d!=null)
            {
                return "你已预约！";
            }
            int num=studySubjectBiz.findAppiontNum(subject.getSubjectFlow());
            if(Integer.valueOf(subject.getReservationsNum())-num<=0)
            {
                return "预约人数已满";
            }
            int result = studySubjectBiz.saveSubject(subject);
            if (GlobalConstant.ZERO_LINE != result) {
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 更新数据
     * @param subjectFlow
     * @param model
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value="/updateStudySubject")
    public String updateStudySubject(String subjectFlow,Model model) throws Exception{
        if(subjectFlow != null) {
            StudySubject studySubject = studySubjectBiz.findBySubjectFlow(subjectFlow);
            String remainderNum = (Integer.parseInt(studySubject.getRemainderNum())-1)+"";
            studySubject.setRemainderNum(remainderNum);
            int result = studySubjectBiz.updateStudySubject(studySubject);
            if (result > 0) {
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }*/
}
