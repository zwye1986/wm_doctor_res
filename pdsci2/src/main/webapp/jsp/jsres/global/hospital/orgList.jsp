<%--
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
// 分页
function toPage(page) {
	var currentPage = "";
	if (page) {
        $("#currentPage").val(page);
	}
	search();
}
// 查询
function search() {
	var url = "<s:url value='/jsres/base/searchBaseInfoList'/>?type=${param.type}";
	jboxPostLoad("hosContent", url, $("#searchForm").serialize(), true);
}

function exportInfo() {
	var url = "<s:url value='/jsres/base/exporthBaseInfoList'/>?type=${param.type}";
	jboxTip("导出中…………");
	jboxExp($("#searchForm"), url);
	jboxEndLoading();
}

// 查看基地信息
function showOrg(orgFlow){
	var url ="<s:url value='/jsres/base/main'/>?viewFlag=Y&ishos=Y&baseFlow="+orgFlow;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'查看基地信息',1250,800);

	&lt;%&ndash;jboxOpen("<s:url value='/jsres/base/main'/>?viewFlag=Y&baseFlow="+orgFlow,"查看基地信息",1000,600);&ndash;%&gt;
}

function divMouseOver(obj) {
	$(obj).css("cssText", "width: 19%;height:30px; border: 1px solid #cfe7fe;line-height: 30px;text-align: center;border-radius: 2px; margin: 4px 3.5px;background-color: #409eff;cursor: pointer;"); //修改多个css样式
}

function divMouseOut(obj) {
	$(obj).css("cssText", "width: 19%;height:30px; border: 1px solid #cfe7fe;line-height: 30px;text-align: center;border-radius: 2px; margin: 4px 3.5px;background-color: #f4f6f9;cursor: pointer;"); //修改多个css样式
}

function showSpe(orgFlow,SpeId) {
	var url = "<s:url value ='/jsres/base/orgShowSpeInfo'/>?onlyRead=Y&ishos=Y&speFlow=" + SpeId + "&orgFlow="+orgFlow;
	var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1200' height='700' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
	jboxMessager(iframe, '专业基地', 1200, 700);
}

function showTable(obj) {
	document.getElementById("orgDiv"+obj).style.display="";
	document.getElementById("imgShow"+obj).style.display="none";
	document.getElementById("imgHide"+obj).style.display="";
}

function hideTable(obj) {
	document.getElementById("orgDiv"+obj).style.display="none";
	document.getElementById("imgShow"+obj).style.display="";
	document.getElementById("imgHide"+obj).style.display="none";
}



</script>
	<style>
		.grid th {
			border: 1px solid #e7e7eb;
		}
		.grid td {
			border: 1px solid #e7e7eb;
		}

		.orgCss {
			height: 40px;
			line-height: 40px;
			background-color: #f4f6f9;
			border-radius: 5px;
			border: 1px solid #e7eef6;
			margin-top: 15px;
		}

		.textRight{
			text-align: right;
		}

		.textLeft{
			text-align: left;
		}
	</style>
