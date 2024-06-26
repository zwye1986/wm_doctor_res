<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#currentPage").val("${param.currentPage}");
	});


</script>
	<c:if test="${empty list}">
		<div class="main_bd clearfix" style="width: 100%;margin-left: 15px">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th>带教姓名</th>
					<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
						<th>基地名称</th>
					</c:if>
					<th>科室</th>
					<th>带教应审核量</th>
					<th>带教已审核量</th>
					<th>带教未审核量</th>
					<th>带教审核率</th>
				</tr>
				<tr>
					<td colspan="6" >无记录！</td>
				</tr>
			</table>
		</div>
	</c:if>
	<c:if test="${not empty list}">
		<div class="main_bd clearfix" style="width: 100%;margin-left: 15px">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
			<thead>
				<tr>
					<th>带教姓名</th>
					<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
						<th>基地名称</th>
					</c:if>
					<th>科室</th>
					<th>带教应审核量</th>
					<th>带教已审核量</th>
					<th>带教未审核量</th>
					<th>带教审核率</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="s" varStatus="status">
					<tr class="fixTrTd">
						<td>${s.USER_NAME}</td>
						<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
							<td>${s.ORG_NAME}</td>
						</c:if>
						<td>${s.DEPT_NAME}</td>
						<td>${(empty s.notAudited ? 0 : s.notAudited) + (empty s.isAudited ? 0 : s.isAudited)}</td>
						<td>${empty s.isAudited ? 0 : s.isAudited}</td>
						<td>${empty s.notAudited ? 0 : s.notAudited}</td>
						<td>
							<c:if test="${((empty s.notAudited ? 0 : s.notAudited) + (empty s.isAudited ? 0 : s.isAudited)) != 0}">
								${pdfn:getPercent(s.isAudited, (s.notAudited + s.isAudited))}
							</c:if>
							<c:if test="${((empty s.notAudited ? 0 : s.notAudited) + (empty s.isAudited ? 0 : s.isAudited)) == 0}">
								0.00%
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>

    <div class="page" style="text-align: center">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
