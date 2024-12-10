package com.pinde.sci.ctrl.osca;


import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.osca.IOscaNoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/osca/oscaNotice")
public class OscaNoticeController extends GeneralController {

    @Autowired
    private IOscaNoticeBiz noticeBiz;

    /**
     *  通知公告
     * */
    @RequestMapping(value="/notice/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
    public String notice(@PathVariable String roleFlag, Integer currentPage , HttpServletRequest request, Model model){
        model.addAttribute("roleFlag", roleFlag);
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<InxInfo> infos = null;
        //searchInfoByOrgBeforeDate扩展
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            infos = this.noticeBiz.searchInfoByOrgBeforeDate(null,null);
        }else{
            SysUser user = GlobalContext.getCurrentUser();
            String userOrgFlow= user.getOrgFlow();
            infos = this.noticeBiz.searchInfoByOrgBeforeDate(userOrgFlow,null);
        }

        model.addAttribute("infos",infos);
        return "/osca/notice/notice";
    }

    @ResponseBody
    @RequestMapping("/saveNotice/{roleFlag}")
    public String saveNotice(@PathVariable String roleFlag,InxInfo info){
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            SysUser user = GlobalContext.getCurrentUser();
            String userOrgFlow= user.getOrgFlow();
            String userOrgName=user.getOrgName();
            info.setOrgFlow(userOrgFlow);
            info.setOrgName(userOrgName);
        }
        info.setColumnId("jinengkaohe");
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
        return "/osca/notice/message";
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
        return "/osca/notice/noticeList";
    }
}
