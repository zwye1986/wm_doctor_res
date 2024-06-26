<%@ page import="com.pinde.core.util.StringUtil" %>
<%@ page import="com.pinde.sci.common.InitConfig" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="false"/>
	<jsp:param name="jbox" value="false"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="false"/>
	<jsp:param name="jquery_datePicker" value="false"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="false"/>
	<jsp:param name="jquery_placeholder" value="false"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
</head>
<body>
	<c:choose>
		<c:when test="${empty applicationScope.sysCfgMap['sys_index_url']}">
			<jsp:forward page="/login"></jsp:forward>
		</c:when>
		<c:otherwise>
			<%--<c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jsres'}">--%>
				<%--&lt;%&ndash;&lt;%&ndash;%>--%>

					<%--&lt;%&ndash;String getPath = request.getServerName();//域名&ndash;%&gt;--%>
					<%--&lt;%&ndash;//StringUtil.defaultString(request.getRequestURL().toString());&ndash;%&gt;--%>
					<%--&lt;%&ndash;String defaultUrl= InitConfig.getSysCfg("sys_index_url");&ndash;%&gt;--%>
					<%--&lt;%&ndash;String url=defaultUrl;&ndash;%&gt;--%>
					<%--&lt;%&ndash;String title="江苏省住院医师规范化培训管理平台";&ndash;%&gt;--%>

					<%--&lt;%&ndash;System.out.println("默认登录页"+defaultUrl);&ndash;%&gt;--%>
					<%--&lt;%&ndash;try{&ndash;%&gt;--%>
						<%--&lt;%&ndash;System.out.println(getPath);&ndash;%&gt;--%>
						<%--&lt;%&ndash;Map<String,String>urlMap= InitConfig.jsResLoginUrlMap;&ndash;%&gt;--%>

						<%--&lt;%&ndash;if(urlMap!=null &&urlMap.size()>0) {&ndash;%&gt;--%>
							<%--&lt;%&ndash;for(String key : urlMap.keySet()){//域名&ndash;%&gt;--%>
								<%--&lt;%&ndash;String tempurl=urlMap.get(key);&ndash;%&gt;--%>
								<%--&lt;%&ndash;if(StringUtil.isNotBlank(tempurl))&ndash;%&gt;--%>
								<%--&lt;%&ndash;{&ndash;%&gt;--%>
									<%--&lt;%&ndash;if (getPath.equals(key)) {&ndash;%&gt;--%>
										<%--&lt;%&ndash;if(StringUtil.isNotBlank(InitConfig.jsResLoginTitleMap.get(key)))&ndash;%&gt;--%>
										<%--&lt;%&ndash;{&ndash;%&gt;--%>
											<%--&lt;%&ndash;title=InitConfig.jsResLoginTitleMap.get(key);&ndash;%&gt;--%>
										<%--&lt;%&ndash;}&ndash;%&gt;--%>

										<%--&lt;%&ndash;request.getSession().setAttribute("loginUrl",tempurl);&ndash;%&gt;--%>
										<%--&lt;%&ndash;request.getSession().setAttribute("sysTitle",title);&ndash;%&gt;--%>
										<%--&lt;%&ndash;application.setAttribute("loginUrl",tempurl);&ndash;%&gt;--%>
										<%--&lt;%&ndash;application.setAttribute("sysTitle",title);&ndash;%&gt;--%>
										<%--&lt;%&ndash;url=tempurl;&ndash;%&gt;--%>
										<%--&lt;%&ndash;request.getRequestDispatcher(tempurl).forward(request,response);&ndash;%&gt;--%>
										<%--&lt;%&ndash;return;&ndash;%&gt;--%>
									<%--&lt;%&ndash;}&ndash;%&gt;--%>
								<%--&lt;%&ndash;}&ndash;%&gt;--%>
							<%--&lt;%&ndash;}&ndash;%&gt;--%>
						<%--&lt;%&ndash;}&ndash;%&gt;--%>
						<%--&lt;%&ndash;request.getSession().setAttribute("loginUrl",url);&ndash;%&gt;--%>
						<%--&lt;%&ndash;request.getSession().setAttribute("sysTitle",title);&ndash;%&gt;--%>
						<%--&lt;%&ndash;application.setAttribute("loginUrl",url);&ndash;%&gt;--%>
						<%--&lt;%&ndash;application.setAttribute("sysTitle",title);&ndash;%&gt;--%>
						<%--&lt;%&ndash;request.getRequestDispatcher(url).forward(request,response);&ndash;%&gt;--%>
						<%--&lt;%&ndash;return;&ndash;%&gt;--%>
					<%--&lt;%&ndash;}catch (Exception e)&ndash;%&gt;--%>
					<%--&lt;%&ndash;{&ndash;%&gt;--%>

					<%--&lt;%&ndash;}&ndash;%&gt;--%>


				<%--&lt;%&ndash;%>&ndash;%&gt;--%>
			<%--</c:if>--%>
			<%--<c:if test="${!(applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jsres')}">--%>
				<jsp:forward page="${applicationScope.sysCfgMap['sys_index_url']}"></jsp:forward>
			<%--</c:if>--%>

		</c:otherwise>
	</c:choose>
</body>
</html>