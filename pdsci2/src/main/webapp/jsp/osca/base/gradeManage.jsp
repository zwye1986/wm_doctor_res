<script>
	$(function(){
		<c:forEach items="${gradeList}" var="info" varStatus="i">
		var sum = 0;
		for(var i = 0;i<$(".${info.TICKET_NUMBER}").size();i++){
			sum+=Number($(".${info.TICKET_NUMBER}").eq(i).text());
			$(".sum${info.TICKET_NUMBER}").text(sum==0?"":sum.toFixed(2));
		}
		</c:forEach>
	});

	<%--function isShowOpt(value,flag,obj){--%>
		<%--jboxConfirm("上报后不能修改，请确认",function(){--%>
			<%--var url = "<s:url value='/osca/base/isShowOpt?clinicalFlow=${param.clinicalFlow}&isShow='/>"+value+"&flag="+flag;--%>
			<%--jboxPost(url, null, function (resp) {--%>
				<%--jboxPostLoad("gradeDiv","<s:url value="/osca/base/gradeManage"/>",$("#gradeForm").serialize(),true);--%>
<%--//				toPage4(1);--%>
				<%--jboxTip(resp);--%>
				<%--if(resp=="${GlobalConstant.OPERATE_SUCCESSED}"){--%>
					<%--$(obj).after("<font color='red'>成绩已上报</font>");--%>
					<%--$(obj).remove();--%>
				<%--}--%>
			<%--}, null, false);--%>
		<%--})--%>

	<%--}--%>
</script>
<form id="gradeForm" method="post">
	<input type="hidden" name="clinicalName" value="${clinicalName}"/>
	<input type="hidden" name="speName" value="${speName}"/>
	<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
	<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
	<input type="hidden" id="currentPage4" name="currentPage4" value="${param.currentPage4}"/>
	<input type="hidden" name="isLocal" value="${param.isLocal}"/>
	<input type="hidden" name="isGradeReleased" value="${param.isGradeReleased}"/>
	<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
		<tr>
			<td style="border:0px;">
				<span style="padding-left:5px;"></span>准考证号：
				<input type="text" name="ticketNumber" value="${param.ticketNumber}">
				<span style="padding-left:5px;"></span>姓名：
				<input type="text" name="gradeDoctorName" value="${param.gradeDoctorName}">
				<span style="padding-left:5px;"></span>考试阶段：
				<select name="trainCategoryId" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${trainCategoryEnumList}" var="cate">
						<option value="${cate.id}" ${param.trainCategoryId eq cate.id?'selected':''}>${cate.name}</option>
					</c:forEach>
				</select>
				<span style="padding-left:5px;"></span>考核结果：
				<select name="resultId" style="width:137px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${doctorScoreEnumList}" var="rlt">
						<option value="${rlt.id}" ${param.resultId eq rlt.id?'selected':''}>${rlt.name}</option>
					</c:forEach>
				</select><br/>
				<span style="padding-left:5px;"></span>考核时间：
				<select name="recrodFlow" style="width:156px;" class="select">
					<option value="">全部</option>
					<c:forEach items="${timeList}" var="time">
						<option value="${time.recrodFlow}" ${param.recrodFlow eq time.recrodFlow?'selected':''}>${time.examStartTime}~${time.examEndTime}</option>
					</c:forEach>
				</select>
				<span style="padding-left:5px;"></span>成绩排序：
				<input type="radio" name="order" value="ASC" ${param.order eq 'ASC'?'checked':''}>升序&nbsp;
				<input type="radio" name="order" value="DESC" ${param.order eq 'DESC'?'checked':''}>降序
				<span style="padding-left:10px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage4(1)"/>
				<br/>
				<span style="display:inline-block;margin-top:20px;">
					<font style="color:red;">通过率${percent}%（说明：查询结果通过率=考核通过人数/参加考核人数）</font>
					&#12288;&#12288;&#12288;
					<c:if test="${param.isLocal eq 'Y'}">
					学员能否查看成绩
					<input type="radio" name="isShow" value="N" ${osa.isShow eq 'N'?'checked':''} onchange="isShowOpt(this.value)">否
					<input type="radio" name="isShow" value="Y" ${osa.isShow eq 'Y'?'checked':''} onchange="isShowOpt(this.value)">是
					<span style="visibility:${osa.isShow eq 'Y'?'':'hidden'}">&#12288;学员能否查看评分表
						<input type="radio" name="isShowFroom" value="N" ${osa.isShowFroom eq 'N'?'checked':''} onchange="isShowOpt(this.value,'form')">否
						<input type="radio" name="isShowFroom" value="Y" ${osa.isShowFroom eq 'Y'?'checked':''} onchange="isShowOpt(this.value,'form')">是
					</span>
					</c:if>
					<span style="padding-left:10px;"></span>
					<input type="button" class="search" value="成绩导入" onclick="gradeImport()"/>
					<input type="button" class="search" value="成绩导出" onclick="gradeExport()"/>
					<c:if test="${param.isLocal eq 'Y'}">
						<input type="button" class="search" value="成绩发布" onclick="isShowOpt('grade')"/>
					</c:if>
					<c:if test="${param.isLocal eq 'N'}">
						<c:if test="${param.isGradeReleased eq 'N'}">
							<input type="button" class="search" value="成绩上报" onclick="isShowOpt('school','',this)"/>
						</c:if>
						<c:if test="${param.isGradeReleased ne 'N'}">
							<input type="button" class="search" value=" 已 上 报 " style="cursor: auto;"/>
						</c:if>
					</c:if>
				</span>
			</td>
		</tr>
	</table>
