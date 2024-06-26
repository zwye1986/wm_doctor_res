package com.pinde.sci.biz.erp.impl;

import com.alibaba.druid.util.StringUtils;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpUserRegionPopedomBiz;
import com.pinde.sci.common.GeneralErpMethod;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpUserRegionPopedomMapper;
import com.pinde.sci.enums.erp.RegionTypeEnum;
import com.pinde.sci.model.mo.ErpUserRegionPopedom;
import com.pinde.sci.model.mo.ErpUserRegionPopedomExample;
import com.pinde.sci.model.mo.ErpUserRegionPopedomExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpUserRegionPopedomBizImpl implements IErpUserRegionPopedomBiz{

	@Autowired
	private ErpUserRegionPopedomMapper popedomMapper;
	
	@Override
	public List<ErpUserRegionPopedom> searchRegionPopList(
			ErpUserRegionPopedom regPop) {
		ErpUserRegionPopedomExample example=new ErpUserRegionPopedomExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(regPop!=null){
			if(StringUtil.isNotBlank(regPop.getUserFlow())){
				criteria.andUserFlowEqualTo(regPop.getUserFlow());
			}
			return popedomMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public void saveRegion(String userFlow, String[] provIds, String[] provNames) {
		ErpUserRegionPopedom update = new ErpUserRegionPopedom();
		update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(update, false);
		ErpUserRegionPopedomExample example = new ErpUserRegionPopedomExample();
		example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		popedomMapper.updateByExampleSelective(update, example);
		if (provIds != null) {
			for (int i = 0; i < provIds.length; i++) {
				String provId = provIds[i];
				ErpUserRegionPopedom erpUserRegionPopedom = new ErpUserRegionPopedom();
				erpUserRegionPopedom.setRecordFlow(PkUtil.getUUID());
				erpUserRegionPopedom.setUserFlow(userFlow);
				erpUserRegionPopedom.setProvId(provId);
				erpUserRegionPopedom.setProvName(provNames[i]);
				erpUserRegionPopedom.setRegionTypeId(RegionTypeEnum.Prov.getId());
				erpUserRegionPopedom.setRegionTypeName(RegionTypeEnum.Prov.getName());
				GeneralMethod.setRecordInfo(erpUserRegionPopedom, true);
				popedomMapper.insertSelective(erpUserRegionPopedom);
			}
		}
	}

	@Override
	public List<ErpUserRegionPopedom> getErpUserRegionByUserFlow(String userFlow) {
		ErpUserRegionPopedomExample example = new ErpUserRegionPopedomExample();
		example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("REGION_TYPE_ID desc,PROV_ID,CITY_ID,AREA_ID");
		return popedomMapper.selectByExample(example);
	}

	@Override
	public void delErpUserRegion(String recordFlow) {
		ErpUserRegionPopedom erpUserRegionPopedom = new ErpUserRegionPopedom();
		GeneralMethod.setRecordInfo(erpUserRegionPopedom, false);
		erpUserRegionPopedom.setRecordFlow(recordFlow);
		erpUserRegionPopedom.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		popedomMapper.updateByPrimaryKeySelective(erpUserRegionPopedom);

	}

	/**
	 * 获取用户地区权限区域
	 * @param currUserFlow
	 * @return
     */
	@Override
	public List<ErpUserRegionPopedom> getUserRegionArea(String currUserFlow) {
		ErpUserRegionPopedom popedom = new ErpUserRegionPopedom();
		popedom.setUserFlow(currUserFlow);
		popedom.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List <ErpUserRegionPopedom> userRegionPopedoms = searchRegionPopList(popedom);
		List<ErpUserRegionPopedom> areaList = GenUserRegionPopedom(userRegionPopedoms);
		return areaList;
	}

	/**
	 * 根据省份权限列表加载区域
	 * @param userRegionPopedoms
	 * @return
	 */
	private List<ErpUserRegionPopedom> GenUserRegionPopedom(List<ErpUserRegionPopedom> userRegionPopedoms){
		List<ErpUserRegionPopedom> pops = new ArrayList<ErpUserRegionPopedom>();
		boolean flag01 = false;
		boolean flag02 = false;
		boolean flag03 = false;
		boolean flag04 = false;

		for(ErpUserRegionPopedom popedom : userRegionPopedoms){
			ErpUserRegionPopedom pop = new ErpUserRegionPopedom();
			if(! flag01 && StringUtil.isEquals(popedom.getProvId(), "310000", "320000", "330000", "340000", "350000", "360000", "370000")){
				pop.setAreaId("01");
				pop.setAreaName("华东地区");
				pops.add(pop);
				flag01 = true;
			}else if(! flag02 && StringUtil.isEquals(popedom.getProvId(), "440000", "450000", "460000")){
				pop.setAreaId("02");
				pop.setAreaName("华南地区");
				pops.add(pop);
				flag02 = true;
			}else if(! flag03 && StringUtil.isEquals(popedom.getProvId(), "110000", "120000", "130000", "140000", "150000", "210000", "220000", "230000")){
				pop.setAreaId("03");
				pop.setAreaName("华北地区");
				pops.add(pop);
				flag03 = true;
			}else if(! flag04 && StringUtil.isEquals(popedom.getProvId(), "410000", "420000", "430000", "500000", "510000", "520000", "530000", "540000",
					"610000", "620000", "630000", "640000", "650000")){
				pop.setAreaId("04");
				pop.setAreaName("中西部地区");
				pops.add(pop);
				flag04 = true;
			}
		}
		return pops;
	}

}
