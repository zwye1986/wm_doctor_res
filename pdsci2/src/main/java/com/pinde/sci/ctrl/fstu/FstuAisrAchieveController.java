package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAchieveAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuAchieveBiz;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.enums.fstu.RelTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.fstu.FstuAchieveAuthorList;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 科研成果-我的成果
 */
@Controller
@RequestMapping("/fstu/aisr/achieve")
public class FstuAisrAchieveController extends GeneralController {
    @Autowired
    private IFstuAchieveBiz achieveBiz;
    @Autowired
    private IFstuAchieveAuthorBiz achieveAuthorBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IFstuAuditProcessBiz auditProcessBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IFstuAuditProcessBiz processBiz;
    /**
     * 加载科技报奖列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(FstuAchieve achieve, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        achieve.setApplyUserFlow(currUser.getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<FstuAchieve> achieveList = achieveBiz.search(achieve, null);
        Map<String, List<FstuAchieveAuthor>> allAuthorMap = new LinkedHashMap<String, List<FstuAchieveAuthor>>();
        List<FstuAchieveAuthor> allAuthorList = achieveAuthorBiz.searchAuthorList(new FstuAchieveAuthor());
        for (FstuAchieveAuthor a : allAuthorList) {
            List<FstuAchieveAuthor> list = allAuthorMap.get(a.getAchieveFlow());
            if (list == null) {
                list = new ArrayList<FstuAchieveAuthor>();
            }
            list.add(a);
            allAuthorMap.put(a.getAchieveFlow(), list);
        }
        model.addAttribute("achieveList", achieveList);
        model.addAttribute("allAuthorMap", allAuthorMap);
        return "/fstu/aisr/achieve/achieveList";
    }

    /**
     * 编辑科技信息
     *
     * @param achieveFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(String achieveFlow, Model model) {
        PubFile file = null;
        List<PubFile> pubFileList = null;
        if (StringUtil.isNotBlank(achieveFlow)) {
            FstuAchieve achieve = achieveBiz.readAchieve(achieveFlow);
            model.addAttribute("achieve", achieve);
            //查询科技作者信息
            FstuAchieveAuthor author = new FstuAchieveAuthor();
            author.setAchieveFlow(achieveFlow);
            List<FstuAchieveAuthor> achieveAuthorList = achieveAuthorBiz.searchAuthorList(author);
            model.addAttribute("achieveAuthorList", achieveAuthorList);
            pubFileList = fileBiz.searchByProductFlow(achieveFlow);
        }
        model.addAttribute("pubFileList", pubFileList);
        return "/fstu/aisr/achieve/edit";
    }

    //下载附件
    @RequestMapping(value = {"/fileDown"}, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
        PubFile file = this.fileBiz.readFile(fileFlow);
        achieveBiz.down(file, response);
    }

    /**
     * 保存成果及作者
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/save"})
    @ResponseBody
    public String save(String jsondata, HttpServletRequest request) throws IOException {
        FstuAchieveAuthorList aList = JSON.parseObject(jsondata, FstuAchieveAuthorList.class);

        FstuAchieve achieve = aList.getAchieve();
        List<FstuAchieveAuthor> authorList = aList.getAuthorList();
        //-----枚举：根据相关的ID获得name-----
        achieve.setAchTypeName(DictTypeEnum.SatType.getDictNameById(achieve.getAchTypeId()));
        achieve.setPrizedGradeName(DictTypeEnum.SatGrade.getDictNameById(achieve.getPrizedGradeId()));
        achieve.setPrizedLevelName(DictTypeEnum.SatLevel.getDictNameById(achieve.getPrizedLevelId()));
        achieve.setCategoryName(DictTypeEnum.SubjectCategories.getDictNameById(achieve.getCategoryId()));
        achieve.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(achieve.getProjSourceId()));
        //所属单位
        achieve.setOrgBelongName(DictTypeEnum.SatOrg.getDictNameById(achieve.getOrgBelongId()));
        //状态
        achieve.setOperStatusId(ProStatusEnum.Draft.getId());
        achieve.setOperStatusName(ProStatusEnum.Draft.getName());
        //获取申请单位信息
        SysUser currUser = GlobalContext.getCurrentUser();
        achieve.setApplyOrgFlow(currUser.getOrgFlow());
        achieve.setApplyOrgName(currUser.getOrgName());
        //申请人
        achieve.setApplyUserFlow(currUser.getUserFlow());
        achieve.setApplyUserName(currUser.getUserName());
        //科室信息
        achieve.setDeptFlow(currUser.getDeptFlow());
        achieve.setDeptName(currUser.getDeptName());

        //根据科技作者相关的ID枚举获得相对应的Name（性别、学历、职称）
        for (int i = 0; i < authorList.size(); i++) {
            authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
            authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
            authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
            authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
        }
        List<PubFile> pubFileList = new ArrayList<>();
        String resultPath = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("files");
        for (int i = 0; i < fileList.size(); i++) {
            PubFile pubFile = aList.getPubFileList().get(i);
            if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
                //保存附件
                String flow = pubFile.getFileFlow();
                if (StringUtil.isNotBlank(flow)) {
                    pubFile.setFileFlow(flow);
                }
                resultPath = achieveBiz.saveFileToDirs(fileList.get(i), "fstuImages", flow);
                pubFile.setFileName(fileList.get(i).getOriginalFilename());
                pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
                pubFile.setFileUpType("1");
            }
            pubFileList.add(pubFile);
        }
       /* //封装成果过程对象
        FstuAuditProcess fstuProcess = new FstuAuditProcess();
        fstuProcess.setRelTypeId(RelTypeEnum.Achieve.getId());
        //        fstuProcess.setRelTypeName(RelTypeEnum.Book.getName());
        fstuProcess.setOperUserFlow(currUser.getUserFlow());
        fstuProcess.setOperUserName(currUser.getUserName());
        fstuProcess.setProStatusId(ProStatusEnum.Draft.getId());
        fstuProcess.setProStatusName(ProStatusEnum.Draft.getName());*/
        int reslut = achieveBiz.save(achieve, authorList, pubFileList, null);
        if (reslut == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;

    }

