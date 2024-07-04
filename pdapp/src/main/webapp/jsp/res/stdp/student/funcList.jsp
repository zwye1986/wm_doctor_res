<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
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
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S001",
            "funcName": "教学记录",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S002",
            "funcName": "大病历",
            "disabled": false,
            "disabledTip": "",
            "img": "Study"
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "S003",
            "funcName": "操作技能",
            "disabled": false,
            "disabledTip": "",
            "img": "Clinical"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S004",
            "funcName": "管床记录",
            "disabled": false,
            "disabledTip": "",
            "img": "Teaching"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S005",
            "funcName": "危重记录",
            "disabled": false,
            "disabledTip": "",
            "img": "Teaching"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S006",
            "funcName": "参加活动",
            "disabled": false,
            "disabledTip": "",
            "img": "Teaching"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "S007",
            "funcName": "实习记录",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "S008",
            "funcName": "评价老师",
            "disabled": false,
            "disabledTip": "",
            "img": "Teacher"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "S009",
            "funcName": "评价科室",
            "disabled": false,
            "disabledTip": "",
            "img": "Dept"
        }
    ]
    </c:if>
}
