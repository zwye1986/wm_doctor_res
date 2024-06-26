package com.pinde.sci.dao.hbzy;

import com.pinde.sci.model.hbzy.JszyResJointOrgExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pdkj20 on 2018/3/29.
 */
public interface HbzyResJointOrgExtMapper {
    //查找协同基地
    List<JszyResJointOrgExt> findJointOrgExtByOrgFlow(@Param(value="orgFlow") String orgFlow);
}
