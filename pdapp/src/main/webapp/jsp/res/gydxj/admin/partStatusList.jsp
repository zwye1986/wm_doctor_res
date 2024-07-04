<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"BaseInfo":${pdfn:toJsonString(statusMap['BaseInfo'])},
		"RecruitInfo":${pdfn:toJsonString(statusMap['RecruitInfo'])},
		"NeedInfo":${pdfn:toJsonString(statusMap['NeedInfo'])},
		"SelectInfo":${pdfn:toJsonString(statusMap['SelectInfo'])},
		"FeeInfo":${pdfn:toJsonString(statusMap['FeeInfo'])},
		"GotCertInfo":${pdfn:toJsonString(statusMap['GotCertInfo'])},
		"CertReqInfo":${pdfn:toJsonString(statusMap['CertReqInfo'])},
		"PaperInfo":${pdfn:toJsonString(statusMap['PaperInfo'])}
    </c:if>
}