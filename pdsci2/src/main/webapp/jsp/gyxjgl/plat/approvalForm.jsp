<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function bindRecord(obj){
			var recordFlow=$(obj).find("option:selected").attr("recordFlow");
			$(obj).parent().find(".recordFlow").val(recordFlow);
		}
		function bindRecord1(obj){
			var recordFlow=$(obj).find("option:selected").attr("recordFlow");
			$(obj).parent().find(".recordFlowY").val(recordFlow);
		}


		function save(){
			var jsonArry = [];
			// $(".recordFlow").each(function(i){
			// 	var json = {
			// 		"recordFlow" : $(this).val(),
			// 		"teacherPost" : $(".teacherPost:eq("+i+")").val(),
			// 		"contentDesc" : $(".contentDesc:eq("+i+")").val()};
			// 	jsonArry.push(json);
			// });
			$('#test').find(".recordFlowY").each(function(i){//实验课
				var json = {
					"recordFlow" : $(this).val(),
					"teacherName" : $(".teacherNameY:eq("+i+")").val(),
					"isTestTeacher" : $(".isTestTeacherY:eq("+i+")").val(),
					"schoolHours" : $(".schoolHoursY:eq("+i+")").val(),
					"teacherPost" : $(".teacherPostY:eq("+i+")").val(),
					"contentDesc" : $(".contentDescY:eq("+i+")").val(),
					"isApprovalTeacher" : $(".isApprovalTeacher:eq("+i+")").val()
				};
				jsonArry.push(json);
			});
			$('#test1').find(".recordFlow").each(function(i){//理论课
				var json = {
					"recordFlow" : $(this).val(),
					"teacherName" : $(".teacherName:eq("+i+")").val(),
					"isTestTeacher" : $(".isTestTeacher:eq("+i+")").val(),
					"schoolHours" : $(".schoolHours:eq("+i+")").val(),
					"teacherPost" : $(".teacherPost:eq("+i+")").val(),
					"contentDesc" : $(".contentDesc:eq("+i+")").val(),
					"isApprovalTeacher" : $(".isApprovalTeacher:eq("+i+")").val()
				};
				jsonArry.push(json);
			});
			$("#jsonArry").val(JSON.stringify(jsonArry));
			var url="<s:url value='/gyxjgl/course/manage/saveApprovalForm'/>";
			jboxPost(url, $("#myForm").serialize(), function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					jboxClose();
				}
			},null,true);
		}
		function addAnswer(){
			$('#test').append($("#moban tr:eq(0)").clone());
		}
		function addAnswer1(){
			$('#test1').append($("#moban1 tr:eq(0)").clone());
		}
		function delAnswer(obj){
			$(obj).parent().parent().remove();
		}
		function down(){
			var printFlag=$("input[name='printFlag']").val();
			jboxTip("下载中,请稍等...");
			var url = '<s:url value="/gyxjgl/course/manage/printApproval"/>?courseFlow=${param.courseFlow}&printFlag='+printFlag;
			window.location.href = url;
		}

		function printapprovalForm(){
			var url = "<s:url value="/gyxjgl/course/manage/printapprovalForm"/>?courseFlow=${param.courseFlow}";
			jboxStartLoading();
			jboxPost(url, null, function(data) {
				$("#printDiv").html(data);
				setTimeout(function(){
					var newstr = $("#printDiv").html();
					var oldstr = document.body.innerHTML;
					document.body.innerHTML =newstr;
					window.print();
					document.body.innerHTML = oldstr;
					jboxEndLoading();
					return false;
				},3000);
			},null,false);

		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="myForm" method="post">
			<input type="hidden" name="courseFlow" value="${param.courseFlow}">
			<input type="hidden" name="jsonArry" id="jsonArry">
			<input type="hidden" name="printFlag" value="jxspb">
			<div style="text-align:center;line-height:30px;">
				<p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课程教学审批表</p>
				<input type="text" name="schoolYearDesc" value="${form.schoolYearDesc}" style="text-align:center;width:80px;">
				学年度
				（<input type="text" name="gradeDesc" value="${form.gradeDesc}" style="text-align:center;width:80px;">年级）
				第
				<input type="text" name="termDesc" value="${form.termDesc}" style="text-align:center;width:20px;">
				学期
			</div>
			<table class="basic" style="width:100%;border:0px;">
				<tr>
					<td style="width:40%;border:0px;padding:0px;">课程名称：<input type="text" name="courseName" value="${empty form?course.courseName:form.courseName}" style="width:140px;"></td>
					<td style="width:60%;border:0px;padding:0px;">授课教研室：<input type="text" name="teachingPlace" value="${empty form?course.assumeOrgName:form.teachingPlace}" style="width:140px;"></td>
				</tr>
				<tr>
					<td style="border:0px;padding:0px;">英文名称：<input type="text" name="courseNameEn" value="${empty form?course.courseNameEn:form.courseNameEn}" style="width:140px;"></td>
					<td style="border:0px;padding:0px;"><div style="float:left;line-height:35px;height:100%;">理论课教师：</div><c:forEach items="${subs}" var="su" varStatus="i"><c:if test="${su.isTestTeacher eq 'N'}">${su.teacherName}<c:if test="${!i.last}">&ensp;</c:if></c:if></c:forEach></td>
				</tr>
				<tr>
					<td style="border:0px;padding:0px;">教材名称：<input type="text" name="teachingMaterial" value="${form.teachingMaterial}" style="width:140px;"></td>
					<td style="border:0px;padding:0px;">
						作者：<input type="text" name="authorName" value="${form.authorName}" style="width:70px;">&ensp;
						版次：<input type="text" name="layoutNum" value="${form.layoutNum}" style="width:40px;">&ensp;
						出版社：<input type="text" name="pressName" value="${form.pressName}" style="width:80px;">
					</td>
				</tr>
			</table>
			<div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">
				<span style="font-size: 14px;">理论课指导教师&emsp;&emsp;<img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addAnswer1()' title='添加'></span>
			</div>
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
					<th style="width:15%;text-align:center;padding:0px;">职称</th>
					<th style="width:60%;text-align:center;padding:0px;">授课内容</th>
					<th style="width:10%;text-align:center;padding:0px;">学时</th>
				</tr>
				<tbody id="test1">
				<c:forEach items="${subs}" var="su">
					<c:if test="${su.isApprovalTeacher eq 'Y'}">
					<tr>
						<td style="text-align:center;padding:0px;">${su.teacherName}<input type="hidden" class="recordFlow" value="${su.recordFlow}"><input type="hidden" class="teacherName" value="${su.teacherName}"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="teacherPost" value="${su.teacherPost}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="contentDesc" value="${su.contentDesc}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="schoolHours" value="${su.schoolHours}" style="width:60%;">	<img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
																																								onclick='delAnswer(this);'/>
							<input type="hidden" class="isTestTeacher" value="N">
						</td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
			<div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">
				<span style="font-size: 14px;">实验课指导教师&emsp;&emsp;<img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addAnswer()' title='添加'></span>
			</div>
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
					<th style="width:15%;text-align:center;padding:0px;">职称</th>
					<th style="width:60%;text-align:center;padding:0px;">实验名称</th>
					<th style="width:10%;text-align:center;padding:0px;">学时</th>
				</tr>
				<tbody id="test">
				<c:forEach items="${practicesList}" var="su">
					<c:if test="${su.isApprovalTeacher eq 'Y'}">
					<tr>
						<td style="text-align:center;padding:0px;">${su.teacherName}<input type="hidden" class="recordFlowY" value="${su.recordFlow}">
							<input type="hidden" class="teacherNameY" value="${su.teacherName}"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="teacherPostY" value="${su.teacherPost}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="contentDescY" value="${su.contentDesc}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" class="schoolHoursY" value="${su.schoolHours}" style="width:60%;">	<img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
																																								 onclick='delAnswer(this);'/>
							<input type="hidden" class="isTestTeacherY" value="Y">
						</td>

					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="search" value="下&#12288;载" onclick="down();"/>
			<input type="button" class="search" value="打&#12288;印" onclick="printapprovalForm();"/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
		<table class="basic" id="moban" style="display: none" style="width: 100%">
			<tr>
				<td style="text-align:center;padding:0px;">
					<input type="hidden" class="recordFlowY" value="">
					<select class="validate[required] teacherNameY" style="width:90%;" onchange="bindRecord1(this);" name="teacherName">
						<option value="">请选择</option>
						<c:forEach items="${practicesList}" var="su">
							<option value="${su.teacherName}" recordFlow="${su.recordFlow}" >${su.teacherName}</option>
						</c:forEach>
					</select><input type="hidden" class="isTestTeacherY" value="Y">
				</td>
				<td style="text-align:center;padding:0px;"><input type="text" class="teacherPostY" value="" style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" class="contentDescY" value="" style="width:90%;"></td>
				<td style="text-align:center;padding:0px;">
					<input class="validate[required] schoolHoursY" name="schoolHours" style="width: 60%;text-align: left;"/>
					<img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
						 onclick='delAnswer(this);'/>
					<input type="hidden" class="isApprovalTeacherY" value="Y">
				</td>
			</tr>
		</table>
		<table class="basic" id="moban1" style="display: none" style="width: 100%">
			<tr>
				<td style="text-align:center;padding:0px;">
					<input type="hidden" class="recordFlow" value="">
					<select class="validate[required] teacherName" style="width:90%;" onchange="bindRecord(this);">
						<option value="">请选择</option>
						<c:forEach items="${subs}" var="su">
							<c:if test="${su.isTestTeacher  eq 'N'}">
								<option value="${su.teacherName}" recordFlow="${su.recordFlow}">${su.teacherName}</option>
							</c:if>
						</c:forEach>
					</select><input type="hidden" class="isTestTeacher" value="N">
				</td>
				<td style="text-align:center;padding:0px;"><input type="text" class="teacherPost" value="" style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" class="contentDesc" value="" style="width:90%;"></td>
				<td style="text-align:center;padding:0px;">
					<input class="validate[required] schoolHours" name="schoolHours" style="width: 60%;text-align: left;"/>
					<img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
						 onclick='delAnswer(this);'/>
					<input type="hidden" class="isApprovalTeacher" name="isApprovalTeacher" value="Y">
				</td>
			</tr>
		</table>

		<div id="printDiv2" hidden="hidden">
			<div style="text-align:center;line-height:30px;">
				<p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课程教学审批表</p>
				${form.schoolYearDesc}学年度（${form.gradeDesc}年级）第${form.termDesc}学期
			</div>
			<table class="basic" style="width:100%;border:0px;">
				<tr>
					<td style="width:40%;border:0px;padding:0px;">课程名称：${empty form?course.courseName:form.courseName}</td>
					<td style="width:60%;border:0px;padding:0px;">授课教研室：${empty form?course.assumeOrgName:form.teachingPlace}</td>
				</tr>
				<tr>
					<td style="border:0px;padding:0px;">英文名称：${empty form?course.courseNameEn:form.courseNameEn}</td>
					<td style="border:0px;padding:0px;"><div style="float:left;line-height:35px;height:100%;">理论课教师：</div><c:forEach items="${subs}" var="su" varStatus="i"><c:if test="${su.isTestTeacher eq 'N'}">${su.teacherName}<c:if test="${!i.last}">&ensp;</c:if></c:if></c:forEach></td>
				</tr>
				<tr>
					<td style="border:0px;padding:0px;">教材名称：${form.teachingMaterial}</td>
					<td style="border:0px;padding:0px;">
						作者：${form.authorName}&emsp;
						版次：${form.layoutNum}&emsp;
						出版社：${form.pressName}
					</td>
				</tr>
			</table>
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
					<th style="width:15%;text-align:center;padding:0px;">职称</th>
					<th style="width:60%;text-align:center;padding:0px;">授课内容</th>
					<th style="width:10%;text-align:center;padding:0px;">学时</th>
				</tr>
				<c:forEach items="${subs}" var="su">
					<c:if test="${su.isTestTeacher  eq 'N'}">
						<tr>
							<td style="text-align:center;padding:0px;">${su.teacherName}</td>
							<td style="text-align:center;padding:0px;">${su.teacherPost}</td>
							<td style="text-align:center;padding:0px;">${su.contentDesc}</td>
							<td style="text-align:center;padding:0px;">${su.schoolHours}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">
				<span style="font-size: 14px;">实验课指导教师</span>
			</div>
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
					<th style="width:15%;text-align:center;padding:0px;">职称</th>
					<th style="width:60%;text-align:center;padding:0px;">实验名称</th>
					<th style="width:10%;text-align:center;padding:0px;">学时</th>
				</tr>
				<c:forEach items="${subs}" var="su">
					<c:if test="${su.isTestTeacher  eq 'Y'}">
						<tr>
							<td style="text-align:center;padding:0px;">${su.teacherName}</td>
							<td style="text-align:center;padding:0px;">${su.teacherPost}</td>
							<td style="text-align:center;padding:0px;">${su.contentDesc}</td>
							<td style="text-align:center;padding:0px;">${su.schoolHours}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>