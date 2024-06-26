package com.pinde.sci.dao.fstu;

import com.pinde.sci.model.mo.FstuScoreMain;
import com.pinde.sci.model.mo.InxColumn;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface FstuScoreExtMapper {
    List<Map<String,Object>> searchScoreList(Map<String, String> paramMap);
    List<SysUser> searchUserNotInScore(@Param("orgFlow") String orgFlow,@Param("flag") String flag);
}
