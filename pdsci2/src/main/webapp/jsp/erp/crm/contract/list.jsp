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
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		$(document).ready(function(){

			loadCustomerList();
			loadContractList();
		});
		function loadCustomerList() {
			var customers = new Array();
			var url = "<s:url value='/erp/crm/searchCustomerJson'/>";
			jboxPostAsync(url,null,function(data){
				if(data!=null){
					for (var i = 0; i < data.length; i++) {
						var aliasName=data[i].aliasName;
						if(data[i].aliasName==null){
							aliasName="";
						}
						customers[i]=new Array(data[i].customerName,data[i].customerName,aliasName);
					}
				}
			},null,false);
			$("#searchParam_Customer").suggest(customers,{
				attachObject:'#suggest_Customer',
				dataContainer:'#searchParam_Customer',
				triggerFunc:function(){},
				enterFunc:function(){}
			});
		}
		function loadContractList() {
			var contractNames = new Array();
			var contractNos = new Array();
			var url = "<s:url value='/erp/crm/searchContractJson'/>";
			jboxPostAsync(url,null,function(data){
				if(data!=null){
					for (var i = 0; i < data.length; i++) {
						contractNames[i]=new Array(data[i].contractName,data[i].contractName);
						contractNos[i]=new Array(data[i].contractNo,data[i].contractNo);
					}
				}
			},null,false);
			$("#contractName").suggest(contractNames,{
				attachObject:'#suggest_ContractName',
				dataContainer:'#contractName',
				triggerFunc:function(){},
				enterFunc:function(){}
			});
			$("#contractNo").suggest(contractNos,{
				attachObject:'#suggest_ContractNo',
				dataContainer:'#contractNo',
				triggerFunc:function(){},
				enterFunc:function(){}
			});
		}
		function initCustomer()
		{

			var url = "<s:url value='/erp/crm/searchCustomerJson'/>";
			jboxPostAsync(url,null,function(data){
				var datas=[];
				if(data!=null){
					for (var i = 0; i < data.length; i++) {
						var d={};
						d.id=data[i].customerName;
						d.text=data[i].customerName;
						datas.push(d);

					}
				}
				$.selectSuggest('customerName',datas,null);
			},null,false);
		}
		</script>
<script type="text/javascript">
function fundCount(){
	var fundBox=$(".money");
	var fundSun=0;
	$.each(fundBox,function(i,n){
		$(n).mask('000000000000000', {reverse: true});
		fundSun+=parseInt($(n).text());
		$(n).mask('000,000,000,000,000', {reverse: true});
	});
	$("#fundCount").text(fundSun);
	$("#fundCount").mask('000,000,000,000,000', {reverse: true});
}
function allMoney(){

	$("#allMoney").text("${allMoney}");
	$("#allMoney").mask('000,000,000,000,000', {reverse: true});
}

function search() {
	jboxStartLoading();
	$("#searchForm").submit();
}

function toPage(page){
	var form = $("#searchForm");
	$("#currentPage").val(page);
	form.submit();
}

function imgToggle(contractId){
	$("._img"+contractId).toggle();
	showSubContract(contractId);
	
}
function moneyMask(){
	var moneyBox=$(".money");
	$.each(moneyBox,function(i,n){
		$(n).mask('000,000,000,000,000', {reverse: true});
	});
}

function contractInfo(contractFlow) {
	$("#prevContractFlow").val($("#sub_"+contractFlow).val());
	var w = $('.mainright').width();
	var h = $('.mainright').height();
	var personal='${GlobalConstant.USER_LIST_PERSONAL}';
	var local='${GlobalConstant.USER_LIST_LOCAL}';
	var nowscope='${sessionScope[GlobalConstant.USER_LIST_SCOPE]}';
	var url="";
	if(personal==nowscope || local==nowscope){
	   url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow=" + contractFlow+"&type=open&userEdit=${GlobalConstant.FLAG_Y}";
	}else{
	   url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow=" + contractFlow;
	}
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'合同详细信息',w,h,null,false);
}

