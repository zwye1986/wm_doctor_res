package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.JsresBaseEvaluation;
import com.pinde.sci.model.mo.JsresBaseEvaluationFile;
import com.pinde.sci.model.mo.JsresBaseEvaluationScore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface IJsResBaseEvaluationBiz {

    List<JsresBaseEvaluation> searchBaseEvaluationList(JsresBaseEvaluation jsresBaseEvaluation);

    /**
     * 根据主表RecordFlow查询EvaluationFileList
     * @param evaluationFlow
     * @return
     */
    List<JsresBaseEvaluationFile> searchBaseEvaluationFileList(String evaluationFlow);

    /**
     * 根据主表RecordFlow查询EvaluationScoreList
     * @param evaluationFlow
     * @return
     */
    List<JsresBaseEvaluationScore> searchBaseEvaluationScoreList(String evaluationFlow);

    /**
     * 根据主表RecordFlow和Item保存专家单项评分
     * @param evaluationFlow
     * @return
     */
    int saveExpertScoreByItem(String evaluationFlow,String itemId,String speScore);


    /**
     * 根据jsresBaseEvaluationScore查询EvaluationScore
     * @param jsresBaseEvaluationScore
     * @return
     */
    JsresBaseEvaluationScore searchBaseEvaluationScoreByItemId(JsresBaseEvaluationScore jsresBaseEvaluationScore);

    /**
     * 保存评分信息
     * @param jsresBaseEvaluationScore
     */
    void saveJsresBaseEvaluationScore(JsresBaseEvaluationScore jsresBaseEvaluationScore);


    /**
     * 查询适合条件的全部评分数据
     * @param jsresBaseEvaluationScore
     * @return
     */
    List<JsresBaseEvaluationScore> searchBaseEvaluationScore(JsresBaseEvaluationScore jsresBaseEvaluationScore);


    /**
     * 检查上传图片的类型及大小
     *
     * @param uploadFile
     * @return
     */
    String checkImg(MultipartFile uploadFile);

    /**
     * 保存文件至指定的目录
     *
     * @param oldFolderName
     * @param file
     * @param folderName
     * @return
     */
    String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName,String orgFlow,String planYear,String itemId );


    /**
     * 新建评分
     * @param jsresBaseEvaluation
     */
    void saveJsResBaseEvaluation(JsresBaseEvaluation jsresBaseEvaluation);

    /**
     * 保存附件信息
     * @param jsresBaseEvaluationFile
     */
    void saveJsresBaseEvaluationFile(JsresBaseEvaluationFile jsresBaseEvaluationFile);

    JsresBaseEvaluationFile searchBaseEvaluationFileByRecordFlow(String recordFlow);

    /**
     * 查询所有基地并根据总分降序排序
     * @param map
     * @return
     */
    List<JsresBaseEvaluation> searchAllBaseEvaluation(Map<String,Object> map);

    /**
     * 查询协同基地并根据总分降序排序
     * @param jointOrgFlowList
     * @return
     */
    List<JsresBaseEvaluation> searchJointBaseEvaluation(List<String> jointOrgFlowList);
}
