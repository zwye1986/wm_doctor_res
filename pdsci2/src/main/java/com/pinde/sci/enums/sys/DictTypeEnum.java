package com.pinde.sci.enums.sys;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;
import com.pinde.sci.model.mo.SysDict;

import java.util.List;
import java.util.Map;

public enum DictTypeEnum implements GeneralEnum<String> {

	UserTitle("UserTitle","人员.职称","sys"),
	UserDegree("UserDegree","人员.学位","sys"),
	UserEducation("UserEducation","人员.学历","sys"),
	UserPost("UserPost","人员.职务","sys"),
	Certificatelevel("Certificatelevel","师资证书级别","sys"),
	TeachersType("TeachersType","师资类型","sys"),
	CertificateTermValidity("CertificateTermValidity","师资证书有效期","sys"),
	LeaveType("LeaveType","请假类型","sys"),
	AppealType("AppealType","申述类型","sys"),
	ConsultType("ConsultType","咨询类型","res",2),
//	SysWorkStation("SysWorkStation","系统.工作站","sys"),
//	SysModule("SysModule","系统.模块菜单","sys",3),
//	Region("Region","系统.行政区域代码","sys",3),
//	SysMenuSet("SysMenuSet","系统.一级菜单","sys",2),
//	SysMenu("SysMenu","系统.二级菜单","sys",3),
	ProjTypeSource("ProjTypeSource","项目来源类型","srm",2),
	ProjType("ProjType","科研项目类型","srm"),//科研项目
	ManageType("ManageType","项目管理类型","srm"),//项目管理
	EdusType("EdusType","科教强卫类型","srm"),//科教强卫
	AwardType("AwardType","科技报奖类型","srm"),//科教强卫
	ProfeType("ProfeType","重点专科类型","srm"),//重点专科
	SubjType("SubjType","重点学科类型","srm"),//重点学科
	TalentType("TalentType","重点人才类型","srm"),//重点人才
	AidProjType("AidProjType" , "项目备案类型" , "srm"),//项目备案类型

	PlanCategory("PlanCategory","计划类别","srm"),

    PostType("PostType" , "职务类别" , "srm"),//职务类别（江苏中医药局）
	
	AidCountryProjType("AidCountryProjType","项目类型[补填].国家级","srm"),
	AidProvinceProjType("AidProvinceProjType","项目类型[补填].省级","srm"),
	AidCityProjType("AidCityProjType","项目类型[补填].市厅级","srm"),

	LaborPay("LaborPay" , "专家.劳务支付方式" , "srm"),
	ExpertScoreResult("ExpertScoreResult" , "专家.评审结果" , "srm"),

//	PersonalInfo("PersonalInfo","人才.个人情况","srm"),
//	PostgraduateTeacher("PostgraduateTeacher","人才.研究生导师","srm"),
//	TalentsDevelop("TalentsDevelop","人才.人才培养","srm"),
//	AcademicHonour("AcademicHonour","人才.学术荣誉","srm"),
	
//	LanSkillful("LanSkillful","医学新技术.外语熟练程度","srm"),
	
//    DiscussType("DiscussType","研究类别","sys"),
//    TrueAndFalse("TrueAndFalse","是与否","sys"),
//    SubjectInfo("SubjectInfo","学科情况","sys"),

	ProjAuthorRank("ProjAuthorRank","课题参与人员排名","srm"),
	OrgBelong("OrgBelong","成果.所属单位","srm"),
	ProjSource("ProjSource","成果.项目来源","srm"),
	LanguageType("LanguageType","成果.语言种类","srm"),	
	AuthorType("AuthorType","成果.作者类型","srm"),
	SubjectType("SubjectType","成果.学科门类","srm"),
	ApplyUserType("ApplyUserType","成果.署名顺序","srm"),
	CgResultName("CgResultName","成果.成果名称","srm"),
	SubjectSource("SubjectSource","课题来源","srm"),
	
	ThesisType("ThesisType","论文.论文类型","srm"),
	JournalType("JournalType","论文.期刊类型","srm"),
	PublishType("PublishType","论文.版面类型","srm"),
	PublishScope("PublishScope","论文.发表范围","srm"),
	MeetingType("MeetingType","论文.会议类型","srm"),
	
