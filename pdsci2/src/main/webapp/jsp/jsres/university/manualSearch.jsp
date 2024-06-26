<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<html >
<head>
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function () {
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});
		$('#graduationTime').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});
		$('#startDate').datepicker();
		$('#endDate').datepicker();
		changeTrainSpes();
	});

	$(function(){
		if($("#trainOrg").length){
			$("#trainOrg").likeSearchInit({
				clickActive:function(flow){
					$("."+flow).show();
				}
			});
		}
	});
	function toPage(page) {
		$("#currentPage").val(page);
		jboxStartLoading();
		<%--jboxPost("<s:url value='/jsres/doctor/manualSearchForUni'/>",$("#searchForm").serialize(),null,null,false);--%>
		<%--jboxEndLoading();--%>
		var url = "<s:url value='/jsres/doctor/manualSearchForUni'/>";
		jboxPost(url,$("#searchForm").serialize(),function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	function changeStatus(){
		if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#orgFlow").val("");
		}
	}
	function changeTrainSpes(){
		//清空原来专业！！！
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			$(this).clone().appendTo($("#trainingSpeId"));
		});
		return false;
	}
	function daochu(recruitFlow){
		jboxTip("导出中,请稍等...");
		var url = '<s:url value="/jsres/doctor/admindaochu"/>?recruitFlow='+recruitFlow;
		window.location.href = url;
	}
</script>
</head>

<body>
	<div class="main_hd">
		<h2 class="underline">
			学员手册查询
		</h2>
	</div>
	<div class="main_bd" id="div_table_0">
	<div class="div_search">
		<form id="searchForm" action="" method="post">
			<input id="currentPage" type="hidden" name="currentPage"  value="${param.currentPage}"/>
			<input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}" />
			<input id="searchType" type="hidden" name="searchType" value=""/>
			<table style="width:100%">
				<tr>
					<td>培训基地：&nbsp;
						<select class="select" style="width: 106px" name="orgFlow" id="orgFlow" onchange="changeTrainSpes()">
							<option></option>
							<c:forEach items="${orgs}" var="org" varStatus="status">
								<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
					</td>
					<td>年&#12288;&#12288;级：&nbsp;
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"readonly="readonly" class="input" style="width: 100px;margin-left: 0px"/>
					</td>
					<td>培训类别：
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
							<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>培训专业：
						<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 107px;">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
								<option value="${spe.dictId}"
										<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
								>${spe.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>姓&#12288;&#12288;名：
						<input type="text" name="doctorName" class="input" value="${param.doctorName}" style="width: 100px;"/>
					</td>
					<td>培训年限：&nbsp;
						<select name="trainingYears" class="select" style="width:107px;">
							<option value="">全部</option>
							<option value="OneYear" <c:if test="${param.trainingYears eq 'OneYear'}">selected</c:if>>一年</option>
							<option value="TwoYear" <c:if test="${param.trainingYears eq 'TwoYear'}">selected</c:if>>两年</option>
							<option value="ThreeYear" <c:if test="${param.trainingYears eq 'ThreeYear'}">selected</c:if>>三年</option>
						</select>
					</td>
					<td>
						<input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询" style=""/>
					</td>
				</tr>
			</table>
			<div style="height: 10px"></div>
		</form>
	</div>
	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" >
			<colgroup>
				<col style="width: 18%;">
				<col style="width: 10%;">
				<col style="width: 25%;">
				<col style="width: 25%;">
				<col style="width: 10%;">
				<col style="width: 12%;">
			</colgroup>
			<tr>
	        	<th>姓名</th>
				<th>年级</th>
				<th>培训基地</th>
	        	<th>培训专业</th>
				<th>培训年限</th>
				<th>培训手册</th>
			</tr>
			<c:forEach items="${doctorList}" var="doctor">
				<tr>
					<td>${doctor.sysUser.userName}</td>
					<td>${doctor.resDoctor.sessionNumber}</td>
					<td>${doctor.resDoctor.orgName}</td>
					<td>${doctor.speName}</td>
					<td>
						<c:set var="year" value="${doctor.trainYear}"/>
						<c:forEach items="${jsResTrainYearEnumList}" var="dict">
							<c:if test="${dict.id eq year}">
								${dict.name}
							</c:if>
						</c:forEach>
					</td>
					<td>
						<img style="cursor:pointer" onclick="daochu('${doctor.recruitFlow}');"
								 src="<s:url value='/jsp/jsres/images/manual.png'/>">
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorList}">
				<tr>
					<td colspan="6">无记录</td>
				</tr>
			</c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
		</div>
        </div>
	</div>
	<div style="display: none;">
		<select id="WMFirst_select">
			<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="WMSecond_select">
			<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="AssiGeneral_select">
			<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option value="${dict.dictId}"<c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
			</c:forEach>
		</select>

	</div>
</body>
</html>
 