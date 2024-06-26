<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	if($("#payPlanTb tr").length<=0){
		addPayPlan();
	}
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

function moneyObjMask(obj){
	$(obj).mask('000,000,000,000,000', {reverse: true});
}

function reMoneyObjMask(obj){
	$(obj).mask('000000000000000', {reverse: true});
}

function save(){
		if($("#planForm").validationEngine("validate")){
			reMoneyMask();
		}else{
			moneyMask();
			return false;
		}
		var fund=parseFloat('${contract.contractFund}');
		var allFund=parseFloat($("#allFund").html());
		if(allFund>fund)
		{
			jboxTip("计划总金额大于合同金额，无法保存！");
			return false;
		}
		var payPlanTr=$("#payPlanTb").children();
		var payPlanDatas=[];
		if(payPlanTr.length>0){
			$.each(payPlanTr , function(i , n){
				var planFlow=$(n).find("input[name='planFlow']").val();
				var contractFlow=$(n).find("input[name='contractFlow']").val();
				var planDate=$(n).find("input[name='planDate']").val();
				var payFund=$(n).find("input[name='payFund']").val();
					var payPlanData={
						"planFlow":planFlow,
						"contractFlow":contractFlow,
						"planDate":planDate,
						"payFund":payFund
					};
					payPlanDatas.push(payPlanData);
			});
		}else{
			jboxTip("至少填写一个款项计划!");
			return false;
		}

			 $("#saveButton").attr("disabled",true);
			 var allData={
					 'payPlanList':payPlanDatas 
				};
			console.log(allData);
			 $('#jsondata').val(JSON.stringify(allData));
			 var url = "<s:url value='/erp/crm/payPlan/savePayPlan'/>";
				jboxSubmit($('#planForm'),url,function(resp){
							jboxTip(resp);
					if(resp=='${GlobalConstant.SAVE_SUCCESSED}')
					{
						setTimeout(function(){
						window.parent.frames['mainIframe'].window.refreshPayPlan('${param.contractFlow }');
							jboxClose();
						},1000);
					}
				},
				function(resp){
				},false);
		 
		
}

function addPayPlan(){
	$('#payPlanTb').append($("#payPlanClone tr:eq(0)").clone());
	moneyMask();
}

function delPayPlan(){
	var mIds = $("#payPlanTb input[name='id']:checked");
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		mIds.each(function(){
			$(this).parent().parent().remove();
			subMoney();
		});
	});
}

function timeMask(obj){
	reMoneyObjMask($(obj));
	setTimeout(function(){
		moneyObjMask($(obj));
	},100);
	sumMoney(obj);
}
function  sumMoney(obj)
{

	reMoneyMask();
	var sum=0;
	$("#planForm").find("input[name='payFund']").each(function(){
		var money=$(this).val();
		if(money==""||money==undefined )
		{
			money="0";
		}
		sum+=parseFloat(money);
	});
	var fund=parseFloat('${contract.contractFund}');
	if(fund<sum)
	{
		jboxTip("计划总金额大于合同金额，请重新填写！");
		var money=$(obj).val();
		if(money==""||money==undefined )
		{
			money="0";
		}
		sum-=parseFloat(money);
		$(obj).val("");
	}
	if(fund==sum)
	{
		$("#add").removeAttr("onclick");
	}
	$("#allFund").html(sum);
	moneyMask();

}
function  subMoney()
{
	reMoneyMask();
	var sum=0;
	$("#planForm").find("input[name='payFund']").each(function(){
		var money=$(this).val();
		console.log("===money=="+money);
		if(money==""||money==undefined )
		{
			money="0";
		}
		sum+=parseFloat(money);
	});
	var fund=parseFloat('${contract.contractFund}');
	if(fund>sum)
	{
		$("#add").attr("onclick","addPayPlan();");
	}
	$("#allFund").html(sum);
	moneyMask();
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="planForm" >
			    <input id="jsondata" type="hidden" name="jsondata"/>
				<input type="hidden" name="contractFlow2" value="${param.contractFlow }"/>
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;">
					<colgroup>
						<col width="10%"/>
						<col width="40%"/>
						<col width="40%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th colspan="4" style="text-align: left;padding-left: 10px">款项执行计划<span class="red" style="padding-left: 12px;">*</span>
							<c:if test="${empty payPlan}">
								<img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="delPayPlan();"/>
								<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" id="add" style="float: right;margin-right: 10px;cursor: pointer;" onclick="addPayPlan();"/>
							</c:if>
						</th>
					</tr>
					<tr>
						<th></th>
						<th>计划日期</th>
						<th colspan="2">计划金额</th>
					</tr>
					<tbody id="payPlanTb">
					  <c:forEach items="${payPlanList }" var="plan">
					   <tr>
		                 <td>
		                  <input type="checkbox" name="id" value="" style="display: none;"/>
		                  <input type="hidden" name="planFlow" value="${plan.planFlow }"/>
		                  <input type="hidden" name="contractFlow" value="${plan.contractFlow }"/>
		                 </td>
		                 <td>
		                    <c:choose>
								<c:when test="${payPlan.planFlow eq plan.planFlow}">
								<input type="text" value="${plan.planDate }" class="inputText ctime validate[required]" name="planDate" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</c:when>
								<c:otherwise>
								  <label>${plan.planDate}</label>
								  <input type="hidden" value="${plan.planDate }" class="inputText ctime validate[required]" name="planDate" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</c:otherwise>
		                    </c:choose>
		                 </td>
		                 <td colspan="2">
		                    <c:choose>
								<c:when test="${payPlan.planFlow eq plan.planFlow}">
								<input type="text" value="${plan.payFund }" class="inputText validate[custom[number],maxSize[9],min[1]] money" name="payFund" style="width: 150px;" onkeyup="timeMask(this);"/>元
								</c:when>
								<c:otherwise>
								  <label class="money">${plan.payFund }</label>
								  <input type="hidden" value="${plan.payFund }" class="inputText validate[custom[number],maxSize[9],min[1]] money" name="payFund" style="width: 150px;" onkeyup="timeMask(this);"/>元
								</c:otherwise>
		                    </c:choose>
		                 </td>
	                   </tr>
	                  </c:forEach>
					</tbody>
					<tr>
						<th>合同金额</th>
						<th><span id="contractFund" class="money">${contract.contractFund}</span></th>
						<th>计划总金额</th>
						<th><span id="allFund" class="money">${sum}</span></th>
					</tr>
				</table>
				<div class="button" style="width: 100%">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>


<table style="display: none;" id="payPlanClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
			<input type="hidden" name="planFlow"/>
			<input type="hidden" name="contractFlow" value="${param.contractFlow }"/>
		</td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="planDate" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
		<td colspan="2">
			<input type="text" class="money inputText validate[required,custom[number],maxSize[9],min[1]]" name="payFund" style="width: 150px;" onkeyup="timeMask(this);"/>元
		</td>
	</tr>
</table>
</body>
</html>