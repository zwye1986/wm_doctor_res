<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
	function downDoctorFile(fileFlow) {
        var url = "<s:url value='/jsres/doctor/downDoctorFile'/>?fileFlow="+fileFlow;
        window.location.href = url;
    }

</script>
<div class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
		<thead>
			<tr>
				<th>序号</th>
				<th>结业年份</th>
				<th>考试编号</th>
				<th>文件名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${fileList}" var="file" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${file.productFlow}</td>
				<td>${file.fileUpType}</td>
				<td>${file.fileName}</td>
				<td>
					<a class="btn" href="javascript:void(0);" onclick="downDoctorFile('${file.fileFlow}');">下载</a>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty fileList }">
			<tr>
				<td colspan="6">无记录</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>
<div class="page" style="margin-right: 30px">
	<c:set var="pageView" value="${pdfn:getPageView(fileList)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
