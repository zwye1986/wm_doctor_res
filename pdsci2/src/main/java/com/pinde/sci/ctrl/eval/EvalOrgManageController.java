package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/eval/orgManage")
public class EvalOrgManageController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IEvalCfgBiz evalCfgBiz;
    @Autowired
    private IExpertOrgBiz evalOrgBiz;
    @Autowired
    private IExpertOrgSpeBiz expertOrgSpeBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IResJointOrgBiz jointBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/orgManage/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model){
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysOrg> orgList=sysOrgBiz.searchOrg(sysOrg);
        model.addAttribute("orgList",orgList);
        //协同医院
        List<ResJointOrg> jointList = jointBiz.searchJointOrgAll();
        if(jointList!=null &&jointList.size()>0){
            Map<String,List<ResJointOrg>> jointOrgMap = new HashMap<String,List<ResJointOrg>>();
            for(ResJointOrg jointOrg : jointList){
                String key = jointOrg.getOrgFlow();
                if(jointOrgMap.get(key)==null){
                    List<ResJointOrg> jointOrgList = new ArrayList<ResJointOrg>();
                    jointOrgList.add(jointOrg);
                    jointOrgMap.put(key,jointOrgList);
                }else{
                    jointOrgMap.get(key).add(jointOrg);
                }
            }
            model.addAttribute("jointOrgMap",jointOrgMap);
        }
        return "eval/orgManage/list";
    }

    @RequestMapping(value = "/edit",method={RequestMethod.GET,RequestMethod.POST})
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
        return "eval/orgManage/edit";
    }

    @RequestMapping(value={"/save"},method=RequestMethod.POST)
    @ResponseBody
    public String save(SysOrg org,HttpServletRequest request) throws Exception{
        String xml = "";
        SysOrg oldOrg =null;
        if(StringUtil.isNotBlank(org.getOrgFlow())){
            oldOrg = this.sysOrgBiz.readSysOrg(org.getOrgFlow());
            xml = oldOrg.getOrgInfo();
        }
        Map<String , String[]> datasMap = request.getParameterMap();//传orgXmlUtilMap<String , String[]>参数
        xml = _createXmlOrg(datasMap , "orgInfo." , xml);//获取xml中的参数
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
        //校验基地代码重复
        int ckeckResult=sysOrgBiz.checkOrgCodeNotSelf(org.getOrgCode(),org.getOrgFlow());
        if(ckeckResult>0)
        {
            return "该基地代码已存在，请重新输入！";
        }
        //校验基地名称重复
        int ckeckResult2=sysOrgBiz.checkOrgNameNotSelf(org.getOrgName(),org.getOrgFlow());
        if(ckeckResult2>0)
        {
            return "该基地名称已存在，请重新输入！";
        }
        if(oldOrg!=null&&OrgLevelEnum.CountryOrg.getId().equals(oldOrg.getOrgLevelId())&&!OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
        {
            List<ResJointOrg> jointOrgs=jointBiz.searchResJointByOrgFlow(org.getOrgFlow());
            if(jointOrgs!=null&&jointOrgs.size()>0)
            {
                return "基地类型变更为非国家基地，该基地下有协同基地，请先取消关联！";
            }
        }
        sysOrgBiz.saveOrg(org);
        InitConfig._loadOrg(request.getServletContext());
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public String delete(SysOrg org,HttpServletRequest request){
        List<ResJointOrg> jointOrgs=jointBiz.searchResJointByOrgFlow(org.getOrgFlow());
        if(jointOrgs!=null&&jointOrgs.size()>0)
        {
            return "该基地下有协同基地，请先取消关联后，再删除！";
        }
        sysOrgBiz.saveOrg(org);
        InitConfig._loadOrg(request.getServletContext());
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     *
     * @param flow
     * @param model
     * @return
     */
    @RequestMapping("/jointAll")
    public String jointAll(String flow,String currentPage,Model model){
        if(StringUtil.isNotBlank(flow)){
            SysOrg org=sysOrgBiz.readSysOrg(flow) ;
            model.addAttribute("org", org);
            //查询
            List<SysOrg> resultList = sysOrgBiz.searchOrgNotSelfAndNotCountryAndNotProvince(flow);
            model.addAttribute("resultList", resultList);
            //jointRead页面checkbox框保存之后默认选中
            List<ResJointOrg> jointList=jointBiz.searchResJointByOrgFlow(flow);
            if(jointList!=null && jointList.size()>0){
                Map<String,ResJointOrg> jointMap = new HashMap<String, ResJointOrg>();
                for(ResJointOrg joint : jointList){
                    jointMap.put(joint.getJointOrgFlow(),joint);
                }
                model.addAttribute("jointMap",jointMap);
            }
        }
        model.addAttribute("currentPage",currentPage);
        return "/eval/orgManage/jointRead";
    }

    /**
     * jointRead页面保存
     * @param orgFlow
     * @param jointOrgFlows
     * @param delJointFlows
     * @return
     */
    @RequestMapping("/saveJoint")
    @ResponseBody
    public String saveJoint(String orgFlow,String[] jointOrgFlows,String[] delJointFlows){
        List<ResJointOrg> jointOrgList = new ArrayList<ResJointOrg>();
        //勾上复选框保存
        if(jointOrgFlows!=null && jointOrgFlows.length>0){
            List<String> orgFlows = new ArrayList<String>();
            orgFlows.add(orgFlow);
            List<String> jointOrgFlowList = Arrays.asList(jointOrgFlows);
            orgFlows.addAll(jointOrgFlowList);
            List<SysOrg> orgList=sysOrgBiz.searchOrgFlowIn(orgFlows);
            if(orgList!=null && orgList.size()>0){
                Map<String,SysOrg> orgMap = new HashMap<String,SysOrg>();
                for(SysOrg org : orgList){
                    orgMap.put(org.getOrgFlow(),org);
                }
                for(String jointOrgFlow : jointOrgFlows){
                    ResJointOrg jointOrg = new ResJointOrg();
                    jointOrg.setOrgFlow(orgFlow);
                    jointOrg.setOrgName(orgMap.get(orgFlow).getOrgName());
                    jointOrg.setJointOrgFlow(jointOrgFlow);
                    jointOrg.setJointOrgName(orgMap.get(jointOrgFlow).getOrgName());
                    jointOrgList.add(jointOrg);
                    int check=jointBiz.checkJointOrgNotSelf(orgFlow,jointOrgFlow);
                    if(check>0)
                    {
                        return "协同基地【"+orgMap.get(jointOrgFlow).getOrgName()+"】已被其他基地关联，无法保存！";
                    }
                }
            }
        }
        //去掉复选框保存
        if(delJointFlows!=null && delJointFlows.length>0){
            for(String jointFlow : delJointFlows){
                ResJointOrg jointOrg = new ResJointOrg();
                jointOrg.setJointFlow(jointFlow);
                jointOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                jointOrgList.add(jointOrg);
            }
        }
        int result=jointBiz.editJointOrgList(jointOrgList);
        if(result!=GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
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
            doc= DocumentHelper.parseText(xml);
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
            Set<Map.Entry<String , String[]>> entrys = datasMap.entrySet();
            Iterator<Map.Entry<String , String[]>> iterator = entrys.iterator();
            while(iterator.hasNext()){
                Map.Entry<String , String[]> entry = iterator.next();
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
            Set<Map.Entry<String , String[]>> entrys = datasMap.entrySet();
            Iterator<Map.Entry<String , String[]>> iterator = entrys.iterator();
            while(iterator.hasNext()){
                Map.Entry<String , String[]> entry = iterator.next();
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
}