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
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
function importExl(){
	jboxOpen("<s:url value='/gyxjgl/majorCourse/importCourseMajor'/>","导入",600,180);
}
var year='${year}';
function edit(majorId){
	var w = $('.mainright').width()*0.9;
	var h = $('.mainright').height()*0.7;
	var url = "<s:url value='/gyxjgl/majorCourse/edit'/>?majorId="+majorId+"&year="+year+"&editFlag=Y";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'专业课程维护',w,h,null,true);
	}
function add(){
	var w = $('.mainright').width()*0.9;
	var h = $('.mainright').height()*0.7;
	var newYear=$("input[name='planYear']").val();
	if(newYear=="")
	{
		newYear=year;
	}
	var url = "<s:url value='/gyxjgl/majorCourse/edit'/>?year="+year;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'专业课程维护',w,h,null,true);
}
function clearQuote(){
	jboxOpenContent($("#delQuote").html(),"删除引用数据",400,160);
}
function quoteData(){
	jboxOpenContent($("#quote").html(),"引用数据",400,160);
}
function search(){
	jboxStartLoading();
	var form=$("#recSearchForm");
	if($("#searchParam_Course").val()==""){
		$("#result_Course").val("");
	}
	if($("#searchParam_Major").val()==""){
		$("#result_Major").val("");
	}
    form.submit();
}

function loadCourseList(){
	var courseArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='planYear']").val();
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
function loadMajorList(){
	var majorArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchMajorJson'/>";
	jboxGetAsync(url,null,function(data){
		if(data){
			for (var i = 0; i < data.length; i++) {
				var dictId=data[i].dictId;
				if(data[i].dictId==null){
					dictId="";
				}
				var dictName=data[i].dictName;
				if(data[i].dictName==null){
					dictName="";
				}
				majorArray[i]=new Array(dictId,dictName,dictId);
				if($("#result_Major").val() == dictId){
					$("#searchParam_Major").val(dictName);	
				}
			}
			jboxStartLoading();
			$("#searchParam_Major").suggest(majorArray,{
				attachObject:'#suggest_Major',
				dataContainer:'#result_Major',
				triggerFunc:function(majorId){
			
				},
			    enterFunc:function(){
			  
			    }
			});
			jboxEndLoading();
		}
	},null,false);
}
$(function(){
	loadMajorList();
	loadCourseList();
});
function adjustResults() {
	$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
	$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	$("#suggest_Major").css("left",$("#searchParam_Major").offset().left);
	$("#suggest_Major").css("top",$("#searchParam_Major").offset().top+$("#searchParam_Major").outerHeight());
}
function toPage(page){
	$("#currentPage").val(page);
	search();
}
function toogleStudent(flow){
	if(window.event.target.nodeName =="TD" ||window.event.target.nodeName =="TH"||window.event.target.nodeName =="DIV"){
		$("#"+flow+"_tbody").toggle();
	}
}
	function reload(){
		$("#searchParam_Course").val("");//清空上前面学年的课程信息
		$("#result_Course").val("");
		loadCourseList();//重新加载某学年的课程
	}