function changeContractTypeOption(obj){
	var type=$(obj).val();
	$("#contractTypeId").removeAttr("disabled");
	$("#contractTypeId").empty();
	if(type!='${contractCategoryEnumSecond.id}' && type != '${contractCategoryEnumOperation.id}'){
		var options=$("#copy_Sales").find("option");
		$.each(options,function(i,n){
			$("#contractTypeId").append($(n).clone());
		});
	} else if (type == '${contractCategoryEnumOperation.id}') {
		var options=$("#copy_Operation").find("option");
		$.each(options,function(i,n){
			$("#contractTypeId").append($(n).clone());
		});
	}else{
		var options=$("#copy_"+type).find("option");
		$.each(options,function(i,n){
			$("#contractTypeId").append($(n).clone());
		});
	}
	//合同类别选择“二次合同”时，将“隐藏二次合同”取消勾选
	if (type=='${contractCategoryEnumSecond.id}') {
		$("#noSecond").attr("checked",false);
	} else if (type != "") {
		$("#noSecond").attr("checked",true);
	}
}
$(document).ready(function(){
	if('${param.bigRegionTypeId}'=='' ||'${param.bigRegionTypeId}'== undefined)
	{
		a();
	}else {
		changeProve($("#bigRegionTypeId"));
	}
	changeContractTypeOption($("#contractCategoryId"));
	searchDeptUser($("#signDeptFlow"));
	if($("#prevContractFlow").val()!=""){
		imgToggle($("#prevContractFlow").val());
	}
	if($("#checkContractFlow").val()!=""){
		checkedView($("#checkContractFlow").val());
	}
	moneyMask();
	fundCount();
	allMoney();
});

function setChargeUserSelected(){
	var options=$("#chargeUserFlow").find("option");
	$.each(options,function(i,n){
		if($(n).val()=='${param.chargeUserFlow}'){
			$(n).attr("selected","selected");
		}
		if('${GlobalConstant.USER_LIST_PERSONAL}'=='${sessionScope[GlobalConstant.USER_LIST_SCOPE]}' 
				&& $(n).val()=='${sessionScope.currUser.userFlow}'){
			$("#chargeUserFlow").attr("disabled","disabled");
			$(n).attr("selected","selected");
		}
	});
}
function searchDeptUser(obj){
	 $("#chargeUserFlow").empty();
	 $("#chargeUserFlow").removeAttr("disabled");
	 var deptFlow=$(obj).val();
	 if(deptFlow==""){
		 var option='<option>请选择负责部门</option>';
		 $("#chargeUserFlow").append(option);
		 $("#chargeUserFlow").attr("disabled","disabled");
	 }else{
		 jboxPost("<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow,null,function(data){
			 if(data!=null){
			 $("#chargeUserFlow").append('<option></option>');
			 for ( var i = 0; i < data.length; i++) {
					$("#chargeUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
				 }
			 setChargeUserSelected();
		  }
		 },null,false);
	 }	 
}

function checkedView(contractFlow){
	$.each($(".contractTr"),function(i,n){
		$(n).css("background-color","");
	});
	$("#contractTr_"+contractFlow).css("background-color","pink");
	$("#checkContractFlow").val(contractFlow);
}
function resetCheckContractFlow(){
	$("#checkContractFlow").val("");
}

function customerInfo(customerFlow){
	var w = $('.mainright').width();
	var h = $('.mainright').height();
	var url = "<s:url value='/erp/crm/customerInfo'/>?customerFlow=" + customerFlow+"&type=open";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'客户详细信息',w,h,null,false);
}
function showSubContract(contractFlow){
	var content = $("#tbody_"+contractFlow);
	var checkContractFlow=$("#checkContractFlow").val();
	var signDeptFlow='${param.signDeptFlow}';
	var url = "<s:url value ='/erp/crm/appendList'/>?contractFlow="+contractFlow;
	if(checkContractFlow!=""){
		url+="&checkContractFlow="+checkContractFlow;
	}
	if(signDeptFlow!=""){
		url+="&signDeptFlow="+signDeptFlow;
	}
	if($.trim(content.html())==""){
		jboxLoad("tbody_"+contractFlow,url,false);
	}
     content.slideToggle("fast",function(){
    	 moneyMask();
     });
}

function resetMaintainDate() {
	if ($("#maintainDue").attr("checked")) {
		$("input[name='maintainDueDateStart']").val("");
		$("input[name='maintainDueDateEnd']").val("");
	}
}

function deleteContract(contractFlow){
	msg = "确认删除？";
	jboxConfirm(msg,function () {
		var url = "<s:url value='/erp/crm/deleteContract'/>?contractFlow=" + contractFlow;
		jboxPost(url, null,
				function(resp){
					if(resp == "${GlobalConstant.DELETE_SUCCESSED}"){
						search();
					}
				},null,true);	
	});
}
function billList(contractFlow) {
	var w = $('.mainright').width();
	var h = $('.mainright').height();
	var url = "<s:url value='/erp/sales/billList'/>?contractFlow" + contractFlow;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'合同款项执行信息',w,h,null,false);
}

