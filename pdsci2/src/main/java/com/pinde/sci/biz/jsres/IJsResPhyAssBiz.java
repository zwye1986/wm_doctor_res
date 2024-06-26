package com.pinde.sci.biz.jsres;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.jsres.ResTeachQualifiedPlanExt;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.ResQualifiedPlanMsg;
import com.pinde.sci.model.mo.ResTeachPlanDoctor;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IJsResPhyAssBiz {

    /**
     * 保存 师资-培训计划
     * @param ext
     * @param fileList
     * @return
     */
    boolean savePlannedRelease (ResTeachQualifiedPlanExt ext,List<MultipartFile> fileList,String type) throws UnsupportedEncodingException;

    List<ResQualifiedPlanMsg> searchByPlanFlow(String planFlow);

    boolean savePubFile(List<MultipartFile> fileList,String planFlow,String fileFlow);

    boolean delPhyAss(String planFlow);

    int checkPlanContent(String planContent);

    String saveBaseListEntryUser(String dataMsg,String speId,String speName,String planFlow);

    List<ResTeachPlanDoctor> searchPlanDoctorByPlanFlow(String planFlow,String speId,String orgFlow);

    List<ResTeachPlanDoctor> searchPlanDoctor(ResTeachPlanDoctor doctor);

    int savePlanDoctorInfo(String planFlow,String recordFlow,String speId,String speName,String doctorCode,String doctorName);

    boolean saveUser(String userFlow,String userCode,String userName,String sexId,String sexName,String userPhone,String idNo,String userEmail,String deptFlow);

    int importBaseUserExcel(MultipartFile file,String planFlow) throws Exception ;

    int addUser(SysUser user,String roleName,String planFlow);

    int selectPlanDoctorByUserCode(String userCode,String orgFlow,String planFlow);

    int countPlanApperNum(String planFlow);

    int operateUser(List<String> recordFlows,String type);

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
}
