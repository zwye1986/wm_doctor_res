package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuFileBiz;
import com.pinde.sci.biz.fstu.IFstuThesisAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuThesisBiz;
import com.pinde.sci.biz.fstu.IFstuThesisProcessBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.enums.fstu.RelTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.fstu.FstuThesisAuthorList;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/fstu/thesis")
public class FstuThesisController extends GeneralController {
    @Autowired
    private IFstuThesisBiz thesisBiz;
    @Autowired
    private IFstuThesisAuthorBiz authorBiz;
    @Autowired
    private IFstuThesisProcessBiz thesisProcessBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IFstuFileBiz fstuFileBiz;
    @Autowired
    IFileBiz fileBiz;
    /**
     * 我的论文列表展示
     * @param currentPage
     * @param thesis
     * @param author
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(Integer currentPage, FstuThesis thesis, FstuThesisAuthor author, HttpServletRequest request, Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
        thesis.setApplyUserFlow(currUser.getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<FstuThesis> thesisList =thesisBiz.search(thesis, null);

        //组织关联作者的Map
        Map<String,List<FstuThesisAuthor>> allAuthorMap = new LinkedHashMap<String, List<FstuThesisAuthor>>();
        List<FstuThesisAuthor> allThesisAuthorList = authorBiz.searchAuthorList(new FstuThesisAuthor());
        if(allThesisAuthorList != null && !allThesisAuthorList.isEmpty()){
            for(FstuThesisAuthor a : allThesisAuthorList){
                List<FstuThesisAuthor> list = allAuthorMap.get(a.getThesisFlow());
                if(list == null){
                    list = new ArrayList<FstuThesisAuthor>();
                }
                list.add(a);
                allAuthorMap.put(a.getThesisFlow(),list);
            }
        }
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("thesisList", thesisList);
        return "fstu/aisr/lecture/myThesisList";
    }

    /**
     * 打开编辑页面并且获取一条论文记录的详细信息
     * @param thesisFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit",method={RequestMethod.GET})
    public String edit(String thesisFlow, Model model)
    {
        PubFile file = null;
        List<PubFile> fileList=null;
        FstuThesis thesis=thesisBiz.readThesis(thesisFlow);
        model.addAttribute("thesis",thesis);
        //根据论文流水号查询其作者
        if(StringUtil.isNotBlank(thesisFlow)){
            FstuThesisAuthor author = new FstuThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<FstuThesisAuthor> authorList=authorBiz.searchAuthorList(author);
            model.addAttribute("authorList", authorList);

            fileList = fileBiz.searchByProductFlow(thesisFlow);

            model.addAttribute("fileList", fileList);
        }
        return "/fstu/aisr/lecture/editThesis";
    }

    /**
     * 保存论文
     * @param
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value={"/save"})
    @ResponseBody
    public  String save(String jsondata , @RequestParam(value="file" , required=false)MultipartFile file, HttpServletRequest request) throws IOException{
        FstuThesisAuthorList aList = JSON.parseObject(jsondata, FstuThesisAuthorList.class);
        List<FstuThesisAuthor> authorList=aList.getAuthorList();
         FstuThesis thesis=aList.getThesis();
        //枚举：根据论文相关ID枚举获得name
        thesis.setTypeName(DictTypeEnum.FstuThesisType.getDictNameById(thesis.getTypeId()));
        thesis.setHospSignName(DictTypeEnum.SatOrg.getDictNameById(thesis.getHospSignId()));
        thesis.setPublishTypeName(DictTypeEnum.FstuPublishType.getDictNameById(thesis.getPublishTypeId()));
//        thesis.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(thesis.getProjTypeId()));
        thesis.setSubjectTypeName(DictTypeEnum.FstuSubjectType.getDictNameById(thesis.getSubjectTypeId()));
        thesis.setPublishScopeName(DictTypeEnum.FstuPublishScope.getDictNameById(thesis.getPublishScopeId()));
        thesis.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(thesis.getProjSourceId()));
        thesis.setMeetingName(DictTypeEnum.FstuMeetingType.getDictNameById(thesis.getMeetingId()));

        SysUser currUser = GlobalContext.getCurrentUser();
        thesis.setApplyUserFlow(currUser.getUserFlow());
        thesis.setApplyUserName(currUser.getUserName());
        thesis.setApplyOrgFlow(currUser.getOrgFlow());
        thesis.setApplyOrgName(currUser.getOrgName());
        //科室信息
        thesis.setDeptFlow(currUser.getDeptFlow());
        thesis.setDeptName(currUser.getDeptName());

        thesis.setOperStatusId(ProStatusEnum.Draft.getId());
        thesis.setOperStatusName(ProStatusEnum.Draft.getName());
        //根据刊物类型ID分解成数组,并枚举获得刊物类型name
        if(StringUtil.isNotBlank(thesis.getJourTypeId())){
            String [] jti=thesis.getJourTypeId().split(",");
            StringBuffer jtn=new StringBuffer();
            for(int i=0;i<jti.length;i++){
                jtn.append(DictTypeEnum.FstuJournalType.getDictNameById(jti[i])+",");
            }
            jtn.delete(jtn.length()-1, jtn.length());
            thesis.setJourTypeName(jtn.toString());
        }
        //枚举：根据论文作者相关ID枚举获得name
        for(int i=0;i<authorList.size();i++){
            authorList.get(i).setTypeName(DictTypeEnum.FstuAuthorType.getDictNameById(authorList.get(i).getTypeId()));
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
            PubFile pubFile = aList.getFileList().get(i);
            if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
                //保存附件
                String flow = pubFile.getFileFlow();
                if (StringUtil.isNotBlank(flow)) {
                    pubFile.setFileFlow(flow);
                }
                resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuThesis", flow);
                pubFile.setFileName(fileList.get(i).getOriginalFilename());
                pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
                pubFile.setFileUpType("1");
            }
            pubFileList.add(pubFile);
        }
        //封装成果过程对象
        FstuAuditProcess fstuAuditProcess=new FstuAuditProcess();
        fstuAuditProcess.setRelTypeId(RelTypeEnum.Lecture.getId());
//        fstuAuditProcess.set(RelTypeEnum.Lecture..getName());
        fstuAuditProcess.setOperUserFlow(currUser.getUserFlow());
        fstuAuditProcess.setOperUserName(currUser.getUserName());
        fstuAuditProcess.setProStatusId(ProStatusEnum.Draft.getId());
        fstuAuditProcess.setProStatusName(ProStatusEnum.Draft.getName());
        thesisBiz.save(thesis, authorList,fstuAuditProcess,pubFileList);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    //下载附件
    @RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
        PubFile file = this.fileBiz.readFile(fileFlow);
        fstuFileBiz.down(file,response);
    }
    /**
     * 删除一条论文记录
     * @param thesisFlow
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    @ResponseBody
    public String delete(String thesisFlow) {
        if (StringUtil.isNotBlank(thesisFlow)) {
            FstuThesis thesis = new FstuThesis();
            thesis.setThesisFlow(thesisFlow);
            thesis.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            FstuThesisAuthor author = new FstuThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<FstuThesisAuthor> authorList = authorBiz.searchAuthorList(author);
            for (FstuThesisAuthor a : authorList) {
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            //成果文件
            PubFile file = null;
            List<PubFile> fileList = fileBiz.searchByProductFlow(thesisFlow);
            if(fileList != null && !fileList.isEmpty()){
                file = fileList.get(0);
                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            int result = thesisBiz.edit(thesis, authorList,file);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }
    /**
     * 审核记录查看
     * @return
     */
    @RequestMapping("/auditProcess")
    public String auditProcess(String flow , Model model){
        FstuAuditProcess achProcess = new FstuAuditProcess();
        achProcess.setRelFlow(flow);
        achProcess.setRecordStatus(GlobalConstant.FLAG_Y);
        achProcess.setRelTypeId(RelTypeEnum.Lecture.getId());
        List<FstuAuditProcess> achProcessList = this.thesisProcessBiz.searchAchProcess(achProcess);
        model.addAttribute("achProcessList" , achProcessList);
        return "fstu/aisr/lecture/processRecordList";
    }

