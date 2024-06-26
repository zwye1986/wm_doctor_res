<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer-jquery.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(function () {
		$('.ratateImg').viewer();
	})
</script>
<div class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<col width="50%"/>
		<col width="50%"/>
		<thead>
		<tr>
			<th>文件名称</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${reductionFiles}" var="file" varStatus="status">
			<tr style="height: 40px">
				<td>${file.fileName}</td>
				<td>
					<span class="ratateImg" style="height: 36px">
							<img  src="${sysCfgMap['upload_base_url']}/${file.filePath}"
								 style="width: 100%;height: 90%;margin-top: 2px">
					</span>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty reductionFiles }">
			<tr>
				<td colspan="2">无记录</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>

