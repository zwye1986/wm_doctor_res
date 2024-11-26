package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgAddressBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.res.ResOrgAddressForm;
import com.pinde.sci.model.mo.ResOrgAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/res/address")
public class ResAddressController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(ResAddressController.class);

	@Autowired
	private IResOrgAddressBiz orgAddressBiz;

	@Autowired
	private IOrgBiz orgBiz;

	/**
	 * 设置单个签到地址
	 * @param model
	 * @return
	 */
	@RequestMapping("/oneAddress")
	public String oneAddress(Model model,String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)) {
			ResOrgAddress orgAddress = orgAddressBiz.readOrgAddressByFlow(recordFlow);
			model.addAttribute("orgAddress", orgAddress);
		}
		return "res/address/oneAddress";
	}

	/**
	 * 设置签到地址
	 * @param model
	 * @return
	 */
	@RequestMapping("/signinSet")
	public String signinSet(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		List<ResOrgAddress> addressList = orgAddressBiz.readOrgAddress(orgFlow);
		if(addressList!=null&&addressList.size()>0)
		{
			model.addAttribute("orgAddress", addressList.get(0));
		}
		model.addAttribute("addressList",addressList);
		return "res/address/signinSet";
	}

	@RequestMapping("/saveSigninSet")
	@ResponseBody
	public String saveSigninSet(ResOrgAddress address){
		String orgFlow=address.getOrgFlow();
		if(StringUtil.isBlank(address.getOrgFlow()))
		{

			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		address.setOrgFlow(orgFlow);
		ResOrgAddress orgAddress = orgAddressBiz.readOrgOneAddress(orgFlow);
		if(orgAddress!=null)
			address.setRecordFlow(orgAddress.getRecordFlow());
		int result = orgAddressBiz.saveOrgAddress(address);
		if(result>GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping("/saveSigninSetList")
	@ResponseBody
	public String saveSigninSetList(@RequestBody ResOrgAddressForm bean){

		String orgFlow=bean.getOrgFlow();
		if(StringUtil.isBlank(bean.getOrgFlow()))
		{
			return "提交格式错误！";
		}
		if(!bean.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
		{
			return "当前登录人已变更，刷新页面！";
		}
		int result = orgAddressBiz.saveOrgAddresses(bean);
		if(result>GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

}

