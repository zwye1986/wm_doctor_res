<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="trKey" value="${param.deptFlow}TeachRecordRegistry"/>
<c:set var="cKey" value="${param.deptFlow}CaseRegistry"/>
<c:set var="srKey" value="${param.deptFlow}SkillRegistry"/>
<c:set var="mbrKey" value="${param.deptFlow}ManageBedRegistry"/>
<c:set var="gKey" value="${param.deptFlow}Grave"/>
<c:set var="cmKey" value="${param.deptFlow}CampaignRegistry"/>
<c:set var="iKey" value="${param.deptFlow}Internship"/>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
	 	{
            "funcTypeId": "qrCode",
            "funcTypeName": "二维码功能",
            "funcFlow": "signin",
            "funcName": "每日签到",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education",
            "lastSignin": ${pdfn:toJsonString(lastSignin)}
            
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "TeachRecordRegistry",
            "funcName": "教学记录",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education",
            "progress":${pdfn:toJsonString(perMap[trKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "CaseRegistry",
            "funcName": "大病历",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Study",
            "progress":${pdfn:toJsonString(perMap[cKey]+0)}
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "SkillRegistry",
            "funcName": "操作技能",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Clinical",
            "progress":${pdfn:toJsonString(perMap[srKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "ManageBedRegistry",
            "funcName": "管床记录",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teaching",
            "progress":${pdfn:toJsonString(perMap[mbrKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "Grave",
            "funcName": "危重记录",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teaching",
            "progress":${pdfn:toJsonString(perMap[gKey]+0)}
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "CampaignRegistry",
            "funcName": "参加活动",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teaching",
            "progress":${pdfn:toJsonString(perMap[cmKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "Internship",
            "funcName": "实习记录",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education",
            "progress":${pdfn:toJsonString(perMap[iKey]+0)}
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "TeacherGrade",
            "funcName": "评价老师",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teacher"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "DeptGrade",
            "funcName": "评价科室",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Dept"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterSummary",
            "funcName": "出科小结",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education"
        }
    ]
    </c:if>
}
