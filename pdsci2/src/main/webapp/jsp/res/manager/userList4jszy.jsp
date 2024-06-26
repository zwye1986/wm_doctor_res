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
			$("#spe").empty();
			$("#spe").append($("."+this.value).clone());
			if(this.value=='${recDocCategoryEnumIntern.id}'){
				$("#work").show();
			}else{
				$("#work").hide();
				$("#workOrgId").val("");
			}
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
		<%--jboxOpen("<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}',"编辑医师信息",950,490);--%>
		var url = "<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}'
		var mainIframe = window.parent.frames["mainIframe"];
		var width = 950;
		var height = 490;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'用户信息',width,height);
	}
	function detailDoc(doctorFlow){
		var url = "<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}&editButtonFlag=no';
		var mainIframe = window.parent.frames["mainIframe"];
		var width = 950;
		var height = 490;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'用户信息',width,height);

	}
	function changeGroupRelated(doctorFlow,value,type){
		var url = "<s:url value='/res/doc/user/saveGroupRelated'/>";
		var data = {
			doctorFlow:doctorFlow,
			value:value,
			type:type
		};
		jboxPost(url, data, null,null,true);
	}
	
	<%--$(function(){--%>
		<%--<c:if test="${empty param.doctorCategoryId}">--%>
		<%--if($("[name='doctorCategoryId'] option").length == 2){--%>
			<%--$("[name='doctorCategoryId'] option:last").attr("selected","selected").parent().change();--%>
		<%--}--%>
		<%--</c:if>--%>
	<%--});--%>

	function exportDoc(){
		if(${empty doctorList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/manager/exportDoc?role=${role}'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}

	function responsibleTeacherCfg(doctorFlow) {
		jboxOpen("<s:url value='/res/responsibleTeacher/responsibleTeacherCfg'/>?doctorFlow="+doctorFlow+"&currentPage=${currentPage}","责任导师", 900, 400,false);
	}
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
	function importDisciple() {
		var url = "<s:url value='/jsp/res/manager/importDisciple.jsp'/>";
		jboxOpen(url, "导入师承老师", 390, 180);

	}
	function importResponsible() {
		var url = "<s:url value='/jsp/res/manager/importResponsible.jsp'/>";
		jboxOpen(url, "导入责任导师", 390, 180);

	}
	function daoRu(){
		var a = $("#orgFlow").val();
		if('${role}' == 'university'){
			a='';
		}else if(!a){
			jboxTip("请先选择基地");
			return;
		}
		var url = "<s:url value='/jsp/res/college/importStudentMain4jszy.jsp'/>?orgFlow="+a+"&role=${role}";
		jboxOpen(url, "导入",700,290);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/manager/userList/${role}'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<select id="doctorCategory" name="doctorCategoryId" class="qselect">
								<option />
								<c:forEach items="${recDocCategoryEnumList}" var="category">
									<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
									<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
										<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
							<div class="inputDiv">
								<label class="qlable">组&#12288;&#12288;别：</label>
								<select name="groupId" class="qselect" >
									<option></option>
									<c:forEach items="${dictTypeEnumResGroupList}" var="group">
										<option value="${group.dictId}" ${param.groupId eq group.dictId?'selected':''}>${group.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<div class="inputDiv">
							<label class="qlable">对应专业：</label>
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
						<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne role}">
							<div class="doctorTypeDiv">
								<div class="doctorTypeLabel">学员类型：</div>
								<div class="doctorTypeContent">
									<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
										<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}"
											${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq role || GlobalConstant.USER_LIST_UNIVERSITY eq role}">
							<div class="inputDiv">
								<label class="qlable">培训基地：</label>
								<select name="orgFlow" id="orgFlow" class="qselect" >
									<option value="">全部</option>
									<c:forEach items="${orgs}" var="org">
										<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
						</div>
						<%--<div class="inputDiv">--%>
							<%--<label class="qlable">结业考核年份：</label>--%>
							<%--<input  class="qtext" type="text" name="graduationYear" value="${param.graduationYear}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">--%>
						<%--</div>--%>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
							<%--<select name="sessionNumber" class="qselect" >--%>
								<%--<option value="">全部</option>--%>
								<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
									<%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</div>
						<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
							<c:if test="${sysCfgMap['res_org_ad_doc'] ne GlobalConstant.FLAG_Y and role ne GlobalConstant.USER_LIST_UNIVERSITY}">
								<input type="button" class="searchInput" value="导&#12288;出" onclick="exportDoc();"/>
							</c:if>
							<c:if test="${sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y or role eq GlobalConstant.USER_LIST_UNIVERSITY}">
								&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
							</c:if>
						</div>
					</div>
					<div class="funcDiv">
						<c:if test="${role ne GlobalConstant.USER_LIST_UNIVERSITY}">
							<c:if test="${sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y}">
								<input type="button" value="新&#12288;增" class="search" onclick="editDoc('');"/>
								<input type="button" class="search" value="导&#12288;出" onclick="exportDoc();"/>
							</c:if>
							<c:if test="${not empty sysCfgMap['res_disciple_role_flow']}">
								<input type="button" class="search" value="导入师承老师" onclick="importDisciple();"/>
							</c:if>
							<c:if test="${not empty sysCfgMap['res_responsible_teacher_role_flow']}">
								<input type="button" class="search" value="导入责任导师" onclick="importResponsible();"/>
							</c:if>
						</c:if>
						<c:if test="${role eq GlobalConstant.USER_LIST_UNIVERSITY}">
							<c:if test="${sysCfgMap['res_university_edit_doc'] eq GlobalConstant.FLAG_Y}">
								<input type="button" value="新&#12288;增" class="search" onclick="editDoc('');"/>
								<input type="button" value="导&#12288;入" class="search" onclick="daoRu();"/>
							</c:if>
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
						<td style="text-align: center;padding: 0px;width: 10%;">培训专业</td>
						<td style="text-align: center;padding: 0px;width: 15%;">对应专业</td>
<!-- 						<td style="text-align: center;padding: 0px;width: 15%;">毕业院校</td> -->
						<%--<td style="text-align: center;padding: 0px;width: 18%;">轮转方案</td>--%>
						<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne role}">
							<td style="text-align: center;padding: 0px;width: 8%;">责任导师</td>
						</c:if>
						<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
							<td style="text-align: center;padding: 0px;width: 9%;">组别</td>
						</c:if>
						<td style="text-align: center;padding: 0px;width: 5%;">状态</td>
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
							<td style="text-align: center;padding: 0px;">${doctor.trainingSpeName}</td>
<%-- 							<td style="text-align: center;padding: 0px;">${doctor.graduatedName}</td> --%>
							<%--<td style="text-align: center;padding: 0px;">${doctor.rotationName}</td>--%>
							<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne role}">
								<td style="text-align: center;padding: 0px;">
									<a style="color: blue;cursor: pointer" onclick="responsibleTeacherCfg('${doctor.doctorFlow}')">
										<c:if test="${ empty teaMap[doctor.doctorFlow]}">请选择</c:if>
										<c:forEach items="${teaMap[doctor.doctorFlow]}" var="item" varStatus="s">
											<c:if test="${s.index+1 <  teaMap[doctor.doctorFlow].size()}">
												${item.responsibleteacherName},
											</c:if>
											<c:if test="${s.index+1 ==  teaMap[doctor.doctorFlow].size()}">
												${item.responsibleteacherName}
											</c:if>
										</c:forEach>
									</a>
								</td>
							</c:if>
							<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
								<td style="text-align: center;padding: 0px;">
									<select name="groupId" class="validate[required]" onchange="changeGroupRelated('${doctor.doctorFlow}',this.value,'groupId');">
										<option></option>
										<c:forEach items="${dictTypeEnumResGroupList}" var="group">
											<option value="${group.dictId}" ${doctor.groupId eq group.dictId?'selected':''}>${group.dictName}</option>
										</c:forEach>
									</select>
								</td>
							</c:if>
							<td style="text-align: center;padding: 0px;">${doctor.doctorStatusName}</td>
							<td style="text-align: center;padding: 0px;">
								<c:choose>
									<c:when test="${GlobalConstant.USER_LIST_UNIVERSITY ne role ||
											(GlobalConstant.USER_LIST_UNIVERSITY eq role && sysCfgMap['res_university_edit_doc'] eq GlobalConstant.FLAG_Y)}">
										<a style="color: blue;cursor: pointer;" onclick="editDoc('${doctor.doctorFlow}');">编辑</a>
									</c:when>
									<c:otherwise>
										<a style="color:blue;cursor:pointer;" onclick="detailDoc('${doctor.doctorFlow}');">详情</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					
					<%--<c:if test="${empty param.doctorCategoryId}">--%>
						<%--<tr><td style="text-align: center;" colspan="9">请选择培训类别!</td></tr>--%>
					<%--</c:if>--%>
					<%--<c:if test="${!empty param.doctorCategoryId}">--%>
						<c:if test="${empty doctorList}">
							<tr><td style="text-align: center;" colspan="10">无记录</td></tr>
						</c:if>
					<%--</c:if>--%>
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