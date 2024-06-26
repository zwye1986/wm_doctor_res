<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
	}
	.table tr:nth-child(2n) {
		background-color: #fcfcfc;
		transition: all 0.125s ease-in-out 0s;
	}
	.table tr:hover {
		background: #fbf8e9 none repeat scroll 0 0;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
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
	$(document).ready(function(){
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
		jboxStartLoading();
		$("#searchForm").submit();
	}

	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function audit(doctorFlow)
	{
		var width=(window.screen.width)*0.7;
		var height=(window.screen.height)*0.6;
		var url = "<s:url value='/res/graduation/main/admin'/>?doctorFlow="+doctorFlow;
		jboxOpen(url, "结业考核情况记录表", width, height);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix" width="100%" height="100%">
			<form id="searchForm" action="<s:url value='/res/discipleAdminAudit/graduationList'/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
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
						<select id="spe" name="speId" class="qselect">
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
								" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
								<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
								<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
								<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</div>
					</div>
					<c:if test="${(scope eq 'ndkhsh') and (roleScope eq 'admin')}">
						<div class="inputDiv">
							<label class="qlable">师承老师：</label>
							<input type="text" name="teacherName" value="${param.teacherName}" class="qtext"/>
						</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" name="doctorName" value="${param.doctorName}" class="qtext"/>
					</div>
					<c:if test="${roleScope eq 'global'}">
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow" class="qselect">
								<c:forEach items="${orgs}" var="org">
									<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">人员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="lastDiv">
						<input type="button"  value="查&#12288;询" class="searchInput"  onclick="toPage(1);" />
					</div>
				</div>
			</form>
			<br>
			<table class="table" width="100%">
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>培训专业</th>
					<th>对应专业</th>
					<th>年级</th>
					<th>结业考核情况记录表</th>
				</tr>
				<c:forEach items="${list}" var="bean" varStatus="status">
					<tr>
						<td style="text-align: center;padding: 0px;">${status.index+1}</td>
						<td style="text-align: center;padding: 0px;">${bean.sysUser.userName}</td>
						<td style="text-align: center;padding: 0px;">${bean.doctorCategoryName}</td>
						<td style="text-align: center;padding: 0px;">${bean.trainingSpeName}</td>
						<td style="text-align: center;padding: 0px;" >${bean.sessionNumber}</td>
						<td style="text-align: center;padding: 0px;">
							<a style="color: blue;" href="javascript:void(0)" onclick="audit('${bean.doctorFlow}');">审核</a>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty list}">
					<tr><td style="text-align: center;" colspan="6">暂无信息！</td></tr>
				</c:if>
			</table>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>