	AchType("AchType","科技.成果形式","srm"),
	PrizedGrade("PrizedGrade","科技.获奖级别","srm"),
	PrizedLevel("PrizedLevel","科技.获奖等级","srm"),
	
	AchBookType("AchBookType","著作.著作类别","srm"),
	PressLevelType("PressLevelType","著作.出版社级别","srm"),
	PlaceNameType("PlaceNameType","著作.出版地","srm"),
	WriteNameType("WriteNameType","著作.作者.参编类型","srm"),
	
	CopyrightType("CopyrightType","著作权.著作权类型","srm"),

	AppraisalResultName("AppraisalResultName","鉴定.鉴定结论","srm"),
	AppraisalTypeName("AppraisalTypeName","鉴定.鉴定形式","srm"),
	FinishTypeName("FinishTypeName","鉴定.完成形式","srm"),
	
	PatentType("PatentType","专利.专利类型","srm"),
	PatentRange("PatentRange","专利.专利范围","srm"),
	PatentStatus("PatentStatus","专利.专利状态","srm"),
	PatentCooperType("PatentCooperType","专利.合作类型","srm"),
	
	AcceptOrg("AcceptOrg" , "研究报告.采纳单位" , "srm"),

    AchTopicType("AchTopicType","课题.课题类别(徐州)","srm"),

	YhProjCategory("YhProjCategory","余杭.项目类别","srm"),
	YhTechnologyField("YhTechnologyField","余杭.技术领域","srm"),
	YhTechnologySource("YhTechnologySource","余杭.技术来源","srm"),
	YhProjStatus("YhProjStatus","余杭.项目状态","srm"),
	YhBAProjCategory("YhBAProjCategory","余杭.备案项目来源/类别","srm",2),
	
	JsPlanCategory("JsPlanCategory","江苏.计划类别","srm"),
	JsApplyType("JsApplyType","江苏.申请类型","srm"),
	JsRelyType("JsRelyType","江苏.依托类型","srm"),
	JsOrgCharacter("JsOrgCharacter","江苏.单位性质","srm"),
	JsSubordinateRelation("JsSubordinateRelation","江苏.隶属关系","srm"),
	JsStudyAbroadTime("JsStudyAbroadTime","江苏.留学时间","srm"),
	JsBAProjCategory("JsBAProjCategory","江苏.备案项目来源","srm",1),
	//江苏科研项目类别
	JsBAProjType("JsBAProjType","江苏科研.备案项目类别","srm"),
	//
	JsBAProjRank("JsBAProjRank","江苏科研.备案项目级别","srm"),
	JsProjStatus("JsProjStatus","江苏.项目状态","srm"),
    //徐州中心医院
    XzApplyDept("XzApplyDept","徐州.承担科室","srm"),
    // XzProjType("XzProjStatus","徐州.项目类别","srm"),
    XzProjSource("XzProjSource","徐州.项目来源","srm"),

   //无锡二院
    WxeyProjType("WxeyProjType","无锡二院.项目类别","srm"),
    WxeyFundType("WxeyFundType","无锡二院.经费类别","srm"),
    WxeyBranch("WxeyBranch","无锡二院.支部","srm"),
    WxeySignSeq("WxeySignSeq","无锡二院.署名顺序","srm"),
    WxeyProjSource("WxeyProjSource","无锡二院.项目来源","srm"),
    WxeyProjRank("WxeyProjRank","无锡二院.项目级别","srm"),

	GcpProjType("GcpProjType","项目类别","irb,gcp"),
	TrackFrequency("TrackFrequency","跟踪审查频率","irb"),
	
	RegulationCountry("RegulationCountry","法规文件.国家文件","gcp"),
	RegulationLocal("RegulationLocal","法规文件.地方文件","gcp"),
	RegulationOrg("RegulationOrg","法规文件.机构文件","gcp"),
	RegulationDept("RegulationDept","法规文件.专业组文件","gcp"),
	
	GcpFundUses("GcpFundUses","经费用途","gcp"),
	
