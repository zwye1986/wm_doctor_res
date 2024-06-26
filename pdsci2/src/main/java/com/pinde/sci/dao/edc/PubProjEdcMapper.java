package com.pinde.sci.dao.edc;

import com.pinde.sci.model.mo.PubProj;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * proj锟斤拷展mapper
 *
 * @author Administrator
 */
public interface PubProjEdcMapper {

    List<PubProj> selectUserProjList(Map<String, String> paramMap);

    List<PubProj> selectUserProjListForAssign(@Param(value = "userFlow") String userFlow, @Param(value = "roleFlow") String roleFlow);

//	public List<PubProj> selectUserProjListForInput(@Param(value="userFlow")String userFlow, @Param(value="roleFlow")String roleFlow);
List<PubProj> selectTerminateProj(Map<String, String> terminateId);

} 
