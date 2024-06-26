package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAchBookAuthorBiz;
import com.pinde.sci.biz.srm.IAchBookBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SrmAchBookAuthorMapper;
import com.pinde.sci.dao.base.SrmAchBookMapper;
import com.pinde.sci.dao.base.SrmAchFileMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SrmAchBookExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@Transactional(rollbackFor=Exception.class)
public class AchBookBizImpl implements IAchBookBiz,IAchBookAuthorBiz {
	
	@Autowired
	private SrmAchBookMapper bookMapper;
	@Autowired
	private SrmAchBookAuthorMapper bookAuthorMapper;
	@Autowired
	private SrmAchFileMapper fileMapper;
	
	@Autowired
	private ISrmAchProcessBiz srmAchProcessBiz;
	/**
	 * 显示著作数据
	 */
	@Override
	public SrmAchBook readBook(String bookFlow){
		SrmAchBook book = null;
		if(StringUtil.isNotBlank(bookFlow)){
			book = bookMapper.selectByPrimaryKey(bookFlow);
		}
		return book;
	}

	@Override
	public void save(SrmAchBook book,List<SrmAchBookAuthor> authorList,SrmAchFile srmAchFile,SrmAchProcess srmAchProcess) {
		//判断著作编号是否为空，空则添加，不为空则修改
		if(StringUtil.isNotBlank(book.getBookFlow())){
			GeneralMethod.setRecordInfo(book, false);
			bookMapper.updateByPrimaryKeySelective(book);
		}else{
			GeneralMethod.setRecordInfo(book, true);
			book.setBookFlow(PkUtil.getUUID());
			bookMapper.insert(book);
		}
		
		//著作作者
		for(int i=0;i<authorList.size();i++){
			//判断著作作者的流水号是否为空，空则增加，不为空则修改
			if(StringUtil.isNotBlank(authorList.get(i).getAuthorFlow())){
				//修改记录状态
				GeneralMethod.setRecordInfo(authorList.get(i), false);
				//著作流水号
				authorList.get(i).setBookFlow(book.getBookFlow());
				bookAuthorMapper.updateByPrimaryKeySelective(authorList.get(i));
			}else{//新增作者
				GeneralMethod.setRecordInfo(authorList.get(i), true);
				//排序（时间只精确到秒，会有重复情况）
				authorList.get(i).setCreateTime(authorList.get(i).getCreateTime()+i);
				//作者流水号
				authorList.get(i).setAuthorFlow(PkUtil.getUUID());
				//著作流水号
				authorList.get(i).setBookFlow(book.getBookFlow());
				bookAuthorMapper.insert(authorList.get(i));
			}
		}
		
		 //操作附件
		 if(srmAchFile != null){
			srmAchFile.setAchFlow(book.getBookFlow());
			if(StringUtil.isNotBlank(srmAchFile.getFileFlow())){
				GeneralMethod.setRecordInfo(srmAchFile, false);
				fileMapper.updateByPrimaryKeySelective(srmAchFile);
			}
			else{
				srmAchFile.setFileFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(srmAchFile, true);
				fileMapper.insert(srmAchFile);
			}
		 }
		//操作成果过程
	    srmAchProcess.setProcessFlow(PkUtil.getUUID());
	    srmAchProcess.setAchFlow(book.getBookFlow());
	    GeneralMethod.setRecordInfo(srmAchProcess, true);
	    srmAchProcess.setOperateTime(srmAchProcess.getModifyTime());
	    srmAchProcessBiz.saveAchProcess(srmAchProcess);
	}
	
	public int save(SrmAchBook book){
		if(StringUtil.isNotBlank(book.getBookFlow())){
			GeneralMethod.setRecordInfo(book, false);
			bookMapper.updateByPrimaryKeySelective(book);
		}else{
			GeneralMethod.setRecordInfo(book, true);
			book.setBookFlow(PkUtil.getUUID());
			bookMapper.insert(book);
		}
		return GlobalConstant.ONE_LINE;
	}
	
	@Override
	public void editBookAthor(SrmAchBookAuthor author) {
		if(StringUtil.isNotBlank(author.getAuthorFlow())){
			GeneralMethod.setRecordInfo(author, false);
		}
		bookAuthorMapper.updateByPrimaryKeySelective(author);
	}
	
	
	@Override
	public void updateBookStatus(SrmAchBook book, SrmAchProcess process) {
		if(StringUtil.isNotBlank(book.getBookFlow())){
			GeneralMethod.setRecordInfo(book, false);
			bookMapper.updateByPrimaryKeySelective(book);
	       }
        srmAchProcessBiz.saveAchProcess(process);
	}
	
	
	
