
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
</jsp:include>

</head>
<body>
<div class="content" style="overflow-y:auto;height: 650px;">
	<c:if test="${type eq 'Customer'}">
	<table class="xllist">
		<colgroup>
			<col width="17%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col  width="10%"/>
			<col  width="10%"/>
			<col width="16%"/>
			<col width="17%"/>
			<col width="10%"/>
		</colgroup>
		<thead>
		<tr>
			<th>客户名称</th>
			<th>类别</th>
			<th>级别</th>
			<th >医院类型</th>
			<th >客户等级</th>
			<th>地址</th>
			<th>网址</th>
			<th>总机号</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="customer">
			<tr id="${customer.customerFlow}_Tr">
				<td>
					<a href="javascript:customerInfo('${customer.customerFlow}');"
					   title="${customer.aliasName}<br/>${customer.customerProvName}
								<c:if test="${not empty customer.customerCityName}">-${customer.customerCityName}</c:if>
								<c:if test="${not empty customer.customerAreaName}">-${customer.customerAreaName}</c:if>">${customer.customerName}</a>
				</td>
				<td>${customer.customerTypeName}</td>
				<td>
						${customer.hospitalGradeName}${customer.hospitalLevelName}
						${customer.schoolTypeName}
				</td>
				<td >${customer.hospitalTypeName}</td>
				<td >${customer.customerGradeName}</td>
				<td title="${customer.customerProvName}
								<c:if test="${not empty customer.customerCityName}">-${customer.customerCityName}</c:if>
								<c:if test="${not empty customer.customerAreaName}">-${customer.customerAreaName}</c:if>">${customer.customerAddress}</td>
				<td>
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
				<td>${customer.telPhone}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr>
				<td colspan="8">无记录</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	</c:if>
	<c:if test="${type eq 'CustomerUser'}">
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
				<th>科室</th>
				<th>职务</th>
				<th>固话</th>
				<th>手机</th>
				<th>邮箱</th>
				<th>部门负责人</th>
				<th>备注</th>
			</tr>
			<tbody>
			<c:forEach items="${list}" var="user" varStatus="status">
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
			<c:if test="${empty list}">
				<tr>
					<td colspan="10">无记录</td>
				</tr>
			</c:if>
		</table>
	</c:if>
	<div class="button" style="width: 100%;">

		<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />

	</div>
</div>
</body>
</html>