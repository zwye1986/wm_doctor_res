package com.pinde.sci.biz.osca;

import com.pinde.core.model.OscaFrom;
import com.pinde.core.model.OscaSubjectStationFrom;
import com.pinde.sci.model.osca.OscaFromCfgExt;
import com.pinde.sci.model.osca.OscaFromCfgItemExt;
import com.pinde.sci.model.osca.OscaFromCfgTitleExt;

import java.util.List;

public interface IOscaFormCfgBiz {

    //省厅评分表单查询
    List<OscaFrom> searchForm(OscaFrom from);
    //医院评分表单查询
    List<OscaFrom> searchHospitalFrom(OscaFrom from);
    //编辑
    int editForm(OscaFrom from);
    //read
    OscaFrom readFrom(String fromFlow);
    //编辑考核指标标题
    int editFromCfgTitle(OscaFrom from, OscaFromCfgTitleExt fromCfgTitleExt) throws Exception;
    //删除考核指标标题
    int deleteTitle(String fromFlow, String id) throws Exception;
    //保存考核指标列表
    int saveFromItemList(OscaFromCfgExt from) throws Exception;
    //修改考核指标
    int modifyItem(String cfgFlow, OscaFromCfgItemExt itemForm) throws Exception;
    //删除考核指标
    int deleteItem(String cfgFlow, String id) throws Exception;
    //查看表单名称是否重复
    String checkFromName(String fromName);
    //根据方案主键查看表单
    List<OscaSubjectStationFrom> selectSubjectStationFromByFlow(String flow);

}