	@Override
	public List<SrmAchBook> search(SrmAchBook book, List<SysOrg> childOrgList,List<String> bookFlows) {
        SrmAchBookExample example=new SrmAchBookExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<String> orgFlowList=new ArrayList<String>();
		if(null!=childOrgList && !childOrgList.isEmpty()){
			for(SysOrg org:childOrgList){
				orgFlowList.add(org.getOrgFlow());
			}
			criteria.andApplyOrgFlowIn(orgFlowList);
		}
		if(book != null){
			if(StringUtil.isNotBlank(book.getBookName())){
				criteria.andBookNameLike("%"+book.getBookName()+"%");
			}
			if(StringUtil.isNotBlank(book.getOperStatusId())){
				List<String> statusList=Arrays.asList(book.getOperStatusId().split(","));
				criteria.andOperStatusIdIn(statusList);
			}
			if(StringUtil.isNotBlank(book.getProjSourceId())){
				criteria.andProjSourceIdEqualTo(book.getProjSourceId());
			}
			if(StringUtil.isNotBlank(book.getApplyOrgFlow())){
				criteria.andApplyOrgFlowEqualTo(book.getApplyOrgFlow());
			}
			if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
				if(StringUtil.isNotBlank(book.getPublishDate())){
					criteria.andPublishDateLike("%"+book.getPublishDate()+"%");
				}
			}else{
				if(StringUtil.isNotBlank(book.getPublishDate())){
					criteria.andPublishDateEqualTo(book.getPublishDate());
				}
			}
			if(StringUtil.isNotBlank(book.getTypeId())){
				criteria.andTypeIdEqualTo(book.getTypeId());
			}
			if(StringUtil.isNotBlank(book.getApplyUserFlow())){
				criteria.andApplyUserFlowEqualTo(book.getApplyUserFlow());
			}
            if(StringUtil.isNotBlank(book.getApplyUserName())){
                criteria.andApplyUserNameLike("%"+book.getApplyUserName()+"%");
            }
            if(StringUtil.isNotBlank(book.getCategoryId())){
                criteria.andCategoryIdEqualTo(book.getCategoryId());
            }
//            if(StringUtil.isNotBlank(book.getPublishDate())){
//                criteria.andPublishDateLike("%"+book.getPublishDate()+"%");
//            }
            if(InitConfig.getSysCfg("srm_local_type").equals(GlobalConstant.FLAG_Y)){
                if(StringUtil.isNotBlank(book.getDeptFlow())){
                    criteria.andDeptFlowEqualTo(book.getDeptFlow());
                }
            }
			if("wxdermyy".equals(InitConfig.getSysCfg("srm_local_type"))){
				if(StringUtil.isNotBlank(book.getDeptFlow())){
					criteria.andDeptFlowEqualTo(book.getDeptFlow());
				}
			}
			if(StringUtil.isNotBlank(book.getBranchId())){
				criteria.andBranchIdEqualTo(book.getBranchId());
			}
            if(bookFlows!=null&&bookFlows.size()>0){
                criteria.andBookFlowIn(bookFlows);
            }
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return bookMapper.selectByExample(example);
	}

	@Override
	public int edit(SrmAchBook book, List<SrmAchBookAuthor> authorList, SrmAchFile file) {
		if(StringUtil.isNotBlank(book.getBookFlow())){
			GeneralMethod.setRecordInfo(book, false);
			bookMapper.updateByPrimaryKeySelective(book);
		}
		//作者
		if(authorList != null && !authorList.isEmpty()){
			for(SrmAchBookAuthor author : authorList){
				GeneralMethod.setRecordInfo(author, false);
				bookAuthorMapper.updateByPrimaryKeySelective(author);
			}
		}
		//附件
		if(file != null && StringUtil.isNotBlank(file.getFileFlow())){
			GeneralMethod.setRecordInfo(file, false);
			fileMapper.updateByPrimaryKeySelective(file);
		}
		return GlobalConstant.ONE_LINE;
	}

	
	@Override
	public List<SrmAchBookAuthor> searchAuthorList(SrmAchBookAuthor author) {
		SrmAchBookAuthorExample example = new SrmAchBookAuthorExample();
		com.pinde.sci.model.mo.SrmAchBookAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(author.getAuthorName())){
			criteria.andAuthorNameLike("%"+author.getAuthorName()+"%");
		}
		if(StringUtil.isNotBlank(author.getBookFlow())){
			criteria.andBookFlowEqualTo(author.getBookFlow());
		}
        if(StringUtil.isNotBlank(author.getWorkCode())){
            criteria.andWorkCodeLike("%"+author.getWorkCode()+"%");
        }
		example.setOrderByClause("CREATE_TIME ASC");
		return bookAuthorMapper.selectByExample(example);
	}

	@Override
	public SrmAchBook bookIsExist(String isbnCode) {
		SrmAchBook book = null;
		SrmAchBookExample example = new SrmAchBookExample();
		SrmAchBookExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(isbnCode)){
			criteria.andIsbnCodeEqualTo(isbnCode);
			List<SrmAchBook> bookList = bookMapper.selectByExample(example);
			if(bookList != null && bookList.size() >0){
				book = bookList.get(0);
			}
		}
		return book;
	}
}
