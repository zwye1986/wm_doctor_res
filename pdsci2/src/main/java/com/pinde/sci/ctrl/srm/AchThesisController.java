package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.AchTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.ach.AchThesisExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmAchThesisAuthorList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/srm/ach/thesis")
public class AchThesisController extends GeneralController {

    @Autowired
    private IThesisBiz thesisBiz;
    @Autowired
    private IThesisAuthorBiz authorBiz;
    @Autowired
    private ISrmAchFileBiz srmAchFileBiz;
    @Autowired
    private ISrmAchProcessBiz srmAchProcessBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IAchScoreBiz achScoreBiz;


    /**
     * 保存论文
     * @param roleScope
     * @param jsondata
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/save/{roleScope}"})
    @ResponseBody
    public String save(@PathVariable String roleScope, String jsondata, HttpServletRequest request) throws IOException {
        SrmAchThesisAuthorList aList = JSON.parseObject(jsondata, SrmAchThesisAuthorList.class);
        List<SrmAchThesisAuthor> authorList = aList.getAuthorList();
        SrmAchThesis thesis = aList.getThesis();
        //枚举：根据论文相关ID枚举获得name
        if(StringUtil.isNotBlank(thesis.getTypeId())){
            thesis.setTypeName(DictTypeEnum.ThesisType.getDictNameById(thesis.getTypeId()));
        }
        thesis.setHospSignName(DictTypeEnum.OrgBelong.getDictNameById(thesis.getHospSignId()));
        thesis.setPublishTypeName(DictTypeEnum.PublishType.getDictNameById(thesis.getPublishTypeId()));
        thesis.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(thesis.getProjTypeId()));
        thesis.setSubjectTypeName(DictTypeEnum.SubjectType.getDictNameById(thesis.getSubjectTypeId()));
        thesis.setPublishScopeName(DictTypeEnum.PublishScope.getDictNameById(thesis.getPublishScopeId()));
        thesis.setProjSourceName(DictTypeEnum.ProjTypeSource.getDictNameById(thesis.getProjSourceId()));
        thesis.setMeetingName(DictTypeEnum.MeetingType.getDictNameById(thesis.getMeetingId()));
        thesis.setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(thesis.getBranchId()));

        SysUser currUser = GlobalContext.getCurrentUser();
         /*thesis.setApplyUserFlow(currUser.getUserFlow());
	     thesis.setApplyUserName(currUser.getUserName());
	     thesis.setApplyOrgFlow(currUser.getOrgFlow());
	     thesis.setApplyOrgName(currUser.getOrgName());*/
        if (GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            thesis.setOperStatusId(AchStatusEnum.Apply.getId());
            thesis.setOperStatusName(AchStatusEnum.Apply.getName());
        }
        //根据刊物类型ID分解成数组,并枚举获得刊物类型name
        if (StringUtil.isNotBlank(thesis.getJourTypeId())) {
            String[] jti = thesis.getJourTypeId().split(",");
            StringBuffer jtn = new StringBuffer();
            for (int i = 0; i < jti.length; i++) {
                jtn.append(DictTypeEnum.JournalType.getDictNameById(jti[i]) + ",");
            }
            jtn.delete(jtn.length() - 1, jtn.length());
            thesis.setJourTypeName(jtn.toString());
        }
        //枚举：根据论文作者相关ID枚举获得name
        for (int i = 0; i < authorList.size(); i++) {
            authorList.get(i).setTypeName(DictTypeEnum.AuthorType.getDictNameById(authorList.get(i).getTypeId()));
            if(StringUtil.isNotBlank(authorList.get(i).getSexId())) {
                authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
            }
            authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
            authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
            authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
            authorList.get(i).setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(authorList.get(i).getBranchId()));
            if (StringUtil.isNotBlank(authorList.get(i).getScoreFlow())) {
                SrmAchScore score = new SrmAchScore();
                score.setScoreFlow(authorList.get(i).getScoreFlow());
                List<SrmAchScore> list = achScoreBiz.searchScoreSetList(score);
                if (list != null && list.size() > 0 && list.get(0) != null) {
                    authorList.get(i).setScoreFlow(list.get(0).getScoreFlow());
                    authorList.get(i).setScoreName(list.get(0).getScoreName());
                    authorList.get(i).setAchScore(list.get(0).getScorePersonalValue().add(new BigDecimal(0)));
                    authorList.get(i).setAchScoreDept(list.get(0).getScoreDeptValue().add(new BigDecimal(0)));
                }
            }
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("files");
        //保存附件
        List<SrmAchFile> achFileList = new ArrayList<>();
        String resultPath = "";
        for (int i = 0; i < fileList.size(); i++) {
            SrmAchFile srmAchFile = aList.getSrmAchFileList().get(i);
            if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
                //保存附件
                String flow = srmAchFile.getFileFlow();
                if (StringUtil.isNotBlank(flow)) {
                    srmAchFile.setFileFlow(flow);
                }
                resultPath = srmAchFileBiz.saveFileToDirs(fileList.get(i), "srmAchThesis", flow);
				srmAchFile.setTypeId(AchTypeEnum.Thesis.getId());
                srmAchFile.setTypeName(AchTypeEnum.Thesis.getName());
                srmAchFile.setFileName(fileList.get(i).getOriginalFilename());
				String[] nameArray = fileList.get(i).getOriginalFilename().split("\\.");
                srmAchFile.setFileSuffix(nameArray[nameArray.length - 1]);
                srmAchFile.setFileType(nameArray[nameArray.length - 1]);
                srmAchFile.setFilePath(resultPath);
                srmAchFile.setFileSaveType("1");
            }
			achFileList.add(srmAchFile);
        }
