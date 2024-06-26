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
			//a();
		});
		function loadCustomerList() {
			var customers = new Array();
			var url = "<s:url value='/erp/crm/searchCustomerForAreaJson'/>";
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
						//newJsonData[j++]=provData;
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
		function search(){
			var $customerName = $("input[name='customerName']");
			var customerNameVal = $customerName.val().trim();
			$customerName.val(customerNameVal);
			jboxPostLoad("payList" ,"<s:url value='/erp/crm/customerArchives/list'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function showCustomerType(customerTypeId){
			if("${customerTypeEnumHospital.id}" == customerTypeId){
				$("#hospitalP").show();
				$("#schoolP").hide();
				$("input[name='schoolTypeId']").each(function(){
					$(this).attr("checked",false);
				});
			}else if("${customerTypeEnumSchool.id}" == customerTypeId){
				$("#hospitalP").hide();
				$("#schoolP").show();
				$("select[name='hospitalGradeId']").val(null);
				$("select[name='hospitalLevelId']").val(null);
				$("input[name='hospitalTypeId']").each(function(){
					$(this).attr("checked",false);
				});
			}else{
				$("#hospitalP").hide();
				$("#schoolP").hide();
				$("select[name='hospitalGradeId']").val(null);
				$("select[name='hospitalLevelId']").val(null);
				$("input[name='hospitalTypeId']").each(function(){
					$(this).attr("checked",false);
				});
				$("input[name='schoolTypeId']").each(function(){
					$(this).attr("checked",false);
				});
			}
		}
		function customerInfo(customerFlow) {
			var w = $('.mainright').width();
			var h = $('.mainright').height();
			var url = "<s:url value='/erp/crm/customerArchives/customerInfo'/>?customerFlow=" + customerFlow;
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			jboxMessager(iframe,'客户详细信息',w,h,null,false);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						<label>客户名称：</label>
						<input type="text" id="searchParam_Customer" name="customerName" placeholder="客户名称/别名" autocomplete="off" class="xltext" style="width:120px;"/>
						<div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 126px;"></div>
						<label>区&#12288;&#12288;域：</label>
						<select  class="xlselect" name="bigRegionTypeId" onchange="changeProve(this)"  style="width:128px;">
							<option value="00">选择区域</option>
							<c:forEach items="${areaList}" var="area">
								<option value="${area.areaId}">${area.areaName}</option>
							</c:forEach>
						</select>
						<label>省&#12288;&#12288;市：</label>
						<select id="customerProvId" name="customerProvId" class="province xlselect" data-first-title="选择省" style="width:128px;">
							<option value="">请先选区域</option>
						</select>
						<select id="customerCityId" name="customerCityId" class="city xlselect" data-first-title="选择市" style="width:128px;display:none;">
							<option value="">选择市</option></select>
					</td>
				</tr>
				<tr>
					<td>
						<label>类&#12288;&#12288;别：</label>
						<select class="xlselect" name="customerTypeId" style="width: 128px;" onchange="showCustomerType(this.value)">
							<option value="">请选择</option>
							<c:forEach var="customerType" items="${customerTypeEnumList}">
								<option value="${customerType.id}">${customerType.name}</option>
							</c:forEach>
						</select>
						<label>客户电话：</label>
						<input name="customerPhone"  value="${param.customerPhone}" class="xltext" placeholder="输入客户电话"   style="width: 122px;text-align: left;padding-left: 5px;"/>
						<input type="button" class="search"  onclick="search();" value="查&#12288;询">
					</td>
				</tr>
				<tr id="hospitalP" style="display: none">
					<td>
						<label>级&#12288;&#12288;别：</label>
						<select name="hospitalGradeId" class="xlselect" style="width:60px;">
							<option value="">请选择</option>
							<c:forEach var="hospitalGrade" items="${dictTypeEnumHospitalGradeList}">
								<option value="${hospitalGrade.dictId}" <c:if test="${param.hospitalGradeId eq hospitalGrade.dictId}">selected="selected"</c:if> >${hospitalGrade.dictName}</option>
							</c:forEach>
						</select>
						<select name="hospitalLevelId" class="xlselect" style="width:60px;margin-right: 5px;">
							<option value="">请选择</option>
							<c:forEach var="hospitalLevel" items="${dictTypeEnumHospitalLevelList}">
								<option value="${hospitalLevel.dictId}" <c:if test="${param.hospitalLevelId eq hospitalLevel.dictId}">selected="selected"</c:if>>${hospitalLevel.dictName}</option>
							</c:forEach>
						</select>
						<input type="checkbox" id="hosGradeFlag" name="hosGradeFlag" value="${GlobalConstant.FLAG_Y }" ${param.hosGradeFlag eq GlobalConstant.FLAG_Y?'checked':'' }><label for="hosGradeFlag" style="padding-right:21px;">&nbsp;及以上</label>
						<label>医院类型：</label>
						<c:forEach var="hospitalType" items="${dictTypeEnumHospitalTypeList}">
							<input name="hospitalTypeId" id="hospitalTypeId_${hospitalType.dictId}"
								   type="checkBox" value="${hospitalType.dictId}"><label for="hospitalTypeId_${hospitalType.dictId}">&nbsp;${hospitalType.dictName}</label>&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr id="schoolP" style="display: none">
					<td>
						<label>级&#12288;&#12288;别：</label>
						<c:forEach var="schoolType" items="${dictTypeEnumSchoolTypeList}">
							<input name=schoolTypeId id="schoolTypeId_${schoolType.dictId}" type="checkBox" value="${schoolType.dictId}" <c:if test="${param.schoolTypeId eq schoolType.dictId}">checked="checked"</c:if> onchange="selectSingle(this)"><label for="schoolTypeId_${schoolType.dictId}">&nbsp;${schoolType.dictName}</label>&nbsp;
						</c:forEach>
					</td>
				</tr>
			</table>
		</form>
		<div id="payList">

		</div>
	</div>
</div>

</body>
</html>