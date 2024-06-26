<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
	<table style="width:100%;text-align: center">
		<tr>
			<th style="width: 6.6%">序号</th>
			<th>计划日期</th>
			<th>计划金额</th>
			<th>
				<c:set var="contractCategoryId" value="${contract.contractCategoryId}"/>
				<c:if test="${contractCategoryId == contractCategoryEnumPurchase.id}">付款金额</c:if>
				<c:if test="${contractCategoryId != contractCategoryEnumPurchase.id}">到账金额</c:if>
			</th>
			<th>状态</th>
			<c:if test="${type ne 'audit'}">
				<th>操作</th>
			</c:if>
		</tr>
		<c:if test="${empty payPlanList}">
			<tr>
				<td colspan="6">无记录</td>
			</tr>
		</c:if>
		<c:set var="allFund" value="0"></c:set>
		<c:forEach items="${payPlanList }" var="payPlan" varStatus="num">
			<c:set var="allFund" value="${allFund +payPlan.payFund}"></c:set>
			<tr>
				<td>${num.count}</td>
				<td>${payPlan.planDate}</td>
				<td><label class="money">${payPlan.payFund}</label>元</td>
				<td><label class="money">${payPlan.balanceFund}</label>元</td>
				<td>${payPlan.planStatusName}</td>
				<c:if test="${type ne 'audit'}">
					<td>
						<c:if test="${type eq 'plan'}">
							<c:if test="${empty balanceMap[payPlan.planFlow]}">
								<a style="color: #3891ef;" href="javascript:delPlan('${payPlan.contractFlow }','${payPlan.planFlow }');">删除</a>
								<a style="color: #3891ef;" href="javascript:editPlan('${payPlan.contractFlow }','${payPlan.planFlow }');">编辑</a>
							</c:if>
						</c:if>
						<c:if test="${type eq 'action'}">
							<c:if test="${ (empty balanceMoneyMap[payPlan.planFlow]?'0':balanceMoneyMap[payPlan.planFlow]) < payPlan.payFund and allBalanceMoney<contract.contractFund}">
								<a style="color: #3891ef;" href="javascript:addPlanIn('${payPlan.contractFlow }','${payPlan.planFlow }');">
									<c:if test="${contract.contractCategoryId == contractCategoryEnumPurchase.id}">新增付款</c:if>
									<c:if test="${contract.contractCategoryId != contractCategoryEnumPurchase.id}">新增到账</c:if>
								</a>
							</c:if>
						</c:if>
					</td>
				</c:if>
			</tr>
			<c:forEach items="${balanceMap[payPlan.planFlow]}" var="payBalance" varStatus="status">
				<tr>
					<td></td>
					<td colspan="5">
						<ul>
							<li>
								<span style="display:inline-block;text-align:left;width: 3%;"></span>
								<span>
										[${status.count}]&nbsp;${payBalance.payDate}&nbsp;
										<c:if test="${contractCategoryId == contractCategoryEnumPurchase.id}">付款</c:if>
										<c:if test="${contractCategoryId != contractCategoryEnumPurchase.id}">到账</c:if>
											&nbsp;<label class="money">${payBalance.payFund}</label>元 （开票申请人：${payBalance.billApplyUserName}&#12288;操作人：${payBalance.createUserName}

											<c:if test="${payBalance.statusId ne planBalanceStatusEnumAuditing.id}">
												&#12288;审核人：${payBalance.auditUserName}
											</c:if>
											&#12288;审核状态：${payBalance.statusName}
											<c:if test="${payBalance.statusId eq planBalanceStatusEnumAuditBack.id}">
												&#12288;审核意见：${payBalance.auditReason}
											</c:if>
									）&#12288;
								</span>
								<span style="">
									<c:if test="${type eq 'action'}">
										<c:if test="${payBalance.statusId eq planBalanceStatusEnumAuditBack.id}">
											<a style="color: #3891ef;" href="javascript:editPlanIn('${payPlan.contractFlow }','${payPlan.planFlow }','${payBalance.recordFlow}');">
												<c:if test="${contract.contractCategoryId == contractCategoryEnumPurchase.id}">修改付款</c:if>
												<c:if test="${contract.contractCategoryId != contractCategoryEnumPurchase.id}">修改到账</c:if>
											</a>
										</c:if>
									</c:if>
									<c:if test="${type eq 'audit'}">
										<c:if test="${payBalance.statusId eq planBalanceStatusEnumAuditing.id}">
											<a style="color: #3891ef;" href="javascript:auditBalance('${payPlan.contractFlow }','${payBalance.recordFlow}');">审核到帐</a>
										</c:if>
									</c:if>
								</span>

							</li>
						</ul>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
<script type="text/javascript">
	$(document).ready(function(){

		<c:if test="${type eq 'plan'}">
			<c:if test="${contract.contractFund <=allFund}">
				$("#btn_${contract.contractFlow }").html("");
			</c:if>
			<c:if test="${contract.contractFund >allFund}">
				var a=$("<a style='color: #3891ef;'>新增到帐计划</a>").attr("href", "javascript:addPlan('${contract.contractFlow }');");
				$("#btn_${contract.contractFlow }").empty();
				$("#btn_${contract.contractFlow }").append(a);
			</c:if>
		</c:if>
		moneyMask();
	});
</script>