<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
	$(document).ready(function(){
		moneyMask();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">

		<colgroup>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
			<col width="6.6%"/>
		</colgroup>
		<thead>
		<tr>
			<th>合同号</th>
			<th>合同档案号</th>
			<th>客户名称</th>
			<th>合同名称</th>
			<th>合同负责人</th>
			<th>合同负责人2</th>
			<th>合同类别</th>
			<th>合同类型</th>
			<th>签订日期</th>
			<th>合同应收金额</th>
			<th>最近应收日期</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="11">无记录</td>
		</tr>
		</c:if>
		<c:set var="allPageFund" value="0"></c:set>
		<c:forEach items="${contractList }" var="contract">
			<c:set var="allPageFund" value="${allPageFund+contract.contractFund-contract.payFund}"></c:set>
		<tr >
			<td style="text-align: left;">
				<span style="margin-left:22px;"><a href="javascript:contractInfo('${contract.contractFlow }');">${contract.contractNo }</a></span>
			</td>
			<td >${contract.contractArchivesNo }</td>
			<td ><a href="javascript:customerInfo('${contract.customerFlow }')">${contract.customerName }</a></td>
			<td >${contract.contractName }</td>
			<td >${contract.chargeUserName }</td>
			<td >${contract.chargeUser2Name }</td>
			<td >${contract.contractCategoryName }</td>
			<td >${contract.contractTypeName }</td>
			<td >${contract.signDate }</td>
			<td ><label class="money">${contract.contractFund-contract.payFund }</label></td>
			<td >
				<c:if test="${ (contract.contractFund-contract.payFund) ne 0}">
					${contract.planDate}
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${not empty contractList }">
			<tr>
				<td colspan="6" style="text-align: right;border: 0;">本页合同应收金额总计：</td>
				<td colspan="" style="text-align: left;padding-left: 5px;"><font  class="money" color="red">${allPageFund}</font>元</td>
				<td colspan="3" style="text-align: right;border: 0;">合同应收总金额：</td>
				<td colspan="" style="text-align: left;padding-left: 5px;"><font  class="money" color="red">${fundMap['allFund']}</font>元</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(contractList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>