</head>
<body>
<div class="main_bd">
	<div style="padding:0 30px 0;">
		<form id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" value=""/>
				<table style="border-collapse:separate; border-spacing:0px 20px;">
					<tr>
						<td class="td_right">基地名称：</td>
						<td class="td_left">
							<input class="input" type="text" name="orgName" value="${param.orgName}">
						</td>
						<td class="td_right">&#12288;协同单位名称：</td>
						<td class="td_left">
							<input class="input" type="text" name="jointOrgName" value="${param.jointOrgName}">
						</td>
						<td>
							&#12288;<input class="btn_green" type="button" onclick="search()" style="background-color: #409eff;color: white;" value="查&#12288;询"/>
						</td>

				</table>
				<div>
					<input class="btn_green" type="button" onclick="exportInfo()"
								   style="background-color: #ebf5ff;color: #409eff;border: 1px solid #409eff;float: right;margin-top: -50px;"
						   value="导&#12288;出"/>
				</div>
		</form>
	</div>
	<div class="search_table"	>
		<c:forEach var="org" items="${orgList}" varStatus="s">
			<div class="orgCss">
				<div style="display: flex;flex-wrap: wrap;">
					<div style="font-weight: bold;margin-left: 10px;background-color: #54B2E5;color: white;height: 20px;line-height: 20px;margin-top: 10px;border-radius: 3px;">
						&#12288;${baseMap[org.orgFlow].baseGradeName}&#12288;
					</div>
					<div style="color:#409eff;font-weight: bold;cursor: pointer;" onclick="showOrg('${org.orgFlow}');">
						&#12288;${org.orgName}（${org.orgCode}）>
					</div>
				</div>
				<div style="float:right;display: flex;flex-wrap: wrap; margin-top: -40px;">

					<div style="margin-right: 50px;">
						协同单位数量：<span style="color:#409eff;">${empty jointOrgNumMap[org.orgFlow]?0:jointOrgNumMap[org.orgFlow]}</span>
					</div>
					<div style="margin-right: 50px;">
						专业基地数量:<span style="color:#409eff;">${empty speNumMap[org.orgFlow]?0:speNumMap[org.orgFlow]}</span>
					</div>
					<div style="margin-right: 10px;margin-top: 10px;">
						<img id="imgShow${org.orgFlow}" src="<s:url value='/jsp/jsres/images/down3.png'/>"  onclick="showTable('${org.orgFlow}');"/>
						<img id="imgHide${org.orgFlow}" style="display: none" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="hideTable('${org.orgFlow}');"/>
					</div>
				</div>
			</div>
			<div id="orgDiv${org.orgFlow}" style="display: none">
				<table border="0" cellpadding="0" cellspacing="0" class="grid" style="margin-top: 20px;margin-bottom: 20px;">
					<colgroup>
						<col width="12%"/>
						<col width="12%"/>
						<col width="19%"/>
						<col width="12%"/>
						<col width="12%"/>
						<col width="12%"/>
						<col width="9%"/>
						<col width="12%"/>
					</colgroup>
					<tr>
						<th class="textRight">地&#12288;&#12288;市</th>
						<td class="textLeft">${org.orgCityName}</td>
						<th class="textRight">培训基地（医院负责人）</th>
						<td class="textLeft">${org.orgPersonInCharge}</td>
						<th class="textRight">联系电话</th>
						<td colspan="3" class="textLeft"></td>
					</tr>
					<tr>
						<th class="textRight">职能部门负责人</th>
						<td class="textLeft">${resBaseMap[org.orgFlow].znbmfzrmc}</td>
						<th class="textRight">联系电话</th>
						<td class="textLeft">${resBaseMap[org.orgFlow].znbmfzrdh}</td>
						<th class="textRight">住培业务联系人</th>
						<td class="textLeft">${resBaseMap[org.orgFlow].zpywlxrmc}</td>
						<th class="textRight">联系电话</th>
						<td class="textLeft">${resBaseMap[org.orgFlow].zpywlxrdh}</td>
					</tr>
					<tr>
						<th class="textRight">协同单位</th>
						<td colspan="7" style="color: #409eff" class="textLeft">
							<c:forEach items="${jointOrgMap[org.orgFlow]}" var="joint" varStatus="j">
								<span style="cursor: pointer;" onclick="showOrg('${joint.jointOrgFlow}');">${joint.jointOrgName}；</span>
							</c:forEach>
						</td>
					</tr>
				</table>
				<div>
					<div style="height: 30px;text-align: center;background-color: #f4f6f9;line-height: 30px;">专业基地</div>
					<div style="display: flex;flex-wrap: wrap;">
						<c:forEach items="${speMap[org.orgFlow]}" var="spe">
							<div class="speDiv"
								 onmouseover="divMouseOver(this);"
								 onmouseout="divMouseOut(this);"
								 onclick="showSpe('${org.orgFlow}','${spe.speId}');"
								 style="width: 19%;height:30px; border: 1px solid #cfe7fe;line-height: 30px;text-align: center;border-radius: 2px; margin: 4px 3.5px;background-color: #f4f6f9;cursor: pointer;">
									${spe.speName}(${spe.speId})
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>--%>


<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>江苏省住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		// 分页
		function toPage(page) {
			var currentPage = "";
			if (page) {
				$("#currentPage").val(page);
			}
			search();
		}
		// 查询
		function search() {
			var url = "<s:url value='/jsres/base/searchBaseInfoList'/>?type=${param.type}";
			jboxPostLoad("hosContent", url, $("#searchForm").serialize(), true);
		}

		// 查看基地信息
		function showOrg(orgFlow){
			var url ="<s:url value='/jsres/base/main'/>?viewFlag=Y&ishos=Y&baseFlow="+orgFlow;
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='"+url+"'></iframe>";
			jboxMessager(iframe,'查看基地信息',1250,800);

			<%--jboxOpen("<s:url value='/jsres/base/main'/>?viewFlag=Y&baseFlow="+orgFlow,"查看基地信息",1000,600);--%>
		}

		function resetTrainBase() {
			$("input[name='jointOrgName']").val("");
			$("input[name='orgName']").val("");
		}
	</script>
	<style>
		.grid th {
			border: 1px solid #e7e7eb;
			color: #000000;
			font: 14px Microsoft YaHei;
			font-weight: 500;
		}
		.grid td {
			border: 1px solid #e7e7eb;
			color: #000000;
			font: 14px Microsoft YaHei;
			font-weight: 400;
		}
	</style>
