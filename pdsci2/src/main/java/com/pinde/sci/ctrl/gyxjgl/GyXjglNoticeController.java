package com.pinde.sci.ctrl.gyxjgl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjNoticeBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.form.gyxjgl.XjEduUserExtInfoForm;
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
import java.util.Map;

@Controller
@RequestMapping("/gyxjgl/notice")
public class GyXjglNoticeController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(GyXjglNoticeController.class);
    @Autowired
    private IGyXjNoticeBiz noticeBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IGyXjEduUserBiz eduUserBiz;
    /**
     *  教学通知
     * */
    @RequestMapping(value="/getList/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
    public String getList(@PathVariable String roleFlag, String infoTypeId, Integer currentPage , HttpServletRequest request, Model model){
        model.addAttribute("roleFlag", roleFlag);
        //查询该工作站下所有角色
        List<SysRole> roles = userRoleBiz.getByWisd("gycmis");
        model.addAttribute("roles",roles);
        //查询完毕
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<EduTeachingnotice> notices;
        if("admin".equals(roleFlag)) {
            notices = this.noticeBiz.searchAllNotice();
        }else {
            SysUser user = GlobalContext.getCurrentUser();
            String userFlow = user.getUserFlow();
            notices = noticeBiz.searchByRoles(userFlow,infoTypeId);
        }
        model.addAttribute("infos",notices);
        return "gyxjgl/plat/teachingNotices";
    }
    @RequestMapping(value="/getListByType",method={RequestMethod.POST,RequestMethod.GET})
    public String getListByType(String infoTypeId, Integer currentPage , HttpServletRequest request, Model model){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<EduTeachingnotice> notices;
        if(StringUtil.isBlank(infoTypeId)) {
            notices = this.noticeBiz.searchAllNotice();
        }else {
            notices = noticeBiz.searchByRoles(null,infoTypeId);
        }
        model.addAttribute("infos",notices);
        return "gyxjgl/plat/noticeByType";
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
        return "gyxjgl/plat/message";
    }

    @ResponseBody
    @RequestMapping("/delNotice")
    public String delNotice(String infoFlow){
        this.noticeBiz.delNotice(infoFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping(value="/agencyThing")
    public String agencyThing(Model model){
        Map<String,Object> dataMap = noticeBiz.searchAgencyThing();
        model.addAttribute("dataMap",dataMap);
        return "gyxjgl/plat/agencyThing";
    }

    /**
     * 是否缴清学费 触发缴费通知
     * @param userFlow 通知对象
     * @param noticeFlag 区分是否缴清学费
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendPaidFeeNotice")
    public String sendPaidFeeNotice(String userFlow,String noticeFlag){
        noticeBiz.sendPaidFeeNotice(userFlow,noticeFlag);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     *  缴费通知 设置/查询
     * */
    @RequestMapping(value="/paidNotice/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
    public String paidNotice(@PathVariable String roleFlag, Integer currentPage , HttpServletRequest request, Model model){
        model.addAttribute("roleFlag", roleFlag);
        List<FeeNoticeTemplate> notices = new ArrayList<>();
        if("admin".equals(roleFlag)) {
            PageHelper.startPage(currentPage,getPageSize(request));
            notices = noticeBiz.searchAllFeeNotice();
            model.addAttribute("config",noticeBiz.queryFeeConfig());
        }else {
            EduUser eduUser=eduUserBiz.findByFlow(GlobalContext.getCurrentUser().getUserFlow());
            if(eduUser!=null){
                String content = eduUser.getContent();
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                PageHelper.startPage(currentPage,getPageSize(request));
                notices = noticeBiz.searchFeeNotice(extInfoForm.getPaidFee());
            }
        }
        model.addAttribute("infos",notices);
        return "gyxjgl/plat/paidNoticesManage";
    }

    /**
     * 缴费通知 发布/保存
     * @param template
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveFeeNotice")
    public String saveFeeNotice(FeeNoticeTemplate template){
        int num = noticeBiz.saveFeeTemplate(template);
        if(num > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 缴费通知 查询（编辑）
     * @param recordFlow
     * @return
     */
    @RequestMapping("/findFeeNoticeByFlow")
    @ResponseBody
    public Object findFeeNoticeByFlow(String recordFlow){
        return noticeBiz.findFeeNoticByFlow(recordFlow);
    }

    /**
     * 缴费通知 删除
     * @param recordFlow
     * @return
     */
    @ResponseBody
    @RequestMapping("/delFeeNotice")
    public String delFeeNotice(String recordFlow){
        noticeBiz.delFeeNotice(recordFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    /**
     * 缴费通知 查看
     * @param model
     * @param recordFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/feeNoticeView")
    public String feeNoticeView(Model model,String recordFlow) throws Exception{
        model.addAttribute("msg", noticeBiz.findFeeNoticByFlow(recordFlow));
        return "gyxjgl/plat/feeNoticeView";
    }

    /**
     * 样式流程配置
     * @param model
     * @return
     */
    @RequestMapping(value="/feeConfig",method={RequestMethod.POST,RequestMethod.GET})
    public String feeConfig(Model model){
        model.addAttribute("config",noticeBiz.queryFeeConfig());
        return "gyxjgl/plat/feeConfig";
    }

    /**
     * 缴费通知配置 保存
     * @param config
     * @return
     */
    @RequestMapping("/saveFeeConfig")
    @ResponseBody
    public String saveFeeConfig(FeeNoticeConfig config){
        if(StringUtil.isBlank(config.getPublishSwitch())){
            config.setPublishSwitch(GlobalConstant.FLAG_N);
        }
        if(StringUtil.isBlank(config.getFontRequired())){
            config.setFontRequired(GlobalConstant.FLAG_N);
        }
        int num = noticeBiz.saveFeeConfig(config);
        if(num > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
}
