package com.pinde.sci.biz.pub;

import com.pinde.core.model.PubUserResume;
import com.pinde.core.model.SysUser;
import org.dom4j.DocumentException;

import java.util.List;

public interface IPubUserResumeBiz {
    /**
     * 获取个人履历
     *
     * @param userFlow
     * @return
     */
    PubUserResume readPubUserResume(String userFlow);

    List<PubUserResume> findPubUserResume();

    /**
     * 保存个人履历
     *
     * @param
     * @return
     */
    int savePubUserResume(SysUser user, PubUserResume resume);

    /**
     * 及诶新学生信息xml返回对象
     *
     * @param xml
     * @param
     * @return
     * @throws DocumentException
     */
    <T> T converyToJavaBean(String xml, Class<T> clazz) throws DocumentException;

    /**
     *  flag标识：class类名必须等于xml根节点（不区分大小写）
     * @throws DocumentException
     */
    <T> T converyToJavaBean(String xml, Class<T> clazz, String flag) throws DocumentException;

    /**
     * 根据医师流水号集合查询
     * @param userFlows
     * @return
     */
    List<PubUserResume> findPubUserResumeByUserFlows(List<String> userFlows);
}
