package com.pinde.sci.form.gyxjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class XjEduUserExtInfoForm {
    //个人信息
    //基本信息
    private String height;//身高
    private String bloodType;//血型
    private String foreignLanguageLevel;//外语水平
    private String computerLevel;//计算机水平
    private String mandarinLevel;//普通话水平
    private String speciality;//特长
    private String firstEducationContentId;
    private String firstEducationContentName;//第一学历
    //学历学位信息
    private String underGraduateOrgName;//本科毕业单位名称
    private String underDiplomaCode; //本科毕业证书编号
    private String underAwardDegreeOrg;//获取学士学位单位
    private String underMajor;//获学士学位专业名称
    private String underDegreeCertCode;//学士学位证书编号
    private String underStudyForm;//取得本科学历的学习形式
    private String underGraduateTime;//本科毕业年月
    private String underAwardDegreeTime;//获得学士学位年月
    private String underGraduateMajor;//本科毕业专业名称
    private String masterUnitName;//硕士毕业单位名称
    private String masterDiplomaCode;//硕士毕业证书编号
    private String masterAwardDegreeOrg;//获取硕士学位单位
    private String masterDegreeCertCode;//硕士学位证书编号
    private String masterStudyForm;//取得硕士学位方式
    private String masterGraduateTime;//硕士毕业年月
    private String masterAwardDegreeTime;//获得硕士学位年月
    private String masterGraduateMajor;//硕士毕业专业名称
    private String masterGraduateMajorCode;//硕士毕业专业代码
    private String code;//最后学位证书编号
    private String reAndPuStatusContent;//入学前奖惩情况
    //户口信息
    private String address;//户口详细地址
    private String oldDomicile;//原户籍地
    //居住信息
    private String nowResideAddress;//现在家庭住址
    private String linkMan;//紧急联系人
    private String postCode;//邮政编码
    private String telephone;//紧急联系人电话
    //档案信息
    private String oldFileLocationOrg;//原档案所在单位
    private String oldFileLocationOrgCode;//原档案所在单位邮编
    private String oldOrgName;//原工作学习单位
    //婚姻生育状况
    private String statusId; //婚姻状况编号
    private String statusName;  //婚姻状况名称
    private String bearStatusName;//婚育状况名称
    //政治情况
    private String joinTime;//加入时间
    private String isRelationInto;//党团关系是否转入
    //缴费信息
    private String accountNum;//缴费账号
    //录取信息
    //外国语
    private String foreignLanguageName;//名称
    private String foreignLanguageScore;// 分数
    //政治理论
    private String politicalTheoryName;//名称
    private String politicalTheoryScore;//分数
    //业务课
    private String firstSubjectCode;//代码
    private String firstSubjectName;//名称
    private String firstSubjectScore;//分数
    private String secondSubjectCode;//代码
    private String secondSubjectName;//名称
    private String secondSubjectScore;//分数
    //加试科
    private String firstAddExamName;//名称
    private String firstAddExamScore;// 分数
    private String secondAddExamName;//名称
    private String secondAddExamScore;// 分数
    //复试
    private String reexamineScore;//分数
    //总分
    private String totalPointsScore;//分数
    //医师资格证
    private String isDoctorQuaCert;//是否拥有
    private String codeDoctorQuaCert;//编号
    //执业医师执照
    private String isMedicalPractitioner;//是否拥有
    private String codeMedicalPractitioner;//编号
    //住宿
    private String isStay;//是否住宿
    private String roomNumStay;//宿舍号
    private String telephoneStay;//宿舍电话
    //配偶姓名
    private String mateName;//姓名
    private String mateIdNo;//身份证
    //定向培养单位
    private String directionalOrgName;//单位名称
    //备注
    private String remark;//备注内容

    private String outOfSchoolDate;//休学时间
    private String backToSchoolDate;//复学时间
    private String dropOutSchoolDate;//退学时间
    private String reportedDate;//报到时间
    private String awardSubjectCategory;//授予学科门类

    private List<XjRegisterDateForm> registerDateList;//注册时间

    /**
     * 学籍板块变更需求新增字段
     */
    //基本信息
    private String xjRegDate;//学籍注册时间
    //录取信息
    private String recruitType;//招录方式
    //必填信息
    private String joinOrgTime;//入党(团)时间
    private String hkInSchool;//户口是否需要迁入我校
    private String hkChangeNo;//户口迁移证编号
    private String ybCardNo;//医保卡号
    //选填信息
    private String spouseUnit;//配偶工作单位
    private String mainResume;//本人主要简历
    private String scientificTogether;//曾参加过合作教学工作和科研工作
    //学费信息
    private String tuitionFee;//学费总额
    private String paytuitionFee;//已缴纳学费
    private String arrearageFee;//欠缴金额
    private String dormitoryFee;//住宿费
    private String payDormitoryFee;//已缴纳住宿费
    private String arrearageDormitoryFee;//欠缴纳住宿费
    private String paidFee;//是否缴清费用
    private String paidFeeNo;//缴费编号
    //学业和学位授予信息
    private String bkgain;//是否获得本科学历
    private String xsgain;//是否获得学士学位
    private String ssyjsgain;//是否获得硕士研究生学历
    private String ssxwgain;//是否获得硕士学位
    private String masterSubject;//获得硕士学位学科门类
    private String planGraduateTime;//预毕业时间
    private String graduateFlag;//是否允许毕业
    private String preGraduation;//前置学历
    private String gotBachelorCertSubject;//获得学士学位学科门类
    private String gotMasterCertSpe;//获得硕士学位专业
    //学位论文信息
    private String paperTitle;//论文题目
    private String paperSource;//论文课题来源
    private String paperKey;//论文关键词
    private String paperType;//论文类型
    //就业信息
    private String degreeDirection;//获学位后去向
    private String unitNature;//就业单位性质
    private String unitAddress;//就业单位所在省（直辖市）
    private String workNature;//工作性质
    private String unitName;//就业单位名称
    private String unitLinkMan;//就业单位联系人
    private String unitLinkManPhone;//就业单位联系人电话
    private String unitLinkManEmail;//就业单位联系人邮箱

    //选填信息 增加几个字段：生源省市、学制、宿舍地址、家庭电话
    private String studentSourceAreaId;//生源省市Id
    private String studentSourceArea;//生源省市
    private String schoolSystem;//学制
    private String dormitoryAdd;//宿舍地址
    private String homePhone;//家庭电话

    //就业、档案信息(派遣信息)
    private String sendUnit;//具体派遣单位
    private String reportAddressId;//报到地址Id
    private String reportAddressName;//报到地址Name
    private String sendNature;//派遣性质
    private String sentMemo;//备注
    private String sendTime;//派遣时间
    //(就业信息)
    private String graduationWhereabouts;//毕业去向
    private String unitCode;//单位组织机构代码
    private String workUnit;//具体就业单位
    private String workType;//单位类型
    private String workOfIndustry;//单位所属行业
    private String workOfAreaId;//单位所属地区id
    private String workOfAreaName;//单位所属地区name
    private String workLinkMan;//单位联系人
    private String occupationType;//职业类型
    private String workLinkPhone;//单位联系电话
    private String workPay;//薪酬
    private String userAim;//使用意图
    private String signTime;//签约时间
    private String majorCounterpart;//是否专业对口、
    private String workEmail;//用人单位邮箱
    private String employmentLevel;//是否就业困难
    private String employAgreementUrl;//就业协议书上传
    private String employSign;//暂缓就业表登记
    //(档案去向信息)
    private String recipients;//收件联系人
    private String recipientsPhone;//收件人电话
    private String archivesPlaceCode;//档案去向邮政编码
    private String archivesEmsNum;//Ems单号
    private String archivesEmsAddressId;//档案去向邮寄地址id
    private String archivesEmsAddressName;//档案去向邮寄地址name
    private String mailTime;//寄出时间
    private String archivesUnit;//档案去向单位
    private String studentPhone;//学生电话
    private String studentCode;//学生学号
    private String studentName;//学生姓名
    //医保、孕育信息
    private String joinMedicalYear;//本次参保起始年份
    private String enterSchoolYear;//入学年份
    private String isNewStudent;//是否新生
    private String college;//学院
    private String countryAreaId;//国家地区id
    private String countryAreaName;//国家地区name
    private String whetherPracticeCert;//有无执业医师资格证
    private String practiceCertCode;//执业医师资格证编号
    private String practiceCertUrl;//执业医师资格证上传Url
    private String whetherBuyMedicalCard;//是否购买医保
    //宿舍信息
    private String unitOfDoctor;//导师所在单位
    private String dormitoryNo;//宿舍楼号
    private String dormitoryFloor;//宿舍楼层
    private String dormitoryRoomNumber;//宿舍房号
    //档案信息
    private String partyMembershipTime;//（党员）入党时间
    private String partyMemberFormalTime;//（党员）转正时间
    private String emsUrl;//EMS截图上传
    private String highSchool;//是否为高中
    private String university;//是否为大学
    //档案信息(硕士)
    private String masterAdmissionSign;//是否有录取登记表
    private String masterScienceDegree;//是否有科学学位（审批表）
    private String masterGraduationSign;//是否有毕业登记表
    private String masterClinicalDegree;//是否有临床学位（答辩表）
    private String masterSchoolReport;//是否有成绩单
    //档案信息(博士)
    private String doctorAdmissionSign;//是否有录取登记表
    private String doctorScienceDegree;//是否有科学学位（审批表）
    private String doctorGraduationSign;//是否有毕业登记表
    private String doctorClinicalDegree;//是否有临床学位（答辩表）
    private String doctorSchoolReport;//是否有成绩单
    private String expertChitLetter;//是否有专家推荐信2封

    //南医大需求新增必填信息以下字段
    private String isPoorStudents;//是否申请贫困生
    private String isFixedIncome;//是否有固定收入
    private String isFiveGuarantees;//是否五保户
    private String isLowGuarantees;//是否低保
    private String isPoor;//是否扶贫户
    private String isOrphan;//是否孤儿
    private String isSingleParent;//是否单亲家庭子女
    private String isDisabledChildren;//是否残疾人子女
    private String isDisabled;//本人是否残疾
    private String isDisabledLevel;//残疾类别
    private String isLostAbilityParent;//是否父母丧失劳动能力
    private String isSeriousIllness;//是否家中有大病患者
    private String isFilingRiser;//是否建档立卡的贫困户
    private String isLowIncomeFamilies;//是否低收入家庭
    private String isSpecialCareChildren;//是否军烈属或优抚子女
    private String annualIncome;//家庭人均年收入
    private String majorSourceIncome;//家庭主要收入来源类型
    private String majorSourceIncomeDesc;//收入来源描述
    private String isSufferNaturalDisasters;//家庭是否遭受自然灾害
    private String naturalDisastersDesc;//自然灾害具体情况描述
    private String isSufferEmergency;//家庭是否遭受突发意外事件
    private String emergencyDesc;//突发意外事件具体描述
    private String debtMoney;//家庭欠债金额
    private String debtReason;//家庭欠债原因
    private String familyPopulation;//家庭人口数
    private String workingPopulation;//劳动力人口数
    private String unemployedPopulation;//家庭成员失业人数
    private String supportPopulation;//赡养人口数
    private String otherDisastersDesc;//其他信息
    private String isRuralStudents;//是否农村学生
    private String isRuralLowInsured;//是否农村低保户
    private String isRuralPoverty;//是否农村特困供养
    private String otherDifficultyDesc;//其他
    //广医大同等学力新增字段
    private String workUnitTDXL;//工作单位
    private String reportTime;//报班时间
    private String addressTDXL;//地址
    private String degreeTimeTDXL;//获得学位时间
    private String graduateSchooTDXL;//毕业学校
    private String tutorUnit;//导师单位
    private String tutorContact;//导师联系方式

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getForeignLanguageLevel() {
        return foreignLanguageLevel;
    }

    public void setForeignLanguageLevel(String foreignLanguageLevel) {
        this.foreignLanguageLevel = foreignLanguageLevel;
    }

    public String getComputerLevel() {
        return computerLevel;
    }

    public void setComputerLevel(String computerLevel) {
        this.computerLevel = computerLevel;
    }

    public String getMandarinLevel() {
        return mandarinLevel;
    }

    public void setMandarinLevel(String mandarinLevel) {
        this.mandarinLevel = mandarinLevel;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFirstEducationContentId() {
        return firstEducationContentId;
    }

    public void setFirstEducationContentId(String firstEducationContentId) {
        this.firstEducationContentId = firstEducationContentId;
    }

    public String getFirstEducationContentName() {
        return firstEducationContentName;
    }

    public void setFirstEducationContentName(String firstEducationContentName) {
        this.firstEducationContentName = firstEducationContentName;
    }

    public String getUnderGraduateOrgName() {
        return underGraduateOrgName;
    }

    public void setUnderGraduateOrgName(String underGraduateOrgName) {
        this.underGraduateOrgName = underGraduateOrgName;
    }

    public String getUnderDiplomaCode() {
        return underDiplomaCode;
    }

    public void setUnderDiplomaCode(String underDiplomaCode) {
        this.underDiplomaCode = underDiplomaCode;
    }

    public String getUnderAwardDegreeOrg() {
        return underAwardDegreeOrg;
    }

    public void setUnderAwardDegreeOrg(String underAwardDegreeOrg) {
        this.underAwardDegreeOrg = underAwardDegreeOrg;
    }

    public String getUnderMajor() {
        return underMajor;
    }

    public void setUnderMajor(String underMajor) {
        this.underMajor = underMajor;
    }

    public String getUnderDegreeCertCode() {
        return underDegreeCertCode;
    }

    public void setUnderDegreeCertCode(String underDegreeCertCode) {
        this.underDegreeCertCode = underDegreeCertCode;
    }

    public String getUnderStudyForm() {
        return underStudyForm;
    }

    public void setUnderStudyForm(String underStudyForm) {
        this.underStudyForm = underStudyForm;
    }

    public String getUnderGraduateTime() {
        return underGraduateTime;
    }

    public void setUnderGraduateTime(String underGraduateTime) {
        this.underGraduateTime = underGraduateTime;
    }

    public String getUnderAwardDegreeTime() {
        return underAwardDegreeTime;
    }

    public void setUnderAwardDegreeTime(String underAwardDegreeTime) {
        this.underAwardDegreeTime = underAwardDegreeTime;
    }

    public String getUnderGraduateMajor() {
        return underGraduateMajor;
    }

    public void setUnderGraduateMajor(String underGraduateMajor) {
        this.underGraduateMajor = underGraduateMajor;
    }

    public String getMasterUnitName() {
        return masterUnitName;
    }

    public void setMasterUnitName(String masterUnitName) {
        this.masterUnitName = masterUnitName;
    }

    public String getMasterDiplomaCode() {
        return masterDiplomaCode;
    }

    public void setMasterDiplomaCode(String masterDiplomaCode) {
        this.masterDiplomaCode = masterDiplomaCode;
    }

    public String getMasterAwardDegreeOrg() {
        return masterAwardDegreeOrg;
    }

    public void setMasterAwardDegreeOrg(String masterAwardDegreeOrg) {
        this.masterAwardDegreeOrg = masterAwardDegreeOrg;
    }

    public String getMasterDegreeCertCode() {
        return masterDegreeCertCode;
    }

    public void setMasterDegreeCertCode(String masterDegreeCertCode) {
        this.masterDegreeCertCode = masterDegreeCertCode;
    }

    public String getMasterStudyForm() {
        return masterStudyForm;
    }

    public void setMasterStudyForm(String masterStudyForm) {
        this.masterStudyForm = masterStudyForm;
    }

    public String getMasterGraduateTime() {
        return masterGraduateTime;
    }

    public void setMasterGraduateTime(String masterGraduateTime) {
        this.masterGraduateTime = masterGraduateTime;
    }

    public String getMasterAwardDegreeTime() {
        return masterAwardDegreeTime;
    }

    public void setMasterAwardDegreeTime(String masterAwardDegreeTime) {
        this.masterAwardDegreeTime = masterAwardDegreeTime;
    }

    public String getMasterGraduateMajor() {
        return masterGraduateMajor;
    }

    public void setMasterGraduateMajor(String masterGraduateMajor) {
        this.masterGraduateMajor = masterGraduateMajor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReAndPuStatusContent() {
        return reAndPuStatusContent;
    }

    public void setReAndPuStatusContent(String reAndPuStatusContent) {
        this.reAndPuStatusContent = reAndPuStatusContent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOldDomicile() {
        return oldDomicile;
    }

    public void setOldDomicile(String oldDomicile) {
        this.oldDomicile = oldDomicile;
    }

    public String getNowResideAddress() {
        return nowResideAddress;
    }

    public void setNowResideAddress(String nowResideAddress) {
        this.nowResideAddress = nowResideAddress;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOldFileLocationOrg() {
        return oldFileLocationOrg;
    }

    public void setOldFileLocationOrg(String oldFileLocationOrg) {
        this.oldFileLocationOrg = oldFileLocationOrg;
    }

    public String getOldFileLocationOrgCode() {
        return oldFileLocationOrgCode;
    }

    public void setOldFileLocationOrgCode(String oldFileLocationOrgCode) {
        this.oldFileLocationOrgCode = oldFileLocationOrgCode;
    }

    public String getOldOrgName() {
        return oldOrgName;
    }

    public void setOldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBearStatusName() {
        return bearStatusName;
    }

    public void setBearStatusName(String bearStatusName) {
        this.bearStatusName = bearStatusName;
    }

    public String getJoinOrgTime() {
        return joinOrgTime;
    }

    public void setJoinOrgTime(String joinOrgTime) {
        this.joinOrgTime = joinOrgTime;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getIsRelationInto() {
        return isRelationInto;
    }

    public void setIsRelationInto(String isRelationInto) {
        this.isRelationInto = isRelationInto;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getForeignLanguageName() {
        return foreignLanguageName;
    }

    public void setForeignLanguageName(String foreignLanguageName) {
        this.foreignLanguageName = foreignLanguageName;
    }

    public String getForeignLanguageScore() {
        return foreignLanguageScore;
    }

    public void setForeignLanguageScore(String foreignLanguageScore) {
        this.foreignLanguageScore = foreignLanguageScore;
    }

    public String getPoliticalTheoryName() {
        return politicalTheoryName;
    }

    public void setPoliticalTheoryName(String politicalTheoryName) {
        this.politicalTheoryName = politicalTheoryName;
    }

    public String getPoliticalTheoryScore() {
        return politicalTheoryScore;
    }

    public void setPoliticalTheoryScore(String politicalTheoryScore) {
        this.politicalTheoryScore = politicalTheoryScore;
    }

    public String getFirstSubjectCode() {
        return firstSubjectCode;
    }

    public void setFirstSubjectCode(String firstSubjectCode) {
        this.firstSubjectCode = firstSubjectCode;
    }

    public String getFirstSubjectName() {
        return firstSubjectName;
    }

    public void setFirstSubjectName(String firstSubjectName) {
        this.firstSubjectName = firstSubjectName;
    }

    public String getFirstSubjectScore() {
        return firstSubjectScore;
    }

    public void setFirstSubjectScore(String firstSubjectScore) {
        this.firstSubjectScore = firstSubjectScore;
    }

    public String getSecondSubjectCode() {
        return secondSubjectCode;
    }

    public void setSecondSubjectCode(String secondSubjectCode) {
        this.secondSubjectCode = secondSubjectCode;
    }

    public String getSecondSubjectName() {
        return secondSubjectName;
    }

    public void setSecondSubjectName(String secondSubjectName) {
        this.secondSubjectName = secondSubjectName;
    }

    public String getSecondSubjectScore() {
        return secondSubjectScore;
    }

    public void setSecondSubjectScore(String secondSubjectScore) {
        this.secondSubjectScore = secondSubjectScore;
    }

    public String getFirstAddExamName() {
        return firstAddExamName;
    }

    public void setFirstAddExamName(String firstAddExamName) {
        this.firstAddExamName = firstAddExamName;
    }

    public String getFirstAddExamScore() {
        return firstAddExamScore;
    }

    public void setFirstAddExamScore(String firstAddExamScore) {
        this.firstAddExamScore = firstAddExamScore;
    }

    public String getSecondAddExamName() {
        return secondAddExamName;
    }

    public void setSecondAddExamName(String secondAddExamName) {
        this.secondAddExamName = secondAddExamName;
    }

    public String getSecondAddExamScore() {
        return secondAddExamScore;
    }

    public void setSecondAddExamScore(String secondAddExamScore) {
        this.secondAddExamScore = secondAddExamScore;
    }

    public String getReexamineScore() {
        return reexamineScore;
    }

    public void setReexamineScore(String reexamineScore) {
        this.reexamineScore = reexamineScore;
    }

    public String getTotalPointsScore() {
        return totalPointsScore;
    }

    public void setTotalPointsScore(String totalPointsScore) {
        this.totalPointsScore = totalPointsScore;
    }

    public String getIsDoctorQuaCert() {
        return isDoctorQuaCert;
    }

    public void setIsDoctorQuaCert(String isDoctorQuaCert) {
        this.isDoctorQuaCert = isDoctorQuaCert;
    }

    public String getCodeDoctorQuaCert() {
        return codeDoctorQuaCert;
    }

    public void setCodeDoctorQuaCert(String codeDoctorQuaCert) {
        this.codeDoctorQuaCert = codeDoctorQuaCert;
    }

    public String getIsMedicalPractitioner() {
        return isMedicalPractitioner;
    }

    public void setIsMedicalPractitioner(String isMedicalPractitioner) {
        this.isMedicalPractitioner = isMedicalPractitioner;
    }

    public String getCodeMedicalPractitioner() {
        return codeMedicalPractitioner;
    }

    public void setCodeMedicalPractitioner(String codeMedicalPractitioner) {
        this.codeMedicalPractitioner = codeMedicalPractitioner;
    }

    public String getIsStay() {
        return isStay;
    }

    public void setIsStay(String isStay) {
        this.isStay = isStay;
    }

    public String getRoomNumStay() {
        return roomNumStay;
    }

    public void setRoomNumStay(String roomNumStay) {
        this.roomNumStay = roomNumStay;
    }

    public String getTelephoneStay() {
        return telephoneStay;
    }

    public void setTelephoneStay(String telephoneStay) {
        this.telephoneStay = telephoneStay;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMateIdNo() {
        return mateIdNo;
    }

    public void setMateIdNo(String mateIdNo) {
        this.mateIdNo = mateIdNo;
    }

    public String getDirectionalOrgName() {
        return directionalOrgName;
    }

    public void setDirectionalOrgName(String directionalOrgName) {
        this.directionalOrgName = directionalOrgName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMasterGraduateMajorCode() {
        return masterGraduateMajorCode;
    }

    public void setMasterGraduateMajorCode(String masterGraduateMajorCode) {
        this.masterGraduateMajorCode = masterGraduateMajorCode;
    }

    public List<XjRegisterDateForm> getRegisterDateList() {
        return registerDateList;
    }

    public void setRegisterDateList(List<XjRegisterDateForm> registerDateList) {
        this.registerDateList = registerDateList;
    }

    public String getAwardSubjectCategory() {
        return awardSubjectCategory;
    }

    public void setAwardSubjectCategory(String awardSubjectCategory) {
        this.awardSubjectCategory = awardSubjectCategory;
    }

    public String getOutOfSchoolDate() {
        return outOfSchoolDate;
    }

    public void setOutOfSchoolDate(String outOfSchoolDate) {
        this.outOfSchoolDate = outOfSchoolDate;
    }

    public String getBackToSchoolDate() {
        return backToSchoolDate;
    }

    public void setBackToSchoolDate(String backToSchoolDate) {
        this.backToSchoolDate = backToSchoolDate;
    }

    public String getDropOutSchoolDate() {
        return dropOutSchoolDate;
    }

    public void setDropOutSchoolDate(String dropOutSchoolDate) {
        this.dropOutSchoolDate = dropOutSchoolDate;
    }

    public String getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(String recruitType) {
        this.recruitType = recruitType;
    }

    public String getHkInSchool() {
        return hkInSchool;
    }

    public void setHkInSchool(String hkInSchool) {
        this.hkInSchool = hkInSchool;
    }

    public String getHkChangeNo() {
        return hkChangeNo;
    }

    public void setHkChangeNo(String hkChangeNo) {
        this.hkChangeNo = hkChangeNo;
    }

    public String getYbCardNo() {
        return ybCardNo;
    }

    public void setYbCardNo(String ybCardNo) {
        this.ybCardNo = ybCardNo;
    }

    public String getSpouseUnit() {
        return spouseUnit;
    }

    public void setSpouseUnit(String spouseUnit) {
        this.spouseUnit = spouseUnit;
    }

    public String getMainResume() {
        return mainResume;
    }

    public void setMainResume(String mainResume) {
        this.mainResume = mainResume;
    }

    public String getScientificTogether() {
        return scientificTogether;
    }

    public void setScientificTogether(String scientificTogether) {
        this.scientificTogether = scientificTogether;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getPaytuitionFee() {
        return paytuitionFee;
    }

    public void setPaytuitionFee(String paytuitionFee) {
        this.paytuitionFee = paytuitionFee;
    }

    public String getArrearageFee() {
        return arrearageFee;
    }

    public void setArrearageFee(String arrearageFee) {
        this.arrearageFee = arrearageFee;
    }

    public String getBkgain() {
        return bkgain;
    }

    public void setBkgain(String bkgain) {
        this.bkgain = bkgain;
    }

    public String getXsgain() {
        return xsgain;
    }

    public void setXsgain(String xsgain) {
        this.xsgain = xsgain;
    }

    public String getSsyjsgain() {
        return ssyjsgain;
    }

    public void setSsyjsgain(String ssyjsgain) {
        this.ssyjsgain = ssyjsgain;
    }

    public String getSsxwgain() {
        return ssxwgain;
    }

    public void setSsxwgain(String ssxwgain) {
        this.ssxwgain = ssxwgain;
    }

    public String getMasterSubject() {
        return masterSubject;
    }

    public void setMasterSubject(String masterSubject) {
        this.masterSubject = masterSubject;
    }

    public String getPlanGraduateTime() {
        return planGraduateTime;
    }

    public void setPlanGraduateTime(String planGraduateTime) {
        this.planGraduateTime = planGraduateTime;
    }

    public String getGraduateFlag() {
        return graduateFlag;
    }

    public void setGraduateFlag(String graduateFlag) {
        this.graduateFlag = graduateFlag;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperSource() {
        return paperSource;
    }

    public void setPaperSource(String paperSource) {
        this.paperSource = paperSource;
    }

    public String getPaperKey() {
        return paperKey;
    }

    public void setPaperKey(String paperKey) {
        this.paperKey = paperKey;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getDegreeDirection() {
        return degreeDirection;
    }

    public void setDegreeDirection(String degreeDirection) {
        this.degreeDirection = degreeDirection;
    }

    public String getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(String unitNature) {
        this.unitNature = unitNature;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getWorkNature() {
        return workNature;
    }

    public void setWorkNature(String workNature) {
        this.workNature = workNature;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitLinkMan() {
        return unitLinkMan;
    }

    public void setUnitLinkMan(String unitLinkMan) {
        this.unitLinkMan = unitLinkMan;
    }

    public String getUnitLinkManPhone() {
        return unitLinkManPhone;
    }

    public void setUnitLinkManPhone(String unitLinkManPhone) {
        this.unitLinkManPhone = unitLinkManPhone;
    }

    public String getUnitLinkManEmail() {
        return unitLinkManEmail;
    }

    public void setUnitLinkManEmail(String unitLinkManEmail) {
        this.unitLinkManEmail = unitLinkManEmail;
    }

    public String getXjRegDate() {
        return xjRegDate;
    }

    public void setXjRegDate(String xjRegDate) {
        this.xjRegDate = xjRegDate;
    }

    public String getPreGraduation() {
        return preGraduation;
    }

    public void setPreGraduation(String preGraduation) {
        this.preGraduation = preGraduation;
    }

    public String getDormitoryFee() {
        return dormitoryFee;
    }

    public void setDormitoryFee(String dormitoryFee) {
        this.dormitoryFee = dormitoryFee;
    }

    public String getPayDormitoryFee() {
        return payDormitoryFee;
    }

    public void setPayDormitoryFee(String payDormitoryFee) {
        this.payDormitoryFee = payDormitoryFee;
    }

    public String getArrearageDormitoryFee() {
        return arrearageDormitoryFee;
    }

    public void setArrearageDormitoryFee(String arrearageDormitoryFee) {
        this.arrearageDormitoryFee = arrearageDormitoryFee;
    }

    public String getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(String paidFee) {
        this.paidFee = paidFee;
    }

    public String getPaidFeeNo() {
        return paidFeeNo;
    }

    public void setPaidFeeNo(String paidFeeNo) {
        this.paidFeeNo = paidFeeNo;
    }

    public String getGotBachelorCertSubject() {
        return gotBachelorCertSubject;
    }

    public void setGotBachelorCertSubject(String gotBachelorCertSubject) {
        this.gotBachelorCertSubject = gotBachelorCertSubject;
    }

    public String getGotMasterCertSpe() {
        return gotMasterCertSpe;
    }

    public void setGotMasterCertSpe(String gotMasterCertSpe) {
        this.gotMasterCertSpe = gotMasterCertSpe;
    }

    public String getStudentSourceArea() {
        return studentSourceArea;
    }

    public void setStudentSourceArea(String studentSourceArea) {
        this.studentSourceArea = studentSourceArea;
    }

    public String getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(String schoolSystem) {
        this.schoolSystem = schoolSystem;
    }

    public String getDormitoryAdd() {
        return dormitoryAdd;
    }

    public void setDormitoryAdd(String dormitoryAdd) {
        this.dormitoryAdd = dormitoryAdd;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSendUnit() {
        return sendUnit;
    }

    public void setSendUnit(String sendUnit) {
        this.sendUnit = sendUnit;
    }

    public String getReportAddressId() {
        return reportAddressId;
    }

    public void setReportAddressId(String reportAddressId) {
        this.reportAddressId = reportAddressId;
    }

    public String getReportAddressName() {
        return reportAddressName;
    }

    public void setReportAddressName(String reportAddressName) {
        this.reportAddressName = reportAddressName;
    }

    public String getSendNature() {
        return sendNature;
    }

    public void setSendNature(String sendNature) {
        this.sendNature = sendNature;
    }

    public String getSentMemo() {
        return sentMemo;
    }

    public void setSentMemo(String sentMemo) {
        this.sentMemo = sentMemo;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getGraduationWhereabouts() {
        return graduationWhereabouts;
    }

    public void setGraduationWhereabouts(String graduationWhereabouts) {
        this.graduationWhereabouts = graduationWhereabouts;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkOfIndustry() {
        return workOfIndustry;
    }

    public void setWorkOfIndustry(String workOfIndustry) {
        this.workOfIndustry = workOfIndustry;
    }

    public String getWorkOfAreaId() {
        return workOfAreaId;
    }

    public void setWorkOfAreaId(String workOfAreaId) {
        this.workOfAreaId = workOfAreaId;
    }

    public String getWorkOfAreaName() {
        return workOfAreaName;
    }

    public void setWorkOfAreaName(String workOfAreaName) {
        this.workOfAreaName = workOfAreaName;
    }

    public String getWorkLinkMan() {
        return workLinkMan;
    }

    public void setWorkLinkMan(String workLinkMan) {
        this.workLinkMan = workLinkMan;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getWorkLinkPhone() {
        return workLinkPhone;
    }

    public void setWorkLinkPhone(String workLinkPhone) {
        this.workLinkPhone = workLinkPhone;
    }

    public String getWorkPay() {
        return workPay;
    }

    public void setWorkPay(String workPay) {
        this.workPay = workPay;
    }

    public String getUserAim() {
        return userAim;
    }

    public void setUserAim(String userAim) {
        this.userAim = userAim;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getMajorCounterpart() {
        return majorCounterpart;
    }

    public void setMajorCounterpart(String majorCounterpart) {
        this.majorCounterpart = majorCounterpart;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getEmploymentLevel() {
        return employmentLevel;
    }

    public void setEmploymentLevel(String employmentLevel) {
        this.employmentLevel = employmentLevel;
    }

    public String getEmployAgreementUrl() {
        return employAgreementUrl;
    }

    public void setEmployAgreementUrl(String employAgreementUrl) {
        this.employAgreementUrl = employAgreementUrl;
    }

    public String getEmploySign() {
        return employSign;
    }

    public void setEmploySign(String employSign) {
        this.employSign = employSign;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone;
    }

    public String getArchivesPlaceCode() {
        return archivesPlaceCode;
    }

    public void setArchivesPlaceCode(String archivesPlaceCode) {
        this.archivesPlaceCode = archivesPlaceCode;
    }

    public String getArchivesEmsNum() {
        return archivesEmsNum;
    }

    public void setArchivesEmsNum(String archivesEmsNum) {
        this.archivesEmsNum = archivesEmsNum;
    }

    public String getArchivesEmsAddressId() {
        return archivesEmsAddressId;
    }

    public void setArchivesEmsAddressId(String archivesEmsAddressId) {
        this.archivesEmsAddressId = archivesEmsAddressId;
    }

    public String getArchivesEmsAddressName() {
        return archivesEmsAddressName;
    }

    public void setArchivesEmsAddressName(String archivesEmsAddressName) {
        this.archivesEmsAddressName = archivesEmsAddressName;
    }

    public String getMailTime() {
        return mailTime;
    }

    public void setMailTime(String mailTime) {
        this.mailTime = mailTime;
    }

    public String getArchivesUnit() {
        return archivesUnit;
    }

    public void setArchivesUnit(String archivesUnit) {
        this.archivesUnit = archivesUnit;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getJoinMedicalYear() {
        return joinMedicalYear;
    }

    public void setJoinMedicalYear(String joinMedicalYear) {
        this.joinMedicalYear = joinMedicalYear;
    }

    public String getEnterSchoolYear() {
        return enterSchoolYear;
    }

    public void setEnterSchoolYear(String enterSchoolYear) {
        this.enterSchoolYear = enterSchoolYear;
    }

    public String getIsNewStudent() {
        return isNewStudent;
    }

    public void setIsNewStudent(String isNewStudent) {
        this.isNewStudent = isNewStudent;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountryAreaId() {
        return countryAreaId;
    }

    public void setCountryAreaId(String countryAreaId) {
        this.countryAreaId = countryAreaId;
    }

    public String getCountryAreaName() {
        return countryAreaName;
    }

    public void setCountryAreaName(String countryAreaName) {
        this.countryAreaName = countryAreaName;
    }

    public String getWhetherPracticeCert() {
        return whetherPracticeCert;
    }

    public void setWhetherPracticeCert(String whetherPracticeCert) {
        this.whetherPracticeCert = whetherPracticeCert;
    }

    public String getPracticeCertCode() {
        return practiceCertCode;
    }

    public void setPracticeCertCode(String practiceCertCode) {
        this.practiceCertCode = practiceCertCode;
    }

    public String getPracticeCertUrl() {
        return practiceCertUrl;
    }

    public void setPracticeCertUrl(String practiceCertUrl) {
        this.practiceCertUrl = practiceCertUrl;
    }

    public String getWhetherBuyMedicalCard() {
        return whetherBuyMedicalCard;
    }

    public void setWhetherBuyMedicalCard(String whetherBuyMedicalCard) {
        this.whetherBuyMedicalCard = whetherBuyMedicalCard;
    }

    public String getUnitOfDoctor() {
        return unitOfDoctor;
    }

    public void setUnitOfDoctor(String unitOfDoctor) {
        this.unitOfDoctor = unitOfDoctor;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public String getDormitoryFloor() {
        return dormitoryFloor;
    }

    public void setDormitoryFloor(String dormitoryFloor) {
        this.dormitoryFloor = dormitoryFloor;
    }

    public String getDormitoryRoomNumber() {
        return dormitoryRoomNumber;
    }

    public void setDormitoryRoomNumber(String dormitoryRoomNumber) {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
    }

    public String getPartyMembershipTime() {
        return partyMembershipTime;
    }

    public void setPartyMembershipTime(String partyMembershipTime) {
        this.partyMembershipTime = partyMembershipTime;
    }

    public String getPartyMemberFormalTime() {
        return partyMemberFormalTime;
    }

    public void setPartyMemberFormalTime(String partyMemberFormalTime) {
        this.partyMemberFormalTime = partyMemberFormalTime;
    }

    public String getEmsUrl() {
        return emsUrl;
    }

    public void setEmsUrl(String emsUrl) {
        this.emsUrl = emsUrl;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMasterAdmissionSign() {
        return masterAdmissionSign;
    }

    public void setMasterAdmissionSign(String masterAdmissionSign) {
        this.masterAdmissionSign = masterAdmissionSign;
    }

    public String getMasterScienceDegree() {
        return masterScienceDegree;
    }

    public void setMasterScienceDegree(String masterScienceDegree) {
        this.masterScienceDegree = masterScienceDegree;
    }

    public String getMasterGraduationSign() {
        return masterGraduationSign;
    }

    public void setMasterGraduationSign(String masterGraduationSign) {
        this.masterGraduationSign = masterGraduationSign;
    }

    public String getMasterClinicalDegree() {
        return masterClinicalDegree;
    }

    public void setMasterClinicalDegree(String masterClinicalDegree) {
        this.masterClinicalDegree = masterClinicalDegree;
    }

    public String getMasterSchoolReport() {
        return masterSchoolReport;
    }

    public void setMasterSchoolReport(String masterSchoolReport) {
        this.masterSchoolReport = masterSchoolReport;
    }

    public String getDoctorAdmissionSign() {
        return doctorAdmissionSign;
    }

    public void setDoctorAdmissionSign(String doctorAdmissionSign) {
        this.doctorAdmissionSign = doctorAdmissionSign;
    }

    public String getDoctorScienceDegree() {
        return doctorScienceDegree;
    }

    public void setDoctorScienceDegree(String doctorScienceDegree) {
        this.doctorScienceDegree = doctorScienceDegree;
    }

    public String getDoctorGraduationSign() {
        return doctorGraduationSign;
    }

    public void setDoctorGraduationSign(String doctorGraduationSign) {
        this.doctorGraduationSign = doctorGraduationSign;
    }

    public String getDoctorClinicalDegree() {
        return doctorClinicalDegree;
    }

    public void setDoctorClinicalDegree(String doctorClinicalDegree) {
        this.doctorClinicalDegree = doctorClinicalDegree;
    }

    public String getDoctorSchoolReport() {
        return doctorSchoolReport;
    }

    public void setDoctorSchoolReport(String doctorSchoolReport) {
        this.doctorSchoolReport = doctorSchoolReport;
    }

    public String getExpertChitLetter() {
        return expertChitLetter;
    }

    public void setExpertChitLetter(String expertChitLetter) {
        this.expertChitLetter = expertChitLetter;
    }

    public String getIsFixedIncome() {
        return isFixedIncome;
    }

    public void setIsFixedIncome(String isFixedIncome) {
        this.isFixedIncome = isFixedIncome;
    }

    public String getIsFiveGuarantees() {
        return isFiveGuarantees;
    }

    public void setIsFiveGuarantees(String isFiveGuarantees) {
        this.isFiveGuarantees = isFiveGuarantees;
    }

    public String getIsLowGuarantees() {
        return isLowGuarantees;
    }

    public void setIsLowGuarantees(String isLowGuarantees) {
        this.isLowGuarantees = isLowGuarantees;
    }

    public String getIsPoor() {
        return isPoor;
    }

    public void setIsPoor(String isPoor) {
        this.isPoor = isPoor;
    }

    public String getIsOrphan() {
        return isOrphan;
    }

    public void setIsOrphan(String isOrphan) {
        this.isOrphan = isOrphan;
    }

    public String getIsSingleParent() {
        return isSingleParent;
    }

    public void setIsSingleParent(String isSingleParent) {
        this.isSingleParent = isSingleParent;
    }

    public String getIsDisabledChildren() {
        return isDisabledChildren;
    }

    public void setIsDisabledChildren(String isDisabledChildren) {
        this.isDisabledChildren = isDisabledChildren;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getIsDisabledLevel() {
        return isDisabledLevel;
    }

    public void setIsDisabledLevel(String isDisabledLevel) {
        this.isDisabledLevel = isDisabledLevel;
    }

    public String getIsLostAbilityParent() {
        return isLostAbilityParent;
    }

    public void setIsLostAbilityParent(String isLostAbilityParent) {
        this.isLostAbilityParent = isLostAbilityParent;
    }

    public String getIsSeriousIllness() {
        return isSeriousIllness;
    }

    public void setIsSeriousIllness(String isSeriousIllness) {
        this.isSeriousIllness = isSeriousIllness;
    }

    public String getIsFilingRiser() {
        return isFilingRiser;
    }

    public void setIsFilingRiser(String isFilingRiser) {
        this.isFilingRiser = isFilingRiser;
    }

    public String getIsLowIncomeFamilies() {
        return isLowIncomeFamilies;
    }

    public void setIsLowIncomeFamilies(String isLowIncomeFamilies) {
        this.isLowIncomeFamilies = isLowIncomeFamilies;
    }

    public String getIsSpecialCareChildren() {
        return isSpecialCareChildren;
    }

    public void setIsSpecialCareChildren(String isSpecialCareChildren) {
        this.isSpecialCareChildren = isSpecialCareChildren;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getMajorSourceIncome() {
        return majorSourceIncome;
    }

    public void setMajorSourceIncome(String majorSourceIncome) {
        this.majorSourceIncome = majorSourceIncome;
    }

    public String getMajorSourceIncomeDesc() {
        return majorSourceIncomeDesc;
    }

    public void setMajorSourceIncomeDesc(String majorSourceIncomeDesc) {
        this.majorSourceIncomeDesc = majorSourceIncomeDesc;
    }

    public String getIsSufferNaturalDisasters() {
        return isSufferNaturalDisasters;
    }

    public void setIsSufferNaturalDisasters(String isSufferNaturalDisasters) {
        this.isSufferNaturalDisasters = isSufferNaturalDisasters;
    }

    public String getNaturalDisastersDesc() {
        return naturalDisastersDesc;
    }

    public void setNaturalDisastersDesc(String naturalDisastersDesc) {
        this.naturalDisastersDesc = naturalDisastersDesc;
    }

    public String getIsSufferEmergency() {
        return isSufferEmergency;
    }

    public void setIsSufferEmergency(String isSufferEmergency) {
        this.isSufferEmergency = isSufferEmergency;
    }

    public String getEmergencyDesc() {
        return emergencyDesc;
    }

    public void setEmergencyDesc(String emergencyDesc) {
        this.emergencyDesc = emergencyDesc;
    }

    public String getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(String debtMoney) {
        this.debtMoney = debtMoney;
    }

    public String getDebtReason() {
        return debtReason;
    }

    public void setDebtReason(String debtReason) {
        this.debtReason = debtReason;
    }

    public String getFamilyPopulation() {
        return familyPopulation;
    }

    public void setFamilyPopulation(String familyPopulation) {
        this.familyPopulation = familyPopulation;
    }

    public String getWorkingPopulation() {
        return workingPopulation;
    }

    public void setWorkingPopulation(String workingPopulation) {
        this.workingPopulation = workingPopulation;
    }

    public String getUnemployedPopulation() {
        return unemployedPopulation;
    }

    public void setUnemployedPopulation(String unemployedPopulation) {
        this.unemployedPopulation = unemployedPopulation;
    }

    public String getSupportPopulation() {
        return supportPopulation;
    }

    public void setSupportPopulation(String supportPopulation) {
        this.supportPopulation = supportPopulation;
    }

    public String getOtherDisastersDesc() {
        return otherDisastersDesc;
    }

    public void setOtherDisastersDesc(String otherDisastersDesc) {
        this.otherDisastersDesc = otherDisastersDesc;
    }

    public String getIsRuralLowInsured() {
        return isRuralLowInsured;
    }

    public void setIsRuralLowInsured(String isRuralLowInsured) {
        this.isRuralLowInsured = isRuralLowInsured;
    }

    public String getIsRuralPoverty() {
        return isRuralPoverty;
    }

    public void setIsRuralPoverty(String isRuralPoverty) {
        this.isRuralPoverty = isRuralPoverty;
    }

    public String getOtherDifficultyDesc() {
        return otherDifficultyDesc;
    }

    public void setOtherDifficultyDesc(String otherDifficultyDesc) {
        this.otherDifficultyDesc = otherDifficultyDesc;
    }

    public String getIsPoorStudents() {
        return isPoorStudents;
    }

    public void setIsPoorStudents(String isPoorStudents) {
        this.isPoorStudents = isPoorStudents;
    }

    public String getIsRuralStudents() {
        return isRuralStudents;
    }

    public void setIsRuralStudents(String isRuralStudents) {
        this.isRuralStudents = isRuralStudents;
    }

    public String getWorkUnitTDXL() {
        return workUnitTDXL;
    }

    public void setWorkUnitTDXL(String workUnitTDXL) {
        this.workUnitTDXL = workUnitTDXL;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getAddressTDXL() {
        return addressTDXL;
    }

    public void setAddressTDXL(String addressTDXL) {
        this.addressTDXL = addressTDXL;
    }

    public String getDegreeTimeTDXL() {
        return degreeTimeTDXL;
    }

    public void setDegreeTimeTDXL(String degreeTimeTDXL) {
        this.degreeTimeTDXL = degreeTimeTDXL;
    }

    public String getGraduateSchooTDXL() {
        return graduateSchooTDXL;
    }

    public void setGraduateSchooTDXL(String graduateSchooTDXL) {
        this.graduateSchooTDXL = graduateSchooTDXL;
    }

    public String getTutorUnit() {
        return tutorUnit;
    }

    public void setTutorUnit(String tutorUnit) {
        this.tutorUnit = tutorUnit;
    }

    public String getTutorContact() {
        return tutorContact;
    }

    public void setTutorContact(String tutorContact) {
        this.tutorContact = tutorContact;
    }

    public String getStudentSourceAreaId() {
        return studentSourceAreaId;
    }

    public void setStudentSourceAreaId(String studentSourceAreaId) {
        this.studentSourceAreaId = studentSourceAreaId;
    }
}

