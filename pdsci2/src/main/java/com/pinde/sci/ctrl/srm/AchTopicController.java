package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAchTopicBiz;
import com.pinde.sci.biz.srm.ISrmAchFileBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AchTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 科研成果（课题）
 */
@Controller
@RequestMapping("/srm/ach/topic")
public class AchTopicController extends GeneralController {
    @Autowired
    private IAchTopicBiz topicBiz;
    @Autowired
    private ISrmAchFileBiz srmAchFileBiz;
    @Autowired
    private ISrmAchProcessBiz srmAchProcessBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;

    /**
     * 保存课题
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/save"})
    @ResponseBody
    public String save(SrmAchTopic topic, String fileFlow, MultipartFile file, HttpServletRequest request) throws IOException {
        SysUser currUser = GlobalContext.getCurrentUser();
        //SrmAchTopic topic = new SrmAchTopic();
        topic.setOperStatusId(AchStatusEnum.Apply.getId());
        topic.setOperStatusName(AchStatusEnum.Apply.getName());
        topic.setApplyOrgFlow(currUser.getOrgFlow());
        topic.setApplyOrgName(currUser.getOrgName());
        topic.setTypeName(DictTypeEnum.getNameById(topic.getTypeId()));
        SrmAchFile srmAchFile = null;
        if (file != null && StringUtil.isNotBlank(file.getOriginalFilename())) {
            //封装附件对象
            srmAchFile = new SrmAchFile();
            byte[] bytes = new byte[(int) file.getSize()];
            file.getInputStream().read(bytes);
            if (StringUtil.isNotBlank(fileFlow)) {
                srmAchFile.setFileFlow(fileFlow);
            }
            srmAchFile.setFileContent(bytes);
            srmAchFile.setFileName(file.getOriginalFilename());

            srmAchFile.setTypeId(AchTypeEnum.Topic.getId());
            srmAchFile.setTypeName(AchTypeEnum.Topic.getName());

            String[] nameArray = file.getOriginalFilename().split("\\.");
            srmAchFile.setFileSuffix(nameArray[nameArray.length - 1]);
            srmAchFile.setFileType(nameArray[nameArray.length - 1]);
        }
        //封装成果过程对象
        SrmAchProcess srmAchProcess = new SrmAchProcess();

        srmAchProcess.setTypeId(AchTypeEnum.Topic.getId());
        srmAchProcess.setTypeName(AchTypeEnum.Topic.getName());

        srmAchProcess.setOperateUserFlow(currUser.getUserFlow());
        srmAchProcess.setOperateUserName(currUser.getUserName());

        srmAchProcess.setOperStatusId(AchStatusEnum.Apply.getId());
        srmAchProcess.setOperStatusName(AchStatusEnum.Apply.getName());

        int reslut = topicBiz.save(topic, srmAchFile, srmAchProcess);
        if (reslut == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value = "topicFlow", required = true) String topicFlow, Model model) {
        if (StringUtil.isNotBlank(topicFlow)) {
            SrmAchTopic topic = topicBiz.readTopic(topicFlow);
            topic.setOperStatusId(AchStatusEnum.Submit.getId());
            topic.setOperStatusName(AchStatusEnum.Submit.getName());

            SrmAchProcess process = srmAchProcessBiz.searchAchProcess(topicFlow, AchStatusEnum.Apply.getId()).get(0);
            process.setProcessFlow(PkUtil.getUUID());
            process.setOperStatusId(AchStatusEnum.Submit.getId());
            process.setOperStatusName(AchStatusEnum.Submit.getName());
            GeneralMethod.setRecordInfo(process, true);
            process.setOperateTime(process.getCreateTime());

            SysUser currUser = GlobalContext.getCurrentUser();
            process.setOperateUserFlow(currUser.getUserFlow());
            process.setOperateUserName(currUser.getUserName());

            int result = topicBiz.updateTopicStatus(topic, process);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.OPRE_SUCCESSED_FLAG;
            }
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }


    /**
     * 加载课题列表
     *
     * @param topic
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(SrmAchTopic topic, Integer currentPage, HttpServletRequest request, Model model) {

        SysUser currUser = GlobalContext.getCurrentUser();
        topic.setApplyUserFlow(currUser.getUserFlow());

        PageHelper.startPage(currentPage, getPageSize(request));

        List<SrmAchTopic> topicList = topicBiz.search(topic);


        model.addAttribute("topicList", topicList);
        return "srm/ach/topic/list";
    }

    /**
     * 编辑课题信息
     *
     * @param topicFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(String topicFlow, Model model) {
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        if (StringUtil.isNotBlank(topicFlow)) {
            SrmAchTopic topic = topicBiz.readTopic(topicFlow);
            model.addAttribute("topic", topic);
            fileList = srmAchFileBiz.searchSrmAchFile(topicFlow);
            if (null != fileList && !fileList.isEmpty()) {
                file = fileList.get(0);
            }
        }
        model.addAttribute("file", file);
        return "srm/ach/topic/edit";
    }


    @RequestMapping(value = "/auditList/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String scope, String currentPage, SrmAchTopic topic, SysOrg org, Model model, HttpServletRequest request) {
        SysUser currUser = GlobalContext.getCurrentUser();
        List<SrmAchTopic> topicList = null;
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            topic.setApplyOrgFlow(currUser.getOrgFlow());
            //审核状态： 审核通过、待审核、全部
            if (StringUtil.isBlank(topic.getOperStatusId())) {
                topic.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(topic.getOperStatusId())) {
                topic.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
            topicList = topicBiz.search(topic);
        }
        model.addAttribute("topicList", topicList);
        return "srm/ach/topic/auditList" + scope;
    }

    @RequestMapping("/searchDept")
    @ResponseBody
    public List<SysDept> searchDept(SysDept dept) {
        List<SysDept> deptList = new ArrayList<>();
        if (StringUtil.isBlank(dept.getOrgFlow())) {
            dept = new SysDept();
            dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if (StringUtil.isNotBlank(dept.getOrgFlow())) {
            deptList = deptBiz.searchDept(dept);
        }
        return deptList;
    }

    /**
     * 跳转到审核界面
     *
     * @param TopicFlow
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    public String audit(@RequestParam(value = "topicFlow", required = true) String topicFlow, Model model) {
        //根据成果流水号查询相关信息，用于加载审核页面
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        if (StringUtil.isNotBlank(topicFlow)) {
            //查询成果本身
            SrmAchTopic topic = topicBiz.readTopic(topicFlow);
            model.addAttribute("topic", topic);
            //查询成果附件
            fileList = srmAchFileBiz.searchSrmAchFile(topicFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                model.addAttribute("file", file);
            }
        }
        SrmAchTopic topic = topicBiz.readTopic(topicFlow);
        model.addAttribute("topic", topic);
        return "srm/ach/topic/audit";
    }

    /**
     * 保存审核结果
     *
     * @param agreeFlag
     * @param auditContent
     * @param TopicFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@RequestParam(value = "agreeFlag", required = true) String agreeFlag, String auditContent,
                            @RequestParam(value = "topicFlow", required = true) String topicFlow, Model model) {
        SrmAchTopic topic = topicBiz.readTopic(topicFlow);
        SrmAchProcess process = srmAchProcessBiz.searchAchProcess(topicFlow, AchStatusEnum.Apply.getId()).get(0);

        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            topic.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            topic.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
        } else {
            topic.setOperStatusId(AchStatusEnum.RollBack.getId());
            topic.setOperStatusName(AchStatusEnum.RollBack.getName());
            process.setOperStatusId(AchStatusEnum.RollBack.getId());
            process.setOperStatusName(AchStatusEnum.RollBack.getName());
        }
        process.setProcessFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperateTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperateUserFlow(currUser.getUserFlow());
        process.setOperateUserName(currUser.getUserName());
        process.setContent(auditContent);
        topicBiz.updateTopicStatus(topic, process);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 删除课题信息
     *
     * @param achTopic
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String topicFlow) {
        if (StringUtil.isNotBlank(topicFlow)) {
            SrmAchTopic topic = new SrmAchTopic();
            topic.setTopicFlow(topicFlow);
            topic.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            SrmAchFile file = null;
            List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(topicFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }

            int result = topicBiz.edit(topic, file);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 成果汇总
     * @param model
     */
    @RequestMapping("/collectHarvest")
    public void collectHarvest(Model model) {

    }

