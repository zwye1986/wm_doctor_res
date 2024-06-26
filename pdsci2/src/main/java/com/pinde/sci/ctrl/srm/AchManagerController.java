package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AchTypeEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.ach.AchExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.AchBookExportExt;
import com.pinde.sci.model.srm.AchPatentExportExt;
import com.pinde.sci.model.srm.AchSatExportExt;
import com.pinde.sci.model.srm.AchThesisExportExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 科研成果信息.
 */
@Controller
@RequestMapping("/srm/ach/manager")
public class AchManagerController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IProjSearchBiz projSearchBiz;
    @Autowired
    private IAchExportBiz achExportBiz;


    @RequestMapping(value = "/list")
    public String list(SysUser search, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        search.setOrgFlow(currUser.getOrgFlow());
        List<SysUser> sysUserList = userBiz.searchUser(search);
        model.addAttribute("sysUserList", sysUserList);
        return "srm/personChecked";
    }

    @RequestMapping("/searchProj")
    @ResponseBody
    public List<PubProj> searchProj(String userFlow,String projDeclarerFlow){
        PubProj pubProj = new PubProj();
        pubProj.setProjDeclarerFlow(projDeclarerFlow);
        if( StringUtil.isNotBlank(userFlow)){
            pubProj.setApplyUserFlow(userFlow);
        }else{
            pubProj.setApplyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            pubProj.setApplyOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        List<String> stageList = new ArrayList<>();
        stageList.add(ProjStageEnum.Approve.getId());
        stageList.add(ProjStageEnum.Apply.getId());
        List<PubProj> projList = projSearchBiz.searchProj(pubProj,stageList,null,null,null);
        return projList;
    }

    /**
     * 导出科研成果
     *
     * @param scope
     * @param jsondata
     * @param exportByDept 按科室导出（暂时只有徐州中心医院论文使用）
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportAchExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportAchExcel(@PathVariable String scope, String jsondata,String exportByDept, HttpServletResponse response) throws Exception {
        /*
        * Map maps = (Map)JSON.parse(str);
        * Map mapTypes = JSON.parseObject(str);
        * Map mapType = JSON.parseObject(str,Map.class);
        * Map json = (Map) JSONObject.parse(str);
         */
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String, String> mapType = JSON.parseObject(jsondata, Map.class);
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            mapType.put("applyOrgFlow", currUser.getOrgFlow());
        }
        if (StringUtil.isBlank(mapType.get("operStatusId"))) {
            mapType.put("operStatusId", AchStatusEnum.Submit.getId());
        }
        if (GlobalConstant.FLAG_Y.equals(mapType.get("operStatusId"))) {
            mapType.put("operStatusId", "");
            mapType.put("notOperStatusId", "('"+AchStatusEnum.Apply.getId()+"','"+AchStatusEnum.RollBack.getId()+"')");
        }
        String[] titles;//导出列表头信息
        String fileName = "";
        if (AchTypeEnum.Thesis.getId().equals(scope) || AchTypeEnum.Sat.getId().equals(scope)) {
            List<SysDict> dicts = new ArrayList<>();
            if (AchTypeEnum.Thesis.getId().equals(scope)) {
                dicts = DictTypeEnum.JournalType.getSysDictList();
            }
            if (AchTypeEnum.Sat.getId().equals(scope)) {
                dicts = DictTypeEnum.PrizedGrade.getSysDictList();
            }
            int size = 5;
            if (null != dicts && dicts.size() > 0) {
                size = dicts.size() + size;
            }
            if("Y".equals(exportByDept)){
                size = size-1;
                titles = new String[size];
                titles[0] = "year:年度";
                titles[1] = "num:序号";
                titles[2] = "deptName:科室";
                titles[3] = "amount:总数";
                for (int i = 0; i < dicts.size(); i++) {
                    titles[i + 4] = "typeMap." + dicts.get(i).getDictId() + ":" + dicts.get(i).getDictName();
                }
            }else {
                titles = new String[size];
                titles[0] = "year:年度";
                titles[1] = "num:序号";
                titles[2] = "authorName:姓名";
                titles[3] = "deptName:科室";
                titles[4] = "amount:总数";
                for (int i = 0; i < dicts.size(); i++) {
                    titles[i + 5] = "typeMap." + dicts.get(i).getDictId() + ":" + dicts.get(i).getDictName();
                }
            }


            //论文
            if (AchTypeEnum.Thesis.getId().equals(scope)) {
                List<AchThesisExportExt> thesisExportExtList;
                if("Y".equals(exportByDept)) {
                    thesisExportExtList = achExportBiz.searchAchThesisExportByDept(mapType);
                }else {
                    thesisExportExtList = achExportBiz.searchAchThesis(mapType);
                }
                Map<String, List<AchThesisExportExt>> thesisExportExtMap = new LinkedHashMap<>();
                for (AchThesisExportExt exportExt : thesisExportExtList) {
                    String key = exportExt.getPublishDate();
                    if("Y".equals(exportByDept)){
                        key += exportExt.getDeptFlow();
                    }else{
                        key += exportExt.getUserFlow();
                        key += exportExt.getAuthorDeptFlow();
                    }

                    if (thesisExportExtMap.containsKey(key)) {
                        thesisExportExtMap.get(key).add(exportExt);
                    } else {
                        List<AchThesisExportExt> exportExtList = new ArrayList<>();
                        exportExtList.add(exportExt);
                        thesisExportExtMap.put(key, exportExtList);
                    }
                }
                List<AchExportForm> thesisExportFormList = new ArrayList<>();
                int num = 0;
                for (Map.Entry<String, List<AchThesisExportExt>> entry : thesisExportExtMap.entrySet()) {
                    AchExportForm thesisExportForm = new AchExportForm();
                    thesisExportForm.setYear(entry.getValue().get(0).getPublishDate());
                    if("Y".equals(exportByDept)) {
                        thesisExportForm.setDeptName(entry.getValue().get(0).getDeptName());
                    }else{
                        thesisExportForm.setAuthorName(entry.getValue().get(0).getAuthorName());
                        thesisExportForm.setDeptName(entry.getValue().get(0).getAuthorDeptName());
                    }
                    thesisExportForm.setAmount(new BigDecimal(entry.getValue() != null ? entry.getValue().size() : 0));
                    Map<String, BigDecimal> map = new HashMap<>();
                    for (SysDict dict : dicts) {
                        int i = 0;
                        for (AchThesisExportExt ext : entry.getValue()) {
                            if (StringUtil.isNotBlank(ext.getJourTypeId())){
                                String[] typeIds=ext.getJourTypeId().split(",");
                                for(int j = 0;j<typeIds.length;j++){
                                    if (typeIds[j].equals(dict.getDictId())) {
                                        i++;
                                    }
                                }
                            }

                        }
                        map.put(dict.getDictId(), new BigDecimal(i));
                    }
                    thesisExportForm.setTypeMap(map);
                    num++;
                    thesisExportForm.setNum(new BigDecimal(num));
                    thesisExportFormList.add(thesisExportForm);
                }
                ExcleUtile.exportSimpleExcleByObjs(titles, thesisExportFormList, response.getOutputStream());
                if("Y".equals(exportByDept)) {
                    fileName = "论文_科室.xls";
                }else{
                    fileName = "论文_个人.xls";
                }
            }

            //奖项
            if (AchTypeEnum.Sat.getId().equals(scope)) {
                List<AchSatExportExt> satExportExtList = achExportBiz.searchAchSat(mapType);
                Map<String, List<AchSatExportExt>> satExportExtMap = new HashMap<>();
                for (AchSatExportExt exportExt : satExportExtList) {
                    String key = exportExt.getPrizedDate() + exportExt.getUserFlow() + exportExt.getDeptFlow();
                    if (satExportExtMap.containsKey(key)) {
                        satExportExtMap.get(key).add(exportExt);
                    } else {
                        List<AchSatExportExt> exportExtList = new ArrayList<>();
                        exportExtList.add(exportExt);
                        satExportExtMap.put(key, exportExtList);
                    }
                }
                List<AchExportForm> satExportFormList = new ArrayList<>();
                int num = 0;
                for (Map.Entry<String, List<AchSatExportExt>> entry : satExportExtMap.entrySet()) {
                    AchExportForm satExportForm = new AchExportForm();
                    satExportForm.setYear(entry.getValue().get(0).getPrizedDate());
                    satExportForm.setAuthorName(entry.getValue().get(0).getAuthorName());
                    satExportForm.setDeptName(entry.getValue().get(0).getDeptName());
                    satExportForm.setAmount(new BigDecimal(entry.getValue() != null ? entry.getValue().size() : 0));
                    Map<String, BigDecimal> map = new HashMap<>();
                    for (SysDict dict : dicts) {
                        int i = 0;
                        for (AchSatExportExt ext : entry.getValue()) {
                            if (dict.getDictId().equals(ext.getPrizedGradeId())) {
                                i++;
                            }
                        }
                        map.put(dict.getDictId(), new BigDecimal(i));
                    }
                    satExportForm.setTypeMap(map);
                    num ++;
                    satExportForm.setNum(new BigDecimal(num));
                    satExportFormList.add(satExportForm);
                }
                ExcleUtile.exportSimpleExcleByObjs(titles, satExportFormList, response.getOutputStream());
                fileName = "科研成果（奖项）.xls";
            }
        }

        if (AchTypeEnum.Book.getId().equals(scope) || AchTypeEnum.Patent.getId().equals(scope)) {
            if (AchTypeEnum.Book.getId().equals(scope)) {
                titles = new String[]{
                        "publishDate:年度",
                        "num:序号",
                        "authorName:姓名",
                        "deptName:科室",
                        "bookName:专著"
                };
                List<AchBookExportExt> bookExportExtList = achExportBiz.searchAchBook(mapType);
                ExcleUtile.exportSimpleExcleByObjs(titles, bookExportExtList, response.getOutputStream());
                fileName = "科研成果（著作）.xls";
            }
            if (AchTypeEnum.Patent.getId().equals(scope)) {
                titles = new String[]{
                        "authorizeYear:年度",
                        "num:序号",
                        "authorName:姓名",
                        "deptName:科室",
                        "authorizeCode:专利号",
                        "patentName:专利"
                };
                List<AchPatentExportExt> patentExportExtList = achExportBiz.searchAchPatent(mapType);
                ExcleUtile.exportSimpleExcleByObjs(titles, patentExportExtList, response.getOutputStream());
                fileName = "科研成果（专利）.xls";
            }
        }

        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 导出科研成果(无锡二院)
     *
     * @param scope
     * @param jsondata
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportWXAchExcel/{scope}/{roleScope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportWXAchExcel(@PathVariable String scope,@PathVariable String roleScope, String jsondata, HttpServletResponse response) throws Exception {
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String, String> mapType = JSON.parseObject(jsondata, Map.class);
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            mapType.put("applyOrgFlow", currUser.getOrgFlow());
        }
        if (StringUtil.isBlank(mapType.get("operStatusId"))) {
            mapType.put("operStatusId", AchStatusEnum.Submit.getId());
        }
        if (GlobalConstant.FLAG_Y.equals(mapType.get("operStatusId"))) {
            mapType.put("operStatusId", "");
            mapType.put("notOperStatusId", AchStatusEnum.Apply.getId());
        }
        if ("all".equals(mapType.get("operStatusId"))) {
            mapType.put("operStatusId", "");
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(roleScope)){
            mapType.put("userFlow",currUser.getUserFlow());
        }
        String[] titles;//导出列表头信息
        String fileName = "";
        if (AchTypeEnum.Thesis.getId().equals(scope) || AchTypeEnum.Sat.getId().equals(scope)) {
            List<SysDict> dicts = new ArrayList<>();
            if (AchTypeEnum.Thesis.getId().equals(scope)) {
                dicts = DictTypeEnum.JournalType.getSysDictList();
            }
            if (AchTypeEnum.Sat.getId().equals(scope)) {
                dicts = DictTypeEnum.PrizedGrade.getSysDictList();
            }
            int size = 6;
            if (null != dicts && dicts.size() > 0) {
                size = dicts.size() + 6;
            }
            titles = new String[size];
            titles[0] = "year:年度";
            titles[1] = "num:序号";
            titles[2] = "authorName:姓名";
            titles[3] = "deptName:科室";
            titles[4] = "branchName:支部";
            titles[5] = "amount:总数";
            for (int i = 0; i < dicts.size(); i++) {
                titles[i + 6] = "typeMap." + dicts.get(i).getDictId() + ":" + dicts.get(i).getDictName();
            }

            //论文
            if (AchTypeEnum.Thesis.getId().equals(scope)) {
                List<AchThesisExportExt> thesisExportExtList = achExportBiz.searchAchThesis(mapType);
                Map<String, List<AchThesisExportExt>> thesisExportExtMap = new HashMap<>();
                for (AchThesisExportExt exportExt : thesisExportExtList) {
                    String key = exportExt.getPublishDate() + exportExt.getUserFlow() + exportExt.getDeptFlow()+ exportExt.getBranchId();
                    if (thesisExportExtMap.containsKey(key)) {
                        thesisExportExtMap.get(key).add(exportExt);
                    } else {
                        List<AchThesisExportExt> exportExtList = new ArrayList<>();
                        exportExtList.add(exportExt);
                        thesisExportExtMap.put(key, exportExtList);
                    }
                }
                List<AchExportForm> thesisExportFormList = new ArrayList<>();
                int num = 0;
                for (Map.Entry<String, List<AchThesisExportExt>> entry : thesisExportExtMap.entrySet()) {
                    AchExportForm thesisExportForm = new AchExportForm();
                    thesisExportForm.setYear(entry.getValue().get(0).getPublishDate());
                    thesisExportForm.setAuthorName(entry.getValue().get(0).getAuthorName());
                    thesisExportForm.setDeptName(entry.getValue().get(0).getDeptName());
                    thesisExportForm.setBranchName(entry.getValue().get(0).getBranchName());
                    thesisExportForm.setAmount(new BigDecimal(entry.getValue() != null ? entry.getValue().size() : 0));
                    Map<String, BigDecimal> map = new HashMap<>();
                    for (SysDict dict : dicts) {
                        int i = 0;
                        for (AchThesisExportExt ext : entry.getValue()) {
                            if (StringUtil.isNotBlank(ext.getJourTypeId())){
                                String[] typeIds=ext.getJourTypeId().split(",");
                                for(int j = 0;j<typeIds.length;j++){
                                    if (typeIds[j].equals(dict.getDictId())) {
                                        i++;
                                    }
                                }
                            }

                        }
                        map.put(dict.getDictId(), new BigDecimal(i));
                    }
                    thesisExportForm.setTypeMap(map);
                    num++;
                    thesisExportForm.setNum(new BigDecimal(num));
                    thesisExportFormList.add(thesisExportForm);
                }
                ExcleUtile.exportSimpleExcleByObjs(titles, thesisExportFormList, response.getOutputStream());
                fileName = "科研成果（论文）.xls";
            }

            //奖项
            if (AchTypeEnum.Sat.getId().equals(scope)) {
                List<AchSatExportExt> satExportExtList = achExportBiz.searchAchSat(mapType);
                Map<String, List<AchSatExportExt>> satExportExtMap = new HashMap<>();
                for (AchSatExportExt exportExt : satExportExtList) {
                    String key = exportExt.getPrizedDate() + exportExt.getUserFlow() + exportExt.getDeptFlow()+ exportExt.getBranchId();
                    if (satExportExtMap.containsKey(key)) {
                        satExportExtMap.get(key).add(exportExt);
                    } else {
                        List<AchSatExportExt> exportExtList = new ArrayList<>();
                        exportExtList.add(exportExt);
                        satExportExtMap.put(key, exportExtList);
                    }
                }
                List<AchExportForm> satExportFormList = new ArrayList<>();
                int num = 0;
                for (Map.Entry<String, List<AchSatExportExt>> entry : satExportExtMap.entrySet()) {
                    AchExportForm satExportForm = new AchExportForm();
                    satExportForm.setYear(entry.getValue().get(0).getPrizedDate());
                    satExportForm.setAuthorName(entry.getValue().get(0).getAuthorName());
                    satExportForm.setDeptName(entry.getValue().get(0).getDeptName());
                    satExportForm.setBranchName(entry.getValue().get(0).getBranchName());
                    satExportForm.setAmount(new BigDecimal(entry.getValue() != null ? entry.getValue().size() : 0));
                    Map<String, BigDecimal> map = new HashMap<>();
                    for (SysDict dict : dicts) {
                        int i = 0;
                        for (AchSatExportExt ext : entry.getValue()) {
                            if (dict.getDictId().equals(ext.getPrizedGradeId())) {
                                i++;
                            }
                        }
                        map.put(dict.getDictId(), new BigDecimal(i));
                    }
                    satExportForm.setTypeMap(map);
                    num ++;
                    satExportForm.setNum(new BigDecimal(num));
                    satExportFormList.add(satExportForm);
                }
                ExcleUtile.exportSimpleExcleByObjs(titles, satExportFormList, response.getOutputStream());
                fileName = "科研成果（奖项）.xls";
            }
        }

        if (AchTypeEnum.Book.getId().equals(scope) || AchTypeEnum.Patent.getId().equals(scope)) {
            if (AchTypeEnum.Book.getId().equals(scope)) {
                titles = new String[]{
                        "publishDate:年度",
                        "num:序号",
                        "authorName:姓名",
                        "deptName:科室",
                        "branchName:支部",
                        "bookName:专著"
                };
                List<AchBookExportExt> bookExportExtList = new ArrayList<>();
                if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(roleScope)){
                    mapType.put("deptFlow",currUser.getDeptFlow());
                    if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                        bookExportExtList = achExportBiz.searchAchBook(mapType);
                    }
                } else {
                    bookExportExtList = achExportBiz.searchAchBook(mapType);
                }
                ExcleUtile.exportSimpleExcleByObjs(titles, bookExportExtList, response.getOutputStream());
                fileName = "科研成果（著作）.xls";
            }
            if (AchTypeEnum.Patent.getId().equals(scope)) {
                titles = new String[]{
                        "authorizeDate:年度",
                        "num:序号",
                        "authorName:姓名",
                        "deptName:科室",
                        "branchName:支部",
                        "patentName:专利"
                };
                List<AchPatentExportExt> patentExportExtList = achExportBiz.searchAchPatent(mapType);
                ExcleUtile.exportSimpleExcleByObjs(titles, patentExportExtList, response.getOutputStream());
                fileName = "科研成果（专利）.xls";
            }
        }

        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
