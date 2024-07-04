<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"action":{
			"save":"保存"
		},
		"tutorInfo": [
			{"inputId": "suffUrl", "label": "照片url前缀", "value": ${pdfn:toJsonString(sysCfgMap['upload_base_url'])} ,"readonly":true, "isHidden": true},
			{"inputId": "doctorFlow", "label": "导师flow", "value": ${pdfn:toJsonString(tutor.doctorFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "headUrl", "label": "导师照片", "value": ${pdfn:toJsonString(tutor.headUrl)} ,"readonly":true,"inputType":"pic"},
			{"inputId": "doctorTypeId", "label": "导师类型", "value": ${pdfn:toJsonString(tutor.doctorTypeId)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},{"optionId": "xsxbd","optionDesc":"学术型博导"},{"optionId": "xsxsd","optionDesc":"学术型硕导"},{"optionId": "zyxbd","optionDesc":"专业型博导"},{"optionId": "zyxsd","optionDesc":"专业型硕导"}]},
			{"inputId": "pydwOrgFlow", "label": "二级机构id", "value": ${pdfn:toJsonString(tutor.pydwOrgFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "pydwOrgName", "label": "二级机构", "value": ${pdfn:toJsonString(tutor.pydwOrgName)} ,"readonly":true},
			{"inputId": "doctorName", "label": "导师姓名", "value": ${pdfn:toJsonString(tutor.doctorName)} ,"readonly":false},
			{"inputId": "spellName", "label": "姓名拼音", "value": ${pdfn:toJsonString(tutor.spellName)} ,"readonly":false},
			{"inputId": "birthDay", "label": "出生年月", "value": ${pdfn:toJsonString(tutor.birthDay)} ,"readonly":false},
			{"inputId": "mobilePhone", "label": "手机", "value": ${pdfn:toJsonString(tutor.mobilePhone)} ,"readonly":false},
			{"inputId": "workPhone", "label": "办公电话", "value": ${pdfn:toJsonString(tutor.workPhone)} ,"readonly":false},
			{"inputId": "emailNo", "label": "电子邮箱", "value": ${pdfn:toJsonString(tutor.emailNo)} ,"readonly":false},
			{"inputId": "workUnit", "label": "工作单位", "value": ${pdfn:toJsonString(tutor.workUnit)} ,"readonly":false},
			{"inputId": "technicalTitleName", "label": "技术职称", "value": ${pdfn:toJsonString(tutor.technicalTitleName)} ,"readonly":false},
			{"inputId": "academicActivities", "label": "学术任职", "value": ${pdfn:toJsonString(tutor.academicActivities)} ,"readonly":false},
			{"inputId": "researchDirection", "label": "研究方向", "value": ${pdfn:toJsonString(tutor.researchDirection)} ,"readonly":false},
			{"inputId": "academicMonographs", "label": "学术专著", "value": ${pdfn:toJsonString(tutor.academicMonographs)} ,"readonly":false},
			{"inputId": "awardSituation", "label": "个人获奖情况", "value": ${pdfn:toJsonString(tutor.awardSituation)} ,"readonly":false},
			{"inputId": "researchDescribe", "label": "本人从事的主要研究方向及其特点和意义、开展新医疗、新技术等情况、国内所处的学术地位", "value": ${pdfn:toJsonString(tutor.researchDescribe)} ,"readonly":false}
		]
    </c:if>
}