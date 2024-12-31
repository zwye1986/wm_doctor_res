package com.pinde.sci.biz.res;

import com.pinde.core.model.ResAssessCfg;
import com.pinde.core.model.ResAssessCfgForm;
import com.pinde.core.model.ResAssessCfgItemForm;
import com.pinde.core.model.ResAssessCfgTitleForm;

import java.util.List;
import java.util.Map;

public interface IResAssessCfgBiz {
	
	/**
	 * 保存
	 * @param assessCfg
	 * @return
	 */
	int saveAssessCfg(ResAssessCfg assessCfg);
	
	/**
	 * 获取一条记录
	 * @param cfgFlow
	 * @return
	 */
	ResAssessCfg readResAssessCfg(String cfgFlow);
	
	/**
	 * 查询
	 * @param assessCfg
	 * @return
	 */
	List<ResAssessCfg> searchAssessCfgList(ResAssessCfg assessCfg);
	
	/**
	 * 编辑考核指标标题
	 * @param cfgForm
	 * @param titleForm
	 * @return
	 * @throws Exception 
	 */
	int editAssessCfgTitle(ResAssessCfg assessCfg, ResAssessCfgTitleForm titleForm) throws Exception;
	
	/**
	 * 删除考核指标标题
	 * @param cfgFlow
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	int deleteTitle(String cfgFlow, String id) throws Exception;
	
	
	
	/**
	 * 保存考核指标列表
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	int saveAssessItemList(ResAssessCfgForm form) throws Exception;
	
	/**
	 * 修改考核指标
	 * @param cfgFlow 
	 * @param itemForm
	 * @return
	 * @throws Exception 
	 */
	int modifyItem(String cfgFlow, ResAssessCfgItemForm itemForm) throws Exception;
	
	/**
	 * 删除考核指标
	 * @param cfgFlow
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	int deleteItem(String cfgFlow, String id) throws Exception;

	/**
	 * 直接获取配置内的内容
	 * @param cfgCodeId
	 * @return
	 */
	List<ResAssessCfgTitleForm> getParsedGrade(String cfgCodeId);

	Map<String,List<ResAssessCfgTitleForm>> getParsedGradeMap(String cfgCodeId);

	List<ResAssessCfgTitleForm> readForm(String cfgFlow);
	
	int updateByPrimaryKeySelective(ResAssessCfg resAssessCfg);

	List<ResAssessCfg> selectByExample(ResAssessCfg resAssessCfg);

	List<ResAssessCfg> selectByExampleWithBLOBs(ResAssessCfg resAssessCfg);

}
