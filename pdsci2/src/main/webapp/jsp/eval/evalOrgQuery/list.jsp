<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>
	function showOrgManage (orgFlow,evalYear)
	{
		var url="<s:url value='/eval/evalOrgQuery/showOrgManage'/>?orgFlow="+orgFlow+"&evalYear="+evalYear;
		jboxOpen(url, "基地评估", 1100, 500,true);
	}
	function showOrgClinical(orgFlow,evalYear)
	{
		var url="<s:url value='/eval/evalOrgQuery/showOrgClinical'/>?orgFlow="+orgFlow+"&evalYear="+evalYear;
		jboxOpen(url, "专业基地评估", 1100, 500,true);
	}
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
		<th>培训基地评估</th>
		<th>专业基地评估</th>
	</tr>
	</thead>
	<tbody id="tbody">
	<c:forEach items="${sysList}" var="org">
		<tr>
			<td>${org.orgName}</td>
			<td>${param.evalYear}</td>
			<td>
				<a style="color:#449bcd;" href="javascript:showOrgManage('${org.orgFlow}','${param.evalYear}');" >查看</a>
			</td>
			<td>
				<a style="color:#449bcd;" href="javascript:showOrgClinical('${org.orgFlow}','${param.evalYear}');" >查看</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty param.evalYear}">
		<tr>
			<td colspan="14">请选择评估年份</td>
		</tr>
	</c:if>
		<c:if test="${empty evalCfg and ( not empty param.evalYear)}">
			<tr>
				<td colspan="14">${param.evalYear}年评估未开始</td>
			</tr>
		</c:if>
		<c:if test="${empty sysList and ( not empty param.evalYear) and (not empty evalCfg)}">
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