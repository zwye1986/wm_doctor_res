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
import com.pinde.sci.form.ach.AchBookExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmAchBookAuthorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/srm/ach/book")
public class AchBookController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(AchBookController.class);

    @Autowired
    private IAchBookBiz bookBiz;
    @Autowired
    private IAchBookAuthorBiz bookAuthorBiz;
    @Autowired
    private ISrmAchFileBiz srmAchFileBiz;
    @Autowired
    private ISrmAchProcessBiz srmAchProcessBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IAchScoreBiz achScoreBiz;


    @RequestMapping(value = {"/save/{roleScope}"})
    @ResponseBody
    public String saveBookAndAuthor(@PathVariable String roleScope,String jsondata, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        SrmAchBookAuthorList aList = JSON.parseObject(jsondata, SrmAchBookAuthorList.class);
        List<SrmAchBookAuthor> authorList = aList.getAuthorList();
        SrmAchBook book = aList.getBook();

        book.setTypeName(DictTypeEnum.AchBookType.getDictNameById(book.getTypeId()));
        //学科门类
        book.setCategoryName(DictTypeEnum.SubjectType.getDictNameById(book.getCategoryId()));
        book.setPressLevelName(DictTypeEnum.PressLevelType.getDictNameById(book.getPressLevelId()));
        book.setPubPlaceName(DictTypeEnum.PlaceNameType.getDictNameById(book.getPubPlaceId()));
        book.setOrgBelongName(DictTypeEnum.OrgBelong.getDictNameById(book.getOrgBelongId()));
        book.setLanguageTypeName(DictTypeEnum.LanguageType.getDictNameById(book.getLanguageTypeId()));
        book.setProjSourceName(DictTypeEnum.ProjSource.getDictNameById(book.getProjSourceId()));
        book.setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(book.getBranchId()));
        if(GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            book.setOperStatusId(AchStatusEnum.Apply.getId());
            book.setOperStatusName(AchStatusEnum.Apply.getName());
        }
        //获取申请单位信息
        SysUser currUser = GlobalContext.getCurrentUser();
        /*book.setApplyOrgFlow(currUser.getOrgFlow());
        book.setApplyOrgName(currUser.getOrgName());
        //申请人信息
        book.setApplyUserFlow(currUser.getUserFlow());
        book.setApplyUserName(currUser.getUserName());*/

        //根据【著作作者】的相关ID枚举获得Name
        for (int i = 0; i < authorList.size(); i++) {
            //authorList.get(i).setTypeName(DictTypeEnum.AuthorType.getDictNameById(authorList.get(i).getTypeId()));//署名顺序
            authorList.get(i).setWriteTypeName(DictTypeEnum.WriteNameType.getDictNameById(authorList.get(i).getWriteTypeId()));
            authorList.get(i).setSexName(UserSexEnum.getNameById(authorList.get(i).getSexId()));
            authorList.get(i).setEducationName(DictTypeEnum.UserEducation.getDictNameById(authorList.get(i).getEducationId()));
            authorList.get(i).setTitleName(DictTypeEnum.UserTitle.getDictNameById(authorList.get(i).getTitleId()));
            authorList.get(i).setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(authorList.get(i).getBranchId()));
            if(StringUtil.isNotBlank(authorList.get(i).getScoreFlow())){
                SrmAchScore score=new SrmAchScore();
                score.setScoreFlow(authorList.get(i).getScoreFlow());
                List<SrmAchScore> list=achScoreBiz.searchScoreSetList(score);
                if(list!=null&&list.size()>0&&list.get(0)!=null){
                    authorList.get(i).setScoreFlow(list.get(0).getScoreFlow());
                    authorList.get(i).setScoreName(list.get(0).getScoreName());
                    authorList.get(i).setAchScore(list.get(0).getScorePersonalValue().add(new BigDecimal(0)));
                    authorList.get(i).setAchScoreDept(list.get(0).getScoreDeptValue().add(new BigDecimal(0)));
                }
            }
        }
        //封装附件对象
        SrmAchFile srmAchFile = null;
        if (file != null && StringUtil.isNotBlank(file.getOriginalFilename())) {
            srmAchFile =new SrmAchFile();
            byte[] b = new byte[(int) file.getSize()];
            file.getInputStream().read(b);
            srmAchFile.setFileFlow(aList.getSrmAchFile().getFileFlow());
            srmAchFile.setFileContent(b);
            srmAchFile.setFileName(file.getOriginalFilename());
            srmAchFile.setTypeId(AchTypeEnum.Book.getId());
            srmAchFile.setTypeName(AchTypeEnum.Book.getName());
            String[] nameArray = file.getOriginalFilename().split("\\.");
            srmAchFile.setFileSuffix(nameArray[nameArray.length - 1]);
            srmAchFile.setFileType(nameArray[nameArray.length - 1]);
        }
        //封装成果过程对象
        SrmAchProcess srmAchProcess = new SrmAchProcess();
        srmAchProcess.setTypeId(AchTypeEnum.Book.getId());
        srmAchProcess.setTypeName(AchTypeEnum.Book.getName());
        srmAchProcess.setOperateUserFlow(currUser.getUserFlow());
        srmAchProcess.setOperateUserName(currUser.getUserName());
        if(GlobalConstant.USER_LIST_PERSONAL.equals(roleScope)) {
            srmAchProcess.setOperStatusId(AchStatusEnum.Apply.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.Apply.getName());
        }
        if(GlobalConstant.USER_LIST_LOCAL.equals(roleScope)){
            srmAchProcess.setOperStatusId(AchStatusEnum.LocalEdit.getId());
            srmAchProcess.setOperStatusName(AchStatusEnum.LocalEdit.getName());
        }
        bookBiz.save(book, authorList, srmAchFile, srmAchProcess);
        // return "<script>parent.callBack('"+GlobalConstant.OPRE_SUCCESSED_FLAG+"');</script>";
        return GlobalConstant.SAVE_SUCCESSED;

    }


    @RequestMapping(value = "deleteAuthor", method = {RequestMethod.GET})
    @ResponseBody
    public String deleteAuthor(String authorFlow) {
        if (StringUtil.isNotBlank(authorFlow)) {
            SrmAchBookAuthor author = new SrmAchBookAuthor();
            author.setAuthorFlow(authorFlow);
            author.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            bookAuthorBiz.editBookAthor(author);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 页面加载著作数据
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{scope}", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(@PathVariable String scope,Integer currentPage, SrmAchBook book, SrmAchBookAuthor author, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        book.setApplyUserFlow(currUser.getUserFlow());
        if("wxdermyy".equals(scope)&&GlobalConstant.FLAG_Y.equals(book.getOperStatusId())){
            book.setOperStatusId("");
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SrmAchBook> achBookList = bookBiz.search(book, null,null);

        //组织关联作者的Map
        Map<String, List<SrmAchBookAuthor>> allAuthorMap = new LinkedHashMap<String, List<SrmAchBookAuthor>>();
        List<SrmAchBookAuthor> allBookAuthorList = bookAuthorBiz.searchAuthorList(new SrmAchBookAuthor());
        if (allBookAuthorList != null && !allBookAuthorList.isEmpty()) {
            for (SrmAchBookAuthor a : allBookAuthorList) {
                List<SrmAchBookAuthor> list = allAuthorMap.get(a.getBookFlow());
                if (list == null) {
                    list = new ArrayList<SrmAchBookAuthor>();
                }
                list.add(a);
                allAuthorMap.put(a.getBookFlow(), list);
            }
        }
        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("achBookList", achBookList);
        if("xzzxyy".equals(scope)){
            return "srm/ach_xzzxyy/book/list";
        }
        if("wxdermyy".equals(scope)){
            return "srm/ach_wxdermyy/book/list";
        }
        if("common".equals(scope)){
            return "srm/ach_common/book/list";
        }
        return "srm/ach/book/list";
    }
    /**
     * ISBN号是否已存在
     * @return
     */
    @RequestMapping("/bookIsExist")
    @ResponseBody
    public SrmAchBook bookIsExist(String isbnCode){
        SrmAchBook book = null;
        if(StringUtil.isNotBlank(isbnCode)) {
            isbnCode = isbnCode.trim();
            book = bookBiz.bookIsExist(isbnCode);
        }
        return book;
    }

    /**
     * 跳转到新增或修改
     *
     * @param bookFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{scope}", method = RequestMethod.GET)
    public String edit(@PathVariable String scope,String bookFlow, Model model) {
        model.addAttribute("scope",scope);
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        //作者信息显示
        if (StringUtil.isNotBlank(bookFlow)) {
            SrmAchBook book = bookBiz.readBook(bookFlow);
            model.addAttribute("book", book);

            SrmAchBookAuthor author = new SrmAchBookAuthor();
            author.setBookFlow(bookFlow);
            List<SrmAchBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(author);
            model.addAttribute("bookAuthorList", bookAuthorList);
            fileList = srmAchFileBiz.searchSrmAchFile(bookFlow);
        }
        model.addAttribute("roleScope",GlobalConstant.USER_LIST_PERSONAL);
        if (fileList != null && !fileList.isEmpty()) {
            file = fileList.get(0);
            model.addAttribute("file", file);
        }
        if("xzzxyy".equals(scope)){
            return "srm/ach_xzzxyy/book/edit";
        }
        if("wxdermyy".equals(scope)){
            return "srm/ach_wxdermyy/book/edit";
        }
        if("common".equals(scope)){
            return "srm/ach_common/book/edit";
        }
        return "srm/ach/book/edit";
    }

    /**
     * 删除
     *
     * @param bookFlow
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String bookFlow) {
        if (StringUtil.isNotBlank(bookFlow)) {
            SrmAchBook book = new SrmAchBook();
            book.setBookFlow(bookFlow);
            book.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

            //作者
            SrmAchBookAuthor author = new SrmAchBookAuthor();
            author.setBookFlow(bookFlow);
            List<SrmAchBookAuthor> authorList = bookAuthorBiz.searchAuthorList(author);
            for (SrmAchBookAuthor a : authorList) {
                a.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            //附件
            SrmAchFile file = null;
            List<SrmAchFile> fileList = srmAchFileBiz.searchSrmAchFile(bookFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }

            int result = bookBiz.edit(book, authorList, file);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }


    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@RequestParam(value = "agreeFlag", required = true) String agreeFlag, String auditContent,
                            @RequestParam(value = "bookFlow", required = true) String bookFlow, Model model) {
        SrmAchBook book = bookBiz.readBook(bookFlow);
        SrmAchProcess process = srmAchProcessBiz.searchAchProcess(bookFlow, AchStatusEnum.Apply.getId()).get(0);

        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            book.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            book.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            process.setOperStatusName(AchStatusEnum.FirstAudit.getName());
        } else {
            book.setOperStatusId(AchStatusEnum.RollBack.getId());
            book.setOperStatusName(AchStatusEnum.RollBack.getName());
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
        bookBiz.updateBookStatus(book, process);
        return GlobalConstant.OPERATE_SUCCESSED;
    }


    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value = "bookFlow", required = true) String bookFlow, Model model) {
        SrmAchBook book = bookBiz.readBook(bookFlow);
        book.setOperStatusId(AchStatusEnum.Submit.getId());
        book.setOperStatusName(AchStatusEnum.Submit.getName());

        SrmAchProcess process = srmAchProcessBiz.searchAchProcess(bookFlow, AchStatusEnum.Apply.getId()).get(0);
        process.setProcessFlow(PkUtil.getUUID());
        process.setOperStatusId(AchStatusEnum.Submit.getId());
        process.setOperStatusName(AchStatusEnum.Submit.getName());
        GeneralMethod.setRecordInfo(process, true);
        process.setOperateTime(process.getCreateTime());
        SysUser currUser = GlobalContext.getCurrentUser();
        process.setOperateUserFlow(currUser.getUserFlow());
        process.setOperateUserName(currUser.getUserName());
        bookBiz.updateBookStatus(book, process);

        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }


    @RequestMapping(value = "/auditList/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String scope, Integer currentPage, SrmAchBook book, SrmAchBookAuthor author, SysOrg org, Model model, HttpServletRequest request) {
        model.addAttribute("scope",scope);
        SysUser currUser = GlobalContext.getCurrentUser();
        List<SrmAchBook> searchList = null;
        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);
        //获取当前机构下属一级的机构
        List<SysOrg> firstGradeOrgList = (List<SysOrg>) resultMap.get("firstGradeOrgList");
        model.addAttribute("firstGradeOrgList", firstGradeOrgList);
        List<String> bookFlows=null;
        if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
            SrmAchBookAuthor b=new SrmAchBookAuthor();
            b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(author.getAuthorName())){
                b.setAuthorName(author.getAuthorName());
            }
            if(StringUtil.isNotBlank(author.getWorkCode())){
                b.setWorkCode(author.getWorkCode());
            }
            List<SrmAchBookAuthor> autorsList=bookAuthorBiz.searchAuthorList(b);
            if(autorsList!=null&&autorsList.size()>0){
                bookFlows=new ArrayList<>();
                for(SrmAchBookAuthor sa:autorsList){
                    if(!bookFlows.contains(sa.getBookFlow())){
                        bookFlows.add(sa.getBookFlow());
                    }
                }

            }
            if(null == bookFlows || bookFlows.size()==0){
                bookFlows=new ArrayList<>();
                bookFlows.add("");
            }
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//无锡二院科主任
            if(StringUtil.isNotBlank(currUser.getDeptFlow())){
                book.setDeptFlow(currUser.getDeptFlow());
            }else{
                if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                    return "srm/ach_wxdermyy/book/auditListlocal";
                }
                return "srm/ach_common/book/auditListlocal";
            }
        }
        //卫生局
        if (GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)) {
            //设置查询条件：科技处审核通过的成果
            book.setOperStatusId(AchStatusEnum.FirstAudit.getId());
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
                    searchList = bookBiz.search(book, selfOrgList,bookFlows);
                } else {
                    PageHelper.startPage(currentPage, getPageSize(request));
                    searchList = bookBiz.search(book, secondGradeOrgList,bookFlows);
                }
            }

        } else if (GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)) {
            book.setOperStatusId(AchStatusEnum.FirstAudit.getId());
        } else {
            org.setOrgFlow(currUser.getOrgFlow());//当前登陆者是医院 只查询本医院下的成果
            if (StringUtil.isBlank(book.getOperStatusId())) {
                book.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(book.getOperStatusId())) {
                book.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
        }
        //如果查询条件orgFlow不为空，则查询该org下所有成果
        if (StringUtil.isNotBlank(org.getOrgFlow())) {
            book.setApplyOrgFlow(org.getOrgFlow());
            PageHelper.startPage(currentPage, getPageSize(request));
            searchList = bookBiz.search(book, null,bookFlows);
        }
        //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
        if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
            PageHelper.startPage(currentPage, getPageSize(request));
            searchList = bookBiz.search(book, currOrgChildList,bookFlows);
        }

        Map<String, List<SrmAchBookAuthor>> allAuthorMap = new HashMap<String, List<SrmAchBookAuthor>>();
        List<SrmAchBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(new SrmAchBookAuthor());
        if (bookAuthorList != null && !bookAuthorList.isEmpty()) {
            for (SrmAchBookAuthor a : bookAuthorList) {
                List<SrmAchBookAuthor> authorList = allAuthorMap.get(a.getBookFlow());
                if (authorList == null) {
                    authorList = new ArrayList<SrmAchBookAuthor>();
                }
                authorList.add(a);
                allAuthorMap.put(a.getBookFlow(), authorList);
            }
        }

        model.addAttribute("allAuthorMap", allAuthorMap);
        model.addAttribute("bookList", searchList);
        if("xzzxyy".equals(scope)){
            return "srm/ach_xzzxyy/book/auditListlocal";
        }
        if("wxdermyy".equals(scope)){
            return "srm/ach_wxdermyy/book/auditListlocal";
        }
        if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)){//科主任
            if ("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))) {
                return "srm/ach_wxdermyy/book/auditListlocal";
            }
            return "srm/ach_common/book/auditListlocal";
        }
        if("common".equals(scope)){
            return "srm/ach_common/book/auditListlocal";
        }
        return "srm/ach/book/auditList" + scope;
    }


    @RequestMapping("/audit")
    public String audit(@RequestParam(value = "bookFlow", required = true) String bookFlow, Model model) {
        SrmAchScore score=new SrmAchScore();
        score.setScoreStatusId(GlobalConstant.RECORD_STATUS_Y);
        List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(score);
        model.addAttribute("srmAchScoreList",srmAchScoreList);
        //根据成果流水号查询相关信息，用于加载审核页面
        SrmAchFile file = null;
        List<SrmAchFile> fileList = null;
        if (StringUtil.isNotBlank(bookFlow)) {
            //查询成果本身
            SrmAchBook book = bookBiz.readBook(bookFlow);
            model.addAttribute("book", book);
            //查询成果作者
            SrmAchBookAuthor author = new SrmAchBookAuthor();
            author.setBookFlow(bookFlow);
            List<SrmAchBookAuthor> bookAuthorList = bookAuthorBiz.searchAuthorList(author);
            model.addAttribute("bookAuthorList", bookAuthorList);
            //查询成果附件
            fileList = srmAchFileBiz.searchSrmAchFile(bookFlow);
            if (fileList != null && !fileList.isEmpty()) {
                file = fileList.get(0);
                model.addAttribute("file", file);
            }
        }
        model.addAttribute("roleScope",GlobalConstant.USER_LIST_LOCAL);
        model.addAttribute("company","Y");
        //徐州中心医院
        if("Y".equals(InitConfig.getSysCfg("srm_local_type"))){
            return "srm/ach_xzzxyy/book/audit";
        }
        //无锡第二人民医院
        if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
            return "srm/ach_wxdermyy/book/audit";
        }
        if("common".equals(InitConfig.getSysCfg("srm_local_type"))){
            return "srm/ach_common/book/audit";
        }
        return "srm/ach/book/audit";
    }

    /**
     * 导出科研成果（著作）
     *
     * @param achBook
     * @param response
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/exportBookExcel/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportBookExcel(@PathVariable String scope,SrmAchBook achBook, SrmAchBookAuthor author, SysOrg org, HttpServletResponse response) throws Exception {

        String[] titles;//导出列表头信息
        SysUser currUser = GlobalContext.getCurrentUser();
        /*著作导出信息列表*/
        List<AchBookExportForm> searchList = new ArrayList<AchBookExportForm>();
        /*查询著作信息*/
        List<SrmAchBook> achBookList = new ArrayList<SrmAchBook>();

        //查询当前机构下属所有级别子机构包含自身
        List<SysOrg> currOrgChildList = orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
        //根据orgFlow和chargeOrgFlow查询包含子机构列表和子机构的子机构的列表的Map
        Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org, scope);

        List<String> bookFlows=null;
        if(StringUtil.isNotBlank(author.getAuthorName()) || StringUtil.isNotBlank(author.getWorkCode())){
            SrmAchBookAuthor b=new SrmAchBookAuthor();
            b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(author.getAuthorName())){
                b.setAuthorName(author.getAuthorName());
            }
            if(StringUtil.isNotBlank(author.getWorkCode())){
                b.setWorkCode(author.getWorkCode());
            }
            List<SrmAchBookAuthor> autorsList=bookAuthorBiz.searchAuthorList(b);
            if(autorsList!=null&&autorsList.size()>0){
                bookFlows=new ArrayList<>();
                for(SrmAchBookAuthor sa:autorsList){
                    if(!bookFlows.contains(sa.getBookFlow())){
                        bookFlows.add(sa.getBookFlow());
                    }
                }

            }
            if(null == bookFlows || bookFlows.size()==0){
                bookFlows=new ArrayList<>();
                bookFlows.add("");
            }
        }

        //卫计委
        if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(scope)){
            achBook.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            titles = new String[]{
                    "srmAchBook.bookName:著作名称",
                    "srmAchBook.orgBelongName:所属单位",
                    "authorName:参编作者",
                    "srmAchBook.publishDate:出版日期",
                    "srmAchBook.typeName:著作类别",
                    "srmAchBook.publishOrg:出版单位",
                    "srmAchBook.pubPlaceName:出版地",
                    "srmAchBook.projSourceName:项目来源"
            };
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isNotBlank(org.getChargeOrgFlow())) {
                //设置查询条件：科技处审核通过的成果
                //如果当前登录者是卫生局，获取该单位选定的下一级机构的所有子机构
                List<SysOrg> secondGradeOrgList = (List<SysOrg>) resultMap.get("secondGradeOrgList");
                if (null == secondGradeOrgList || secondGradeOrgList.isEmpty()) {
                    SysOrg sysOrg = orgBiz.readSysOrg(org.getChargeOrgFlow());
                    List<SysOrg> selfOrgList = new ArrayList<SysOrg>();
                    selfOrgList.add(sysOrg);
                    achBookList = bookBiz.search(achBook, selfOrgList,bookFlows);
                } else {
                    achBookList = bookBiz.search(achBook, secondGradeOrgList,bookFlows);
                }
            }

        //主管部门
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(scope)){
            titles = new String[]{
                    "srmAchBook.bookName:著作名称",
                    "srmAchBook.orgBelongName:所属单位",
                    "authorName:参编作者",
                    "srmAchBook.publishDate:出版日期",
                    "srmAchBook.typeName:著作类别",
                    "srmAchBook.publishOrg:出版单位",
                    "srmAchBook.pubPlaceName:出版地",
                    "srmAchBook.projSourceName:项目来源"
            };
            achBook.setOperStatusId(AchStatusEnum.FirstAudit.getId());
        //申报单位
        }else if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(scope)){
            titles = new String[]{
                    "srmAchBook.bookName:著作名称",
                    "authorName:参编作者",
                    "srmAchBook.publishDate:出版日期",
                    "srmAchBook.publishOrg:出版单位",
                    "srmAchBook.pubPlaceName:出版地",
                    "srmAchBook.projSourceName:项目来源",
                    "srmAchBook.operStatusName:审核状态"
            };
            org.setOrgFlow(currUser.getOrgFlow());
            if (StringUtil.isBlank(achBook.getOperStatusId())) {
                achBook.setOperStatusId(AchStatusEnum.Submit.getId());
            }
            if (GlobalConstant.FLAG_Y.equals(achBook.getOperStatusId())) {
                achBook.setOperStatusId(AchStatusEnum.Submit.getId() + "," + AchStatusEnum.FirstAudit.getId());
            }
        //负责人
        }else {
            titles = new String[]{
                    "srmAchBook.bookName:著作名称",
                    "authorName:参编作者",
                    "srmAchBook.publishDate:出版日期",
                    "srmAchBook.typeName:著作类别",
                    "srmAchBook.pubPlaceName:出版地",
                    "srmAchBook.projSourceName:项目来源",
                    "srmAchBook.operStatusName:审核状态"
            };
            achBook.setApplyUserFlow(currUser.getUserFlow());
            achBookList = bookBiz.search(achBook, null,bookFlows);
        }
        if(!scope.equals(GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL)) {
            //如果查询条件orgFlow不为空，则查询该org下所有成果
            if (StringUtil.isNotBlank(org.getOrgFlow())) {
                achBook.setApplyOrgFlow(org.getOrgFlow());
                achBookList = bookBiz.search(achBook, null,bookFlows);
            }
            //如果查询条件orgFlow和chargeOrgFlow都为空，那么查询当前登录者下所有成果
            if (StringUtil.isBlank(org.getOrgFlow()) && StringUtil.isBlank(org.getChargeOrgFlow())) {
                achBookList = bookBiz.search(achBook, currOrgChildList,bookFlows);
            }
        }

        List<SrmAchBookAuthor> allBookAuthorList = bookAuthorBiz.searchAuthorList(new SrmAchBookAuthor());

        if (allBookAuthorList != null && !allBookAuthorList.isEmpty() && achBookList != null) {
            for (SrmAchBook book : achBookList) {
                //组织关联作者
                String authorName = "";
                AchBookExportForm bookExportForm = new AchBookExportForm();
                for (SrmAchBookAuthor bookAuthor : allBookAuthorList) {
                    if (book.getBookFlow().equals(bookAuthor.getBookFlow())) {
//                        authorName = authorName + " " + bookAuthor.getAuthorName();
                        if(authorName.equals("")) {
                            authorName = bookAuthor.getAuthorName();
                        }else{
                            authorName = authorName + "," + bookAuthor.getAuthorName();
                        }
                    }
                }
                bookExportForm.setSrmAchBook(book);
                bookExportForm.setAuthorName(authorName);
                searchList.add(bookExportForm);
            }
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, searchList, response.getOutputStream());
        String fileName = "科研成果（著作）.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
