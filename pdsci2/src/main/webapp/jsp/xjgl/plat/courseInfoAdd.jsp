<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
<script type="text/javascript">
	function add(courseFlow){
		if(!$("#form").validationEngine("validate")){
			return ;
		}
		jboxConfirm('确认保存?',function(){
			sum();
			if(courseFlow==""){
				$('[name="gradationName"]').val($('#gradationId option:selected').text());
				$('[name="courseTypeName"]').val($('#courseTypeId option:selected').text());
				$('[name="assumeOrgName"]').val($('#assumeOrgFlow option:selected').text());
				$('[name="courseMoudleName"]').val($('#courseMoudleId option:selected').text());
				$('[name="courseSpeaker"]').val($('#courseSpeakerFlow option:selected').text());
				var url="<s:url value='/xjgl/course/manage/saveCourse'/>";
				var fromSerizalize=$("#form").serialize();
				 jboxPost(url,fromSerizalize,function(){
					setTimeout(function(){
						window.parent.frames['mainIframe'].window.toPage();
						jboxClose();
					},1000);
				},null,true);
			}else{
				$('[name="gradationName"]').val($('#gradationId option:selected').text());
				$('[name="courseTypeName"]').val($('#courseTypeId option:selected').text());
				$('[name="assumeOrgName"]').val($('#assumeOrgFlow option:selected').text());
				$('[name="courseMoudleName"]').val($('#courseMoudleId option:selected').text());
				$('[name="courseSpeaker"]').val($('#courseSpeakerFlow option:selected').text());
				var url="<s:url value='/xjgl/course/manage/saveCourse'/>?courseFlow="+courseFlow;
				var fromSerizalize=$("#form").serialize();
				 jboxPost(url,fromSerizalize,function(){
					setTimeout(function(){
						window.parent.frames['mainIframe'].window.toPage();
						jboxClose();
					},1000);
				},null,true);
			}
		});
	}
	/* 统计 */
	function sum(){
		var zhonghe = 0;		
		var $item = $(".time");	
		$item.each(function(){		
			var value = this.value;
			if(value!="" && !isNaN(value)){	
				var numItem = parseInt(value);	
				zhonghe+=numItem;	
			}
		});
		$("#coursePeriod").text(zhonghe);
		$('[name="coursePeriod"]').val($("#coursePeriod").text());
		
		
	}
	function courseCodeOk(){//课程代码+年份组合维度
		var courseSession=$("[name='courseSession']").val();
		if(courseSession == ""){
			jboxTip("请维护好该课程所属年份");
			$('[name="courseCode"]').val("");
			return;
		}
		var courseCode=$('[name="courseCode"]').val();
		var url="<s:url value='/xjgl/course/manage/courseCodeOk'/>?courseCode="+courseCode+"&courseSession="+courseSession;
		 jboxPost(url,null,function(s){
				if(s=="${GlobalConstant.COURSE_COURSECODE_EXIST}"){
					$('[name="courseCode"]').val("");
				}
		 },null,true);
	}
	function changeName(obj)
	{
		var name=""
		if($(obj).val()!="")
		{
			name=$(obj).find("option:selected").text();
		}
		console.log(name);
		$("#courseSpeaker").val(name);
	}

