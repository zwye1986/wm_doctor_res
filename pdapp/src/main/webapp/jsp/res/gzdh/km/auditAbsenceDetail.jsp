<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"absence":
			{
				"doctorFlow": ${pdfn:toJsonString(absence.doctorFlow)},
				"doctorName": ${pdfn:toJsonString(docUser.userName)},
				"headImg": "",
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
				"makingFile" : ${pdfn:toJsonString(file.fileFlow)},
				"makingName" : ${pdfn:toJsonString(file.fileName)},
				<c:set var="headImg2" value="${baseDirs}/${file.filePath}"/>
				<c:set var="headImg" value="${empty file.filePath?'':headImg2}"/>
				"makingUrl" : ${pdfn:toJsonString(img)},
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
			},
		"absenceStatus":[
			<c:if test="${not empty absence.teacherAgreeFlag}">
				<c:if test="${absence.teacherAgreeFlag eq 'Y'}">
					<c:set var="statusId" value="Passed"></c:set>
					<c:set var="statusName" value="审核通过"></c:set>
				</c:if>
				<c:if test="${absence.teacherAgreeFlag eq 'N'}">
					<c:set var="statusId" value="UnPassed"></c:set>
					<c:set var="statusName" value="审核不通过"></c:set>
				</c:if>
				{
					"auditUserFlow" : ${pdfn:toJsonString(absence.teacherFlow)},
					"auditUserName" : ${pdfn:toJsonString(absence.teacherName)},
					"auditId" :  ${pdfn:toJsonString(statusId)},
					"auditName" :  ${pdfn:toJsonString(statusName)},
					"auditInfo" : ${pdfn:toJsonString(absence.teacherAuditInfo)},
					"auditTime"  : ${pdfn:toJsonString(absence.teacherAuditTime)}
				}
				<c:if test="${absence.absenceTypeId eq 'Yearleave' and absence.intervalDay eq '1'}">
				,
				<c:if test="${empty absence.headAgreeFlag}">
					<c:set var="statusId" value="NotAudit"></c:set>
					<c:set var="statusName" value="待审核"></c:set>
				</c:if>
				<c:if test="${absence.headAgreeFlag eq 'Y'}">
					<c:set var="statusId" value="Passed"></c:set>
					<c:set var="statusName" value="审核通过"></c:set>
				</c:if>
				<c:if test="${absence.headAgreeFlag eq 'N'}">
					<c:set var="statusId" value="UnPassed"></c:set>
					<c:set var="statusName" value="审核不通过"></c:set>
				</c:if>
				{
					"auditUserFlow" : ${pdfn:toJsonString(absence.headFlow)},
					"auditUserName" : ${pdfn:toJsonString(absence.headName)},
					"auditId" :  ${pdfn:toJsonString(statusId)},
					"auditName" :  ${pdfn:toJsonString(statusName)},
					"auditInfo" : ${pdfn:toJsonString(absence.headAuditInfo)},
					"auditTime"  : ${pdfn:toJsonString(absence.headAuditTime)}
				},
				<c:if test="${empty absence.managerAgreeFlag}">
					<c:set var="statusId" value="NotAudit"></c:set>
					<c:set var="statusName" value="待审核"></c:set>
				</c:if>
				<c:if test="${absence.managerAgreeFlag eq 'Y'}">
					<c:set var="statusId" value="Passed"></c:set>
					<c:set var="statusName" value="审核通过"></c:set>
				</c:if>
				<c:if test="${absence.managerAgreeFlag eq 'N'}">
					<c:set var="statusId" value="UnPassed"></c:set>
					<c:set var="statusName" value="审核不通过"></c:set>
				</c:if>
				{
					"auditUserFlow" : ${pdfn:toJsonString(absence.managerFlow)},
					"auditUserName" : ${pdfn:toJsonString(absence.managerName)},
					"auditId" :  ${pdfn:toJsonString(statusId)},
					"auditName" :  ${pdfn:toJsonString(statusName)},
					"auditInfo" : ${pdfn:toJsonString(absence.managerAuditInfo)},
					"auditTime"  : ${pdfn:toJsonString(absence.managerAuditTime)}
				}
				</c:if>
			</c:if>
		],
		"repealStatus":[
			<c:if test="${not empty absence.repealAbsenceDay}">
				{
					"auditUserFlow" : ${pdfn:toJsonString(absence.teacherFlow)},
					"auditUserName" : ${pdfn:toJsonString(absence.teacherName)},
					"auditId" : "Audited",
					"auditName" : "已审核",
					"auditInfo" : ${pdfn:toJsonString(absence.repealAbsenceInfo)},
					"auditTime"  : ${pdfn:toJsonString(absence.repealAbsenceAuditTime)}
				}
			</c:if>
		]
    </c:if>
}