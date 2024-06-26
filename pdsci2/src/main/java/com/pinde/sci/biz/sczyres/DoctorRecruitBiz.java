package com.pinde.sci.biz.sczyres;

import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResOrgSpeAssign;
import com.pinde.sci.model.mo.ScresRecuritMoreSpe;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DoctorRecruitBiz {

    /**
     * 查询招录信息
     *
     * @param recruit      招录条件
     * @param orderByColum 根据这个字段排序 为null时不排序
     * @param order        desc or asc
     * @return
     */
    List<ResDoctorRecruit> findDoctorRecruit(ResDoctorRecruit recruit, String orderByColum, String order);

    /**
     * 根据机构流水号和年份 查询指定年份某家医院的所有专业
     *
     * @param orgFlow
     * @param assignYear
     * @return
     */
    List<ResOrgSpeAssign> findSpeAssign(String orgFlow, String assignYear);

    List<ResOrgSpeAssign> findResOrgSpeAssign(ResOrgSpeAssign orgSpeAssign);


//	public List<ResOrgSpeAssign> findSpes(ResOrgSpeAssign spe);

    List<ResDoctorRecruitExt> selectDoctorRecruitExt(
            Map<String, Object> paramMap);

    ResDoctorRecruit readResDoctorRecruit(String recruitFlow);

    int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit);

    int giveUpDoctorRecruit(ResDoctorRecruitWithBLOBs recruit);

    /**
     * 招录操作 录取 不录取
     *
     * @param recruit
     */
    int recruit(ResDoctorRecruitWithBLOBs recruit,String planFlag,String orgLevel);

    /**
     * 根据自身条件查询招录人数
     *
     * @param recruit
     * @return
     */
    int findCountByRecruit(ResDoctorRecruit recruit);

    /**
     * 根据没有被录取的学员流水号 查询这些学员的调剂记录
     *
     * @param noRecruitDoctors
     * @return
     */
    Map<String, ResDoctorRecruit> findSwapDoctorRecruit(List<String> noRecruitDoctors);

    /**
     * 根据给定已经被调剂学员的流水号 查询这些学员没有被录取的记录
     *
     * @param swapRecruitDoctors
     * @return
     */
    Map<String, ResDoctorRecruit> findNoRecruitDoctorRecruit(List<String> swapRecruitDoctors);

    /**
     * 调剂操作
     *
     * @param recruit
     */
    void swapRecruit(ResDoctorRecruitWithBLOBs recruit,String oldRecruitFlow);

    //准考证导入
    int importGrades(MultipartFile file);

    //查询学员列表（目前只有省厅角色用）
    List<Map<String,Object>> searchDoctorRecruitExtList(Map<String, Object> paramMap);
    //查询学员列表（包括大字段）
    List<Map<String,Object>> searchDoctorRecruitExtListWithClob(Map<String, Object> paramMap);
    //根据条件查询学员更多志愿
    List<ScresRecuritMoreSpe> searchMoreSpe(ScresRecuritMoreSpe moreSpe);
    //编辑学员更多志愿
    int editMoreSpe(ScresRecuritMoreSpe moreSpe);
    //修改学员占指标属性
    int changeReturnBackFlag(String recruitFlow,String flag);
}