$(function(){
	sum();
	var datas =[];//所有的分配的授课老师账号
	<c:forEach items="${userList}" var="teacher">
		var arry = {"id":"${teacher.userFlow}","text":"${teacher.userName}"};
		datas.push(arry);
	</c:forEach>
	var datas2 =[];//已关联的授课老师
	<c:forEach items="${teacherLst}" var="teacher">
	var arry={};
	arry.id="${teacher.classTeacherFlow}";
	arry.text="${teacher.classTeacherName}";
		datas2.push(arry);
	</c:forEach>
	<c:if test="${not empty userList}">
		$.itemSelect("courseTeacher",datas,null,null,datas2);
	</c:if>
});
</script>
</head>
<body>
	<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
		<form  id="form" method="post">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<input type="hidden" name="gradationName" value=""/>
				<input type="hidden" name="courseTypeName" value=""/>
				<input type="hidden" name="assumeOrgName" value=""/>
				<input type="hidden" name="courseMoudleName" value=""/>
				<tr>
					<th><span style="color: red">*</span>&nbsp;课程名称：</th>
					<td colspan="3" style="text-align: left;padding-left: 10px;"><input type="text" class="validate[required]" style="width: 402px;" name="courseName" value="${course.courseName}"/></td>
					<th><span style="color: red">*</span>&nbsp;课程代码：</th>
					<td><input type="text" class="validate[required,custom[onlyNumberSp]]" style="width: 137px;" name="courseCode" value="${course.courseCode}" onchange="courseCodeOk();"/></td>
				</tr>
				<tr>
					<th><!-- <span style="color: red">*</span> -->&nbsp;英文名称：</th>
					<td colspan="3" style="text-align: left;padding-left: 10px;"><input type="text"  style="width:402px" name="courseNameEn" value="${course.courseNameEn}"/></td>
					<th><span style="color: red">*</span>&nbsp;授课层次：</th><!--  -->
					<td>
						<select style="width: 140px;" name="gradationId" id="gradationId" class="validate[required]">
						<option value="">请选择</option>
						  <c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
							<option value="${trainType.dictId}" <c:if test="${course.gradationId==trainType.dictId}">selected="selected"</c:if>>${trainType.dictName}</option>
						  </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th >讲授学时：</th>
					<td style="text-align: left;padding-left: 10px;width: 170px;"><input class="time validate[custom[number]]" onkeyup="sum()"  type="text" style="width: 137px;" name="coursePeriodTeach" value="${course.coursePeriodTeach}"/></td>
					<th>实验学时：</th>
					<td style="text-align: left;padding-left: 10px;"><input class="time validate[custom[number]]" onkeyup="sum()"  type="text" style="width: 137px;" name="coursePeriodExper" value="${course.coursePeriodExper}"/></td>
					<th><span style="color: red">*</span>&nbsp;课程类别：</th>
					<td>
					<select style="width: 140px;" name="courseTypeId"  id="courseTypeId" class="validate[required]">
						<option value="">请选择</option>
					 		<c:forEach items="${dictTypeEnumXjCourseTypeList}" var="courseType">
						 		<option value="${courseType.dictId}" <c:if test="${course.courseTypeId==courseType.dictId}">selected="selected"</c:if>>${courseType.dictName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>上机学时：</th>
					<td style="text-align: left;padding-left: 10px"><input style="width: 137px;" class="time validate[custom[number]]" onkeyup="sum()"  type="text" name="coursePeriodMachine" value="${course.coursePeriodMachine}"/></td>
					<th>其他学时：</th>
					<td style="text-align: left;padding-left: 10px;"><input style="width: 137px;" class="time validate[custom[number]]" onkeyup="sum()"  type="text"  name="coursePeriodOther" value="${course.coursePeriodOther}"/></td>
					<th>总学时：</th>
					<td style="padding-left:3%;"><label id="coursePeriod" >${course.coursePeriod}</label>
					<input type="hidden" name="coursePeriod"/>
				</tr>
				
				
				<tr>
					<th>容纳人数：</th>
					<td style="text-align: left;padding-left: 10px" ><input style="width: 137px;" class="validate[custom[number]]"  type="text" name="courseUserCount" value="${course.courseUserCount}"/></td>
					<th>所属模块：</th>
					<td style="text-align: left;padding-left: 10px;">
						<select style="width: 140px;" name="courseMoudleId" id="courseMoudleId">
						<option value="">请选择</option>
						  <c:forEach items="${dictTypeEnumXjCourseMoudleList}" var="XjCourseMoudle">
							<option value="${XjCourseMoudle.dictId}" <c:if test="${course.courseMoudleId==XjCourseMoudle.dictId}">selected="selected"</c:if>>${XjCourseMoudle.dictName}</option>
						  </c:forEach>
						</select>
					</td>
					<th><span style="color: red">*</span>&nbsp;学分：</th>
					<td><input type="text" class="validate[required,custom[number]]" style="width: 137px;" name="courseCredit" value="${course.courseCredit}"/></td>
				</tr>
				<tr>
					<th>
					<span style="color: red">*</span>&nbsp;承担单位：</th>
					<td colspan="3" style="text-align: left;padding-left: 10px;">
						<select style="width: 406px;" name="assumeOrgFlow"  id="assumeOrgFlow" class="validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${sysOrg}" var="org">
								<option value="${org.orgFlow}" <c:if test="${course.assumeOrgFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
					</td>
					<th><span style="color: red">*</span>&nbsp;所属学年：</th>
					<td>
						<input type="text" name="courseSession" value="${course.courseSession}" class="ctime validate[required]" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:133px;height:22px;">
					</td>
				</tr>
				<tr>
					<th>教学组长：</th>
					<td style="text-align: left;padding-left: 10px" >
						<select  style="width: 140px;" name="courseSpeakerFlow" id="courseSpeakerFlow">
							<option value="">请选择</option>
							<c:forEach items="${masterList}" var="master">
								<option value="${master.userFlow}" <c:if test="${course.courseSpeakerFlow eq master.userFlow}">selected="selected"</c:if>>${master.userName}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="courseSpeaker" id="courseSpeaker" value="${course.courseSpeaker}">
					</td>
					<th>联系电话：</th>
					<td style="text-align: left;padding-left: 10px;">
						<input style="width: 137px;" type="text" name="courseSpeakerPhone" value="${course.courseSpeakerPhone}"/>
					</td>
					<th>前置课程代码：</th>
					<td>
						<input style="width: 137px;"   type="text" name="preCourse" value="${course.preCourse}"/>
					</td>
				</tr>
				<tr>
					<th>授课老师：</th>
					<td colspan="3" style="text-align: left;padding-left: 7px;">
						<div style="min-width:150px;width:150px;border:1px;padding-left:3px;">
							<input id="courseTeacher" placeholder="授课老师维护" style="width: 402px;" type="text" readonly="readonly" />
						</div>
					</td>
					<th>有效成绩：</th>
					<td><input type="text" class="validate[custom[number]]" style="width: 137px;" name="effectiveGrade" value="${course.effectiveGrade}"/></td>
				</tr>
				<tr>
					<th>课程简介：</th>
					<td colspan="3" style="text-align: left;padding-left: 5px;">
						<textarea style="height: 50px;width:403px" name="courseIntro" class="xltxtarea">${course.courseIntro}</textarea>
					</td>
					<th></th>
					<td ></td>
				</tr>
			</table>
		</form>
			<div style="text-align: center;">
				<input type="button" class="search" onclick="add('${course.courseFlow}');" value="保&#12288;存"/>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>
		</div>
	</div>
	</div>
</body>
</html>