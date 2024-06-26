
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>工作站名称</th>
			<th>登录地址</th>
			<th>退出地址</th>
			<th>登录图片</th>
			<th>系统头图片</th>
			<th>系统名称</th>
			<th>是否为系统默认</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="cfg">
		<tr>
			<td>${cfg.wsName }</td>
			<td>${cfg.loginUrl }</td>
			<td>${cfg.logoutUrl }</td>
			<td>${cfg.sysLoginImg }</td>
			<td>${cfg.sysHeadImg }</td>
			<td>${cfg.sysTitleName }</td>
			<td>
				<c:if test="${cfg.isDefault eq 'Y'}">是</c:if>
				<c:if test="${cfg.isDefault eq 'N'}">否</c:if>
			</td>
			<td>
				<a href="javascript:edit('${cfg.wsId}','Y');" >编辑</a>
				<c:if test="${cfg.isDefault eq 'N'}">
					<a href="javascript:updateDefault('${cfg.wsId}','Y');" >设置为系统默认</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr>
			<td colspan="6" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>