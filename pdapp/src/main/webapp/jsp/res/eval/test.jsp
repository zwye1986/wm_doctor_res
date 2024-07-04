<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>基地评估专家APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}

		var isForm = $("#reqCode option:selected").attr("isForm");
		if(isForm=="Y")
		{
			var url = "<s:url value='/res/eval'/>/" + action ;
			var form=$('<form action="'+url+'" method="'+(method||'post')+'"></form>');
			var formData = $("#reqParam").val();
			var needDataType = $("#reqCode option:selected").attr("needDataType");
			var dataType = $("#dataType option:selected").val();
			if (needDataType == 'Y') {
				formData = formData + "&dataType=" + dataType;
				formData = formData + "&" + $("#reqParamDataType").val();
			} else {

			}
			var arr=formData.split("&"); //各个参数放到数组里
			for(var i=0;i < arr.length;i++){
				var num=arr[i].indexOf("=");
				if(num>0){
					$(form).append($('<input name="'+arr[i].substring(0,num)+'" value="'+arr[i].substr(num+1)+'" type="hidden"/>'));
				}
			}
			$(form).appendTo('body').submit().remove();
		}else {
			var url = "<s:url value='/res/eval'/>/" + action + "?_method=" + method;
			var formData = $("#reqParam").val();
			var needDataType = $("#reqCode option:selected").attr("needDataType");
			var dataType = $("#dataType option:selected").val();
			if (needDataType == 'Y') {
				formData = formData + "&dataType=" + dataType;
				formData = formData + "&" + $("#reqParamDataType").val();
			} else {

			}
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
		$("#reqParam").val($("#" + divid + "  textarea").first().val());
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
	<form name="appForm" action="${ctxPath}/res/eval/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" isForm="N" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >1.0.0 获取版本号(version)get</option>
						<option value="login" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >1.0.1 登录(login)post</option>
						<option value="index" method="post"  isForm="N" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >1.0.2 首页基地列表(index)post</option>
						<option value="evalOrg" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='evalOrg'}">selected</c:if> >1.0.3 选中基地评估(evalOrg)post</option>
						<option value="evalOrgUrl" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='evalOrgUrl'}">selected</c:if> >1.0.4 加载基地评估html(evalOrgUrl)post</option>
						<option value="evalOrgFile" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='evalOrgFile'}">selected</c:if> >1.0.5 加载基地评估file(evalOrgFile)post</option>
						<option value="downFile" method="post" isForm="Y" needDataType="N" <c:if test="${param.reqCode=='downFile'}">selected</c:if> >1.0.6 下载基地评估file(downFile)post</option>
						<option value="addFile" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='addFile'}">selected</c:if> >1.0.7 保存基地评估file(addFile)post</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			roleId:<input name="roleId" type="text" value="${roleId}">
			<br/>
			evalYear:<input name="evalYear" type="text" value="${evalYear}">
			orgFlow:<input name="orgFlow" type="text" value="${orgFlow}">
			<br/>
			cfgFlow:<input name="cfgFlow" type="text" value="${cfgFlow}">
			<input type="submit" value="记住" class="search">
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" ></textarea>
	</div>
	<div id="reqParamDataTypeDiv"  style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px;display: none;">
		参数2：<textarea id="reqParamDataType" style="width: 600px; height: 200px;" name="reqParamDataType" wrap="soft"></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=student&userPasswd=123456</textarea>
	</div>
	<div id="index" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="evalOrg" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&evalYear=${evalYear}&orgFlow=${orgFlow}</textarea>
	</div>
	<div id="evalOrgUrl" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&evalYear=${evalYear}&orgFlow=${orgFlow}&cfgFlow=${cfgFlow}</textarea>
	</div>
	<div id="evalOrgFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&evalYear=${evalYear}&orgFlow=${orgFlow}&cfgFlow=${cfgFlow}</textarea>
	</div>
	<div id="downFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&evalYear=${evalYear}&orgFlow=${orgFlow}&cfgFlow=${cfgFlow}</textarea>
	</div>
	<div id="addFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&evalYear=${evalYear}&orgFlow=${orgFlow}&cfgFlow=${cfgFlow}&uploadFile=${uploadFile}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>