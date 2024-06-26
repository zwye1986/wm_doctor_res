package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.fstu.IFstuFileBiz;
import com.pinde.sci.biz.fstu.IFstuPatentAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuPatentBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.enums.fstu.RelTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.fstu.FstuPatentAuthorList;
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
 * 科研成果-我的专利
 */
@Controller
@RequestMapping("/fstu/aisr/patent")
public class FstuAisrPatentController extends GeneralController {
    @Autowired
    private IFstuPatentBiz patentBiz;
    @Autowired
    private IFstuPatentAuthorBiz patentAuthorBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IFstuAuditProcessBiz auditProcessBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IFstuFileBiz fstuFileBiz;
    /**
     * 加载科技报奖列表
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(FstuPatent patent, Integer currentPage, HttpServletRequest request, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        patent.setApplyUserFlow(currUser.getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<FstuPatent> patentList=patentBiz.search(patent, null);
        Map<String,List<FstuPatentAuthor>> allAuthorMap = new LinkedHashMap<String, List<FstuPatentAuthor>>();
        List<FstuPatentAuthor> allAuthorList  = patentAuthorBiz.searchAuthorList(new FstuPatentAuthor());
        for(FstuPatentAuthor a : allAuthorList){
            List<FstuPatentAuthor> list =  allAuthorMap.get(a.getPatentFlow());
            if(list == null){
                list = new ArrayList<FstuPatentAuthor>();
            }
            list.add(a);
            allAuthorMap.put(a.getPatentFlow(), list);
        }
        model.addAttribute("patentList",patentList);
        model.addAttribute("allAuthorMap", allAuthorMap);
        return "/fstu/aisr/patent/patentList";
    }
    /**
     * 编辑科技信息
     * @param patentFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/edit",method= RequestMethod.GET)
    public String edit(String patentFlow, Model model){
        PubFile file = null;
        List<PubFile> pubFileList = null;
        if(StringUtil.isNotBlank(patentFlow)){
            FstuPatent patent=patentBiz.readPatent(patentFlow);
            model.addAttribute("patent", patent);
            //查询科技作者信息
            FstuPatentAuthor author = new FstuPatentAuthor();
            author.setPatentFlow(patentFlow);
            List<FstuPatentAuthor> patentAuthorList=patentAuthorBiz.searchAuthorList(author);
            model.addAttribute("patentAuthorList",patentAuthorList);
            pubFileList = fileBiz.searchByProductFlow(patentFlow);
        }
        model.addAttribute("pubFileList", pubFileList);
        if(StringUtil.isBlank(patentFlow)){
            String changeMan = GlobalContext.getCurrentUser().getUserName();
            model.addAttribute("changeMan",changeMan);
        }
        return "/fstu/aisr/patent/edit";
    }

    //下载附件
    @RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
        PubFile file = this.fileBiz.readFile(fileFlow);
        patentBiz.down(file,response);
    }

    /**
     * 保存科研项目及作者
     * @return
     * @throws IOException
     */
    @RequestMapping(value={"/save"})
    @ResponseBody
    public String save(String jsondata, HttpServletRequest request) throws IOException{
        FstuPatentAuthorList aList = JSON.parseObject(jsondata, FstuPatentAuthorList.class);

        FstuPatent patent = aList.getPatent();
        List<FstuPatentAuthor> authorList = aList.getAuthorList();
        //-----枚举：根据相关的ID获得name-----
        patent.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(patent.getProjTypeId()));
        patent.setPatentLevelName(DictTypeEnum.ProLevel.getDictNameById(patent.getPatentLevelId()));
        patent.setPatentStatusName(DictTypeEnum.FstuProStatus.getDictNameById(patent.getPatentStatusId()));
//        patent.setAchTypeName(DictTypeEnum.PatentType.getDictNameById(patent.getAchTypeId()));
//        patent.setPrizedGradeName(DictTypeEnum.SatGrade.getDictNameById(patent.getPrizedGradeId()));
//        patent.setPrizedLevelName(DictTypeEnum.SatLevel.getDictNameById(patent.getPrizedLevelId()));
//        patent.setCategoryName(DictTypeEnum.SubjectCategories.getDictNameById(patent.getCategoryId()));
        patent.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(patent.getProjSourceId()));
        //所属单位
