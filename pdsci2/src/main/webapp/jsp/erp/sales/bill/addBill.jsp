
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
	var url = "<s:url value='/erp/sales/saveBillBalance'/>?contractFlow=${param.contractFlow}&billPlanFlow=${param.billPlanFlow}";

	jboxConfirm("确认保存? 保存后不可修改",  function(){
		jboxPost(url, $("#balanceForm").serialize(),
				function(resp){
					if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
						setTimeout(function(){
							window.parent.frames['jbox-message-iframe'].window.search();
							jboxClose();
						},1000);
					}
				},null,true);
	});
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="balanceForm" >
				<input type="hidden" name="recordFlow" value="${BillBalance.recordFlow}" />
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="35%"/>
						<col width="65%"/>
					</colgroup>
					<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL and (pdfn:contain('action-erp-swgl-crmhtgl-crmhtcx-xzkp', sessionScope.currUserMenuIdList))}">
						<tr>
							<td style="text-align: right;padding-right: 10px;">
								实际开票日期：
							</td>
							<td>
								<input type="text" name="billDate" class="xltext ctime validate[required]" style="width: 140px;margin-right: 0;" value="${BillBalance.billDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">实际开票金额：</td>
							<td>
								<input type="text" name="billFund" class="xltext validate[required,custom[number],maxSize[9]] money" value="${BillBalance.billFund}" style="width: 140px;margin-right: 0;"/>&nbsp;元
							</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">发票号：</td>
							<td>
								<input type="text" name="billNo" class="xltext validate[required,custom[number],maxSize[50]]" value="${BillBalance.billNo}" style="width: 140px;margin-right: 0;"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${ ! pdfn:contain('action-erp-swgl-crmhtgl-crmhtcx-xzkp', sessionScope.currUserMenuIdList)}">
						<tr>
							<td style="text-align: right;padding-right: 10px;">
								实际开票日期：
							</td>
							<td>&nbsp;${BillBalance.billDate}</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">实际开票金额：</td>
							<td>&nbsp;${BillBalance.billFund}</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">发票号：</td>
							<td>&nbsp;${BillBalance.billNo}</td>
						</tr>
					</c:if>

					<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL and (pdfn:contain('action-erp-swgl-crmhtgl-crmhtcx-kdxx', sessionScope.currUserMenuIdList))}">  <%--or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE--%>
						<tr>
							<td style="text-align: right;padding-right: 10px;">快递号：</td>
							<td>
								<input type="text" name="billTrackeNumber" class="xltext validate[required,custom[number],maxSize[20]]" value="${BillBalance.billTrackeNumber}" style="width: 140px;margin-right: 0;"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">寄送信息：</td>
							<td>
								<textarea type="text" name="billTrackeContent" class="validate[required,maxSize[500]]" style="width: 150px;margin-right: 0;height: 80px">${BillBalance.billTrackeContent}</textarea>
							</td>
						</tr>
					</c:if>
				</table>
				<div class="button">
					<input class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>