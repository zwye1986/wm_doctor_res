
<script type="text/javascript">

</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<tr>
		<th >基地名称</th>
		<th >应签人数</th>
		<th >过程管理开通人数</th>
		<th >出科考试开通人数</th>
	</tr>
	<c:forEach items="${list}" var="org">
		<tr>
			<td>${org.orgName }</td>
			<td>${org.zsCount}</td>
			<td>${org.guochengCount}</td>
			<td>${org.ckkCount}</td>
		</tr>
	</c:forEach>
	<c:if test="${empty list}">
		<tr>
			<td colspan="20" style="border:none;">暂无记录！</td>
		</tr>
	</c:if>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>