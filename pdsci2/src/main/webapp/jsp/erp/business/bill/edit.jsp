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
	<jsp:param name="jquery_ui_combobox" value="true"/>
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
	<jsp:param name="jquery_mask" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
var customerHash = new Array();
var contractHash = new Array();
var contactUserHash = new Array();
var contactProductHash = new Array();
$(document).ready(function(){
	var customerFlow='${contactOrder.customerFlow}';
	if(customerFlow!=""){
		fillContractHash(customerFlow,null);
		fillContactUserHash(customerFlow,null);
	}
	setUserName('applyUser');
	checkServiceType();
});

function checkServiceType(){
	var serviceTypes=$(".demandMatterCheck");
	$.each(serviceTypes,function(i,n){
		changeMainTainType($(n));
	});
}
function save() {
	if(!$("#billForm").validationEngine("validate")){
		return false;
	}
	reMoneyMask();
	var url = "<s:url value='/erp/business/saveBill'/>";
	jboxPost(url, $("#billForm").serialize(),
			function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					setTimeout(function(){
						window.parent.frames['mainIframe'].window.search();
						jboxCloseMessager();
					},1000);
				}
			},null,true);
}

$(function(){
	/*查询客户*/
	loadCustomerList();
});
 

function loadCustomerList() {
	var customers = new Array();
	var url = "<s:url value='/erp/crm/searchCustomerJson'/>";
	jboxPost(url,null,function(data){
		if(data!=null){
			for (var i = 0; i < data.length; i++) {
				customerHash[data[i].customerFlow] = data[i];
				customers[i]=new Array(data[i].customerFlow,data[i].customerName,data[i].aliasName);
			}
		}
	},null,false);
	$("#searchParam_Customer").suggest(customers,{
		attachObject:'#suggest_Customer',
		dataContainer:'#result_Customer',
		triggerFunc:function(customerFlow){
			customerRelative(customerFlow);
		},
	    enterFunc:function(customerFlow){
	    	customerRelative(customerFlow);
	    }
	});
}
function blank(a){
	return a==null?"":a;
}



function fillContractHash(customerFlow,htmlContent){
	var url = "<s:url value='/erp/sales/searchContractJson'/>?isAdd=Y&customerFlow="+customerFlow+"&time="+new Date();
	var content="";
	jboxPost(url,null,function(data){
		if(data!=null){
			content = '<select class="inputText validate[required]" id="contractFlow" name="contractFlow" onchange="contractRelative(this.value);" style="width: 300px;">'+
			'<option value="">请选择</option>';
			for (var i = 0; i < data.length; i++) {
				contractHash[data[i].contractFlow] = data[i];
				content += '<option value="'+data[i].contractFlow+'">'+data[i].contractName+"     "+data[i].signDate+'</option>';
			}
			content += '</select>';
			content += '<span id="contractInfo" style="margin-left: 10px;"></span>';
			if(htmlContent!=null){
				htmlContent(content);
			}
		}
	},null,false);
}

function fillContactUserHash(customerFlow,htmlContent){
	var content="";
	var url="<s:url value='/erp/sales/searchCustomerUserJson'/>?customerFlow="+customerFlow;
	jboxPost(url,null,function(data){
		if(data!=null){
		    content = '<select class="inputText validate[required]"  onchange="contactUserRelative(this.value);">'+
			'<option value="">请选择</option>';
			for (var i = 0; i < data["customerUserList"].length; i++) {
				contactUserHash[data["customerUserList"][i].userFlow] = data["customerUserList"][i];
				content += '<option value="'+data["customerUserList"][i].userFlow+'" title="'+data["customerUserList"][i].deptName+'">'+data["customerUserList"][i].userName+'</option>';
			}
			content += '</select>';
			if(htmlContent!=null){
				htmlContent(content);
			}
		}
	},null,false);
}

