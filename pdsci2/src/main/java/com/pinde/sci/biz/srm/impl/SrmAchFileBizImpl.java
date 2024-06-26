package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.ISrmAchFileBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SrmAchFileMapper;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.SrmAchFile;
import com.pinde.sci.model.mo.SrmAchFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class SrmAchFileBizImpl implements ISrmAchFileBiz{
	@Autowired
	private SrmAchFileMapper srmAchFileMapper;

	@Override
	public List<SrmAchFile> searchSrmAchFile(String thesisFlow) {
		SrmAchFileExample example=new SrmAchFileExample();
		example.createCriteria().andAchFlowEqualTo(thesisFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CREATE_TIME DESC");
		return srmAchFileMapper.selectByExample(example);
	}


	@Override
	public SrmAchFile readAchFile(String fileFlow){
		SrmAchFile file = null;
		if(StringUtil.isNotBlank(fileFlow)){
			file = srmAchFileMapper.selectByPrimaryKey(fileFlow);
		}
		return file;
	}

	@Override
	public String saveFileToDirs(MultipartFile file, String folderName, String flow) {
		String path = GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + folderName + File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}


			//删除原文件
			if(StringUtil.isNotBlank(flow)){
				SrmAchFile oldFile = readAchFile(flow);
				String oldPath =StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + oldFile.getFilePath();
				try {
					if(StringUtil.isNotBlank(oldPath)) {
						File imgFile = new File(oldPath);
						if (imgFile.exists()) {
							imgFile.delete();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + File.separator+dateString+File.separator+originalFilename;
		}

		return path;
	}

	@Override
	public int deleteFile(SrmAchFile file) {
		if(file != null){
			if(StringUtil.isNotBlank(file.getFilePath())){
				File imgFile = new File(StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator + file.getFilePath());
				if (imgFile.exists() && imgFile.isFile()) {
					imgFile.delete();
				}
			}
			file.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			return srmAchFileMapper.updateByPrimaryKeySelective(file);
		}
		return 0;
	}
}
