package com.pinde.sci.biz.jszy;

import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJszyResDoctorBiz {

    /**
     * 保存个人信息
     *
     * @param user
     * @param doctor
     * @param userResumeExt
     * @return
     */
    int saveDoctorInfo(SysUser user, ResDoctor doctor, BaseUserResumeExtInfoForm userResumeExt);

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
    String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName);

    ResRec searResRecZhuZhi(String formFileName, String recFlow, String operUserFlow, String roleFlag, String processFlow, String recordFlow, HttpServletRequest req);

    void exportForBack(Map<Object, Object> backInfoMap, HttpServletResponse response, String flag) throws Exception;

    void exportForBack2(List<ResDocotrDelayTeturn> list,Map<Object, Object> backInfoMap, HttpServletResponse response, String flag) throws Exception;
    void exportForDetail(List<JszyDoctorInfoExt> doctorInfoExts, HttpServletResponse response) throws Exception;

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

    /**
     * 根据基地查询所有培训过的学员
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getDocByOrg(Map<String, Object> paramMap);

    int findWaitPassCountByOrgFlow(int f, List<String> list);

    /**
     * 添加黑名单
     * @param backTrainForm
     * @param recruitWithBLOBs
     * @return
     */
    int joinJsresUserBalckList(ResDocotrDelayTeturn docotrDelayTeturn);

    /**
     * 检索黑名单
     * @param map
     * @return
     */
    List<JsresUserBalcklist> checkBlackList(Map<String, Object> map);

    JsresUserBalcklist findByUserEmail4Black(String userEmail);
    JsresUserBalcklist findByIdNo4Black(String idNo);
    JsresUserBalcklist findByUserPhone4Black(String userPhone);

    List<Map<String,Object>> searchGraduatesByOrg(Map<String, Object> paramMap);

    List<Map<String,Object>> searchGraduates(Map<String, Object> paramMap);
}