//        patent.setOrgBelongName(DictTypeEnum.SatOrg.getDictNameById(patent.getOrgBelongId()));
        //状态
        patent.setOperStatusId(ProStatusEnum.Draft.getId());
        patent.setOperStatusName(ProStatusEnum.Draft.getName());
        //获取申请单位信息
        SysUser currUser = GlobalContext.getCurrentUser();
        patent.setApplyOrgFlow(currUser.getOrgFlow());
        patent.setApplyOrgName(currUser.getOrgName());
        //申请人
        patent.setApplyUserFlow(currUser.getUserFlow());
        patent.setApplyUserName(currUser.getUserName());
        //科室信息
        patent.setDeptFlow(currUser.getDeptFlow());
        patent.setDeptName(currUser.getDeptName());

        //根据科技作者相关的ID枚举获得相对应的Name（性别、学历、职称）
        for(int i = 0; i<authorList.size();i++){
            authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
            authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
            authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
            authorList.get(i).setDegreeName(DictTypeEnum.UserDegree.getDictNameById(authorList.get(i).getDegreeId()));
        }
        //保存附件
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
                resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuImages", flow);
                pubFile.setFileName(fileList.get(i).getOriginalFilename());
                pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
                pubFile.setFileUpType("1");
            }
            pubFileList.add(pubFile);
        }
        /*//封装成果过程对象
        FstuAuditProcess fstuProcess = new FstuAuditProcess();
        fstuProcess.setRelTypeId(RelTypeEnum.Patent.getId());
        //        fstuProcess.setRelTypeName(RelTypeEnum.Book.getName());
        fstuProcess.setOperUserFlow(currUser.getUserFlow());
        fstuProcess.setOperUserName(currUser.getUserName());
        fstuProcess.setProStatusId(ProStatusEnum.Draft.getId());
        fstuProcess.setProStatusName(ProStatusEnum.Draft.getName());*/
        int reslut = patentBiz.save(patent, authorList, pubFileList, null);
        if(reslut == GlobalConstant.ONE_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;

    }

    @RequestMapping(value = "/deleteAuthor",method={RequestMethod.GET})
    @ResponseBody
    public  String deleteAuthor(String authorFlow){
        if(StringUtil.isNotBlank(authorFlow)){
            FstuPatentAuthor author = new FstuPatentAuthor();
            author.setAuthorFlow(authorFlow);
            patentAuthorBiz.deleteOneAuthor(author);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public String delete(String patentFlow){
        if(StringUtil.isNotBlank(patentFlow)){
            FstuPatent patent = new FstuPatent();
            patent.setPatentFlow(patentFlow);
            patent.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            FstuPatentAuthor author = new FstuPatentAuthor();
            author.setPatentFlow(patentFlow);
            List<FstuPatentAuthor> authorList = patentAuthorBiz.searchAuthorList(author);
            for(FstuPatentAuthor a : authorList){
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            //附件
            List<PubFile> fileList = fileBiz.searchByProductFlow(patentFlow);
            for(PubFile file:fileList){
                file.setRecordStatus("N");
            }
            int result = patentBiz.save(patent, authorList, fileList,null);
            if(result == GlobalConstant.ONE_LINE){
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value="/submitAudit",method={RequestMethod.GET})
    @ResponseBody
    public String submitAudit(String patentFlow,Model model){
        FstuPatent patent=patentBiz.readPatent(patentFlow);
        patent.setOperStatusId(ProStatusEnum.Apply.getId());
        patent.setOperStatusName(ProStatusEnum.Apply.getName());

        FstuAuditProcess process = new FstuAuditProcess();
        process.setRelTypeId(RelTypeEnum.Achieve.getId());
        process.setRelFlow(patent.getPatentFlow());
        process.setProcessFlow(PkUtil.getUUID());
        process.setProStatusId(ProStatusEnum.Apply.getId());
        process.setProStatusName(ProStatusEnum.Apply.getName());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        patentBiz.updatePatentStatus(patent, process);
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value="/auditList/{role}",method={RequestMethod.POST,RequestMethod.GET})
    public String auditList(@PathVariable String role, Integer currentPage, FstuPatent patent, FstuPatentAuthor author, SysOrg org, Model model, HttpServletRequest request){
        PageHelper.startPage(currentPage, getPageSize(request));
        model.addAttribute("role",role);
        SysUser currUser = GlobalContext.getCurrentUser();
        if("adminAudit".equals(role)) {
            if (StringUtil.isBlank(patent.getOperStatusId())) {
                patent.setOperStatusId(ProStatusEnum.Apply.getId());
            }
        }
        if("admin".equals(role)) {
            if (StringUtil.isBlank(patent.getOperStatusId())) {
                patent.setOperStatusId("all");
            }
        }
        List<FstuPatent> patents = new ArrayList<>();
        List<FstuPatent> patentList = new ArrayList<>();
        if("deptMaster".equals(role)){
            //科主任角色查询
            String userFlow = currUser.getUserFlow();
            String dept = currUser.getDeptFlow();
            List<String> deptFlowList = new ArrayList<>();
            if(StringUtil.isNotBlank(dept)){
                deptFlowList.add(dept);
            }
            List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
            if(userDepts!=null&&userDepts.size()>0){
                for(SysUserDept userDept:userDepts){
                    String deptFlow = userDept.getDeptFlow();
                    deptFlowList.add(deptFlow);
                }
            }
            if(deptFlowList.size()==0){
                PageHelper.startPage(currentPage, getPageSize(request));
                patent.setApplyUserFlow(userFlow);
                patents = patentBiz.search(patent,null);
            }else {
                PageHelper.startPage(currentPage, getPageSize(request));
                patents = patentBiz.search(patent, deptFlowList);
            }
        }else{
            PageHelper.startPage(currentPage, getPageSize(request));
            patents = patentBiz.search(patent,null);
        }

        Map<String, List<FstuPatentAuthor>> allAuthorMap = new HashMap<String, List<FstuPatentAuthor>>();
        List<FstuPatentAuthor> patentAuthorList = patentAuthorBiz.searchAuthorList(new FstuPatentAuthor());
        if(patentAuthorList != null && !patentAuthorList.isEmpty()){
            for(FstuPatentAuthor a : patentAuthorList){
                List<FstuPatentAuthor> authorList = allAuthorMap.get(a.getPatentFlow());
                if(authorList == null){
                    authorList = new ArrayList<FstuPatentAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getPatentFlow(), authorList);
            }
        }
        //作者查询
        String pageFlag = "Y";
        if(StringUtil.isNotBlank(author.getAuthorName())){
            pageFlag = "N";
            for(FstuPatent s:patents){
                boolean addFlag = false;
                List<FstuPatentAuthor> authorByNameList = allAuthorMap.get(s.getPatentFlow());
                if(authorByNameList != null){
                    for(FstuPatentAuthor na : authorByNameList){
                        if(na.getAuthorName().equals(author.getAuthorName())){
                            addFlag = true;
                            break;
                        }
                    }
                }
                if(addFlag){
                    patentList.add(s);
                }
            }
        }else {
            patentList = patents;
        }
        model.addAttribute("pageFlag",pageFlag);
        model.addAttribute("patentList",patentList);
        model.addAttribute("allAuthorMap", allAuthorMap);
        return "fstu/aisr/patent/auditList";
    }

    /**
     * 跳转到审核界面
     * @param patentFlow
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    public String audit(String patentFlow, Model model){
        //根据成果流水号查询相关信息，用于加载审核页面
        List<PubFile> fileList=null;
        if(StringUtil.isNotBlank(patentFlow)){
            //查询成果本身
            FstuPatent patent=patentBiz.readPatent(patentFlow);
            model.addAttribute("patent", patent);
            //查询成果作者
            FstuPatentAuthor author = new FstuPatentAuthor();
            author.setPatentFlow(patentFlow);
            List<FstuPatentAuthor> patentAuthorList=patentAuthorBiz.searchAuthorList(author);
            model.addAttribute("patentAuthorList", patentAuthorList);
            //查询成果附件
            fileList=fileBiz.searchByProductFlow(patentFlow);
            if(fileList!=null && !fileList.isEmpty()){
                model.addAttribute("pubFileList", fileList);
            }
        }
        FstuPatent patent=patentBiz.readPatent(patentFlow);
        model.addAttribute("patent", patent);
        return "fstu/aisr/patent/audit";
    }

    /**
     * 保存审核结果
     * @param agreeFlag
     * @param auditContent
     * @param patentFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/saveAudit",method={RequestMethod.POST})
    @ResponseBody
    public String saveAudit(String agreeFlag,String auditContent,Model model,FstuPatent patent){
        FstuAuditProcess process=auditProcessBiz.search(patent.getPatentFlow()).get(0);

        //-----枚举：根据相关的ID获得name-----
        patent.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(patent.getProjTypeId()));
        patent.setPatentLevelName(DictTypeEnum.ProLevel.getDictNameById(patent.getPatentLevelId()));
        patent.setPatentStatusName(DictTypeEnum.FstuProStatus.getDictNameById(patent.getPatentStatusId()));
        patent.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(patent.getProjSourceId()));
        if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
            patent.setOperStatusId(ProStatusEnum.Passed.getId());
            patent.setOperStatusName(ProStatusEnum.Passed.getName());
            process.setProStatusId(ProStatusEnum.Passed.getId());
            process.setProStatusName(ProStatusEnum.Passed.getName());
        }
        else{
            patent.setOperStatusId(ProStatusEnum.UnPassed.getId());
            patent.setOperStatusName(ProStatusEnum.UnPassed.getName());
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
        patent.setLastAuditAdvice(auditContent);
        patent.setLastAuditTime(currTime);
        patentBiz.updatePatentStatus(patent, process);
        return GlobalConstant.OPRE_SUCCESSED;
    }

    /**
     * 审核记录查看
     * @return
     */
    @RequestMapping("/auditProcess")
    public String auditProcess(String flow , Model model){
        List<FstuAuditProcess> processList = this.auditProcessBiz.search(flow);
        model.addAttribute("processList" , processList);
        return "fstu/aisr/patent/auditProcess";
    }
}
