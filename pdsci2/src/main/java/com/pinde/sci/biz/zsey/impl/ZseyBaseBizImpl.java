package com.pinde.sci.biz.zsey.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.zsey.IZseyBaseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.zsey.ZseyBaseExtMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.zsey.ZseyAuditStatusEnum;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZseyBaseBizImpl implements IZseyBaseBiz {

    @Autowired
    private ZseyBaseExtMapper baseExtMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private ZseyDevicesMapper devicesMapper;
    @Autowired
    private ZseySuppliesMapper suppliesMapper;
    @Autowired
    private ZseySuppliesBatchMapper batchMapper;
    @Autowired
    private ZseyAppointMainMapper mainMapper;
    @Autowired
    private ZseyProjectMainMapper projectMainMapper;
    @Autowired
    private ZseyAppointMaterialMapper materialMapper;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IDictBiz dictBiz;

    @Override
    public List<Map<String, Object>> queryAppointList(Map<String, String> map) {
        return baseExtMapper.queryAppointList(map);
    }

    @Override
    public List<ZseyAppointMain> checkRoomInUsing(Map<String, String> map) {
        return baseExtMapper.checkRoomInUsing(map);
    }

    @Override
    public int saveAppointRoom(ZseyAppointMain main) {
        if(StringUtil.isNotBlank(main.getAppointFlow())){
            main.setAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            main.setAuditStatusId(ZseyAuditStatusEnum.Passed.getId());
            main.setAuditStatusName(ZseyAuditStatusEnum.Passed.getName());
            GeneralMethod.setRecordInfo(main,false);
            return mainMapper.updateByPrimaryKeySelective(main);
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryAccountList(Map<String, String> map) {
        return baseExtMapper.queryAccountList(map);
    }

    @Override
    public List<SysRole> queryRoleList() {
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andWsIdEqualTo(GlobalContext.getCurrentWsId());
        return roleMapper.selectByExample(example);
    }

    @Override
    public int saveAccount(SysUser user, SysUserRole role) {
        if(StringUtil.isNotBlank(user.getUserFlow())){
            SysUserExample example = new SysUserExample();
            example.createCriteria().andUserCodeEqualTo(user.getUserCode()).andUserFlowNotEqualTo(user.getUserFlow());
            if(userMapper.countByExample(example) > 0){
                return -1;
            }
            //变更角色
            SysUserRoleExample roleExample = new SysUserRoleExample();
            roleExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andUserFlowEqualTo(role.getUserFlow()).andRoleFlowEqualTo(role.getRoleFlow());
            if(userRoleMapper.countByExample(roleExample) <= 0){//不存在该角色
                roleExample.clear();
                roleExample.createCriteria().andUserFlowEqualTo(role.getUserFlow()).andWsIdEqualTo(GlobalContext.getCurrentWsId());
                userRoleMapper.deleteByExample(roleExample);//删除其他可能存在的角色
                role.setRecordFlow(PkUtil.getUUID());
                role.setWsId(GlobalContext.getCurrentWsId());
                GeneralMethod.setRecordInfo(role,true);
                userRoleMapper.insertSelective(role);//添加角色
            }
            return userMapper.updateByPrimaryKeySelective(user);
        }else{
            SysUserExample example = new SysUserExample();
            example.createCriteria().andUserCodeEqualTo(user.getUserCode());
            int num = userMapper.countByExample(example);
            if(num >0){
                return -1;
            }
            //添加用户
            String userFlow = PkUtil.getUUID();
            user.setUserFlow(userFlow);
            user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, InitPasswordUtil.getInitPass()));
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            GeneralMethod.setRecordInfo(user,true);
            //添加角色
            role.setRecordFlow(PkUtil.getUUID());
            role.setUserFlow(userFlow);
            role.setWsId(GlobalContext.getCurrentWsId());
            GeneralMethod.setRecordInfo(role,true);
            userRoleMapper.insertSelective(role);
            return userMapper.insertSelective(user);
        }
    }

    @Override
    public int accountOpt(String userFlow, String recordStatus) {
        if(StringUtil.isNotBlank(userFlow)){
            SysUser user = new SysUser();
            user.setUserFlow(userFlow);
            user.setRecordStatus(recordStatus);
            return userMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }

    @Override
    public List<ZseyDevices> queryDevicesList(String deviceName) {
        ZseyDevicesExample example = new ZseyDevicesExample();
        ZseyDevicesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(deviceName)){
            criteria.andDeviceNameLike("%"+deviceName+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return devicesMapper.selectByExample(example);
    }

    @Override
    public int delDevices(String deviceFlow) {
        return devicesMapper.deleteByPrimaryKey(deviceFlow);
    }

    @Override
    public ZseyDevices queryDevicesByFlow(String deviceFlow) {
        return devicesMapper.selectByPrimaryKey(deviceFlow);
    }

    @Override
    public int saveDevices(ZseyDevices devices) {
        if(StringUtil.isNotBlank(devices.getDeviceFlow())){
            ZseyDevicesExample example = new ZseyDevicesExample();
            example.createCriteria().andDeviceNameEqualTo(devices.getDeviceName()).andDeviceFlowNotEqualTo(devices.getDeviceFlow());
            if(devicesMapper.countByExample(example) > 0){
                return -1;
            }
            return devicesMapper.updateByPrimaryKeySelective(devices);
        }else{
            ZseyDevicesExample example = new ZseyDevicesExample();
            example.createCriteria().andDeviceNameEqualTo(devices.getDeviceName());
            int num = devicesMapper.countByExample(example);
            if(num >0){
                return -1;
            }
            //添加设备
            devices.setDeviceFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(devices,true);
            return devicesMapper.insertSelective(devices);
        }
    }

    @Override
    public List<ZseySupplies> querySuppliesList(String suppliesName) {
        ZseySuppliesExample example = new ZseySuppliesExample();
        ZseySuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(suppliesName)){
            criteria.andSuppliesNameLike("%"+suppliesName+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return suppliesMapper.selectByExample(example);
    }

    @Override
    public int saveSupplies(ZseySupplies supplies) {
        ZseySuppliesExample example = new ZseySuppliesExample();
        example.createCriteria().andSuppliesNameEqualTo(supplies.getSuppliesName());
        int num = suppliesMapper.countByExample(example);
        if(num >0){
            return -1;
        }
        //添加耗材
        supplies.setSuppliesFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(supplies,true);
        return suppliesMapper.insertSelective(supplies);
    }

    @Override
    public int delSupplies(String suppliesFlow) {
        return suppliesMapper.deleteByPrimaryKey(suppliesFlow);
    }

    @Override
    public int importDevicesExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            byte[] fileData = new byte[(int) file.getSize()];
            is = file.getInputStream();
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseDevicesExcel(wb);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            is.close();
        }
    }
    private int parseDevicesExcel(Workbook wb) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num <= 1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                if(null == titleR.getCell(i)){
                    throw new Exception("导入文件首行列名异常！");
                }
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                ZseyDevices devices = new ZseyDevices();
                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("设备名称".equals(currTitle)){
                        devices.setDeviceName(value);
                    }
                    if("品牌".equals(currTitle)){
                        devices.setBrandName(value);
                    }
                    if("规格型号".equals(currTitle)){
                        devices.setDeviceMode(value);
                    }
                    if("数量".equals(currTitle) && StringUtil.isNotBlank(value)){
                        devices.setDeviceNumber(value);
                    }
                }
                if(StringUtil.isBlank(devices.getDeviceName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行设备名称不能为空！");
                }
                if(StringUtil.isBlank(devices.getBrandName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行品牌不能为空！");
                }
                if(StringUtil.isBlank(devices.getDeviceMode())){
                    throw new Exception("导入失败！第"+ (count+2) +"行规格型号不能为空！");
                }
                if(StringUtil.isBlank(devices.getDeviceNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行数量不能为空！");
                }else if(!isNumeric(devices.getDeviceNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行数量必须为整数！");
                }
                System.out.println(isNumeric(devices.getDeviceNumber())+"=====");
                ZseyDevicesExample example = new ZseyDevicesExample();
                example.createCriteria().andDeviceNameEqualTo(devices.getDeviceName())
                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                int num = devicesMapper.countByExample(example);
                if(num <= 0){//新增
                    devices.setDeviceFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(devices,true);//初始为启用状态
                    devicesMapper.insertSelective(devices);
                }else{//修改
                    GeneralMethod.setRecordInfo(devices,false);
                    devicesMapper.updateByExampleSelective(devices,example);
                }
                count ++;
            }
        }
        return count;
    }
    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str).matches();
    }
    private Workbook createCommonWorkbook(InputStream inS) throws Exception {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
        // EXCEL2003使用的是微软的文件系统
        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
            return new HSSFWorkbook(inS);
        }
        // EXCEL2007使用的是OOM文件格式
        if (POIXMLDocument.hasOOXMLHeader(inS)) {
            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
            return new XSSFWorkbook(OPCPackage.open(inS));
        }
        throw new Exception("不能解析的excel版本");
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public int importSuppliesExcel(MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            byte[] fileData = new byte[(int) file.getSize()];
            is = file.getInputStream();
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseSuppliesExcel(wb);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            is.close();
        }
    }
    private int parseSuppliesExcel(Workbook wb) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num <= 1){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                if(null == titleR.getCell(i)){
                    throw new Exception("导入文件首行列名异常！");
                }
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 1;i < row_num; i++) {
                ZseySupplies supplies = new ZseySupplies();
                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if("耗材名称".equals(currTitle)){
                        supplies.setSuppliesName(value);
                    }
                    if("一次性量".equals(currTitle)){
                        supplies.setOneNumber(value);
                    }
                    if("可重复量".equals(currTitle)){
                        supplies.setRepeatNumber(value);
                    }
                }
                if(StringUtil.isBlank(supplies.getSuppliesName())){
                    throw new Exception("导入失败！第"+ (count+2) +"行耗材名称不能为空！");
                }
                if(StringUtil.isBlank(supplies.getOneNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行一次性量不能为空！");
                }else if(!isNumeric(supplies.getOneNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行一次性量必须为整数！");
                }
                if(StringUtil.isBlank(supplies.getRepeatNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行可重复量不能为空！");
                }else if(!isNumeric(supplies.getRepeatNumber())){
                    throw new Exception("导入失败！第"+ (count+2) +"行可重复量必须为整数！");
                }
                ZseySuppliesExample example = new ZseySuppliesExample();
                example.createCriteria().andSuppliesNameEqualTo(supplies.getSuppliesName())
                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                int num = suppliesMapper.countByExample(example);
                if(num <= 0){//新增
                    supplies.setSuppliesFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(supplies,true);
                    suppliesMapper.insertSelective(supplies);
                }else{//修改
                    GeneralMethod.setRecordInfo(supplies,false);
                    suppliesMapper.updateByExampleSelective(supplies,example);
                }
                count ++;
            }
        }
        return count;
    }

    @Override
    public int saveBatchSupplies(ZseySuppliesBatch batch) {
        //添加耗材批次信息
        batch.setBatchFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(batch,true);
        ZseySupplies supplies = suppliesMapper.selectByPrimaryKey(batch.getSuppliesFlow());
        if("IN".equals(batch.getBatchType())){
            supplies.setOneNumber(String.valueOf(Integer.valueOf(supplies.getOneNumber())+Integer.valueOf(batch.getOneNumber())));
            supplies.setRepeatNumber(String.valueOf(Integer.valueOf(supplies.getRepeatNumber())+Integer.valueOf(batch.getRepeatNumber())));
        }else{
            supplies.setOneNumber(String.valueOf(Integer.valueOf(supplies.getOneNumber())-Integer.valueOf(batch.getOneNumber())));
            supplies.setRepeatNumber(String.valueOf(Integer.valueOf(supplies.getRepeatNumber())-Integer.valueOf(batch.getRepeatNumber())));
        }
        suppliesMapper.updateByPrimaryKeySelective(supplies);
        return batchMapper.insertSelective(batch);
    }

    @Override
    public List<ZseySuppliesBatch> queryBatchList(String suppliesFlow, String batchType) {
        ZseySuppliesBatchExample example = new ZseySuppliesBatchExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSuppliesFlowEqualTo(suppliesFlow).andBatchTypeEqualTo(batchType);
        example.setOrderByClause("BATCH_DATE DESC,CREATE_TIME DESC");
        return batchMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> deptAppointList(String trainDate) {
        return baseExtMapper.deptAppointList(trainDate);
    }

    @Override
    public List<Map<String, Object>> roomUseNumList(String beginDate, String endDate) {
        return baseExtMapper.roomUseNumList(beginDate,endDate);
    }

    @Override
    public List<Map<String, Object>> monthStatistics(String beginDate, String endDate) {
        return baseExtMapper.monthStatistics(beginDate,endDate);
    }

    @Override
    public int bindTeacherRole(String userFlow) {
        int count = 0;
        String roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");//中二院住培带教角色配置
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow).andWsIdEqualTo("res").andRoleFlowEqualTo(roleFlow);
        if(StringUtil.isNotBlank(roleFlow)){
            count = userRoleMapper.countByExample(example);
        }
        if(count > 0){
            SysRoleExample roleExample = new SysRoleExample();
            roleExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andWsIdEqualTo("zseylcjn").andRoleNameEqualTo("任课老师");
            List<SysRole> roleList = roleMapper.selectByExample(roleExample);//查询临床任课老师角色
            if(null != roleList && roleList.size() > 0){
                SysUserRoleExample userRoleExample = new SysUserRoleExample();
                userRoleExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andUserFlowEqualTo(userFlow).andWsIdEqualTo("zseylcjn").andRoleFlowEqualTo(roleList.get(0).getRoleFlow());
                count = userRoleMapper.countByExample(userRoleExample);//查询此任课老师在临床是否已绑定角色
                if(count == 0){
                    SysUserRole record = new SysUserRole();
                    record.setRecordFlow(PkUtil.getUUID());
                    record.setUserFlow(userFlow);
                    record.setWsId("zseylcjn");
                    record.setRoleFlow(roleList.get(0).getRoleFlow());
                    GeneralMethod.setRecordInfo(record,true);
                    count = userRoleMapper.insertSelective(record);
                }
            }
        }
        return count;
    }

    @Override
    public List<Map<String, Object>> deptStatisticsList(Map<String, String> map) {
        return baseExtMapper.deptStatisticsList(map);
    }

    @Override
    public List<ZseyProjectMain> queryProjectList(String projectName) {
        ZseyProjectMainExample example = new ZseyProjectMainExample();
        ZseyProjectMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(projectName)){
            criteria.andProjectNameLike("%"+projectName+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return projectMainMapper.selectByExample(example);
    }

    @Override
    public int delProject(String projectFlow) {
        return projectMainMapper.deleteByPrimaryKey(projectFlow);
    }

    @Override
    public int saveProject(ZseyProjectMain project) {
        ZseyProjectMainExample example = new ZseyProjectMainExample();
        example.createCriteria().andProjectNameEqualTo(project.getProjectName());
        int num = projectMainMapper.countByExample(example);
        if(num >0){
            return -1;
        }
        //添加培训项目
        project.setProjectFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(project,true);
        return projectMainMapper.insertSelective(project);
    }

    @Override
    public ZseyProjectMain queryProjectByFlow(String projectFlow) {
        return projectMainMapper.selectByPrimaryKey(projectFlow);
    }

    @Override
    public List<ZseyAppointMaterial> queryProjectConfig(String projectFlow) {
        ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andAppointFlowEqualTo(projectFlow);
        example.setOrderByClause("CREATE_TIME DESC,TYPE_ID");
        return materialMapper.selectByExample(example);
    }

    @Override
    public int saveProjectConfig(Map<String, Object> param) {
        int count = 0;
        List<String> suppliesFlowList = (List<String>)param.get("suppliesFlowList");
        List<String> suppliesNameList = (List<String>)param.get("suppliesNameList");
        List<String> suppliesNumList = (List<String>)param.get("suppliesNumList");
        List<String> devicesFlowList = (List<String>)param.get("devicesFlowList");
        List<String> devicesNameList = (List<String>)param.get("devicesNameList");
        List<String> devicesNumList = (List<String>)param.get("devicesNumList");
        if(StringUtil.isNotBlank(String.valueOf(param.get("projectFlow")))){//修改
            List<String> suppliesRecordList = (List<String>)param.get("suppliesRecordList");
            List<String> devicesRecordList = (List<String>)param.get("devicesRecordList");
            if(null != suppliesRecordList && suppliesRecordList.size() > 0){//只是基于历史数据变更数量（耗材）
                //删除不在此次记录中的历史数据
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andTypeIdEqualTo("SUPPLIES")
                        .andAppointFlowEqualTo(String.valueOf(param.get("projectFlow"))).andRecordFlowNotIn(suppliesRecordList);
                materialMapper.deleteByExample(example);
                ZseyAppointMaterial zam = new ZseyAppointMaterial();
                for(int i=0; i<suppliesRecordList.size(); i++){
                    zam.setRecordFlow(suppliesRecordList.get(i));
                    zam.setMaterialNumber(suppliesNumList.get(i));
                    GeneralMethod.setRecordInfo(zam,false);
                    count += materialMapper.updateByPrimaryKeySelective(zam);
                }
            }else{
                //删除历史数据（耗材）
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andAppointFlowEqualTo(String.valueOf(param.get("projectFlow"))).andTypeIdEqualTo("SUPPLIES");
                materialMapper.deleteByExample(example);
                //新增项目耗材信息
                ZseyAppointMaterial zam = new ZseyAppointMaterial();
                zam.setAppointFlow(String.valueOf(param.get("projectFlow")));
                for(int i=0; i<suppliesFlowList.size(); i++){
                    zam.setRecordFlow(PkUtil.getUUID());
                    zam.setTypeId("SUPPLIES");
                    zam.setTypeName("耗材");
                    zam.setMaterialFlow(suppliesFlowList.get(i));
                    zam.setMaterialName(suppliesNameList.get(i));
                    zam.setMaterialNumber(suppliesNumList.get(i));
                    GeneralMethod.setRecordInfo(zam,true);
                    count += materialMapper.insertSelective(zam);
                }
            }
            if(null != devicesRecordList && devicesRecordList.size() > 0){//只是基于历史数据变更使用数量（设备）
                //删除不在此次记录中的历史数据
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andTypeIdEqualTo("DEVICES")
                        .andAppointFlowEqualTo(String.valueOf(param.get("projectFlow"))).andRecordFlowNotIn(devicesRecordList);
                materialMapper.deleteByExample(example);
                ZseyAppointMaterial zam = new ZseyAppointMaterial();
                for(int i=0; i<devicesRecordList.size(); i++){
                    zam.setRecordFlow(devicesRecordList.get(i));
                    zam.setMaterialNumber(devicesNumList.get(i));
                    GeneralMethod.setRecordInfo(zam,false);
                    count += materialMapper.updateByPrimaryKeySelective(zam);
                }
            }else{
                //删除历史数据（设备）
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andAppointFlowEqualTo(String.valueOf(param.get("projectFlow"))).andTypeIdEqualTo("DEVICES");
                materialMapper.deleteByExample(example);
                //新增项目设备信息
                ZseyAppointMaterial zam = new ZseyAppointMaterial();
                zam.setAppointFlow(String.valueOf(param.get("projectFlow")));
                for(int i=0; i<devicesFlowList.size(); i++){
                    zam.setRecordFlow(PkUtil.getUUID());
                    zam.setTypeId("DEVICES");
                    zam.setTypeName("设备");
                    zam.setMaterialFlow(devicesFlowList.get(i));
                    zam.setMaterialName(devicesNameList.get(i));
                    zam.setMaterialNumber(devicesNumList.get(i));
                    GeneralMethod.setRecordInfo(zam,true);
                    count += materialMapper.insertSelective(zam);
                }
            }
        }else{//新增
            ZseyProjectMainExample example = new ZseyProjectMainExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andProjectNameEqualTo(String.valueOf(param.get("projectName")));
            if(projectMainMapper.countByExample(example) > 0){//是否已配置过同培训项目
                return -1;
            };
            //新增培训项目
            ZseyProjectMain main = new ZseyProjectMain();
            String pk = PkUtil.getUUID();
            main.setProjectFlow(pk);
            main.setProjectName(String.valueOf(param.get("projectName")));
            GeneralMethod.setRecordInfo(main,true);
            projectMainMapper.insertSelective(main);
            //新增设备耗材详情
            ZseyAppointMaterial zam = new ZseyAppointMaterial();
            zam.setAppointFlow(pk);
            for(int i=0; i<suppliesFlowList.size(); i++){
                zam.setRecordFlow(PkUtil.getUUID());
                zam.setTypeId("SUPPLIES");
                zam.setTypeName("耗材");
                zam.setMaterialFlow(suppliesFlowList.get(i));
                zam.setMaterialName(suppliesNameList.get(i));
                zam.setMaterialNumber(suppliesNumList.get(i));
                GeneralMethod.setRecordInfo(zam,true);
                count += materialMapper.insertSelective(zam);
            }
            for(int i=0; i<devicesFlowList.size(); i++){
                zam.setRecordFlow(PkUtil.getUUID());
                zam.setTypeId("DEVICES");
                zam.setTypeName("设备");
                zam.setMaterialFlow(devicesFlowList.get(i));
                zam.setMaterialName(devicesNameList.get(i));
                zam.setMaterialNumber(devicesNumList.get(i));
                GeneralMethod.setRecordInfo(zam,true);
                count += materialMapper.insertSelective(zam);
            }
        }
        return count;
    }

    @Override
    public void exportZdyExcel(String beginDate, String endDate, List<Map<String, Object>> dataList, OutputStream os) throws Exception{
        List<String> monthList = new ArrayList<>();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId("ZseyTrainRoom");
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date d1 = sdf.parse(beginDate);
        Date d2 = sdf.parse(endDate);
        Calendar dd = Calendar.getInstance();
        dd.setTime(d1);
        monthList.add("教室");
        monthList.add(sdf.format(d1.getTime()));
        while(dd.getTime().before(d2)){
            dd.add(Calendar.MONTH, 1);
            monthList.add(sdf.format(dd.getTime()));
        }
        monthList.add("合计");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = null;
        for(int i=0;i<monthList.size();i++){
            cell = row.createCell(i);
            cell.setCellValue(monthList.get(i));
            cell.setCellStyle(style);
            if(i==0){
                sheet.setColumnWidth(i, 7*700);
            }else if(i==monthList.size()-1){
                sheet.setColumnWidth(i, 5*700);
            }else{
                sheet.setColumnWidth(i, monthList.get(i).length()*700);
            }
        }
        if(null != dictList && !dictList.isEmpty()){
            for(int i=0;i<dictList.size()+1;i++){
                row = sheet.createRow(i + 1);
                if(i==dictList.size()){//合计行
                    row.createCell(0).setCellValue("合计");
                    Integer total = 0;
                    for(Map<String,Object> map : dataList){
                        total = total+Integer.valueOf(String.valueOf((BigDecimal)map.get("monthTotal")));
                    }
                    row.createCell(monthList.size()-1).setCellValue(total);//总合计
                    for(int j=1;j<monthList.size()-1;j++){
                        Integer count = 0;
                        Integer rlt = 0;
                        String month = monthList.get(j);
                        for(Map<String,Object> map : dataList){
                            if(month.equals(map.get("trainDate"))){
                                rlt = Integer.valueOf(String.valueOf((BigDecimal)map.get("monthTotal")));
                                count= count+rlt;
                            }
                        }
                        if(null != rlt){
                            row.createCell(j).setCellValue(count);
                        }else{
                            row.createCell(j).setCellValue(0);
                        }
                    }
                }else{//数据行
                    row.createCell(0).setCellValue(dictList.get(i).getDictName());
                    String dictId = dictList.get(i).getDictId();
                    Integer hxcount = 0;
                    for(int j=1;j<monthList.size();j++){
                        Integer rlt = 0;
                        String month = monthList.get(j);
                        if(j==monthList.size()-1){//合计列
                            row.createCell(j).setCellValue(hxcount);
                        }else{
                            for(Map<String,Object> map : dataList){
                                if(dictId.equals(map.get("trainRoomId")) && month.equals(map.get("trainDate"))){
                                    rlt = Integer.valueOf(String.valueOf((BigDecimal)map.get("monthTotal")));
                                    hxcount= hxcount+ rlt;
                                }
                            }
                            if(null != rlt){
                                row.createCell(j).setCellValue(rlt);
                            }else{
                                row.createCell(j).setCellValue(0);
                            }
                        }
                    }
                }
            }
        }
        wb.write(os);
    }

    @Override
    public void exportDeptUseExcel(List<Map<String, Object>> dataList, OutputStream os) throws Exception {
        List<SysDept> deptList = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        int inx = 0;
        Map<String,List<Map<String,Object>>> deptMap = new HashMap<>();
        if(null != dataList && !dataList.isEmpty()) {
            for (Map<String, Object> dept : dataList) {
                String key = (String) dept.get("deptFlow");
                int inx2 = Integer.valueOf(dept.get("inx").toString());
                if (inx2 > inx) {
                    inx = inx2;
                }
                if (deptMap.get(key) == null) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    list.add(dept);
                    deptMap.put(key, list);
                } else {
                    deptMap.get(key).add(dept);
                }
            }
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = null;
        HSSFCell cell2 = null;
        for(int i=0;i<deptList.size();i++){
            cell = row.createCell(2*i);
            cell.setCellValue(deptList.get(i).getDeptName());
            cell.setCellStyle(style);
            cell = row1.createCell(2*i);
            cell.setCellValue("耗材名称");
            cell.setCellStyle(style);
            cell = row1.createCell(2*i+1);
            cell.setCellValue("数量");
            cell.setCellStyle(style);
            sheet.addMergedRegion(new CellRangeAddress(0,0,2*i,2*i+1));
        }
        for(int i=0;i<inx;i++) {
            row = sheet.createRow(i + 2);
            for(int j=0;j<deptList.size();j++){
                cell = row.createCell(2*j);
                cell2 = row.createCell(2*j+1);
                List<Map<String,Object>> list = deptMap.get(deptList.get(j).getDeptFlow());
                if(null != list && i<list.size()){
                    cell.setCellValue((String)list.get(i).get("suppliesName"));
                    cell2.setCellValue(String.valueOf((BigDecimal)list.get(i).get("total")));
                }else{
                    cell.setCellValue("");
                    cell2.setCellValue("");
                }
                cell.setCellStyle(style);
                cell2.setCellStyle(style);
            }
        }
        wb.write(os);
    }
}
