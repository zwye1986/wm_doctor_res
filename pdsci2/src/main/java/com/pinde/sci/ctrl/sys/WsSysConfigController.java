package com.pinde.sci.ctrl.sys;

import com.pinde.core.common.sci.dao.SysWsConfigMapper;
import com.pinde.core.model.SysWsConfig;
import com.pinde.core.model.SysWsConfigExample;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IWsCfgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sys/wsCfg")
public class WsSysConfigController extends GeneralController{
	private static Logger logger=LoggerFactory.getLogger(WsSysConfigController.class);

	@Autowired
	private IWsCfgBiz wsCfgBiz;

	@RequestMapping(value={"/main"})
	public String main(Model model){

		return "sys/wsCfg/main";
	}

	@RequestMapping(value = {"/list" })
	public String list(Model model,Integer currentPage,HttpServletRequest request,
							String wsId) throws Exception{
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysWsConfig> list = wsCfgBiz.searchList(wsId);
		model.addAttribute("list", list);
		return "sys/wsCfg/list";
	}
	@RequestMapping(value = {"/check" })
	@ResponseBody
	public String check(Model model,String wsId) throws Exception{
		SysWsConfig config = wsCfgBiz.searchByKey(wsId);
		if(config!=null)
			return "1";
		return "0";
	}
	@RequestMapping(value = {"/edit" })
	public String edit(Model model,String wsId,String isEdit) throws Exception{
		SysWsConfig config = wsCfgBiz.searchByKey(wsId);
		model.addAttribute("config", config);
		return "sys/wsCfg/edit";
	}
	@RequestMapping(value = {"/save" })
	@ResponseBody
	public String save(Model model,SysWsConfig config) throws Exception{
		config.setWsName(InitConfig.getWorkStationName(config.getWsId()));
		int result=wsCfgBiz.save(config);
		if(result==0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value = {"/updateDefault" })
	@ResponseBody
	public String updateDefault(Model model,SysWsConfig config) throws Exception{
		int result=wsCfgBiz.updateDefault(config);
		if(result==0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	public static String getSysTitle()
	{
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		SysWsConfigMapper quserBiz= SpringUtil.getBean(SysWsConfigMapper.class);
		String defaultSysTitle=StringUtil.defaultString(InitConfig.getSysCfg("sys_title_name"));
		//获取工作站配置
		SysWsConfig config=quserBiz.selectByPrimaryKey(wsId);
		if(config!=null&&StringUtil.isNotBlank(config.getSysTitleName()))
		{
			return config.getSysTitleName();
		}else{
			SysWsConfigExample example=new SysWsConfigExample();
			SysWsConfigExample.Criteria criteria=example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsDefaultEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysWsConfig> list= quserBiz.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				config= list.get(0);
			}
			if(config!=null&&StringUtil.isNotBlank(config.getSysTitleName()))
			{
				return config.getSysTitleName();
			}
		}
		return defaultSysTitle;
	}
	public static String getHeadImg()
	{
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		SysWsConfigMapper quserBiz= SpringUtil.getBean(SysWsConfigMapper.class);
		String defaultSysTitle="/css/skin/"+InitConfig.getSysCfg("sys_skin")+"/images/"+InitConfig.getSysCfg("sys_login_img")+"_head.png";
		//获取工作站配置
		SysWsConfig config=quserBiz.selectByPrimaryKey(wsId);
		if(config!=null&&StringUtil.isNotBlank(config.getSysHeadImg()))
		{
			return config.getSysHeadImg();
		}else{
			SysWsConfigExample example=new SysWsConfigExample();
			SysWsConfigExample.Criteria criteria=example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsDefaultEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysWsConfig> list= quserBiz.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				config= list.get(0);
			}
			if(config!=null&&StringUtil.isNotBlank(config.getSysHeadImg()))
			{
				return config.getSysHeadImg();
			}
		}
		return defaultSysTitle;
	}
	public static String getLoginImg()
	{
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		SysWsConfigMapper quserBiz= SpringUtil.getBean(SysWsConfigMapper.class);
		String defaultSysTitle="/css/skin/"+InitConfig.getSysCfg("sys_skin")+"/images/all_login.png";
		//获取工作站配置
		SysWsConfig config=quserBiz.selectByPrimaryKey(wsId);
		if(config!=null&&StringUtil.isNotBlank(config.getSysLoginImg()))
		{
			return config.getSysLoginImg();
		}else{
			SysWsConfigExample example=new SysWsConfigExample();
			SysWsConfigExample.Criteria criteria=example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsDefaultEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysWsConfig> list= quserBiz.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				config= list.get(0);
			}
			if(config!=null&&StringUtil.isNotBlank(config.getSysLoginImg()))
			{
				return config.getSysLoginImg();
			}
		}
		return defaultSysTitle;
	}
}
