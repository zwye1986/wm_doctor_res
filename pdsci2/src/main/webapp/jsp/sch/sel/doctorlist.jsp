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

			if(this.value=="${recDocCategoryEnumChineseMedicine.id}")
			{
				$("#secondSpe").show();
			}else{
				$("#secondSpe").hide();
				$("#secondSpeId").val("");
			}
			$("#spe").empty();
			$("#spe").append($("."+this.value).clone());
		}).change();
	});

	function search(){
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}

	function searchUser(){
		toPage("${param.currentPage}");
	}

	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}

	//轮转方案说明
	function openDetail(rotationName,rotationFlow){
		var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
		jboxOpen(url, rotationName, 800, 500);
	}
	function openSelDept(doctorFlow,selFlag){
		if("Y"==selFlag) {
			var url = "<s:url value='/sch/sel/seldept'/>?role=admin&doctorFlow=" + doctorFlow;
			jboxOpen(url, "查看选科", 1100, 500);
		}else{
			if("${result}"=="")
			{
				var url = "<s:url value='/sch/sel/seldept'/>?role=admin&doctorFlow=" + doctorFlow;
				jboxOpen(url, "选科", 1100, 500);
			}else{
				jboxInfo("${result}");
			}

		}
	}
	function openSchResult(doctorFlow,selFlag){
		var url = "<s:url value='/sch/sel/arrange/results'/>?role=admin&doctorFlow=" + doctorFlow;
		jboxOpen(url, "查看排班信息", 900, 500);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/sch/sel/doctorList'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<input id="orgFlow" type="hidden" name="orgFlow" value="${orgFlow}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">培训类别：</label>
							<select id="doctorCategory" name="doctorCategoryId" class="qselect">
								<option value="">全部</option>
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
								<option></option>
							</select>
							<div style="display: none;">
								<option
										class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								${recDocCategoryEnumWMFirst.id} |
								${recDocCategoryEnumWMSecond.id} |
								${recDocCategoryEnumAssiGeneral.id} |
								${recDocCategoryEnumChineseMedicine.id} |
								${recDocCategoryEnumTCMGeneral.id} |
								${recDocCategoryEnumTCMAssiGeneral.id} |
								" value="">请选择</option>
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
						<%--<div class="inputDiv" id="secondSpe" style="display: none;">--%>
							<%--<label class="qlable">二级专业：</label>--%>
							<%--<select id="secondSpeId" name="secondSpeId" class="qselect">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">--%>
									<%--<option  value="${dict.dictId}" ${param.secondSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</div>--%>
						<div class="doctorTypeDiv">
							<div class="doctorTypeLabel">学员类型：</div>
							<div class="doctorTypeContent">
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
										${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
								</c:forEach>
							</div>
						</div>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="lastDiv" style="min-width: 285px;max-width: 285px;">
							<input type="checkbox" value="${GlobalConstant.FLAG_Y}" name="selAllFlag" <c:if test="${param.selAllFlag eq GlobalConstant.FLAG_Y}">checked</c:if> onchange="search();"/>
							只看未完成&#12288;&#12288;&#12288;&#12288;
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
								&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
						</div>
					</div>
				</form>
				<div>
					<c:forEach items="${doctorList}" var="doctor">
						<table class="basic" style="width:100%;margin-top: 10px;">
							<tr class="checkRotation" rotationFlow="${doctor.rotationFlow}" orgFlow="${doctor.orgFlow}" doctorFlow="${doctor.doctorFlow}">
								<th style="text-align: left;padding-left: 10px;">
									<span style="display: inline-block;width: 10%;">姓名：${doctor.doctorName}</span>
									<span style="display: inline-block;width: 10%;">年级：${doctor.sessionNumber}</span>
									<span style="display: inline-block;width: 15%;">选科状态：<font id="selDeptStatus${doctor.doctorFlow}">${doctor.selAllFlag eq GlobalConstant.FLAG_Y ? '已完成':'未完成'}</font></span>
									<span style="display: inline-block;width: 10%;">专业：${doctor.trainingSpeName}</span>
									<span style="display: inline-block;width: 35%;">
										轮转方案：
										<a onclick="openDetail('${doctor.rotationName}','${doctor.rotationFlow}');" style="cursor: pointer;color: blue;">
												${doctor.rotationName}
										</a>
										<span class="operTipInfo" style="cursor: pointer;margin-left: 20px;background-color: #3d91d5"></span>
									</span>
									<span class="operContentSpan" style="display: inline-block;width: 15%;text-align: left;">
										操作：
										<a onclick="openSelDept('${doctor.sysUser.userFlow}','${doctor.selAllFlag}');" style="cursor: pointer;color: blue;">
												${doctor.selAllFlag eq GlobalConstant.FLAG_Y ? '查看选科':'选科'}
										</a>
										<c:if test="${doctor.schAllFlag eq GlobalConstant.FLAG_Y }">
											<a onclick="openSchResult('${doctor.sysUser.userFlow}');" style="cursor: pointer;color: blue;">
												查看排班
											</a>
										</c:if>
									</span>
								</th>
							</tr>
						</table>
					</c:forEach>
					<c:if test="${empty doctorList and  empty result}">
						<table class="basic" style="width:100%;margin-top: 10px;">
							<tr><td style="text-align: center;">无记录</td></tr>
						</table>
					</c:if>
					<c:if test="${empty doctorList and not empty result}">
						<table class="basic" style="width:100%;margin-top: 10px;">
							<tr><td style="text-align: center;">${ result}</td></tr>
						</table>
					</c:if>
					<div>
						<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
						<pd:pagination toPage="toPage"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>