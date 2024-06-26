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
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	function loadCourseList(){
		var courseArray = new Array();
		var url = "<s:url value='/xjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='studentPeriod']").val();
		jboxGetAsync(url,null,function(data){
			if(data!=null){
				for (var i = 0; i < data.length; i++) {
					var courseFlow=data[i].courseFlow;
					if(data[i].courseFlow==null){
						courseFlow="";
					}
					var courseName=data[i].courseName;
					if(data[i].courseName==null){
						courseName="";
					}
					var courseCode=data[i].courseCode;
					if(data[i].courseCode==null){
						courseCode="";
					}
					courseArray[i]=new Array(courseFlow,courseName,courseCode);
					if($("#result_Course").val() == courseFlow){
						$("#searchParam_Course").val(courseName);
					}
				}
				jboxStartLoading();
				$("#searchParam_Course").suggest(courseArray,{
					attachObject:'#suggest_Course',
					dataContainer:'#result_Course',
					triggerFunc:function(courseFlow){

					},
					enterFunc:function(courseFlow){

					}
				});
				jboxEndLoading();
			}
		},null,false);
	}
	$(function(){
		loadCourseList();
	});
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
	function add(){
		if(!$("#addForm").validationEngine("validate")){
			return ;
		}
		jboxConfirm("确认保存?",function(){
			jboxPost('<s:url value="/xjgl/student/saveOneGrade"/>',$('#addForm').serialize(),function(resp){
				if("1"==resp){
					jboxTip("保存成功！");
					window.parent.frames['mainIframe'].window.toPage();
					jboxClose();
				}else if("-1"==resp){
					jboxTip("已维护过此学生此课程！");
				}else if("-2"==resp){
					jboxTip("不存在此学号的学生！");
				}else if("-3"==resp){
					jboxTip("该课程代码未维护！");
				}else{
					jboxTip("保存失败！");
				}
			},null,false);
		},null);
	}
	function checkScore(val,obj){
		var regx=/^(0|([1-9]\d*))(\.\d+)?$/;
		if(regx.test(val)||("通过"==val||"不通过"==val||"缺考"==val)){
		}else {
			jboxTip("只能填写数值或者通过、不通过、缺考！");
			$(obj).val("");
		}
	}
	function reload(){
		$("#searchParam_Course").val("");//清空上前面学年的课程信息
		$("#result_Course").val("");
		loadCourseList();//重新加载某学年的课程
	}
	function searchStuBySid(sid){
		jboxPost('<s:url value="/xjgl/student/searchStuBySid"/>?sid='+sid,null,function(resp){
			if("${GlobalConstant.OPRE_FAIL}"==resp){
				$("input[name='userName']").val("");
				jboxTip("不存在此学号的学生！");
			}else{
				$("input[name='userName']").val(resp);
			}
		},null,false);
	}
</script>
</head>
<body>
	<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
		<form  id="addForm" method="post">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<td>&ensp;&#12288;<font color="red">*</font>获得学年：<input class="validate[required]" name="studentPeriod" type="text" value="${pdfn:getCurrYear()}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" onchange="reload()"></td>
					<td><font color="red">*</font>获得学期：<select class="validate[required]" name="gradeTermId" style="width: 200px;">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
							<option value="${recruitSeason.dictId}" <c:if test="${pdfn:contains(recruitSeason.dictName,'秋季')}">selected</c:if>>${recruitSeason.dictName}</option>
						</c:forEach>
					</select>
					</td>
					<td><font color="red">*</font>学&#12288;号：<input class="validate[required]" name="sid" type="text" style="width: 138px;" value="${user.SID}" onchange="searchStuBySid(this.value);"> </td>
				</tr>
				<tr>
					<td>&ensp;&#12288;<font color="red">*</font>姓&#12288;&#12288;名：<input name="userName" class="validate[required]" type="text" value="${user.USER_NAME}"></td>
					<td colspan="2">
						<font color="red">*</font>课程检索：<input id="searchParam_Course" placeholder="输入课程名称/代码" class="validate[required]"  style="width: 196px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
						<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 196px;"></div>
						<input type="hidden" id="result_Course" name="courseFlow"/>
					</td>

				</tr>
				<tr>
					<td>&ensp;&#12288;<font color="red">*</font>修读方式：<select class="validate[required]" name="studyWayId" style="width: 156px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumXjStudyWayList}" var="way">
							<option value="${way.dictId}" <c:if test="${pdfn:contains(way.dictName,'初修')}">selected</c:if>>${way.dictName}</option>
						</c:forEach>
					</select>
					</td>
					<td colspan="2"><font color="red">*</font>考核方式：<select class="validate[required]" name="assessTypeId" style="width: 200px;">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumXjEvaluationModeList}" var="mode">
							<option value="${mode.dictId}" <c:if test="${pdfn:contains(mode.dictName,'考试')}">selected</c:if>>${mode.dictName}</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>&ensp;&#12288;<font color="red" style="visibility: hidden">*</font>考勤成绩：<input class="validate[custom[number]]" name="pacificGrade" type="text"></td>
					<td><font color="red" style="visibility: hidden">*</font>考核成绩：<input class="validate[custom[number]]" name="teamEndGrade" type="text" style="width: 196px;"></td>
					<td><font color="red">*</font>成&#12288;绩：<input class="validate[required]" name="courseGrade" type="text" style="width: 138px;" onchange="checkScore(this.value,this)"></td>
				</tr>
				<tr>
					<td colspan="3">成绩获得方式：<select name="scoreMode" style="width: 156px;">
						<option value=""/>
						<option value="R">补考</option>
					</select>
					</td>
				</tr>
			</table>
		</form>
			<div style="text-align: center;">
				<input type="button" class="search" onclick="add();" value="保&#12288;存"/>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>
		</div>
	</div>
	</div>
</body>
</html>