</script>
<style type="text/css">
	.table tr td{
		border-bottom: 0px;
		border-right: 0px;
	}
	.table tr th{
		border-bottom: 0px;
		border-right: 0px;
	}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
	<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
	<table style="width: 100%;margin: 10px 0px;border: none;">
		<tr>
			<td style="border: none;line-height: 260%;">
	  			<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/majorCourse/list'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div>
						&nbsp;专业检索：<input  id="searchParam_Major"  placeholder="输入专业名称/代码" class="inputText"  style="width: 137px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
						<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
						<input type="hidden" id="result_Major" name="majorId" value="${param.majorId }"/>&#12288;
						课程检索：<input id="searchParam_Course"   placeholder="输入课程名称/代码" class="inputText"  style="width: 137px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
						<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
						<input type="hidden" id="result_Course" name="courseFlow" value="${param.courseFlow }"/>&#12288;
						年份：<input type="text" name="planYear" value="<c:out value="${param.planYear}" default="${year}"/>" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;margin: 0" onchange="reload()"/>&#12288;
						<input type="button" name="" value="查&#12288;询" class="search" onclick="search();"/>
					</div>
					<div style="margin-top: 5px;margin-bottom: 5px;">
						<input type="button" name="" value="新&#12288;增" class="search" onclick="add();"/>
						<input type="button" name="" value="导&#12288;入" class="search" onclick="importExl	();"/>
					</div>
				</form>
			</td>
		</tr>
	</table>
	<c:forEach items="${yearCourseMajorMap}" var="yearMap">
			<c:set var="courseMajorMap" value="${yearMap.value}"/>
			<c:forEach items="${courseMajorMap}" var="courseMap">
			<c:set var="courseList" value="${courseMap.value}"/>
			<c:set var="majorAllCoursePeriod" value="${ yearMap.key}_${courseMap.key}_majorAllCoursePeriod"/>
			<c:set var="majorAllCourseCredit" value="${ yearMap.key}_${courseMap.key}_majorAllCourseCredit"/>
			<table class="xllist" width="100%" style="margin-bottom: 10px;">
				<tr style="cursor: pointer;" > 
					<th colspan="10" style="text-align: left;padding-left: 10px;width: 100%;"  onclick="toogleStudent('${yearMap.key}_${courseMap.key }');">
						<div style="float: left;">年份：${yearMap.key}&#12288;&#12288;</div>
						<div style="width: 240px;float: left;">[${courseMap.key}] ${majorMap[courseMap.key]}</div>
						<div style="width: 120px;float: left;">课程总数：${fn:length(courseList)}</div>
						<div style="width: 120px;float: left;">总学时：<fmt:formatNumber value="${majorMap[majorAllCoursePeriod]}" type="number" maxFractionDigits="1"/></div>
						<div style="width: 120px;float: left;">总学分：<fmt:formatNumber value="${majorMap[majorAllCourseCredit]}" type="number" maxFractionDigits="1"/></div>
						<span style="float: right;padding-right: 10px;"><a style="cursor: pointer;color:blue;" href="javascript:edit('${courseMap.key }')">编辑</a></span>
					</th>
				</tr>
				 <tbody style="display:none " id="${yearMap.key}_${courseMap.key }_tbody">
				 <tr>
					<th  style="width: 15%;">[课程代码]对应课程</th>
					<th  style="width: 10%;">课程层次</th>
					<th  style="width: 10%;">课程类型</th>
					<th  style="width: 5%;">学时</th>
					<th  style="width: 5%;">学分</th>
					<!-- 
					<th  style="width: 10%;">操作</th>
					 -->
				</tr>
				 <c:forEach items="${courseList}" var="course">
				  <c:set var="eduCourse" value="${eduCourseMap[course.courseFlow]}"/>
				<tr>
					 <td style="text-align: left;padding-left: 10px;">[${eduCourse.courseCode }]${course.courseName}</td>
			         <td>${eduCourse.gradationName}</td>
					<c:if test="${!gzFlag}">
			         <td>${course.courseTypeName}</td>
					</c:if>
					<c:if test="${gzFlag}">
						<td>
							<c:if test="${course.courseTypeName=='公共选修课'}">选修课</c:if>
							<c:if test="${course.courseTypeName=='专业必选课'}">必选课</c:if>
						</td>
					</c:if>
			         <td>${eduCourse.coursePeriod}</td>
			         <td>${eduCourse.courseCredit}</td>
			      </tr>
					<!-- 
					<td><a style="cursor: pointer; color: blue;" href="javascript:edit('${form.majorId }')">[编辑]</a></td>
					 -->
				</c:forEach>
				</tbody>
			</table>
			</c:forEach>
		</c:forEach>
	 <!-- 引用弹框 -->
	<div style="display: none;" id="quote">
		<script type="text/javascript">
		function recommend(){
	     if($("#recommendForm").validationEngine("validate")){
		 var year=$("#recommendYear").val();
		 var url = "<s:url value='/gyxjgl/majorCourse/saveRecommendData'/>?planYear="+year+"&targetYear=${year}";
		 var msg="确认引用"+year+"年的课程专业关系吗？";
	     jboxConfirm(msg,function(){
	    	 jboxStartLoading();
		  jboxPost(url,null,function(resp){
			 window.parent.frames["mainIframe"].window.search();
			 jboxEndLoading();
		  },null,true);
	     });
	}
 }
		</script>
		      <form id="recommendForm" style="position: relative;">
				<h1 style="text-align: left;">您当前正在维护<font color="red">${year }</font>年各专业课程数据</h1><br>
			    <h2 style="text-align: left;">请选择需要引用的数据年份:
			       <select id="recommendYear" class="validate[required]">
			       <option value="">请选择</option>
			       <c:forEach items="${yearList }" var="year">
			            <option value="${year }">${year }</option>
			       </c:forEach>
			    </select>
			    </h2>
			   </form>
			<div style=" text-align: center;margin-top: 20px;">
			    <input type="button" class="search" value="确&#12288;定" onclick="recommend();">
			    <input type="button" class="search" value="关&#12288;闭" onclick="jboxContentClose();">
			</div>
		</div>
		<!-- 删除引用弹框 -->
		<div style="display: none;" id="delQuote">
		<script type="text/javascript">
		function delRecommend(){
	     if($("#delRecommendForm").validationEngine("validate")){
		 var url = "<s:url value='/gyxjgl/majorCourse/delRecommendData'/>?planYear="+year;
		 var msg="确认删除"+year+"年的课程专业关系吗？";
	     jboxConfirm(msg,function(){
	    	jboxStartLoading();
		 jboxPost(url,null,function(resp){
			 window.parent.frames["mainIframe"].window.search();
			 jboxEndLoading();
		 },null,true);
	     });
	}
 }
		</script>
		      <form id="delRecommendForm" style="position: relative;">
			    <h2 style="text-align: left;">请选择需要删除的引用数据年份:
			       <select id="delRecommendYear" class="validate[required]">
			       <option value="">请选择</option>
			       <c:forEach items="${delYearList }" var="year">
			            <option value="${year }">${year }</option>
			       </c:forEach>
			    </select>
			    </h2>
			   </form>
			<div style=" text-align: center;margin-top: 20px;">
			    <input type="button" class="search" value="确&#12288;定" onclick="delRecommend();">
			    <input type="button" class="search" value="关&#12288;闭" onclick="jboxContentClose();">
			</div>
		</div>
		</div>
	</div>

</body>
</html>