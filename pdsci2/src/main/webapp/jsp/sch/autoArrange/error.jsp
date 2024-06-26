<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<style type="text/css">
		.errorMsg{
			background: url(<c:url value="/jsp/sch/image/jinggao.png"/>) no-repeat ;
			width: 100%;
			height: 60%;
			min-height: 400px;
			background-position:bottom;
			text-align: center;
			vertical-align: middle;
		}
		.errorMsg2{
			height: 40%;
			text-align: center;
		}
		span{
			margin-top: 60px;
			font-size: 20px;
			color: #000;
		}
	</style>
	<script>
		function goMenu(type)
		{
			if(1==type)
			{
				$('#res-yygly-pbgl-lzmb2',window.parent.document)[0].click();
			}
			if(2==type||3==type||0==type)
			{
				$('#res-yygly-rygl-rywh',window.parent.document)[0].click();
			}
		}
	</script>
</head>
<body>
		<div class="errorMsg">
		</div>
		<div class="errorMsg2">
			<c:if test="${error eq '1'}">
				还有<span style='color:blue;cursor: pointer;' onclick="goMenu(1);">${msg}</span>个方案未完成配置！
			</c:if>
			<c:if test="${error eq '2'}">
				还有<span style='color:blue;cursor: pointer;' onclick="goMenu(2);">${msg}</span>名学员未分配方案！
			</c:if>
			<c:if test="${error eq '3'}">
				还有<span style='color:blue;cursor: pointer;' onclick="goMenu(3);">${msg}</span>名学员未分配二级轮转方案！
			</c:if>
			<c:if test="${error eq '0'}">
				当前年级暂无学员信息，赶快去<span style='color:blue;cursor: pointer;' onclick="goMenu(0);">维护</span>吧！
			</c:if>
		</div>
</body>
</html>