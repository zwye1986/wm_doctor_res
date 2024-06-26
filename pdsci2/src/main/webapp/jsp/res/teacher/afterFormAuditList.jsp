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
	<jsp:param name="jquery_fullcalendar" value="true" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<style>
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
	function search(){
		$("#isFirst").val("0");
		$("#searchForm").submit();
	}

	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
	
	function opRec(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow,typeId,typeName,processFlow){
		var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
				"?schDeptFlow="+schDeptFlow+
				"&rotationFlow="+rotationFlow+
				"&recTypeId="+typeId+"&userFlow="+userFlow+
				"&roleFlag=${roleFlag}&openType=open"+
				"&resultFlow="+schResultFlow+
				"&recFlow="+recFlow+
				"&processFlow="+processFlow+
				"&operUserFlow="+userFlow;
		jboxOpen(url, typeName, 1000, 500);
	}

    //评分
    function deptEval(recTypeName,recTypeId,recFlow,processFlow,operUserFlow){
        jboxOpen("<s:url value='/res/rec/deptEval'/>?roleFlag=${roleFlag}&processFlow="+processFlow+"&recTypeId="+recTypeId+"&recFlow="+recFlow+"&operUserFlow="+operUserFlow,
                recTypeName,1200,500);
    }

    //批量打印DOPS,MINICEX
	function batchExport(recType){
        jboxTip("打印中,请稍等...");
        var url = "<s:url value='/res/teacher/batchExport'/>?recType="+recType;
		<c:forEach items="${processList}" var="process" varStatus="s">
			var recFlow = $("#"+recType+"${s.index}").attr("recFlow");
			if(recFlow){
                url+=('&data='+recFlow)
            }
		</c:forEach>
        window.location.href = url;
	}
</script>

</head>
<body>
<div class="mainright">
	<div class="content">

		<form id="searchForm" action="<s:url value='/res/teacher/afterFormAudit/${roleFlag}'/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlable">年&#12288;&#12288;级：</label>

					<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
						   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
					<%--<select name="sessionNumber" class="qselect">--%>
						<%--<option value="">全部</option>--%>
						<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="sessionNumber">--%>
						<%--<option value="${sessionNumber.dictName}" <c:if test="${param.sessionNumber eq sessionNumber.dictName }">selected</c:if> >${sessionNumber.dictName}</option>--%>
						<%--&lt;%&ndash;or (sessionNumber1 eq sessionNumber.dictName)&ndash;%&gt;--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
				</div>
				<div class="inputDiv">
					<label class="qlable">姓&#12288;&#12288;名：</label>
					<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
				</div>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_MANAGER || roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
				<div class="inputDiv">
					<label class="qlable">培训专业：</label>
					<select name="trainingSpeId" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
							<option value="${training.dictId}" <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
						</c:forEach>
					</select>
				</div>
				</c:if>
				<c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_MANAGER}">
					<c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
						<div class="doctorTypeDiv">
							<div class="doctorTypeLabel">学员类型：</div>
							<div class="doctorTypeContent">
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<c:set var="docType" value="${type.dictId},"/>
									<label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
								</c:forEach>
							</div>
						</div>
					</c:if>
					<div class="qcheckboxDiv" style="padding-left:30px;">
						<c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
							<label class="qlable">
								<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}/>
								当前轮转科室
							</label>
						</c:if>
						<label class="qlable">
							<input type="checkbox" name="dshStatus" value="${GlobalConstant.FLAG_Y}" ${param.dshStatus eq GlobalConstant.FLAG_Y?"checked":""} />
							只看待审核
						</label>
					</div>
					<div class="inputDiv" style="min-width: 600px;text-align: left">
						<input type="button" class="searchInput" value="查&#12288;询" onclick="toPage('1')">
						<input type="button" class="searchInput" value="导出本页临床操作技能评估量化表" onclick="batchExport('DOPS')">
						<input type="button" class="searchInput" value="导出本页迷你临床演练评估量化表" onclick="batchExport('Mini_CEX')">
					</div>
				</c:if>
			</div>
		</form>

		<div class="resultDiv">
			<table class="xllist" >
			<tr>
				<th style="min-width: 100px;">姓名</th>
	  			<th style="min-width: 50px;">年级</th>
	  			<th style="min-width: 100px;">电话</th>
	  			<th style="min-width: 100px;">培训专业</th>
