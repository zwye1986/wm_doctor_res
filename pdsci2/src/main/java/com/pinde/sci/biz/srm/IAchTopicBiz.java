package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016.8.24.
 */
public interface IAchTopicBiz {
    /**
     * 读课题信息
     *
     * @param topicFlow
     * @return
     */
    SrmAchTopic readTopic(String topicFlow);

    /**
     * 保存和修改课题信息
     *
     * @param topic
     * @param process
     * @return
     */
    int save(SrmAchTopic topic,SrmAchFile srmAchFile, SrmAchProcess process);

    int save(SrmAchTopic topic);

    /**
     * 修改状态
     *
     * @param topic
     * @return
     */
    int updateTopicStatus(SrmAchTopic topic, SrmAchProcess process);

    /**
     * @param topic
     * @param childOrgList
     * @return
     */
    List<SrmAchTopic> search(SrmAchTopic topic);

    /**
     * 修改课题
     *
     * @param topic
     * @return
     */
    int edit(SrmAchTopic topic, SrmAchFile file);
}
