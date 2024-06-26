package com.pinde.sci.ctrl.xjgl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.sys.ISysUserRoleBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.xjgl.IXjNoticeBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/xjgl/notice")
public class XjglNoticeController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(XjglNoticeController.class);
    @Autowired
    private IXjNoticeBiz noticeBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    /**
     *  教学通知
     * */
    @RequestMapping(value="/getList/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
    public String getList(@PathVariable String roleFlag, Integer currentPage , HttpServletRequest request, Model model){
        model.addAttribute("roleFlag", roleFlag);
        //查询该工作站下所有角色
        List<SysRole> roles = userRoleBiz.getByWisd(GlobalConstant.CMIS_WS_ID);
        model.addAttribute("roles",roles);
        //查询完毕
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<EduTeachingnotice> notices;
        if(roleFlag.equals("admin")) {
            notices = this.noticeBiz.searchAllNotice();
        }else {
            SysUser user = GlobalContext.getCurrentUser();
            String userFlow = user.getUserFlow();
            notices = noticeBiz.searchByRoles(userFlow);
        }
        model.addAttribute("infos",notices);
        return "xjgl/plat/teachingNotices";
    }

    @ResponseBody
    @RequestMapping("/saveNotice/{roleFlag}")
    public String saveNotice(@PathVariable String roleFlag,EduTeachingnotice teachingnotice){
        noticeBiz.editNotice(teachingnotice);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping("/findNoticeByFlow")
    @ResponseBody
    public Object findNoticeByFlow(String infoFlow){
        return this.noticeBiz.findNoticByFlow(infoFlow);
    }

    @RequestMapping(value="/noticeView")
    public String message(Model model,String infoFlow) throws Exception{
        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "xjgl/plat/message";
    }

    @ResponseBody
    @RequestMapping("/delNotice")
    public String delNotice(String infoFlow){
        this.noticeBiz.delNotice(infoFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }
}
