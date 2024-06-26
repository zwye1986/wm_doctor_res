package com.pinde.sci.ctrl.srm;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;

import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by www.0001.Ga on 2017-08-21.
 */
@Controller
@RequestMapping("/srm/proj/reAudit")
public class ProjReAuditController extends GeneralController {
    @Autowired
    private IProjReAuditBiz projReAuditBiz;

    /**
     * 卫计委通过后，撤销-申报重申
     * @param projFlow
     * @return
     */
    @RequestMapping("/reAuditOption")
    @ResponseBody
    public String reAuditOption(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.reAuditOption(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 立项通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/approveReAudit")
    @ResponseBody
    public String approveReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.approveReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 合同审核通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/contractReAudit")
    @ResponseBody
    public String contractReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.contractReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 进展报告通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/scheduleReAudit")
    @ResponseBody
    public String scheduleReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.scheduleReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 变更申请通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/changeReAudit")
    @ResponseBody
    public String changeReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.changeReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 验收报告通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/completeReAudit")
    @ResponseBody
    public String completeReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.completeReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 中止报告通过后撤销重审
     * @param projFlow
     * @return
     */
    @RequestMapping("/terminateReAudit")
    @ResponseBody
    public String terminateReAudit(String projFlow){
        if(StringUtil.isNotBlank(projFlow)){
            return projReAuditBiz.terminateReAudit(projFlow);
        }
        return GlobalConstant.OPRE_FAIL;
    }

}
