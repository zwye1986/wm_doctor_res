<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": 1,
    "pageSize": 10,
    "dataCount": 1,
    "doctorList": [
        {
            "doctorFlow": "11201",
            "doctorName": "实习生10201",
            "doctorImg": "${ctxPath}/images/xx.png",
            "schStartDate": "2015-10-01",
            "schEndDate": "2015-11-11",
            "statusId": "Entering",
            "statusDesc": "轮转中",
            "startDate": "2015-11-01",
            "endDate": "2015-11-11",
			"score": "80"
        },
		{
            "doctorFlow": "11201",
            "doctorName": "实习生10202",
            "doctorImg": "${ctxPath}/images/xx.png",
            "schStartDate": "2015-11-01",
            "schEndDate": "2015-12-11",
            "schStatusId": "NotEntered",
            "schStatusDesc": "轮转中",
            "startDate": "2015-11-01",
            "endDate": "2015-11-11",
			"score": "90"
        },
		{
            "doctorFlow": "11201",
            "doctorName": "实习生10203",
            "doctorImg": "${ctxPath}/images/xx.png",
            "schStartDate": "2015-12-01",
            "schEndDate": "2016-1-11",
            "schStatusId": "Exited",
            "schStatusDesc": "已出科",
            "startDate": "2015-11-01",
            "endDate": "2015-11-11",
			"score": "100"
        }
    ]
    </c:if>
}
