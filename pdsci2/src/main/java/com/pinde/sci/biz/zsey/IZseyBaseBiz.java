package com.pinde.sci.biz.zsey;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface IZseyBaseBiz {

    //培训预约审核信息
    List<Map<String, Object>> queryAppointList(Map<String, String> map);
    //验证时间段内教室是否占用
    List<ZseyAppointMain> checkRoomInUsing(Map<String, String> map);
    //管理员分配房间操作
    int saveAppointRoom(ZseyAppointMain main);
    //用户账号查询
    List<Map<String, Object>> queryAccountList(Map<String, String> map);
    //角色查询
    List<SysRole> queryRoleList();
    //用戶及角色保存操作
    int saveAccount(SysUser user, SysUserRole role);
    //启用/停用账号操作
    int accountOpt(String userFlow,String recordStatus);
    //设备信息查询
    List<ZseyDevices> queryDevicesList(String deviceName);
    //设备信息删除操作
    int delDevices(String deviceFlow);
    //单个设备信息查询
    ZseyDevices queryDevicesByFlow(String deviceFlow);
    //设备信息保存操作
    int saveDevices(ZseyDevices devices);
    //耗材信息查询
    List<ZseySupplies> querySuppliesList(String suppliesName);
    //耗材信息保存操作
    int saveSupplies(ZseySupplies supplies);
    //耗材信息删除操作
    int delSupplies(String suppliesFlow);
    //设备信息导入操作
    int importDevicesExcel(MultipartFile file) throws Exception;
    //设备信息导入操作
    int importSuppliesExcel(MultipartFile file) throws Exception;
    //耗材信息保存操作
    int saveBatchSupplies(ZseySuppliesBatch batch);
    //耗材批次明细查询
    List<ZseySuppliesBatch> queryBatchList(String suppliesFlow,String batchType);
    //教室使用情况
    List<Map<String,Object>> deptAppointList(String trainDate);
    //教室使用统计
    List<Map<String, Object>> roomUseNumList(String beginDate,String endDate);
    //教室每月统计
    List<Map<String, Object>> monthStatistics(String beginDate,String endDate);
    //住培带教绑定临床任课老师角色
    int bindTeacherRole(String userFlow);
    //部门使用耗材统计
    List<Map<String,Object>> deptStatisticsList(Map<String, String> map);
    //培训项目查询
    List<ZseyProjectMain> queryProjectList(String projectName);
    //培训项目删除操作
    int delProject(String projectFlow);
    //耗材信息保存操作
    int saveProject(ZseyProjectMain project);
    //教室每月统计导出
    void exportZdyExcel(String beginDate,String endDate,List<Map<String, Object>> dataList,OutputStream os) throws Exception;
    //部门使用统计导出
    void exportDeptUseExcel(List<Map<String, Object>> dataList,OutputStream os) throws Exception;
    //培训项目查询
    ZseyProjectMain queryProjectByFlow(String projectFlow);
    //培训项目配置的设备和耗材查询
    List<ZseyAppointMaterial> queryProjectConfig(String projectFlow);
    //新增培训项目配置保存操作
    int saveProjectConfig(Map<String, Object> param);
}
