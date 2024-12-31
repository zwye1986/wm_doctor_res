package com.pinde.sci.ctrl.cfg;


import com.pinde.core.common.sci.dao.ResPowerCfgMapper;
import com.pinde.core.common.sci.dao.SysUserRoleMapper;
import com.pinde.core.model.ResPowerCfg;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.model.SysUserRoleExample;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/res/powerCfg")
public class ResPowerCfgController  extends GeneralController{

    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;

    public static ResPowerCfg resPowerCfgMap (String cfgCode){
        ResPowerCfgMapper resPowerCfgMapper = SpringUtil.getBean(ResPowerCfgMapper.class);
        ResPowerCfg resPowerCfg = resPowerCfgMapper.selectByPrimaryKey(cfgCode);
//        String value = "";
//        if(resPowerCfg != null){
//            value = resPowerCfg.getCfgValue();
//        }
        return resPowerCfg;
    }
    public static boolean isFreeGlobal (){
        boolean isFree=false;
        if(GlobalContext.getCurrentUser()!=null) {
            SysUserRoleMapper sysUserRoleMapper = SpringUtil.getBean(SysUserRoleMapper.class);
            SysUserRoleExample example = new SysUserRoleExample();
            SysUserRoleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            criteria.andWsIdEqualTo(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            criteria.andUserFlowEqualTo(GlobalContext.getCurrentUser().getUserFlow());
            List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
            if (userRoleList != null && userRoleList.size() > 0) {
                for (SysUserRole sysUserRole : userRoleList) {
                    if (InitConfig.getSysCfg("res_free_global_role_flow").equals(sysUserRole.getRoleFlow())) {
                        isFree = true;
                        break;
                    }
                }
            }
        }
        return isFree;
    }
    public static boolean hasSchRole (String userFlow){
        boolean hasSchRole=false;
        if(StringUtil.isNotBlank(userFlow)) {
            SysUserRoleMapper sysUserRoleMapper = SpringUtil.getBean(SysUserRoleMapper.class);
            SysUserRoleExample example = new SysUserRoleExample();
            SysUserRoleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            criteria.andWsIdEqualTo(com.pinde.core.common.GlobalConstant.SCH_WS_ID);
            criteria.andUserFlowEqualTo(userFlow);
            List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
            if (userRoleList != null && userRoleList.size() > 0) {
                hasSchRole = true;
            }
        }
        return hasSchRole;
    }

    @RequestMapping(value="/saveOne",method= RequestMethod.POST)
    @ResponseBody
    public String saveOne(HttpServletRequest request){
        String [] cfgCodes = request.getParameterValues("cfgCode");
        if(cfgCodes!=null){
            List<ResPowerCfg> cfgList = new ArrayList<>();
            for(String cfgCode : cfgCodes){
                String cfgValue = request.getParameter(cfgCode);
                String cfgDesc = request.getParameter(cfgCode+"_desc");
                ResPowerCfg cfg = new ResPowerCfg();
                    cfg.setCfgCode(cfgCode);
                    cfg.setCfgValue(cfgValue);
                    cfg.setCfgDesc(cfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    cfgList.add(cfg);
            }
            int result = resPowerCfgBiz.saveList(cfgList);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
}
