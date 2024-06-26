<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function save(){
			if (!$("#myForm").validationEngine("validate")) {
				return;
			}
			var datas =[];
			var trs = $('#dataBody').children();
			$.each(trs , function(i , n){
				var recordFlow= $(n).find('[name="recordFlow"]').val();
				var pacificGrade=$(n).find('[name="pacificGrade"]').val();
				var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
				var courseGrade=$(n).find('[name="courseGrade"]').val();
				var flag = true;
				<c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
				if('${dict.dictName}'==courseGrade){
					courseGrade = "${dict.dictId}";
					flag=false;
				}
				</c:forEach>
				if(flag && courseGrade != "" && isNaN(courseGrade)){//非数字
					courseGrade = "";
				}
				var data = {
					"recordFlow":recordFlow,
					"pacificGrade":pacificGrade,
					"teamEndGrade":teamEndGrade,
					"courseGrade":courseGrade
				};
				datas.push(data);
			});
			jboxStartLoading();
			var url = "<s:url value='/gyxjgl/teachingGroup/saveStudentGrade'/>";
			jboxPostJson(url,JSON.stringify(datas),function(){
				window.parent.frames['mainIframe'].location.reload();
				jboxClose();
			},null,true);
		}
		function submitOpt(){
			if (!$("#myForm").validationEngine("validate")) {
				return;
			}
			var allGradeFlag = true;
			var datas =[];
			var trs = $('#dataBody').children();
			$.each(trs , function(i , n){
				var recordFlow= $(n).find('[name="recordFlow"]').val();
				var pacificGrade=$(n).find('[name="pacificGrade"]').val();
				var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
				var courseGrade=$(n).find('[name="courseGrade"]').val();
				var flag = true;
				<c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
					if('${dict.dictName}'==courseGrade){
						courseGrade = "${dict.dictId}";
						flag=false;
					}
				</c:forEach>
				if(flag && courseGrade != "" && isNaN(courseGrade)){//非数字
					courseGrade = "";
				}
				var data = {
					"recordFlow":recordFlow,
					"pacificGrade":pacificGrade,
					"teamEndGrade":teamEndGrade,
					"courseGrade":courseGrade,
					"roleFlag":"teachingGroup",
					"auditStatusId":"",//审核不通过，可通过再次提交成待审核初始状态
					"auditStatusName":"",
					"submitFlag":"Y"
				};
				if($.trim(courseGrade) != ""){
					datas.push(data);
				}
//				if($.trim(courseGrade) == ""){
//					allGradeFlag = false;
//					return;
//				}
//				datas.push(data);
			});
//			if(!allGradeFlag){
//				jboxTip("请完善所有选课学生成绩！");
//				return;
//			}
			jboxStartLoading();
			var url = "<s:url value='/gyxjgl/teachingGroup/saveStudentGrade'/>";
			jboxPostJson(url,JSON.stringify(datas),function(){
				window.parent.frames['mainIframe'].location.reload();
				jboxClose();
			},null,true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<table class="basic" style="width: 100%;margin-bottom: 5px;margin-top: 0px;">
				<tr>
					<td>
						&#12288;
						课程名称：<label>${course.courseName}</label>
						&#12288;
						课程代码：<label>${course.courseCode}</label>
						&#12288;
						课程学时：<label>${course.coursePeriod}</label>
						&#12288;
						课程学分：<label>${course.courseCredit}</label>
					</td>
				</tr>
			</table>
			<table class="xllist" style="width:100%;">
				<tr>
					<th style="width:80px;">姓名</th>
					<th style="width:100px;">学号</th>
					<th style="width:140px;">课程名称</th>
					<th style="width:120px;">课程类型</th>
					<th style="width:60px;">学时</th>
					<th style="width:60px;">学分</th>
					<th style="width:90px;">修读方式</th>
					<th style="width:90px;">考核方式</th>
					<th style="width:90px;">平时成绩</th>
					<th style="width:90px;">期末成绩</th>
					<th style="width:80px;">成绩</th>
					<th style="width:90px;">获得学期</th>
				</tr>
				<tbody id="dataBody">
					<c:forEach items="${dataList}" var="info" varStatus="i">
						<tr>
							<td>${info.USER_NAME}</td>
							<td>${info.SID}</td>
							<td>${info.COURSE_NAME}</td>
							<td>${info.COURSE_TYPE_NAME}</td>
							<td>
								<c:if test="${not empty info.COURSE_PERIOD}">
									<c:choose>
										<c:when test="${fn:length(info.COURSE_PERIOD)==1}">
											&ensp;&ensp;${info.COURSE_PERIOD}
										</c:when>
										<c:when test="${fn:length(info.COURSE_PERIOD)==2}">
											&ensp;${info.COURSE_PERIOD}
										</c:when>
										<c:otherwise>${info.COURSE_PERIOD}</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>
								<c:if test="${not empty info.COURSE_CREDIT}">
									<c:choose>
										<c:when test="${fn:contains(info.COURSE_CREDIT,'.')}">
											<c:choose>
												<c:when test="${fn:length(fn:split(info.COURSE_CREDIT,'.')[0])==1}">
													&ensp;&ensp;${info.COURSE_CREDIT}
												</c:when>
												<c:when test="${fn:length(fn:split(info.COURSE_CREDIT,'.')[0])==2}">
													&ensp;${info.COURSE_CREDIT}
												</c:when>
												<c:otherwise>${info.COURSE_CREDIT}</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${fn:length(info.COURSE_CREDIT)==1}">
													&ensp;&ensp;${info.COURSE_CREDIT}&nbsp;&nbsp;
												</c:when>
												<c:when test="${fn:length(info.COURSE_CREDIT)==2}">
													&ensp;${info.COURSE_CREDIT}&nbsp;&nbsp;
												</c:when>
												<c:otherwise>${info.COURSE_CREDIT}&nbsp;&nbsp;</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>${info.STUDY_WAY_NAME}</td>
							<td>${info.ASSESS_TYPE_NAME}</td>
							<input type="hidden" name="recordFlow" value="${info.RECORD_FLOW}"/>
							<td><input name="pacificGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.PACIFIC_GRADE}"/></td>
							<td><input name="teamEndGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.TEAM_END_GRADE}"/></td>
							<c:set var="gradeId" value="GyXjIsPassed.${info.COURSE_GRADE}" />
							<td><input name="courseGrade" type="text" style="width:70%;text-align:center;" value="${empty applicationScope.sysDictIdMap[gradeId]?info.COURSE_GRADE:applicationScope.sysDictIdMap[gradeId]}"/></td>
							<td>${info.GRADE_TERM_NAME}</td>
						</tr>
					</c:forEach>
				</tbody>
				<c:if test="${fn:length(dataList) eq 0}"><tr><td colspan="14">无记录！</td></tr></c:if>
			</table>
			<div style="text-align: center;margin-top:20px;">
				<input type="button" class="search" onclick="save()" value="保&#12288;存"/>
				<input type="button" class="search" onclick="submitOpt()" value="提&#12288;交"/>
				<input type="button" class="search" value="取&#12288;消" onclick="jboxClose();"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>