
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
	var  statusId=$("input[name='statusId']:checked").val();
	var msg="";
	if('${planBalanceStatusEnumAuditBack.id}'==statusId)
	{
		msg="确认驳回此开票信息？";
	}else  if('${planBalanceStatusEnumAuditPass.id}'==statusId)
	{
		msg="确认此开票信息已开票？";
	}else{
		jboxTip("请选择审核状态！");
		return false;
	}

	jboxConfirm(msg,function(){
		var url = "<s:url value='/erp/crm/billPlan/auditBalance'/>";
		jboxPost(url,$("#balanceForm").serialize(),function(resp){
			if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
				jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				setTimeout(function(){
					jboxClose();
				},2000)
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
				jboxTip("${GlobalConstant.OPRE_FAIL}");
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
			{
				jboxTip(resp);
			}
			window.parent.frames['mainIframe'].window.refreshBillPlan('${contract.contractFlow }');
		},null,false);
	},null);
}

function changeClass(){


	var  statusId=$("input[name='statusId']:checked").val();
	if('${planBalanceStatusEnumAuditBack.id}'==statusId)
	{
		$("#auditReason").attr("class","validate[required,maxSize[200]] xltxtarea");
	}else  if('${planBalanceStatusEnumAuditPass.id}'==statusId)
	{
		$("#auditReason").attr("class","validate[maxSize[200]] xltxtarea");
	}

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
				<input name="billPlanFlow" value="${balance.billPlanFlow}" type="hidden">
				<input name="contractFlow" value="${contract.contractFlow}" type="hidden">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="30%"/>
						<col width="70%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							实际开票日期
						</td>
						<td>
							 ${balance.billDate}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							开票申请人：
						</td>
						<td>
							${balance.billApplyUserName}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">开票金额：</td>
						<td>
							<span class="money">${balance.billFund}</span>元
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">发票号：</td>
						<td>
							${balance.billNo}
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">开票经办人：</td>
						<td>
							${balance.handleUserName}
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">审核意见：</td>
						<td>
							<input id="agree"  name="statusId" value="${planBalanceStatusEnumAuditPass.id}" type="radio" onclick="changeClass();" class="validate[required]">
							<label for="agree">确认开票</label>&#12288;&#12288;
							<input id="disAgree"  name="statusId" value="${planBalanceStatusEnumAuditBack.id}" type="radio" onclick="changeClass();" class="validate[required]">
							<label for="disAgree">驳回开票</label>&#12288;
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">驳回意见：</td>
						<td>
                            <textarea class="xltxtarea " placeholder="200个字符以内" style="margin-left: 0px;resize: none;"
										  name="auditReason"  id="auditReason" ></textarea>
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