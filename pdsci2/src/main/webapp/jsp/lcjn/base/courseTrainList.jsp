<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	td.appoint{cursor:pointer;}
	#searchForm input[type='text']{width:133px;}
</style>
<script type="text/javascript">
	$(function(){
		$('.appoint').bind('click',function(){
			var courseFlow = $(this).closest("tr").attr("id");
			var courseName = $(this).closest("tr").find("td").eq(1).text();
			var url = "<s:url value='/lcjn/base/courseTrainManage?initFlag=Y&courseFlow='/>"+courseFlow+"&courseName="+encodeURI(encodeURI(courseName));
			jboxPostLoad("initCont",url,null,true);
		});
	});
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function addInfo(courseFlow,flag){
		var title = courseFlow == ""?"新增":"编辑";
		title = flag == "view"?"查看":title;
		var url = "<s:url value='/lcjn/base/addCourseTrain?courseFlow='/>"+courseFlow+"&flag="+flag;
		jboxOpen(url, title,700,600,false);
	}
	function changeInfo(){
		location.href ="<s:url value="/lcjn/base/courseTrainView"/>";
	}
	function releasedInfo(courseFlow){
		jboxConfirm("是否确认发布该课程？一旦发布则无法修改课程信息", function(){
			var url = "<s:url value='/lcjn/base/releasedInfo?courseFlow='/>"+courseFlow;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});
	}
	function delInfo(courseFlow,isReleased){
		jboxConfirm("是否确认删除课程信息？", function(){
			var url = "<s:url value='/lcjn/base/delCourseTrain?courseFlow='/>"+courseFlow+"&isReleased="+isReleased;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
					location.reload();
				}
			}, null, true);
		});
	}
	function queryQrCode(courseFlow){
		jboxOpen("<s:url value='/jsp/lcjn/base/courseQrCode.jsp'/>?courseFlow=" + courseFlow,'课程信息二维码',125,125);
	}
	function checkBDDate(dt) {
		var dates = $(':text', $(dt).closest("span"));
		if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
			jboxTip("培训开始时间不能大于结束时间！");
			dt.value = "";
		}
	}
