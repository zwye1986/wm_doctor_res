<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "T002",
            "funcName": "数据审核",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
        "funcTypeId": "dataInput11",
        "funcTypeName": "数据输入",
        "funcFlow": "DOPS",
        "funcName": "临床操作技能评估量化表",
        "disabled": false,
        "disabledTip": "",
        "img": "Education"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "Mini_CEX",
            "funcName": "迷你临床演练评估量化表",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterSummary",
            "funcName": "出科小结",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterEvaluation",
            "funcName": "出科考核表",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        }
    ]
    </c:if>
}