function refreshFillContactUserHash(customerFlow,htmlContent){
	var contractFlow=$("#contractFlow").val();
	var url;
	if(contractFlow!=""){
		url = "<s:url value='/erp/sales/searchCustomerUserJson'/>?customerFlow="+customerFlow+"&contractFlow="+contractFlow;
	}else{
		url = "<s:url value='/erp/sales/searchCustomerUserJson'/>?customerFlow="+customerFlow;
	}
	var content="";
	jboxPost(url,null,function(data){
		if(data!=null){
		    content = '<select class="inputText validate[required]"  onchange="contactUserRelative(this.value);">'+
			'<option value="">请选择</option>';
			for (var i = 0; i < data["customerUserList"].length; i++) {
				contactUserHash[data["customerUserList"][i].userFlow] = data["customerUserList"][i];
					if(data["userCategoryMap"][data["customerUserList"][i].userFlow]){
						content += '<option value="'+data["customerUserList"][i].userFlow+'" title="'+data["customerUserList"][i].deptName+'('+data["userCategoryMap"][data["customerUserList"][i].userFlow]+')">'+data["customerUserList"][i].userName+'</option>';
					}
				    else{
					content += '<option value="'+data["customerUserList"][i].userFlow+'" title="'+data["customerUserList"][i].deptName+'">'+data["customerUserList"][i].userName+'</option>';
				    }
			}
			content += '</select>';
			if(htmlContent!=null){
				htmlContent(content);
			}
		}
	},null,false);
}
function customerRelative(customerFlow) {
	var customer = customerHash[customerFlow];
	//客户地址
	$("#customerAddress").val(blank(customer.customerProvName)+blank(customer.customerCityName)+blank(customer.customerAreaName)+blank(customer.customerAddress));
	contractRelative('');
	contactUserRelative('');
	searchConsumerInfo(customerFlow);
	//合同号
	fillContractHash(customerFlow,function(resp){
		$("#contractNameTd").html(resp);
	});
	//联系人
	fillContactUserHash(customerFlow,function(resp){
		$("#contactUserTd").html(resp);
	});
}

function searchConsumerInfo(consumerFlow){
	var customer = customerHash[consumerFlow];
    var consumerNameTd=$("#consumerNameTd");
    var consumerAddressTd=$("#consumerAddressTd");
    consumerNameTd.text("");
	consumerAddressTd.text("");
    var consumerAddress=blank(customer.customerProvName)+blank(customer.customerCityName)+blank(customer.customerAreaName)+blank(customer.customerAddress);
    var consumerNameTdText='<input type="text" value="'+customer.customerName+'" id="consumerNameTdText" class="inputText" style="width:200px; text-align:left;"/>';
    var consumerAddressTdText='<input type="text" value="'+consumerAddress+'" id="consumerAddressTdText" class="inputText" style="width:400px; text-align:left;"/>';
    consumerNameTd.append(consumerNameTdText);
    consumerAddressTd.append(consumerAddressTdText);
    $("#consumerFlow").val(customer.customerFlow);
    $("#consumerName").val(customer.customerName);
    $("#consumerAliasName").val(customer.aliasName);
    $("#consumerAddress").val(blank(customer.customerProvName)+blank(customer.customerCityName)+blank(customer.customerAreaName)+blank(customer.customerAddress));
}

