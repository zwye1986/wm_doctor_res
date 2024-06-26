package com.pinde.sci.dao.res;

import com.pinde.sci.model.dwjxres.SysUserStaffExt;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.StuUserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StuUserResumeExtMapper {


	int updateSpeNameById(@Param("dictId") String dictId, @Param("dictName") String dictName);
	int updateUserDeptNameById(@Param("dictId") String dictId, @Param("dictName") String dictName);
	int updateRegistDeptNameById(@Param("dictId") String dictId, @Param("dictName") String dictName);

	List<Map<String,String>> searchUserForOwner(Map<String, String> paramMap);
}