    /**
     * 导出课题信息
     * @param scope
     * @param topic
     * @param author
     * @param org
     * @param response
     * @throws Exception
     */
   /* @RequestMapping(value = "/exportTopicExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportTopicExcel(@PathVariable String scope,SrmAchTopic topic, SrmAchTopicAuthor author, SysOrg org, HttpServletResponse response) throws Exception {
        String[] titles;//导出列表头信息
        SysUser currUser = GlobalContext.getCurrentUser();
        List<AchTopicExportForm> searchList=new ArrayList<AchTopicExportForm>();
        List<SrmAchTopic> achTopicList = null;
        List<SrmAchTopic> TopicList=null;
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String,List<SysOrg>> resultMap=orgBiz.searchChargeAndApply(org,scope);
        //获取当前机构下属一级的机构
        List<SysOrg> firstGradeOrgList=(List<SysOrg>) resultMap.get("firstGradeOrgList");
        if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
            //设置查询条件：课题处审核通过的成果
            topic.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
            List<SysOrg> secondGradeOrgList=(List<SysOrg>) resultMap.get("secondGradeOrgList");
            titles = new String[]{
                    "srmAchTopic.TopicName:课题名称",
                    "authorName:课题作者",
                    "srmAchTopic.prizedGradeName:获奖级别",
                    "srmAchTopic.prizedLevelName:获奖等级",
                    "srmAchTopic.prizedDate:获奖日期",
                    "srmAchTopic.applyOrgName:申报单位"
            };
            //如果查询条件orgFlow为空且chargeOrgFlow不为空，则查询该主管部门下所有子机构的所有成果
            if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())){
                if(null == secondGradeOrgList || secondGradeOrgList.isEmpty()){
                    SysOrg sysOrg=orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList=new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    achTopicList = topicBiz.search(topic,selfOrgList);
                }else{
                    achTopicList = topicBiz.search(topic,secondGradeOrgList);
                }
            }

        }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
            titles = new String[]{
                    "srmAchTopic.TopicName:课题名称",
                    "authorName:课题作者",
                    "srmAchTopic.prizedGradeName:获奖级别",
                    "srmAchTopic.prizedLevelName:获奖等级",
                    "srmAchTopic.prizedDate:获奖日期"
            };
            topic.setOperStatusId(AchStatusEnum.FirstAudit.getId());
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)){
            titles = new String[]{
                    "srmAchTopic.TopicName:课题名称",
                    "authorName:课题作者",
                    "srmAchTopic.prizedGradeName:获奖级别",
                    "srmAchTopic.prizedLevelName:获奖等级",
                    "srmAchTopic.prizedDate:获奖日期",
                    "srmAchTopic.operStatusName:审核状态"
            };
            //审核状态： 审核通过、待审核、全部
            if(StringUtil.isBlank(topic.getOperStatusId())){
                topic.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if(GlobalConstant.FLAG_Y.equals(topic.getOperStatusId())){
                topic.setOperStatusId(AchStatusEnum.Submit.getId()+","+AchStatusEnum.FirstAudit.getId());
            }
        }else{
            titles = new String[]{
                    "srmAchTopic.TopicName:奖项名称",
                    "authorName:作者",
                    "srmAchTopic.prizedGradeName:奖项级别",
                    "srmAchTopic.prizedLevelName:奖项等级",
                    "srmAchTopic.prizedDate:获奖日期",
                    "srmAchTopic.operStatusName:审核状态"
            };
            topic.setApplyUserFlow(currUser.getUserFlow());
            achTopicList = topicBiz.search(topic, null);
        }
        //如果查询条件orgFlow不为空，则查询该org下所有成果
        if(StringUtil.isNotBlank(org.getOrgFlow())){
            topic.setApplyOrgFlow(org.getOrgFlow());
            achTopicList = topicBiz.search(topic, null);
        }
        //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
        if(StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())){
            achTopicList = topicBiz.search(topic, currOrgChildList);
        }

        Map<String, List<SrmAchTopicAuthor>> allAuthorMap = new HashMap<String, List<SrmAchTopicAuthor>>();
        List<SrmAchTopicAuthor> TopicAuthorList = TopicAuthorBiz.searchAuthorList(new SrmAchTopicAuthor());
        if(TopicAuthorList != null && !TopicAuthorList.isEmpty()){
            for(SrmAchTopicAuthor a : TopicAuthorList){
                List<SrmAchTopicAuthor> authorList = allAuthorMap.get(a.getTopicFlow());
                if(authorList == null){
                    authorList = new ArrayList<SrmAchTopicAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getTopicFlow(), authorList);
            }
        }

        //过滤
        if(StringUtil.isNotBlank(author.getAuthorName())){
            TopicList = new ArrayList<SrmAchTopic>();
            for(SrmAchTopic b : achTopicList){
                boolean addFlag = false;
                List<SrmAchTopicAuthor> authorByNameList = allAuthorMap.get(b.getTopicFlow());
                if(authorByNameList != null){
                    for(SrmAchTopicAuthor na : authorByNameList){
                        if(na.getAuthorName().equals(author.getAuthorName())){
                            addFlag = true;
                            break;
                        }
                    }
                }
                if(addFlag){
                    TopicList.add(b);
                }
            }
        }else {
            TopicList = achTopicList;
        }

        if (TopicAuthorList != null && !TopicAuthorList.isEmpty() && TopicList != null) {
            for (SrmAchTopic achTopic : TopicList) {
                //组织关联作者
                String authorName = "";
                AchTopicExportForm TopicExportForm = new AchTopicExportForm();
                for (SrmAchTopicAuthor achTopicAuthor : TopicAuthorList) {
                    if (achTopic.getTopicFlow().equals(achTopicAuthor.getTopicFlow())) {
                        authorName = authorName + " " + achTopicAuthor.getAuthorName();
                    }
                }
                TopicExportForm.setSrmAchTopic(achTopic);
                TopicExportForm.setAuthorName(authorName);
                searchList.add(TopicExportForm);
            }
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, searchList, response.getOutputStream());
        String fileName = "科研成果（奖项）.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }*/
}