	DoseUnit("DoseUnit","剂量单位","gcp"),
	PreparationUnit("PreparationUnit","制剂单位","gcp"),
	Usage("Usage","给药途径","gcp"),
	Solution("Solution","溶液","gcp"),
	MiniPackUnit("MiniPackUnit","最小包装单位","gcp"),

	
	VisitType("VisitType","ECRF.访视类型","edc"),
	StandardUnit("StandardUnit","ECRF.正常值单位","edc"),
	ModuleType("ModuleType","ECRF.模块类型","edc"),
	QueryType("QueryType","ECRF.疑问类型","edc"),
	
	BudgetItem("BudgetItem","经费.预算项","srm",2),
	ProjFundType("ProjFundType","经费.经费类型","srm"),
	ProjFundAccountsType("ProjFundAccountsType","经费.经费到账类型","srm"),
	FundRetype("FundRetype","经费.报销方式","srm"),

	CourseMajor("CourseMajor","edu.专业","edu"),
	NjmuCourseMajor("NjmuCourseMajor","edu.专业","njmuedu"),

	FstuType("FstuType","继教项目分类","fstu"),
	SatOrg("SatOrg","科研成果.所属单位","fstu"),
	ProSource("ProSource","科研成果.项目来源","fstu"),
	ProType("ProType","科研成果.项目类别","fstu"),
	ProLevel("ProLevel","科研成果.项目级别","fstu"),
	FstuProStatus("FstuProStatus","科研成果.项目状态","fstu"),
	FstuLanguageType("FstuLanguageType","科研成果.语言种类","fstu"),
	FstuSubjectType("FstuSubjectType","科研成果.学科门类","fstu"),
	SatType("SatType","科研成果.成果形式","fstu"),
	SatGrade("SatGrade","科研成果.获奖级别","fstu"),
	SatLevel("SatLevel","科研成果.获奖等级","fstu"),
	SubjectCategories("SubjectCategories","科研成果.学科门类","fstu"),
	AcademicType("AcademicType","学术类型","fstu"),
	ScoreType("ScoreType","学分类别","fstu"),
	AcaScoreType("AcaScoreType","学分类别联动项目大类","fstu",2),
	AssessItem("AssessItem","评分项","fstu"),
	AccomplishResult("AccomplishResult","完成情况","fstu"),
	ScoreYear("ScoreYear","学分年份","fstu"),
	FstuThesisType("FstuThesisType","论文.论文类型","fstu"),
	FstuJournalType("FstuJournalType","论文.期刊类型","fstu"),
	FstuPublishType("FstuPublishType","论文.版面类型","fstu"),
	FstuPublishScope("FstuPublishScope","论文.发表范围","fstu"),
	FstuMeetingType("FstuMeetingType","论文.会议类型","fstu"),
	FstuAuthorType("FstuAuthorType","论文.作者类型","fstu"),
	FstuBookType("FstuBookType","著作.著作类别","fstu"),
	FstuPressLevelType("FstuPressLevelType","著作.出版社级别","fstu"),
	FstuPlaceNameType("FstuPlaceNameType","著作.出版地","fstu"),
	FstuWriteNameType("FstuWriteNameType","著作.作者.参编类型","fstu"),
	FstuProjSubject("FstuProjSubject","项目管理.所属学科","fstu"),
	FstuProjTeachingObject("FstuProjTeachingObject","项目管理.教学对象","fstu"),
	
	CustomerGrade("CustomerGrade","客户等级","erp"),
	HospitalGrade("HospitalGrade","医院级别","erp"),
	HospitalLevel("HospitalLevel","医院子级别","erp"),
	HospitalType("HospitalType","医院类型","erp"),
	SchoolType("SchoolType","学校级别","erp"),
	ProductType("ProductType","产品类型","erp"),
	DocType("DocType","公共文档.文档类型","erp"),
	Label("Label","公共文档.标签","erp"),
	PreSalesSupport("PreSalesSupport","售前支持","erp"),
	SalesImplement("SalesImplement","售中实施","erp"),
	Service("Service","售后服务","erp"),

