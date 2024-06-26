
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
	<jsp:param name="jquery_mask" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	moneyMask();
	$("a").bind("click",function(e){
		e.stopPropagation();
	});
});

function contractInfo(contractFlow) {
	var mainIframe = window.parent.frames["mainIframe"];
	var width = mainIframe.document.body.scrollWidth;
	if (width >1100) {
		width = 1000;
	}
	var url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow="+contractFlow+"&status=main&type=open";
	jboxOpen(url, "合同信息", width, 600);
}
function contractOrderInfo(contractFlow){
	var content = $("#tbody_"+contractFlow);
	var contentTd = $("#td_"+contractFlow);
	if(content.is(":hidden")) {
		contentTd.html("");
		var url = "<s:url value='/erp/crm/customerArchives/contractOrderInfo'/>?contractFlow="+contractFlow+"&status=main&type=open&customerFlow=${customer.customerFlow}";
		jboxLoad("td_" + contractFlow, url, true);
	}
	content.slideToggle("fast",function(){
	});
}
function moneyMask(){
	var moneyBox=$(".money");
	$.each(moneyBox,function(i,n){
		$(n).mask('000,000,000,000,000', {reverse: true});
	});

}

function info(contactFlow) {
	var mainIframe = window.parent.frames["mainIframe"];
	var w = mainIframe.document.body.scrollWidth;
	if (w >1100) {
		w= 1100;
	}
	var h = 600;
	var url="<s:url value='/erp/crm/customerArchives/contactOrderInfo'/>?contactFlow="+contactFlow+"&historyFlag=Y&CanNot=Y&customerFlow=${customer.customerFlow}";
//	var iframe ="<iframe name='jbox-message-iframe2' id='jbox-message-iframe2' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
//	jboxMessager(iframe,'查看工作联系单',w,h,"jbox-message-iframe2",true);
	jboxOpen(url, "查看工作联系单", w, h);
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent" >
			<div class="tagContent selectTag" id="tagContent0">
				
				<div id="customerDiv">
					<table width="100%" cellpadding="0" cellspacing="0" class="basic">
						<colgroup>
							<col width="12%"/>
							<col width="12%"/>
							<col width="12%"/>
							<col width="14%"/>
							<col width="12%"/>
							<col width="14%"/>
							<col width="10%"/>
							<col width="14%"/>
						</colgroup>
						<tr>
							<th colspan="8" style="text-align: left;padding-left: 10px">客户信息
							</th>

						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">客户名称：</td>
							<td colspan="3">${customer.customerName}</td>
							<td style="text-align: right;padding-right: 10px;">别&#12288;&#12288;名：</td>
							<td colspan="3">${customer.aliasName}</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">地&#12288;&#12288;区：</td>
							<td colspan="3">
								${customer.customerProvName}
								<c:if test="${not empty customer.customerCityName}">-${customer.customerCityName}</c:if>
								<c:if test="${not empty customer.customerAreaName}">-${customer.customerAreaName}</c:if>
							</td>
							<td style="text-align: right;padding-right: 10px;">地&#12288;&#12288;址：</td>
							<td colspan="3">${customer.customerAddress}</td>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">网&#12288;&#12288;站：</td>
							<td colspan="3">
								<c:set var="lowerSite" value="${pdfn:toLowerCase(customer.customerSite)}"/>
								<c:set var="site" value="${pdfn:contains(lowerSite,'http://')}"/>
								<c:choose>
									<c:when test="${site}">
										<a href="${customer.customerSite}" target="_blank">${customer.customerSite}</a>
									</c:when>
									<c:otherwise>
										<a href="http://${customer.customerSite}" target="_blank">${customer.customerSite}</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align: right;padding-right: 10px;">邮&#12288;&#12288;编：</td>
							<td>${customer.zipCode}</td>
							<td style="text-align: right;padding-right: 10px;">总&nbsp;机&nbsp;&nbsp;号：</td>
							<td>${customer.telPhone}</td>
						</tr>

						<tr>
							<c:choose>
								<c:when test="${customer.customerTypeId eq customerTypeEnumHospital.id}">
									<td style="text-align: right;padding-right: 10px;">类&#12288;&#12288;别：</td>
									<td>${customer.customerTypeName}</td>
									<td style="text-align: right;padding-right: 10px;">级&#12288;&#12288;别：</td>
									<td>${customer.hospitalGradeName}${customer.hospitalLevelName}</td>
									<td style="text-align: right;padding-right: 10px;">医院类型：</td>
									<td colspan="3">${customer.hospitalTypeName}</td>
								</c:when>
								<c:when test="${customer.customerTypeId eq customerTypeEnumSchool.id}">
									<td style="text-align: right;padding-right: 10px;">类&#12288;&#12288;别：</td>
									<td>${customer.customerTypeName}</td>
									<td style="text-align: right;padding-right: 10px;">级&#12288;&#12288;别：</td>
									<td colspan="5">${customer.schoolTypeName}</td>
								</c:when>
								<c:otherwise>
									<td style="text-align: right;padding-right: 10px;">类&#12288;&#12288;别：</td>
									<td colspan="7">${customer.customerTypeName}</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${customer.isContract eq GlobalConstant.FLAG_Y}">
									<td style="text-align: right;padding-right: 10px;">合同客户：</td>
									<td>
										<c:if test="${customer.isContract eq GlobalConstant.RECORD_STATUS_Y}">是</c:if>
										<c:if test="${customer.isContract eq GlobalConstant.RECORD_STATUS_N}">否</c:if>
									</td>
									<td style="text-align: right;padding-right: 10px;">客户等级：</td>
									<td colspan="5">${customer.customerGradeName}</td>
								</c:when>
								<c:otherwise>
									<td style="text-align: right;padding-right: 10px;">合同客户：</td>
									<td colspan="7">
										<c:if test="${customer.isContract eq GlobalConstant.RECORD_STATUS_Y}">是</c:if>
										<c:if test="${customer.isContract eq GlobalConstant.RECORD_STATUS_N}">否</c:if>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td style="text-align: right;padding-right: 10px;">备&#12288;&#12288;注：</td>
							<td colspan="7" style="text-align:left;line-height: 25px;">
								${customer.remark}
							</td>
						</tr>
					</table>
				</div>
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;margin-top:5px;">
				<tr>
					<th style="text-align: left;padding-left: 10px">联系人信息</th>
				</tr>
				</table>
				<div id="userListDiv">
					<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
						<colgroup>
							<col width="6%"/>
							<col width="4%"/>
							<col width="9%"/>
							<col width="9%"/>
							<col width="11%"/>
							<col width="10%"/>
							<col width="17%"/>
							<col width="7%"/>
							<col width="10%"/>
						</colgroup>
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>部门</th>
							<th>职务</th>
							<th>固话</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>部门负责人</th>
							<th>备注</th>
						</tr>
						<tbody>
						<c:forEach items="${customerUserList}" var="user" varStatus="status">
							<tr <c:if test="${user.recordStatus eq GlobalConstant.FLAG_N}"> style="background-color:#eeeeee;"</c:if>>
								<td>${user.userName}</td>
								<td>${user.sexName}</td>
								<td>${user.deptName}</td>
								<td>${user.postName}</td>
								<td>${user.userTelphone}</td>
								<td>${user.userCelphone}</td>
								<td>${user.userEmail}</td>
								<td>
									<c:if test="${user.isMain eq GlobalConstant.FLAG_Y}">是</c:if>
									<c:if test="${user.isMain eq GlobalConstant.FLAG_N}">否</c:if>
								</td>
								<td>${user.remark}</td>
							</tr>
						</c:forEach>
						</tbody>
						<c:if test="${empty customerUserList}">
							<tr>
								<td colspan="10">无记录</td>
							</tr>
						</c:if>
					</table>
				</div>
				<table width="100%" cellpadding="0" cellspacing="0" class="xllist" style="margin-top:5px;">
					<colgroup>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
						<col width="9%"/>
					</colgroup>
					<tr>
						<th colspan="11" style="text-align: left;padding-left: 10px">合同信息</th>
					</tr>
					<tr>
						<th>合同号</th>
						<th>合同档案号</th>
						<th>合同名称</th>
						<th>合同负责人</th>
						<th>合同负责人2</th>
						<th>合同类别</th>
						<th>合同类型</th>
						<th>签订日期</th>
						<th>合同应收金额</th>
						<th>最近应收日期</th>
						<th>操作</th>
					</tr>
					<c:if test="${empty contracts }">
						<tr>
							<td colspan="11">无记录</td>
						</tr>
					</c:if>
					<c:forEach items="${contracts }" var="contract" varStatus="num">
						<tr title="显示或关闭联系单列表" onclick="contractOrderInfo('${contract.contractFlow }');">
							<td>
									${contract.contractNo }
							</td>
							<td>${contract.contractArchivesNo }</td>
							<td>${contract.contractName }</td>
							<td>${contract.chargeUserName }</td>
							<td>${contract.chargeUser2Name }</td>
							<td>${contract.contractCategoryName }</td>
							<td>${contract.contractTypeName }</td>
							<td>${contract.signDate }</td>
							<td><span class="money">${contract.contractFund-contract.payFund }</span></td>
							<td>
								<c:if test="${ (contract.contractFund-contract.payFund) ne 0}">
									${contract.planDate}
								</c:if>
							</td>
							<td>
								<a href="javascript:contractInfo('${contract.contractFlow }');" style="color:#0b58a2;">详情</a>
							</td>
						</tr>
						<tr id="tbody_${contract.contractFlow }" style="display: none;max-height: 300px;">
							<td id="td_${contract.contractFlow }"colspan="11" style="max-height: 300px;overflow: auto;">
							</td>
						</tr>
					</c:forEach>
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="xllist" style="margin-top:5px;">
					<colgroup>
						<col width="12%"/>
						<col width="18%"/>
						<col width="60%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th colspan="4" style="text-align: left;padding-left: 10px">回访信息</th>
					</tr>
					<tr>
						<th style="text-align: center;">回访时间</th>
						<th style="text-align: left;">&nbsp;回访主题</th>
						<th style="text-align: left;">&nbsp;回访内容</th>
						<th style="text-align: center;">回访人</th>
					</tr>
					<c:if test="${empty visitList }">
						<tr>
							<td colspan="4">无回访记录</td>
						</tr>
					</c:if>
					<c:forEach items="${visitList}" var="visit" varStatus="status">
						<tr>
							<td style="text-align: center;">${pdfn:transDate(visit.visitTime)}</td>
							<td style="text-align: left;">${visit.visitSubject}</td>
							<td style="text-align: left;">${visit.visitContent}</td>
							<td style="text-align: center;">${visit.visitUserName}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="button" style="width: 100%;">
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
				</div>
								
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>