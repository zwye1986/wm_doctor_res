<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
<%--function edit(courseFlow){--%>
	  <%--jboxOpen("<s:url value='/gyxjgl/course/manage/currCourse'/>?courseFlow="+courseFlow,"课程编辑",850,420);--%>
	<%--}--%>
<%--function daoRu(){--%>
	 <%--jboxOpen("<s:url value='/gyxjgl/course/manage/importCourse'/>","导入",600,200);	--%>
	<%--}--%>
<%--function add(){--%>
	 <%--jboxOpen("<s:url value='/gyxjgl/course/manage/currCourse'/>","课程添加",850,420);--%>
<%--}--%>
function toPage(page){
	$("#assumeOrgFlow").val("");
	if($("#assumeOrgName").val()!=""){
		$("#assumeOrgFlow").val($("#assumeOrgName").attr("flow"));
	}
	var form=$("#recSearchForm");
	$("#currentPage").val(page);
	jboxStartLoading();
	form.submit();
}
<%--function deletes(courseFlow){--%>
	<%--jboxConfirm('确认删除吗?',function(){--%>
	<%--var url="<s:url value='/gyxjgl/course/manage/courseDelete'/>?courseFlow="+courseFlow;--%>
	 <%--jboxPost(url,null,function(){--%>
		 <%--toPage($("#currentPage").val());//window.location.href=window.location.href;--%>
		<%--},null,true);  --%>
	<%--});--%>
<%--}--%>
function courseExport(){
	jboxConfirm('确认导出吗?',function(){
	var url="<s:url value='/gyxjgl/course/manage/courseExport'/>";
	jboxTip("导出中…………");
	jboxSubmit($('#recSearchForm'),url,null, null,false);
	jboxEndLoading();
	});
}
/**
 *模糊查询加载
 */
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			searchInput.attr("flow",$(this).attr("flow"));
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
$(function(){
	$("#assumeOrgName").likeSearchInit({
	});
});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width:100%;min-width: 1080px;margin: 10px 0px;border: none;">
			<tr>
				<td style="line-height: 260%;">
					<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/teacher/courseList'/>">
						<input type="hidden" name="currentPage" id="currentPage"/>
						所属学年：<input type="text" name="courseSession" value="${param.courseSession}"
									   onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;margin: 0">
						<span style="padding-left:10px;"></span>
						课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width:137px;" />
						<span style="padding-left:10px;"></span>
						课程代码：<input type="text" name="courseCode" value="${param.courseCode}" style="width:137px;" />
						<span style="padding-left:10px;"></span>
						授课层次：<select style="width: 141px;" name="gradationId">
					 		<option value="">请选择</option>
					 		 <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
								 <c:if test="${trainType.dictId eq '1' || trainType.dictId eq '2'}">
									 <option name="${trainType.dictId}" value="${trainType.dictId}" <c:if test="${param.gradationId==trainType.dictId}">selected="selected"</c:if>>${trainType.dictName}</option>
								 </c:if>
						 	 </c:forEach>
					 	</select>
						<span style="padding-left:10px;"></span>
						<%--课程类别：<select style="width: 141px;" name="courseTypeId">--%>
					 		<%--<option value="">请选择</option>--%>
					 		<%--<c:forEach items="${dictTypeEnumGyXjCourseTypeList}" var="courseType">--%>
						 		<%--<option value="${courseType.dictId}" <c:if test="${param.courseTypeId==courseType.dictId}">selected="selected"</c:if>>${courseType.dictName}</option>--%>
								<%----%>
							<%--</c:forEach>--%>
					 	<%--</select>--%>
						<br/>
						 承担单位：<input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}" style="width: 137px;"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:65px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
								<c:forEach items="${orgMap}" var="org">
									<p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
								</c:forEach>
							</div>
						</div>
						<input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
						<%--<select  name="assumeOrgFlow" style="width: 141px;">--%>
					 		<%--<option value=""></option>--%>
					 		<%--<c:forEach items="${orgMap}" var="org">--%>
						 		<%--<option value="${org.key}" <c:if test="${param.assumeOrgFlow==org.key}">selected="selected"</c:if>>${org.value}</option>--%>
							<%--</c:forEach>--%>
					 	<%--</select>--%>
						<span style="padding-left:10px;"></span>
						   <input type="button"  value="查&#12288;询" class="search" onclick="toPage();"/>
					 	<%--<input type="button"  value="导&#12288;入" class="search" onclick="daoRu();"/>--%>
					 	<input type="button"  value="导&#12288;出" class="search" onclick="courseExport();"/>
					 	<%--<input type="button"  value="新&#12288;增" class="search" onclick="add();"/>--%>
					</form>
				</td>
			</tr>
		</table>
		<div class="resultDiv">
			<table class="basic" width="100%">
			<tr style="font-weight: bold;">
				<td style="text-align: center;padding: 0px;width:6%;">所属学年</td>
				<td style="text-align: center;padding: 0px;width:6%;">代码</td>
				<td style="text-align: center;padding: 0px;width:10%;">中文名称</td>
				<td style="text-align: center;padding: 0px;width:7%;">授课层次</td>
				<td style="text-align: center;padding: 0px;width:7%;">课程类别</td>
				<!-- 
				<td style="width:6%;">英文名称</td>
				 -->
				<td style="text-align: center;padding: 0px;width:6%;">容纳人数</td>
				<td style="text-align: center;padding: 0px;width:6%;">所属模块</td>
				<td style="text-align: center;padding: 0px;width:10%;">承担单位</td>
				<td style="text-align: center;padding: 0px;width:6%;">学分</td>
				<td style="text-align: center;padding: 0px;width:6%;">总学时</td>
				<!-- 
				<td style="width:6%;">讲授学时</td>
				<td style="width:6%;">实验学时</td>
				<td style="width:6%;">上机学时</td>
				<td style="width:6%;">其它学时</td>
				 -->
				<%----%>
				<%--<td style="width:6%;">操作</td>--%>
			</tr>
			
			<c:forEach items="${coursesList}" var="course">
				<tr>
						<td style="text-align: center;padding: 0px;">${course.courseSession}</td>
						<td style="text-align: center;padding: 0px;">${course.courseCode}</td>
						<td style="text-align: center;padding: 0px;">${course.courseName}</td>
						<td style="text-align: center;padding: 0px;">${course.gradationName}</td>
						<td style="text-align: center;padding: 0px;">${course.courseTypeName}</td>
						<!-- 
						<td>${course.courseNameEn}</td>
						-->
						<td style="text-align: center;padding: 0px;">${course.courseUserCount}</td>
						<td style="text-align: center;padding: 0px;">${course.courseMoudleName}</td>
						<td style="text-align: center;padding: 0px;">${course.assumeOrgName}</td>
						<td style="text-align: center;padding: 0px;">${course.courseCredit}</td>
						<td style="text-align: center;padding: 0px;">${course.coursePeriod}</td>
						<!-- 
						<td>${course.coursePeriodTeach}</td>
						<td>${course.coursePeriodExper}</td>
						<td>${course.coursePeriodMachine}</td>
						<td>${course.coursePeriodOther}</td>
						-->
						<%--<td><a style="cursor: pointer; color: blue;" onclick="edit('${course.courseFlow}');">[编辑]</a>&nbsp;<a style="cursor: pointer; color: blue;" onclick="deletes('${course.courseFlow}');">[删除]</a></td>--%>
				</tr>
			</c:forEach>
			<c:if test="${empty coursesList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
		</table>
	       	<c:set var="pageView" value="${pdfn:getPageView(coursesList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>