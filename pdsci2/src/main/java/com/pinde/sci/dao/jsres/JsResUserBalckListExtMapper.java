package com.pinde.sci.dao.jsres;


import com.pinde.sci.model.mo.JsresUserBalcklist;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResUserBalckListExtMapper {

	List<JsresUserBalcklist> selectBlackUser(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlackUserOfAll(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlack();

	List<JsresUserBalcklist> searchInfo(@Param("jsresUserBalcklist") JsresUserBalcklist jsresUserBalcklist,
										@Param("userFlowList") List<String> userFlowList,@Param("orgFlowList")List<String> orgFlowList,
										@Param("trainingTypeId")  String trainingTypeId);


	List<JsresUserBalcklist> searchInfo2(@Param("jsresUserBalcklist") JsresUserBalcklist jsresUserBalcklist,
										 @Param("userFlowList") List<String> userFlowList, @Param("orgFlowList") List<String> orgFlowList,
										 @Param("sessionNumbers")  List<String> sessionNumbers, @Param("auditStatusId") String auditStatusId,
										 @Param("trainingTypeId")  String trainingTypeId);

	/**
	 * 根据用户ID查询黑名单
	 * @param userIdNo
	 * @return
     */
	JsresUserBalcklist selectByIdNo(String userIdNo);



}
