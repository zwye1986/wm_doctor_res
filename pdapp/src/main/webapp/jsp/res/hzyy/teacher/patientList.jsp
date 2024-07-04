<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
	,
	"isAudit":"${isAudit}",
		<c:if test="${isAudit eq 'N'}">
			"dataCount":2,
		</c:if>
		<c:if test="${isAudit ne 'N' and outPatientMap.Patient_Type eq '1'}">
			"dataCount":1,
		</c:if>
		<c:if test="${isAudit ne 'N' and outPatientMap.Patient_Type eq '2'}">
			"dataCount":1,
		</c:if>
    "datas": [
		<c:if test="${isAudit eq 'N'}">
			{
				"patientTypeName":"门急诊接诊评分表",
				"patientTypeId":"1"
			}
			,
			{
				"patientTypeName":"病房接诊评分表",
				"patientTypeId":"2"
			}
		</c:if>
		<c:if test="${isAudit ne 'N' and outPatientMap.Patient_Type eq '1'}">
			{
				"patientTypeName":"门急诊接诊评分表",
				"patientTypeId":"1"
			}
		</c:if>
		<c:if test="${isAudit ne 'N' and outPatientMap.Patient_Type eq '2'}">
			{
				"patientTypeName":"病房接诊评分表",
				"patientTypeId":"2"
			}
		</c:if>
	]
	</c:if>
}