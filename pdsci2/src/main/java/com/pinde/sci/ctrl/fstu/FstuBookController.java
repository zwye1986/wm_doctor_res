package com.pinde.sci.ctrl.fstu;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.enums.fstu.RelTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.fstu.FstuBookAuthorList;
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
@RequestMapping("/fstu/book")
public class FstuBookController extends GeneralController {
    @Autowired
    private IFstuBookBiz bookBiz;
    @Autowired
    private IFstuBookAuthorBiz bookAuthorBiz;
    @Autowired
    private IFstuAuditProcessBiz processBiz;
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
     * 我的著作列表
     * @param currentPage
     * @param book
     * @param author
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Integer currentPage, FstuBook book, FstuBookAuthor author, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        book.setApplyUserFlow(currUser.getUserFlow());

        PageHelper.startPage(currentPage, getPageSize(request));
        List<FstuBook> achBookList = bookBiz.search(book, null);

        //组织关联作者的Map
        Map<String, List<FstuBookAuthor>> allAuthorMap = new LinkedHashMap<String, List<FstuBookAuthor>>();
        List<FstuBookAuthor> allBookAuthorList = bookAuthorBiz.searchAuthorList(new FstuBookAuthor());
        if (allBookAuthorList != null && !allBookAuthorList.isEmpty()) {
            for (FstuBookAuthor a : allBookAuthorList) {
                List<FstuBookAuthor> list = allAuthorMap.get(a.getBookFlow());
                if (list == null) {
                    list = new ArrayList<FstuBookAuthor>();
                }
                list.add(a);
                allAuthorMap.put(a.getBookFlow(), list);
            }
        }
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("achBookList", achBookList);
        return "fstu/aisr/book/bookList";
    }

    /**
     * 跳转到新增或修改
     *
     * @param bookFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(String bookFlow, Model model) {
        PubFile file = null;
        List<PubFile> fileList=null;
        //作者信息显示
        if (StringUtil.isNotBlank(bookFlow)) {
            FstuBook book = bookBiz.readBook(bookFlow);
            model.addAttribute("book", book);

            FstuBookAuthor author = new FstuBookAuthor();
            author.setBookFlow(bookFlow);
            List<FstuBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(author);
            model.addAttribute("bookAuthorList", bookAuthorList);

            fileList = fileBiz.searchByProductFlow(bookFlow);
            model.addAttribute("fileList", fileList);
        }
        return "fstu/aisr/book/editBook";
    }

    /**
     * 保存著作
     * @param jsondata
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/save"})
    @ResponseBody
    public String saveBookAndAuthor(String jsondata, @RequestParam(value="file" , required=false)MultipartFile file, HttpServletRequest request) throws IOException {
        FstuBookAuthorList aList = JSON.parseObject(jsondata, FstuBookAuthorList.class);
        List<FstuBookAuthor> authorList = aList.getAuthorList();
        FstuBook book = aList.getBook();

        book.setTypeName(DictTypeEnum.FstuBookType.getDictNameById(book.getTypeId()));
//        //学科门类
        book.setCategoryName(DictTypeEnum.FstuSubjectType.getDictNameById(book.getCategoryId()));
        book.setPressLevelName(DictTypeEnum.FstuPressLevelType.getDictNameById(book.getPressLevelId()));
        book.setPubPlaceName(DictTypeEnum.FstuPlaceNameType.getDictNameById(book.getPubPlaceId()));
        book.setOrgBelongName(DictTypeEnum.SatOrg.getDictNameById(book.getOrgBelongId()));
        book.setLanguageTypeName(DictTypeEnum.FstuLanguageType.getDictNameById(book.getLanguageTypeId()));
        book.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(book.getProjSourceId()));

        book.setOperStatusId(ProStatusEnum.Draft.getId());
        book.setOperStatusName(ProStatusEnum.Draft.getName());
        //获取申请单位信息
        SysUser currUser = GlobalContext.getCurrentUser();
        book.setApplyOrgFlow(currUser.getOrgFlow());
        book.setApplyOrgName(currUser.getOrgName());
        //申请人信息
        book.setApplyUserFlow(currUser.getUserFlow());
        book.setApplyUserName(currUser.getUserName());
        book.setDeptFlow(currUser.getDeptFlow());
        book.setDeptName(currUser.getDeptName());

        //根据【著作作者】的相关ID枚举获得Name
        for (int i = 0; i < authorList.size(); i++) {
            authorList.get(i).setWriteTypeName(DictTypeEnum.FstuWriteNameType.getDictNameById(authorList.get(i).getWriteTypeId()));
            authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
            authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
            authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
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
                resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuBook", flow);
                pubFile.setFileName(fileList.get(i).getOriginalFilename());
                pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
                pubFile.setFileUpType("1");
            }
            pubFileList.add(pubFile);
        }
        //封装成果过程对象
        FstuAuditProcess fstuProcess = new FstuAuditProcess();
        fstuProcess.setRelTypeId(RelTypeEnum.Book.getId());
//        fstuProcess.setRelTypeName(RelTypeEnum.Book.getName());
        fstuProcess.setOperUserFlow(currUser.getUserFlow());
        fstuProcess.setOperUserName(currUser.getUserName());
        fstuProcess.setProStatusId(ProStatusEnum.Draft.getId());
        fstuProcess.setProStatusName(ProStatusEnum.Draft.getName());
        bookBiz.save(book, authorList,fstuProcess,pubFileList);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    //下载附件
    @RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
        PubFile file = this.fileBiz.readFile(fileFlow);
        fstuFileBiz.down(file,response);
    }

    /**
     * 删除作者
     * @param authorFlow
     * @return
     */
    @RequestMapping(value = "deleteAuthor", method = {RequestMethod.GET})
    @ResponseBody
    public String deleteAuthor(String authorFlow) {
        if (StringUtil.isNotBlank(authorFlow)) {
            FstuBookAuthor author = new FstuBookAuthor();
            author.setAuthorFlow(authorFlow);
            author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            bookAuthorBiz.editBookAthor(author);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 删除一条著作信息
     * @param bookFlow
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String bookFlow) {
        if (StringUtil.isNotBlank(bookFlow)) {
            FstuBook book = new FstuBook();
            book.setBookFlow(bookFlow);
            book.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            //作者
            FstuBookAuthor author = new FstuBookAuthor();
            author.setBookFlow(bookFlow);
            List<FstuBookAuthor> authorList = bookAuthorBiz.searchAuthorList(author);
            for (FstuBookAuthor a : authorList) {
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            //附件
            PubFile file = null;
            List<PubFile> fileList = fileBiz.searchByProductFlow(bookFlow);
            if(fileList != null && !fileList.isEmpty()){
                file = fileList.get(0);
                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            int result = bookBiz.edit(book, authorList,file);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 审核（通过和退回）
     * @param agreeFlag
     * @param auditContent
     * @param bookFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@RequestParam(value = "agreeFlag", required = true) String agreeFlag, String auditContent,
                            FstuBook book, Model model) {
        FstuAuditProcess process = processBiz.searchFsaProcess(book.getBookFlow(), ProStatusEnum.Apply.getId()).get(0);
//        //学科门类
        book.setCategoryName(DictTypeEnum.FstuSubjectType.getDictNameById(book.getCategoryId()));
        book.setPressLevelName(DictTypeEnum.FstuPressLevelType.getDictNameById(book.getPressLevelId()));
        book.setPubPlaceName(DictTypeEnum.FstuPlaceNameType.getDictNameById(book.getPubPlaceId()));
        book.setOrgBelongName(DictTypeEnum.SatOrg.getDictNameById(book.getOrgBelongId()));
        book.setLanguageTypeName(DictTypeEnum.FstuLanguageType.getDictNameById(book.getLanguageTypeId()));
        book.setProjSourceName(DictTypeEnum.ProSource.getDictNameById(book.getProjSourceId()));
        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            book.setOperStatusId(ProStatusEnum.Passed.getId());
            book.setOperStatusName(ProStatusEnum.Passed.getName());
            process.setProStatusId(ProStatusEnum.Passed.getId());
            process.setProStatusName(ProStatusEnum.Passed.getName());
        } else {
            book.setOperStatusId(ProStatusEnum.UnPassed.getId());
            book.setOperStatusName(ProStatusEnum.UnPassed.getName());
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
        book.setLastAuditAdvice(auditContent);
        book.setLastAuditTime(process.getCreateTime());
        bookBiz.updateBookStatus(book, process);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 提交审核
     * @param bookFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value = "bookFlow", required = true) String bookFlow, Model model) {
        FstuBook book = bookBiz.readBook(bookFlow);
        book.setOperStatusId(ProStatusEnum.Apply.getId());
        book.setOperStatusName(ProStatusEnum.Apply.getName());

        FstuAuditProcess process = processBiz.searchFsaProcess(bookFlow, ProStatusEnum.Draft.getId()).get(0);
        process.setProcessFlow(PkUtil.getUUID());
        process.setProStatusId(ProStatusEnum.Apply.getId());
        process.setProStatusName(ProStatusEnum.Apply.getName());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperUserFlow(currUser.getUserFlow());
        process.setOperUserName(currUser.getUserName());
        bookBiz.updateBookStatus(book, process);

        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 审核列表
     * @param scope
     * @param currentPage
     * @param book
     * @param author
     * @param org
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/auditList/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String scope, Integer currentPage, FstuBook book, FstuBookAuthor author, SysOrg org, Model model, HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        SysUser currUser = GlobalContext.getCurrentUser();
        List<FstuBook> searchList = new ArrayList<>();
        List<FstuBook> bookList = new ArrayList<>();
        if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)) {
            book.setOperStatusId(ProStatusEnum.Passed.getId());
        }else if (GlobalConstant.PROJ_STATUS_SCOPE_LOCALSCH.equals(scope)||"deptSch".equals(scope)) {
            if(StringUtil.isBlank(book.getOperStatusId())||GlobalConstant.FLAG_Y.equals(book.getOperStatusId())){
                book.setOperStatusId(ProStatusEnum.Apply.getId()+","+ProStatusEnum.Passed.getId());
            }
        } else {
            if (StringUtil.isBlank(book.getOperStatusId())) {
                book.setOperStatusId(ProStatusEnum.Apply.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(book.getOperStatusId())) {
                book.setOperStatusId(ProStatusEnum.Apply.getId() + "," + ProStatusEnum.Passed.getId());
            }
        }
        Map<String, List<FstuBookAuthor>> allAuthorMap = new HashMap<String, List<FstuBookAuthor>>();
        List<FstuBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(new FstuBookAuthor());
        if (bookAuthorList != null && !bookAuthorList.isEmpty()) {
            for (FstuBookAuthor a : bookAuthorList) {
                List<FstuBookAuthor> authorList = allAuthorMap.get(a.getBookFlow());
                if (authorList == null) {
                    authorList = new ArrayList<FstuBookAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getBookFlow(), authorList);
            }
        }
        String pageFlag = "Y";
        if("deptSch".equals(scope)){
            //科主任角色查询著作列表
            String userFlow = currUser.getUserFlow();
            String dept = currUser.getDeptFlow();
            List<String> deptFlowList = new ArrayList<>();
            if(StringUtil.isBlank(dept)){
                deptFlowList =null;
                book.setApplyUserFlow(currUser.getUserFlow());
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
            if (StringUtil.isNotBlank(author.getAuthorName())) {
                pageFlag = "N";
                bookList = new ArrayList<FstuBook>();
                searchList = bookBiz.searchByDeptFlow(book,deptFlowList);
                for (FstuBook b : searchList) {
                    boolean addFlag = false;
                    List<FstuBookAuthor> authorByNameList = allAuthorMap.get(b.getBookFlow());
                    if (authorByNameList != null) {
                        for (FstuBookAuthor na : authorByNameList) {
                            if (na.getAuthorName().equals(author.getAuthorName())) {
                                addFlag = true;
                                break;
                            }
                        }
                    }
                    if (addFlag) {
                        bookList.add(b);
                    }
                }
            } else {
                PageHelper.startPage(currentPage, getPageSize(request));
                searchList = bookBiz.searchByDeptFlow(book,deptFlowList);
                bookList = searchList;
            }

        }else{
            if (StringUtil.isNotBlank(author.getAuthorName())) {
                pageFlag = "N";
                bookList = new ArrayList<FstuBook>();
                searchList = bookBiz.searchByDeptFlow(book,null);
                for (FstuBook b : searchList) {
                    boolean addFlag = false;
                    List<FstuBookAuthor> authorByNameList = allAuthorMap.get(b.getBookFlow());
                    if (authorByNameList != null) {
                        for (FstuBookAuthor na : authorByNameList) {
                            if (na.getAuthorName().equals(author.getAuthorName())) {
                                addFlag = true;
                                break;
                            }
                        }
                    }
                    if (addFlag) {
                        bookList.add(b);
                    }
                }
            } else {
                PageHelper.startPage(currentPage, getPageSize(request));
                searchList = bookBiz.searchByDeptFlow(book,null);
                bookList = searchList;
            }

        }

        //过滤

        model.addAttribute("pageFlag",pageFlag);
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("bookList", bookList);
        return "fstu/aisr/book/auditList" + scope;
    }

    /**
     * 加载审核页面
     * @param bookFlow
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    public String audit(@RequestParam(value = "bookFlow", required = true) String bookFlow, Model model) {
        //根据成果流水号查询相关信息，用于加载审核页面
        List<PubFile> fileList=null;
        if (StringUtil.isNotBlank(bookFlow)) {
            //查询成果本身
            FstuBook book = bookBiz.readBook(bookFlow);
            model.addAttribute("book", book);
            //查询成果作者
            FstuBookAuthor author = new FstuBookAuthor();
            author.setBookFlow(bookFlow);
            List<FstuBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(author);
            model.addAttribute("bookAuthorList", bookAuthorList);
            //查询成果附件
            fileList=fileBiz.searchByProductFlow(bookFlow);
            if(fileList!=null && !fileList.isEmpty()){
                model.addAttribute("fileList", fileList);
            }
        }
        return "fstu/aisr/book/audit";
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
        achProcess.setRelTypeId(RelTypeEnum.Book.getId());
        List<FstuAuditProcess> achProcessList = this.thesisProcessBiz.searchAchProcess(achProcess);
        model.addAttribute("achProcessList" , achProcessList);
        return "fstu/aisr/book/processRecordList";
    }

}
