package com.pinde.sci.ctrl.eval;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.ResOrgSpe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于评估系统与评估专业相关的操作
 */
@Controller
@RequestMapping("/eval/orgSpe")
public class EvalOrgSpeController extends GeneralController {

    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;

    @RequestMapping("/orgSpeManage")
    public String orgSpeManage(Model model,ResOrgSpe resOrgSpe){
        String orgFlow = resOrgSpe.getOrgFlow();
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("orgName", resOrgSpe.getOrgName());
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgSpe serach = new ResOrgSpe();
            serach.setOrgFlow(orgFlow);
            serach.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
            model.addAttribute("resBaseList", resBaseList);
            if(resBaseList != null && !resBaseList.isEmpty()){
                Map<String, ResOrgSpe> orgSpeMap = new HashMap<String, ResOrgSpe>();
                for(ResOrgSpe os : resBaseList){
                    String key = os.getSpeTypeId() + os.getSpeId();
                    orgSpeMap.put(key, os);
                }
                model.addAttribute("orgSpeMap", orgSpeMap);
            }
        }
        return "eval/spe/baseSpecialList";
    }

    /**
     * 保存基地专业信息
     * @param orgSpe
     * @return
     */
    @RequestMapping("/saveOrgSpeManage")
    @ResponseBody
    public String saveOrgSpeManage(ResOrgSpe orgSpe){
        int result = resOrgSpeBiz.saveOrgSpeManage(orgSpe);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
}
