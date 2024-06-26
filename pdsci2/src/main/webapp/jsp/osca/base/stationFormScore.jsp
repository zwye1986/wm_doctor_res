<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<style type="text/css">
	.label td{
		width:${100/fn:length(scoreList)}%;height:35px;text-align:center;border:1px solid gray;cursor:pointer;
	}
	.label td.on{background-color:#4195c5;}
</style>
<script type="text/javascript">
	$(function(){
		$(".label td").bind('click',function(){
			$(this).attr("class","on");
			$(this).siblings("td").removeAttr("class");
			var index = $(this).index();
			$(".labelDiv").each(function(i){
				if(i == index){
					$(this).show();
					$(this).siblings(".labelDiv").hide();
				}
			});
			$(".labelDiv1").each(function(i){
				if(i == index){
					$(this).show();
					$(this).siblings(".labelDiv1").hide();
				}
			});
			jboxPostLoad("div"+index,"<s:url value="/osca/base/formScoreDetail"/>",$("#form"+index).serialize(),false);
		});
		$(".label td").eq(0).click();
	});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="margin:20px 0px 10px;">
			<table class="label" width="100%">
				<tr>
					<c:forEach items="${scoreList}" var="info" varStatus="i">
						<td ${i.first?"class='on'":""}>${info.partnerName}<br/>${info.fromName}</td>
					</c:forEach>
					<c:if test="${fn:length(scoreList) eq 0}"><div style="font:18px bold;">考生此考站尚未有考官打分！</div></c:if>
				</tr>
			</table>
		</div>
		<c:forEach items="${scoreList}" var="info" varStatus="i">
			<div id="div${i.index}" class="labelDiv">
				<form id="form${i.index}" method="post">
					<input type="hidden" name="formId" value="form${i.index}">
					<input type="hidden" name="scoreFlow" value="${info.scoreFlow}">
				</form>
			</div>
			<div class="labelDiv1">
				<table width="100%">
					<tr>
						<th style="text-align: center;width: 40%;">评分人签名</th>
						<td style="text-align: left;width: 60%;">
							<c:if test="${not empty info.siginImage}">
								<img src="${sysCfgMap['upload_base_url']}/${info.siginImage}" width="96" height="50"/>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
</div>
</body>	
</html>