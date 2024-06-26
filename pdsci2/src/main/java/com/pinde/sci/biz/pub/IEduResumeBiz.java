package com.pinde.sci.biz.pub;

import com.pinde.sci.form.pub.EduResumeForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

public interface IEduResumeBiz {

    /**
     * 获取全部教育履历
     *
     * @param resume
     * @return
     * @throws Exception
     */
    List<EduResumeForm> queryEduResumeList(PubUserResume resume) throws Exception;

    /**
     * 保存教育经历
     *
     * @param
     * @return
     * @throws Exception
     */
    int saveEduResume(SysUser user, EduResumeForm form) throws Exception;


    /**
     * 获取一条教育记录
     *
     * @param recordId
     * @throws Exception
     */
    EduResumeForm getEduResumeByRecordId(String userFlow, String recordId) throws Exception;

    /**
     * 删除一条教育经历
     *
     * @param recordId
     * @return
     * @throws Exception
     */
    int delEduResumeByRecordId(String userFlow, String recordId) throws Exception;
}
