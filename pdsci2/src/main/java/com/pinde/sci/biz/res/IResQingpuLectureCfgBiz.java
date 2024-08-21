package com.pinde.sci.biz.res;

import com.pinde.sci.form.res.QingpuLectureCfgExt;
import com.pinde.sci.form.res.QingpuLectureCfgItemExt;
import com.pinde.sci.form.res.QingpuLectureCfgTitleExt;
import com.pinde.sci.model.mo.QingpuLectureEvalCfg;

import java.util.List;

public interface IResQingpuLectureCfgBiz {
	
	/**
	 * 保存
	 */
	int saveQingpuLectureCfgCfg(QingpuLectureEvalCfg lectureEvalCfg);
	
	/**
	 * 获取一条记录
	 */
	QingpuLectureEvalCfg readLectureEvalCfg(String recordFlow);
	
	/**
	 * 查询
	 */
	List<QingpuLectureEvalCfg> searchLectureEvalCfgList(QingpuLectureEvalCfg lectureEvalCfg);
	
	/**
	 * 编辑考核指标标题
	 */
	int editLectureEvalCfgTitle(QingpuLectureEvalCfg lectureEvalCfg, QingpuLectureCfgTitleExt titleForm) throws Exception;

	/**
	 * 删除考核指标标题
	 */
	int deleteTitle(String recordFlow, String id) throws Exception;

	/**
	 * 保存考核指标列表
	 */
	int saveLectureCfgItemList(QingpuLectureCfgExt form) throws Exception;

	/**
	 * 修改考核指标
	 */
	int modifyItem(String recordFlow, QingpuLectureCfgItemExt itemForm) throws Exception;

	/**
	 * 删除考核指标
	 */
	int deleteItem(String cfgFlow, String id) throws Exception;


}
