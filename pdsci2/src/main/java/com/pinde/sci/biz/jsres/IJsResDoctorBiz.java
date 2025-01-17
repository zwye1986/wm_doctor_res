package com.pinde.sci.biz.jsres;

import com.pinde.core.model.*;
import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResDoctorBiz {

    /**
     * 保存个人信息
     *
     * @param user
     * @param doctor
     * @param userResumeExt
     * @return
     */
    int saveDoctorInfo(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt, String qtCountry);
    int saveDoctorInfo2(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt);
    /**
     * 检查上传图片的类型及大小
     *
     * @param uploadFile
     * @return
     */
    String checkImg(MultipartFile uploadFile);
    /**
     * 检查上传文件的类型及大小
     *
     * @param file
     * @return
     */
    String checkFile(MultipartFile file);
    /**
     * 保存文件至指定的目录
     *
     * @param oldFolderName
     * @param file
     * @param folderName
     * @return
     */
    String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName);

//	List<ResDoctorExt> searchDoctorExtsList(ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList);

//    ResRec searResRecZhuZhi(String formFileName, String recFlow, String operUserFlow, String roleFlag, String processFlow, String recordFlow, HttpServletRequest req);

    ResSchProcessExpress searResRecZhuZhi(String formFileName, String recFlow, String operUserFlow, String roleFlag, String processFlow, String recordFlow, String cksh, HttpServletRequest req);

    void exportForBack(Map<Object, Object> backInfoMap, HttpServletResponse response, String flag) throws Exception;
    void exportForBack2(List<ResDocotrDelayTeturn> list,Map<Object, Object> backInfoMap, HttpServletResponse response, String flag) throws Exception;

    void exportForDetail(List<JsDoctorInfoExt> doctorInfoExts, HttpServletResponse response) throws Exception;
    void exportForDetailLog(List<JsDoctorInfoLogExt> doctorInfoExts, HttpServletResponse response) throws Exception;

    /**
     * 查询符合减免条件的医师
     * @param paramMap
     * @return
     */
    List<ResDoctor> searchReductionDoc(Map<String, Object> paramMap);

    /**
     * 查询该届别下各类型人员数量
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getDocCountBySession(Map<String, Object> paramMap);

    List<Map<String,Object>> getDocLogCountBySession(Map<String, Object> paramMap);

    /**
     * 根据基地查询所有培训过的学员
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getDocByOrg(Map<String,Object> paramMap);

    List<Map<String,Object>> getDocLogByOrg(Map<String,Object> paramMap);

    int findWaitPassCountByOrgFlow(int f,List<String> list);
    int findWaitPassCountByOrgFlow2(int f,List<String> list,String trainingTypeId);

    /**
     * 添加黑名单
     * @param docotrDelayTeturn
     * @param recruitWithBLOBs
     * @return
     */
    int joinJsresUserBalckList(ResDocotrDelayTeturn docotrDelayTeturn);

    /**
     * 检索黑名单
     * @param map
     * @return
     */
    List<JsresUserBalcklist> checkBlackList(Map<String,Object> map);

    JsresUserBalcklist findByUserEmail4Black(String userEmail);
    JsresUserBalcklist findByIdNo4Black(String idNo);
    JsresUserBalcklist findByUserPhone4Black(String userPhone);

    List<Map<String,Object>> getAssiGeneralDocCountBySession(Map<String, Object> paramMap);
    List<Map<String,Object>> getAssiGeneralDocLogCountBySession(Map<String, Object> paramMap);

    List<Map<String,Object>> getAssiGeneralDocLogByOrg(Map<String, Object> paramMap);
    List<Map<String,Object>> getAssiGeneralDocByOrg(Map<String, Object> paramMap);

    /**
     * 查询高校在校专硕信息概况
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> searchGraduates(Map<String, Object> paramMap);

    List<Map<String,Object>> searchGraduatesByOrg(Map<String, Object> paramMap);

    //保存考评项目
    int saveEvalConfig(String configFlow,JsEvalCfgTitleExt title) throws DocumentException;
    //保存考评指标
    int modifyItem(String configFlow,JsEvalCfgItemExt title) throws DocumentException;
    //保存所有评分项目考评指标
    int saveFormItemList(JsEvalCfgExt form) throws DocumentException;
    //获取当前基地考评记录
    ResDoctorProcessEvalConfig readEvalConfig(String orgFlow);
    //删除评分项目
    int deleteTitle(String configFlow,String id) throws DocumentException;
    //删除评分指标
    int deleteItem(String configFlow,String id) throws DocumentException;

    void exportForDetail2(List<JsDoctorInfoExt> doctorInfoExts,String titleYear, HttpServletResponse response) throws Exception;
    void exportForRecruitDetail(List<JsRecruitDocInfoExt> doctorInfoExts,String titleYear, HttpServletResponse response) throws Exception;
    void exportMessage(List<JsDoctorInfoExt> doctorInfoExts,String titleYear, HttpServletResponse response) throws Exception;

    /**
     * 校验身份证图片
     * @param uploadFile
     * @return
     */
    String checkIdCardImg(MultipartFile uploadFile);

    void exportToXlsx(HttpServletRequest request, HttpServletResponse response, String fileName, List<JsresDoctorInfoExt> doctorInfoExts) throws Exception;

    List<ResJointOrg> queryJoinOrg(String orgFlow);
}
