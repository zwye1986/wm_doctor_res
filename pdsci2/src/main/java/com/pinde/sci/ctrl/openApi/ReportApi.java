package com.pinde.sci.ctrl.openApi;

import cn.hutool.core.util.ObjectUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.model.mo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/12/03/15:17
 * @Description: 对外提供的接口：：月度报表
 */
@RestController
@RequestMapping("/jsres/open")
@Slf4j
public class ReportApi {

    @Resource
    private SysDeptExtMapper deptExtMapper;
    @GetMapping("/loginInfo")
    public Map<String,List<String>> getLoginInfo(HttpServletRequest request){
        Map<String, List<String>> result = new HashMap<>();
        result.put("orgFlow", Arrays.asList(new String[]{"-1"}));
        result.put("tenantId", Arrays.asList(new String[]{"10000"}));
        result.put("spe", Arrays.asList(new String[]{"-1"}));
        result.put("dept",Arrays.asList(new String[]{"-1"}));
        HttpSession session = request.getSession();
        log.info("session的值是：：：：：：：：{}",session.getId());
        if (null == session) {
            return result;
        }
        SysUser sysUser = (SysUser) request.getSession().getAttribute(GlobalConstant.CURRENT_USER);
        if (ObjectUtil.isEmpty(sysUser)) {
            return result;
        }
        String orgFlow = "-1";
        if (StringUtils.isNotEmpty(sysUser.getOrgFlow())) {
            orgFlow = sysUser.getOrgFlow();
        }
        List<String> currRoleList = (List<String>) request.getSession().getAttribute(GlobalConstant.CURRENT_ROLE_LIST);
        if (currRoleList != null && currRoleList.size() > 0) {
            for (String roleFlow : currRoleList) {
                if (StringUtil.isNotBlank(roleFlow)) {
                    if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
                        //省级部门的角色不分机构和专业，查询所有的数据
                        result.put("orgFlow", null);
                        result.put("spe", null);
                        result.put("dept",null);
                        return result;
                    } else if (roleFlow.equals(InitConfig.getSysCfg("res_quality_control_role_flow"))) {//医院管理员
                        result.put("orgFlow", Arrays.asList(new String[]{orgFlow}));
                        result.put("spe", null);
                        result.put("dept",null);
                        return result;
                    } else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                        result.put("orgFlow", Arrays.asList(new String[]{orgFlow}));
                        result.put("spe", null);
                        result.put("dept",null);
                        return result;
                    }  else if (roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_admin_role_flow")) ||
                            roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_adminSecretary_role_flow"))) {//专业基地管理员
                        result.put("orgFlow", Arrays.asList(new String[]{orgFlow}));
                        //查询当前账号的专业
                        String speId = sysUser.getResTrainingSpeId();
                        if (StringUtils.isNotEmpty(speId)) {
                            result.put("spe", Arrays.asList(new String[]{speId}));
                        }
                        result.put("dept",null);
                        return result;
                    }else {
                        result.put("orgFlow", Arrays.asList(new String[]{orgFlow}));
                        //查询当前账号的专业
                        String speId = sysUser.getResTrainingSpeId();
                        if (StringUtils.isNotEmpty(speId)) {
                            result.put("spe", Arrays.asList(new String[]{speId}));
                        }
                        //查询当前用户关联的科室id
                        List<String> strings = deptExtMapper.deptFlowsByUserId(sysUser.getUserFlow());
                        result.put("dept",strings);
                        return result;
                    }
                }
            }
        }
        return result;
    }
}
