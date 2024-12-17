package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResBaseSpeDept;
import com.pinde.core.model.ResBaseSpeDeptData;
import com.pinde.core.model.ResBaseSpeDeptInfo;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptForm;
import com.pinde.core.model.ResTeacherTraining;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IDeptBasicInfoBiz {
    /**
     * 保存基地信息
     *
     * @throws IOException
     * @throws JAXBException
     */
    int saveBaseInfo(String flag, BaseSpeDeptForm baseSpeDeptForm, String index, String type, String[] fileFlows,
                     HttpServletRequest request,String infoType) throws Exception;

    ResBaseSpeDept readByRecordFlow(String recordFlow);

    ResBaseSpeDept readByOrgAndDept(String orgFlow,String deptFlow,String sessionNumber);

    ResBaseSpeDept readByOrgAndSpe(String orgFlow,String speFlow,String sessionNumber);

    List<ResBaseSpeDept> readByOrgFlowAndYear(String orgFlow,String sessionNumber);

    List<ResTeacherTraining> searchByOrgAndDept(String orgFlow,String deptFlow);

    List<ResTeacherTraining> searchByOrgAndSpe(String orgFlow,String speFlow);

    List<ResBaseSpeDeptInfo> searchInfo(ResBaseSpeDeptInfo baseSpeDeptInfo);

    ResBaseSpeDeptData searchResBaseSpeDeptDataOne(String infoFlow,String orgFlow,String speFlow,String deptFlow,String type,String sessionNumber,String infoType);

    int saveResBaseSpeDeptInfoData(ResBaseSpeDeptData speDeptData);

    List<Map<String,String>> searchResBaseSpeDeptInfoData(Map<String, String> paramMap);

    int countResBaseSpeDeptInfoData(Map<String, String> paramMap);

    List<Map<String,String>> countByOrgListAndSpe(List<String> orgFlow);

    List<Map<String,Object>> countDoctorNum(Map<String, String> paramMap);
}
