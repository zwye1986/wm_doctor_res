<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
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
		.label td{
			width:120px;height:35px;text-align:center;border:1px solid gray;cursor:pointer;
		}
		.label td.on{background-color:#FF9933;}
	</style>
	<script type="text/javascript">
		$(function(){
			<c:forEach items="${gradeList}" var="info" varStatus="i">
			var sum = 0;
			for(var i = 0;i<$(".${info.TICKET_NUMBER}").size();i++){
				sum+=Number($(".${info.TICKET_NUMBER}").eq(i).text());
				$(".sum${info.TICKET_NUMBER}").text(sum==0?"":sum);
			}
			</c:forEach>
			$('#trainingSpeId option').hide();
			$('#trainingSpeId option[value=""]').show();
			$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
		})
		function toPage4(page){
            var exmp=$("select[name='clinicalFlow']").val();
			var exmp1=$("select[name='speId']").val();
			if(exmp1==""||exmp1==null){
				jboxTip("请选择培训专业！");
				return;
			}
            if(exmp==""||exmp==null){
                jboxTip("请选择考核名称！");
				return;
            }
			$("#currentPage4").val(page);
			jboxPostLoad("doctorScoreDiv","<s:url value="/osca/city/gradeManageList"/>",$("#doctorScoreForm").serialize(),true);
		}
        function isShowOpt(value){
			if(value=="city"){
				jboxConfirm("成绩上报后不可修改，请确认",function(){
					var page=$("#currentPage4").val();
					var exmp=$("select[name='clinicalFlow']").val();
					var exmp1=$("select[name='speId']").val();
					if(exmp1==""||exmp1==null){
						jboxTip("请选择培训专业！");
						return;
					}
					if(exmp==""||exmp==null){
						jboxTip("请选择考核名称！");
						return;
					}
					var exmp2='${clinicalFlow}';
					if(exmp2==""||exmp2==null){
						jboxTip("请点击查询后再执行此操作！");
						return;
					}
					var url = "<s:url value='/osca/base/isShowOpt?clinicalFlow=${clinicalFlow}&isShow='/>"+value;
					jboxPost(url, null, function (resp) {
						toPage4(page);
					}, null, true);
				})
			}
        }

		function isShowForm(value){
			var url = "<s:url value='/osca/base/isShowFroom?clinicalFlow=${clinicalFlow}&isShowFroom='/>"+value;
			jboxPost(url, null, function (resp) {
			}, null, true);
		}
        function gradeImport(){
            jboxOpen("<s:url value='/jsp/osca/base/importGrade.jsp?clinicalFlow=${clinicalFlow}'/>", "成绩导入",600,200);
        }
        function exportScoreExcel(){
			var clinicalFlow='${clinicalFlow}';
			var subjectFlow='${subjectFlow}';
			var ticketNumber=$("input[name='ticketNumber']").val();
			var gradeDoctorName=$("input[name='gradeDoctorName']").val();
			var trainCategoryId=$("select[name='trainCategoryId']").val();
			var resultId=$("select[name='resultId']").val();
			var order=$("input[name='order']:checked").val();
			var data = {
				clinicalFlow:clinicalFlow,
				subjectFlow:subjectFlow,
				ticketNumber:ticketNumber,
				gradeDoctorName:gradeDoctorName,
				trainCategoryId:trainCategoryId,
				resultId:resultId,
				order:order
			};
            var url = "<s:url value='/osca/doctorScore/exportScoreExcel'/>?clinicalFlow="+clinicalFlow+"&subjectFlow="+subjectFlow+"&ticketNumber="+ticketNumber+"&gradeDoctorName="+gradeDoctorName+"&trainCategoryId="+trainCategoryId+"&resultId="+resultId+"&order="+order;
            jboxTip("导出中…………");
			$("#exportA").attr("href",url);
			$("#outToExcelSpan").click();
        }
		function editGradeOpt(doctorFlow){
            var clinicalFlow='${clinicalFlow}';
            var subjectFlow='${subjectFlow}';
			jboxOpen("<s:url value='/osca/base/editGradeOpt?doctorFlow='/>"+doctorFlow+"&clinicalFlow="+clinicalFlow+"&subjectFlow="+subjectFlow+"&currentPage="+$("#currentPage4").val(), "成绩编辑",480,530);
		}

		function selectSpe1(){
			jboxPost("<s:url value='/osca/doctorScore/querySpeRelation'/>", $("#doctorScoreForm").serialize(), function (resp) {
				$("#clinicalFlow").empty();
				if (null != resp && resp.length > 0) {
					for(var i= 0;i<resp.length;i++){
						$("#clinicalFlow").append("<option value='"+resp[i].clinicalFlow+"'>"+resp[i].clinicalName+"</option>");
					}
				}else {
					$("#clinicalFlow").append("<option value=''></option>");
				}
			},null,false);
		}

		function initSpe(){
			var value = $("select[name='isLocal']").val();
			if(value == "N"){
				jboxPost("<s:url value='/osca/base/queryInitSpe'/>", $("#doctorScoreForm").serialize(), function (resp) {
					$("#speId").empty();
					if (null != resp && resp.length > 0) {
						for(var i= 0;i<resp.length;i++){
							$("#speId").append("<option value='"+resp[i].speId+"'>"+resp[i].speName+"</option>");
						}
					}
					selectSpe1();
				},null,false);
			}else if(value == "Y"){
				$("#speId").empty();
				var html = "<option/><c:forEach items='${dictTypeEnumCheckSpeList}' var='dict'><option value='${dict.dictId}' ${osa.speId eq dict.dictId?'selected':''}>${dict.dictName}</option></c:forEach>";
				$("#speId").append(html);
				selectSpe1();
			}
		}

		function searchScoreForm(doctorFlow,stationFlow){
			jboxOpen("<s:url value='/osca/base/searchScoreForm?clinicalFlow=${clinicalFlow}&doctorFlow='/>"+doctorFlow+"&stationFlow="+stationFlow, "评分表",600,600);
		}

		function exportAllScore(){
			var clinicalYear=$("input[name='clinicalYear']").val();
			var msg="确认导出"+clinicalYear+"年结业考核成绩吗?";
			if(clinicalYear==""){
				msg="确认导出结业考核成绩吗?";
			}
			jboxConfirm(msg, function () {
				jboxStartLoading();
				var url = "<s:url value='/osca/doctorScore/exportAllScore'/>?clinicalYear="+clinicalYear+"&isLocal=N&flag=city";
				jboxTip("导出中…………");
				window.location.href=url;
				jboxEndLoading();
			});
		}
		function linkageSubject(dictId){
			$('#trainingSpeId').val("");//清空上次展现数据
			$('#trainingSpeId option').hide();
			$('#trainingSpeId option[value=""]').show();
			$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="doctorScoreDiv">
        <div class="queryDiv">
		<form id="doctorScoreForm" method="post">
			<%--<div class="choseDivNewStyle" style="min-width: 1180px;">--%>
				<%--<input type="hidden" name="clinicalName" value="${param.clinicalName}"/>--%>
				<%--<input type="hidden" name="speName" value="${param.speName}"/>--%>
				<%--<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>--%>
				<input type="hidden" name="subjectFlow" value="${subjectFlow}"/>
				<input type="hidden" name="isLocal" value="N"/>
				<input type="hidden" name="flag" value="city"/>
				<input type="hidden" id="currentPage4" name="currentPage4" value="${param.currentPage4}"/>
				<input type="hidden" name="role" value="${role}"/>
				<a class="btn" id="exportA" type="hidden"><span id="outToExcelSpan"> </span></a>
				<%--<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">--%>
					<%--<tr>--%>
						<%--<td style="border:0px;">--%>
							<%--<span style="padding-left:26px;"></span>--%>
                        <div class="inputDiv">
                            <label class="qlable">年&#12288;&#12288;份：</label>
							<input type="text" class="qtext" name="clinicalYear" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" value="${(empty param.clinicalYear)?currYear:param.clinicalYear }" onchange="selectSpe1()"/>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">培训基地：</label>
                            <input type="text" name="orgName" class="qtext" value="${param.orgName}">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">所在考点：</label>
							<select name="orgFlow2"  class="qselect" onchange="selectSpe1()">
								<option value="">全部</option>
								<c:forEach items="${examOrgList}" var="item">
									<option value="${item.orgFlow}"
											<c:if test="${item.orgFlow eq param.orgFlow2}">selected</c:if>>${item.orgName}</option>
								</c:forEach>
							</select>
                        </div>
				<div class="inputDiv">
					培训类型：
					<select name="trainingTypeId" class="qselect" onchange="linkageSubject(this.value)">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					培训专业：
					<c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
					<select id="trainingSpeId" name="speId" class="qselect" onchange="selectSpe1()">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							<c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
							<c:forEach items="${applicationScope[dictKey]}" var="scope">
								<option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.speId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
							</c:forEach>
						</c:forEach>
					</select>
				</div>
                        <div class="inputDiv">
                            <input type="hidden" id="actionTypeName" name="actionTypeName" value="${osa.actionTypeName}">
                            <label class="qlable">考核名称：</label>
                            <select id="clinicalFlow" name="clinicalFlow"  class="qselect">
                                <c:if test="${not empty osaList}">
                                    <c:forEach var="osaCF" items="${osaList}">
                                        <option value="${osaCF.clinicalFlow}" <c:if test="${param.clinicalFlow eq osaCF.clinicalFlow}">selected="selected"</c:if>>${osaCF.clinicalName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <input type="hidden" id="clinicalName" name="clinicalName" style="width:130px;" value="${osa.clinicalName}">
                            <label class="qlable">考试阶段：</label>
                            <select name="trainCategoryId"  class="qselect">
                                <option value="">全部</option>
                                <c:if test="${not empty trainCategoryEnumList}">
                                    <c:forEach items="${trainCategoryEnumList}" var="cate">
                                        <option value="${cate.id}" ${param.trainCategoryId eq cate.id?'selected':''}>${cate.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">考核结果：</label>
                            <select name="resultId"  class="qselect">
                                <option value="">全部</option>
                                <c:if test="${not empty doctorScoreEnumList}">
                                    <c:forEach items="${doctorScoreEnumList}" var="rlt">
                                        <option value="${rlt.id}" ${param.resultId eq rlt.id?'selected':''}>${rlt.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">准考证号：</label>
                            <input type="text" name="ticketNumber" class="qtext" value="${param.ticketNumber}">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">姓&#12288;&#12288;名：</label>
                            <input type="text" name="gradeDoctorName" class="qtext" value="${param.gradeDoctorName}">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">成绩排序：</label>
                            <input type="radio" name="order" value="ASC" ${param.order eq 'ASC'?'checked':''}>升序&nbsp;
                            <input type="radio" name="order" value="DESC" ${param.order eq 'DESC'?'checked':''}>降序
                        </div>
                        <div class="lastDiv">
							<input type="button" class="searchInput" value="查&#12288;询" name="searchDoctor" onclick="toPage4(1)"/>
						</div>
                        <div class="funcDiv">
							<span style="display:inline-block;margin-top:20px;">
							<font style="color:red;">当前考核通过率<c:if test="${not empty gradeList}">${percent}</c:if>%（说明：查询结果通过率=考核通过人数/参加考核人数）<br/>
								总通过率${allPercent}%（总通过率=全部专业考核通过人员/全部专业参加考核人员）
							</font>								&#12288;
								<input type="button" class="search" value="成绩导入" onclick="gradeImport()"/>
								<input type="button" class="search" value="成绩导出" onclick="exportScoreExcel()"/>
								<c:if test="${osa.isGradeReleased eq 'S'}">
									<input type="button" class="search" value="成绩上报" onclick="isShowOpt('city')"/>
								</c:if>
								<c:if test="${not empty osa.isGradeReleased and osa.isGradeReleased ne 'S' and osa.isGradeReleased ne 'N'}">
									<input type="button" class="search" value=" 已 上 报 " style="cursor: auto;"/>
								</c:if>
								<input type="button" class="search" value="导出全部成绩" onclick="exportAllScore()"/>
                        </div>
							<%--</span>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%--</table>--%>
			<%--</div>--%>
		</form>
        </div>
		<table class="xllist" style="width:100%;">
			<tr style="background-color:#F5F5F5;">
				<td rowspan="2">序号</td>
				<td rowspan="2">准考证号</td>
				<td rowspan="2">姓名</td>
				<td rowspan="2">培训基地</td>
				<td rowspan="2">所在单位</td>
				<td rowspan="2">培训专业</td>
				<td rowspan="2">考试阶段</td>
				<td colspan="${fn:length(stationList)+1}">成绩</td>
				<td rowspan="2">考核结果</td>
				<td rowspan="2">操作</td>
			</tr>
			<tr style="background-color:#F5F5F5;">
				<c:set var="count" value="0"></c:set>
				<c:if test="${not empty stationList}">
				<c:forEach items="${stationList}" var="station">
					<td style="line-height: 130%">${station.stationName}<br/>${station.stationScore}</td>
					<c:set var="count" value='${count + station.stationScore}'></c:set>
				</c:forEach>
				</c:if>
				<td style="line-height: 130%">总分<c:if test="${not empty stationList}"><br/><fmt:formatNumber type="number" value="${count}" /></c:if></td>
			</tr>
			<c:if test="${not empty gradeList}">
				<c:forEach items="${gradeList}" var="info" varStatus="i">
					<tr style="${info.IS_PASS eq 'Miss'?'background-color:#D7D7D7':''}">
						<td>${i.index + 1}</td>
						<td>${info.TICKET_NUMBER}</td>
						<td>${info.DOCTOR_NAME}</td>
						<td>${info.ORG_NAME}</td>
						<td>${info.WORK_ORG_NAME}</td>
						<td>${info.SPE_NAME}</td>
						<td>${info.TRAINING_TYPE_NAME}</td>
						<c:set var="stuCount" value='0'></c:set>
						<c:if test="${not empty stationList}">
						<c:forEach begin="0" end="${fn:length(stationList)-1}" varStatus="s">
							<c:forEach items="${fn:split(info.STATION_FLOW,',')}" var="sta" varStatus="si">
								<c:if test="${sta eq stationList[s.index].stationFlow}">
									<td>
										<a class="${info.TICKET_NUMBER}" onclick="searchScoreForm('${info.DOCTOR_FLOW}','${sta}')" style="cursor:pointer;color:#4195C5;">
										<fmt:formatNumber type="number" pattern="0" value="${fn:split(info.EXAM_SCORE,',')[si.index] eq '*'?'':fn:split(info.EXAM_SCORE,',')[si.index]+0.001}"/>
										</a>
										<%--<c:set var="stuCount" value="${stuCount + fn:split(info.EXAM_SCORE,',')[si.index]}"></c:set>--%>
									</td>
									<c:set var="exitFlag" value="${s.index}"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${s.index ne exitFlag || empty exitFlag}">
								<td></td>
								<c:set var="exitFlag"></c:set>
							</c:if>
						</c:forEach>
						</c:if>
						<td class="sum${info.TICKET_NUMBER}">
							<%--<fmt:formatNumber type="number" pattern="0" maxFractionDigits="0" value="${stuCount eq 0?'':stuCount+ 0.0001}" />--%>
						</td>
						<td>${info.IS_PASS_NAME}</td>
						<td>
							<c:if test="${info.IS_PASS eq 'Miss'||(osa.isGradeReleased ne 'S')}"><a style="color:#4195c5;">编辑</a></c:if>
							<c:if test="${info.IS_PASS ne 'Miss'&&(osa.isGradeReleased eq 'S')}"><a onclick="editGradeOpt('${info.DOCTOR_FLOW}')" style="cursor:pointer;color:#4195c5;">编辑</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div style="float:right;margin-top:100px;">
			<c:if test="${not empty gradeList}">
				<c:set var="pageView" value="${pdfn:getPageView(gradeList)}" scope="request"/>
				<pd:pagination toPage="toPage4"/>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>