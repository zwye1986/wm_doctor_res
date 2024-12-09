package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.JsresPowerCfg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResPowerCfgExtMapper {
	List<JsresPowerCfg> selectPowerCfg(Map<String, String> paramMap);

	List<JsresPowerCfg> searchPowerCfg(@Param(value = "codes")List<String> codes);
}
