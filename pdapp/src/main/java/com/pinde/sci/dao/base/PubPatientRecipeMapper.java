package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientRecipe;
import com.pinde.sci.model.mo.PubPatientRecipeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientRecipeMapper {
    int countByExample(PubPatientRecipeExample example);

    int deleteByExample(PubPatientRecipeExample example);

    int deleteByPrimaryKey(String recipeFlow);

    int insert(PubPatientRecipe record);

    int insertSelective(PubPatientRecipe record);

    List<PubPatientRecipe> selectByExample(PubPatientRecipeExample example);

    PubPatientRecipe selectByPrimaryKey(String recipeFlow);

    int updateByExampleSelective(@Param("record") PubPatientRecipe record, @Param("example") PubPatientRecipeExample example);

    int updateByExample(@Param("record") PubPatientRecipe record, @Param("example") PubPatientRecipeExample example);

    int updateByPrimaryKeySelective(PubPatientRecipe record);

    int updateByPrimaryKey(PubPatientRecipe record);
}