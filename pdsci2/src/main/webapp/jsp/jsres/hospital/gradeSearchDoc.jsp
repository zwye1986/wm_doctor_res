<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
	function detailShow(span,clazz){
		$("font",span).toggle();
		$("."+clazz+"Show").fadeToggle(100);
	}
	function exportGradeSearchDoc(recFlow){
		if(!recFlow){
			recFlow = "";
		}
		jboxStartLoading();
		jboxTip("导出中…………");
		var url = "<s:url value='/jsres/manage/exportGradeSearchDoc'/>?gradeRole=${param.gradeRole}&recFlow="+recFlow+"&keyCode=${param.keyCode}&operStartDate=${param.startDate}&operEndDate=${param.endDate}&date=${param.date}";
		window.location.href=url;
		jboxEndLoading();

	}
</script>

</head>
<body>
<!-- 	<div class="main_hd"> -->
<!-- 		<h2>出科评价查看</h2> -->
<!-- 	</div> -->
	
	<div class="search_table" style="height: 440px;overflow: auto;padding: 0;margin: 0px 10px;margin-top: 10px;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="60%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="10%"/>
			</colgroup>
			
			<tr>
				<th style="text-align: left;padding-left: 10px;">
					<c:if test="${param.gradeRole eq 'teacher'}">
						学员姓名
					</c:if>
					<c:if test="${param.gradeRole eq 'head'}">
						学员姓名
					</c:if>
				</th>
				<th>标准分</th>
				<th>总分</th>
				<th><a style="color: #459ae9;" onclick="exportGradeSearchDoc();">批量导出</a></th>
			</tr>
			
			<c:forEach items="${recList}" var="rec">
				<c:set var="key" value="${rec.operUserFlow}${rec.recFlow}"/>
				<tr>
					<th style="font-weight:normal;text-align: left;padding-left: 10px;">
						<span style="cursor: pointer;color: #459ae9;font-size: 0.5em;line-height: 5px;" onclick="detailShow(this,'${key}');">
							[<font class="open">展开</font><font class="close" style="display: none;">收起</font>]
						</span>
						&nbsp;
						${rec.operUserName}
					</th>
					<th style="font-weight:normal;color: #C5C5C5;">${scoreSumMap[key]}</th>
					<th style="font-weight:normal;">${scoreMap[key]}</th>
					<th style="font-weight:normal;"><a style="color: #459ae9;" onclick="exportGradeSearchDoc('${rec.recFlow}')">[导出]</a></th>
				</tr>
				<c:set var="assessCfgList" value="${assessCfgMap[rec.cfgFlow]}"/>
				<c:forEach items="${assessCfgList}" var="title">
					<tr>
						<td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
							${title.name}
						</td>
						<td class="${key}Show"  style="font-weight: bold;display: none;padding-right: 20px;">
						<td class="${key}Show"  style="font-weight: bold;display: none;padding-right: 20px;"></td>
					</tr>
					<c:forEach items="${title.itemList}" var="item">
						<c:set var="scoreKey" value="${key}${item.id}"/>
						<tr>
							<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
							<td class="${key}Show" style="display: none;color: #C5C5C5;">${item.score}</td>
							<td class="${key}Show" style="display: none;">${scoreMap[scoreKey]}</td>
							<td class="${key}Show" style="display: none;"></td>
						</tr>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			<c:if test="${empty recList}">
				<tr>
					<td colspan="3">
						无记录
					</td>
				</tr>
			</c:if>
		</table>
		
	</div>
	<div align="center" style="margin-top: 10px;">
		<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</body>
</html>