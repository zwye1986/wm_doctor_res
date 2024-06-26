package com.pinde.sci.biz.zsey;

import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.ZseyAppointMain;
import com.pinde.sci.model.mo.ZseyAppointMaterial;

import java.util.List;
import java.util.Map;

public interface IZseyDeptBiz {

    //培训预约信息查询
    List<ZseyAppointMain> queryAppointList(Map<String, String> map);
    //培训预约加载任课老师
    List<SysUser> searchTeacher();
    //培训预约及关联设备信息保存操作
    int saveAppoint(Map<String, Object> param);
    //预约信息删除操作
    int delAppoint(String deviceFlow);
    //单个预约信息记录
    ZseyAppointMain queryAppointByFlow(String appointFlow);
    //预约信息所配设备
    List<ZseyAppointMaterial> queryMaterialList(String appointFlow);
    //查询设备和耗材信息
    List<Map<String, Object>> queryAppointMaterialList(String materialName);
}
