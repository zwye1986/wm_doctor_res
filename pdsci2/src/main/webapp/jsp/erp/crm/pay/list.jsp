<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
	function customerInfo(customerFlow){
		var w = $('.mainright').width();
		var h = $('.mainright').height();
		var url = "<s:url value='/erp/crm/customerInfo'/>?customerFlow=" + customerFlow+"&type=open";
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'客户详细信息',w,h,null,false);
	}

	function contractInfo(contractFlow) {
		var w = $('.mainright').width();
		var h = $('.mainright').height();
		var url= "<s:url value='/erp/crm/contractInfo'/>?contractFlow=" + contractFlow+"&type=open&userEdit=${GlobalConstant.FLAG_Y}";
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'合同详细信息',w,h,null,false);
	}
	function showPayPlan(contractFlow){
		var content = $("#tbody_"+contractFlow);
		var contentTd = $("#td_"+contractFlow);
		if(content.is(":hidden")) {
			contentTd.html("");
			var url = "<s:url value ='/erp/crm/payPlan/payPlanList'/>?type=${type}&contractFlow=" + contractFlow;
			jboxLoad("td_" + contractFlow, url, true);
		}
		content.slideToggle("fast",function(){
			moneyMask();
		});
	}
	function refreshPayPlan(contractFlow){
		var contentTd = $("#td_"+contractFlow);
			contentTd.html("");
		var url = "<s:url value ='/erp/crm/payPlan/payPlanList'/>?type=${type}&contractFlow=" + contractFlow;
		jboxLoad("td_" + contractFlow, url, true);
		var content = $("#tbody_"+contractFlow);
		if(content.is(":hidden")) {
			content.slideToggle("fast",function(){
				moneyMask();
			});
		}
	}
	function delPlan(contractFlow,planFlow)
	{
		jboxConfirm("确认删除此条款项计划？",function(){
			var url = "<s:url value='/erp/crm/payPlan/delPlan'/>?planFlow="+planFlow;
			jboxPost(url,null,function(resp){
				if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_FAIL}");
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
				{
					jboxTip(resp);
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp)
				{
					refreshPayPlan(contractFlow);
				}
			},null,false);
		},null);
	}
	function auditBalance(contractFlow,recordFlow)
	{
		if(!recordFlow)
		{
			jboxTip("请选择需要审核的到帐信息！");
			return false;
		}
		jboxOpen("<s:url value='/erp/crm/payPlan/audit'/>?contractFlow="+contractFlow+"&recordFlow="+recordFlow,"审核到帐信息", 400, 400);

	}
	function editPlan(contractFlow,planFlow)
	{
		jboxOpen("<s:url value='/erp/crm/payPlan/addPayPlan'/>?contractFlow="+contractFlow+"&planFlow="+planFlow,"编辑回款计划", 800, 400);
	}
	function addPlanIn(contractFlow,planFlow)
	{
		var url = "<s:url value='/erp/crm/payPlan/checkPlanBalance'/>?contractFlow="+contractFlow+"&planFlow="+planFlow;
		jboxPost(url,null,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED_FLAG}')
			{
				jboxOpen("<s:url value='/erp/crm/payPlan/addPlanIn'/>?contractFlow="+contractFlow+"&planFlow="+planFlow,"新增到款", 800, 210);
			}else{
				jboxTip(resp);
				refreshPayPlan(contractFlow);
			}
		},null,false);
	}
	function editPlanIn(contractFlow,planFlow,recordFlow)
	{
		jboxOpen("<s:url value='/erp/crm/payPlan/addPlanIn'/>?contractFlow="+contractFlow+"&planFlow="+planFlow+"&recordFlow="+recordFlow,"修改到款", 800, 210);
	}
	function addPlan(contractFlow)
	{
		jboxOpen("<s:url value='/erp/crm/payPlan/addPayPlan'/>?contractFlow="+contractFlow,"新增回款计划", 800, 400);
	}
	$(document).ready(function(){
		moneyMask();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">

		<colgroup>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<col width="6.4%"/>
			<c:if test="${type eq 'plan'}">
				<col width="10%"/>
			</c:if>
		</colgroup>
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
			<th>合同状态</th>
			<th>签订日期</th>
			<th>合同创建日期</th>
			<th>合同到期日</th>
			<th>维护到期日</th>
			<th>合同金额</th>
			<c:if test="${type eq 'plan'}">
				<th>操作</th>
			</c:if>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty contractList }">
		<tr>
			<td colspan="15">无记录</td>
		</tr>
		</c:if>
		<c:set var="allFund" value="0"></c:set>
		<c:forEach items="${contractList }" var="contractExt">
			<c:set var="allFund" value="${allFund+contractExt.contractFund}"></c:set>
		<tr id="contractTr_${contractExt.contractFlow }" class="contractTr"  title="点击显示或关闭款项执行计划">

			<td style="text-align: left;" onclick="showPayPlan('${contractExt.contractFlow }')">
				<span style="margin-left:22px;">${contractExt.contractNo }</span>
			</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractArchivesNo }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')"><a href="javascript:customerInfo('${contractExt.customer.customerFlow }')">${contractExt.customer.customerName }</a></td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractCategoryName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractTypeName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.signDeptName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.chargeUserName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.chargeUser2Name }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractStatusName }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.signDate }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${pdfn:transDateTimeForPattern(contractExt.createTime,"yyyyMMddHHmmss","yyyy-MM-dd") }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.contractDueDate }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')">${contractExt.maintainDueDate }</td>
			<td onclick="showPayPlan('${contractExt.contractFlow }')"><label class="money">${contractExt.contractFund }</label></td>
			<c:if test="${type eq 'plan'}">
				<td id="btn_${contractExt.contractFlow }">
						<c:if test="${contractExt.contractFund > payFundMap[contractExt.contractFlow]}">
							<a  href="javascript:addPlan('${contractExt.contractFlow }');">新增到帐计划</a>
						</c:if>
				</td>
			</c:if>
		</tr>
		<tr id="tbody_${contractExt.contractFlow }" style="display: none;">
			<td id="td_${contractExt.contractFlow }"colspan="16">
			</td>
		</tr>
		</c:forEach>
		<c:if test="${not empty contractList }">
			<c:if test="${type eq 'audit'}">
				<tr>
					<td colspan="99" style="text-align: right;border: 0;">待确认到帐${auditMap['num']}笔，总金额为<font  class="money" color="red">${auditMap['allFund']}</font>元&#12288;&#12288;&#12288;&#12288;</td>
				</tr>
			</c:if>
			<c:if test="${type ne 'audit'}">
				<tr>
					<td colspan="10" style="text-align: right;border: 0;">本页金额总计：</td>
					<td colspan="" style="text-align: left;padding-left: 5px;"><font  class="money" color="red">${allFund}</font>元</td>
					<td colspan="3" style="text-align: right;border: 0;">总金额：</td>
					<td colspan="" style="text-align: left;padding-left: 5px;"><font  class="money" color="red">${fundMap['allFund']}</font>元</td>
				</tr>
			</c:if>
		</c:if>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(contractList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>