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
	var absenceJson = {"04":"事假","02":"病假","11":"产假","10":"婚假","06":"年假","08":"补休","11":"产假","12":"陪产假","13":"计生假"
		,"14":"丧假","15":"出国","16":"进修","18":"脱产读研","19":"放射假","20":"旷工","00":"出勤","03":"0.5天病假","05":"0.5天事假"
		,"07":"0.5天带薪年假","09":"0.5天补休","21":"0.5天旷工","22":"0.5天放射假"};
	$(function(){
		<c:forEach items="${doctorTypeList}" var="type">
		$("#${type}").attr("checked","checked");
		</c:forEach>
		$(".absenceIds").each(function(){
			var content = absenceJson[$(this).text()];
			$(this).text(content);
			if(!$(this).text()){
				$(this).text("出勤");
			}
		});
		$(".kqny").each(function(){
			var month = $(this).text().split("-");
			if(month[1]=="4"||month[1]=="6"||month[1]=="9"||month[1]=="11"){
				$(this).nextAll().last().children().text("");
			}
			if(month[1]=="2"){
				if((parseInt(month[0])%4==0 && parseInt(month[0])%100!=0)||parseInt(month[0])%400==0){
					$(this).nextAll().last().children().text("");
					$(this).nextAll().last().prev().children().text("");
				}else{
					$(this).nextAll().last().children().text("");
					$(this).nextAll().last().prev().children().text("");
					$(this).nextAll().last().prev().prev().children().text("");
				}
			}
		});
	})
//	function toPage(page) {
//		if(page){
//			$("#currentPage").val(page);
//		}
//		search();
//	}
	function search(){
		var startDate=$("[name='startTime']").val();
		var endDate=$("[name='endTime']").val();
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
		var url = '<s:url value="/res/zsrykqmonth/kqList/dept/${role}"/>';
		jboxPost(url,$("#searchForm").serialize(),function(resp){
			$("#content2").html(resp);
		},null,false);
	}
	function exportExl(){
		if(${empty resultMapList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/zsrykqmonth/exportKqListDept/${role}'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							考勤年月：
							<input class="qtext" name="startTime" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  readonly="readonly"
								   value="${empty param.startTime?startTime:param.startTime}"/>

						</div>
						<div class="inputDiv">
							&#12288;&#12288;&#12288;
							 ~&#12288;&#12288;&#12288;<input class="qtext" name="endTime" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  readonly="readonly"
								   value="${empty param.endTime?endTime:param.endTime}"/>
						</div>
						<c:if test="${role ne 'doctor'}">
						<div class="inputDiv">
							<label class="qlable">轮转科室：</label>
							<select name="schDeptFlow" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${schDeptList}" var="dept">
									<option value="${dept.schDeptFlow}" ${param.schDeptFlow eq dept.schDeptFlow?'selected':''}>${dept.schDeptName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="userName" value="${param.userName}" class="qtext">
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
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
							<label class="qlable">培训年限：</label>
							<select name="trainingYears" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
									<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							入培时间：
							<input class="qtext" name="inHosDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  readonly="readonly"
								   value="${param.inHosDate}"/>
						</div>
						<div class="inputDiv">
							结业时间：
							<input class="qtext" name="graduationYear" type="text" onclick="WdatePicker({dateFmt:'yyyy'})"  readonly="readonly"
								   value="${param.graduationYear}"/>
						</div>
						<div class="inputDiv" style="min-width: 730px;max-width: 730px;width: 730px;text-align: left;margin-left:23px; ">
							学员类型：
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input type="checkbox" name="doctorTypeList" value="${type.dictId}" id="${type.dictId}"
												  <c:if test="${empty param.doctorTypeList}">checked</c:if>>${type.dictName}</label>
								</c:forEach>
							&#12288;<label><input type="checkbox" name="onlyMiss" value="Y" <c:if test="${param.onlyMiss eq 'Y'}">checked</c:if>>只显示旷工人员</label>
							&#12288;<label><input type="checkbox" name="onlyAbsence" value="Y" <c:if test="${param.onlyAbsence eq 'Y'}">checked</c:if>>只显示请假人员</label>
						</div>
						</c:if>
						<div class="lastDiv" style="min-width: 90%;max-width: 90%;text-align: left;margin-left: 20px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
							<input type="button" value="导&#12288;出" class="searchInput" onclick="exportExl();"/>
						</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;">姓名</td>
						<td style="text-align: center;padding: 0px;">年级</td>
						<td style="text-align: center;padding: 0px;">所属科室</td>
						<td style="text-align: center;padding: 0px;">培训专业</td>
						<td style="text-align: center;padding: 0px;">培训年限</td>
						<td style="text-align: center;padding: 0px;">轮转科室</td>
						<td style="text-align: center;padding: 0px;">轮转时间</td>
						<td style="text-align: center;padding: 0px;">入培时间</td>
						<td style="text-align: center;padding: 0px;">结业时间</td>
						<td style="text-align: center;padding: 0px;">考勤年月</td>
						<c:forEach items="${Mlist2}" var="m">
							<td style="text-align: center;padding: 0px;">${m}</td>
						</c:forEach>
					</tr>
					<c:forEach items="${resultMapList}" var="result">
						<c:set value="${result['PROCESS_FLOW']}" var="key1"></c:set>
						<c:set value="${sizeMap[key1]}" var="colspan"></c:set>
						<c:forEach items="${dateMap[key1]}" var="date" varStatus="status">
							<tr>
								<c:if test="${status.first}">
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["USER_NAME"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["SESSION_NUMBER"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">
										<c:choose>
											<c:when test="${result['DOCTOR_TYPE_ID'] eq 'Company'}">
												${result["DEPART_MENT_NAME"]}
											</c:when>
											<c:when test="${result['DOCTOR_TYPE_ID'] eq 'Social'}">
												--
											</c:when>
											<c:otherwise>
												${result['WORK_ORG_NAME']}
											</c:otherwise>
										</c:choose>
									</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["TRAINING_SPE_NAME"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["TRAINING_YEARS"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["SCH_DEPT_NAME"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["SCH_START_DATE"]}~${result["SCH_END_DATE"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["IN_HOS_DATE"]}</td>
									<td style="text-align: center;padding: 0px;" rowspan="${colspan}">${result["GRADUATION_YEAR"]}</td>
								</c:if>
									<td class="kqny" style="text-align: center;padding: 0px;">${date}</td>
								<c:forEach items="${Mlist}" var="m">
									<td style="text-align: center;padding: 0px;">
										<a class="absenceIds"	>${monthMap[key1][date][m]}</a>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:forEach>
						<c:if test="${empty resultMapList}">
							<tr><td style="text-align: center;" colspan="80">无记录</td></tr>
						</c:if>
				</table>
				<%--<div>--%>
				   	<%--<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>--%>
					<%--<pd:pagination toPage="toPage"/>--%>
				<%--</div>--%>
			</div>
		</div>
	</div>
</body>
</html>