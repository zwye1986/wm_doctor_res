
<script>

	function toPage(page) {
		if (page) {
			$("#currentPage").val(page);
		}
		getSigninData();
	}

	function getSigninData(){
		jboxPostLoad("signinData","<s:url value='/res/doctorSignin/signinData'/>",$("#searchForm").serialize(),true);
	}
</script>
<div>

	<form id="searchForm" >
	<input type="hidden" name="currentPage" id="currentPage">
	<div class="" style="width: 100%;">
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
			<div class="inputDiv">
				<label class="qlable">培训年限：</label>
				<select name="trainingYears"  class="qselect" >
					<option></option>
					<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
						<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="inputDiv">
				<label class="qlable">签到时间：</label>
				<input type="text" class="qtext" name="signinDate" value="${param.signinDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
			</div>
			<div class="qcheckboxDiv" style="text-align: left;margin-left: 0px;margin-top: 6px;">
				<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
			</div>
		</div>
		</form>
</div>
<table class="xllist" style="margin-top: 10px;">
	<tr>
	<th style="width: 100px;">姓名</th>
	<th style="width: 200px;">签到时间</th>
	<th style="width: 200px;">轮转科室</th>
	<th style="width: 250px;">轮转时间</th>
	<th style="width: 100px;">二维码提供人</th>
		<c:forEach items="${list}" var="bean" varStatus="status">
			<tr>
				<td>${bean.doctorName}</td>
				<td>${bean.signinDate}</td>
				<td>${bean.schDeptName}</td>
				<td>${bean.schStartDate}~${bean.schEndDate}</td>
				<td>${bean.teacherUserName}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr><td colspan="99">暂无签到信息</td></tr>
		</c:if>
</table>
<p>
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</p>
		