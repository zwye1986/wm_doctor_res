<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"dataCount": "${dataCount}",
		"doctorList": [
		<c:forEach items="${list}" var="absence" varStatus="status">
			<c:set var="headImg" value="${uploadBaseUrl}/${empty absence.userHeadImg?'default.png':absence.userHeadImg}"/>
			{
				"doctorFlow": ${pdfn:toJsonString(absence.doctorFlow)},
				"doctorName": ${pdfn:toJsonString(absence.userName)},
				"headImg": ${pdfn:toJsonString(headImg)},
				"absenceFlow": ${pdfn:toJsonString(absence.absenceFlow)},
				"sessionNumber" : ${pdfn:toJsonString(absence.sessionNumber)},
				"trainingSpeId" : ${pdfn:toJsonString(absence.trainingSpeId)},
				"trainingSpeName" : ${pdfn:toJsonString(absence.trainingSpeName)},
				"startDate" : ${pdfn:toJsonString(absence.startDate)},
				"endDate" : ${pdfn:toJsonString(absence.endDate)},
				"intervalDay" : ${pdfn:toJsonString(absence.intervalDay)},
				"absenceReson" : ${pdfn:toJsonString(absence.absenceReson)},
				"doctorCategoryId" : ${pdfn:toJsonString(absence.doctorCategoryId)},
				"doctorCategoryName" : ${pdfn:toJsonString(absence.doctorCategoryName)},
				"schDeptFlow" : ${pdfn:toJsonString(absence.schDeptFlow)},
				"schDeptName" : ${pdfn:toJsonString(absence.schDeptName)},
				"teacherAgreeFlag" : ${pdfn:toJsonString(absence.teacherAgreeFlag)},
				"headAgreeFlag" : ${pdfn:toJsonString(absence.headAgreeFlag)},
				"managerAgreeFlag" : ${pdfn:toJsonString(absence.managerAgreeFlag)},
				"orgFlow" : ${pdfn:toJsonString(absence.orgFlow)},
				"orgName" : ${pdfn:toJsonString(absence.orgName)},
				"teacherFlow" : ${pdfn:toJsonString(absence.teacherFlow)},
				"teacherName" : ${pdfn:toJsonString(absence.teacherName)},
				"headFlow" : ${pdfn:toJsonString(absence.headFlow)},
				"headName" : ${pdfn:toJsonString(absence.headName)},
				"managerFlow" : ${pdfn:toJsonString(absence.managerFlow)},
				"managerName" : ${pdfn:toJsonString(absence.managerName)},
				"repealAbsence" : ${pdfn:toJsonString(absence.repealAbsence)},
				"repealAbsenceDate" : ${pdfn:toJsonString(absence.repealAbsenceDate)},
				"absenceTypeId" : ${pdfn:toJsonString(absence.absenceTypeId)},
				"absenceTypeName" : ${pdfn:toJsonString(absence.absenceTypeName)},
				"isRegister" : ${pdfn:toJsonString(absence.isRegister)},
				"makingFile" : ${pdfn:toJsonString(absence.makingFile)},
				"deptFlow" : ${pdfn:toJsonString(absence.deptFlow)},
				"deptName" : ${pdfn:toJsonString(absence.deptName)},
				"doctorTypeId" : ${pdfn:toJsonString(absence.doctorTypeId)},
				"doctorTypeName" : ${pdfn:toJsonString(absence.doctorTypeName)},
				"repealAbsenceDay" : ${pdfn:toJsonString(absence.repealAbsenceDay)},
				"teachingFlow" : ${pdfn:toJsonString(absence.teachingFlow)},
				"teachingName" : ${pdfn:toJsonString(absence.teachingName)},
				"teacherAuditInfo" : ${pdfn:toJsonString(absence.teacherAuditInfo)},
				"headAuditInfo" : ${pdfn:toJsonString(absence.headAuditInfo)},
				"managerAuditInfo" : ${pdfn:toJsonString(absence.managerAuditInfo)},
				"repealAbsenceInfo" : ${pdfn:toJsonString(absence.repealAbsenceInfo)},
				"teacherAuditTime"  : ${pdfn:toJsonString(absence.teacherAuditTime)},
				"headAuditTime"  : ${pdfn:toJsonString(absence.headAuditTime)},
				"managerAuditTime"  : ${pdfn:toJsonString(absence.managerAuditTime)},
				"repealAbsenceAuditTime"  : ${pdfn:toJsonString(absence.repealAbsenceAuditTime)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}