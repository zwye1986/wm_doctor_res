<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	.cur{ background-color: pink;}
	.basic td,tr{border: 0}
</style>
<script type="text/javascript">
	function result(userFlow,period){
		<c:if test="${empty param.from}">
		jboxOpen("<s:url value='/xjgl/user/resultSun'/>?userFlow="+userFlow+"&period="+period,"成绩管理",1000,500);
		</c:if>

		<c:if test="${!empty param.from}">
			jboxOpen("<s:url value='/xjgl/user/resultSun'/>?userFlow="+userFlow+"&period="+period+"&openType=${GlobalConstant.FLAG_Y}","成绩管理",1000,500);
		</c:if>
	}
	function toPage(page){
		if(false==$("#recSearchForm").validationEngine("validate")){
			return;
		}
		if($("#orgName").val() != ""){
			$("#orgFlow").val($("#orgName").attr("flow"));
		}else{
			$("#orgFlow").val("");
		}
		$("#currentPage").val(page);
		search();
	}
	function toAssHole(page){
		jboxLoad("slideDiv","<s:url value='/xjgl/user/impRecordList'/>?currentPage2="+page,false);
	}
	function search(){
		if($("#courseTypeScore").val() !=  "" && $("#courseTypeId").val() == ""){
			jboxTip("请先选择课程类型！");
			$('#courseTypeScore').val("");
			return;
		}
		var form = $("#recSearchForm");
		jboxStartLoading();
		form.submit();
	}
	function leadTo(){
		 jboxOpen("<s:url value='/xjgl/user/leadTo'/>","导入",360,150);
	}
	function open(){
		$("#hanghao").show();
	}
	$(document).ready(function(){
		<c:forEach items="${eduUserList}" var="user">
			<c:forEach items="${eduCourseUserMap[user.userFlow].courseExtsList}" var="studentCourse">
					var result=0;var grade="";
				<c:forEach items="${dictTypeEnumXjIsPassedList}" var="dict">
					if("${studentCourse.courseGrade}"=="${dict.dictId}"){
						grade="${dict.dictName}";
						result=1;
					}
				</c:forEach>
				if(result==0){
					grade="${studentCourse.courseGrade}";
				}
				$("#"+"${studentCourse.recordFlow}"+"courseGradeInput").text(grade);
			</c:forEach>
		</c:forEach>
		slideInit();
	});

	function slideInit(){
		$("#slideDiv").slideInit({
			width:1000,
			speed:500,
			outClose:true,
			haveZZ:true
		});
	}

	function impRecordList(){
		var url="<s:url value='/xjgl/user/impRecordList'/>";
		jboxLoad("slideDiv", url, true);
		$("#slideDiv").rightSlideOpen();
	}
	function addRecord(){
		jboxOpen('<s:url value="/xjgl/student/addGrade?sid="/>'+$("#sid").val(),'添加成绩',800,300);
	}
	function expExcel(){
		if(false==$("#recSearchForm").validationEngine("validate")){
			return;
		}
		var url = "<s:url value='/xjgl/user/expExcel'/>?from=${param.from}";
		jboxTip("导出中…………");
		jboxSubmit($("#recSearchForm"), url, null, null, false);
		jboxEndLoading();
	}
	function linkCourseType(value){
		if(value !=  "" && $("#courseTypeId").val() == ""){
			jboxTip("请先选择课程类型！");
			$('#courseTypeScore').val("");
		}
	}
	function linkCourseTypeScore(value){
		if(value ==  "" ){
			$('#courseTypeScore').val("");
		}
	}

	//复选框事件
	//全选、取消全选、反选的事件
	function selectAll() {
		if($("#checkAll").is(':checked')){
			$(".check").prop("checked",true);
		}else{
			$(".check").prop("checked",false);
		}
	}
	//子复选框的事件
	function setSelectAll(obj){
		if(!$(obj).is(':checked')){
			$("#checkAll").prop("checked",false);
		}else{
			var checkAllLen = $("input[type='checkbox'][class='check']").length;
			var checkLen = $("input[type='checkbox'][class='check']:checked").length;
			if(checkAllLen == checkLen){
				$("#checkAll").prop("checked",true);
			}
		}
	}
	function del(){
		if($(".check:checked").size()==0){
			jboxTip("至少勾选一条成绩记录");
			return;
		}
		var recordLst = [];
		$(".check:checked").each(function(){
			recordLst.push(this.value);
		})
		jboxConfirm("确认批量删除勾选成绩记录？", function(){
			var url = "<s:url value='/xjgl/user/delBatchGrade?recordLst='/>"+recordLst;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});

	}

	function resultInfo(userFlow,period){
		jboxOpen("<s:url value='/xjgl/user/resultSunInfo'/>?userFlow="+userFlow+"&period="+period,"详情",1000,500);
	}


	function printResultList(isStmp){
		if($("#orgName").val() != ""){
			$("#orgFlow").val($("#orgName").attr("flow"));
		}else{
			$("#orgFlow").val("");
		}
		if(false==$("#recSearchForm").validationEngine("validate")){
			return;
		}
		if($("#sid").val()==""){
			if(!($("#orgFlow").val() != "" ||$("input[name='period']").val() != "")){
				jboxTip("请通过完整的学号、年级或者培养单位进行打印！");
				return;
			}
			if(${param.from eq 'charge'}){
				if($("input[name='period']").val() == ""){
					jboxTip("请通过完整的学号或年级进行打印！");
					return;
				}
			}
		}
		var orgFlow=$("#orgFlow").val();
		var sid=$("#sid").val();
		var userName=$("input[name='userName']").val();
		var period=$("input[name='period']").val();
		var printFlag=$("select[name='printFlag']").val();
		if(orgFlow==undefined||orgFlow=="undefined"){
			orgFlow="";
		}
		var url = "<s:url value='/xjgl/user/print4AdminOL'/>?orgFlow="+orgFlow+"&sid="+sid+"&period="+period+"&userName="+encodeURI(encodeURI(userName))+"&printFlag="+printFlag+"&isStmp="+isStmp+"&from=${param.from}";
		jboxTip("打印中，请稍等...");
		setTimeout(function(){
//			jboxOpen(url,"打印",800,600,true);
//			setTimeout(function(){jboxClose();},2000);
			$("#printA").attr("href",url);
			$("#clickSpan").click();

		},2000);
	}
	$(function(){
		$("#orgName").likeSearchInit({});
	});
