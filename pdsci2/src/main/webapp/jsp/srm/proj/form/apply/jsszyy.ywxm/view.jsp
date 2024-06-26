<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
</jsp:include>
<style type="text/css">
	#fund td{
	 text-align: center;
}
</style>
<script type="text/javascript">
	$(function(){
		$(".redspan").hide();
		$('input').attr("readonly","readonly");
		$('textarea').attr("readonly","readonly");
		$("select").attr("disabled", "disabled");
		$(":checkbox").attr("disabled", "disabled");
		$(":radio").attr("disabled", "disabled");
		$(".ctime").removeAttr("onclick");
	});
	function back() {
		history.back();
	}
</script>
<style type="text/css">
.line {border: none;}
</style>
</head>
<body>
<div id="main">   
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
        <div id="tagContent" style="margin-top:30px;">
            <div class="tagContent selectTag" id="tagContent0" style="padding-top: 0px;">
           	  <jsp:include page="step1.jsp">
					<jsp:param  name="view" value="${ GlobalConstant.FLAG_Y}"/>
           	  </jsp:include>
            </div>

        </div>
    </div>
      </div>
    </div>
</div>
</body>
</html>