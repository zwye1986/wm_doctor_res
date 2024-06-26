<table class="xllist" style="width:100%;">
	<tr>
		<th><input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号</th>
		<th>姓名</th>
		<th>证件号码</th>
		<th>性别</th>
		<th>培训届别</th>
		<th>培训基地</th>
		<th>培训专业</th>
		<th>联系方式</th>
		<th>学员预约时间</th>
		<th>状态</th>
	</tr>
	<c:forEach items="${list}" var="info" varStatus="i">
		<tr>
			<td><input type="checkbox" class="check" value="${info.detailFlow}" statusId="${info.auditStatusId}" signStatusId="${info.siginStatusId}" onclick="checkSingel(this)">&nbsp;${i.index + 1}</td>
			<td>${info.userName}</td>
			<td>${info.idNo}</td>
			<td>${info.sexName}</td>
			<td>${info.sessionNumber}</td>
			<td>${info.orgName}</td>
			<td>${info.trainingTypeName}</td>
			<td>${info.userPhone}</td>
			<td>${info.orderTime}</td>
			<td>${info.auditStatusName}</td>
		</tr>
	</c:forEach>
</table>
<div id="detail"></div>
<div style="float:right;margin-top:100px;">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
	<pd:pagination toPage="toPage1"/>
</div>