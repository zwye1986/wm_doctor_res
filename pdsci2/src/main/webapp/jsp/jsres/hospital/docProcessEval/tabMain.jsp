<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
</style>
<script type="text/javascript">

	$(function(){
		$("#tags li:first a").click();
	});
	function docProcessEval(form){
		var url="<s:url value='/jsres/hospital/docProcessEvalMain'/>?isQuery=Y";
		jboxLoad("contentMain",url,true);
	}
	//考评指标维护
	function evaluationIndexMaintenance(){
		var url = "<s:url value='/jsres/manage/evaluationIndex'/>";
		jboxLoad("contentMain", url, true);
	}
function tabClick(tag,type){
	$('.tab_select').removeClass('tab_select');
	$(tag).removeClass('tab');
	$(tag).addClass('tab_select');
	if(type=='query')docProcessEval();
	if(type=='wh')evaluationIndexMaintenance();
}
</script>
<div class="main_hd">
	<h2>学员考评查询/考评指标维护</h2>
	<div class="title_tab">
		<ul id="tags">
			<li class="tab" onclick="tabClick(this,'query');"><a>学员考评查询</a></li>
			<li class="tab" onclick="tabClick(this,'wh');"><a>考评指标维护</a></li>
		</ul>
	</div>
</div>
<div id="contentMain" >
</div>