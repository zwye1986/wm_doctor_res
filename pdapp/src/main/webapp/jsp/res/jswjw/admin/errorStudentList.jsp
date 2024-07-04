<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "depts": [
        <c:forEach items="${depts}" var="dept" varStatus="status">
	        {
				<c:set var="list" value="${processMap[dept.deptFlow]}"></c:set>
	            "deptFlow": ${pdfn:toJsonString(dept.deptFlow)},
	            "deptName": ${pdfn:toJsonString(dept.deptName)},
	            "order": ${status.index+1},
				"studentList":[
							<c:forEach items="${list}" var="bean" varStatus="s">
									{
										<c:set var="headImg" value="${uploadBaseUrl}/${empty bean.userHeadImg?'default.png':bean.userHeadImg}"/>
										"userName":"${bean.userName}",
										"userHeadImg":"${headImg}",
										"docFlow":"${bean.userFlow}",
										"deptFlow":"${bean.deptFlow}"
									}
									<c:if test="${not s.last }">
										,
									</c:if>
							 </c:forEach>
				]
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}