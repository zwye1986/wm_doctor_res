package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResRecBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/jsres/doctorDataAudit")
public class JsResDoctorDataAuditController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    @Autowired
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;

    /**
     *
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/auditMain")
    public String auditMain(Model model,String roleFlag){
//        return "jsres/hospital/doctorDataAudit/auditMain";
        return "jsres/hospital/doctorDataAudit/batchMain";
    }
    @RequestMapping(value="/batchAudit")
    @ResponseBody
    public String batchAudit(Model model,String graduationYear){
        if(StringUtil.isBlank(graduationYear))
        {
            return "请选择结业考核年份";
        }
        Map<String,Object> param=new HashMap<>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        param.put("orgFlow",sysuser.getOrgFlow());//培训基地
        param.put("graduationYear",graduationYear);//培训基地
        param.put("sysuser",sysuser);//培训基地
        int count=resRecBiz.orgBatchAuditDoctorInfo(param);
        if(count==0)
        {
            return graduationYear+"年结业的学员,无数据需要审核！";
        }
        return "成功审核"+graduationYear+"年结业的学员数据!一共审核"+count+"条数据！";
    }

    /**
     *
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/auditList")
    public String auditList(Model model,Integer currentPage ,HttpServletRequest request,
                                 String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,
                                String userName,String idNo
                                ){
        Map<String,Object> param=new HashMap<>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        param.put("orgFlow",sysuser.getOrgFlow());//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryList(param);

        model.addAttribute("list",list);
        return "jsres/hospital/doctorDataAudit/auditList";
    }
}