    @RequestMapping(value = "/deleteAuthor", method = {RequestMethod.GET})
    @ResponseBody
    public String deleteAuthor(String authorFlow) {
        if (StringUtil.isNotBlank(authorFlow)) {
            FstuAchieveAuthor author = new FstuAchieveAuthor();
            author.setAuthorFlow(authorFlow);
            achieveAuthorBiz.deleteOneAuthor(author);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String achieveFlow) {
        if (StringUtil.isNotBlank(achieveFlow)) {
            FstuAchieve achieve = new FstuAchieve();
            achieve.setAchieveFlow(achieveFlow);
            achieve.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            FstuAchieveAuthor author = new FstuAchieveAuthor();
            author.setAchieveFlow(achieveFlow);
            List<FstuAchieveAuthor> authorList = achieveAuthorBiz.searchAuthorList(author);
            for (FstuAchieveAuthor a : authorList) {
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            //附件
            List<PubFile> fileList = fileBiz.searchByProductFlow(achieveFlow);
            if (fileList != null && !fileList.isEmpty()) {
                for (int i = 0; i < fileList.size(); i++) {
                    fileList.get(i).setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                }
//                file = fileList.get(0);
//                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            int result = achieveBiz.save(achieve, authorList, fileList, null);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(String achieveFlow, Model model) {
        FstuAchieve achieve = achieveBiz.readAchieve(achieveFlow);
        achieve.setOperStatusId(ProStatusEnum.Apply.getId());
        achieve.setOperStatusName(ProStatusEnum.Apply.getName());
        FstuAuditProcess process = new FstuAuditProcess();
        process.setRelTypeId(RelTypeEnum.Achieve.getId());
        process.setRelFlow(achieve.getAchieveFlow());
        process.setProcessFlow(PkUtil.getUUID());
        process.setProStatusId(ProStatusEnum.Apply.getId());
        process.setProStatusName(ProStatusEnum.Apply.getName());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        achieveBiz.updateAchieveStatus(achieve, process);

        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value = "/auditList/{role}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String role, Integer currentPage, FstuAchieve achieve, FstuAchieveAuthor author, SysOrg org, Model model, HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        model.addAttribute("role", role);
        SysUser currUser = GlobalContext.getCurrentUser();
        if ("adminAchieve".equals(role)) {
            if (StringUtil.isBlank(achieve.getOperStatusId())) {
                achieve.setOperStatusId(ProStatusEnum.Apply.getId());
            }
        }
        if ("admin".equals(role)) {
            if (StringUtil.isBlank(achieve.getOperStatusId())) {
                achieve.setOperStatusId("all");
            }
        }
        List<FstuAchieve> achieves = new ArrayList<>();
        List<FstuAchieve> achieveList = new ArrayList<>();
        if ("deptMaster".equals(role)) {
            //科主任角色查询
            String userFlow = currUser.getUserFlow();
            String dept = currUser.getDeptFlow();
            List<String> deptFlowList = new ArrayList<>();
            if (StringUtil.isNotBlank(dept)) {
                deptFlowList.add(dept);
            }
            List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
            if (userDepts != null && userDepts.size() > 0) {
                for (SysUserDept userDept : userDepts) {
                    String deptFlow = userDept.getDeptFlow();
                    deptFlowList.add(deptFlow);
                }
            }
            if (deptFlowList.size() == 0) {
                PageHelper.startPage(currentPage, getPageSize(request));
                achieve.setApplyUserFlow(userFlow);
                achieves = achieveBiz.search(achieve, null);
            } else {
                PageHelper.startPage(currentPage, getPageSize(request));
                achieves = achieveBiz.search(achieve, deptFlowList);
            }
        } else {
            PageHelper.startPage(currentPage, getPageSize(request));
            achieves = achieveBiz.search(achieve, null);
        }

        Map<String, List<FstuAchieveAuthor>> allAuthorMap = new HashMap<String, List<FstuAchieveAuthor>>();
        List<FstuAchieveAuthor> achieveAuthorList = achieveAuthorBiz.searchAuthorList(new FstuAchieveAuthor());
        if (achieveAuthorList != null && !achieveAuthorList.isEmpty()) {
            for (FstuAchieveAuthor a : achieveAuthorList) {
                List<FstuAchieveAuthor> authorList = allAuthorMap.get(a.getAchieveFlow());
                if (authorList == null) {
                    authorList = new ArrayList<FstuAchieveAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getAchieveFlow(), authorList);
            }
        }
        //作者查询
        String pageFlag = "Y";
        if (StringUtil.isNotBlank(author.getAuthorName())) {
            pageFlag = "N";
            for (FstuAchieve s : achieves) {
                boolean addFlag = false;
                List<FstuAchieveAuthor> authorByNameList = allAuthorMap.get(s.getAchieveFlow());
                if (authorByNameList != null) {
                    for (FstuAchieveAuthor na : authorByNameList) {
                        if (na.getAuthorName().equals(author.getAuthorName())) {
                            addFlag = true;
                            break;
                        }
                    }
                }
                if (addFlag) {
                    achieveList.add(s);
                }
            }
        } else {
            achieveList = achieves;
        }
        model.addAttribute("pageFlag", pageFlag);
        model.addAttribute("achieveList", achieveList);
        model.addAttribute("allAuthorMap", allAuthorMap);
        return "fstu/aisr/achieve/auditList";
    }

    /**
     * 跳转到审核界面
     *
     * @param achieveFlow
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    public String audit(String achieveFlow, Model model) {
        //根据成果流水号查询相关信息，用于加载审核页面
        List<PubFile> pubFileList = null;
        if (StringUtil.isNotBlank(achieveFlow)) {
            //查询成果本身
            FstuAchieve achieve = achieveBiz.readAchieve(achieveFlow);
            model.addAttribute("achieve", achieve);
            //查询成果作者
            FstuAchieveAuthor author = new FstuAchieveAuthor();
            author.setAchieveFlow(achieveFlow);
            List<FstuAchieveAuthor> achieveAuthorList = achieveAuthorBiz.searchAuthorList(author);
            model.addAttribute("achieveAuthorList", achieveAuthorList);
            //查询成果附件
            pubFileList = fileBiz.searchByProductFlow(achieveFlow);
            if (pubFileList != null && !pubFileList.isEmpty()) {
                model.addAttribute("pubFileList", pubFileList);
            }
        }
        FstuAchieve achieve = achieveBiz.readAchieve(achieveFlow);
        model.addAttribute("achieve", achieve);
        return "fstu/aisr/achieve/audit";
    }

    /**
     * 保存审核结果
     *
     * @param agreeFlag
     * @param auditContent
     * @param achieveFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(String agreeFlag, String auditContent, String achieveFlow, Model model) {
        FstuAchieve achieve = achieveBiz.readAchieve(achieveFlow);
        FstuAuditProcess process = auditProcessBiz.search(achieveFlow).get(0);

        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            achieve.setOperStatusId(ProStatusEnum.Passed.getId());
            achieve.setOperStatusName(ProStatusEnum.Passed.getName());
            process.setProStatusId(ProStatusEnum.Passed.getId());
            process.setProStatusName(ProStatusEnum.Passed.getName());
        } else {
            achieve.setOperStatusId(ProStatusEnum.UnPassed.getId());
            achieve.setOperStatusName(ProStatusEnum.UnPassed.getName());
            process.setProStatusId(ProStatusEnum.UnPassed.getId());
            process.setProStatusName(ProStatusEnum.UnPassed.getName());
        }

        process.setAuditContent(auditContent);
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        process.setOperOrgFlow(currUser.getOrgFlow());
        process.setOperOrgName(currUser.getOrgName());
        process.setProcessFlow(PkUtil.getUUID());
        String currTime = DateUtil.getCurrDateTime();
        process.setOperTime(currTime);
        achieve.setLastAuditAdvice(auditContent);
        achieve.setLastAuditTime(currTime);
        achieveBiz.updateAchieveStatus(achieve, process);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 审核记录查看
     *
     * @return
     */
    @RequestMapping("/auditProcess")
    public String auditProcess(String flow, Model model) {
        List<FstuAuditProcess> processList = this.auditProcessBiz.search(flow);
        model.addAttribute("processList", processList);
        return "fstu/aisr/achieve/auditProcess";
    }
}
