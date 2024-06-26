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
	})
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	function search(){
		jboxStartLoading();
		$("#searchForm").submit();
	}
	function edit(obj){
		$(obj).hide();
		$(obj).next().show();
	}
	function editAbsence(obj,monthFlow,m,userFlow){
		var data = {userFlow:userFlow,kqDate:"${param.kqDate}"}
		jboxPost("<s:url value='/res/zsrykqmonth/editAbsence'/>?monthFlow="+monthFlow+"&"+m+"="+$(obj).val(),data,function(resp){
			if(resp==1){
				jboxTip("操作成功");
				search();
			}
		},null,false)
	}
	function submitAbsence(monthFlow){
		jboxConfirm("提交后该月数据不可修改，请确认",function(){
			jboxPost("<s:url value='/res/zsrykqmonth/editAbsence'/>?monthFlow="+monthFlow+"&isSubmit=Y",null,function(resp){
				if(resp==1){
					jboxTip("操作成功");
					search();
				}
			},null,false)
		})
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/zsrykqmonth/absenseMark/${role}'/>">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							考勤年月：
							<input class="qtext" name="kqDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  readonly="readonly"
								   value="${empty param.kqDate?kqDate:param.kqDate}"/>
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
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<select name="trainingSpeId" class="qselect" >
								<option  value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
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
							<label class="qlable">轮转科室：</label>
							<select name="schDeptFlow" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${schDeptList}" var="dept">
									<option value="${dept.schDeptFlow}" ${param.schDeptFlow eq dept.schDeptFlow?'selected':''}>${dept.schDeptName}</option>
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
							<label><input type="checkbox" name="onlyAbsence" value="Y" <c:if test="${param.onlyAbsence eq 'Y'}">checked</c:if>>只显示请假人员</label>
						</div>
						<div class="lastDiv">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
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
						<td style="text-align: center;padding: 0px;">操作</td>
					</tr>
					<c:forEach items="${resultMapList}" var="result">
						<tr>
							<td style="text-align: center;padding: 0px;">${result["USER_NAME"]}</td>
							<td style="text-align: center;padding: 0px;">${result["SESSION_NUMBER"]}</td>
							<td style="text-align: center;padding: 0px;">
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
							<td style="text-align: center;padding: 0px;">${result["TRAINING_SPE_NAME"]}</td>
							<td style="text-align: center;padding: 0px;">${result["TRAINING_YEARS"]}</td>
							<td style="text-align: center;padding: 0px;">${result["SCH_DEPT_NAME"]}</td>
							<td style="text-align: center;padding: 0px;">${result["SCH_START_DATE"]}~${result["SCH_END_DATE"]}</td>
							<td style="text-align: center;padding: 0px;">${result["IN_HOS_DATE"]}</td>
							<td style="text-align: center;padding: 0px;">${result["GRADUATION_YEAR"]}</td>
							<td style="text-align: center;padding: 0px;">${empty param.kqDate?kqDate:param.kqDate}</td>
							<c:forEach items="${Mlist}" var="m">
								<c:set value='${empty param.kqDate?kqDate:param.kqDate}-${fn:substring(m, 1, 3) }' var="key"></c:set>
								<td style="text-align: center;padding: 0px;">
									<a class="absenceIds"
									<c:if test="${((empty result[m])||(result[m] eq '00')||(result[m] eq '20')||(result[m] eq '21'))&&
										(result['SCH_START_DATE'] le key)&&(result['SCH_END_DATE'] ge key)&&(result['IS_SUBMIT'] ne 'Y')&&
										isCurrMonth}">
									   onclick="edit(this)" style="color: blue;cursor:pointer;"
									</c:if>
									>${result[m]}</a>
									<select style="display: none" onchange="editAbsence(this,'${result["MONTH_FLOW"]}','${m}','${result["USER_FLOW"]}');">
										<option value="00" ${((empty result[m])||(result[m] eq '00'))?'selected':''}>出勤</option>
										<option value="21" ${(result[m] eq '21')?'selected':''}>0.5天旷工</option>
										<option value="20" ${(result[m] eq '20')?'selected':''}>旷工</option>
									</select>
								</td>
							</c:forEach>
							<td style="text-align: center;padding: 0px;">
								<c:if test="${result['IS_SUBMIT'] ne 'Y' && isCurrMonth}">
									<a style="cursor: pointer;color: blue" onclick="submitAbsence('${result["MONTH_FLOW"]}')">提交</a>
								</c:if>
								<c:if test="${result['IS_SUBMIT'] eq 'Y' || (!isCurrMonth)}">
									已提交
								</c:if>
							</td>
						</tr>
					</c:forEach>
						<c:if test="${empty resultMapList}">
							<tr><td style="text-align: center;" colspan="80">无记录</td></tr>
						</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>