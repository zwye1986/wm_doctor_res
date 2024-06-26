<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
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
				var scoreMode=$(n).find('[name="scoreMode"]').val();
				var gradeTermId=$(n).find('[name="gradeTermId"]').val();
				var studyWayId=$(n).find('[name="studyWayId"]').val();
				var assessTypeId=$(n).find('[name="assessTypeId"]').val();
				var gradeYear=$(n).find('[name="gradeYear"]').val();
				if(courseGrade == "通过"){
					courseGrade = "Y";
				}else if(courseGrade == "不通过"){
					courseGrade = "N";
				}else if(courseGrade == "缺考"){
					courseGrade = "T";
				}else if(courseGrade != "" && isNaN(courseGrade)){//非数字
					courseGrade = "";
				}
				var data = {
					"recordFlow":recordFlow,
					"pacificGrade":pacificGrade,
					"teamEndGrade":teamEndGrade,
					"courseGrade":courseGrade,
					"studyWayId":studyWayId,
					"assessTypeId":assessTypeId,
					"gradeTermId":gradeTermId,
					"scoreMode":scoreMode,
					"gradeYear":gradeYear
				};
				datas.push(data);
			});
			jboxStartLoading();
			var url = "<s:url value='/xjgl/teachingGroup/saveStudentGrade'/>";
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
				var scoreMode=$(n).find('[name="scoreMode"]').val();
				if(courseGrade == "通过"){
					courseGrade = "Y";
				}else if(courseGrade == "不通过"){
					courseGrade = "N";
				}else if(courseGrade == "缺考"){
					courseGrade = "T";
				}else if(courseGrade != "" && isNaN(courseGrade)){//非数字
					courseGrade = "";
				}
				var data = {
					"recordFlow":recordFlow,
					"pacificGrade":pacificGrade,
					"teamEndGrade":teamEndGrade,
					"courseGrade":courseGrade,
					"scoreMode":scoreMode,
					"roleFlag":"teachingGroup",
					"auditStatusId":"",//审核不通过，可通过再次提交成待审核初始状态
					"auditStatusName":"",
					"cddwAuditStatusId":"",//审核不通过，可通过再次提交成待审核初始状态
					"cddwAuditStatusName":"",
					"submitFlag":"Y"
				};
				if($.trim(courseGrade) == ""){
					allGradeFlag = false;
					return;
				}
				datas.push(data);
			});
			if(!allGradeFlag){
				jboxTip("请完善所有选课学生成绩！");
				return;
			}
			jboxStartLoading();
			var url = "<s:url value='/xjgl/teachingGroup/saveStudentGrade'/>";
			jboxPostJson(url,JSON.stringify(datas),function(){
				window.parent.frames['mainIframe'].location.reload();
				jboxClose();
			},null,true);
		}
		function changeAll(obj){
			var result=obj.value;
			if(result){
				$("#appendTbody").find("select[name='gradeTermId']").each(function(){
					if(!this.value){
						$("[value='"+result+"']",this).attr("selected","selected");
					}
				});
			}
		}
		function bindTerm(value){
			$(".gradeTermId").val(value);
		}
		function bindAssess(value){
			$(".assessTypeId").val(value);
		}
		function bindStudy(value){
			$(".studyWayId").val(value);
		}
		function bindGradeYear(value){
			$(".gradeYear").val(value);
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
						&#12288;<br/>&#12288;
						获得学期：<label><select style="width:80px;" onchange="bindTerm(this.value);">
								<option value=""></option>
								<c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
									<option value="${recruitSeason.dictId}">${recruitSeason.dictName}</option>
								</c:forEach>
							</select>
						</label>
						考核方式：<label><select style="width:80px;" onchange="bindAssess(this.value)">
							<option value=""></option>
							<c:forEach items="${dictTypeEnumXjEvaluationModeList}" var="dict">
								<option value="${dict.dictId}">${dict.dictName }</option>
							</c:forEach>
						</select></label>
						修读方式：<label><select style="width:80px;" onchange="bindStudy(this.value)">
							<option value=""></option>
							<c:forEach items="${dictTypeEnumXjStudyWayList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${dict.dictId eq info.STUDY_WAY_ID}">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select></label>
						获得学年：<label>
							<input type="text" style="width:80px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" onblur="bindGradeYear(this.value)">
						</label>
					</td>
				</tr>
			</table>
			<table class="xllist" style="width:100%;">
				<tr>
					<th style="width:80px;">选课学年</th>
					<th style="width:80px;">姓名</th>
					<th style="width:100px;">学号</th>
					<th style="width:140px;">课程名称</th>
					<th style="width:100px;">课程类型</th>
					<th style="width:50px;">学时</th>
					<th style="width:50px;">学分</th>
					<th style="width:80px;">修读方式</th>
					<th style="width:80px;">考核方式</th>
					<th style="width:80px;">成绩获得方式</th>
					<th style="width:85px;">考勤成绩</th>
					<th style="width:85px;">考核成绩</th>
					<th style="width:80px;">获得学年</th>
					<th style="width:80px;">成绩</th>
					<th style="width:80px;">获得学期</th>
				</tr>
				<tbody id="dataBody">
					<c:forEach items="${dataList}" var="info" varStatus="i">
						<tr>
							<td>${info.STUDENT_PERIOD}</td>
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
							<td>
								<select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumXjStudyWayList}" var="dict">
										<option value="${dict.dictId}" <c:if test="${dict.dictId eq info.STUDY_WAY_ID}">selected="selected"</c:if>>${dict.dictName }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumXjEvaluationModeList}" var="dict">
										<option value="${dict.dictId}" <c:if test="${dict.dictId eq info.ASSESS_TYPE_ID}">selected="selected"</c:if>>${dict.dictName }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select style="width:  80%;text-align: center;" name="scoreMode">
									<option value=""/>
									<option value="R"<c:if test="${info.SCORE_MODE eq 'R'}">selected="selected"</c:if>>补考</option>
								</select>
							</td>
							<input type="hidden" name="recordFlow" value="${info.RECORD_FLOW}"/>
							<td><input name="pacificGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.PACIFIC_GRADE}"/></td>
							<td><input name="teamEndGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.TEAM_END_GRADE}"/></td>
							<td>
								<input type="text" style="width:70%;" class="gradeYear" name="gradeYear" value="${info.GRADE_YEAR}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});">
							</td>
							<td><input name="courseGrade" type="text" style="width:70%;text-align:center;" value="${info.COURSE_GRADE eq 'Y'?'通过':''}${info.COURSE_GRADE eq 'N'?'不通过':''}
							${info.COURSE_GRADE eq 'T'?'缺考':''}${info.COURSE_GRADE ne 'Y' && info.COURSE_GRADE ne 'N' && info.COURSE_GRADE ne 'T'?info.COURSE_GRADE:''}"/></td>
							<td>
								<select style="width:  80%;text-align: center;" class="gradeTermId" name="gradeTermId"  onchange="changeAll(this);">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
										<option value="${recruitSeason.dictId}" <c:if test="${info.GRADE_TERM_ID==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<c:if test="${fn:length(dataList) eq 0}"><tr><td colspan="99">无记录！</td></tr></c:if>
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