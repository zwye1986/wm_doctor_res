<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    <c:if test="${resultId eq '200' }">
	    ,
	"inputs": [
		<%--{--%>
			<%--"inputId": "userName",--%>
			<%--"label": "姓名",--%>
			<%--"required":true,--%>
			<%--"inputType": "text",--%>
			<%--"value":"${docUser.userName}",--%>
			<%--"readonly":true--%>
		<%--},--%>
		<%--{--%>
			<%--"inputId": "sessionNumber",--%>
			<%--"label": "年级",--%>
			<%--"required":true,--%>
			<%--"inputType": "text",--%>
			<%--"value":"${doctor.sessionNumber}",--%>
			<%--"readonly":true--%>
		<%--},--%>
		<%--{--%>
			<%--"inputId": "departMentName",--%>
			<%--"label": "所在科室",--%>
			<%--"required":true,--%>
			<%--"inputType": "text",--%>
			<%--"value":"${doctor.departMentName}",--%>
			<%--"readonly":true--%>
		<%--},--%>
		<%--{--%>
			<%--"inputId": "trainingSpeName",--%>
			<%--"label": "专业",--%>
			<%--"required":true,--%>
			<%--"inputType": "text",--%>
			<%--"value":"${doctor.trainingSpeName}",--%>
			<%--"readonly":true--%>
		<%--},--%>
		{
			"inputId": "bj",
			"label": "病假",
			"required":true,
			"inputType": "text",
			"value":"${countDetailMap['02']+countDetail['03']/2}",
			"readonly":true
		},
		{
			"inputId": "sj",
			"label": "事假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['04']?'0.0':countDetailMap['04']}",
			"readonly":true
		},
		{
			"inputId": "dxnj",
			"label": "带薪年假",
			"required":true,
			"inputType": "text",
			"value":"${countDetailMap['06']+countDetail['07']/2}",
			"readonly":true
		},
		{
			"inputId": "hj",
			"label": "婚假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['10']?'0.0':countDetailMap['10']}",
			"readonly":true
		},
		{
			"inputId": "cj",
			"label": "产假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['11']?'0.0':countDetailMap['11']}",
			"readonly":true
		},
		{
			"inputId": "pcj",
			"label": "陪产假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['12']?'0.0':countDetailMap['12']}",
			"readonly":true
		},
		{
			"inputId": "jsj",
			"label": "计生假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['13']?'0.0':countDetailMap['13']}",
			"readonly":true
		},
		{
			"inputId": "cg",
			"label": "出国",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['15']?'0.0':countDetailMap['15']}",
			"readonly":true
		},
		{
			"inputId": "jx",
			"label": "进修",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['16']?'0.0':countDetailMap['16']}",
			"readonly":true
		},
		{
			"inputId": "tcdy",
			"label": "脱产读研",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['18']?'0.0':countDetailMap['18']}",
			"readonly":true
		},
		{
			"inputId": "fsj",
			"label": "放射假",
			"required":true,
			"inputType": "text",
			"value":"${empty countDetailMap['19']?'0.0':countDetailMap['19']}",
			"readonly":true
		},
		{
			"inputId": "kg",
			"label": "旷工",
			"required":true,
			"inputType": "text",
			"value":"${countDetailMap['20']+countDetail['21']/2}",
			"readonly":true
		},
		{
			"inputId": "cq",
			"label": "出勤天数",
			"required":true,
			"inputType": "text",
			"value":"${countDetailMap['00']}",
			"readonly":true
		}
	]
    </c:if>
}