package com.pinde.sci.biz.sys.impl;

import com.pinde.core.model.DictForm;
import com.pinde.core.model.DictFormExample;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.DictFormMapper;
import com.pinde.sci.dao.base.SysDictMapper;
import com.pinde.sci.dao.sys.SysCfgExtMapper;
import com.pinde.sci.form.sys.SubDictEditForm;
import com.pinde.core.model.SysDictExample;
import com.pinde.core.model.SysDictExample.Criteria;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class DictBizImpl implements IDictBiz{
	
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private DictFormMapper dictFormMapper;
	@Autowired
	private SysCfgExtMapper sysCfgExtMapper;

    private static final Logger logger = LoggerFactory.getLogger(DictBizImpl.class);


	@Override
	public void addDict(SysDict dict) {
		dict.setDictFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(dict, true);
		this.sysDictMapper.insert(dict);
	}

	@Override
	public void modDict(SysDict dict) {
		GeneralMethod.setRecordInfo(dict, false);
		this.sysDictMapper.updateByPrimaryKeySelective(dict);
	}
	
	@Override
	public void modeDictAndSubDict(SysDict dict) {
		SysDict oldDict = this.readDict(dict.getDictFlow());
		String dictTypeId = oldDict.getDictTypeId();
		if(StringUtil.isNotBlank(dictTypeId)){
			String[] dictDatas = dictTypeId.split("\\.");
			String typeId = dictDatas[0];
            int level = com.pinde.core.common.enums.DictTypeEnum.valueOf(typeId).getLevel();
			if(level>1){
				//查要修改节点的子节点
				SysDictExample example = new SysDictExample();
				example.createCriteria().andDictTypeIdLike(dictTypeId+"."+oldDict.getDictId()+"%").andDictFlowNotEqualTo(dict.getDictFlow());
				List<SysDict> subDicts = this.sysDictMapper.selectByExample(example);
				for(SysDict subDict:subDicts){
					String newDictTypeId = subDict.getDictTypeId().replace(oldDict.getDictId(),dict.getDictId());
					subDict.setDictTypeId(newDictTypeId);
					subDict.setDictTypeName(dict.getDictName());
					GeneralMethod.setRecordInfo(subDict, false);
					this.sysDictMapper.updateByPrimaryKeySelective(subDict);
				}
			}
		}
		GeneralMethod.setRecordInfo(dict, false);
		this.sysDictMapper.updateByPrimaryKeySelective(dict);
		
	}

//	@Override
//	public void delDict(String dictFlow,String recordStatus) {
//		SysDict dict = new SysDict();
//		dict.setDictFlow(dictFlow);
//		dict.setRecordStatus(recordStatus);
//		GeneralMethod.setRecordInfo(dict, false);
//		this.sysDictMapper.updateByPrimaryKeySelective(dict);
//
//	}
	
	@Override
	public void delDictAndSubDict(String dictFlow,String recordStatus) {
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(recordStatus)) {
			SysDict oldDict = this.readDict(dictFlow);
			String dictTypeId = oldDict.getDictTypeId();
			if(StringUtil.isNotBlank(dictTypeId)){
				String[] dictDatas = dictTypeId.split("\\.");
				String typeId = dictDatas[0];
                int level = com.pinde.core.common.enums.DictTypeEnum.valueOf(typeId).getLevel();
				if(level>1){
					SysDict record = new SysDict();
					record.setRecordStatus(recordStatus);
					GeneralMethod.setRecordInfo(record, false);
					SysDictExample example = new SysDictExample();
					example.createCriteria().andDictTypeIdLike(dictTypeId+"."+oldDict.getDictId()+"%");
					this.sysDictMapper.updateByExampleSelective(record , example);
				}
			}
		}
		
		SysDict dict = new SysDict();
		dict.setDictFlow(dictFlow);
		dict.setRecordStatus(recordStatus);
		GeneralMethod.setRecordInfo(dict, false);
		this.sysDictMapper.updateByPrimaryKeySelective(dict);
		
	}

	@Override
	public SysDict readDict(String dictFlow) {
		return this.sysDictMapper.selectByPrimaryKey(dictFlow);
	}

	@Override
	public SysDict readDict(String dictTypeId,String dictId){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		criteria.andDictIdEqualTo(dictId);
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

	@Override
	public SysDict readAllSecondLevelDict(String dictTypeId, String dictId){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictIdEqualTo(dictId);
		criteria.andDictTypeIdLike(dictTypeId+"%");
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

	@Override
	public SysDict readDict(String dictTypeId,String dictId, String orgFlow){
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		criteria.andDictIdEqualTo(dictId);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
		if(sysDictList.size()>0){
			return sysDictList.get(0);
		}
		return null;
	}

    @Override
    public List<SysDict> searchDictListByDictName(SysDict sysDict) {
        SysDictExample example = new SysDictExample();
        Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
            criteria.andDictTypeIdEqualTo(sysDict.getDictTypeId());
        }
        if(StringUtil.isNotBlank(sysDict.getDictName())){
            criteria.andDictNameEqualTo(sysDict.getDictName());
        }
        return this.sysDictMapper.selectByExample(example);
    }

    @Override
	public List<SysDict> searchDictList(SysDict sysDict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
//		criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			criteria.andDictTypeIdEqualTo(sysDict.getDictTypeId());
		}
		if(StringUtil.isNotBlank(sysDict.getDictId())){
			criteria.andDictIdLike("%"+sysDict.getDictId()+"%");
		}
		if(StringUtil.isNotBlank(sysDict.getDictName())){
			criteria.andDictNameLike("%"+sysDict.getDictName()+"%");
		}
		if(StringUtil.isNotBlank(sysDict.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysDict.getRecordStatus());
		}
		if(StringUtil.isNotBlank(sysDict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(sysDict.getOrgFlow());
		}
		if(StringUtil.isNotBlank(sysDict.getCheckStatusId()) && "NotSubmit".equals(sysDict.getCheckStatusId())){
			criteria.andIsSubmitIdEqualTo(sysDict.getCheckStatusId());
		}
		if(StringUtil.isNotBlank(sysDict.getCheckStatusId()) && !"NotSubmit".equals(sysDict.getCheckStatusId())){
			criteria.andCheckStatusIdEqualTo(sysDict.getCheckStatusId());
		}
		//example.setOrderByClause(" RECORD_STATUS DESC,CREATE_TIME");
		example.setOrderByClause(" ORDINAL");
		return this.sysDictMapper.selectByExample(example);
	}

	@Override
	public List<SysDict> searchDictListAllByDictTypeId(String dictTypeId , boolean isShowAll) {
		
		SysDictExample example = new SysDictExample();
		Criteria criteria  = example.createCriteria();
		criteria.andDictTypeIdEqualTo(dictTypeId);
		if(!isShowAll){
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		example.setOrderByClause(" ORDINAL");
		return this.sysDictMapper.selectByExample(example);
		
	}

	@Override
	public List<SysDict> searchDictListByDictTypeId(String dictTypeId) {
		SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL");
		return this.sysDictMapper.selectByExample(example);
		
	}

    @Override
    public List<SysDict> searchDictListByDictTypeIdAndDictName(String dictTypeId, String dictName) {
        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andDictNameEqualTo(dictName)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return this.sysDictMapper.selectByExample(example);

    }

    @Override
	public List<SysDict> searchDictListByDictTypeIdAndDictId(String dictTypeId,String dictId) {
		SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria().andDictTypeIdEqualTo(dictTypeId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(dictId))
		{
			criteria.andDictIdEqualTo(dictId);
		}
		example.setOrderByClause("ORDINAL");
		return this.sysDictMapper.selectByExample(example);

	}

//	@Override
//	public List<SysDict> searchDictListByDictTypeIdLike(String dictTypeId) {
//		SysDictExample example = new SysDictExample();
//		example.createCriteria().andDictTypeIdLike(dictTypeId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		example.setOrderByClause("ORDINAL");
//		return this.sysDictMapper.selectByExample(example);
//	}


	@Override
	public List<SysDict> searchDictListByDictTypeIdNotIncludeSelf(SysDict dict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(dict.getDictTypeId())){
			criteria.andDictTypeIdEqualTo(dict.getDictTypeId());
		}
		if(StringUtil.isNotBlank(dict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(dict.getOrgFlow());
		}
		if(StringUtil.isNotBlank(dict.getDictFlow())){
			criteria.andDictFlowNotEqualTo(dict.getDictFlow());
		}
		return this.sysDictMapper.selectByExample(example);
	}

	@Override
	public List<SysDict> searchAllSecondLevelDictListByDictTypeIdNotIncludeSelf(String dictTypeId ,SysDict dict) {
		SysDictExample example = new SysDictExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(dict.getDictId())){
			criteria.andDictIdEqualTo(dict.getDictId());
		}
		if(StringUtil.isNotBlank(dictTypeId)){
			criteria.andDictTypeIdLike(dictTypeId+"%");
		}
		if(StringUtil.isNotBlank(dict.getOrgFlow())){
			criteria.andOrgFlowEqualTo(dict.getOrgFlow());
		}
		if(StringUtil.isNotBlank(dict.getDictFlow())){
			criteria.andDictFlowNotEqualTo(dict.getDictFlow());
		}
		return this.sysDictMapper.selectByExample(example);
	}

	@Override
	public void saveOrder(String[] dictFlows) {
		if(dictFlows==null) return;
		int i=1;
		for(String flow : dictFlows){
			SysDict dict = new SysDict();
			dict.setDictFlow(flow);
			dict.setOrdinal((i++));
			this.sysDictMapper.updateByPrimaryKeySelective(dict);		
		}	
		
	}

	@Override
	public void saveSubDict(SubDictEditForm subDictForm) {
		String topDictFlow = subDictForm.getTopDictFlow();
		SysDict topDict = this.readDict(topDictFlow);
		//二级及三级字典代码不能重复
        if (com.pinde.core.common.enums.DictTypeEnum.OscaTrainingType.getId().equals(topDict.getDictTypeId()))
		{
			saveNewFunc(subDictForm,topDict);
		}else{
			saveOldFunc(subDictForm,topDict);
		}
		
	}

	private void saveNewFunc(SubDictEditForm subDictForm, SysDict topDict) {
		String dictTypeId = "";
		List<SysDict> subDicts = subDictForm.getSubDicts();
		dictTypeId = topDict.getDictTypeId()+"."+topDict.getDictId();
		SysDict dict = subDictForm.getDict();
		dict.setDictTypeName(topDict.getDictName());
		dict.setDictTypeId(dictTypeId);
		if(StringUtil.isBlank(dict.getDictFlow())){
			//新增字典时判断该类型字典代码是否存在
			SysDict sysDict = this.readAllSecondLevelDict(topDict.getDictTypeId()+".",dict.getDictId());
			if(null!=sysDict){
				throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
			}
			this.addDict(dict);
			if(subDicts!=null && !subDicts.isEmpty()){
				dictTypeId = dict.getDictTypeId()+"."+dict.getDictId();
				for(SysDict subdict:subDicts){
					subdict.setDictTypeName(dict.getDictName());
					subdict.setDictTypeId(dictTypeId);
					if(StringUtil.isBlank(subdict.getDictFlow())){
						SysDict exitSysDict = this.readDict(dictTypeId, dict.getDictId());
						if(null!=exitSysDict){
							throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
						}
						this.addDict(subdict);
					}else{
						List<SysDict> dictList = this.searchDictListByDictTypeIdNotIncludeSelf(subdict);
						for(SysDict exitDict:dictList){
							if(subdict.getDictId().equals(exitDict.getDictId())){
								throw new RuntimeException("字典代码为："+subdict.getDictId()+"的字典已经存在，请换一个！");
							}
						}
						this.modDict(dict);
					}

				}
			}
		}else{
			//修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
			List<SysDict> exitDictList = this.searchAllSecondLevelDictListByDictTypeIdNotIncludeSelf(topDict.getDictTypeId()+".",dict);
			for(SysDict sysDict:exitDictList){
				if(dict.getDictId().equals(sysDict.getDictId())){
					throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
				}
			}
			this.modDict(dict);
			if(subDicts!=null && !subDicts.isEmpty()){
				dictTypeId = dict.getDictTypeId()+"."+dict.getDictId();
				for(SysDict subdict:subDicts){
					subdict.setDictTypeName(dict.getDictName());
					subdict.setDictTypeId(dictTypeId);
					if(StringUtil.isBlank(subdict.getDictFlow())){
						SysDict exitSysDict = this.readDict(dictTypeId, dict.getDictId());
						if(null!=exitSysDict){
							throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
						}
						this.addDict(subdict);
					}else{
						List<SysDict> dictList = this.searchDictListByDictTypeIdNotIncludeSelf(subdict);
						for(SysDict exitDict:dictList){
							if(subdict.getDictId().equals(exitDict.getDictId())){
								throw new RuntimeException("字典代码为："+subdict.getDictId()+"的字典已经存在，请换一个！");
							}
						}
						this.modDict(dict);
					}

				}
			}
		}
	}

	private void saveOldFunc(SubDictEditForm subDictForm, SysDict topDict) {

		String dictTypeId = "";
		List<SysDict> subDicts = subDictForm.getSubDicts();
		dictTypeId = topDict.getDictTypeId()+"."+topDict.getDictId();
		SysDict dict = subDictForm.getDict();
		dict.setDictTypeName(topDict.getDictName());
		dict.setDictTypeId(dictTypeId);
		if(StringUtil.isBlank(dict.getDictFlow())){
			//新增字典时判断该类型字典代码是否存在
			SysDict sysDict = this.readDict(dictTypeId, dict.getDictId());
			if(null!=sysDict){
				throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
			}
			this.addDict(dict);
			if(subDicts!=null && !subDicts.isEmpty()){
				dictTypeId = dict.getDictTypeId()+"."+dict.getDictId();
				for(SysDict subdict:subDicts){
					subdict.setDictTypeName(dict.getDictName());
					subdict.setDictTypeId(dictTypeId);
					if(StringUtil.isBlank(subdict.getDictFlow())){
						SysDict exitSysDict = this.readDict(dictTypeId, dict.getDictId());
						if(null!=exitSysDict){
							throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
						}
						this.addDict(subdict);
					}else{
						List<SysDict> dictList = this.searchDictListByDictTypeIdNotIncludeSelf(subdict);
						for(SysDict exitDict:dictList){
							if(subdict.getDictId().equals(exitDict.getDictId())){
								throw new RuntimeException("字典代码为："+subdict.getDictId()+"的字典已经存在，请换一个！");
							}
						}
						this.modDict(dict);
					}

				}
			}
		}else{
			//修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
			List<SysDict> exitDictList = this.searchDictListByDictTypeIdNotIncludeSelf(dict);
			for(SysDict sysDict:exitDictList){
				if(dict.getDictId().equals(sysDict.getDictId())){
					throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
				}
			}
			this.modDict(dict);
			if(subDicts!=null && !subDicts.isEmpty()){
				dictTypeId = dict.getDictTypeId()+"."+dict.getDictId();
				for(SysDict subdict:subDicts){
					subdict.setDictTypeName(dict.getDictName());
					subdict.setDictTypeId(dictTypeId);
					if(StringUtil.isBlank(subdict.getDictFlow())){
						SysDict exitSysDict = this.readDict(dictTypeId, dict.getDictId());
						if(null!=exitSysDict){
							throw new RuntimeException("字典代码为："+dict.getDictId()+"的字典已经存在，请换一个！");
						}
						this.addDict(subdict);
					}else{
						List<SysDict> dictList = this.searchDictListByDictTypeIdNotIncludeSelf(subdict);
						for(SysDict exitDict:dictList){
							if(subdict.getDictId().equals(exitDict.getDictId())){
								throw new RuntimeException("字典代码为："+subdict.getDictId()+"的字典已经存在，请换一个！");
							}
						}
						this.modDict(dict);
					}

				}
			}
		}
	}


	@Override
	public int importDict(MultipartFile file,String dictTypeId) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			return parseExcel(wb,dictTypeId);
		} catch (Exception e) {
            logger.error("", e);
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
//        return 0;
	}
	private static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
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
	private int parseExcel(Workbook wb,String dictTypeId) {
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new RuntimeException("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
				SysDict dict = new SysDict();
				dict.setDictTypeId(dictTypeId);
                dict.setDictTypeName(com.pinde.core.common.enums.DictTypeEnum.getNameById(dictTypeId));
				SysUser su = GlobalContext.getCurrentUser();
				dict.setOrgFlow(su.getOrgFlow());
				dict.setOrgName(su.getOrgName());
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					Cell cell = r.getCell(j);
					if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType().getCode() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					String currTitle=colnames.get(j);
					if("字典代码".equals(currTitle)){
						dict.setDictId(value);
					}
					if("字典名称".equals(currTitle)){
						dict.setDictName(value);
					}
					if("代码描述".equals(currTitle)){
						dict.setDictDesc(value);
					}
					if("排序码".equals(currTitle)){
						dict.setOrdinal(Integer.parseInt(value));
					}
				}
				if(StringUtil.isBlank(dict.getDictId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，字典代码不能为空！");
				}
				if(StringUtil.isBlank(dict.getDictName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，字典名称不能为空！");
				}
				if(dict.getOrdinal()==null){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，排序码不能为空！");
				}
				SysDict sysDict=readDict(dict.getDictTypeId(),dict.getDictId(),dict.getOrgFlow());
				if(null!=sysDict){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，字典代码重复！");
				}
				addDict(dict);
				count ++;
			}
		}
		return count;
	}

	@Override
	public List<DictForm> searchDictForm(DictForm dictForm) {
		DictFormExample example = new DictFormExample();
        DictFormExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(dictForm!=null){
			if(StringUtil.isNotBlank(dictForm.getDictFlow())){
				criteria.andDictFlowEqualTo(dictForm.getDictFlow());
			}
		}
		example.setOrderByClause("DETAIL_ORDER");
		return dictFormMapper.selectByExample(example);
	}

	@Override
	public int editDictForm(DictForm dictForm) {
		String recordFlow = dictForm.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			GeneralMethod.setRecordInfo(dictForm,false);
			return dictFormMapper.updateByPrimaryKeySelective(dictForm);
		}else {
			dictForm.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(dictForm,true);
			return dictFormMapper.insertSelective(dictForm);
		}
	}

	@Override
	public int updateSchoolSubmit(Map<String,Object> map) {
		return sysCfgExtMapper.updateSchoolSubmit(map);
	}

	@Override
	public int saveSchoolSubmit(List<String> dictFlowList) {
		return sysCfgExtMapper.saveSchoolSubmit(dictFlowList);
	}

	@Override
	public int updateCheckAll(Map<String, Object> param) {
		return sysCfgExtMapper.updateCheckAll(param);
	}

	@Override
	public int updateSchoolNotSubmit(List<String> dictFlowList) {
		return sysCfgExtMapper.updateSchoolNotSubmit(dictFlowList);
	}

	@Override
	public SysDict searchDict(SysDict dictSearch) {
		SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(dictSearch.getDictId())){
			criteria.andDictIdEqualTo(dictSearch.getDictId());
		}
		if(StringUtil.isNotBlank(dictSearch.getDictTypeId())){
			criteria.andDictTypeIdEqualTo(dictSearch.getDictTypeId());
		}
		List<SysDict> list = sysDictMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
