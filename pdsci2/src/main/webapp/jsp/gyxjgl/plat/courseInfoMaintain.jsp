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
function edit(courseFlow){
	  jboxOpen("<s:url value='/gyxjgl/course/manage/currCourse'/>?courseFlow="+courseFlow,"课程编辑",850,420);
	}
function daoRu(){
	 jboxOpen("<s:url value='/gyxjgl/course/manage/importCourse'/>","导入",600,200);
	}
function add(){
	 jboxOpen("<s:url value='/gyxjgl/course/manage/currCourse'/>","课程添加",850,420);
}
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
function deletes(courseFlow){
	jboxConfirm('确认删除吗?',function(){
	var url="<s:url value='/gyxjgl/course/manage/courseDelete'/>?courseFlow="+courseFlow;
	 jboxPost(url,null,function(){
		 toPage($("#currentPage").val());//window.location.href=window.location.href;
		},null,true);  
	});
}
function courseExport(){
	jboxConfirm('确认导出吗?',function(){
	var url="<s:url value='/gyxjgl/course/manage/courseExport'/>";
	jboxTip("导出中…………");
	jboxSubmit($('#recSearchForm'),url,null, null,false);
	jboxEndLoading();
	});
}
	function uploadOutlineInfo(courseFlow,formType){
		jboxOpen("<s:url value='/gyxjgl/course/manage/uploadOutlineInfo'/>?courseFlow="+courseFlow+"&formType="+formType,"大纲教材录入",800,500);
	}
	function editForm(courseFlow){
		jboxOpen("<s:url value='/gyxjgl/course/manage/editApprovalForm'/>?courseFlow="+courseFlow,"教学审批表",660,500);
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
<c:if test="${param.role ne 'skz'}">
	$(function(){
		$("#assumeOrgName").likeSearchInit({
		});
	});
</c:if>
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width: 100%;margin: 10px 0px;border: none;">
			<tr>
				<td style="border: none;line-height: 260%;">
					<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/course/manage/courseList'/>">
						<input type="hidden" name="currentPage" id="currentPage"/>
						<input type="hidden" name="role" value="${param.role}"/>
						&nbsp;所属学年：<input type="text" name="courseSession" value="${param.courseSession}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;">
						<c:if test="${param.role ne 'skz'}">
							&#12288;课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width:137px;" onchange="search();"/>
							&#12288;课程代码：<input type="text" name="courseCode" value="${param.courseCode}" style="width:137px;" />
							<br/>
							&nbsp;授课层次：<select style="width: 141px;" name="gradationId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
								<option name="${trainType.dictId}" value="${trainType.dictId}" <c:if test="${param.gradationId==trainType.dictId}">selected="selected"</c:if>>${trainType.dictName}</option>
							</c:forEach>
							</select>
							&#12288;承担单位：<input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}" style="width: 137px;"/>&#12288;&nbsp;
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:290px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
									<c:forEach items="${orgMap}" var="org">
										<p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
									</c:forEach>
								</div>
							</div>
							<input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
						</c:if>
					   	<input type="button"  value="查&#12288;询" class="search" onclick="toPage();"/>
						<c:if test="${param.role ne 'skz'}"><br/>
							<input type="button"  value="新&#12288;增" class="search" onclick="add();"/>
							<input type="button"  value="导&#12288;入" class="search" onclick="daoRu();"/>
							<input type="button"  value="导&#12288;出" class="search" onclick="courseExport();"/>
						</c:if>
					</form>
				</td>
			</tr>
		</table>
		<table class="basic" width="100%">
			<tr style="font-weight: bold;">
				<td style="width:4%;text-align: center;padding-left: 0px;">所属学年</td>
				<td style="width:6%;text-align: center;padding-left: 0px;">代码</td>
				<td style="width:12%;text-align: center;padding-left: 0px;">中文名称</td>
				<td style="width:6%;text-align: center;padding-left: 0px;">授课层次</td>
				<td style="width:6%;text-align: center;padding-left: 0px;">课程类别</td>
				<td style="width:4%;text-align: center;padding-left: 0px;">容纳人数</td>
				<td style="width:4%;text-align: center;padding-left: 0px;">所属模块</td>
				<td style="width:10%;text-align: center;padding-left: 0px;">承担单位</td>
				<td style="width:4%;text-align: center;padding-left: 0px;">学分</td>
				<td style="width:4%;text-align: center;padding-left: 0px;">总学时</td>
				<td style="width:5%;text-align: center;padding-left: 0px;">课程操作</td>
				<td style="width:14%;text-align: center;padding-left: 0px;">教学文件</td>
			</tr>
			
			<c:forEach items="${coursesList}" var="course">
				<tr>
						<td style="text-align: center;padding-left: 0px;">${course.courseSession}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseCode}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseName}</td>
						<td style="text-align: center;padding-left: 0px;">${course.gradationName}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseTypeName}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseUserCount}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseMoudleName}</td>
						<td style="text-align: center;padding-left: 0px;">${course.assumeOrgName}</td>
						<td style="text-align: center;padding-left: 0px;">${course.courseCredit}</td>
						<td style="text-align: center;padding-left: 0px;">${course.coursePeriod}</td>
						<td style="text-align: center;padding-left: 0px;">
							<a style="cursor: pointer; color: blue;" onclick="edit('${course.courseFlow}');">编辑</a>
							<c:if test="${param.role ne 'skz'}">
								<a style="cursor: pointer; color: blue;" onclick="deletes('${course.courseFlow}');">删除</a>
							</c:if>
						</td>
						<td style="text-align: center;padding-left: 0px;">
							<a style="cursor: pointer; color: blue;" onclick="uploadOutlineInfo('${course.courseFlow}','${xjEduMaterialTypeEnumKcdg.id}');">课程大纲</a>
							<a style="cursor: pointer; color: blue;" onclick="editForm('${course.courseFlow}');">课程审批表</a>
							<a style="cursor: pointer; color: blue;" onclick="uploadOutlineInfo('${course.courseFlow}','${xjEduMaterialTypeEnumKcjdb.id}');">课程进度表</a>
						</td>
				</tr>
			</c:forEach>
			<c:if test="${empty coursesList}">
				<tr>
					<td colspan="15" >无记录！</td>
				</tr>
			</c:if>
		</table>
		<div>
	       	<c:set var="pageView" value="${pdfn:getPageView(coursesList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>	 
	    </div>
	</div>
</div>
</body>
</html>