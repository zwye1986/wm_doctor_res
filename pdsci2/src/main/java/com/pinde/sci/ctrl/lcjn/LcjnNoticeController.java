package com.pinde.sci.ctrl.lcjn;


import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.lcjn.ILcjnNoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/lcjn/lcjnNotice")
public class LcjnNoticeController extends GeneralController {

    @Autowired
    private ILcjnNoticeBiz noticeBiz;

    /**
     *  通知公告
     * */
    @RequestMapping(value="/notice",method={RequestMethod.POST,RequestMethod.GET})
    public String notice(Integer currentPage , HttpServletRequest request, Model model){
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<InxInfo> infos = null;
        //searchInfoByOrgBeforeDate扩展
        SysUser user = GlobalContext.getCurrentUser();
        String userOrgFlow= user.getOrgFlow();
        infos = this.noticeBiz.searchInfoByOrgBeforeDate(userOrgFlow,null);
        model.addAttribute("infos",infos);
        return "/lcjn/notice/notice";
    }

    @ResponseBody
    @RequestMapping("/saveNotice")
    public String saveNotice(InxInfo info){
            SysUser user = GlobalContext.getCurrentUser();
            String userOrgFlow= user.getOrgFlow();
            String userOrgName=user.getOrgName();
            info.setOrgFlow(userOrgFlow);
            info.setOrgName(userOrgName);
        info.setColumnId("lcjn");
        noticeBiz.editInfo(info);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping("/findNoticeByFlow")
    @ResponseBody
    public Object findNoticeByFlow(String infoFlow){
        return this.noticeBiz.findNoticByFlow(infoFlow);
    }

    @RequestMapping(value="/noticeView")
    public String message(Model model,String infoFlow) throws Exception{
        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "/lcjn/notice/message";
    }

    @ResponseBody
    @RequestMapping("/delNotice")
    public String delNotice(String infoFlow){
        this.noticeBiz.delNotice(infoFlow);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

    /**
     * 公告列表
     * @param model
     * @return
     */
    @RequestMapping(value="/noticeList",method=RequestMethod.GET)
    public String noticeList(Integer currentPage ,HttpServletRequest request, Model model){
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));

        List<InxInfo> infos = this.noticeBiz.searchInfoByOrgBeforeDate(GlobalContext.getCurrentUser().getOrgFlow(),null);
        model.addAttribute("infos",infos);
        return "/lcjn/notice/noticeList";
    }
}
