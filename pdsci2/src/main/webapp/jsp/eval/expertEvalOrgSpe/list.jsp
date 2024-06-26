<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>

	function expertEvalOrg(obj,userFlow,orgFlow,evalYear,recordFlow)
	{
		var checkedVal = $(obj).attr("checked");
		var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
		if ("checked" == checkedVal) {
			recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
		}
		var evalOrg={
			expertUserFlow:userFlow,
			orgFlow:orgFlow,
			evalYear:evalYear,
			recordFlow:recordFlow,
			recordStatus:recordStatus
		};

		jboxPost("<s:url value='/eval/expertEvalOrg/saveEvalOrg'/>",evalOrg,function(resp){
			if('${GlobalConstant.SAVE_SUCCESSED}'!=resp){
				$(obj).attr("checked",false);
			}
		},null,true);
	}
	function cfgOrgDetail(userFlow,orgFlow,speId,evalYear,userName,orgName,speName)
	{
		var evalOrg={
			expertUserFlow:userFlow,
			orgFlow:orgFlow,
			evalYear:evalYear
		};
		var url="<s:url value='/eval/expertEvalOrgSpe/showExpertOrgSpeCfg'/>?orgFlow="+orgFlow+"&evalYear="+evalYear+"&expertUserFlow="+userFlow+"&speId="+speId;
		jboxOpen(url,"专家【"+userName+"】"+evalYear+"年评估专业基地【"+speName+"("+orgName+")】评估配置", 500, 400);
	}
	function addOrgExpert(orgFlow,speId,evalYear,orgName,speName)
	{
		var evalOrg={
			orgFlow:orgFlow,
			evalYear:evalYear
		};
		var url="<s:url value='/eval/expertEvalOrgSpe/addOrgExpert'/>?orgFlow="+orgFlow+"&evalYear="+evalYear+"&speId="+speId;
		jboxOpen(url,"专业基地【"+speName+"("+orgName+")】"+evalYear+"年评估专家配置", 500, 400);
	}
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="5%"/>
		<col width="10%"/>
		<col width="15%"/>
		<col width="60%"/>
		<col width="10%"/>
	</colgroup>
	<thead>
	<tr>
		<th>评估年份</th>
		<th>培训基地</th>
		<th>专业基地</th>
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
	<c:if test="${empty orgSpeList and( not empty param.evalYear)}">
		<tr>
			<td colspan="14">无记录</td>
		</tr>
	</c:if>
	<c:forEach items="${orgSpeList }" var="orgSpe">
		<tr >
			<td >${param.evalYear }</td>
			<td >${orgSpe.orgName }</td>
			<td >${orgSpe.speName }</td>
			<td >
				<div style="width: 100%;max-height: 200px;overflow: auto;" id="${orgSpe.orgFlow}${orgSpe.speId}">
					<c:set var="key" value="${orgSpe.orgFlow}${orgSpe.speId}"></c:set>
					<c:set value="${evalOrgSpeExpertMap[key]}" var="userList"></c:set>
					<c:forEach items="${userList}" var="user">
						<div style="width: 24%;float: left;" id="${orgSpe.orgFlow}${orgSpe.speId}${user.userFlow}">
							<table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
								<tr>
									<td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">
										<label>
											<a style="color:#449bcd;" href="javascript:void(0);" onclick="cfgOrgDetail('${user.userFlow}','${orgSpe.orgFlow}','${orgSpe.speId}',
													'${param.evalYear}','${user.userName}','${orgSpe.orgName}','${orgSpe.speName}')">
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
				<a style="color:#449bcd;" href="javascript:void(0);" onclick="addOrgExpert('${orgSpe.orgFlow}','${orgSpe.speId}'
						,'${param.evalYear}','${orgSpe.orgName}','${orgSpe.speName}')">添加专家</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table>
	<tr><td colspan="99">注：点击专家姓名，进行评估表配置</td></tr>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(orgSpeList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>