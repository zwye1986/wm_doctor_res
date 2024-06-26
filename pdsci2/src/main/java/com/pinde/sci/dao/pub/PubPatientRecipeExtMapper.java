package com.pinde.sci.dao.pub;

import com.pinde.sci.model.pub.PubPatientRecipeExt;

import java.util.List;
import java.util.Map;

public interface PubPatientRecipeExtMapper {

	List<PubPatientRecipeExt> searchPatientRecipe(Map<String, Object> map);
}
