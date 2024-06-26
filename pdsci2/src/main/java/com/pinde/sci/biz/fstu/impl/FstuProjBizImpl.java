package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuProjBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.FstuProjMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.model.mo.FstuProj;
import com.pinde.sci.model.mo.FstuProjExample;
import com.pinde.sci.model.mo.FstuProjExample.Criteria;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Transactional(rollbackFor=Exception.class)
public class FstuProjBizImpl implements IFstuProjBiz{

	@Autowired
	private FstuProjMapper projMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Override
	public List<FstuProj> search(FstuProj proj) {
		FstuProjExample example=new FstuProjExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);;
		if(StringUtil.isNotBlank(proj.getProjYear())){
			criteria.andProjYearEqualTo(proj.getProjYear());
		}
		if(StringUtil.isNotBlank(proj.getProjLevelName())){
			criteria.andProjLevelNameEqualTo(proj.getProjLevelName());
		}
		if(StringUtil.isNotBlank(proj.getProjSubject())){
			criteria.andProjSubjectEqualTo(proj.getProjSubject());
		}
//		if(StringUtil.isNotBlank(proj.getDeclarationResultId())){
//			criteria.andDeclarationResultIdEqualTo(proj.getDeclarationResultId());
//		}
		if(StringUtil.isNotBlank(proj.getProjName())){
			criteria.andProjNameLike("%"+proj.getProjName()+"%");
		}
		if(StringUtil.isNotBlank(proj.getApplyUserName())){
			criteria.andApplyUserNameLike("%"+proj.getApplyUserName()+"%");
		}
		if(StringUtil.isNotBlank(proj.getOrgFlow())){
			criteria.andOrgFlowEqualTo(proj.getOrgFlow());
		}
		if(StringUtil.isNotBlank(proj.getApplyOrgName())){
			criteria.andApplyOrgNameLike("%"+proj.getApplyOrgName()+"%");
		}
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			criteria.andApplyOrgFlowEqualTo(proj.getApplyOrgFlow());
		}
		if(StringUtil.isNotBlank(proj.getProjTypeId())){
			criteria.andProjTypeIdEqualTo(proj.getProjTypeId());
		}
		if(StringUtil.isNotBlank(proj.getProjSubjectId())){
			criteria.andProjSubjectIdEqualTo(proj.getProjSubjectId());
		}
		return projMapper.selectByExample(example);
	}
	@Override
	public int save(FstuProj proj){
		if(proj!=null){
			if(StringUtil.isNotBlank(proj.getProjFlow())){
				GeneralMethod.setRecordInfo(proj, false);
				return projMapper.updateByPrimaryKeySelective(proj);
			}else{
				proj.setProjFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(proj, true);
				return projMapper.insertSelective(proj);
				}
		}
		return GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int saveProjAndFile(FstuProj proj,List<PubFile> fileList) throws IOException {
		int result=0;
		if(proj!=null){
			if(StringUtil.isNotBlank(proj.getProjFlow())){
				GeneralMethod.setRecordInfo(proj, false);
				result = projMapper.updateByPrimaryKeySelective(proj);
			}else{
				proj.setProjFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(proj, true);
				result = projMapper.insertSelective(proj);
			}
		}
		//操作附件
		for(PubFile pubFile : fileList) {
			//操作附件
			if (null != pubFile) {
				pubFile.setProductFlow(proj.getProjFlow());
				if (StringUtil.isNotBlank(pubFile.getFileFlow())) {
					GeneralMethod.setRecordInfo(pubFile, false);
					pubFileMapper.updateByPrimaryKeySelective(pubFile);
				} else {
					pubFile.setFileFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(pubFile, true);
					pubFileMapper.insertSelective(pubFile);
				}
			}
		}

		return result;

		}

	
	private void delFile(String projFlow){
		FstuProj oldProj = findByFlow(projFlow);
		if(oldProj!=null){
//			String fileFlow = oldProj.getDeclarationFormFlow();
            String fileFlow = "";
			if(StringUtil.isNotBlank(fileFlow)){
				PubFile pubFile = new PubFile();
				pubFile.setFileFlow(fileFlow);
				pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				this.fileBiz.editFile(pubFile);	
			}
		}
	}
	
	@Override
	public FstuProj findByFlow(String projFlow) {
		return projMapper.selectByPrimaryKey(projFlow);
	}
	

}
