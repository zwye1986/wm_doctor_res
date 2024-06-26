package com.pinde.sci.biz.portal;

import com.pinde.sci.form.portal.PortalInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.portal.PortalInfoExt;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPortalInfoManageBiz {
    /**
     * 保存资讯
     *
     * @param info
     * @return
     */
    int save(PortalInfo info);

    /**
     * 上传图片
     *
     * @param request
     * @param fileInputName 文件控件名称
     * @return
     */
    String uploadImg(HttpServletRequest request, String fileInputName);

    /**
     * 获取资讯扩展
     *
     * @param infoFlow 流水号
     * @return
     */
    PortalInfoExt getExtByFlow(String infoFlow);

    /**
     * 获取上传图片url根路径
     *
     * @return
     */
    String getImageBaseUrl();

    /**
     * 更新资讯
     *
     * @param info
     * @return
     */
    int update(PortalInfo info);

    /**
     * 获取资讯
     *
     * @param infoFlow 流水号
     * @return
     */
    PortalInfo getByFlow(String infoFlow);

    /**
     * 批量更新资讯状态
     *
     * @param infoFlows    流水号列表
     * @param infoStatusId 资讯审核状态Id
     * @return
     */
    int updateInfoStatus(List<String> infoFlows, String infoStatusId);

    /**
     * 删除标题图片
     *
     * @param infoFlow 流水号
     * @return 0：失败，1：成功
     */
    int deleteTitleImg(String infoFlow);

    /**
     * 获取资讯列表
     *
     * @param form
     * @return
     */
    List<PortalInfo> getList(PortalInfoForm form,List<String> statusList);

    int modifyPortalInfo(PortalInfo info);

    //信息统计
    List<Map<String,Object>> getStatistics(Map<String,Object> paramMap);

    //编辑文件
    int edit(PortalFile file);

    //根据条件查询文件
    List<PortalFile> getFileList(PortalFile file);

    //上传文件
    int uploadFile(MultipartFile uploadFile);

    //根据条件查询建议
    List<PortalSuggest> getSuggestList(PortalSuggest suggest);

    //根据主键查询建议
    PortalSuggest readSuggest(String recordFlow);

    //编辑建议
    int editSuggest(PortalSuggest suggest);

    //医患交流主表列表
    List<JsszportalCommunicationMain> searchCommunicationMain(JsszportalCommunicationMain communicationMain,String order);

    //获取单个主表
    JsszportalCommunicationMain readCommunicationMain(String recordFlow);

    //编辑主表
    int editCommunicationMain(JsszportalCommunicationMain communicationMain);

    //获取子表
    List<JsszportalCommunicationRe> searchCommunicationRe(JsszportalCommunicationRe communicationRe);

    //编辑子表
    int editCommunicationRe(JsszportalCommunicationRe communicationRe);

}
