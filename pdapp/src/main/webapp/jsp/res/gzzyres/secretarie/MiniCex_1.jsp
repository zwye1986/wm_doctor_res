<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "datas": [
            {
                 "id":"truename",
                 "name":"学员姓名",
                 "type":"text",
                 "required":true,
                 "readonly":true,
                 "value":${pdfn:toJsonString(doctorInfo.TrueName)}
             },
             {
                 "id":"username",
                 "name":"工号",
                 "type":"text",
                 "required":true,
                 "readonly":true,
                 "value":${pdfn:toJsonString(doctorInfo.username)}
             },
             {
                 "id":"HosSecName",
                 "name":"轮转科室",
                 "type":"text",
                 "required":true,
                 "readonly":true,
                 "value":${pdfn:toJsonString(doctorInfo.HosSecName)}
             },
             {
                 "id":"StartTime",
                 "name":"开始时间",
                 "type":"text",
                 "required":true,
                 "readonly":true,
                 "value":${pdfn:toJsonString(doctorInfo.StartTime)}
             },
             {
                 "id":"EndTime",
                 "name":"结束时间",
                 "type":"text",
                 "required":true,
                 "readonly":true,
                 "value":${pdfn:toJsonString(doctorInfo.EndTime)}
             },
             {
                 "id":"Mini_Item",
                 "name":"考核内容",
                 "type":"textarea",
                 "required": true,
                 "placeholder":"",
                "readonly":true,
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Item)}
             },
             {
                 "id":"Mini_Content",
                 "name":"点评",
                 "type":"textarea",
                 "required": true,
                 "placeholder":"",
                "readonly":true,
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Content)}
             },
             {
                 "id":"Mini_Score",
                 "name":"得分",
                 "type":"text",
                 "required":true,
                 "verify": {
					"max": "100",
					"type": "int"
				 },
                "readonly":true,
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Score)}
             },
            {
                "id":"Mini_TecName",
                "name":"考官签名",
                "type":"text",
                "required":true,
                "readonly":true,
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_TecName ? doctorInfo.TecName:outMiniCex.Mini_TecName)}
            },
            {
                "id":"Mini_Time",
                "name":"考核时间",
                "type":"text",
                "required":true,
                "readonly":true,
                <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_Time ? (currDate):outMiniCex.Mini_Time)}
            },
            {
                "id":"Mini_ProfessorName",
                "name":"科主任审批签名",
                "type":"text",
                "required":true,
                "readonly":true,
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_ProfessorName ? doctorInfo.ManagerName:outMiniCex.Mini_ProfessorName)}
            }
    ]
	</c:if>
}
