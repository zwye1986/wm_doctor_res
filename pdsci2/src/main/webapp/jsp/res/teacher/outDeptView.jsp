<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true" />
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
<style type="text/css">

	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">

	function showView(){
		var startDate = $("input[name='startDate']").val();
		var endDate = $("input[name='endDate']").val();
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return false;
		}
		if($("input[name='doctorTypeIdList']:checked").length<=0)
		{
			jboxTip("请选择学员类型！");
			return false;
		}
			$("#searchForm").submit();
		}

	function toPage1(page) {
		if (page) {
			$("#currentPage").val(page);
		}
		showView();
	}

	function goAudit(doctorName){
		$("#doctorName").val(doctorName);
		$("#doctorForm").submit();
		$("#dateAreaValue").val();
	}
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}

	function downloadCkkPaper(processFlow) {
		var url = "<s:url value='/res/exam/paper/downloadCkkPaper?processFlow='/>" + processFlow;
		jboxGet(url,null,function(resp){
			var data=eval("("+resp+")");
			if(data.result=="1")
			{
				$("#url").val(data.url);
				jboxExp($("#exportFrom"),"<s:url value='/res/exam/paper/downloadCkkFile?processFlow='/>" + processFlow);
			}else{
				jboxTip(data.msg);
			}
		},null,false);
	}
</script>
</head>
<body>
<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="mainright">
	<div class="content" >
		<div>
			<div>
				<jsp:include page="/res/doc/newNoticeList" flush="true">
					<jsp:param name="fromSch" value="Y"></jsp:param>
				</jsp:include>
			</div>
			<form id="searchForm" action="<s:url value='/res/teacher/outDeptView/${role}'/>" method="post">
				<input type="hidden" name="currentPage" id="currentPage">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">轮转时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}" >
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select name="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList }" var="dict">
								<option value="${dict.dictId }"<c:if test="${param.trainingSpeId eq dict.dictId  }">selected</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv" style="text-align: left;">
						<label class="qlable">培训年限：</label>
						<select name="trainingYears"  class="qselect" style="width: 180px;">
							<option></option>
							<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="qcheckboxDiv" style="text-align: left;margin-left: 0px;">
						<label class="qlable">
							&#12288;&#12288;<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}>
							轮转中学员
						</label>
						<input type="button" value="查&#12288;询" class="searchInput" onclick="showView();"/>
					</div>
				</div>
			</form>
		</div>

		<div style="padding-top: 5px;">
			<table class="xllist" style="width:100%;">
			<tr>
				<th style="width: 10%;">姓名</th>
				<th style="width: 5%;">性别</th>
				<th style="width: 5%;">培训类别</th>
				<th style="width: 5%;">学员类型</th>
				<th style="width: 5%;">年级</th>
				<th style="width: 5%;">培训年限</th>
				<th style="width: 10%;">培训专业</th>
				<th style="width: 10%;">轮转科室</th>
				<th style="width: 10%;">计划轮转时间</th>
				<th style="width: 5%;">状态</th>
				<th style="width: 10%;">理论成绩</th>
				<th style="width: 10%;">出科试卷</th>
			</tr>
			<c:forEach items="${processList}" var="process">
				<tr>
					<td onclick="edit('${process.userFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${process.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"><a style="cursor:pointer;color: #2f8cef">${process.userName}</a></td>
					<td>${process.sexName}</td>
					<td>${process.doctorCategoryName}</td>
					<td>${process.doctorTypeName}</td>
					<td>${process.sessionNumber}</td>
					<td>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							<c:if test="${process.trainingYears eq dict.dictId}">
								${dict.dictName}
							</c:if>
						</c:forEach>
					</td>
					<td>${process.trainingSpeName}</td>
					<td>${process.schDeptName}</td>
					<td>${process.schStartDate} ~ ${process.schEndDate}</td>
					<td>
						<c:set var="key" value="${process.processFlow}"/>
						<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
							已出科
						</c:if>
						<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
							<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
								<c:if test="${!empty afterMap[key] || !empty recMap[key]}">
									待出科
								</c:if>
								<c:if test="${!(!empty afterMap[key] || !empty recMap[key])}">
									轮转中
								</c:if>
							</c:if>
							<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
								待入科
							</c:if>
						</c:if>
					</td>
					<td>${process.schScore}</td>
					<td>
						<c:set var="key12" value="jswjw_${process.orgFlow}_downExamFile"/>
						<c:set var="key2" value="res_doctor_ckks_${process.userFlow}"/>
						<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
						<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
						<c:set var="ckks" value="${pdfn:resPowerCfgMap(key2).cfgValue eq GlobalConstant.FLAG_Y}"/>
						<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
						<c:if test="${sysCfgMap[key12]==GlobalConstant.FLAG_Y and testTypeFlag and not empty process.schScore }">
							<a style="cursor: pointer" onclick="downloadCkkPaper('${key}');">[下载试卷]</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty processList}">
				<tr><td colspan="99">暂无数据！</td></tr>
			</c:if>
		</table>
		</div>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
			<pd:pagination toPage="toPage1"/>
		</p>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#cannotOper").append($(".cannotIn"));
	});
</script>
</body>
</html>