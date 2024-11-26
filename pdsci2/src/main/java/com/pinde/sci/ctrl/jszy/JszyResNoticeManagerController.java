package com.pinde.sci.ctrl.jszy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/jszy/notice")
public class JszyResNoticeManagerController extends GeneralController {

	@Autowired
	private INoticeBiz noticeBiz;
	/**
	 *  通知公告
	 * */
	@RequestMapping(value="/main",method={RequestMethod.POST,RequestMethod.GET})
	public String main(String sysId,String typeId, Integer currentPage , HttpServletRequest request, Model model){
		return "/jszy/notice/main";
	}
	@RequestMapping(value="/notice",method={RequestMethod.POST,RequestMethod.GET})
	public String notice(String roleFlow,String columnId, Integer currentPage , HttpServletRequest request, Model model){
        SysUser user = GlobalContext.getCurrentUser();
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = null;
		if(GlobalConstant.RES_NOTICE_TYPE5_ID.equals(columnId)){
			infos = this.noticeBiz.searchInfoByOrgNoRoleFlow(null,columnId,roleFlow);
		}else if(GlobalConstant.RES_NOTICE_TYPE10_ID.equals(columnId)){
            infos = this.noticeBiz.searchInfoByOrgNoRoleFlow(user.getOrgFlow(),columnId,roleFlow);
		}else{
            infos = this.noticeBiz.searchInfoByOrg(null,columnId,roleFlow);
        }
		model.addAttribute("infos",infos);
		return "/jszy/notice/notice";
	}

	@ResponseBody
	@RequestMapping("/saveNotice")
	public String saveNotice(InxInfo info){
        SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(info.getColumnId())){
			if(GlobalConstant.RES_NOTICE_TYPE5_ID.equals(info.getColumnId()))
			{
				info.setColumnName(GlobalConstant.RES_NOTICE_TYPE5_NAME);
			}
			if(GlobalConstant.RES_NOTICE_TYPE6_ID.equals(info.getColumnId()))
			{
				info.setColumnName(GlobalConstant.RES_NOTICE_TYPE6_NAME);
			}
			if(GlobalConstant.RES_NOTICE_TYPE7_ID.equals(info.getColumnId()))
			{
				info.setColumnName(GlobalConstant.RES_NOTICE_TYPE7_NAME);
			}
			if(GlobalConstant.RES_NOTICE_TYPE8_ID.equals(info.getColumnId()))
			{
				info.setColumnName(GlobalConstant.RES_NOTICE_TYPE8_NAME);
			}
			if(GlobalConstant.RES_NOTICE_TYPE9_ID.equals(info.getColumnId()))
			{
				info.setColumnName(GlobalConstant.RES_NOTICE_TYPE9_NAME);
			}if(GlobalConstant.RES_NOTICE_TYPE10_ID.equals(info.getColumnId()))
            {
                info.setColumnName(GlobalConstant.RES_NOTICE_TYPE10_NAME);
                info.setOrgFlow(user.getOrgFlow());
            }
		}
		noticeBiz.editInfo(info,"","");
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping("/findNoticeByFlow")
	@ResponseBody
	public Object findNoticeByFlow(String infoFlow){
		Map<String,Object> resp=new HashMap<>();
		resp.put("info",this.noticeBiz.findNoticByFlow(infoFlow));
		resp.put("infoRoles",this.noticeBiz.readRoleByFlow(infoFlow));
		return resp;
	}

	@ResponseBody
	@RequestMapping("/delNotice")
	public String delNotice(String infoFlow){
		this.noticeBiz.delNotice(infoFlow);
		return GlobalConstant.DELETE_SUCCESSED;
	}
}
