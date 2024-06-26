<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
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

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
			loadCustomerList();
			loadContractList();
			moneyMask();
			a();
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
		function moneyMask(){
			var moneyBox=$(".money");
			$.each(moneyBox,function(i,n){
				$(n).mask('000,000,000,000,000', {reverse: true});
			});
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
		}
		function a(){
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
		function searchDeptUser(obj){

			var deptFlow=$(obj).val();
			var url="<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow;
			$("#chargeUserFlow").empty();
			$("#chargeUser2Flow").empty();

			var option="<option value=''>请选择</option>";
			$("#chargeUserFlow").append(option);
			$("#chargeUser2Flow").append(option);
			if(deptFlow!=""){
				jboxPost(url,null,function(data){
					if(data!=null){
						for ( var i = 0; i < data.length; i++) {
							$("#chargeUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
							$("#chargeUser2Flow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
						}
					}
				},null,false);
			}
		}
		function resetMaintainDate() {
			if ($("#maintainDue").attr("checked")) {
				$("input[name='maintainDueDateStart']").val("");
				$("input[name='maintainDueDateEnd']").val("");
			}
		}
		function search(){
			jboxPostLoad("payList" ,"<s:url value='/erp/crm/billPlan/billList'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input id="" type="hidden" name="type" value="${type}"/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						<label>&#12288;客户名称：</label>
						<input type="text" id="searchParam_Customer" name="customerName" placeholder="客户名称/别名" autocomplete="off" class="xltext" style="width:120px;"/>
						<div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
						<label>&#12288;合同名称：</label>
						<input type="text" id="contractName" name="contractName" class="xltext" autocomplete="off" style="width:120px;"/>
						<div id="suggest_ContractName" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
						<label>&#12288;合同类别：</label>
						<select class="xlselect" id="contractCategoryId" name="contractCategoryId" onchange="changeContractTypeOption(this);" style="width:128px;">
							<option value="">请选择</option>
							<c:forEach var="contractCategory" items="${contractCategoryEnumList}">
								<option value="${contractCategory.id}"<c:if test="${param.contractCategoryId eq contractCategory.id }">selected="selected"</c:if>>${contractCategory.name}</option>
							</c:forEach>
						</select>
						<label>&#12288;&nbsp;合同类型：</label>
						<select class="xlselect" id="contractTypeId" name="contractTypeId" style="width:128px;">
							<option value="">请选择合同类别</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>&#12288;&nbsp;合&nbsp;同&nbsp;号：</label>
						<input type="text" id="contractNo" name="contractNo"  class="xltext"autocomplete="off" style="width: 120px;"/>
						<div id="suggest_ContractNo" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
						<label>&#12288;负责部门：</label>
						<select name="signDeptFlow" id="signDeptFlow" class="xlselect" onchange="searchDeptUser(this);" style="width:128px;">
							<option value="">请选择</option>
							<option value="f48a5060931147daa467eadbb5885629">总经办</option>
							<option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
							<option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
							<option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
							<option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
							<option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
						</select>
						<label>合同负责人：</label>
						<select name="chargeUserFlow" id="chargeUserFlow" class="xlselect" style="width:128px;">
							<option value="">请选择负责部门</option>
						</select>
						<label>合同负责人2：</label>
						<select name="chargeUser2Flow" id="chargeUser2Flow" class="xlselect" style="width:128px;">
							<option value="">请选择负责部门</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>&#12288;区&#12288;&#12288;域：</label>
						<select  class="xlselect" name="bigRegionTypeId" onchange="changeProve(this)"  style="width:128px;">
							<option value="00">全国</option>
							<option value="01">华东地区</option>
							<option value="02">华南地区</option>
							<option value="03">华北地区</option>
							<option value="04">中西部地区</option>
						</select>
						<label>&#12288;省&#12288;&#12288;市：</label>
						<select id="customerProvId" name="customerProvId" class="province xlselect" data-first-title="选择省" style="width:128px;"></select>
						<select id="customerCityId" name="customerCityId" class="city xlselect" data-first-title="选择市" style="width:128px;"></select>
						<input type="checkbox" id="maintainDue" name="maintainDue" onclick="resetMaintainDate();" value="${GlobalConstant.FLAG_Y }"><label for="maintainDue">&nbsp;维护过期合同</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>&#12288;合同状态：</label>
						<select class="xlselect" name="contractStatusId" style="width:128px;">
							<option value="">请选择</option>
							<c:forEach var="contractStatus" items="${contractStatusEnumList}">
								<c:if test="${contractStatus.id ne contractStatusEnumAuditing.id and (contractStatus.id ne contractStatusEnumAuditBack.id)}">
									<option value="${contractStatus.id}">${contractStatus.name}</option>
								</c:if>
							</c:forEach>
						</select>
						<label>&#12288;签订日期：</label>
						<input type="text" name="signDateStart" value="${param.signDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;margin: 0"/>&nbsp;~&nbsp;
						<input type="text" name="signDateEnd" value="${param.signDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;"/>
						<label>&#12288;维护到期日：</label>
						<input type="text" name="maintainDueDateStart" value="${param.maintainDueDateStart }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;margin: 0"/>&nbsp;~&nbsp;
						<input type="text" name="maintainDueDateEnd" value="${param.maintainDueDateEnd }" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>&#12288;排序字段：</label>
						<select class="xlselect" name="orderByCase" style="width:128px;">
							<option value="">默认</option>
							<option value="CONTRACT_NO">合同号</option>
							<option value="CONTRACT_ARCHIVES_NO">合同档案号</option>
							<option value="CUSTOMER_NAME">客户名称</option>
							<option value="CONTRACT_NAME">合同名称</option>
							<option value="CONTRACT_CATEGORY_NAME">合同类别</option>
							<option value="CONTRACT_TYPE_NAME">合同类型</option>
							<option value="SIGN_DEPT_NAME">负责部门</option>
							<option value="CHARGE_USER_NAME">合同负责人</option>
							<option value="CHARGE_USER2_NAME">合同负责人2</option>
							<option value="CONTRACT_STATUS_NAME">合同状态</option>
							<option value="SIGN_DATE">签订日期</option>
							<option value="CREATE_TIME">合同创建日期</option>
							<option value="CONTRACT_DUE_DATE">合同到期日</option>
							<option value="MAINTAIN_DUE_DATE">维护到期日</option>
							<option value="CONTRACT_FUND">合同金额</option>
						</select>
						<label>&#12288;排序方式：</label>
						<input type="radio" value="desc" name="orderAction" />倒序&#12288;
						<input type="radio" value="asc"  name="orderAction"/>正序&#12288;
						<c:if test="${type eq 'plan'}">
							<input type="checkbox" id="waitJH" name="waitJH" checked="checked" value="${GlobalConstant.FLAG_Y }"><label for="waitJH">&nbsp;待开票申请合同</label>
						</c:if>
						<c:if test="${type eq 'action'}">
							<input type="checkbox" id="waitKP" name="waitKP" checked="checked" value="${GlobalConstant.FLAG_Y }"><label for="waitKP">&nbsp;待开票执行合同</label>
							<input type="checkbox" id="KPBH" name="KPBH" checked="checked" value="${GlobalConstant.FLAG_Y }"><label for="KPBH">&nbsp;开票驳回合同</label>
						</c:if>
						<c:if test="${type eq 'audit'}">
							<input type="checkbox" id="waitAudit" name="waitAudit" checked="checked" value="${GlobalConstant.FLAG_Y }"><label for="waitAudit">&nbsp;待开票确认合同</label>
						</c:if>
						<c:if test="${type eq 'kp'}">
							<input type="checkbox" id="waitTracke" name="waitTracke" checked="checked" value="${GlobalConstant.FLAG_Y }"><label for="waitTracke">&nbsp;无快递信息合同</label>
						</c:if>
						<input type="button" class="search"  onclick="search();" value="查&#12288;询">
					</td>
				</tr>
			</table>
		</form>
		<div id="payList">

		</div>
	</div>
</div>

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
</body>
</html>