package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuSatAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuSatBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.impl.FileBizImpl;
import com.pinde.sci.biz.srm.IAchSatAuthorBiz;
import com.pinde.sci.biz.srm.ISatBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SrmAchSatExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FstuSatBizImpl implements IFstuSatBiz,IFstuSatAuthorBiz {

	@Autowired
	private ISrmAchProcessBiz srmAchProcessBiz;
	@Autowired
	private SrmAchSatMapper satMapper;
	@Autowired
	private SrmAchSatAuthorMapper satAuthorMapper;
	@Autowired
	private SrmAchFileMapper fileMapper;
	@Autowired
	private FstuSatMapper fstuSatMapper;
	@Autowired
	private FstuSatAuthorMapper fstuSatAuthorMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private IFileBiz fileBiz;

	@Override
	public FstuSat readSat(String satFlow) {
		return fstuSatMapper.selectByPrimaryKey(satFlow);
	}

	@Override
	public int editSatAuthor(SrmAchSatAuthor author) {
		if(author != null && StringUtil.isNotBlank(author.getAuthorFlow())){
			GeneralMethod.setRecordInfo(author, false);
			satAuthorMapper.updateByPrimaryKeySelective(author);
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int save(FstuSat sat, List<FstuSatAuthor> authorList, List<PubFile> fileList, FstuAuditProcess auditProcess) {
		//判断科技编号是否为空，空则添加，不为空则修改
		if(StringUtil.isNotBlank(sat.getSatFlow())){
			GeneralMethod.setRecordInfo(sat, false);
			fstuSatMapper.updateByPrimaryKeySelective(sat);
		}else{
			GeneralMethod.setRecordInfo(sat, true);
			sat.setSatFlow(PkUtil.getUUID());
			fstuSatMapper.insertSelective(sat);
		}

		//科技作者的操作
		if(null != authorList && !authorList.isEmpty()){
			for(FstuSatAuthor author : authorList){
				if(StringUtil.isNotBlank(author.getAuthorFlow())){//修改作者
					GeneralMethod.setRecordInfo(author, false);
					author.setSatFlow(sat.getSatFlow());
					fstuSatAuthorMapper.updateByPrimaryKeySelective(author);
				}else{//新增作者
					GeneralMethod.setRecordInfo(author, true);
					//作者流水号
					author.setAuthorFlow(PkUtil.getUUID());
					//科技流水号！！！
					author.setSatFlow(sat.getSatFlow());
					fstuSatAuthorMapper.insert(author);
				}
			}
		}

		//操作附件
		for(PubFile pubFile : fileList) {
			//操作附件
			if (null != pubFile) {
				pubFile.setProductFlow(sat.getSatFlow());
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

		return GlobalConstant.ONE_LINE;
	}

	public int save(FstuSat sat){
		if(StringUtil.isNotBlank(sat.getSatFlow())){
			GeneralMethod.setRecordInfo(sat, false);
			fstuSatMapper.updateByPrimaryKeySelective(sat);
		}else{
			GeneralMethod.setRecordInfo(sat, true);
			sat.setSatFlow(PkUtil.getUUID());
			fstuSatMapper.insertSelective(sat);
		}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int updateSatStatus(SrmAchSat sat, SrmAchProcess process) {
		if(StringUtil.isNotBlank(sat.getSatFlow())){
			GeneralMethod.setRecordInfo(sat, false);
			satMapper.updateByPrimaryKeySelective(sat);
			//保存操作过程
			srmAchProcessBiz.saveAchProcess(process);
			return GlobalConstant.ONE_LINE;
        }
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<FstuSat> search(FstuSat sat, List<String> deptFlows) {
		FstuSatExample example=new FstuSatExample();
		FstuSatExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(null != deptFlows && !deptFlows.isEmpty()){
			criteria.andDeptFlowIn(deptFlows);
		}
		if(null != sat){
			if(StringUtil.isNotBlank(sat.getSatName())){
				criteria.andSatNameLike("%"+sat.getSatName()+"%");
			}
			if(StringUtil.isNotBlank(sat.getProjSourceId())){
				criteria.andProjSourceIdEqualTo(sat.getProjSourceId());
			}
			if(StringUtil.isNotBlank(sat.getOperStatusId())){
				if("all".equals(sat.getOperStatusId())){
					List<String> ids = new ArrayList<>();
					ids.add(ProStatusEnum.Apply.getId());
					ids.add(ProStatusEnum.Passed.getId());
					criteria.andOperStatusIdIn(ids);
				}else{
					criteria.andOperStatusIdEqualTo(sat.getOperStatusId());
				}
			}
			if(StringUtil.isNotBlank(sat.getApplyUserFlow())){
				criteria.andApplyUserFlowEqualTo(sat.getApplyUserFlow());
			}
			if(StringUtil.isNotBlank(sat.getPrizedDate())){
				criteria.andPrizedDateLike("%"+sat.getPrizedDate()+"%");
			}
			if(StringUtil.isNotBlank(sat.getApplyUserName())){
				criteria.andApplyUserNameLike("%"+sat.getApplyUserName()+"%");
			}
			if(StringUtil.isNotBlank(sat.getDeptName())){
				criteria.andDeptNameLike("%"+sat.getDeptName()+"%");
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return fstuSatMapper.selectByExample(example);
	}


	@Override
	public int edit(SrmAchSat sat, List<SrmAchSatAuthor> authorList, SrmAchFile file){
		if(StringUtil.isNotBlank(sat.getSatFlow())){
			GeneralMethod.setRecordInfo(sat, false);
			satMapper.updateByPrimaryKeySelective(sat);
		}
		//作者
		if(null != authorList && !authorList.isEmpty()){
			for(SrmAchSatAuthor author : authorList){
				GeneralMethod.setRecordInfo(author, false);
				satAuthorMapper.updateByPrimaryKeySelective(author);
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
	public List<FstuSatAuthor> searchAuthorList(FstuSatAuthor author) {
		FstuSatAuthorExample example = new FstuSatAuthorExample();
		FstuSatAuthorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(author.getAuthorName())){
			criteria.andAuthorNameLike("%"+ author.getAuthorName() +"%");
		}
		if(StringUtil.isNotBlank(author.getSatFlow())){
			criteria.andSatFlowEqualTo(author.getSatFlow());
		}
		example.setOrderByClause("TYPE_NAME");
		return fstuSatAuthorMapper.selectByExample(example);
	}

	@Override
	public void deleteOneAuthor(FstuSatAuthor satAuthor) {
		if(satAuthor != null){
			satAuthor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			GeneralMethod.setRecordInfo(satAuthor, false);
			fstuSatAuthorMapper.updateByPrimaryKeySelective(satAuthor);
		}
	}

	@Override
	public void down(PubFile file, final HttpServletResponse response) throws Exception{
        /*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if("1".equals(file.getFileUpType()) && StringUtil.isNotBlank(file.getFilePath())){
                /*获取文件物理路径*/
				String filePath =file.getFilePath();
				File downLoadFile = new File(file.getFilePath());
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					fileName = URLEncoder.encode(fileName, "UTF-8");
					if (StringUtil.isNotBlank(file.getFileSuffix())) {
						fileName += "." + file.getFileSuffix();
					}
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}
}
