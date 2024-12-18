package com.pinde.sci.biz.sys.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.common.enums.sys.ReqTypeEnum;
import com.pinde.core.common.sci.dao.SysUserDeptMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.model.SysDeptExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class DeptBizImpl implements IDeptBiz {
    private static final Logger logger = LoggerFactory.getLogger(DeptBizImpl.class);
	
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysDeptExtMapper deptExtMapper;
	@Autowired
	private SysUserDeptMapper userDeptMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private ISchDeptBiz schDeptBiz;

	@Override
	public List<SysDept> selectByExample(SysDeptExample example) {
		return sysDeptMapper.selectByExample(example);
	}

	@Override
	public List<SysDept> selectByDeptId(String rotationFlow,String orgFlow) {
		List<SysDept> result = new ArrayList<>();
		if (StringUtils.isEmpty(rotationFlow)) {
			return result;
		}
		if (StringUtils.isEmpty(orgFlow)) {
			return result;
		}
		return deptExtMapper.getSchDeptByBzDeptCode(rotationFlow,orgFlow);
	}

	@Override
	public List<SchAndStandardDeptCfg> getSchDeptByBzIds(List<String> bzDeptIds, List<String> orgFlows,String orgFlow) {
		List<SchAndStandardDeptCfg> result = new ArrayList<>();
		if (CollectionUtil.isEmpty(bzDeptIds)) {
			return result;
		}
		if (CollectionUtil.isEmpty(orgFlows)) {
			return result;
		}
		return deptExtMapper.getSchDeptByBzIds(bzDeptIds, orgFlows,orgFlow);
	}

	@Override
	public List<String> relToMeOrgFlow(String myOrgFlow) {
		List<String> result = new ArrayList<>();
		if (StringUtils.isEmpty(myOrgFlow))  {
			return result;
		}
		return deptExtMapper.relToMeOrgFlow(myOrgFlow);
	}

	@Override
	public SysDept readSysDept(String sysDeptFlow) {
		return sysDeptMapper.selectByPrimaryKey(sysDeptFlow);
	}

	@Override
	public int saveDept(SysDept dept) {
		if(StringUtil.isNotBlank(dept.getDeptFlow())){
			GeneralMethod.setRecordInfo(dept, false);
			int ret = sysDeptMapper.updateByPrimaryKeySelective(dept);
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))){
//				//全部同步后saveDept改称update
//				dept = sysDeptMapper.selectByPrimaryKey(dept.getDeptFlow());
//				boolean result = WeixinQiYeUtil.saveDept(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"),dept);
//				logger.debug("wei xin qi ye saveuser is "+result);
//			}
			if(ret==1)
			{
				updateOtherTable(dept);
			}
			return ret;
		}else{
			dept.setDeptFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(dept, true);
			int ret = sysDeptMapper.insert(dept);
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))){
//				//全部同步后saveDept改称update
//				dept = sysDeptMapper.selectByPrimaryKey(dept.getDeptFlow());
//				boolean result = WeixinQiYeUtil.saveDept(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"),dept);
//				logger.debug("wei xin qi ye saveuser is "+result);
//			}
			if(ret==1)
			{
				updateOtherTable(dept);
			}
			return ret;
		}
	}


	@Override
	public int saveDept2(SysDept dept, HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(dept.getDeptFlow())){
			//记录日志
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogUpdate.getId());
			log.setOperName(OperTypeEnum.LogUpdate.getName());
			log.setReqTypeId(ReqTypeEnum.PUT.getId());
			log.setLogDesc(user.getUserName()+OperTypeEnum.LogUpdate.getName()+"了"+dept.getDeptName()+"轮转科室，"+"登录IP["+request.getRemoteAddr()+"]");
            log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			logMapper.insert(log);
			GeneralMethod.setRecordInfo(dept, false);
			int ret = sysDeptMapper.updateByPrimaryKeySelective(dept);
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))){
//				//全部同步后saveDept改称update
//				dept = sysDeptMapper.selectByPrimaryKey(dept.getDeptFlow());
//				boolean result = WeixinQiYeUtil.saveDept(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"),dept);
//				logger.debug("wei xin qi ye saveuser is "+result);
//			}
			if(ret==1)
			{
				updateOtherTable(dept);
			}
			return ret;
		}else{
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogInsert.getId());
			log.setOperName(OperTypeEnum.LogInsert.getName());
			log.setReqTypeId(ReqTypeEnum.POST.getId());
			log.setLogDesc(user.getUserName()+OperTypeEnum.LogInsert.getName()+"了"+dept.getDeptName()+"轮转科室，"+"登录IP["+request.getRemoteAddr()+"]");
            log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			logMapper.insert(log);
			dept.setDeptFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(dept, true);
			int ret = sysDeptMapper.insert(dept);
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))){
//				//全部同步后saveDept改称update
//				dept = sysDeptMapper.selectByPrimaryKey(dept.getDeptFlow());
//				boolean result = WeixinQiYeUtil.saveDept(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"),dept);
//				logger.debug("wei xin qi ye saveuser is "+result);
//			}
			if(ret==1)
			{
				updateOtherTable(dept);
			}
			return ret;
		}
	}

	private void updateOtherTable(SysDept dept) {
		if(StringUtil.isNotBlank(dept.getDeptName())&&StringUtil.isNotBlank(dept.getDeptFlow())) {
			SysUser sysUser = new SysUser();
			sysUser.setDeptName(dept.getDeptName());
			SysUserExample example = new SysUserExample();
			example.createCriteria().andDeptFlowEqualTo(dept.getDeptFlow());
			userMapper.updateByExampleSelective(sysUser, example);
		}
	}

	@Override
	public List<SysDept> searchDept(SysDept sysdept) {
		SysDeptExample sysDeptExample=new SysDeptExample();
        Criteria criteria = sysDeptExample.createCriteria();//.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysdept.getDeptFlow())){
			criteria.andDeptFlowEqualTo(sysdept.getDeptFlow());
		}
		if(StringUtil.isNotBlank(sysdept.getDeptName())){
			criteria.andDeptNameLike("%"+sysdept.getDeptName()+"%");
		}
		if(StringUtil.isNotBlank(sysdept.getDeptCode())){
			criteria.andDeptCodeLike("%"+sysdept.getDeptCode()+"%");
		}
		if(StringUtil.isNotBlank(sysdept.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysdept.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysdept.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysdept.getRecordStatus());
		}
		sysDeptExample.setOrderByClause("RECORD_STATUS DESC,ORDINAL");
		return sysDeptMapper.selectByExample(sysDeptExample);
	}

	@Override
	public List<SysDept> searchDeptByOrg(String orgFlow){
		SysDeptExample example = new SysDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		example.setOrderByClause("ORDINAL");
		return sysDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SysDept> searchDeptByNameAndFlow(String deptName,String orgFlow){
		SysDeptExample example = new SysDeptExample();
		Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if(StringUtil.isNotBlank(deptName)){
			criteria.andDeptNameEqualTo(deptName);
		}
		return sysDeptMapper.selectByExample(example);
	}
	@Override
	public void saveOrder(String[] deptFlow) {
		if(deptFlow==null) return;
		int i=1;
		for(String flow : deptFlow){
			SysDept dept = new SysDept();
			dept.setDeptFlow(flow);
			dept.setOrdinal((i++));
			sysDeptMapper.updateByPrimaryKeySelective(dept);		
		}	
	}
	
	@Override
	public List<SysDept> searchDeptByFlows(List<String> deptFlows){
		if (CollectionUtil.isEmpty(deptFlows)) {
			return new ArrayList<>();
		}
		SysDeptExample example = new SysDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDeptFlowIn(deptFlows);
		return sysDeptMapper.selectByExample(example);
	}
	
	@Override
	public List<SysDept> getDeptByRec(Map<String,Object> map){
		return deptExtMapper.getDeptByRec(map);
	}
	@Override
	public List<SysDept> getDeptByRecForUni(Map<String,Object> map){
		return deptExtMapper.getDeptByRecForUni(map);
	}

	@Override
	public List<SysUserDept> searchByUserFlow(String userFlow) {
		SysUserDeptExample example = new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		return userDeptMapper.selectByExample(example);
	}


	@Override
	public List<SysUserDept> searchByUserFlow(String userFlow, String orgFlow) {
		SysUserDeptExample example = new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if (StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		example.setOrderByClause("ORDINAL");
		return userDeptMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, String>> searchDeptByUnion(SysDept dept, String isUnion) {
		return deptExtMapper.searchDeptByUnion(dept,isUnion);
	}

	@Override
	public SysDept readSysDeptByName(String orgFlow, String deptName) {
		if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(deptName))
		{
			SysDeptExample example = new SysDeptExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDeptNameEqualTo(deptName)
					.andOrgFlowEqualTo(orgFlow);
			List<SysDept> depts=sysDeptMapper.selectByExample(example);
			if(depts!=null&&depts.size()>0)
			{
				return  depts.get(0);
			}

		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryDeptListByFlow(String userFlow) {
		return deptExtMapper.queryDeptListByFlow(userFlow);
	}

	@Override
	public List<Map<String,String>> searchDeptByExt(Map<String, Object> paramMap) {
		return deptExtMapper.searchDeptByExt(paramMap);
	}

	@Override
	public int importDeptFromExcel(MultipartFile file,String orgFlow) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcel(wb,orgFlow);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public List<Map<String, Object>> searchDeptByDoctor(String userFlow, String orgFlow) {
		return deptExtMapper.searchDeptByDoctor(userFlow,orgFlow);
	}

	@Override
	public List<SysDept> searchDeptBySpe(String resTrainingSpeId, String orgFlow) {
		if(StringUtil.isNotBlank(resTrainingSpeId))
			return  deptExtMapper.searchDeptBySpe(resTrainingSpeId,orgFlow);
		return null;
	}

	@Override
	public int deleteDeptByKey(String deptFlow) {
		return sysDeptMapper.deleteDeptByKey(deptFlow);
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}

	private int parseExcel(Workbook wb,String orgFlow){
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();

		SysOrg org = null;
		if(StringUtil.isNotBlank(orgFlow)){
			org = orgBiz.readSysOrg(orgFlow);
		}
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <= row_num; i++){
				Row r =  sheet.getRow(i);
				SysDept sysDept = new SysDept();
				String deptName="";
				SchDept schDept = new SchDept();
				String schDeptName="";
				String deptNum;
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell!=null){
							cell.setCellType(CellType.STRING);
							value = cell.getStringCellValue().trim();
						}
					}
					/* 轮转科室	医院科室（父级）	容纳人数 */
					if("实际轮转科室（二级）".equals(colnames.get(j))) {
						schDeptName = value;
						schDept.setSchDeptName(schDeptName);
						if(org != null){
							schDept.setOrgFlow(org.getOrgFlow());
						}
						//检查轮转科室名是否重复
						List<SchDept> schDeptForNames = schDeptBiz.searchSchDeptByExample(schDept);
						if (schDeptForNames != null && schDeptForNames.size() > 0) {
							throw new RuntimeException("导入失败！第"+ i +"行，实际轮转科室（二级）名重复！");
						}
					}else if("医院科室（一级）".equals(colnames.get(j))){
						deptName = value;
						//检查医院科室名是否重复
						List<SysDept> deptForNames = searchDeptByNameAndFlow(deptName,org.getOrgFlow());
						if (deptForNames != null && deptForNames.size() > 0) {
							sysDept = deptForNames.get(0);
						}
					}else if("容纳人数".equals(colnames.get(j))){
						deptNum = value;
						if(StringUtil.isNotBlank(deptNum)){
							if(value.matches("^[1-9]+\\d*$")){
								schDept.setDeptNum(Integer.parseInt(deptNum));
							}else {
								throw new RuntimeException("导入失败！第" + i + "行，容纳人数只能为正整数！");
							}
						}
					}
				}
				if(StringUtil.isBlank(deptName) || StringUtil.isBlank(schDept.getSchDeptName()))
				{
					throw new RuntimeException("导入失败！第"+  i +"行，必填项不能为空！");
				}
				//执行保存
				if(StringUtil.isBlank(sysDept.getDeptFlow())) {
					sysDept.setDeptName(deptName);
					sysDept.setOrgFlow(org.getOrgFlow());
                    sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				}
				String permissionAddSysDeptFlow = InitConfig.getSysCfg("permission_add_sys_dept");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(permissionAddSysDeptFlow)) {
					saveDept(sysDept);
				}else {
					if(StringUtil.isBlank(sysDept.getDeptFlow())) {
						throw new RuntimeException("导入失败！第"+  i +"行，医院科室（一级）不存在！");
					}
				}
				schDept.setDeptFlow(sysDept.getDeptFlow());
				schDept.setDeptName(sysDept.getDeptName());
				schDept.setOrgFlow(org.getOrgFlow());
				schDept.setOrgName(org.getOrgName());
				if(schDept.getDeptNum()==null){
					schDept.setDeptNum(0);
				}
				schDeptBiz.saveSchDept(schDept);
				count ++;
			}
		}
		return count;
	}
}
