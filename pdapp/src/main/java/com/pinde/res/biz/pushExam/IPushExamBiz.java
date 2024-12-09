package com.pinde.res.biz.pushExam;

import com.pinde.core.model.SysUser;

public interface IPushExamBiz {

	String getCfgCode(String code);

	SysUser readUserByFlow(String userFlow);

	void updateUser(SysUser user);
}
  