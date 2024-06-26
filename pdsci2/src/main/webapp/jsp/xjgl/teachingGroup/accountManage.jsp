<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(function(){
		loadCourseList();
	});
	function toPage(page){
		if($("#searchParam_Course").val()==""){
			$("#result_Course").val("");
		}
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function addInfo(userFlow){
		var url = "<s:url value='/xjgl/teachingGroup/addCourseGroup?userFlow='/>"+userFlow;
		jboxOpen(url, '课程维护',600,360,true);
	}
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
	function loadCourseList(){
		var courseArray = new Array();
		var url = "<s:url value='/xjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='planYear']").val();
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
					triggerFunc:function(courseFlow){},
					enterFunc:function(courseFlow){}
				});
				jboxEndLoading();
			}
		},null,false);
	}
	function reload(){
		$("#searchParam_Course").val("");//清空上前面学年的课程信息
		$("#result_Course").val("");
		loadCourseList();//重新加载某学年的课程
	}
	function toAdd(){
		jboxOpen("<s:url value='/jsp/xjgl/teachingGroup/addAccount.jsp'/>", "新增",500,200);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/xjgl/teachingGroup/accountManage"/>" method="post">
			<div class="choseDivNewStyle">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<span></span>授课组账户：
				<input type="text" value="${param.userCode}" name="userCode" style="width: 137px;">
				<span style="margin-left:20px;"></span>所属学年：
				<input class="validate[required]" name="planYear" type="text" value="${param.planYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" onchange="reload()"></td>
				<span style="margin-left:20px;"></span>课程检索：
				<input id="searchParam_Course" placeholder="输入课程名称/代码" class="inputText" style="width: 137px;text-align: left;" onkeydown="adjustResults();" onfocus="adjustResults();"/>
				<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
				<input type="hidden" id="result_Course" name="courseFlow" value="${param.courseFlow }"/>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
				<input type="button" class="search" value="新&#12288;增" onclick="toAdd(1)"/>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr style="font-weight: bold;">
				<td style="width:80px;">序号</td>
				<td style="width:140px;">授课组账户</td>
				<td style="width:140px;">授课组账户名称</td>
				<td style="width:140px;">课程代码</td>
				<td style="width:140px;">课程名称</td>
				<td style="width:140px;">操作</td>
			</tr>
			<c:forEach items="${dataList}" var="info" varStatus="i">
				<tr>
					<td>${i.index + 1}</td>
					<td>${info.USER_CODE}</td>
					<td>${info.USER_NAME}</td>
					<td>${info.COURSE_CODE}</td>
					<td>${info.COURSE_NAME}</td>
					<td>
						<a onclick="addInfo('${info.USER_FLOW}');" style="cursor:pointer;color:blue;"><c:out value="课程维护"/></a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty dataList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
		</table>
		<div style="margin-top:65px;">
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>	
</html>