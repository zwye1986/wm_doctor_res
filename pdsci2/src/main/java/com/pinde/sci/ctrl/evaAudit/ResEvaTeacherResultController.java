package com.pinde.sci.ctrl.evaAudit;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.res.IResGradeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.sci.model.hbres.teacherRec;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/evaTeacherResult")
public class ResEvaTeacherResultController extends GeneralController {

    @Autowired
    private IResGradeBiz resGradeBiz;

    @RequestMapping(value="/main")
    public String main( String year, Integer currentPage, HttpServletRequest request, Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
//        if (year == null || year.equals("")){
//            year = DateUtil.getYear();
//        }
        //查询条件
        Map<String,Object> paramMap = new HashMap<String, Object>();
        String orgFlow = currentUser.getOrgFlow();
        paramMap.put("orgFlow", orgFlow);
        String userFlow = currentUser.getUserFlow();
        paramMap.put("userFlow", userFlow);

        paramMap.put("sessionNumber",year);
        String recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
        paramMap.put("recTypeId",recType);

        PageHelper.startPage(currentPage,getPageSize(request));
        List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore(paramMap);
        model.addAttribute("datas",userList);
        model.addAttribute("allScore", "100");
//        model.addAttribute("year",year);
        return "hbres/evaAudit/resultSearch/teacherResule";
    }









}
