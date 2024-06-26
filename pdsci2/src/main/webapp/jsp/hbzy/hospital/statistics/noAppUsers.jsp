<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#month').datepicker({
 	startView: 2, 
 	maxViewMode: 2,
 	minViewMode:1,
	format:'yyyy-mm'
	});
	top.jboxEndLoading();
});
function toPage(page){
	var currentPage="1";
	if(page != undefined){
		currentPage=page;			
	}
	showNoAppUser("${param.orgFlow}","${param.catSpeId}",currentPage);
}
function changeMonth(){
	var currentPage=1;
	var month = $("#month").val();
	var url = "<s:url value='/hbzy/statistic/statisticsNoAppUser'/>?open=${param.open}&month="+month+"&currentPage="+currentPage+"&catSpeId=${param.catSpeId}&orgFlow=${param.orgFlow}";
	jboxPostLoad("othersDiv", url, null, false); 
}
</script>
	<div class="div_table">
		<table border="0" cellpadding="0" cellspacing="0" class="grid" style="border-bottom: 0px;" >
			<tr>
				<th>学生姓名</th>
				<th>专业</th>
				<th>手机号码</th>
			</tr>
			<c:forEach items="${docInfoExtsList }" var="doctorInfo">
				<tr>
					<td>${doctorInfo.sysUser.userName}</td>
					<td>${doctorInfo.speName }</td>
					<td>${doctorInfo.sysUser.userPhone }</td>
				</tr>
			</c:forEach>
			<c:if test="${empty docInfoExtsList}">
				<tr>
					<td colspan="3">无记录</td>
				</tr>
			</c:if>
		</table>
		<div class="page" style="padding-right: 40px;height: 32px;">
			<c:set var="pageView" value="${pdfn:getPageView(docInfoExtsList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
		</div>
	</div>
