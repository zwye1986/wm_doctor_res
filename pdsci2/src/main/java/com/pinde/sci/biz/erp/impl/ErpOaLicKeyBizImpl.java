package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractProductBiz;
import com.pinde.sci.biz.erp.IErpOaLicKeyBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ErpOaLicKeyMapper;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ErpOaLicKeyExample.Criteria;
import com.verhas.licensor.License;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpOaLicKeyBizImpl implements IErpOaLicKeyBiz{

	@Autowired
	private ErpOaLicKeyMapper licKeyMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IErpContractProductBiz contractProductBiz;
	
	@Override
	public ErpOaLicKey readLicKey(String licFlow) {
		return licKeyMapper.selectByPrimaryKey(licFlow);
	}

	@Override
	public List<ErpOaLicKey> searchLicList(ErpOaLicKey erpOaLicKey) {
		ErpOaLicKeyExample example=new ErpOaLicKeyExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(erpOaLicKey!=null){
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerName())){
				criteria.andAliasNameLike("%"+erpOaLicKey.getCustomerName()+"%");
			}
			if(StringUtil.isNotBlank(erpOaLicKey.getAliasName())){
				criteria.andAliasNameLike("%"+erpOaLicKey.getAliasName()+"%");
			}
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerFlow())){
				criteria.andCustomerFlowEqualTo(erpOaLicKey.getCustomerFlow());
			}
			if(StringUtil.isNotBlank(erpOaLicKey.getContractFlow())){
				criteria.andContractFlowEqualTo(erpOaLicKey.getContractFlow());
			}
			if(StringUtil.isNotBlank(erpOaLicKey.getContractNo())){
				criteria.andContractNoEqualTo(erpOaLicKey.getContractNo());
			}
		}
		example.setOrderByClause("CREATE_TIME desc");
		return licKeyMapper.selectByExample(example);
	}

	@Override
	public int save(ErpOaLicKey erpOaLicKey) {
		if(StringUtil.isNotBlank(erpOaLicKey.getLicFlow())){
			GeneralMethod.setRecordInfo(erpOaLicKey, false);
			return licKeyMapper.updateByPrimaryKeySelective(erpOaLicKey);
		}else{
			erpOaLicKey.setLicFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(erpOaLicKey, true);
			return licKeyMapper.insertSelective(erpOaLicKey);
		}
	}

	@Override
	public void createLicFile(ErpOaLicKey erpOaLicKey) throws Exception {
		if(erpOaLicKey!=null){
			String licFlow="";
			if(StringUtil.isNotBlank(erpOaLicKey.getLicFlow())){
				licFlow="licFlow="+StringEscapeUtils.escapeJava(erpOaLicKey.getLicFlow());
			}
			String customerFlow="";
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerFlow())){
				customerFlow="customerFlow="+StringEscapeUtils.escapeJava(erpOaLicKey.getCustomerFlow());
			}
			String customerName="";
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerName())){
				customerName="customerName="+StringEscapeUtils.escapeJava(erpOaLicKey.getCustomerName());
			}
			String aliasName="";
			if(StringUtil.isNotBlank(erpOaLicKey.getAliasName())){
				aliasName="aliasName="+StringEscapeUtils.escapeJava(erpOaLicKey.getAliasName());
			}
			String contractFlow="";
			if(StringUtil.isNotBlank(erpOaLicKey.getContractFlow())){
				contractFlow="contractFlow="+StringEscapeUtils.escapeJava(erpOaLicKey.getContractFlow());
			}
			String contractNo="";
			if(StringUtil.isNotBlank(erpOaLicKey.getContractNo())){
				contractNo="contractNo="+StringEscapeUtils.escapeJava(erpOaLicKey.getContractNo());
			}
			String issueDate="";
			if(StringUtil.isNotBlank(erpOaLicKey.getIssueDate())){
				issueDate="issueDate="+StringEscapeUtils.escapeJava(erpOaLicKey.getIssueDate());
			}
			String machineId="";
			if(StringUtil.isNotBlank(erpOaLicKey.getMachineId())){
				machineId="machineId="+StringEscapeUtils.escapeJava(erpOaLicKey.getMachineId().trim());
			}
			String workStationId="";
			if(StringUtil.isNotBlank(erpOaLicKey.getWorkStationId())){
				workStationId="workStation="+StringEscapeUtils.escapeJava(erpOaLicKey.getWorkStationId());
			}
			String validDate="";
			if(StringUtil.isNotBlank(erpOaLicKey.getVaildDate())){
				validDate="validDate="+StringEscapeUtils.escapeJava(erpOaLicKey.getVaildDate());
			}
			String encryptFlag="";
			if(StringUtil.isNotBlank(erpOaLicKey.getEncryptFlag())){
				encryptFlag="encryptFlag="+StringEscapeUtils.escapeJava(erpOaLicKey.getEncryptFlag());
			}
			String encryptName="";
			if(StringUtil.isNotBlank(erpOaLicKey.getEncryptName())){
				encryptName="encryptName="+StringEscapeUtils.escapeJava(erpOaLicKey.getEncryptName());
			}
			String licenseString = licFlow+System.getProperty("line.separator")
					+customerFlow+System.getProperty("line.separator")
					+customerName+System.getProperty("line.separator")
					+aliasName+System.getProperty("line.separator")
					+contractFlow+System.getProperty("line.separator")
					+contractNo+System.getProperty("line.separator")
					+issueDate+System.getProperty("line.separator")
					+machineId+System.getProperty("line.separator")
					+workStationId+System.getProperty("line.separator")
					+validDate+System.getProperty("line.separator")
					+encryptFlag+System.getProperty("line.separator")
					+encryptName;
			License license=new License();
			license.setLicense(licenseString);
			InputStream in=ErpOaLicKeyBizImpl.class.getResourceAsStream("/secring.gpg");
			license.loadKey(in, "njpdkj <admin@njpdkj.com>");
			String encoded = license.encodeLicense("njpdkj123456");
	        byte [] blobResult=encoded.getBytes();
	        PubFile pubFile=new PubFile();
			pubFile.setFileName("license");
			pubFile.setFileType("key");
			pubFile.setFileContent(blobResult);
			pubFile.setFileSuffix("key");
		    String fileFlow=this.fileBiz.addFileReturnFlow(pubFile);
		    erpOaLicKey.setFileFlow(fileFlow);
		}
	}

	@Override
	public int sendLicFile(ErpOaLicKey erpOaLicKey, String email) {
		if(erpOaLicKey!=null){
			String customerName="<br>客户名称：";
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerName())){
				customerName="<br>客户名称："+erpOaLicKey.getCustomerName();
			}
			String issueDate="<br>授权文件发行日期：";
			if(StringUtil.isNotBlank(erpOaLicKey.getIssueDate())){
				issueDate="<br>授权文件发行日期："+erpOaLicKey.getIssueDate();
			}
			String validDate="";//"<br>授权文件到期日：";
