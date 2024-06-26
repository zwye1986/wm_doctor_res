<table id="orgTable" class="xllist" style="margin-top: 10px;">
	<thead>
	<tr>
		<th width="55%">基地名称</th>
		<th width="15%">是否显示月度考核</th>
		<th width="15%"></th>
		<th width="15%"></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${sysOrgList}" var="sysOrg">
		<tr>
			<td>${sysOrg.orgName }</td>
			<c:set var="key1" value="jswjw_${sysOrg.orgFlow }_M001"/>
			<td>
				<input type="checkbox"  <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_M001" ${sysCfgMap[key1]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_M001');"/>
			</td>
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
	</tbody>
	<c:if test="${empty sysOrgList}">
		<tr>
			<td align="center" colspan="5">无记录</td>
		</tr>
	</c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>