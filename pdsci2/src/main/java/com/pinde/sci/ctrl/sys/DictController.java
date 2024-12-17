package com.pinde.sci.ctrl.sys;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.model.DictForm;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.TeachingActivityFormValue;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.InitResConfig;
import com.pinde.sci.form.sys.SubDictEditForm;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.*;

@Controller
@RequestMapping("/sys/dict")
public class DictController extends GeneralController{
	private static Logger logger=LoggerFactory.getLogger(DictController.class);

	@Value("#{configProperties['remote.cluster.customer']}")
	private String remoteClusterCustomer;

	@Value("#{configProperties['remote.refresh.hosts']}")
	private String remoteRefreshHosts;

	@Value("#{configProperties['remote.refresh.host']}")
	private String remoteRefreshHost;

	@Value("#{configProperties['remote.refresh.port']}")
	private String remoteRefreshPort;


	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResActivityTargetBiz resActivityTargetBiz;
	
	@RequestMapping(value="/list" , method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView selectDictList(SysDict sysDict) {
		ModelAndView mav = new ModelAndView("sys/dict/list");
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
            int level = com.pinde.core.common.enums.DictTypeEnum.valueOf(sysDict.getDictTypeId()).getLevel();
		    if(level>1){
		    	mav = new ModelAndView("sys/dict/mullist");
		    	mav.addObject("level" , level);
		    }
			List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
			//可简写mav.addObject(dictList);
			mav.addObject("dictList",dictList);
		}
		return mav;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value="dictFlow",required=false) String dictFlow) {
		ModelAndView mav = new ModelAndView("sys/dict/edit");
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			mav.addObject("dict" , dict);
		}
		return mav;
	}
	
	@RequestMapping(value="/subdictedit")
	public String subdictedit(String dictFlow , String parentDictFlow , Model model){
		int level = 0;
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			String dictTypeId = dict.getDictTypeId().split("\\.")[0];
            level = com.pinde.core.common.enums.DictTypeEnum.valueOf(dictTypeId).getLevel();
			model.addAttribute("level" , level);
			model.addAttribute("dict" , dict);
			List<SysDict> subdicts = this.dictBiz.searchDictListAllByDictTypeId(dict.getDictTypeId()+"."+dict.getDictId() , true);
			model.addAttribute("subdicts" , subdicts);
		}
		if(StringUtil.isNotBlank(parentDictFlow)){
			SysDict parentDict = this.dictBiz.readDict(parentDictFlow);
			String dictTypeId = parentDict.getDictTypeId().split("\\.")[0];
            level = com.pinde.core.common.enums.DictTypeEnum.valueOf(dictTypeId).getLevel();
			model.addAttribute("level" , level);
			model.addAttribute("parentDict" , parentDict);
		}
		
		return "sys/dict/subdictedit";
	}
	
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	public @ResponseBody String saveDict(SysDict dict , String parentDictFlow) {
		String dictTypeId = "";
		if(StringUtil.isBlank(parentDictFlow)){
			dictTypeId = dict.getDictTypeId();
            DictTypeEnum dictTypeEnum = com.pinde.core.common.enums.DictTypeEnum.valueOf(dictTypeId);
			dict.setDictTypeName(dictTypeEnum.getName());
		}else{
			SysDict parentDict = this.dictBiz.readDict(parentDictFlow);
			dictTypeId = parentDict.getDictTypeId()+"."+parentDict.getDictId();
			dict.setDictTypeName(parentDict.getDictName());
			dict.setDictTypeId(dictTypeId);
		}
		
		if(StringUtil.isBlank(dict.getDictFlow())){
			//新增字典时判断该类型字典代码是否存在
			SysDict sysDict=dictBiz.readDict(dictTypeId, dict.getDictId());
			if(null!=sysDict){
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
			}
			this.dictBiz.addDict(dict);				
		}else{
			//修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同			
			List<SysDict> dictList=dictBiz.searchDictListByDictTypeIdNotIncludeSelf(dict);
			for(SysDict sysDict:dictList){
				 if(dict.getDictId().equals(sysDict.getDictId())){
                     return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
				 }
			}
			this.dictBiz.modeDictAndSubDict(dict);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value="/saveSubDict" , method=RequestMethod.POST)
	public @ResponseBody String saveSubDict(@RequestBody SubDictEditForm subDictEditForm) {
		try {
			this.dictBiz.saveSubDict(subDictEditForm);
		} catch (Exception e) {
            logger.error("", e);
			return e.getMessage();
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping(value="/delete" , method=RequestMethod.GET)
	public @ResponseBody String delDict(@RequestParam(value="dictFlow",required=true) String dictFlow,@RequestParam(value="recordStatus",required=true) String recordStatus) {		
		this.dictBiz.delDictAndSubDict(dictFlow,recordStatus);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	
	@RequestMapping(value="/refresh" , method=RequestMethod.GET)
	public String refresh(HttpServletRequest request,Model model) {
	    String ret = "refresh";
		if(StringUtil.isNotBlank(remoteClusterCustomer)){
            String indexUrl = InitConfig.getSysCfg("sys_index_url");
            String[] custs = remoteClusterCustomer.split(",");
            boolean isJQ = false;

            for(String cust : custs){
                if(StringUtil.isEquals(cust, indexUrl)){
                    isJQ = true;
                    break;
                }
            }

            //判断当前系统首页，是否为启用了集群的系统，是则进入集群刷新页面（需配置）
            if(isJQ){
                if(StringUtil.isNotBlank(remoteRefreshHosts)) {
                    String[] hosts = remoteRefreshHosts.split(",");
                    model.addAttribute("hostPortList", CollectionUtils.arrayToList(hosts));
                }
                ret = "refreshs";
            }else{
                ret = refreshOld(request, model);
            }
		}else{
            ret = refreshOld(request, model);
		}
		return ret;
	}

	private String refreshOld(HttpServletRequest request,Model model){
		Map<Integer,Integer> portMap = new HashMap<Integer,Integer>();
		List<Map<String, String>> hostPortMapList = new ArrayList<Map<String, String>>();
		if(StringUtil.isNotBlank(remoteRefreshPort)){
			String[] ports = remoteRefreshPort.split(",");
			remoteRefreshHost = StringUtil.isBlank(remoteRefreshHost) ? "127.0.0.1" : remoteRefreshHost;
			for(String port : ports){
				Map<String, String> hostPortMap = new HashMap<String, String>();
				hostPortMap.put("hostname", remoteRefreshHost);
				hostPortMap.put("port", port);
				hostPortMapList.add(hostPortMap);
			}
		}else {
			portMap.put(4007, 7070);
			portMap.put(4008, 8080);
			portMap.put(4009, 9090);
			portMap.put(null, 8080);
			model.addAttribute("portMap", portMap);
			try {
				MBeanServer server = ManagementFactory.getPlatformMBeanServer();
				Set instances = server.queryMBeans(null, null);
				Iterator iterator = instances.iterator();
				while (iterator.hasNext()) {
					ObjectInstance instance = (ObjectInstance) iterator.next();
					ObjectName objectName = instance.getObjectName();
					String canonicalName = objectName.getCanonicalName();
//            	System.out.println("MBean Found:");
//            	System.out.println("Class Name:\t" + instance.getClassName());
//            	System.out.println("Object Name:\t" + instance.getObjectName());
//            	System.out.println("Canonical Name:\t" + objectName.getCanonicalName());
//            	System.out.println("****************************************");
					if (canonicalName.startsWith("Catalina:component=Member")) {
						String hostname = (String) server.getAttribute(objectName, "hostname");
						Integer port = (Integer) server.getAttribute(objectName, "port");
						hostname = hostname.replaceAll(",", ".");
						hostname = hostname.replaceAll("\\{", "");
						hostname = hostname.replaceAll("\\}", "");
						hostname = hostname.replaceAll(" ", "");
						System.out.println("hostname:" + hostname);
						System.out.println("port:" + port);
						Map<String, String> hostPortMap = new HashMap<String, String>();
						hostPortMap.put("hostname", hostname);
						hostPortMap.put("port", portMap.get(port).toString());
						hostPortMapList.add(hostPortMap);
					}
				}
			} catch (Exception e) {
                logger.error("", e);
			}
		}
		model.addAttribute("hostPortMapList", hostPortMapList);
		return "refresh";
	}
	
	@RequestMapping(value="/doRefresh" , method=RequestMethod.GET)
	@ResponseBody
	public String doRefresh(HttpServletRequest request) {
		InitConfig.refresh(request.getServletContext());
//		InitSrmConfig.refresh(request.getServletContext());
//		InitEdcConfig.refresh(request.getServletContext());
		InitResConfig.refresh(request.getServletContext());
		return "刷新成功！";
	}
	
	@RequestMapping(value="/doRemoteRefresh" , method=RequestMethod.GET)
	@ResponseBody
	public String doRemoteRefresh(String hostname,String port,HttpServletRequest request) {
		String url = "http://"+hostname+":"+port+""+request.getContextPath()+"/sys/dict/doRefresh";
		return doRemoteRefresh(url);
	}

	@RequestMapping(value="/doRemoteRefresh2" , method=RequestMethod.GET)
	@ResponseBody
	public String doRemoteRefresh(String hostPort, HttpServletRequest request) {
		String url = "http://"+hostPort+""+request.getContextPath()+"/sys/dict/doRefresh";
		return doRemoteRefresh(url);
	}

	public static String doRemoteRefresh(String url) {
		StringBuilder sb = new StringBuilder();
		InputStream ins = null;
		try {
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(url);
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				ins = method.getResponseBodyAsStream();
				byte[] b = new byte[1024];
				int r_len = 0;
				while ((r_len = ins.read(b)) > 0) {
					sb.append(new String(b, 0, r_len, method
							.getResponseCharSet()));
				}
				logger.debug("doRemoteRefresh @ "+url+" statusCode:"+statusCode);
				return sb.toString();
			}else{
				logger.debug("doRemoteRefresh @ "+url+" statusCode:"+statusCode);
				return "刷新远程失败！";
			}
		} catch (Exception e) {
            logger.error("", e);
			return "刷新远程失败！";
		}
	}
	
	@RequestMapping("/saveorder")
	@ResponseBody
	public String saveOrder(String[] dictFlow , HttpServletRequest request){
		this.dictBiz.saveOrder(dictFlow);
		InitConfig._loadDept(request.getServletContext());
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	
	@RequestMapping("/getChildDict")
	public String getChildDict(SysDict dict , Model model){
		dict = this.dictBiz.readDict(dict.getDictFlow());
		List<SysDict> dicts = this.dictBiz.searchDictListAllByDictTypeId(dict.getDictTypeId()+"."+dict.getDictId() , true);
		String dictType = dict.getDictTypeId().split("\\.")[0];
        int level = com.pinde.core.common.enums.DictTypeEnum.valueOf(dictType).getLevel();
		if(level>2){
			Map<String , List<SysDict>> subDictMap = new HashMap<String, List<SysDict>>();
			//查询再下一级的节点
			if(dicts!=null && !dicts.isEmpty()){
				for(SysDict sd:dicts){
					List<SysDict> subDicts = this.dictBiz.searchDictListAllByDictTypeId(sd.getDictTypeId()+"."+sd.getDictId() , false);
				    subDictMap.put(sd.getDictFlow(), subDicts);
				}
			}
			model.addAttribute("subDicts" , subDictMap);
		}
		model.addAttribute("level" , level);
		model.addAttribute("dictList" , dicts);
		model.addAttribute("parentDict", dict);
		return "sys/dict/subdictlist";
	}
	
	/**
	 * 来源、类别二级菜单查询
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCategoryDictByDeclarer")
	@ResponseBody
	public List<SysDict> getCategoryDictByDeclarer(SysDict dict, Model model){
	    List<SysDict> sysDictList = new ArrayList<SysDict>();
		if(StringUtil.isNotBlank(dict.getDictTypeId())){ 
			dict = this.dictBiz.readDict(dict.getDictFlow());
			sysDictList = dictBiz.searchDictListAllByDictTypeId(dict.getDictTypeId()+"."+dict.getDictId() , true);
		}
		return sysDictList;
	}

	/*
	 导入字典
	 */
	@RequestMapping(value="/importDict")
	@ResponseBody
	public String importStudentExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = dictBiz.importDict(file,"GzykdxSpe");
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	//打开编辑字典表单页面
	@RequestMapping(value="/dictForm")
	public String dictForm(String dictFlow,Model model){
		DictForm search = new DictForm();
		search.setDictFlow(dictFlow);
		List<DictForm> dictForms = dictBiz.searchDictForm(search);
		model.addAttribute("dictForms",dictForms);
		return "sys/dict/dictForm";
	}

	//保存表单
	@RequestMapping("/editForm")
	@ResponseBody
	public int  editForm(String data,String dictFlow){
		List<Map<String,Object>> mapList = JSON.parseObject(data, ArrayList.class);
		if(mapList!=null&&mapList.size()>0){
			for(Map<String,Object> map:mapList){
				String recordFlow = (String)map.get("recordFlow");
				String detailOrder = (String)map.get("detailOrder");
				String detailKey = (String)map.get("detailKey");
				String inputType = (String)map.get("inputType");

				DictForm save = new DictForm();
				save.setDictFlow(dictFlow);
				save.setRecordFlow(recordFlow);
				save.setDetailOrder(detailOrder);
				save.setDetailKey(detailKey);
				save.setInputType(inputType);
				dictBiz.editDictForm(save);
			}
		}
		return 1;
	}

	//删除
	@RequestMapping("/delForm")
	@ResponseBody
	public int delForm(DictForm dictForm){
		TeachingActivityFormValue search = new TeachingActivityFormValue();
		search.setFormFlow(dictForm.getRecordFlow());
		List<TeachingActivityFormValue> activityFormValues = resActivityTargetBiz.searchFormValue(search);
		if(activityFormValues!=null&&activityFormValues.size()>0){
			return -1;
		}
		if(StringUtil.isNotBlank(dictForm.getRecordFlow())){
            dictForm.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			dictBiz.editDictForm(dictForm);
		}
		return 1;
	}
}
