<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script type="text/javascript">

function del(impFlow) {
	jboxConfirm("确认删除该批次导入的成绩记录?",  function(){
		jboxStartLoading();
		var url = "<s:url value='/gyxjgl/user/delImpRecord'/>?impFlow="+impFlow;
		jboxPost(url, null, function(resp){
				jboxEndLoading();
				if("${GlobalConstant.DELETE_SUCCESSED}" ==resp){
					search();
					location.reload();
				}
			}, null , true);
	});
}

</script>

</head>
<body>
<div class="mainright" id="content" style="background: white;margin-top: 0px;width: 100%">
<div class="content">

	<table class="xllist" style="width:100%; margin-top: 20px;">
		<tr>
			<th style="width: 40%;">导入时间</th>
			<th style="width: 25%;">导入记录数量</th>
			<th style="width: 25%;">操作人</th>
			<th style="width: 10%;">操作</th>
		</tr>
		<c:forEach items="${importRecordList}" var="record">
		<tr>
			<td>${pdfn:transDateTime(record.impTime) }</td>
			<td>${record.impNum }</td>
			<td>${record.impUserName}</td>
			<td>
				[<a onclick="del('${record.impFlow}');" style="cursor: pointer;color: blue;">删除</a>]
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty importRecordList}">
		<tr><td colspan="5">无记录</td></tr>
		</c:if>
	</table>
	<div>
	   	<c:set var="pageView" value="${pdfn:getPageView(importRecordList)}" scope="request"/>
		<pd:pagination toPage="toAssHole"/>
	</div>

</div>	
</div>
</body>	
</html>