package com.pinde.sci.biz.irb;

import com.pinde.sci.form.irb.IrbApplyFileForm;
import com.pinde.sci.form.sys.SysUserForm;
import com.pinde.sci.model.irb.ProjInfoForm;
import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IIrbResearcherBiz {

    ProjInfoForm readProjInfoForm(String projFlow);

    List<SysDept> searchSysDept(String orgFlow);

    List<SysUser> searchSysUserByDept(String deptFlow);

    void addProj(PubProj proj);

    void modifyProj(PubProj proj);

    PubProj readPubProj(String projFlow);

    void addIrbApply(IrbApply apply);

    List<IrbApply> searchIrbApplyList(String projFlow);

    //public void handProcess(IrbApply irb);
//    public void modifyIrbApply(IrbApply irb);

    /**
     * 保存送审文件
     *
     * @param pubFile
     * @param irbRec
     * @param form
     * @return
     */
    int saveApplyFile(PubFile pubFile, IrbRec irbRec, IrbApplyFileForm form) throws Exception;

    /**
     * 删除文件节点
     *
     * @param irbFlow
     * @param fileId
     */
    void delApplyFile(String irbFlow, String fileId) throws Exception;

    List<IrbApply> searchIrbApplyList(String projFlow, String id);

    /**
     * 查询可选择为研究人员的用户
     *
     * @param form
     * @return
     */
    List<SysUser> queryResUser(SysUserForm form);

    IrbApply readIrbApply(String irbFlow);

//    public List<IrbProcess> searchProcess(String irbFlow);

    List<PubProj> searchProjList(PubProj proj);

    void saveProcess(IrbApply irb, IrbProcess process);

    void modifyIrbProcess(IrbProcess process);
}
