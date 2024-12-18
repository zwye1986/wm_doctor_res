package com.pinde.sci.ctrl.osca;

import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysOrg;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaExamineInfoBiz;
import com.pinde.sci.biz.osca.ISiteInformationBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/5/12
 */

@Controller
@RequestMapping("/osca/examineInfo")
public class OscaExamineInfoController extends GeneralController {
    @Autowired
    private IOscaExamineInfoBiz cityManagerBiz;
    @Autowired
    private ISiteInformationBiz siteInformationBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;

    /**
     * 专业考核情况查询
     * @param clinicalYear
     * @param speId
     * @param orgFlow
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkCityInfoBySpeList/{flag}", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchCheckInfoBySpe(String clinicalYear, String speId, String orgFlow, String orgCityId,Integer currentPage,
                                       @PathVariable String flag,HttpServletRequest request, Model model,String trainingTypeId){
        String userOrgFlow="";
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear= DateUtil.getYear();
        }
        if(StringUtil.isBlank(orgFlow)){
            userOrgFlow= GlobalContext.getCurrentUser().getOrgFlow();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("clinicalYear",clinicalYear);
        map.put("speId",speId);
        map.put("orgFlow",orgFlow);
        map.put("flag",flag);
        map.put("userOrgFlow",userOrgFlow);
        map.put("orgCityId",orgCityId);
        SysDict searchDict = new SysDict();
        searchDict.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        searchDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + trainingTypeId);
        List<SysDict> dictList = dictBiz.searchDictList(searchDict);
        map.put("dictList",dictList);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> checkInfoList=cityManagerBiz.selectCheckInfoBySpeId(map);
        DecimalFormat df=new DecimalFormat("0.00");
        double percent=0.00;
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for (Map<String,Object> info:checkInfoList) {
                double a=Double.parseDouble(info.get("PASSNUM").toString());
                int b=Integer.parseInt(info.get("TOTLENUM").toString());
                int c=Integer.parseInt(info.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                info.put("percent",df.format(percent*100)+"");
            }
        }
        List<Map<String,String>> cityOrgList=new ArrayList<>();
        if("city".equals(flag)){//市局角色
            cityOrgList=cityManagerBiz.selectCityOrg(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("global".equals(flag)){//省厅角色
            Map<String, String> map1=new HashMap<>();
            map1.put("userOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
            map1.put("orgCityId",orgCityId);
            cityOrgList=cityManagerBiz.selectGlobalOrg(map1);//考点
            SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            List<SysOrg> orgCityList=siteInformationBiz.serachOrgCity(sysOrg);//地市
            model.addAttribute("orgCityList",orgCityList);
        }
        model.addAttribute("cityOrgList",cityOrgList);
        model.addAttribute("checkInfoList",checkInfoList);
        model.addAttribute("thisYear",clinicalYear);
        model.addAttribute("flag",flag);
        if("global".equals(flag)){
            return "/osca/global/globalSpeCheckInfoList";
        }
        return "/osca/city/checkStatus/speCheckStatus";
    }

    /**
     * 地市基地联动
     * @param orgCityId
     * @return
     */
    @RequestMapping(value="/loadCityOrgList")
    @ResponseBody
    public List<SysOrg> loadCityOrgList(String orgCityId){
        List<SysOrg> cityOrgList=new ArrayList<>();
        Map<String, String> map1=new HashMap<>();
        map1.put("userOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        map1.put("orgCityId",orgCityId);
        cityOrgList=cityManagerBiz.selectGlobalOrg1(map1);
        return cityOrgList;
    }
    /**
     * 基地考核情况查询
     * @param clinicalYear
     * @param speId
     * @param orgFlow
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkCityInfoByOrgList/{flag}", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchCheckInfoByOrgFlow(String clinicalYear, String speId, String orgFlow,String orgCityId, @PathVariable String flag,
                                           Integer currentPage, HttpServletRequest request, Model model,String trainingTypeId){
        String userOrgFlow="";
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear= DateUtil.getYear();
        }
        if(StringUtil.isBlank(orgFlow)){
            userOrgFlow= GlobalContext.getCurrentUser().getOrgFlow();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("clinicalYear",clinicalYear);
        map.put("speId",speId);
        map.put("orgFlow",orgFlow);
        map.put("userOrgFlow",userOrgFlow);
        map.put("orgCityId",orgCityId);
        map.put("flag",flag);
        SysDict searchDict = new SysDict();
        searchDict.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        searchDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + trainingTypeId);
        List<SysDict> dictList = dictBiz.searchDictList(searchDict);
        map.put("dictList",dictList);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> checkInfoList=cityManagerBiz.selectCheckInfoByOrgFlow(map);
        DecimalFormat df=new DecimalFormat("0.00");
        Double percent=null;
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for (Map<String,Object> info:checkInfoList) {
                double a=Double.parseDouble(info.get("PASSNUM").toString());
                int b=Integer.parseInt(info.get("TOTLENUM").toString());
                int c=Integer.parseInt(info.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                info.put("percent",df.format(percent*100)+"");
            }
        }
        List<Map<String,String>> cityOrgList=new ArrayList<>();
        if("city".equals(flag)){//市局角色
            cityOrgList=cityManagerBiz.selectCityOrg(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("global".equals(flag)){//省厅角色
            Map<String, String> map1=new HashMap<>();
            map1.put("userOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
            map1.put("orgCityId",orgCityId);
            cityOrgList=cityManagerBiz.selectGlobalOrg(map1);//考点
            SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            List<SysOrg> orgCityList=siteInformationBiz.serachOrgCity(sysOrg);//地市
            model.addAttribute("orgCityList",orgCityList);
        }
        model.addAttribute("cityOrgList",cityOrgList);
        model.addAttribute("checkInfoList",checkInfoList);
        model.addAttribute("thisYear",clinicalYear);
        model.addAttribute("flag",flag);
        if("global".equals(flag)){
            return "/osca/global/globalOrgCheckInfoList";
        }
        return "/osca/city/checkStatus/orgCheckStatus";
    }

    /**
     * 考核情况导出
     * @param clinicalYear
     * @param speId
     * @param orgFlow
     * @param flag
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportCityInfo/{flag}/{roleFlag}")
    public void exportInfo(String clinicalYear, String speId, String orgFlow,String orgCityId, @PathVariable String flag,@PathVariable String roleFlag,HttpServletResponse response)throws Exception {
        String[] titles;//导出列表头信息
        String userOrgFlow="";
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear= DateUtil.getYear();
        }
        if(StringUtil.isBlank(orgFlow)){
            userOrgFlow= GlobalContext.getCurrentUser().getOrgFlow();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("clinicalYear",clinicalYear);
        map.put("speId",speId);
        map.put("orgFlow",orgFlow);
        map.put("userOrgFlow",userOrgFlow);
        map.put("orgCityId",orgCityId);
        map.put("flag",roleFlag);
        List<Map<String,Object>> checkInfoList=cityManagerBiz.selectCheckInfoBySpeId(map);
        if("orgInfo".equals(flag)){
            checkInfoList=cityManagerBiz.selectCheckInfoByOrgFlow(map);
        }
        DecimalFormat df=new DecimalFormat("0.00");
        double percent=0.00;
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for (Map<String,Object> info:checkInfoList) {
                double a=Double.parseDouble(info.get("PASSNUM").toString());
                int b=Integer.parseInt(info.get("TOTLENUM").toString());
                int c=Integer.parseInt(info.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                info.put("percent",df.format(percent*100)+"%");
            }
        }
        titles = new String[]{
                "SPE_NAME:专业",
                "TOTLENUM:应考人数",
                "NOSIGNNUM:缺考人数",
                "PASSNUM:通过人数",
                "UNPASSNUM:不通过人数",
                "percent:合格率"
        };
        if("orgInfo".equals(flag)){
            titles = new String[]{
                    "ORG_NAME:培训基地",
                    "TOTLENUM:报考人数",
                    "NOSIGNNUM:缺考人数",
                    "PASSNUM:合格数",
                    "UNPASSNUM:不合格",
                    "percent:合格率"
            };
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, checkInfoList, response.getOutputStream());
        String fileName = "专业考核情况.xls";
        if("orgInfo".equals(flag)){
            fileName="基地考核情况.xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 基地管理员专业考核情况查询
     * @param clinicalYear
     * @param speId
     * @param isLocal
     * @param clinicalName
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/orgCheckInfoList", method = {RequestMethod.GET, RequestMethod.POST})
    public String orgCheckInfolist(String clinicalYear, String speId, String isLocal, String clinicalName,Integer currentPage,
                                   HttpServletRequest request, Model model,String trainingTypeId){
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear= DateUtil.getYear();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("clinicalYear",clinicalYear);
        map.put("speId",speId);
        map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        map.put("isLocal",isLocal);
        map.put("clinicalName",clinicalName);
        SysDict searchDict = new SysDict();
        searchDict.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        searchDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId() + "." + trainingTypeId);
        List<SysDict> dictList = dictBiz.searchDictList(searchDict);
        map.put("dictList",dictList);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> checkInfoList=cityManagerBiz.selectOrgCheckInfo(map);
        DecimalFormat df=new DecimalFormat("0.00");
        Double percent=null;
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for (Map<String,Object> info:checkInfoList) {
                double a=Double.parseDouble(info.get("PASSNUM").toString());
                int b=Integer.parseInt(info.get("TOTLENUM").toString());
                int c=Integer.parseInt(info.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                info.put("percent",df.format(percent*100)+"");
            }
        }
        model.addAttribute("checkInfoList",checkInfoList);
        model.addAttribute("thisYear",clinicalYear);
        return "/osca/examResult/orgCheckInfoList";
    }

    /**
     * 专业考核情况导出（基地管理员）
     * @param clinicalYear
     * @param speId
     * @param isLocal
     * @param clinicalName
     * @param response
     */
    @RequestMapping(value = "/exportOrgCheckInfo")
    public void exportOrgCheckInfo(String clinicalYear, String speId, String isLocal, String clinicalName,HttpServletResponse response)throws Exception {
        String[] titles;//导出列表头信息
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear= DateUtil.getYear();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("clinicalYear",clinicalYear);
        map.put("speId",speId);
        map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        map.put("isLocal",isLocal);
        map.put("clinicalName",clinicalName);
        List<Map<String,Object>> checkInfoList=cityManagerBiz.selectOrgCheckInfo(map);
        DecimalFormat df=new DecimalFormat("0.00");
        Double percent=null;
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for (Map<String,Object> info:checkInfoList) {
                double a=Double.parseDouble(info.get("PASSNUM").toString());
                int b=Integer.parseInt(info.get("TOTLENUM").toString());
                int c=Integer.parseInt(info.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                info.put("percent",df.format(percent*100)+"%");
                if(info.get("IS_LOCAL")!=null){
                    String isLocalName="";
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(info.get("IS_LOCAL").toString())) {
                        isLocalName="结业考核";
                    }
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.get("IS_LOCAL").toString())) {
                        isLocalName="院内考核";
                    }
                    info.put("IS_LOCAL",isLocalName);
                }
            }
        }
        titles = new String[]{
                "SPE_NAME:专业",
                "CLINICAL_YEAR:年份",
//                "IS_LOCAL:考核类型",
//                "CLINICAL_NAME:考核名称",
                "TOTLENUM:应考人数",
                "NOSIGNNUM:缺考人数",
                "PASSNUM:通过人数",
                "UNPASSNUM:不通过人数",
                "percent:合格率"
        };

        String fileName = "";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isLocal)) {
            fileName+="院内考核.xls";
        } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(isLocal)) {
            fileName+="结业考核.xls";
        }

        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, checkInfoList, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    //统计信息查询-基地考核统计
    @RequestMapping("/orgInfoList")
    public String orgInfolist(String clinicalYear, String isLocal, String orgFlow, Integer currentPage, HttpServletRequest request,
                              String orgName,Model model){
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear = DateUtil.getYear();
        }
        Map<String, Object> paraMap=new HashMap<>();
        paraMap.put("clinicalYear",clinicalYear);
        paraMap.put("currentOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paraMap.put("orgFlow",orgFlow);
        paraMap.put("isLocal",isLocal);
        paraMap.put("orgName",orgName);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> resultMapList=cityManagerBiz.queryOrgInfo(paraMap);
        DecimalFormat df=new DecimalFormat("0.00");
        Double percent=null;
        Double percentAll=null;
        if(resultMapList!=null&&resultMapList.size()>0){
            for (Map<String,Object> result:resultMapList) {
                double a=Double.parseDouble(result.get("PASSNUM").toString());
                int b=Integer.parseInt(result.get("TOTLENUM").toString());
                int c=Integer.parseInt(result.get("NOSIGNNUM").toString());
                percent=a==0.0?0:a/(b-c);
                if(b-c==0.0){
                    result.put("percent","-");
                }else {
                    result.put("percent",df.format(percent*100)+"%");
                }
                double a2=Double.parseDouble(result.get("PASSNUMALL").toString());
                int b2=Integer.parseInt(result.get("TOTLENUMALL").toString());
                int c2=Integer.parseInt(result.get("NOSIGNNUMALL").toString());
                percentAll=a2==0.0?0:a2/(b2-c2);
                if(b2-c2==0.0){
                    result.put("percentAll","-");
                }else {
                    result.put("percentAll",df.format(percentAll*100)+"%");
                }
            }
        }
        model.addAttribute("resultMapList",resultMapList);
        model.addAttribute("thisYear",clinicalYear);
        return "/osca/examResult/orgInfoList";
    }

    //统计信息查询-基地考核统计-打印
    @RequestMapping("/exportOrgInfoList")
    public void exportOrgInfoList(String clinicalYear, String isLocal, String orgFlow, HttpServletResponse response,
                              String orgName) throws Exception {
        String[] titles;//导出列表头信息
        if(StringUtil.isBlank(clinicalYear)){
            clinicalYear = DateUtil.getYear();
        }
        if(StringUtil.isBlank(isLocal)){
            isLocal = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        Map<String, Object> paraMap=new HashMap<>();
        paraMap.put("clinicalYear",clinicalYear);
        paraMap.put("currentOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        paraMap.put("orgFlow",orgFlow);
        paraMap.put("isLocal",isLocal);
        paraMap.put("orgName",orgName);
        List<Map<String,Object>> resultMapList=cityManagerBiz.queryOrgInfo(paraMap);
        DecimalFormat df = new DecimalFormat("#0.00%");
        if(resultMapList!=null&&resultMapList.size()>0){
            for (Map<String,Object> result:resultMapList) {
                double a=Double.parseDouble(result.get("PASSNUM").toString());
                int b=Integer.parseInt(result.get("TOTLENUM").toString());
                int c=Integer.parseInt(result.get("NOSIGNNUM").toString());
                if(b-c==0.0){
                    result.put("percent","-");
                }else {
                    result.put("percent",df.format(a/(b-c)));
                }
                double a2=Double.parseDouble(result.get("PASSNUMALL").toString());
                int b2=Integer.parseInt(result.get("TOTLENUMALL").toString());
                int c2=Integer.parseInt(result.get("NOSIGNNUMALL").toString());
                if(b2-c2==0.0){
                    result.put("percentAll","-");
                }else {
                    result.put("percentAll",df.format(a2/(b2-c2)));
                }
                if(result.get("IS_LOCAL")!=null){
                    String isLocalName="";
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(result.get("IS_LOCAL").toString())) {
                        isLocalName="结业考核";
                    }
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(result.get("IS_LOCAL").toString())) {
                        isLocalName="院内考核";
                    }
                    result.put("IS_LOCAL",isLocalName);
                }
            }
        }
        titles = new String[]{
                "ORG_NAME:培训基地",
                "CLINICAL_YEAR:年份",
                "IS_LOCAL:考核类型",
                "SPE_NAME:考核专业",
                "TOTLENUM:应考人数",
                "NOSIGNNUM:缺考人数",
                "PASSNUM:通过人数",
                "UNPASSNUM:不通过人数",
                "percent:合格率",
                "percentAll:总合格率"
        };

        String fileName = "基地考核统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
