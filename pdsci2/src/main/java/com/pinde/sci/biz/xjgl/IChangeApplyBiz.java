package com.pinde.sci.biz.xjgl;

import com.pinde.sci.form.xjgl.SubmitApplyForm;
import com.pinde.sci.form.xjgl.UserChangeApplyForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentChangeExt;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface IChangeApplyBiz {
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
     *
     * @param user
     * @return
     */
    List<EduUserChangeApply> searchEduUserChangeApply(EduUserChangeApply user);

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
    int saveTblFileInfo(SubmitApplyForm form, String applyTypeId);

    //查询上传表格文件集合
    List<String> searchUrlList(String applyTypeId,String column);

    //查询同种异动申请未提交的记录数
    int reqTypeCount(EduUserChangeApply apply);

    //保存上传说明信息于pub_file表中
    int saveInstructionInfo(String applyTypeId,String content);

    //查询上传说明信息
    PubFile searchInstrutionInfo(String applyTypeId);
}
