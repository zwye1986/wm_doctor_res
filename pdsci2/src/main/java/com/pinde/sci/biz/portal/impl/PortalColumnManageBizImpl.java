package com.pinde.sci.biz.portal.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PortalColumnMapper;
import com.pinde.sci.dao.portal.PortalColumnExtMapper;
import com.pinde.sci.model.mo.PortalColumn;
import com.pinde.sci.model.mo.PortalColumnExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class PortalColumnManageBizImpl implements IPortalColumnManageBiz {
	@Autowired
	private PortalColumnMapper columnMapper;
	@Autowired
	private PortalColumnExtMapper columnExtMapper;

	@Override
	public List<PortalColumn> getAll(String recordStatus,List<String> columnIds) {
		PortalColumnExample example = new PortalColumnExample();
		PortalColumnExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(recordStatus);
		if(columnIds!=null&&columnIds.size()>0)
		{
			criteria.andColumnIdIn(columnIds);
		}
		example.setOrderByClause(" ordinal asc , create_time asc");
		return this.columnMapper.selectByExample(example);
	}

	@Override
	public String save(PortalColumn column) {
		column.setColumnFlow(PkUtil.getUUID());
		if("0".equals(column.getParentColumnId())){
			column.setParentColumnId(null);
		}
		column.setColumnId(this.getNextColumnId(column.getParentColumnId()));
		GeneralMethod.setRecordInfo(column, true);
		int saveResult =  this.columnMapper.insertSelective(column);
		String returnInfo = "0";
		if(saveResult==1){
			returnInfo = column.getColumnId();
		}
		return returnInfo;
	}

	@Override
	public String getNextColumnId(String parentColumnId) {
		PortalColumnExample example = new PortalColumnExample();
		PortalColumnExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotEmpty(parentColumnId)){
			criteria.andParentColumnIdEqualTo(parentColumnId);
		}else{
			criteria.andParentColumnIdIsNull();
		}
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("column_id desc ");
		List<PortalColumn> list = this.columnMapper.selectByExample(example);
		if(list!=null&&list.size()>0){//已有子栏目
			String bigestColumnId = list.get(0).getColumnId();
			int length = bigestColumnId.length();
			String leftPart = bigestColumnId.substring(0, length-2);
			String rightPart = bigestColumnId.substring(length-2);
			int idValue = new Integer(rightPart).intValue();
			idValue++;
			if(idValue<10){
				rightPart = "0"+idValue;
			}else{
				rightPart = String.valueOf(idValue);
			}
			return leftPart + rightPart;
		}else{//没有子栏目
			if(StringUtil.isNotBlank(parentColumnId)){
				return parentColumnId + "01";
			}
			return "LM01";
		}
	}


	@Override
	public PortalColumn getById(String columnId) {
		if(StringUtil.isNotBlank(columnId)){
			PortalColumnExample example = new PortalColumnExample();
			example.createCriteria().andColumnIdEqualTo(columnId).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<PortalColumn> list = this.columnMapper.selectByExample(example);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null ;
	}

	@Override
	public int update(PortalColumn column) {
		if("0".equals(column.getParentColumnId())){
			column.setParentColumnId(null);
		}
		return this.columnMapper.updateByPrimaryKeySelective(column);
	}


	@Override
	public int updateRecordStatus(List<String> ids, String recordStatus) {
		if(StringUtil.isNotBlank(recordStatus)&&ids!=null&&!ids.isEmpty()){
			PortalColumnExample example = new PortalColumnExample();
			example.createCriteria().andColumnIdIn(ids);
			PortalColumn col = new PortalColumn();
			col.setRecordStatus(recordStatus);
			return this.columnMapper.updateByExampleSelective(col, example);
		}
		return 0;
	}

	@Override
	public List<PortalColumn> searchInxColumnList(Map<String, Object> paramMap) {
		return columnExtMapper.searchInxColumnList(paramMap);
	}
	

}
