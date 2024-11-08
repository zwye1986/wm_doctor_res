package com.pinde.sci.form.jsres;

import java.io.Serializable;
import java.util.List;

public class BasicInfoForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3081738391574662552L;
	/**
	 * 职业许可证url
	 */
	private String professionLicenceUrl;
	/**
	 * 医院等级证书url
	 */
	private String hospitalLevelLicenceUrl;
	/**
	 * 附件url
	 */
	private String appendixUrl;
	/**
	 * 获得普通专科培训合格证人数
	 */
	private String normalTraningNumber;
	/**
	 * 宿舍床位数
	 */
	private String bedNumber;
	/**
	 * 获得亚专科培训合格证
	 */
	private String inferiorTrainingNumber;
	
	/**
	 * 获得全科培训合格证
	 */
	private String allTrainingNumber;
	/**
	 * 联系人
	 */
	private List<ContactorInfoForm> contactorsList;
	/**
	 * 培训基地（医院）信誉
	 */
	private String hasAccident;
	/**
	 * 分类管理方式
	 */
	private String classificationManagement;
	/**
	 * 以往是否为省级基地
	 */
	private String isProvincialBaser;
	/**
	 * 以往是否为国家协同单位
	 */
	private String isNationalCollaborativeUnit;

	private String lx;	//类别
	private String zcdjlx;	//注册登记类型
	private String dj;  //等级
	private String djContext;//等级-其他
    // 等次
    private String dc;
	private String zzOtherInfo;

	private String znbmfzrmc;	//职能部门负责人
	private String znbmfzrdh;	//职能部门负责人电话
	private String znbmfzryx;	//职能部门负责人邮箱
	private String zpywlxrmc;	//住培业务联系人
	private String zpywlxrdh;	//住培业务联系人电话
	private String zpywlxryx;	//住培业务联系人邮箱

	// 培训基地负责人职称ID
	private String jdfzrTitleId;
	// 培训基地负责人职称
	private String jdfzrTitleName;
	// 培训基地负责人职务ID
	private String jdfzrPostId;
	// 培训基地负责人职务
	private String jdfzrPostName;
	// 培训基地负责人移动电话
	private String jdfzrMobilephone;
	// 培训基地负责人固定电话
	private String jdfzrFixedPhone;
	// 培训基地负责人邮箱
	private String jdfzrMailAddress;
    // 业务分管负责人信息
	private List<ContactorInfoForm> ywfgfzrList;
    // 住培业务科室联系人信息
    private List<ContactorInfoForm> zpywkslxrList;
    // 住培管理部门负责人信息
    private List<ContactorInfoForm> zpglbmfzrList;
	// 联络人信息列表
	private List<ContactorInfoForm> contactManList;
    // 协同关系协议
    private String collaborativeRelationshipAgreementUrl;

	private String levelRank;

	private String levelRankName;

	private String jointOrgFlag;

	public String getJointOrgFlag() {
		return jointOrgFlag;
	}

	public void setJointOrgFlag(String jointOrgFlag) {
		this.jointOrgFlag = jointOrgFlag;
	}

	public String getLevelRank() {
		return levelRank;
	}

	public void setLevelRank(String levelRank) {
		this.levelRank = levelRank;
	}

	public String getLevelRankName() {
		return levelRankName;
	}

	public void setLevelRankName(String levelRankName) {
		this.levelRankName = levelRankName;
	}

	public String getCollaborativeRelationshipAgreementUrl() {
		return collaborativeRelationshipAgreementUrl;
	}

	public void setCollaborativeRelationshipAgreementUrl(String collaborativeRelationshipAgreementUrl) {
		this.collaborativeRelationshipAgreementUrl = collaborativeRelationshipAgreementUrl;
	}

	public String getDjContext() {
		return djContext;
	}

	public void setDjContext(String djContext) {
		this.djContext = djContext;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getZcdjlx() {
		return zcdjlx;
	}

	public void setZcdjlx(String zcdjlx) {
		this.zcdjlx = zcdjlx;
	}

	public String getZnbmfzrmc() {
		return znbmfzrmc;
	}

	public void setZnbmfzrmc(String znbmfzrmc) {
		this.znbmfzrmc = znbmfzrmc;
	}

	public String getZnbmfzrdh() {
		return znbmfzrdh;
	}

	public void setZnbmfzrdh(String znbmfzrdh) {
		this.znbmfzrdh = znbmfzrdh;
	}

	public String getZnbmfzryx() {
		return znbmfzryx;
	}

	public void setZnbmfzryx(String znbmfzryx) {
		this.znbmfzryx = znbmfzryx;
	}

	public String getZpywlxrmc() {
		return zpywlxrmc;
	}

	public void setZpywlxrmc(String zpywlxrmc) {
		this.zpywlxrmc = zpywlxrmc;
	}

	public String getZpywlxrdh() {
		return zpywlxrdh;
	}

	public void setZpywlxrdh(String zpywlxrdh) {
		this.zpywlxrdh = zpywlxrdh;
	}

	public String getZpywlxryx() {
		return zpywlxryx;
	}

	public void setZpywlxryx(String zpywlxryx) {
		this.zpywlxryx = zpywlxryx;
	}

	public String getHasAccident() {
		return hasAccident;
	}

	public void setHasAccident(String hasAccident) {
		this.hasAccident = hasAccident;
	}

	public String getClassificationManagement() {
		return classificationManagement;
	}

	public void setClassificationManagement(String classificationManagement) {
		this.classificationManagement = classificationManagement;
	}

	public String getIsProvincialBaser() {
		return isProvincialBaser;
	}

	public void setIsProvincialBaser(String isProvincialBaser) {
		this.isProvincialBaser = isProvincialBaser;
	}

	public String getIsNationalCollaborativeUnit() {
		return isNationalCollaborativeUnit;
	}

	public void setIsNationalCollaborativeUnit(String isNationalCollaborativeUnit) {
		this.isNationalCollaborativeUnit = isNationalCollaborativeUnit;
	}

	public String getProfessionLicenceUrl() {
		return professionLicenceUrl;
	}

	public void setProfessionLicenceUrl(String professionLicenceUrl) {
		this.professionLicenceUrl = professionLicenceUrl;
	}

	public String getHospitalLevelLicenceUrl() {
		return hospitalLevelLicenceUrl;
	}

	public void setHospitalLevelLicenceUrl(String hospitalLevelLicenceUrl) {
		this.hospitalLevelLicenceUrl = hospitalLevelLicenceUrl;
	}

	public String getAppendixUrl() {
		return appendixUrl;
	}

	public void setAppendixUrl(String appendixUrl) {
		this.appendixUrl = appendixUrl;
	}

	public String getNormalTraningNumber() {
		return normalTraningNumber;
	}

	public void setNormalTraningNumber(String normalTraningNumber) {
		this.normalTraningNumber = normalTraningNumber;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getInferiorTrainingNumber() {
		return inferiorTrainingNumber;
	}

	public void setInferiorTrainingNumber(String inferiorTrainingNumber) {
		this.inferiorTrainingNumber = inferiorTrainingNumber;
	}

	public String getAllTrainingNumber() {
		return allTrainingNumber;
	}

	public void setAllTrainingNumber(String allTrainingNumber) {
		this.allTrainingNumber = allTrainingNumber;
	}

	public List<ContactorInfoForm> getContactorsList() {
		return contactorsList;
	}

	public void setContactorsList(List<ContactorInfoForm> contactorsList) {
		this.contactorsList = contactorsList;
	}

    public String getJdfzrTitleId() {
        return jdfzrTitleId;
    }

    public void setJdfzrTitleId(String jdfzrTitleId) {
        this.jdfzrTitleId = jdfzrTitleId;
    }

    public String getJdfzrTitleName() {
        return jdfzrTitleName;
    }

    public void setJdfzrTitleName(String jdfzrTitleName) {
        this.jdfzrTitleName = jdfzrTitleName;
    }

    public String getJdfzrPostId() {
        return jdfzrPostId;
    }

    public void setJdfzrPostId(String jdfzrPostId) {
        this.jdfzrPostId = jdfzrPostId;
    }

    public String getJdfzrPostName() {
        return jdfzrPostName;
    }

    public void setJdfzrPostName(String jdfzrPostName) {
        this.jdfzrPostName = jdfzrPostName;
    }

    public List<ContactorInfoForm> getYwfgfzrList() {
        return ywfgfzrList;
    }

    public void setYwfgfzrList(List<ContactorInfoForm> ywfgfzrList) {
        this.ywfgfzrList = ywfgfzrList;
    }

    public List<ContactorInfoForm> getZpywkslxrList() {
        return zpywkslxrList;
    }

    public void setZpywkslxrList(List<ContactorInfoForm> zpywkslxrList) {
        this.zpywkslxrList = zpywkslxrList;
    }

    public List<ContactorInfoForm> getZpglbmfzrList() {
        return zpglbmfzrList;
    }

    public void setZpglbmfzrList(List<ContactorInfoForm> zpglbmfzrList) {
        this.zpglbmfzrList = zpglbmfzrList;
    }

	public String getZzOtherInfo() {
		return zzOtherInfo;
	}

	public void setZzOtherInfo(String zzOtherInfo) {
		this.zzOtherInfo = zzOtherInfo;
	}

	public String getJdfzrMobilephone() {
		return jdfzrMobilephone;
	}

	public void setJdfzrMobilephone(String jdfzrMobilephone) {
		this.jdfzrMobilephone = jdfzrMobilephone;
	}

	public String getJdfzrFixedPhone() {
		return jdfzrFixedPhone;
	}

	public void setJdfzrFixedPhone(String jdfzrFixedPhone) {
		this.jdfzrFixedPhone = jdfzrFixedPhone;
	}

	public String getJdfzrMailAddress() {
		return jdfzrMailAddress;
	}

	public void setJdfzrMailAddress(String jdfzrMailAddress) {
		this.jdfzrMailAddress = jdfzrMailAddress;
	}

	public List<ContactorInfoForm> getContactManList() {
		return contactManList;
	}

	public void setContactManList(List<ContactorInfoForm> contactManList) {
		this.contactManList = contactManList;
	}
}
