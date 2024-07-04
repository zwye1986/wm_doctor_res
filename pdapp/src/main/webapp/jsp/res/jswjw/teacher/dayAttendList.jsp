<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount":${dataCount},
	"allDataCount":${allDataCount},
	"datas":[
			<c:forEach items="${list}" var="bean" varStatus="s">
				{
					"docFlow":"${bean.userFlow}",
					"processFlow":"${bean.processFlow}",
					"schStartDate":"${bean.schStartDate}",
					"schEndDate":"${bean.schEndDate}",
					"dateDay":"${bean.dateDay}",
					"attendanceFlow":"${bean.attendanceFlow}",
					"attendStatus":"${bean.attendStatus}",
					"attendStatusName":"${bean.attendStatusName}",
					"auditStatusId":"${bean.auditStatusId}",
					"auditStatusName":"${bean.auditStatusName}",
					"attendTime":"${bean.attendTime}",
					"attendLocal":"${bean.attendLocal}",
					"remarks":"${bean.remarks}",
					"action":[
							<c:if test="${param.attendType eq '-1'}">
								{
									"id":"1",
									"name":"出勤"
								},
								{
									"id":"0",
									"name":"缺勤"
								}
							</c:if>
							<c:if test="${param.attendType eq '0'}">
								{
									"id":"1",
									"name":"出勤"
								},
								{
									"id":"-1",
									"name":"休息"
								}
							</c:if>
							<c:if test="${param.attendType eq '1'}">
								{
									"id":"-1",
									"name":"休息"
								},
								{
									"id":"0",
									"name":"缺勤"
								}
							</c:if>
					]
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}