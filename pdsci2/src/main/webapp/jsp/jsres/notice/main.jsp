<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	toPage();
});

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	search();
} 

function search(){
	var url ="<s:url value='/jsres/notice/list'/>";
	jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
}

function delNotice(infoFlow){
	jboxConfirm("确认删除？" , function(){
		var url = "<s:url value='/jsres/notice/delNotice'/>?infoFlow="+infoFlow;
		jboxGet(url , null , function(resp){
			toPage(1);
		} , null , true);
	});
}

function edit(infoFlow){
	var url = "<s:url value='/jsres/notice/edit'/>?infoFlow="+infoFlow;
	if(infoFlow)
	{
		jboxOpen(url,"编辑通知",900,500,true);
	}else{
		jboxOpen(url,"新增通知",900,500,true);
	}
}

</script>


<div class="main_hd">
    <h2 class="underline">科教通知</h2>
</div>
<div class="main_bd">
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
			通知标题：
			<input type="text" class="input" name="noticeTitle" value="${param.noticeTitle}" style="width:100px;"/>&#12288;
			&#12288;
			<input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>
			&#12288;
			<input class="btn_green" type="button" onclick="edit('')" value="新&#12288;增"/>
		</form>
	</div>
	
	<div id="contentDiv">
	  
	</div>
</div>
