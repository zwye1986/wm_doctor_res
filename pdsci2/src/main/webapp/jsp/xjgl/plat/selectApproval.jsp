<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
function courseMaintain(period, majorId, userFlow){
	var url = "<s:url value='/xjgl/student/course/courseMaintain'/>?period=" + period + "&majorId=" + majorId + "&userFlow=" + userFlow;
  	jboxOpen(url,"选课维护", 850, 500, false);
}

function search(){
	if($("#searchParam_Major").val()==""){
		$("#result_Major").val("");
	}
	$("#searchForm").submit();	
}

function toPage(page){
	$("#currentPage").val(page);
	jboxStartLoading();
	search();
}

/**
 * 专业检索
 */
$(document).ready(function(){
	loadMajorJson();
});
function loadMajorJson(){
	var majorArray = new Array();
	var url = "<s:url value='/xjgl/majorCourse/searchMajorJson'/>";
	jboxPost(url,null,function(data){
		if(data!=null){
			for (var i = 0; i < data.length; i++) {
				var dictId=data[i].dictId;
				if(data[i].dictId==null){
					dictId="";
				}
				var dictName=data[i].dictName;
				if(data[i].dictName==null){
					dictName="";
				}
				majorArray[i]=new Array(dictId, dictName, dictId);
				if($("#result_Major").val() == dictId){
					$("#searchParam_Major").val(dictName);	
				}
			}
		}
	},null,false);
	$("#searchParam_Major").suggest(majorArray,{
		attachObject:'#suggest_Major',
		dataContainer:'#result_Major',
		triggerFunc:function(majorId){
	
		},
	    enterFunc:function(){
	  
	    }
	});
}
function adjustResults() {
	$("#suggest_Major").css("left",$("#searchParam_Major").offset().left);
	$("#suggest_Major").css("top",$("#searchParam_Major").offset().top+$("#searchParam_Major").outerHeight());
}
function sysCfgUpdate(startCode,endCode){
	jboxConfirm('确认保存吗?',function(){
	var startValue=$("#start").val();
	var endValue=$("#end").val();
	var url="<s:url value='/xjgl/course/manage/sysCfgUpdate'/>?startCode="+startCode+"&startValue="+startValue+"&endCode="+endCode+"&endValue="+endValue;
	 jboxPost(url,null,function(){
		 window.parent.frames['mainIframe'].window.toPage();
	},null,true); 
	});
}
function checkYear(obj) {
	var dates = $(':text', $(obj).closest("span"));
	if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
		jboxTip("开始时间不能大于结束时间！");
		obj.value = "";
	}
}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width: 100%;margin: 10px 0px;border: none;">
			<tr>
				<td style="border: none;line-height: 260%;">
					<form id="searchForm" method="post" action="<s:url value="/xjgl/student/course/selectApproval?from=${param.from }"/>">
						<input id="currentPage" type="hidden" name="currentPage"/>
						入学年级：<input type="text" name="period" value="${param.period}" onClick="WdatePicker({dateFmt:'yyyy'})" style="width:137px;margin:0px;" readonly="readonly"/>&#12288;
						姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;"/>&#12288;
						专&#12288;&#12288;业：<input  id="searchParam_Major"  placeholder="输入专业名称/代码"  style="width: 137px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
			        	<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 300px;"></div>
			        	<input type="hidden" id="result_Major" name="majorId" value="${param.majorId }"/>&#12288;
						学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width: 137px;"/><br/>
						<!-- 
						身份证号：<input type="text" name="idNo" value="${param.idNo}"/>&#12288;
						 -->
						学年学期：<input type="text" name="studentPeriod" value="${param.studentPeriod}" onClick="WdatePicker({dateFmt:'yyyy'})" style="width:137px;margin:0px;" readonly="readonly"/>&#12288;
					   培养层次：<select name="trainTypeId" style="width: 141px;">
							<option/>
							<c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
								<c:if test="${trainType.dictId ne '3'}">
									<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
								</c:if>
							</c:forEach>
						</select>
						&nbsp;<input type="button" class="search" value="查&#12288;询" onclick="search();"/>&#12288;
						&nbsp;<font color="red">注意：红色表示补选课程!</font>
						<c:if test="${empty param.from }">
					    <br/><span style="float: right;">
					           开放选课时间：
							<input type="text" style="width:137px;margin:0px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="start" value="${start.cfgValue}" onchange="checkYear(this)"/>
							&nbsp;-&nbsp;
							<input type="text" style="width:137px;margin:0px;" id="end" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${end.cfgValue}" onchange="checkYear(this)"/>
							<input type="button" class="search"  onclick="sysCfgUpdate('choose_course_start_time','choose_course_end_time');" value="确&#12288;认" />
					    </span>
					    </c:if>
					</form>
				</td>
			</tr>
		</table>
		<table class="xllist" style="width:100%;">
			<tr>
				<th rowspan="2" width="50px;">入学<br/>年级</th>
				<th rowspan="2" width="75px;">学号</th>
				<th rowspan="2" width="50px;">姓名</th>
				<th rowspan="2" width="100px;">[专业代码]专业</th>
				<th rowspan="2" width="45px;">学年<br/>学期</th>
				<th rowspan="2" width="150px;">[课程代码]学位课程</th>
				<th rowspan="2" width="160px;">[课程代码]专业选修课</th>
				<th rowspan="2" width="160px;">[课程代码]公共选修课程</th>
				<th rowspan="2" width="160px;">[课程代码]专业必选课程</th>
				<th rowspan="2" width="160px;">[课程代码]公共必选课程</th>
				<th colspan="2">合计</th>
				<c:if test="${empty param.from }">
				<th rowspan="2"  width="80px;">操作</th>
				</c:if>
			</tr>
			<tr style="font-weight: bold;">
				<th width="45px;">总学时</th>
				<th width="45px;">总学分</th>
			</tr>
			<c:forEach items="${eduUserExtList}" var="eduUserExt" >
			<c:set var="studentPeriodCount"  value="${empty studentPeriodCountMap[eduUserExt.userFlow]?'1':studentPeriodCountMap[eduUserExt.userFlow]}" />
			<c:set var="periodMap" value="${studentCourseMap[eduUserExt.userFlow]}" />
			<c:if test="${!empty periodMap }">
				<c:forEach items="${periodMap}" var="period" varStatus="status">
					<c:set var="coursePeriodSum" value="0"/>
					<c:set var="courseCreditSum" value="0"/>
					<tr>	
						<c:if test="${status.first }">
							<td rowspan="${studentPeriodCount }">${empty eduUserExt.period?'--':eduUserExt.period}</td>
							<td rowspan="${studentPeriodCount }">${empty eduUserExt.sid?'--':eduUserExt.sid}</td>
							<td rowspan="${studentPeriodCount }">${empty eduUserExt.sysUser.userName?'--':eduUserExt.sysUser.userName}</td>
							<c:set var="major" value="[${eduUserExt.majorId}]${eduUserExt.majorName}"/>
							<td rowspan="${studentPeriodCount }" style="text-align: left;padding-left: 5px;">${empty eduUserExt.majorId?'--':major}</td>
						</c:if>
						<td>${period.key}</td>
						<c:set var="courseTypeMap" value="${period.value}"/>
						<c:set var="degreeEscList" value="${courseTypeMap[xjglCourseTypeEnumDegree.id] }"/>
						<td <c:if test="${!empty degreeEscList}">style="text-align: left;"</c:if>>
							<c:forEach items="${degreeEscList}" var="studentCourse" varStatus="status">
								<font <c:if test="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if> >[${studentCourse.courseCode}]${studentCourse.courseName}</font><br/>
								<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
								<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
							</c:forEach>
							<c:if test="${empty degreeEscList}">--</c:if>
						</td>
						<c:set var="optionalEscList" value="${courseTypeMap[xjglCourseTypeEnumOptional.id] }"/>
						<td <c:if test="${!empty optionalEscList}">style="text-align: left;"</c:if>>
							<c:forEach items="${optionalEscList}" var="studentCourse" varStatus="status">
								<font <c:if test="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if> >[${studentCourse.courseCode}]${studentCourse.courseName}</font><br/>
								<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
								<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
							</c:forEach>
							<c:if test="${empty optionalEscList}">--</c:if>
						</td>
						<c:set var="pulicEscList" value="${courseTypeMap[xjglCourseTypeEnumPublic.id] }"/>
						<td <c:if test="${not empty pulicEscList}">style="text-align: left;"</c:if>>
							<c:forEach items="${pulicEscList}" var="studentCourse" varStatus="status">
								<font <c:if test="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if> >[${studentCourse.courseCode}]${studentCourse.courseName}</font><br/>
								<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
								<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
							</c:forEach>
							<c:if test="${empty pulicEscList}">--</c:if>
						</td>
						<c:set var="optionalNeedList" value="${courseTypeMap[xjglCourseTypeEnumOptionalNeed.id] }"/>
						<td <c:if test="${not empty optionalNeedList}">style="text-align: left;"</c:if>>
							<c:forEach items="${optionalNeedList}" var="studentCourse" varStatus="status">
								<font <c:if test="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if> >[${studentCourse.courseCode}]${studentCourse.courseName}</font><br/>
								<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
								<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
							</c:forEach>
							<c:if test="${empty optionalNeedList}">--</c:if>
						</td>
						<c:set var="publicNeedList" value="${courseTypeMap[xjglCourseTypeEnumPublicNeed.id] }"/>
						<td <c:if test="${not empty publicNeedList}">style="text-align: left;"</c:if>>
							<c:forEach items="${publicNeedList}" var="studentCourse" varStatus="status">
								<font <c:if test="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if> >[${studentCourse.courseCode}]${studentCourse.courseName}</font><br/>
								<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
								<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
							</c:forEach>
							<c:if test="${empty publicNeedList}">--</c:if>
						</td>
						<td id="${eduUserExt.userFlow}PeriodSum">
							<%--<fmt:formatNumber type="number" value="${coursePeriodSum }" maxFractionDigits="1"/>--%>
							<script>
								var v = "${coursePeriodSum}"==""?"":Number('${coursePeriodSum}');
								$("#${eduUserExt.userFlow}PeriodSum").html(v);
							</script>
						</td>
						<td id="${eduUserExt.userFlow}CreditSum">
							<%--<fmt:formatNumber type="number" value="${courseCreditSum }" maxFractionDigits="1"/>--%>
								<script>
									var v = "${courseCreditSum}"==""?"":Number('${courseCreditSum}').toFixed(1);
									$("#${eduUserExt.userFlow}CreditSum").html(v);
								</script>
						</td>
						<c:if test="${empty param.from }">
						<td ><a style="cursor: pointer; color: blue;" onclick="courseMaintain('${period.key}','${eduUserMap[eduUserExt.userFlow].majorId}','${eduUserExt.userFlow}');">选课维护</a></td>
						</c:if>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty  periodMap}">
				<tr>
					<td>${empty eduUserExt.period?'--':eduUserExt.period}</td>
					<td >${empty eduUserExt.sid?'--':eduUserExt.sid}</td>
					<td>${empty eduUserExt.sysUser.userName?'--':eduUserExt.sysUser.userName}</td>
					<c:set var="major" value="[${eduUserExt.majorId}]${eduUserExt.majorName}"/>
					<td  style="text-align: left;padding-left: 5px;">${empty eduUserExt.majorId?'--':major}</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<c:if test="${empty param.from }">
					<td></td>
					</c:if>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${empty eduUserExtList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
		</table>
       	<c:set var="pageView" value="${pdfn:getPageView(eduUserExtList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>	 
	</div>
</div>
</body>
</html>