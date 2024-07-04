<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">,
        "startDateCheck":${pdfn:toJsonString(startDate)},
        "endDateCheck":${pdfn:toJsonString(endDate)}
        ,"button":
        <c:if test="${empty jsres_is_train or jsres_is_train eq 'Y'}">
            <c:if test="${empty recruitList or recruitList.get(recruitList.size()-1).doctorStatusId eq '21' or isRetrain eq 'Y'}">
                "Y",
            </c:if>
        </c:if>
            <c:if test="${(not empty jsres_is_train and jsres_is_train eq 'N') or (not empty recruitList and recruitList.get(recruitList.size()-1).doctorStatusId ne '21' and isRetrain eq 'N')}">
                "N",
            </c:if>
        "infoButton":${pdfn:toJsonString(isDoctorSend)},
        "recruitFlow":${pdfn:toJsonString(recruitFlow)}
    </c:if>
}
