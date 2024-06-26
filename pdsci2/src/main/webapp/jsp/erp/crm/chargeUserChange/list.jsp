<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>申请状态</th>
			<th>原合同负责部门</th>
			<th>原合同负责人</th>
			<th>原合同负责人2</th>
			<th>变更为负责部门</th>
			<th>变更为负责人</th>
			<th>变更为负责人2</th>
			<th>申请人</th>
			<th>申请时间</th>
			<th>审核人</th>
			<th>审核时间</th>
			<c:if test="${param.role eq 'global'}">
				<th>操作</th>
			</c:if>
		</tr>
		<c:forEach items="${changes }" var="change">
		<tr>
			<td>${change.statusName }</td>
			<td>${change.oldSignDeptName }</td>
			<td>${change.oldChargeUserName }</td>
			<td>${change.oldChargeUser2Name }</td>
			<td>${change.signDeptName }</td>
			<td>${change.chargeUserName }</td>
			<td>${change.chargeUser2Name }</td>
			<td>${change.applyUserName }</td>
			<td>${change.applyTime}</td>
			<td>${change.auditUserName }</td>
			<td>${change.auditTime}</td>
			<c:if test="${param.role eq 'global'}">
				<td>
					<c:if test="${change.statusId eq userChangeStatusEnumApplying.id}">
						<a href="javascript:void(0);" onclick="audit('${change.changeFlow}','${userChangeStatusEnumApplyBack.id}')" class="parentA">审核驳回</a>
						<a href="javascript:void(0);" onclick="audit('${change.changeFlow}','${userChangeStatusEnumApplyPass.id}')" class="parentA">审核通过</a>
					</c:if>
				</td>
			</c:if>
		</tr>
		</c:forEach>
		<c:if test="${empty changes}">
		<tr>
			<td colspan="11" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(changes)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>