package com.pinde.res.enums.shfx;

import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.EnumUtil;

public enum RecSkillTypeEnum implements GeneralEnum<String> {
	Physique_FCK1("Physique_FCK1","妇产科学体格检查评分表一","Physique"),
	Physique_FCK2("Physique_FCK2","妇产科学体格检查评分表二","Physique"),
	Physique_FCK3("Physique_FCK3","妇产科学体格检查评分表三","Physique"),
	Physique_FBTJ("Physique_FBTJ","体格检查评分表（腹部体检）","Physique"),
	Physique_JZXTJ("Physique_JZXTJ","体格检查评分表（甲状腺体检）","Physique"),
	Physique_SJXTTJ("Physique_SJXTTJ","体格检查评分表（神经系统体检）","Physique"),
	Physique_XZTJ("Physique_XZTJ","体格检查评分表（心脏体检）","Physique"),
	Physique_XBTJ("Physique_XBTJ","体格检查评分表（胸部体检）","Physique"),
	Clinical_CRQG("Clinical_CRQG","成人气管插管穿刺术操作技能考考核评分表","Clinical"),
	Clinical_FCKX("Clinical_FCKX","妇产科学专业手术技能操作评分表","Clinical"),
	Clinical_FQCC("Clinical_FQCC","腹腔穿刺术操作技能考核评分表","Clinical"),
	Clinical_GDMCC("Clinical_GDMCC","股动脉穿刺操作技能考考核评分表","Clinical"),
	Clinical_GSCC("Clinical_GSCC","骨髓穿刺术操作技能考核评分表","Clinical"),
	Clinical_SJMCC("Clinical_SJMCC","深静脉穿刺术操作技能考考核评分表","Clinical"),
	Clinical_SZCC("Clinical_SZCC","肾脏穿刺活检术操作技能考考核评分表","Clinical"),
	Clinical_DSXFFS("Clinical_DSXFFS","徒手心肺复苏操作技能考核评分表","Clinical"),
	Clinical_WKXSS("Clinical_WKXSS","外科学专业手术技能操作评分表","Clinical"),
	Clinical_WKXWJ("Clinical_WKXWJ","外科学专业无菌操作（换药拆线）技能评分表","Clinical"),
	Clinical_XQCC("Clinical_XQCC","胸腔穿刺术操作技能考核评分表","Clinical"),
	Clinical_YZCC("Clinical_YZCC","腰椎穿刺术操作技能考核评分表","Clinical"),
	MedicalHistory("MedicalHistory","病史采集评分表","MedicalHistory"),
	CaseAnalysis("CaseAnalysis","病例分析评分表","CaseAnalysis"),
	;

	private final String id;
	private final String name;
	private final String type;

	RecSkillTypeEnum(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public static String getNameById(String id) {
		return EnumUtil.getById(id, RecSkillTypeEnum.class).getName();
	}
}
