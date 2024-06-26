<form id="searchForm2" method="post">
	<input type="hidden" id="currentPage2" name="currentPage">
	<span style="padding-left:10px;"></span>年&#12288;&#12288;级：
	<input type="text" name="sessionNumber" value="${param.sessionNumber}">
	<span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
	<input type="text" name="userName" value="${param.userName}">
	<span style="padding-left:10px;"></span>专&#12288;&#12288;业：
	<input type="text" name="majorName" value="${param.majorName}">
	<input type="button" value="查&#12288;询" class="search" onclick="toPage2(1);"/>
	<input type="button" value="就业情况登记" class="search" onclick="register('');"/>
</form>
<table class="basic" width="100%" style="margin-top:10px;">
	<tr style="font-weight: bold;">
		<th style="text-align:center;padding:0px;">序号</th>
		<th style="text-align:center;padding:0px;">年级</th>
		<th style="text-align:center;padding:0px;">姓名</th>
		<th style="text-align:center;padding:0px;">专业</th>
		<th style="text-align:center;padding:0px;">就业单位</th>
		<th style="text-align:center;padding:0px;">就业情况反馈</th>
		<th style="text-align:center;padding:0px;">操作</th>
	</tr>
	<c:forEach items="${dataList}" var="data" varStatus="i">
		<tr>
			<td style="text-align:center;padding:0px;">${i.count}</td>
			<td style="text-align:center;padding:0px;">${data.sessionNumber}</td>
			<td style="text-align:center;padding:0px;">${data.userName}</td>
			<td style="text-align:center;padding:0px;">${data.majorName}</td>
			<td style="text-align:center;padding:0px;">${data.unitName}</td>
			<td style="text-align:center;padding:0px;">${data.employFeedback}</td>
			<td style="text-align:center;padding:0px;">
				<a style="cursor: pointer;color: blue;" onclick="register('${data.recordFlow}');">编辑</a>
				<a style="cursor: pointer;color: blue;" onclick="delOption('${data.recordFlow}');">删除</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty dataList}">
		<td colspan="99" style="text-align: center;padding: 0px;">无记录！</td>
	</c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
<pd:pagination toPage="toPage2"/>