<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>
</script>

<table class="xllist"  id="treeTable"  style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="30%"/>
		<col width="10%"/>
		<col width="20%"/>
		<col width="30%"/>
		<col width="10%"/>
	</colgroup>
	<thead>
	<tr>
		<th >基地名称</th>
		<th >基地代码</th>
		<th >基地等级</th>
		<th >协同医院</th>
		<th >操作</th>
	</tr>
	</thead>
	<tbody id="tbody">
		<c:forEach items="${orgList}" var="org">
			<c:set var="user" value="${userMap[org.orgFlow]}"/>
			<tr>
				<td style="text-align: center;padding: 0px;">${org.orgName}</td>
				<td style="text-align: center;padding: 0px;">${org.orgCode}</td>
				<td style="text-align: center;padding: 0px;">${org.orgLevelName}</td>
				<td style="text-align: center;padding: 0px;">
					<c:forEach items="${jointOrgMap[org.orgFlow]}" var="joint" varStatus="relStatus">
						<c:if test="${!relStatus.first}">
							,
						</c:if>
						${joint.jointOrgName }
					</c:forEach>
				</td>
				<td style="text-align: left;padding-left: 10px;">

					<c:if test="${org.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
						<a style="color: #449bcd;cursor: pointer;" onclick="editOrg('${org.orgFlow}');">编辑</a>|
						<c:if test="${org.orgLevelId eq orgLevelEnumCountryOrg.id}">
							<a style="color: #449bcd;cursor: pointer;" onclick="xieTong('${org.orgFlow}');">协同基地维护</a>|
						</c:if>
						<a  style="color: #449bcd;cursor: pointer;" href="javascript:delOrg('${org.orgFlow}','${GlobalConstant.RECORD_STATUS_N}');">停用</a>
					</c:if>
					<c:if test="${org.recordStatus == GlobalConstant.RECORD_STATUS_N }">
						<a  style="color: #449bcd;cursor: pointer;" href="javascript:delOrg('${org.orgFlow}','${GlobalConstant.RECORD_STATUS_Y}');">启用</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty orgList}">
			<tr><td colspan="5" style="text-align: center;">无记录</td></tr>
		</c:if>
	</tbody>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>