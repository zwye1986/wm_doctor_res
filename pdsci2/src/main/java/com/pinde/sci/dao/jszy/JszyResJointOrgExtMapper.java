package com.pinde.sci.dao.jszy;

import com.pinde.sci.model.jszy.JszyResJointOrgExt;
import com.pinde.sci.model.mo.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pdkj20 on 2018/3/29.
 */
public interface JszyResJointOrgExtMapper {
    //查找协同基地
    List<JszyResJointOrgExt> findJointOrgExtByOrgFlow(@Param(value="orgFlow") String orgFlow);

    //查询主基地在本地市的外地协同基地
    List<SysOrg> findJointOrgByCityId(@Param(value="cityId") String cityId);
}
