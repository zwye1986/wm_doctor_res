<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>江苏省住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".tab:first").click();
		});
		function selTag(tag,inx){
			$('.tab_select').removeClass('tab_select');
			$(tag).removeClass('tab');
			$(tag).addClass('tab_select');
			var url = '<s:url value="/jsres/evaluation/"/>'+inx;
			jboxLoad("hosContent",url,false);
		}
	</script>
</head>
<body>
<div class="main_hd">
	<h2>培训基地评估</h2>
	<div class="title_tab">
			<ul>
				<li class="tab" onclick="selTag(this,'evaluationInfo');"><a>评估指标(2019年版)</a></li>
				<c:if test="${countryOrgFlag eq 'Y'}">
					<li class="tab" onclick="selTag(this,'joinOrgEvaluationInfo');"><a>协同基地</a></li>
				</c:if>
			</ul>
	</div>
</div>
<div class="main_bd">
	<div id="hosContent">
	</div>
</div>
</body>
</html>
