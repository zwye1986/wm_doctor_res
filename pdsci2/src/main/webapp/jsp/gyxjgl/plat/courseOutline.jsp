<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
	function save(){
		jboxPost("<s:url value='/gyxjgl/course/manage/saveOutline'/>", $("#myForm").serialize(), function (obj) {
			if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
				location.reload();
				jboxClose();
			}
		});
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
<html>
<body>
	<div style="width:100%;height:100%;overflow-y:auto;">
		<div style="clear:both;">
			<form id="myForm" method="post">
				<input type="hidden" name="printFlag" value="Kcdg">
				<input type="hidden" name="courseFlow" value="${param.courseFlow}">
				<input type="hidden" name="jsonArry" id="jsonArry">

				<table  style="width:100%;border:0px; border-collapse:separate; border-spacing:0px 10px;">
					<input type="hidden" name="gradationId" value="${empty eduCourseMaterial.gradationId?eduCourse.gradationId:eduCourseMaterial.gradationId}"/>
					<input type="hidden" name="assumeOrgFlow" value="${empty eduCourseMaterial.assumeOrgFlow?eduCourse.assumeOrgFlow:eduCourseMaterial.assumeOrgFlow}"/>
					<input type="hidden" name="recordFlow" value="${eduCourseMaterial.recordFlow}"/>
					<input type="hidden" name="formType" value="${xjEduMaterialTypeEnumKcdg.id}"/>
					<div style="text-align:center;line-height:30px; margin:20px 0">
						<p style="font-size:28px;font-weight:bold;">研究生课程教学大纲</p>
					</div>
					<tr>
						<td colspan="2" style="width:100%;border:0px;padding:0px;margin-top:10px; text-align: center;">中文课程名称：<input   readonly="readonly" type="text" name="courseName" value="${empty eduCourseMaterial.courseName?eduCourse.courseName:eduCourseMaterial.courseName}" ></td>
					</tr>
					<tr>
						<td colspan="2" style="width:100%;border:0px;padding:0px;text-align: center; ">英文课程名称：<input type="text"  readonly="readonly" name="courseNameEn" value="${empty eduCourseMaterial.courseNameEn?eduCourse.courseNameEn:eduCourseMaterial.courseNameEn}" ></td>
					</tr>
					<tr>
						<td style="border:0px;padding:0px;">&emsp;总学时：<input type="text" name="coursePeriod"  readonly="readonly" value="${empty form?eduCourse.coursePeriod:form.coursePeriod}"></td>
						<td style="border:0px;padding:0px;">&emsp;总学分：<input type="text" name="courseCredit" readonly="readonly"  value="${empty form?eduCourse.courseCredit:form.courseCredit}"></td>

					</tr>
					<tr>
						<td style="width:40%;border:0px;padding:0px;">授课层次：<input type="text" name="gradationName" value="${empty form?eduCourse.gradationName:form.gradationName}"  readonly="readonly"></td>
						<td style="width:60%;border:0px;padding:0px;">承担单位：<input type="text" name="assumeOrgName" value="${empty form?eduCourse.assumeOrgName:form.assumeOrgName}"   readonly="readonly"></td>
					</tr>
					<tr>
						<td colspan="2" >一、课程简介：<br/>
						<textarea name="courseSynopsis" style="width: 98%;" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if> placeholder="简要介绍课程教授内容">${outlineForm.courseSynopsis }</textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" >二、课程目标：<br/>
							<textarea name="courseTarget" style="width: 98%" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if> placeholder="简要介绍课程目标、意义、方法等">${outlineForm.courseTarget }</textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" >三、课程进度：<br/>
							<textarea name="courseTerm" style="width: 98%" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if> placeholder="详细介绍各章讲授的内容及学时分配和相应的授课教师（针对两名讲师或以上）。">${outlineForm.courseTerm }</textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" >四、教学方式及要求：<br/>
							<textarea name="teachingMethod" style="width: 98%" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if> placeholder="主要说明教学方式和课堂学习要求，比如讲授、实验、讨论、案例分析等。">${outlineForm.teachingMethod }</textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" >五、考核方式：<br/>
							<textarea name="assessType" style="width: 98%" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if> placeholder="说明课程考核和成绩评定办法及其各自所占比例。考核方式分为考试和考查两种，考试分开卷考试和闭卷考试，考查主要指课程论文、综述等其他形式的考核。">${outlineForm.assessType}</textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" >六、教材及主要参考书目：<br/>
							<textarea name="teachingMaterial" style="width: 98%" <c:if test="${viewFlag eq 'view'}">readonly="readonly"</c:if>>${outlineForm.teachingMaterial }</textarea>
						</td>
					</tr>


				</table>
			</form>
		</div>
			<div style="text-align: center;margin:20px;">
				<c:if test="${viewFlag ne 'view'}">
					<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
					<input type="button" class="search" value="下&#12288;载" onclick="down();"/>
					<input type="button" class="search" value="打&#12288;印" onclick="printedm();"/>
				</c:if>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>

	</div>
	<div id="printDiv" style="display: none;"></div>
</body>
</html>