</form>
<table class="xllist" style="width:100%;">
	<tr style="background-color:#F5F5F5;">
		<th rowspan="2">序号</th>
		<th rowspan="2">准考证号</th>
		<th rowspan="2">姓名</th>
		<td rowspan="2">性别</td>
		<td rowspan="2">身份证号</td>
		<th rowspan="2">培训基地</th>
		<th rowspan="2">所在单位</th>
		<th rowspan="2">培训专业</th>
		<th rowspan="2">考试阶段</th>
		<th colspan="${fn:length(stationList)+1}">成绩</th>
		<th rowspan="2">考核结果</th>
		<th rowspan="2">操作</th>
	</tr>
	<tr style="background-color:#F5F5F5;">
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${stationList}" var="station">
			<th style="line-height: 130%;">${station.stationName}<br/>${station.stationScore}</th>
			<c:set var="count" value='${count + station.stationScore}'></c:set>
		</c:forEach>
		<th style="line-height: 130%;">总分<c:if test="${not empty stationList}"><br/><fmt:formatNumber type="number" value="${count}" /></c:if></th>
	</tr>
	<c:forEach items="${gradeList}" var="info" varStatus="i">
		<tr style="${info.IS_PASS eq 'Miss'?'background-color:#D7D7D7':''}">
			<td>${i.index + 1}</td>
			<td>${info.TICKET_NUMBER}</td>
			<td>${info.DOCTOR_NAME}</td>
			<td>${info.SEX_NAME}</td>
			<td>${info.ID_NO}</td>
			<td>${info.ORG_NAME}</td>
			<td>${info.WORK_ORG_NAME}</td>
			<td>${info.SPE_NAME}</td>
			<td>${info.TRAINING_TYPE_NAME}</td>
			<c:set var="stuCount" value='0'></c:set>
			<c:forEach begin="0" end="${fn:length(stationList)-1}" varStatus="s">
				<c:forEach items="${fn:split(info.STATION_FLOW,',')}" var="sta" varStatus="si">
					<c:if test="${sta eq stationList[s.index].stationFlow}">
						<td>
							<a class="${info.TICKET_NUMBER}" onclick="searchScoreForm('${info.DOCTOR_FLOW}','${sta}')" style="cursor:pointer;color:#4195C5;">
							${fn:split(info.EXAM_SCORE,',')[si.index] eq '*'?'':fn:split(info.EXAM_SCORE,',')[si.index]}
							</a>
						</td>
						<c:set var="exitFlag" value="${s.index}"></c:set>
					</c:if>
				</c:forEach>
				<c:if test="${s.index ne exitFlag || empty exitFlag || empty fn:split(info.STATION_FLOW,',')[0]}">
					<td></td>
					<c:set var="exitFlag"></c:set>
				</c:if>
			</c:forEach>
			<td class="sum${info.TICKET_NUMBER}"></td>
			<td>${info.IS_PASS_NAME}</td>
			<td>
				<c:if test="${((info.IS_PASS eq 'Miss')||(param.isGradeReleased ne 'N'))&&(param.isLocal eq 'N')}"><a style="color:#4195c5;">编辑</a></c:if>
				<c:if test="${((info.IS_PASS ne 'Miss')&&(param.isGradeReleased eq 'N'))||(param.isLocal eq 'Y')}"><a onclick="editGradeOpt('${info.DOCTOR_FLOW}')" style="cursor:pointer;color:#4195c5;">编辑</a></c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<div style="float:right;margin-top:100px;">
	<c:set var="pageView" value="${pdfn:getPageView(gradeList)}" scope="request"/>
	<pd:pagination toPage="toPage4"/>
</div>