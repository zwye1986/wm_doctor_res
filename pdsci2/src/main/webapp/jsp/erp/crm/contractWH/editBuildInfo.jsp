
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
<script type="text/javascript">
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

function save() {
	reMoneyMask();
	if(!$("#balanceForm").validationEngine("validate")){
		return false;
	}
	var url = "<s:url value='/erp/crm/contractWH/saveBuildInfo'/>?contractFlow=${contract.contractFlow}";
	jboxConfirm("确认保存? 保存后不可修改",  function(){
		$("#saveButton").attr("disabled",true);
		jboxPost(url, $("#balanceForm").serialize(),
				function(resp){
					if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
						setTimeout(function(){
							jboxClose();
						},1000);
					}else{
						$("#saveButton").attr("disabled",false);
					}
					window.parent.frames['mainIframe'].window.search();
				},null,true);
	});
}

function setUserName(id){
	$("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
}

function searchDeptUser(obj){
	var deptFlow=$(obj).val();
	var url="<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow;
	$("#buildingUserFlow").empty();

	var option="<option value=''>请选择</option>";
	$("#buildingUserFlow").append(option);
	if(deptFlow!=""){
		jboxPost(url,null,function(data){
			if(data!=null){
				for ( var i = 0; i < data.length; i++) {
					$("#buildingUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
				}
			}
		},null,false);
	}
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="balanceForm" >
				<input name="infoFlow" value="${info.infoFlow}" type="hidden">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="30%"/>
						<col width="70%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							首次安装人部门：
						</td>
						<td>
							<c:if test="${not empty info.deptFlow}">
								${info.deptName}
							</c:if>
							<c:if test="${empty info.deptFlow}">
								<select name="deptFlow" id="deptFlow" class="xlselect" onchange="searchDeptUser(this);setUserName('dept');" style="width:150px;">
									<option value="">请选择</option>
									<c:forEach items="${deptList}" var="d">
										<option value="${d.deptFlow}">${d.deptName}</option>
									</c:forEach>
								</select>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							首次安装人：
						</td>
						<td>
							<c:if test="${not empty info.buildingUserFlow}">
								${info.buildingUserName}
							</c:if>
							<c:if test="${empty info.buildingUserFlow}">
								<select name="buildingUserFlow" id="buildingUserFlow" class="xlselect" onchange="setUserName('buildingUser');" style="width:150px;">
									<option value="">请选择</option>
								</select>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">首次安装地点：</td>
						<td>
							<c:if test="${not empty info.buildingAddress}">
								${info.buildingAddress}
							</c:if>
							<c:if test="${empty info.buildingAddress}">
								<textarea name="buildingAddress" class="" style="width: 150px;height: 80px;resize: none"></textarea>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">出差日期：</td>
						<td>
							<c:if test="${not empty info.travelDate}">
								${info.travelDate}
							</c:if>
							<c:if test="${empty info.travelDate}">
								<input type="text" name="travelDate" class="xltext ctime "  style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">安装时间：</td>
						<td>
							<c:if test="${not empty info.buildingDate}">
								${info.buildingDate}
							</c:if>
							<c:if test="${empty info.buildingDate}">
								<input type="text" name="buildingDate" class="xltext ctime "  style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">派工单号：</td>
						<td>
							<c:if test="${not empty info.orderNo}">
								${info.orderNo}
							</c:if>
							<c:if test="${empty info.orderNo}">
								<input type="text" name="orderNo" class="xltext "  style="width: 150px;"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">实际验收日期：</td>
						<td>
							<c:if test="${not empty info.acceptDate}">
								${info.acceptDate}
							</c:if>
							<c:if test="${empty info.acceptDate}">
								<input type="text" name="acceptDate" class="xltext ctime "  style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
							</c:if>
						</td>
					</tr>
				</table>
				<div class="button">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>