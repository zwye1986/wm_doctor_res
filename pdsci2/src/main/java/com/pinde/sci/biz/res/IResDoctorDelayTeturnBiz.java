package com.pinde.sci.biz.res;


import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *用于湖北退赔延期
 */
public interface IResDoctorDelayTeturnBiz {

    /**
     *
     * @param resDocotrDelayTeturn
     * @param orgFlowList 机构流水号
     * @param flags 用于查询resDocotrDelayTeturn.getAuditStatusId是通过或不通过
     * @return
     */
    List<ResDocotrDelayTeturn> searchInfo(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
            , List<String> docTypeList);
    List<ResDocotrDelayTeturn> searchInfo(Map<String,Object> map);

    /**
     * 根据主键查询表中信息
     * @param recordFlow
     * @return
     */
    ResDocotrDelayTeturn readInfo(String recordFlow);

    /**
     * insert或update
     * @param resDocotrDelayTeturn
     * @return
     */
    Integer save(ResDocotrDelayTeturn resDocotrDelayTeturn);

   Integer saveSynDoctorStatus(ResDocotrDelayTeturn resDocotrDelayTeturn);

    /**
     * 用于导出
     * @param resRecList
     * @param response
     * @param flag 区分导出的是审核后（Y）的数据还是审核前（）的数据
     * @throws IOException
     */
    void exportForBack(List<ResDocotrDelayTeturn> resRecList, HttpServletResponse response,String flag) throws IOException;

    //保存延期退培记录及对应附件
    int edit(ResDocotrDelayTeturn resDocotrDelayTeturn, PubFile pubFile);
    //保存延期退培记录及对应附件集合
    int edit(ResDocotrDelayTeturn resDocotrDelayTeturn, List<PubFile> pubFiles);

    int editJszy(ResDocotrDelayTeturn resDocotrDelayTeturn, ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    //根据doctorFlow查找延期、退培记录
    List<ResDocotrDelayTeturn> searchByDoctorFlow(String doctorFlow);

    List<Map<String,String>> findTrainCharts(List<String> orgFlowList, String sessionNumber, String speName, List<String> docTypeList,String resTrainingSpeId);
    List<Map<String,String>> findTrainCharts2(List<String> orgFlowList, String sessionNumber, String speName, List<String> docTypeList,String resTrainingSpeId,String resTrainingTypeId);
    List<Map<String,String>> findJszyTrainCharts(List<String> orgFlowList, String sessionNumber, String speName);

    List<ResDocotrDelayTeturn> searchInfoForUni(ResDocotrDelayTeturn docotrDelayTeturn, ResDoctor currdoctor);

    int checkBackTrain(ResDocotrDelayTeturn docotrDelayTeturn, com.pinde.core.model.ResDoctorRecruit recruit) throws DocumentException;

    int saveDelayInfo(ResDocotrDelayTeturn docotrDelayTeturn);

    List<ResDocotrDelayTeturn> searchDelayInfo(ResDocotrDelayTeturn delay4Search);

    List<Map<String,String>> searchDelayInfoByParamMap(Map<String, Object> paramMap);

    List<Map<String,String>> searchBackTrainInfoByParamMap(Map<String, Object> paramMap);

    String checkFile(MultipartFile file);

    String saveCheckFileToDirs(MultipartFile file, String changeRecruitFile, String typeId);

    int checkDelayInfo(ResDocotrDelayTeturn delayInfo);

    List<ResDocotrDelayTeturn> backTrainCheck(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,
                                              List<String> flags, String medicineTypeId,String workOrgId);

    List<ResDocotrDelayTeturn> backTrainCheck(Map<String,Object> map);

    ResDocotrDelayTeturn findTeturnInfo(String recruitFlow);

    ResDocotrDelayTeturn findDelayInfo(String recruitFlow);

    List<ResDocotrDelayTeturn> searchInfoNew(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
            , List<String> docTypeList);

    List<ResDocotrDelayTeturn> searchInfo2(ResDocotrDelayTeturn docotrDelayTeturn, List<String> orgFlowList, List<String> flags, List<String> docTypeList, List<String> sessionNumbers);
}
	
	
