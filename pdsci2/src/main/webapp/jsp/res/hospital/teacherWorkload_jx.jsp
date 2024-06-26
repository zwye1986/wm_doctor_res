<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
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
			$("#teacherWorkloadForm").submit();
		}

		function toPage(page) {
//			if($("input[name='doctorTypeIdList']:checked").length<=0)
//			{
//				jboxTip("请选择学员类型！");
//				return false;
//			}
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

        function toPrint() {
            var url = "<s:url value='/res/manager/exportTeacherWorkload'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#teacherWorkloadForm"), url, null, null, false);
            jboxEndLoading();
        }

		function details(teacherUserFlow,schDeptFlow,leiXin,biaoJi){
			var operStartDate=$('#operStartDate').val();
			var operEndDate=$('#operEndDate').val();
			var doctorTypeIdList = "";
			<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
			if("${doctorTypeSelectMap[type.dictId]}"!="")
			{
				doctorTypeIdList = doctorTypeIdList + "&doctorTypeIdList=${type.dictId}";
			}
			</c:forEach>
			var url="<s:url value='/res/manager/details'/>?teacherUserFlow="+teacherUserFlow+"&biaoJi="+biaoJi+"&schDeptFlow="+schDeptFlow+"&operStartDate="+operStartDate+"&operEndDate="+operEndDate+doctorTypeIdList;
			jboxOpen(url,leiXin,850,450,true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="teacherWorkloadForm" action="<s:url value='/res/manager/teacherWorkload'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>
						<select name="deptFlow" class="qselect">
							<option></option>
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.deptFlow}"
										<c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" name="userName" value="${param.userName}" class="qtext"/>
					</div>
					<div class="inputDiv" style="min-width: 415px;max-width: 415px;">
						<label class="qlable">时间范围：</label>
						<input type="text" id="operStartDate" name="operStartDate" value="${operStartDate}"
							   class="qtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~
						<input type="text" id="operEndDate" name="operEndDate" value="${operEndDate}"
							   class="qtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</div>
					<%--<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>--%>
					<div class="lastDiv" style="min-width: 180px;max-width: 180px;">
						<input class="search" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="search" type="button" value="导&#12288;出" onclick="toPrint();"/>
					</div>
				</div>
			</form>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="basic" style="text-align: center">
				<colgroup>
					<col width="17%"/>
					<col width="15%"/>
					<col width="14%"/>
					<col width="13%"/>
					<col width="15%"/>
					<col width="14%"/>
					<col width="12%"/>
				</colgroup>
				<tr>
					<th style="text-align: center">带教老师</th>
					<th style="text-align: center">轮转科室</th>
					<th style="text-align: center">已审核数</th>
					<th style="text-align: center">未审核数</th>
					<th style="text-align: center">当前带教人数</th>
					<th style="text-align: center">已出科人数</th>
					<th style="text-align: center">总带教学员</th>
				</tr>
				<c:forEach items="${list}" var="map">
					<tr>
						<td style="padding-left: 0px;text-align: center">${map.userName}</td>
						<td style="padding-left: 0px;text-align: center">${map.schDeptName}</td>
						<td style="padding-left: 0px;text-align: center">${auditNumMap[map.userFlow]+0}</td>
						<td style="padding-left: 0px;text-align: center">${notAuditNumMap[map.userFlow]+0}</td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="details('${map.userFlow}','${map.schDeptFlow}','当前带教学员','1');">${currDocNumMap[map.userFlow]+0}</a></td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="details('${map.userFlow}','${map.schDeptFlow}','已出科学员','0');">${schDocNumMap[map.userFlow]+0}</a></td>
						<td style="padding-left: 0px;text-align: center">${allDocNumMap[map.userFlow]+0}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;" colspan="8" style="text-align: center;">无记录!</td>
					</tr>
				</c:if>
			</table>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
		</div>
	</div>
</div>
</body>
</html>


