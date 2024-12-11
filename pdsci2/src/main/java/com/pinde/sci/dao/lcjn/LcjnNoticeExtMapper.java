package com.pinde.sci.dao.lcjn;


import com.pinde.core.model.InxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnNoticeExtMapper {
    /**
     * 某日期之后的通知
     * 如果org不为空则插叙当前机构和org_flow为空的数据,否则只查询org_flow为空的数据
     *
     * @param orgFlow
     * @return
     */
    List<InxInfo> searchInfoByOrgBeforeDate(@Param(value = "orgFlow") String orgFlow, @Param(value = "date") String date);
}