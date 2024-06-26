<table  class="basic" width="100%">
	<tr>
		<td></td>
		<td>标准科室</td>
		<td>医院轮转科室</td>
		<td>开始时间</td>
		<td>结束时间</td>
		<td>开放人数</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${extDepts}" var="dept">
		<tr>
			<td><input name="externalDept" type="checkbox" value="${dept.recordFlow}"></td>
			<td>${dept.standardDeptName}</td>
			<td>${dept.schDeptName}</td>
			<td>${dept.startDate}</td>
			<td>${dept.endDate}</td>
			<td>${dept.peopleNum}</td>
			<td>
				<a href="javascript:changePeopleNum('${dept.recordFlow}')" style="color: blue">编辑</a>
				<a href="javascript:delExternalDept('${dept.recordFlow}')" style="color: blue">删除</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty extDepts}">
		<td colspan="7" style="text-align: center;">
			暂未设置开放时间
		</td>
	</c:if>
</table>
