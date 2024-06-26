<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
	if ("${param.tagId}" != "") {
		$("#${param.tagId}").click();
	} else {
		$("li a:first").click();
	}
});

function selectTag(selfObj,url) {
	var selLi = $(selfObj).parent();
	selLi.siblings("li").removeClass("selectTag");
	selLi.addClass("selectTag");
	jboxLoad("tagContent",url,true);
}
</script>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<ul id="tags">
				<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
					<li>
						<a id="tpyssh" onclick="selectTag(this,'<s:url value='/res/doc/backTrainCheck'/>?tagId=tpyssh')" href="javascript:void(0)">退培医师审核</a>
					</li>
				</c:if>
					<li>
						<a id="tpyscx" onclick="selectTag(this,'<s:url value='/res/doc/backTrainInfo?roleFlag=${param.role}'/>&tagId=tpyscx')" href="javascript:void(0)">退培医师查询</a>
					</li>
			</ul>
			<div id="tagContent">
			</div>
		</div>
	</div> 
</div>
</body>
</html>