function contractRelative(contractFlow) {
	if (contractFlow != '') {
		//合同状态
		var contractStatusName = contractHash[contractFlow].contractStatusName;
		var contractStatusId = contractHash[contractFlow].contractStatusId;
		var contractName = contractHash[contractFlow].contractName;
		var productNameTd=$("#productNameTd");
		$("#contractStatusTd").html(contractStatusName);
		$("#contractStatusId").val(contractStatusId);
		$("#contractName").val(contractName);
		var a = $('<a></a>');
		$(a).attr("href","javascript:contractInfo('"+contractFlow+"');");
		$(a).text(">>");
		$("#contractInfo").html(a);
		var contractCategoryId=contractHash[contractFlow].contractCategoryId;
		if(contractCategoryId=='${contractCategoryEnumSell.id}'){
			var consumerFlow=contractHash[contractFlow].consumerFlow;
			searchConsumerInfo(consumerFlow);
		}else{
			var consumerFlow=contractHash[contractFlow].customerFlow;
			searchConsumerInfo(consumerFlow);
		}
		//产品名称、备注
		var url = "<s:url value='/erp/crm/searchContractProductJson'/>?contractFlow="+contractFlow;
		jboxPost(url,null,function(data){
			if(data!=null){
				var content = '';
				var productItem = '';
				var check='';
				if (data.length == 1) {
					validate = "disabled";
				} else {
					validate = 'class="validate[required]"';
				}
				if(data.length==1){
					check='checked';
				}
				else{
					check='';
					productNameTd.html('<span class="red">*</span>产品名称');
				}
				for (var i = 0; i < data.length; i++) {
					contactProductHash[data[i].productFlow] = data[i];
					var productId = data[i].productTypeId;
					if (productId == null) {
						productId = data[i].productTypeName;
					}
					content += '<input type="checkbox" id="product_'+productId+'"  name="productTypeId" value="'+productId+'" '+validate+' '+check+'/><label for="product_'+productId+'">&nbsp;'+data[i].productTypeName+'</label>&#12288;';
					if (data.length >1) {
						if (blank(data[i].productItem) != "") {
							productItem += data[i].productTypeName + "：" +blank(data[i].productItem)+"；";
						}
					} else {
						productItem += blank(data[i].productItem);
						content +='<input type="hidden" id="product_'+productId+'"  name="productTypeId" value="'+data[i].productTypeName+'"/>';
					}
				}
				$("#contractProductTd").html(content);
				$("#productItem").val(productItem);
			}
		},null,false);
		var customerFlow=$("#result_Customer").val();
		//联系人
		refreshFillContactUserHash(customerFlow,function(resp){
			$("#contactUserTd").html(resp);
		});
	} else {
		$("#contractProductTd").html($("#productSpan").html());
		$("#contractProductTd").append('<input type="checkbox" id="orderProject" onclick="appendInput(this);"><label for="orderProject"> 定制项目</label>  <span id="xmSpan"></span>');
		$("#productItem").val("");
		$("#contractStatusTd").html("");
		$("#contractStatusId").val("");
		$("#contractName").val("");
		$("#contractInfo").html("");
	}
}

function contactUserRelative(userFlow) {
	if (userFlow != '') {
		var user = contactUserHash[userFlow];
		$("#deptNameTd").html(user.deptName);
		$("#postNameTd").html(user.postName);
		$("#telPhoneTd").html(user.userTelphone);
		$("#celPhoneTd").html(user.userCelphone);
		$("#deptName").val(user.deptName);
		$("#postName").val(user.postName);
		$("#telPhone").val(user.userTelphone);
		$("#celPhone").val(user.userCelphone);
		$("#userFlow").val(user.userFlow);
		$("#userName").val(user.userName);
	} else {
		$("#deptNameTd").html("");
		$("#postNameTd").html("");
		$("#telPhoneTd").html("");
		$("#celPhoneTd").html("");
		$("#deptName").val("");
		$("#postName").val("");
		$("#telPhone").val("");
		$("#celPhone").val("");
		$("#userFlow").val("");
		$("#userName").val("");
	}
}

function selectSingle(obj) {
	var value = $(obj).val();
	var name = $(obj).attr("name");
	$("input[name="+name+"][value!="+value+"]:checked").attr("checked",false);
}

