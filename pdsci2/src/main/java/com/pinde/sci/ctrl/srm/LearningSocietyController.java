package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.ILearningSocietyBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SrmLearningSociety;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.0001.Ga on 2016.8.26.
 */
@Controller
@RequestMapping("/srm/learning/sociery")
public class LearningSocietyController extends GeneralController {
    @Autowired
    private ILearningSocietyBiz learningSocietyBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IDeptBiz deptBiz;

    /**
     * （徐州中心医院）学会任职登记
     *
     * @return
     */
    @RequestMapping(value = "/registerSociety")
    public String registerSociety(Model model, String userFlow) {
        // SysUser logiunUser= GlobalContext.getCurrentUser();
        SrmLearningSociety learningSociety = new SrmLearningSociety();
        SysUser sysUser = new SysUser();
        if (StringUtil.isNotBlank(userFlow)) {
            learningSociety.setUserFlow(userFlow);
            List<SrmLearningSociety> learningSocietyList = learningSocietyBiz.search(learningSociety);
            model.addAttribute("learningSocietyList", learningSocietyList);
            sysUser = userBiz.findByFlow(userFlow);
            model.addAttribute("sysUser", sysUser);
            if (StringUtil.isNotBlank(sysUser.getOrgFlow())) {
                SysDept sysDept = new SysDept();
                sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysDept.setOrgFlow(sysUser.getOrgFlow());
                List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
                model.addAttribute("sysDeptList", sysDeptList);
            }
        }

        return "/srm/society/societyJob";
    }

    @RequestMapping("/saveSociety")
    public
    @ResponseBody
    String saveSociety(@RequestBody ArrayList<SrmLearningSociety> learningSocietyList) {
        if (learningSocietyList != null) {
            for (SrmLearningSociety a : learningSocietyList) {
                this.learningSocietyBiz.save(a);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("/deleteSociety")
    public
    @ResponseBody
    String deleteSociety(@RequestBody List<String> arr) {
        System.out.println(arr);
        if (arr.size() > 0) {
            learningSocietyBiz.deleteByPrimaryKeys(arr);
            return GlobalConstant.DELETE_SUCCESSED;
        }

        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/list")
    public String list(Model model, SrmLearningSociety society, Integer currentPage, HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        if (StringUtil.isNotBlank(orgFlow)) {
            society.setOrgFlow(orgFlow);
            List<SrmLearningSociety> learningSocietyList = learningSocietyBiz.search(society);
            model.addAttribute("learningSocietyList", learningSocietyList);

            SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            sysDept.setOrgFlow(orgFlow);
            List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
            model.addAttribute("sysDeptList", sysDeptList);
        }
        return "/srm/society/societyList";
    }
}
