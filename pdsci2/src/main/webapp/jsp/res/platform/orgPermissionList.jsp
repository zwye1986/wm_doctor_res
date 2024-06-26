<table id="orgTable" class="xllist">
	<thead>
	<tr>
		<th width="55%">基地名称</th>
		<th width="15%">过程管理</th>
		<th width="15%">支持委培</th>
		<th width="15%">登记数据查询</th>
	</tr>
	</thead>						
	<tbody>
	<c:forEach items="${sysOrgList}" var="sysOrg">
		<tr>
			<td>${sysOrg.orgName }</td>
			<c:set var="key1" value="jswjw_${sysOrg.orgFlow }_P001"/>
			<c:set var="key2" value="jswjw_${sysOrg.orgFlow }_P002"/>
			<c:set var="key3" value="jswjw_${sysOrg.orgFlow }_P003"/>
			<td>
				<input type="checkbox" id="${sysOrg.orgFlow }_P001" ${sysCfgMap[key1]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P001');"/>
			</td>
			<td>
				<input type="checkbox" id="${sysOrg.orgFlow }_P002" ${sysCfgMap[key2]==GlobalConstant.FLAG_Y?'checked':''} ${value1!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P002');"/>
			</td>
			<td>
				<input type="checkbox" id="${sysOrg.orgFlow }_P003" ${sysCfgMap[key3]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P003');"/>
			</td>
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