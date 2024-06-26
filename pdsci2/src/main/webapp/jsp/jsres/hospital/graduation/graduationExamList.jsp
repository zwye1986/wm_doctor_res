<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
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
    .page .btn_next{
        height: auto;
    }
	.page .btn_prev{
		height: auto;
	}
</style>
<script type="text/javascript">

	$(document).ready(function(){
		toPage2(1);
	});
function toPage2(page)
{
	page=page||1;
	var url="<s:url value='/jsres/graduation/searchScore2?doctorFlow='/>${param.doctorFlow}&currentPage="+page;
	jboxLoad("examresults",url,true);
}
</script>
<div id="examresults">

</div>
      
