
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
	table.xllist a{color: blue;cursor: pointer;}
</style>
<script type="text/javascript">

	function save(recordFlow, agreeFlag){
		var url = "<s:url value='/res/doctorSignin/saveKqInfo'/>?recordFlow="+recordFlow+"&agreeFlag="+agreeFlag+"&roleFlag="+'${roleFlag}';
		jboxOpen(url,"审核意见",500,300,true);
	}
	
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
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value='/res/doctorSignin/appealAuditList/${roleFlag}'/>" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv" style="min-width: 880px;max-width: 880px;">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
						</div>
						<%--<div class="inputDiv">--%>
							<%--<label class="qlable">申诉类型：</label>--%>
							<%--<select name="typeId" class="qselect">--%>
								<%--<option value="">请选择</option>--%>
								<%--<c:forEach var="type" items="${dictTypeEnumAppealTypeList}">--%>
									<%--<option value="${type.dictId}" <c:if test="${param.typeId eq type.dictId}">selected="selected"</c:if> >${type.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</div>--%>
						<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
							<label class="qlable">申诉时间：</label>
							<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
							~
							<input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">审核状态：</label>
							<select name="auditStatusId" class="qselect">
								<option value="">请选择</option>
								<option value="Auditing" ${param.auditStatusId eq 'Auditing' ? 'selected':''} >待审核</option>
								<option value="ManagerPass"${param.auditStatusId eq 'ManagerPass' ? 'selected':''}>审核通过</option>
								<option value="ManagerUnPass"${param.auditStatusId eq 'ManagerUnPass' ? 'selected':''}>审核不通过</option>
							</select>
						</div>
						<div class="lastDiv">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						</div>
					</div>

				</form>
				<div class="resultDiv">
				<table class="xllist">
					<tr>
						<th width="13%">姓名</th>
						<%--<th width="20%">申诉类型</th>--%>
						<th width="27%">申诉时间</th>
						<th width="20%">申诉事由</th>
						<th width="10%">申诉附件</th>
						<th width="15%">审核状态</th>
						<th width="10%">操作</th>
					</tr>
					<c:set var="emptyRecord" value="true"/>
					<c:forEach items="${kqList}" var="kq">
						<c:set var="emptyRecord" value="false"/>
						<tr>
							<td>${kq.doctorName}</td>
							<%--<td>${kq.typeName}</td>--%>
							<td>
									${kq.startDate}~${kq.endDate}
							</td>
							<td>${kq.doctorRemarks}</td>
							<td>
								<c:forEach items="${fileMap[kq.recordFlow]}" var="f">
									<a href="${sysCfgMap['upload_base_url']}/${f.filePath}" target="_blank">${f.fileName}</a>
									<br/>
								</c:forEach>
							</td>
							<td >${kq.auditStatusName}</td>
							<td>
								<c:if test="${empty kq.managerAgreeFlag && 'manager' eq roleFlag}">
									<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
									<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

					<c:if test="${emptyRecord}">
						<tr>
							<td style="text-align: center" colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div class="resultDiv">
		             <c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
		        </div>
			</div>
		</div>
	</div>
</body>
</html>