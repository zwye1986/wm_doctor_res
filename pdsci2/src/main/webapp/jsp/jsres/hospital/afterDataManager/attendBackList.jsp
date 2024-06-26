<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){

	});

	function backAttend(attendanceFlow)
	{
		if(!attendanceFlow){
			jboxTip("请选择轮需要退回的考勤记录！");
			return false;
		}
		var url = "<s:url value='/jsres/afterDataManager/backAttend'/>?attendanceFlow="+attendanceFlow;

		jboxConfirm("确认退回考勤记录？",function(){
			jboxPost(url, null, function (resp) {
				toPage();
			}, null, true);
		});
	}
</script>
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>考勤日期</th>
				<th>出勤状态</th>
				<th>操作</th>
			</tr>

			<c:forEach items="${list}" var="bean">
				<tr>
					<td>${bean.attendDate}</td>
					<td>${bean.attendStatusName}</td>
					<td>
						<a class="btn"  onclick="backAttend('${bean.attendanceFlow}');" style="margin-top: 5px;margin-bottom: 5px;">退回</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="10" >${empty msg ? '无考勤记录！':msg}</td>
				</tr>
			</c:if>
		</table>
	</div>
