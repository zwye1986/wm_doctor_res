package com.pinde.sci.biz.edu;

import com.pinde.sci.model.mo.EduCourseFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEduCourseFileBiz {
	/**
	 * 保存资料
	 * @param courseFile
	 * @return
	 */
	int saveCourseFile(EduCourseFile courseFile);
	
	/**
	 * 查询
	 * @param courseFile
	 * @return
	 */
	List<EduCourseFile> searchCourseFileList(EduCourseFile courseFile);
	
	/**
	 * 保存文件
	 * @param courseFile
	 * @return
	 * @throws Exception 
	 */
	int operateFile(MultipartFile file, String courseFlow) throws Exception;
	
	/**
	 * 删除图片
	 * @param courseFlow
	 * @return
	 */
	int delCourseFile(String recordFlow);
	
	/**
	 * 获取一条课程资料
	 * @return
	 */
	EduCourseFile readCourseFile(String recordFlow);
}