    @RequestMapping(value="/submitAudit",method={RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value="thesisFlow" , required=true)String thesisFlow, Model model){
        if(StringUtil.isNotBlank(thesisFlow)){
            FstuThesis thesis=thesisBiz.readThesis(thesisFlow);
            thesis.setOperStatusId(ProStatusEnum.Apply.getId());
            thesis.setOperStatusName(ProStatusEnum.Apply.getName());

            FstuAuditProcess process=thesisProcessBiz.searchAchProcess(thesisFlow, ProStatusEnum.Draft.getId()).get(0);
            process.setProcessFlow(PkUtil.getUUID());
            process.setProStatusId(ProStatusEnum.Apply.getId());
            process.setProStatusName(ProStatusEnum.Apply.getName());
            GeneralMethod.setRecordInfo(process, true);
            process.setOperTime(process.getCreateTime());
            SysUser currUser = GlobalContext.getCurrentUser();
            process.setOperUserFlow(currUser.getUserFlow());
            process.setOperUserName(currUser.getUserName());
            process.setOperOrgFlow(currUser.getOrgFlow());
            process.setOperOrgName(currUser.getOrgName());
            thesisBiz.updateThesisStatus(thesis,process);
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL;
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
     * 论文审核
     * @param agreeFlag
     * @param auditContent
     * @param thesisFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/saveAudit",method={RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@RequestParam(value="agreeFlag" , required=true)String agreeFlag,String auditContent,
                            FstuThesis thesis,Model model){
        FstuAuditProcess process=thesisProcessBiz.searchAchProcess(thesis.getThesisFlow(), ProStatusEnum.Apply.getId()).get(0);
//枚举：根据论文相关ID枚举获得name
        thesis.setTypeName(DictTypeEnum.FstuThesisType.getDictNameById(thesis.getTypeId()));
        thesis.setHospSignName(DictTypeEnum.SatOrg.getDictNameById(thesis.getHospSignId()));
        thesis.setPublishTypeName(DictTypeEnum.FstuPublishType.getDictNameById(thesis.getPublishTypeId()));
//        thesis.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(thesis.getProjTypeId()));
        thesis.setSubjectTypeName(DictTypeEnum.FstuSubjectType.getDictNameById(thesis.getSubjectTypeId()));
        thesis.setPublishScopeName(DictTypeEnum.FstuPublishScope.getDictNameById(thesis.getPublishScopeId()));
        thesis.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(thesis.getProjSourceId()));
        thesis.setMeetingName(DictTypeEnum.FstuMeetingType.getDictNameById(thesis.getMeetingId()));
        if(agreeFlag.equals(GlobalConstant.FLAG_Y)){
            thesis.setOperStatusId(ProStatusEnum.Passed.getId());
            thesis.setOperStatusName(ProStatusEnum.Passed.getName());
            process.setProStatusId(ProStatusEnum.Passed.getId());
            process.setProStatusName(ProStatusEnum.Passed.getName());
        }else{
            thesis.setOperStatusId(ProStatusEnum.UnPassed.getId());
            thesis.setOperStatusName(ProStatusEnum.UnPassed.getName());
            process.setProStatusId(ProStatusEnum.UnPassed.getId());
            process.setProStatusName(ProStatusEnum.UnPassed.getName());
        }
        process.setProcessFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        process.setAuditContent(auditContent);
        //最后审核说明和最后审核时间
        thesis.setLastAuditAdvice(auditContent);
        thesis.setLastAuditTime(process.getCreateTime());
        thesisBiz.updateThesisStatus(thesis,process);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 删除作者
     * @param authorFlow
     * @return
     */
    @RequestMapping(value = "/deleteAuthor",method={RequestMethod.GET})
    @ResponseBody
    public  String deleteAuthor(String authorFlow){
        if(StringUtil.isNotBlank(authorFlow)){
            FstuThesisAuthor author = new FstuThesisAuthor();
            author.setAuthorFlow(authorFlow);
            author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result =  authorBiz.editAuthor(author);
            if(result == GlobalConstant.ONE_LINE){
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 加载审核页面
     * @param thesisFlow
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    public String audit(@RequestParam(value="thesisFlow" , required=true)String thesisFlow,Model model){
        //根据成果流水号查询相关信息，用于加载审核页面
        PubFile file = null;
        List<PubFile> fileList=null;
        if(StringUtil.isNotBlank(thesisFlow)){
            //查询成果本身
            FstuThesis thesis=thesisBiz.readThesis(thesisFlow);
            model.addAttribute("thesis",thesis);
            //查询成果作者
            FstuThesisAuthor author = new FstuThesisAuthor();
            author.setThesisFlow(thesisFlow);
            List<FstuThesisAuthor> authorList=authorBiz.searchAuthorList(author);
            model.addAttribute("authorList", authorList);
            //查询成果附件
            fileList=fileBiz.searchByProductFlow(thesisFlow);
            if(fileList!=null && !fileList.isEmpty()){
                model.addAttribute("fileList", fileList);
            }
        }
        return "fstu/aisr/lecture/examineThesis";
    }

    /**
     * 加载论文审核列表
     * @param scope
     * @param currentPage
     * @param thesis
     * @param author
     * @param org
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/auditList/{scope}",method={RequestMethod.POST,RequestMethod.GET})
    public String auditList(@PathVariable String scope, Integer currentPage, FstuThesis thesis, FstuThesisAuthor author, SysOrg org, Model model, HttpServletRequest request){
//        PageHelper.startPage(currentPage, getPageSize(request));
        SysUser currUser = GlobalContext.getCurrentUser();
        List<FstuThesis> searchList=new ArrayList<>();
        List<FstuThesis> thesisList=null;
        if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
            thesis.setOperStatusId(ProStatusEnum.Passed.getId());
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCALSCH.equals(scope) || "deptSch".equals(scope)){
            if(StringUtil.isBlank(thesis.getOperStatusId())||GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())){
                thesis.setOperStatusId(ProStatusEnum.Apply.getId()+","+ProStatusEnum.Passed.getId());
            }
        }else{
            if(StringUtil.isBlank(thesis.getOperStatusId())){
                thesis.setOperStatusId(ProStatusEnum.Apply.getId());
            }
            else if(GlobalConstant.FLAG_Y.equals(thesis.getOperStatusId())){
                thesis.setOperStatusId(ProStatusEnum.Apply.getId()+","+ProStatusEnum.Passed.getId());
            }
        }
        Map<String, List<FstuThesisAuthor>> allAuthorMap = new HashMap<String, List<FstuThesisAuthor>>();
        List<FstuThesisAuthor> thesisAuthorList = authorBiz.searchAuthorList(new FstuThesisAuthor());
        if(thesisAuthorList != null && !thesisAuthorList.isEmpty()){
            for(FstuThesisAuthor a : thesisAuthorList){
                List<FstuThesisAuthor> authorList = allAuthorMap.get(a.getThesisFlow());
                if(authorList == null){
                    authorList = new ArrayList<FstuThesisAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getThesisFlow(), authorList);
            }
        }
        String pageFlag = "Y";
        if("deptSch".equals(scope)){
            //科主任角色查询论文列表
            String userFlow = currUser.getUserFlow();
            String dept = currUser.getDeptFlow();
            List<String> deptFlowList = new ArrayList<>();
            if(StringUtil.isBlank(dept)){
                thesis.setApplyUserFlow(currUser.getUserFlow());
                deptFlowList = null;
            }else{
                deptFlowList.add(dept);
                List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
                if(userDepts!=null&&userDepts.size()>0){
                    for(SysUserDept userDept:userDepts){
                        String deptFlow = userDept.getDeptFlow();
                        deptFlowList.add(deptFlow);
                    }
                }
            }
            if(StringUtil.isNotBlank(author.getAuthorName())){
                pageFlag = "N";
                thesisList = new ArrayList<FstuThesis>();
                searchList = thesisBiz.searchByDeptFlow(thesis,deptFlowList);
                for(FstuThesis b : searchList){
                    boolean addFlag = false;
                    List<FstuThesisAuthor> authorByNameList = allAuthorMap.get(b.getThesisFlow());
                    if(authorByNameList != null){
                        for(FstuThesisAuthor na : authorByNameList){
                            if(na.getAuthorName().equals(author.getAuthorName())){
                                addFlag = true;
                                break;
                            }
                        }
                    }
                    if(addFlag){
                        thesisList.add(b);
                    }
                }
            }else{
                PageHelper.startPage(currentPage, getPageSize(request));
                searchList = thesisBiz.searchByDeptFlow(thesis,deptFlowList);
                thesisList = searchList;
            }

        }else{
            if(StringUtil.isNotBlank(author.getAuthorName())){
                pageFlag = "N";
                thesisList = new ArrayList<FstuThesis>();
                searchList = thesisBiz.searchByDeptFlow(thesis,null);
                for(FstuThesis b : searchList){
                    boolean addFlag = false;
                    List<FstuThesisAuthor> authorByNameList = allAuthorMap.get(b.getThesisFlow());
                    if(authorByNameList != null){
                        for(FstuThesisAuthor na : authorByNameList){
                            if(na.getAuthorName().equals(author.getAuthorName())){
                                addFlag = true;
                                break;
                            }
                        }
                    }
                    if(addFlag){
                        thesisList.add(b);
                    }
                }
            }else{
                PageHelper.startPage(currentPage, getPageSize(request));
                searchList = thesisBiz.searchByDeptFlow(thesis,null);
                thesisList = searchList;
            }
        }

        //过滤论文流水号

        model.addAttribute("pageFlag",pageFlag);
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("thesisList", thesisList);
        return "fstu/aisr/lecture/examineThesisList"+scope;
    }
}
