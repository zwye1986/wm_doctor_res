<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
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
	<script type="text/javascript">
		var batchMap = {"0":"无","1":"第一批次","2":"第二批次","3":"第三批次","4":"第四批次","5":"第五批次","6":"第六批次",
		"7":"第七批次","8":"第八批次","9":"第九批次","10":"第十批次"}
		function search(){
			$("#serchForm").submit();
		}
		function toPage(page){
			if(page){
				$("#currentPage").val(page);
				search();
			}
		}
		function detail(flow){
			jboxOpen("<s:url value='/gzykdx/recruit/getDetail'/>?userFlow="+flow, "复试考生信息",800,600);
		}
		function pass(flow){
			if(${!pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.student_recruit_date}){
				jboxTip("当前时间不在指标上报时间内！");
			}else {
				jboxOpen("<s:url value='/gzykdx/secondaryRecruit/changeAuditStatus'/>?recordFlow="+flow, "详情",800,250)
			}
		}
		function changeTeacher(flow){
			jboxOpen("<s:url value='/gzykdx/secondaryRecruit/changeTeacher'/>?recordFlow="+flow, "更换录取导师",750,250)
		}
		function exportExl(){
			var url = "<s:url value='/gzykdx/secondaryRecruit/schoolExportStudents'/>?isSecondary=isSecondary&"+$("#serchForm").serialize();
			jboxTip("导出中…………");
			window.location.href = url;
			jboxEndLoading();
		}
		function changeFscj(userFlow){
			var fscj = $("#"+userFlow).val();
			jboxPost("<s:url value='/gzykdx/secondaryRecruit/changeFscj'/>?userFlow="+userFlow+"&fscj="+fscj,
					null,function(){
						search();
					},null,false);
		}
		function changeRecruitNum(recordFlow){
			var recruitNum = $("#"+recordFlow).val();
			var r = /^\+?[1-9][0-9]*$/;　　//正整数
			if(recruitNum<1||recruitNum>20||!r.test(recruitNum)){
				jboxTip("请输入1至20正整数");
				setTimeout(function(){
					search();
				},1000);
				return;
			}
			jboxPost("<s:url value='/gzykdx/secondaryRecruit/changeRecruitNum'/>?recordFlow="+recordFlow+"&recruitNum="+recruitNum,
					null,function(){
						search();
					},null,false);
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/secondaryRecruit/secondaryReexamineList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							学生编号：
							<input type="text" name="userCode" value="${param.userCode}" class="xltext">

							姓&#12288;&#12288;名：
							<input type="text" name="userName" value="${param.userName}" class="xltext">

							专业名称：
							<select class="xlselect" name="speId" style="height: 27px;">
								<option></option>
								<c:forEach items="${dictTypeEnumGzykdxSpeList}" var="dict">
									<option value="${dict.dictId}"
											<c:if test="${dict.dictId eq param.speId}">selected</c:if>
									>${dict.dictName}[${dict.dictId}]</option>
								</c:forEach>
							</select>

							状态：
							<label><input type="radio" name="schoolAuditStatusId" value=""
										  <c:if test="${empty param.schoolAuditStatusId}">checked="checked"</c:if>
							>全部</label>
							<label><input type="radio" name="schoolAuditStatusId" value="${gzykdxAdmissionStatusEnumPassing.id}"
										  <c:if test="${param.schoolAuditStatusId eq gzykdxAdmissionStatusEnumPassing.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumPassing.name}</label>
							<label><input type="radio" name="schoolAuditStatusId" value="${gzykdxAdmissionStatusEnumSchoolPassing.id}"
										  <c:if test="${param.schoolAuditStatusId eq gzykdxAdmissionStatusEnumSchoolPassing.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumSchoolPassing.name}</label>
							<label><input type="radio" name="schoolAuditStatusId" value="${gzykdxAdmissionStatusEnumPassed.id}"
										  <c:if test="${param.schoolAuditStatusId eq gzykdxAdmissionStatusEnumPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumPassed.name}</label>
							<label><input type="radio" name="schoolAuditStatusId" value="${gzykdxAdmissionStatusEnumUnPassed.id}"
										  <c:if test="${param.schoolAuditStatusId eq gzykdxAdmissionStatusEnumUnPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumUnPassed.name}</label>
							<label><input type="radio" name="schoolAuditStatusId" value="${gzykdxAdmissionStatusEnumGiveUpPassed.id}"
										  <c:if test="${param.schoolAuditStatusId eq gzykdxAdmissionStatusEnumGiveUpPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumGiveUpPassed.name}</label>
						</td>
					</tr>
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="recruitYear" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"
								   value="${(empty param.recruitYear)?thisYear:param.recruitYear}" class="xltext">

							学位类型：
							<select class="xlselect" name="degreeTypeId" style="height: 27px;">
								<option/>
								<c:forEach items="${gzykdxDegreeTypeEnumList}" var="dict">
									<option value="${dict.id}"
											<c:if test="${dict.id eq param.degreeTypeId}">selected</c:if>
									>${dict.name}</option>
								</c:forEach>
							</select>

							研究方向：
							<input type="text" name="researchAreaName" value="${param.researchAreaName}" class="xltext">
							<%--<select class="xlselect" name="researchAreaName" style="height: 27px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumResearchAreaList}" var="dict">--%>
									<%--<option value="${dict.dictName}"--%>
											<%--<c:if test="${dict.dictName eq param.researchAreaName}">selected</c:if>--%>
									<%-->${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>

							是否校外考生：
							<label><input type="radio" name="isOwnerStu" value=""
								<c:if test="${empty param.isOwnerStu}">checked="checked"</c:if>
							>全部</label>
							<input type="radio" name="isOwnerStu" value="N"
								<c:if test="${param.isOwnerStu eq 'N'}">checked="checked"</c:if>
							>是</label>
							<label><input type="radio" name="isOwnerStu" value="Y"
								<c:if test="${param.isOwnerStu eq 'Y'}">checked="checked"</c:if>
							>否</label>
							&#12288;&#12288;&#12288;&#12288;
							<input type="button" value="查&#12288;询" onclick="search()" class="search">
							<input type="button" value="导&#12288;出" onclick="exportExl()" class="search">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th nowrap="">年份</th>
				<th nowrap="">考生编号</th>
				<th nowrap="">姓名</th>
				<th nowrap="">毕业学校</th>
				<th nowrap="">复试号</th>
				<th nowrap="">报考专业名称</th>
				<th nowrap="" style="max-width: 180px;min-width: 77px;">研究方向</th>
				<th nowrap="">报考类型</th>
				<th nowrap="">报考导师</th>
				<th style="max-width: 20%" nowrap="">报考机构</th>

				<th nowrap="">录取导师</th>
				<th nowrap="">录取导师名额</th>
				<th nowrap="">学位类型</th>
				<th nowrap="">录取专业</th>
				<th nowrap="" style="max-width: 180px;">录取研究方向</th>
				<th nowrap="">录取机构</th>
				<th nowrap="">初试成绩</th>
				<th nowrap="">复试原始成绩</th>
				<th nowrap="">复试折算成绩</th>
				<th nowrap="">总成绩</th>
				<th nowrap="">调剂批次</th>
				<th nowrap="">第几录取</th>
				<th nowrap="">录取状态</th>
				<th nowrap="">操作</th>
			</tr>
			<c:choose>
			<c:when test="${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.studentsInfoOpen_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.studentsInfoOpen_end_date}">
			<c:forEach items="${secondaryReexamineList}" var="student">
				<c:set var="key" value="${student['USER_FLOW']}"></c:set>
				<tr>
					<td nowrap="">&nbsp;${student['RECRUIT_YEAR']}&nbsp;</td>
					<td>&nbsp;${student['USER_CODE']}&nbsp;</td>
					<td nowrap="">&nbsp;${student['USER_NAME']}&nbsp;</td>
					<td>${bydwMap[key]}</td>
					<td>${fshMap[key]}</td>
					<td>${student['SPE_NAME']}</td>
					<td style="max-width: 180px;min-width: 77px;" <c:if test="${fn:length(student['RESEARCH_AREA_NAME'])>20}">
						title="${student['RESEARCH_AREA_NAME']}"</c:if>>
						<c:if test="${fn:length(student['RESEARCH_AREA_NAME'])>20}">
							${fn:substring(student['RESEARCH_AREA_NAME'],0,20)}......
						</c:if>
						<c:if test="${fn:length(student['RESEARCH_AREA_NAME'])<=20}">
							${student['RESEARCH_AREA_NAME']}
						</c:if>
					</td>
					<td>${student['RECRUIT_TYPE_NAME']}</td>
					<td nowrap="">${student['TEACHER_NAME']}</td>
					<td>${student['ORG_NAME']}</td>

					<td nowrap="">${student['FINAL_USER_NAME']}
						<c:if test="${(student['SCHOOL_AUDIT_STATUS_ID'] eq gzykdxAdmissionStatusEnumPassing.id)}">
							<input type="button" value="更换" onclick="changeTeacher('${student['RECORD_FLOW']}');" class="search" style="float: right">
						</c:if>
					</td>
					<td nowrap="">
						<c:if test="${!((student['ACADEMIC_NUM'] eq '0')||(empty student['ACADEMIC_NUM']))}">
						学术型：${(empty student['ACADEMIC_NUM']?0:student['ACADEMIC_NUM'])}剩${(empty student['ACADEMIC_NUM']?0:student['ACADEMIC_NUM'])-student['ACADEMIC_RECRUIT_NUM']}
						<br/>
						</c:if>
						<c:if test="${!((student['SPECIALIZED_NUM'] eq '0')||(empty student['SPECIALIZED_NUM']))}">
						专业型：${(empty student['SPECIALIZED_NUM']?0:student['SPECIALIZED_NUM'])}剩${(empty student['SPECIALIZED_NUM']?0:student['SPECIALIZED_NUM'])-student['SPECIALIZED_RECRUIT_NUM']}
						</c:if>
					</td>
					<td>${student['FINAL_DEGREE_TYPE_NAME']}</td>
					<td>${student['FINAL_SPE_NAME']}</td>
					<td style="max-width: 180px;" <c:if test="${fn:length(student['FINAL_RESEARCH_AREA_NAME'])>20}">
						title="${student['FINAL_RESEARCH_AREA_NAME']}"</c:if>>
						<c:if test="${fn:length(student['FINAL_RESEARCH_AREA_NAME'])>20}">
							${fn:substring(student['FINAL_RESEARCH_AREA_NAME'],0,20)}......
						</c:if>
						<c:if test="${fn:length(student['FINAL_RESEARCH_AREA_NAME'])<=20}">
							${student['FINAL_RESEARCH_AREA_NAME']}
						</c:if>
					</td>
					<td>${student['FINAL_ORG_NAME']}</td>
					<td>${cscjMap[key]}</td>
					<td>
						<input id=${key} value="${fscjMap[key]}" type="text" style="width: 35px;"
							   onchange="changeFscj('${key}');"/>
					</td>
					<td>${halfFscjMap[key]}</td>
					<td>${zcjMap[key]}</td>
					<td id="batch${student['USER_FLOW']}"></td>
					<script>
						$("#batch${student['USER_FLOW']}").text(batchMap["${student['RECRUIT_BATCH']}"]);
					</script>
					<td>
						<input id=${student["RECORD_FLOW"]} value="${student['RECRUIT_NUM']}" type="text" style="width: 30px;"
							   onchange="changeRecruitNum('${student["RECORD_FLOW"]}');"/>
					</td>
					<td nowrap="">${student['SCHOOL_AUDIT_STATUS_NAME']}</td>
					<td nowrap="">
						<c:if test="${student['SCHOOL_AUDIT_STATUS_ID'] eq gzykdxAdmissionStatusEnumPassing.id}">
						<a onclick="pass('${student['RECORD_FLOW']}')" style="cursor: pointer">[录取]</a>
						</c:if>
						<a onclick="detail('${student['USER_FLOW']}')" style="cursor: pointer">[详情]</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty secondaryReexamineList}">
				<tr>
					<td colspan="30">暂无信息</td>
				</tr>
			</c:if>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="30">考生信息暂未开放</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(secondaryReexamineList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>

</body>
</html>