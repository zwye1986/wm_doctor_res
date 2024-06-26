<table border="0" cellpadding="0" cellspacing="0" class="course-table">
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
			<td>
			<c:if test="${info.auditStatusId ne 'PassedTwo' && info.auditStatusId ne 'UnPassedTwo' || isAdmin eq 'Y'}">
				<input type="checkbox" class="check" value="${info.detailFlow}" statusId="${info.auditStatusId}" signStatusId="${info.siginStatusId}" onclick="checkSingel(this)">
				&nbsp;${i.index + 1}
			</c:if>
		</td>
			<td>${info.userName}</td>
			<td>${info.idNo}</td>
			<td>${info.sexName}</td>
			<td>${info.period}</td>
			<td>${info.orgName}</td>
			<td>${info.majorName}</td>
			<td>${info.userPhone}</td>
			<td>${info.orderTime}</td>
			<td>${info.auditStatusName}</td>
		</tr>
	</c:forEach>
</table>
<div id="detail"></div>
<div class="pages text-center">
	<div class="pagination">
		<ul class="pagination">
			<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
			<pd:pagination-njmuedu toPage="toPage1"/>
		</ul>
	</div>

</div>