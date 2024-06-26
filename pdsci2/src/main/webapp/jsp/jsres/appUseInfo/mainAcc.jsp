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
		function appUser(tag){
			$('.tab_select').removeClass('tab_select');
			$(tag).removeClass('tab');
			$(tag).addClass('tab_select');
			if("${param.userListScope}"=="local"){
				jboxLoad("hosContent","<s:url value='/jsres/statistic/statisticsAppUserAcc?userListScope=${param.userListScope}'/>",true);
			}else if("${param.userListScope}"=="global"||"${param.userListScope}"=="charge") {
				jboxLoad("hosContent", "<s:url value='/jsres/statistic/statisticsAppUserForOrgAcc'/>", true);
			}
		}
		function appUserTable(tag){
			$('.tab_select').removeClass('tab_select');
			$(tag).removeClass('tab');
			$(tag).addClass('tab_select');
			jboxLoad("hosContent","<s:url value='/jsres/appUseInfo/appUserTableAcc?userListScope=${param.userListScope}'/>",true);
			jboxStartLoading();
		}
	</script>
</head>
<body>
<div class="main_hd">
	<h2>APP使用情况统计</h2>
	<div class="title_tab">
			<ul>
				<li class="tab" onclick="appUser(this);"><a>统计图</a></li>
				<li class="tab" onclick="appUserTable(this);"><a>统计表<font color="red" style="font-size:12px">（列表为未使用app学员）</font></a></li>
			</ul>
	</div>
</div>
<div class="main_bd">
	<div id="hosContent">
	</div>
</div>
</body>
</html>
