<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>南医大研究生APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/nydyjs'/>/"+action+"?_method="+method;
		var formData = $("#reqParam").val();
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var dataType = $("#dataType option:selected").val();
		if(needDataType=='Y'){
			formData = formData+"&dataType="+dataType;
			formData = formData+"&"+$("#reqParamDataType").val();
		}else{
			
		}
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
	<form name="appForm" action="${ctxPath}/res/nydyjs/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >3.1.1登录(login)post</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >3.1.2查询最新版本号(version)get</option>
						<option value="index" method="get" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >3.1.3带教老师首页(index)get</option>
						<option value="studentList" method="post" needDataType="N" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >3.10学员列表(studentList)post</option>
						<option value="dataStudentList" method="get" needDataType="N" <c:if test="${param.reqCode=='dataStudentList'}">selected</c:if> >3.10.1数据审核学员列表(dataStudentList)get</option>
						<option value="appealStudentList" method="get" needDataType="N" <c:if test="${param.reqCode=='appealStudentList'}">selected</c:if> >3.10.2申诉查询学员列表(appealStudentList)get</option>
						<option value="afterSummeryStudentList" method="get" needDataType="N" <c:if test="${param.reqCode=='afterSummeryStudentList'}">selected</c:if> >3.10.3出科小结学员列表(afterSummeryStudentList)get</option>
						<option value="afterEvaStudentList" method="get" needDataType="N" <c:if test="${param.reqCode=='afterEvaStudentList'}">selected</c:if> >3.10.4出科考核学员列表(afterEvaStudentList)get</option>
						<option value="fiveDataNoAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='fiveDataNoAudit'}">selected</c:if> >3.10.5【病历】或【病种】或【操作】或【手术】或【活动】待审核数量(fiveDataNoAudit)get</option>
						<option value="caseRegistryList" method="get" needDataType="N" <c:if test="${param.reqCode=='caseRegistryList'}">selected</c:if> >3.10.6大病历列表(caseRegistryList)get</option>
						<option value="diseaseRegistryList" method="get" needDataType="N" <c:if test="${param.reqCode=='diseaseRegistryList'}">selected</c:if> >3.10.7病种列表(diseaseRegistryList)get</option>
						<option value="operateSkillList" method="get" needDataType="N" <c:if test="${param.reqCode=='operateSkillList'}">selected</c:if> >3.10.8操作列表(operateSkillList)get</option>
						<option value="possSkillList" method="get" needDataType="N" <c:if test="${param.reqCode=='possSkillList'}">selected</c:if> >3.10.9手术列表(possSkillList)get</option>
						<option value="activityList" method="get" needDataType="N" <c:if test="${param.reqCode=='activityList'}">selected</c:if> >3.10.10活动列表(activityList)get</option>
						<option value="dataAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='dataAudit'}">selected</c:if> >3.10.11单个【病历】或【病种】或【操作】或【手术】审核(dataAudit)post</option>
						<option value="dataBatchAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='dataBatchAudit'}">selected</c:if> >3.10.12 批量【病历】或【病种】或【操作】或【手术】审核(dataBatchAudit)post</option>
						<option value="activityAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='activityAudit'}">selected</c:if> >3.10.13单个【活动】审核(activityAudit)post</option>
						<option value="activityBatchAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='activityBatchAudit'}">selected</c:if> >3.10.14 批量【活动】审核(activityBatchAudit)post</option>
						<option value="appealDataList" method="get" needDataType="N" <c:if test="${param.reqCode=='appealDataList'}">selected</c:if> >3.10.15申诉【病种】、【操作】、【手术】数据列表(appealDataList)get  (dataType的值为1病种2操作3手术)</option>
						<option value="appealAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='appealAudit'}">selected</c:if> >3.10.16单个申诉【病种】、【操作】、【手术】审核(appealAudit)post</option>
						<option value="appealBatchAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='appealBatchAudit'}">selected</c:if> >3.10.17批量申诉【病种】、【操作】、【手术】审核(appealBatchAudit)post (dataType的值为1病种2操作3手术)</option>
						<option value="afterDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='afterDetail'}">selected</c:if> >3.10.18出科小结详情(afterDetail)post</option>
						<option value="afterAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='afterAudit'}">selected</c:if> >3.10.19审核【出科小结】(afterAudit)post</option>
						<option value="afterEvaDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='afterEvaDetail'}">selected</c:if> >3.10.20出科考核表(afterEvaDetail)post</option>
						<option value="saveEva" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEva'}">selected</c:if> >3.10.21审核【出科考核表】(saveEva)post</option>
						<option value="aboutUs" method="get" needDataType="N" <c:if test="${param.reqCode=='aboutUs'}">selected</c:if> >3.19获取关于我们图片(aboutUs)get</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>

		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			roleId:<input name="roleId" type="text" value="${roleId}">
			<br>
			cySecId:<input name="cySecId" type="text" value="${cySecId}">
			docFlow:<input name="docFlow" type="text" value="${docFlow}">
			<br>
			hosSecId:<input name="hosSecId" type="text" value="${hosSecId}">
			hosCySecId:<input name="hosCySecId" type="text" value="${hosCySecId}">
			<br>
			funcTypeId:<input name="funcTypeId" type="text" value="${funcTypeId}">
			funcFlow:<input name="funcFlow" type="text" value="${funcFlow}">
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&studentName=${studentName}&sessionNumber=${sessionNumber}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="dataStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="appealStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="afterSummeryStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="afterEvaStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="fiveDataNoAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}</textarea>
	</div>
	<div id="caseRegistryList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="dataAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recId=${recId}&docFlow=${docFlow}&fromId=${fromId}&reviewIndex=${reviewIndex}&content=${content}&classId=${classId}&checkStatus=${checkStatus}</textarea>
	</div>
	<div id="diseaseRegistryList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="operateSkillList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="possSkillList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="activityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="dataBatchAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&type=${type}</textarea>
	</div>
	<div id="activityAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recId=${recId}&content=&docFlow=${docFlow}&checkStatus=${checkStatus}</textarea>
	</div>
	<div id="activityBatchAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&docFlow=${docFlow}</textarea>
	</div>
	<div id="appealDataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&dataType=${dataType}&hosSecId=${hosSecId}&docFlow=${docFlow}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="appealAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recId=${recId}&content=${content}&checkStatus=${checkStatus}&appealNum=${appealNum}</textarea>
	</div>
	<div id="appealBatchAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&dataType=${dataType}&docFlow=${docFlow}</textarea>
	</div>
	<div id="afterDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recId=${recId}</textarea>
	</div>
	<div id="afterAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recId=${recId}&content=${content}&headId=${headId}&cySecId=${cySecId}&hosSecId=${hosSecId}&checkStatus=${checkStatus}</textarea>
	</div>
	<div id="afterEvaDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cySecId=${cySecId}&docFlow=${docFlow}&hosCySecId=${hosCySecId}&afterEvaId=${afterEvaId}</textarea>
	</div>
	<div id="saveEva" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&workId=${workId}&roleId=${roleId}&cySecId=${cySecId}&hosCySecId=${hosCySecId}&headId=${headId}&schDeptName=${schDeptName}&docFlow=${docFlow}&afterEvaId=${afterEvaId}&LJXY=${LJXY}&GZZRX=${GZZRX}&YLCCSG=${YLCCSG}&YHGTNL=${YHGTNL}&JBLL=${JBLL}&JBJN=${JBJN}&JBCZ=${JBCZ}&TGJC=${TGJC}&ZYLL=${ZYLL}&CLCJBR=${CLCJBR}&CLWZBR=${CLWZBR}&SYJC=${SYJC}&LCSW=${LCSW}&RZQQ=${RZQQ}&GLBZ=${GLBZ}&GLBL=${GLBL}&CZSS=${CZSS}&CCHD=${CCHD}&LLKS=${LLKS}&JNKS=${JNKS}&TotalScore=${TotalScore}&YDJF=${YDJF}&RZJF=${RZJF}&BZJF=${BZJF}&BLJF=${BLJF}&CZJF=${CZJF}</textarea>
	</div>
	<div id="aboutUs" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>