package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmIrbApply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IEthicalReviewBiz {
    /**
     * 查询
     */
    List<PubProj> getPubProjInfo(PubProj proj);

    /**
     * 查询审查信息
     * @param projFlow
     * @return
     */
    List<SrmIrbApply> searchSrmIrbApply(String projFlow);

    /**
     * 保存审查报告
     * @return
     */
    int updateFile(SrmIrbApply srmIrbApply,MultipartFile file,String fileFlow);

    int addFile(SrmIrbApply srmIrbApply,MultipartFile file);

    /**
     * 新增或修改文件
     * @param file
     * @return
     */
     int editFile(PubFile file);
}
