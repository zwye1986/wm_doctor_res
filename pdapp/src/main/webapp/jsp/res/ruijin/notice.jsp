<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex": 1,
    "pageSize": 10,
    "dataCount": 0,
    "noticeList": [
	    <c:if test="${param.userFlow eq 'student' }">
	    {
            "deptFlow": "11177",
            "deptName": "内科",
            "content": "有1个科室待入科",
            "funcTypeId": "qrCode",
            "funcFlow": "signin",
            "dataFlow": "1",
            "dataName": "内科/张三",
            "cataFlow": "123456"
        },
        {
            "deptFlow": "11177",
            "deptName": "内科",
            "content": "有两个活动未参加",
            "funcTypeId": "dataInput11",
            "funcFlow": "S009",
            "dataFlow": "11177",
            "dataName": "内科/张三",
            "cataFlow": "123456"
        },
        {
        	"deptFlow": "11177",
            "deptName": "内科",
            "content": "有两个活动未参加",
            "funcTypeId": "dataInput1N",
            "funcFlow": "S001",
            "dataFlow": "1",
            "dataName": " 内科/张三",
            "cataFlow": "123456"
        },
        {
        	"deptFlow": "11177",
            "deptName": "内科",
            "content": "有2个活动未参加",
            "funcTypeId": "dataInputNN",
            "funcFlow": "S003",
            "dataFlow": "2",
            "dataName": "李四",
            "cataFlow": "123456"
        }
	    </c:if>
	    <c:if test="${param.userFlow eq 'teacher' }">

	    </c:if>
    ]
}