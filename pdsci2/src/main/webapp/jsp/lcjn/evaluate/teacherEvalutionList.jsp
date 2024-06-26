<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<%--<jsp:include page="/jsp/common/htmlhead.jsp">--%>
	<%--<jsp:param name="basic" value="true"/>--%>
	<%--<jsp:param name="jbox" value="true"/>--%>
	<%--<jsp:param name="font" value="true"/>--%>
	<%--<jsp:param name="jquery_form" value="true"/>--%>
	<%--<jsp:param name="jquery_ui_tooltip" value="true"/>--%>
	<%--<jsp:param name="jquery_ui_combobox" value="false"/>--%>
	<%--<jsp:param name="jquery_ui_sortable" value="false"/>--%>
	<%--<jsp:param name="jquery_cxselect" value="false"/>--%>
	<%--<jsp:param name="jquery_scrollTo" value="false"/>--%>
	<%--<jsp:param name="jquery_jcallout" value="false"/>--%>
	<%--<jsp:param name="jquery_validation" value="true"/>--%>
	<%--<jsp:param name="jquery_datePicker" value="true"/>--%>
	<%--<jsp:param name="jquery_fullcalendar" value="false"/>--%>
	<%--<jsp:param name="jquery_fngantt" value="false"/>--%>
	<%--<jsp:param name="jquery_fixedtableheader" value="true"/>--%>
	<%--<jsp:param name="jquery_placeholder" value="true"/>--%>
	<%--<jsp:param name="jquery_iealert" value="false"/>--%>
<%--</jsp:include>--%>
<style type="text/css">
	.selected{background-color: #4195C5;color: white;text-align: center;line-height:35px;}
	.unSelected{background-color: white;color: black;text-align: center;line-height:35px;}
</style>
<script type="text/javascript">
	$(function(){
		$(".unSelected").eq(0).click();
	})
	function change8(div){
		$(".selected").removeClass("selected").addClass("unSelected");
		$(div).removeClass("unSelected").addClass("selected");
		var val = $(div).attr("value");
		jboxLoad("dataArea","<s:url value='/lcjn/evaluate/teacherEvaluation'/>?courseFlow=${param.courseFlow}"+"&teacherFLow="+val,true);
	}
</script>
</head>
<body>
 <div class="mainright">
    <div class="content">
  		<div>
			<c:forEach items="${courseTeas}" var="courseTeaher">
			<div class="unSelected" value="${courseTeaher.userFlow}" style="float: left;width: 120px;height: 35px;border:1px solid #E3E3E3;cursor: pointer" onclick="change8(this)">${courseTeaher.userName}</div>
			</c:forEach>
			<c:if test="${empty courseTeas}">
				<div style="color: red;width: 120px;height: 40px;line-height: 40px">未关联教师</div>
			</c:if>
		</div>
		<div id="dataArea" style="margin-top: 10px">
		</div>
   </div>
</div> 	

</body>
</html>