<!-- 	  			<th style="min-width: 150px;">身份证</th> -->
	  			<%--<th style="min-width: 100px;">培训类别</th>--%>
	  			<th style="min-width: 100px;">轮转科室</th>
	  			<th style="min-width: 100px;">轮转开始日期</th>
	  			<th style="min-width: 100px;">轮转结束日期</th>
	  			<th style="min-width: 100px;">状态</th>
	  			<th style="min-width:400px;max-width: 400px;width: 400px">出科审核</th>
			</tr>
			<c:forEach items="${processList}" var="process" varStatus="s">
				<c:set var="user" value="${userMap[process.userFlow]}"/>
				<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
				<c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
				<tr>
					<td title="<img src='${sysCfgMap['upload_base_url']}/${user.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>">
						${user.userName}
					</td>
					<td>${doctor.sessionNumber}</td>
					<td>${user.userPhone}</td>
					<td>${doctor.trainingSpeName}</td>
					<%--<td>${doctor.doctorCategoryName }</td>--%>
					<td>${process.schDeptName }</td>
					<td>${process.schStartDate }</td>
					<td>${process.schEndDate}</td>
					<td>
						<c:set var="aftersKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterSummary.id}"/>
						<c:set var="aftereKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterEvaluation.id}"/>
						<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
							已出科
						</c:if>
						<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
							<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
								<c:if test="${!empty recMap[aftersKey] || !empty recMap[aftersKey]}">
									待出科
								</c:if>
								<c:if test="${!(!empty recMap[aftersKey] || !empty recMap[aftersKey])}">
									轮转中
								</c:if>
							</c:if>
							<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
								待入科
							</c:if>
						</c:if>
					</td>