//			if(StringUtil.isNotBlank(erpOaLicKey.getVaildDate())){
//				validDate="<br>授权文件到期日："+erpOaLicKey.getVaildDate();
//			}
			String workStationName="<br>授权产品名称：";
			if(StringUtil.isNotBlank(erpOaLicKey.getWorkStationName())){
				workStationName="<br>授权产品名称："+erpOaLicKey.getWorkStationName();
			}
			String machineId="<br>机器码：";
			if(StringUtil.isNotBlank(erpOaLicKey.getMachineId())){
				machineId="<br>机器码："+erpOaLicKey.getMachineId().trim();
			}
			String developLan="<br>开发语言：";
			if(StringUtil.isNotBlank(erpOaLicKey.getDevelopLanguage())){
				developLan="<br>开发语言："+erpOaLicKey.getDevelopLanguage();
			}
			String encryptFlag="<br>是否加密：";
			if(StringUtil.isNotBlank(erpOaLicKey.getEncryptFlag())){
				if(GlobalConstant.FLAG_Y.equals(erpOaLicKey.getEncryptFlag())){
					encryptFlag="<br>是否加密：是";
				}else{
					encryptFlag="<br>是否加密：否";
				}
				
			}
			String encryptName="<br>加密关键字：";
			if(StringUtil.isNotBlank(erpOaLicKey.getEncryptName())){
				encryptName="<br>加密关键字："+erpOaLicKey.getEncryptName();
			}
			if(StringUtil.isNotBlank(erpOaLicKey.getFileFlow()) && StringUtil.isNotBlank(email)){
				PubMsg msg=new PubMsg();
				String title=erpOaLicKey.getCustomerName()+"  产品（"+erpOaLicKey.getWorkStationName()+"）的license.key";
				String content="您申请的授权文件详细内容如下：";
				content+=customerName+issueDate+validDate+workStationName+machineId+developLan;
				//判断是否包含考试系统工作站
				if (StringUtil.isNotBlank(erpOaLicKey.getWorkStationId())) {
//					String [] workStationArray=erpOaLicKey.getWorkStationId().split(",");
//					List<String> workStationList=Arrays.asList(workStationArray);
//					if(workStationArray!=null && workStationArray.length>0){
//						if(workStationList.contains("netexam")){
//							content+=encryptFlag+encryptName;
//						}
//					}
					
					if (erpOaLicKey.getWorkStationId().indexOf("netexam") != -1) {
						content += encryptFlag + encryptName;
					}
				}
				msg.setMsgTypeId(MsgTypeEnum.Email.getId());
				msg.setMsgTypeName(MsgTypeEnum.Email.getName());
				msg.setMsgTitle(title);
				msg.setMsgContent(content);
				msg.setReceiver(email);
				msg.setFileFlow(erpOaLicKey.getFileFlow());
				msg.setMsgTime(DateUtil.getCurrDateTime());
				msg.setSender(StringUtil.defaultString(InitConfig.getSysCfg("sys_mail_from")));
				msg.setSendFlag(GlobalConstant.FLAG_N);
				return msgBiz.saveMsg(msg);
			}
		}
		return 0;
	}

	@Override
	public String saveAndSendLicFile(ErpOaLicKey erpOaLicKey) throws Exception {
		createLicFile(erpOaLicKey);
		int saveResult=save(erpOaLicKey);
		backUpdateContractProduct(erpOaLicKey);
		SysUser applyUser=this.userBiz.readSysUser(erpOaLicKey.getApplyUserFlow());
		String email=null;
		if(applyUser!=null){
			email=applyUser.getUserEmail();
		}
		if(StringUtil.isNotBlank(erpOaLicKey.getFileFlow())){
			int sendResult=sendLicFile(erpOaLicKey,email);
			if(saveResult==GlobalConstant.ONE_LINE && sendResult==GlobalConstant.ONE_LINE){
				return GlobalConstant.SAVE_SEND_SUCCESSED;
			}else if(sendResult!=GlobalConstant.ONE_LINE){
				return GlobalConstant.SEND_FAIL;
			}else if(saveResult!=GlobalConstant.ONE_LINE){
				return GlobalConstant.SAVE_FAIL;
			}
		}
		return null;
	}

	@Override
	public void backUpdateContractProduct(ErpOaLicKey erpOaLicKey) {
		if (erpOaLicKey != null) {
			if (StringUtil.isNotBlank(erpOaLicKey.getContractFlow())) {
				ErpCrmContractProduct product = new ErpCrmContractProduct();
				product.setContractFlow(erpOaLicKey.getContractFlow());
				List<ErpCrmContractProduct> productList = this.contractProductBiz.searchContactProductList(product);
				if (productList != null && !productList.isEmpty()) {
					List<ErpCrmContractProduct> products = new ArrayList<ErpCrmContractProduct>();
					for (ErpCrmContractProduct pro : productList) {
						if ("netexam".equals(pro.getProductTypeId())) {
							if (StringUtil.isNotBlank(erpOaLicKey.getEncryptName())) {
								pro.setRegFileClientName(erpOaLicKey.getEncryptName());
							}
						}
						if (StringUtil.isNotBlank(erpOaLicKey.getVaildDate())) {
							pro.setRegFileIndate(erpOaLicKey.getVaildDate());
						}
						products.add(pro);
					}
					this.contractProductBiz.saveContractProduct(products, null);
				}
			}
		}
	}


}
