<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
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
	<script type="text/javascript">
		$(function(){
			<c:forEach items="${resResponsibleteacherDoctorList}" var="user">
			$('#${user.responsibleteacherFlow}').attr("checked","checked");
			</c:forEach>
		})
		function search(){
			$("#searchForm").submit();
		}
		function save(obj){
			var doctorFlow = $("#doctorFlow").val();
			var teacherFlow = $(obj).val();
			var teacherName = $(obj).attr("value2");
			var recordStatus = '';
			if($(obj).attr("checked")){
				recordStatus ='Y';
			}else{
				recordStatus ='N';
			}
			jboxPost('<s:url value="/res/responsibleTeacher/saveResponsibleTeacher"/>',"doctorFlow="+doctorFlow+
					"&responsibleteacherFlow="+teacherFlow+"&responsibleteacherName="+
					teacherName+"&recordStatus="+recordStatus,function(resp){
				if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
					search();
				}
			},null,false);
		}
		function searchUser(obj){
			var userName = $(obj).val();
			if(userName==""){
				$(obj).parent().parent().find("label").show();
			}else{
				$(obj).parent().parent().find("label").each(function(){
					var name = $(this).children().eq(0).attr("value2");
					var array = userName.split();
					for(var i=0;i<array.length;i++){
						if(name.indexOf(array[i])>-1){
							$(this).show();
						}else{
							$(this).hide();
						}
					}
				})
			}
		}
		function closeThis(){
			window.parent.toPage($("#currentPage").val());
			jboxClose();
			// window.parent.frames['mainIframe'].window.toPage($("#currentPage").val());
		}
	</script>
</head>
<body>
<form id="searchForm" action="<s:url value="/res/responsibleTeacher/responsibleTeacherCfg"/>">
	<input type="hidden" name="doctorFlow" id="doctorFlow" value="${doctorFlow}">
	<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}">
</form>
<div class="search_table"  style="overflow: auto;height: 350px">
	<table class="grid">
		<tr>
			<td>
				<div style="text-align: left;margin: 5px">
					<input type="text" class="input" id="fuzzyQuery" placeholder="姓名模糊检索" onkeyup="searchUser(this);">
				</div>
				<div style="position: relative">
					<c:forEach items="${userList}" var="user">
						<div style="width: 23.5%;float: left;text-align: left;padding-left: 5px;">
							<label title="${user.userCode}">
								<input type="checkbox" id="${user.userFlow}" value="${user.userFlow}"
									   value2="${user.userName}" onchange="save(this)">${user.userName}</label>
						</div>
					</c:forEach>
				</div>
			</td>
		</tr>
		<c:if test="${empty userList}">
			<tr><td colspan="99" style="text-align: center">暂无数据</td></tr>
		</c:if>
	</table>
</div>
<div style="margin: 15px;text-align: center">
	<button class="btn_green"  onclick="closeThis();">关&#12288;闭</button>
</div>

</body>
</html>