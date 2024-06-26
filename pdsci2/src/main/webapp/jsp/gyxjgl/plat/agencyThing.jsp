<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<script type="text/javascript">
	function winTip(){
		//设置定时任务，并创建session对象
		sessionStorage.setItem("tip", setInterval(tip,600000));
		jboxTip("桌面提醒成功");
		//刷新主页面
		setTimeout(function(){
			window.parent.location.reload();
		}, 3000);
	}
	function tip(){
		jboxInfoBasic("您有未办理事项，请及时处理！",260);
	}
	function cancel(){
		//清除定时任务
		clearInterval(sessionStorage.getItem("tip"));
		//清除session对象
		sessionStorage.removeItem("tip");
		jboxTip("撤销成功");
		//刷新主页面
		setTimeout(function(){
			window.parent.location.reload();
		}, 3000);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" style="width:100%;">
			<tr>
				<th style="text-align:left;padding-left:10px;">序号</th>
				<th style="text-align:left;padding-left:10px;">待办事项</th>
				<th style="text-align:left;padding-left:10px;min-width:200px;">操作</th>
			</tr>
			<c:if test="${dataMap.CHANGE_NUM eq '0' && dataMap.GRADE_NUM eq '0' && dataMap.DOCTOR_NUM eq '0'}">
				<tr><td colspan="3">暂无待办事项</td></tr>
			</c:if>
			<c:set var="inx" value="0"/>
			<c:if test="${dataMap.CHANGE_NUM gt '0'}">
				<tr>
					<c:set var="inx" value="${inx+1}" />
					<td>${inx}</td>
					<td>学生异动审核</td>
					<td style="min-width:200px;">
						<a href="<s:url value='/gyxjgl/change/apply/findChangeInfo'/>" style="cursor:pointer;color:blue;">办理</a>
						<a onclick="winTip()" style="cursor:pointer;color:blue;">桌面提醒</a>
						<a onclick="cancel()" style="cursor:pointer;color:blue;">撤销</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${dataMap.GRADE_NUM gt '0'}">
				<tr>
					<c:set var="inx" value="${inx+1}" />
					<td>${inx}</td>
					<td>学生成绩审核</td>
					<td style="min-width:200px;">
						<a href="<s:url value='/gyxjgl/secondaryOrg/gradeResultAudit'/>" style="cursor:pointer;color:blue;">办理</a>
						<a onclick="winTip()" style="cursor:pointer;color:blue;">桌面提醒</a>
						<a onclick="cancel()" style="cursor:pointer;color:blue;">撤销</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${dataMap.DOCTOR_NUM gt '0'}">
				<tr>
					<c:set var="inx" value="${inx+1}" />
					<td>${inx}</td>
					<td>导师审核</td>
					<td style="min-width:200px;">
						<a href="<s:url value='/gyxjgl/tutor/tutorAudit?role=xwk'/>" style="cursor:pointer;color:blue;">办理</a>
						<a onclick="winTip()" style="cursor:pointer;color:blue;">桌面提醒</a>
						<a onclick="cancel()" style="cursor:pointer;color:blue;">撤销</a>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>