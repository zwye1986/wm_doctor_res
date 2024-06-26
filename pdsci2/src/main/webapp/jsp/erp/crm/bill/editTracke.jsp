
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
	jboxConfirm("确认保存快递信息，保存后无法修改？",function(){
		var url = "<s:url value='/erp/crm/billPlan/saveTracke'/>";
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
							${balance.statusName}
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">快递号：</td>
						<td>

							<c:if test="${param.type eq 'N'}">
								<input type="text" name="billTrackeNumber" class="xltext validate[required,custom[onlyLetterNumber],maxSize[20]]" value="${balance.billTrackeNumber}" style="width: 140px;margin-right: 0;"/>
							</c:if>
							<c:if test="${param.type eq 'Y'}">
								${balance.billTrackeNumber}
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">寄送信息：</td>
						<td>
							<c:if test="${param.type eq 'N'}">
								<textarea type="text" name="billTrackeContent" class="validate[required,maxSize[500]]" style="width: 150px;margin-right: 0;height: 80px">${balance.billTrackeContent}</textarea>
							</c:if>
							<c:if test="${param.type eq 'Y'}">
								${balance.billTrackeContent}
							</c:if>
						</td>
					</tr>
				</table>
				<div class="button">
					<c:if test="${param.type eq 'N'}">
						<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					</c:if>
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>