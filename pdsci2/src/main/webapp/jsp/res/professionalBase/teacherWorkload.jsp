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
	<script type="text/javascript">
		function search(){
			$("#teacherWorkloadForm").submit();
		}

		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

        function toPrint() {
            var url = "<s:url value='/res/manager/exportTeacherWorkload'/>?role=professionalBase";
            jboxTip("导出中…………");
            jboxSubmit($("#teacherWorkloadForm"), url, null, null, false);
            jboxEndLoading();
        }

		function details(teacherUserFlow,schDeptFlow,leiXin,biaoJi){
			var operStartDate=$('#operStartDate').val();
			var operEndDate=$('#operEndDate').val();
			var url="<s:url value='/res/manager/details'/>?teacherUserFlow="+teacherUserFlow+"&biaoJi="+biaoJi+"&schDeptFlow="+schDeptFlow+"&operStartDate="+operStartDate+"&operEndDate="+operEndDate;
			jboxOpen(url,leiXin,850,450,false);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class=" clearfix">
			<div class="queryDiv">
			<form id="teacherWorkloadForm" action="<s:url value='/res/ProfessionalBase/teacherWorkload'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<div class="inputDiv">
					科室：
					<select name="schDeptFlow" class="qselect">
						<option></option>
						<c:forEach items="${deptList}" var="dept">
							<option value="${dept.deptFlow}"
									<c:if test="${param.schDeptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv" style="max-width: 220px;min-width: 220px;">
					姓名：
					<input type="text" name="userName" value="${param.userName}" class="qtext"/>
				</div>
				<div class="inputDiv">
					时间范围：
					<input type="text" id="operStartDate" name="operStartDate" value="${operStartDate}"
						   class="qtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 73px;"/>~<input type="text" id="operEndDate" name="operEndDate" value="${operEndDate}"
						   class="qtext" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 73px;"/>
				</div>
				<div class="inputDiv" style="text-align: left">
					&#12288;
					<input class="search" type="button" value="查&#12288;询" onclick="toPage();"/>
					<input class="search" type="button" value="导&#12288;出" onclick="toPrint();"/>
				</div>
			</form>
			</div>
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
					<th style="text-align: center">科室</th>
					<th style="text-align: center">带教老师</th>
					<th style="text-align: center">已审核数</th>
					<th style="text-align: center">未审核数</th>
					<th style="text-align: center">当前带教人数</th>
					<th style="text-align: center">已出科人数</th>
					<th style="text-align: center">总带教学员</th>
				</tr>
				<c:forEach items="${list}" var="map">
					<tr>
						<td style="padding-left: 0px;text-align: center">${map.schDeptName}</td>
						<td style="padding-left: 0px;text-align: center">${map.userName}</td>
						<td style="padding-left: 0px;text-align: center">${map.auditNumber}</td>
						<td style="padding-left: 0px;text-align: center">${map.notAuditNumber}</td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="details('${map.userFlow}','${map.schDeptFlow}','当前带教学员','1');">${map.isCurrentFlagNumber}</a></td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="details('${map.userFlow}','${map.schDeptFlow}','已出科学员','0');">${map.schFlagNumber}</a></td>
						<td style="padding-left: 0px;text-align: center">${map.allNumber}</td>
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


