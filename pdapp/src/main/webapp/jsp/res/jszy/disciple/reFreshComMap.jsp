<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    <c:if test="${param.noteTypeId eq 'FollowTeacherRecord'}">
        ,
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'PendingAudit'"])},
            "NotAudit":${pdfn:toJsonString(compMap["'PendingAudit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        }
    </c:if>
    <c:if test="${param.noteTypeId eq 'Note'or param.noteTypeId eq 'Experience' or param.noteTypeId eq 'BookExperience' }">
        ,
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'Apply'"]+compMap["'Submit'"])},
            "Saved":${pdfn:toJsonString(compMap["'Apply'"]+0)},
            "NotAudit":${pdfn:toJsonString(compMap["'Submit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        }
    </c:if>
    <c:if test="${param.noteTypeId eq 'TypicalCases'}">
        ,
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'Apply'"]+compMap["'PendingAudit'"]+0)},
            "Saved":${pdfn:toJsonString(compMap["'Apply'"]+0)},
            "NotAudit":${pdfn:toJsonString(compMap["'PendingAudit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        }
    </c:if>
</c:if>
}