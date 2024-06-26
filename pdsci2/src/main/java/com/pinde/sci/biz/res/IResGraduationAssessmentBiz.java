package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.ResGraduationAssessment;
import com.pinde.sci.model.mo.ResGraduationAssessmentWithBLOBs;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.ResGraduationAssessmentExt;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface IResGraduationAssessmentBiz {

    ResGraduationAssessmentExt getDocGraduationAssessment(String userFlow);

    /**
     * 保存结业考核
     * @param graduationAssessmentWithBLOBs
     * @return
     */
    int save(ResGraduationAssessmentWithBLOBs graduationAssessmentWithBLOBs);

    /**
     * 校验上传论文格式
     * @param uploadFile
     * @return
     */
    String checkFile(MultipartFile uploadFile);

    /**
     * 保存文件
     * @param fileFlow
     * @param productType
     * @param uploadFile
     * @param user
     * @return
     */
    String saveFileToDirs(String fileFlow, String productType, MultipartFile uploadFile, SysUser user);

    String uploadImg(String recordFlow, MultipartFile graduationFile);

    ResGraduationAssessmentWithBLOBs getGraduationAssessmentWithBlobByFlow(String recordFlow);
    void down(PubFile file, HttpServletResponse response) throws Exception;

    void downImg(ResGraduationAssessmentWithBLOBs ext, HttpServletResponse response) throws Exception;
}
