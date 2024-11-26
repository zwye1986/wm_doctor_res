package com.pinde.sci.ctrl.res;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDiscipleInfoBiz;
import com.pinde.sci.biz.res.IResDiscipleTeacherInfoBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResGraduationAssessmentBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.res.DiscipleStatusEnum;
import com.pinde.sci.enums.res.NoteTypeEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.ResDiscipleReq;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.ResDoctorExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author tiger
 */
@Controller
@RequestMapping("/res/discipleAdminAudit")
public class ResDiscipleAdminAuditController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResDiscipleAdminAuditController.class);

    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IResDiscipleInfoBiz discipleInfoBiz;
    @Autowired
    private IResDiscipleTeacherInfoBiz teacherInfoBiz;
    @Autowired
    private IResGraduationAssessmentBiz graduationAssessmentBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private SysOrgExtMapper sysOrgExtMapper;

    /**
     * 学员列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/graduationList")
    public String main(Integer currentPage, HttpServletRequest request, Model model, String sessionNumber,String[] doctorTypeIdList,
                       String teacherName, String doctorCategoryId,String roleFlag,String orgFlow,
                       String speId, String doctorName) throws Exception {
        SysUser currUser = GlobalContext.getCurrentUser();
        String workOrgId="";
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
            //查询所有医院
            SysOrg org = new SysOrg();
            org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(org);
            model.addAttribute("orgs",orgs);
        }else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            String currentOrgFlow = currUser.getOrgFlow();
            if(StringUtil.isNotBlank(currentOrgFlow)){
                SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
                workOrgId = org.getSendSchoolId();
                List<SysOrg> orgs = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
                model.addAttribute("orgs",orgs);
            }
        }else{
            orgFlow=currUser.getOrgFlow();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow", orgFlow);
        param.put("sessionNumber", sessionNumber);
        param.put("speId", speId);
        param.put("doctorCategoryId", doctorCategoryId);
        param.put("docName", doctorName);
        param.put("teacherName", teacherName);
        param.put("workOrgId", workOrgId);
        param.put("auditId", DiscipleStatusEnum.DiscipleAudit.getId());
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            param.put("auditId", DiscipleStatusEnum.AdminAudit.getId());
        }
        List<String> doctorTypeIds = null;
        if(doctorTypeIdList != null && doctorTypeIdList.length > 0){
            doctorTypeIds = new ArrayList<>();
            for(String temp : doctorTypeIdList){
                doctorTypeIds.add(temp);
            }
        }
        //复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIds!=null&&doctorTypeIds.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIds.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        param.put("doctorTypeIds", doctorTypeIds);
        model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorExt> students = doctorBiz.searchDocByDiscipleAdmin(param);
        model.addAttribute("list", students);
        model.addAttribute("roleFlag", roleFlag);
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            return "res/disciple/admin/graduationList4Global";
        }
        return "res/disciple/admin/graduationList";
    }

    /**
     * 学习情况查询学员列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/studentList")
    public String studentList(Integer currentPage, HttpServletRequest request, Model model, String sessionNumber,String[] doctorTypeIdList,
                              String teacherName, String speId, String doctorName, String doctorCategoryId,String orgFlow,String roleFlag) throws Exception {
        model.addAttribute("roleFlag",roleFlag);
        String workOrgId = "";
        List<SysOrg> orgList=new ArrayList<>();
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
            //查询所有医院
            SysOrg orgTemp = new SysOrg();
            orgTemp.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            orgList = orgBiz.searchOrg(orgTemp);
        }else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            SysUser currUser = GlobalContext.getCurrentUser();
            String currentOrgFlow = currUser.getOrgFlow();
            if(StringUtil.isNotBlank(currentOrgFlow)){
                SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
                workOrgId = org.getSendSchoolId();
                orgList = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
            }
        }else{
            //医院管理员增加查协同基地学员轮转情况
            SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            orgList.add(sysOrg);
            List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
            if(jointOrgList!=null&&jointOrgList.size()>0){
                for (SysOrg so:jointOrgList) {
                    orgList.add(so);
                }
            }
        }
        model.addAttribute("orgList",orgList);
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String, Object> param = new HashMap<>();
        if(StringUtil.isBlank(orgFlow)&&!GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)&&!GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)) {
            orgFlow = currUser.getOrgFlow();
            param.put("orgFlow", currUser.getOrgFlow());
            SysOrg org =orgBiz.readSysOrg(orgFlow);
            model.addAttribute("org",org);
        }else{
            param.put("orgFlow", orgFlow);
        }
        param.put("sessionNumber", sessionNumber);
        param.put("speId", speId);
        param.put("doctorCategoryId", doctorCategoryId);
        param.put("docName", doctorName);
        param.put("teacherName", teacherName);
        param.put("workOrgId",workOrgId);
        List<String> doctorTypeIds = null;
        if(doctorTypeIdList != null && doctorTypeIdList.length > 0){
            doctorTypeIds = new ArrayList<>();
            for(String temp : doctorTypeIdList){
                doctorTypeIds.add(temp);
            }
        }
        //复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0){
            if(doctorTypeIds!=null&&doctorTypeIds.size()>0){
                for (SysDict dict:dictList){
                    if(doctorTypeIds.contains(dict.getDictId())){
                        doctorTypeSelectMap.put(dict.getDictId(),"checked");
                    }
                }
            }else{
                doctorTypeIds = new ArrayList<>();
                for (SysDict dict:dictList){
                    doctorTypeIds.add(dict.getDictId());
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        param.put("doctorTypeIds", doctorTypeIds);
        model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> resultList = new ArrayList<>();
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            resultList = doctorBiz.globalQueryDocDisciple(param);
        }else{
            resultList = doctorBiz.adminQueryDocDisciple(param);
        }
        model.addAttribute("list", resultList);
        if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
            model.addAttribute("jszyFlag",GlobalConstant.FLAG_Y);
        }
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
            return "res/disciple/admin/studentList4Global";
        }
        return "res/disciple/admin/studentList";
    }

    @RequestMapping(value = "/discipleReqManange")
    public String discipleReqManange(Model model) {
        String sessionNumber = "";
        if(StringUtil.isBlank(sessionNumber)){
            int year = 0;
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            if (month < 9) {
                year = cal.get(Calendar.YEAR) - 1;
            } else {
                year = cal.get(Calendar.YEAR);
            }
            sessionNumber = year + "";
            model.addAttribute("sessionNumber", sessionNumber);
        }else {
            model.addAttribute("sessionNumber", sessionNumber);
        }
        return "res/disciple/admin/reqManange";
    }

    @RequestMapping(value = "/reqEdit")
    public String edit(Model model, String recordFlow) {
        ResDiscipleReq resDiscipleReq = discipleInfoBiz.findResDiscipleReqByFlow(recordFlow);
        model.addAttribute("resDiscipleReq", resDiscipleReq);
        return "res/disciple/admin/reqEdit";
    }

    @RequestMapping(value = "/showAddBatch")
    public String showAddBatch(Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("currentOrg",currentOrg);
        return "res/disciple/admin/addBatch";
    }

    @RequestMapping(value = "/discipleReqInfoList")
    public String discipleReqInfoList(Model model, ResDiscipleReq resDiscipleReq) {
        List<ResDiscipleReq> discipleReqList = discipleInfoBiz.findResDiscipleReqList(resDiscipleReq);

        //用于页面取数据key是trainingTypeId+noteTylpeId，value是reqNumber
        Map<String,String> resDiscipleReqMap = new HashMap<>();

        String trainingTypeId = resDiscipleReq.getTrainingTypeId();
        if(StringUtil.isBlank(trainingTypeId)){
            Enum[] trainingTypeIds = RecDocCategoryEnum.values();
            for(Enum tempEnum : trainingTypeIds){
                for(ResDiscipleReq tempReq : discipleReqList){
                    if(tempEnum.toString().equals(tempReq.getTrainingTypeId())
                            && resDiscipleReq.getSessionNumber().equals(tempReq.getSessionNumber())){
                        resDiscipleReqMap.put(tempReq.getTrainingTypeId() + tempReq.getDiscipleTypeId(),tempReq.getReqNumber().toString());
                    }
                }
            }
        }else {
            resDiscipleReq.setTrainingTypeName(RecDocCategoryEnum.getNameById(resDiscipleReq.getTrainingTypeId()));
            for(ResDiscipleReq tempReq : discipleReqList){
                if(trainingTypeId.equals(tempReq.getTrainingTypeId())
                        && resDiscipleReq.getSessionNumber().equals(tempReq.getSessionNumber())){
                    resDiscipleReqMap.put(tempReq.getTrainingTypeId() + tempReq.getDiscipleTypeId(),tempReq.getReqNumber().toString());
                }
            }
        }
        model.addAttribute("resDiscipleReq", resDiscipleReq);
        model.addAttribute("resDiscipleReqMap", resDiscipleReqMap);
        return "res/disciple/admin/discipleReqInfoList";
    }

    @RequestMapping(value = "/updateDiscipleReq")
    @ResponseBody
    public String updateDiscipleReq(ResDiscipleReq resDiscipleReq) {
        ResDiscipleReq resDiscipleReq4Search = new ResDiscipleReq();
        resDiscipleReq4Search.setDiscipleTypeId(resDiscipleReq.getDiscipleTypeId());
        resDiscipleReq4Search.setSessionNumber(resDiscipleReq.getSessionNumber());
        resDiscipleReq4Search.setTrainingTypeId(resDiscipleReq.getTrainingTypeId());

        List<ResDiscipleReq> list = discipleInfoBiz.findResDiscipleReqList(resDiscipleReq4Search);
        ResDiscipleReq resDiscipleReq4Save = new ResDiscipleReq();
        if (list != null && list.size() > 0) {
            resDiscipleReq4Save = list.get(0);
            resDiscipleReq4Save.setReqNumber(resDiscipleReq.getReqNumber());
        }else {
            resDiscipleReq4Save = resDiscipleReq;
            if (StringUtil.isNotBlank(resDiscipleReq.getDiscipleTypeId())) {
                resDiscipleReq.setDiscipleTypeName(NoteTypeEnum.getNameById(resDiscipleReq.getDiscipleTypeId()));
            }
            if (StringUtil.isNotBlank(resDiscipleReq.getTrainingTypeId())) {
                resDiscipleReq.setTrainingTypeName(RecDocCategoryEnum.getNameById(resDiscipleReq.getTrainingTypeId()));
            }
        }

        int result = discipleInfoBiz.updateDiscipleReq(resDiscipleReq4Save);
        if (result == 0) {
            return "操作失败！";
        }
        return "操作成功！";
    }
}
