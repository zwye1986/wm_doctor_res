<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="false"/>
	<jsp:param name="jquery_placeholder" value="false"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_fixedtable" value="false"/>
</jsp:include>
<style type="text/css">
	.basic tbody th{text-align: left;padding-left: 10px;}
	.basic td.title_td{text-align: right;padding:0;}
	textarea{width: 98%;height: 150px;overflow:scroll;margin: 0px 0px 5px;}
	div.option{height: 30px;line-height: 30px; vertical-align: middle;}
</style>
<script>
function view(processFlow){
	$("."+processFlow).toggle();
}
</script>
</head>
<body>
	<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div class="title1 clearfix">
				<c:forEach items="${processList}" var="process" varStatus="statu"> 
				<table width="100%" cellpadding="0" cellspacing="0" class="basic" style="margin-bottom: 10px;">
					<tr <c:if test="${!statu.first }"> onclick="view('${process.processFlow}');" style="cursor: pointer;"</c:if>>
						<th>${statu.count }、审核时间：${pdfn:transDateTime(process.operTime) } &#12288;&#12288;审核结果：${process.projStatusName}
							<c:if test="${!statu.first }"><span style="float: right;margin-right: 10px;">[<a href="#">点击查看审查意见]</a></span></c:if>
						</th>
					</tr>
					<tr class="${ process.processFlow}" style="display: ${statu.first?'':'none'}"> 
						<td><pre style="font-family: Microsoft Yahei;line-height:25px;">${process.auditContent}</pre></td>
					</tr>
				</table>
				</c:forEach>
				<c:if test="${empty processList }">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<tr><th>暂无审核记录！</th></tr>
					</table>
				</c:if>
		</div>
		<div style="width: 100%;" align="center" >
			<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()"  />
		</div>
</div></div></div>
</body>
</html>