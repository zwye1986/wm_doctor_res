<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"inputs":[
		{
			"inputId":"doctorName",
			"inputType":"text",
			"label":"姓名",
			"readonly":true
		},
		{
			"inputId":"schDeptName",
			"inputType":"text",
			"label":"科室",
			"readonly":true
		},
		{
			"inputId":"trainingSpeName",
			"inputType":"text",
			"label":"专业",
			"readonly":true
		},
		{
			"inputId":"orgName",
			"inputType":"text",
			"label":"医院",
			"readonly":true
		},
		{
			"inputId":"inHosDate",
			"inputType":"text",
			"label":"入培时间",
			"readonly":true
		},
		{
			"inputId":"schStartDate",
			"inputType":"text",
			"label":"轮转开始时间",
			"readonly":true
		},
		{
			"inputId":"schEndDate",
			"inputType":"text",
			"label":"轮转结束时间",
			"readonly":true
		},
		{
			"inputId":"schMonth",
			"inputType":"text",
			"label":"轮转时间（月）",
			"readonly":true
		},
		{
			"inputId":"manqin",
			"inputType":"text",
			"label":"满勤（天）",
			"required":true,
			"verify": {
				"max": "9999",
				"type": "int"
			}
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"queqin",
			"inputType":"text",
			"label":"缺勤（天）",
			"required":true,
			"verify": {
				"max": "9999",
				"type": "int"
			}
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"personalSummary",
			"inputType":"textarea",
			"label":"个人总结",
			"required":true
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"doctorSiginName",
			"inputType":"text",
			"label":"住院医师签名",
			"required":true,
			"readonly":true
		},
		{
			"inputId":"doctorSiginDate",
			"inputType":"text",
			"label":"签名日期",
			"required":true,
			"readonly":true
		}
		<c:if test="${!empty formDataMap.deptAppraise}">
			,
			{
				"inputId":"deptAppraise",
				"inputType":"textarea",
				"label":"带教意见",
				"required":true	,
				"readonly":true
			},
			{
				"inputId":"teacherSiginName",
				"inputType":"text",
				"label":"带教老师签名",
				"required":true,
			    "readonly":true
			},
			{
				"inputId":"teacherSiginDate",
				"inputType":"text",
				"label":"带教老师签名日期",
				"required":true,
			    "readonly":true
			}
		</c:if>
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
			,
			{
				"inputId":"deptHeadAutograth",
				"inputType":"textarea",
				"label":"教学主任意见",
				"required":true,
				"readonly":true
			},
			{
				"inputId":"headSiginName",
				"inputType":"text",
				"label":"教学主任签名",
				"required":true,
				"readonly":true
			},
			{
				"inputId":"headSiginDate",
				"inputType":"text",
				"label":"教学主任签名日期",
				"required":true,
				"readonly":true
			}
		</c:if>
	]
	,
	"values":[
		{
			"inputId":"doctorName",
			"value":${pdfn:toJsonString(empty formDataMap.doctorName?result.doctorName:formDataMap.doctorName)}
		},
		{
			"inputId":"schDeptName",
			"value":${pdfn:toJsonString(empty formDataMap.schDeptName?result.schDeptName:formDataMap.schDeptName)}
		},
		{
			"inputId":"trainingSpeName",
			"value":${pdfn:toJsonString(empty formDataMap.trainingSpeName?doctor.trainingSpeName:formDataMap.trainingSpeName)}
		},
		{
			"inputId":"orgName",
			"value":${pdfn:toJsonString(empty formDataMap.orgName?doctor.orgName:formDataMap.orgName)}
		},
		{
			"inputId":"inHosDate",
			"value":${pdfn:toJsonString(empty formDataMap.inHosDate?doctor.inHosDate:formDataMap.inHosDate)}
		},
		{
			"inputId":"schStartDate",
			"value":${pdfn:toJsonString(result.schStartDate)}
		},
		{
			"inputId":"schEndDate",
			"value":${pdfn:toJsonString(result.schEndDate)}
		},
		{
			"inputId":"schMonth",
			"value":${pdfn:toJsonString(result.schMonth)}
		},
		{
			"inputId":"manqin",
			"value":${pdfn:toJsonString(formDataMap.manqin)}
		},
		{
			"inputId":"queqin",
			"value":${pdfn:toJsonString(formDataMap.queqin)}
		},
		{
			"inputId":"personalSummary",
			"value":${pdfn:toJsonString(formDataMap.personalSummary)}
		},
		{
			"inputId":"doctorSiginName",
			"value":${pdfn:toJsonString(empty formDataMap.doctorSiginName?doctor.doctorName:formDataMap.doctorSiginName)}
		},
		<c:set value="${pdfn:getCurrDate()}" var="curDate"></c:set>
		{
			"inputId":"doctorSiginDate",
			"value":${pdfn:toJsonString(empty formDataMap.doctorSiginDate?curDate: formDataMap.doctorSiginDate)}
		}
		<c:if test="${!empty formDataMap.deptAppraise}">
			,
			{
				"inputId":"deptAppraise",
				"value":${pdfn:toJsonString(formDataMap.deptAppraise)}
			}
			,
			{
				"inputId":"teacherSiginName",
				"value":${pdfn:toJsonString(formDataMap.teacherSiginName)}
			}
			,
			{
				"inputId":"teacherSiginDate",
				"value":${pdfn:toJsonString(formDataMap.teacherSiginDate)}
			}
		</c:if>
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
			,
			{
				"inputId":"deptHeadAutograth",
				"value":${pdfn:toJsonString(formDataMap.deptHeadAutograth)}
			}
			,
			{
				"inputId":"headSiginName",
				"value":${pdfn:toJsonString(formDataMap.headSiginName)}
			}
			,
			{
				"inputId":"headSiginDate",
				"value":${pdfn:toJsonString(formDataMap.headSiginDate)}
			}
		</c:if>
	]