	InspectionType("InspectionType","院级督导检查类型","res"),
	ActivityType("ActivityType","教学活动类型","res"),
	TrainingType("TrainingType","培训类别","res,recruit"),
	TrainingYears("TrainingYears","培养年限","sch,res,recruit"),
	CampaignType("CampaignType","活动形式","res"),
	GraduateSchool("GraduateSchool","毕业学校","sch,res"),
	GraduateMajor("GraduateMajor","毕业专业","res"),
	DoctorTrainingSpe("DoctorTrainingSpe","培训专业","sch,res,recruit"),
	TrainingSpeInShort("TrainingSpeInShort","紧缺专业","sch,res"),
	SecondTrainingSpe("SecondTrainingSpe","二级培训专业","sch,res"),
	ZyCatSpe("ZyCatSpe","中医培训类别","sch,res"),
	DoctorSessionNumber("DoctorSessionNumber","届数","sch,res"),
	ExamSite("ExamSite","考点","res"),
	InfoColumn("InfoColumn","通知栏目","res"),
	TeachType("TeachType","教学形式","res"),
	StandardDept("StandardDept","标准科室","sch,res,recruit",3),
	ResGroup("ResGroup","组别","res"),
	TestType("TestType","考试类型","res"),
//	OrgLevel("OrgLevel","机构等级","res"),
	DoctorType("DoctorType","人员类型","sch,res,recruit"),
	DegreeType("DegreeType","学位类型","res"),
	//江苏省住院医师 - 培训专业分类
	WMFirst("WMFirst","江苏.西医一阶段培训专业","res"),
	/*NationalResident("NationalResident","江苏.国家住院医师培训专业","res"),*/
	WMSecond("WMSecond","江苏.西医二阶段培训专业","res"),
	AssiGeneral("AssiGeneral","江苏.助理全科培训专业","res"),
	
	YetTrainYear("YetTrainYear","江苏.已培养年限","res"),
	DoctorStatus("DoctorStatus","江苏.医师状态","sch,res,recruit"),
	DoctorStrike("DoctorStrike","江苏.医师走向","res"),
	UnGrantCertfResaon("UnGrantCertfResaon","暂缓发证理由","res"),
	PracticeType("PracticeType","执业类型","res",2),
	QualifiedMaterial("QualifiedMaterial","江苏中医.执业资格材料","res"),
	ProTechnicalQualified("ProTechnicalQualified","江苏中医.专业技术资格","res"),

	ZySpe("ZySpe","四川中医.二级专业","res"),
//	ZyqkSpe("ZyqkSpe","四川中医.中医全科二级专业","res"),
	ZyCoSpe("ZyCoSpe","四川中医.中医对应专业","res"),
	ZyqkCoSpe("ZyqkCoSpe","四川中医.中医全科对应专业","res"),
	SczyOrgLevel("SczyOrgLevel","四川中医.基地等级","res"),
//	ZyzlqkSpe("ZyzlqkSpe","四川中医.中医助理全科专业","res"),

	BaseAttribute("BaseAttribute","单位性质","res"),
	MedicalHeaithOrg("MedicalHeaithOrg","医疗卫生机构","res"),
	HospitalAttr("HospitalAttr","医院属性","res"),
	HospitalCategory("HospitalCategory","医院类别","res"),
	BasicHealthOrg("BasicHealthOrg","基层医疗卫生机构","res"),
	BasicHealthOrgLevel("BasicHealthOrgLevel","基层医疗卫生机构等级","res"),