</script>
<style type="text/css">
 .table tr td, .table tr th{border-bottom: 0px; }
.table1 td{border: none;}
.table1{border: none;}
 .basicData{border:1px solid #bbbbbb;}
 .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;}
 .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
 .basicData td { text-align:center; line-height:35px;}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="recSearchForm" method="post" action="<s:url value='/xjgl/user/selectEduUserStudentCourseSun'/>">
		<table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
			<tr>
				<td style="border: none;">

		<table class="basic table1" style="width: 980px">
			<input id="currentPage" type="hidden" name="currentPage"/>
			<input type="hidden" name="from" value="${param.from }"/>
			<input type="hidden" name="flag" value="${flag}"/>
			<tr>
				<td>学&#12288;&#12288;号：<input type="text" id="sid" name="sid" value="${param.sid}" style="width: 137px;"/>
					&#12288;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;"/>
					&#12288;课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>
					&#12288;&#12288;&#12288;选课学年：<input style="width:137px;" value="${param.studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
					<br/>
					获得学期：<select style="width: 141px;" name="gradeTermId" id="gradeTermId">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
						<option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
					</c:forEach>
					</select>
					&#12288;总学分&nbsp;≥：<input type="text" name="scoreSum" class="validate[custom[number]]" value="${param.scoreSum}" style="width: 137px;"/>
					&#12288;课程类型：<select style="width: 141px;" id="courseTypeId" name="courseTypeId" onchange="linkCourseTypeScore(this.value)">
						<option value="">请选择</option>
						<c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
							<option value="${courseType.id}" <c:if test="${param.courseTypeId==courseType.id}">selected="selected"</c:if>>${courseType.name}</option>
						</c:forEach>
					</select>
					学位课程成绩&nbsp;≥：<input type="text" name="degreeGrade" class="validate[custom[number]]" value="${param.degreeGrade}" style="width: 137px;"/>
					<br/>
					学&#12288;分&nbsp;≥：<input type="text" id="courseTypeScore" name="courseTypeScore" class="validate[custom[number]]" value="${param.courseTypeScore}" style="width: 137px;"/>
				<c:if test="${empty param.from }">
					&#12288;导入批次：<select style="width: 141px;" name="impFlow">
						<option value="">请选择</option>
						<c:forEach items="${importRecords}" var="record">
							<c:set var="impTime" value="${pdfn:transDateTime(record.impTime) }"/>
							<c:set var="impTime" value="${pdfn:split(impTime,':') }"/>
							<option value="${record.impFlow}" <c:if test="${param.impFlow==record.impFlow}">selected="selected"</c:if>>${impTime[0]}:${impTime[1]}(${record.impNum })</option>
						</c:forEach>
					</select>
				</c:if>
				&#12288;成&#12288;绩＜：<input type="text" id="courseGrade" name="courseGrade" class="validate[custom[number]]" value="${param.courseGrade}" style="width: 137px;"/>
				<c:if test="${flag eq 'view'}">
					&#12288;&#12288;&#12288;培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.orgFlow}" style="width: 137px;"/>&#12288;
					<div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:758px;">
						<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
							<c:forEach items="${orgList}" var="org">
								<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
							</c:forEach>
						</div>
					</div>
					<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>
					<br/>
				</c:if>
				<c:if test="${flag eq 'view' or param.from eq 'charge'}">
					<c:if test="${param.from eq 'charge'}">&#12288;</c:if>年&#12288;&#12288;级：<input style="width:137px;" value="${param.period}" name="period" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
					<c:if test="${param.from eq 'charge'}">&#12288;&#12288;</c:if>&#12288;打印选项：<select  name="printFlag" style="width: 141px;">
					<option value="${GlobalConstant.FLAG_N }" <c:if test="${param.printFlag == 'N' or empty param.printFlag}">selected</c:if>>中文打印</option>
					<option value="${GlobalConstant.FLAG_Y }" <c:if test="${param.printFlag == 'Y' }">selected</c:if>>英文打印</option>
					</select>
				</c:if>
				<c:if test="${param.from eq 'charge'}"><br/></c:if>
				<input type="button" name="" class="search" onclick="toPage();" value="查&#12288;询" />
				<c:if test="${flag eq 'view' or !empty param.from}">
					<input type="button" class="search" onclick="expExcel();" value="导&#12288;出"/>
					<c:if test="${empty param.from or param.from eq 'charge'}">
						<input type="button" class="search" onclick="printResultList('Y');" value="有章打印"/>
						<input type="button" class="search" onclick="printResultList('N');" value="无章打印"/>
					</c:if>
				</c:if>
				<c:if test="${empty param.from && flag eq 'edit'}">
					<br/>
						<input type="button" name="" class="search" onclick="addRecord();" value="新&#12288;增"/>
						<input type="button" name="" class="search" onclick="leadTo();" value="导&#12288;入" />
						<input type="button" name="" class="search" onclick="impRecordList();" value="导入记录" />
						<input type="button" name="" class="search" onclick="del();" value="删&#12288;除"/>
				</c:if></td>
			</tr>
		</table>
				</td>
			</tr>
		</table>
			</form>
		<table class="basicData" style="width: 100%;">
			<tr style="font-weight: bold;">
				<c:if test="${flag eq 'edit'}">
					<td style="min-width: 65px;"><input type="checkbox" name="checkAll" class="checkAll" value="Y" id="checkAll" onclick="selectAll()"/>&nbsp;正/反选</td>
				</c:if>
				<td style="min-width: 55px;">选课学年</td>
				<td style="min-width: 55px;">获得学期</td>
				<td width="100px">学号</td>
				<td width="80px">姓名</td>
				<td>课程名称</td>
				<td width="70px">学时</td>
				<td width="70px">学分</td>
				<td width="70px">修读方式</td>
				<td width="70px">考核方式</td>
				<td width="85px">成绩获得方式</td>
				<td width="70px">获得学年</td>
				<td width="70px">成绩</td>
				<c:if test="${flag eq 'edit' or !empty param.from}">
					<td style="min-width: 55px;">操作</td>
				</c:if>
			</tr>
			<c:forEach items="${recordList}" var="record">
				<tr id="${record.recordFlow}">
					<c:if test="${flag eq 'edit'}">
						<td><input type="checkbox" name="checkOne" class="check" value="${record.recordFlow}" onclick="setSelectAll(this)" /></td>
					</c:if>
					<td>${record.studentPeriod}</td>
					<td style="line-height: 140%;">${record.gradeTermName}</td>
					<td style="line-height: 140%;">${record.eduUser.sid}</td>
					<td style="line-height: 140%;">${record.sysUser.userName}</td>
					<td style="text-align: left;padding-left: 10px;line-height: 140%;">[${record.courseCode}]${record.courseName}</td>
					<td>${record.coursePeriod}</td>
					<td>${record.courseCredit}</td>
					<td>${record.studyWayName}</td>
					<td>${record.assessTypeName}</td>
					<td>${record.scoreMode=='R'?"补考":""}</td>
					<td>${record.gradeYear}</td>
					<td id="score${record.recordFlow}">
					<c:choose>
						<c:when test="${record.courseGrade==GlobalConstant.FLAG_Y }">
							通过
						</c:when>
						<c:when test="${record.courseGrade==GlobalConstant.FLAG_N}">
							不通过
						</c:when>
						<c:when test="${record.courseGrade eq 'T'}">
							缺考
						</c:when>
						<c:otherwise>
							${record.courseGrade}
							<script>
								var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
								$("#score${record.recordFlow}").html(v);
							</script>
						</c:otherwise>
					</c:choose>
					</td>
					<c:if test="${flag eq 'edit' or !empty param.from}">
						<td>
							<a href="javascript:void(0);" onclick="result('${record.userFlow}','${record.studentPeriod}')" style="color: blue;">
								<c:if test="${param.from ne GlobalConstant.USER_LIST_LOCAL}">
									成绩管理
								</c:if>
								<c:if test="${param.from eq GlobalConstant.USER_LIST_LOCAL}">
									成绩查询
								</c:if>
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${empty recordList}">
				<tr>
					<td colspan="14" >无记录！</td>
				</tr>
			</c:if>
		</table>
		<div>
	       	<c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	    </div>
	</div>
	<a hidden="hidden" target="_blank" id="printA"><span id="clickSpan"/></a>
</div>
<div id="slideDiv"></div>
<div id="printDiv2" style="display: none;"></div>
</body>
</html>