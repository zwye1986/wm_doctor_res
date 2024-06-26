package com.pinde.sci.biz.exam;

import com.pinde.sci.model.exam.ExamBookImpExt;
import com.pinde.sci.model.exam.ExamBookRegistInfo;
import com.pinde.sci.model.exam.ExamQuestionExt;
import com.pinde.sci.model.exam.QuestionFileVerifyInfo;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IBookImpBiz {

	List<ExamBookImp> search(ExamBookImp imp);

	List<ExamBookImpDetail> searchDetail(String bookImpFlow);

	List<ExamBookImpDetail> searchDetail(ExamBookImpDetail bookImpDetail);

	List<ExamBookReloadRec> searchBookReloadRecsByBookFlow(String bookFlow);

	/**
	 * 查询导入内容，包含文件
	 * @param imp
	 * @param impDetailList
	 */
//	public List<ExamBookImpDetail> searchDetailWithFileContent(String bookImpFlow);

	void addImp(ExamBookImp imp, List<ExamBookImpDetail> impDetailList);

	void modifyImp(ExamBookImp imp, List<ExamBookImpDetail> impDetailList);

	/**
	 * 重新上传试题文件
	 * @param impDetailFlow
	 * @param questionFile
	 */
	void reuploadQuestion(String impDetailFlow, MultipartFile questionFile);

	void submit(String bookImpFlow);
	
	/**
	 * 根据主键查询导入信息
	 * @param bookImpFlow
	 * @return
	 */
	ExamBookImp findBookImpByImpFlow(String bookImpFlow);
	
	/**
	 * 根据主键查询导入的详细信息
	 * @param bookImpDetailFlow
	 * @return
	 */
	ExamBookImpDetail findBookImpDetailByDetailFlow(String bookImpDetailFlow);
	
	/**
	 * 获取上传文件的内容
	 * @param detailFlow
	 * @return
	 */
//	public String findQuestionContentByimpDetailFlow(String detailFlow);
	
	/**
	 * 解析题目类型的文件，将文件的信息设置到要保存的detail中
	 * @param detail
	 * @param questionTypeFile
	 */
	void parseQuestionFile(ExamBookImpDetail detail, MultipartFile questionTypeFile);
	
	/**
	 * 解析上传的题目文件
	 * @param questionTypeFile
	 * @return Map<第几题 ,Map<行号,内容>> 其中 Map<行号,内容>是LinkedHashMap 是有顺序的
	 */
	Map<Integer, Map<Integer, String>> parseQuestionFile(MultipartFile questionTypeFile);
	
	/**
	 * 根据题目类型做文件校验
	 * @param questionTypeId
	 * @param questionTypeFile
	 * @return 题目文件校验信息
	 */
	QuestionFileVerifyInfo verifyQuestionFile(String questionTypeId, MultipartFile questionTypeFile);
	
	/**
	 * 查询已经导入的信息
	 * @param impExt
	 * @return
	 */
	List<ExamBookImpExt> search(ExamBookImpExt impExt);
	
	/**
	 * 对导入的信息做更新操作，该操作包过校验和审核
	 * @param bookImp
	 */
	void operate(ExamBookImp bookImp);
	
	/**
	 * 根据主键查询导入信息(包含书的信息)
	 * @param bookImpFlow
	 * @return
	 */
	ExamBookImpExt findExamBookImpByExamBookImpFlow(String bookImpFlow);
	
	/**
	 * 获取书目的登记信息
	 * @param bookFlow
	 * @return
	 */
	ExamBookRegistInfo getBookRegistInfo(String bookFlow);
	
	/**
	 * 登记书目扫描信息
	 * @param bookScanInfo
	 */
	void registBookScanInfo(ExamBookScanInfo bookScanInfo);
	
	/**
	 * 登记识别信息
	 * @param recognizeInfo
	 */
	void registRecognizeInfo(ExamBookRecognizeInfo recognizeInfo);
	
	/**
	 * 登记排版信息
	 * @param bookComposInfo
	 */
	void registComposInfo(ExamBookComposInfo bookComposInfo);

//	List<ExamQuestionExt> createQuestionsfortest(
//			ExamBookImpDetail bookImpDetail, ExamBookImp bookImp);

	/**
	 * 根据导入的信息创建试题
	 * @param bookImpDetail
	 * @param bookImp
	 * @return
	 */
	List<ExamQuestionExt> createQuestions(ExamBookImpDetail bookImpDetail,
			ExamBookImp bookImp);
	
	/**
	 * 删除导入文件
	 * @param impDetailFlow
	 */
	void delImpDetailByImpDetailFlow(String impDetailFlow);
	
}
