<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/basic.css'/>" /> 
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/font.css'/>" /> 
<title>${info.infoTitle}</title>
<style type="text/css">
body,html{
	overflow:auto;
}
</style>
<script type="text/javascript">
	function doSearch() {
		$("#searchForm").submit();
	}
	function toPage(page){
		var form = $("#searchForm");
		$("#currentPage").val(page);
		form.submit();
	}
</script>
</head>

<body>
<div id="menhu">
	<%@include file="header.jsp" %>

   	 <form id="searchForm" action="<s:url value='/inx/szwsj/doSearch'/>" method="POST">
    	 <div id="search" style="margin: 10px 0;">
    	 	<input type="hidden" id="currentPage" name="currentPage">
	        <input type="hidden" id="columnId" name="columnId" value="${param.columnId}">
    	 	<input name="search" type="text" class="xltext" value="${param.search}"/>
			 <img src="<s:url value='/'/>jsp/inx/szwsj/images/portal/search.png" onclick="doSearch();"/>
    	 </div>	
	 </form>

	 
	<c:choose>
	     <c:when test="${not empty info}">
	        <div id="menhu_con">
		    	<div class="news_nav" style="height:auto;">
		   	   		<h2>${info.infoTitle}</h2><br/><b>${info.infoTime}</b>
		       		<p>${info.infoContent}</p>
		    	</div>
			</div>
	    </c:when>
	     
		<c:otherwise>
			<p><b>搜索结果：</b></p>
			<c:if test="${empty infoList}">
					<center>没有您要搜索的结果！</center>
			</c:if>
			<form id="myform">
			<div class="list">
	        	<ul>
	             <c:forEach items="${infoList}" var="news" varStatus="status">
			 	 <li>
				 	 <p>${news.infoTime}</p>
					 <b><a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${news.infoFlow }'/>">
						 <c:set var="search" value="${param.search}"></c:set>
						 ${pdfn:markKeyword(news.infoTitle,search)} 
					 </a></b>
					 <ul>
					 <li style="list-style: none;">
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 	 <a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${news.infoFlow }'/>">
						 <c:set var="infoContent" value=" ${pdfn:cutString(news.infoContent,130,true,6)}"></c:set>
						   ${pdfn:markKeyword(infoContent,search)}
					 </a>
					 </li>
					 </ul>
			 	 </li>
	    		</c:forEach>
	            </ul>
	        </div>
	        
	        <div id="ym">
		       	<c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
				<pd:pagination-inx toPage="toPage"/>	
	        </div>
	        </form>	
		</c:otherwise>
	</c:choose>
	<%@include file="footer.jsp" %>
</div>
</body>
</html>