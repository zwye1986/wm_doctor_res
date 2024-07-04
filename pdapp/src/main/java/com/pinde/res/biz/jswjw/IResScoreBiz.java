package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResPassScoreCfg;
import com.pinde.sci.model.mo.ResScore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResScoreBiz {

	/**
	 *	根据主键scoreFlow查询单条数据
	 * @param scoreFlow
	 * @return
	 */
	ResScore searchByScoreFlow(String scoreFlow);

	/**
	 * 根据主键doctorFlow查询多条数据
	 * @param doctorFlow
	 * @return
	 */
	List<ResScore> searchByScoreList(String doctorFlow);
	List<ResScore> selectByExampleWithBLOBs(String doctorFlow);


	/**
	 * 根据process获取分数
	 * @param processFlow
	 * @return
	 */
	ResScore getScoreByProcess(String processFlow);

	List<ResPassScoreCfg> getScoreCfgList();

}
