package com.pinde.sci.biz.res;

import com.pinde.core.model.ResAnnualAssessmentRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author william.li
 * @date 2017/12/29
 */
public interface IResAnnualCheckBiz {
    /**
     *年度考核成绩查询
     */
    List<ResAnnualAssessmentRecord> listAssessmentRecord(String userFlow);

    /**
     * 保存年度考核录入
     * @param assessmentRecord
     * @return
     */
    int saveAnnualCheck(ResAnnualAssessmentRecord assessmentRecord);

    int deleteMatertialUrlAndFile(ResAnnualAssessmentRecord assessmentRecord);

    int updateByUserFlowAndYear(ResAnnualAssessmentRecord assessmentRecord);
    /**
     * 保存文件至指定的目录
     *
     * @param oldFolderName
     * @param file
     * @param folderName
     * @return
     */
    String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName);

    /**
     * 导入年度考核成绩excel
     * @param file
     * @return
     * @throws Exception
     */
    int importCheckExcel(MultipartFile file) throws Exception;

}
