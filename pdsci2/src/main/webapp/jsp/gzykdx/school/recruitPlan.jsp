<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(){
		$("#searchForm").submit();
	}
	function addInfo(rargetFlow){
		var title = rargetFlow == ""?"新增":"编辑";
		var url = "<s:url value='/gzykdx/school/addRecruitPlan?rargetFlow='/>"+rargetFlow;
		jboxOpen(url, title,600,360,true);
	}
	function allocateInfo(rargetFlow){
		jboxLoad("initCont","<s:url value='/gzykdx/school/allocateDetail'/>?rargetFlow="+rargetFlow,true);
	}
</script>
</head>
<body id="initCont">
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gzykdx/school/recruitTargetPlan"/>" method="post">
			<div class="choseDivNewStyle">
				<span></span>年份：
				<input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="recruitYear" value="${param.recruitYear}"/>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
				<input type="button" class="search" value="新&#12288;增" onclick="addInfo('')"/>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:120px;">年份</th>
				<th style="width:120px;">学术学位</th>
				<th style="width:120px;">专业学位</th>
				<th style="width:120px;">合计</th>
				<th style="width:140px;">操作</th>
			</tr>
			<c:forEach items="${dataList}" var="info">
				<tr>
					<td>${info.recruitYear}</td>
					<td>${info.academicNum}</td>
					<td>${info.specializedNum}</td>
					<td>${info.allNum}</td>
					<td>
						<a onclick="addInfo('${info.rargetFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>
						<a onclick="allocateInfo('${info.rargetFlow}');" style="cursor:pointer;color:#4195c5;">分配</a>
				</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>	
</html>