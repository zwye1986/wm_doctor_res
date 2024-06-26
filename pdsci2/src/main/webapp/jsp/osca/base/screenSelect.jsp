<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<style type="text/css">
	.xllistNew th{font-size:30px;line-height:50px;border: 0px;background: none;color: white;font-weight: normal;}
	.xllistNew td{font-size:20px;line-height:38px;border: 0px;background: none;color: white;}
</style>
<script type="text/javascript">
	function go(){
		if($("[type='checkbox']:checked").length>0){
			$('#searchForm').submit();
		}else {
			jboxTip("请选择考核")
		}
	}
</script>
</head>
<body id="initCont" background="<s:url value='/jsp/inx/osce/images/${applicationScope.sysCfgMap["osce_screenBg"]}.png'/>">
<div class="mainright">
	<div class="content" style="background: none;">
		<div style="text-align: center;margin-top: 30px;margin-bottom: 20px;">
			<label style="font-size: 34px;color: white;">${orgName}临床技能考核${type eq 'wait'?'房间动态':'考生进度'}</label>
		</div>
		<div style="text-align: center;width:100%;height:90%;">
			<div style="text-align: center;background-color: white;width: 60%;padding: 1%;margin:auto;">
			<form id="searchForm" action="<s:url value='/osca/base/screenDisplay2/${type}'/>" method="post">
			<table class="xllist" style="width: 100%;margin:auto">
				<tr>
					<th style="font-size: 30px;" colspan="3">屏显设置（请选择本屏幕展示的考核信息）</th>
				</tr>
				<tr>
					<th style="font-size: 20px;">考核专业名称</th>
					<th style="font-size: 20px;">考核时间段</th>
					<th style="font-size: 20px;">是否展示</th>
				</tr>
				<c:forEach items="${dataList}" var="data">
				<tr>
					<td style="font-size: 20px;">${data.CLINICAL_NAME}</td>
					<td style="font-size: 20px;">${data.EXAM_START_TIME}~${data.EXAM_END_TIME}</td>
					<td style="font-size: 20px;">
						<input type="checkbox" name="clinicalFlow" value="${data.CLINICAL_FLOW}">
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty dataList}">
				<tr>
					<th colspan="10">今日无考核</th>
				</tr>
				</c:if>
			</table>
			</form>
				<c:if test="${not empty dataList}">
					<input type="button" value="确&#12288;定" onclick="go()" class="search" style="margin-top: 25px;">
				</c:if>
			</div>
		</div>
	</div>
<div style="position:absolute; bottom:10px;width: 100%;">
	<div style="text-align:center;"><label style="font-size: 18px;color: white;">技术支持：南京品德网络信息技术有限公司</label></div>
</div>
</div>
</body>
</html>