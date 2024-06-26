<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script>
	$(function(){
		getjidiLineTable();
	})
    function getjidiLineTable(){
    	top.jboxStartLoading();
    	jboxGet("<c:url value='/hbres/singup/doctor/jidiLineTable'/>" , {"orgFlow":$("#orgFlow").val()} , function(resp){
    		$("#jidiLineTable").html(resp);
    		top.jboxEndLoading();
    	} , null , false);
    }
</script>
</head>
<body>
<div class="div_search">
    <label>基地名称：</label>
    <select id="orgFlow" style="border:1px solid #d6d7d9; height:34px; line-height:30px; outline:none;font-family: microsoft yahei;" onchange="getjidiLineTable();">
	    <option value=''>请选择</option>
	    <c:forEach items="${hospitals}" var="hospital">
	        <option value='${hospital.orgFlow}'>${hospital.orgName}</option>
	    </c:forEach>
	</select>
</div>
	<div id="jidiLineTable">
	
	</div>
</body>
</html>