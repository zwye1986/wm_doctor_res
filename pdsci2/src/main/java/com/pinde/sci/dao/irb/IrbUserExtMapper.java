package com.pinde.sci.dao.irb;

import com.pinde.sci.model.mo.IrbUser;

import java.util.List;

public interface IrbUserExtMapper {

	List<IrbUser> queryIrbUserList(String year);
}
