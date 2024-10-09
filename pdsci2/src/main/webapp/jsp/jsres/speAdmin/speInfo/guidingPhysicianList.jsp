<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function () {
		$("#currentPage").val("${param.currentPage}");
	});
</script>
<table cellspacing="0" cellpadding="0" class="base_info">
	<colgroup>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="13%"/>
		<col width="9%"/>
		<col width="13%"/>
		<col width="9%"/>
	</colgroup>
	<tbody>
	<c:if test="${empty list}">
		<tr>
			<td colspan="13" style="text-align: center;">暂无数据</td>
		</tr>
	</c:if>
	<c:if test="${not empty list}">
		<c:forEach items="${list}" var="t">
			<tr>
				<td style="text-align: center">${t.doctorName}</td>
				<td style="text-align: center">${t.sexName}</td>
				<td style="text-align: center">${t.doctorAge}</td>
				<td style="text-align: center">${t.doctorEdu}</td>
				<td style="text-align: center">${t.deptName}</td>
				<td style="text-align: center">${t.technicalTitle}</td>
				<td style="text-align: center">${t.officeYear}</td>
				<td style="text-align: center">${t.workYear}</td>
				<%--<td style="text-align: center">${t.internYear}</td>
				<td style="text-align: center">${t.threeInternYear}</td>
				<td style="text-align: center">${t.hosYear}</td>
				<td style="text-align: center">${t.threeHosYear}</td>--%>
				<td style="text-align: center">
					<c:forEach items="${teachingCertLevelEnumList}" var="certLevel" varStatus="s">
						<c:if test="${t.teachingCertLevel eq certLevel.code}">${certLevel.name}</c:if>
					</c:forEach>
				</td>
				<td style="text-align: center">
						${t.certGrantedDate}
				</td>
				<td style="text-align: center">
					<a href="javascript:viewAttachment('${t.recordFlow}');" class="btn">查看</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>


<%--<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>--%>

