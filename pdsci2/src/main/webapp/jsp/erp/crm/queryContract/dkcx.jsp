<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
	$(document).ready(function(){
		moneyMask();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<thead>
		<tr>
			<th>合同号</th>
			<th>合同档案号</th>
			<th>客户名称</th>
			<th>合同名称</th>
			<th>合同类别</th>
			<th>合同类型</th>
			<th>负责部门</th>
			<th>合同负责人</th>
			<th>合同负责人2</th>
			<th>签订日期</th>
			<th>合同金额</th>
			<th>财务应收金额</th>
			<th>合同应收金额</th>
			<th>开票申请人</th>
			<th>计划到帐金额</th>
			<th>计划到帐日期</th>
			<th>实际到帐金额</th>
			<th>实际到帐日期</th>
			<th>逾期</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="18">无记录</td>
		</tr>
		</c:if>
		<c:forEach items="${contractList }" var="contract">
		<tr >
			<td style="text-align: left;">
				<span style="margin-left:22px;"><a href="javascript:contractInfo('${contract.contractFlow }');">${contract.contractNo }</a></span>
			</td>
			<td >${contract.contractArchivesNo }</td>
			<td ><a href="javascript:customerInfo('${contract.customerFlow }')">${contract.customerName }</a></td>
			<td >${contract.contractName }</td>
			<td >${contract.contractCategoryName }</td>
			<td >${contract.contractTypeName }</td>
			<td >${contract.signDeptName }</td>
			<td >${contract.chargeUserName }</td>
			<td >${contract.chargeUser2Name }</td>
			<td >${contract.signDate }</td>
			<td ><label class="money">${contract.contractFund}</label></td>
			<td ><label class="money">${contract.billFund-contract.payFund }</label></td>
			<td ><label class="money">${contract.contractFund-contract.payFund }</label></td>
			<td >${contract.billApplyUserName}</td>
			<td ><label class="money">${contract.planPayFund}</label></td>
			<td >${contract.planDate}</td>
			<td ><label class="money">${contract.billPayFund}</label></td>
			<td >${contract.payDate}</td>
			<td >
				<c:choose>
					<c:when test="${contract.yuqi eq 'two'}">
						2个月以下
					</c:when>
					<c:when test="${contract.yuqi eq 'four'}">
						2~4个月
					</c:when>
					<c:when test="${contract.yuqi eq 'six'}">
						4~6个月
					</c:when>
					<c:when test="${contract.yuqi eq 'twelve'}">
						6~12个月
					</c:when>
					<c:when test="${contract.yuqi eq 'twelveMore'}">
						12个月以上
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(contractList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>