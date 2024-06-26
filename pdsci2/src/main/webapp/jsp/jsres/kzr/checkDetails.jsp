<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
<style type="text/css">

</style>

<script type="text/javascript">
function detailShow(span,clazz){
	$("font",span).toggle();
	$("."+clazz+"Show").fadeToggle(100);
}

</script>
</head>
<body >
 <div class="search_table" style="overflow-x: auto;height: 500px;margin: 10px; padding: 0px;">
   	 <table border="0" cellpadding="0" cellspacing="0" class="grid" style="margin-bottom: 10px;">
   	 	<colgroup>
			<col width="70%"/>
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
   	 	<tr>
   	 		<th style="text-align: left;">学员姓名</th>
   	 		<th >标准分</th>
   	 		<th>总分</th>
   	 	</tr>
   	 	<c:forEach items="${processRecList}" var="processRec">
   	 		<c:set var="key" value="${processRec.processFlow}"/>
   	 		<tr>
   	 			<th style="font-weight:normal;text-align: left;">
   	 				<span style="cursor: pointer;color: #459ae9;font-size: 0.5em;line-height: 5px;" onclick="detailShow(this,'${key}');">
						[<font class="open">展开</font><font class="close" style="display: none;">收起</font>]
					</span>
   	 				${processRec.userName}
   	 			</th>
   	 			<th style="font-weight:normal;color: #C5C5C5;">${scoreSumMap[processRec.userFlow]}</th>
 				<th  style="font-weight:normal;">${heJiMap[processRec.processFlow]}</th>
 			</tr>
 				<c:forEach items="${titleFormList}" var="title">
   					<tr>
 						<td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;font-weight:bold;font-size: 16px;display: none;">${title.name}</td>
 						<td class="${key}Show"  style="font-weight:bold;font-size: 16px;display: none;"></td>
 					</tr>
 						
   				<c:forEach items="${title.itemList}" var="item">
	 				<tr>
	 					<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
	 					<td class="${key}Show"  style="display: none;color: #C5C5C5;" ><c:out value="${item.score}" default="-"/></td>
	 					<td class="${key}Show"  style="display: none;"><c:out value="${gradeMaps[processRec.processFlow][item.id]['score']}" default="-"/></td>
	 				</tr>
				</c:forEach>
				</c:forEach>
   	 	</c:forEach>
   	 	<c:if test="${empty processRecList}">
			<tr>
				<td colspan="3">
					暂无记录！
				</td>
			</tr>
		</c:if>
   	 </table>
   	 	<center> <input type="button"  class="btn_green" value="关&#12288;闭" onclick="top.jboxClose();"/></center>
</div>
</body>
</html>