package com.pinde.res.biz.hzyy.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyTutorBiz;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.res.dao.hzyy.ext.HzyyAppMapper;
import com.pinde.res.dao.hzyy.ext.HzyyTutorAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class HzyyTutorBizImpl implements IHzyyTutorBiz {
	@Autowired
	private HzyyAppMapper appMapper;
	@Autowired
	private HzyyTutorAppMapper tutorAppMapper;

	@Value("#{configProperties['hzyy.upload.base.dir']}")
	public  String baseDir;
	@Value("#{configProperties['hzyy.upload.dir']}")
	public  String uploadDir;

	@Override
	public Map<String, String> readDicItemByName(String dicName) {
		return appMapper.readDicItemByName(dicName);
	}

	@Override
	public List<Map<String, String>> stuImgList(String userFlow, String startTime, String endTime) {
		return tutorAppMapper.stuImgList( userFlow,  startTime,  endTime);
	}

	@Override
	public List<Map<String, String>> stuSchImgList(Map<String, Object> searchMap) {
		int start = ((int) searchMap.get("pageIndex")-1)*(int)searchMap.get("pageSize")+1;
		int end = (int)searchMap.get("pageIndex")*(int)searchMap.get("pageSize");
		searchMap.put("start",start);
		searchMap.put("end",end);
		return tutorAppMapper.stuSchImgList(searchMap);
	}

	@Override
	public int delFile(String fileFlow) {
		return tutorAppMapper.delFile(fileFlow);
	}

	@Override
	public Map<String, String> readFile(String fileFlow) {
		if(StringUtil.isNotBlank(fileFlow))
		{
			return tutorAppMapper.readFile(fileFlow);
		}
		return null;
	}

	@Override
	public int saveFile(FileForm form, Map<String, Object> param) {
		String filePath = baseDir + "/" ;
		MultipartFile uploadFile = form.getUploadFile();
		String fileUrl= (String) param.get("fileUrl");
		String dirUrl =	filePath +=fileUrl;
		//System.out.println("上传保存路径："+dirUrl);
		File dir = new File(dirUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dirUrl);
		try {
			uploadFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return tutorAppMapper.saveFile(param);
	}
}
