<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveAfterEvaluation1",
        "save":"保存"
    },
    "datas": [
            {
                "id": "UCSID",
                "name": "主键：",
                "type": "text",
                "required":true,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(param.UCSID)}
            },
            {
                "id": "ID",
                "name": "主键：",
                "type": "text",
                "required":true,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(auditData.ID)}
            },
            {
                "id": "AssessmentMark",
                "name": "理论考核分数：",
                "type": "double",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(empty auditData.AssessmentMark ?0.0: auditData.AssessmentMark)}
            },
            {
                "id": "miniScore",
                "name": "Mini-CEX评量：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniScore)}
            },
            <%--{--%>
                <%--"id": "dopsScore",--%>
                <%--"name": "DOPS评量：",--%>
                <%--"type": "text",--%>
                <%--"required":false,--%>
                <%--"readonly":true,--%>
                <%--"value":${pdfn:toJsonString(dopsScore)}--%>
            <%--},--%>
            {
                "id": "TecName",
                "name": "日常评价|评价老师：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dailyInfo.Teacher)}
            },
            {
                "id": "evalScore",
                "name": "日常评价|得分：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dailyInfo.TotalScore)}
            },
            {
                "id": "totelScore",
                "name": "总分：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(totelScore)}
            },
			{
				"id":"shijia",
				"name":"事假",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty shijia ? '0':shijia)}
			},
			{
				"id":"bingjia",
				"name":"病假",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty bingjia ? '0':bingjia)}
			},
			{
				"id":"queqing",
				"name":"缺勤",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty queqing ? '0':queqing)}
			},
            {
                "id": "TecComment",
                "name": "带教老师鉴定意见：",
                "type": "textarea",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(auditData.TecComment)}
            },
            {
                "id": "TecName",
                "name": "带教老师签名：",
                "type": "text",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(auditData.TecName)}
            },
            {
                "id": "TecDate",
                "name": "带教老师鉴定日期：",
                "type": "date",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(auditData.TecDate)}
            },
            {
                "id": "SecComment",
                "name": "教学秘书鉴定意见：",
                "type": "textarea",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(auditData.SecComment)}
            },
            {
                "id": "SecName",
                "name": "教学秘书签名：",
                "type": "text",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(empty auditData.SecName?user.userName:auditData.SecName)}
            },
            {
                "id": "SecDate",
                "name": "教学秘书鉴定日期：",
                "type": "date",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(nowDate)}
            },
            {
                "id": "ProfesserID",
                "name": "科主任ID：",
                "type": "text",
                "required":true,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(auditData.SectionManagerID)}
            },
            {
                "id": "ProfesserComent",
                "name": "科主任鉴定意见：",
                "type": "textarea",
                "required":true,
                "readonly":true,
                <%--"value":"sssss"--%>
                "value":${pdfn:toJsonString(empty auditData.ProfesserComent ?"同意出科":auditData.ProfesserComent)}
            },
            {
                "id": "ProfesserName",
                "name": "科主任签名：",
                "type": "text",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(auditData.ManagerName)}
            },
            {
                "id": "ProfesserDate",
                "name": "科主任鉴定日期：",
                "type": "date",
                "required":true,
                "readonly":true,
                "value":${pdfn:toJsonString(nowDate)}
            }
    ]
	</c:if>
}
