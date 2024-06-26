<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ztree" value="true"/>
</jsp:include>

<style type="text/css">
</style>

<script type="text/javascript">

function search(){
	jboxStartLoading();
	$("#searchForm").submit();
}

function toPage(page){
	if(page){
		$("#currentPage").val(page);
		search();
	}
}

function delFile(flow){
	jboxConfirm("确认删除？",function(){
		jboxPost('<s:url value="/portal/manage/info/delFile"/>?recordFlow='+flow,null,function(){
			jboxTip("操作成功");
			search();
		},null,false);
	})
}

function reply(recordFlow){
	jboxOpen('<s:url value="/portal/manage/info/reply"/>?recordFlow='+recordFlow,"回复",800,400,true);
}

function changeShowFlag(recordFlow,obj){
	var showFlag='';
	if($(obj).attr("checked")){
		showFlag='Y';
	}
	jboxPost('<s:url value="/portal/manage/info/changeShowFlag"/>?recordFlow='+recordFlow+"&showFlag="+showFlag,null,function(resp){
		if(resp==-1){
			jboxTip("最多只能展示三条");
			$(obj).attr("checked",false);
		}
		if(resp==1){
			jboxTip("操作成功");
		}
	},null,false);
}
</script>

</head>
<body>
<div class="mainright" id="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/portal/manage/info/portalSuggestList" />" method="post" >
		 <div class="queryDiv">
			 <input type="hidden" id="currentPage" name="currentPage" value="">
		 	<div class="inputDiv">
		 		建议标题：<input type="text" name="suggestTitle" value="${param.suggestTitle}" class="qtext"/>
		 	</div>
			<div class="lastDiv" style="min-width: 200px;max-width: 200px;">
				<input type="button" onclick="search()" class="search" value="查&#12288;询">&#12288;
			</div>
		</div>
	</form>
	<div class="resultDiv">
		<table class="xllist" style="margin-top: 10px;">
			<thead>
			<tr>
				<th width="130px">标题</th>
				<th width="60px">提交人</th>
				<th width="90px">手机号码</th>
				<th width="80px">提交时间</th>
				<th style="width: 60%">建议内容</th>
				<th width="55px">是否展示</th>
				<th width="40px">回复</th>
			</tr>
			</thead>
			<c:forEach items="${portalSuggestList}" var="suggest">
				<tr>
					<td>${suggest.suggestTitle}</td>
					<td>${suggest.userName}</td>
					<td nowrap="">${suggest.userPhone}</td>
					<td nowrap="">${suggest.submitTime}</td>
					<td>${suggest.suggestContent}</td>
					<td><input type="checkbox" value="Y" ${suggest.showFlag eq 'Y'?'checked':''}
							   onchange="changeShowFlag('${suggest.recordFlow}',this)"></td>
					<td><a style="cursor: pointer" onclick="reply('${suggest.recordFlow}')">
						<c:if test="${empty suggest.replyContent}">回复</c:if>
						<c:if test="${!(empty suggest.replyContent)}">查看</c:if>
					</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty portalSuggestList}">
				<tr>
					<td colspan="20">无数据</td>
				</tr>
			</c:if>
		</table>
		<c:set var="pageView" value="${pdfn:getPageView(portalSuggestList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div> 
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>