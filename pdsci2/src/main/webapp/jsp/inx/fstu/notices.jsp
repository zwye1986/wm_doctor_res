<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/fstu/css/font.css'/>" />
<script type="text/javascript">
$(document).ready(function(){
	loadInfoList("${param.columnId}");
});

function loadInfoList(columnId){
	var currentPage = $("#currentPage").val();
	var url = "<s:url value='/inx/fstu/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId=${param.columnId}&currentPage='/>" + currentPage;
	jboxLoad("content", url, true);
}

function toPage(page){
	$("#currentPage").val(page);
	loadInfoList("${param.columnId}");
}
</script>
</head>

<body>
<input type="hidden" id="currentPage" name="currentPage" />
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">成都中医药大学附属医院继续教育管理平台</a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container" id="content">
      <div class="notice">

      </div>
   </div>
 </div>
</div>
</div>
 
<div class="footer_index">
	技术支持：<a href="http://www.njpdkj.com" target="_blank" style="color: white;">南京品德信息网络技术有限公司</a>
</div>

</body>
</html>
