
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>姓名</th>
			<th>年级</th>
			<th>培训基地</th>
			<th>培训专业</th>
			<th>证件号码</th>
			<th>培训手册
				<br>
				<input type="checkbox" name="choose" onclick="choose();"/>
			</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.userName }</td>
			<td>${userExt.sessionNumber }</td>
			<td>${userExt.orgName }</td>
			<td>${userExt.trainingSpeName}</td>
			<td>${userExt.idNo }</td>
			<td>
				<c:set var="key" value="stu_manual_${userExt.userFlow}"/>
				<input type="checkbox" name="userFlows" value="${userExt.userFlow}" ${cfgMap[key]==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }');"/>
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