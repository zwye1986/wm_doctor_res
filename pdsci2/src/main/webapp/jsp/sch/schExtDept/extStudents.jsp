
<script type="text/javascript">
	function toPage(page){
		if(page){
			getExtStudents(page);
		}else {
			page = 1;
			getExtStudents(page);
		}
	}
	function modifyProcessTime(resultFlow,processFlow)
	{
		var url="<s:url value='/sch/schExtDept/modifyProcessTime'/>?resultFlow="+resultFlow+"&processFlow="+processFlow;
		jboxOpen(url,"修改时间",400,200,true);
	}

</script>
<div>
	<div>

	</div>
	<table class="basic" style="width: 100%;text-align: center">
		<tr>
			<td colspan="6" style="text-align: left;border-right: 0px;" ><span style="font-size: 18px;">学员外院轮转安排情况</span></td>
			<td style="text-align: right;border-left: 0px;"><input type="button" value="导&#12288;出" class="search" style="padding-right: 10px;" onclick="exportExtStudents();"/></td>
		</tr>
		<tr>
			<th style="text-align: center;padding: 0;">学员姓名</th>
			<th style="text-align: center;padding: 0;">所在医院</th>
			<th style="text-align: center;padding: 0;">轮转科室</th>
			<th style="text-align: center;padding: 0;">开始时间</th>
			<th style="text-align: center;padding: 0;">结束时间</th>
			<th style="text-align: center;padding: 0;">轮转时长</th>
			<th style="text-align: center;padding: 0;">操作</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td  style="text-align: center;padding: 0;">${user.userName}</td>
				<td  style="text-align: center;padding: 0;">${user.orgName}</td>
				<td  style="text-align: center;padding: 0;">${user.schDeptName}</td>
				<td  style="text-align: center;padding: 0;">${user.schStartDate}</td>
				<td  style="text-align: center;padding: 0;">${user.schEndDate}</td>
				<td  style="text-align: center;padding: 0;"><p style="text-align: right;width: 66%">${user.schMonth}个月</p></td>
				<td  style="text-align: center;padding: 0;">
					<a onclick="modifyProcessTime('${user.resultFlow}','${user.processFlow}');" style="cursor:pointer;color:blue;">修改轮转时间</a>&#12288;
					<c:if test="${(empty user.teacherUserFlow)and(empty user.headUserFlow)}">
						<a onclick="delDoctorProcess('${user.resultFlow}','${user.processFlow}');" style="cursor:pointer;color:blue;">删除</a>
					</c:if>
					<c:if test="${!((empty user.teacherUserFlow)and(empty user.headUserFlow))}">
						&#12288;&#12288;
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty users}">
		<tr>
			<td style="text-align: center;padding: 0;" colspan="7">暂无学员在外院轮转</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(users)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>
</div>