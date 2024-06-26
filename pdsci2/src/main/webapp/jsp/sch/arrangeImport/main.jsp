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
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
	#searchForm td{border: hidden;width: 10%}
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

	$(function () {
		$("#doctorCategory").change(function(){
			changeSpe()
		});
		$("#spe").change(function(){
			getRotationInfo();
		});
		$("#sessionNumber").change(function(){
			getRotationInfo();
		});
		$("#userName").change(function(){
			getRotationInfo();
		});
		changeSpe();
	});
	//获取相关配置信息
	function getRotationInfo() {
		var doctorCategory=$("#doctorCategory").val()||"";
		if(doctorCategory=="")
		{
			jboxTip("请选择培训类别！");
			return false;
		}
		var sessionNumber=$("#sessionNumber").val()||"";
		if(sessionNumber=="")
		{
			jboxTip("请选择年级！");
			return false;
		}
		var trainingSpeId=$("#spe").val()||"";
		var userName=$("#userName").val()||"";
		if(trainingSpeId=="")
		{
			jboxTip("请选择专业！");
			return false;
		}
		getStudents(doctorCategory,trainingSpeId,sessionNumber,userName);
	}
	function getStudents(doctorCategoryId,trainingSpeId,sessionNumber,userName)
	{
		$("#students").html("");
		$("#arrangeResults").html("");
		var data={
			doctorCategoryId:doctorCategoryId,
			trainingSpeId:trainingSpeId,
			userName:userName,
			sessionNumber:sessionNumber
		}
		jboxPostLoad("students" ,"<s:url value='/sch/arrangeImport/students'/>" ,data , true);
	}
	function changeSpe() {
		var doctorCategory=$("#doctorCategory").val()||"";
		$("#spe").empty();
		$("#spe").append($("."+doctorCategory).clone());
	}
	function openImport() {
		jboxOpen("<s:url value='/jsp/sch/arrangeImport/importPage.jsp'/>","导入",400,250);
	}
	function openImport2() {
		jboxOpen("<s:url value='/jsp/sch/arrangeImport/importPage2.jsp'/>","导入",500,300);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/sch/sel/doctorList'/>">
					<input id="orgFlow" type="hidden" name="orgFlow" value="${orgFlow}"/>
					<input id="cycleTypeId" type="hidden" name="cycleTypeId" />
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">培训类别：</label>
							<select id="doctorCategory" name="doctorCategoryId" class="qselect">
								<c:forEach items="${recDocCategoryEnumList}" var="category">
									<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
									<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
										<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<select id="spe" name="trainingSpeId" class="qselect">
							</select>
							<div style="display: none;">
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option
											class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
									<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
									<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
									<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
									<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
									<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
									<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</div>
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select id="sessionNumber" name="sessionNumber" class="qselect" >
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" id="userName" name="userName" class="qtext">
						</div>
					</div>
					<div class="funcDiv">
						<c:if test="${sysCfgMap['res_inSch_flag'] ne GlobalConstant.FLAG_Y}">
							<div class="lastDiv" style="min-width: 200px;max-width: 200px;">
								<input type="button" value="导&#12288;入" class="searchInput" onclick="openImport();"/>
								<input type="button" value="导&#12288;入2" class="searchInput" onclick="openImport2();"/>
							</div>
						</c:if>
					</div>
				</form>
			</div>
			<div class="content" style="width: 100%;margin-left:10px;">
				<div style="float: left;width: 24%">
					<table class="basic" style="width:100%;">
						<tr style="width: 100%;">
							<td>
								<p>
								<h2>学员</h2>
								</p>
							</td>
						</tr>
						<tr>
							<td style="padding-left: 0px;">
								<div id="students" style="width: 100%;min-height: 350px;max-height: 350px;overflow: auto;">

								</div>
							</td>
						</tr>
					</table>
				</div>
				<div style="float: left;width: 74%">

					<table class="basic" style="width:100%;">
						<tr style="width: 100%;">
							<td>
								<p>
								<h2>排班信息</h2>
								</p>
							</td>
						</tr>
						<tr>
							<td style="padding-left: 0px;">
								<div id="arrangeResults" style="width: 100%;min-height: 350px;max-height: 350px;overflow: auto;">

								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>