<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
</style>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}

	function addId(){
		jboxOpen("<s:url value='/res/idCtrl/addId'/>","详情", 480, 300);
	}

	function details(recordFlow){
		jboxOpen("<s:url value='/res/idCtrl/details'/>?recordFlow="+recordFlow+"&currentPage=${param.currentPage}","详情", 480, 480,false);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/idCtrl/mainList'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">操作人：</label>
						<input type="text" name="operUserName" value="${param.operUserName}" class="qtext">
					</div>
					<div class="inputDiv">
						<label class="qlable">单位名称：</label>
						<input type="text" name="orgName" value="${param.orgName}" class="qtext">
					</div>
					<div class="inputDiv" style="text-align: left">&#12288;&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						<input type="button" value="新&#12288;增" class="search" onclick="addId();"/>
					</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;">序号</td>
						<td style="text-align: center;padding: 0px;">操作人</td>
						<td style="text-align: center;padding: 0px;">单位名称</td>
						<td style="text-align: center;padding: 0px;">分配数量</td>
						<td style="text-align: center;padding: 0px;">操作时间</td>
						<td style="text-align: center;padding: 0px;">操作</td>
					</tr>
					<c:forEach items="${idctrlMainList}" var="item" varStatus="s">
						<tr>
							<td style="text-align: center;padding: 0px;">${s.index+1}</td>
							<td style="text-align: center;padding: 0px;">${item.operUserName}</td>
							<td style="text-align: center;padding: 0px;">${item.orgName}</td>
							<td style="text-align: center;padding: 0px;">${item.idNumber}</td>
							<td style="text-align: center;padding: 0px;">${pdfn:transDate(item.createTime)}</td>
							<td style="text-align: center;padding: 0px;"><a onclick="details('${item.recordFlow}')" style="cursor: pointer;color: blue;">查看</a></td>
						</tr>
					</c:forEach>
					<c:if test="${empty idctrlMainList}">
						<tr><td style="text-align: center;" colspan="10">无记录</td></tr>
					</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(idctrlMainList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>