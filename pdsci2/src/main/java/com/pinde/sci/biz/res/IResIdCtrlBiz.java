package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResIdctrlDetail;
import com.pinde.sci.model.mo.ResIdctrlMain;

import java.util.List;
import java.util.Map;

public interface IResIdCtrlBiz {
	//根据条件查询分配记录
	List<ResIdctrlMain> searchMain(ResIdctrlMain idctrlMain);
	//根据条件查询分配记录子记录
	List<ResIdctrlDetail> searchDetail(ResIdctrlDetail idctrlDetail);
	//新增ID
	int saveIds(ResIdctrlMain resIdctrlMain);
	//编辑ID
	int editId(ResIdctrlDetail idctrlDetail);
	//根据主键查询ID
	ResIdctrlDetail readDetail(String recordFlow);
	//查询学员_ID列表
	List<Map<String,Object>> doctorIdList(Map<String,Object> paramMap);
	//为学员绑定ID
	int bindingID(ResIdctrlDetail detail);
}
