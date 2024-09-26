package com.pinde.res.biz.pushExam;

import com.pinde.sci.model.mo.SysUser;

public interface IPushExamBiz {

	String getCfgCode(String code);

	SysUser readUserByFlow(String userFlow);

	void updateUser(SysUser user);
}
  