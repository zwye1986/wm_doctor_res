package com.pinde.sci.ctrl.osca;

import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaOrgMenuBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.OscaOrgMenu;
import com.pinde.sci.model.osca.OscaOrgMenuExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/osca/oscaOrgMenu")
public class OscaOrgMenuController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IOscaOrgMenuBiz oscaOrgMenuBiz;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String OscaOrgMenuList(Integer currentPage, OscaOrgMenuExt oscaOrgMenuExt, HttpServletRequest request, Model model){
        SysOrg sysOrg=new SysOrg();
        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(currUser.getOrgFlow())){
            sysOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
            oscaOrgMenuExt.setOrgProvId(sysOrg.getOrgProvId());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<OscaOrgMenuExt> orgMenuList =oscaOrgMenuBiz.searchOscaOrgMenuList(oscaOrgMenuExt);
        if(orgMenuList!=null&&orgMenuList.size()>0){
            for(OscaOrgMenuExt oome:orgMenuList){
                if(oome!=null){
                    OscaOrgMenu menu=new OscaOrgMenu();
                    menu.setOrgFlow(oome.getOrgFlow());
                    List<OscaOrgMenu> menus=oscaOrgMenuBiz.searchAllOrgMenu(menu);
                    if (menus!=null&&menus.size()>0){
                        for (OscaOrgMenu menuTemp:menus) {
                            if (menuTemp.getMenuId().equals("osca-gly-jcxxgl-khxm")){
                                oome.setProjectMenu("osca-gly-jcxxgl-khxm");
                            }
                            if (menuTemp.getMenuId().equals("osca-gly-jcxxgl-pfbd")){
                                oome.setScoreMenu("osca-gly-jcxxgl-pfbd");
                            }
                            if (menuTemp.getMenuId().equals("osca-gly-jcxxgl-kgxx")){
                                oome.setExamMenu("osca-gly-jcxxgl-kgxx");
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("orgMenuList",orgMenuList);
        return "/osca/global/oscaOrgMenuList";
    }

    @RequestMapping(value = {"/saveOrgMenu"})
    @ResponseBody
    public String saveOrgMenu(String orgFlow,String orgName,String menuId,String isChecked) throws Exception{
        String menuName="";
        orgName=java.net.URLDecoder.decode(orgName,"UTF-8");
        if (menuId .equals("osca-gly-jcxxgl-khxm")){
            menuName="考核项目管理";
        }
        if (menuId .equals("osca-gly-jcxxgl-pfbd")){
            menuName="评分表单配置";
        }
        if (menuId .equals("osca-gly-jcxxgl-kgxx")){
            menuName="考官信息管理";
        }
        OscaOrgMenu oscaOrgMenu=new OscaOrgMenu();
        oscaOrgMenu.setOrgFlow(orgFlow);
        oscaOrgMenu.setOrgName(orgName);
        oscaOrgMenu.setMenuId(menuId);
        oscaOrgMenu.setMenuName(menuName);
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(isChecked)) {
            List<OscaOrgMenu> oscaOrgMenuTemp=oscaOrgMenuBiz.searchAllOrgMenu(oscaOrgMenu);
            if (oscaOrgMenuTemp!=null&&oscaOrgMenuTemp.size()>0){
                oscaOrgMenu.setRecordFlow(oscaOrgMenuTemp.get(0).getRecordFlow());
            }
            oscaOrgMenu.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            oscaOrgMenuBiz.updateOscaOrgMenu(oscaOrgMenu);
        }else{
            oscaOrgMenu.setRecordFlow(PkUtil.getUUID());
            oscaOrgMenu.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            oscaOrgMenu.setCreateTime(DateUtil.getCurrentTime());
            oscaOrgMenu.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            oscaOrgMenuBiz.saveOscaOrgMenu(oscaOrgMenu);
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }
}
