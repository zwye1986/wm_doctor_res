<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	.searchTable{
		width: auto;
	}
	.searchTable td{
		width: auto;
		height: auto;
		line-height: auto;
		text-align: left;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		width:5em;
		height: auto;
		line-height: auto;
		text-align: right;
	}
</style>
<script type="text/javascript">

	$(document).ready(function(){
		toPage2(1);
	});
	function toPage2(page)
	{
		page=page||1;
		var url="<s:url value='/jsres/graduation/searchScore2?role=doctor&doctorFlow='/>${param.doctorFlow}&currentPage="+page;
		jboxLoad("examresults",url,true);
	}
	function goToTest() {
        var url = '<s:url value="/res/test/toGraduationTest"/>?userCode=${sysUser.userCode}';
        window.open(url);
    }
    function showErrorInfo(resultId) {
        var url = "<s:url value='/jsres/graduation/showErrorInfo?resultId='/>" + resultId;
        window.open(url);
    }
</script>
<div class="main_hd">
	<h2 class="underline">结业理论模拟考核</h2>
</div>

	<div class="search_table" style="width: 100%;box-sizing: border-box;margin-top: 10px;">
		<c:if test="${not empty errorMeg}">
			<font color="red" size="5">${errorMeg}</font>
		</c:if>
		<c:if test="${empty errorMeg}">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<td>${paperName}</td>
					<td><button onclick="goToTest()" class="btn" style="color:#459ae9;">开始考试</button></td>
				</tr>
			</table>
		</c:if>
	</div>

<div id="examresults">

</div>