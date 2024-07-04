<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>惠州APP-导师端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css">
<style type="text/css">
body{ 
	height:100%;
	overflow:scroll;
}
</style>
<script type="text/javascript">
	function test() {
		var action = $("#reqCode option:selected").val();
		if(action==""){
			jboxTip("请选择测试的交易");
			return;
		}
		var method = $("#reqCode option:selected").attr("method");
		var url ="";
		if(action=="login" || action=="version" || action=="notice" || action=="noticeCount"){
			url = "<s:url value='/res/hzyy'/>/"+action+"?_method="+method;	
		}else{
			url = "<s:url value='/res/hzyy/tutor'/>/"+action+"?_method="+method;
		}
		
		if(method=="put"){
			method = "post";
		}
		if(method=="delete"){
			method = "post";
		}
		var formData = $("#reqParam").val();
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var dataType = $("#dataType option:selected").val();
		if(needDataType=='Y'){
			formData = formData+"&dataType="+dataType;
			formData = formData+"&"+$("#reqParamDataType").val();
		}else{
			
		}
		var isForm = $("#reqCode option:selected").attr("isForm");
		if(isForm=="Y")
		{
			var form=$('<form action="'+url+'" method="'+(method||'post')+'"></form>');
			var arr=formData.split("&"); //各个参数放到数组里
			for(var i=0;i < arr.length;i++){
				var num=arr[i].indexOf("=");
				if(num>0){
					$(form).append($('<input name="'+arr[i].substring(0,num)+'" value="'+arr[i].substr(num+1)+'" type="hidden"/>'));
				}
			}
			$(form).appendTo('body').submit().remove();
		}else {
			$.ajax({
				type: method,
				url: url,
				data: formData,
				cache: false,
				beforeSend: function () {
					jboxStartLoading();
				},
				success: function (resp) {
					jboxEndLoading();
					jboxTip("测试成功");
					console.log(resp);
					$("#rsp").text(JSON.stringify(resp,null,4));
				},
				error: function (resp,XMLHttpRequest, textStatus, errorThrown) {

					jboxEndLoading();
					console.log(resp.responseText);
					console.log(resp);
					jboxTip("操作失败,请刷新页面后重试");
				},
				complete: function () {
					jboxEndLoading();
				},
				statusCode: {
					405: function () {
						jboxTip("交易方法不正确");
					}
				}
			});
		}

	}
	function change(divid) {
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var dataType = $("#dataType option:selected").val();
		console.log($("#" + divid + " textarea").first().val());
		$("#reqParam").val($("#" + divid + " textarea").first().val());
		$("#reqParamDataType").text($("#" + divid + " textarea[dataType='"+dataType+"']").first().val());
		if(needDataType=='Y'){
			$("#dataType").css("display","");
			$("#reqParamDataTypeDiv").css("display","");
		}else{
			$("#dataType").css("display","none");
			$("#reqParamDataTypeDiv").css("display","none");
		}
	}
	function change2(dataType) {
		var divid = $("#reqCode option:selected").val();
		$("#reqParamDataType").text($("#" + divid + " textarea[dataType='"+dataType+"']").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="${ctxPath}/res/hzyy/tutor/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="ownerInfo" method="get" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >个人信息(ownerInfo)get</option>
						<option value="index" method="post" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >首页查看年度总计划(index)post</option>
						<option value="fileList" method="post" needDataType="Y" <c:if test="${param.reqCode=='fileList'}">selected</c:if> >图片或文件列表(fileList)post</option>
						<option value="delFile" method="post" needDataType="N" <c:if test="${param.reqCode=='delFile'}">selected</c:if> >删除文件(delFile)post</option>
						<option value="checkFile" method="post"  needDataType="N" <c:if test="${param.reqCode=='checkFile'}">selected</c:if> >下载文件或图片前先校验(checkFile)post</option>
						<option value="downFile" method="post" isForm="Y" needDataType="N" <c:if test="${param.reqCode=='downFile'}">selected</c:if> > 下载文件或图片(downFile)post</option>
						<option value="addFile" method="post" isForm="N" needDataType="Y" <c:if test="${param.reqCode=='addFile'}">selected</c:if> > 上传文件(addFile)post</option>
		</select>
			<select id="dataType" name="dataType" style="display: none;" onchange="change2(this.value);">
				<option value="stuImgList" <c:if test="${param.dataType=='stuImgList'}">selected</c:if> >学员见面照片[stuImgList]</option>
				<option value="stuSchImgList" <c:if test="${param.dataType=='stuSchImgList'}">selected</c:if> >学员培训计划[stuSchImgList]</option>
				<option value="preYearImgList" <c:if test="${param.dataType=='preYearImgList'}">selected</c:if> >工作总结（上半年）[preYearImgList]</option>
				<option value="afterYearImgList" <c:if test="${param.dataType=='afterYearImgList'}">selected</c:if> >工作总结（下半年）[afterYearImgList]</option>
			</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			roleId:<input name="roleId" type="text" value="${roleId}">
			<br>
			indexType:<input name="indexType" type="text" value="${indexType}">
			inProcessType:<input name="inProcessType" type="text" value="${inProcessType}">
			<br>
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
			<input type="submit" value="记住" class="search">
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" wrap="soft"></textarea>
	</div>
	<div id="reqParamDataTypeDiv"  style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px;display: none;">
		参数2：<textarea id="reqParamDataType" style="width: 600px; height: 200px;" name="reqParamDataType" wrap="soft"></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="ownerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="index" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&year=2018</textarea>
	</div>
	<div id="fileList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&year=2018&pageIndex=1&pageSize=10&tabType=image或file</textarea>
	</div>
	<div id="delFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&fileFlow=</textarea>
	</div>
	<div id="checkFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=</textarea>
	</div>
	<div id="downFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=</textarea>
	</div>
	<div id="addFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&year=2018&yearMonth=2018-05&tabType=image或file&fileName=&uploadFile=${uploadFile}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>