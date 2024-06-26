<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<html >
<head>
<style type="text/css">
table{
/*     width:30em; */
    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}
.over{
    width:100%;
    word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}
td{
	word-break:break-all;
}
.input{
	margin-left: 4px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#startDate').datepicker().on('changeDate', function(e) {
		searchAtendanceByitems();
	});;
	$('#endDate').datepicker().on('changeDate', function(e) {
		searchAtendanceByitems();
	});;
	$(".showInfo").on("mouseover mouseout",
			function(){
				$(".theInfo",this).toggle();
			}
	);
	changeTrainSpes();
});
$(function(){
	if(${searchType eq "search7day"}){
		$("#searchMonth").removeClass("btn_blue");
		$("#searchMonth").addClass("btn_grey");
		$("#search7day").removeClass("btn_grey");
		$("#search7day").addClass("btn_blue");
		return;
	}
	if(${searchType eq "searchMonth"}){
		$("#search7day").removeClass("btn_blue");
		$("#search7day").addClass("btn_grey");
		$("#searchMonth").removeClass("btn_grey");
		$("#searchMonth").addClass("btn_blue");
		return;
	}
	$("#searchMonth").removeClass("btn_grey");
	$("#searchMonth").addClass("btn_blue");
});
function toPage(page) {
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	page=page||"${param.currentPage}";
	$("#currentPage").val(page);
	var form=$("#searchForm").serialize();
	attendanceSearch(form);
}
function search7day2(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	$("#currentPage").val(1);
	<c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
	<c:set var="startDate" value="${pdfn:addDate(currDate,-7)}"></c:set>
	var exp="search7day";
	$("#searchType").val(exp);
	$("#startDate").val("${startDate}");
	$("#endDate").val("${currDate}");
	var form=$("#searchForm").serialize();
	attendanceSearch(form);
}
function searchMonth2(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	$("#currentPage").val(1);
	<c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
	<c:set var="startDate" value="${pdfn:addDate(currDate,-30)}"></c:set>
	var exp="searchMonth";
	$("#searchType").val(exp);
	$("#startDate").val("${startDate}");
	$("#endDate").val("${currDate}");
	var form=$("#searchForm").serialize();
	attendanceSearch(form);
}
function searchAtendanceByitems(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var currentDate="${pdfn:getCurrDate()}";
	if(endDate>currentDate){
		var startDate = $("#itemsDate").attr("schStartDate");
		var endDate = $("#itemsDate").attr("schEndDate");
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		jboxTip("只能查询当前日期之前的记录");
		return;
	}
	if(startDate>endDate){
		var startDate = $("#itemsDate").attr("schStartDate");
		var endDate = $("#itemsDate").attr("schEndDate");
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		jboxTip("开始日期不能小于截止日期");
		return;
	}
	$("#currentPage").val(1);
	var form=$("#searchForm").serialize();
	attendanceSearch(form);
}
function exportAttendance(){
	if(${empty jsResAttendanceExts}){
		jboxTip("当前无记录!");
		return;
	}
	var roleId="${roleId}";
	var url = "<s:url value='/jsres/teacher/exportAttendance?roleId='/>"+roleId;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
}
function changeTrainSpes(){
	//清空原来专业！！！
	var trainCategoryid=$("#trainingTypeId").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	$("select[name=trainingSpeId] option[value != '']").remove();
	$("#"+trainCategoryid+"_select").find("option").each(function(){
		$(this).clone().appendTo($("#trainingSpeId"));
	});
//	var orgFlow = $("#orgFlow select").val();
//	searchOrgSchDept(orgFlow);
	return false;
}
function searchOrgSchDept(orgFlow)
{
	var url = "<s:url value='/sch/doc/getSchDept?orgFlow='/>"+orgFlow;
	var courseArray = new Array();
	jboxGetAsync(url,null,function(data){
		if(data){
			var html="<option></option>";
			for (var i = 0; i < data.length; i++) {
				var schDeptFlow=data[i].schDeptFlow;
				var schDeptName=data[i].schDeptName;
				html+="<option value='"+schDeptFlow+"'>"+schDeptName+"</option>";
			}
			$("#schDeptFlow").html(html);
		}
	},null,false);
}
</script>
</head>

