<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_mask" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			moneyMask();
		});
		function moneyMask(){
			var moneyBox=$(".money");
			$.each(moneyBox,function(i,n){
				$(n).mask('000,000,000,000,000', {reverse: true});
			});
		}

		function reMoneyMask(){
			var moneyBox=$(".money");
			$.each(moneyBox,function(i,n){
				$(n).mask('000000000000000', {reverse: true});
			});
		}
		</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="" style="max-height: 550px;overflow: auto;width: 100%;">
	<table class="xllist" style="width:100%;text-align: center">
		<tr>
			<th colspan="9" style="text-align: left">开票情况</th>
		</tr>
		<tr>
			<th>计划开票日期</th>
			<th>计划开票金额</th>
			<th>实际开票日期</th>
			<th>实际开票金额</th>
			<th>发票号</th>
			<th>开票申请人</th>
			<th>开票经办人</th>
			<th>申请操作人</th>
			<th>审核操作人</th>
		</tr>
		<c:if test="${empty billPlanList}">
			<tr>
				<td colspan="9">无开票信息</td>
			</tr>
		</c:if>
		<c:forEach items="${billPlanList }" var="billPlan" varStatus="num">
			<c:set var="billBalances" value="${billBalanceMap[billPlan.billPlanFlow]}"></c:set>
			<c:if test="${empty billBalances}">
				<tr>
					<td>${billPlan.billPlanDate}</td>
					<td><label class="money">${billPlan.billPayFund}</label>元</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${not empty billBalances}">
				<c:forEach items="${billBalances}" var="billBalance" varStatus="status">
					<tr>
						<c:if test="${status.count eq 1}">
							<td rowspan="${fn:length(billBalances)}">${billPlan.billPlanDate}</td>
							<td rowspan="${fn:length(billBalances)}"><label class="money">${billPlan.billPayFund}</label>元</td>
						</c:if>
						<td>${billBalance.billDate}</td>
						<td>${billBalance.billFund}</td>
						<td>${billBalance.billNo}</td>
						<td>${billBalance.billApplyUserName}</td>
						<td>${billBalance.handleUserName}</td>
						<td>${billBalance.createUserName}</td>
						<td>${billBalance.auditUserName}</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
	</table>
	<table class="xllist"  style="width:100%;text-align: center;margin-top: 10px;">
		<tr>
			<th colspan="8" style="text-align: left">款项情况</th>
		</tr>
		<tr>
			<th>计划回款日期</th>
			<th>计划回款金额</th>
			<th>实际到帐日期</th>
			<th>实际到款金额</th>
			<th>开票申请人</th>
		</tr>
		<c:if test="${empty payPlanList}">
			<tr>
				<td colspan="5">无款项信息</td>
			</tr>
		</c:if>
		<c:forEach items="${payPlanList }" var="payPlan" varStatus="num">
			<c:set var="balances" value="${balanceMap[payPlan.planFlow]}"></c:set>
			<c:if test="${empty balances}">
				<tr>
					<td>${payPlan.planDate}</td>
					<td><label class="money">${payPlan.payFund}</label>元</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${not empty balances}">
				<c:forEach items="${balances}" var="balance" varStatus="status">
					<tr>
						<c:if test="${status.count eq 1}">
							<td rowspan="${fn:length(balances)}">${payPlan.planDate}</td>
							<td rowspan="${fn:length(balances)}"><label class="money">${payPlan.payFund}</label>元</td>
						</c:if>
						<td>${balance.payDate}</td>
						<td>${balance.payFund}</td>
						<td>${balance.billApplyUserName}</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
	</table>
	<div class="button">
		<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		moneyMask();
	});
</script>
</div>
</div>
</div>
</body>
</html>