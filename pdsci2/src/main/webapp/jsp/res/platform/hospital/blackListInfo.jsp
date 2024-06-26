<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
	function toPage(page) {
		var currentPage = "";
		if (!page || page != undefined) {
			currentPage = page;
		}
		if (page == undefined || page == "") {
			currentPage = 1;
		}
		$("#currentPage").val(currentPage);
		search();
	}
	//黑名单信息查询
	function search() {
		if ($("#searchParam_Course").val() == "") {
			$("#result_Course").val("");
		}
		if ($('#jointOrg').get(0) != undefined) {
			$('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
		}
		$("#searchForm").submit();
	}

	//黑名单移除
	function removeBlack(userFlow, recordStatus, recordflow) {
		jboxConfirm("确认移除该记录吗？", function () {
			var url = "<s:url value='/res/doc/removeBlack?userFlow='/>" + userFlow + "&recordStatus=" + recordStatus + "&recordFlow=" + recordflow;
			jboxPost(url, null, function () {
				search();
			});
		});
	}
	//展示原因详情
	function showDetail(resp1, resp2) {
		jboxOpenContent(resp1,"原因",300,100,true)
	}
	//添加黑名单
	function addBlackList() {
		jboxOpen("<s:url value='/jsp/res/platform/hospital/addBlackList.jsp'/>", "黑名单添加", 500, 300);
	}
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
	$(function(){
		var courseArray = [];
		<c:forEach items="${orgs}" var="org" varStatus="i">
			var orgName='${org.orgName}';
			var orgFlow='${org.orgFlow}';
			courseArray[${i.index}] = [orgFlow,orgName];
		</c:forEach>
		$("#searchParam_Course").suggest(courseArray,{
			attachObject:'#suggest_Course',
			dataContainer:'#result_Course',
			triggerFunc:function(orgName){},
			enterFunc:function(orgName){}
		});
	})
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doc/blackListInfo?roleFlag=${param.roleFlag}" />" method="post">
			<input type="hidden" name="currentPage" id="currentPage"/>
			<div class="queryDiv">
				<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					<div class="inputDiv">
						<label class="qlable">培训基地：</label>
						<input id="searchParam_Course" class="qtext"  name="searchParam_Course" value="${param.searchParam_Course }" placeholder="输入培训基地名称" onkeydown="adjustResults();" onfocus="adjustResults();"/>
						<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
						<input type="hidden" id="result_Course" name="orgFlow" value="${param.orgFlow}"/>
					</div>
				</c:if>
				<div class="inputDiv">
					<label class="qlable">学员姓名：</label>
					<input type="text" value="${param.userName}" name="userName" class="qtext"/>
				</div>
				<div class="inputDiv">
					<label class="qlable">原培训年级：</label>
					<input type="text" value="${param.sessionNumber}" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="qtext"/>
				</div>
				<div class="inputDiv">
					<label class="qlable">原培训专业：</label>
					<input type="text" value="${param.trainingSpeName}" name="trainingSpeName" class="qtext"/>
				</div>
				<c:if test="${countryOrgFlag eq 'Y'}">
					<div class="inputDiv" style="text-align:left;">
						&#12288;&#12288;&#12288;<input type="checkbox" id="jointOrg" name="jointOrg" <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label for="jointOrg">协同基地</label>&#12288;
					</div>
				</c:if>
				<div class="lastDiv" style="min-width: 183px;max-width: 183px;">
					<c:if test="${param.roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
						<div style="float:left;margin-left:20px;"><input class="searchInput" type="button" onclick="search()" value="查&#12288;询"/></div>
					</c:if>
					<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<input class="searchInput" type="button" onclick="search()" value="查&#12288;询"/>
						<input class="searchInput" type="button" onclick="addBlackList()" value="新&#12288;增"/>
					</c:if>
				</div>
			</div>
		</form>
		</div>
		<table class="xllist">
				<tr>
					<th>姓名</th>
					<th>证件号</th>
					<th>原培训基地</th>
					<th>原培训专业</th>
					<th>原培训年级</th>
					<th>创建时间</th>
					<th>原因</th>
					<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<th>操作</th>
					</c:if>
				</tr>
				<c:forEach items="${blackLists}" var="black">
					<tr>
						<td>${black.userName}</td>
						<td>${black.idNo}</td>
						<td title="${black.orgName}">${pdfn:cutString(black.orgName,10,true,3) }</td>
						<td title="${black.trainingSpeName}">${pdfn:cutString(black.trainingSpeName,6,true,3) }</td>
						<td>${black.sessionNumber}</td>
						<td>${pdfn: transDateTime(black.createTime)}</td>
						<td><a style="color:blue;cursor:pointer;" onclick="showDetail('${black.reason}','${black.reasonYj}');">详情</a></td>
						<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<td><a style="color:blue;cursor:pointer;" onclick="removeBlack('${black.userFlow}','${GlobalConstant.RECORD_STATUS_N}','${black.recordFlow}');">移除</a></td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${empty blackLists}">
					<tr><td colspan="8">无记录</td></tr>
				</c:if>
		</table>
		<c:set var="pageView" value="${pdfn:getPageView(blackLists)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>