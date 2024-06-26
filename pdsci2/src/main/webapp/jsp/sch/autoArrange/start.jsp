<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<style type="text/css">
		.errorMsg{
			width: 100%;
			min-height: 400px;
			background-position:bottom;
			text-align: center;
			vertical-align: middle;
			padding-top: 190px;
		}
		.errorMsg2{
			min-height: 400px;
			text-align: center;
			vertical-align: middle;
		}
		span{
			margin-top: 60px;
			font-size: 20px;
			color: #000;
		}
	</style>
	<script>
		function showMsg()
		{
			$("#errorMsg").show();
			$("#errorMsg2").hide();
		}
		function startAutoArrange()
		{
			var url = "<s:url value='/sch/autoArrange/checkStartTime'/>?sessionNumber=${param.sessionNumber}";
			jboxPost(url,null,function(resp){
				if(resp == '1'){
					startArrange();
				}else if(resp == '0'){
					var url2 = "<s:url value='/sch/autoArrange/setStartTime'/>?sessionNumber=${param.sessionNumber}";
					jboxOpen(url2, "设置开始时间",330,200);
				}else{
					jboxTip(resp);
				}
			},null,false);
		}
		function startArrange()
		{
			setTimeout(function(){
				jboxStartLoading();
				var url2 = "<s:url value='/sch/autoArrange/startAutoArrange'/>?sessionNumber=${param.sessionNumber}";
				jboxPostNoLoad(url2,null,function(resp){
					jboxEndLoading();
					if(resp == '排班成功！'){
						$("#startAuto").attr("disabled","disabled");
						var url ="<s:url value='/sch/autoArrange/arrangeResult?sessionNumber=${param.sessionNumber}'/>";
						jboxLoad("resultDiv",url,true);
					}else{

						$("#startAuto").attrAttr("disabled");
					}
				},null,true);
			},500);
		}
	</script>
</head>
<body>
		<div class="errorMsg" id="errorMsg"<c:if test="${count > 0}">style="display: none"</c:if>>
			<p><span>校验成功！</span><span id="startDate"><c:if test="${not empty cfg.startDate}">排班开始时间：${cfg.startDate}</c:if></span></p>
			<input type="button" class="search" style="margin-top: 5px;" id="startAuto" onclick="startAutoArrange()" value="开始排班">
		</div>
		<div class="errorMsg2" id="errorMsg2" <c:if test="${count<=0 or empty count}">style="display: none" </c:if>>
			<c:if test="${count > 0}">
				<p style="margin-top:190px;">已有${count}名学员存在轮转记录，自动排班将删除学员原有轮转记录。点击此处
					<span style="color: blue;cursor: pointer;" onclick="showMsg();">开始排班</span></p>
			</c:if>
		</div>
</body>
</html>