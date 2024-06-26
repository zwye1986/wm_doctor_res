package com.pinde.sci.ctrl.sys;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.SysOrg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/sys/org")
public class OrgController extends GeneralController {
	
	private static Logger logger=LoggerFactory.getLogger(OrgController.class);
	@Autowired
	private IOrgBiz sysOrgBiz;

	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(SysOrg org, Integer currentPage, HttpServletRequest request, Model model){
		PageHelper.startPage(currentPage, getPageSize(request));
//		org.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//		if(this.getSessionAttribute("currWsId").toString().equals(GlobalConstant.EDC_WS_ID) && StringUtil.isBlank(org.getOrgProvId())){
//			org.setOrgProvId(InitConfig.getSysCfg("srm_default_orgProvId"));
//		}
		List<SysOrg> sysList=sysOrgBiz.searchOrg(org);
		model.addAttribute("sysList",sysList);
		return "sys/org/list";
	}
	
	@RequestMapping(value = "/edit",method={RequestMethod.GET})
	public String edit(String orgFlow,Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg=sysOrgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg", sysOrg);
			Map<String,Object> orgInfoData =_parseXmlOrg(sysOrg.getOrgInfo());//通过Map<String,Object>,OrgXmlUtil.parseXmlOrg解析
			model.addAttribute("orgInfoData",orgInfoData);//放到model里面去
			List<SysOrg> childOrgList = this.sysOrgBiz.searchChildrenOrgByOrgFlow(orgFlow);
			List<String> childOrgFlowList = new ArrayList<String>();
			for(SysOrg org:childOrgList){
				childOrgFlowList.add(org.getOrgFlow());
			}
			model.addAttribute("childOrgFlowList" , childOrgFlowList);
		}
		return "sys/org/edit";
	}

	//四川招录前台专用
	@RequestMapping(value = "/edit4sczy",method={RequestMethod.GET})
	public String edit4sczy(String orgFlow,Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg=sysOrgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg", sysOrg);
			Map<String,Object> orgInfoData =_parseXmlOrg(sysOrg.getOrgInfo());//通过Map<String,Object>,OrgXmlUtil.parseXmlOrg解析
			model.addAttribute("orgInfoData",orgInfoData);//放到model里面去
			List<SysOrg> childOrgList = this.sysOrgBiz.searchChildrenOrgByOrgFlow(orgFlow);
			List<String> childOrgFlowList = new ArrayList<String>();
			for(SysOrg org:childOrgList){
				childOrgFlowList.add(org.getOrgFlow());
			}
			model.addAttribute("childOrgFlowList" , childOrgFlowList);
		}
		return "sczyres/manage/orgEdit";
	}

	@RequestMapping(value={"/save4sczy"},method=RequestMethod.POST)
	@ResponseBody
	public String save4sczy(SysOrg org,HttpServletRequest request) throws Exception{
		sysOrgBiz.saveOrg(org);
		InitConfig._loadOrg(request.getServletContext());
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value = "/info",method={RequestMethod.GET})
	public String info(Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg=sysOrgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg", sysOrg);
			Map<String,Object> orgInfoData =_parseXmlOrg(sysOrg.getOrgInfo());//通过Map<String,Object>,OrgXmlUtil.parseXmlOrg解析
			model.addAttribute("orgInfoData",orgInfoData);//放到model里面去			
		}
		return "sys/org/info";
	} 
	
	@RequestMapping(value={"/save"},method=RequestMethod.POST)
	@ResponseBody
	public String save(SysOrg org,HttpServletRequest request) throws Exception{
		String xml = "";
		if(StringUtil.isNotBlank(org.getOrgFlow())){
			SysOrg oldOrg = this.sysOrgBiz.readSysOrg(org.getOrgFlow());
			xml = oldOrg.getOrgInfo();
			
//			if(!org.getOrgName().equals(oldOrg.getOrgName()) && StringUtil.isNotBlank(org.getOrgName())){
//				ResDoctor doctor = new ResDoctor();
//				doctor.setOrgFlow(org.getOrgFlow());
//				doctor.setOrgName(org.getOrgName());
//				doctorBiz.updateRedundancyData(doctor);
//			}
		}
		Map<String , String[]> datasMap = request.getParameterMap();//传orgXmlUtilMap<String , String[]>参数
		xml = _createXmlOrg(datasMap , "orgInfo." , xml);//获取xml中的参数
//		org.setOrgProvName(DictTypeEnum.OrgProv.getDictNameById(org.getOrgProvId()));
		if (StringUtil.isNotBlank(org.getOrgTypeId())) {
			org.setOrgTypeName(OrgTypeEnum.getNameById(org.getOrgTypeId()));
		}
		org.setOrgInfo(xml);//放进OrgInfo大字段
		if(StringUtil.isNotBlank(org.getChargeOrgFlow())){
			org.setChargeOrgName(InitConfig.getOrgNameByFlow(org.getChargeOrgFlow()));			
		}else{
			org.setChargeOrgFlow("");
			org.setChargeOrgName("");
		}
		if(StringUtil.isNotBlank(org.getOrgLevelId())){
			org.setOrgLevelName(OrgLevelEnum.getNameById(org.getOrgLevelId()));
		}else{
			org.setOrgLevelName("");
		}
		sysOrgBiz.saveOrg(org);
		
		InitConfig._loadOrg(request.getServletContext());
		return GlobalConstant.SAVE_SUCCESSED;		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(SysOrg org,HttpServletRequest request){
//		org.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		sysOrgBiz.saveOrg(org);
		InitConfig._loadOrg(request.getServletContext());
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	

	@RequestMapping("/loadApplyOrg")
	@ResponseBody
	public Object loadApplyOrg(@RequestParam(value="orgFlow" , required=true)String orgFlow){
		SysOrg sysOrg = new SysOrg();
		sysOrg.setChargeOrgFlow(orgFlow);
		sysOrg.setRecordStatus(GlobalConstant.FLAG_Y);
		List<SysOrg> orgList = this.sysOrgBiz.searchOrg(sysOrg);
		//List<SysOrg> chargeOrgList = this.sysOrgBiz.searchChargeOrg();
		//List<SysOrg> resultOrgList = new ArrayList<SysOrg>();
		
		return orgList;
	}
	
	/**
	 * 
	 * @param datasMap 参数的MAP集合
	 * @param namePattern xml字段的前缀
	 * @return
	 */
	private String _createXmlOrg(Map<String , String[]> datasMap , String namePattern , String xml) throws Exception{
		Document document = null;
		if(StringUtil.isBlank(xml)){
			document = DocumentHelper.createDocument();
			Element rootElement=document.addElement("orgInfo");//根节点
			Element infoEle = rootElement.addElement("info");//信息节点
			Set<Entry<String , String[]>> entrys = datasMap.entrySet();
			Iterator<Entry<String , String[]>> iterator = entrys.iterator();
			while(iterator.hasNext()){
				Entry<String , String[]> entry = iterator.next();
				String key = entry.getKey();
				if(!key.startsWith(namePattern)){
					continue;
				}
				String[] values = entry.getValue();
				Element itemEle = infoEle.addElement("item");
				itemEle.addAttribute("name", key);
				if(values!=null && values.length>0){
					for(String val:values){
						Element valEle = itemEle.addElement("value");
						valEle.setText(val);
					}
				}
			}
			
		}else{
			document = DocumentHelper.parseText(xml);
			Element rootEle = document.getRootElement();
			Element infoEle = (Element)rootEle.selectSingleNode("info");
			if(infoEle!=null){
				infoEle.detach();//如果info节点存在就删除掉
			}
			//重新创建info节点
			infoEle = rootEle.addElement("info");//信息节点
			Set<Entry<String , String[]>> entrys = datasMap.entrySet();
			Iterator<Entry<String , String[]>> iterator = entrys.iterator();
			while(iterator.hasNext()){
				Entry<String , String[]> entry = iterator.next();
				String key = entry.getKey();
				if(!key.startsWith(namePattern)){
					continue;
				}
				String[] values = entry.getValue();
				Element itemEle = infoEle.addElement("item");
				itemEle.addAttribute("name", key);
				if(values!=null && values.length>0){
					for(String val:values){
						Element valEle = itemEle.addElement("value");
						valEle.setText(val);
					}
				}
			}
		}
		return document.asXML();
		
	}
	
	/**
	 * 
	 * @param xml
	 * @return
	 */
	private Map<String, Object> _parseXmlOrg(String xml){
		Map<String , Object> map = new HashMap<String , Object>();
		if(StringUtil.isBlank(xml)){
			return map;
		}
		Document doc=null;
		try {
			doc=DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();//根节点
			Element infoEle = (Element)rootEle.selectSingleNode("info");//查找info节点
			if(infoEle==null){
				return map;
			}
			Iterator iterator = infoEle.elementIterator();
			while(iterator.hasNext()){
				Element itemEle = (Element)iterator.next();
				String name = itemEle.attributeValue("name");
				List<Element> valEleList = itemEle.elements();
				if(valEleList!=null && valEleList.size()==1){
					Element valEle = (Element)valEleList.get(0);
					map.put(name, valEle.getText());
				}else if(valEleList!=null && valEleList.size()>1){
					List<String> vals = new ArrayList<String>(); 
					for(Object obj : valEleList){
						Element valEle = (Element)obj;
						String val = valEle.getText();
						vals.add(val);
					}
					map.put(name, vals);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

}
