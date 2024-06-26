package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpOaLicKey;
import com.pinde.sci.model.mo.ErpOaLicKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpOaLicKeyMapper {
    int countByExample(ErpOaLicKeyExample example);

    int deleteByExample(ErpOaLicKeyExample example);

    int deleteByPrimaryKey(String licFlow);

    int insert(ErpOaLicKey record);

    int insertSelective(ErpOaLicKey record);

    List<ErpOaLicKey> selectByExample(ErpOaLicKeyExample example);

    ErpOaLicKey selectByPrimaryKey(String licFlow);

    int updateByExampleSelective(@Param("record") ErpOaLicKey record, @Param("example") ErpOaLicKeyExample example);

    int updateByExample(@Param("record") ErpOaLicKey record, @Param("example") ErpOaLicKeyExample example);

    int updateByPrimaryKeySelective(ErpOaLicKey record);

    int updateByPrimaryKey(ErpOaLicKey record);
}