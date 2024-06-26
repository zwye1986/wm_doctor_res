<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true" />
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<script type="text/javascript">
    function searchGraScore(doctorFlow,userName,trainingSpeName) {
        var url = "<s:url value='/jszy/graduation/searchScore?doctorFlow='/>"+doctorFlow+"&userName="+userName+"&speName="+trainingSpeName;
        jboxOpen(url, "模拟考核详情",1330,500);
    }
</script>

<table class="xllist">
	<tr>
		<th>姓名</th>
		<th>人员类型</th>
		<th>培训专业</th>
		<th>对应专业</th>
		<th>年级</th>
		<th>最高分</th>
		<th>考核次数</th>
		<th>合格率</th>
	</tr>
	<c:forEach items="${examResults}" var="examList">
		<tr>
			<c:forEach items="${list}" var="b">
				<c:if test="${examList.doctorFlow == b.doctorFlow}">
					<td>${b.sysUser.userName}</td>
					<td>${b.doctorTypeName}</td>
					<td>${b.doctorCategoryName}</td>
					<td>${b.trainingSpeName}</td>
					<td>${b.sessionNumber}</td>
					<td>${examList.theoryScore}</td>
					<td><a href="javascript:void(0);" onclick="searchGraScore('${b.doctorFlow}','${b.sysUser.userName}','${b.trainingSpeName}')">${examList.totalNum}</a></td>
					<td>
						<c:forEach items="${daMap}" var="item">
							<c:if test="${examList.doctorFlow == item.key}">
								${item.value}
							</c:if>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>
	<c:if test="${empty examResults}">
		<tr>
			<td colspan="99" >无记录！</td>
		</tr>
	</c:if>
</table>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(examResults)}" scope="request"></c:set>
	<pd:pagination toPage="toPage"/>
</div>
      
