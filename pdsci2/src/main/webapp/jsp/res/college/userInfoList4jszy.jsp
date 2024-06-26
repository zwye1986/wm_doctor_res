
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
	<style type="text/css">
		.btn {
			display: inline-block;
			overflow: visible;
			padding: 0 22px;
			height: 30px;
			line-height: 30px;
			vertical-align: middle;
			text-align: center;
			text-decoration: none;
			border-radius: 3px;
			-moz-border-radius: 3px;
			-webkit-border-radius: 3px;
			font-size: 14px;
			border-width: 1px;
			border-style: solid;
			cursor: pointer;
		}

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
		var docType = $("input[name='doctorTypeIdList']:checked");
		if(docType.size() < 1){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#searchForm").submit();
	}
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	
	function pageSearch(){
		toPage("${param.currentPage}");
	}
	
	function searchUser(){
		pageSearch();
	}
	
	function editDoc(doctorFlow){
		<%--jboxOpen("<s:url value='/res/platform/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow,"编辑医师信息",950,490);--%>
		var url = "<s:url value='/res/platform/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow;
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
	
	$(function(){
		<c:if test="${empty doctorCategoryId}">
			if($("[name='doctorCategoryId'] option").length == 2){
				$("[name='doctorCategoryId'] option:last").attr("selected","selected").parent().change();
			}
		</c:if>
		<c:if test="${empty doctorTypeSelectMap}">
			var docType = $("input[name='doctorTypeIdList']");
			docType.each(function(index, domEle){
				$(domEle).attr("checked","checked");
			});
		</c:if>
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
	function daoRu(){
		var a = $("#orgFlow").val();
		if(a){
		var url = "<s:url value='/jsp/res/college/importStudentMain4jszy.jsp'/>?orgFlow="+a;
		jboxOpen(url, "导入",700,290);
		}else{
			jboxTip("请先选择基地");
		}
	}

	function exportDoc(){
		if(${empty doctorList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/platform/exportDoc'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}

	function exportDoc2(){
		if(${empty doctorList}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/platform/exportDoc2'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
	}

	function addArchive(){
		jboxOpen('<s:url value="/hbres/archive/addArchive"/>',"",300,200,true);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">

				<form id="searchForm" method="post" action="<s:url value='/res/platform/userInfoList'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<input type="hidden" name="roleFlag" value="${roleFlag}"/>
					<input id="" type="hidden" name="isQuery" value="Y"/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
							<%--<select name="sessionNumber"  class="qselect">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">--%>
									<%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</div>
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow"  id="orgFlow"  class="qselect">
								<option/>
								<c:forEach items="${applicationScope.sysOrgList}" var="org">
									<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">证件号码：</label>
							<input type="text"  class="qtext" name="sysUser.idNo" value="${param['sysUser.idNo']}" >
						</div>
						<div class="inputDiv" style="min-width: 278px;max-width: 278px;">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="doctorName" value="${param.doctorName}"  class="qtext">
						</div>
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
						<div class="inputDiv">
							<label class="qlable">医师状态：</label>
							<select name="doctorStatusId" class="qselect">
								<option></option>
								<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
									<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 278px;max-width: 278px;">
							<label class="qlable">结业考核年份：</label>
							<input type="text" class="qtext" name="graduationYear" readonly="readonly" id="graduationYear" value="${param.graduationYear}"
								   onclick="WdatePicker({dateFmt:'yyyy'})" >
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
						<div class="lastDiv" style="margin-left:65px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						</div>
					</div>
				<div class="funcDiv">
					<input type="button" value="新&#12288;增" class="search" onclick="editDoc('');"/>
					<input type="button" value="导&#12288;入" class="search" onclick="daoRu();"/>
					<input type="button" class="search" value="导&#12288;出" onclick="exportDoc();"/>
					<c:if test="${cundang ne GlobalConstant.FLAG_N}">
						<input type="button" class="search" value="国家医师协会名册" onclick="exportDoc2();"/>
						<input type="button" class="search" value="学员信息存档" onclick="addArchive();"/>
					</c:if>
				</div>
				</form>
				<div class="resultDiv">
				<table class="basic" width="100%">
					<%--<tr>--%>
						<%--<th colspan="10" style="text-align: left;">&#12288;实习安排</th>--%>
					<%--</tr>--%>
					<tr>
						<th style="text-align: center;padding: 0px;width: 5%;">姓名</th>
						<%--<td style="text-align: center;padding: 0px;width: 5%;">学/工号</td>--%>
						<th style="text-align: center;padding: 0px;width: 4%;">性别</th>
						<th style="text-align: center;padding: 0px;width: 10%;">身份证</th>
						<th style="text-align: center;padding: 0px;width: 4%;">年级</th>
						<th style="text-align: center;padding: 0px;width: 5%;">培训专业</th>
						<th style="text-align: center;padding: 0px;width: 12%;">对应专业</th>
 						<th style="text-align: center;padding: 0px;width: 4%;">培养年限</th>
						<th style="text-align: center;padding: 0px;width: 5%;" nowrap="">结业考核年份</th>
 						<th style="text-align: center;padding: 0px;width: 5%;">学员类型</th>
 						<th style="text-align: center;padding: 0px;width: 5%;">学员状态</th>
						<th style="text-align: center;padding: 0px;width: 15%;">培训基地</th>
						<c:if test="${doctorCategoryId eq recDocCategoryEnumIntern.id}">
							<th style="text-align: center;padding: 0px;width: 9%;">组间职务</th>
						</c:if>
						<th style="text-align: center;padding: 0px;width: 5%;">操作</th>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr>
							<td style="text-align: center;padding: 0px;">${doctor.doctorName}</td>
							<%--<td style="text-align: center;padding: 0px;">${doctor.doctorCode}</td>--%>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.sexName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.idNo}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;padding: 0px;">${doctor.doctorCategoryName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingSpeName}
							</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingYears}</td>
							<td style="text-align: center;padding: 0px;">${doctor.graduationYear}</td>
							<td style="text-align: center;padding: 0px;">${doctor.doctorTypeName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.doctorStatusName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.orgName}</td>
							<c:if test="${doctorCategoryId eq recDocCategoryEnumIntern.id}">
								<td style="text-align: center;padding: 0px;">
									<select name="groupRoleId" onchange="changeGroupRelated('${doctor.doctorFlow}',this.value,'groupRoleId');">
										<option></option>
										<c:forEach items="${groupRoleEnumList}" var="groupRole">
											<option value="${groupRole.id}" ${doctor.groupRoleId eq groupRole.id?'selected':''}>${groupRole.name}</option>
										</c:forEach>
									</select>
								</td>
							</c:if>
							<td style="text-align: center;padding: 0px;">
								<a style="color:blue;cursor:pointer;" onclick="editDoc('${doctor.doctorFlow}');">编辑</a>
							</td>
						</tr>
					</c:forEach>
					
					<c:if test="${empty doctorList}">
						<tr><td style="text-align: center;" colspan="99">无记录</td></tr>
					</c:if>
				</table>
				</div>
				<div class="resultDiv">
				   	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>