</head>
<body>
<div class="main_bd">
	<div style="padding:0 30px 0;">
		<form id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" value=""/>
			<table style="border-collapse:separate; border-spacing:0px 20px;">
				<tr>
<%--					<td class="td_right" style="color: #000000;font: 14px Microsoft YaHei;font-weight: 400;">年份：</td>--%>
<%--					<td class="td_left">--%>
<%--						<input class="input" type="text" name="sessionYear" value="${sessionYear}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">--%>
<%--					</td>--%>
					<td class="td_right" style="color: #000000;font: 14px Microsoft YaHei;font-weight: 400;">&#12288;基地名称：</td>
					<td class="td_left">
						<input class="input" type="text" name="orgName" value="${param.orgName}">
					</td>
					<td class="td_right" style="color: #000000;font: 14px Microsoft YaHei;font-weight: 400;">&#12288;协同单位名称：</td>
					<td class="td_left">
						<input class="input" type="text" name="jointOrgName" value="${param.jointOrgName}">
					</td>
					<td>
						&#12288;<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
					</td>
					<td>
						&#12288;<input class="btn_grey" type="button" onclick="resetTrainBase()" value="重&#12288;置"/>
					</td>
			</table>
		</form>
	</div>
	<div class="search_table"	>
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
<%--				<col width="5%"  style="text-align: center;"/>--%>
<%--				<col width="8%"  style="text-align: center;"/>--%>
<%--				<col width="10%" style="text-align: center;"/>--%>
<%--				<col width="20%"  style="text-align: center;"/>--%>
<%--				<col width="11%"  style="text-align: center;"/>--%>
<%--				<col width="12%" style="text-align: center;"/>--%>
<%--				<col width="20%"  style="text-align: center;"/>--%>
<%--				<col width="15%" style="text-align: center;"/>--%>
				<col width="5%"  style="text-align: center;"/>
				<col width="8%"  style="text-align: center;"/>
				<col width="10%" style="text-align: center;"/>
				<col width="32%"  style="text-align: center;"/>
				<col width="11%"  style="text-align: center;"/>
				<col width="35%"  style="text-align: center;"/>
			</colgroup>
			<tr>
				<th>序号</th>
				<th>地区</th>
				<th>基地编号</th>
				<th>基地名称</th>
				<th>基地级别</th>
<%--				<th>协同单位编号</th>--%>
				<th>协同单位名称</th>
<%--				<th>协同单位级别</th>--%>
			</tr>
			<c:forEach var="joint" items="${jointCountList}" varStatus="j">
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${sysOrgExtList}" var="org" varStatus="s">
					<c:if test="${joint.ORG_FLOW eq org.orgFlow }">
						<c:choose>
							<c:when test="${joint.JOINT_COUNT > 0}">
								<c:choose>
									<c:when test="${count == 0 }">
										<tr>
											<td rowspan="${joint.JOINT_COUNT}">${j.index + 1}</td>
											<td rowspan="${joint.JOINT_COUNT}">${org.orgCityName}</td>
											<td rowspan="${joint.JOINT_COUNT}">${org.orgCode}</td>
											<td rowspan="${joint.JOINT_COUNT}"><a onclick="showOrg('${org.orgFlow}');">${org.orgName}</a></td>
											<td rowspan="${joint.JOINT_COUNT}">${org.orgBaseGradeName}</td>
<%--											<td>${org.orgCode}-${count + 1}</td>--%>
											<td><a onclick="showOrg('${org.jointOrgFlow}');">${org.jointOrgName}</a></td>
<%--											<td>${org.jointBaseGradeName}</td>--%>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
<%--											<td>${org.orgCode}-${count + 1}</td>--%>
											<td><a onclick="showOrg('${org.jointOrgFlow}');">${org.jointOrgName}</a></td>
<%--											<td>${org.jointBaseGradeName}</td>--%>
										</tr>
									</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}"></c:set>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${j.index + 1}</td>
									<td>${org.orgCityName}</td>
									<td>${org.orgCode}</td>
									<td class="left-content"><a onclick="showOrg('${org.orgFlow}');">${org.orgName}</a></td>
									<td>${org.orgBaseGradeName}</td>
									<td>————</td>
<%--									<td></td>--%>
<%--									<td></td>--%>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</c:forEach>
			<c:if test="${empty sysOrgExtList}">
				<tr>
					<td colspan="8">无记录</td>
				</tr>
			</c:if>
		</table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(jointCountList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
</body>
</html>
