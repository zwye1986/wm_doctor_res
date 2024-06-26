<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="treetable" value="true"/>
</jsp:include>
<script type="text/javascript">

function doClose() {
	jboxClose();
}

function saveAudit(agreeFlag,fundFlow) {
	var tip;
	if(agreeFlag=="${GlobalConstant.FLAG_Y}"){
		tip = "审核通过";
	}else if(agreeFlag=="${GlobalConstant.FLAG_N}"){
		tip = "退回";
	}else{
		tip = "保存";
	}
	if ($("#schemeDtlList").validationEngine("validate")) {
		jboxConfirm("确认"+tip+"?", function () {
			var fundProcessFlow = $("#fundProcessFlow").val();
			var content = $("#content").val();
			//var schemeFlow = $("#schemeFlow").val();
			var budgetAmount = $("#budgetAmount").val();
			var budgetGove = $("#budgetGove").val();
			var budgetOrg = $("#budgetOrg").val();
			var trs = $('#tbody').find('tr');
			var fundInfo = {
				"fundFlow": fundFlow,
				"budgetAmount": budgetAmount/10000,
				"budgetGove": budgetGove/10000,
				"budgetOrg": budgetOrg/10000
			};
			var process = {
				"fundProcessFlow": fundProcessFlow,
				"content": content
			}
			var datas = [];
			$.each(trs, function (i, n) {
				var money = $(n).find("input[name='money']").val();
				//var content = $(n).find('td').eq(4).text();
				var fundDetailFlow = $(n).find("input[name='fundDetailFlow']").val();
				var itemFlow = $(n).find("input[name='itemFlow']").val();
				var itemId = $(n).find("input[name='itemId']").val();
				var itemPid = $(n).find("input[name='itemPid']").val();
				var itemName = $(n).find("input[name='itemName']").val();
				var maxLimit = $(n).find("input[name='maxLimit']").val();
				var allocateMoney = $(n).find("input[name='allocateMoney']").val();
				var matchingMoney = $(n).find("input[name='matchingMoney']").val();
				var remark = $(n).find("input[name='remark']").val();

				var fundDetailFlow = $(n).find("input[name='fundDetailFlow']").val();
				var data = {
					"fundDetailFlow": fundDetailFlow,
					"money": money/10000,
					"remark": remark,
					"itemId": itemId,
					"itemPid": itemPid,
					"itemName": itemName,
					"itemFlow": itemFlow,
					"maxLimit": maxLimit,
					"allocateMoney": allocateMoney/10000,
					"matchingMoney": matchingMoney/10000,
					"fundDetailFlow": fundDetailFlow
				};

				datas.push(data);
			});

			var t = {'fundDetailList': datas, 'process': process,'fundInfo':fundInfo};
			var requestData = JSON.stringify(t);
			var url = "<s:url value='/srm/fund/scheme/saveAudit'/>?agreeFlag="+agreeFlag;
			jboxStartLoading();
			jboxPostJson(url, requestData, function () {
				$("#budgetList", window.parent.frames['mainIframe'].document).submit();
				jboxCloseMessager();
			}, null, true);
		});
	}
}
function toggleTr(obj){
	$('#treeTable').treetable('collapseOrexpand',$(obj).parents('tr').attr("data-tt-id"));
}
$(function () {

	//$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
	$("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
	$("#treeTable").treetable("expandAll");
});
function countBudget() {
	var trs = $('#tbody tr');
	var tdLimit = 0;
	var moneys = 0;

	var allocate = 0;
	var matching = 0;
	$.each(trs, function (i, n) {
		if (!$(n).attr("data-tt-parent-id")) {
			tdLimit += parseFloat($(n).find("input[name='maxLimit']").val()) ? parseFloat($(n).find("input[name='maxLimit']").val()) : 0;
			moneys += parseFloat($(n).find("input[name='money']").val()) ? parseFloat($(n).find("input[name='money']").val()) : 0;
			allocate += parseFloat($(n).find("input[name='allocateMoney']").val()) ? parseFloat($(n).find("input[name='allocateMoney']").val()) : 0;
			matching += parseFloat($(n).find("input[name='matchingMoney']").val()) ? parseFloat($(n).find("input[name='matchingMoney']").val()) : 0;
		}
	});
	$("#countLimit").val(parseFloat(tdLimit.toFixed(4)));
	$("#budgetAmount").val(parseFloat(moneys.toFixed(2)));
	$("#budgetGove").val(parseFloat(allocate.toFixed(2)));
	$("#budgetOrg").val(parseFloat(matching.toFixed(2)));
}
function calcuTdFund(obj) {
	var trs = $('#tbody tr');
	// $(obj).validationEngine("validate");
	var amountFund = $("#amountFund").val();
	if (!parseFloat(amountFund)) {
		jboxTip("总经费为空，无法填写预算！");
		return false;
	}
	var tr = $(obj).parents("tr");

	var limit = $(tr).find("input[name='maxLimit']");
	var amount = $(tr).find("input[name='money']");
	var allocate = $(tr).find("input[name='allocateMoney']");
	var matching = $(tr).find("input[name='matchingMoney']");

	var allocateMoney = $(allocate).val();
	var matchingMoney = $(matching).val();
	allocateMoney = parseFloat((parseFloat(allocateMoney) ? parseFloat(allocateMoney) : 0).toFixed(2));
	matchingMoney = parseFloat((parseFloat(matchingMoney) ? parseFloat(matchingMoney) : 0).toFixed(2));
	var money = parseFloat((allocateMoney + matchingMoney).toFixed(2));
	$(allocate).val(allocateMoney);
	$(matching).val(matchingMoney);
	$(amount).val(money);
	$(limit).val(parseFloat(((money / amountFund) * 100).toFixed(4)));


	if (!$(tr).attr("data-tt-parent-id")) {
		countBudget();
	} else {
		var ttPid = $(tr).attr("data-tt-parent-id");
		var pTr = $("#tbody").find("tr[data-tt-id='" + ttPid + "']");
		var allocateChild = 0;
		var matchingChild = 0;
		var amountChild = 0;
		var limitChild = 0;
		$.each(trs, function (i, n) {
			if ($(n).attr("data-tt-parent-id") == ttPid) {
				allocateChild += parseFloat($(n).find("input[name='allocateMoney']").val()) ? parseFloat($(n).find("input[name='allocateMoney']").val()) : 0;
				matchingChild += parseFloat($(n).find("input[name='matchingMoney']").val()) ? parseFloat($(n).find("input[name='matchingMoney']").val()) : 0;
				amountChild += parseFloat($(n).find("input[name='money']").val()) ? parseFloat($(n).find("input[name='money']").val()) : 0;
				limitChild += parseFloat($(n).find("input[name='maxLimit']").val()) ? parseFloat($(n).find("input[name='maxLimit']").val()) : 0;
			}
		});

		$(pTr).find("input[name='maxLimit']").val(parseFloat(limitChild.toFixed(4)));
		$(pTr).find("input[name='money']").val(parseFloat(amountChild.toFixed(2)));
		$(pTr).find("input[name='allocateMoney']").val(parseFloat(allocateChild.toFixed(2)));
		$(pTr).find("input[name='matchingMoney']").val(parseFloat(matchingChild.toFixed(2)));
		countBudget();
	}

}

$(function(){
	var trs = $('#tbody tr');
	var limit = 0;
	$.each(trs, function (i, n) {
		if(!$(n).attr("data-tt-parent-id")){
			limit += parseFloat($(n).find("input[name='maxLimit']").val()) ? parseFloat($(n).find("input[name='maxLimit']").val()) : 0;
		}else {
			var parentId = $(n).attr("data-tt-parent-id")
			var tr = $("#tbody").find("tr[data-tt-id='" + parentId + "']");
			$(tr).find("input[name='maxLimit']").attr("readonly", "readonly");
			$(tr).find("input[name='maxLimit']").addClass("readonlycss");

			$(tr).find("input[name='allocateMoney']").attr("readonly", "readonly");
			$(tr).find("input[name='allocateMoney']").addClass("readonlycss");

			$(tr).find("input[name='matchingMoney']").attr("readonly", "readonly");
			$(tr).find("input[name='matchingMoney']").addClass("readonlycss");
		}
	});
	$("#countLimit").val(parseFloat(limit.toFixed(4)));
});
</script>
	<style type="text/css">
		.readonlycss {
			background-color: #EEEEEE;
		}
	</style>
</head>
<body>
<div class="mainright" id="mainright">
   <div class="content">
   <br>
	   <form id="schemeDtlList">
   <p style="font-weight: bold;color:red;">
	   &#12288;总经费（元）：<input id="amountFund" type="text" class="xltext" value="${pdfn:transMultiply(fundInfo.amountFund,10000)}" readonly="readonly"/>
	   <%--&#12288;预算方案：<input type="text" class="xltext" name="schemeName" value="${scheme.schemeName}" readonly="readonly"/>--%>
	   <input type="hidden" id="schemeFlow" name="schemeFlow" value="${fundInfo.schemeFlow}"/>

	   &#12288;下拨金额（元）：<input type="text" readonly="readonly" class="xltext" value="${pdfn:transMultiply(fundInfo.goveFund,10000) }" />
	   &#12288;配套金额（元）：<input type="text" readonly="readonly" class="xltext" value="${pdfn:transMultiply(fundInfo.orgFund,10000) }" />
   </p>
    <br>
	   <table id="treeTable" class="xllist linetable">
		   <tr>
			   <th style="width:300px;">预算项名称</th>
			   <th style="width:110px;">预算比例(单位：%)</th>
			   <th style="width:100px;">预算金额(元)</th>
			   <th style="width:120px;">下拨金额预算(元)</th>
			   <th style="width:120px;">配套金额预算(元)</th>
			   <th>预算备注</th>
		   </tr>
		   <tr>
			   <td>&#12288;合&#12288;计</td>
			   <td>
				   <input id="countLimit" type="text" style="width: 90%;" class="readonlycss" readonly="readonly"/>
			   </td>
			   <td>
				   <input type="text" id="budgetAmount" name="budgetAmount" value="${pdfn:transMultiply(fundInfo.budgetAmount,10000)}"
						  readonly="readonly" style="width: 90%;" class="readonlycss" />
			   </td>
			   <td>
				   <input type="text" id="budgetGove" name="budgetGove" value="${pdfn:transMultiply(fundInfo.budgetGove,10000)}"
						  readonly="readonly" style="width: 90%;" class="readonlycss"/>
			   </td>
			   <td>
				   <input type="text" id="budgetOrg" name="budgetOrg" value="${pdfn:transMultiply(fundInfo.budgetOrg,10000)}"
						  readonly="readonly" style="width: 90%;" class="readonlycss"/>
			   </td>
			   <td>

			   </td>
		   </tr>
		   <tbody id="tbody">
			   <c:forEach var="n" items="${schemeDtlList}">
				   <c:if test="${(empty n.itemPid) or (n.itemPid eq '0')}">
					   <tr data-tt-id="${n.itemId}">
						   <td ondblclick="toggleTr(this)" style="text-align: left;cursor:pointer">${n.itemName}
							   <input name='fundDetailFlow' value='${fundDetailMap[n.itemId].fundDetailFlow}' type='hidden'/>
							   <input name='itemFlow' value='${n.itemFlow}' type='hidden'/>
							   <input name='itemId' value='${n.itemId}' type='hidden'/>
							   <input name='itemName' value='${n.itemName}' type='hidden'/>
							   <input name='itemPid' value='${n.itemPid}' type='hidden'/>
						   </td>
						   <td>
							   <input type="text"  name="maxLimit" value="<c:if test="${empty fundDetailMap[n.itemId].maxLimit}">${n.maxLimit}</c:if>${fundDetailMap[n.itemId].maxLimit}"
									  class="readonlycss validate[required,custom[number],min[0]]" readonly="readonly"
									  style="width: 90%;"/>
						   </td>
						   <td>
							   <input type="text" readonly="readonly" value="${pdfn:transMultiply(fundDetailMap[n.itemId].money,10000)}" class="readonlycss" name="money" style="width: 90%;"/>
						   </td>
						   <td <c:if test="${n.itemId eq 'guanlifei'}">title="管理费从下拨经费中扣除"</c:if> >
							   <input type="text" class="validate[required,custom[number],min[0]]" value="${pdfn:transMultiply(fundDetailMap[n.itemId].allocateMoney,10000)}" name="allocateMoney" onchange="calcuTdFund(this);" style="width: 90%;"/>
						   </td>
						   <td>
							   <input type="text" class="validate[required,custom[number],min[0]]" value="${pdfn:transMultiply(fundDetailMap[n.itemId].matchingMoney,10000)}" name="matchingMoney" onchange="calcuTdFund(this);" style="width: 90%;"/>
						   </td>
						   <td>
							   <input type="text" value="<c:if test="${empty fundDetailMap[n.itemId].remark}">${n.remark}</c:if>${fundDetailMap[n.itemId].remark}" name="remark" style="width: 90%;"/>
						   </td>
					   </tr>
					   <c:forEach var="childScheme" items="${schemeDtlList}">
						   <c:if test="${n.itemId eq childScheme.itemPid}">
							   <c:set var="key" value="${childScheme.itemPid}${childScheme.itemId}"/>
							   <tr data-tt-id="${childScheme.itemId}" data-tt-parent-id="${n.itemId}">
								   <td style="text-align: left">${childScheme.itemName}
									   <input name='fundDetailFlow' value='${fundDetailMap[key].fundDetailFlow}' type='hidden'/>
									   <input name='itemFlow' value='${childScheme.itemFlow}' type='hidden'/>
									   <input name='itemId' value='${childScheme.itemId}' type='hidden'/>
									   <input name='itemName' value='${childScheme.itemName}' type='hidden'/>
									   <input name='itemPid' value='${childScheme.itemPid}' type='hidden'/>
								   </td>
								   <td>
									   <input type="text" name="maxLimit" value="<c:if test="${empty fundDetailMap[key].maxLimit}">${childScheme.maxLimit}</c:if>${fundDetailMap[key].maxLimit}"
											  class="readonlycss validate[required,custom[number],min[0]]" readonly="readonly"
											  style="width: 90%;"/>
								   </td>
								   <td>
									   <input type="text" readonly="readonly" class="readonlycss" value="${pdfn:transMultiply(fundDetailMap[key].money,10000)}" name="money" style="width: 90%;"/>
								   </td>
								   <td>
									   <input type="text" class="validate[required,custom[number],min[0]]" value="${pdfn:transMultiply(fundDetailMap[key].allocateMoney,10000)}" name="allocateMoney" onchange="calcuTdFund(this);"  style="width: 90%;"/>
								   </td>
								   <td>
									   <input type="text" class="validate[required,custom[number],min[0]]" value="${pdfn:transMultiply(fundDetailMap[key].matchingMoney,10000)}"  name="matchingMoney" onchange="calcuTdFund(this);"  style="width: 90%;"/>
								   </td>
								   <td>
									   <input type="text" value="<c:if test="${empty fundDetailMap[key].remark}">${childScheme.remark}</c:if>${fundDetailMap[key].remark}" name="remark" style="width: 90%;"/>
								   </td>
							   </tr>
						   </c:if>
					   </c:forEach>

				   </c:if>
			   </c:forEach>
		   </tbody>
	   </table>
	   </form>
<h2 style="margin-top: 15px;">审核意见：</h2>
<hr/>

<form  method="post" id="auditForm">
<div>
	<input type="hidden" id="fundProcessFlow" value="${process.fundProcessFlow}">
	<textarea id="content" name="content" cols="25" rows="3" style="margin-top: 10px; width:100%; height:150px; border:1px solid #D0E3F2" >${process.content}</textarea>
	<p style="text-align:center;">
	 <input class='search' onclick="saveAudit('S','${fundInfo.fundFlow }')" type='button' value='保&#12288;存'/>&#12288;
	 <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_N}','${fundInfo.fundFlow }')" type='button' value='退&#12288;回'/>&#12288;
     <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_Y}','${fundInfo.fundFlow }')" type='button'  value='同&#12288;意' />&#12288;
     <input class='search' onclick="jboxClose()" type='button'  value='关&#12288;闭' />
	</p>
</div>
</form>
</div>
</div>
</body>
</html>