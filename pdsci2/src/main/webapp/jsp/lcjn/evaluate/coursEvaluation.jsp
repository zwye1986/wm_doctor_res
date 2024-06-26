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
<script type="text/javascript">
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
			<%--location.href="<s:url value='/lcjn/evaluate/coursEvaluation'/>?currentPage="+page;--%>
			jboxLoad("courseEvaluationDiv",'<s:url value="/lcjn/evaluate/coursEvaluation"/>?courseFlow=${param.courseFlow}&currentPage='+page,true);
		}
	}
</script>
</head>
<body>

 <div class="mainright">
    <div class="content">
  <table class="xllist" style="margin-top: 10px;min-width: 999px;">
  	<thead>
         <tr>
            <th>序号</th>
            <th>学生姓名</th>
			<c:forEach items="${dictTypeEnumCourseEvaluationItemList}" var="dict">
				<th>${dict.dictName}</th>
			</c:forEach>
            <th style="width: 45%">课程评价与建议</th>
         </tr>
     </thead>
     <c:forEach items="${courseEvaluates}" var="courseEvaluate" varStatus="s">
		<tr>
	     <td>${s.index+1}</td>
	     <td>${courseEvaluate.doctorName}</td>
		 <c:forEach items="${dictTypeEnumCourseEvaluationItemList}" var="dict">
			 <c:set var="key" value="${dict.dictId}${courseEvaluate.evaluateFlow}">	</c:set>
			 <c:set var="score" value="${scoreMap[key]}"></c:set>
			 <td id="score${s.index}${dict.dictId}" align="center">
					 ${scoreMap[dict.dictId]}
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
			 </td>
			 <script>
				$("#score${s.index}${dict.dictId} span:lt(${score})").css("color","#ffb60b").text("★");
			 </script>
		 </c:forEach>
		 <td>${empty courseEvaluate.evalContent?"暂无":courseEvaluate.evalContent}</td>
		</tr>
     </c:forEach>
	  <c:if test="${empty courseEvaluates}">
		  <tr>
			  <td colspan="20"> 无信息</td>
		  </tr>
	  </c:if>
  </table>
	<p>
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
	    <c:set var="pageView" value="${pdfn:getPageView2(courseEvaluates, 10)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</p>
   </div>
</div> 	

</body>
</html>