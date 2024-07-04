<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				<c:set value="${bean.processFlow}DOPS" var="dopsKey"/>
				<c:set value="${bean.processFlow}Mini_CEX" var="miniKey"/>
				<c:set value="${bean.processFlow}AfterEvaluation" var="afterKey"/>
				<c:set value="${bean.processFlow}schScore" var="scoreKey"/>
				<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
				"userName":"${bean.userName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"userHeadImg":"${bean.userHeadImg}",
				"docFlow":"${bean.userFlow}",
				"schResultFlow":"${bean.schResultFlow}",
				"resultFlow":"${bean.resultFlow}",
				"sessionNumber":"${bean.sessionNumber}",
				"speName":"${bean.speName}",
				"teacherUserName":"${bean.teacherUserName}",
				"processFlow":"${bean.processFlow}",
				"orgFlow":"${bean.orgFlow}",
				"orgName":"${bean.orgName}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"recordFlow":"${schRotationDeptMap[bean.processFlow].recordFlow}",
				"per": ${pdfn:toJsonString(empty bean.per?0:bean.per)},
				"currDate":"${currDate}",
				"schType":"${bean.statusId}",
				"schStatus":"${bean.statusName}",
				"abnormalCause":"${bean.abnormalCause}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}