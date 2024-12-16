package com.pinde.sci.biz.pub;

import com.pinde.core.model.PubFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface IFileBiz {
	
	PubFile readFile(String fileFlow);

	List<PubFile> searchFile(List<String> fileFlows);

	void down(PubFile doc,final HttpServletResponse response) throws Exception;

	void downPubFile(PubFile doc,final HttpServletResponse response) throws Exception;

	void addFile(PubFile file);
	
	/**
	 * 新增文件 
	 * @param file userFlow
	 * @return  文件流水号
	 */
    String addFile(MultipartFile file, String userFlow);
	/**
	 * 新增或修改
	 * @param file
	 * @return
	 */
	int editFile(PubFile file);

	void downFile(File file, HttpServletResponse response) throws Exception;

	int saveFile(PubFile file);

    int deleteFile(List<String> fileFlow);

    List<PubFile> findFileByTypeFlow(String productType,String productFlow);

    List<PubFile> findFileByLikeTypeFlow(String productType,String productFlow);

    void deleteFileByTypeFlow(String productType,String productFlow);

	/**
	 * 根据ProductFlow查找文件
	 */
	List<PubFile> searchByProductFlow(String productFlow);

	PubFile readDocGraduationFile(PubFile search);

	List<PubFile> searchByProductFlowAndNotInFileFlows(String productType, List<String> fileFlows);

	void saveFiles(List<PubFile> pubFiles);

	String addAfterFile(MultipartFile file, String fileType, String productFlow);

	int deleteAfterFile(String fileFlow);

	List<PubFile> searchByProductFlowAndTypeAndNotInFileFlows(String recordFlow, List<String> fileFlows, String type);

	List<PubFile> findFileByDoctorFlow(String doctorFlow);

	String addDeptFile(MultipartFile file, String itemFlow);

	List<PubFile> findFileByTypeFlows(String productType, List<String> productFlows);

	PubFile readProductFile(String resultFlow, String fileType);

	List<PubFile> findFileByType(String productType,String productFlow,String testId);

	void downPubFile2(PubFile pubFile, HttpServletResponse response) throws Exception;
}
 