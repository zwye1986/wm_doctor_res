package com.pinde.sci.dao.base;

import com.pinde.core.model.SysUser;

import java.util.List;

public interface SysUserRegisterMapper {

    List<SysUser> selectByUserPhoneAndIsVerify(String userPhone);

    SysUser findByUserPhone(String userCode);

    List<SysUser> checkPhoneIsVerify(String userPhone);

    List<SysUser> selectByUserEmail(String userEmail);

    List<SysUser> selectByUserCode(String userEmail);
}
