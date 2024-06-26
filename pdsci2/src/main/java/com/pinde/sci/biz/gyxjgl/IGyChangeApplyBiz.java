package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.form.gyxjgl.SubmitApplyForm;
import com.pinde.sci.form.gyxjgl.UserChangeApplyForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduStudentChangeExt;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface IGyChangeApplyBiz {
    /**
     * 查找学生信息
     *
     * @return
     */
    EduUser readEduUser(String userFlow);

    /**
     * 查找每个学生所选的课程
     */
    List<EduCourse> searchStuCourseList(Map<String, Object> paramMap);

    /**
     * 保存申请信息
     *
     * @param form
     * @param applyForm
     * @param userFlow
     * @param recordFlow
     * @return
     */
    int saveAndUpdateChangeApplyInfo(SubmitApplyForm form, UserChangeApplyForm applyForm, String userFlow, String recordFlow);

    /**
     * 查找eduUserChangeApply信息
     * @param user
     * @param flag 区分异动申请和奖助学金申请
     * @return
     */
    List<EduUserChangeApply> searchEduUserChangeApply(EduUserChangeApply user, String flag);

    /**
     * 更新基本信息
     *
     * @param user
     * @return
     */
    int updataApplyInfo(EduUserChangeApply user, SubmitApplyForm form);

    /**
     * 查找changeInfo扩展
     *
     * @param paramMap
     * @return
     */
    List<XjEduStudentChangeExt> searchStdentChangeExtsList(Map<String, Object> paramMap);

    /**
     * 查询一个EduUserChangeApply
     *
     * @param recordFlow
     * @return
     */
    EduUserChangeApply readEduUserChangeApply(String recordFlow);

    /**
     * 导出excel
     *
     * @throws Exception
     */
    void exportExcel(String[] headLines, String[] titles, List<?> dataList, OutputStream os) throws Exception;

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

    //保存某申请类型文件至指定目录
    String saveFileToDirs(MultipartFile file, String folderName, String applyTypeId);

    /**
     * 根据用户流水号查找
     *
     * @param userFlow 用户流水号
     * @return
     */
    ResDoctor searchByUserFlow(String userFlow);

    //保存路径于pub_file表中
    int saveTblFileInfo(SubmitApplyForm form, String applyTypeId, String awardFlag);

    //查询上传表格文件集合
    List<String> searchUrlList(String applyTypeId,String column);

    //查询同种异动申请未提交的记录数
    int reqTypeCount(EduUserChangeApply apply);

    //保存上传说明信息于pub_file表中 awardFlag：异动申请和助学奖金申请标识
    int saveInstructionInfo(String applyTypeId,String content,String awardFlag);

    //查询上传说明信息 awardFlag：异动申请和助学奖金申请标识
    PubFile searchInstrutionInfo(String applyTypeId,String awardFlag);

    //思政就业管理-求职意向查询
    List<JobIntensionInfo> jobIntensionList(Map<String,String> map);
    JobIntensionInfo queryIntensionByFlow(String recordFlow);
    int saveIntesion(JobIntensionInfo info);
    int delIntesion(String recordFlow);
    List<EmploymentFeedback> feedbackList(Map<String,String> map);
    EmploymentFeedback queryFeedbackByFlow(String recordFlow);
    int saveFeedback(EmploymentFeedback info);
    int delFeedback(String recordFlow);
}
