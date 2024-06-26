
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
	var url = "<s:url value='/erp/crm/contractWH/saveNextDate'/>?contractFlow=${contract.contractFlow}";
	jboxConfirm("确认保存? ",  function(){
		$("#saveButton").attr("disabled",true);
		jboxPost(url, $("#balanceForm").serialize(),
				function(resp){
					if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
						setTimeout(function(){
							window.parent.frames['mainIframe'].window.search();
							jboxClose();
						},1000);
					}else{
						$("#saveButton").attr("disabled",false);
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
			<c:if test="${empty contract.contractFlow}">
				  请选择需要更新下次维护日期的合同！
			</c:if>
			<c:if test="${not empty contract.contractFlow}">
			<form id="balanceForm" >
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="40%"/>
						<col width="60%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							下次维护日期：
						</td>
						<td>
							<input type="text" name="nextMaintainDate" class="xltext ctime validate[required]" value="${contract.nextMaintainDate}"  style="width: 150px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'
									<c:if test="${not empty contract.nextMaintainDate}">
									,minDate: '${contract.nextMaintainDate}'</c:if>})" readonly="readonly"/>
						</td>
					</tr>
				</table>
				<div class="button">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>