<%-- 					<td>${user.idNo}</td> --%>
					<td style="text-align: left;">&#12288;
						<c:forEach items="${afterRecTypeEnumList}" var="after">

							<c:set var="showKey" value="res_${after.id}_form_flag"/>
							<c:set var="key" value="${process.processFlow}${after.id}"/>
							<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[showKey]}">
								<c:set var="isShow" value="false"/>
								<c:set var="cfgKey" value="jswjw_${result.orgFlow}_P013"/>
								<%--//带教--%>
								<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag}">
									<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] }">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${afterRecTypeEnumAfterSummary.id ne after.id
									&&afterRecTypeEnumDiscipleSummary.id ne after.id
           							 &&resRecTypeEnumDeptEval.id ne after.id
            						&&afterRecTypeEnumMonthlyAssessment_clinic.id ne after.id
            						&&afterRecTypeEnumMonthlyAssessment_inpatientArea.id ne after.id}">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'}">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
										<c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
											<c:set var="isShow" value="true"/>
										</c:if>
									</c:if>
								</c:if>
								<%--非带教--%>
								<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne roleFlag}">
									<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] && not empty recMap[key].auditStatusId}">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${afterRecTypeEnumAfterSummary.id ne after.id
									&&afterRecTypeEnumDiscipleSummary.id ne after.id
           							 &&resRecTypeEnumDeptEval.id ne after.id
            						&&afterRecTypeEnumMonthlyAssessment_clinic.id ne after.id
            						&&afterRecTypeEnumMonthlyAssessment_inpatientArea.id ne after.id && !empty recMap[key]}">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'&& !empty recMap[key]}">
										<c:set var="isShow" value="true"/>
									</c:if>
									<c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
										<c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y' && !empty recMap[key]}">
											<c:set var="isShow" value="true"/>
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${isShow}">
									<c:set var="color" value="blue"/>
									<c:set var="auditStatusId" value="N"/>
									<c:set var="headAuditStatusId" value="N"/>
									<c:if test="${!empty recMap[key].auditStatusId}">
										<c:set var="auditStatusId" value="Y"/>
									</c:if>
									<c:if test="${!empty recMap[key].headAuditStatusId }">
										<c:set var="headAuditStatusId" value="Y"/>
									</c:if>
									<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id
        || afterRecTypeEnumDiscipleSummary.id eq after.id
        || afterRecTypeEnumAfterEvaluation.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id
        || resRecTypeEnumDeptEval.id eq after.id) }">
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag && !empty recMap[key].headAuditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag && !empty recMap[key].managerAuditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq roleFlag && !empty recMap[key].managerAuditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
									</c:if>
									<c:if test="${(afterRecTypeEnumDOPS.id eq after.id
        || afterRecTypeEnumMini_CEX.id eq after.id) }">
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag && !empty recMap[key].headAuditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag  }">
											<c:set var="color" value="black"/>
										</c:if>
										<c:if test="${GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq roleFlag && !empty recMap[key].managerAuditStatusId}">
											<c:set var="color" value="black"/>
										</c:if>
									</c:if>

									<c:if test="${resRecTypeEnumDeptEval.id ne after.id }">
										<a id="${after.id}${s.index}" style="color: ${color};cursor:pointer;width: 48%;float: left" auditStatusId="${auditStatusId}" headAuditStatusId="${headAuditStatusId}" recFlow="${recMap[key].recFlow}"
										   onclick="opRec('${recMap[key].recFlow}','${process.schDeptFlow}','${doctor.rotationFlow}','${process.userFlow}','${process.schResultFlow}','${after.id}','${after.name}','${process.processFlow}','${process.schDeptName}');">${after.name}
										</a>
									</c:if>
								</c:if>
							</c:if>
						</c:forEach>
						<c:set var="after" value="${resRecTypeEnumDeptEval}"/>
						<c:set var="showKey" value="res_${after.id}_form_flag"/>
						<c:set var="key" value="${process.processFlow}${after.id}"/>
							<c:set var="isShow" value="false"/>
							<c:set var="cfgKey" value="jswjw_${result.orgFlow}_P013"/>
							<%--//带教--%>
							<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag}">
								<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] }">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${!afterRecTypeEnumAfterSummary.id eq after.id &&!afterRecTypeEnumDiscipleSummary.id eq after.id
            &&!resRecTypeEnumDeptEval.id eq after.id &&!afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            &&!afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id}">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'}">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
									<c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
										<c:set var="isShow" value="true"/>
									</c:if>
								</c:if>
							</c:if>
							<%--非带教--%>
							<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne roleFlag}">
								<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] && not empty recMap[key].auditStatusId }">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${!afterRecTypeEnumAfterSummary.id eq after.id &&!afterRecTypeEnumDiscipleSummary.id eq after.id
            &&!resRecTypeEnumDeptEval.id eq after.id &&!afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            &&!afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id}">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'}">
									<c:set var="isShow" value="true"/>
								</c:if>
								<c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
									<c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
										<c:set var="isShow" value="true"/>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${isShow}">
								<c:set var="color" value="blue"/>
								<c:set var="auditStatusId" value="N"/>
								<c:set var="headAuditStatusId" value="N"/>
								<c:if test="${!empty recMap[key].auditStatusId}">
									<c:set var="auditStatusId" value="Y"/>
								</c:if>
								<c:if test="${!empty recMap[key].headAuditStatusId }">
									<c:set var="headAuditStatusId" value="Y"/>
								</c:if>
								<c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id
        || afterRecTypeEnumDiscipleSummary.id eq after.id
        || afterRecTypeEnumAfterEvaluation.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id
        || resRecTypeEnumDeptEval.id eq after.id) }">
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag && !empty recMap[key].headAuditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag && !empty recMap[key].managerAuditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq roleFlag && !empty recMap[key].managerAuditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
								</c:if>
								<c:if test="${(afterRecTypeEnumDOPS.id eq after.id
        || afterRecTypeEnumMini_CEX.id eq after.id) }">
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag  }">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag  }">
										<c:set var="color" value="black"/>
									</c:if>
									<c:if test="${GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq roleFlag && !empty recMap[key].managerAuditStatusId}">
										<c:set var="color" value="black"/>
									</c:if>
								</c:if>
								<c:if test="${resRecTypeEnumDeptEval.id eq after.id }">
									<a id="${after.id}${s.index}" style="color: ${color};cursor:pointer;width: 48%;float: left" auditStatusId="${auditStatusId}" headAuditStatusId="${headAuditStatusId}"
									   onclick="deptEval('${resRecTypeEnumDeptEval.name}','${resRecTypeEnumDeptEval.id}','${recMap[key].recFlow}','${process.processFlow}','${process.userFlow}');">${resRecTypeEnumDeptEval.name}
									</a>
								</c:if>
							</c:if>
						<c:set var="file" value="${fileMap[process.processFlow]}"></c:set>
						<c:if test="${not empty result.afterFileFlow}">
							<a id="${process.processFlow}down" style="color: blue;cursor:pointer;width: 48%;float: left" href="<s:url value='/res/teacher/downFile'/>?fileFlow=${result.afterFileFlow}">出科考核材料</a>
						</c:if>
						<script>
							<c:if test="${isMonthOpen!='Y'}">
								$("#${afterRecTypeEnumMonthlyAssessment_clinic.id}${s.index}").hide();
								$("#${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}${s.index}").hide();
							</c:if>
							<c:if test="${isMonthOpen eq'Y'}">

							if($("#${afterRecTypeEnumMonthlyAssessment_clinic.id}${s.index}").attr("auditStatusId")=="Y"||$("#${afterRecTypeEnumMonthlyAssessment_clinic.id}${s.index}").attr("headAuditStatusId")=="Y"){
								$("#${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}${s.index}").hide();
							}
							if($("#${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}${s.index}").attr("auditStatusId")=="Y"||$("#${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}${s.index}").attr("headAuditStatusId")=="Y"){
								$("#${afterRecTypeEnumMonthlyAssessment_clinic.id}${s.index}").hide();
							}
							</c:if>
						</script>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty processList}">
				<tr>
					<td colspan="10">无记录</td>
				</tr>
			</c:if>
		</table>
		</div>

		<div>
			<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>
