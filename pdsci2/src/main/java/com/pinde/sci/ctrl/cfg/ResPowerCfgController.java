package com.pinde.sci.ctrl.cfg;


import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResPowerCfgMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.model.mo.ResPowerCfg;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.mo.SysUserRoleExample;
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
            SysUserRoleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andWsIdEqualTo(GlobalConstant.RES_WS_ID);
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
            SysUserRoleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andWsIdEqualTo(GlobalConstant.SCH_WS_ID);
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
                    cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    cfgList.add(cfg);
            }
            int result = resPowerCfgBiz.saveList(cfgList);
            if(GlobalConstant.ZERO_LINE != result){
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }
}
