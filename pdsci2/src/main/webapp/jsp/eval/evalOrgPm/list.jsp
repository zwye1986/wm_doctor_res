<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>
</script>

<table class="xllist"  id="treeTable"  style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="30%"/>
		<col width="10%"/>
		<col width="30%"/>
		<col width="30%"/>
	</colgroup>
	<thead>
	<tr>
		<th>基地名称</th>
		<th>评估年份</th>
		<th>专家评分</th>
		<th>基地排名</th>
	</tr>
	</thead>
	<tbody id="tbody">
	<c:forEach items="${sysList}" var="bean">
		<tr>
			<td>${bean.orgName}</td>
			<td>${param.evalYear}</td>
			<td>${bean.score}</td>
			<td>
				<c:if test="${bean.score ne '-'}">
					${bean.orderNum}
				</c:if>
				<c:if test="${bean.score eq '-'}">
					-
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty param.evalYear}">
		<tr>
			<td colspan="14">请选择年度</td>
		</tr>
	</c:if>
		<c:if test="${empty evalCfg and( not empty param.evalYear)}">
			<tr>
				<td colspan="14">${param.evalYear}年评估未开始</td>
			</tr>
		</c:if>
		<c:if test="${empty sysList and( not empty param.evalYear) and (not empty evalCfg)}">
			<tr>
				<td colspan="14">无记录</td>
			</tr>
		</c:if>
	</tbody>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(sysList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>