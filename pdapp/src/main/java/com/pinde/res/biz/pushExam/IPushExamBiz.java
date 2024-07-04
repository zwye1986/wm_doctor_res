package com.pinde.res.biz.pushExam;

import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPushExamBiz {

	String getCfgCode(String code);

	SysUser readUserByFlow(String userFlow);

	void updateUser(SysUser user);
}
  