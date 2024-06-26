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

function addFlie(){
	jboxOpen('<s:url value="/portal/manage/info/addFile"/>',"上传文件",500,180,true);
}
</script>

</head>
<body>
<div class="mainright" id="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/portal/manage/info/portalFileList" />" method="post" >
		 <div class="queryDiv">
			 <input type="hidden" id="currentPage" name="currentPage" value="">
		 	<div class="inputDiv">
		 		文件名称：<input type="text" name="fileName" value="${param.fileName}" class="qtext"/>
		 	</div>
			<div class="lastDiv" style="min-width: 200px;max-width: 200px;">
				<input type="button" onclick="search()" class="search" value="查&#12288;询">
				<input type="button" onclick="addFlie()" class="search" value="新&#12288;增">
			</div>
		</div>
	</form>
	<div class="resultDiv">
		<table class="xllist" style="margin-top: 10px;">
			<thead>
			<tr>
				<th>文件名称</th>
				<th>上传时间</th>
				<th>大小</th>
				<th>操作</th>
			</tr>
			</thead>
			<c:forEach items="${fileList}" var="file">
				<tr>
					<td>${file.fileName}</td>
					<td>${file.uploadTime}</td>
					<td>${file.fileSize}</td>
					<td><a style="cursor: pointer" href="${sysCfgMap['upload_base_url']}${file.fileUrl}" target="_blank">查看</a>&nbsp;
						<a style="cursor: pointer" href="${sysCfgMap['upload_base_url']}${file.fileUrl}" download="${file.fileName}">下载</a>&nbsp;
						<a style="cursor: pointer" onclick="delFile('${file.recordFlow}')">删除</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty fileList}">
				<tr>
					<td colspan="7">无数据</td>
				</tr>
			</c:if>
		</table>
		<c:set var="pageView" value="${pdfn:getPageView(fileList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div> 
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>