	GzzyjxUserTitle("GzzyjxUserTitle","广州中医药大学第一附属医院进修.人员职称","res",2),
	GzzyjxNational("GzzyjxNational","广州中医药大学第一附属医院进修.国籍配置","res"),
	GzzyjxSuggestion("CzyyjxSuggestion","广州中医药大学第一附属医院进修.审核意见配置","res"),
	CzyyjxUserTitle("CzyyjxUserTitle","潮州市人民医院进修.人员职称","res",2),
	CzyyjxNational("CzyyjxNational","潮州市人民医院进修.国籍配置","res"),
	CzyyjxSuggestion("CzyyjxSuggestion","潮州市人民医院进修.审核意见配置","res"),
	DwjxBatch("DwjxBatch","成都中医进修批次","res"),
	DwjxSpe("DwjxSpe","成都中医药大学附属医院.进修专业","res"),
	TitleGenre("TitleGenre","职称类别","res"),
	PracticeGenre("PracticeGenre","执业类别","res"),
	HospitalRank("HospitalRank","医院级别","res"),
	// 江苏中医.中医培训专业
	ChineseMedicine("ChineseMedicine","江苏中医.中医培训专业","res"),
	// 江苏中医.中医全科培训专业
	TCMGeneral("TCMGeneral","江苏中医.中医全科培训专业","res"),
	// 江苏中医.中医助理全科培训专业
	TCMAssiGeneral("TCMAssiGeneral","江苏中医.中医助理全科培训专业","res"),
	//sr
    OrgRank("OrgRank","江苏.单位级别","res"),
    BaseLevel("BaseLevel","江苏.单位等级","res"),
    BaseType("BaseType","江苏.基地类型","res"),
    BasProperty("BasProperty","江苏.基地性质","res"),
    ResidentBaseApproveNum("ResidentBaseApproveNum","江苏.住院医师基地获批文号","res"),
	GeneralBaseApproNum("GeneralBaseApproNum","江苏.全科医师基地获批文号","res"),
	SendSchool("SendSchool","江苏.派送学校","res"),
	WorkOrg("WorkOrg","江苏.派送单位","res"),
	LectureType("LectureType","讲座类型","res"),
	LectureLevel("LectureLevel","讲座级别","res"),
	LectureSpeakerRole("LectureSpeakerRole","讲座授课角色","res"),
	DeptActivityType("DeptActivityType","科室活动类型","res"),

	SourcesOfFunds("SourcesOfFunds","经费来源（省厅）","res"),
	ProjectOfFunds("ProjectOfFunds","经费用途（省厅）","res"),
	BaseFundingUse("BaseFundingUse","基地经费用途","res"),
	SynCostManagement("SynCostManagement","省级经费用途","res"),

	// 个人信息数据采集新增
	Nationality("Nationality","国籍","res"),
	EnglishAbility("EnglishAbility","英语能力","res"),
	EnglishGradeExamType("EnglishGradeExamType"," 外语等级考试类型","res"),
	//CollegeInformation("CollegeInformation"," 院校信息","res"),
	//MajorsStudied("MajorsStudied"," 所学专业信息","res"),

	StudentSource("StudentSource","学籍.考生来源","cmis"),
	AdmitType("AdmitType","学籍.录取类别","cmis"),
	TrainType("TrainType","学籍.培养层次","cmis"),
	StudyForm("StudyForm","学籍.学习形式","cmis"),
	AtSchoolStatus("AtSchoolStatus","学籍.在校状态","cmis"),
	SchoolRollStatus("SchoolRollStatus","学籍.学籍状态","cmis"),
	RecruitSeason("RecruitSeason","学籍.招生季节","cmis"),
	TermSeason("TermSeason","学籍.课表学期","cmis"),
	TeachingPlace("TeachingPlace","学籍.上课地点","cmis"),
	TrainCategory("TrainCategory","学籍.培养类型","cmis"),
	FirstEducation("FirstEducation","学籍.第一学历","cmis"),
	Major("Major","学籍.专业","cmis"),
	DegreeCategory("DegreeCategory","学籍.学位类别","cmis"),
	XjClass("XjClass","学籍.班级","cmis"),
	XjCourseType("XjCourseType","学籍.课程类别","cmis"),
	IsExceptional("IsExceptional","学籍.是否破格","cmis"),
	IsRecommend("IsRecommend","学籍.是否推免生","cmis"),
	XjCourseMoudle("XjCourseMoudle","学籍.课程模块","cmis"),
	XjIsPassed("XjIsPassed","学籍.是否通过","cmis"),
	XjStudyWay("XjStudyWay","学籍.修读方式","cmis"),
	XjEvaluationMode("XjEvaluationMode","学籍.考核方式","cmis"),

