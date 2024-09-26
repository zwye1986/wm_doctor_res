package com.pinde.res.biz.eval;

import com.pinde.res.ctrl.eval.EvalResultBean;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IEvalAppBiz {
	/**
	 * 读取一条用户信息
	 * @param userFlow
	 * @return
     */
	SysUser readSysUser(String userFlow);

	/**
	 * 获取该用户的所有角色
	 * @param userFlow
	 * @return
	 */
	List<SysUserRole> getSysUserRole(String userFlow);

	/**
	 * 获取配置
	 * @param code
	 * @return
	 */
	String getCfgByCode(String code);


}
  