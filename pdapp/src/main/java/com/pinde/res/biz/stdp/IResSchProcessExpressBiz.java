package com.pinde.res.biz.stdp;

import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.SchRotationDeptAfterWithBLOBs;
import com.pinde.core.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tiger
 */
public interface IResSchProcessExpressBiz {

    ResSchProcessExpress getExpressByRecType(String processFlow, String funcFlow);

    ResSchProcessExpress getExpressByRecFlow(String dataFlow);


    /**
     * 审核一条登记数据
     * @param userFlow
     * @param dataFlow
     */
    void auditDate(String userFlow, String dataFlow);

    List<ResSchProcessExpress> getDocexpressList(String processFlow, List<String> recTypeIds);

    /**
     * 江苏西医 DOPS MINI_SEX 出科考核表 大字段
     * @param formFileName
     * @param recFlow
     * @param operUserFlow
     * @param roleFlag
     * @param processFlow
     * @param recordFlow
     * @param userFlow
     * @param req
     * @return
     */
    ResSchProcessExpress searResRecZhuZhi(String formFileName, String recFlow, String operUserFlow, String roleFlag, String processFlow, String recordFlow, String userFlow, String cksh, HttpServletRequest req);

    /**
     * 江苏西医 DOPS MINI_SEX 出科考核表
     * @param re
     * @return
     */
    int editResRec(ResSchProcessExpress rec, SysUser user);

    SysUser readSysUser(String userFlow);

    ResSchProcessExpress queryResRec(String processFlow,String recTypeId);

    ResSchProcessExpress getExpressByRecTypeNoStatus(String processFlow, String recTypeId);

    List<ResSchProcessExpress> searchByUserFlowAndTypeId(String operUserFlow,String recTypeId);

    List<SchRotationDeptAfterWithBLOBs> getAfterByDoctorFlow(String doctorFlow, String applyYear);

    int editAfter(SchRotationDeptAfterWithBLOBs after);
}

	
