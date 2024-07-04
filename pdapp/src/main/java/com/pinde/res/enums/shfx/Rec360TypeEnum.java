package com.pinde.res.enums.shfx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum Rec360TypeEnum implements GeneralEnum<String> {
	Teacher_360("Teacher_360","带教评估住院医师"),
	Paramedic_360("Paramedic_360","临床护理人员对住院医师评价"),
	Patient_360("Patient_360","患者对住院医师评价"),
	TeacherGrade("TeacherGrade","学员对带教老师评分"),
	DeptGrade("DeptGrade","学员对科室评分"),
	Physique_FCK1("Physique_FCK1","妇产科学体格检查评分表一"),
	Physique_FCK2("Physique_FCK2","妇产科学体格检查评分表二"),
	Physique_FCK3("Physique_FCK3","妇产科学体格检查评分表三"),
	Physique_FBTJ("Physique_FBTJ","体格检查评分表（腹部体检）"),
	Physique_JZXTJ("Physique_JZXTJ","体格检查评分表（甲状腺体检）"),
	Physique_SJXTTJ("Physique_SJXTTJ","体格检查评分表（神经系统体检）"),
	Physique_XZTJ("Physique_XZTJ","体格检查评分表（心脏体检）"),
	Physique_XBTJ("Physique_XBTJ","体格检查评分表（胸部体检）"),
	Clinical_CRQG("Clinical_CRQG","成人气管插管穿刺术操作技能考考核评分表"),
	Clinical_FCKX("Clinical_FCKX","妇产科学专业手术技能操作评分表"),
	Clinical_FQCC("Clinical_FQCC","腹腔穿刺术操作技能考核评分表"),
	Clinical_GDMCC("Clinical_GDMCC","股动脉穿刺操作技能考考核评分表"),
	Clinical_GSCC("Clinical_GSCC","骨髓穿刺术操作技能考核评分表"),
	Clinical_SJMCC("Clinical_SJMCC","深静脉穿刺术操作技能考考核评分表"),
	Clinical_SZCC("Clinical_SZCC","肾脏穿刺活检术操作技能考考核评分表"),
	Clinical_DSXFFS("Clinical_DSXFFS","徒手心肺复苏操作技能考核评分表"),
	Clinical_WKXSS("Clinical_WKXSS","外科学专业手术技能操作评分表"),
	Clinical_WKXWJ("Clinical_WKXWJ","外科学专业无菌操作（换药拆线）技能评分表"),
	Clinical_XQCC("Clinical_XQCC","胸腔穿刺术操作技能考核评分表"),
	Clinical_YZCC("Clinical_YZCC","腰椎穿刺术操作技能考核评分表"),
	MedicalHistory("MedicalHistory","病史采集评分表"),
	CaseAnalysis("CaseAnalysis","病例分析评分表")
	;

	private final String id;
	private final String name;

	Rec360TypeEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static String getNameById(String id) {
		return EnumUtil.getById(id, Rec360TypeEnum.class).getName();
	}
}
