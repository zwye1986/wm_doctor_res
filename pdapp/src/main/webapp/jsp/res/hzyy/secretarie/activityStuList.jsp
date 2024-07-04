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
            <c:forEach items="${datas}" var="data" varStatus="s">
                {
                    "UserID": ${pdfn:toJsonString(data.UserID)},
                    "FCaDisID": ${pdfn:toJsonString(data.FCaDisID)},
                    "TrueName": ${pdfn:toJsonString(data.TrueName)},
                    "CaDisTime": ${pdfn:toJsonString(data.CaDisTime)},
                    "RoleName": ${pdfn:toJsonString(data.RoleName)},
                    "Score": ${pdfn:toJsonString(data.Score)},
                    "ISXGH": ${pdfn:toJsonString(data.ISXGH)},
                    "ISJXDW": ${pdfn:toJsonString(data.ISJXDW)},
                    "ISGFSL": ${pdfn:toJsonString(data.ISGFSL)},
                    "ISMDMQ": ${pdfn:toJsonString(data.ISMDMQ)},
                    "CaEndDisTime": ${pdfn:toJsonString(data.CaEndDisTime)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
