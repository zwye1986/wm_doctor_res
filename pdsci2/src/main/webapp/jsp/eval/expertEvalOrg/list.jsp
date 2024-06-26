<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>

	function cfgOrgDetail(userFlow,orgFlow,evalYear,userName,orgName)
	{
		var evalOrg={
			expertUserFlow:userFlow,
			orgFlow:orgFlow,
			evalYear:evalYear
		};
		var url="<s:url value='/eval/expertEvalOrg/showExpertOrgCfg'/>?orgFlow="+orgFlow+"&evalYear="+evalYear+"&expertUserFlow="+userFlow;
		jboxOpen(url,"专家【"+userName+"】"+evalYear+"年评估【"+orgName+"】基地配置", 500, 400);
	}
	function addOrgExpert(orgFlow,evalYear,orgName)
	{
		var evalOrg={
			orgFlow:orgFlow,
			evalYear:evalYear
		};
		var url="<s:url value='/eval/expertEvalOrg/addOrgExpert'/>?orgFlow="+orgFlow+"&evalYear="+evalYear;
		jboxOpen(url,"培训基地【"+orgName+"】"+evalYear+"年评估专家配置", 500, 400);
	}
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="10%"/>
		<col width="10%"/>
		<col width="70%"/>
		<col width="10%"/>
	</colgroup>
	<thead>
	<tr>
		<th>评估年份</th>
		<th>培训基地</th>
		<th>专家评估配置</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${empty param.evalYear}">
		<tr>
			<td colspan="14">请选择评估年份</td>
		</tr>
	</c:if>
	<c:if test="${empty orgs and( not empty param.evalYear)}">
		<tr>
			<td colspan="14">无记录</td>
		</tr>
	</c:if>
	<c:forEach items="${orgs }" var="org">
		<tr >
			<td >${param.evalYear }</td>
			<td >${org.orgName }</td>
			<td >
				<div style="width: 100%;max-height: 200px;overflow: auto;" id="${org.orgFlow}">
					<c:set value="${evalOrgExpertMap[org.orgFlow]}" var="userList"></c:set>
					<c:forEach items="${userList}" var="user">
						<div style="width: 24%;float: left;" id="${org.orgFlow}${user.userFlow}">
							<table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
								<tr>
									<td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">
										<label>
											<a style="color:#449bcd;" href="javascript:void(0);" onclick="cfgOrgDetail('${user.userFlow}','${org.orgFlow}','${param.evalYear}','${user.userName}','${org.orgName}')">
												${user.userName}[${user.userCode}]
											</a>
										</label>
									</td>
								</tr>
							</table>
						</div>
					</c:forEach>
				</div>
			</td>
			<td >
				<a style="color:#449bcd;" href="javascript:void(0);" onclick="addOrgExpert('${org.orgFlow}','${param.evalYear}','${org.orgName}')">添加专家</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table>
	<tr><td colspan="99">注：点击专家姓名，进行评估表配置</td></tr>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(orgs)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>