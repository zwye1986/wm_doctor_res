package com.pinde.sci.dao.res;

import com.pinde.core.model.StuHeadAuditStatus;
import com.pinde.core.model.StuUserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StuUserExtMapper {

	List<StuHeadAuditStatus> getStuHeadAudit(Map<String,Object> mp);
	//审核信息查询
	List<StuUserExt> searchStuUser(Map<String,Object> mp);
	List<StuUserExt> searchStuUserByrosteringHand(Map<String,Object> mp);
	List<StuUserExt> searchStuUserByForeign(Map<String,Object> mp);
	List<StuUserExt> searchStuUserAll(Map<String,Object> mp);
	List<StuUserExt> searchStuUserByHead(Map<String,Object> mp);
	List<StuUserExt> searchStuUserAndDoc(Map<String,Object> mp);
	List<StuUserExt> teaQueryStuList(Map<String,Object> mp);

	/**
	 * 学员信息查询
	 * @param mp
	 * @return
     */
	List<StuUserExt> queryStuList(Map<String,Object> mp);
	//录取信息查询
	StuUserExt searchAdmitedInfo(@Param("resumeFlow") String resumeFlow);

	List<Map<String,Object>> queryStuListForExport(Map<String, Object> mp);

    List<StuUserExt> searchStuNurseUser(Map<String, Object> mp);

	List<StuUserExt> searchStuUser2(Map<String, Object> mp);

	List<StuUserExt> searchUser(Map<String, Object> mp);

	List<StuUserExt> queryNurseList(Map<String, Object> parMp);

	List<Map<String, Object>> queryNurseListForExport(Map<String, Object> parMp);

}
