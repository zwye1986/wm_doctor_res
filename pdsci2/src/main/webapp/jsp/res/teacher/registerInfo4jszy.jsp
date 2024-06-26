<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	
	<style type="text/css">
		table.basic th,table.basic td{text-align: center;padding: 0px;}
		table a{color: #2f8cef;cursor: pointer;}
		.finish{color:green;font-weight: bold;}
		.toAudit:HOVER{background-color: #eee;}
		.toAudit{cursor: pointer;}
		.toInfo:HOVER{color: orange;}

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
		<c:if test="${roleFlag ne GlobalConstant.USER_LIST_UNIVERSITY}">
			var docType = $("input[name='doctorTypeIdList']:checked");
			if(docType.size() < 1){
				jboxTip("请选择人员类型！");
				return false;
			}
		</c:if>
		jboxStartLoading();
		$("#searchForm").submit();
	}
	$(function () {
		if("${click}" == "${GlobalConstant.FLAG_Y}")
		{
			parent.window.document.getElementById("res-pt-pxtj-jszyreport10").click();
		}
		if("${param.onlyOrg}" == "${GlobalConstant.FLAG_Y}"){
			$("#orgFlow").attr("disabled","disabled");
		}
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

		<c:if test="${empty doctorTypeSelectMap}">
		var docType = $("input[name='doctorTypeIdList']");
		docType.each(function(index, domEle){
			$(domEle).attr("checked","checked");
		});
		</c:if>
	});
	<%--function defaultImg(img){--%>
		<%--img.src = "<s:url value='/css/skin/up-pic.jpg'/>";--%>
	<%--}--%>
	function toPage(page) {
		$("#currentPage").val(page);			
		search();
	}
	function info(doctorFlow,processFlow){
		var url="<s:url value='/res/teacher/details'/>?doctorFlow="+doctorFlow+"&processFlow="+processFlow;
		jboxOpen(url,"登记数据", 900,410,true);
	}
	function details(doctorFlow,doctorName){
		var url="<s:url value='/res/teacher/schDetails'/>?doctorFlow="+doctorFlow;
		jboxOpen(url,doctorName+"轮转数据详情", 800,410,true);
	}
	</script>
</head>
<body>
<div class="mainright">
  <div class="content">
	  <div class="title1 clearfix">
		  <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
			  <div class="queryDiv">
				  <form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
					  <input id="currentPage" type="hidden" name="currentPage" value=""/>
					  <input type="hidden" name="roleFlag" value="${param.roleFlag}">
					  <input type="hidden" name="onlyOrg" value="${param.onlyOrg}">

					  <div class="inputDiv">
						  <label class="qlable">基&#12288;&#12288;地：</label>
						  <select name="orgFlow" <c:if test="${param.onlyOrg eq 'Y'}">readonly="readonly" </c:if> id="orgFlow" class="qselect" >
							  <option value="">全部</option>
							  <c:forEach items="${orgs}" var="org">
								  <option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							  </c:forEach>
						  </select>
					  </div>

					  <c:if test="${param.onlyOrg eq 'Y'}">
						  <input type="hidden" name="orgFlow" value="${param.orgFlow}">
					  </c:if>
					  <div class="inputDiv">
						  <label class="qlable">姓&#12288;&#12288;名：</label>
						  <input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
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
						  <label class="qlable">年&#12288;&#12288;级：</label>
						  <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								 readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
						  <%--<select name="sessionNumber" class="qselect">--%>
							  <%--<option value="">全部</option>--%>
							  <%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
								  <%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
							  <%--</c:forEach>--%>
						  <%--</select>--%>
					  </div>
					  <div class="inputDiv">
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
					  <div class="qcheckboxDiv">
						  &#12288;&#12288;<label class="qlable">
						  <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
						  仅看轮转中学员
					  </label>
						  &#12288;&#12288;
						  <input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>

					  </div>
				  </form>
			  </div>
		  </c:if>
		  <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN }">
			  <div class="queryDiv">
				  <form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
					  <input id="currentPage" type="hidden" name="currentPage" value=""/>
					  <input type="hidden" name="roleFlag" value="${param.roleFlag}">
					  <div class="inputDiv">
						  <label class="qlable">姓&#12288;&#12288;名：</label>
						  <input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
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
						  <label class="qlable">年&#12288;&#12288;级：</label>
						  <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								 readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
						  <%--<select name="sessionNumber" class="qselect">--%>
							  <%--<option value="">全部</option>--%>
							  <%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
								  <%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
							  <%--</c:forEach>--%>
						  <%--</select>--%>
					  </div>
					  <%--<div class="inputDiv">--%>
						  <%--<label class="qlable">轮转科室：</label>--%>
						  <%--<select class="qselect" name="schDeptFlow">--%>
							  <%--<option value="">全部</option>--%>
							  <%--<c:forEach items="${schDeptList}" var="schDept">--%>
								  <%--<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>--%>
							  <%--</c:forEach>--%>
						  <%--</select>--%>
					  <%--</div>--%>
					  <div class="inputDiv">
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
					  <div class="inputDiv">
						  <label class="qlable">培训基地：</label>
						  <select name="orgFlow"  id="orgFlow" class="qselect" >
							  <c:forEach items="${orgList}" var="org">
								  <option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							  </c:forEach>
						  </select>
					  </div>
					  <div class="qcheckboxDiv">
						  &#12288;&#12288;<label class="qlable">
						  <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
						  轮转中学员
					  </label>
						  &#12288;&#12288;
						  <input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>

					  </div>
				  </form>
			  </div>
		  </c:if>
		  <c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_ADMIN and roleFlag ne GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
			  <div class="queryDiv">
				  <form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
					  <input id="currentPage" type="hidden" name="currentPage" value=""/>
					  <input type="hidden" name="roleFlag" value="${param.roleFlag}">
					  <div class="inputDiv">
						  <label class="qlable">姓&#12288;&#12288;名：</label>
						  <input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
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
						  <label class="qlable">年&#12288;&#12288;级：</label>
						  <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								 readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
						  <%--<select name="sessionNumber" class="qselect">--%>
							  <%--<option value="">全部</option>--%>
							  <%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
								  <%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
							  <%--</c:forEach>--%>
						  <%--</select>--%>
					  </div>
					  <c:if test="${roleFlag ne GlobalConstant.USER_LIST_UNIVERSITY}">
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
					  <div class="qcheckboxDiv" style="min-width: 115px;max-width: 115px;">
						  &#12288;&#12288;<label class="qlable">
						  <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
						  轮转中学员
					  </label>
					  </div>
					  <div class="lastDiv">
						  <input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>
					  </div>
				  </form>
			  </div>
		  </c:if>
	  </div>
  	<c:set var="preKey" value="res_${preRecTypeEnumPreTrainForm.id}_form_flag"/>
	  <table class="basic" width="100%">
		  <tr>
			  <th style="min-width: 80px;">姓名</th>
			  <th style="min-width: 80px;">证件号</th>
			  <th style="min-width: 50px;">年级</th>
			  <%--<th style="min-width: 100px;">电话</th>--%>
			  <c:if test="${roleFlag eq 'charge' and param.onlyOrg ne 'Y'}">
				  <th style="min-width: 100px;">基地</th>
			  </c:if>
			  <th style="min-width: 100px;">培训专业</th>
			  <th style="min-width: 100px;">对应专业</th>
			  <%--<th style="min-width: 100px;">轮转科室</th>--%>
			  <%--<th style="min-width: 100px;">轮转时间</th>--%>
			  <%--<th style="min-width: 100px;">状态</th>--%>
			  <%--<th style="min-width: 100px;">带教老师</th>--%>
			  <%--<th style="min-width: 100px;">完成比例</th>--%>
			  <th style="min-width: 50px;">登记数据</th>
		  </tr>
		  <c:forEach items="${doctorList}" var="doctor">
			  <tr>
				  <td>
						  ${userMap[doctor.doctorFlow].userName}
				  </td>
				  <td>
						  ${userMap[doctor.doctorFlow].idNo}
				  </td>
				  <td>${doctor.sessionNumber}</td>
				  <c:if test="${roleFlag eq 'charge' and param.onlyOrg ne 'Y'}">
					  <td>${doctor.orgName}</td>
				  </c:if>
				  <td>${doctor.trainingTypeName}</td>
				  <td>${doctor.trainingSpeName}</td>
					  <%--<td>${doctor.schDeptName}</td>--%>
					  <%--<td><c:out value="${doctor.schStartDate}" default="${doctor.schStartDate}"/>~<c:out value="${doctor.schEndDate}" default="${doctor.schEndDate}"/></td>--%>
					  <%--<td style="text-align: center;padding-left: 0px">--%>
					  <%--<c:if test="${(doctor.isCurrentFlag eq 'N')&&(doctor.schFlag eq 'N')}">未入科</c:if>--%>
					  <%--<c:if test="${(doctor.isCurrentFlag eq 'N')&&(doctor.schFlag eq 'Y')}">已出科</c:if>--%>
					  <%--<c:if test="${(doctor.isCurrentFlag eq 'Y')&&(doctor.schFlag eq 'N')}">轮转中</c:if>--%>
					  <%--</td>--%>
					  <%--<td style="text-align: center;padding-left: 0px">${doctor.teacherUserName}</td>--%>
					  <%--<td style="text-align: center;padding-left: 0px">${biliFin[doctor.processFlow]}%</td>--%>
				  <td>
					  <a style="color: blue" onclick="details('${doctor.doctorFlow}','${doctor.doctorName}')">详情</a>
				  </td>
			  </tr>
		  </c:forEach>
		  <c:if test="${empty doctorList}">
			  <tr><td colspan="20">无记录</td></tr>
		  </c:if>
	  </table>

  	<div class="resultDiv">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>	 
	</div>
  </div>
</div>
<div id="yearAuditHome">
	
</div>
</body>
</html>