<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
	$(document).ready(function(){
		var customerTypeId = $("select[name='customerTypeId']").val();
		showHospitalTypeTd(customerTypeId);
	});

	function showHospitalTypeTd(customerTypeId){
		if("${customerTypeEnumHospital.id}" == customerTypeId) {
			$(".hospitalTypeTd").show();
		}else{
			$(".hospitalTypeTd").hide();
		}
	}
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="17%"/>
		<col width="6%"/>
					<col width="7%"/>
					<col class="hospitalTypeTd" style="display: none;" width="9%"/>
					<col class="customerGradeTd" style="display: none;" width="8%"/>
					<col width="13%"/>
					<col width="14%"/>
					<col width="9%"/>
					<col width="10%"/>
					<col width="7%"/>
				</colgroup>
				<thead>
				<tr>
					<th>客户名称</th>
					<th>类别</th>
					<th>级别</th>
					<th class="hospitalTypeTd" style="display: none;">医院类型</th>
					<th class="customerGradeTd" style="display: none;">客户等级</th>
					<th>地址</th>
					<th>网址</th>
					<th>总机号</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
				</thead>						
				<tbody>
				<c:forEach items="${customerList}" var="customer">
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
						<td class="hospitalTypeTd" style="display: none;">${customer.hospitalTypeName}</td>
						<td class="customerGradeTd" style="display: none;">${customer.customerGradeName}</td>
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
						<td>
							${customer.remark}
						</td>
						<td>
							<a href="javascript:customerInfo('${customer.customerFlow}');">查看</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty customerList}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<div>
			   	<c:set var="pageView" value="${pdfn:getPageView2(customerList, 10)}" scope="request"/> 
				<pd:pagination toPage="toPage"/>
			</div>