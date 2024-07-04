package com.pinde.res.biz.xnres;

import java.util.List;
import java.util.Map;

import com.pinde.sci.model.mo.ResRec;

public interface IXnresTeacherBiz {
	/**
	 * 获取该教师的所有学员
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getDocListByTeacher(Map<String, Object> paramMap);

    /**
     * 数据待审核学员列表
     * @param schArrangeResultMap
     * @return
     */
    List<Map<String,String>> schDoctorSchProcessQuery(Map<String, Object> schArrangeResultMap);

    /**
     * dops minicex 出科小结 出科考核 待审核学员列表
     * @param schArrangeResultMap
     * @return
     */
    List<Map<String,String>> schDoctorSchProcessInfoQuery(Map<String, Object> schArrangeResultMap);

    List<Map<String,Object>> findDataNoAudit(Map<String, Object> param);

    /**
     * 获取学员在当前轮转科室中填写的数据
     * @param processFlow
     * @param userFlow
     * @param typeId
     * @return
     */
    List<ResRec> getDocRecs(String processFlow, String userFlow, List<String> typeId);

    /**
     * 某个类型的数据信息
     * @param processFlow
     * @param docFlow
     * @param recType
     * @param biaoJi
     * @return
     */
    List<ResRec> searchRecByProcessAndRecType(String processFlow, String docFlow, String recType, String biaoJi);

    /**
     *
     * @param recFlow
     * @return
     */
    ResRec readResRec(String recFlow);

    int auditRecDate(String userFlow, String recFlow,String statusId);
	/**
	 * 审核一条登记数据
	 * @param userFlow
	 * @param dataFlow
	 */
	void auditDate(String userFlow, String dataFlow);
    List<Map<String, Object>> getDocCapDatas(String id, String processFlow);
}
