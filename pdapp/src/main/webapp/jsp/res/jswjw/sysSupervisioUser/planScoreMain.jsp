<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,
<%--	    "orgs": [--%>
<%--	    <c:forEach items="${orgs}" var="org" varStatus="status">--%>
<%--	        {--%>
<%--			"orgFlow"=${pdfn:toJsonString(org.orgFlow)},--%>
<%--			"orgName"=${pdfn:toJsonString(org.orgName)},--%>
<%--			"cityId"=${pdfn:toJsonString(org.orgCityId)}--%>
<%--	        }--%>
<%--	        <c:if test="${!status.last}">--%>
<%--	        	,--%>
<%--	        </c:if>--%>
<%--	    </c:forEach>--%>
<%--	    ],--%>
		"spes":[
		{
		"dictId":"",
		"dictName":"请选择"
		},
		<c:forEach items="${spes}" var="dict" varStatus="status">
			{
			"dictId":${pdfn:toJsonString(dict.dictId)},
			"dictName":${pdfn:toJsonString(dict.dictName)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>]
    </c:if>
}
