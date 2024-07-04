<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    "datas":[
            <c:forEach items="${internshipList}" var="internship" varStatus="s">
                {
                    "InDateTime": ${pdfn:toJsonString(internship.InDateTime)},
                    "NO": ${pdfn:toJsonString(internship.NO)},
                    "Patient": ${pdfn:toJsonString(internship.Patient)},
                    "Diagnosis": ${pdfn:toJsonString(internship.Diagnosis)},
                    "TecName": ${pdfn:toJsonString(internship.TecName)},
                    "DicCount": ${pdfn:toJsonString(internship.DicCount)},
                    "Name": ${pdfn:toJsonString(internship.Name)},
                    "Study": ${pdfn:toJsonString(internship.Study)},
                    "Master": ${pdfn:toJsonString(internship.Master)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
