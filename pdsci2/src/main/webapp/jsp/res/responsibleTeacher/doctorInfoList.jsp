<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(function () {
			<c:forEach items="${doctorTypeIdList}" var="type">
			$("#${type}").attr("checked", "checked");
			</c:forEach>
		})

		function search() {
			jboxStartLoading();
			jboxPostLoad("content","<s:url value='/res/responsibleTeacher/doctorInfoList'/>",$("#searchForm").serialize(),true);
		}

		function toPage(page) {
			if (page) {
				$("#currentPage").val(page);
				search();
			}
		}

		function viewDoc(doctorFlow) {
			jboxOpen("<s:url value='/res/responsibleTeacher/editDocSimple'/>?doctorFlow=" + doctorFlow, "医师信息", 950, 490);
		}

		function doctorPassedList(doctorFlow, recruitFlow) {
			var hideApprove = "hideApprove";
			var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId=" + recruitFlow + "&recruitFlow=" + recruitFlow + "&openType=open&doctorFlow=" + doctorFlow + "&hideApprove=" + hideApprove;
			jboxOpen(url, "学员信息", 1050, 600);

		}
	</script>
</head>
<div class="main_hd">
	<h2 class="underline">学员信息查询</h2>
</div>
<body>
<div style="padding: 10px 40px;">
	<form id="searchForm" method="post" action="<s:url value='/res/responsibleTeacher/doctorInfoList'/>">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		<table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input class="input" type="text" name="doctorName" value="${param.doctorName}"
						   style="width: 118px;">
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" class="select" style="margin: 0 5px;width: 124px">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<select name="sessionNumber" class="select" style="margin: 0 5px;width: 124px">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
							<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">结业考核年份：</td>
				<td>
					<input class="input" type="text" name="graduationYear" style="width: 118px;"
						   value="${param.graduationYear}" onchange="search()" onclick="WdatePicker({dateFmt:'yyyy'})"
						   readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="td_left">学员类型：</td>
				<td colspan="6">
					<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
						<label style="margin: 0 5px;"><input type="checkbox" name="doctorTypeIdList"
															 value="${type.dictId}" id="${type.dictId}"
															 <c:if test="${empty param.doctorTypeIdList}">checked</c:if>
						>${type.dictName}</label>
					</c:forEach>
					<input type="button" value="查&#12288;询" class="btn_green" onclick="search()"/>&nbsp;
				</td>
			</tr>
		</table>
		<%--	<div class="doctorTypeDiv">
                <div class="doctorTypeLabel">学员类型：</div>
                <div class="doctorTypeContent">
                    <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
                        <label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}" id="${type.dictId}"
                                      <c:if test="${empty param.doctorTypeIdList}">checked</c:if>
                        >${type.dictName}</label>
                    </c:forEach>
                </div>
            </div>--%>
	</form>
	<table class="grid" width="100%">
		<tr>
			<th style="text-align: center;padding: 0px">姓名</th>
			<th style="text-align: center;padding: 0px">性别</th>
			<th style="text-align: center;padding: 0px">手机</th>
			<th style="text-align: center;padding: 0px">身份证</th>
			<th style="text-align: center;padding: 0px">年级</th>
			<th style="text-align: center;padding: 0px">专业</th>
			<th style="text-align: center;padding: 0px">培养年限</th>
			<th style="text-align: center;padding: 0px">状态</th>
			<th style="text-align: center;padding: 0px">操作</th>
		</tr>
		<c:forEach items="${doctorList}" var="doctor">
			<tr>
				<td style="text-align: center;padding: 0px">${doctor.resDoctor.doctorName}</td>
				<td style="text-align: center;padding: 0px">${doctor.sysUser.sexName}</td>
				<td style="text-align: center;padding: 0px">${doctor.sysUser.userPhone}</td>
				<td style="text-align: center;padding: 0px">${doctor.sysUser.idNo}</td>
				<td style="text-align: center;padding: 0px">${doctor.resDoctor.sessionNumber}</td>
				<td style="text-align: center;padding: 0px">${doctor.resDoctor.trainingSpeName}</td>
				<td style="text-align: center;padding: 0px">
						${doctor.resDoctor.trainingYears eq 'OneYear'?'一年':''}
						${doctor.resDoctor.trainingYears eq 'TwoYear'?'两年':''}
						${doctor.resDoctor.trainingYears eq 'ThreeYear'?'三年':''}
				</td>
				<td style="text-align: center;padding: 0px">${doctor.resDoctor.doctorStatusName}</td>
				<td style="text-align: center;padding: 0px">
					<a class="btn show" onclick="doctorPassedList('${doctor.resDoctor.doctorFlow}','${doctor.recruitFlow}');">详情</a>
						<%--                    <a style="cursor: pointer;color: blue" onclick="viewDoc('${doctor.resDoctor.doctorFlow}');">查看</a>--%>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty doctorList}">
			<tr>
				<td colspan="20" style="text-align: center"> 暂无数据</td>
			</tr>
		</c:if>
	</table>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
</body>
</html>