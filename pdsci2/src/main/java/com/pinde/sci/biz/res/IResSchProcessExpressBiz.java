package com.pinde.sci.biz.res;




import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResSchProcessExpress;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IResSchProcessExpressBiz {


    List<ResSchProcessExpress> searchResRecWithBLOBsByRotationDeptFlow(String recTypeId,
                                                         String recordFlow, String operUserFlow);


    int editAndOut(ResSchProcessExpress rec, ResDoctorSchProcess process);


    /**
     * 查询当前带教或科主任的待出科人员数据
     * @param process
     * @param user
     * @param recTypeIds
     * @return
     */
    List<ResSchProcessExpress> searchAfterAuditRec(ResDoctorSchProcess process, SysUser user,
                                                   List<String> recTypeIds, Map<String,String> roleFlagMap);

    List<ResSchProcessExpress> searchByProcessFlowAndRecTypeIdClob(String processFlow,String recTypeId);



    List<ResSchProcessExpress> searchRecByProcess(String processFlow,String doctorFlow);

    /**
     * 获取某类型的登记数据
     * @param recTypeId
     * @param rotationDeptFlow
     * @param operUserFlow
     * @return
     */
    List<ResSchProcessExpress> searchRecByProcess(String recTypeId, String rotationDeptFlow,
                                    String operUserFlow);

    /** 根据process和登记类型集合查询该用户的登记数据
    * @param recTypeIds
    * @param processFlow
    * @return
            */
    List<ResSchProcessExpress> searchRecByProcessWithBLOBs(List<String> recTypeIds,String processFlow);

    List<ResSchProcessExpress> searchByUserFlowAndTypeId(String operUserFlow,String recTypeId);
    /**
     * 根据主键查找
     * @param recFlow
     * @return
     */
    ResSchProcessExpress readResExpress(String recFlow);

    List<ResSchProcessExpress> searchFinishRec(List<String> recTypeIds, String operUserFlow);

    List<ResSchProcessExpress> searchByRecAndProcess(String recTypeId, String schDeptFlow, String operUserFlow, String processFlow);

    String getRecContent(String formName, List<Element> list,HttpServletRequest req);

    List<ResSchProcessExpress> searchResRecExpressWithBLOBs(String recTypeId, String processFlow);
    /**
     * 新增或修改
     * @param express
     * @return
     */
    int edit(ResSchProcessExpress express);
    /**
     * 保存表单
     * @param formFileName
     * @param recFlow
     * @param schDeptFlow
     * @param rotationFlow
     * @param req
     * @param orgFlow
     * @param medicineTypeId
     * @return
     */
    int saveRegistryForm(String formFileName, String recFlow,
                         String schDeptFlow, String rotationFlow, HttpServletRequest req,
                         String orgFlow, String medicineTypeId);

    ResSchProcessExpress queryResRec(String processFlow, String operUserFlow,String recTypeId);

    List<ResSchProcessExpress> queryResRecList(List<String> processFlowList, List<String> operUserFlowList,String recTypeId);

    ResSchProcessExpress queryResRecByProcessAndType(String processFlow,String recTypeId);

    List<ResSchProcessExpress> getAllAfterSummary(String id);

    List<ResSchProcessExpress> searchByProcessFlowClob(String processFlow);

    List<ResSchProcessExpress> searchByRecAndProcess(String recTypeId, String processFlow);

    ResSchProcessExpress getExpressByRecTypeNoStatus(String processFlow, String recTypeId);
}
