
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
	var url = "<s:url value='/erp/crm/billPlan/saveBalance'/>?contractFlow=${contract.contractFlow}&billPlanFlow=${billPlan.billPlanFlow}";
	jboxConfirm("确认保存开票金额? 保存后不可修改",  function(){
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
					window.parent.frames['mainIframe'].window.refreshBillPlan('${contract.contractFlow }');
				},null,true);
	});
}

function setUserName(id){
	$("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="balanceForm" >
				<input name="recordFlow" value="${balance.recordFlow}" type="hidden">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="30%"/>
						<col width="70%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							实际开票日期：
						</td>
						<td>
							<input type="text" name="billDate" class="xltext ctime validate[required]" value="${empty balance.billDate ?pdfn:getCurrDate(): balance.billDate}"  style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							开票申请人：
						</td>
						<td>
							<select name="billApplyUserFlow" id="billApplyUserFlow" class="xlselect" style="width: 158px;" onchange="setUserName('billApplyUser');">
								<option value="">请选择</option>
								<c:forEach items="${users}" var="user">
									<option value="${user.userFlow}" <c:if test="${balance.billApplyUserFlow eq user.userFlow}">selected="selected"</c:if>>${user.userName}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="billApplyUserName" id="billApplyUserName" value="${balance.billApplyUserName}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							开票经办人：
						</td>
						<td>
							<select name="handleUserFlow" id="handleUserFlow" class="xlselect" style="width: 158px;" onchange="setUserName('handleUser');">
								<option value="">请选择</option>
								<c:forEach items="${users}" var="user">
									<option value="${user.userFlow}" <c:if test="${balance.handleUserFlow eq user.userFlow}">selected="selected"</c:if>>${user.userName}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="handleUserName" id="handleUserName" value="${balance.handleUserName}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							经办时间：
						</td>
						<td>
							<input type="text" value="${balance.handleTime }" class="ctime validate[required]" name="handleTime" style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">开票金额：</td>
						<td>
							<input type="text" name="billFund" class="xltext validate[required,custom[number],maxSize[9]] money" value="${balance.billFund}" style="width: 150px;margin-right: 0;"/>&nbsp;元
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">&#12288;发票号：</td>
						<td>
							<input type="text" name="billNo" class="xltext validate[required,custom[number],maxSize[50]]" value="${balance.billNo}" style="width: 150px;margin-right: 0;"/>
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