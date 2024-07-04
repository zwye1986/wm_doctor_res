<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据列表",
            "funcFlow": "SE001",
            "funcName": "入科教育",
            "disabled": false,
            "disabledTip": "",
            "img": "Education"
        }
    ]
    </c:if>
}
