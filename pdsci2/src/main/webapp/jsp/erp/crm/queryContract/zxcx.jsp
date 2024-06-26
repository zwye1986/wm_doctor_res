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
			<th>计划验收日期</th>
			<th>合同到期日</th>
			<th>维护到期日</th>
			<th>合同维护次数</th>
			<th>下次最晚维护日期</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="14">无记录</td>
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
			<td >${contract.chargeUserName }</td>
			<td >${contract.chargeUser2Name }</td>
			<td >${contract.contractCategoryName }</td>
			<td >${contract.contractTypeName }</td>
			<td >${contract.signDate }</td>
			<td >${contract.planAcceptDate }</td>
			<td >${contract.contractDueDate }</td>
			<td >${contract.maintainDueDate }</td>
			<td >${contract.maintainCount }</td>
			<td >${contract.nextMaintainDate }</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(contractList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>