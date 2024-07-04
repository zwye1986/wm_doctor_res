<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"inputs":[
		{
			"inputId":"basicCheckBranch",
			"inputType":"text",
			"label":"坚持四项基本原则，遵纪守法(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"disciplineCheckBranch",
			"inputType":"text",
			"label":"组织纪律性(10分)",
			"placeholder": "0~10",
			"required":true,
			"verify": {
				"max": "10",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"uniteCheckBranch",
			"inputType":"text",
			"label":"团结协作(7分)",
			"placeholder": "0~7",
			"required":true,
			"verify": {
				"max": "7",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"activityCheckBranch",
			"inputType":"text",
			"label":"政治活动和社会活动(8分)",
			"placeholder": "0~8",
			"required":true,
			"verify": {
				"max": "8",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"attendanceCheckBranch",
			"inputType":"text",
			"label":"履行本岗位职责,出勤率(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"attitudeCheckBranch",
			"inputType":"text",
			"label":"职业道德和服务态度(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"technologyCheckBranch",
			"inputType":"text",
			"label":"技术操作规程(10分)",
			"placeholder": "0~10",
			"required":true,
			"verify": {
				"max": "10",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"studyCheckBranch",
			"inputType":"text",
			"label":"业务活动和学习(5分)",
			"placeholder": "0~5",
			"required":true,
			"verify": {
				"max": "5",
				 "type": "int"
			},
			"readonly":${isAudit}
		},
		{
			"inputId":"checkBranchThetotalscore",
			"inputType":"text",
			"label":"总分(100分)",
			"readonly":${isAudit}
		}
		{
			"inputId":"basicSelfratingBranch",
			"inputType":"text",
			"label":"坚持四项基本原则，遵纪守法(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"disciplineSelfratingBranch",
			"inputType":"text",
			"label":"组织纪律性(10分)",
			"placeholder": "0~10",
			"required":true,
			"verify": {
				"max": "10",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"uniteSelfratingBranch",
			"inputType":"text",
			"label":"团结协作(7分)",
			"placeholder": "0~7",
			"required":true,
			"verify": {
				"max": "7",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"activitySelfratingBranch",
			"inputType":"text",
			"label":"政治活动和社会活动(8分)",
			"placeholder": "0~8",
			"required":true,
			"verify": {
				"max": "8",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"attendanceSelfratingBranch",
			"inputType":"text",
			"label":"履行本岗位职责,出勤率(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"attitudeSelfratingBranch",
			"inputType":"text",
			"label":"职业道德和服务态度(20分)",
			"placeholder": "0~20",
			"required":true,
			"verify": {
				"max": "20",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"technologySelfratingBranch",
			"inputType":"text",
			"label":"技术操作规程(10分)",
			"placeholder": "0~10",
			"required":true,
			"verify": {
				"max": "10",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"studySelfratingBranch",
			"inputType":"text",
			"label":"业务活动和学习(5分)",
			"placeholder": "0~5",
			"required":true,
			"verify": {
				"max": "5",
				 "type": "int"
			}
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"selfratingBranchThetotalscore",
			"inputType":"text",
			"label":"总分(100分)",
			"readonly":true
		}
			,
			{
				"inputId": "accident",
				"label": "医疗事故",
				"required":true,
				"inputType": "select",
				"options": [
					{
						"optionId": "有",
						"optionDesc": "有"
					},
					{
						"optionId": "无",
						"optionDesc": "无"
					}
				],
				"readonly":${isAudit}
			},
			{
				"inputId": "error",
				"label": "医疗差错",
				"required":true,
				"inputType": "select",
				"options": [
					{
						"optionId": "有",
						"optionDesc": "有"
					},
					{
						"optionId": "无",
						"optionDesc": "无"
					}
				],
				"readonly":${isAudit}
			},
			{
				"inputId": "wrong",
				"label": "工作错误",
				"required":true,
				"inputType": "select",
				"options": [
					{
						"optionId": "有",
						"optionDesc": "有"
					},
					{
						"optionId": "无",
						"optionDesc": "无"
					}
				],
				"readonly":${isAudit}
			}
			,
			{
				"inputId":"isAgree",
				"inputType":"select",
				"label":"同意出科",
				"required":true,
				"readonly":true,
				"options": [
					{
						"optionId": "Y",
						"optionDesc": "是"
					},
					{
						"optionId": "N",
						"optionDesc": "否"
					}
				]
			}
	]
	,
	"values":[
		{
			"inputId":"basicCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.basicCheckBranch)}
		},
		{
			"inputId":"disciplineCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.disciplineCheckBranch)}
		},
		{
			"inputId":"uniteCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.uniteCheckBranch)}
		},
		{
			"inputId":"activityCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.activityCheckBranch)}
		},
		{
			"inputId":"attendanceCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.attendanceCheckBranch)}
		},
		{
			"inputId":"technologyCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.technologyCheckBranch)}
		},
		{
			"inputId":"studyCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.studyCheckBranch)}
		},
		{
			"inputId":"attitudeCheckBranch",
			"value":${pdfn:toJsonString(formDataMap.attitudeCheckBranch)}
		},
		{
			"inputId":"checkBranchThetotalscore",
			"value":${pdfn:toJsonString(formDataMap.checkBranchThetotalscore)}
		},
		{
			"inputId":"basicSelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.basicSelfratingBranch)}
		},
		{
			"inputId":"disciplineSelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.disciplineSelfratingBranch)}
		},
		{
			"inputId":"uniteSelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.uniteSelfratingBranch)}
		},
		{
			"inputId":"activitySelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.activitySelfratingBranch)}
		},
		{
			"inputId":"attendanceSelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.attendanceSelfratingBranch)}
		},
		{
			"inputId":"attitudeSelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.attitudeSelfratingBranch)}
		},
		{
			"inputId":"technologySelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.technologySelfratingBranch)}
		},
		{
			"inputId":"studySelfratingBranch",
			"value":${pdfn:toJsonString(formDataMap.studySelfratingBranch)}
		},
		{
			"inputId":"selfratingBranchThetotalscore",
			"value":${pdfn:toJsonString(formDataMap.selfratingBranchThetotalscore)}
		}
			,
			{
				"inputId": "accident",
				"value":${pdfn:toJsonString(formDataMap.accident)}
			},
			{
				"inputId": "error",
				"value":${pdfn:toJsonString(formDataMap.error)}
			},
			{
				"inputId": "wrong",
				"value":${pdfn:toJsonString(formDataMap.wrong)}
			}
			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree)}
			}

	]
