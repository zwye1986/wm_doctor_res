
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<head>
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
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
</style>
<script type="text/javascript">
	$(function(){
		if("${empty param.recTypeId}"=="true"){
			$("#reducationTab li:first").click();
		}
	});
	function setType(flag,obj){
		$("#reducationTab li").removeClass();
		$(obj).addClass("tab_select");
		$("#recTypeId").val(flag);
		dataChange();
	}
	function dataChange(){
		var url = "<s:url value='/res/manager/doctorRecruit/catalogue/doctorCKdetail?'/>" + $("#searchForm").serialize();
		jboxStartLoading();

		jboxLoad("tagContent", url,false);
		jboxEndLoading();
	}
</script>
</head>
<body>
	<div class="main_hd" >
	    <div class="title_tab" style="margin-top: 10px;">
	        <ul id="reducationTab">
				<c:forEach items="${afterRecTypeEnumList}" var="eu">
					<c:set var="key" value="res_${eu.id}_form_flag"/>
					<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[key]}">
						<li class="${param.recTypeId eq eu.id?'tab_select':'tab'}" style="width:auto;text-align: center;height: 60px;"  onclick="setType('${eu.id}',this);"><a>${eu.name}</a></li>
					</c:if>
				</c:forEach>
			</ul>
	    </div>
	</div>
	<div class="div_search">
		<form id="searchForm" method="post">
			<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}">
			<input type="hidden" id="processFlow" name="processFlow" value="${param.processFlow}">
		</form>
	</div>
	<div id="tagContent" style="max-height: 500px;border:0px;overflow: auto;">

	</div>

</body>
</html>