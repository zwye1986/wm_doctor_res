package com.pinde.res.biz.hzyy;

import com.pinde.res.ctrl.upload.FileForm;

import java.util.List;
import java.util.Map;

public interface IHzyyTutorBiz {

	Map<String,String> readDicItemByName(String dicName);

	List<Map<String,String>> stuImgList(String userFlow, String startTime, String endTime);

	List<Map<String,String>> stuSchImgList(Map<String, Object> searchMap);

	int delFile(String fileFlow);

	Map<String,String> readFile(String fileFlow);

	int saveFile(FileForm form, Map<String, Object> param);
}
