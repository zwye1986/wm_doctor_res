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
            <c:forEach items="${zhuPeiInfoList}" var="info" varStatus="s">
                {
                    "CIFlow": ${pdfn:toJsonString(info.CIFlow)},
                    "CIRemark": ${pdfn:toJsonString(info.CIRemark)},
                    "CIAttachPath": ${pdfn:toJsonString(info.CIAttachPath)},
                    "RoleName": ${pdfn:toJsonString(info.RoleName)},
                    "CIAttachFileNames": [
                        <c:forEach items="${cIAttachFileNameMap[info.CIFlow]}" var="item" varStatus="s1">
                            {
                                "name":${pdfn:toJsonString(item)}
                            }
                            <c:if test='${not s1.last}'>
                            ,
                            </c:if>
                        </c:forEach>
                    ],
                    "CIAttachNames":[
                        <c:forEach items="${cIAttachNameMap[info.CIFlow]}" var="item" varStatus="s1">
                        {
                            "name":${pdfn:toJsonString(item)}
                        }
                        <c:if test='${not s1.last}'>
                        ,
                        </c:if>
                        </c:forEach>
                    ]
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
