<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<c:if test="${empty exception }">				
	<c:set var="exception" value="${pageContext.exception}" scope="request"></c:set>
	<% if(null != exception) exception.printStackTrace(); %>
</c:if>
<%@include file="/jsp/common/doctype.jsp" %>
<html>  
<head>
	<title>${applicationScope.sysCfgMap['sys_title_name']}V${applicationScope.sysCfgMap['sys_version']}<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
	<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
	<link rel="shortcut icon" href="<s:url value='/favicon.ico'/>" />
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
	</jsp:include>
</head>  
<body>

		<c:choose>
		<c:when test="${empty applicationScope.sysCfgMap['sys_logout_url']}">

			<%
				try{
			%>
			<div class="content">
				<div class="title1 clearfix">
					<div id="tagContent">
						<div class="tagContent selectTag" id="tagContent0" style="padding-left: 200px">
							<table width="800" cellpadding="0" cellspacing="0" class="basic">
								<tr>
									<th style="text-align: center;">发生错误啦：${exception.message }</th>
								</tr>
								<tr>
									<td style="text-align: center;">
											<%--<input class="search" type="button" value="查看详情" onclick="showErrors();" />--%>
										&#12288;&#12288;&#12288;&#12288;
										<input class="search" type="button" value="返&#12288;&#12288;回" onclick="window.history.back(-1);" />
									</td>
								</tr>
								<tr>
									<td>
										<div id="errors" style="display: none; color: red; width: 800px;">
												${exception.message }
											<c:forEach items="${exception.stackTrace }" var="e">
												${e }<br />
											</c:forEach>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<%
				}catch (Exception e){
				%>服务器错误(500)  <a href="javascript:window.history.back(-1);">返回</a> <%
				}
			%>
		</c:when>
		<c:otherwise>

			<script type="text/javascript" defer="defer">
				$(document).ready(function(){
					if(window.parent.frames['mainIframe']!=null){
						window.setTimeout(function () {window.parent.location.href='${applicationScope.sysCfgMap["sys_logout_url"]}';},3000);
					}else{
						window.setTimeout(function () {window.location.href='${applicationScope.sysCfgMap["sys_logout_url"]}';},3000);
					}
				});
			</script>
		</c:otherwise>

		</c:choose>
</body>
</html>