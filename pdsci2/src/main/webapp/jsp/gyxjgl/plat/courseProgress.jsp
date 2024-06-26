<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function save(){
			//var jsonArry = [];

			//$("#jsonArry").val(JSON.stringify(jsonArry));
			var answerTab = $('#test');
			var trs = answerTab.children();
			var datas = [];
			$.each(trs , function(i , n){
				var weekTimes = $(n).find("input[name='weekTimes']").val();
				var monthOrDay =  $(n).find("input[name='monthOrDay']").val();
				var week = $(n).find("input[name='week']").val();
				var hours =  $(n).find("input[name='hours']").val();
				var festivals = $(n).find("input[name='festivals']").val();
				var teachingContent =  $(n).find("input[name='teachingContent']").val();
				var teachingman = $(n).find("input[name='teachingman']").val();
				var data = {
					"weekTimes":weekTimes,
					"monthOrDay":monthOrDay,
					"week":week,
					"hours":hours,
					"festivals":festivals,
					"teachingContent":teachingContent,
					"teachingman":teachingman
				};
				datas.push(data);
			});


			var json = {'courserProgressList':datas};
			$("#jsondata").val(JSON.stringify(json));
			var url="<s:url value='/gyxjgl/course/manage/saveOutline'/>?formType=${param.formType}";
			jboxPost(url, $("#myForm").serialize(), function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					jboxClose();
				}
			},null,true);
		}
		function addAnswer(){
			$('#test').append($("#moban tr:eq(0)").clone());
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

		function printedm(){
			var url = "<s:url value="/gyxjgl/course/manage/printEduCourseMaterial"/>?courseFlow=${param.courseFlow}&formType=${param.formType}";
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
			<input type="hidden" name="printFlag" value="Kcjdb">
			<input id="jsondata" type="hidden" name="json" value=""/>
			<input type="hidden" name="courseFlow" value="${param.courseFlow}">
			<%--<input type="hidden" name="jsonArry" id="jsonArry">--%>
			<input type="hidden" name="recordFlow" value="${eduCourseMaterial.recordFlow}"/>
			<div style="text-align:center;line-height:30px;">
				<p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课教学进度表</p>
				<input type="text" name="schoolYearDesc" value="${eduCourseMaterial.schoolYearDesc}" style="text-align:center;width:80px;">
				学年度
				（<input type="text" name="gradeDesc" value="${eduCourseMaterial.gradeDesc}" style="text-align:center;width:80px;">年级）
				第
				<input type="text" name="termDesc" value="${eduCourseMaterial.termDesc}" style="text-align:center;width:20px;">
				学期
			</div>
			<table class="basic" style="width:100%;border:0px;">
				<tr>
					<td style="width:40%;border:0px;padding:0px;">课程名称：<input type="text" name="courseName" value="${empty eduCourseMaterial?eduCourse.courseName:eduCourseMaterial.courseName}" style="width:140px;"></td>
					<td style="width:60%;border:0px;padding:0px;">授课教研室：<input type="text" name="assumeOrgName" value="${empty eduCourseMaterial?eduCourse.assumeOrgName:eduCourseMaterial.assumeOrgName}" style="width:140px;"></td>
				</tr>
				<tr>
					<td colspan="2" style="width:100%;border:0px;padding:0px;">授课地点：<input   type="text" name="teachingPlace" value="${eduCourseMaterial.teachingPlace}" ></td>
				</tr>
			</table>
			<div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">

			</div>
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:10%;text-align:center;padding:0px;">周次</th>
					<th style="width:10%;text-align:center;padding:0px;">月/日</th>
					<th style="width:10%;text-align:center;padding:0px;">星期</th>
					<th style="width:10%;text-align:center;padding:0px;">时数</th>
					<th style="width:10%;text-align:center;padding:0px;">节次</th>
					<th style="width:35%;text-align:center;padding:0px;">讲授内容（理论课或实验课）</th>
					<th style="width:10%;text-align:center;padding:0px;">授课人<img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addAnswer()' title='添加'></th>
				</tr>
				<tbody id="test">
				<c:forEach items="${courserProgressList}" var="su">
					<tr>
						<td style="text-align:center;padding:0px;"> <input type="text" name="weekTimes" value="${su.weekTimes}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" name="monthOrDay" value="${su.monthOrDay}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" name="week" value="${su.week}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" name="hours" value="${su.hours}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" name="festivals" value="${su.festivals}" style="width:90%;"></td>
						<td style="text-align:center;padding:0px;"><input type="text" name="teachingContent" value="${su.teachingContent}" style="width:90%;"></td>
						<td>
							<input type="text" name="teachingman" value="${su.teachingman}" style="width:65%;text-align: left;" >
							<img class='opBtn' title='删除' style='cursor: pointer;'  src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick='delAnswer(this);'/>
						</td>
					</tr>
				</c:forEach>

				</tbody>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="search" value="下&#12288;载" onclick="down();"/>
			<input type="button" class="search" value="打&#12288;印" onclick="printedm();"/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
		<table class="basic" id="moban" style="display: none" style="width: 100%">
			<tr>
				<td style="text-align:center;padding:0px;"> <input type="text" name="weekTimes"  style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" name="monthOrDay"  style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" name="week" style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" name="hours"  style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" name="festivals"  style="width:90%;"></td>
				<td style="text-align:center;padding:0px;"><input type="text" name="teachingContent"  style="width:90%;"></td>
				<td colspan="2">
					<input type="text" name="teachingman" value="${su.teachingman}" style="width:65%;text-align: left;" >
					<img class='opBtn' title='删除' style='cursor: pointer;'  src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
						 onclick='delAnswer(this);'/>
				</td>
			</tr>
		</table>

	</div>
</div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>