function a(){
	<c:if test="${GlobalConstant.USER_LIST_GLOBAL == sessionScope[GlobalConstant.USER_LIST_SCOPE] or GlobalConstant.USER_LIST_CHARGE == sessionScope[GlobalConstant.USER_LIST_SCOPE]}">

	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
		$.cxSelect.defaults.url = json;
		$.cxSelect.defaults.nodata = "none";
		$("#provCityAreaId").cxSelect({
			selects : ["province", "city"],
			nodata : "none",
			firstValue : ""
		});
	},null,false);
	</c:if>
	<c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope[GlobalConstant.USER_LIST_SCOPE] and GlobalConstant.USER_LIST_CHARGE != sessionScope[GlobalConstant.USER_LIST_SCOPE]}">
	var provUrl= "<s:url value='/erp/crm/searchPopdom'/>";
	jboxPost(provUrl,null,function(data){
		if(data){
			var provIdArray=new Array();
			for(var i=0;i<data.length;i++){
				provIdArray.push(data[i]);
			}
			var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
			 jboxGet(url,null, function(json) {
				// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
				var newJsonData=new Array();
				var j=0;
				for(var i=0;i<json.length;i++){
					var provData = json[i];
					if(provIdArray.length>0){
						if(provIdArray.indexOf(provData.v)>-1){
							newJsonData[j++]=provData;
						}
					}else{
						newJsonData[j++]=provData;
					}
				}
				$.cxSelect.defaults.url = newJsonData; 
				$.cxSelect.defaults.nodata = "none"; 
				$("#provCityAreaId").cxSelect({ 
				    selects : ["province", "city", "area"], 
				    nodata : "none",
					firstValue : ""
				}); 
			},null,false);
		}
	},null,false);
	</c:if>
}
function changeProve(obj)
{
	var v=$(obj).val();
	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
		var newJsonData=new Array();
		var j=0;
		for(var i=0;i<json.length;i++){
			var provData = json[i];
			if(v=="00")
			{
				newJsonData[j++]=provData;
			}else if(v=="01"){
				if(provData.v=='310000'||provData.v=='320000'||provData.v=='330000'||provData.v=='340000'||provData.v=='370000'){
					newJsonData[j++]=provData;
				}
			}else if(v=="02"){
				if(provData.v=='350000'||provData.v=='440000'||provData.v=='450000'||provData.v=='460000'||provData.v=='520000'||provData.v=='530000'){
					newJsonData[j++]=provData;
				}
			}else if(v=="03"){
				if(provData.v=='110000'||provData.v=='120000'||provData.v=='130000'||provData.v=='140000'||provData.v=='150000'||provData.v=='210000'||provData.v=='220000'||provData.v=='230000'){
					newJsonData[j++]=provData;
				}
			}else if(v=="04"){
				if(!(provData.v=='310000'||provData.v=='320000'||provData.v=='330000'||provData.v=='340000'||provData.v=='370000'
				||provData.v=='350000'||provData.v=='440000'||provData.v=='450000'||provData.v=='460000'||provData.v=='520000'||provData.v=='530000'
				||provData.v=='110000'||provData.v=='120000'||provData.v=='130000'||provData.v=='140000'||provData.v=='150000'||provData.v=='210000'||provData.v=='220000'||provData.v=='230000'
				)) {
					newJsonData[j++]=provData;
				}
			}
		}
		$.cxSelect.defaults.url = newJsonData;
		$.cxSelect.defaults.nodata = "none";
		$("#provCityAreaId").cxSelect({
			selects : ["province", "city"],
			nodata : "none",
			firstValue : ""
		});
	},null,false);
}
function exportExcel(){
	var url = "<s:url value='/erp/crm/exportExcel/${sessionScope[GlobalConstant.USER_LIST_SCOPE] }'/>";
	jboxTip("导出中…………");
	jboxSubmit($('#searchForm'), url, null, null);
	jboxEndLoading();
}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value="/erp/crm/searchContract/${sessionScope[GlobalConstant.USER_LIST_SCOPE] }" />"	method="post">
                    <input type="hidden" id="currentPage2" name="currentPage2" value="${param.currentPage }"/>
                    <input type="hidden" id="checkContractFlow" name="checkContractFlow" value="${param.checkContractFlow }"/>
                    <input type="hidden" id="prevContractFlow" name="prevContractFlow" value="${param.prevContractFlow }"/>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL == sessionScope[GlobalConstant.USER_LIST_SCOPE] or GlobalConstant.USER_LIST_CHARGE == sessionScope[GlobalConstant.USER_LIST_SCOPE]}">
                        <div style="margin-bottom: 10px;">
                                   客户名称：<input type="text" id="searchParam_Customer" name="customerName" placeholder="客户名称/别名" value="${param.customerName }" autocomplete="off" class="xltext" style="width:120px;"/>
                            <label><input type="checkbox" name="isLike" value="1" <c:if test="${param.isLike eq '1'}">checked</c:if>/>&nbsp;模糊查询</label>
                            <div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>

                            &#12288;&#12288;合同名称：<input type="text" id="contractName" name="contractName" value="${param.contractName }" class="xltext" autocomplete="off" style="width:100px;"/>
                            <div id="suggest_ContractName" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 106px;"></div>
                            合同类别：<select class="xlselect" id="contractCategoryId" name="contractCategoryId" onchange="changeContractTypeOption(this);" style="width:108px;">
                                        <option value="">请选择</option>
                                        <c:forEach var="contractCategory" items="${contractCategoryEnumList}">
                                            <option value="${contractCategory.id}"<c:if test="${param.contractCategoryId eq contractCategory.id }">selected="selected"</c:if>>${contractCategory.name}</option>
                                        </c:forEach>
                                    </select>
                                  &#12288;合同类型：<select class="xlselect" id="contractTypeId" name="contractTypeId" style="width:118px;">
                                    <option value="">请选择合同类别</option>
                                   </select>
                            <input type="checkbox" id="noSecond" name="noSecond" value="${GlobalConstant.FLAG_Y }" ${noSecond eq GlobalConstant.FLAG_Y?'checked':'' }><label for="noSecond">&nbsp;隐藏二次合同</label>
                        </div>
                        <div style="margin-bottom: 10px;">
                                   合&nbsp;同&nbsp;&nbsp;号：
                            <input type="text" id="contractNo" name="contractNo"  value="${param.contractNo }" class="xltext"autocomplete="off" style="width: 120px;"/>
                            <div id="suggest_ContractNo" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
						负责部门：<select name="signDeptFlow" id="signDeptFlow" class="xlselect" onchange="searchDeptUser(this);" style="width: 108px;" >
                                       <option value="">请选择</option>
                                       <%--<option value="f48a5060931147daa467eadbb5885629" <c:if test="${'f48a5060931147daa467eadbb5885629' eq param.signDeptFlow }">selected="selected"</c:if>>总经办</option>--%>
                                       <%--<option value="a8a0aeef43c846c4a976589184b4ed8b" <c:if test="${'a8a0aeef43c846c4a976589184b4ed8b' eq param.signDeptFlow }">selected="selected"</c:if>>销售一部</option>--%>
                                       <%--<option value="a02a8190fc58440f8635b7c5cfcd1949" <c:if test="${'a02a8190fc58440f8635b7c5cfcd1949' eq param.signDeptFlow }">selected="selected"</c:if>>销售二部</option>--%>
                                       <%--<option value="d7f1afc58416446ba414d3c69bb204e2" <c:if test="${'d7f1afc58416446ba414d3c69bb204e2' eq param.signDeptFlow }">selected="selected"</c:if>>销售三部</option>--%>
                                       <%--<option value="5b42e66a13e742d3a796f17d8104e02d" <c:if test="${'5b42e66a13e742d3a796f17d8104e02d' eq param.signDeptFlow }">selected="selected"</c:if>>销售四部</option>--%>
                                       <%--<option value="1f56f216c3fd4dc4a1b23e7dda645c0b" <c:if test="${'1f56f216c3fd4dc4a1b23e7dda645c0b' eq param.signDeptFlow }">selected="selected"</c:if>>销售五部</option>--%>
                                       <c:forEach items="${deptList }" var="dept">
                                         <option value="${dept.deptFlow }"<c:if test="${dept.deptFlow eq param.signDeptFlow }">selected="selected"</c:if>>${dept.deptName }</option>
                                       </c:forEach>
                                   </select>
                               合同负责人：<select name="chargeUserFlow" id="chargeUserFlow" class="xlselect" style="width:118px;">
                                     <option value="">请选择负责部门</option>
                                   </select>
                             <input type="checkbox" id="maintainDue" name="maintainDue" onclick="resetMaintainDate();" value="${GlobalConstant.FLAG_Y }" ${maintainDue eq GlobalConstant.FLAG_Y?'checked':'' }><label for="maintainDue">&nbsp;维护过期合同</label>
                        </div>
						<div id="provCityAreaId"  style="margin-bottom: 10px;">
							区&#12288;&#12288;域：<select  class="xlselect" name="bigRegionTypeId" id="bigRegionTypeId" onchange="changeProve(this)"  style="width:128px;">
							<option value="00"  <c:if test="${param.bigRegionTypeId=='00' or empty param.bigRegionTypeId }">selected</c:if>>全国</option>
							<option value="01"  <c:if test="${param.bigRegionTypeId=='01' }">selected</c:if>>华东地区</option>
							<option value="02"  <c:if test="${param.bigRegionTypeId=='02' }">selected</c:if>>华南地区</option>
							<option value="03"  <c:if test="${param.bigRegionTypeId=='03' }">selected</c:if>>华北地区</option>
							<option value="04"  <c:if test="${param.bigRegionTypeId=='04' }">selected</c:if>>中西部地区</option>
						</select>
							省&#12288;&#12288;市：
							<select id="customerProvId" name="customerProvId" class="province xlselect" data-value="${param.customerProvId}" data-first-title="选择省" style="width:128px;"></select>
							<select id="customerCityId" name="customerCityId" class="city xlselect" data-value="${param.customerCityId}" data-first-title="选择市" style="width:128px;"></select>
						</div>
                        <div style="margin-bottom: 10px;">
                            合同状态：<select class="xlselect" name="contractStatusId" style="width:128px;">
                                        <option value="">请选择</option>
                                        <c:forEach var="contractStatus" items="${contractStatusEnumList}">
                                            <option value="${contractStatus.id}"<c:if test="${param.contractStatusId eq contractStatus.id }">selected="selected"</c:if>>${contractStatus.name}</option>
                                        </c:forEach>
                                    </select>
                                 签订日期：<input type="text" name="signDateStart" value="${param.signDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                                   <input type="text" name="signDateEnd" value="${param.signDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/>
                                   <%--   合同到期日：<input type="text" name="contractDueDateStart" value="${param.contractDueDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                                   <input type="text" name="contractDueDateEnd" value="${param.contractDueDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/> --%>
                            维护到期日：<input type="text" name="maintainDueDateStart" value="${param.maintainDueDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                                    <input type="text" name="maintainDueDateEnd" value="${param.maintainDueDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/>
                        </div>
                        <div style="margin-bottom: 10px;">
						合同创建日期：<input type="text" name="createDateStart" value="${param.createDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
						<input type="text" name="createDateEnd" value="${param.createDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/>
						排序字段：<select class="xlselect" name="orderByCase" style="width:128px;">
                        <option value="">默认</option>
                        <option value="CONTRACT_NO" <c:if test="${param.orderByCase=='CONTRACT_NO' }">selected</c:if>>合同号</option>
                        <option value="CONTRACT_ARCHIVES_NO" <c:if test="${param.orderByCase=='CONTRACT_ARCHIVES_NO' }">selected</c:if>>合同档案号</option>
                        <option value="CUSTOMER_NAME" <c:if test="${param.orderByCase=='CUSTOMER_NAME' }">selected</c:if>>客户名称</option>
                        <option value="CONTRACT_NAME" <c:if test="${param.orderByCase=='CONTRACT_NAME' }">selected</c:if>>合同名称</option>
                        <option value="CONTRACT_CATEGORY_NAME" <c:if test="${param.orderByCase=='CONTRACT_CATEGORY_NAME' }">selected</c:if>>合同类别</option>
                        <option value="CONTRACT_TYPE_NAME" <c:if test="${param.orderByCase=='CONTRACT_TYPE_NAME' }">selected</c:if>>合同类型</option>
                        <c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] == GlobalConstant.USER_LIST_GLOBAL)}">
                            <option value="SIGN_DEPT_NAME" <c:if test="${param.orderByCase=='SIGN_DEPT_NAME' }">selected</c:if>>负责部门</option>
                        </c:if>
                        <option value="CHARGE_USER_NAME" <c:if test="${param.orderByCase=='CHARGE_USER_NAME' }">selected</c:if>>合同负责人</option>
                        <option value="CONTRACT_STATUS_NAME" <c:if test="${param.orderByCase=='CONTRACT_STATUS_NAME' }">selected</c:if>>合同状态</option>
                        <option value="SIGN_DATE" <c:if test="${param.orderByCase=='SIGN_DATE' }">selected</c:if>>签订日期</option>
                        <option value="CREATE_TIME" <c:if test="${param.orderByCase=='CREATE_TIME' }">selected</c:if>>合同创建日期</option>
                        <option value="CONTRACT_DUE_DATE" <c:if test="${param.orderByCase=='CONTRACT_DUE_DATE' }">selected</c:if>>合同到期日</option>
                        <option value="MAINTAIN_DUE_DATE" <c:if test="${param.orderByCase=='MAINTAIN_DUE_DATE' }">selected</c:if>>维护到期日</option>
                        <option value="CONTRACT_FUND" <c:if test="${param.orderByCase=='CONTRACT_FUND' }">selected</c:if>>合同金额</option>
                    </select>
                        排序方式：<input type="radio" value="desc"<c:if test="${param.orderAction=='desc' }">checked</c:if> name="orderAction" />倒序&#12288;
                        <input type="radio" value="asc"<c:if test="${param.orderAction=='asc' }">checked</c:if>   name="orderAction"/>正序&#12288;
						<input type="checkbox" id="notCG" name="notCG" <c:if test="${notCG=='Y'}">checked</c:if> value="${GlobalConstant.FLAG_Y }"><label for="notCG">不含采购合同</label>
                        <input type="button" class="search"  onclick="resetCheckContractFlow();search();" value="查&#12288;询">
                        <c:if test="${isCaiWu eq GlobalConstant.FLAG_Y}">
                            <%--<input type="button" class="search"  onclick="exportExcel();" value="导出汇总">--%>
                        </c:if>
                        <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    </div>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope[GlobalConstant.USER_LIST_SCOPE] and GlobalConstant.USER_LIST_CHARGE != sessionScope[GlobalConstant.USER_LIST_SCOPE]}">
                        <div style="margin-bottom: 10px;">
                             客户名称：<input type="text" id="searchParam_Customer" name="customerName" placeholder="客户名称/别名" value="${param.customerName }" autocomplete="off" class="xltext" style="width:120px;"/>
                            <div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
                            &#12288;合同名称：<input type="text" id="contractName" name="contractName" value="${param.contractName }" class="xltext" autocomplete="off" style="width:100px;"/>
                            <div id="suggest_ContractName" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 106px;"></div>
                            &#12288;签订日期：<input type="text" name="signDateStart" value="${param.signDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                            <input type="text" name="signDateEnd" value="${param.signDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/>
                            <input type="checkbox" id="noSecond" name="noSecond" value="${GlobalConstant.FLAG_Y }" ${noSecond eq GlobalConstant.FLAG_Y?'checked':'' }><label for="noSecond">&nbsp;隐藏二次合同</label>&#12288;
                        </div>
                        <div style="margin-bottom: 10px;">
                             合同类别：<select class="xlselect" id="contractCategoryId" name="contractCategoryId" onchange="changeContractTypeOption(this);" style="width:128px;">
                                        <option value="">请选择</option>
                                        <c:forEach var="contractCategory" items="${contractCategoryEnumList}">
                                            <option value="${contractCategory.id}"<c:if test="${param.contractCategoryId eq contractCategory.id }">selected="selected"</c:if>>${contractCategory.name}</option>
                                        </c:forEach>
                                    </select>
                            &#12288;合同类型：<select class="xlselect" id="contractTypeId" name="contractTypeId" style="width:128px;">
                                    <option value="">请选择合同类别</option>
                                   </select>
                                   维护到期日：<input type="text" name="maintainDueDateStart" value="${param.maintainDueDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                                   <input type="text" name="maintainDueDateEnd" value="${param.maintainDueDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/>
                            <input type="checkbox" id="maintainDue" name="maintainDue" onclick="resetMaintainDate();" value="${GlobalConstant.FLAG_Y }" ${maintainDue eq GlobalConstant.FLAG_Y?'checked':'' }><label for="maintainDue">&nbsp;维护过期合同</label>
                        </div>
                        <div style="margin-bottom: 10px;">
                            合&nbsp;同&nbsp;&nbsp;号：
                            <input type="text" id="contractNo" name="contractNo"  value="${param.contractNo }" class="xltext"autocomplete="off" style="width: 120px;"/>
                            <div id="suggest_ContractNo" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>

							<c:if test="${GlobalConstant.USER_LIST_LOCAL == sessionScope[GlobalConstant.USER_LIST_SCOPE]}">
							<span style="display:none;">
                                        负责部门：<select class="xlselect" style="width: 128px;"disabled="disabled">
                                           <option value="${sessionScope.currUser.deptFlow}">${sessionScope.currUser.deptName}</option>
                                       </select>
                                       <input type="hidden" name="signDeptFlow" id="signDeptFlow" value="${sessionScope.currUser.deptFlow }"/>
                             </span>
							</c:if>
                             合同负责人：<select name="chargeUserFlow" id="chargeUserFlow" class="xlselect" style="width:128px;">
                                       </select>
                            排序字段：<select class="xlselect" name="orderByCase" style="width:128px;">
                            <option value="">默认</option>
                            <option value="CONTRACT_NO" <c:if test="${param.orderByCase=='CONTRACT_NO' }">selected</c:if>>合同号</option>
                            <option value="CONTRACT_ARCHIVES_NO" <c:if test="${param.orderByCase=='CONTRACT_ARCHIVES_NO' }">selected</c:if>>合同档案号</option>
                            <option value="CUSTOMER_NAME" <c:if test="${param.orderByCase=='CUSTOMER_NAME' }">selected</c:if>>客户名称</option>
                            <option value="CONTRACT_NAME" <c:if test="${param.orderByCase=='CONTRACT_NAME' }">selected</c:if>>合同名称</option>
                            <option value="CONTRACT_CATEGORY_NAME" <c:if test="${param.orderByCase=='CONTRACT_CATEGORY_NAME' }">selected</c:if>>合同类别</option>
                            <option value="CONTRACT_TYPE_NAME" <c:if test="${param.orderByCase=='CONTRACT_TYPE_NAME' }">selected</c:if>>合同类型</option>
                            <c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] == GlobalConstant.USER_LIST_GLOBAL)}">
                                <option value="SIGN_DEPT_NAME" <c:if test="${param.orderByCase=='SIGN_DEPT_NAME' }">selected</c:if>>负责部门</option>
                            </c:if>
                            <option value="CHARGE_USER_NAME" <c:if test="${param.orderByCase=='CHARGE_USER_NAME' }">selected</c:if>>合同负责人</option>
                            <option value="CONTRACT_STATUS_NAME" <c:if test="${param.orderByCase=='CONTRACT_STATUS_NAME' }">selected</c:if>>合同状态</option>
                            <option value="SIGN_DATE" <c:if test="${param.orderByCase=='SIGN_DATE' }">selected</c:if>>签订日期</option>
                            <option value="CREATE_TIME" <c:if test="${param.orderByCase=='CREATE_TIME' }">selected</c:if>>合同创建日期</option>
                            <option value="CONTRACT_DUE_DATE" <c:if test="${param.orderByCase=='CONTRACT_DUE_DATE' }">selected</c:if>>合同到期日</option>
                            <option value="MAINTAIN_DUE_DATE" <c:if test="${param.orderByCase=='MAINTAIN_DUE_DATE' }">selected</c:if>>维护到期日</option>
                            <option value="CONTRACT_FUND" <c:if test="${param.orderByCase=='CONTRACT_FUND' }">selected</c:if>>合同金额</option>
                        </select>
                            排序方式：<input type="radio" value="desc"<c:if test="${param.orderAction=='desc' }">checked</c:if> name="orderAction" />倒序&#12288;
                            <input type="radio" value="asc"<c:if test="${param.orderAction=='asc' }">checked</c:if>   name="orderAction"/>正序&#12288;
                            &#12288;合同状态：<select class="xlselect" name="contractStatusId" style="width:108px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="contractStatus" items="${contractStatusEnumList}">
                                        <option value="${contractStatus.id}"<c:if test="${param.contractStatusId eq contractStatus.id }">selected="selected"</c:if>>${contractStatus.name}</option>
                                    </c:forEach>
                                </select>
                            <%--   合同到期日：<input type="text" name="contractDueDateStart" value="${param.contractDueDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;margin: 0"/>&nbsp;~&nbsp;
                                   <input type="text" name="contractDueDateEnd" value="${param.contractDueDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:100px;"/> --%>

                        </div>
                        <div id="provCityAreaId">
                                   地&#12288;&#12288;区：<select id="customerProvId" name="customerProvId" class="province xlselect" data-value="${param.customerProvId}" data-first-title="选择省" style="width: 130px;"></select>
                            <select id="customerCityId" name="customerCityId" class="city xlselect" data-value="${param.customerCityId}" data-first-title="选择市" style="width: 130px;"></select>
                            <select id="customerAreaId" name="customerAreaId" class="area xlselect" data-value="${param.customerAreaId}" data-first-title="选择地区" style="width: 130px;"></select>
                            <input type="button" class="search"  onclick="resetCheckContractFlow();search();" value="查&#12288;询">
                            <c:if test="${isCaiWu eq GlobalConstant.FLAG_Y}">
                                <%--<input type="button" class="search"  onclick="exportExcel();" value="导出汇总">--%>
                            </c:if>
                            <input id="currentPage" type="hidden" name="currentPage" value=""/>
                        </div>
                    </c:if>
				</form>
			</div>			
			<table id="orgTable" class="xllist">
				<colgroup>
				    <col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] eq GlobalConstant.USER_LIST_GLOBAL) }">
					<col width="6.6%"/>
					</c:if>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="6.6%"/>
					<col width="7.6%"/>
				</colgroup>
				<thead>
				<tr>
				    <th>合同号</th>
				    <th>合同档案号</th>
					<th>客户名称</th>
					<th>合同名称</th>
					<th>合同类别</th>
					<th>合同类型</th>
					<c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] eq GlobalConstant.USER_LIST_GLOBAL)}">
					<th>负责部门</th>
					</c:if>
					<th>合同负责人</th>
					<th>合同状态</th>
					<th>签订日期</th>
					<th>合同创建日期</th>
					<th>合同到期日</th>
					<th>维护到期日</th>
					<th>合同金额</th>
					<th>操作</th>
				</tr>
				</thead>						
				<tbody>
				<c:if test="${empty contractListExt }">
				 <tr>
					 <c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] eq GlobalConstant.USER_LIST_GLOBAL)}">
						 <td colspan="15">无记录</td>
					 </c:if>
					 <c:if test="${not empty param.signDeptFlow or (sessionScope[GlobalConstant.USER_LIST_SCOPE] ne GlobalConstant.USER_LIST_GLOBAL)}">
						 <td colspan="14">无记录</td>
					 </c:if>
				 </tr>
				</c:if>
				<c:forEach items="${contractListExt }" var="contractExt">
				<tr id="contractTr_${contractExt.contractFlow }" class="contractTr">
					
					<td style="text-align: left;">
					  <c:if test="${contractExt.subCount>0 }">
					  <img class="_img${contractExt.contractFlow}" title="展开子合同" src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_03.png" />" style="margin-left: 5px;margin-right: 5px;cursor: pointer;" onclick="imgToggle('${contractExt.contractFlow}');" />
					  <img class="_img${contractExt.contractFlow}" title="收起子合同" src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_05.png" />" style="margin-left: 5px;margin-right: 5px;cursor: pointer;display: none;" onclick="imgToggle('${contractExt.contractFlow}');"/>
					  </c:if>
					  <span <c:if test="${!(contractExt.subCount>0) }">style="margin-left:22px;"</c:if>>${contractExt.contractNo }</span>
					</td>
					<td>${contractExt.contractArchivesNo }</td>
					<td><a href="javascript:customerInfo('${contractExt.customer.customerFlow }')">${contractExt.customer.customerName }</a></td>
					<td>${contractExt.contractName }</td>
					<td>${contractExt.contractCategoryName }</td>
					<td>${contractExt.contractTypeName }</td>
					<c:if test="${empty param.signDeptFlow and (sessionScope[GlobalConstant.USER_LIST_SCOPE] == GlobalConstant.USER_LIST_GLOBAL)}">
					<td>${contractExt.signDeptName }</td>
					</c:if>
					<td>${contractExt.chargeUserName }</td>
					<td>${contractExt.contractStatusName }</td>
					<td>${contractExt.signDate }</td>
					<td>${pdfn:transDateTimeForPattern(contractExt.createTime,"yyyyMMddHHmmss","yyyy-MM-dd") }</td>
					<td>${contractExt.contractDueDate }</td>
					<td>${contractExt.maintainDueDate }</td>
					<td><label class="money">${contractExt.contractFund }</label></td>
					<td>[<a href="javascript:contractInfo('${contractExt.contractFlow }');" onclick="checkedView('${contractExt.contractFlow }');">查看</a>] <!-- | -->
						<c:if test="${(pdfn:contain('action-erp-zjl-crmhtgl-crmhtcx-scht', sessionScope.currUserMenuIdList) and (sessionScope[GlobalConstant.USER_LIST_SCOPE] == GlobalConstant.USER_LIST_CHARGE)) or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
					    | [<a href="javascript:deleteContract('${contractExt.contractFlow }');">删除</a>]
					    </c:if>
					    <%--[<a href="javascript:billList('${contractExt.contractFlow}');">开票</a>]--%>
					</td>
				</tr>
				<c:if test="${contractExt.subCount>0 }">
				   <tbody id="tbody_${contractExt.contractFlow }" style="display: none;">
				    
				   </tbody>
				</c:if>
			    </c:forEach>
			    <c:if test="${!empty contractListExt }">
			    <c:set var="colNum" value="${(empty param.signDeptFlow and sessionScope[GlobalConstant.USER_LIST_SCOPE] == GlobalConstant.USER_LIST_GLOBAL)?'12':'11'}"></c:set>
				    <tr>
				    <td colspan="${colNum }" style="text-align: right;border: 0;">本页金额总计：</td>
				    <td colspan="" style="text-align: left;padding-left: 5px;"><font color="red" id="fundCount"></font>元</td>
					<td colspan="" style="text-align: right;border: 0;">总金额：</td>
					<td colspan="" style="text-align: left;padding-left: 5px;"><font color="red" id="allMoney">${allMoney}</font>元</td>
				    </tr>
			    </c:if>
				</tbody>
			</table>
			<div style="display: none;">
				<select id="copy_Operation" name="contractTypeId">
					<option value=""></option>
					<c:forEach items="${contractTypeEnumList }" var="contractType">
						<c:if test="${contractType.categoryId eq 'Operation' }">
							<option value="${contractType.id }"<c:if test="${contractType.id eq param.contractTypeId }">selected="selected"</c:if>>${contractType.name }</option>
						</c:if>
					</c:forEach>
				</select>
			  <select id="copy_Sales" name="contractTypeId">
			   <option value=""></option>
			      <c:forEach items="${contractTypeEnumList }" var="contractType">
	                <c:if test="${contractType.categoryId eq 'Sales' }">
	                 <option value="${contractType.id }"<c:if test="${contractType.id eq param.contractTypeId }">selected="selected"</c:if>>${contractType.name }</option>
	                </c:if>
	              </c:forEach>
			  </select>
			  <select id="copy_Second" name="contractTypeId">
			       <option value=""></option>
			       <c:forEach items="${contractTypeEnumList }" var="contractType">
	                 <c:if test="${contractType.categoryId eq 'Second' }">
	                  <option value="${contractType.id }" <c:if test="${contractType.id eq param.contractTypeId }">selected="selected"</c:if>>${contractType.name }</option>
	                 </c:if>
	               </c:forEach>
			  </select>
			</div>
			<c:set var="pageView" value="${pdfn:getPageView(contractListExt)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>			
	</div>
</div>
</body>
</html>