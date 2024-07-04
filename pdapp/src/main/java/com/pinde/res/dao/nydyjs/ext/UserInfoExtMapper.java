package com.pinde.res.dao.nydyjs.ext;

import com.pinde.res.model.njmu.mo.Userinfo;

import java.util.List;
import java.util.Map;

public interface UserInfoExtMapper {
	

	public List<Userinfo> seachUser(Map<String, String> paramMap);
}