</script>
</head>
<body id="initCont">
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/lcjn/base/courseTrainList"/>" method="post">
			<div class="choseDivNewStyle">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<span></span>课程名称：
				<input type="text" name="courseName" value="${param.courseName}">
				<span style="padding-left:20px;"></span>培训时间：
						<span>
							<input type="text" class="select" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainStartDate" value="${param.trainStartDate}" onchange="checkBDDate(this)"/>
							—— <input type="text" class="select" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainEndDate" value="${param.trainEndDate}" onchange="checkBDDate(this)"/>
						</span>
				<span style="padding-left:20px;"></span>发布状态：
				<select name="isReleased" style="width:137px;" class="select">
					<option/>
					<option value="Y" ${param.isReleased eq 'Y'?'selected':''}>已发布</option>
					<option value="N" ${param.isReleased eq 'N'?'selected':''}>未发布</option>
				</select>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
				<div class="newStyleSubDiv">
					<input type="button" class="search" value="新&#12288;增" onclick="addInfo('')"/>
					<input type="button" class="search" value="切&#12288;换" onclick="changeInfo()"/>
				</div>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:70px;">序号</th>
				<th style="width:100px;">课程名称</th>
				<th style="width:120px;">预约开放时间</th>
				<th style="width:100px;">预约人员容量</th>
				<th style="width:100px;">预约剩余人数</th>
				<th style="width:140px;">培训时间</th>
				<th style="width:140px;">培训地点</th>
				<th style="width:120px;">授课老师</th>
				<th style="width:100px;">二维码</th>
				<th style="width:140px;">操作</th>
			</tr>
			<c:forEach items="${dataList}" var="info" varStatus="i">
				<tr id="${info.COURSE_FLOW}">
					<td class="appoint">${i.index + 1}</td>
					<td class="appoint">${info.COURSE_NAME}</td>
					<td class="appoint">${info.APPOINT_START_TIME}~<br/>${info.APPOINT_END_TIME}</td>
					<td class="appoint">${info.COURSE_PEOPLE_NUM}</td>
					<td class="appoint">${info.DYYNUM}</td>
					<td class="appoint" title="<table><tr><th><c:if test="${empty info.TRAIN_START_DATE_LIST}">暂无培训时间</c:if><c:forEach items="${fn:split(info.TRAIN_START_DATE_LIST,',')}" var="startDate" varStatus="i">${startDate}&nbsp;${fn:split(info.START_TIME_LIST,',')[i.index]}
						<c:forEach items="${fn:split(info.TRAIN_END_DATE_LIST,',')}" var="endDate" varStatus="j"><c:if test="${i.index eq j.index}">${not empty endDate?'~':''}${endDate}&nbsp;${fn:split(info.END_TIME_LIST,',')[j.index]}<br/></c:if></c:forEach></c:forEach>
					</th></tr></table>">
						<c:forEach items="${fn:split(info.TRAIN_START_DATE_LIST,',')}" var="startDate" varStatus="i">
							<c:if test="${i.first}">${startDate}&nbsp;${fn:split(info.START_TIME_LIST,',')[i.index]}</c:if>
						</c:forEach>
						<c:forEach items="${fn:split(info.TRAIN_END_DATE_LIST,',')}" var="endDate" varStatus="i">
							<c:if test="${i.first}">${not empty endDate?'~':''}<br/>${endDate}&nbsp;${fn:split(info.END_TIME_LIST,',')[i.index]}</c:if>
							<c:if test="${i.last}">${i.count gt 1?'<br/>......':''}</c:if>
						</c:forEach>
					</td>
					<td class="appoint">${info.COURSE_ADDRESS}</td>
					<td class="appoint" title="<table><tr><th><c:if test="${empty info.TEACHER_LIST}">暂无授课老师</c:if><c:forEach items="${fn:split(info.TEACHER_LIST,',')}" var="teacher" varStatus="i"><c:if test="${!i.first}"><br/></c:if>${teacher}&nbsp;
						<c:forEach items="${fn:split(info.COST_LIST,',')}" var="cost" varStatus="j"><c:if test="${j.index eq i.index}">${cost}</c:if></c:forEach></c:forEach>
					</th></tr></table>">
						<c:forEach items="${fn:split(info.TEACHER_LIST,',')}" var="teacher" varStatus="i">
							<c:if test="${i.index lt 2}">
								<c:if test="${!i.first}"><br/></c:if>${teacher}&nbsp;
								<c:forEach items="${fn:split(info.COST_LIST,',')}" var="cost" varStatus="j">
									<c:if test="${j.index eq i.index}">${cost}</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<c:forEach items="${fn:split(info.TEACHER_LIST,',')}" var="teacher" varStatus="i">
							<c:if test="${i.last}">${i.count gt 2?'<br/>.....':''}</c:if>
						</c:forEach>
					</td>
					<td><a onclick="queryQrCode('${info.COURSE_FLOW}')" style="cursor:pointer;"><c:out value="查看"/></a></td>
					<td>
						<c:if test="${info.IS_RELEASED eq 'Y'}">
							<a href="javascript:;" style="color:grey;"><c:out value="已发布"/></a>
							<a onclick="addInfo('${info.COURSE_FLOW}','view');" style="cursor:pointer;color:#4195c5;"><c:out value="查看"/></a>
							<c:set var="sum" value="0" />
							<c:forEach items="${fn:split(info.TRAIN_START_TIME_LIST,',')}" var="startTime" varStatus="i">
								<c:if test="${startTime le pdfn:getCurrDateTime('yyyy-MM-dd HH:mm')}"><c:set var="delFlag" value="N" /></c:if>
								<c:if test="${startTime gt pdfn:getCurrDateTime('yyyy-MM-dd HH:mm')}"><c:set var="sum" value="${sum +1}" /></c:if>
								<c:if test="${i.last && i.count eq sum}"><c:set var="delFlag" value="Y" /></c:if>
							</c:forEach>
							<c:if test="${!delFlag || delFlag eq 'Y'}">
								<a onclick="delInfo('${info.COURSE_FLOW}','${info.IS_RELEASED}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
							</c:if>
							<c:if test="${delFlag && delFlag eq 'N'}">
								<a href="javascript:;" style="color:grey;"><c:out value="删除"/></a>
							</c:if>
						</c:if>
						<c:if test="${info.IS_RELEASED ne 'Y'}">
							<a onclick="releasedInfo('${info.COURSE_FLOW}');" style="cursor:pointer;color:#4195c5;"><c:out value="发布"/></a>
							<a onclick="addInfo('${info.COURSE_FLOW}');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
							<a onclick="delInfo('${info.COURSE_FLOW}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div style="margin-top:65px;">
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>	
</html>