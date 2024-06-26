package com.pinde.sci.ctrl.res;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEntryReportBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SysUserDeptMapper;
import com.pinde.sci.model.mo.SysUserDept;
import com.pinde.sci.model.mo.SysUserDeptExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/entry/report")
public class ResEntryReportController extends GeneralController {

    @Autowired
    private IResEntryReportBiz reportBiz;
    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;

    @RequestMapping(value="/main")
    public String main(Model model) {
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
        List<String> deptFlows = new ArrayList<String>();
        String deptFlowsStr = "";
        for(SysUserDept dept: deptList){
            deptFlows.add(dept.getDeptFlow());
            if(StringUtil.isNotBlank(deptFlowsStr)){
                deptFlowsStr+=",";
            }
            deptFlowsStr+=dept.getDeptFlow();
        }
        model.addAttribute("signUrl","func://funcFlow=EntryReport&teacherDeptFlows="+deptFlowsStr+"&headUserFlow="+userFlow);
        model.addAttribute("deptFlowsStr",deptFlowsStr);
        return "res/entry/report/main";
    }
    @RequestMapping(value="/reportData")
    public String reportData(Model model,String doctorName,String trainingYears,String sessionNumber,
                             String reportDate, String trainingSpeId,Integer currentPage,HttpServletRequest request) {
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
        List<String> deptFlows = new ArrayList<String>();
        for(SysUserDept dept: deptList){
            deptFlows.add(dept.getDeptFlow());
        }
        Map<String,Object> param=new HashMap<>();
        param.put("deptFlows",deptFlows);
        param.put("doctorName",doctorName);
        param.put("trainingYears",trainingYears);
        param.put("sessionNumber",sessionNumber);
        param.put("reportDate",reportDate);
        param.put("trainingSpeId",trainingSpeId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,String>> list=reportBiz.getReportList(param);
        model.addAttribute("list",list);
        return "res/entry/report/reportData";
    }
    @RequestMapping(value="/codeIsUse")
    @ResponseBody
    public Object codeIsUse(Model model,String userName,String deptName,String sessionNumber,
                                   String token) {
        Object val="";
        if(val==null)
        {
            return "N";
        }
        return val;
    }
}
