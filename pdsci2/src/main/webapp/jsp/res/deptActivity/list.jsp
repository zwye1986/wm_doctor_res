<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<table class="xllist">
	<tr>
		<th>科室</th>
		<th>科室类型</th>
		<th>月度</th>
		<th>审核状态</th>
		<th>发布状态</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${list}" var="b">
		<tr>
			<td>${b.deptName}</td>
			<td>${b.planTypeName}</td>
			<td>${b.planDate}</td>
			<td>${b.auditStatusName}</td>
			<td>
				<c:if test="${b.isReport eq 'Y'}">
					已发布
				</c:if>
				<c:if test="${b.isReport eq 'N'}">
					未发布
				</c:if>
			</td>
			<td>
				<c:if test="${role eq 'head'}">
					<c:if test="${b.isReport eq 'N'}">
						<c:if test="${b.auditStatusId eq 'Save'}">
							<a href="javascript:void(0)" onclick="addPlan('${b.planFlow}')">修改</a>
							<a href="javascript:void(0)" onclick="delPlan('${b.planFlow}')">删除</a>
							<a href="javascript:void(0)" onclick="subPlan('${b.planFlow}')">提交</a>
						</c:if>
						<c:if test="${b.auditStatusId eq 'UnPass'}">
							<a href="javascript:void(0)" onclick="addPlan('${b.planFlow}')">修改</a>
							<a href="javascript:void(0)" onclick="delPlan('${b.planFlow}')">删除</a>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${role ne 'head'}">
					<c:if test="${b.auditStatusId eq 'Submit'}">
						<a href="javascript:void(0)" onclick="auditPlan('${b.planFlow}')">审核</a>
					</c:if>
					<c:if test="${b.auditStatusId eq 'Pass'}">
						<c:if test="${b.isReport eq 'N'}">
							<a href="javascript:void(0)" onclick="reportPlan('${b.planFlow}')">发布</a>
						</c:if>
					</c:if>
				</c:if>
				<a href="javascript:void(0)" onclick="show('${b.planFlow}')">查看</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty list}">
	<tr>
		<td colspan="6" >无记录！</td>
	</tr>
	</c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>

      
