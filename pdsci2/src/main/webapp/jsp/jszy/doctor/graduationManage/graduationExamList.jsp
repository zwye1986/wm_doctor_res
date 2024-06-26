<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
        toPage3(1);
	});
    function toPage3(page)
    {
        page=page||1;
        var url="<s:url value='/jszy/graduation/searchScore2?role=doctor'/>&doctorFlow=${doctorFlow}"+"&currentPage="+page;
        jboxLoad("examresults",url,true);
    }
	function goToTest() {
        var url = '<s:url value="/res/test/toGraduationTest"/>?userCode=${sysUser.userCode}';
        window.open(url);
    }
    function showErrorInfo(resultId) {
        var url = "<s:url value='/jszy/graduation/showErrorInfo?resultId='/>" + resultId;
        window.open(url);
    }
</script>
<div class="mainright">
	<div class="content">
	<div class="main_hd">
		<h1 class="underline" style="font-size: 16px;">结业理论模拟考核</h1>
	</div>
		<c:if test="${not empty errorMeg1}">
			<font color="red" size="5" style="width:100%;text-align: center;display: inline-block;margin-top: 70px;">${errorMeg1}</font>
		</c:if>
		<c:if test="${empty errorMeg1}">
		<div class="search_table" style="width: 100%;box-sizing: border-box;margin-top: 10px;">
			<c:if test="${not empty errorMeg}">
				<font color="red" size="5">${errorMeg}</font>
			</c:if>
			<c:if test="${empty errorMeg}">
				<table border="0" cellpadding="0" cellspacing="0" class="basic" style="width: 100%;">
					<tr>
						<td style="text-align: center;border-right: none;width: 60%;">${paperName}</td>
						<td style="text-align: center;"><button onclick="goToTest()" class="searchInput" style="margin:0;">开始考试</button></td>
					</tr>
				</table>
			</c:if>
		</div>

			<div id="examresults">

			</div>
		</c:if>
	</div>
</div>