	Schoolmaster("Schoolmaster","学位证明-校长","cmis"),
	Medicine("Medicine","学位证明-学科门类","cmis"),
	SpeEnglish("SpeEnglish","专业对应英文名","cmis"),
	RecruitType("RecruitType","学籍.招录方式","cmis"),
	WorkNature("WorkNature","学籍.工作性质","cmis"),
	UnitNature("UnitNature","学籍.就业单位性质","cmis"),
	GotDegreeDirection("GotDegreeDirection","学籍.获学位后去向","cmis"),
	PaperType("PaperType","学籍.论文类型","cmis"),
	PaperSource("PaperSource","学籍.论文选题来源","cmis"),
	PreGraduation("PreGraduation","学籍.前置学历","cmis"),
	UnderStudyForm("UnderStudyForm","学籍.获得本科学历的学习形式","cmis"),
	MasterStudyForm("MasterStudyForm","学籍.获得硕士学位的学习形式","cmis"),
	PoliticalOutlook("PoliticalOutlook","学籍.政治面貌","cmis"),
	XjZsCategory("XjZsCategory","学籍.招生门类","cmis",3),
	XjTechnicalTitle("XjTechnicalTitle","学籍.技术职称","cmis"),
	XjSubjectDeclaration("XjSubjectDeclaration","学籍.申报学科","cmis",2),
	DefenceTime("DefenceTime","学籍.论文答辩时间","cmis"),
	ReplenishTime("ReplenishTime","学籍.学位补授时间","cmis"),
	XjPartyBranch("XjPartyBranch","学籍.所属党支部","cmis"),

	//广医大学籍字典
	GyStudentSource("GyStudentSource","学籍.考生来源","gycmis"),
	GyAdmitType("GyAdmitType","学籍.录取类别","gycmis"),
	GyTrainType("GyTrainType","学籍.培养层次","gycmis"),
	GyStudyForm("GyStudyForm","学籍.学习形式","gycmis"),
	GyAtSchoolStatus("GyAtSchoolStatus","学籍.在校状态","gycmis"),
	GySchoolRollStatus("GySchoolRollStatus","学籍.学籍状态","gycmis"),
	GyRecruitSeason("GyRecruitSeason","学籍.招生季节","gycmis"),
	GyTermSeason("GyTermSeason","学籍.课表学期","gycmis"),
	GyTeachingPlace("GyTeachingPlace","学籍.上课地点","gycmis"),
	GyTrainCategory("GyTrainCategory","学籍.培养类型","gycmis"),
	GyFirstEducation("GyFirstEducation","学籍.第一学历","gycmis"),
	GyMajor("GyMajor","学籍.专业","gycmis"),
	GyDegreeCategory("GyDegreeCategory","学籍.学位类别","gycmis"),
	GyXjClass("GyXjClass","学籍.班级","gycmis"),
	GyXjCourseType("GyXjCourseType","学籍.课程类别","gycmis"),
	GyIsExceptional("GyIsExceptional","学籍.是否破格","gycmis"),
	GyIsRecommend("GyIsRecommend","学籍.是否推免生","gycmis"),
	GyXjCourseMoudle("GyXjCourseMoudle","学籍.课程模块","gycmis"),
	GyXjIsPassed("GyXjIsPassed","学籍.是否通过","gycmis"),
	GyXjStudyWay("GyXjStudyWay","学籍.修读方式","gycmis"),
	GyXjEvaluationMode("GyXjEvaluationMode","学籍.考核方式","gycmis"),

	GySchoolmaster("GySchoolmaster","学位证明-校长","gycmis"),
	GyMedicine("GyMedicine","学位证明-学科门类","gycmis"),
	GySpeEnglish("GySpeEnglish","专业对应英文名","gycmis"),
	GyRecruitType("GyRecruitType","学籍.招录方式","gycmis"),
	GyWorkNature("GyWorkNature","学籍.工作性质","gycmis"),
	GyUnitNature("GyUnitNature","学籍.就业单位性质","gycmis"),
	GyGotDegreeDirection("GyGotDegreeDirection","学籍.获学位后去向","gycmis"),
	GyPaperType("GyPaperType","学籍.论文类型","gycmis"),
	GyPaperSource("GyPaperSource","学籍.论文选题来源","gycmis"),
	GyPreGraduation("GyPreGraduation","学籍.前置学历","gycmis"),
	GyUnderStudyForm("GyUnderStudyForm","学籍.获得本科学历的学习形式","gycmis"),
	GyMasterStudyForm("GyMasterStudyForm","学籍.获得硕士学位的学习形式","gycmis"),
	GyPoliticalOutlook("GyPoliticalOutlook","学籍.政治面貌","gycmis"),
	GyDefenceTime("GyDefenceTime","学籍.论文答辩时间","gycmis"),
	GyReplenishTime("GyReplenishTime","学籍.学位补授时间","gycmis"),
	GyXjPartyBranch("GyXjPartyBranch","学籍.所属党支部","gycmis"),
	GyXjDegreeLevel("GyXjDegreeLevel","学籍.学位级别下类别","gycmis"),
	GyXjStudentSourceArea("GyXjStudentSourceArea","学籍.生源地","gycmis"),

