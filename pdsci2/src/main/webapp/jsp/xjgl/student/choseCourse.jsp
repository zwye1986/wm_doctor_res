<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript">
		function loadWeek(){
			if($('#year').val() == "" || $('#termSeason').val() == ""){
				return;
			}
			var url = "<s:url value='/xjgl/student/course/loadWeek'/>";
			jboxPost(url, $('#myForm').serialize(),
					function(resp){
						document.getElementById("weekNum").options.length=1;
						if(null != resp && resp.length > 0){
							for(var i=1; i<=resp.length; i++){
								document.getElementById("weekNum").options.add(new Option("第"+i+"周",i))
							}
						}
					}, null, false);
		}
		function searchScheduleClass(weekNumber){
			if (!$("#myForm").validationEngine("validate")) {
				return;
			}
			weekNumber = weekNumber == ""?$('#weekNum').val():weekNumber;
			weekNumber = (weekNumber=="" || parseInt(weekNumber) <= 2)?"":weekNumber;
			var url = "<s:url value='/xjgl/student/course/searchScheduleClass'/>?userFlow=${param.userFlow}&classId=${param.classId}&majorId=${param.majorId}&trainTypeId=${param.trainTypeId}&year="+$('#year').val()+"&termSeason="+$('#termSeason').val()+"&weekNumber="+weekNumber;
			jboxLoad("contentDiv", url, true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm" method="post">
			<table class="basic" style="width: 100%;">
				<tr>
					<td>
						<input type="hidden" name="userFlow" value="${param.userFlow}">
						<input type="hidden" name="classId" value="${param.classId}">
						<input type="hidden" name="majorId" value="${param.majorId}">
						<input type="hidden" name="trainTypeId" value="${param.trainTypeId}">
						年份：<input class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});" onchange="loadWeek();" type="text" id="year" name="year" style="width: 120px;">&#12288;&#12288;
						学期：<select class="select validate[required]" id="termSeason" name="termSeason" style="width: 120px;" onchange="loadWeek();">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumTermSeasonList}" var="term">
							<option value="${term.dictId}">${term.dictName}</option>
						</c:forEach>
					</select>&#12288;&#12288;
						周数：<select class="select" id="weekNum" name="weekNum" style="width: 120px;">
						<option value="">请选择</option>
					</select>&#12288;&#12288;
						<input type="button" class="search" value="查&#12288;询" id="search" onclick="searchScheduleClass('');">
						<input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();">&#12288;&#12288;
						<font color="red">注意：绿色方框表示已选课程</font>
					</td>
				</tr>
			</table>
		</form>
		<table id="contentDiv" class="xllist nofix" style="margin-top:20px;">

		</table>
	</div>
</div>
</body>
</html>