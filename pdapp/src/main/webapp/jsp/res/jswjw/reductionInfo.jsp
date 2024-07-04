<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "recordFlow":${pdfn:toJsonString(reduction.recordFlow)},
    "recruitFlow":${pdfn:toJsonString(doctorRecruit.recruitFlow)},
    "reduceDoctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
    "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
    "reduceYear":${pdfn:toJsonString(reduction.reduceYear)},
    "defaultTrainYear":"3",
    "afterReduceTrainYear":${pdfn:toJsonString(reduction.afterReduceTrainYear)},
    </c:if>
}
