<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
	<table style="width:100%;text-align: center">
		<tr>
			<th style="width: 6.6%">序号</th>
			<th>计划开票日期</th>
			<th>计划开票金额</th>
			<th>实际开票金额</th>
			<th>状态</th>
			<c:if test="${type ne 'audit'}">
				<th>操作</th>
			</c:if>
		</tr>
		<c:if test="${empty billPlanList}">
			<tr>
				<td colspan="6">无记录</td>
			</tr>
		</c:if>
		<c:set var="allFund" value="0"></c:set>
		<c:forEach items="${billPlanList }" var="billPlan" varStatus="num">
			<c:set var="allFund" value="${allFund +billPlan.billPayFund}"></c:set>
			<tr>
				<td>${num.count}</td>
				<td>${billPlan.billPlanDate}</td>
				<td><label class="money">${billPlan.billPayFund}</label>元</td>
				<td><label class="money">${billPlan.billBalanceFund}</label>元</td>
				<td>${billPlan.billPlanStatusName}</td>
				<c:if test="${type ne 'audit'}">
				<td>
					<c:if test="${type eq 'plan'}">
						<c:if test="${empty billBalanceMap[billPlan.billPlanFlow]}">
							<a style='color: #3891ef;' href="javascript:delPlan('${billPlan.contractFlow }','${billPlan.billPlanFlow }');">删除</a>
							<a style='color: #3891ef;' href="javascript:editPlan('${billPlan.contractFlow }','${billPlan.billPlanFlow }');">编辑</a>
						</c:if>
					</c:if>
					<c:if test="${type eq 'action'}">
						<c:if test="${ (empty balanceMoneyMap[billPlan.billPlanFlow]?'0':balanceMoneyMap[billPlan.billPlanFlow]) < billPlan.billPayFund and allBalanceMoney<contract.contractFund}">
							<a  style='color: #3891ef;' href="javascript:addBillIn('${billPlan.contractFlow }','${billPlan.billPlanFlow }');">新增开票</a>
						</c:if>
					</c:if>
				</td>
				</c:if>
			</tr>
			<c:if test="${not empty billBalanceMap[billPlan.billPlanFlow]}">
				<tr>
					<td></td>
					<td colspan="6">
						<table width="100%">
							<tr>
								<th>序号</th>
								<th>开票日期</th>
								<th>开票金额</th>
								<th>发票号</th>
								<th>开票申请人</th>
								<th>开票经办人</th>
								<th>开票经办时间</th>
								<th>开票操作人</th>
								<th>开票操作时间</th>
								<th>审核状态</th>
								<th>审核人</th>
								<th>审核意见</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${billBalanceMap[billPlan.billPlanFlow]}" var="billBalance" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${billBalance.billDate}</td>
									<td>${billBalance.billFund}</td>
									<td>${billBalance.billNo}</td>
									<td>${billBalance.billApplyUserName}</td>
									<td>${billBalance.handleUserName}</td>
									<td>${billBalance.handleTime}</td>
									<td>${billBalance.createUserName}</td>
									<td>${pdfn:transDateTime(billBalance.createTime)}</td>
									<td>${billBalance.statusName}</td>
									<td>
										<c:if test="${billBalance.statusId ne planBalanceStatusEnumAuditing.id}">
											${billBalance.auditUserName}
										</c:if>
									</td>
									<td>
										<c:if test="${billBalance.statusId ne planBalanceStatusEnumAuditing.id}">
											${billBalance.auditReason}
										</c:if>
									</td>
										<td>
											<c:if test="${type eq 'action'}">
												<c:if test="${billBalance.statusId eq planBalanceStatusEnumAuditBack.id}">
													<a style="color: #3891ef;" href="javascript:editPlanIn('${billPlan.contractFlow }','${billPlan.billPlanFlow }','${billBalance.recordFlow}');">修改开票
													</a>
												</c:if>
											</c:if>
											<c:if test="${type eq 'audit'}">
												<c:if test="${billBalance.statusId eq planBalanceStatusEnumAuditing.id}">
													<a style="color: #3891ef;" href="javascript:auditBalance('${billPlan.contractFlow }','${billBalance.recordFlow}');">审核开票</a>
												</c:if>
											</c:if>
											<c:if test="${type eq 'kp'}">
												<c:if test="${billBalance.statusId eq planBalanceStatusEnumAuditPass.id and (empty billBalance.billTrackeNumber)}">
													<a style="color: #3891ef;" href="javascript:editTracke('${billPlan.contractFlow }','${billBalance.recordFlow}','N');">填写快递信息</a>
												</c:if>
											</c:if>
											<c:if test="${billBalance.statusId eq planBalanceStatusEnumAuditPass.id and (not empty billBalance.billTrackeNumber)}">
												<a style="color: #3891ef;" href="javascript:editTracke('${billPlan.contractFlow }','${billBalance.recordFlow}','Y');">查看快递信息</a>
											</c:if>
										</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
<script type="text/javascript">
	$(document).ready(function(){

		<c:if test="${type eq 'plan'}">
			<c:if test="${contract.contractFund <=allFund}">
				$("#btn_${contract.contractFlow }").html("");
			</c:if>
			<c:if test="${contract.contractFund >allFund}">
				var a=$("<a  style='color: #3891ef;' >新增开票计划</a>").attr("href", "javascript:addPlan('${contract.contractFlow }');");
				$("#btn_${contract.contractFlow }").empty();
				$("#btn_${contract.contractFlow }").append(a);
			</c:if>
		</c:if>
		moneyMask();
	});
</script>