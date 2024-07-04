<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广医大研究生系统APP测试程序</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
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
		var url = "<s:url value='/res/gydxj'/>/"+action+"?_method="+method;
		var formData = $("#reqParam").val();
		$.ajax({
			type : method,
			url : url,
			data : formData,
			cache : false,
			beforeSend : function(){
				jboxStartLoading();
			},
			success : function(resp) {
				jboxEndLoading();
				jboxTip("测试成功");
				$("#rsp").text(JSON.stringify(resp,null,4));
			},
			error : function() {
				jboxEndLoading();
				jboxTip("操作失败,请刷新页面后重试");
			},
			complete : function(){
				jboxEndLoading();
			},
			statusCode: {405: function() {
				jboxTip("交易方法不正确");
			  }
			}
		});

	}
	function change(divid) {
		$("#reqParam").val($("#" + divid + "  textarea").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="${ctxPath}/res/gydxj/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);" style="min-width:400px;">
						<option value="" method="">请选择</option>
						<option value="login" method="post" <c:if test="${param.reqCode=='login'}">selected</c:if> >1.0.0 登录 login</option>
						<option value="noticeList" method="post" <c:if test="${param.reqCode=='noticeList'}">selected</c:if> >1.0.1 通知列表 noticeList</option>
						<option value="noticeView" method="post" <c:if test="${param.reqCode=='noticeView'}">selected</c:if> >1.0.2 通知详情 noticeView</option>
						<option value="changePwd" method="post" <c:if test="${param.reqCode=='changePwd'}">selected</c:if> >1.0.3 修改密码 changePwd</option>
						<option value="version" method="get" <c:if test="${param.reqCode=='version'}">selected</c:if> >1.0.5 最新版本号 version</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gydxj/student/test'/>">学生端测试工具</a>
					<a href="<s:url value='/res/gydxj/teacher/test'/>">教师端测试工具</a>
					<a href="<s:url value='/res/gydxj/admin/test'/>">管理员端测试工具</a>
					<script>
						function reload(){
							jboxGet('<s:url value="/common/reLoadCfg"/>',null,function(resp){
								if(resp=='Y'){
									jboxTip('刷新成功');
								}
							},null,false);
						}
					</script>
					<a href="javascript:reload();">刷新配置</a>
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" ></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=&userPasswd=</textarea>
	</div>
	<div id="noticeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&roleId=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="noticeView" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">infoFlow=</textarea>
	</div>
	<div id="changePwd" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&oldPwd=&newPwd=</textarea>
	</div>
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
</body>
</html>