//        //封装附件对象
//        SrmAchFile srmAchFile = null;
//        if (file != null && StringUtil.isNotBlank(file.getOriginalFilename())) {
//            srmAchFile = new SrmAchFile();
//            byte[] b = new byte[(int) file.getSize()];
//            System.err.println(b.toString());
//            file.getInputStream().read(b);
//            srmAchFile.setFileFlow(aList.getSrmAchFile().getFileFlow());
//            srmAchFile.setFileContent(b);
//            srmAchFile.setFileName(file.getOriginalFilename());
//            srmAchFile.setTypeId(AchTypeEnum.Thesis.getId());
//            srmAchFile.setTypeName(AchTypeEnum.Thesis.getName());
//            String[] nameArray = file.getOriginalFilename().split("\\.");
//            srmAchFile.setFileSuffix(nameArray[nameArray.length - 1]);
//            srmAchFile.setFileType(nameArray[nameArray.length - 1]);
//        }
        //封装成果过程对象
        SrmAchProcess srmAchProcess = new SrmAchProcess();
        srmAchProcess.setTypeId(AchTypeEnum.Thesis.getId());
        srmAchProcess.setTypeName(AchTypeEnum.Thesis.getName());

        srmAchProcess.setOperateUserFlow(currUser.getUserFlow());
        srmAchProcess.setOperateUserName(currUser.getUserName());
        if (GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            srmAchProcess.setOperStatusId(AchStatusEnum.Apply.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.Apply.getName());
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleScope)) {
            srmAchProcess.setOperStatusId(AchStatusEnum.LocalEdit.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.LocalEdit.getName());
        }
        int result = thesisBiz.save(thesis, authorList, achFileList, srmAchProcess);
        if(result >0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 查询论文列表
     *
     * @param thesis
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@PathVariable String scope, Integer currentPage, SrmAchThesis thesis, SrmAchThesisAuthor author, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        thesis.setApplyUserFlow(currUser.getUserFlow());
        if ("wxdermyy".equals(scope) && GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())) {
            thesis.setOperStatusId("");
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SrmAchThesis> thesitlist = thesisBiz.search(thesis, null);

        //组织关联作者的Map
        Map<String, List<SrmAchThesisAuthor>> allAuthorMap = new LinkedHashMap<String, List<SrmAchThesisAuthor>>();
        List<SrmAchThesisAuthor> allThesisAuthorList = authorBiz.searchAuthorList(new SrmAchThesisAuthor());
        if (allThesisAuthorList != null && !allThesisAuthorList.isEmpty()) {
            for (SrmAchThesisAuthor a : allThesisAuthorList) {
                List<SrmAchThesisAuthor> list = allAuthorMap.get(a.getThesisFlow());
                if (list == null) {
                    list = new ArrayList<SrmAchThesisAuthor>();
                }
                list.add(a);
                allAuthorMap.put(a.getThesisFlow(), list);
            }
        }
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("thesisList", thesitlist);
        if ("xzzxyy".equals(scope)) {
            return "srm/ach_xzzxyy/thesis/list";
        }
        if ("wxdermyy".equals(scope)) {
            return "srm/ach_wxdermyy/thesis/list";
        }
        if ("common".equals(scope)) {
            return "srm/ach_common/thesis/list";
        }
        return "srm/ach/thesis/list";
    }

    /**
     * 论文是否已存在
     * @return
     */
    @RequestMapping("/thesisIsExist")
    @ResponseBody
    public SrmAchThesis thesisIsExist(String thesisName){
        SrmAchThesis thesis = null;
        if(StringUtil.isNotBlank(thesisName)) {
            thesisName = thesisName.replaceAll(" ", "");//半角空格
            thesisName = thesisName.replaceAll("　", "");//全角空格
            thesis = thesisBiz.thesisIsExist(thesisName);
        }
        return thesis;
    }

    @RequestMapping(value = "/auditList/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String scope, Integer currentPage, SrmAchThesis thesis, SrmAchThesisAuthor author, SysOrg org, Model model, HttpServletRequest request) {
        model.addAttribute("scope", scope);
        SysUser currUser = GlobalContext.getCurrentUser();
        List<SrmAchThesis> searchList = null;
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);
        //获取当前机构下属一级的机构
        List<SysOrg> firstGradeOrgList = (List<SysOrg>) resultMap.get("firstGradeOrgList");
        model.addAttribute("firstGradeOrgList", firstGradeOrgList);
        List<String> thesisFlows = null;
        if (StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())) {
            SrmAchThesisAuthor b = new SrmAchThesisAuthor();
            b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(author.getAuthorName())){
                b.setAuthorName(author.getAuthorName());
            }
            if(StringUtil.isNotBlank(author.getWorkCode())){
                b.setWorkCode(author.getWorkCode());
            }
            List<SrmAchThesisAuthor> autorsList = authorBiz.searchAuthorList(b);
            if (autorsList != null && autorsList.size() > 0) {
                thesisFlows = new ArrayList<>();
                for (SrmAchThesisAuthor sa : autorsList) {
                    if (!thesisFlows.contains(sa.getThesisFlow())) {
                        thesisFlows.add(sa.getThesisFlow());
                    }
                }
            }
            if(null == thesisFlows || thesisFlows.size()==0){
                thesisFlows=new ArrayList<>();
                thesisFlows.add("");
            }
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                thesis.setDeptFlow(currUser.getDeptFlow());
            }else{
                if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                    return "srm/ach_wxdermyy/thesis/auditListlocal";
                }
                return "srm/ach_common/thesis/auditListlocal";
            }
        }
        if (GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)) {
            //设置查询条件：科技处审核通过的成果
            thesis.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
            List<SysOrg> secondGradeOrgList = (List<SysOrg>) resultMap.get("secondGradeOrgList");
            if (null != secondGradeOrgList && !secondGradeOrgList.isEmpty()) {
                model.addAttribute("secondGradeOrgList", secondGradeOrgList);
            }
            //如果查询条件orgFlow为空且chargeOrgFlow不为空，则查询该主管部门下所有子机构的所有成果
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())) {
                if (null == secondGradeOrgList || secondGradeOrgList.isEmpty()) {
                    SysOrg sysOrg = orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList = new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    PageHelper.startPage(currentPage, getPageSize(request));
                    searchList = thesisBiz.search2(thesis, selfOrgList, thesisFlows);
                } else {
                    PageHelper.startPage(currentPage, getPageSize(request));
                    searchList = thesisBiz.search2(thesis, secondGradeOrgList, thesisFlows);
                }
            }
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)) {
            thesis.setOperStatusId(AchStatusEnum.FirstAudit.getId());
        } else {
            org.setOrgFlow(currUser.getOrgFlow());//当前登陆者是医院 只查询本医院下的成果
            if (StringUtil.isBlank(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId());
            } else if (GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
        }
        //如果查询条件orgFlow不为空，则查询该org下所有成果
        if (StringUtil.isNotBlank(org.getOrgFlow())) {
            PageHelper.startPage(currentPage, getPageSize(request));
            thesis.setApplyOrgFlow(org.getOrgFlow());
            searchList = thesisBiz.search2(thesis, null, thesisFlows);
        }
        //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
        if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
            PageHelper.startPage(currentPage, getPageSize(request));
            searchList = thesisBiz.search2(thesis, currOrgChildList, thesisFlows);
        }

        Map<String, List<SrmAchThesisAuthor>> allAuthorMap = new HashMap<String, List<SrmAchThesisAuthor>>();
        List<SrmAchThesisAuthor> thesisAuthorList = authorBiz.searchAuthorList(new SrmAchThesisAuthor());
        if (thesisAuthorList != null && !thesisAuthorList.isEmpty()) {
            for (SrmAchThesisAuthor a : thesisAuthorList) {
                List<SrmAchThesisAuthor> authorList = allAuthorMap.get(a.getThesisFlow());
                if (authorList == null) {
                    authorList = new ArrayList<SrmAchThesisAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getThesisFlow(), authorList);
            }
        }
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("thesisList", searchList);
        if ("xzzxyy".equals(scope)) {
            return "srm/ach_xzzxyy/thesis/auditListlocal";
        }
        if ("wxdermyy".equals(scope)) {
            return "srm/ach_wxdermyy/thesis/auditListlocal";
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//科主任
            if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                return "srm/ach_wxdermyy/thesis/auditListlocal";
            }
            return "srm/ach_common/thesis/auditListlocal";
        }
        if ("common".equals(scope)) {
            return "srm/ach_common/thesis/auditListlocal";
        }
        return "srm/ach/thesis/auditList" + scope;
    }

    /**
     * 打开编辑页面并且获取一条论文记录的详细信息
     *
     * @param thesisFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{scope}", method = {RequestMethod.GET})
    public String edit(@PathVariable String scope, String thesisFlow, Model model) {
        model.addAttribute("scope", scope);
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        SrmAchThesis thesis = thesisBiz.readThesis(thesisFlow);
        model.addAttribute("thesis", thesis);
        //根据论文流水号查询其作者
        if (StringUtil.isNotBlank(thesisFlow)) {
            SrmAchThesisAuthor author = new SrmAchThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<SrmAchThesisAuthor> authorList = authorBiz.searchAuthorList(author);
            model.addAttribute("authorList", authorList);

            fileList = srmAchFileBiz.searchSrmAchFile(thesisFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                model.addAttribute("file", file);
            }
        }
        model.addAttribute("roleScope", GlobalConstant.USER_LIST_PERSONAL);
        if ("xzzxyy".equals(scope)) {
            return "srm/ach_xzzxyy/thesis/edit";
        }
        if ("wxdermyy".equals(scope)) {
            model.addAttribute("fileList", fileList);
            return "srm/ach_wxdermyy/thesis/edit";
        }
        if ("common".equals(scope)) {
            model.addAttribute("fileList", fileList);
            return "srm/ach_common/thesis/edit";
        }
        return "srm/ach/thesis/edit";
    }


    /**
     * 删除一条论文记录
     *
     * @param thesisFlow
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String thesisFlow) {
        if (StringUtil.isNotBlank(thesisFlow)) {
            SrmAchThesis thesis = new SrmAchThesis();
            thesis.setThesisFlow(thesisFlow);
            thesis.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            SrmAchThesisAuthor author = new SrmAchThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<SrmAchThesisAuthor> authorList = authorBiz.searchAuthorList(author);
            for (SrmAchThesisAuthor a : authorList) {
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }

            List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(thesisFlow);
            SrmAchFile file = null;
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }

            int result = thesisBiz.edit(thesis, authorList, file);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }


    @RequestMapping(value = "/deleteAuthor", method = {RequestMethod.GET})
    @ResponseBody
    public String deleteAuthor(String authorFlow) {
        if (StringUtil.isNotBlank(authorFlow)) {
            SrmAchThesisAuthor author = new SrmAchThesisAuthor();
            author.setAuthorFlow(authorFlow);
            author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result = authorBiz.editAuthor(author);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/audit")
    public String audit(@RequestParam(value = "thesisFlow", required = true) String thesisFlow, Model model) {
        SrmAchScore score = new SrmAchScore();
        score.setScoreStatusId(GlobalConstant.RECORD_STATUS_Y);
        List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(score);
        model.addAttribute("srmAchScoreList", srmAchScoreList);
        //根据成果流水号查询相关信息，用于加载审核页面
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        if (StringUtil.isNotBlank(thesisFlow)) {
            //查询成果本身
            SrmAchThesis thesis = thesisBiz.readThesis(thesisFlow);
            model.addAttribute("thesis", thesis);
            //查询成果作者
            SrmAchThesisAuthor author = new SrmAchThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<SrmAchThesisAuthor> authorList = authorBiz.searchAuthorList(author);
            model.addAttribute("authorList", authorList);
            //查询成果附件
            fileList = srmAchFileBiz.searchSrmAchFile(thesisFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                model.addAttribute("file", file);
            }
        }
        model.addAttribute("roleScope", GlobalConstant.USER_LIST_LOCAL);
        model.addAttribute("company","Y");
        //徐州中心医院
        if ("Y".equals(InitConfig.getSysCfg("srm_local_type"))) {
            return "srm/ach_xzzxyy/thesis/audit";
        }
        //无锡第二人民医院
        if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
            model.addAttribute("fileList", fileList);
            return "srm/ach_wxdermyy/thesis/audit";
        }
        if ("common".equals(InitConfig.getSysCfg("srm_local_type"))) {
            model.addAttribute("fileList", fileList);
            return "srm/ach_common/thesis/audit";
        }
        return "srm/ach/thesis/audit";
    }

    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@RequestParam(value = "agreeFlag", required = true) String agreeFlag, String auditContent,
                            @RequestParam(value = "thesisFlow", required = true) String thesisFlow, Model model) {
        SrmAchThesis thesis = thesisBiz.readThesis(thesisFlow);
        SrmAchProcess process = srmAchProcessBiz.searchAchProcess(thesisFlow, AchStatusEnum.Apply.getId()).get(0);

        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            thesis.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            thesis.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
        } else {
            thesis.setOperStatusId(AchStatusEnum.RollBack.getId());
            thesis.setOperStatusName(AchStatusEnum.RollBack.getName());
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
        thesisBiz.updateThesisStatus(thesis, process);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value = "thesisFlow", required = true) String thesisFlow, Model model) {
        if (StringUtil.isNotBlank(thesisFlow)) {
            SrmAchThesis thesis = thesisBiz.readThesis(thesisFlow);
            thesis.setOperStatusId(AchStatusEnum.Submit.getId());
            thesis.setOperStatusName(AchStatusEnum.Submit.getName());

            SrmAchProcess process = srmAchProcessBiz.searchAchProcess(thesisFlow, AchStatusEnum.Apply.getId()).get(0);
            process.setProcessFlow(PkUtil.getUUID());
            process.setOperStatusId(AchStatusEnum.Submit.getId());
            process.setOperStatusName(AchStatusEnum.Submit.getName());
            GeneralMethod.setRecordInfo(process, true);
            process.setOperateTime(process.getCreateTime());
            SysUser currUser = GlobalContext.getCurrentUser();
            process.setOperateUserFlow(currUser.getUserFlow());
            process.setOperateUserName(currUser.getUserName());
            thesisBiz.updateThesisStatus(thesis, process);
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping(value = "/exportThesisExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportThesisExcel(@PathVariable String scope, SrmAchThesis thesis, SrmAchThesisAuthor author, SysOrg org, HttpServletResponse response) throws Exception {
        if(scope.equals("wxdermyy") || "common".equals(scope)){
            scope = GlobalConstant.PROJ_STATUS_SCOPE_LOCAL;
        }
        String[] titles;//导出列表头信息
        SysUser currUser = GlobalContext.getCurrentUser();
        /*著作权导出信息列表*/
        List<AchThesisExportForm> searchList = new ArrayList<AchThesisExportForm>();
        /*查询著作权信息*/
        List<SrmAchThesis> thesisList = null;
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);

        List<String> thesisFlows = null;
        if (StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())) {
            SrmAchThesisAuthor b = new SrmAchThesisAuthor();
            b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(author.getAuthorName())){
                b.setAuthorName(author.getAuthorName());
            }
            if(StringUtil.isNotBlank(author.getWorkCode())){
                b.setWorkCode(author.getWorkCode());
            }
            List<SrmAchThesisAuthor> autorsList = authorBiz.searchAuthorList(b);
            if (autorsList != null && autorsList.size() > 0) {
                thesisFlows = new ArrayList<>();
                for (SrmAchThesisAuthor sa : autorsList) {
                    if (!thesisFlows.contains(sa.getThesisFlow())) {
                        thesisFlows.add(sa.getThesisFlow());
                    }
                }
            }
            if(null == thesisFlows || thesisFlows.size()==0){
                thesisFlows=new ArrayList<>();
                thesisFlows.add("");
            }
        }

        //卫计委
        if (GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)) {
            thesis.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            titles = new String[]{
                    "srmAchThesis.thesisName:论文题目",
                    "srmAchThesis.applyOrgName:申报单位",
                    "srmAchThesis.typeName:论文类型",
                    "authorName:作者",
                    "firstAuthor:第一作者",
                    "correspondingAuthor:通讯作者",
                    "srmAchThesis.publishDate:发表/出版日期",
                    "srmAchThesis.projSourceName:项目来源"
            };
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())) {
                //设置查询条件：科技处审核通过的成果
                //如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
                List<SysOrg> secondGradeOrgList = (List<SysOrg>) resultMap.get("secondGradeOrgList");
                if (null == secondGradeOrgList || secondGradeOrgList.isEmpty()) {
                    SysOrg sysOrg = orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList = new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    thesisList = thesisBiz.search2(thesis, selfOrgList,thesisFlows);
                } else {
                    thesisList = thesisBiz.search2(thesis, secondGradeOrgList,thesisFlows);
                }
            }

            //主管部门
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)) {
            titles = new String[]{
                    "srmAchThesis.thesisName:论文题目",
                    "srmAchThesis.applyOrgName:申报单位",
                    "srmAchThesis.typeName:论文类型",
                    "authorName:作者",
                    "firstAuthor:第一作者",
                    "correspondingAuthor:通讯作者",
                    "srmAchThesis.publishDate:发表/出版日期",
                    "srmAchThesis.projSourceName:项目来源"
            };
            thesis.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //申报单位
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)) {
            titles = new String[]{
                    "srmAchThesis.publishDate:年度",
                    "workCode:工号",
                    "firstAuthor:第一作者",
                    "parallelFirstAuthor:并列第一作者",
                    "correspondingAuthor:通讯作者",
                    "srmAchThesis.deptName:科室",
                    "srmAchThesis.thesisName:论文题目",
                    "srmAchThesis.publishJour:杂志名称",
                    "srmAchThesis.jourCode:杂志年卷页",
                    "srmAchThesis.jourTypeName:刊物类型",
                    "srmAchThesis.typeName:论文类别",
                    "srmAchThesis.sci:影响因子"
            };
            if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                titles = new String[]{
                        "srmAchThesis.publishDate:年度",
                        "workCode:工号",
                        "firstAuthor:第一作者",
                        "parallelFirstAuthor:并列第一作者",
                        "correspondingAuthor:通讯作者",
                        "parallelCorrespondingAuthor:并列通讯作者",
                        "srmAchThesis.deptName:科室",
                        "srmAchThesis.branchName:支部",
                        "srmAchThesis.thesisName:论文题目",
                        "srmAchThesis.publishJour:杂志名称",
                        "srmAchThesis.jourCode:杂志年卷页",
                        "srmAchThesis.jourTypeName:刊物类型",
                        "srmAchThesis.typeName:论文类别",
                        "srmAchThesis.sci:影响因子"
                };
            }
            org.setOrgFlow(currUser.getOrgFlow());
            if (StringUtil.isBlank(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }

        } else if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//科主任
            titles = new String[]{
                    "srmAchThesis.publishDate:年度",
                    "workCode:工号",
                    "authorName:姓名",
                    "firstAuthor:第一作者",
                    "correspondingAuthor:通讯作者",
                    "srmAchThesis.deptName:科室",
                    "srmAchThesis.thesisName:论文题目",
                    "srmAchThesis.publishJour:杂志名称",
                    "srmAchThesis.jourCode:杂志年卷页",
                    "srmAchThesis.jourTypeName:刊物类型",
                    "srmAchThesis.typeName:论文类别"
            };
            if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                titles = new String[]{
                        "srmAchThesis.publishDate:年度",
                        "workCode:工号",
                        "authorName:姓名",
                        "firstAuthor:第一作者",
                        "correspondingAuthor:通讯作者",
                        "srmAchThesis.deptName:科室",
                        "srmAchThesis.branchName:支部",
                        "srmAchThesis.thesisName:论文题目",
                        "srmAchThesis.publishJour:杂志名称",
                        "srmAchThesis.jourCode:杂志年卷页",
                        "srmAchThesis.jourTypeName:刊物类型",
                        "srmAchThesis.typeName:论文类别"
                };
            }
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                thesis.setDeptFlow(currUser.getDeptFlow());
            }
            if (StringUtil.isBlank(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())) {
                thesis.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
        } else {
            titles = new String[]{
                    "srmAchThesis.publishDate:年度",
                    "workCode:工号",
                    "authorName:姓名",
                    "firstAuthor:第一作者",
                    "correspondingAuthor:通讯作者",
                    "srmAchThesis.deptName:科室",
                    "srmAchThesis.thesisName:论文题目",
                    "srmAchThesis.publishJour:杂志名称",
                    "srmAchThesis.jourCode:杂志年卷页",
                    "srmAchThesis.jourTypeName:刊物类型",
                    "srmAchThesis.typeName:论文类别"
            };
            if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                titles = new String[]{
                        "srmAchThesis.publishDate:年度",
                        "workCode:工号",
                        "authorName:姓名",
                        "firstAuthor:第一作者",
                        "correspondingAuthor:通讯作者",
                        "srmAchThesis.deptName:科室",
                        "srmAchThesis.branchName:支部",
                        "srmAchThesis.thesisName:论文题目",
                        "srmAchThesis.publishJour:杂志名称",
                        "srmAchThesis.jourCode:杂志年卷页",
                        "srmAchThesis.jourTypeName:刊物类型",
                        "srmAchThesis.typeName:论文类别"
                };
            }
            thesis.setApplyUserFlow(currUser.getUserFlow());
            if (GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())) {
                thesis.setOperStatusId("");
            }
            thesisList = thesisBiz.search(thesis, null);
        }
        if (!scope.equals(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL)) {
            //如果查询条件orgFlow不为空，则查询该org下所有成果
            if (StringUtil.isNotBlank(org.getOrgFlow())) {
                thesis.setApplyOrgFlow(org.getOrgFlow());
                thesisList = thesisBiz.search2(thesis, null,thesisFlows);
            }
            //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
                thesisList = thesisBiz.search2(thesis, currOrgChildList,thesisFlows);
            }

            if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope) && StringUtil.isBlank(thesis.getDeptFlow())){
                thesisList = null;
            }
        }
        List<SrmAchThesisAuthor> allThesisAuthorList = authorBiz.searchAuthorList(new SrmAchThesisAuthor());
        if (allThesisAuthorList != null && !allThesisAuthorList.isEmpty() && thesisList != null) {
            for (SrmAchThesis achThesis : thesisList) {
                //杂志年卷页不为空（二院历史数据筛选）
                if(StringUtil.isNotBlank(achThesis.getJourYear())&&StringUtil.isNotBlank(achThesis.getVolumeCode()) && StringUtil.isNotBlank(achThesis.getPageNoRange())){
                    achThesis.setJourCode(achThesis.getJourYear() + "," + achThesis.getVolumeCode() + "(" + achThesis.getJourCode() + ")" + achThesis.getPageNoRange());
                }
                //组织关联作者
                String authorName = "";
                String workCode = "";
                String firstAuthor = "";
                String parallelFirstAuthor = "";
                String correspondingAuthor = "";
                String parallelCorrespondingAuthor = "";
                AchThesisExportForm thesisExportForm = new AchThesisExportForm();
                for (SrmAchThesisAuthor thesisAuthor : allThesisAuthorList) {
                    if (achThesis.getThesisFlow().equals(thesisAuthor.getThesisFlow())) {
                        if("外院".equals(thesisAuthor.getAuthorPart())){
                            if(authorName.equals("")){
                                authorName = thesisAuthor.getAuthorName()+"(外院)";
                            }else {
                                authorName = authorName + "," + thesisAuthor.getAuthorName() + "(外院)";
                            }
                        }else {
                            if(authorName.equals("")) {
                                authorName = thesisAuthor.getAuthorName();
                            }else{
                                authorName = authorName + "," + thesisAuthor.getAuthorName();
                            }
                        }
                        if(StringUtil.isNotBlank(thesisAuthor.getWorkCode())) {
                            if(workCode.equals("")){
                                workCode = thesisAuthor.getWorkCode();
                            }else {
                                workCode = workCode + "," + thesisAuthor.getWorkCode();
                            }
                        }
                        if("第一作者".equals(thesisAuthor.getTypeName())){
                            if("外院".equals(thesisAuthor.getAuthorPart())){
                                if(firstAuthor.equals("")){
                                    firstAuthor = thesisAuthor.getAuthorName()+"(外院)";
                                }else {
                                    firstAuthor = firstAuthor + "," + thesisAuthor.getAuthorName() + "(外院)";
                                }
                            }else{
                                if(firstAuthor.equals("")){
                                    firstAuthor = thesisAuthor.getAuthorName();
                                }else {
                                    firstAuthor = firstAuthor + "," + thesisAuthor.getAuthorName();
                                }
                            }
                        }
                        if("并列第一作者".equals(thesisAuthor.getTypeName())){
                            if("外院".equals(thesisAuthor.getAuthorPart())){
                                if(parallelFirstAuthor.equals("")){
                                    parallelFirstAuthor = thesisAuthor.getAuthorName()+"(外院)";
                                }else {
                                    parallelFirstAuthor = parallelFirstAuthor + "," + thesisAuthor.getAuthorName() + "(外院)";
                                }
                            }else{
                                if(parallelFirstAuthor.equals("")){
                                    parallelFirstAuthor = thesisAuthor.getAuthorName();
                                }else {
                                    parallelFirstAuthor = parallelFirstAuthor + "," + thesisAuthor.getAuthorName();
                                }
                            }
                        }
                        if("通讯作者".equals(thesisAuthor.getTypeName())){
                            if("外院".equals(thesisAuthor.getAuthorPart())){
                                if(correspondingAuthor.equals("")){
                                    correspondingAuthor = thesisAuthor.getAuthorName() + "(外院)";
                                }else {
                                    correspondingAuthor = correspondingAuthor + "," + thesisAuthor.getAuthorName() + "(外院)";
                                }
                            }else{
                                if(correspondingAuthor.equals("")){
                                    correspondingAuthor = thesisAuthor.getAuthorName();
                                }else {
                                    correspondingAuthor = correspondingAuthor + "," + thesisAuthor.getAuthorName();
                                }
                            }

                        }
                        //省中医院
                        if("1".equals(thesisAuthor.getIsCorresponding())){
                            if("外院".equals(thesisAuthor.getAuthorPart())){
                                if(correspondingAuthor.equals("")){
                                    correspondingAuthor = thesisAuthor.getAuthorName() + "(外院)";
                                }else {
                                    correspondingAuthor = correspondingAuthor + "," + thesisAuthor.getAuthorName() + "(外院)";
                                }
                            }else{
                                if(correspondingAuthor.equals("")){
                                    correspondingAuthor = thesisAuthor.getAuthorName();
                                }else {
                                    correspondingAuthor = correspondingAuthor + "," + thesisAuthor.getAuthorName();
                                }
                            }

                        }
                        if("并列通讯作者".equals(thesisAuthor.getTypeName())){
                            if("外院".equals(thesisAuthor.getAuthorPart())){
                                if(parallelCorrespondingAuthor.equals("")){
                                    parallelCorrespondingAuthor = thesisAuthor.getAuthorName() + "(外院)";
                                }else {
                                    parallelCorrespondingAuthor = parallelCorrespondingAuthor + "," + thesisAuthor.getAuthorName() + "(外院)";
                                }
                            }else{
                                if(parallelCorrespondingAuthor.equals("")){
                                    parallelCorrespondingAuthor = thesisAuthor.getAuthorName();
                                }else {
                                    parallelCorrespondingAuthor = parallelCorrespondingAuthor + "," + thesisAuthor.getAuthorName();
                                }
                            }
                        }
                    }
                }
                thesisExportForm.setFirstAuthor(firstAuthor);
                thesisExportForm.setParallelFirstAuthor(parallelFirstAuthor);
                thesisExportForm.setCorrespondingAuthor(correspondingAuthor);
                thesisExportForm.setParallelCorrespondingAuthor(parallelCorrespondingAuthor);
                thesisExportForm.setSrmAchThesis(achThesis);
                thesisExportForm.setAuthorName(authorName);
                thesisExportForm.setWorkCode(workCode);
                searchList.add(thesisExportForm);
            }
        }
        String fileName = "科研成果（论文）.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, searchList, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping("/getFileNum")
    @ResponseBody
    public int getFileNum(String flow){
        int result = 0;
        List<SrmAchFile> files = srmAchFileBiz.searchSrmAchFile(flow);
        if(files!=null&&files.size()>0){
            result = files.size();
        }
        return result;
    }
}
