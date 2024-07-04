
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres_en.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="yw">
	<%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">--%>
    <div class="top" onclick="window.location.href='<s:url value='/inx/czyyjxrecruit'/>'" style="cursor: pointer;">${sysCfgMap['jx_top_name_en']}</div>

	<div class="content" style="text-align: center;">
	<div class="notPass wjpsw" >
   <div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">User activation</h1>
	     <div style="margin-left: 50px;margin-top: 20px;">
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
			 Activation success!

			 	<a href="<s:url value='/inx/czyyjxrecruit'/>">Click login</a>

			 <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
				 <a href="<s:url value='/inx/xzjx'/>">Click login</a>
			 </c:if>
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
<%--<div class="footer">--%>
	<%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">--%>
		<%--主管单位：四川省卫生厅科教处 | 协管单位：四川省毕业后医学继续教育委员会--%>
	<%--</c:if>--%>

	<%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">--%>
		<%--主办单位：徐州中心医院--%>
	<%--</c:if>--%>
<%--</div>--%>
    <div class="footer"><jsp:include page="/jsp/czyyjxres/english_foot.jsp" flush="true"/></div>

</body>
</html>