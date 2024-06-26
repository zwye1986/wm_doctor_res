
<script type="text/javascript">

</script>
<div>
	<div>

	</div>
	<table class="basic" style="width: 100%;text-align: center">
		<tr>
			<td colspan="6" style="text-align: left;border-right: 0px;" ><span style="font-size: 18px;">学员外院轮转安排情况</span></td>
			<td style="text-align: right;border-left: 0px;"><input type="button" value="导出" class="search" style="padding-right: 0px;" onclick="exportExtStudents();"/></td>
		</tr>
		<tr>
			<th style="text-align: center;">学员姓名</th>
			<th style="text-align: center;">所在医院</th>
			<th style="text-align: center;">轮转科室</th>
			<th style="text-align: center;">开始时间</th>
			<th style="text-align: center;">结束时间</th>
			<th style="text-align: center;">轮转时长</th>
			<th style="text-align: center;">操作</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td  style="text-align: center;">${user.userName}</td>
				<td  style="text-align: center;">${user.orgName}</td>
				<td  style="text-align: center;">${user.schDeptName}</td>
				<td  style="text-align: center;">${user.schStartDate}</td>
				<td  style="text-align: center;">${user.schEndDate}</td>
				<td  style="text-align: center;">${user.schMonth}个月</td>
				<td  style="text-align: center;">
					<c:if test="${(empty user.teacherUserFlow)and(empty user.headUserFlow)}">
						<a onclick="delDoctorProcess('${user.resultFlow}','${user.processFlow}');" style="cursor:pointer;color:#4195c5;">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty users}">
		<tr>
			<td style="text-align: center" colspan="7">暂无学员在外院轮转</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>
</div>