function resetCustomerName(name){
	 var customerFlow=$("#result_"+name).val();
	 var customerName=$("#searchParam_"+name);
	 if(customerFlow==""){
		 customerName.val("");
	 }
}
function resetCustomerFlow(name){
	 var customerFlow=$("#result_"+name);
	 customerFlow.val("");
}
function setUserName(id){
	 $("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
}
//重置客户名称
function resetCustomerName(){
	 var customerFlow=$("#result_Customer").val();
	 var customerName=$("#searchParam_Customer");
	 if(customerFlow==""){
		 customerName.val("");
	 }
}
//重置客户流水号
function resetCustomerFlow(){
	 var customerFlow=$("#result_Customer");
	 customerFlow.val("");
}
function contractInfo(contractFlow) {
	var mainIframe = window.parent.frames["mainIframe"];
	var width = mainIframe.document.body.scrollWidth;
	var url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow="+contractFlow+"&status=main&type=open";
	jboxOpen(url, "合同详细信息", 1000, 600);
}

function appendInput(obj){
	if($(obj).attr("checked")=="checked"){
		$("#xmSpan").append($("#inputSpan").html());
	}else{
		$("#xmSpan").html("");
	}
    
}

function changeMainTainType(obj){
	var demandMatter=$(obj).val();
	var serviceTypeTd=$("#serviceTypeTd");
	if($(obj).attr("checked")=="checked"){
		$(serviceTypeTd).html($("#"+demandMatter+"DictEnum").clone());
	}
}
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
function searchDeptUser(obj,flag){
	 $("#"+flag+"Flow").empty();
	 var deptFlow=$(obj).val();
	 if(deptFlow!=""){
		 jboxPost("<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow,null,function(data){
			 if(data!=null){
			 $("#"+flag+"Flow").append('<option></option>');
			 for ( var i = 0; i < data.length; i++) {
					$("#"+flag+"Flow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
				 }
			 var deptName=$(obj).find("option:selected").text();
			 $("#"+flag+"Name").val(deptName);
		  }
		 },null,false); 
	 }else{
		 var option='<option>请先选择对应部门</option>';
		 $("#"+flag+"Flow").append(option);
	 }
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="billForm" style="position: relative;">
			<input type="hidden" name="contactFlow" value="${contactOrder.contactFlow}"/>
			<input type="hidden" id="contractName" name="contractName" value="${contactOrder.contractName}"/>
			<input type="hidden" name="consumerFlow" id="consumerFlow" value="${contactOrder.consumerFlow }"/>
			<input type="hidden" name="consumerName" id="consumerName" value="${contactOrder.consumerName }"/>
			<input type="hidden" name="consumerAliasName" id="consumerAliasName" value="${contactOrder.consumerAliasName }"/>
			<input type="hidden" id="userFlow" name="userFlow" value="${contactOrderForm.userFlow }"/>
			<input type="hidden" id="userName" name="userName" value="${contactOrderForm.userName }"/>
			<input type="hidden" id="postName" name="postName" value="${contactOrderForm.postName }"/>
			<input type="hidden" id="deptName" name="deptName" value="${contactOrderForm.deptName }"/>
			<input type="hidden" id="telPhone" name="telPhone" value="${contactOrderForm.telPhone }"/>
			<input type="hidden" id="celPhone" name="celPhone" value="${contactOrderForm.celPhone }"/>
			<input type="hidden" id="customerAddress" name="customerAddress" value="${contactOrderForm.customerAddress }"/>
			<input type="hidden" id="consumerAddress" name="consumerAddress" value="${contactOrderForm.consumerAddress }"/>
			<input type="hidden" id="contractStatusId" name="contractStatusId" value="${contactOrderForm.contractStatusId }"/>		
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="13%"/>
						<col width="25%"/>
						<col width="12%"/>
						<col width="50%"/>
					</colgroup>
					<tr>
						<th colspan="4" style="text-align: left;padding-left: 10px">客户和合同信息</th>
					</tr>
					<c:if test="${not empty contactOrder.contactNo }">
					<tr>
						<td style="text-align: right;padding-right: 10px;">编&#12288;&#12288;号：</td>
						<td colspan="3">${contactOrder.contactNo }</td>
					</tr>
					</c:if>
					<tr>
						<td style="text-align: right;padding-right: 10px;" rowspan="2"><span class="red">*</span>客户名称：</td>
						<td rowspan="2">
							<input id="searchParam_Customer" name="customerName" value="${contactOrder.customerName }" placeholder="输入客户名称/别名" class="inputText validate[required]"  style="width: 180px;text-align: left;" onblur="resetCustomerName();" onchange="resetCustomerFlow();"/>
				        	<div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 370px;"></div>
				        	<input type="hidden" id="result_Customer" name="customerFlow" value="${contactOrder.customerFlow }"/>
						</td>
						<td style="text-align: right;padding-right: 10px;">合同名称：</td>
						<td colspan="3" id="contractNameTd">
							<select class="inputText" style="width: 300px;" id="contractFlow" name="contractFlow" onchange="contractRelative(this.value);">
								<option value="">请选择</option>
				             	<c:forEach var="contract" items="${contractList}">
						            <option value="${contract.contractFlow}" <c:if test="${contract.contractFlow eq contactOrder.contractFlow }">selected</c:if>>${contract.contractName}&#12288;&#12288;${contract.signDate }</option>
						        </c:forEach>
							</select>
							<span id="contractInfo" style="margin-left: 10px;"><c:if test="${not empty contactOrder.contractFlow }"><a title="查看合同信息" href="javascript:contractInfo('${contactOrder.contractFlow }');">>></a></c:if></span>
						</td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">合同状态：</td>
						<td style="text-align: left;" id="contractStatusTd">
						  <c:forEach items="${contractStatusEnumList }" var="status">
						      <c:if test="${status.id eq contactOrderForm.contractStatusId }">${status.name }</c:if>
						  </c:forEach>
						</td>
					</tr>
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="basic" style="border-top:none;">
					<colgroup>
						<col width="13%"/>
						<col width="25%"/>
						<col width="12%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
					</colgroup>	
					<tr>
						<td style="text-align: right;padding-right: 10px;" rowspan="2"><span class="red">*</span>联&nbsp;系&nbsp;&nbsp;人：</td>
						<td rowspan="2" id="contactUserTd">
						  <select class="inputText validate[required]"  onchange="contactUserRelative(this.value);">
						     <option value="">请选择</option>
						     <c:forEach items="${customerUserList }" var="customerUser">
						         <option value="${customerUser.userFlow }" title="${customerUser.deptName }<c:if test="${not empty userCategoryMap[customerUser.userFlow] }">（${userCategoryMap[customerUser.userFlow] }）</c:if>" <c:if test="${customerUser.userFlow eq contactOrderForm.userFlow }">selected</c:if>>${customerUser.userName }</option>
						     </c:forEach>
						  </select>
						</td>
						<td style="text-align: right;padding-right: 10px;">部&#12288;&#12288;门：</td>
						<td id="deptNameTd">${contactOrderForm.deptName }</td>
						<td style="text-align: right;padding-right: 10px;">职&#12288;&#12288;务：</td>
						<td id="postNameTd">${contactOrderForm.postName }</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">固&#12288;&#12288;话：</td>
						<td id="telPhoneTd">${contactOrderForm.telPhone }</td>
						<td style="text-align: right;padding-right: 10px;">手&#12288;&#12288;机：</td>
						<td id="celPhoneTd">${contactOrderForm.celPhone }</td>
					</tr>
					
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="basic" style="border-top:none;">
					<colgroup>
						<col width="13%"/>
						<col width="25%"/>
						<col width="12%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
					</colgroup>	
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">发票信息：</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>客户开票抬头：</td>
						<td id="consumerNameTd">
						   <input type="text" value="${contactOrder.consumerName }" id="consumerNameTdText" class="inputText" style="width:200px; text-align:left;"/>
						</td>
					    <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>客户地址：</td>
					    <td colspan="3" id="consumerAddressTd">
					       <input type="text" value="${contactOrderForm.consumerAddress }" id="consumerAddressTdText" class="inputText" style="width:400px;text-align:left;"/>
					    </td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>开票内容：</td>
					    <td colspan="5">
					       <input type="text" value="${contactOrderForm.consumerAddress }" class="inputText validate[required]" style="width:400px;text-align:left;"/>
					    </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>发票号：</td>
						<td>
						   <input type="text" value="${contactOrder.consumerName }" class="inputText validate[required]" style="width:200px; text-align:left;"/>
						</td>
					    <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>开票金额：</td>
					    <td colspan="3">
					       <input type="text" value="${contactOrderForm.consumerAddress }" class="inputText validate[required,custom[number],maxSize[9]] money" style="width:400px;text-align:left;"/>
					    </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>我司开票抬头：</td>
						<td>
						   <select name="billOwnId" class="inputText validate[required]" style="width: 80%;">
						   	    <option value="">请选择</option>
						       <c:forEach items="${companyNameEnumList }" var="companyName">
						        <option value="${companyName.id }">${companyName.name }</option>
						       </c:forEach>
						    </select>
						</td>
					    <td style="text-align: right;padding-right: 10px;"><span class="red"></span></td>
					    <td colspan="3">
					    </td>
					</tr>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">流程信息</td>
					</tr>
					<tr>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>申请部门：</td>
				        <td>
				            <select id="applyDeptFlow" class="inputText validate[required]" style="width:120px;" name="applyDeptFlow" onchange="searchDeptUser(this,'applyUser');">
				               <option value="">请选择</option>
				               <c:forEach items="${deptList }" var="dept">
							      <option value="${dept.deptFlow }">${dept.deptName }</option>
							   </c:forEach>
				            </select>
				            <input type="hidden" name="applyDeptName" id="applyDeptName"/>
				        </td>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>申&nbsp;&nbsp;请&nbsp;&nbsp;人：</td>
				        <td>
				           <select id="applyUserFlow" class="inputText validate[required]" style="width:120px;" name="applyUserFlow" onchange="setUserName('applyUser');">
				               <option value="">请选择申请人</option>
				            </select>
				            <input type="hidden" name="applyUserName" id="applyUserName"/>
				        </td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>申请日期：</td>
				        <td>
				           <input type="text"id="vaildDate" name="applyDate" class="inputText ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="display: ;">
				        	<span id="date" style="display: none;"></span>
				        </td>
					</tr>
					<tr>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>开票部门：</td>
				        <td>
				            <select id="issueDeptFlow" class="inputText validate[required]" style="width:120px;" name="issueDeptFlow" onchange="searchDeptUser(this,'issueUser');">
				               <option value="">请选择</option>
				               <c:forEach items="${deptList }" var="dept">
							      <option value="${dept.deptFlow }">${dept.deptName }</option>
							   </c:forEach>
				            </select>
				            <input type="hidden" name="issueDeptName" id="issueDeptName"/>
				        </td>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>开&nbsp;&nbsp;票&nbsp;&nbsp;人：</td>
				        <td>
				           <select id="issueUserFlow" class="inputText validate[required]" style="width:120px;" name="issueUserFlow" onchange="setUserName('issueUser');">
				               <option value="">请选择开票人</option>
				            </select>
				            <input type="hidden" name="issueUserName" id="issueUserName"/>
				        </td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>开票日期：</td>
				        <td>
				           <input type="text"id="vaildDate" name="issueDate" class="inputText ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="display: ;">
				        	<span id="date" style="display: none;"></span>
				        </td>
					</tr>
					
					<tr>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>领用部门：</td>
				        <td>
				            <select id="useDeptFlow" class="inputText validate[required]" style="width:120px;" name="useDeptFlow" onchange="searchDeptUser(this,'useUser');">
				               <option value="">请选择</option>
				               <c:forEach items="${deptList }" var="dept">
							      <option value="${dept.deptFlow }">${dept.deptName }</option>
							   </c:forEach>
				            </select>
				            <input type="hidden" name="useDeptName" id="useDeptName"/>
				        </td>
				        <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>领&nbsp;&nbsp;用&nbsp;&nbsp;人：</td>
				        <td>
				           <select id="useUserFlow" class="inputText validate[required]" style="width:120px;" name="useUserFlow" onchange="setUserName('useUser');">
				               <option value="">请选择领用人</option>
				            </select>
				            <input type="hidden" name="useUserName" id="useUserName"/>
				        </td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>领用日期：</td>
				        <td>
				           <input type="text"id="vaildDate" name="useDate" class="inputText ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="display: ;">
				        	<span id="date" style="display: none;"></span>
				        </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">备&#12288;&#12288;注</td>
						<td colspan="5">
						 <textarea id="productItem" class="xltxtarea validate[maxSize[250]]" name="remark" style="width: 770px;text-align: left;">${contactOrder.remark }</textarea>
						</td>
					</tr>
				</table>
				<c:if test="${param.type != 'audit' }">
				<div class="button">
					<c:if test="${param.type != 'show'}">
					<input class="search" type="button" value="保&#12288;存" onclick="save();" />
					</c:if>
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxCloseMessager();" />
				</div>
				</c:if>
			</form>
			</div>
		</div>
	</div>
</body>
</html>