package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.InxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/notice")
public class JsResNoticeController extends GeneralController {

	@Autowired
	private INoticeBiz noticeBiz;
	@RequestMapping(value="/main")
	public String main(){
		return "jsres/notice/main";
	}

	/**
	 * 加载医师账户列表
	 * @param currentPage
     * @param sysUser
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/list")
	public String list(Integer currentPage,String noticeTitle,  Model model,HttpServletRequest request){

		PageHelper.startPage(currentPage,getPageSize(request));
		Map<String,String> param=new HashMap<>();
        param.put("resNoticeTypeId", com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID);
        param.put("resNoticeSysId", com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
		param.put("noticeTitle",noticeTitle);
		param.put("orgFlow",null);
		List<InxInfo> infos =   this.noticeBiz.searchInfoByOrg(param);
		model.addAttribute("infos",infos);
		return "jsres/notice/list";
	}

	@ResponseBody
	@RequestMapping("/delNotice")
	public String delNotice(String infoFlow){
		this.noticeBiz.delNotice(infoFlow);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}
	@RequestMapping("/edit")
	public String edit(String infoFlow,  Model model){
		InxInfo info=this.noticeBiz.findNoticByFlow(infoFlow);
		model.addAttribute("info",info);
		return "jsres/notice/edit";
	}


	@ResponseBody
	@RequestMapping("/saveNotice/{roleFlag}")
	public String saveNotice(@PathVariable String roleFlag, InxInfo info, String sessionNumber){

        info.setRoleFlow(com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
		noticeBiz.editInfo(info,null,sessionNumber);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
}
