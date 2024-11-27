package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.StuBatchMapper;
import com.pinde.sci.dao.res.StuBatchExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.StuBatchExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class StuBatchBizImpl implements IStuBatchBiz{
	@Autowired
	private StuBatchMapper stuBatchMapper;
	@Autowired
	private StuBatchExtMapper stuBatchExtMapper;


	@Override
	public List<StuBatch> getSingUpStuBatchLst() {
		StuBatchExample example = new StuBatchExample();
		example.createCriteria().andBatchStatusEqualTo("报名中");
		example.setOrderByClause("batch_Date ASC");
		return stuBatchMapper.selectByExample(example);
	}

	@Override
	public List<StuBatch> getStuBatchLst() {
		StuBatchExample example = new StuBatchExample();
		example.setOrderByClause("batch_Date ASC");
		return stuBatchMapper.selectByExample(example);
	}

	@Override
	public StuBatch searchStuBatch(String batchFlow) {
		return stuBatchMapper.selectByPrimaryKey(batchFlow);
	}

	@Override
	public int saveBatchSetting(String batchFlow,String registerTime,String fee) {
		int count = 0;
		StuBatch stuBatch = stuBatchMapper.selectByPrimaryKey(batchFlow);
		if(null != stuBatch){
			stuBatch = new StuBatch();
			stuBatch.setBatchFlow(batchFlow);
			stuBatch.setBatchRegDate(registerTime);
			stuBatch.setMonthFee(new BigDecimal(fee));
			GeneralMethod.setRecordInfo(stuBatch,false);
			count = stuBatchMapper.updateByPrimaryKeySelective(stuBatch);
		}else{
			stuBatch = new StuBatch();
			stuBatch.setBatchFlow(batchFlow);
			stuBatch.setBatchNo(DictTypeEnum.getDictName(DictTypeEnum.DwjxBatch,batchFlow));
			stuBatch.setBatchRegDate(registerTime);
			stuBatch.setMonthFee(new BigDecimal(fee));
			stuBatch.setIsDefault("N");
			GeneralMethod.setRecordInfo(stuBatch,true);
			count = stuBatchMapper.insert(stuBatch);
		}
		return count;
	}

	@Override
	public int saveBatchSetting(String batchFlow,String registerTime,String fee,String batchDate,String batchStatus) {
		int count = 0;
		StuBatch stuBatch = stuBatchMapper.selectByPrimaryKey(batchFlow);
		String batchNo=DateUtil.transDateTime(batchDate,"yyyy-MM","yyyy")+"年"+DateUtil.transDateTime(batchDate,"yyyy-MM","MM")+"月";
		if(null != stuBatch){
			stuBatch.setBatchNo(batchNo);
			stuBatch.setBatchDate(batchDate);
			stuBatch.setBatchRegDate(registerTime);
			stuBatch.setMonthFee(new BigDecimal(fee));
			if(!"报名中".equals(batchStatus))
			{
				stuBatch.setIsDefault("N");
			}
			stuBatch.setBatchStatus(batchStatus);
			GeneralMethod.setRecordInfo(stuBatch,false);
			count = stuBatchMapper.updateByPrimaryKeySelective(stuBatch);
		}else{
			stuBatch = new StuBatch();
			stuBatch.setBatchFlow(PkUtil.getUUID());
			stuBatch.setBatchNo(batchNo);
			stuBatch.setBatchDate(batchDate);
			stuBatch.setBatchStatus(batchStatus);
			stuBatch.setBatchRegDate(registerTime);
			stuBatch.setMonthFee(new BigDecimal(fee));
			stuBatch.setIsDefault("N");
			GeneralMethod.setRecordInfo(stuBatch,true);
			count = stuBatchMapper.insert(stuBatch);
		}
		return count;
	}

	@Override
	public int delBatchSetting(String batchFlow) {
		return stuBatchMapper.deleteByPrimaryKey(batchFlow);
	}

	@Override
	public void changeDefaultSetting(String batchFlow) {
		StuBatch stuBatch = new StuBatch();
		stuBatch.setIsDefault(GlobalConstant.FLAG_N);
		StuBatchExample example = new StuBatchExample();
		example.createCriteria().andIsDefaultEqualTo(GlobalConstant.FLAG_Y);
		stuBatchMapper.updateByExampleSelective(stuBatch,example);//默认系统批次设为N
		stuBatch = new StuBatch();
		stuBatch.setBatchFlow(batchFlow);
		stuBatch.setIsDefault(GlobalConstant.FLAG_Y);
		stuBatchMapper.updateByPrimaryKeySelective(stuBatch);//设为当前系统默认批次
	}

	@Override
	public StuBatch searchStuBatchByDate(String batchDate) {
		if(StringUtil.isNotBlank(batchDate)) {
			StuBatchExample example = new StuBatchExample();
			example.createCriteria().andBatchDateEqualTo(batchDate);
			List<StuBatch> list= stuBatchMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}
	@Override
	public StuBatch getDefualBatch() {
			StuBatchExample example = new StuBatchExample();
			example.createCriteria().andIsDefaultEqualTo(GlobalConstant.FLAG_Y).andBatchStatusEqualTo("报名中");
			List<StuBatch> list= stuBatchMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		return null;
	}

	@Override
	public List<StuBatch> getDefualStuBatchAndStuSelect(String userFlow/*, String batchDate*/) {
		return stuBatchExtMapper.getDefualStuBatchAndStuSelect(userFlow/*,batchDate*/);
	}

	@Override
	public int getStuNumByFlow(String batchFlow) {
		if(StringUtil.isNotBlank(batchFlow))
		{
			return 	stuBatchExtMapper.getStuNumByFlow(batchFlow);
		}
		return 0;
	}
}
