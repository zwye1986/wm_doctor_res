<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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

<script type="text/javascript">
function detailShow(span,clazz){
	$("font",span).toggle();
	$("."+clazz+"Show").fadeToggle(100);
}

function exportGradeSearchDoc(recFlow){
	<c:if test="${empty processRecList}">
		jboxTip("无评分信息导出！");
		return ;
	</c:if>
	if(!recFlow){
		recFlow = "";
	}
	jboxStartLoading();
	jboxTip("导出中…………");
	var url = "<s:url value='/res/head/exportCheckDetails'/>?roleType=${param.roleType}&recFlow="+recFlow+"&userFlow=${param.userFlow}&startDate=${param.startDate}&endDate=${param.endDate}";
	window.location.href=url;
	jboxEndLoading();

}
</script>
<script type="text/css">

</script>

</head>
<body >
	<div style="max-height: 500px;overflow: auto">
   	 <table class="basic list" width="100%">
   	 	<colgroup>
			<col width="60%"/>
			<col width="15%"/>
			<col width="15%"/>
			<col width="10%"/>
		</colgroup>
   	 	<tr>
   	 		<th style="text-align: left;">&#12288;学员姓名</th>
   	 		<th style="text-align: center;padding-right: 0px;">标准分</th>
   	 		<th style="text-align: center;padding-right: 0px;">总分</th>
			<th style="text-align: center;padding-right: 0px;">
				<a style="color: #459ae9;text-align: center;cursor:pointer;" onclick="exportGradeSearchDoc();">批量导出</a>
			</th>
   	 	</tr>
   	 	<c:forEach items="${processRecList}" var="processRec">
   	 		<c:set var="key" value="${processRec.processFlow}"/>
   	 		<tr>
   	 			<th style="text-align: left;">
   	 				<span style="cursor: pointer;color: blue;font-size: 0.8em;line-height: 5px;" onclick="detailShow(this,'${key}');">
						<%--[<font>展开${processRec.userName}</font><font style="display: none;">收起${processRec.userName}</font>]--%>

						&#12288;<font>${processRec.userName}</font><font style="display: none;">${processRec.userName}</font>
					</span>

   	 			</th>
   	 			<th style="font-weight:normal;text-align: center;padding-right: 0px;">${scoreSumMap[processRec.userFlow]}</th>
 				<th style="text-align: center;padding-right: 0px;">${heJiMap[processRec.processFlow]}</th>
				<th style="font-weight:normal;text-align: center;"><a style="color: #459ae9;cursor:pointer;" onclick="exportGradeSearchDoc('${processRec.recFlow}')">[导出]</a></th>
 			</tr>
 				<c:forEach items="${titleFormList}" var="title">
   					<tr>
 						<td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;font-weight:bold;font-size: 16px;display: none;">${title.name}</td>
 						<td class="${key}Show"  style="font-weight:bold;font-size: 16px;display: none;"></td>
 						<td class="${key}Show"  style="font-weight:bold;font-size: 16px;display: none;"></td>
 					</tr>
   				<c:forEach items="${title.itemList}" var="item">
	 				<tr>
	 					<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
	 					<td class="${key}Show"  style="display: none;text-align: center;padding:0" ><c:out value="${item.score}" default="-"/></td>
						<c:set value="${empty gradeMaps[processRec.processFlow][item.id]['score']?'-':gradeMaps[processRec.processFlow][item.id]['score']}" var="score"></c:set>
	 					<td class="${key}Show"  style="display: none;text-align: center;padding:0">
							<c:if test="${score eq '-'}">
								${score}
							</c:if>
							<c:if test="${!(score eq '-')}">
								<fmt:formatNumber type="number" pattern="0.0" value="${score}"/>
							</c:if>
						</td>
						<td class="${key}Show"  style="font-weight:bold;font-size: 16px;display: none;"></td>
	 				</tr>
				</c:forEach>
				</c:forEach>
   	 	</c:forEach>
   	 	<c:if test="${empty processRecList}">
			<tr>
				<td style="text-align: center;" colspan="4">
					无记录！
				</td>
			</tr>
		</c:if>
   	 </table>
	</div>
	<div style="text-align: center">
	 <input type="button"  class="searchInput" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</body>
</html>