package com.pinde.sci.biz.sys.impl;

import com.pinde.sci.biz.sys.IEdcUserRegBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.common.util.WeixinQiYeUtil;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Transactional(rollbackFor = Exception.class)
public class EdcUserRegBizImpl implements IEdcUserRegBiz {

    private static Logger logger = LoggerFactory.getLogger(EdcUserRegBizImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void activatSysUser(SysUser sysUser) {
        sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), sysUser.getUserPasswd()));
        //修改用户信息
        sysUser.setStatusId(UserStatusEnum.Activated.getId());
        sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
        GeneralMethod.setRecordInfo(sysUser, false);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))) {
            sysUser = sysUserMapper.selectByPrimaryKey(sysUser.getUserFlow());
            boolean result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), sysUser);
            logger.debug("wei xin qi ye createUser is " + result);
        }
    }

}
