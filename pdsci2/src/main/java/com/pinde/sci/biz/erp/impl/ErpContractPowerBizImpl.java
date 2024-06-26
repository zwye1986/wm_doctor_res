package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractPowerBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ErpCrmContractPowerMapper;
import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.mo.ErpCrmContractPower;
import com.pinde.sci.model.mo.ErpCrmContractPowerExample;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpContractPowerBizImpl implements IErpContractPowerBiz {

	@Autowired
	private ErpCrmContractPowerMapper powerMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Override
	public List<ErpCrmContractPower> searchContractPowerList(
			ErpCrmContractPower power) {
		ErpCrmContractPowerExample example=new ErpCrmContractPowerExample();
		ErpCrmContractPowerExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(power.getContractFlow())){
			criteria.andContractFlowEqualTo(power.getContractFlow());
		}
		return this.powerMapper.selectByExample(example);
	}

	@Override
	public String saveContractPower(List<ErpCrmContractPower> powerList, String contractFlow) {
		if(powerList!=null && !powerList.isEmpty()){
			for(ErpCrmContractPower power:powerList){
				moveTempFile(power.getFileFlow());
				if(StringUtil.isBlank(power.getContractFlow())){
					power.setContractFlow(contractFlow);
				}
				save(power);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	private void moveTempFile(String fileFlow) {
		if(StringUtil.isNotBlank(fileFlow))
		{
			PubFile oldFile = fileBiz.readFile(fileFlow);
			if(oldFile!=null)
			{
				String baseDir=StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"));
				String filePath=oldFile.getFilePath();//旧文件
				if(filePath.indexOf(File.separator + "temp")>=0) {
					String newFilePath =  filePath.replace(File.separator + "temp", "");//新文件路径
					String dir = baseDir + newFilePath.substring(0, newFilePath.lastIndexOf(File.separator) + 1);//文件夹

					File fileDir = new File(dir);
					if (!fileDir.exists()) {
						fileDir.mkdirs();
					}
					//新文件
					File newFile = new File(baseDir,newFilePath);

					//临时文件
					String filePath1 = baseDir + filePath;
					File fileDir1 = new File(filePath1);

					//保存文件文件
					if(saveTempFile(newFile, fileDir1)) {
						//删除临时文件
						if (fileDir1.exists()) {
							fileDir1.delete();
						}
						oldFile.setFilePath(newFilePath);
						fileBiz.editFile(oldFile);
					}
				}
			}
		}
	}

	private boolean saveTempFile(File newFile, File fileDir1) {
		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;
		boolean f=false;
		try {
			in = new FileInputStream(fileDir1);
			out = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			f=true;
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	public static void main(String[] args)
	{
		String filePath =File.separator + "temp"+ File.separator + "ContractPowerFile"+File.separator+"1.text" ;
		System.err.println(filePath);
		String newFilePath=filePath.replace(File.separator + "temp","");
		System.err.println(filePath);
		System.err.println(newFilePath);
		String dir=newFilePath.substring(0,newFilePath.lastIndexOf(File.separator)+1);
		System.err.println(dir);
	}

	@Override
	public int save(ErpCrmContractPower power) {
		if(StringUtil.isNotBlank(power.getPowerFlow())){
			GeneralMethod.setRecordInfo(power, false);
			return powerMapper.updateByPrimaryKeySelective(power);
		}else{
			power.setPowerFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(power, true);
			return powerMapper.insertSelective(power);
		}
	}

	@Override
	public ErpCrmContractPower readpower(String powerFlow) {
		return powerMapper.selectByPrimaryKey(powerFlow);
	}

	@Override
	public String saveContractPowerFiles(List<ErpCrmContractPowerExt> powerList, String contractFlow) {
		if(powerList!=null) {
			//删除所有 已有的权限信息
			deleteByContractFlow(contractFlow);
			for (ErpCrmContractPowerExt powerExt : powerList) {
				String powerFlow = powerExt.getPowerFlow();
				ErpCrmContractPower p = readpower(powerFlow);
				if (p == null)
					p = new ErpCrmContractPower();
				p.setContractFlow(contractFlow);
				p.setSessionNumber(powerExt.getSessionNumber());
				p.setPowerIds(powerExt.getPowerIds());
				p.setPowerNames(powerExt.getPowerNames());
				p.setPeopleNum(powerExt.getPeopleNum());
				p.setGraduatePeopleNum(powerExt.getGraduatePeopleNum());
				p.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				p.setPowerFlow(powerFlow);
				//新添加文件
				if (StringUtil.isBlank(powerExt.getOldFileFlow()) && StringUtil.isNotBlank(powerExt.getFileFlow())) {
					p.setFileFlow(powerExt.getFileFlow());
					moveTempFile(powerExt.getFileFlow());
				} else if (StringUtil.isNotBlank(powerExt.getOldFileFlow()) && StringUtil.isBlank(powerExt.getFileFlow()))//未做修改
				{
					p.setFileFlow(powerExt.getOldFileFlow());
				} else if (StringUtil.isNotBlank(powerExt.getOldFileFlow()) && StringUtil.isNotBlank(powerExt.getFileFlow()))//原来已有文件，后来修改了
				{
					p.setFileFlow(powerExt.getFileFlow());
					moveTempFile(powerExt.getFileFlow());
					deleteFile(powerExt.getOldFileFlow());
				}
				save(p);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	private void deleteFile(String oldFileFlow) {
		if (StringUtil.isNotBlank(oldFileFlow)) {
			PubFile oldFile = fileBiz.readFile(oldFileFlow);
			if (oldFile != null) {
				oldFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				fileBiz.editFile(oldFile);
				String baseDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"));
				String filePath = oldFile.getFilePath();//旧文件
				//临时文件
				String filePath1 = baseDir + filePath;
				File fileDir1 = new File(filePath1);
				//删除临时文件
				if (fileDir1.exists()) {
					fileDir1.delete();
				}
			}
		}
	}

	private void deleteByContractFlow(String contractFlow) {
		if (StringUtil.isNotBlank(contractFlow)) {
			ErpCrmContractPowerExample example = new ErpCrmContractPowerExample();
			ErpCrmContractPowerExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			criteria.andContractFlowEqualTo(contractFlow);
			ErpCrmContractPower p=new ErpCrmContractPower();
			p.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			powerMapper.updateByExampleSelective(p,example);
		}
	}

}
