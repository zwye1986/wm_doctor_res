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
	
	function editDoc(doctorFlow){
		var url = "<s:url value='/sch/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}'
		var mainIframe = window.parent.frames["mainIframe"];
		var width = 950;
		var height = 490;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'学员信息',width,height);
	}
	function showDoc(doctorFlow){
		var url = "<s:url value='/sch/manager/showDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}'
		var mainIframe = window.parent.frames["mainIframe"];
		var width = 950;
		var height = 490;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'学员信息',width,height);
	}

	function delDoc(docFlow){
		jboxConfirm("确认删除该学员吗？",function () {
			var url = "<s:url value='/sch/manager/delete?userFlow='/>"+docFlow;
			jboxGet(url,null,function(){
				searchUser();
			});
		});
	}

	function exportDoc(){
		if(${empty doctorList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/sch/manager/exportDoc'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}

	function daoRu(){
		var a = $("#orgFlow").val();
		if(a){
			var url = "<s:url value='/jsp/sch/manager/importStudent.jsp'/>?orgFlow="+a;
			jboxOpen(url, "导入",700,290);
		}else{
			jboxTip("请先选择基地");
		}
	}
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/sch/manager/userList'/>">
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
						<div class="inputDiv" id="secondSpe" style="display: none;">
							<label class="qlable">二级专业：</label>
							<select id="secondSpeId" name="secondSpeId" class="qselect">
								<option></option>
								<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
									<option  value="${dict.dictId}" ${param.secondSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
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
						<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
								&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
						</div>
					</div>
					<div class="funcDiv">
						<c:if test="${sysCfgMap['sch_add_student'] eq GlobalConstant.FLAG_Y and sysCfgMap['sch_inRes_flag'] ne GlobalConstant.FLAG_Y}">
							<input type="button" value="新&#12288;增" class="search" onclick="editDoc('');"/>
							<input type="button" value="导&#12288;入" class="search" onclick="daoRu();"/>
							<input type="button" class="search" value="导&#12288;出" onclick="exportDoc();"/>
						</c:if>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;width: 8%;">姓名</td>
						<td style="text-align: center;padding: 0px;width: 5%;">性别</td>
						<td style="text-align: center;padding: 0px;width: 8%;">手机</td>
						<td style="text-align: center;padding: 0px;width: 9%;">身份证</td>
						<td style="text-align: center;padding: 0px;width: 8%;">年级</td>
						<td style="text-align: center;padding: 0px;width: 10%;">培训类别</td>
						<td style="text-align: center;padding: 0px;width: 15%;">培训专业</td>
						<td style="text-align: center;padding: 0px;width: 5%;">学员类型</td>
						<td style="text-align: center;padding: 0px;width: 5%;">操作</td>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr>
							<td  title="<img src='${sysCfgMap['upload_base_url']}/${doctor.sysUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"
									style="text-align: center;padding: 0px;">${doctor.doctorName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.sexName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.userPhone}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.idNo}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingTypeName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingSpeName}
							<c:if test="${recDocCategoryEnumChineseMedicine.id eq doctor.doctorCategoryId}">
								(${doctor.secondSpeName})
							</c:if>
							</td>
							<td style="text-align: center;padding: 0px;">${doctor.doctorTypeName}</td>
							<td style="text-align: center;padding: 0px;">
								<c:if test="${sysCfgMap['sch_add_student'] eq GlobalConstant.FLAG_Y and sysCfgMap['sch_inRes_flag'] ne GlobalConstant.FLAG_Y}">
									<a style="color: blue;cursor: pointer;" onclick="editDoc('${doctor.doctorFlow}');">编辑</a>
									<a style="color: blue;cursor: pointer;" onclick="delDoc('${doctor.doctorFlow}');">删除</a>
								</c:if>
								<c:if test="${!(sysCfgMap['sch_add_student'] eq GlobalConstant.FLAG_Y and sysCfgMap['sch_inRes_flag'] ne GlobalConstant.FLAG_Y)}">
									<a style="color: blue;cursor: pointer;" onclick="showDoc('${doctor.doctorFlow}');">查看</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty doctorList}">
						<tr><td style="text-align: center;" colspan="10">无记录</td></tr>
					</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>