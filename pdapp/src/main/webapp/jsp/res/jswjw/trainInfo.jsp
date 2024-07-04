<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"currDegreeCategoryName":${pdfn:toJsonString(doctorRecruit.currDegreeCategoryName)},
	"auditStatusName":	<c:if test="${jsResDoctorAuditStatusEnumNotSubmit.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId}">
		${pdfn:toJsonString(doctorRecruit.auditStatusName)},
		</c:if>
		<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}">
		${pdfn:toJsonString(doctorRecruit.auditStatusName)},
		</c:if>
		<c:if test="${'WaitGlobalPass' eq doctorRecruit.auditStatusId}">
		${pdfn:toJsonString(doctorRecruit.auditStatusName)},
		</c:if>
	"recruitDate":${pdfn:toJsonString(doctorRecruit.recruitDate)},
	"sessionNumber":${pdfn:toJsonString(doctorRecruit.sessionNumber)},
	"graduationYear":${pdfn:toJsonString(doctorRecruit.graduationYear)},
	"orgName":
		<c:if test="${!empty doctorRecruit.jointOrgFlow}">
			${pdfn:toJsonString(doctorRecruit.jointOrgName)},
		</c:if>
		<c:if test="${empty doctorRecruit.jointOrgFlow}">
			${pdfn:toJsonString(doctorRecruit.orgName)},
		</c:if>
	"catSpeName":${pdfn:toJsonString(doctorRecruit.catSpeName)},
	"speName":${pdfn:toJsonString(doctorRecruit.speName)},
	"trainYear":
		<c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
			<c:if test="${doctorRecruit.trainYear eq trainYear.id}">${pdfn:toJsonString(trainYear.name)}</c:if>
		</c:forEach>,
	"yetTrainYear":"<c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}"><c:if test="${doctorRecruit.yetTrainYear eq dict.dictId}">${dict.dictName}</c:if></c:forEach>",
	"doctorStatusName":${pdfn:toJsonString(doctorRecruit.doctorStatusName)},
	"doctorStrikeName":${pdfn:toJsonString(doctorRecruit.doctorStrikeName)},
	"placeName":${pdfn:toJsonString(doctorRecruit.placeName)},
	"specialFileUrl":${pdfn:toJsonString(doctorRecruit.specialFileUrl)},
	"specialCertNo":${pdfn:toJsonString(doctorRecruit.specialCertNo)},
	"upload_base_url":${pdfn:toJsonString(upload_base_url)},
		"admitNotice":
		<c:if test="${not empty doctorRecruit.admitNotice}">
			${pdfn:toJsonString(doctorRecruit.admitNotice)},
		</c:if>
		<c:if test="${empty doctorRecruit.admitNotice}">
			"",
		</c:if>
		"globalNotice":
		<c:if test="${not empty doctorRecruit.globalNotice}">
			${pdfn:toJsonString(doctorRecruit.globalNotice)},
		</c:if>
		<c:if test="${empty doctorRecruit.globalNotice}">
			"",
		</c:if>
	"imageUrl":
	<c:if test="${not empty resRec.imageUrl}">
		${pdfn:toJsonString(resRec.imageUrl)},
	</c:if>
	<c:if test="${empty resRec.imageUrl}">
		"暂未提交",
	</c:if>
	"sumbitButton":
	<c:if test="${(doctorRecruit.auditStatusId eq 'NotSubmit' or doctorRecruit.auditStatusId eq 'NotPassed') && sessionNumber == doctorRecruit.sessionNumber && doctorRecruit.doctorStatusId != '24' && jsres_is_train ne 'N'}">
		"Y",
	</c:if>
	<c:if test="${doctorRecruit.auditStatusId ne 'NotSubmit' and  doctorRecruit.auditStatusId ne 'NotPassed'  or sessionNumber != doctorRecruit.sessionNumber or doctorRecruit.doctorStatusId == '24' or jsres_is_train eq 'N'}">
		"N",
	</c:if>
	"editButton":
		<c:if test="${(doctorRecruit.auditStatusId eq 'NotSubmit' or doctorRecruit.auditStatusId eq 'NotPassed') && sessionNumber == doctorRecruit.sessionNumber  && jsres_is_train ne 'N'}">
			"Y",
		</c:if>
		<c:if test="${doctorRecruit.auditStatusId ne 'NotSubmit' and  doctorRecruit.auditStatusId ne 'NotPassed'  or sessionNumber != doctorRecruit.sessionNumber  or jsres_is_train eq 'N'}">
			"N",
		</c:if>
	"graduationButton":
	<c:if test="${doctorRecruit.doctorStatusId eq '21'}">
		"Y"
	</c:if>
	<c:if test="${doctorRecruit.doctorStatusId ne '21'}">
		"N"
	</c:if>


    </c:if>
}
