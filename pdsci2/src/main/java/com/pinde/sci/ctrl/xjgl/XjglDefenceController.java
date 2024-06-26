package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.xjgl.IXjDefenceBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.xjgl.XjAuthorInfoForm;
import com.pinde.sci.form.xjgl.XjAuthorOrgForm;
import com.pinde.sci.form.xjgl.XjPaperTitleExtForm;
import com.pinde.sci.model.mo.*;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/xjgl/paper")
public class XjglDefenceController extends GeneralController {
    @Autowired
    private IXjDefenceBiz defenceBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IRoleBiz roleBiz;
    private static String XS = "xs";
    private static String DS = "ds";
    private static String PYDW = "pydw";
    private static String FWH = "fwh";
    private static String FUNC = "xxAdvice";

    /**
     * 答辩申请
     */
    @RequestMapping("/defenceApply")
    public String defenceApply(Integer currentPage,String isReplenish, HttpServletRequest request, Model model) {
        NydbDefenceApply defence = new NydbDefenceApply();
        defence.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        defence.setIsReplenish(isReplenish);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        model.addAttribute("isReplenish",isReplenish);
        return "xjgl/defence/defenceApply";
    }

    /**
     * 新增/编辑 答辩申请界面
     */
    @RequestMapping(value="/editDefence")
    public String editAccount(String recordFlow,String auditFlag, String role, String isReplenish,Model model){
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            NydbDefenceApply defence = defenceBiz.queryDefenceByFlow(recordFlow);
            model.addAttribute("defence",defence);
            if(StringUtil.isNotBlank(role)){
                userFlow = defence.getUserFlow();
            }
        }else if("Y".equals(isReplenish)){
            NydbDefenceApply apply=new NydbDefenceApply();
            apply.setUserFlow(userFlow);
            List<NydbDefenceApply> applyList=defenceBiz.queryDefenceList(apply);
            if(applyList!=null&&applyList.size()>0){
                NydbDefenceApply tempApply=applyList.get(0);
                tempApply.setRecordFlow("");
                model.addAttribute("defence",tempApply);
            }
        }
        Map<String,Object> baseInfo = defenceBiz.queryBaseInfo(userFlow);
        model.addAttribute("baseInfo",baseInfo);
        model.addAttribute("isReplenish",isReplenish);
        if(GlobalConstant.FLAG_Y.equals(auditFlag)){
            return "xjgl/defence/auditOption";
        }
        return "xjgl/defence/editDefence";
    }

    /**
     * 答辩申请保存操作
     */
    @RequestMapping("/saveDefence")
    @ResponseBody
    public String saveDefence(NydbDefenceApply defence){
        int num = defenceBiz.saveDefence(defence);
        if (num > 0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 答辩申请删除操作
     */
    @RequestMapping("/delDefence")
    @ResponseBody
    public String delDevices(String recordFlow){
        int num = defenceBiz.delDefence(recordFlow);
        if (num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 答辩申请审核(导师、培养单位)
     */
    @RequestMapping("/paperAudit")
    public String paperAudit(String role,NydbDefenceApply defence,Integer currentPage,HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        String readOnlyFlag="";
        List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
        if(userRoles!=null&&userRoles.size()>0){
            for (SysRole sr:userRoles) {
                if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_xxcx_role_flow"))){
                    readOnlyFlag=GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("readOnlyFlag",readOnlyFlag);
        if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
            model.addAttribute("tutorFlow",sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        defence.setStatusId("Submit");
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        model.addAttribute("isReplenish",defence.getIsReplenish());
        return "xjgl/defence/defenceAudit";
    }

    /**
     * 学员答辩申请导出
     */
    @RequestMapping("/expUserExcel")
    public void expUserExcel(String isReplenish,HttpServletResponse response)throws Exception{
        NydbDefenceApply defence = new NydbDefenceApply();
        defence.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        defence.setIsReplenish(isReplenish);
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorId:专业代码",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "tutorAuditName:导师一审核",
                "twoTutorAuditName:导师二审核",
                "pydwAuditName:培养单位审核"
        };
        String fileName = "答辩申请信息.xls";
        if("Y".equals(isReplenish)){
            titles = new String[]{
                    "stuNo:学号",
                    "userName:姓名",
                    "tutorName:导师一",
                    "twoTutorName:导师二",
                    "majorId:专业代码",
                    "majorName:专业名称",
                    "trainGradationName:培养层次",
                    "trainCategoryName:培养类型",
                    "replenishTime:学位补授时间",
                    "paperChiTitle:中文论文题目",
                    "paperEngTitle:英文论文题目",
                    "researchDirection:研究方向",
                    "keyWord:关键字",
                    "tutorAuditName:导师一审核",
                    "twoTutorAuditName:导师二审核",
                    "pydwAuditName:培养单位审核"
            };
            fileName = "学位补授申请信息.xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 答辩申请导出
     */
    @RequestMapping("/expExcel")
    public void expExcel(String role,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        defence.setStatusId("Submit");
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "pydwOrgName:培养单位",
                "fwhOrgName:分委会",
                "tutorAuditName:导师一审核",
                "twoTutorAuditName:导师二审核",
                "pydwAuditName:培养单位审核"
        };
        String fileName = "答辩申请信息.xls";
        if(StringUtil.isNotBlank(defence.getIsReplenish())&&"Y".equals(defence.getIsReplenish())){
            titles = new String[]{
                    "stuNo:学号",
                    "userName:姓名",
                    "tutorName:导师一",
                    "twoTutorName:导师二",
                    "majorName:专业名称",
                    "trainGradationName:培养层次",
                    "trainCategoryName:培养类型",
                    "replenishTime:学位补授时间",
                    "paperChiTitle:中文论文题目",
                    "paperEngTitle:英文论文题目",
                    "researchDirection:研究方向",
                    "keyWord:关键字",
                    "pydwOrgName:培养单位",
                    "fwhOrgName:分委会",
                    "tutorAuditName:导师一审核",
                    "twoTutorAuditName:导师二审核",
                    "pydwAuditName:培养单位审核"
            };
            fileName = "学位补授申请信息.xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 期刊论文题目信息
     */
    @RequestMapping("/paperManage")
    public String paperManage(Integer currentPage, String isReplenish,HttpServletRequest request, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        Map<String,String> params = new HashMap<>();
        params.put("userFlow",user.getUserFlow());
        params.put("isReplenish",isReplenish);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> titleList = defenceBiz.queryPaperTitleList(params);
        model.addAttribute("userFlow",user.getUserFlow());
        model.addAttribute("titleList",titleList);
        model.addAttribute("isReplenish",isReplenish);
        return "xjgl/defence/paperManage";
    }

    /**
     * 期刊论文题目 删除操作
     */
    @RequestMapping("/delTitle")
    @ResponseBody
    public String delTitle(String recordFlow){
        int num = defenceBiz.delTitle(recordFlow);
        if (num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 编辑 期刊论文题目
     */
    @RequestMapping(value="/editTitle")
    public String editTitle(String recordFlow,String defenceFlow,String isReplenish, Model model){
        NydbDefenceApply defence = defenceBiz.queryDefenceByFlow(defenceFlow);
        if(StringUtil.isNotBlank(recordFlow)){
            NydbPaperTitle title = defenceBiz.queryTitleByFlow(recordFlow);
            model.addAttribute("title",title);
            String content = title.getPaperContent();
            XjPaperTitleExtForm extInfo = defenceBiz.parseExtInfoXml(content);
            model.addAttribute("extInfo", extInfo);
        }
        model.addAttribute("defence",defence);
        model.addAttribute("isReplenish",isReplenish);
        return "xjgl/defence/editTitle";
    }

    /**
     * 保存 论文期刊题目信息
     */
    @RequestMapping(value="/saveTitle")
    @ResponseBody
    public String saveTitle(NydbPaperTitle title,XjPaperTitleExtForm extInfo) throws Exception {
        int result = defenceBiz.saveTitle(title,extInfo);
        if(result > 0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 期刊论文题目信息(导师、培养单位、分委会、学校)
     */
    @RequestMapping("/paperList")
    public String paperList(String role,NydbDefenceApply defence, NydbPaperTitle paper,Integer currentPage, HttpServletRequest request, Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        Map<String,String> params = new HashMap<>();
        params.put("stuNo",defence.getStuNo());
        params.put("userName",defence.getUserName());
        params.put("majorId",defence.getMajorId());
        params.put("trainGradationId",defence.getTrainGradationId());
        params.put("trainCategoryId",defence.getTrainCategoryId());
        params.put("defenceTime",defence.getDefenceTime());
        params.put("researchDirection",defence.getResearchDirection());
        params.put("keyWord",defence.getKeyWord());
        params.put("tutorName",defence.getTutorName());
        params.put("pydwOrgFlow",defence.getPydwOrgFlow());
        params.put("fwhOrgFlow",defence.getFwhOrgFlow());
        params.put("journalName",paper.getJournalName());
        params.put("publishYear",paper.getPublishYear());
        params.put("volume",paper.getVolume());
        params.put("pageNumber",paper.getPageNumber());
        params.put("studyFormId",defence.getStudyFormId());
        params.put("isReplenish",defence.getIsReplenish());
        params.put("replenishTime",defence.getReplenishTime());
        if(DS.equals(role)){
            params.put("tutorFlow",user.getUserFlow());
        }else if(PYDW.equals(role)){
            params.put("pydwOrgFlow",user.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                params.put("fwhOrgFlow",user.getDeptFlow());
            }else if(StringUtil.isNotBlank(user.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(user.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> titleList = defenceBiz.queryPaperTitleList(params);
        model.addAttribute("titleList",titleList);
        model.addAttribute("isReplenish",defence.getIsReplenish());
        return "xjgl/defence/paperManage";
    }

    /**
     * 论文期刊信息导出(学员、导师、培养单位、分委会、学校)
     */
    @RequestMapping(value = "/expTitleExcel",method = {RequestMethod.GET, RequestMethod.POST})
    public void expTitleExcel(String role,NydbDefenceApply defence,NydbPaperTitle paper,HttpServletResponse response)throws Exception{
        SysUser user = GlobalContext.getCurrentUser();
        Map<String,String> params = new HashMap<>();
        params.put("stuNo",defence.getStuNo());
        params.put("userName",defence.getUserName());
        params.put("majorId",defence.getMajorId());
        params.put("trainGradationId",defence.getTrainCategoryId());
        params.put("trainCategoryId",defence.getTrainCategoryId());
        params.put("defenceTime",defence.getDefenceTime());
        params.put("researchDirection",defence.getResearchDirection());
        params.put("keyWord",defence.getKeyWord());
        params.put("tutorName",defence.getTutorName());
        params.put("pydwOrgFlow",defence.getPydwOrgFlow());
        params.put("fwhOrgFlow",defence.getFwhOrgFlow());
        params.put("userFlow",defence.getUserFlow());
        params.put("journalName",paper.getJournalName());
        params.put("publishYear",paper.getPublishYear());
        params.put("volume",paper.getVolume());
        params.put("pageNumber",paper.getPageNumber());
        params.put("isReplenish",defence.getIsReplenish());
        if(DS.equals(role)){
            params.put("tutorFlow",user.getUserFlow());
        }else if(PYDW.equals(role)){
            params.put("pydwOrgFlow",user.getOrgFlow());
        }else if(FWH.equals(role)){
            params.put("fwhOrgFlow",user.getDeptFlow());
        }
        List<Map<String,Object>> titleList = defenceBiz.queryPaperTitleList(params);
        if(null != titleList && !titleList.isEmpty()){
            for(Map<String,Object> map : titleList){
                if(null != map.get("paperContent")){
                    String content = ClobToString((Clob)map.get("paperContent"));
                    XjPaperTitleExtForm extInfo = defenceBiz.parseExtInfoXml(content);
                    //有无共同一作
                    if(StringUtil.isNotBlank(extInfo.getIsCoAuthor())){
                        extInfo.setIsCoAuthor(GlobalConstant.FLAG_Y.equals(extInfo.getIsCoAuthor())?"有":"无");
                    }
                    //有无共同通讯作者
                    if(StringUtil.isNotBlank(extInfo.getIsCoMessAuthor())){
                        extInfo.setIsCoMessAuthor(GlobalConstant.FLAG_Y.equals(extInfo.getIsCoMessAuthor())?"有":"无");
                    }
                    //导师是否通讯作者
                    if(StringUtil.isNotBlank(extInfo.getIsMessAuthor())){
                        extInfo.setIsMessAuthor(GlobalConstant.FLAG_Y.equals(extInfo.getIsMessAuthor())?"是":"否");
                    }
                    //刊物类型
                    if("sci".equals(extInfo.getJournalType())){
                        extInfo.setJournalType("SCI");
                    }else if("ei".equals(extInfo.getJournalType())){
                        extInfo.setJournalType("EI");
                    }else if("zwhx".equals(extInfo.getJournalType())){
                        extInfo.setJournalType("中文核心");
                    }else if("zwtjy".equals(extInfo.getJournalType())){
                        extInfo.setJournalType("中文统计源");
                    }else if("qt".equals(extInfo.getJournalType())){
                        extInfo.setJournalType("其他");
                    }
                    //SCI大类分区
                    if("1".equals(extInfo.getSciBigArea())){
                        extInfo.setSciBigArea("一区");
                    }else if("2".equals(extInfo.getSciBigArea())){
                        extInfo.setSciBigArea("二区");
                    }else if("3".equals(extInfo.getSciBigArea())){
                        extInfo.setSciBigArea("三区");
                    }else if("4".equals(extInfo.getSciBigArea())){
                        extInfo.setSciBigArea("四区");
                    }
                    //SCI小类分区
                    if("1".equals(extInfo.getSciSmalArea())){
                        extInfo.setSciSmalArea("一区");
                    }else if("2".equals(extInfo.getSciSmalArea())){
                        extInfo.setSciSmalArea("二区");
                    }else if("3".equals(extInfo.getSciSmalArea())){
                        extInfo.setSciSmalArea("三区");
                    }else if("4".equals(extInfo.getSciSmalArea())){
                        extInfo.setSciSmalArea("四区");
                    }

                    //文章类型
                    if("lz".equals(extInfo.getArticleType())){
                        extInfo.setArticleType("论著");
                    }else if("zs".equals(extInfo.getArticleType())){
                        extInfo.setArticleType("综述");
                    }else if("blbd".equals(extInfo.getArticleType())){
                        extInfo.setArticleType("病例报道");
                    }else if("meta".equals(extInfo.getArticleType())){
                        extInfo.setArticleType("META分析");
                    }else if("qt".equals(extInfo.getArticleType())){
                        extInfo.setArticleType("其他");
                    }
                    //文章状态
                    if("fb".equals(extInfo.getArticleStatus())){
                        extInfo.setArticleStatus("发表");
                    }else if("ly".equals(extInfo.getArticleStatus())){
                        extInfo.setArticleStatus("录用");
                    }else if("qt".equals(extInfo.getArticleStatus())){
                        extInfo.setArticleStatus("其他");
                    }
                    List<XjAuthorInfoForm> coAuthorList = extInfo.getCoAuthorList();
                    List<XjAuthorInfoForm> messAuthorList = extInfo.getMessAuthorList();
                    List<XjAuthorOrgForm> ownerOrgList = extInfo.getOwnerOrgList();
                    if(null != coAuthorList && !coAuthorList.isEmpty()){
                        String firstCoAuthorOrg = "";
                        for(int i=0;i<coAuthorList.size();i++){
                            List<XjAuthorOrgForm> orgList = coAuthorList.get(i).getCoAuthorOrgList();
                            if(orgList!=null&&orgList.size()>0){
                                for(int j=0;j<orgList.size();j++){
                                    firstCoAuthorOrg += orgList.get(j).getCoAuthorOrg();
                                    if(j<orgList.size()-1){
                                        firstCoAuthorOrg += "，";
                                    }else if(i<coAuthorList.size()-1){
                                        firstCoAuthorOrg += "；\n";
                                    }
                                }
                            }
                        }
                        map.put("firstCoAuthorOrg",firstCoAuthorOrg);
                    }
                    if(null != messAuthorList && !messAuthorList.isEmpty()){
                        String messCoAuthorOrg = "";
                        for(int i=0;i<messAuthorList.size();i++){
                            List<XjAuthorOrgForm> orgList = messAuthorList.get(i).getMessAuthorOrgList();
                            if(orgList!=null&&orgList.size()>0){
                                for(int j=0;j<orgList.size();j++){
                                    messCoAuthorOrg += orgList.get(j).getMessAuthorOrg();
                                    if(j<orgList.size()-1){
                                        messCoAuthorOrg += "，";
                                    }else if(i<messAuthorList.size()-1){
                                        messCoAuthorOrg += "；\n";
                                    }
                                }
                            }
                        }
                        map.put("messCoAuthorOrg",messCoAuthorOrg);
                    }
                    if(null != ownerOrgList && !ownerOrgList.isEmpty()){
                        String ownerOrg = "";
                        for(int i=0;i<ownerOrgList.size();i++){
                            ownerOrg += ownerOrgList.get(i).getOwnerOrg();
                            if(i<ownerOrgList.size()-1){
                                ownerOrg += "\n";
                            }
                        }
                        map.put("ownerOrg",ownerOrg);
                    }
                    beanToMap(map,extInfo);
                }
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "sexName:性别",
                "majorName:专业",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "tutorName:导师一",
                "tutorPhone:移动电话",
                "twoTutorName:导师二",
                "twoTutorPhone:移动电话",
                "fwhOrgName:分委会",
                "pydwOrgName:培养单位",
                "defenceTime:论文答辩年月",
                "paperChiTitle:论文中文题目",
                "paperEngTitle:论文英文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "paperTitle:发表期刊论文题目",
                "authorChiName:全部作者中文名单",
                "authorEngName:全部作者英文名单",
                "ownerRank:本人在全部作者中的排名",
                "isCoAuthor:有无共同一作",
                "firstCoAuthor:共同第一作者中文名单",
                "firstCoAuthorOrg:共同第一作者所在单位",
                "ownerCoRank:本人在共同一作中的排名",
                "firstAuthor:第一作者中文姓名",
                "authorOrg:所在单位",
                "ownerOrg:本人作者单位",
                "isCoMessAuthor:有无共同通讯者",
                "messCoAuthor:共同通讯作者中文名单",
                "messCoAuthorOrg:共同通讯作者所在单位",
                "messAuthor:通讯作者姓名",
                "messAuthorOrg:通讯作者单位",
                "isMessAuthor:导师是否通讯作者",
                "journalName:发表论文刊物名称",
                "publishYear:发表年份",
                "volume:卷期",
                "pageNumber:页面范围",
                "journalType:刊物类型",
                "sciBigArea:SCI大类分区",
                "sciSmalArea:SCI小类分区",
                "scifactor:SCI影响因子",
                "articleType:文章类型",
                "articleStatus:文章发表状态",
                "memo:备注"
        };
        if(StringUtil.isNotBlank(defence.getIsReplenish())&&"Y".equals(defence.getIsReplenish())){
            titles = new String[]{
                    "stuNo:学号",
                    "userName:姓名",
                    "sexName:性别",
                    "majorName:专业",
                    "trainGradationName:培养层次",
                    "trainCategoryName:培养类型",
                    "tutorName:导师一",
                    "tutorPhone:移动电话",
                    "twoTutorName:导师二",
                    "twoTutorPhone:移动电话",
                    "fwhOrgName:分委会",
                    "pydwOrgName:培养单位",
                    "replenishTime:学位补授时间",
                    "paperChiTitle:论文中文题目",
                    "paperEngTitle:论文英文题目",
                    "researchDirection:研究方向",
                    "keyWord:关键字",
                    "paperTitle:发表期刊论文题目",
                    "authorChiName:全部作者中文名单",
                    "authorEngName:全部作者英文名单",
                    "ownerRank:本人在全部作者中的排名",
                    "isCoAuthor:有无共同一作",
                    "firstCoAuthor:共同第一作者中文名单",
                    "firstCoAuthorOrg:共同第一作者所在单位",
                    "ownerCoRank:本人在共同一作中的排名",
                    "firstAuthor:第一作者中文姓名",
                    "authorOrg:所在单位",
                    "ownerOrg:本人作者单位",
                    "isCoMessAuthor:有无共同通讯者",
                    "messCoAuthor:共同通讯作者中文名单",
                    "messCoAuthorOrg:共同通讯作者所在单位",
                    "messAuthor:通讯作者姓名",
                    "messAuthorOrg:通讯作者单位",
                    "isMessAuthor:导师是否通讯作者",
                    "journalName:发表论文刊物名称",
                    "publishYear:发表年份",
                    "volume:卷期",
                    "pageNumber:页面范围",
                    "journalType:刊物类型",
                    "sciBigArea:SCI大类分区",
                    "sciSmalArea:SCI小类分区",
                    "scifactor:SCI影响因子",
                    "articleType:文章类型",
                    "articleStatus:文章发表状态",
                    "memo:备注"
            };
        }
        String fileName = "论文期刊信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, titleList,response.getOutputStream());
    }
    private String ClobToString(Clob clob) throws Exception {
        // 得到流
        java.io.Reader is = clob.getCharacterStream();
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
        while (s != null) {
            sb.append(s);
            s = br.readLine();
        }
        return sb.toString();
    }
    private void beanToMap(Map<String, Object> params,Object obj) throws Exception{
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
        for (int i = 0; i < descriptors.length; i++) {
            String name = descriptors[i].getName();
            if (!"class".equals(name)) {
                params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
            }
        }
    }

    /**
     * 答辩资格
     */
    @RequestMapping("/qualifyApply")
    public String qualifyApply(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        String readOnlyFlag="";
        List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
        if(userRoles!=null&&userRoles.size()>0){
            for (SysRole sr:userRoles) {
                if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_xxcx_role_flow"))){
                    readOnlyFlag=GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("readOnlyFlag",readOnlyFlag);
        defence.setPydwAuditId("Passed");
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/qualifyApply";
    }

    /**
     * 答辩资格修改操作
     */
    @RequestMapping("/updateQualify")
    @ResponseBody
    public String updateQualify(NydbDefenceApply defence){
        if(StringUtil.isNotBlank(defence.getFirstAuditFlag())|| StringUtil.isNotBlank(defence.getLastAuditFlag())){
            String auditResultFlag = GlobalConstant.FLAG_Y.equals(defence.getFirstAuditFlag()) || GlobalConstant.FLAG_Y.equals(defence.getLastAuditFlag())?GlobalConstant.FLAG_Y:GlobalConstant.FLAG_N;
            defence.setAuditResultFlag(auditResultFlag);
        }
        int num = defenceBiz.saveDefence(defence);
        if (num > 0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 答辩资格(导出操作)
     */
    @RequestMapping("/expQualify")
    public void expQualify(String role,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setPydwAuditId("Passed");
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        if(null != recordList && !recordList.isEmpty()){
            for(NydbDefenceApply apply : recordList){
                if(StringUtil.isNotBlank(apply.getPaperSameFlag())){
                    apply.setPaperSameFlag(GlobalConstant.FLAG_Y.equals(apply.getPaperSameFlag())?"是":"否");
                }
                if(StringUtil.isNotBlank(apply.getThinkSameFlag())){
                    apply.setThinkSameFlag(GlobalConstant.FLAG_Y.equals(apply.getThinkSameFlag())?"是":"否");
                }
                if(StringUtil.isNotBlank(apply.getPreDefenceFlag())){
                    apply.setPreDefenceFlag(GlobalConstant.FLAG_Y.equals(apply.getPreDefenceFlag())?"合格":"不合格");
                }
                if(StringUtil.isNotBlank(apply.getPaperReadFlag())){
                    apply.setPaperReadFlag(GlobalConstant.FLAG_Y.equals(apply.getPaperReadFlag())?"合格":"不合格");
                }
                if(StringUtil.isNotBlank(apply.getFirstAuditFlag())){
                    apply.setFirstAuditFlag(GlobalConstant.FLAG_Y.equals(apply.getFirstAuditFlag())?"通过":"不通过");
                }
                if(StringUtil.isNotBlank(apply.getLastAuditFlag())){
                    apply.setLastAuditFlag(GlobalConstant.FLAG_Y.equals(apply.getLastAuditFlag())?"通过":"不通过");
                }
                if(StringUtil.isNotBlank(apply.getAuditResultFlag())){
                    apply.setAuditResultFlag(GlobalConstant.FLAG_Y.equals(apply.getAuditResultFlag())?"通过":"不通过");
                }
                if(StringUtil.isNotBlank(apply.getOtherFlag())){
                    apply.setOtherFlag(GlobalConstant.FLAG_Y.equals(apply.getOtherFlag())?"是":"否");
                }
                if(StringUtil.isNotBlank(apply.getFormalDefenceFlag())){
                    apply.setFormalDefenceFlag(GlobalConstant.FLAG_Y.equals(apply.getFormalDefenceFlag())?"是":"否");
                }
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "sexName:性别",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "pydwOrgName:培养单位",
                "fwhOrgName:分委会",
                "paperSameFlag:是否进行学位论文相似性检测",
                "thinkSameFlag:导师、学科是否认为学位论文相似性检测结果合格",
                "preDefenceFlag:预答辩学位论文结果",
                "paperReadFlag:论文评阅结果",
                "firstAuditFlag:初审",
                "lastAuditFlag:复审",
                "auditResultFlag:盲审结果",
                "otherFlag:其他",
                "formalDefenceFlag:是否有正式答辩资格",
        };
        String fileName = "答辩资格信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 答辩日程
     */
    @RequestMapping("/defenceSchedule")
    public String defenceSchedule(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/defenceSchedule";
    }

    /**
     * 新增/编辑 答辩日程界面
     */
    @RequestMapping(value="/editSchedule")
    public String editSchedule(String recordFlow, Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            NydbDefenceApply defence = defenceBiz.queryDefenceByFlow(recordFlow);
            model.addAttribute("defence",defence);
            List<NydbCommitteeMember> memberList = defenceBiz.queryMemberList(recordFlow);
            model.addAttribute("memberList",memberList);
        }
        return "xjgl/defence/editSchedule";
    }

    /**
     * 答辩日程信息保存提交操作
     */
    @RequestMapping("/saveSchedule")
    @ResponseBody
    public String saveSchedule(NydbDefenceApply defence,@RequestBody List<NydbCommitteeMember> memberList) throws Exception{
        defence.setDefencePlace(java.net.URLDecoder.decode(defence.getDefencePlace(),"UTF-8"));
        defence.setChairmanName(java.net.URLDecoder.decode(defence.getChairmanName(),"UTF-8"));
        defence.setChairmanPost(java.net.URLDecoder.decode(defence.getChairmanPost(),"UTF-8"));
        defence.setChairmanOrg(java.net.URLDecoder.decode(defence.getChairmanOrg(),"UTF-8"));
        int num = defenceBiz.saveSchedule(defence,memberList);
        if (num > 0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 答辩日程(导出操作)
     */
    @RequestMapping("/expSchedule")
    public void expSchedule(String role,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        if(null != recordList && !recordList.isEmpty()){
            for(NydbDefenceApply apply : recordList){
                String beginTime = StringUtil.isBlank(apply.getDefenceBeginTime())?"":apply.getDefenceBeginTime();
                String endTime = StringUtil.isBlank(apply.getDefenceEndTime())?"":apply.getDefenceEndTime();
                apply.setDefenceBeginTime(beginTime+"~"+endTime);
                String memberName="";
                String memberPost="";
                String memberOrg="";
                List<NydbCommitteeMember> memberList = defenceBiz.queryMemberList(apply.getRecordFlow());
                for(int i=0;i<memberList.size();i++){
                    NydbCommitteeMember mem = memberList.get(i);
                    memberName+=mem.getMemberName();
                    memberPost+=mem.getMemberPost();
                    memberOrg+=mem.getMemberOrg();
                    if(i<memberList.size()-1){
                        memberName+="\n";
                        memberPost+="\n";
                        memberOrg+="\n";
                    }
                }
                apply.setCreateUserFlow(memberName);
                apply.setCreateTime(memberPost);
                apply.setModifyTime(memberOrg);
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorId:专业代码",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "pydwOrgName:培养单位",
                "fwhOrgName:分委会",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "defenceBeginTime:答辩起止时间",
                "defencePlace:答辩地点",
                "chairmanName:委员会主席姓名",
                "chairmanPost:职称",
                "chairmanOrg:单位",
                "createUserFlow:委员姓名",
                "createTime:职称",
                "modifyTime:单位"
        };
        String fileName = "答辩日程信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 答辩结果
     */
    @RequestMapping("/defenceResult")
    public String defenceResult(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/defenceResult";
    }

    /**
     * 答辩结果(导出操作)
     */
    @RequestMapping("/expResult")
    public void expResult(String role,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        if(null != recordList && !recordList.isEmpty()){
            for(NydbDefenceApply apply : recordList){
                if(StringUtil.isNotBlank(apply.getDefenceResultFlag())){
                    apply.setDefenceResultFlag(GlobalConstant.FLAG_Y.equals(apply.getDefenceResultFlag())?"通过":"不通过");
                }
                String result = "优秀("+apply.getExcellentResult()+") 良好("+apply.getGoodResult()+") 及格("+apply.getPassResult()+") 不及格（"+apply.getFailResult()+"）";
                apply.setExcellentResult(result.replace("null",""));
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorId:专业代码",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "pydwOrgName:培养单位",
                "fwhOrgName:分委会",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "defenceResultFlag:答辩结果",
                "excellentResult:答辩结果详情",
        };
        String fileName = "答辩结果信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 分委会意见
     */
    @RequestMapping("/fwhAdvice")
    public String fwhAdvice(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        String readOnlyFlag="";
        List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
        if(userRoles!=null&&userRoles.size()>0){
            for (SysRole sr:userRoles) {
                if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_xxcx_role_flow"))){
                    readOnlyFlag=GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("readOnlyFlag",readOnlyFlag);
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/fwhAdvice";
    }

    /**
     * 分委会意见
     */
    @RequestMapping("/schoolAdvice")
    public String schoolAdvice(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        String readOnlyFlag="";
        List<SysRole> userRoles=roleBiz.search(sysUser.getUserFlow(),GlobalConstant.CMIS_WS_ID);
        if(userRoles!=null&&userRoles.size()>0){
            for (SysRole sr:userRoles) {
                if(sr!=null&&sr.getRoleFlow().equals(InitConfig.getSysCfg("xjgl_xxcx_role_flow"))){
                    readOnlyFlag=GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("readOnlyFlag",readOnlyFlag);
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            List<SysOrg> orgList = orgBiz.searchTrainOrgList();
            model.addAttribute("orgList", orgList);
            if(FWH.equals(role)){
                defence.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/schoolAdvice";
    }

    /**
     * 答辩分委会/学校意见(导出操作)
     */
    @RequestMapping("/expAdvice")
    public void expAdvice(String role,String funcFlag,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(XS.equals(role)){
            defence.setUserFlow(sysUser.getUserFlow());
        }else if(DS.equals(role)){
            //导师一
            defence.setTutorFlow(sysUser.getUserFlow());
            //导师二
            defence.setTwoTutorFlow(sysUser.getUserFlow());
        }else if(PYDW.equals(role)) {
            defence.setPydwOrgFlow(sysUser.getOrgFlow());
        }else if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        if(null != recordList && !recordList.isEmpty()){
            for(NydbDefenceApply apply : recordList){
                if(StringUtil.isNotBlank(apply.getDefenceResultFlag())){
                    apply.setDefenceResultFlag(GlobalConstant.FLAG_Y.equals(apply.getDefenceResultFlag())?"通过":"不通过");
                }
                String result = "优秀("+apply.getExcellentResult()+") 良好("+apply.getGoodResult()+") 及格("+apply.getPassResult()+") 不及格（"+apply.getFailResult()+"）";
                apply.setExcellentResult(result.replace("null",""));
                if(StringUtil.isNotBlank(apply.getXwfwhAdviceFlag())){
                    apply.setXwfwhAdviceFlag(GlobalConstant.FLAG_Y.equals(apply.getXwfwhAdviceFlag())?"建议授予":"建议缓授");
                }
                if(StringUtil.isNotBlank(apply.getXxwhAdviceFlag())){
                    apply.setXxwhAdviceFlag(GlobalConstant.FLAG_Y.equals(apply.getXxwhAdviceFlag())?"建议授予":"建议缓授");
                }
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "tutorName:导师一",
                "twoTutorName:导师二",
                "majorId:专业代码",
                "majorName:专业名称",
                "trainGradationName:培养层次",
                "trainCategoryName:培养类型",
                "pydwOrgName:培养单位",
                "fwhOrgName:分委会",
                "defenceTime:答辩时间",
                "paperChiTitle:中文论文题目",
                "paperEngTitle:英文论文题目",
                "researchDirection:研究方向",
                "keyWord:关键字",
                "defenceResultFlag:答辩结果",
                "excellentResult:答辩结果详情",
                "xwfwhAdviceFlag:学位分委会意见"
        };
        String fileName = "分委会意见.xls";
        if(FUNC.equals(funcFlag)){
            fileName = "校学位会意见.xls";
            titles = new String[]{
                    "stuNo:学号",
                    "userName:姓名",
                    "tutorName:导师一",
                    "twoTutorName:导师二",
                    "majorId:专业代码",
                    "majorName:专业名称",
                    "trainGradationName:培养层次",
                    "trainCategoryName:培养类型",
                    "pydwOrgName:培养单位",
                    "fwhOrgName:分委会",
                    "defenceTime:答辩时间",
                    "paperChiTitle:中文论文题目",
                    "paperEngTitle:英文论文题目",
                    "researchDirection:研究方向",
                    "keyWord:关键字",
                    "defenceResultFlag:答辩结果",
                    "excellentResult:答辩结果详情",
                    "xwfwhAdviceFlag:学位分委会意见",
                    "xxwhAdviceFlag:校学位会意见"
            };
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }

    /**
     * 导出上会数据
     */
    @RequestMapping("/exportDate")
    public String exportDate(String role, NydbDefenceApply defence, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }else if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
            model.addAttribute("deptList",deptList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydbDefenceApply> defenceList = defenceBiz.queryDefenceList(defence);
        model.addAttribute("defenceList",defenceList);
        return "xjgl/defence/exportDate";
    }

    /**
     * 导出上会数据(导出操作)
     */
    @RequestMapping("/expUpDate")
    public void expUpDate(String role,NydbDefenceApply defence,HttpServletResponse response)throws Exception{
        SysUser sysUser = GlobalContext.getCurrentUser();
        defence.setFormalDefenceFlag(GlobalConstant.FLAG_Y);
        if(FWH.equals(role)){
            defence.setFwhOrgFlow(sysUser.getDeptFlow());
        }
        List<NydbDefenceApply> recordList = defenceBiz.queryDefenceList(defence);
        if(null != recordList && !recordList.isEmpty()){
            for(NydbDefenceApply apply : recordList){
                //用于导出空字段信息
                apply.setRecordFlow("");
                if(StringUtil.isNotBlank(apply.getAuditResultFlag())){
                    apply.setAuditResultFlag(GlobalConstant.FLAG_Y.equals(apply.getAuditResultFlag())?"通过":"不通过");
                }
                if(StringUtil.isNotBlank(apply.getDefenceResultFlag())){
                    apply.setDefenceResultFlag(GlobalConstant.FLAG_Y.equals(apply.getDefenceResultFlag())?"通过":"不通过");
                }
            }
        }
        String[] titles = new String[]{
                "stuNo:学号",
                "userName:姓名",
                "sexName:性别",
                "defenceTime:答辩时间",
                "majorName:专业",
                "tutorName:导师一",
                "tutorPhone:联系电话",
                "twoTutorName:导师二",
                "twoTutorPhone:联系电话",
                "paperChiTitle:学位论文题目",
                "recordFlow:发表文章刊物名称及作者",
                "recordFlow:发表年月，卷期，页面",
                "recordFlow:刊物类型",
                "recordFlow:SCI影响因子及分区",
                "auditResultFlag:盲审",
                "defenceResultFlag:答辩结果",
                "recordFlow:分委会意见",
                "recordFlow:校学位办意见",
                "recordFlow:评审意见",
                "recordFlow:备注",
                "fwhOrgName:所属学位分委会",
                "pydwOrgName:培养单位"
        };
        String fileName = "导出上会数据.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, recordList,response.getOutputStream());
    }
    @RequestMapping(value="/leadTo")
    public String leadTo(String isReplenish){
        return "xjgl/defence/importDefence";
    }
    /**
     * 补授申请信息导入
     */
    @RequestMapping(value="/importDefenceFromExcel")
    @ResponseBody
    public String importDefenceFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = defenceBiz.importDefenceFromExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
}
