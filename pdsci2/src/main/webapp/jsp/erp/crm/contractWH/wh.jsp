<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">

	$(document).ready(function(){
		moneyMask();
	});
	function updateNextDate(contractFlow) {
		jboxOpen("<s:url value='/erp/crm/contractWH/updateNextDate'/>?contractFlow="+contractFlow,"更新下次维护时间", 400, 200);
	}
	function editContract(contractFlow) {
		jboxPost("<s:url value='/erp/crm/contractWH/checkContractStatus'/>?contractFlow="+contractFlow,null,function(data){
			if(data==1)
			{
				var url= "<s:url value='/erp/crm/contractWH/editContract'/>?contractFlow=" + contractFlow;
				var msg="新增合同";
				if(contractFlow!='')
				{
					msg="编辑合同";
				}
				var w = $('.mainright').width();
				var h = $('.mainright').height();
				var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
				jboxMessager(iframe,msg,w,h,null,false);
			}else{
				jboxTip(data);
			}
		},null,false);
	}
	function editBuildInfo(contractFlow) {
		jboxOpen("<s:url value='/erp/crm/contractWH/editBuildInfo'/>?contractFlow="+contractFlow,"安装信息维护", 600, 400);
	}
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">

		<colgroup>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
			<col width="5.6%"/>
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
			<th>下次维护日期</th>
			<th>合同金额</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="99">无记录</td>
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
			<td>${contract.nextMaintainDate }</td>
			<td><label class="money">${contract.contractFund }</label></td>
			<td style="text-align: left;">
				[<a href="javascript:contractInfo('${contract.contractFlow }');">查看</a>]
				<c:if test="${((contract.contractStatusId eq contractStatusEnumAuditing.id )or(contract.contractStatusId eq contractStatusEnumAuditBack.id )) and contract.createUserFlow eq sessionScope.currUser.userFlow}">
					<br/>[<a href="javascript:editContract('${contract.contractFlow }');">编辑</a>]
				</c:if>
				<c:if test="${(contract.contractStatusId ne contractStatusEnumAuditing.id )
								and(contract.contractStatusId ne contractStatusEnumAuditBack.id )
								and(not empty contract.contractStatusId )}">
					<br/>[<a href="javascript:updateNextDate('${contract.contractFlow }');">更新下次维护日期</a>]
					<c:if test="${btnMap[contract.contractFlow] eq 'Y'}">
						<br/>[<a href="javascript:editBuildInfo('${contract.contractFlow }');">安装信息维护</a>]
					</c:if>
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