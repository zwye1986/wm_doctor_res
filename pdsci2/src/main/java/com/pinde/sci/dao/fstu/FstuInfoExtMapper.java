package com.pinde.sci.dao.fstu;

import com.pinde.sci.form.fstu.FstuNewsInfoForm;
import com.pinde.sci.model.fstu.FstuInfoExt;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.InxInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FstuInfoExtMapper {
    FstuInfoExt selectExtByFlow(String infoFlow);

    int updateInfoState(FstuNewsInfoForm form);

    /**
     * 某日期之后的通知
     * 如果org不为空则插叙当前机构和org_flow为空的数据,否则只查询org_flow为空的数据
     *
     * @param orgFlow
     * @return
     */
    List<InxInfo> searchInfoByOrgBeforeDate(@Param(value = "orgFlow") String orgFlow, @Param(value = "date") String date);

    List<FstuNewsInfoForm> selectByFstuNewsInfoExample(InxInfoExample example);

}
