<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "data":
    {
        "CIFlow": ${pdfn:toJsonString(data.CIFlow)},
        "CIRemark": ${pdfn:toJsonString(data.CIRemark)},
        "CIAttachPath": ${pdfn:toJsonString(data.CIAttachPath)},
        "CIAttachFileNames": [
            <c:forEach items="${cIAttachFileNameMap[data.CIFlow]}" var="item" varStatus="s1">
                {
                    "name":${pdfn:toJsonString(item)}
                }
                <c:if test='${not s1.last}'>
                    ,
                </c:if>
            </c:forEach>
        ],
        "CIAttachNames":[
            <c:forEach items="${cIAttachNameMap[data.CIFlow]}" var="item" varStatus="s1">
                {
                    "name":${pdfn:toJsonString(item)}
                }
                <c:if test='${not s1.last}'>
                    ,
                </c:if>
            </c:forEach>
        ],
        "SpName": ${pdfn:toJsonString(data.SpName)}
    }
	</c:if>
}
