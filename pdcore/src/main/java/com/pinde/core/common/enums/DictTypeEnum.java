package com.pinde.core.common.enums;

import com.pinde.core.common.GeneralEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.util.EnumUtil;

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


	BaseAttribute("BaseAttribute","单位性质","res"),
	MedicalHeaithOrg("MedicalHeaithOrg","医疗卫生机构","res"),
	HospitalAttr("HospitalAttr","医院属性","res"),
	HospitalCategory("HospitalCategory","医院类别","res"),
	BasicHealthOrg("BasicHealthOrg","基层医疗卫生机构","res"),
	BasicHealthOrgLevel("BasicHealthOrgLevel","基层医疗卫生机构等级","res"),



	TitleGenre("TitleGenre","职称类别","res"),
	PracticeGenre("PracticeGenre","执业类别","res"),
	HospitalRank("HospitalRank","医院级别","res"),
    /*	// 江苏中医.中医培训专业
        ChineseMedicine("ChineseMedicine","江苏中医.中医培训专业","res"),
        // 江苏中医.中医全科培训专业
        TCMGeneral("TCMGeneral","江苏中医.中医全科培训专业","res"),
        // 江苏中医.中医助理全科培训专业
        TCMAssiGeneral("TCMAssiGeneral","江苏中医.中医助理全科培训专业","res"),*/
	//sr
    OrgRank("OrgRank", "江苏.单位级别", "res"),
    BaseLevel("BaseLevel", "江苏.单位等级", "res"),
    BaseType("BaseType", "江苏.基地类型", "res"),
    BasProperty("BasProperty", "江苏.基地性质", "res"),
    ResidentBaseApproveNum("ResidentBaseApproveNum", "江苏.住院医师基地获批文号", "res"),
	GeneralBaseApproNum("GeneralBaseApproNum","江苏.全科医师基地获批文号","res"),
	SendSchool("SendSchool","江苏.派送学校","res"),
    WorkOrg("WorkOrg", "江苏.派送单位", "res"),
	LectureType("LectureType","讲座类型","res"),
	LectureLevel("LectureLevel","讲座级别","res"),
	LectureSpeakerRole("LectureSpeakerRole","讲座授课角色","res"),
	DeptActivityType("DeptActivityType","科室活动类型","res"),

	SourcesOfFunds("SourcesOfFunds","经费来源（省厅）","res"),
	ProjectOfFunds("ProjectOfFunds","经费用途（省厅）","res"),
	BaseFundingUse("BaseFundingUse","基地经费用途","res"),
	SynCostManagement("SynCostManagement","省级经费用途","res"),

    // 个人信息数据采集新增
    Nationality("Nationality", "国籍", "res"),
    EnglishAbility("EnglishAbility", "英语能力", "res"),
    EnglishGradeExamType("EnglishGradeExamType", " 外语等级考试类型", "res"),


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


	DigestiveDiseasesType("DigestiveDiseasesType","消化疾病类型","portals"),
	CourseType("CourseType","课程类型","study"),
	CatSpeType("CatSpeType","培训类型","study",2),
    OrgLevelRank("OrgLevelRank", "等级等次", "ws", 2),
    ;

	public static Map<String,String> sysDictIdMap ;
	public static Map<String,List<SysDict>> sysListDictMap ;
	private final String id;
	private final String name;
	private final String wsid;
	private Integer level = 1;

    DictTypeEnum(String id, String name, String wsid) {
		this.id = id;
		this.name = name;
		this.wsid = wsid;
	}

    DictTypeEnum(String id, String name, String wsid, int level) {
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
