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
			jboxOpen("<s:url value='/gzykdx/secondaryRecruit/schoolChangeAuditStatus'/>?recordFlow="+flow, "详情",800,250)
		}
		function trajectory(flow){
			jboxOpen("<s:url value='/gzykdx/secondaryRecruit/trajectory'/>?recordFlow="+flow, "详情",800,600)
		}
		function drop(flow){
			jboxConfirm("放弃录取后考生将不在招录，导师进行补录。",function(){
				var url = "<s:url value='/gzykdx/secondaryRecruit/editDocTeaRec'/>";
				var data = {"recordFlow":flow,
							"schoolAuditStatusId":'${gzykdxAdmissionStatusEnumGiveUpPassed.id}',
							"schoolAuditStatusName":'${gzykdxAdmissionStatusEnumGiveUpPassed.name}'
							};
				jboxPost(url,data,function(resp){
					if(resp==1) jboxTip("操作成功！");
					jboxEndLoading();
					window.parent.frames['mainIframe'].location.reload(true);
					jboxClose();
				},null,false);
			});
		}
		function fastPass(){
			var checkLen = $(":checkbox[class='check']:checked").length;
			if(checkLen == 0){
				jboxTip("请勾选考生");
				return;
			}
			var recordLst = [];
			$(":checkbox[class='check']:checked").each(function(){
				recordLst.push(this.value);
			})
			jboxConfirm("确认一键通过？", function(){
				var json = {"recordLst":recordLst,
							"auditStatusId":'${gzykdxAdmissionStatusEnumPassed.id}'
							};
				var url = "<s:url value='/gzykdx/secondaryRecruit/fastPass'></s:url>";
				jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
						location.reload();
				}, null, true);
			});
		}
		function checkAll(){
			if($("#checkAll").attr("checked")){
				$(".check").attr("checked",true);
			}else{
				$(".check").attr("checked",false);
			}
		}
		function checkSingel(obj){
			if(!$(obj).attr("checked")){
				$("#checkAll").attr("checked",false);
			}else{
				var checkAllLen = $("input[type='checkbox'][class='check']").length;
				var checkLen = $("input[type='checkbox'][class='check']:checked").length;
				if(checkAllLen == checkLen){
					$("#checkAll").attr("checked",true);
				}
			}
		}
		function exportExl(){
			var url = "<s:url value='/gzykdx/secondaryRecruit/schoolExportStudents'/>?"+$("#serchForm").serialize();
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
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/secondaryRecruit/firstReexamineList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="recruitYear" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"
								   value="${(empty param.recruitYear)?thisYear:param.recruitYear}" class="xltext">

							机构名称：
							<select class="xlselect" name="org" style="height: 27px;">
								<option></option>
								<c:forEach items="${orgList}" var="org">
									<option value="${org.orgFlow}"
											<c:if test="${org.orgFlow eq param.org}">selected</c:if>
									>${org.orgName}</option>
								</c:forEach>
							</select>

							调剂批次：
							<select class="xlselect" name="recruitBatch" style="height: 27px;">
								<option></option>
								<option value="1" <c:if test="${'1' eq param.recruitBatch}">selected</c:if>>第一批</option>
								<option value="2" <c:if test="${'2' eq param.recruitBatch}">selected</c:if>>第二批</option>
								<option value="3" <c:if test="${'3' eq param.recruitBatch}">selected</c:if>>第三批</option>
								<option value="4" <c:if test="${'4' eq param.recruitBatch}">selected</c:if>>第四批</option>
								<option value="5" <c:if test="${'5' eq param.recruitBatch}">selected</c:if>>第五批</option>
								<option value="6" <c:if test="${'6' eq param.recruitBatch}">selected</c:if>>第六批</option>
								<option value="7" <c:if test="${'7' eq param.recruitBatch}">selected</c:if>>第七批</option>
								<option value="8" <c:if test="${'8' eq param.recruitBatch}">selected</c:if>>第八批</option>
								<option value="9" <c:if test="${'9' eq param.recruitBatch}">selected</c:if>>第九批</option>
								<option value="10" <c:if test="${'10' eq param.recruitBatch}">selected</c:if>>第十批</option>
							</select>

							状态：
							<label><input type="radio" name="auditStatusId" value=""
										  <c:if test="${empty param.auditStatusId}">checked="checked"</c:if>
							>全部</label>
							<label><input type="radio" name="auditStatusId" value="${gzykdxAdmissionStatusEnumPassing.id}"
										  <c:if test="${param.auditStatusId eq gzykdxAdmissionStatusEnumPassing.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumPassing.name}</label>
							<label><input type="radio" name="auditStatusId" value="${gzykdxAdmissionStatusEnumSchoolPassing.id}"
										  <c:if test="${param.auditStatusId eq gzykdxAdmissionStatusEnumSchoolPassing.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumSchoolPassing.name}</label>
							<label><input type="radio" name="auditStatusId" value="${gzykdxAdmissionStatusEnumPassed.id}"
										  <c:if test="${param.auditStatusId eq gzykdxAdmissionStatusEnumPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumPassed.name}</label>
							<label><input type="radio" name="auditStatusId" value="${gzykdxAdmissionStatusEnumUnPassed.id}"
										  <c:if test="${param.auditStatusId eq gzykdxAdmissionStatusEnumUnPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumUnPassed.name}</label>
							<label><input type="radio" name="auditStatusId" value="${gzykdxAdmissionStatusEnumGiveUpPassed.id}"
										  <c:if test="${param.auditStatusId eq gzykdxAdmissionStatusEnumGiveUpPassed.id}">checked="checked"</c:if>
							>${gzykdxAdmissionStatusEnumGiveUpPassed.name}</label>
							</td>
					</tr>
					<tr>
						<td nowrap="">
							考生编号：
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

							研究方向：
							<input type="text" name="researchAreaName" value="${param.researchAreaName}" style="width:137px;" class="xltext"/>
							<%--<select class="xlselect" name="researchAreaName" style="height: 27px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumResearchAreaList}" var="dict">--%>
									<%--<option value="${dict.dictName}"--%>
											<%--<c:if test="${dict.dictName eq param.researchAreaName}">selected</c:if>--%>
									<%-->${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</td>
					</tr>
					<tr>
						<td nowrap="">
							&#12288;&#12288;&#12288;&#12288;
							<input type="button" value="查&#12288;询" onclick="search()" class="search">
							<input type="button" value="导&#12288;出" onclick="exportExl()" class="search">
							<input type="button" value="一键审核" onclick="fastPass()" class="search">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th nowrap=""><input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号</th>
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
				<th nowrap="">复试成绩</th>
				<th nowrap="">总成绩</th>
				<th nowrap="">调剂批次</th>
				<th nowrap="">录取状态</th>
				<th nowrap="">操作</th>
			</tr>
			<c:forEach items="${firstReexamineList}" var="student" varStatus="s">
				<c:set var="key" value="${student['USER_FLOW']}"></c:set>
				<tr>
					<td nowrap="">
						<c:if test="${student['SCHOOL_AUDIT_STATUS_ID'] eq gzykdxAdmissionStatusEnumSchoolPassing.id}">
						<input type="checkbox" class="check" value="${student['RECORD_FLOW']}" onclick="checkSingel(this)">&nbsp;
						</c:if>
						${s.index + 1}</td>
					<td nowrap="">&nbsp;${student['RECRUIT_YEAR']}&nbsp;</td>
					<td>&nbsp;${student['USER_CODE']}&nbsp;</td>
					<td nowrap=""><a onclick="detail('${student["USER_FLOW"]}')">&nbsp;${student['USER_NAME']}&nbsp;</a></td>
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

					<td nowrap="">${student['FINAL_USER_NAME']}</td>
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
					<td><input id=${key} value="${fscjMap[key]}" type="text" style="width: 35px;" onchange="changeFscj('${key}');"/></td>
					<td>${zcjMap[key]}</td>
					<td id="batch${student['USER_FLOW']}"><a onclick="trajectory('${student["RECORD_FLOW"]}');" style="cursor: pointer"></a></td>
					<script>
						$("#batch${student['USER_FLOW']} a").text(batchMap["${student['RECRUIT_BATCH']}"]);
					</script>
					<td nowrap="">${student['SCHOOL_AUDIT_STATUS_NAME']}</td>
					<td nowrap="">
						<c:if test="${student['SCHOOL_AUDIT_STATUS_ID'] eq gzykdxAdmissionStatusEnumSchoolPassing.id}">
							<a onclick="pass('${student['RECORD_FLOW']}')" style="cursor: pointer">[审核]</a>
							&nbsp;
						</c:if>
						<c:if test="${student['SCHOOL_AUDIT_STATUS_ID'] eq gzykdxAdmissionStatusEnumPassed.id}">
							<a onclick="drop('${student['RECORD_FLOW']}')" style="cursor: pointer">[放弃]</a>
							&nbsp;
						</c:if>
						<a onclick="detail('${student['USER_FLOW']}')" style="cursor: pointer">[详情]</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty firstReexamineList}">
				<tr>
					<td colspan="20">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(firstReexamineList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>

</body>
</html>