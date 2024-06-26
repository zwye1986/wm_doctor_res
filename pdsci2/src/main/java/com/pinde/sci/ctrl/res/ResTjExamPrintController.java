package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.ITjResExamBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.TjDocinfo;
import com.pinde.sci.model.res.TjDocinfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/res/tjExam")
public class ResTjExamPrintController extends GeneralController{
    @Autowired
    private ITjResExamBiz resExamBiz;

    @RequestMapping("/index")
    public String showIndex(TjDocinfo searchDocinfo,Model model){
        TjDocinfo docinfo = (TjDocinfo) GlobalContext.getSession().getAttribute("currUser");
        searchDocinfo.setDocrole("1");
        if(docinfo.getDocrole().equals("1")){
            if(StringUtil.isNotBlank(docinfo.getUserId())){
                searchDocinfo.setUserId(docinfo.getUserId());
                List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(searchDocinfo);
                model.addAttribute("docinfo",extList.get(0));
            }
            return "/tjresexam/index";
        }
        if(docinfo.getDocrole().equals("2")){
            if(StringUtil.isNotBlank(docinfo.getOrgCode())) {
                searchDocinfo.setOrgCode(docinfo.getOrgCode());
                List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(searchDocinfo);
                model.addAttribute("extList",extList);
            }
            return "/tjresexam/adminIndex";
        }
        if(docinfo.getDocrole().equals("3")){
            List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(searchDocinfo);
            model.addAttribute("extList",extList);
            return "/tjresexam/adminIndex";
        }
        return "";
    }
    @RequestMapping("/printCard")
    public String printCard(String userId,Model model){
        TjDocinfo searchDocinfo = new TjDocinfo();
        if(StringUtil.isNotBlank(userId)){
            searchDocinfo.setUserId(userId);
            List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(searchDocinfo);
            model.addAttribute("docinfo",extList.get(0));
        }
        return "/tjresexam/printExamCard";
    }
    @RequestMapping("/printCertificate")
    public String printCertificate(String userId,Model model){
        TjDocinfo searchDocinfo = new TjDocinfo();
        if(StringUtil.isNotBlank(userId)){
            searchDocinfo.setUserId(userId);
            List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(searchDocinfo);
            model.addAttribute("docinfo",extList.get(0));
        }
        return "/tjresexam/printCertificate";
    }

    @RequestMapping("/searchDocInfo")
    public String searchDocInfo(TjDocinfo docinfo,Model model){
        docinfo.setDocrole("1");
        List<TjDocinfoExt> extList = resExamBiz.searchTjDocinfoExt(docinfo);
        model.addAttribute("extList",extList);
        return "/tjresexam/adminIndex";
    }
}
