<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,
		"pageIndex": ${param.pageIndex},
	    "pageSize": 10,
	    "dataCount": 1,
	    "depts": [
	        {
				"stageName": "第一阶段必轮",
	            "deptFlow": "11177",
	            "deptName": "麻醉科",
	            "statusId": "Entering",
	            "statusDesc": "已入科",
	            "teacherFlow": "5217",
	            "teacherName": "郭培培",
	            "deptHeadFlow": "5264",
	            "deptHeadName": "刘涛",
	            "startDate": "2015-10-19",
	            "endDate": "2015-11-01",
				"schMonth": 10,
	            "realMonth": 6,
	            "schScore": 60,
	            "progress": 100
	        }
	    ]
    </c:if>
}
