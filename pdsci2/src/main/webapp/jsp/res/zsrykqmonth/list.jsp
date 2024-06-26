<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
</style>
<script type="text/javascript">
	$(function(){
		<c:forEach items="${doctorTypeIdList}" var="type">
		$("#${type}").attr("checked","checked");
		</c:forEach>
	})
	function search(){
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		if(startDate==""||startDate==undefined)
		{
			jboxTip("开始时间不得为空！！");
			return false;
		}
		if(endDate==""||endDate==undefined)
		{
			jboxTip("结束时间不得为空！！");
			return false;
		}
		if(startDate!=""&&endDate!=""&&endDate<startDate)
		{
			jboxTip("开始时间不得大于结束时间！！");
			return false;
		}
		var url = '<s:url value="/res/zsrykqmonth/list/time/${role}"/>';
		jboxPost(url,$("#searchForm").serialize(),function(resp){
			$("#content2").html(resp);
		},null,false);
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	function detail(doctorFlow){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		jboxOpen('<s:url value="/res/zsrykqmonth/detail"/>?doctorFlow='+doctorFlow+"&startDate="+startDate+"&endDate="+endDate,"详情",1400,600,true);
	}
	function exportDoc(){
		if(${empty doctorList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/zsrykqmonth/exportExl/${role}'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}
	function changeList(obj){
		if($(obj).attr("checked")=="checked"){
			$(".miss").each(function(){
				if(parseFloat($(this).text())==0){
					$(this).parent().hide();
				}
			})
		}else{
			$(".miss").each(function(){
				if(parseFloat($(this).text())==0){
					$(this).parent().show();
				}
			})
		}
	}
	function changeList2(obj){
		if($(obj).attr("checked")=="checked"){
			$(".miss").each(function(){
				if(parseFloat($(this).next().text())-parseFloat($(this).text())==0){
					$(this).parent().hide();
				}
			})
		}else{
			$(".miss").each(function(){
				if(parseFloat($(this).next().text())-parseFloat($(this).text())==0){
					$(this).parent().show();
				}
			})
		}
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
						<div class="inputDiv" style="max-width: 280px;min-width: 280px;">
							考勤时间：
							<input class="qtext" name="startDate" id="startDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"
								   value="${empty param.startDate?startDate:param.startDate}" class="input" style="width: 80px;"/>~<input class="qtext" name="endDate" id="endDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
																																		  value="${empty param.endDate?endDate:param.endDate}" class="input" style="width: 80px;"/>
						</div>
						<c:if test="${role ne 'doctor'}">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
						</div>
						<div class="inputDiv">
							结业时间：
							<input class="qtext" name="graduationYear" type="text" onclick="WdatePicker({dateFmt:'yyyy'})"  readonly="readonly"
								   value="${param.graduationYear}"/>
						</div>
						<div class="inputDiv">
							入培时间：
							<input class="qtext" name="inHosDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  readonly="readonly"
								   value="${param.inHosDate}"/>
						</div>
						<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
							<label class="qlable">培训年限：</label>&#12288;&#12288;&nbsp;&nbsp;&nbsp;
							<select name="trainingYears" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
									<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">所属科室：</label>
							<select name="deptFlow" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
								</c:forEach>
							</select>
						</div>
						<c:if test="${role ne 'speBase'}">
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<select name="trainingSpeId" class="qselect" >
								<option  value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						</c:if>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 99%;max-width: 99%;width: 99%;text-align: left;margin-left:23px; ">
							学员类型：
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}" id="${type.dictId}"
												  <c:if test="${empty param.doctorTypeIdList}">checked</c:if>>${type.dictName}</label>
								</c:forEach>
							&#12288;&#12288;&#12288;<label><input type="checkbox" name="onlyMiss" value="Y" onclick="changeList(this)" >只显示旷工人员</label>
							&#12288;<label><input type="checkbox" name="onlyAbsence" value="Y" onclick="changeList2(this)">只显示请假人员</label>
						</div>
						</c:if>
						<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
								<input type="button" class="searchInput" value="导&#12288;出" onclick="exportDoc();"/>
						</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;">考勤时间</td>
						<td style="text-align: center;padding: 0px;">姓名</td>
						<td style="text-align: center;padding: 0px;">年级</td>
						<td style="text-align: center;padding: 0px;">所属科室</td>
						<td style="text-align: center;padding: 0px;">培训专业</td>
						<td style="text-align: center;padding: 0px;">培训年限</td>
						<td style="text-align: center;padding: 0px;">入培时间</td>
						<td style="text-align: center;padding: 0px;">结业时间</td>
						<td style="text-align: center;padding: 0px;">病假</td>
						<td style="text-align: center;padding: 0px;">事假</td>
						<td style="text-align: center;padding: 0px;">带薪年假</td>
						<td style="text-align: center;padding: 0px;">婚假</td>
						<td style="text-align: center;padding: 0px;">产假</td>
						<td style="text-align: center;padding: 0px;">陪产假</td>
						<td style="text-align: center;padding: 0px;">计生假</td>
						<td style="text-align: center;padding: 0px;">出国</td>
						<td style="text-align: center;padding: 0px;">进修</td>
						<td style="text-align: center;padding: 0px;">脱产读研</td>
						<td style="text-align: center;padding: 0px;">放射假</td>
						<td style="text-align: center;padding: 0px;">旷工</td>
						<td style="text-align: center;padding: 0px;">请假合计</td>
						<td style="text-align: center;padding: 0px;">出勤</td>
						<td style="text-align: center;padding: 0px;">考勤天数</td>
						<td style="text-align: center;padding: 0px;">出勤率</td>
						<td style="text-align: center;padding: 0px;">操作</td>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr>
							<c:set var="time1" value="${empty param.startDate?startDate:param.startDate}"></c:set>
							<c:set var="time2" value="${empty param.endDate?endDate:param.endDate}"></c:set>
							<c:set var="time3" value="${time1}~${time2}"></c:set>
							<c:set var="content" value="${time1 eq time2?time1:time3}"></c:set>
							<td style="text-align: center;padding: 0px;">${content}</td>
							<td style="text-align: center;padding: 0px;">${doctor.doctorName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;padding: 0px;">
							<c:choose>
								<c:when test="${doctor.doctorTypeId eq 'Company'}">
									${doctor.departMentName}
								</c:when>
								<c:when test="${doctor.doctorTypeId eq 'Social'}">
									--
								</c:when>
								<c:otherwise>
									${doctor.workOrgName}
								</c:otherwise>
							</c:choose>
							</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingSpeName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingYears}</td>
							<td style="text-align: center;padding: 0px;">${doctor.inHosDate}</td>
							<td style="text-align: center;padding: 0px;">${doctor.graduationYear}</td>
							<c:set value="${doctor.doctorFlow}02" var="key0"></c:set>
							<c:set value="${doctor.doctorFlow}03" var="key1"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key0]+resultMap[key1]/2}</td>
							<c:set value="${doctor.doctorFlow}04" var="key4"></c:set>
							<c:set value="${doctor.doctorFlow}05" var="key5"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key4]+resultMap[key5]/2}</td>
							<c:set value="${doctor.doctorFlow}06" var="key6"></c:set>
							<c:set value="${doctor.doctorFlow}07" var="key7"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key6]+resultMap[key7]/2}</td>
							<c:set value="${doctor.doctorFlow}10" var="key10"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key10]+0.0}</td>
							<c:set value="${doctor.doctorFlow}11" var="key11"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key11]+0.0}</td>
							<c:set value="${doctor.doctorFlow}12" var="key12"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key12]+0.0}</td>
							<c:set value="${doctor.doctorFlow}13" var="key13"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key13]+0.0}</td>
							<c:set value="${doctor.doctorFlow}15" var="key15"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key15]+0.0}</td>
							<c:set value="${doctor.doctorFlow}16" var="key16"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key16]+0.0}</td>
							<c:set value="${doctor.doctorFlow}18" var="key18"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key18]+0.0}</td>
							<c:set value="${doctor.doctorFlow}19" var="key19"></c:set>
							<c:set value="${doctor.doctorFlow}22" var="key22"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;">${resultMap[key19]+resultMap[key22]/2}</td>
							<c:set value="${doctor.doctorFlow}20" var="key20"></c:set>
							<c:set value="${doctor.doctorFlow}21" var="key21"></c:set>
							<td style="text-align: center;padding: 0px;width: 4%;" class="miss">${resultMap[key20]+resultMap[key21]/2}</td>
							<td style="text-align: center;padding: 0px;width: 4%;" class="absence">${sumMap[doctor.doctorFlow]+0.0}</td>
							<td style="text-align: center;padding: 0px;width: 4%;">${sumAttendantMap[doctor.doctorFlow]+0.0}</td>
							<td style="text-align: center;padding: 0px;width: 4%;">${sumAttendantDay}</td>
							<td style="text-align: center;padding: 0px;width: 4%;">${percentMap[doctor.doctorFlow]}</td>
							<td style="text-align: center;padding: 0px;"><a style="cursor: pointer;color: blue" onclick="detail('${doctor.doctorFlow}')">详情</a></td>
						</tr>
					</c:forEach>
						<c:if test="${empty doctorList}">
							<tr><td style="text-align: center;" colspan="30">无记录</td></tr>
						</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>