	CheckSpe("CheckSpe","临床技能考核.专业","osca"),
	ExamRoom("ExamRoom","临床技能考核.考场","osca"),
	ExamPart("ExamPart","临床技能考核.考核部分","osca"),
	OscaTrainingType("OscaTrainingType","临床技能考核.培训类型","osca",2),
	OscaCity("OscaCity","临床技能考核.地市","osca"),
	OscaUserTitle("OscaUserTitle","临床技能考核.职称","osca"),

	LcjnUserTitle("LcjnUserTitle","职称","lcjn"),
	LcjnSpe("LcjnSpe","培训专业","lcjn"),
	LcjnSkill("LcjnSkill","课程技能","lcjn"),
	SkillMaterial("SkillMaterial","技能耗材","lcjn"),
	SkileFixedAssets("SkileFixedAssets","技能固定资产","lcjn"),
	CourseEvaluationItem("CourseEvaluationItem","课程评价项目","lcjn"),
	TeacherEvaluationItem("TeacherEvaluationItem","教师评价项目","lcjn"),

	HighestEducation("HighestEducation","最高学历","gzykdx"),
	HighestDegree("HighestDegree","最高学位","gzykdx"),
	GzykdxUserTitle("GzykdxUserTitle","职称","gzykdx"),
	AuthorRanking("AuthorRanking","作者排名","gzykdx"),
	GzykdxSpe("GzykdxSpe","专业名称","gzykdx"),
	ResearchArea("ResearchArea","研究方向","gzykdx"),
	GzykdxDegreeType("GzykdxDegreeType","学位类型","gzykdx"),

	NyzlSpe("NyzlSpe","专业名称","nyzl"),
	NyzlSubject("NyzlSubject","学科名称","nyzl"),
	NyzlTeacherType("NyzlTeacherType","导师类型","nyzl"),
	NyzlBatch("NyzlBatch","批次信息","nyzl"),
	NyzlDegreeType("NyzlDegreeType","学位类型","nyzl"),
	NyzlApplicationType("NyzlApplicationType","报考类型","nyzl"),
	NyzlDept("NyzlDept","科室维护","nyzl"),
	NyzlDirection("NyzlDirection","方向","nyzl"),

	ZseyTrainRoom("ZseyTrainRoom","培训教室","zseylcjn"),
	Trainees("Trainees","培训对象","zseylcjn"),

	DigestiveDiseasesType("DigestiveDiseasesType","消化疾病类型","portals"),
	CourseType("CourseType","课程类型","study"),
	CatSpeType("CatSpeType","培训类型","study",2),
	OrgLevelRank("OrgLevelRank","级别","ws"),
			;

	public static Map<String,String> sysDictIdMap ;
	public static Map<String,List<SysDict>> sysListDictMap ;
	private final String id;
	private final String name;
	private final String wsid;
	private Integer level = 1;
	DictTypeEnum(String id,String name,String wsid) {
		this.id = id;
		this.name = name;
		this.wsid = wsid;
	}
	DictTypeEnum(String id,String name,String wsid,int level) {
		this.id = id;
		this.name = name;
		this.wsid = wsid;
		this.level = level;
	}

	//标签使用
	public static String getDictName(DictTypeEnum dictTypeEnum, String id) {
		return sysDictIdMap.get(dictTypeEnum.getId() + "." + id);
	}

	public static String getNameById(String id) {
		if (EnumUtil.getById(id, DictTypeEnum.class) != null) {
			return EnumUtil.getById(id, DictTypeEnum.class).getName();
		} else {
			return "";
		}
	}

	public String getDictNameById(String id) {
		return sysDictIdMap.get(getId() + "." + id);
	}

	public List<SysDict> getSysDictList() {
		return sysListDictMap.get(getId());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getWsid() {
		return wsid;
	}

	public int getLevel() {
		return level;
	}
}
