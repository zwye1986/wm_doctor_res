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
	 * 根据用户名查询用户
	 * @param userCode
	 * @return
	 */
	SysUser getUserByCode(String userCode);
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

	/**
	 * 获取专家可以评估的所有基地
	 * @param evalYear
	 * @param userFlow
     * @return
     */
	List<SysOrg> getExpertEvalOrg(String evalYear, String userFlow);

	/**
	 * 获取每年第一个评估配置
	 * @param evalYear
	 * @return
     */
	ExpertEvalCfg getExpertBaseCfg(String evalYear);

	/**
	 * 获取专家可评估的配置列表
	 * @param cfgFlow
	 * @param evalYear
	 * @param userFlow
     * @return
     */
	List<ExpertEvalCfg> getExpertCfgChildrens(String cfgFlow, String evalYear, String userFlow, String orgFlow);

	ExpertEvalResult getOrgCfgEvalReustl(String evalYear, String orgFlow, String cfgFlow);

	ExpertEvalCfg readCfgByFlow(String cfgFlow);

	Map<String,Object> parseContent(String evalContent);

	int saveCfgResultByFile(FileForm form, String evalScore, String evalYear, String userFlow, String orgFlow, String cfgFlow);

	int saveCfgResult(ExpertEvalResult result, SysUser user);

	int saveCfgResultNotFile(EvalResultBean resultBean, String evalYear, String userFlow, String orgFlow, String cfgFlow) throws DocumentException;

	int saveCfgResultNotFile2(HttpServletRequest request, String evalYear, String userFlow, String orgFlow, String cfgFlow);
}
  