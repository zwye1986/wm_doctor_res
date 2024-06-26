
<script type="text/javascript">
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
			jboxLoad("dataArea","<s:url value='/lcjn/evaluate/teacherEvaluation'/>?courseFlow=${param.courseFlow}&teacherFLow=${param.teacherFLow}&currentPage="+page,true);
		}
	}
</script>

  <table class="xllist" style="min-width: 999px;">
  	<thead>
         <tr>
            <th>序号</th>
            <th>学生姓名</th>
			<c:forEach items="${dictTypeEnumTeacherEvaluationItemList}" var="dict">
				<th>${dict.dictName}</th>
			</c:forEach>
            <th style="width: 45%">课程评价与建议</th>
         </tr>
     </thead>
     <c:forEach items="${teaEvaluates}" var="teaEvaluate" varStatus="s">
		<tr>
	     <td>${s.index+1}</td>
	     <td>${teaEvaluate.doctorName}</td>
		 <c:forEach items="${dictTypeEnumTeacherEvaluationItemList}" var="dict">
			 <c:set var="key" value="${dict.dictId}${teaEvaluate.teaEvaluateFlow}">	</c:set>
			 <c:set var="score" value="${scoreMap[key]}"></c:set>
			 <td id="scores${s.index}${dict.dictId}" align="center">
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
				 <span>☆</span>
			 </td>
			 <script>
				$("#scores${s.index}${dict.dictId} span:lt(${score})").css("color","#ffb60b").text("★");
			 </script>
		 </c:forEach>
		 <td>${empty teaEvaluate.evalContent?"暂无":teaEvaluate.evalContent}</td>
		</tr>
     </c:forEach>
	  <c:if test="${empty teaEvaluates}">
		  <tr>
			  <td colspan="20"> 无信息</td>
		  </tr>
	  </c:if>
  </table>
	<p>
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
	    <c:set var="pageView" value="${pdfn:getPageView2(teaEvaluates, 10)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</p>


