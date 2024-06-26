
<table class="basic" style="width: 100%">
	<c:forEach items="${extDepts}" var="dept">
		<tr>
			<td style="width: 100%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<input type="checkbox" style="width: auto" name="recordFlows"
					   lastNum="${dept.peopleNum-peopleNumMap[dept.recordFlow]+0}" value="${dept.recordFlow}"
					   deptName="${dept.schDeptName}" date="${fn:substring(dept.startDate, 0, 7)}"
				/>
				<span>${fn:substring(dept.startDate, 0, 7)}（${dept.peopleNum-peopleNumMap[dept.recordFlow]+0}）</span>
			</td>
		</tr>
	</c:forEach>
</table>