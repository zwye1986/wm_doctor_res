<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"data":
        {
            "discipleFlow":${pdfn:toJsonString(resDiscipleInfo.discipleFlow)},
            "doctorName":${pdfn:toJsonString(( empty resDiscipleInfo.doctorName)?user.userName:resDiscipleInfo.doctorName)},
            "doctorFlow":${pdfn:toJsonString(( empty resDiscipleInfo.doctorFlow)?resDoctor.doctorFlow:resDiscipleInfo.doctorFlow)},
            "speName":${pdfn:toJsonString(( empty resDiscipleInfo.speName)?resDoctor.trainingSpeName:resDiscipleInfo.speName)},
            "speId":${pdfn:toJsonString(( empty resDiscipleInfo.speId)?resDoctor.trainingSpeId:resDiscipleInfo.speId)},
            "workOrgName":${pdfn:toJsonString(resDiscipleInfo.workOrgName)},
            "orgName":${pdfn:toJsonString(( empty resDiscipleInfo.orgName)?resDoctor.orgName:resDiscipleInfo.orgName)},
            "orgFlow":${pdfn:toJsonString(( empty resDiscipleInfo.orgFlow)?resDoctor.orgName:resDiscipleInfo.orgFlow)},
            "teacherNames":${pdfn:toJsonString(teacherNames)},
            "discipleStartDate":${pdfn:toJsonString(resDiscipleInfo.discipleStartDate)},
            "discipleEndDate":${pdfn:toJsonString(resDiscipleInfo.discipleEndDate)}
        }
    </c:if>
}
