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
	</jsp:include>
	<style type="text/css">
		#myForm input[type='text']{width:133px;}
	</style>
	<script type="text/javascript">
		function toPage(currentPage){
			$("#currentPage").val(currentPage);
			$("#myForm").submit();
		}
		function editInfo(recordFlow){
			var url = "<s:url value='/xjgl/term/manage/settingAdd?sessionNumber=${param.sessionNumber}&recordFlow='/>"+recordFlow;
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			jboxMessager(iframe,recordFlow==""?"新增":"编辑", 460,200);
		}
		function delInfo(recordFlow){
			jboxConfirm("确认删除吗？" , function(){
				var url = "<s:url value='/xjgl/term/manage/delSetting?recordFlow='/>"+recordFlow;
				jboxPost(url , null , function(){
					toPage('1');
				} , null , true);
			});
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm" action="<s:url value="/xjgl/term/manage/scheduleSetting?sessionNumber=${param.sessionNumber}"/>" method="post">
			<input type="hidden" name="currentPage" id="currentPage">
			<input type="button"  value="查&#12288;询" class="search" onclick="toPage('1');"/>
			<input type="button"  value="新&#12288;增" class="search" onclick="editInfo('');"/>
		</form>
		<table class="basic" style="margin-top:10px;width:100%;">
			<tr>
				<th style="text-align:center;padding:0px;">年份</th>
				<th style="text-align:center;padding:0px;">开始时间</th>
				<th style="text-align:center;padding:0px;">结束时间</th>
				<th style="text-align:center;padding:0px;">备注</th>
				<th style="text-align:center;padding:0px;">操作</th>
			</tr>
			<c:forEach items="${limitList}" var="limit">
				<tr>
					<td style="text-align:center;padding:0px;">${limit.sessionNumber}</td>
					<td style="text-align:center;padding:0px;">${limit.beginTime}</td>
					<td style="text-align:center;padding:0px;">${limit.endTime}</td>
					<td style="text-align:center;padding:0px;">${limit.memo}</td>
					<td style="text-align:center;padding:0px;"><a style="cursor: pointer; color: blue;" onclick="editInfo('${limit.recordFlow}');">编辑</a>
						<a style="cursor: pointer; color: blue;" onclick="delInfo('${limit.recordFlow}');">删除</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty limitList}">
				<tr>
					<td colspan="5" style="text-align:center;">无记录！</td>
				</tr>
			</c:if>
		</table>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(limitList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>