<body>
	<div class="main_hd">
		<h2 class="underline">
			学员考勤查询
		</h2>
	</div>
	<div class="div_table" id="div_table_0">
	<div class="div_search">
			<form id="searchForm" action="" method="post">
				<div style="height: 10px"></div>
  				<input id="currentPage" type="hidden" name="currentPage"  value="${param.currentPage}"/>
				<input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}" />
				<input id="searchType" type="hidden" name="searchType" value=""/>
				<table style="width:100%">
					<tr>
						<td>培训基地：
							<select class="select" style="width: 106px" name="orgFlow" id="orgFlow" onchange="changeTrainSpes()">
								<option></option>
								<c:forEach items="${orgs}" var="org" varStatus="status">
									<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
								</c:forEach>
							</select>
						</td>
						<td>培训类别：
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:106px;">
								<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<c:choose>
										<c:when test="${empty param.trainingTypeId}">
											<option value="${trainCategory.id}" <c:if test="${'DoctorTrainingSpe' eq trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
										</c:when>
										<c:when test="${!empty param.trainingTypeId}">
											<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select>
						</td>
						<td>培训专业：
							<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
									<option value="${spe.dictId}"
											<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
									>${spe.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<%--<td>科&#12288;&#12288;室：<select style="width: 100px;" id="schDeptFlow" name="schDeptFlow">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${schDeptList}" var="schDept">--%>
								<%--<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<%--</td>--%>
						<td>出勤状态：
							<select class="select" id="statueId" name="statueId" style="width: 100px;" >
							<option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤</option>
							<option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤</option>
						</select></td>
					</tr>
					<tr>
						<td>带教老师：<input type="text" name="teacherName" class="input"  value="${param.teacherName}" style="width: 100px;"/></td>
						<td>学生姓名：<input type="text" name="studentName" class="input"  value="${param.studentName}"  style="width: 100px;"/></td>
						<td colspan="2">考勤时间：<input type="text" id="startDate" name="schStartDate" value="${schStartDate}"  class="input datepicker"  readonly="readonly" style="width: 80px;"/>
							~
							<input type="text" id="endDate" name="schEndDate" value="${schEndDate}"  class="input datepicker"  readonly="readonly" style="width: 80px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							快速查询：
							<input id="search7day" name="searchMonth" type="radio" value="" onclick="search7day2();"
								   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
							<label for="search7day">最近7天</label>&#12288;
							<input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
								   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
							<label for="searchMonth">本月</label>
							&#12288;<input type="button" class="btn_green" onclick="searchAtendanceByitems();" value="查&#12288;询" />
						</td>
						<td>

						</td>
					</tr>
				</table>
			</form>
	</div>
	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" >
			<colgroup>
				<col style="width: 80px;">
				<col style="width: 80px;">
				<col style="width: 80px;">
				<col style="width: 80px;">
				<col style="width: 200px;">
				<col style="width: 80px;">
				<col style="width: 80px;">
				<col style="width: 80px;">
			</colgroup>
			<tr>
				<th>日期</th>
	        	<th>姓名</th>
				<th>科室</th>
				<th>带教老师</th>
	        	<th>考勤记录</th>
				<th>出勤状态</th>
				<th>备注</th>
				<th>学员备注</th>
			</tr>
			<c:forEach items="${jsResAttendanceExts}" var="jsResAttendanceExt">
				<tr>
				<td>${jsResAttendanceExt.jsresAttendance.attendDate}</td>
				<td>${jsResAttendanceExt.userName}</td>
				<td>${jsResAttendanceExt.sysDept.deptName}</td>
				<td>${jsResAttendanceExt.jsresAttendance.teacherName}</td>
				<td>
					<c:choose>
						<c:when test="${ not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
							<c:set var="length" value="${fn:length(attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow])}"></c:set>
							<c:if test="${length >'4'}">
								<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
									<c:if test="${status.count=='1'or status.count=='2'}">
										<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
									</c:if>
									<c:if test="${length >'4' and status.count=='2'}">...&nbsp;&nbsp;</c:if>
									<c:if test="${length >'4'and status.count==(length) or	status.count==(length-1)}">
										<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${length <='4'}">
								<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
									<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
								</c:forEach>
							</c:if>
						</c:when>
						<c:otherwise>
							暂无签到记录！
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					${jsResAttendanceExt.jsresAttendance.attendStatusName}
					<c:if test="${empty jsResAttendanceExt.jsresAttendance.attendStatusName}">
						待审核
					</c:if>
				</td>
				<td  align="center">
					${jsResAttendanceExt.jsresAttendance.teacherRemarks}
				</td>
				<td style="padding-left: 25px;" <c:if test='${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}'>
					class="showInfo"
				</c:if> >
					<c:if test="${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
						备注详情
					</c:if>
					<div style="width: 0px;height: 0px;overflow: visible;">
						<div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 400px;" class="theInfo">
							<table class="grid" style="background: white;width: 350px;">
								<tr>
									<th align="center" style="width:80px">签到时间</th>
									<th align="center" style="width:135px">签到地点</th>
									<th align="center" style="width:135px">特殊备注</th>
								</tr>
								<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail">
									<tr>
										<td align="center">${jsresAttendanceDetail.attendTime}</td>
										<td align="center">${jsresAttendanceDetail.attendLocal}</td>
										<td align="center">${jsresAttendanceDetail.selfRemarks}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</td>
				</tr>
			</c:forEach>
			<c:if test="${empty jsResAttendanceExts}">
				<tr>
					<td colspan="8">无记录</td>
				</tr>
			</c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(jsResAttendanceExts)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
		</div>
        </div>
	</div>
	<div style="display: none;">
		<select id="WMFirst_select">
			<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="WMSecond_select">
			<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="AssiGeneral_select">
			<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>

	</div>
</body>
</html>
 