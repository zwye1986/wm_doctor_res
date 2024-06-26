<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">

	$(document).ready(function(){
		moneyMask();
	});
	function auditContract(contractFlow) {
		var w = $('.mainright').width();
		var h = $('.mainright').height();
		jboxOpen("<s:url value='/erp/crm/contractWH/auditContract'/>?contractFlow="+contractFlow,"审核合同信息", w,h);
	}
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">

		<colgroup>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="6%"/>
			<col width="10%"/>
		</colgroup>
		<thead>
		<tr>
			<th>合同号</th>
			<th>合同档案号</th>
			<th>客户名称</th>
			<th>合同名称</th>
			<th>合同类别</th>
			<th>合同类型</th>
			<th>合同负责人</th>
			<th>合同负责人2</th>
			<th>合同状态</th>
			<th>退回原因</th>
			<th>签订日期</th>
			<th>合同创建日期</th>
			<th>合同到期日</th>
			<th>维护到期日</th>
			<th>合同金额</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="15">无记录</td>
		</tr>
		</c:if>
		<c:forEach items="${contractList }" var="contract">
		<tr >
			<td style="text-align: left;">
				<span style="margin-left:10px;"><a href="javascript:contractInfo('${contract.contractFlow }');">${contract.contractNo }</a></span>
			</td>
			<td >${contract.contractArchivesNo }</td>
			<td ><a href="javascript:customerInfo('${contract.customerFlow }')">${contract.customerName }</a></td>
			<td >${contract.contractName }</td>
			<td >${contract.contractCategoryName }</td>
			<td >${contract.contractTypeName }</td>
			<td >${contract.chargeUserName }</td>
			<td >${contract.chargeUser2Name }</td>
			<td>${contract.contractStatusName }</td>
			<td><c:if test="${'AuditBack' eq contract.contractStatusId}">${contract.auditReason }</c:if></td>
			<td>${contract.signDate }</td>
			<td>${pdfn:transDateTimeForPattern(contract.createTime,"yyyyMMddHHmmss","yyyy-MM-dd") }</td>
			<td>${contract.contractDueDate }</td>
			<td>${contract.maintainDueDate }</td>
			<td><label class="money">${contract.contractFund }</label></td>
			<td>
				[<a href="javascript:contractInfo('${contract.contractFlow }');">查看</a>]
				<c:if test="${(contract.contractStatusId eq contractStatusEnumAuditing.id )}">
					| [<a href="javascript:auditContract('${contract.contractFlow }');">审核</a>]
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(contractList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>