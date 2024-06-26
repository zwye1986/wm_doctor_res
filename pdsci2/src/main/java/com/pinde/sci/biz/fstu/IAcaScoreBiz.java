package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.*;
import org.docx4j.wml.P;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IAcaScoreBiz {
    //查询评分层级及学分配置记录
    List<FstuScoreConfig> searchScoreCfgList(FstuScoreConfig cfg);
    //查询配置单条记录
    FstuScoreConfig searchScoreCfgByFlow(String recordFlow);
    //新增配置记录
    int saveScoreCfg(FstuScoreConfig cfg);
    //删除配置记录
    int delConfigByFlow(String recordFlow);
    //查询我的学分记录
    List<FstuScoreMain> searchScoreList(FstuScoreMain score,String roleFlag);
    //查询我的学分单条记录
    FstuScoreMain searchScoreByFlow(String recordFlow);
    //新增我的学分记录及保存上传文件路径
    int saveScore(FstuScoreMain score,List<String> urlLst);
    //删除我的学分记录
    int delScoreByFlow(String recordFlow);
    //提交我的学分记录
    int submitScoreByFlow(String recordFlow,String roleFlag);
    //我的学分审核操作
    int auditScore(String recordFlow,String auditStatusId,String roleFlag);
    //查询我的学分上传文件
    List<PubFile> queryFileList(String recordFlow);
    //根据条件查询学分记录
    List<Map<String,Object>> scoreMains(Map<String,String> paramMap);
    //获取某一年各项目大类总得分
    Map<String,Double> calculation(String year,String firstScoreTypeId);

    int importScoreFromExcel(MultipartFile file);

    /**
     * 保存我的学分及附件信息
     * @param score
     * @return
     */
    int saveScoreAndFile(FstuScoreMain score,List<PubFile> fileList);
    //余杭项目，学分查询
    List<FstuUserScore> searchScoreList(FstuUserScore score);
    int saveUserScore(FstuUserScore score);
    FstuUserScore searchUserScoreByFlow(String userFlow);
    //flag为登记学分和进修申请的标识
    List<SysUser> searchUserNotInScore(String orgFlow,String flag);
    int saveUserStudy(FstuUserStudy score);
    List<FstuUserStudy> searchStudyList(FstuUserStudy study);
    FstuUserStudy searchUserStudyByFlow(String userFlow);
}
