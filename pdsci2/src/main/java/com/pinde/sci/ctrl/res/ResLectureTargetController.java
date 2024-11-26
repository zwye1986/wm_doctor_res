package com.pinde.sci.ctrl.res;

import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeachingActivityTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/lectureTarget")
public class ResLectureTargetController extends GeneralController {

    @Autowired
    private IJsResActivityTargetBiz activityTargeBiz;

    /**
     * 教学活动指标
     */
    @RequestMapping(value = "/manageMain")
    public String manageMain(Model model, Integer currentPage, HttpServletRequest request) {
        return "res/activity/target/main";
    }

    @RequestMapping(value = "/add")
    public String add(Model model, HttpServletRequest request, String targetFlow) {
        TeachingActivityTarget target = activityTargeBiz.readByFlow(targetFlow);
        model.addAttribute("target", target);
        return "res/activity/target/addNew";
    }

    @RequestMapping(value = "/list")
    public String list(Model model, Integer currentPage, String targetName, String targetType, String activityTypeId, HttpServletRequest request) {
        List<SysDict> dictList = DictTypeEnum.ActivityType.getSysDictList();
        SysUser curUser = GlobalContext.getCurrentUser();
        Map<String, String> param = new HashMap<>();
        param.put("activityTypeId", activityTypeId);
        param.put("targetType", targetType);
        param.put("orgFlow", curUser.getOrgFlow());
        if (StringUtil.isNotBlank(targetName)) {
            targetName = targetName.trim();
        }
        param.put("targetName", targetName);
        if (currentPage == null) {
            currentPage = 1;
        }
        Integer currentPageSize = getPageSize(request);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentPageSize", currentPageSize);
        PageHelper.startPage(currentPage, currentPageSize);
        List<TeachingActivityTarget> targets = activityTargeBiz.listNew(param);
        model.addAttribute("targets", targets);
        return "res/activity/target/mainNew";
    }

    @RequestMapping(value = "/saveAdd")
    @ResponseBody
    public String saveAdd(Model model, TeachingActivityTarget activityTarget) {
        if (StringUtil.isBlank(activityTarget.getTargetName())) {
            return "请填写指标名称！";
        }
        SysUser curUser = GlobalContext.getCurrentUser();
        // 同一类型下的指标名称是否存在
        TeachingActivityTarget target = activityTargeBiz.readByNameNew(curUser.getOrgFlow(), activityTarget.getTargetName().trim(), activityTarget.getTargetType());
        if (null != target) {
            if (!target.getTargetFlow().equals(activityTarget.getTargetFlow())) {
                return "指标名称已存在，请修改后保存！";
            }
        }
        activityTarget.setActivityTypeName(DictTypeEnum.ActivityType.getDictNameById(activityTarget.getActivityTypeId()));
        activityTarget.setOrgFlow(curUser.getOrgFlow());
        activityTarget.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        int num = activityTargeBiz.saveTargetNew(activityTarget);
        if (num == 0) {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/delTarget")
    @ResponseBody
    public String delTarget(Model model, String targetFlow) {
        if (StringUtil.isBlank(targetFlow)) {
            return "评价指标流水号为空";
        }
        int c = activityTargeBiz.delTarget(targetFlow);
        if (c == 0) {
            return GlobalConstant.DELETE_FAIL;
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }
}
