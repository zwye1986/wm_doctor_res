<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"action":{
			"save":"保存"
		},
		"roleId":${pdfn:toJsonString(roleId)},
		"args":[
			{"inputId": "eduUser.userFlow", "label": "学籍用户flow", "value": ${pdfn:toJsonString(eduUser.userFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "sysUser.userFlow", "label": "系统用户flow", "value": ${pdfn:toJsonString(eduUser.userFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "sysUser.userName", "label": "系统用户flow", "value": ${pdfn:toJsonString(sysUser.userName)} ,"readonly":true, "isHidden": true},
			{"inputId": "resDoctor.doctorFlow", "label": "医师用户flow", "value": ${pdfn:toJsonString(eduUser.userFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.joinTime", "label": "转入年月日", "value": ${pdfn:toJsonString(extInfoForm.joinTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.ssyjsgain", "label": "是否获得硕士研究生学历", "value": ${pdfn:toJsonString(extInfoForm.ssyjsgain)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterGraduateTime", "label": "硕士研究生毕业年月", "value": ${pdfn:toJsonString(extInfoForm.masterGraduateTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterDiplomaCode", "label": "硕士研究生毕业证编号", "value": ${pdfn:toJsonString(extInfoForm.masterDiplomaCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterUnitName", "label": "硕士研究生毕业单位名称", "value": ${pdfn:toJsonString(extInfoForm.masterUnitName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterGraduateMajor", "label": "硕士研究生毕业专业名称", "value": ${pdfn:toJsonString(extInfoForm.masterGraduateMajor)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.ssxwgain", "label": "是否获得硕士学位", "value": ${pdfn:toJsonString(extInfoForm.ssxwgain)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterStudyForm", "label": "获得硕士学位的学习形式", "value": ${pdfn:toJsonString(extInfoForm.masterStudyForm)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterAwardDegreeTime", "label": "获得硕士学位年月", "value": ${pdfn:toJsonString(extInfoForm.masterAwardDegreeTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterDegreeCertCode", "label": "硕士学位证书编号", "value": ${pdfn:toJsonString(extInfoForm.masterDegreeCertCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterAwardDegreeOrg", "label": "获得硕士学位单位名称", "value": ${pdfn:toJsonString(extInfoForm.masterAwardDegreeOrg)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.masterSubject", "label": "获得硕士学位学科门类", "value": ${pdfn:toJsonString(extInfoForm.masterSubject)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.gotMasterCertSpe", "label": "获得硕士学位专业", "value": ${pdfn:toJsonString(extInfoForm.gotMasterCertSpe)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.awardSubjectCategory", "label": "授予学科门类", "value": ${pdfn:toJsonString(extInfoForm.awardSubjectCategory)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.recipients", "label": "收件联系人", "value": ${pdfn:toJsonString(extInfoForm.recipients)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.recipientsPhone", "label": "收件人电话", "value": ${pdfn:toJsonString(extInfoForm.recipientsPhone)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.archivesPlaceCode", "label": "档案去向邮政编码", "value": ${pdfn:toJsonString(extInfoForm.archivesPlaceCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.archivesEmsNum", "label": "Ems单号", "value": ${pdfn:toJsonString(extInfoForm.archivesEmsNum)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.archivesEmsAddressId", "label": "档案去向邮寄地址id", "value": ${pdfn:toJsonString(extInfoForm.archivesEmsAddressId)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.archivesEmsAddressName", "label": "档案去向邮寄地址", "value": ${pdfn:toJsonString(extInfoForm.archivesEmsAddressName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.mailTime", "label": "寄出时间", "value": ${pdfn:toJsonString(extInfoForm.mailTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.archivesUnit", "label": "档案去向单位", "value": ${pdfn:toJsonString(extInfoForm.archivesUnit)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.studentPhone", "label": "学生电话", "value": ${pdfn:toJsonString(extInfoForm.studentPhone)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.studentCode", "label": "学生学号", "value": ${pdfn:toJsonString(extInfoForm.studentCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.studentName", "label": "学生姓名", "value": ${pdfn:toJsonString(extInfoForm.studentName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.joinMedicalYear", "label": "本次参保起始年份", "value": ${pdfn:toJsonString(extInfoForm.joinMedicalYear)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.enterSchoolYear", "label": "入学年份", "value": ${pdfn:toJsonString(extInfoForm.enterSchoolYear)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.isNewStudent", "label": "是否新生", "value": ${pdfn:toJsonString(extInfoForm.isNewStudent)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.college", "label": "学院", "value": ${pdfn:toJsonString(extInfoForm.college)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.countryAreaId", "label": "国家地区id", "value": ${pdfn:toJsonString(extInfoForm.countryAreaId)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.countryAreaName", "label": "国家地区", "value": ${pdfn:toJsonString(extInfoForm.countryAreaName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.whetherPracticeCert", "label": "有无执业医师资格证", "value": ${pdfn:toJsonString(extInfoForm.whetherPracticeCert)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.practiceCertCode", "label": "执业医师资格证编号", "value": ${pdfn:toJsonString(extInfoForm.practiceCertCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.practiceCertUrl", "label": "执业医师资格证上传", "value": ${pdfn:toJsonString(extInfoForm.practiceCertUrl)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.whetherBuyMedicalCard", "label": "是否购买医保", "value": ${pdfn:toJsonString(extInfoForm.whetherBuyMedicalCard)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.unitOfDoctor", "label": "导师所在单位", "value": ${pdfn:toJsonString(extInfoForm.unitOfDoctor)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dormitoryNo", "label": "宿舍楼号", "value": ${pdfn:toJsonString(extInfoForm.dormitoryNo)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dormitoryFloor", "label": "宿舍楼层", "value": ${pdfn:toJsonString(extInfoForm.dormitoryFloor)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dormitoryRoomNumber", "label": "宿舍房号", "value": ${pdfn:toJsonString(extInfoForm.dormitoryRoomNumber)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.xjRegDate", "label": "学籍注册时间", "value": ${pdfn:toJsonString(extInfoForm.xjRegDate)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.reportedDate", "label": "报到时间", "value": ${pdfn:toJsonString(extInfoForm.reportedDate)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.outOfSchoolDate", "label": "休学日期", "value": ${pdfn:toJsonString(extInfoForm.outOfSchoolDate)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.backToSchoolDate", "label": "复学日期", "value": ${pdfn:toJsonString(extInfoForm.backToSchoolDate)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dropOutSchoolDate", "label": "退学时间", "value": ${pdfn:toJsonString(extInfoForm.dropOutSchoolDate)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.recruitType", "label": "招录方式", "value": ${pdfn:toJsonString(extInfoForm.recruitType)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.foreignLanguageName", "label": "外国语名称", "value": ${pdfn:toJsonString(extInfoForm.foreignLanguageName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.foreignLanguageScore", "label": "外国语成绩", "value": ${pdfn:toJsonString(extInfoForm.foreignLanguageScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.politicalTheoryName", "label": "政治理论", "value": ${pdfn:toJsonString(extInfoForm.politicalTheoryName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.politicalTheoryScore", "label": "政治理论成绩", "value": ${pdfn:toJsonString(extInfoForm.politicalTheoryScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.firstSubjectName", "label": "业务课一", "value": ${pdfn:toJsonString(extInfoForm.firstSubjectName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.firstSubjectScore", "label": "业务课一成绩", "value": ${pdfn:toJsonString(extInfoForm.firstSubjectScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.secondSubjectName", "label": "业务课二", "value": ${pdfn:toJsonString(extInfoForm.secondSubjectName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.secondSubjectScore", "label": "业务课二成绩", "value": ${pdfn:toJsonString(extInfoForm.secondSubjectScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.firstAddExamName", "label": "加试科一", "value": ${pdfn:toJsonString(extInfoForm.firstAddExamName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.firstAddExamScore", "label": "加试科一成绩", "value": ${pdfn:toJsonString(extInfoForm.firstAddExamScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.secondAddExamName", "label": "加试科二", "value": ${pdfn:toJsonString(extInfoForm.secondAddExamName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.secondAddExamScore", "label": "加试科二成绩", "value": ${pdfn:toJsonString(extInfoForm.secondAddExamScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.reexamineScore", "label": "复试成绩", "value": ${pdfn:toJsonString(extInfoForm.reexamineScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.totalPointsScore", "label": "总分", "value": ${pdfn:toJsonString(extInfoForm.totalPointsScore)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.joinOrgTime", "label": "入党（团）时间", "value": ${pdfn:toJsonString(extInfoForm.joinOrgTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.isRelationInto", "label": "党团关系是否转入", "value": ${pdfn:toJsonString(extInfoForm.isRelationInto)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.hkInSchool", "label": "户口是否需要迁入我校", "value": ${pdfn:toJsonString(extInfoForm.hkInSchool)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.hkChangeNo", "label": "户口迁移证编号", "value": ${pdfn:toJsonString(extInfoForm.hkChangeNo)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.oldOrgName", "label": "原学习或工作单位", "value": ${pdfn:toJsonString(extInfoForm.oldOrgName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.oldFileLocationOrg", "label": "原档案所在单位", "value": ${pdfn:toJsonString(extInfoForm.oldFileLocationOrg)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.oldFileLocationOrgCode", "label": "原档案所在单位邮编", "value": ${pdfn:toJsonString(extInfoForm.oldFileLocationOrgCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.nowResideAddress", "label": "现家庭住址", "value": ${pdfn:toJsonString(extInfoForm.nowResideAddress)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.postCode", "label": "邮政编码", "value": ${pdfn:toJsonString(extInfoForm.postCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.linkMan", "label": "紧急联系人姓名", "value": ${pdfn:toJsonString(extInfoForm.linkMan)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.telephone", "label": "紧急联系人电话", "value": ${pdfn:toJsonString(extInfoForm.telephone)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.isDoctorQuaCert", "label": "是否有医师资格证", "value": ${pdfn:toJsonString(extInfoForm.isDoctorQuaCert)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.codeDoctorQuaCert", "label": "资格证编号", "value": ${pdfn:toJsonString(extInfoForm.codeDoctorQuaCert)} ,"readonly":true},
			{"inputId": "eduUserExtInfoForm.isMedicalPractitioner", "label": "是否有执业医师执照", "value": ${pdfn:toJsonString(extInfoForm.isMedicalPractitioner)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.codeMedicalPractitioner", "label": "执照编号", "value": ${pdfn:toJsonString(extInfoForm.codeMedicalPractitioner)} ,"readonly":true},
			{"inputId": "eduUserExtInfoForm.isStay", "label": "是否住宿", "value": ${pdfn:toJsonString(extInfoForm.isStay)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.roomNumStay", "label": "宿舍号", "value": ${pdfn:toJsonString(extInfoForm.roomNumStay)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.height", "label": "身高", "value": ${pdfn:toJsonString(extInfoForm.height)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.bloodType", "label": "血型", "value": ${pdfn:toJsonString(extInfoForm.bloodType)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.ybCardNo", "label": "医保卡号", "value": ${pdfn:toJsonString(extInfoForm.ybCardNo)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.mandarinLevel", "label": "普通话水平", "value": ${pdfn:toJsonString(extInfoForm.mandarinLevel)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.foreignLanguageLevel", "label": "外语水平", "value": ${pdfn:toJsonString(extInfoForm.foreignLanguageLevel)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.computerLevel", "label": "计算机水平", "value": ${pdfn:toJsonString(extInfoForm.computerLevel)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.schoolSystem", "label": "学制", "value": ${pdfn:toJsonString(extInfoForm.schoolSystem)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.studentSourceArea", "label": "生源省市", "value": ${pdfn:toJsonString(extInfoForm.studentSourceArea)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dormitoryAdd", "label": "宿舍地址", "value": ${pdfn:toJsonString(extInfoForm.dormitoryAdd)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.homePhone", "label": "家庭电话", "value": ${pdfn:toJsonString(extInfoForm.homePhone)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.mateName", "label": "配偶姓名", "value": ${pdfn:toJsonString(extInfoForm.mateName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.mateIdNo", "label": "配偶身份证", "value": ${pdfn:toJsonString(extInfoForm.mateIdNo)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.spouseUnit", "label": "配偶工作单位", "value": ${pdfn:toJsonString(extInfoForm.spouseUnit)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.speciality", "label": "特长", "value": ${pdfn:toJsonString(extInfoForm.speciality)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.mainResume", "label": "本人主要简历", "value": ${pdfn:toJsonString(extInfoForm.mainResume)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.reAndPuStatusContent", "label": "入学前奖惩情况", "value": ${pdfn:toJsonString(extInfoForm.reAndPuStatusContent)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.scientificTogether", "label": "曾担任过合作教学工作和进行何种科研工作", "value": ${pdfn:toJsonString(extInfoForm.scientificTogether)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.remark", "label": "备注", "value": ${pdfn:toJsonString(extInfoForm.remark)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.accountNum", "label": "缴费账号", "value": ${pdfn:toJsonString(extInfoForm.accountNum)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.tuitionFee", "label": "学费总额", "value": ${pdfn:toJsonString(extInfoForm.tuitionFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paytuitionFee", "label": "已缴纳学费", "value": ${pdfn:toJsonString(extInfoForm.paytuitionFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.arrearageFee", "label": "欠缴纳学费", "value": ${pdfn:toJsonString(extInfoForm.arrearageFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.dormitoryFee", "label": "住宿费", "value": ${pdfn:toJsonString(extInfoForm.dormitoryFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.payDormitoryFee", "label": "已缴纳住宿费", "value": ${pdfn:toJsonString(extInfoForm.payDormitoryFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.arrearageDormitoryFee", "label": "欠缴纳住宿费", "value": ${pdfn:toJsonString(extInfoForm.arrearageDormitoryFee)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paidFee", "label": "是否缴清费用", "value": ${pdfn:toJsonString(extInfoForm.paidFee)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paidFeeNo", "label": "缴费编号", "value": ${pdfn:toJsonString(extInfoForm.paidFeeNo)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.firstEducationContentId", "label": "第一学历", "value": ${pdfn:toJsonString(extInfoForm.firstEducationContentId)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.preGraduation", "label": "前置学历", "value": ${pdfn:toJsonString(extInfoForm.preGraduation)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.bkgain", "label": "是否获得本科学历", "value": ${pdfn:toJsonString(extInfoForm.bkgain)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underStudyForm", "label": "获得本科学历的学习形式", "value": ${pdfn:toJsonString(extInfoForm.underStudyForm)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underGraduateTime", "label": "本科毕业年月", "value": ${pdfn:toJsonString(extInfoForm.underGraduateTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underDiplomaCode", "label": "本科毕业证书编号", "value": ${pdfn:toJsonString(extInfoForm.underDiplomaCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underGraduateOrgName", "label": "本科毕业单位名称", "value": ${pdfn:toJsonString(extInfoForm.underGraduateOrgName)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underGraduateMajor", "label": "本科毕业专业名称", "value": ${pdfn:toJsonString(extInfoForm.underGraduateMajor)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.xsgain", "label": "是否获得学士学位", "value": ${pdfn:toJsonString(extInfoForm.xsgain)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underAwardDegreeTime", "label": "获得学士学位年月", "value": ${pdfn:toJsonString(extInfoForm.underAwardDegreeTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underDegreeCertCode", "label": "学士学位证书编号", "value": ${pdfn:toJsonString(extInfoForm.underDegreeCertCode)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underAwardDegreeOrg", "label": "获学士学位单位名称", "value": ${pdfn:toJsonString(extInfoForm.underAwardDegreeOrg)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.underMajor", "label": "获学士学位专业名称", "value": ${pdfn:toJsonString(extInfoForm.underMajor)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.gotBachelorCertSubject", "label": "获得学士学位学科门类", "value": ${pdfn:toJsonString(extInfoForm.gotBachelorCertSubject)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.code", "label": "最后学位证编号", "value": ${pdfn:toJsonString(extInfoForm.code)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.planGraduateTime", "label": "预毕业时间", "value": ${pdfn:toJsonString(extInfoForm.planGraduateTime)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.graduateFlag", "label": "是否毕业", "value": ${pdfn:toJsonString(extInfoForm.graduateFlag)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paperTitle", "label": "论文题目", "value": ${pdfn:toJsonString(extInfoForm.paperTitle)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paperSource", "label": "论文选题来源", "value": ${pdfn:toJsonString(extInfoForm.paperSource)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paperKey", "label": "论文关键词", "value": ${pdfn:toJsonString(extInfoForm.paperKey)} ,"readonly":false, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.paperType", "label": "论文类型", "value": ${pdfn:toJsonString(extInfoForm.paperType)} ,"readonly":false, "isHidden": true}
		],
		"dispatchInfoInputList": [
			{"inputId": "eduUserExtInfoForm.sendUnit", "label": "具体派遣单位", "value": ${pdfn:toJsonString(extInfoForm.sendUnit)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.reportAddressId", "label": "报到地址id", "value": ${pdfn:toJsonString(extInfoForm.reportAddressId)} ,"readonly":true},
			{"inputId": "eduUserExtInfoForm.reportAddressName", "label": "报到地址", "value": ${pdfn:toJsonString(extInfoForm.reportAddressName)} ,"readonly":true},
			{"inputId": "eduUserExtInfoForm.sendNature", "label": "派遣性质", "value": ${pdfn:toJsonString(extInfoForm.sendNature)} ,"readonly":false, "inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"升学深造"},{"optionId": "2","optionDesc":"正常派遣"},{"optionId": "3","optionDesc":"申请暂缓就业"},{"optionId": "4","optionDesc":"出国"},{"optionId": "5","optionDesc":"不纳入就业方案"}]},
			{"inputId": "eduUserExtInfoForm.sendTime", "label": "派遣时间", "value": ${pdfn:toJsonString(extInfoForm.sendTime)} ,"readonly":false,"inputType":"date"},
			{"inputId": "eduUserExtInfoForm.sentMemo", "label": "备注", "value": ${pdfn:toJsonString(extInfoForm.sentMemo)} ,"readonly":false}
		],
		"employInfoInputList": [
			{"inputId": "eduUserExtInfoForm.graduationWhereabouts", "label": "毕业去向", "value": ${pdfn:toJsonString(extInfoForm.graduationWhereabouts)} ,"readonly":false, "inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"签就业协议形式就业"},{"optionId": "2","optionDesc":"签劳动合同形式就业"},{"optionId": "3","optionDesc":"其他录用形式就业"},{"optionId": "4","optionDesc":"其他情况"}]},
			{"inputId": "eduUserExtInfoForm.unitCode", "label": "单位组织机构代码", "value": ${pdfn:toJsonString(extInfoForm.unitCode)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.workUnit", "label": "具体就业单位", "value": ${pdfn:toJsonString(extInfoForm.workUnit)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.workType", "label": "单位类型", "value": ${pdfn:toJsonString(extInfoForm.workType)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"地市（州、盟、省辖市）厅局属的医疗卫生单位"}]},
			{"inputId": "eduUserExtInfoForm.workOfIndustry", "label": "单位所属行业", "value": ${pdfn:toJsonString(extInfoForm.workOfIndustry)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"卫生"}]},
			{"inputId": "eduUserExtInfoForm.workOfAreaId", "label": "单位所属地区id", "value": ${pdfn:toJsonString(extInfoForm.workOfAreaId)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.workOfAreaName", "label": "单位所属地区", "value": ${pdfn:toJsonString(extInfoForm.workOfAreaName)} ,"readonly":true},
			{"inputId": "eduUserExtInfoForm.workLinkMan", "label": "单位联系人", "value": ${pdfn:toJsonString(extInfoForm.workLinkMan)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.occupationType", "label": "职业类型", "value": ${pdfn:toJsonString(extInfoForm.occupationType)} ,"readonly":false, "inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"西医医师"},{"optionId": "2","optionDesc":"中医医师"},{"optionId": "3","optionDesc":"中西医结合医师"},{"optionId": "4","optionDesc":"护理人员"},{"optionId": "5","optionDesc":"管理科学研究人员"}]},
			{"inputId": "eduUserExtInfoForm.workLinkPhone", "label": "单位联系电话", "value": ${pdfn:toJsonString(extInfoForm.workLinkPhone)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.workPay", "label": "薪酬", "value": ${pdfn:toJsonString(extInfoForm.workPay)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.userAim", "label": "使用意图", "value": ${pdfn:toJsonString(extInfoForm.userAim)} ,"readonly":false, "inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "1","optionDesc":"行政"},{"optionId": "2","optionDesc":"读书"},{"optionId": "3","optionDesc":"服役"},{"optionId": "4","optionDesc":"待业"},{"optionId": "5","optionDesc":"出国"},{"optionId": "6","optionDesc":"其他"}]},
			{"inputId": "eduUserExtInfoForm.signTime", "label": "签约时间", "value": ${pdfn:toJsonString(extInfoForm.signTime)} ,"readonly":false, "inputType":"date"},
			{"inputId": "eduUserExtInfoForm.majorCounterpart", "label": "是否专业对口", "value": ${pdfn:toJsonString(extInfoForm.majorCounterpart)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"是"},{"optionId": "N","optionDesc":"否"}]},
			{"inputId": "eduUserExtInfoForm.workEmail", "label": "用人单位邮箱", "value": ${pdfn:toJsonString(extInfoForm.workEmail)} ,"readonly":false},
			{"inputId": "eduUserExtInfoForm.employmentLevel", "label": "是否就业困难", "value": ${pdfn:toJsonString(extInfoForm.employmentLevel)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"是"},{"optionId": "N","optionDesc":"否"}]},
			{"inputId": "eduUserExtInfoForm.employAgreementUrl", "label": "就业协议书上传", "value": ${pdfn:toJsonString(extInfoForm.employAgreementUrl)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.employSign", "label": "暂缓就业表登记", "value": ${pdfn:toJsonString(extInfoForm.employSign)} ,"readonly":false}
		],
		"dossierInfoInputList": [
			{"inputId": "eduUser.receiveFlag", "label": "档案接收状态", "value": ${pdfn:toJsonString(eduUser.receiveFlag)} ,"readonly":false, "inputType": "select","options":[{"optionId": "N","optionDesc": "未接收"},{"optionId": "Y","optionDesc":"已接收"}]},
			{"inputId": "eduUserExtInfoForm.partyMembershipTime", "label": "（党员）入党时间", "value": ${pdfn:toJsonString(extInfoForm.partyMembershipTime)} ,"readonly":false, "inputType": "date"},
			{"inputId": "eduUserExtInfoForm.partyMemberFormalTime", "label": "（党员）转正时间", "value": ${pdfn:toJsonString(extInfoForm.partyMemberFormalTime)} ,"readonly":false, "inputType": "date"},
			{"inputId": "eduUserExtInfoForm.emsUrl", "label": "EMS截图上传", "value": ${pdfn:toJsonString(extInfoForm.emsUrl)} ,"readonly":true, "isHidden": true},
			{"inputId": "eduUserExtInfoForm.highSchool", "label": "高中", "value": ${pdfn:toJsonString(extInfoForm.highSchool)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"是"},{"optionId": "N","optionDesc":"否"}]},
			{"inputId": "eduUserExtInfoForm.university", "label": "大学", "value": ${pdfn:toJsonString(extInfoForm.university)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"是"},{"optionId": "N","optionDesc":"否"}]},
			{"inputId": "eduUserExtInfoForm.masterAdmissionSign", "label": "录取登记表", "value": ${pdfn:toJsonString(extInfoForm.masterAdmissionSign)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.masterScienceDegree", "label": "科学学位（审批表）", "value": ${pdfn:toJsonString(extInfoForm.masterScienceDegree)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.masterGraduationSign", "label": "毕业登记表", "value": ${pdfn:toJsonString(extInfoForm.masterGraduationSign)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.masterClinicalDegree", "label": "临床学位（答辩表）", "value": ${pdfn:toJsonString(extInfoForm.masterClinicalDegree)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.masterSchoolReport", "label": "成绩单", "value": ${pdfn:toJsonString(extInfoForm.masterSchoolReport)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.doctorAdmissionSign", "label": "录取登记表", "value": ${pdfn:toJsonString(extInfoForm.doctorAdmissionSign)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.doctorScienceDegree", "label": "科学学位（审批表）", "value": ${pdfn:toJsonString(extInfoForm.doctorScienceDegree)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.doctorGraduationSign", "label": "毕业登记表", "value": ${pdfn:toJsonString(extInfoForm.doctorGraduationSign)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.doctorClinicalDegree", "label": "临床学位（答辩表）", "value": ${pdfn:toJsonString(extInfoForm.doctorClinicalDegree)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.doctorSchoolReport", "label": "成绩单", "value": ${pdfn:toJsonString(extInfoForm.doctorSchoolReport)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]},
			{"inputId": "eduUserExtInfoForm.expertChitLetter", "label": "专家推荐信2封", "value": ${pdfn:toJsonString(extInfoForm.expertChitLetter)} ,"readonly":false, "inputType":"select","options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "Y","optionDesc":"有"},{"optionId": "N","optionDesc":"无"}]}
		]
    </c:if>
}