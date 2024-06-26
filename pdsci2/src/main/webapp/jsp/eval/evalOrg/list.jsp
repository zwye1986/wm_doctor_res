<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>

	$(function () {

	});
	function evalOrg(obj,orgFlow,evalYear,recordFlow)
	{
		var checkedVal = $(obj).attr("checked");
		var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
		if ("checked" == checkedVal) {
			recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
		}
		var evalOrg={
			orgFlow:orgFlow,
			evalYear:evalYear,
			recordFlow:recordFlow,
			recordStatus:recordStatus
		};

		jboxPost("<s:url value='/eval/evalOrg/saveEvalOrg'/>",evalOrg,function(resp){
			if('${GlobalConstant.SAVE_SUCCESSED}'!=resp){
				$(obj).attr("checked",false);
			}
		},null,true);
	}

	function showExpertOrgSpe(orgFlow,evalYear,orgName)
	{
		var checkedVal = $("#"+orgFlow+"ExportOrg").attr("checked");
		var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
		<%--if ("checked" == checkedVal) {--%>
			<%--recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";--%>
		<%--}--%>
		<%--if(recordStatus == "${GlobalConstant.RECORD_STATUS_N}")--%>
		<%--{--%>
			<%--jboxTip("请配置基地参与评估！");--%>
			<%--return false;--%>
		<%--}--%>
		var url="<s:url value='/eval/evalOrg/showExpertOrgSpe'/>?orgFlow="+orgFlow+"&evalYear="+evalYear;
		jboxOpen(url,"【"+orgName+"】"+evalYear+"年专业基地评估配置", 900, 400);
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
		<th>参与评估</th>
		<th>专业基地</th>
	</tr>
	</thead>
	<tbody id="tbody">
	<c:forEach items="${sysList}" var="org">
		<tr>
			<td>${org.orgName}</td>
			<td>${param.evalYear}</td>
			<td>
				<input onclick="evalOrg(this,'${org.orgFlow}','${param.evalYear}','${exportMap[org.orgFlow].recordFlow}')"
					   style="vertical-align: middle; cursor: pointer;" id="${org.orgFlow}ExportOrg"
					   <c:if test="${exportMap[org.orgFlow].recordStatus eq 'Y'}"> checked="checked"</c:if> type="checkbox">
			</td>
			<td>
				<span style=" cursor: pointer;color: #449bcd"
					  onclick="showExpertOrgSpe('${org.orgFlow}','${param.evalYear}','${org.orgName}')" id="${org.orgFlow}">${speMap[org.orgFlow]}</span>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty param.evalYear}">
		<tr>
			<td colspan="14">请选择评估年份</td>
		</tr>
	</c:if>
		<c:if test="${empty sysList and( not empty param.evalYear)}">
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